package com.omquark.fluidizationcraft.region;

import com.mojang.datafixers.util.Pair;
import com.omquark.fluidizationcraft.biomes.ModBiomes;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.biome.Climate;
import terrablender.api.ParameterUtils;
import terrablender.api.Region;
import terrablender.api.RegionType;

import java.util.List;
import java.util.function.Consumer;

public class AcidWastesRegion extends Region {

    public AcidWastesRegion(ResourceLocation name, int weight){
        super(name, RegionType.OVERWORLD, 50);
    }

    @Override
    public void addBiomes(Registry<Biome> registry, Consumer<Pair<Climate.ParameterPoint, ResourceKey<Biome>>> mapper) {

        this.addModifiedVanillaOverworldBiomes(mapper, builder -> {
            builder.replaceBiome(Biomes.PLAINS, ModBiomes.ACID_WASTES);

            List<Climate.ParameterPoint> acidWastes = new ParameterUtils.ParameterPointListBuilder()
                    .temperature(ParameterUtils.Temperature.HOT, ParameterUtils.Temperature.WARM, ParameterUtils.Temperature.UNFROZEN)
                    .humidity(ParameterUtils.Humidity.DRY, ParameterUtils.Humidity.ARID)
                    .continentalness(ParameterUtils.Continentalness.INLAND)
                    .erosion(ParameterUtils.Erosion.span(ParameterUtils.Erosion.EROSION_4, ParameterUtils.Erosion.EROSION_6))
                    .depth(ParameterUtils.Depth.SURFACE)
                    .weirdness(ParameterUtils.Weirdness.FULL_RANGE)
                    .build();

            acidWastes.forEach(point -> builder.replaceBiome(point, ModBiomes.ACID_WASTES));
        });
    }
}
