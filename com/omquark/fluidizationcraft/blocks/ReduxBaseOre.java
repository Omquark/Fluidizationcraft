package com.omquark.fluidizationcraft.blocks;

import java.util.HashMap;
import java.util.Map;

import com.omquark.fluidizationcraft.PlasmaCraftRedux;

import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.ItemMeshDefinition;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ReduxBaseOre extends ReduxBaseBlock{
	
	public ReduxBaseOre(Material material, MapColor color){
		super(material, color);
	}
}
