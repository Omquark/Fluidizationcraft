package com.omquark.fluidizationcraft.Items;

import com.omquark.fluidizationcraft.Entity.AcidShotProjectile;
import net.minecraft.MethodsReturnNonnullByDefault;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

import javax.annotation.ParametersAreNonnullByDefault;

@ParametersAreNonnullByDefault
@MethodsReturnNonnullByDefault
public class ItemGunAcid extends Item {
    public ItemGunAcid(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        final ItemStack cellStack = new ItemStack(FluidizationItems.CELL_ACID.get(), 1);
        ItemStack currentGun = player.getItemInHand(hand);
        int cellSlot;
        ItemStack activeCell;

        if (currentGun.getDamageValue() < currentGun.getMaxDamage()) {
            if (!player.hasInfiniteMaterials()) currentGun.setDamageValue(currentGun.getDamageValue() + 1);
            if (!level.isClientSide) {
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
}
