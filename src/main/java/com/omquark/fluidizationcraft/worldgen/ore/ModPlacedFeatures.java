package com.omquark.fluidizationcraft.worldgen.ore;

import com.omquark.fluidizationcraft.FluidizationCraft;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.Vec3i;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.blockpredicates.BlockPredicate;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.placement.*;
import net.minecraft.core.Direction;

import java.util.List;

public class ModPlacedFeatures {
    public static ResourceKey<PlacedFeature> ALUMINUM_ORE = createKey("ore_aluminum");
    public static ResourceKey<PlacedFeature> LEAD_ORE = createKey("ore_lead");
    public static ResourceKey<PlacedFeature> NEPUTUNIUM_ORE = createKey("ore_neptunium");
    public static ResourceKey<PlacedFeature> LAKE_ACID_SURFACE = createKey("lake_acid_surface");
    public static final ResourceKey<PlacedFeature> LAKE_ACID_UNDERGROUND = createKey("lake_acid_underground");


    public static void bootstrap(BootstrapContext<PlacedFeature> context) {
        HolderGetter<ConfiguredFeature<?, ?>> configuredFeature = context.lookup(Registries.CONFIGURED_FEATURE);

        Holder<ConfiguredFeature<?, ?>> aluminumHolder =
                configuredFeature.getOrThrow(ModConfiguredFeatures.ALUMINUM_ORE);
        Holder<ConfiguredFeature<?, ?>> leadHolder =
                configuredFeature.getOrThrow(ModConfiguredFeatures.LEAD_ORE);
        Holder<ConfiguredFeature<?, ?>> neptuniumHolder =
                configuredFeature.getOrThrow(ModConfiguredFeatures.NEPTUNIUM_ORE);
        Holder<ConfiguredFeature<?, ?>> overworldLakeAcidHolder =
                configuredFeature.getOrThrow(ModConfiguredFeatures.LAKE_ACID);
        Holder<ConfiguredFeature<?, ?>> undergroundLakeAcidHolder =
                configuredFeature.getOrThrow(ModConfiguredFeatures.LAKE_ACID);

        register(context, ALUMINUM_ORE, aluminumHolder,
                ModOrePlacement.commonOrePlacement(
                        6,
                        HeightRangePlacement.uniform(
                                VerticalAnchor.absolute(-64),
                                VerticalAnchor.absolute(20))));

        register(context, LEAD_ORE, leadHolder,
                ModOrePlacement.commonOrePlacement(
                        6,
                        HeightRangePlacement.uniform(
                                VerticalAnchor.absolute(-64),
                                VerticalAnchor.absolute(20))));

        register(context, NEPUTUNIUM_ORE, neptuniumHolder,
                ModOrePlacement.commonOrePlacement(
                        6,
                        HeightRangePlacement.uniform(
                                VerticalAnchor.absolute(-64),
                                VerticalAnchor.absolute(64)
                        )
                ));

        register(context, LAKE_ACID_SURFACE, overworldLakeAcidHolder,
                List.of(
                        CountPlacement.of(100),
//                        RarityFilter.onAverageOnceEvery(50),
                        InSquarePlacement.spread(),
                        PlacementUtils.HEIGHTMAP_WORLD_SURFACE,
                        BiomeFilter.biome())
        );
        register(context, LAKE_ACID_UNDERGROUND, undergroundLakeAcidHolder,
                List.of(
                        RarityFilter.onAverageOnceEvery(100),
                        InSquarePlacement.spread(),
                        PlacementUtils.FULL_RANGE,
                        BiomeFilter.biome()
                ));
    }

    private static void register(
            BootstrapContext<PlacedFeature> context,
            ResourceKey<PlacedFeature> key,
            Holder<ConfiguredFeature<?, ?>> feature,
            List<PlacementModifier> placementModifiers) {
        context.register(key, new PlacedFeature(feature, placementModifiers));
    }

    private static ResourceKey<PlacedFeature> createKey(String resourceName) {
        return ResourceKey.create(Registries.PLACED_FEATURE,
                ResourceLocation.fromNamespaceAndPath(FluidizationCraft.MODID, resourceName));
    }
}
