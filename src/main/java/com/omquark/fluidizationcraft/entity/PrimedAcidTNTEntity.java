package com.omquark.fluidizationcraft.entity;

import com.omquark.fluidizationcraft.fluids.FluidizationFluids;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.PrimedTnt;
import net.minecraft.world.level.Level;

import javax.annotation.Nullable;

public class PrimedAcidTNTEntity extends PrimedTnt {
    private final @org.jetbrains.annotations.Nullable LivingEntity owner;

    public PrimedAcidTNTEntity(EntityType<? extends PrimedTnt> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
        this.owner = null;
    }

    public PrimedAcidTNTEntity(EntityType<? extends PrimedAcidTNTEntity> type, Level pLevel, double pX, double pY, double pZ, @Nullable LivingEntity pOwner) {
        super(pLevel, pX, pY, pZ, pOwner);
        this.setPos(pX, pY, pZ);
        this.setFuse(80);
        this.owner = pOwner;
    }

    @Override
    protected void explode() {
        if (!level().isClientSide) {
            level().explode(this, getX(), getY(), getZ(), 6.0f, Level.ExplosionInteraction.TNT);

            BlockPos center = this.blockPosition();
            int radius = 10;
            for (BlockPos pos : BlockPos.betweenClosed(center.offset(-radius, -radius, -radius),
                    center.offset(radius, radius, radius))) {
                if (level().random.nextFloat() < 0.001f && level().isEmptyBlock(pos)) {
                    level().setBlock(pos, FluidizationFluids.SOURCE_ACID.get().defaultFluidState().createLegacyBlock(), 3);
                }
            }
        }
    }
}
