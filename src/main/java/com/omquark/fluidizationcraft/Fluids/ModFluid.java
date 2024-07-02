package com.omquark.fluidizationcraft.Fluids;

import net.minecraft.core.BlockPos;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.FluidState;
import net.minecraftforge.fluids.FluidType;
import net.minecraftforge.fluids.ForgeFlowingFluid;
import net.minecraftforge.jarjar.selection.util.Constants;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.HashMap;

@ParametersAreNonnullByDefault
public abstract class ModFluid extends ForgeFlowingFluid implements FluidizationBaseFluid{

    //Used to determine block interactions. This depends on the children to define this and add interactions.
    //Used for blocks vs fluids, not Fluids vs Fluids - Use the FluidInteractionFactory for now...
    protected static HashMap<BlockState, BlockState> blockInteractions;
    protected static HashMap<FluidType, BlockState> fluidInteractions;

    protected ModFluid(Properties props) {
        super(props);
    }

    @Override
    public abstract Fluid getFlowing();
    @Override
    public abstract Fluid getSource();

    @Override
    protected boolean canConvertToSource(Level level) {
        return false;
    }

    @Override
    public boolean canConvertToSource(FluidState state, Level level, BlockPos pos) {
        return false;
    }

    @Override
    protected int getSlopeFindDistance(LevelReader reader) {
        return 4;
    }

    @Override
    protected int getDropOff(LevelReader reader) {
        return 1;
    }

    @Override
    public abstract Item getBucket();

    @Override
    protected boolean canBeReplacedWith(FluidState state, BlockGetter getter, BlockPos pos, Fluid fluid, net.minecraft.core.Direction direction){
        return false;
    }

    @Override
    protected boolean isRandomlyTicking() {
        return false;
    }

    @Override
    protected float getExplosionResistance() {
        return 255f;
    }

    @Override
    protected abstract BlockState createLegacyBlock(FluidState state);
}