package com.omquark.fluidizationcraft.data;

import com.omquark.fluidizationcraft.blocks.FluidizationBlocks;
import com.omquark.fluidizationcraft.FluidizationCraft;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.neoforged.neoforge.client.model.generators.BlockStateProvider;
import net.neoforged.neoforge.client.model.generators.ConfiguredModel;
import net.neoforged.neoforge.client.model.generators.ModelFile;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.minecraft.core.Direction;

import java.util.function.Function;

import static net.neoforged.neoforge.client.model.generators.ModelProvider.BLOCK_FOLDER;

public class FluidizationBlockStateProvider extends BlockStateProvider {
    public FluidizationBlockStateProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, FluidizationCraft.MODID, existingFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        blockItemWithTranslucency(FluidizationBlocks.FROZEN_ACID_BLOCK.get());
        blockItemWithTranslucency(FluidizationBlocks.FROZEN_CRYONITE_BLOCK.get());

        blockWithItem(FluidizationBlocks.ALUMINUM_ORE_BLOCK.get());
        blockWithItem(FluidizationBlocks.LEAD_ORE_BLOCK.get());
        blockWithItem(FluidizationBlocks.NEPTUNIUM_ORE_BLOCK.get());
        blockWithItem(FluidizationBlocks.PLUTONIUM_ORE_BLOCK.get());
        blockWithItem(FluidizationBlocks.RADIONITE_ORE_BLOCK.get());
        blockWithItem(FluidizationBlocks.TIN_ORE_BLOCK.get());
        blockWithItem(FluidizationBlocks.URANIUM_ORE_BLOCK.get());
//        blockWithItem(FluidizationBlocks.DISSOLVINATOR_BLOCK.get());

        cubeBottomTop(FluidizationBlocks.ACID_TNT.get(), "acid_tnt");

        blockItemWithTranslucency(FluidizationBlocks.REINFORCED_GLASS.get());

        this.yDirectionalBlock(
                FluidizationBlocks.DISSOLVINATOR_BLOCK.get(),
                (state) -> orientable(FluidizationBlocks.DISSOLVINATOR_BLOCK.get(), "dissolvinator"),
                180);

    }

    //As directional block from BlockStateProvider, but skips the X direction to prevent rotation on that axis
    public void yDirectionalBlock(Block block, Function<BlockState, ModelFile> modelFunc, int angleOffset) {
        this.getVariantBuilder(block).forAllStates((state) -> {
            Direction dir = (Direction) state.getValue(BlockStateProperties.FACING);
            return ConfiguredModel
                    .builder()
                    .modelFile(modelFunc.apply(state))
                    .rotationY(dir.getAxis().isVertical() ? 0 : ((int) dir.toYRot() + angleOffset) % 360)
                    .build();
        });
    }

    private ResourceLocation key(Block block) {
        return BuiltInRegistries.BLOCK.getKey(block);
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
        ResourceLocation side = ResourceLocation.tryBuild(FluidizationCraft.MODID, BLOCK_FOLDER + "/" + baseName);
        ResourceLocation bottom = ResourceLocation.tryBuild(FluidizationCraft.MODID, BLOCK_FOLDER + "/" + baseName + "_bottom");
        ResourceLocation top = ResourceLocation.tryBuild(FluidizationCraft.MODID, BLOCK_FOLDER + "/" + baseName + "_top");

        assert side != null;
        assert bottom != null;
        assert top != null;

        ModelFile modelFile = models().cubeBottomTop(baseName, side, bottom, top);
        simpleBlockWithItem(block, modelFile);
    }

    private ModelFile orientable(Block block, String baseName) {
        ResourceLocation side = ResourceLocation.tryBuild(FluidizationCraft.MODID, BLOCK_FOLDER + "/" + baseName + "_side");
        ResourceLocation front = ResourceLocation.tryBuild(FluidizationCraft.MODID, BLOCK_FOLDER + "/" + baseName + "_front");
        ResourceLocation top = ResourceLocation.tryBuild(FluidizationCraft.MODID, BLOCK_FOLDER + "/" + baseName + "_top");

        assert side != null;
        assert front != null;
        assert top != null;

        ModelFile modelFile = models().orientable(baseName, side, front, top);
//        simpleBlockWithItem(block, modelFile);
        simpleBlockItem(block, modelFile);
        return modelFile;
    }

}
