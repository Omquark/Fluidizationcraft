package com.omquark.fluidizationcraft.client.model;

import net.minecraft.block.Block;
import net.minecraft.block.BlockLiquid;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ItemMeshDefinition;
import net.minecraft.client.renderer.block.model.ModelBakery;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.block.statemap.StateMap;
import net.minecraft.client.renderer.block.statemap.StateMapperBase;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fluids.IFluidBlock;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.HashSet;
import java.util.Set;

import com.omquark.fluidizationcraft.PlasmaCraftRedux;
import com.omquark.fluidizationcraft.Entities.EntityAcidGrenade;
import com.omquark.fluidizationcraft.Entities.EntityAcidShot;
import com.omquark.fluidizationcraft.Entities.EntityCryoBlast;
import com.omquark.fluidizationcraft.Fluids.ReduxBaseFluid;
import com.omquark.fluidizationcraft.init.ReduxBlocks;
import com.omquark.fluidizationcraft.init.ReduxFluids;
import com.omquark.fluidizationcraft.init.ReduxItems;
import com.omquark.fluidizationcraft.items.ItemVial;
import com.omquark.fluidizationcraft.util.Constants;

@SideOnly(Side.CLIENT)
public class ReduxModelManager {
	public static final ReduxModelManager INSTANCE = new ReduxModelManager();

	private static final String FLUID_MODEL_PATH = Constants.RESOURCE_PREFIX + "fluid";
	
	private ReduxModelManager() {
	}

	public void registerAllModels() {
		registerFluidModels();
		registerBlockModels();
		registerItemModels();
		registerEntityRender();
		
		
		ReduxItems.vialEmpty.setModels();
		ReduxItems.baseMaterial.setModels();
	}

	private void registerFluidModels() {
		ReduxFluids.reduxFluidBlockList.forEach(this::registerFluidModel);
	}
	
	private void registerFluidModel(IFluidBlock fluidBlock) {
		ModelResourceLocation modelResourceLocation = new ModelResourceLocation(FLUID_MODEL_PATH, fluidBlock.getFluid().getName());

		ModelLoader.setCustomStateMapper((Block) fluidBlock, new StateMapperBase() {
			@Override
			protected ModelResourceLocation func_178132_a(IBlockState p_178132_1_) {
				return modelResourceLocation;
			}
		});
		
		registerBlockItemModel(fluidBlock.getFluid().getBlock());
	}

	private void registerBlockModels() {
		ReduxBlocks.reduxBlockList.forEach(this::registerBlockItemModel);
	}

	private void registerBlockItemModel(Block block) {
		Item item = Item.func_150898_a(block);
		if (item != null) {
			ModelLoader.setCustomModelResourceLocation(item, 0, new ModelResourceLocation(item.getRegistryName(), "inventory"));
		}
	}

	private void registerItemModels() {
		ReduxItems.reduxItemList.forEach(this::registerItemModel);
	}

	private void registerItemModel(Item item) {
		registerItemModel(item, item.getRegistryName().toString());
	}

	private void registerItemModel(Item item, String modelLocation) {
		final ModelResourceLocation fullModelLocation = new ModelResourceLocation(modelLocation);
		ModelLoader.setCustomModelResourceLocation(item, 0, fullModelLocation);
	}

	private void registerItemModelForMeta(Item item, int metadata, String variant) {
		
		ModelLoader.setCustomModelResourceLocation(item, metadata, new ModelResourceLocation(item.getRegistryName(), variant));
		
	}
	
	private void registerEntityRender() {
		RenderingRegistry.registerEntityRenderingHandler(EntityAcidGrenade.class, RenderGrenade::new);
		RenderingRegistry.registerEntityRenderingHandler(EntityCryoBlast.class, RenderBolt::new);		
		RenderingRegistry.registerEntityRenderingHandler(EntityAcidShot.class, RenderBolt::new);		
	}

}
