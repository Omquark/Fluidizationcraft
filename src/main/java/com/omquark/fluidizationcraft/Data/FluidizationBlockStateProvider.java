package com.omquark.fluidizationcraft.data;

import com.omquark.fluidizationcraft.blocks.FluidizationBlocks;
import com.omquark.fluidizationcraft.FluidizationCraft;
import net.minecraft.data.PackOutput;
import net.minecraft.data.models.BlockModelGenerators;
import net.minecraft.data.models.blockstates.BlockStateGenerator;
import net.minecraft.data.models.model.TexturedModel;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.Resource;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.function.Consumer;

import static net.minecraftforge.client.model.generators.ModelProvider.BLOCK_FOLDER;

public class FluidizationBlockStateProvider extends BlockStateProvider {
    public FluidizationBlockStateProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, FluidizationCraft.MODID, existingFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        blockItemWithTranslucency(FluidizationBlocks.FROZEN_ACID_BLOCK.get());
        blockItemWithTranslucency(FluidizationBlocks.FROZEN_CRYONITE_BLOCK.get());

        blockWithItem(FluidizationBlocks.DISSOLVINATOR_BLOCK.get());

        blockWithItem(FluidizationBlocks.ALUMINUM_ORE_BLOCK.get());
        blockWithItem(FluidizationBlocks.LEAD_ORE_BLOCK.get());
        blockWithItem(FluidizationBlocks.NEPTUNIUM_ORE_BLOCK.get());
        blockWithItem(FluidizationBlocks.PLUTONIUM_ORE_BLOCK.get());
        blockWithItem(FluidizationBlocks.RADIONITE_ORE_BLOCK.get());
        blockWithItem(FluidizationBlocks.TIN_ORE_BLOCK.get());
        blockWithItem(FluidizationBlocks.URANIUM_ORE_BLOCK.get());
        cubeBottomTop(FluidizationBlocks.ACID_TNT.get(), "acid_tnt");

        blockItemWithTranslucency(FluidizationBlocks.REINFORCED_GLASS.get());
    }

    private ResourceLocation key(Block block) {
        return ForgeRegistries.BLOCKS.getKey(block);
    }

    private String name(Block block) {
        return key(block).getPath();
    }

    private void blockWithItem(Block block) {
        simpleBlockWithItem(block, cubeAll(block));
    }

    private void blockItemWithTranslucency(Block block) {
        models()
                .cubeAll(
                        name(block),
                        blockTexture(block))
                .renderType("translucent");
        simpleBlockWithItem(block, cubeAll(block));
    }

    private void cubeBottomTop(Block block, String baseName) {
        ModelFile modelFile = models().cubeBottomTop(baseName,
                new ResourceLocation(FluidizationCraft.MODID, BLOCK_FOLDER + "/" + baseName),
                new ResourceLocation(FluidizationCraft.MODID, BLOCK_FOLDER + "/" + baseName + "_bottom"),
                new ResourceLocation(FluidizationCraft.MODID, BLOCK_FOLDER + "/" + baseName + "_top"));
        simpleBlockWithItem(block, modelFile);
    }

    private void orientable(Block block, String baseName){
    }
}
