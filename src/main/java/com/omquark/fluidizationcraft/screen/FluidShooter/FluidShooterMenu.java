package com.omquark.fluidizationcraft.screen.FluidShooter;

import com.omquark.fluidizationcraft.capabilities.FluidShooterState;
import com.omquark.fluidizationcraft.capabilities.FluidShooterStateUtil;
import com.omquark.fluidizationcraft.data.items.FluidShooter;
import com.omquark.fluidizationcraft.fluids.FluidizationFluids;
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
import net.neoforged.neoforge.items.SlotItemHandler;

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

        inputSlot = this.addSlot(new SlotItemHandler(slotHandler, 0, 47, 37));
        outputSlot = this.addSlot(new SlotItemHandler(slotHandler, 0, 115, 37));

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

    private void refreshFromComponent() {
        FluidShooterState state = FluidShooterStateUtil.get(gun);
//        this.inputSlot.set(state.input() != null ? state.input() : ItemStack.EMPTY);
//        this.outputSlot.set(state.output() != null ? state.output() : ItemStack.EMPTY);
        this.amount = state.amount();
        this.capacity = 16000;
    }

    @Override
    public void broadcastChanges() {
//        FluidShooterState state = FluidShooterStateUtil.get(gun);
//        FluidShooterStateUtil.set(gun,
//                new FluidShooterState(this.inputSlot.getItem(), this.outputSlot.getItem(),
//                        ResourceLocation.bySeparator(FluidizationFluids.SOURCE_ACID.get().toString(), ':'),
//                        this.amount));
//        this.inputSlot.set(state.input());
//        this.outputSlot.set(state.output());
//        this.amount = state.amount();
//        this.capacity = 16000;
        refreshFromComponent();
        super.broadcastChanges();
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
//                this.addSlot(new Slot(playerInventory, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
            }
        }
    }

    private void addPlayerHotBar(Inventory playerInventory) {
        for (int i = 0; i < 9; i++) {
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

//    @Override
//    public boolean stillValid(Player pPlayer) {
//        return !level.isClientSide;
//    }

    public int getScaledProgress() {
        int progress = amount;
        int maxProgress = capacity;
        int progressArrowSize = 50;

        return maxProgress != 9 && progress != 0 ? progress * progressArrowSize / maxProgress : 0;
    }

}
