package com.omquark.fluidizationcraft.screen.FluidShooter;

import com.omquark.fluidizationcraft.capabilities.FluidShooterState;
import com.omquark.fluidizationcraft.capabilities.FluidShooterStateUtil;
import com.omquark.fluidizationcraft.data.items.FluidShooter;
import com.omquark.fluidizationcraft.fluids.FluidizationFluids;
import com.omquark.fluidizationcraft.items.FluidizationItems;
import com.omquark.fluidizationcraft.screen.ModMenuTypes;
import com.omquark.fluidizationcraft.util.EverythingNonNullByDefault;
import com.omquark.fluidizationcraft.util.GunSlotHandler;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.*;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.neoforged.neoforge.items.SlotItemHandler;

import java.util.Optional;
import java.util.function.Consumer;

@EverythingNonNullByDefault
public class FluidShooterMenu extends AbstractContainerMenu {
    private final Player player;
    private final InteractionHand hand;
    private final ItemStack gun;
    private final GunSlotHandler slotHandler;

    private int amount;
    private int capacity;

    private final Slot inputSlot;
    private final Slot outputSlot;

    public FluidShooterMenu(int id, Inventory inv, InteractionHand hand) {
        super(ModMenuTypes.FLUID_SHOOTER_MENU.get(), id);
        this.player = inv.player;
        this.hand = hand;
        this.gun = player.getItemInHand(hand);
        this.slotHandler = new GunSlotHandler(gun);
        addPlayerInventory(inv);
        addPlayerHotBar(inv);

        inputSlot = this.addSlot(
                new SlotItemHandler(slotHandler, 0, 66, 37) {

                    private void updateStateFromSlot(ItemStack stack) {
                        FluidShooterState state = FluidShooterStateUtil.get(gun);
                        FluidShooterStateUtil.set(gun, new FluidShooterState(Optional.of(stack), state.output(), state.fluidId(), state.amount()));
                    }

                    @Override
                    public boolean mayPlace(ItemStack stack) {
                        return stack.is(FluidizationItems.VIAL_ACID.get());
                    }

                    @Override
                    public void set(ItemStack stack) {
                        updateStateFromSlot(stack);
                        super.set(stack);
                    }

                    @Override
                    public void onTake(Player player, ItemStack stack) {
                        super.onTake(player, stack);
                        updateStateFromSlot(ItemStack.EMPTY);
                    }
                });

        outputSlot = this.addSlot(new SlotItemHandler(slotHandler, 1, 99, 37) {

            private void updateStateFromSlot(ItemStack stack) {
                FluidShooterState state = FluidShooterStateUtil.get(gun);
                FluidShooterStateUtil.set(gun, new FluidShooterState(state.input(), Optional.of(stack), state.fluidId(), state.amount()));
            }

            @Override
            public boolean mayPlace(ItemStack stack) {
                return false;
            }

            //TODO: When taking a half stack, it will delete the other half. Fix
            @Override
            public void onTake(Player player, ItemStack stack) {
                super.onTake(player, stack);
                updateStateFromSlot(ItemStack.EMPTY);
            }
        });


        this.addDataSlot(new DataSlot() {
            @Override
            public int get() {
                return amount;
            }

            @Override
            public void set(int pValue) {
                amount = pValue;
            }
        });

        this.addDataSlot(new DataSlot() {
            @Override
            public int get() {
                return capacity;
            }

            @Override
            public void set(int pValue) {
                capacity = pValue;
            }
        });

        refreshFromComponent();
    }

    public FluidShooterMenu(int containerId, Inventory inv, FriendlyByteBuf extraData) {
        this(containerId, inv, inv.player.getUsedItemHand());
    }

    public void refreshFromComponent() {
        FluidShooterState state = FluidShooterStateUtil.get(gun);
        this.inputSlot.set(state.input().orElse(ItemStack.EMPTY));
        this.outputSlot.set(state.output().orElse(ItemStack.EMPTY));
        this.amount = state.amount();
        this.capacity = 16000;
    }

    @Override
    public boolean stillValid(Player p) {
        ItemStack current = p.getItemInHand(hand);
        return ItemStack.isSameItemSameComponents(current, gun);
    }

    public int getAmount() {
        return amount;
    }

    public int getCapacity() {
        return capacity;
    }

    private void addPlayerInventory(Inventory playerInventory) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 9; j++) {
                this.addSlot(new Slot(playerInventory, j + i * 9 + 9, 9 + j * 18, 85 + i * 18));
            }
        }
    }

    private void addPlayerHotBar(Inventory playerInventory) {
        for (int i = 0; i < 9; i++) {
            this.addSlot(new Slot(playerInventory, i, 9 + i * 18, 142));
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
//
//        if (pIndex >= PLAYER_INVENTORY_COUNT && pIndex <= PLAYER_INVENTORY_COUNT + ITEM_INVENTORY_SIZE) { //Moving from entity to player
//            if (!moveItemStackTo(sourceStack, 0, PLAYER_INVENTORY_COUNT, false)) {
//                return ItemStack.EMPTY;
//            }
//        } else if (pIndex < PLAYER_INVENTORY_COUNT) { //Moving from the player to entity
//            if (inputSlot.mayPlace(sourceStack)) { //Moving a recipe item
//                if (!moveItemStackTo(sourceStack, PLAYER_INVENTORY_COUNT + inputSlot.index, PLAYER_INVENTORY_COUNT + inputSlot.index + 1, false)) {
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

    public int getScaledProgress() {
        int progress = amount;
        int maxProgress = capacity;
        int progressArrowSize = 50;

        return maxProgress != 9 && progress != 0 ? progress * progressArrowSize / maxProgress : 0;
    }

}
