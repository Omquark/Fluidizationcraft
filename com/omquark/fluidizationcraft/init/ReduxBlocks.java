package com.omquark.fluidizationcraft.init;

import java.util.ArrayList;

import com.omquark.fluidizationcraft.PlasmaCraftRedux;
import com.omquark.fluidizationcraft.Fluids.ReduxBaseFluid;
import com.omquark.fluidizationcraft.blocks.BlockCausticPump;
import com.omquark.fluidizationcraft.blocks.BlockDissolvinator;
import com.omquark.fluidizationcraft.blocks.BlockFrozenAcid;
import com.omquark.fluidizationcraft.blocks.BlockFrozenCryonite;
import com.omquark.fluidizationcraft.blocks.BlockGlassTempered;
import com.omquark.fluidizationcraft.blocks.ReduxBaseBlock;
import com.omquark.fluidizationcraft.blocks.ReduxBaseOre;
import com.omquark.fluidizationcraft.tileentities.TileCausticPump;
import com.omquark.fluidizationcraft.tileentities.TileDissolvinator;
import com.omquark.fluidizationcraft.util.Constants;

import net.minecraft.block.Block;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.OreDictionary;

public class ReduxBlocks {
	
	public static final BlockFrozenAcid blockFrozenAcid = new BlockFrozenAcid(Material.field_151588_w, MapColor.field_151651_C);
	public static final BlockFrozenCryonite blockFrozenCryonite = new BlockFrozenCryonite(Material.field_151588_w, MapColor.field_151649_A);
	public static final ReduxBaseOre blockOreLead = new ReduxBaseOre(Material.field_151576_e, MapColor.field_151670_w);
	public static final ReduxBaseOre blockOreTin = new ReduxBaseOre(Material.field_151576_e, MapColor.field_151670_w);
	public static final ReduxBaseOre blockOreCopper = new ReduxBaseOre(Material.field_151576_e, MapColor.field_151670_w);
	public static final ReduxBaseOre blockOreAluminum = new ReduxBaseOre(Material.field_151576_e, MapColor.field_151670_w);
	public static final BlockGlassTempered blockGlassTempered = new BlockGlassTempered(Material.field_151592_s, MapColor.field_151674_s);
	public static final ReduxBaseOre blockTankAcid = new ReduxBaseOre(Material.field_151592_s, MapColor.field_151674_s);
	
	public static final BlockDissolvinator blockDissolvinator = new BlockDissolvinator(Material.field_151576_e, MapColor.field_151670_w);
	public static final BlockCausticPump blockCausticPump = new BlockCausticPump(Material.field_151576_e, MapColor.field_151670_w);
	
	public static final ArrayList<Block> reduxBlockList = new ArrayList<Block>();
	
	public static final ArrayList<ItemBlock> reduxBlockItemList = new ArrayList<ItemBlock>();
	
	//Registers the Block as well as the associated Item
	public static void registerBlocks()
	{
		registerBlock(blockFrozenAcid, "frozenAcid");
		blockFrozenAcid.setDefaultSlipperiness(1.0f);
		OreDictionary.registerOre("blockFrozenAcid", blockFrozenAcid);
		
		registerBlock(blockFrozenCryonite, "frozenCryonite");
		blockFrozenCryonite.setDefaultSlipperiness(1.0f);
		OreDictionary.registerOre("blockFrozenCryonite", blockFrozenCryonite);
		
		registerBlock(blockOreAluminum, "oreAluminum");
		blockOreAluminum.func_149711_c(3.0f).func_149752_b(5.0f).setHarvestLevel("pickaxe",  2);
		OreDictionary.registerOre("oreAluminum", blockOreAluminum);
		
		registerBlock(blockOreCopper, "oreCopper");
		blockOreCopper.func_149711_c(3.0f).func_149752_b(5.0f).setHarvestLevel("pickaxe",  2);
		OreDictionary.registerOre("oreCopper", blockOreCopper);

		registerBlock(blockOreLead, "oreLead");
		blockOreLead.func_149711_c(3.0f).func_149752_b(5.0f).setHarvestLevel("pickaxe",  2);
		OreDictionary.registerOre("oreLead", blockOreLead);

		registerBlock(blockOreTin, "oreTin");
		blockOreTin.func_149711_c(3.0f).func_149752_b(5.0f).setHarvestLevel("pickaxe",  2);
		OreDictionary.registerOre("oreTin", blockOreTin);
		
		registerBlock(blockGlassTempered, "blockGlassTempered");
		OreDictionary.registerOre("blockGlassTempered", blockGlassTempered);
		
		registerBlock(blockTankAcid, "blockTankAcid");
		blockTankAcid.func_149711_c(3.0f).func_149752_b(5.0f).setHarvestLevel("pickaxe",  2);
		OreDictionary.registerOre("blockTankAcid", blockTankAcid);

		registerBlock(blockDissolvinator, "blockDissolvinator");
		blockDissolvinator.func_149711_c(3.0f).func_149752_b(5.0f).setHarvestLevel("pickaxe",  2);
		GameRegistry.registerTileEntity(TileDissolvinator.class, "tileDissolvinator");		

		registerBlock(blockCausticPump, "blockCausticPump");
		blockCausticPump.func_149711_c(3.0f).func_149752_b(5.0f).setHarvestLevel("pickaxe",  2);
		GameRegistry.registerTileEntity(TileCausticPump.class, "tileCausticPump");		
	}
	
	private static void registerBlock(Block block, String name){

		ItemBlock itemBlock;
		
		block.setRegistryName(PlasmaCraftRedux.instance.MODID, name);

		name = PlasmaCraftRedux.MODID + "." + name;
		block.func_149663_c(name);
		
		Constants.BLOCK_REGISTRY.register(block);
		
		reduxBlockList.add(block);

		itemBlock = new ItemBlock(block);
		
		itemBlock.setRegistryName(block.getRegistryName().toString());

		Constants.ITEM_REGISTRY.register(itemBlock);
	}
}
