package com.omquark.fluidizationcraft.inventory;

import com.omquark.fluidizationcraft.data.DataComponent;
import com.omquark.fluidizationcraft.data.items.FluidShooter;
import com.omquark.fluidizationcraft.screen.FluidShooter.FluidShooterMenu;
import com.omquark.fluidizationcraft.util.EverythingNonNullByDefault;
import net.minecraft.core.NonNullList;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.items.ItemStackHandler;
import org.jetbrains.annotations.Nullable;

@EverythingNonNullByDefault
public class FluidShooterInventory implements MenuProvider {

    private ItemStackHandler inventory;
    private ItemStack stack;
    private int fluidAmount;

    public FluidShooterInventory(ItemStack stack) {
        this.stack = stack;
        fluidAmount = 0;

        if (!this.stack.isEmpty()) {
            loadData();
        }
    }

    @Override
    public Component getDisplayName() {
        return Component.translatable("item.fluidizationcraft.gun_acid");
    }

    @Nullable
    @Override
    public AbstractContainerMenu createMenu(int pContainerId, Inventory pPlayerInventory, Player pPlayer) {
        return new FluidShooterMenu(pContainerId, pPlayerInventory, null);
    }

    public void setStack(ItemStack stack) {
        this.stack = stack;
    }

    public static void openGUI(ServerPlayer player, ItemStack stack) {
        if (!player.level().isClientSide) {
            player.openMenu(new FluidShooterInventory(stack));
        }
    }

    private void saveData() {
        this.stack.set(DataComponent.FLUID_SHOOTER.get(), FluidShooter.fromItems(inventory.getStackInSlot(0), inventory.getStackInSlot(1), fluidAmount));
    }

    private void loadData() {
        FluidShooter fluidShooter = this.stack.getOrDefault(DataComponent.FLUID_SHOOTER.get(), new FluidShooter(ItemStack.EMPTY, ItemStack.EMPTY, 0));
        this.inventory = new ItemStackHandler(NonNullList.withSize(2, ItemStack.EMPTY)){
            @Override
            protected void onContentsChanged(int slot) {
                stack.update(DataComponent.FLUID_SHOOTER.get(),
                        new FluidShooter(ItemStack.EMPTY, ItemStack.EMPTY, 0),
                        (a) -> a.updateSlot(new FluidShooter.Slot(slot, inventory.getStackInSlot(slot))));
                saveData();
            }
        };
        this.inventory.setStackInSlot(0, fluidShooter.input());
        this.inventory.setStackInSlot(1, fluidShooter.output());
        this.fluidAmount = fluidShooter.amount();
    }
}
