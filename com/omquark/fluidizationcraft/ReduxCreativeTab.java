package com.omquark.fluidizationcraft;

import java.util.List;

import com.omquark.fluidizationcraft.init.ReduxFluids;
import com.omquark.fluidizationcraft.init.ReduxItems;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.util.NonNullList;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ReduxCreativeTab extends CreativeTabs{
	
	private ItemStack tabIcon;
	
	public ReduxCreativeTab() {
		super(PlasmaCraftRedux.MODID);
	}
	
	public void setTabIcon(ItemStack icon) {
		tabIcon = icon;
	}
	
	@SideOnly(Side.CLIENT)
	@Override
	public ItemStack func_78016_d() {
		return tabIcon;
	}
}
