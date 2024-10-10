package com.omquark.fluidizationcraft.screen.Dissolvinator;

import com.omquark.fluidizationcraft.Items.FluidizationItems;
import com.omquark.fluidizationcraft.blocks.blockEntity.DissolvinatorBlockEntity;
import com.omquark.fluidizationcraft.blocks.FluidizationBlocks;
import com.omquark.fluidizationcraft.data.ModRecipeDataProvider;
import com.omquark.fluidizationcraft.recipe.DissolvinatorRecipe;
import com.omquark.fluidizationcraft.screen.ModMenuTypes;
import com.omquark.fluidizationcraft.util.EverythingNonNullByDefault;
import com.omquark.fluidizationcraft.util.ModInputSlot;
import com.omquark.fluidizationcraft.util.ModOutputSlotItemHandler;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.*;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.neoforged.neoforge.items.IItemHandler;
import net.neoforged.neoforge.items.SlotItemHandler;
import net.minecraft.core.Direction;

import java.util.List;
import java.util.Objects;

@EverythingNonNullByDefault
public class DissolvinatorMenu extends AbstractContainerMenu {

    public final DissolvinatorBlockEntity blockEntity;
    private final Level level;
    private final ContainerData data;

    private final int INPUT_SLOT = 0;
    private final int OUTPUT_SLOT = 1;
    private final int INPUT_FUEL_SLOT = 2;
    private final int OUT_FUEL_SLOT = 3;

    Slot inputSlot, outputSlot, inFuelSlot, outFuelSlot;


    public DissolvinatorMenu(int containerId, Inventory inv, FriendlyByteBuf extraData) {
        this(containerId, inv, Objects.requireNonNull(inv.player.level().getBlockEntity(extraData.readBlockPos())), new SimpleContainerData(4));
    }

    public DissolvinatorMenu(int containerId, Inventory inv, BlockEntity entity, ContainerData data) {
        super(ModMenuTypes.DISSOLVINATOR_MENU.get(), containerId);
        checkContainerSize(inv, 4);
        blockEntity = ((DissolvinatorBlockEntity) entity);
        this.level = inv.player.level();
        this.data = data;
        addPlayerInventory(inv);
        addPlayerHotBar(inv);

        IItemHandler iItemHandler = level.getCapability(DissolvinatorBlockEntity.ITEM_HANDLER_BLOCK, blockEntity.getBlockPos(), blockEntity.getBlockState(), blockEntity);
        if (iItemHandler == null) return;

        inputSlot = new ModInputSlot(iItemHandler, 0, 55, 24);
        outputSlot = new ModOutputSlotItemHandler(iItemHandler, 1, 114, 24);
        inFuelSlot = new ModInputSlot(iItemHandler, 2, 63, 57);
        outFuelSlot = new ModOutputSlotItemHandler(iItemHandler, 3, 99, 57);

        ((ModInputSlot) inFuelSlot).addAllowableItem(FluidizationItems.VIAL_ACID.get());
        List<Item> items = this.level.getRecipeManager().getAllRecipesFor(ModRecipeDataProvider.DISSOLVINATOR_RECIPE.get()).stream().map(
                recipeHolder -> recipeHolder.value().getInputItem().getItems()[0].getItem()
        ).toList();

        ((ModInputSlot) inputSlot).addAllowableItems(items);


        this.addSlot(inputSlot);
        this.addSlot(outputSlot);
        this.addSlot(inFuelSlot);
        this.addSlot(outFuelSlot);

        addDataSlots(data);
    }

    public int getDataFrom(int pIndex){
        if(pIndex < 0 || pIndex > data.getCount()) return 0;
        return data.get(pIndex);
    }

    public boolean isCrafting() {
        return data.get(0) > 0;
    }

    public int getScaledProgress() {
        int progress = this.data.get(0);
        int maxProgress = this.data.get(1);
        int progressArrowSize = 18;

        return maxProgress != 9 && progress != 0 ? progress * progressArrowSize / maxProgress : 0;
    }

    public int getScaledFuel(){
        int fuel = this.data.get(2);
        int maxFuel = this.data.get(3);
        int tankSize = 50;

        return fuel != 0 ? fuel * tankSize / maxFuel : 0;
    }

    @Override
    public ItemStack quickMoveStack(Player pPlayer, int pIndex) {
        final int INVENTORY_SIZE = 27;
        final int HOTBAR_SIZE = 9;
        final int PLAYER_INVENTORY_COUNT = INVENTORY_SIZE + HOTBAR_SIZE;
        final int TILE_ENTITY_INVENTORY_SIZE = 4;

        Slot sourceSlot = slots.get(pIndex);
        ItemStack sourceStack = sourceSlot.getItem();
        ItemStack sourceCopy = sourceStack.copy();

        if (pIndex >= PLAYER_INVENTORY_COUNT && pIndex <= PLAYER_INVENTORY_COUNT + TILE_ENTITY_INVENTORY_SIZE) { //Moving from entity to player
            if (!moveItemStackTo(sourceStack, 0, PLAYER_INVENTORY_COUNT, false)) {
                return ItemStack.EMPTY;
            }
        } else if (pIndex < PLAYER_INVENTORY_COUNT) { //Moving from the player to entity
            if (blockEntity.acceptsInput(sourceStack.getItem())) { //Moving a recipe item
                if (!moveItemStackTo(sourceStack, PLAYER_INVENTORY_COUNT + INPUT_SLOT, PLAYER_INVENTORY_COUNT + INPUT_SLOT + 1, false)) {
                    return ItemStack.EMPTY;
                }
            } else if (blockEntity.acceptsFuel(sourceStack.getItem())) { //Moving a fuel item
                if (!moveItemStackTo(sourceStack, PLAYER_INVENTORY_COUNT + INPUT_FUEL_SLOT, PLAYER_INVENTORY_COUNT + INPUT_FUEL_SLOT + 1, false)) {
                    return ItemStack.EMPTY;
                }
            } else {
                return ItemStack.EMPTY;
            }
        } else { //INVALID SLOT
            return ItemStack.EMPTY;
        }

        if (sourceStack.getCount() == 0) {
            sourceSlot.set(ItemStack.EMPTY);
        } else {
            sourceSlot.setChanged();
        }
        sourceSlot.onTake(pPlayer, sourceStack);
        return sourceCopy;
    }

    @Override
    public boolean stillValid(Player player) {
        return stillValid(ContainerLevelAccess.create(level, blockEntity.getBlockPos()),
                player, FluidizationBlocks.DISSOLVINATOR_BLOCK.get());
    }



    private void addPlayerInventory(Inventory playerInventory) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 9; j++) {
                this.addSlot(new Slot(playerInventory, j + i * 9 + 9, 9 + j * 18, 85 + i * 18));
//                this.addSlot(new Slot(playerInventory, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
            }
        }
    }

    private void addPlayerHotBar(Inventory playerInventory) {
        for (int i = 0; i < 9; i++) {
            this.addSlot(new Slot(playerInventory, i, 9 + i * 18, 142));
//                this.addSlot(new Slot(playerInventory, i, 8 + i * 18, 142));
        }
    }
}
