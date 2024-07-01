package com.omquark.fluidizationcraft.Fluids;

import com.omquark.fluidizationcraft.Blocks.FluidizationBlocks;
import com.omquark.fluidizationcraft.Items.FluidizationItems;
import net.minecraft.core.BlockPos;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.FluidState;

public abstract class CryoniteFluid extends ModFluid {
    protected CryoniteFluid(Properties props) {
        super(props);
    }

    @Override
    public Fluid getFlowing() {
        return FluidizationFluids.FLOWING_CRYONITE.get();
    }

    @Override
    public Fluid getSource() {
        return FluidizationFluids.SOURCE_CRYONITE.get();
    }

    @Override
    public Item getBucket() {
        return FluidizationItems.BUCKET_CRYONITE.get();
    }

    @Override
    public int getTickDelay(LevelReader reader) {
        return 16;
    }

    @Override
    protected BlockState createLegacyBlock(FluidState state) {
        return FluidizationBlocks.CRYONITE_BLOCK.get().defaultBlockState().setValue(LiquidBlock.LEVEL, Integer.valueOf(getLegacyLevel(state)));
    }

    @Override
    protected int getDropOff(LevelReader reader) {
        return 3;
    }

    public static class Flowing extends CryoniteFluid {

        public Flowing(Properties props) {
            super(props);
        }

        @Override
        protected void createFluidStateDefinition(StateDefinition.Builder<Fluid, FluidState> builder) {
            super.createFluidStateDefinition(builder);
            builder.add(LEVEL);
        }

        @Override
        public int getAmount(FluidState state) {
            return state.getValue(LEVEL);
        }

        @Override
        public boolean isSource(FluidState state) {
            return false;
        }
    }

    public static class Source extends CryoniteFluid {

        public Source(Properties props) {
            super(props);
        }

        @Override
        public int getAmount(FluidState state) {
            return 8;
        }

        @Override
        public boolean isSource(FluidState state) {
            return true;
        }
    }

}