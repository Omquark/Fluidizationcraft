package com.omquark.fluidizationcraft.Fluids;

import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraftforge.fluids.FluidType;
import net.minecraftforge.fluids.ForgeFlowingFluid;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.HashMap;
import java.util.function.Supplier;
import java.util.stream.Stream;

@ParametersAreNonnullByDefault
public abstract class ModFluid extends ForgeFlowingFluid implements FluidizationBaseFluid{

    //Used to determine block interactions. This depends on the children to define this and add interactions.
    //Used for blocks vs fluids, not Fluids vs Fluids
    protected HashMap<BlockState, BlockState> blockInteractions;
    //Used to determine fluid to fluid interactions. Use this instead of the FluidInteractionFactory because it's less confusing
    //and allows for finer grained control (i.e. registering Cryonite to change water to ice will ALSO cause water to change Cryonite to ice)
    protected HashMap<FluidType, BlockState> fluidInteractions;
    protected Supplier<? extends Item> vial;

    protected ModFluid(ModProperties props) {
        super(props);
        this.vial = props.vial;
    }

    public Item getVial(){
        return vial != null ? vial.get() : Items.AIR;
    }

    @Override
    protected void randomTick(Level level, BlockPos blackPos, FluidState fluidState, RandomSource random) {

        super.randomTick(level, blackPos, fluidState, random);
    }

    public void setFluidInteractions(HashMap<FluidType, BlockState> fluidInteractions) {
        this.fluidInteractions = fluidInteractions;
    }

    public void setBlockInteractions(HashMap<BlockState, BlockState> blockInteractions) {
        this.blockInteractions = blockInteractions;
    }

    @Override
    protected boolean canBeReplacedWith(FluidState state, BlockGetter level, BlockPos pos, Fluid fluidIn, net.minecraft.core.Direction direction) {
        return false;
    }

    protected void checkFluidInteractions(Level level, BlockPos pos){
        Stream.of(net.minecraft.core.Direction.values()).forEach((dir) -> {
            if(dir != net.minecraft.core.Direction.UP) {
                FluidState otherState = level.getFluidState(pos.relative(dir));
                if(otherState != Fluids.EMPTY.defaultFluidState() && fluidInteractions.containsKey(otherState.getFluidType())){
                    level.setBlock(pos.relative(dir), fluidInteractions.get(otherState.getFluidType()), 2);
                }
            }
        });
    }

    protected void checkNeighbors(Level level, BlockPos pos) {
        Stream.of(net.minecraft.core.Direction.values()).forEach((dir) -> {
            if (dir != net.minecraft.core.Direction.UP) {
                BlockState otherState = level.getBlockState(pos.relative(dir));
                if (blockInteractions.containsKey(otherState)) {
                    level.setBlock(pos.relative(dir), blockInteractions.get(otherState), 2); //Setting 2 updates the block
                }
            }
        });
    }


    public static class ModProperties extends ForgeFlowingFluid.Properties {

        private Supplier<? extends Item> vial;
        public ModProperties(Supplier<? extends FluidType> fluidType, Supplier<? extends Fluid> still, Supplier<? extends Fluid> flowing) {
            super(fluidType, still, flowing);
        }

        public ModProperties vial(Supplier<? extends Item> vial){
            this.vial = vial;
            return this;
        }
    }

    public static class Flowing extends ModFluid {

        public Flowing(ModProperties props) {
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

    public static class Source extends ModFluid {

        public Source(ModProperties props) {
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