package com.omquark.fluidizationcraft.Data;

import com.omquark.fluidizationcraft.Items.FluidizationItems;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.world.level.material.Fluid;
import net.minecraftforge.common.crafting.conditions.IConditionBuilder;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class ModRecipeProvider extends RecipeProvider implements IConditionBuilder {
    public ModRecipeProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> future) {
        super(output, future);
    }

    @Override
    protected void buildRecipes(RecipeOutput consumer) {
        oreSmelting(consumer, List.of(FluidizationItems.RAW_ALUMINUM.get()),
                net.minecraft.data.recipes.RecipeCategory.MISC,
                FluidizationItems.RAW_ALUMINUM.get(), 0.7f, 200, "raw_aluminum");
    }
}
