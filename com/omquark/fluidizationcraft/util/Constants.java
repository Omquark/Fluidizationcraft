package com.omquark.fluidizationcraft.util;

import com.omquark.fluidizationcraft.PlasmaCraftRedux;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.registries.IForgeRegistry;

public class Constants {
	public static final String RESOURCE_PREFIX = PlasmaCraftRedux.MODID + ":";
	
	public static final IForgeRegistry<Item> ITEM_REGISTRY = GameRegistry.findRegistry(Item.class);
	public static final IForgeRegistry<Block> BLOCK_REGISTRY = GameRegistry.findRegistry(Block.class);
	
}
