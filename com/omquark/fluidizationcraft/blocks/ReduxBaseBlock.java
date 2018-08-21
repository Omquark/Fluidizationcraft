package com.omquark.fluidizationcraft.blocks;

import com.omquark.fluidizationcraft.PlasmaCraftRedux;

import net.minecraft.block.Block;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;

public class ReduxBaseBlock extends Block{

	public ReduxBaseBlock(Material material, MapColor mapColor) {
		super(material, mapColor);
		func_149647_a(PlasmaCraftRedux.reduxTab);
	}
}
