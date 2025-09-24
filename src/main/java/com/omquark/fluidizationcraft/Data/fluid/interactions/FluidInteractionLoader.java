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

        FluidizationCraft.LOGGER.warn("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!{}{}", resourceManager, "!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");

        for (Map.Entry<ResourceLocation, JsonElement> entry : jsons.entrySet()) {
            FluidInteractionData interactionData = GSON.fromJson(entry.getValue(), FluidInteractionData.class);
            Fluid fluid = BuiltInRegistries.FLUID.get(ResourceLocation.fromNamespaceAndPath(FluidizationCraft.MODID, "blocks/" + interactionData.fluid));
            if (fluid instanceof ModFluid modFluid) {
                HashMap<Block, BlockState> blockInteractionMap = new HashMap<>();
                for (Map.Entry<String, String> e : interactionData.blockInteractions.entrySet()) {
                    Block from = BuiltInRegistries.BLOCK.get(ResourceLocation.withDefaultNamespace(e.getKey()));
                    Block to = BuiltInRegistries.BLOCK.get(ResourceLocation.withDefaultNamespace(e.getValue()));
                    blockInteractionMap.put(from, to.defaultBlockState());
                }

                HashMap<FluidType, BlockState> fluidInteractionMap = new HashMap<>();
                for (Map.Entry<String, String> e : interactionData.fluidInteractions.entrySet()) {
                    FluidType other = BuiltInRegistries.FLUID.get(ResourceLocation.withDefaultNamespace(e.getKey())).getFluidType();
                    Block to = BuiltInRegistries.BLOCK.get(ResourceLocation.withDefaultNamespace(e.getValue()));
                    fluidInteractionMap.put(other, to.defaultBlockState());
                }

                modFluid.setBlockInteractions(blockInteractionMap);
                modFluid.setFluidInteractions(fluidInteractionMap);
            }
        }
    }
}
