package com.omquark.fluidizationcraft.blocks.blockEntity;

import com.omquark.fluidizationcraft.Items.FluidizationItems;
import com.omquark.fluidizationcraft.data.ModRecipeDataProvider;
import com.omquark.fluidizationcraft.recipe.DissolvinatorRecipe;
import com.omquark.fluidizationcraft.recipe.DissolvinatorRecipeInput;
import com.omquark.fluidizationcraft.screen.Dissolvinator.DissolvinatorMenu;
import com.omquark.fluidizationcraft.util.EverythingNonNullByDefault;
import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.Containers;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.capabilities.BlockCapability;
import net.neoforged.neoforge.items.IItemHandler;
import net.neoforged.neoforge.items.ItemStackHandler;
import org.jetbrains.annotations.Nullable;
import net.minecraft.core.Direction;

import java.util.Optional;

@EverythingNonNullByDefault
public class DissolvinatorBlockEntity extends BlockEntity implements MenuProvider {
    private static final int SLOT_COUNT = 4;
    private static final int INPUT_SLOT = 0;
    private static final int OUTPUT_SLOT = 1;
    private static final int INPUT_FUEL_SLOT = 2;
    private static final int OUTPUT_FUEL_SLOT = 3;
    private final ItemStackHandler itemStackHandler = new ItemStackHandler(SLOT_COUNT);

