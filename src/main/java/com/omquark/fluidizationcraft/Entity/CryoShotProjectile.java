package com.omquark.fluidizationcraft.entity;

import com.omquark.fluidizationcraft.blocks.FluidizationBlocks;
import com.omquark.fluidizationcraft.util.EverythingNonNullByDefault;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.phys.BlockHitResult;

import javax.annotation.Nullable;
import java.util.concurrent.atomic.AtomicBoolean;

@EverythingNonNullByDefault
public class CryoShotProjectile extends AbstractArrow {
    public CryoShotProjectile(EntityType<? extends AbstractArrow> abstractArrow, Level level) {
        super(abstractArrow, level);
    }

    public CryoShotProjectile(Level level, LivingEntity livingEntity, ItemStack stack, @Nullable ItemStack firedFrom){
        super(ModEntities.CRYO_PROJECTILE.get(), livingEntity, level, stack, firedFrom);
    }

    @Override
    protected void onHitBlock(BlockHitResult hitResult) {
        cryoExplosion(hitResult.getBlockPos());
    }

    private void cryoExplosion(BlockPos pos){
        Explosion explosion = level().explode(
                null,
                pos.getX(),
                pos.getY(),
                pos.getZ(),
                3.0f, Level.ExplosionInteraction.TNT);
        final AtomicBoolean spawnCryo = new AtomicBoolean(false);

        explosion.getToBlow().forEach(blockPos -> {
            int rand = random.nextIntBetweenInclusive(0, 99);
            Block spawnedBlock;
            if(rand == 99 && !spawnCryo.get()){
                spawnedBlock = FluidizationBlocks.FROZEN_CRYONITE_BLOCK.get();
                spawnCryo.set(true);
            } else if (rand < 99 && rand >= 80) {
                spawnedBlock = Blocks.ICE;
            } else {
                spawnedBlock = Blocks.SNOW_BLOCK;
            }
            level().setBlock(blockPos, spawnedBlock.defaultBlockState(), 3);
        });
        this.discard();
    }

    @Override
    protected ItemStack getDefaultPickupItem() {
        return null;
    }
}
