package com.omquark.fluidizationcraft.fluids;

import com.omquark.fluidizationcraft.blocks.FluidizationBlocks;
import com.omquark.fluidizationcraft.util.EverythingNonNullByDefault;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.material.FluidState;


@EverythingNonNullByDefault
public class ModFluidSourceAcid extends ModFluid.Source {

    private static final float FREEZING_TEMPERATURE = 100f;

    protected ModFluidSourceAcid(ModProperties props) {
        super(props);
    }

    @Override
    public void randomTick(Level level, BlockPos blockPos, FluidState state, RandomSource random) {
        Holder<Biome> currentBiome = level.getBiome(blockPos);
        int y = blockPos.getY() + 1;
        boolean canSeeSky = true;

        for( ; y < 320; y++){
            if(level.getBlockState(blockPos.atY(y)).canOcclude()){
                canSeeSky = false;
                break;
            }
        }

        if (currentBiome.value().coldEnoughToSnow(blockPos) && canSeeSky) {
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
