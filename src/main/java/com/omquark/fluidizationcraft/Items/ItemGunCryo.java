package com.omquark.fluidizationcraft.Items;

import com.omquark.fluidizationcraft.entity.CryoShotProjectile;
import com.omquark.fluidizationcraft.util.EverythingNonNullByDefault;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

@EverythingNonNullByDefault
public class ItemGunCryo extends Item {
    public ItemGunCryo(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {

        if (!level.isClientSide) {
            CryoShotProjectile cryoShot = new CryoShotProjectile(level, player, player.getItemInHand(hand), new ItemStack(this));
            cryoShot.shootFromRotation(player, player.getXRot(), player.getYRot(), 0.0f, 3.0f, 0.0f);
            level.addFreshEntity(cryoShot);
        }
        return InteractionResultHolder.fail(player.getItemInHand(hand));
    }
}
