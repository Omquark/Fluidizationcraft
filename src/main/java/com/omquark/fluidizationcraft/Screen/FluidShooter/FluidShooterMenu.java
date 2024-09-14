package com.omquark.fluidizationcraft.screen.FluidShooter;

import com.omquark.fluidizationcraft.screen.ModMenuTypes;
import com.omquark.fluidizationcraft.util.EverythingNonNullByDefault;
import com.omquark.fluidizationcraft.util.ModInputSlot;
import com.omquark.fluidizationcraft.util.ModOutputSlotItemHandler;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.inventory.SimpleContainerData;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.neoforged.neoforge.capabilities.ItemCapability;
import net.neoforged.neoforge.common.extensions.IItemStackExtension;
import net.neoforged.neoforge.items.IItemHandler;
import net.neoforged.neoforge.items.ItemStackHandler;
import net.neoforged.neoforge.items.SlotItemHandler;
import org.jetbrains.annotations.Nullable;

@EverythingNonNullByDefault
public class FluidShooterMenu extends AbstractContainerMenu {
    private final Level level;
    private final ContainerData data;

    private static final int SLOT_COUNT = 2;
    private final static int INPUT_SLOT = 0;
    private final static int OUTPUT_SLOT = 1;

    ItemStackHandler itemStackHandler = new ItemStackHandler(SLOT_COUNT);
    public FluidShooterMenu(int containerId, Inventory inv, FriendlyByteBuf extraData) {
        this(containerId, inv, new SimpleContainerData(SLOT_COUNT));
    }

    public FluidShooterMenu(int containerId, Inventory inv, ContainerData data){
        super(ModMenuTypes.FLUID_SHOOTER_MENU.get(), containerId);
        checkContainerSize(inv, SLOT_COUNT);
        this.level = inv.player.level();
        this.data = data;

        addPlayerInventory(inv);
        addPlayerHotBar(inv);

        Slot inputSlot = new ModInputSlot(itemStackHandler, INPUT_SLOT, 47, 37);
        Slot outputSlot = new ModOutputSlotItemHandler(itemStackHandler, OUTPUT_SLOT, 115, 37);

        this.addSlot(inputSlot);
        this.addSlot(outputSlot);

        addDataSlots(data);
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
    }

    @Override
    public boolean stillValid(Player pPlayer) {
        return true;
    }
}
