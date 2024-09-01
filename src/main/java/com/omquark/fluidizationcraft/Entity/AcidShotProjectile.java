package com.omquark.fluidizationcraft.entity;

import com.omquark.fluidizationcraft.blocks.FluidizationBlocks;
import com.omquark.fluidizationcraft.FluidizationCraft;
import net.minecraft.MethodsReturnNonnullByDefault;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;

import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;

@ParametersAreNonnullByDefault
@MethodsReturnNonnullByDefault
public class AcidShotProjectile extends AbstractArrow {
    public AcidShotProjectile(EntityType<? extends AbstractArrow> abstractArrow, Level level) {
        super(abstractArrow, level);
    }

    public AcidShotProjectile(Level level, LivingEntity livingEntity, ItemStack stack, @Nullable ItemStack firedFrom) {
        super(ModEntities.ACID_PROJECTILE.get(), livingEntity, level, stack, firedFrom);
    }

    @Override
    protected void onHitBlock(BlockHitResult hitResult) {
        spawnAcid(hitResult.getBlockPos().relative(hitResult.getDirection()));
    }

    @Override
    protected void onHitEntity(EntityHitResult hitResult) {
        spawnAcid(hitResult.getEntity().getOnPos().relative(net.minecraft.core.Direction.UP));
    }

    private void spawnAcid(BlockPos pos) {
        this.level().setBlock(pos, FluidizationBlocks.ACID_BLOCK.get().defaultBlockState(), 3);
        this.discard();
    }

    @Override
    protected ItemStack getDefaultPickupItem() {
        return new ItemStack(Items.AIR);
    }
}
