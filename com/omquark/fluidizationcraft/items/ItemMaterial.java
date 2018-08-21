package com.omquark.fluidizationcraft.items;

import java.util.Map;

import net.minecraft.client.renderer.ItemMeshDefinition;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.oredict.OreDictionary;

public class ItemMaterial extends ItemMultiBase{

	public ItemMaterial(String name) {
		super(name);
		
		nameMap.put(0, "dustIron");
		nameMap.put(1, "dustGold");
		nameMap.put(2, "dustAluminum");
		nameMap.put(3, "dustBronze");
		nameMap.put(4, "dustCopper");
		nameMap.put(5, "dustGlass");
		nameMap.put(6, "dustLead");
		nameMap.put(7, "dustTin");

		nameMap.put(8, "ingotAluminum");
		nameMap.put(9, "ingotBronze");
		nameMap.put(10, "ingotCopper");
		nameMap.put(11, "ingotLead");
		nameMap.put(12, "ingotTin");

		func_77627_a(true);
		func_77656_e(nameMap.size());
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void setModels() {
		super.setModels();
	}
}
