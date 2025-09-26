package com.omquark.fluidizationcraft.items;

import com.omquark.fluidizationcraft.FluidizationCraft;
import com.omquark.fluidizationcraft.capabilities.FluidShooterState;
import com.omquark.fluidizationcraft.data.items.FluidShooter;
import com.omquark.fluidizationcraft.dataComponents.ModDataComponents;
import com.omquark.fluidizationcraft.items.FluidizationItems;
import com.omquark.fluidizationcraft.entity.AcidShotProjectile;
import com.omquark.fluidizationcraft.inventory.FluidShooterInventory;
import com.omquark.fluidizationcraft.screen.FluidShooter.FluidShooterMenu;
import com.omquark.fluidizationcraft.util.EverythingNonNullByDefault;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.*;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.Fluids;
import net.neoforged.neoforge.capabilities.ICapabilityProvider;
import net.neoforged.neoforge.items.ItemStackHandler;

import javax.annotation.Nullable;

/**
 * TODO: This item doesn't work, the menu does not display the inventory correctly, thus the recipe is removed
 * Add this recipe when the menu is fixed to add the item back in to be crafted
 */
@EverythingNonNullByDefault
public class ItemGunFluid extends Item {

    private int maxFuel = 16000;

    public ItemGunFluid(Properties properties) {
        super(properties);
    }

    public static FluidShooterState getState(ItemStack stack) {
        return stack.getOrDefault(ModDataComponents.FLUID_SHOOTER_STATE.get(), FluidShooterState.EMPTY);
    }

    public static void setState(ItemStack stack, FluidShooterState state) {
        stack.set(ModDataComponents.FLUID_SHOOTER_STATE, state);
    }

    @Nullable
    public AbstractContainerMenu createMenu(int containerId, Inventory playerInventory, Player player) {
        FluidShooterState state = getState(player.getItemInHand(InteractionHand.MAIN_HAND));
        ContainerData data = new ContainerData() {
            @Override
            public int get(int pIndex) {
                return  switch (pIndex) {
                    case (0) -> state.amount();
                    case (1) -> ItemGunFluid.this.maxFuel;
                    default -> 0;
                };
            }

            @Override
            public void set(int pIndex, int pValue) {
                int fuel = state.amount();
                switch (pIndex) {
                    case (0) -> {
                        fuel = pValue;
                        ItemGunFluid.this.setState(
                                player.getItemInHand(InteractionHand.MAIN_HAND),
                                new FluidShooterState(state.input(), state.output(), state.fluidId(), fuel)
                        );
                    }
                    case (1) -> ItemGunFluid.this.maxFuel = pValue;
                }
            }

            @Override
            public int getCount() {
                return 2;
            }
        };
        return new FluidShooterMenu(containerId, playerInventory, null);
//        return new FluidShooterMenu(containerId, playerInventory, data, null);
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
                serverPlayer.openMenu(new SimpleMenuProvider((id, inv, p) -> createMenu(id, player.getInventory(), player), getDisplayName()));
//                serverPlayer.openMenu(new FluidShooterInventory());
                FluidizationCraft.LOGGER.debug(player.getInventory().getSelected().toString());
                FluidShooterInventory.openGUI(serverPlayer, player.getInventory().getSelected());

            }
            return InteractionResultHolder.sidedSuccess(player.getItemInHand(hand), level.isClientSide());
        }

        if (!level.isClientSide) {
//            if (fuelMb >= 1000) {
//                this.fuelMb -= 1000;
            //TODO: Adjust the acid projectile to spawn different fluid depending on what is in the gun
            AcidShotProjectile acidShot = new AcidShotProjectile(level, player, player.getItemInHand(hand), new ItemStack(this));
            //shootFromRotation(player, xRot, yRot, gravity effect?, power <- setting this high will glitch, inaccuracy
            acidShot.shootFromRotation(player, player.getXRot(), player.getYRot(), 0.0f, 3.0f, 0.0f);
            level.addFreshEntity(acidShot);
//            }
            return InteractionResultHolder.success(player.getItemInHand(hand));
        }

        return InteractionResultHolder.fail(player.getItemInHand(hand));
    }

    @Override
    public void inventoryTick(ItemStack pStack, Level pLevel, Entity pEntity, int pSlotId, boolean pIsSelected) {
        if (!pLevel.isClientSide) return;
        addFuel(pStack);
    }

    private void addFuel(ItemStack stack) {

        FluidShooterState state = getState(stack);

        ItemStack fuel = state.input();
        ItemStack outFuel = state.output();
        Fluid fuelType = BuiltInRegistries.FLUID.get(state.fluidId());
        int fuelAmount = state.amount();

        ModVial vial;
        if (outFuel == null) return;
        if (outFuel.getCount() >= outFuel.getMaxStackSize()) return;
        if (fuel == null) return;
        if (!(fuel.getItem() instanceof ModVial)) return;
        vial = (ModVial) fuel.getItem();
        if (!vial.content.isSame(fuelType)) return;
        if (fuelAmount + 1000 > maxFuel) return;
        if (fuelType.isSame(Fluids.EMPTY)) fuelType = vial.content;

        fuel.shrink(1);
        outFuel.grow(1);
        fuelAmount += 1000;

        setState(stack, new FluidShooterState(fuel, outFuel, ResourceLocation.tryBySeparator(fuelType.defaultFluidState().toString(), ':'), fuelAmount));
    }
}
