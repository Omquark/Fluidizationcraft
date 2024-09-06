package com.omquark.fluidizationcraft.worldgen.ore;

import com.omquark.fluidizationcraft.FluidizationCraft;
import com.omquark.fluidizationcraft.blocks.FluidizationBlocks;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.LakeFeature;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.structure.templatesystem.BlockMatchTest;
import net.minecraft.world.level.levelgen.structure.templatesystem.RuleTest;
import net.minecraft.world.level.levelgen.structure.templatesystem.TagMatchTest;

import java.util.List;

public class ModConfiguredFeatures {
    public static final ResourceKey<ConfiguredFeature<?, ?>> ALUMINUM_ORE = createKey("ore_aluminum");
    public static final ResourceKey<ConfiguredFeature<?, ?>> LEAD_ORE = createKey("ore_lead");
    public static final ResourceKey<ConfiguredFeature<?, ?>> NEPTUNIUM_ORE = createKey("ore_neptunium");
    public static final ResourceKey<ConfiguredFeature<?, ?>> LAKE_ACID = createKey("lake_acid");

    public static void bootstrap(BootstrapContext<ConfiguredFeature<?, ?>> context) {
        RuleTest stoneReplacable = new TagMatchTest(BlockTags.STONE_ORE_REPLACEABLES);
        RuleTest deepslateReplacable = new TagMatchTest(BlockTags.DEEPSLATE_ORE_REPLACEABLES);
        RuleTest netherrackReplacable = new BlockMatchTest(Blocks.NETHERRACK);

        List<OreConfiguration.TargetBlockState> aluminumOre =
                List.of(OreConfiguration.target(stoneReplacable, FluidizationBlocks.ALUMINUM_ORE_BLOCK.get().defaultBlockState()),
                        OreConfiguration.target(deepslateReplacable, FluidizationBlocks.ALUMINUM_ORE_BLOCK.get().defaultBlockState()));

        List<OreConfiguration.TargetBlockState> leadOre =
                List.of(OreConfiguration.target(stoneReplacable, FluidizationBlocks.LEAD_ORE_BLOCK.get().defaultBlockState()),
                        OreConfiguration.target(deepslateReplacable, FluidizationBlocks.LEAD_ORE_BLOCK.get().defaultBlockState()));

        List<OreConfiguration.TargetBlockState> neptuniumOre =
                List.of(OreConfiguration.target(netherrackReplacable, FluidizationBlocks.NEPTUNIUM_ORE_BLOCK.get().defaultBlockState()));

        register(context, ALUMINUM_ORE, Feature.ORE, new OreConfiguration(aluminumOre, 10));
        register(context, LEAD_ORE, Feature.ORE, new OreConfiguration(leadOre, 10));
        register(context, NEPTUNIUM_ORE, Feature.ORE, new OreConfiguration(neptuniumOre, 10));
        register(context, LAKE_ACID, Feature.LAKE, new LakeFeature.Configuration(
                BlockStateProvider.simple(FluidizationBlocks.ACID_BLOCK.get().defaultBlockState()),
                BlockStateProvider.simple(Blocks.STONE.defaultBlockState())));
    }


    private static <FC extends FeatureConfiguration, F extends Feature<FC>> void register(BootstrapContext<ConfiguredFeature<?, ?>> context, ResourceKey<ConfiguredFeature<?, ?>> key, F feature, FC config) {
        context.register(key, new ConfiguredFeature<>(feature, config));
    }

    private static ResourceKey<ConfiguredFeature<?, ?>> createKey(String resourceName) {
        return ResourceKey.create(Registries.CONFIGURED_FEATURE,
                ResourceLocation.fromNamespaceAndPath(FluidizationCraft.MODID, resourceName));
    }
}