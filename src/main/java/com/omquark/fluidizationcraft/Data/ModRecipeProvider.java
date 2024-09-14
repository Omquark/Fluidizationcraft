package com.omquark.fluidizationcraft.data;

import com.omquark.fluidizationcraft.Items.FluidizationItems;
import com.omquark.fluidizationcraft.blocks.FluidizationBlocks;
import com.omquark.fluidizationcraft.recipe.DissolvinatorRecipeBuilder;
import com.omquark.fluidizationcraft.util.EverythingNonNullByDefault;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.block.Blocks;
import net.neoforged.neoforge.common.conditions.IConditionBuilder;
import net.neoforged.neoforge.common.crafting.SizedIngredient;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@EverythingNonNullByDefault
public class ModRecipeProvider extends RecipeProvider implements IConditionBuilder {
    public ModRecipeProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> future) {
        super(output, future);
    }

    @Override
    protected void buildRecipes(RecipeOutput consumer) {
        buildSmelting(consumer);
        buildShaped(consumer);
        buildDissolvinator(consumer);
    }

    private void buildSmelting (RecipeOutput consumer){
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
        oreSmelting(consumer, List.of(FluidizationItems.DUST_IRON.get()), RecipeCategory.MISC,
                FluidizationItems.INGOT_ALUMINUM.get(), 0.7f, 200, "raw_aluminum");
        oreSmelting(consumer, List.of(FluidizationItems.DUST_GOLD.get()), RecipeCategory.MISC,
                FluidizationItems.INGOT_LEAD.get(), .7f, 200, "raw_lead");
        oreSmelting(consumer, List.of(FluidizationItems.DUST_ALUMINUM.get()), RecipeCategory.MISC,
                FluidizationItems.INGOT_ALUMINUM.get(), 0.7f, 200, "raw_aluminum");
        oreSmelting(consumer, List.of(FluidizationItems.DUST_LEAD.get()), RecipeCategory.MISC,
                FluidizationItems.INGOT_LEAD.get(), .7f, 200, "raw_lead");
        oreSmelting(consumer, List.of(FluidizationItems.DUST_NEPTUNIUM.get()), RecipeCategory.MISC,
                FluidizationItems.INGOT_NEPTUNIUM.get(), .7f, 200, "raw_neptunium");
        oreSmelting(consumer, List.of(FluidizationItems.DUST_PLUTONIUM.get()), RecipeCategory.MISC,
                FluidizationItems.INGOT_PLUTONIUM.get(), .7f, 200, "raw_plutonium");
        oreSmelting(consumer, List.of(FluidizationItems.DUST_RADIONITE.get()), RecipeCategory.MISC,
                FluidizationItems.INGOT_RADIONITE.get(), .7f, 200, "raw_radionite");
        oreSmelting(consumer, List.of(FluidizationItems.DUST_TIN.get()), RecipeCategory.MISC,
                FluidizationItems.INGOT_TIN.get(), .7f, 200, "raw_tin");
        oreSmelting(consumer, List.of(FluidizationItems.DUST_URANIUM.get()), RecipeCategory.MISC,
                FluidizationItems.INGOT_URANIUM.get(), .7f, 200, "raw_uranium");
    }

    private void buildShaped(RecipeOutput consumer){
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

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, FluidizationBlocks.ACID_TANK.get())
                .define('T', FluidizationBlocks.TRANSPARENT_ALUMINUM.get())
                .define('C', Items.COPPER_INGOT)
                .pattern("CTC")
                .pattern("T T")
                .pattern("CTC")
                .unlockedBy("has_transparent_aluminum", has(FluidizationBlocks.TRANSPARENT_ALUMINUM.get()))
                .unlockedBy("has_copper_ingot", has(Items.COPPER_INGOT))
                .save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, FluidizationBlocks.DISSOLVINATOR_BLOCK.get())
                .define('I', Items.IRON_INGOT)
                .define('A', FluidizationItems.INGOT_ALUMINUM.get())
                .define('V', FluidizationItems.VIAL_EMPTY.get())
                .define('T', FluidizationBlocks.ACID_TANK.get())
                .pattern("IAI")
                .pattern("VTV")
                .pattern("IAI")
                .unlockedBy("has_iron_ingot", has(Items.IRON_INGOT))
                .unlockedBy("has_aluminum_ingot", has(FluidizationItems.INGOT_ALUMINUM.get()))
                .unlockedBy("has_empty_vial", has(FluidizationItems.VIAL_EMPTY.get()))
                .unlockedBy("has_acid_tank", has(FluidizationBlocks.ACID_TANK.get()))
                .save(consumer);
    }

    private void buildDissolvinator(RecipeOutput consumer){

        new DissolvinatorRecipeBuilder(
                new SizedIngredient(Ingredient.of(Items.RAW_IRON), 3),
                new ItemStack(FluidizationItems.DUST_IRON.get(), 4)
        )
                .unlockedBy("has_raw_iron", has(Items.RAW_IRON))
                .unlockedBy("has_acid_vial", has(FluidizationItems.VIAL_ACID.get()))
                .save(consumer, "dust_iron_from_iron_raw");

        new DissolvinatorRecipeBuilder(
                new SizedIngredient(Ingredient.of(Blocks.IRON_ORE), 1),
                new ItemStack(FluidizationItems.DUST_IRON.get(), 2)
        )
                .unlockedBy("has_iron_ore", has(Blocks.IRON_ORE))
                .unlockedBy("has_acid_vial", has(FluidizationItems.VIAL_ACID.get()))
                .save(consumer, "dust_iron_from_iron_ore");

        new DissolvinatorRecipeBuilder(
                new SizedIngredient(Ingredient.of(Items.RAW_GOLD), 3),
                new ItemStack(FluidizationItems.DUST_GOLD.get(), 4)
        )
                .unlockedBy("has_raw_gold", has(Items.RAW_GOLD))
                .unlockedBy("has_acid_vial", has(FluidizationItems.VIAL_ACID.get()))
                .save(consumer, "dust_gold_from_raw_gold");

        new DissolvinatorRecipeBuilder(
                new SizedIngredient(Ingredient.of(Blocks.GOLD_ORE), 1),
                new ItemStack(FluidizationItems.DUST_GOLD.get(), 2)
        )
                .unlockedBy("has_gold_ore", has(Blocks.GOLD_ORE))
                .unlockedBy("has_acid_vial", has(FluidizationItems.VIAL_ACID.get()))
                .save(consumer, "dust_gold_from_gold_ore");

        new DissolvinatorRecipeBuilder(
                new SizedIngredient(Ingredient.of(Items.RAW_COPPER), 3),
                new ItemStack(FluidizationItems.DUST_COPPER.get(), 4)
        )
                .unlockedBy("has_raw_copper", has(Items.RAW_COPPER))
                .unlockedBy("has_acid_vial", has(FluidizationItems.VIAL_ACID.get()))
                .save(consumer, "dust_copper_from_raw_copper");

        new DissolvinatorRecipeBuilder(
                new SizedIngredient(Ingredient.of(Blocks.COPPER_ORE), 1),
                new ItemStack(FluidizationItems.DUST_COPPER.get(), 2)
        )
                .unlockedBy("has_copper_ore", has(Blocks.COPPER_ORE))
                .unlockedBy("has_acid_vial", has(FluidizationItems.VIAL_ACID.get()))
                .save(consumer, "dust_copper_from_copper_ore");

        new DissolvinatorRecipeBuilder(
                new SizedIngredient(Ingredient.of(Blocks.REDSTONE_ORE), 1),
                new ItemStack(Blocks.REDSTONE_WIRE, 12)
        )
                .unlockedBy("has_redstone_ore", has(Blocks.REDSTONE_ORE))
                .unlockedBy("has_acid_vial", has(FluidizationItems.VIAL_ACID.get()))
                .save(consumer, "dust_redstone_from_redstone_ore");

        new DissolvinatorRecipeBuilder(
                new SizedIngredient(Ingredient.of(Blocks.DIAMOND_ORE), 1),
                new ItemStack(Items.DIAMOND, 2)
        )
                .unlockedBy("has_diamond_ore", has(Blocks.DIAMOND_ORE))
                .unlockedBy("has_acid_vial", has(FluidizationItems.VIAL_ACID.get()))
                .save(consumer, "diamond_from_diamond_ore");

        new DissolvinatorRecipeBuilder(
                new SizedIngredient(Ingredient.of(Blocks.LAPIS_ORE), 1),
                new ItemStack(Items.LAPIS_LAZULI, 12)
        )
                .unlockedBy("has_lapis_ore", has(Blocks.LAPIS_ORE))
                .unlockedBy("has_acid_vial", has(FluidizationItems.VIAL_ACID.get()))
                .save(consumer, "lapis_lazuli_from_lapis_ore");

        new DissolvinatorRecipeBuilder(
                new SizedIngredient(Ingredient.of(FluidizationItems.RAW_ALUMINUM.get()), 3),
                new ItemStack(FluidizationItems.DUST_ALUMINUM.get(), 4)
        )
                .unlockedBy("has_raw_aluminum", has(FluidizationItems.RAW_ALUMINUM.get()))
                .unlockedBy("has_acid_vial", has(FluidizationItems.VIAL_ACID.get()))
                .save(consumer, "dust_aluminum_from_raw_aluminum");

        new DissolvinatorRecipeBuilder(
                new SizedIngredient(Ingredient.of(FluidizationBlocks.ALUMINUM_ORE_BLOCK.get()), 1),
                new ItemStack(FluidizationItems.DUST_ALUMINUM.get(), 2)
        )
                .unlockedBy("has_aluminum_ore", has(FluidizationBlocks.ALUMINUM_ORE_BLOCK.get()))
                .unlockedBy("has_acid_vial", has(FluidizationItems.VIAL_ACID.get()))
                .save(consumer, "dust_aluminum_from_aluminum_ore");

        new DissolvinatorRecipeBuilder(
                new SizedIngredient(Ingredient.of(FluidizationItems.RAW_LEAD.get()), 3),
                new ItemStack(FluidizationItems.DUST_LEAD.get(), 4)
        )
                .unlockedBy("has_raw_lead", has(FluidizationItems.RAW_LEAD.get()))
                .unlockedBy("has_acid_vial", has(FluidizationItems.VIAL_ACID.get()))
                .save(consumer, "lead_dust_from_raw_lead");

        new DissolvinatorRecipeBuilder(
                new SizedIngredient(Ingredient.of(FluidizationBlocks.LEAD_ORE_BLOCK.get()), 1),
                new ItemStack(FluidizationItems.DUST_LEAD.get(), 2)
        )
                .unlockedBy("has_lead_ore", has(FluidizationBlocks.LEAD_ORE_BLOCK.get()))
                .unlockedBy("has_acid_vial", has(FluidizationItems.VIAL_ACID.get()))
                .save(consumer, "lead_dust_from_lead_ore");

        new DissolvinatorRecipeBuilder(
                new SizedIngredient(Ingredient.of(FluidizationItems.RAW_NEPTUNIUM.get()), 3),
                new ItemStack(FluidizationItems.DUST_NEPTUNIUM.get(), 4)
        )
                .unlockedBy("has_raw_neptunium", has(FluidizationItems.RAW_NEPTUNIUM.get()))
                .unlockedBy("has_acid_vial", has(FluidizationItems.VIAL_ACID.get()))
                .save(consumer, "dust_neptunium_from_raw_neptunium");

        new DissolvinatorRecipeBuilder(
                new SizedIngredient(Ingredient.of(FluidizationBlocks.NEPTUNIUM_ORE_BLOCK.get()), 1),
                new ItemStack(FluidizationItems.DUST_NEPTUNIUM.get(), 2)
        )
                .unlockedBy("has_neptunium_ore", has(FluidizationBlocks.NEPTUNIUM_ORE_BLOCK.get()))
                .unlockedBy("has_acid_vial", has(FluidizationItems.VIAL_ACID.get()))
                .save(consumer, "dust_neptunium_from_neptunium_ore");

        new DissolvinatorRecipeBuilder(
                new SizedIngredient(Ingredient.of(FluidizationItems.RAW_PLUTONIUM.get()), 3),
                new ItemStack(FluidizationItems.DUST_PLUTONIUM.get(), 4)
        )
                .unlockedBy("has_raw_plutonium", has(FluidizationItems.RAW_PLUTONIUM.get()))
                .unlockedBy("has_acid_vial", has(FluidizationItems.VIAL_ACID.get()))
                .save(consumer, "dust_plutonium_from_raw_plutonium");

        new DissolvinatorRecipeBuilder(
                new SizedIngredient(Ingredient.of(FluidizationBlocks.PLUTONIUM_ORE_BLOCK.get()), 1),
                new ItemStack(FluidizationItems.DUST_PLUTONIUM.get(), 2)
        )
                .unlockedBy("has_plutonium_ore", has(FluidizationBlocks.PLUTONIUM_ORE_BLOCK.get()))
                .unlockedBy("has_acid_vial", has(FluidizationItems.VIAL_ACID.get()))
                .save(consumer, "dust_plutonium_from_plutonium_ore");

        new DissolvinatorRecipeBuilder(
                new SizedIngredient(Ingredient.of(FluidizationItems.RAW_RADIONITE.get()), 3),
                new ItemStack(FluidizationItems.DUST_RADIONITE.get(), 4)
        )
                .unlockedBy("has_raw_radionite", has(FluidizationItems.RAW_RADIONITE.get()))
                .unlockedBy("has_acid_vial", has(FluidizationItems.VIAL_ACID.get()))
                .save(consumer, "dust_radionite_from_raw_radionite");

        new DissolvinatorRecipeBuilder(
                new SizedIngredient(Ingredient.of(FluidizationBlocks.RADIONITE_ORE_BLOCK.get()), 1),
                new ItemStack(FluidizationItems.DUST_RADIONITE.get(), 2)
        )
                .unlockedBy("has_radionite_ore", has(FluidizationBlocks.RADIONITE_ORE_BLOCK.get()))
                .unlockedBy("has_acid_vial", has(FluidizationItems.VIAL_ACID.get()))
                .save(consumer, "dust_radionite_from_radionite_ore");

        new DissolvinatorRecipeBuilder(
                new SizedIngredient(Ingredient.of(FluidizationItems.RAW_TIN.get()), 3),
                new ItemStack(FluidizationItems.DUST_TIN.get(), 4)
        )
                .unlockedBy("has_raw_tin", has(FluidizationItems.RAW_TIN.get()))
                .unlockedBy("has_acid_vial", has(FluidizationItems.VIAL_ACID.get()))
                .save(consumer, "dust_tin_from_raw_tin");

        new DissolvinatorRecipeBuilder(
                new SizedIngredient(Ingredient.of(FluidizationBlocks.TIN_ORE_BLOCK.get()), 1),
                new ItemStack(FluidizationItems.DUST_TIN.get(), 2)
        )
                .unlockedBy("has_aluminum_ore", has(FluidizationBlocks.TIN_ORE_BLOCK.get()))
                .unlockedBy("has_acid_vial", has(FluidizationItems.VIAL_ACID.get()))
                .save(consumer, "dust_tin_from_tin_ore");

        new DissolvinatorRecipeBuilder(
                new SizedIngredient(Ingredient.of(FluidizationItems.RAW_URANIUM.get()), 3),
                new ItemStack(FluidizationItems.DUST_URANIUM.get(), 4)
        )
                .unlockedBy("has_raw_uranium", has(FluidizationItems.RAW_URANIUM.get()))
                .unlockedBy("has_acid_vial", has(FluidizationItems.VIAL_ACID.get()))
                .save(consumer, "dust_uranium_from_raw_uranium");

        new DissolvinatorRecipeBuilder(
                new SizedIngredient(Ingredient.of(FluidizationBlocks.URANIUM_ORE_BLOCK.get()), 1),
                new ItemStack(FluidizationItems.DUST_URANIUM.get(), 2)
        )
                .unlockedBy("has_uranium_ore", has(FluidizationBlocks.URANIUM_ORE_BLOCK.get()))
                .unlockedBy("has_acid_vial", has(FluidizationItems.VIAL_ACID.get()))
                .save(consumer, "dust_uranium_from_uranium_ore");
    }
}
