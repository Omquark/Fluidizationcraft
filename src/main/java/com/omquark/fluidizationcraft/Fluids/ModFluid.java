package com.omquark.fluidizationcraft.fluids;

import com.omquark.fluidizationcraft.FluidizationCraft;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
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
public abstract class ModFluid extends ForgeFlowingFluid {

    private static final int BLOCK_INTERACTION_CHANCE = 15;

    //Used to determine block interactions. This depends on the children to define this and add interactions.
    //Used for blocks vs fluids, not Fluids vs Fluids
    protected HashMap<Block, BlockState> blockInteractions;
    //Used to determine fluid to fluid interactions. Use this instead of the FluidInteractionFactory because it's less confusing
    //and allows for finer grained control (i.e. registering Cryonite to change water to ice will ALSO cause water to change Cryonite to ice)
    protected HashMap<FluidType, BlockState> fluidInteractions;
    protected Supplier<? extends Item> vial;

    protected ModFluid(ModProperties props) {
        super(props);
        this.vial = props.vial;
        fluidInteractions = new HashMap<>();
        blockInteractions = new HashMap<>();
    }

    public Item getVial(){
        return vial != null ? vial.get() : Items.AIR;
    }

    @Override
    protected void randomTick(Level level, BlockPos blockPos, FluidState fluidState, RandomSource random) {
        checkNeighbors(level, blockPos, random);
        super.randomTick(level, blockPos, fluidState, random);
    }

    @Override
    public void tick(Level level, BlockPos blockPos, FluidState state) {
        checkFluidInteractions(level, blockPos);
        super.tick(level, blockPos, state);
    }

    public void setFluidInteractions(HashMap<FluidType, BlockState> fluidInteractions) {
        this.fluidInteractions = fluidInteractions;
    }

    public void setBlockInteractions(HashMap<Block, BlockState> blockInteractions) {
        this.blockInteractions = blockInteractions;
    }

    @Override
    protected boolean canBeReplacedWith(FluidState state, BlockGetter level, BlockPos pos, Fluid fluidIn, Direction direction) {
        return false;
    }

    @Override
    protected boolean isRandomlyTicking() {
        return true;
    }



    protected void checkFluidInteractions(Level level, BlockPos pos){
        if(fluidInteractions.isEmpty()) return;
        Stream.of(Direction.values()).forEach((dir) -> {
            if(dir != Direction.UP) {
                FluidState otherState = level.getFluidState(pos.relative(dir));
                if(otherState != Fluids.EMPTY.defaultFluidState() &&
                        fluidInteractions.containsKey(otherState.getFluidType())){
                    level.setBlock(pos.relative(dir), fluidInteractions.get(otherState.getFluidType()), 255);
                }
            }
        });
    }

    protected void checkNeighbors(Level level, BlockPos pos, RandomSource random) {
        if(blockInteractions.isEmpty()) return;
        Stream.of(Direction.values()).forEach((dir) -> {
            if (dir != Direction.UP) {
                Block otherBlock = level.getBlockState(pos.relative(dir)).getBlock();
                if (blockInteractions.containsKey(otherBlock) && random.nextInt(0, 99) < BLOCK_INTERACTION_CHANCE) {
                    level.setBlock(pos.relative(dir), blockInteractions.get(otherBlock), 255); //Setting 2 updates the block immediately and visually
                    //Setting to 3 updates the block visually AND forces the fluid to update, i.e. when vanishing a block into air, forces the fluid to update as well
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