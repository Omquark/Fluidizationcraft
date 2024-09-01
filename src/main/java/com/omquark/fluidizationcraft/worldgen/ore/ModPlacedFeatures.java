package com.omquark.fluidizationcraft.worldgen.ore;

import com.omquark.fluidizationcraft.FluidizationCraft;
import com.omquark.fluidizationcraft.blocks.FluidizationBlocks;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.placement.HeightRangePlacement;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraft.world.level.levelgen.placement.PlacementModifier;

import java.util.List;

public class ModPlacedFeatures {
    public static ResourceKey<PlacedFeature> ALUMINUM_ORE =
            ResourceKey.create(Registries.PLACED_FEATURE,
                    ResourceLocation.fromNamespaceAndPath(FluidizationCraft.MODID, "ore_aluminum"));

    public static void bootstrap(BootstrapContext<PlacedFeature> context){
        HolderGetter<ConfiguredFeature<?, ?>> configuredFeature = context.lookup(Registries.CONFIGURED_FEATURE);

        Holder<ConfiguredFeature<?, ?>> holder =
                configuredFeature.getOrThrow(ModConfiguredFeatures.OVERWORLD_ORE);

        register(context, ALUMINUM_ORE, holder,
                ModOrePlacement.commonOrePlacement(
                        6,
                        HeightRangePlacement.uniform(
                                VerticalAnchor.absolute(-64),
                                VerticalAnchor.absolute(20))));
    }

    private static void register(
            BootstrapContext<PlacedFeature> context,
            ResourceKey<PlacedFeature> key,
            Holder<ConfiguredFeature<?, ?>> feature,
            List<PlacementModifier> placementModifiers){
        context.register(key, new PlacedFeature(feature, placementModifiers));
    }
}
