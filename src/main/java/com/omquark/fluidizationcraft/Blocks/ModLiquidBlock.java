package com.omquark.fluidizationcraft.blocks;

import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.damagesource.DamageType;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.material.FlowingFluid;
import net.minecraft.world.level.material.Fluid;

import javax.annotation.Nullable;
import java.util.function.Supplier;

public class ModLiquidBlock extends LiquidBlock {

    private final ResourceKey<DamageType> damageType;

    public ModLiquidBlock(FlowingFluid fluid, BlockBehaviour.Properties properties, @Nullable ResourceKey<DamageType> damageType) {
        super(fluid, properties);
        this.damageType = damageType;
    }

    @Override
    protected void entityInside(BlockState state, Level level, BlockPos pos, Entity entity) {
        if (damageType != null) entity.hurt(level.damageSources().source(damageType), 1.0f);
    }
}