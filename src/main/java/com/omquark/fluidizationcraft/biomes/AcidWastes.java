package com.omquark.fluidizationcraft.biomes;

import com.omquark.fluidizationcraft.worldgen.features.ModPlacedFeatures;
import net.minecraft.client.particle.Particle;
import net.minecraft.core.particles.ParticleGroup;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.data.worldgen.placement.VegetationPlacements;
import net.minecraft.sounds.Music;
import net.minecraft.sounds.Musics;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.level.biome.*;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.SurfaceRules;

public class AcidWastes {

    private static final SurfaceRules.RuleSource COARSE_DIRT = makeStateRule(Blocks.COARSE_DIRT);
    private static final SurfaceRules.RuleSource DIRT = makeStateRule(Blocks.DIRT);
    private static final SurfaceRules.RuleSource GRASS_BLOCK = makeStateRule(Blocks.GRASS_BLOCK);

    public static SurfaceRules.RuleSource makeRules() {
        SurfaceRules.ConditionSource isAtOrAboveWaterLevel = SurfaceRules.waterBlockCheck(-1, 0);
        SurfaceRules.RuleSource grassSurface = SurfaceRules.sequence(SurfaceRules.ifTrue(isAtOrAboveWaterLevel, GRASS_BLOCK), DIRT);


        return SurfaceRules.sequence(
                SurfaceRules.ifTrue(SurfaceRules.isBiome(ModBiomes.ACID_WASTES), COARSE_DIRT),
                SurfaceRules.ifTrue(SurfaceRules.ON_FLOOR, grassSurface)
        );
    }

    private static SurfaceRules.RuleSource makeStateRule(Block block) {
        return SurfaceRules.state(block.defaultBlockState());
    }

    public static Biome acidWastes(BootstrapContext<Biome> ctx) {
        BiomeSpecialEffects effects = new BiomeSpecialEffects.Builder()
                .fogColor(0x556655)
                .skyColor(0x779977)
                .waterColor(0x2ADFB3)
                .waterFogColor(0X115544)
                .backgroundMusic(Musics.GAME)
                .build();

        BiomeGenerationSettings generatedSettings =
                new BiomeGenerationSettings
                        .Builder(ctx.lookup(Registries.PLACED_FEATURE), ctx.lookup(Registries.CONFIGURED_CARVER))
                        .addFeature(GenerationStep.Decoration.TOP_LAYER_MODIFICATION, ModPlacedFeatures.COARSE_DIRT_SURFACE)
                        .addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, VegetationPlacements.PATCH_DEAD_BUSH)
                        .build();

        MobSpawnSettings.Builder spawns = new MobSpawnSettings
                .Builder()
                .addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(EntityType.COW, 1, 5, 20));

        return new Biome.BiomeBuilder()
                .hasPrecipitation(true)
                .temperature(0.8f)
                .downfall(0.4f)
                .specialEffects(effects)
                .mobSpawnSettings(spawns.build())
                .generationSettings(generatedSettings)
                .build();
    }

}
