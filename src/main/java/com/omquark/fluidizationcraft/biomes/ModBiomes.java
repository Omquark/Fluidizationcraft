package com.omquark.fluidizationcraft.biomes;

import com.omquark.fluidizationcraft.FluidizationCraft;
import com.omquark.fluidizationcraft.region.MyOverworldRegion;
import com.omquark.fluidizationcraft.worldgen.features.ModPlacedFeatures;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.BiomeGenerationSettings;
import net.minecraft.world.level.biome.BiomeSpecialEffects;
import net.minecraft.world.level.biome.MobSpawnSettings;
import net.minecraft.world.level.levelgen.GenerationStep;
import terrablender.api.Region;
import terrablender.api.Regions;

public class ModBiomes {

    public static final ResourceKey<Biome> ACID_WASTES =
            ResourceKey.create(Registries.BIOME, ResourceLocation.fromNamespaceAndPath(FluidizationCraft.MODID, "acid_wastes"));

    public static void bootstrap(BootstrapContext<Biome> ctx) {
        ctx.register(ACID_WASTES, acidWastes(ctx));
    }

    private static Biome acidWastes(BootstrapContext<Biome> ctx) {
        BiomeSpecialEffects effects = new BiomeSpecialEffects.Builder()
                .fogColor(0x556655)
                .skyColor(0x88AA88)
                .waterColor(0x2AEFC3)
                .waterFogColor(0X116655)
                .build();

        BiomeGenerationSettings.Builder generation =
                new BiomeGenerationSettings
                        .Builder(ctx.lookup(Registries.PLACED_FEATURE), ctx.lookup(Registries.CONFIGURED_CARVER));

        generation.addFeature(GenerationStep.Decoration.SURFACE_STRUCTURES, ModPlacedFeatures.COARSE_DIRT_SURFACE);

        MobSpawnSettings.Builder spawns = new MobSpawnSettings
                .Builder()
                .addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(EntityType.COW, 1, 20, 20));

        return new Biome.BiomeBuilder()
                .hasPrecipitation(true)
                .temperature(0.8f)
                .downfall(0.4f)
                .specialEffects(effects)
                .mobSpawnSettings(spawns.build())
                .generationSettings(generation.build())
                .build();
    }

}
