package com.omquark.fluidizationcraft.Fluids;

import com.omquark.fluidizationcraft.Blocks.FluidizationBlocks;
import com.omquark.fluidizationcraft.Items.FluidizationItems;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.HashMap;
import java.util.stream.Stream;

@ParametersAreNonnullByDefault
public abstract class AcidFluid extends ModFluid {
    protected AcidFluid(Properties props) {
        super(props);
    }

    public static void addInteractions(){
        blockInteractions = new HashMap<>();
        blockInteractions.put(Blocks.GRASS_BLOCK.defaultBlockState(), Blocks.DIRT.defaultBlockState());
        blockInteractions.put(Blocks.DIRT.defaultBlockState(), Blocks.COARSE_DIRT.defaultBlockState());
        blockInteractions.put(Blocks.SANDSTONE.defaultBlockState(), Blocks.SAND.defaultBlockState());

        fluidInteractions = new HashMap<>();
        fluidInteractions.put(Fluids.WATER.getFluidType(), Blocks.ICE.defaultBlockState());
//        fluidInteractions.put(FluidizationFluidTypes.ACID_FLUID_TYPE.get(),
//                FluidizationBlocks.FROZEN_ACID_BLOCK.get().defaultBlockState());
    }


    @Override
    public Fluid getFlowing() {
        return FluidizationFluids.FLOWING_ACID.get();
    }

    @Override
    public Fluid getSource() {
        return FluidizationFluids.SOURCE_ACID.get();
    }

    @Override
    public Item getBucket() {
        return FluidizationItems.BUCKET_ACID.get();
    }

    @Override
    public int getTickDelay(LevelReader reader) {
        return 2;
    }

    @Override
    protected BlockState createLegacyBlock(FluidState state) {
        return FluidizationBlocks.ACID_BLOCK.get().defaultBlockState().setValue(LiquidBlock.LEVEL, Integer.valueOf(getLegacyLevel(state)));
    }

    @Override
    protected boolean isRandomlyTicking() {
        return true;
    }

    @Override
    protected void randomTick(Level level, BlockPos pos, FluidState fluidState, RandomSource randomSource) {
        int roll = randomSource.nextIntBetweenInclusive(0, 99);
        //TODO: Make configurable
        if (roll >= 50) {
            checkNeighbors(level, pos);
        }

//        super.randomTick(p_230554_, p_230555_, p_230556_, p_230557_);
    }

    @Override
    public void tick(Level level, BlockPos pos, FluidState state) {
        checkFluidInteractions(level, pos);
        super.tick(level, pos, state);
    }

    private void checkFluidInteractions(Level level, BlockPos pos){
        Stream.of(net.minecraft.core.Direction.values()).forEach((dir) -> {
            if(dir != net.minecraft.core.Direction.UP) {
                FluidState otherState = level.getFluidState(pos.relative(dir));
                if(otherState != Fluids.EMPTY.defaultFluidState() && fluidInteractions.containsKey(otherState.getFluidType())){
                    level.setBlock(pos.relative(dir), fluidInteractions.get(otherState.getFluidType()), 2);
                }
            }
        });
    }

    private void checkNeighbors(Level level, BlockPos pos) {
        Stream.of(net.minecraft.core.Direction.values()).forEach((dir) -> {
            if (dir != net.minecraft.core.Direction.UP) {
                BlockState otherState = level.getBlockState(pos.relative(dir));
                if (blockInteractions.containsKey(otherState)) {
                    level.setBlock(pos.relative(dir), blockInteractions.get(otherState), 2); //Setting 2 updates the block
                }
            }
        });
    }

    public static class Flowing extends AcidFluid {

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

    public static class Source extends AcidFluid {

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