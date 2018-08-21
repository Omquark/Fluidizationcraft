package com.omquark.fluidizationcraft.items;

import java.util.HashMap;
import java.util.Map;

import com.omquark.fluidizationcraft.PlasmaCraftRedux;
import com.omquark.fluidizationcraft.init.ReduxItems;

import net.minecraft.client.renderer.ItemMeshDefinition;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.oredict.OreDictionary;

public class ItemMultiBase extends BaseItem {

	protected HashMap<Integer, String> nameMap = new HashMap<Integer, String>();

	public ItemMultiBase(String name) {
		super(name);
		func_77627_a(true);
	}

	@Override
	public int func_77647_b(int damage) {
		return damage;
	}
	
	public int getMetadata(ItemStack stack) {
		return stack.func_77952_i();
	}

	@Override
	public boolean showDurabilityBar(ItemStack stack) {
		return false;
	}
	
	@Override
	public double getDurabilityForDisplay(ItemStack stack) {
		return 0.0;
	}

	@Override 
	public String func_77667_c(ItemStack stack) {
		return "item." + PlasmaCraftRedux.MODID + "." + nameMap.get(stack.func_77952_i());
	}
	
	@Override
	public void func_150895_a(CreativeTabs tab, NonNullList<ItemStack> subItems){
		if(func_194125_a(tab)) {
			for(int i = 0; i < nameMap.size(); i++){
				subItems.add(new ItemStack(this, 1, i));
			}
		}
	}
	
	public void addSubsToOreDictionary() {
		for(Map.Entry<Integer, String> entry : nameMap.entrySet()) {
			OreDictionary.registerOre(entry.getValue(), new ItemStack(this, 1, entry.getKey()));
			//System.out.println(entry.getKey() + "=" + entry.getValue());
		}
	}
	
	@SideOnly(Side.CLIENT)
	public void setModels() {

		for(Map.Entry<Integer, String> entry : nameMap.entrySet()) {
			
			ModelLoader.registerItemVariants(this, new ModelResourceLocation(getRegistryName(), "type=" + entry.getValue()));
		}

		ModelLoader.setCustomMeshDefinition(this, new ItemMeshDefinition() {
			public ModelResourceLocation func_178113_a(ItemStack stack) {
				
				return new ModelResourceLocation(stack.func_77973_b().getRegistryName(),
						"type=" + nameMap.get(stack.func_77960_j()));
			}
		});
	}
}
