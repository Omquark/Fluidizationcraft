package com.omquark.fluidizationcraft.Data;

import com.omquark.fluidizationcraft.Blocks.FluidizationBlocks;
import com.omquark.fluidizationcraft.FluidizationCraft;
import com.omquark.fluidizationcraft.Util.ModTags;
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
        this.tag(ModTags.Blocks.ALUMINUM_ORE)
                .add(FluidizationBlocks.ALUMINUM_ORE_BLOCK.get())
                .addTag(Tags.Blocks.ORES);
        this.tag(BlockTags.NEEDS_IRON_TOOL)
                .add(FluidizationBlocks.ALUMINUM_ORE_BLOCK.get());
    }
}
