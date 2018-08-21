package com.omquark.fluidizationcraft.util.RecipeHandler;

import java.util.HashMap;
import java.util.List;

import com.omquark.fluidizationcraft.init.ReduxBlocks;
import com.omquark.fluidizationcraft.init.ReduxItems;

import java.util.ArrayList;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.OreDictionary;

public class RecipeHandler {
	
	public static ArrayList<Recipe> DissolvinatorRecipes = new ArrayList<Recipe>();

	public static void initRecipes() {
		addSmeltingRecipes();
		createDissolvinatorRecipes(DissolvinatorRecipes);
	}
	
	//Adds the smelting recipes only for this mod
	private static void addSmeltingRecipes() {
		GameRegistry.addSmelting(ReduxBlocks.blockOreAluminum, ReduxItems.itemIngotAluminum, .4F);
		GameRegistry.addSmelting(ReduxBlocks.blockOreCopper, ReduxItems.itemIngotCopper, .4F);
		GameRegistry.addSmelting(ReduxBlocks.blockOreLead, ReduxItems.itemIngotLead, .4F);
		GameRegistry.addSmelting(ReduxBlocks.blockOreTin, ReduxItems.itemIngotTin, .4F);
		
		GameRegistry.addSmelting(ReduxItems.itemDustIron, new ItemStack(Items.field_151042_j), .4F);
		GameRegistry.addSmelting(ReduxItems.itemDustGold, new ItemStack(Items.field_151043_k), .6F);
		GameRegistry.addSmelting(ReduxItems.itemDustAluminum, ReduxItems.itemIngotAluminum, .4F);
		GameRegistry.addSmelting(ReduxItems.itemDustBronze, ReduxItems.itemIngotBronze, .5F);
		GameRegistry.addSmelting(ReduxItems.itemDustCopper, ReduxItems.itemIngotCopper, .4F);
		GameRegistry.addSmelting(ReduxItems.itemDustLead, ReduxItems.itemIngotLead, .4F);
		GameRegistry.addSmelting(ReduxItems.itemDustTin, ReduxItems.itemIngotTin, .4F);
		GameRegistry.addSmelting(ReduxItems.itemDustGlass, new ItemStack(ReduxBlocks.blockGlassTempered), .4F);
	}
	
	//This will add other mods ores to be dissolved.
	//If no "dusts" exist then they will not be added!
	
	private static void createDissolvinatorRecipes(List<Recipe> recipeList) {
		
		for(String oreName : OreDictionary.getOreNames()) {
			//System.out.println("Ore name: " + oreName);
			if(oreName.startsWith("ore")) {
				String dustName = "dust" + oreName.substring(3);
				if(!(OreDictionary.doesOreNameExist(dustName))) continue;
				//TODO: This assumes both the dust and ore exists because of the previous statement
				//If there is a null error from this method, odds are this is it!
				
				//System.out.println("Dust Name: " + dustName);
				
				ItemStack inputStack = OreDictionary.getOres(oreName).get(0);
				inputStack.func_190920_e(1);
				ItemStack outputStack = OreDictionary.getOres(dustName).get(0);
				outputStack.func_190920_e(2);
				
				//A special case to get extra redstone
				if(outputStack.func_77969_a(OreDictionary.getOres("dustRedstone").get(0))) {
					outputStack.func_190920_e(12);
				}
				recipeList.add(new Recipe(inputStack, outputStack, .4f));
			}
		}
	}

	public static Recipe getDissolvinatorRecipe(ItemStack input) {
		for(Recipe recipe : DissolvinatorRecipes) {
			if(recipe.getInput().func_77969_a(input)) return recipe;
		}
		return null;
	}
	
	public static class Recipe{
		private ItemStack in;
		private ItemStack out;
		private float exp;
		
		public Recipe(ItemStack input, ItemStack output, float xp) {
			in = input;
			out = output;
			exp = xp;
		}
		
		public ItemStack getInput() {return in;}
		public ItemStack getOutput() {return out;}
		public float getXp() {return exp;}
	}
}
