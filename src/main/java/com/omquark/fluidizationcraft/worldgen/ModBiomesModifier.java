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
    protected static ResourceKey<BiomeModifier> ADD_ORE =
            ResourceKey.create(NeoForgeRegistries.Keys.BIOME_MODIFIERS,
                    ResourceLocation.fromNamespaceAndPath(FluidizationCraft.MODID, "add_ore"));

    public static void bootstrap(BootstrapContext<BiomeModifier> context){
        HolderGetter<PlacedFeature> placedFeature = context.lookup(Registries.PLACED_FEATURE);
        HolderGetter<Biome> biomes = context.lookup(Registries.BIOME);

        context.register(
                ADD_ORE,
                new BiomeModifiers.AddFeaturesBiomeModifier(
                        biomes.getOrThrow(BiomeTags.IS_OVERWORLD),
                        HolderSet.direct(placedFeature.getOrThrow(ModPlacedFeatures.ALUMINUM_ORE)),
                        GenerationStep.Decoration.UNDERGROUND_ORES
                )
        );
    }
}
