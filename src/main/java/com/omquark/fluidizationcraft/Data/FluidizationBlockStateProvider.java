package com.omquark.fluidizationcraft.Data;

import com.omquark.fluidizationcraft.Blocks.FluidizationBlocks;
import com.omquark.fluidizationcraft.FluidizationCraft;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

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

    private void blockItemWithTranslucency(Block block){
        models()
                .cubeAll(
                        name(block),
                        blockTexture(block))
                .renderType("translucent");
        simpleBlockWithItem(block, cubeAll(block));
    }
}
