package com.omquark.fluidizationcraft.data;

import com.omquark.fluidizationcraft.blocks.ModBlocks;
import com.omquark.fluidizationcraft.FluidizationCraft;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.common.data.BlockTagsProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class ModBlockDataGenerator extends BlockTagsProvider {
    public ModBlockDataGenerator(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, FluidizationCraft.MODID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {

        this.tag(Tags.Blocks.ORES)
                .add(ModBlocks.ALUMINUM_ORE_BLOCK.get())
                .add(ModBlocks.LEAD_ORE_BLOCK.get())
                .add(ModBlocks.NEPTUNIUM_ORE_BLOCK.get())
                .add(ModBlocks.PLUTONIUM_ORE_BLOCK.get())
                .add(ModBlocks.RADIONITE_ORE_BLOCK.get())
                .add(ModBlocks.TIN_ORE_BLOCK.get())
                .add(ModBlocks.URANIUM_ORE_BLOCK.get());

        this.tag(BlockTags.NEEDS_IRON_TOOL)
                .add(ModBlocks.ALUMINUM_ORE_BLOCK.get())
                .add(ModBlocks.LEAD_ORE_BLOCK.get())
                .add(ModBlocks.NEPTUNIUM_ORE_BLOCK.get())
                .add(ModBlocks.PLUTONIUM_ORE_BLOCK.get())
                .add(ModBlocks.RADIONITE_ORE_BLOCK.get())
                .add(ModBlocks.TIN_ORE_BLOCK.get())
                .add(ModBlocks.URANIUM_ORE_BLOCK.get());

        this.tag(BlockTags.MINEABLE_WITH_PICKAXE)
                .add(ModBlocks.ALUMINUM_ORE_BLOCK.get())
                .add(ModBlocks.LEAD_ORE_BLOCK.get())
                .add(ModBlocks.NEPTUNIUM_ORE_BLOCK.get())
                .add(ModBlocks.PLUTONIUM_ORE_BLOCK.get())
                .add(ModBlocks.RADIONITE_ORE_BLOCK.get())
                .add(ModBlocks.TIN_ORE_BLOCK.get())
                .add(ModBlocks.URANIUM_ORE_BLOCK.get())
                .add(ModBlocks.DISSOLVINATOR_BLOCK.get());
    }
}
