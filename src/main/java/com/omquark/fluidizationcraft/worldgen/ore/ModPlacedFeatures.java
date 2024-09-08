package com.omquark.fluidizationcraft.worldgen.ore;

import com.omquark.fluidizationcraft.FluidizationCraft;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.placement.*;
import net.minecraft.core.Direction;

import java.util.List;

public class ModPlacedFeatures {
    public static final ResourceKey<PlacedFeature> ALUMINUM_ORE = createKey("ore_aluminum");
    public static final ResourceKey<PlacedFeature> LEAD_ORE = createKey("ore_lead");
    public static final ResourceKey<PlacedFeature> NEPTUNIUM_ORE = createKey("ore_neptunium");
    public static final ResourceKey<PlacedFeature> PLUTONIUM_ORE = createKey("ore_plutonium");
    public static final ResourceKey<PlacedFeature> RADIONITE_ORE = createKey("ore_radionite");
    public static final ResourceKey<PlacedFeature> TIN_ORE = createKey("ore_tin");
    public static final ResourceKey<PlacedFeature> URANIUM_ORE = createKey("ore_uranium");
    public static final ResourceKey<PlacedFeature> LAKE_ACID_SURFACE = createKey("lake_acid_surface");
    public static final ResourceKey<PlacedFeature> LAKE_ACID_UNDERGROUND = createKey("lake_acid_underground");
    public static final ResourceKey<PlacedFeature> LAKE_CRYONITE_OVERWORLD = createKey("lake_cryonite_overworld");
    public static final ResourceKey<PlacedFeature> LAKE_CRYONITE_UNDERGROUND = createKey("lake_cryonite_underground");
    public static final ResourceKey<PlacedFeature> LAKE_NETHERFLOW = createKey("lake_netherflow");


    public static void bootstrap(BootstrapContext<PlacedFeature> context) {
        HolderGetter<ConfiguredFeature<?, ?>> configuredFeature = context.lookup(Registries.CONFIGURED_FEATURE);

        Holder<ConfiguredFeature<?, ?>> aluminumHolder =
                configuredFeature.getOrThrow(ModConfiguredFeatures.ALUMINUM_ORE);
        Holder<ConfiguredFeature<?, ?>> leadHolder =
                configuredFeature.getOrThrow(ModConfiguredFeatures.LEAD_ORE);
        Holder<ConfiguredFeature<?, ?>> neptuniumHolder =
                configuredFeature.getOrThrow(ModConfiguredFeatures.NEPTUNIUM_ORE);
        Holder<ConfiguredFeature<?, ?>> plutoniumHolder =
                configuredFeature.getOrThrow(ModConfiguredFeatures.PLUTONIUM_ORE);
        Holder<ConfiguredFeature<?, ?>> radioniteHolder =
                configuredFeature.getOrThrow(ModConfiguredFeatures.RADIONITE_ORE);
        Holder<ConfiguredFeature<?, ?>> tinHolder =
                configuredFeature.getOrThrow(ModConfiguredFeatures.TIN_ORE);
        Holder<ConfiguredFeature<?, ?>> uraniumHolder =
                configuredFeature.getOrThrow(ModConfiguredFeatures.URANIUM_ORE);
        Holder<ConfiguredFeature<?, ?>> overworldLakeAcidHolder =
                configuredFeature.getOrThrow(ModConfiguredFeatures.LAKE_ACID);
        Holder<ConfiguredFeature<?, ?>> undergroundLakeAcidHolder =
                configuredFeature.getOrThrow(ModConfiguredFeatures.LAKE_ACID);
        Holder<ConfiguredFeature<?, ?>> overwordLakeCryoniteHolder =
                configuredFeature.getOrThrow(ModConfiguredFeatures.LAKE_CRYONITE);
        Holder<ConfiguredFeature<?, ?>> undergroundLakeCryoniteHolder =
                configuredFeature.getOrThrow(ModConfiguredFeatures.LAKE_CRYONITE);
        Holder<ConfiguredFeature<?, ?>> netherflowLakeHolder =
                configuredFeature.getOrThrow(ModConfiguredFeatures.LAKE_NETHERFLOW);

        register(context, ALUMINUM_ORE, aluminumHolder,
                List.of(
                        CountPlacement.of(10),
                        HeightRangePlacement.uniform(
                                VerticalAnchor.absolute(-64),
                                VerticalAnchor.absolute(384))
                ));
        register(context, LEAD_ORE, leadHolder,
                List.of(
                        CountPlacement.of(10),
                        HeightRangePlacement.uniform(
                                VerticalAnchor.absolute(-64),
                                VerticalAnchor.absolute(384)
                        )
                ));

        register(context, NEPTUNIUM_ORE, neptuniumHolder,
                List.of(
                        CountPlacement.of(10),
                        HeightRangePlacement.uniform(
                                VerticalAnchor.absolute(-64),
                                VerticalAnchor.absolute(384)
                        )
                ));
        register(context, PLUTONIUM_ORE, plutoniumHolder,
                List.of(
                        CountPlacement.of(10),
                        HeightRangePlacement.uniform(
                                VerticalAnchor.absolute(-64),
                                VerticalAnchor.absolute(384)
                        )
                ));
        register(context, RADIONITE_ORE, radioniteHolder,
                List.of(
                        CountPlacement.of(10),
                        HeightRangePlacement.uniform(
                                VerticalAnchor.absolute(-64),
                                VerticalAnchor.absolute(384)
                        )
                ));
        register(context, TIN_ORE, tinHolder,
                List.of(
                        CountPlacement.of(10),
                        HeightRangePlacement.uniform(
                                VerticalAnchor.absolute(-64),
                                VerticalAnchor.absolute(384)
                        )
                ));
        register(context, URANIUM_ORE, uraniumHolder,
                List.of(
                        CountPlacement.of(10),
                        HeightRangePlacement.uniform(
                                VerticalAnchor.absolute(-64),
                                VerticalAnchor.absolute(384)
                        )
                ));
        register(context, LAKE_ACID_SURFACE, overworldLakeAcidHolder,
                List.of(
                        RarityFilter.onAverageOnceEvery(15),
                        InSquarePlacement.spread(),
                        PlacementUtils.HEIGHTMAP_WORLD_SURFACE,
                        BiomeFilter.biome())
        );
        register(context, LAKE_ACID_UNDERGROUND, undergroundLakeAcidHolder,
                List.of(
                        RarityFilter.onAverageOnceEvery(9),
                        InSquarePlacement.spread(),
                        PlacementUtils.FULL_RANGE,
                        BiomeFilter.biome()
                ));
        register(context, LAKE_CRYONITE_OVERWORLD, overwordLakeCryoniteHolder,
                List.of(
                        RarityFilter.onAverageOnceEvery(9),
                        InSquarePlacement.spread(),
                        PlacementUtils.HEIGHTMAP_WORLD_SURFACE,
                        BiomeFilter.biome())
        );
        register(context, LAKE_CRYONITE_UNDERGROUND, undergroundLakeCryoniteHolder,
                List.of(
                        RarityFilter.onAverageOnceEvery(7),
                        InSquarePlacement.spread(),
                        PlacementUtils.FULL_RANGE,
                        BiomeFilter.biome()
                ));
        register(context, LAKE_NETHERFLOW, netherflowLakeHolder,
                List.of(
                        RarityFilter.onAverageOnceEvery(15),
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
