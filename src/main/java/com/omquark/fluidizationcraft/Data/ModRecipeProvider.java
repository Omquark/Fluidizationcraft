package com.omquark.fluidizationcraft.data;

import com.omquark.fluidizationcraft.Items.FluidizationItems;
import com.omquark.fluidizationcraft.blocks.FluidizationBlocks;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.world.item.Items;
import net.neoforged.neoforge.common.conditions.IConditionBuilder;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@ParametersAreNonnullByDefault
public class ModRecipeProvider extends RecipeProvider implements IConditionBuilder {
    public ModRecipeProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> future) {
        super(output, future);
    }

    @Override
    protected void buildRecipes(RecipeOutput consumer) {
        oreSmelting(consumer, List.of(FluidizationItems.RAW_ALUMINUM.get()), RecipeCategory.MISC,
                FluidizationItems.INGOT_ALUMINUM.get(), 0.7f, 200, "raw_aluminum");
        oreSmelting(consumer, List.of(FluidizationItems.RAW_LEAD.get()), RecipeCategory.MISC,
                FluidizationItems.INGOT_LEAD.get(), .7f, 200, "raw_lead");
        oreSmelting(consumer, List.of(FluidizationItems.RAW_NEPTUNIUM.get()), RecipeCategory.MISC,
                FluidizationItems.INGOT_NEPTUNIUM.get(), .7f, 200, "raw_neptunium");
        oreSmelting(consumer, List.of(FluidizationItems.RAW_PLUTONIUM.get()), RecipeCategory.MISC,
                FluidizationItems.INGOT_PLUTONIUM.get(), .7f, 200, "raw_plutonium");
        oreSmelting(consumer, List.of(FluidizationItems.RAW_RADIONITE.get()), RecipeCategory.MISC,
                FluidizationItems.INGOT_RADIONITE.get(), .7f, 200, "raw_radionite");
        oreSmelting(consumer, List.of(FluidizationItems.RAW_TIN.get()), RecipeCategory.MISC,
                FluidizationItems.INGOT_TIN.get(), .7f, 200, "raw_tin");
        oreSmelting(consumer, List.of(FluidizationItems.RAW_URANIUM.get()), RecipeCategory.MISC,
                FluidizationItems.INGOT_URANIUM.get(), .7f, 200, "raw_uranium");

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, FluidizationItems.VIAL_EMPTY.get())
                .define('I', Items.IRON_INGOT)
                .define('R', FluidizationBlocks.REINFORCED_GLASS.get())
                .pattern("III")
                .pattern("IRI")
                .pattern("III")
                .group("vial")
                .unlockedBy("has_reinforced_glass", has(FluidizationBlocks.REINFORCED_GLASS.get()))
                .unlockedBy("has_iron_ingots", has(Items.IRON_INGOT))
                .save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, FluidizationBlocks.REINFORCED_GLASS.get())
                .define('L', FluidizationItems.INGOT_LEAD.get())
                .define('G', Items.GLASS_PANE)
                .pattern(" L ")
                .pattern("LGL")
                .pattern(" L ")
                .group("misc")
                .unlockedBy("has_glass", has(Items.GLASS_PANE))
                .unlockedBy("has_lead_ingot", has(FluidizationItems.INGOT_LEAD.get()))
                .save(consumer);
    }
}
