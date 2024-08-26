package com.omquark.fluidizationcraft.Items;

import com.omquark.fluidizationcraft.entity.AcidShotProjectile;
import com.omquark.fluidizationcraft.FluidizationCraft;
import com.omquark.fluidizationcraft.screen.FluidShooter.FluidShooterMenu;
import net.minecraft.MethodsReturnNonnullByDefault;
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
import net.minecraftforge.items.ItemStackHandler;
import org.jetbrains.annotations.Nullable;

import javax.annotation.ParametersAreNonnullByDefault;

@ParametersAreNonnullByDefault
@MethodsReturnNonnullByDefault
public class ItemGunFluid extends Item implements MenuProvider {

    protected final ContainerData data;
    private final static int SLOT_COUNT = 2;
    private final static int INPUT_SLOT = 0;
    private final static int OUTPUT_SLOT = 1;
    private int fuelMb = 0;
    private int fuelMaxMb = 16000;
    ItemStackHandler itemStackHandler = new ItemStackHandler(SLOT_COUNT);

    public ItemGunFluid(Properties properties) {
        super(properties);
        this.data = new ContainerData() {
            @Override
            public int get(int index) {
                return switch (index) {
                    case (0) -> ItemGunFluid.this.fuelMb;
                    case (1) -> ItemGunFluid.this.fuelMaxMb;
                    default -> 0;
                };
            }

            @Override
            public void set(int index, int value) {
                switch (index) {
                    case (0) -> ItemGunFluid.this.fuelMb = value;
                    case (1) -> ItemGunFluid.this.fuelMaxMb = value;
                }
            }

            @Override
            public int getCount() {
                return 2;
            }
        };
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        if(player.isShiftKeyDown()) {
            if (!level.isClientSide() && player instanceof ServerPlayer serverPlayer) {
                serverPlayer.openMenu(new SimpleMenuProvider((id, inv, p) -> createMenu(id, player.getInventory(), player), getDisplayName()));
            }
            return InteractionResultHolder.sidedSuccess(player.getItemInHand(hand), level.isClientSide());
        }
        final ItemStack cellStack = new ItemStack(FluidizationItems.CELL_ACID.get(), 1);
        ItemStack currentGun = player.getItemInHand(hand);
        int cellSlot;
        ItemStack activeCell;

        if (currentGun.getDamageValue() < currentGun.getMaxDamage()) {
            if (!player.hasInfiniteMaterials()) currentGun.setDamageValue(currentGun.getDamageValue() + 1);
            if (!level.isClientSide) {
                //TODO: Adjust the acid projectile to spawn different fluid depending on what is in the gun
                AcidShotProjectile acidShot = new AcidShotProjectile(level, player, player.getItemInHand(hand));
                //shootFromRotation(player, xRot, yRot, gravity effect?, power <- setting this high will glitch, inaccuracy
                acidShot.shootFromRotation(player, player.getXRot(), player.getYRot(), 0.0f, 3.0f, 0.0f);
                level.addFreshEntity(acidShot);
            }
            return InteractionResultHolder.success(player.getItemInHand(hand));
        }

        cellSlot = player.getInventory().findSlotMatchingItem(cellStack);
        if (cellSlot < 0) {
            return InteractionResultHolder.fail(player.getItemInHand(hand));
        }

        activeCell = player.getInventory().getItem(cellSlot);
        currentGun.setDamageValue(currentGun.getMaxDamage() - activeCell.getMaxDamage());
        activeCell.shrink(1);
        return InteractionResultHolder.success(player.getItemInHand(hand));
    }

    @Override
    public Component getDisplayName() {
        return Component.translatable("item.fluidizationcraft.gun_acid");
    }

    @Nullable
    @Override
    public AbstractContainerMenu createMenu(int containerId, Inventory playerInventory, Player player) {
        FluidizationCraft.LOGGER.debug("Creating FluidShooterMenu");
        return new FluidShooterMenu(containerId, playerInventory, this.data);
    }

    //    @Override
//    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
//        final ItemStack cellStack = new ItemStack(FluidizationItems.CELL_ACID.get(), 1);
//        ItemStack currentGun = player.getItemInHand(hand);
//        int cellSlot;
//        ItemStack activeCell;
//
//        if (currentGun.getDamageValue() < currentGun.getMaxDamage()) {
//            if (!player.hasInfiniteMaterials()) currentGun.setDamageValue(currentGun.getDamageValue() + 1);
//            if (!level.isClientSide) {
//                AcidShotProjectile acidShot = new AcidShotProjectile(level, player, player.getItemInHand(hand));
//                //shootFromRotation(player, xRot, yRot, gravity effect?, power <- setting this high will glitch, inaccuracy
//                acidShot.shootFromRotation(player, player.getXRot(), player.getYRot(), 0.0f, 3.0f, 0.0f);
//                level.addFreshEntity(acidShot);
//            }
//            return InteractionResultHolder.success(player.getItemInHand(hand));
//        }
//
//        cellSlot = player.getInventory().findSlotMatchingItem(cellStack);
//        if (cellSlot < 0) {
//            return InteractionResultHolder.fail(player.getItemInHand(hand));
//        }
//
//        activeCell = player.getInventory().getItem(cellSlot);
//        currentGun.setDamageValue(currentGun.getMaxDamage() - activeCell.getMaxDamage());
//        activeCell.shrink(1);
//        return InteractionResultHolder.success(player.getItemInHand(hand));
//    }
}
