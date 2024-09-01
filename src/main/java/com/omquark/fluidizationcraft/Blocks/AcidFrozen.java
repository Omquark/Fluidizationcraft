package com.omquark.fluidizationcraft.blocks;

import com.mojang.serialization.MapCodec;
import com.omquark.fluidizationcraft.damageTypes.FluidizationDamageTypes;
import net.minecraft.MethodsReturnNonnullByDefault;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.HalfTransparentBlock;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.material.MapColor;

import javax.annotation.ParametersAreNonnullByDefault;

@ParametersAreNonnullByDefault
@MethodsReturnNonnullByDefault
public class AcidFrozen extends HalfTransparentBlock {
    public static final MapCodec<AcidFrozen> CODEC = simpleCodec(block -> new AcidFrozen());

    @Override
    protected MapCodec<? extends AcidFrozen> codec() {
        return CODEC;
    }

    public AcidFrozen() {
        super(BlockBehaviour.Properties.of()
                .mapColor(MapColor.COLOR_LIGHT_GREEN)
                .sound(SoundType.GLASS)
                .friction(.95f)
                .isViewBlocking((blockState, blockGetter, blockPos) -> false)
                .noOcclusion());
    }

    @Override
    public void stepOn(Level level, BlockPos pos, BlockState state, Entity entity) {
        entity.hurt(level.damageSources().source(FluidizationDamageTypes.ACID_DAMAGE), 1f);
        super.stepOn(level, pos, state, entity);
    }
}
