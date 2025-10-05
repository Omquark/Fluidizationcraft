package com.omquark.fluidizationcraft.blocks;

import com.omquark.fluidizationcraft.entity.CryoShotProjectile;
import com.omquark.fluidizationcraft.entity.ModEntities;
import com.omquark.fluidizationcraft.entity.PrimedAcidTNTEntity;
import com.omquark.fluidizationcraft.util.EverythingNonNullByDefault;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.MapColor;

import javax.annotation.Nullable;

@EverythingNonNullByDefault
public class AcidTNTBlock extends TntBlock {
    public AcidTNTBlock() {
        super(BlockBehaviour.Properties.
                ofFullCopy(Blocks.TNT)
                .mapColor(MapColor.COLOR_LIGHT_GREEN)
                .sound(SoundType.WOOL));
    }

    @Override
    public void onCaughtFire(BlockState state, Level level, BlockPos pos, @Nullable Direction facing, @Nullable LivingEntity igniter) {
        if (!level.isClientSide) {
            PrimedAcidTNTEntity tnt = new PrimedAcidTNTEntity(ModEntities.ACID_TNT.get(), level,
                    pos.getX(), pos.getY(), pos.getZ() + 0.5, igniter);
            level.addFreshEntity(tnt);
            level.removeBlock(pos, false);
        }
    }

    @Override
    public void neighborChanged(BlockState state, Level level, BlockPos pos, Block block, BlockPos fromPos, boolean isMoving) {
        if (level.hasNeighborSignal(pos)) {
            onCaughtFire(state, level, pos, null, null);
        }
    }

    @Override
    public void wasExploded(Level pLevel, BlockPos pPos, Explosion pExplosion) {
        PrimedAcidTNTEntity tnt = new PrimedAcidTNTEntity(ModEntities.ACID_TNT.get(), pLevel,
                pPos.getX(), pPos.getY(), pPos.getZ() + 0.5, null);
        tnt.setFuse(20);
        pLevel.addFreshEntity(tnt);
        pLevel.removeBlock(pPos, false);
    }
}
