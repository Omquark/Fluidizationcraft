package com.omquark.fluidizationcraft.data.fluid.interactions;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.omquark.fluidizationcraft.FluidizationCraft;
import com.omquark.fluidizationcraft.fluids.ModFluid;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.server.packs.resources.SimpleJsonResourceReloadListener;
import net.minecraft.util.profiling.ProfilerFiller;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.Fluids;
import net.neoforged.neoforge.fluids.FluidType;

import java.util.HashMap;
import java.util.Map;


public class FluidInteractionLoader extends SimpleJsonResourceReloadListener {
    private static final Gson GSON = new Gson();
    public static final FluidInteractionLoader INSTANCE = new FluidInteractionLoader();

    private FluidInteractionLoader() {
        super(GSON, "fluid_interactions");
    }

    @Override
    protected void apply(Map<ResourceLocation, JsonElement> jsons,
                         ResourceManager resourceManager,
                         ProfilerFiller profiler) {

        for (Map.Entry<ResourceLocation, JsonElement> entry : jsons.entrySet()) {
            FluidInteractionData interactionData = GSON.fromJson(entry.getValue(), FluidInteractionData.class);
            Fluid fluidSource = BuiltInRegistries.FLUID.get(ResourceLocation.fromNamespaceAndPath(FluidizationCraft.MODID, interactionData.fluid + "_fluid"));
            Fluid fluidFlowing = BuiltInRegistries.FLUID.get(ResourceLocation.fromNamespaceAndPath(FluidizationCraft.MODID, interactionData.fluid + "_flowing"));
            if (fluidSource instanceof ModFluid modFluidSource && fluidFlowing instanceof ModFluid modFluidFlowing) {
                HashMap<Block, BlockState> blockInteractionMap = new HashMap<>();
                for (Map.Entry<String, String> e : interactionData.block_interactions.entrySet()) {
                    ResourceLocation fromResource = ResourceLocation.tryBySeparator(e.getKey(), ':');
                    ResourceLocation toResource = ResourceLocation.tryBySeparator(e.getValue(), ':');
                    if (fromResource == null || toResource == null) continue;
                    Block from = BuiltInRegistries.BLOCK.get(fromResource);
                    Block to = BuiltInRegistries.BLOCK.get(toResource);
                    blockInteractionMap.put(from, to.defaultBlockState());
                }

                HashMap<FluidType, BlockState> fluidInteractionMap = new HashMap<>();
                for (Map.Entry<String, String> e : interactionData.fluid_interactions.entrySet()) {
                    ResourceLocation fromResource = ResourceLocation.tryBySeparator(e.getKey(), ':');
                    ResourceLocation toResource = ResourceLocation.tryBySeparator(e.getValue(), ':');
                    if (fromResource == null || toResource == null) continue;
                    Fluid from = BuiltInRegistries.FLUID.get(fromResource);
                    Block to = BuiltInRegistries.BLOCK.get(toResource);
                    if(from.isSame(Fluids.EMPTY) || to.isEmpty(to.defaultBlockState())) continue;
                    fluidInteractionMap.put(from.getFluidType(), to.defaultBlockState());
                }

                modFluidSource.setBlockInteractions(blockInteractionMap);
                modFluidSource.setFluidInteractions(fluidInteractionMap);
                modFluidFlowing.setBlockInteractions(blockInteractionMap);
                modFluidFlowing.setFluidInteractions(fluidInteractionMap);
            }
        }
    }
}
