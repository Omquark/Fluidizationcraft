package com.omquark.fluidizationcraft.worldgen.ore;

import com.omquark.fluidizationcraft.FluidizationCraft;
import com.omquark.fluidizationcraft.blocks.FluidizationBlocks;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;
import net.minecraft.world.level.levelgen.structure.templatesystem.RuleTest;
import net.minecraft.world.level.levelgen.structure.templatesystem.TagMatchTest;

import java.util.List;

public class ModConfiguredFeatures {
    protected static ResourceKey<ConfiguredFeature<?, ?>> OVERWORLD_ORE =
            ResourceKey.create(Registries.CONFIGURED_FEATURE,
                    ResourceLocation.fromNamespaceAndPath(FluidizationCraft.MODID, "aluminum_ore"));

    public static void bootstrap(BootstrapContext<ConfiguredFeature<?, ?>> context){
        RuleTest stoneReplacable = new TagMatchTest(BlockTags.STONE_ORE_REPLACEABLES);
        RuleTest deepslateReplacable = new TagMatchTest(BlockTags.DEEPSLATE_ORE_REPLACEABLES);

        List<OreConfiguration.TargetBlockState> aluminumOre =
                List.of(OreConfiguration.target(stoneReplacable, FluidizationBlocks.ALUMINUM_ORE_BLOCK.get().defaultBlockState()),
                        OreConfiguration.target(deepslateReplacable, FluidizationBlocks.ALUMINUM_ORE_BLOCK.get().defaultBlockState()));

        register(context, OVERWORLD_ORE, Feature.ORE, new OreConfiguration(aluminumOre, 10));
    }

    private static <FC extends FeatureConfiguration, F extends Feature<FC>> void register(BootstrapContext<ConfiguredFeature<?, ?>> context, ResourceKey<ConfiguredFeature<?, ?>> key, F feature, FC config){
        context.register(key, new ConfiguredFeature<>(feature, config));
    }
}