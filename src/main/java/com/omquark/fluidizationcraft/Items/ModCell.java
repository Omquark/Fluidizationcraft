package com.omquark.fluidizationcraft.Items;

import com.omquark.fluidizationcraft.FluidizationCraft;
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
public class ModCell extends Item {
    public ModCell(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        ItemStack handItemStack = player.getItemInHand(hand);
        if (handItemStack.getDamageValue() == handItemStack.getMaxDamage())
            return InteractionResultHolder.fail(handItemStack);
        handItemStack.setDamageValue(handItemStack.getDamageValue() + 1);
        return InteractionResultHolder.sidedSuccess(handItemStack, level.isClientSide);
    }
}
