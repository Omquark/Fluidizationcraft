package com.omquark.fluidizationcraft.data;

import com.omquark.fluidizationcraft.FluidizationCraft;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.DamageTypeTagsProvider;
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

import java.util.concurrent.CompletableFuture;

public class FluidizationDamageTypeProvider extends DamageTypeTagsProvider {
    public FluidizationDamageTypeProvider(PackOutput packOutput, CompletableFuture<HolderLookup.Provider> future,
                                          ExistingFileHelper existingFileHelper) {
        super(packOutput, future, FluidizationCraft.MODID, existingFileHelper);
    }
}
