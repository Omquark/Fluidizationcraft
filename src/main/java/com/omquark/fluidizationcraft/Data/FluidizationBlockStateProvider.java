package com.omquark.fluidizationcraft.Data;

import com.omquark.fluidizationcraft.Blocks.FluidizationBlocks;
import com.omquark.fluidizationcraft.FluidizationCraft;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class FluidizationBlockStateProvider extends BlockStateProvider {
    public FluidizationBlockStateProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, FluidizationCraft.MODID, existingFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        blockItemWithTranslucency(FluidizationBlocks.FROZEN_ACID_BLOCK);
        blockWithItem(FluidizationBlocks.ALUMINUM_ORE_BLOCK);
        blockWithItem(FluidizationBlocks.LEAD_ORE_BLOCK);
        blockWithItem(FluidizationBlocks.NEPTUNIUM_ORE_BLOCK);
        blockWithItem(FluidizationBlocks.PLUTONIUM_ORE_BLOCK);
        blockWithItem(FluidizationBlocks.RADIONITE_ORE_BLOCK);
        blockWithItem(FluidizationBlocks.TIN_ORE_BLOCK);
        blockWithItem(FluidizationBlocks.URANIUM_ORE_BLOCK);
    }

    private ResourceLocation key(Block block) {
        return ForgeRegistries.BLOCKS.getKey(block);
    }
    private String name(Block block) {
        return key(block).getPath();
    }

    private void blockWithItem(RegistryObject<Block> blockRegistryObject) {
        simpleBlockWithItem(blockRegistryObject.get(), cubeAll(blockRegistryObject.get()));
    }

    private void blockItemWithTranslucency(RegistryObject<Block> blockRegistryObject){
        models()
                .cubeAll(
                        name(blockRegistryObject.get()),
                        blockTexture(blockRegistryObject.get()))
                .renderType("translucent");
        simpleBlockWithItem(blockRegistryObject.get(), cubeAll(blockRegistryObject.get()));
    }
}
