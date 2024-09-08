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
                .define('A', FluidizationItems.INGOT_ALUMINUM.get())
                .define('R', FluidizationBlocks.TRANSPARENT_ALUMINUM.get())
                .pattern("AAA")
                .pattern("ARA")
                .pattern("AAA")
                .group("vial")
                .unlockedBy("has_transparent_aluminum", has(FluidizationBlocks.TRANSPARENT_ALUMINUM.get()))
                .unlockedBy("has_iron_ingots", has(Items.IRON_INGOT))
                .save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, FluidizationBlocks.TRANSPARENT_ALUMINUM.get())
                .define('A', FluidizationItems.INGOT_ALUMINUM.get())
                .define('G', Items.GLASS_PANE)
                .pattern(" A ")
                .pattern("AGA")
                .pattern(" A ")
                .group("misc")
                .unlockedBy("has_glass", has(Items.GLASS_PANE))
                .unlockedBy("has_aluminum_ingot", has(FluidizationItems.INGOT_ALUMINUM.get()))
                .save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, FluidizationBlocks.ACID_BARRIER.get())
                .define('R', FluidizationBlocks.TRANSPARENT_ALUMINUM.get())
                .define('V', FluidizationItems.VIAL_ACID.get())
                .pattern(" R ")
                .pattern("RVR")
                .pattern(" R ")
                .unlockedBy("has_transparent_aluminum", has(FluidizationBlocks.TRANSPARENT_ALUMINUM.get()))
                .unlockedBy("has_acid_vial", has(FluidizationItems.VIAL_ACID.get()))
                .save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, FluidizationItems.GUN_ACID.get())
                .define('V', FluidizationItems.VIAL_EMPTY.get())
                .define('I', Items.IRON_INGOT)
                .define('T', FluidizationBlocks.TRANSPARENT_ALUMINUM.get())
                .define('G', Items.GUNPOWDER)
                .pattern("VT ")
                .pattern("TGI")
                .pattern("II ")
                .unlockedBy("has_iron_ingot", has(Items.IRON_INGOT))
                .unlockedBy("has_gunpowder", has(Items.GUNPOWDER))
                .unlockedBy("has_transparent_aluminum", has(FluidizationBlocks.TRANSPARENT_ALUMINUM.get()))
                .unlockedBy("has_empty_vial", has(FluidizationItems.VIAL_EMPTY.get()))
                .save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, FluidizationItems.GUN_CRYO.get())
                .define('C', FluidizationItems.VIAL_CRYONITE.get())
                .define('I', Items.IRON_INGOT)
                .define('T', FluidizationBlocks.TRANSPARENT_ALUMINUM.get())
                .define('G', Items.GUNPOWDER)
                .pattern("CT ")
                .pattern("TGI")
                .pattern("II ")
                .unlockedBy("has_iron_ingot", has(Items.IRON_INGOT))
                .unlockedBy("has_gunpowder", has(Items.GUNPOWDER))
                .unlockedBy("has_transparent_aluminum", has(FluidizationBlocks.TRANSPARENT_ALUMINUM.get()))
                .unlockedBy("has_cryonite_vial", has(FluidizationItems.VIAL_CRYONITE.get()))
                .save(consumer);
    }
}
