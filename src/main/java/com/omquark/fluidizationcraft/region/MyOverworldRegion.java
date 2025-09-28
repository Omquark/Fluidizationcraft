package com.omquark.fluidizationcraft.region;

import com.mojang.datafixers.util.Pair;
import com.omquark.fluidizationcraft.biomes.ModBiomes;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.biome.Climate;
import terrablender.api.ModifiedVanillaOverworldBuilder;
import terrablender.api.ParameterUtils;
import terrablender.api.Region;
import terrablender.api.RegionType;
import terrablender.worldgen.DefaultOverworldRegion;

import java.util.List;
import java.util.Objects;
import java.util.function.Consumer;

public class MyOverworldRegion extends Region {

//    public MyOverworldRegion(ResourceLocation name, RegionType type, int weight) {
//        super(name, RegionType.OVERWORLD, 50);
//    }

    public MyOverworldRegion(ResourceLocation name, int weight){
        super(name, RegionType.OVERWORLD, 50);
    }

    @Override
    public void addBiomes(Registry<Biome> registry, Consumer<Pair<Climate.ParameterPoint, ResourceKey<Biome>>> mapper) {
        this.addModifiedVanillaOverworldBiomes(mapper, builder -> {
            builder.replaceBiome(Biomes.PLAINS, ModBiomes.ACID_WASTES);

            List<Climate.ParameterPoint> acidWastes = new ParameterUtils.ParameterPointListBuilder()
                    .temperature(ParameterUtils.Temperature.WARM, ParameterUtils.Temperature.UNFROZEN)
                    .humidity(ParameterUtils.Humidity.DRY, ParameterUtils.Humidity.NEUTRAL)
                    .continentalness(ParameterUtils.Continentalness.FULL_RANGE)
                    .erosion(ParameterUtils.Erosion.EROSION_0, ParameterUtils.Erosion.EROSION_1)
                    .depth(ParameterUtils.Depth.SURFACE, ParameterUtils.Depth.FLOOR)
                    .weirdness(ParameterUtils.Weirdness.HIGH_SLICE_VARIANT_ASCENDING
                            , ParameterUtils.Weirdness.PEAK_VARIANT, ParameterUtils.Weirdness.HIGH_SLICE_VARIANT_DESCENDING)
                    .build();

            acidWastes.forEach(point -> builder.replaceBiome(point, ModBiomes.ACID_WASTES));
        });
    }
}