    public static final BlockCapability<IItemHandler, Void> ITEM_HANDLER_BLOCK = BlockCapability.createVoid(
            ResourceLocation.parse("dissolvinator_item_handler"), IItemHandler.class);

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
                switch (index) {
                    case (0):
                        DissolvinatorBlockEntity.this.progress = value;
                    case (1):
                        DissolvinatorBlockEntity.this.maxProgress = value;
                    case (2):
                        DissolvinatorBlockEntity.this.fuelMb = value;
                    case (3):
                        DissolvinatorBlockEntity.this.maxFuelMb = value;
                }
            }

            @Override
            public int getCount() {
                return SLOT_COUNT;
            }
        };
    }

    @Override
    public Component getDisplayName() {
        return Component.translatable("block.fluidizationcraft.dissolvinator");
    }

    public ItemStackHandler getItemStackHandler() {
        return itemStackHandler;
    }

    public boolean acceptsInput(Item input) {
        Optional<RecipeHolder<DissolvinatorRecipe>> recipeHolder = this.level
                .getRecipeManager()
                .getRecipeFor(
                        ModRecipeDataProvider.DISSOLVINATOR_RECIPE.get(),
                        new DissolvinatorRecipeInput(new ItemStack(input, 64)),
                        level);

        return recipeHolder.isPresent();
    }

    public boolean acceptsFuel(Item fuel) {
        return new ItemStack(fuel).is(FluidizationItems.VIAL_ACID.get());
    }

    public void drops() {
        SimpleContainer inventory = new SimpleContainer(itemStackHandler.getSlots());
        for (int i = 0; i < itemStackHandler.getSlots(); i++) {
            inventory.setItem(i, itemStackHandler.getStackInSlot(i));
        }

        if (this.level != null)
            Containers.dropContents(this.level, this.worldPosition, inventory);
    }

    @Nullable
    @Override
    public AbstractContainerMenu createMenu(int containerId, Inventory playerInventory, Player player) {
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
        itemStackHandler.deserializeNBT(provider, tag.getCompound("inventory"));
        progress = tag.getInt("dissolvinator.progress");
        fuelMb = tag.getInt("dissolvinator.fuelMb");
    }

    public static void tick(Level pLevel, BlockPos pPos, BlockState pState, BlockEntity blockEntity) {
        if (!(blockEntity instanceof DissolvinatorBlockEntity dissolvinatorBlock)) return;

        if (dissolvinatorBlock.hasRecipe()) {
            dissolvinatorBlock.increaseCraftingProcess();
            setChanged(pLevel, pPos, pState);

            if (dissolvinatorBlock.hasProgressFinished()) {
                dissolvinatorBlock.craftItem();
                dissolvinatorBlock.resetProgress();
                dissolvinatorBlock.consumeFuel();
            }
        } else {
            dissolvinatorBlock.resetProgress();
        }

        dissolvinatorBlock.addFuel();
    }

    private void addFuel() {
        ItemStack fuel = itemStackHandler.getStackInSlot(INPUT_FUEL_SLOT).copy();
        ItemStack outFuel = itemStackHandler.getStackInSlot(OUTPUT_FUEL_SLOT).copy();

        if (!fuel.is(FluidizationItems.VIAL_ACID.get()) || //Do not add if not acid
                (!outFuel.is(FluidizationItems.VIAL_EMPTY.get()) && !outFuel.isEmpty()) || //Do not add if output is NOT an empty vial
                (!outFuel.isEmpty() && outFuel.getCount() == outFuel.getMaxStackSize()) || //Do not add if output slot is full
                fuelMb + 1000 > maxFuelMb) { //Do not add if it will go beyond max fuel
            return;
        }

        fuel.shrink(1);
        if (outFuel.isEmpty()) outFuel = new ItemStack(FluidizationItems.VIAL_EMPTY.get(), 1);
        else outFuel.grow(1);
        itemStackHandler.setStackInSlot(INPUT_FUEL_SLOT, fuel);
        itemStackHandler.setStackInSlot(OUTPUT_FUEL_SLOT, outFuel);
        this.fuelMb += 1000;
    }

    private boolean hasRecipe() {
        Optional<RecipeHolder<DissolvinatorRecipe>> recipe = getCurrentRecipe();
        if (recipe.isEmpty()) {
            return false;
        }
        ItemStack result = recipe.get().value().getResultItem(getLevel().registryAccess());
        int requiredCount = recipe.get().value().getInputItem().count();
        boolean canInsertItem = canInsertItemIntoOutputSlot(result);
        boolean hasEnoughInputItems = requiredCount <= itemStackHandler.getStackInSlot(INPUT_SLOT).getCount();

        return canInsertItem && hasEnoughInputItems;
    }

    private Optional<RecipeHolder<DissolvinatorRecipe>> getCurrentRecipe() {
        SimpleContainer inventory = new SimpleContainer(this.itemStackHandler.getSlots());
        for (int i = 0; i < itemStackHandler.getSlots(); i++) {
            inventory.setItem(i, this.itemStackHandler.getStackInSlot(i));
        }

        return this.level
                .getRecipeManager()
                .getRecipeFor(
                        ModRecipeDataProvider.DISSOLVINATOR_RECIPE.get(),
                        new DissolvinatorRecipeInput(itemStackHandler.getStackInSlot(INPUT_SLOT)),
                        level);
    }

    private void craftItem() {
        Optional<RecipeHolder<DissolvinatorRecipe>> recipeHolder = getCurrentRecipe();
        if (recipeHolder.isEmpty()) return;
        ItemStack result = recipeHolder.get().value().getResult();
        int inputCount = recipeHolder.get().value().getInputItem().count();
        this.itemStackHandler.extractItem(INPUT_SLOT, inputCount, false);

        this.itemStackHandler.setStackInSlot(OUTPUT_SLOT, new ItemStack(result.getItem(),
                this.itemStackHandler.getStackInSlot(OUTPUT_SLOT).getCount() + result.getCount()));
    }

    private boolean canInsertItemIntoOutputSlot(ItemStack result) {
        boolean isEmpty = this.itemStackHandler.getStackInSlot(OUTPUT_SLOT).isEmpty();
        boolean itemMatch = this.itemStackHandler.getStackInSlot(OUTPUT_SLOT).is(result.getItem());
        boolean notOverflowStack = this.itemStackHandler.getStackInSlot(OUTPUT_SLOT).getCount() + result.getCount() <= result.getMaxStackSize();
        return isEmpty || itemMatch || notOverflowStack;
    }

    private boolean hasProgressFinished() {
        return progress >= maxProgress;
    }

    private void increaseCraftingProcess() {
        if (fuelMb >= 125) {
            progress++;
        }
    }

    private void resetProgress() {
        progress = 0;
    }

    private void consumeFuel() {
        fuelMb = Math.max(fuelMb - 125, 0);
    }
}