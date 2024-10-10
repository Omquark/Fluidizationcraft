package com.omquark.fluidizationcraft.screen.FluidShooter;

import com.omquark.fluidizationcraft.Items.FluidizationItems;
import com.omquark.fluidizationcraft.data.Capability;
import com.omquark.fluidizationcraft.data.items.FluidShooter;
import com.omquark.fluidizationcraft.screen.ModMenuTypes;
import com.omquark.fluidizationcraft.util.EverythingNonNullByDefault;
import com.omquark.fluidizationcraft.util.ModInputSlot;
import com.omquark.fluidizationcraft.util.ModOutputSlotItemHandler;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.*;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.neoforged.neoforge.items.IItemHandler;
import net.neoforged.neoforge.items.ItemStackHandler;

import javax.annotation.Nullable;

@EverythingNonNullByDefault
public class FluidShooterMenu extends AbstractContainerMenu {
    private final Level level;
    private final ContainerData data;

    private static final int SLOT_COUNT = 2;
    private final static int INPUT_SLOT = 0;
    private final static int OUTPUT_SLOT = 1;
//    private final Slot inputSlot, outputSlot;

//    private final ItemStackHandler inventory = new ItemStackHandler(SLOT_COUNT);

    public FluidShooterMenu(int containerId, Inventory inv, @Nullable FriendlyByteBuf extraData) {
        this(containerId, inv, new SimpleContainerData(SLOT_COUNT), new ItemStackHandler(2));
    }

    public FluidShooterMenu(int containerId, Inventory inv, ContainerData data, @Nullable ItemStackHandler stackHandler){
        super(ModMenuTypes.FLUID_SHOOTER_MENU.get(), containerId);
        checkContainerSize(inv, SLOT_COUNT);
        this.level = inv.player.level();
        this.data = data;

        addPlayerInventory(inv);
        addPlayerHotBar(inv);

//        FluidShooter itemHandler = inv.player.getItemInHand(InteractionHand.MAIN_HAND).getCapability(Capability.FLUID_SHOOTER_HANDLER, null);
//        IItemHandler itemHandler = stackHandler;

//        if(itemHandler == null) return;
//
//        inputSlot = new ModInputSlot(itemHandler, INPUT_SLOT, 47, 37);
//        outputSlot = new ModOutputSlotItemHandler(itemHandler, OUTPUT_SLOT, 115, 37);
//
//        this.addSlot(inputSlot);
//        this.addSlot(outputSlot);
//
//        ((ModInputSlot) inputSlot).addAllowableItem(FluidizationItems.VIAL_ACID.get());
//
//        addDataSlots(data);

    }

    public int getDataFrom(int pIndex){
        return this.data.get(pIndex);
    }

    private void addPlayerInventory(Inventory playerInventory){
        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 9; j++){
                this.addSlot(new Slot(playerInventory, j + i * 9 + 9, 9 + j * 18, 85 + i * 18));
//                this.addSlot(new Slot(playerInventory, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
            }
        }
    }

    private void addPlayerHotBar(Inventory playerInventory){
        for(int i = 0; i < 9; i++){
            this.addSlot(new Slot(playerInventory, i, 9 + i * 18, 142));
//            this.addSlot(new Slot(playerInventory, i, 8 + i * 18, 142));
        }
    }

    @Override
    public ItemStack quickMoveStack(Player pPlayer, int pIndex) {
        return ItemStack.EMPTY;
//        final int INVENTORY_SIZE = 27;
//        final int HOTBAR_SIZE = 9;
//        final int PLAYER_INVENTORY_COUNT = INVENTORY_SIZE + HOTBAR_SIZE;
//        final int ITEM_INVENTORY_SIZE = 2;
//
//        Slot sourceSlot = slots.get(pIndex);
//        ItemStack sourceStack = sourceSlot.getItem();
//        ItemStack sourceCopy = sourceStack.copy();
//
//        if (pIndex >= PLAYER_INVENTORY_COUNT && pIndex <= PLAYER_INVENTORY_COUNT + ITEM_INVENTORY_SIZE) { //Moving from entity to player
//            if (!moveItemStackTo(sourceStack, 0, PLAYER_INVENTORY_COUNT, false)) {
//                return ItemStack.EMPTY;
//            }
//        } else if (pIndex < PLAYER_INVENTORY_COUNT) { //Moving from the player to entity
//            if (inputSlot.mayPlace(sourceStack)) { //Moving a recipe item
//                if (!moveItemStackTo(sourceStack, PLAYER_INVENTORY_COUNT + INPUT_SLOT, PLAYER_INVENTORY_COUNT + INPUT_SLOT + 1, false)) {
//                    return ItemStack.EMPTY;
//                }
//            } else {
//                return ItemStack.EMPTY;
//            }
//        } else { //INVALID SLOT
//            return ItemStack.EMPTY;
//        }
//
//        if (sourceStack.getCount() == 0) {
//            sourceSlot.set(ItemStack.EMPTY);
//        } else {
//            sourceSlot.setChanged();
//        }
//        sourceSlot.onTake(pPlayer, sourceStack);
//        return sourceCopy;
    }

    @Override
    public boolean stillValid(Player pPlayer) {
        return !level.isClientSide;
    }

    public int getScaledProgress() {
        int progress = this.data.get(0);
        int maxProgress = this.data.get(1);
        int progressArrowSize = 50;

        return maxProgress != 9 && progress != 0 ? progress * progressArrowSize / maxProgress : 0;
    }

}
