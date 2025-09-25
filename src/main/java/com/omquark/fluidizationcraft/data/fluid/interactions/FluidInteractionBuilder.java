package com.omquark.fluidizationcraft.data.fluid.interactions;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.material.Fluid;

import java.util.HashMap;
import java.util.Map;


public class FluidInteractionBuilder {
    private final ResourceLocation fluid;
    private final Map<ResourceLocation, ResourceLocation> blockInteractions = new HashMap<>();
    private final Map<ResourceLocation, ResourceLocation> fluidInteractions = new HashMap<>();

    public FluidInteractionBuilder(ResourceLocation fluid){
        this.fluid = fluid;
    }

    public FluidInteractionBuilder block(Block from, Block to){
        blockInteractions.put(BuiltInRegistries.BLOCK.getKey(from), BuiltInRegistries.BLOCK.getKey(to));
        return this;
    }

    public FluidInteractionBuilder fluid(Fluid from, Block to){
        fluidInteractions.put(BuiltInRegistries.FLUID.getKey(from), BuiltInRegistries.BLOCK.getKey(to));
        return this;
    }

    public FluidInteractionJson build(){
        return new FluidInteractionJson(fluid, blockInteractions, fluidInteractions);
    }
}
