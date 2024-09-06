package com.omquark.fluidizationcraft.fluids;

import com.omquark.fluidizationcraft.blocks.FluidizationBlocks;
import net.minecraft.MethodsReturnNonnullByDefault;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.material.FluidState;

import javax.annotation.ParametersAreNonnullByDefault;

@MethodsReturnNonnullByDefault
@ParametersAreNonnullByDefault
public class ModFluidSourceAcid extends ModFluid.Source {

    private static final float FREEZING_TEMPERATURE = 100f;

    protected ModFluidSourceAcid(ModProperties props) {
        super(props);
    }

    @Override
    public void tick(Level level, BlockPos blockPos, FluidState state) {
        Holder<Biome> currentBiome = level.getBiome(blockPos);

        if (currentBiome.value().coldEnoughToSnow(blockPos) && level.canSeeSky(blockPos)) {
            level.setBlockAndUpdate(blockPos, FluidizationBlocks.FROZEN_ACID_BLOCK.get().defaultBlockState());
            return;
        }
        super.tick(level, blockPos, state);
    }

//    @Override
//    protected void randomTick(Level level, BlockPos blockPos, FluidState fluidState, RandomSource random) {
//
//
//        super.randomTick(level, blockPos, fluidState, random);
//    }
}
