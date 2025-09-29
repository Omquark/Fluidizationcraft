package com.omquark.fluidizationcraft.biomes;

import com.omquark.fluidizationcraft.FluidizationCraft;
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
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.SurfaceRules;

import static com.omquark.fluidizationcraft.biomes.AcidWastes.acidWastes;

public class ModBiomes {

    public static final ResourceKey<Biome> ACID_WASTES =
            ResourceKey.create(Registries.BIOME, ResourceLocation.fromNamespaceAndPath(FluidizationCraft.MODID, "acid_wastes"));

    public static void bootstrap(BootstrapContext<Biome> ctx) {
        ctx.register(ACID_WASTES, acidWastes(ctx));
    }
}
