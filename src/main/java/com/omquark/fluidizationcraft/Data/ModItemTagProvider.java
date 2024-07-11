package com.omquark.fluidizationcraft.Data;

import com.omquark.fluidizationcraft.FluidizationCraft;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class ModItemTagProvider extends ItemTagsProvider {

    public ModItemTagProvider(PackOutput p_275204_, CompletableFuture<HolderLookup.Provider> p_275194_,
                              CompletableFuture<TagLookup<Item>> p_275207_, CompletableFuture<TagLookup<Block>> p_275634_,
                              @Nullable ExistingFileHelper existingFileHelper) {
        super(p_275204_, p_275194_, p_275207_, p_275634_, FluidizationCraft.MODID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider p_256380_) {

    }
}
