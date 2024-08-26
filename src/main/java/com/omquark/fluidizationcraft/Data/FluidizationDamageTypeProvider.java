package com.omquark.fluidizationcraft.data;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.DamageTypeTagsProvider;

import java.util.concurrent.CompletableFuture;

public class FluidizationDamageTypeProvider extends DamageTypeTagsProvider {
    public FluidizationDamageTypeProvider(PackOutput packOutput, CompletableFuture<HolderLookup.Provider> future) {
        super(packOutput, future);
    }
}
