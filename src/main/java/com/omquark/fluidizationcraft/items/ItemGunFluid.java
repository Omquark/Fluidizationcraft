package com.omquark.fluidizationcraft.items;

import com.omquark.fluidizationcraft.capabilities.FluidShooterState;
import com.omquark.fluidizationcraft.capabilities.FluidShooterStateUtil;
import com.omquark.fluidizationcraft.dataComponents.ModDataComponents;
import com.omquark.fluidizationcraft.entity.AcidShotProjectile;
import com.omquark.fluidizationcraft.screen.FluidShooter.FluidShooterMenu;
import com.omquark.fluidizationcraft.util.EverythingNonNullByDefault;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.*;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

import javax.annotation.Nullable;
import java.util.Optional;

@EverythingNonNullByDefault
public class ItemGunFluid extends Item {

    private int maxFuel = 16000;
    ContainerData data;

    public ItemGunFluid(Properties properties) {
        super(properties);
    }

    public static FluidShooterState getState(ItemStack stack) {
        return stack.getOrDefault(ModDataComponents.FLUID_SHOOTER_STATE.get(), FluidShooterState.EMPTY);
    }

    public static void setState(ItemStack stack, FluidShooterState state) {
        FluidShooterStateUtil.set(stack, state);
//        stack.set(ModDataComponents.FLUID_SHOOTER_STATE, state);
    }

    @Nullable
    public AbstractContainerMenu createMenu(int containerId, Inventory playerInventory, Player player) {
        FluidShooterState state = getState(player.getItemInHand(InteractionHand.MAIN_HAND));
        data = new ContainerData() {
            @Override
            public int get(int pIndex) {
                return switch (pIndex) {
                    case (0) -> state.amount();
                    case (1) -> ItemGunFluid.this.maxFuel;
                    default -> 0;
                };
            }

            @Override
            public void set(int pIndex, int pValue) {
                switch (pIndex) {
                    case (0) -> {
                        setState(player.getItemInHand(player.getUsedItemHand()),
                                new FluidShooterState(state.input(), state.output(), state.fluidId(), pValue));
                    }
                    case (1) -> ItemGunFluid.this.maxFuel = pValue;
                }
            }

            @Override
            public int getCount() {
                return 2;
            }
        };

        return new FluidShooterMenu(containerId, playerInventory, player.getUsedItemHand());
    }

    public Component getDisplayName() {
        return Component.translatable("item.fluidizationcraft.gun_acid");
    }


    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {

        ItemStack stack = player.getItemInHand(hand);
        FluidShooterState state = getState(stack);

        if (player.isShiftKeyDown()) {
            if (!level.isClientSide() && player instanceof ServerPlayer serverPlayer) {
                serverPlayer.openMenu(new SimpleMenuProvider(this::createMenu, getDisplayName()),
                        buf -> buf.writeEnum(hand)
                );
            }
            return InteractionResultHolder.sidedSuccess(player.getItemInHand(hand), level.isClientSide());
        }

        if (!level.isClientSide) {
            ItemStack fuel = state.input().orElse(ItemStack.EMPTY);
            ItemStack out = state.output().orElse(ItemStack.EMPTY);
            if (!fuel.isEmpty() && fuel.is(ModItems.VIAL_ACID.get())) {
                fuel.shrink(1);
                if(out.isEmpty())
                    out = new ItemStack(ModItems.VIAL_EMPTY.get(), 1);
                else
                    out.grow(1);
                //TODO: Adjust the acid projectile to spawn different fluid depending on what is in the gun
                AcidShotProjectile acidShot = new AcidShotProjectile(level, player, player.getItemInHand(hand), new ItemStack(this));
                //shootFromRotation(player, xRot, yRot, gravity effect?, power <- setting this high will glitch, inaccuracy
                acidShot.shootFromRotation(player, player.getXRot(), player.getYRot(), 0.0f, 3.0f, 0.0f);
                level.addFreshEntity(acidShot);
                setState(stack, new FluidShooterState(Optional.of(fuel), Optional.of(out), state.fluidId(), state.amount()));
            }
            return InteractionResultHolder.success(player.getItemInHand(hand));
        }

        return InteractionResultHolder.fail(player.getItemInHand(hand));
    }

//    @Override
//    public void inventoryTick(ItemStack pStack, Level pLevel, Entity pEntity, int pSlotId, boolean pIsSelected) {
//        if (!pLevel.isClientSide) return;
//        if(getState(pStack).input().isEmpty()) return;
//        addFuel(pStack);
//    }

//    private void addFuel(ItemStack stack) {
//
//        FluidShooterState state = getState(stack);
//
//        ItemStack fuel = (state.input().orElse(ItemStack.EMPTY)).copy();
//        ItemStack outFuel = (state.output().orElse(ItemStack.EMPTY)).copy();
//        Fluid fuelType = BuiltInRegistries.FLUID.get(state.fluidId());
//        int fuelAmount = state.amount();
//
//        ModVial vial;
//        if (outFuel.getCount() >= outFuel.getMaxStackSize()) return;
//        if (!(fuel.getItem() instanceof ModVial)) return;
//        vial = (ModVial) fuel.getItem();
//        if (!fuelType.isSame(Fluids.EMPTY) && !vial.content.isSame(fuelType)) return;
//        if (fuelAmount + 1000 > maxFuel) return;
//        if (fuelType.isSame(Fluids.EMPTY)) fuelType = vial.content;
//
//        fuel.shrink(1);
//        if(outFuel.isEmpty()) outFuel = fuel.copyWithCount(0);
//        if(outFuel.isEmpty()) outFuel = new ItemStack(FluidizationItems.VIAL_EMPTY, 1);
//        else outFuel.grow(1);
//        fuelAmount += 1000;
//
//        state = new FluidShooterState(
//                Optional.of(fuel), Optional.of(outFuel),
//                ResourceLocation.tryBySeparator(fuelType.defaultFluidState().toString(), ':'), fuelAmount);
//
//        setState(stack, state);
//    }
}
