package com.omquark.fluidizationcraft.Blocks.Entity;

import com.omquark.fluidizationcraft.FluidizationCraft;
import com.omquark.fluidizationcraft.Screen.Dissolvinator.DissolvinatorMenu;
import net.minecraft.MethodsReturnNonnullByDefault;
import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.Containers;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.annotation.ParametersAreNonnullByDefault;

@ParametersAreNonnullByDefault
@MethodsReturnNonnullByDefault
public class DissolvinatorBlockEntity extends BlockEntity implements MenuProvider {
    private static final int SLOT_COUNT = 4;
    private final ItemStackHandler itemStackHandler = new ItemStackHandler(SLOT_COUNT);

    private final static int INPUT_SLOT = 0;
    private final static int OUTPUT_SLOT = 1;
    private final static int INPUT_FUEL_SLOT = 2;
    private final static int OUTPUT_FUEL_SLOT = 3;

    private LazyOptional<IItemHandler> lazyItemHandler = LazyOptional.empty();

    protected final ContainerData data;
    private int progress = 0;
    private int maxProgress = 200;
    private int fuelMb = 0;
    private int maxFuelMb = 10000;

    public DissolvinatorBlockEntity(BlockPos blockPos, BlockState state) {
        super(ModBlockEntities.DISSOLVINATOR_ENTITY.get(), blockPos, state);
        this.data = new ContainerData() {
            @Override
            public int get(int index) {
                return switch (index) {
                    case (0) -> DissolvinatorBlockEntity.this.progress;
                    case (1) -> DissolvinatorBlockEntity.this.maxProgress;
                    case (2) -> DissolvinatorBlockEntity.this.fuelMb;
                    case (3) -> DissolvinatorBlockEntity.this.maxFuelMb;
                    default -> 0;
                };
            }

            @Override
            public void set(int index, int value) {
                switch (index){
                    case(0) : DissolvinatorBlockEntity.this.progress = value;
                    case(1) : DissolvinatorBlockEntity.this.maxProgress = value;
                    case(2) : DissolvinatorBlockEntity.this.fuelMb = value;
                    case(3) : DissolvinatorBlockEntity.this.maxFuelMb = value;
                }
            }

            @Override
            public int getCount() {
                return SLOT_COUNT;
            }
        };
    }

    @Override
    public @NotNull <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable net.minecraft.core.Direction side) {
        if(cap == ForgeCapabilities.ITEM_HANDLER){
            return lazyItemHandler.cast();
        }
        return super.getCapability(cap, side);
    }

    @Override
    public void onLoad() {
        super.onLoad();
        lazyItemHandler = LazyOptional.of(() -> itemStackHandler);

    }

    @Override
    public void invalidateCaps() {
        super.invalidateCaps();
        lazyItemHandler.invalidate();
    }

    @Override
    public Component getDisplayName() {
        return Component.translatable("block.fluidizationcraft.dissolvinator");
    }

    public void drops() {
        SimpleContainer inventory = new SimpleContainer(itemStackHandler.getSlots());
        for(int i = 0; i < itemStackHandler.getSlots(); i++){
            inventory.setItem(i, itemStackHandler.getStackInSlot(i));
        }

        if(this.level != null)
            Containers.dropContents(this.level, this.worldPosition, inventory);
    }

    @Nullable
    @Override
    public AbstractContainerMenu createMenu(int containerId, Inventory playerInventory, Player player) {
        FluidizationCraft.LOGGER.debug("Creating DissolvinatorMenu");
        return new DissolvinatorMenu(containerId, playerInventory, this, this.data);
    }

    @Override
    protected void saveAdditional(CompoundTag tag, HolderLookup.Provider provider) {
        tag.put("inventory", itemStackHandler.serializeNBT(provider));
        tag.putInt("dissolvinator.progress", progress);
        tag.putInt("dissolvinator.fuelMb", fuelMb);
    }

    @Override
    protected void loadAdditional(CompoundTag tag, HolderLookup.Provider provider) {
        itemStackHandler.deserializeNBT(provider, tag);
        progress = tag.getInt("dissolvinator.progress");
        fuelMb = tag.getInt("dissolvinator.fuelMb");
    }

    public void tick(Level level, BlockPos pos, BlockState state) {
    }
}
