package com.omquark.fluidizationcraft.worldgen;

import com.omquark.fluidizationcraft.FluidizationCraft;
import com.omquark.fluidizationcraft.worldgen.ore.ModPlacedFeatures;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.HolderSet;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BiomeTags;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.neoforged.neoforge.common.world.BiomeModifier;
import net.neoforged.neoforge.common.world.BiomeModifiers;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

public class ModBiomesModifier {
    protected static final ResourceKey<BiomeModifier> ALUMINUM_ORE = createKey("ore_aluminum");
    protected static final ResourceKey<BiomeModifier> LEAD_ORE = createKey("ore_lead");
    protected static final ResourceKey<BiomeModifier> NEPTUNIUM_ORE = createKey("ore_neptunium");
    protected static final ResourceKey<BiomeModifier> ACID_LAKE_OVERWORLD = createKey("acid_lake_overworld");
    protected static final ResourceKey<BiomeModifier> ACID_LAKE_UNDERGROUND = createKey("acid_lake_underground");

    public static void bootstrap(BootstrapContext<BiomeModifier> context) {
        HolderGetter<PlacedFeature> placedFeature = context.lookup(Registries.PLACED_FEATURE);
        HolderGetter<Biome> biomes = context.lookup(Registries.BIOME);


        context.register(
                ALUMINUM_ORE,
                new BiomeModifiers.AddFeaturesBiomeModifier(
                        biomes.getOrThrow(BiomeTags.IS_OVERWORLD),
                        HolderSet.direct(placedFeature.getOrThrow(ModPlacedFeatures.ALUMINUM_ORE)),
                        GenerationStep.Decoration.UNDERGROUND_ORES
                )
        );
        context.register(
                LEAD_ORE,
                new BiomeModifiers.AddFeaturesBiomeModifier(
                        biomes.getOrThrow(BiomeTags.IS_OVERWORLD),
                        HolderSet.direct(placedFeature.getOrThrow(ModPlacedFeatures.LEAD_ORE)),
                        GenerationStep.Decoration.UNDERGROUND_ORES
                )
        );
        context.register(
                NEPTUNIUM_ORE,
                new BiomeModifiers.AddFeaturesBiomeModifier(
                        biomes.getOrThrow(BiomeTags.IS_NETHER),
                        HolderSet.direct(placedFeature.getOrThrow(ModPlacedFeatures.NEPUTUNIUM_ORE)),
                        GenerationStep.Decoration.UNDERGROUND_ORES
                )
        );
        context.register(
                ACID_LAKE_OVERWORLD,
                new BiomeModifiers.AddFeaturesBiomeModifier(
                        biomes.getOrThrow(BiomeTags.IS_OVERWORLD),
                        HolderSet.direct(placedFeature.getOrThrow(ModPlacedFeatures.LAKE_ACID_SURFACE)),
                        GenerationStep.Decoration.LAKES
                )
        );
        context.register(
                ACID_LAKE_UNDERGROUND,
                new BiomeModifiers.AddFeaturesBiomeModifier(
                        biomes.getOrThrow(BiomeTags.IS_OVERWORLD),
                        HolderSet.direct(placedFeature.getOrThrow(ModPlacedFeatures.LAKE_ACID_UNDERGROUND)),
                        GenerationStep.Decoration.LAKES
                )
        );
    }

    private static ResourceKey<BiomeModifier> createKey(String resourceName) {
        return ResourceKey.create(NeoForgeRegistries.Keys.BIOME_MODIFIERS,
                ResourceLocation.fromNamespaceAndPath(FluidizationCraft.MODID, resourceName));
    }
}
