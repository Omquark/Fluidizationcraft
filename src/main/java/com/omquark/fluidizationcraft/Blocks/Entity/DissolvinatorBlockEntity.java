package com.omquark.fluidizationcraft.Blocks.Entity;

import com.omquark.fluidizationcraft.Screen.DissolvinatorMenu;
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
import org.lwjgl.system.windows.INPUT;

import javax.annotation.ParametersAreNonnullByDefault;

@ParametersAreNonnullByDefault
@MethodsReturnNonnullByDefault
public class DissolvinatorBlockEntity extends BlockEntity implements MenuProvider {
    private static final int SLOT_COUNT = 4;
    private final ItemStackHandler itemHandler = new ItemStackHandler(SLOT_COUNT);

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
                    case (INPUT_SLOT) -> DissolvinatorBlockEntity.this.progress;
                    case (OUTPUT_SLOT) -> DissolvinatorBlockEntity.this.maxProgress;
                    case (INPUT_FUEL_SLOT) -> DissolvinatorBlockEntity.this.fuelMb;
                    case (OUTPUT_FUEL_SLOT) -> DissolvinatorBlockEntity.this.maxFuelMb;
                    default -> 0;
                };
            }

            @Override
            public void set(int index, int value) {
                switch (index){
                    case(INPUT_SLOT) : DissolvinatorBlockEntity.this.progress = value;
                    case(OUTPUT_SLOT) : DissolvinatorBlockEntity.this.maxProgress = value;
                    case(INPUT_FUEL_SLOT) : DissolvinatorBlockEntity.this.fuelMb = value;
                    case(OUTPUT_FUEL_SLOT) : DissolvinatorBlockEntity.this.maxFuelMb = value;
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
        lazyItemHandler = LazyOptional.of(() -> itemHandler);

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
        SimpleContainer inventory = new SimpleContainer(itemHandler.getSlots());
        for(int i = 0; i < itemHandler.getSlots(); i++){
            inventory.setItem(i, itemHandler.getStackInSlot(i));
        }

        if(this.level != null)
            Containers.dropContents(this.level, this.worldPosition, inventory);
    }

    @Nullable
    @Override
    public AbstractContainerMenu createMenu(int containerId, Inventory playerInventory, Player player) {
        return new DissolvinatorMenu(containerId, playerInventory, this, this.data);
    }

    @Override
    protected void saveAdditional(CompoundTag tag, HolderLookup.Provider provider) {
        tag.put("inventory", itemHandler.serializeNBT(provider));
        tag.putInt("dissolvinator.progress", progress);
        tag.putInt("dissolvinator.fuelMb", fuelMb);
    }

    @Override
    protected void loadAdditional(CompoundTag tag, HolderLookup.Provider provider) {
        itemHandler.deserializeNBT(provider, tag);
        progress = tag.getInt("dissolvinator.progress");
        fuelMb = tag.getInt("dissolvinator.fuelMb");
    }

    public void tick(Level level, BlockPos pos, BlockState state) {
    }
}
