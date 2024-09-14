package com.omquark.fluidizationcraft.blocks;

import com.omquark.fluidizationcraft.damageTypes.FluidizationDamageTypes;
import com.omquark.fluidizationcraft.util.EverythingNonNullByDefault;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.HalfTransparentBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.MapColor;

@EverythingNonNullByDefault
public class AcidBarrier extends HalfTransparentBlock {
    public AcidBarrier() {
        super(BlockBehaviour.Properties.of()
                .mapColor(MapColor.COLOR_GREEN)
                .sound(SoundType.GLASS)
                .noOcclusion());
    }

    @Override
    public void stepOn(Level level, BlockPos pos, BlockState state, Entity entity) {
        entity.hurt(level.damageSources().source(FluidizationDamageTypes.ACID_DAMAGE), 10f);
        super.stepOn(level, pos, state, entity);
    }
}
