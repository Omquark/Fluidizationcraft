package com.omquark.fluidizationcraft.data;

import com.omquark.fluidizationcraft.blocks.ModBlocks;
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

public class ModBlockStateProvider extends BlockStateProvider {
    public ModBlockStateProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, FluidizationCraft.MODID, existingFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        blockItemWithTranslucency(ModBlocks.FROZEN_ACID_BLOCK.get());
        blockItemWithTranslucency(ModBlocks.FROZEN_CRYONITE_BLOCK.get());
        blockItemWithTranslucency(ModBlocks.ACID_BARRIER.get());
        blockItemWithTranslucency(ModBlocks.TRANSPARENT_ALUMINUM.get());

        blockWithItem(ModBlocks.ALUMINUM_ORE_BLOCK.get());
        blockWithItem(ModBlocks.LEAD_ORE_BLOCK.get());
        blockWithItem(ModBlocks.NEPTUNIUM_ORE_BLOCK.get());
        blockWithItem(ModBlocks.PLUTONIUM_ORE_BLOCK.get());
        blockWithItem(ModBlocks.RADIONITE_ORE_BLOCK.get());
        blockWithItem(ModBlocks.TIN_ORE_BLOCK.get());
        blockWithItem(ModBlocks.URANIUM_ORE_BLOCK.get());
        blockWithItem(ModBlocks.ACID_TANK.get());
//        blockWithItem(FluidizationBlocks.CAUSTIC_DRUM_BLOCK.get());

        cubeBottomTop(ModBlocks.ACID_TNT.get(), "acid_tnt");

        this.yDirectionalBlock(
                ModBlocks.DISSOLVINATOR_BLOCK.get(),
                (state) -> orientable(ModBlocks.DISSOLVINATOR_BLOCK.get(), "dissolvinator"),
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
