package com.omquark.fluidizationcraft.Blocks;

import com.omquark.fluidizationcraft.DamageTypes.FluidizationDamageTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.FlowingFluid;

import java.util.function.Supplier;

public class CryoniteBlock extends LiquidBlock {

    public CryoniteBlock(Supplier<? extends FlowingFluid> supplier, Properties properties){
        super(supplier, properties);
    }

    @Override
    protected void entityInside(BlockState state, Level level, BlockPos pos, Entity entity){
        entity.hurt((level.damageSources().source(FluidizationDamageTypes.CRYONITE_DAMAGE)), 1.0f);
    }
}
