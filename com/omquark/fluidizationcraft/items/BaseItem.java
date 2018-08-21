package com.omquark.fluidizationcraft.items;

import com.omquark.fluidizationcraft.PlasmaCraftRedux;

import net.minecraft.item.Item;

public abstract class BaseItem extends Item{
	
	public BaseItem(String name)
	{
		setRegistryName(name);
		func_77637_a(PlasmaCraftRedux.reduxTab);
	}
}
