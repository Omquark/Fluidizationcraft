package com.omquark.fluidizationcraft.data;

import com.omquark.fluidizationcraft.blocks.FluidizationBlocks;
import com.omquark.fluidizationcraft.FluidizationCraft;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.data.BlockTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class ModBlockDataGenerator extends BlockTagsProvider {
    public ModBlockDataGenerator(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, FluidizationCraft.MODID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {

        this.tag(Tags.Blocks.ORES)
                .add(FluidizationBlocks.ALUMINUM_ORE_BLOCK.get())
                .add(FluidizationBlocks.LEAD_ORE_BLOCK.get())
                .add(FluidizationBlocks.NEPTUNIUM_ORE_BLOCK.get())
                .add(FluidizationBlocks.PLUTONIUM_ORE_BLOCK.get())
                .add(FluidizationBlocks.RADIONITE_ORE_BLOCK.get())
                .add(FluidizationBlocks.TIN_ORE_BLOCK.get())
                .add(FluidizationBlocks.URANIUM_ORE_BLOCK.get());

        this.tag(BlockTags.NEEDS_IRON_TOOL)
                .add(FluidizationBlocks.ALUMINUM_ORE_BLOCK.get())
                .add(FluidizationBlocks.LEAD_ORE_BLOCK.get())
                .add(FluidizationBlocks.NEPTUNIUM_ORE_BLOCK.get())
                .add(FluidizationBlocks.PLUTONIUM_ORE_BLOCK.get())
                .add(FluidizationBlocks.RADIONITE_ORE_BLOCK.get())
                .add(FluidizationBlocks.TIN_ORE_BLOCK.get())
                .add(FluidizationBlocks.URANIUM_ORE_BLOCK.get());

        this.tag(BlockTags.MINEABLE_WITH_PICKAXE)
                .add(FluidizationBlocks.ALUMINUM_ORE_BLOCK.get())
                .add(FluidizationBlocks.LEAD_ORE_BLOCK.get())
                .add(FluidizationBlocks.NEPTUNIUM_ORE_BLOCK.get())
                .add(FluidizationBlocks.PLUTONIUM_ORE_BLOCK.get())
                .add(FluidizationBlocks.RADIONITE_ORE_BLOCK.get())
                .add(FluidizationBlocks.TIN_ORE_BLOCK.get())
                .add(FluidizationBlocks.URANIUM_ORE_BLOCK.get())
                .add(FluidizationBlocks.DISSOLVINATOR_BLOCK.get());
    }
}
