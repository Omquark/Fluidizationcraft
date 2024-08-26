package com.omquark.fluidizationcraft.blocks;

import com.omquark.fluidizationcraft.damageTypes.FluidizationDamageTypes;
import net.minecraft.MethodsReturnNonnullByDefault;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.HalfTransparentBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.MapColor;

import javax.annotation.ParametersAreNonnullByDefault;

@ParametersAreNonnullByDefault
@MethodsReturnNonnullByDefault
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
