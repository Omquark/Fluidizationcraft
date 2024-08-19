package com.omquark.fluidizationcraft.Blocks;

import com.mojang.serialization.MapCodec;
import com.omquark.fluidizationcraft.DamageTypes.FluidizationDamageTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.HalfTransparentBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.MapColor;

import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;

@ParametersAreNonnullByDefault
public class CryoniteFrozen extends HalfTransparentBlock {
    public static final MapCodec<CryoniteFrozen> CODEC = simpleCodec(block -> new CryoniteFrozen());

    @Override
    protected MapCodec<? extends CryoniteFrozen> codec() {
        return CODEC;
    }

    public CryoniteFrozen() {
        super(BlockBehaviour.Properties.of()
                .mapColor(MapColor.COLOR_LIGHT_BLUE)
                .sound(SoundType.GLASS)
                .friction(.95f)
                .isViewBlocking((blockState, blockGetter, blockPos) -> false)
                .noOcclusion()
                .ignitedByLava());
    }

    @Override
    public void stepOn(Level level, BlockPos pos, BlockState state, Entity entity) {
        entity.hurt(level.damageSources().source(FluidizationDamageTypes.CRYONITE_DAMAGE), 1f);
    }
}
