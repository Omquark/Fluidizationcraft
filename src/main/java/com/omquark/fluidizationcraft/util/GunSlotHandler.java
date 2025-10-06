package com.omquark.fluidizationcraft.util;

import com.omquark.fluidizationcraft.capabilities.FluidShooterState;
import com.omquark.fluidizationcraft.capabilities.FluidShooterStateUtil;
import com.omquark.fluidizationcraft.items.FluidizationItems;
import com.omquark.fluidizationcraft.items.ModVial;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.neoforged.neoforge.items.ItemStackHandler;
import org.jetbrains.annotations.NotNull;

public class GunSlotHandler extends ItemStackHandler {
    private final ItemStack gun;

    public static final int SLOT_COUNT = 2;
    public static final int INPUT_SLOT = 0;
    public static final int OUTPUT_SLOT = 1;

    public GunSlotHandler(ItemStack gun) {
        super(SLOT_COUNT);
        this.gun = gun;
    }

    @Override
    public @NotNull ItemStack getStackInSlot(int slot) {
        ItemStack result = ItemStack.EMPTY;
        FluidShooterState state = FluidShooterStateUtil.get(this.gun);
        switch (slot) {
            case (0) -> result = state.input().orElse(ItemStack.EMPTY);
            case (1) -> result = state.output().orElse(ItemStack.EMPTY);
//            default -> result = ItemStack.EMPTY;
        }

        return result;
    }

    @Override
    public void setStackInSlot(int slot, @NotNull ItemStack stack) {
        FluidShooterState state = FluidShooterStateUtil.get(gun);
        switch (slot) {
            case (0) -> {
//                FluidShooterStateUtil.set(gun, new FluidShooterState(stack.copy(), new ItemStack(FluidizationItems.VIAL_EMPTY, 1), state.fluidId(), state.amount()));
//                FluidShooterStateUtil.set(gun, new FluidShooterState(state.input(), state.output(), state.fluidId(), state.amount()));
            }
            case (1) -> {
//                FluidShooterStateUtil.set(gun, new FluidShooterState(new ItemStack(FluidizationItems.VIAL_EMPTY, 1), stack.copy(), state.fluidId(), state.amount()));
//                FluidShooterStateUtil.set(gun, new FluidShooterState(state.input(), state.output(), state.fluidId(), state.amount()));
            }
            default -> {
            }
        }
//        FluidShooterStateUtil.set(gun, new FluidShooterState(state.input(), state.output(), state.fluidId(), state.amount()));
    }

    @Override
    protected void onContentsChanged(int slot) {
    }

    @Override
    public boolean isItemValid(int slot, @NotNull ItemStack stack) {
        return stack.getItem() instanceof ModVial && slot == 0;
    }

    @Override
    public @NotNull ItemStack insertItem(int slot, @NotNull ItemStack stack, boolean simulate) {
        if (slot != 0) return stack;
        if (stack.isEmpty() || !isItemValid(slot, stack)) return stack;
        ItemStack current = getStackInSlot(slot);
        if (!current.isEmpty()) return current;
        int slotCount = current.getCount();
        int toFill = current.getMaxStackSize() - slotCount;
        if (current.isEmpty()) toFill = stack.getMaxStackSize();
        int moveAmount = Math.min(toFill, stack.getCount());
        if (!simulate) setStackInSlot(slot, stack.copyWithCount(moveAmount));
        ItemStack remainder = stack.copy();
        remainder.shrink(moveAmount);
        return remainder;
    }

    @Override
    public @NotNull ItemStack extractItem(int slot, int amount, boolean simulate) {
        ItemStack current = getStackInSlot(slot);
        if (current.isEmpty() || amount <= 0) return ItemStack.EMPTY;
        ItemStack out = current.copyWithCount(Math.min(amount, current.getCount()));
        if (!simulate) setStackInSlot(0, ItemStack.EMPTY);
        return out;
    }
}
