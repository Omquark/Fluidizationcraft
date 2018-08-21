package com.omquark.fluidizationcraft;

import com.omquark.fluidizationcraft.Entities.EntityAcidGrenade;
import com.omquark.fluidizationcraft.Entities.EntityCryoBlast;
import com.omquark.fluidizationcraft.Fluids.AcidFluidBlock;
import com.omquark.fluidizationcraft.Fluids.ReduxBaseFluid;
import com.omquark.fluidizationcraft.util.RecipeHandler.RecipeHandler;
import com.omquark.fluidizationcraft.client.model.ReduxModelManager;
import com.omquark.fluidizationcraft.client.model.RenderBolt;
import com.omquark.fluidizationcraft.client.model.RenderGrenade;
import com.omquark.fluidizationcraft.config.Config;
import com.omquark.fluidizationcraft.guiHandler.FluidizationGuiHandler;
import com.omquark.fluidizationcraft.init.ReduxBlocks;
import com.omquark.fluidizationcraft.init.ReduxEntities;
import com.omquark.fluidizationcraft.init.ReduxFluids;
import com.omquark.fluidizationcraft.init.ReduxItems;
import com.omquark.fluidizationcraft.init.ReduxWorldGen;
import com.omquark.fluidizationcraft.items.ItemAcidGrenade;
import com.omquark.fluidizationcraft.proxy.CommonProxy;
import com.omquark.fluidizationcraft.tileentities.TileDissolvinator;
import com.omquark.fluidizationcraft.util.Constants;
import com.omquark.fluidizationcraft.world.gen.PlasmaCraftLakesGen;
import com.omquark.fluidizationcraft.world.gen.PlasmaCraftOreGen;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.block.statemap.IStateMapper;
import net.minecraft.client.renderer.block.statemap.StateMap;
import net.minecraft.client.renderer.block.statemap.StateMapperBase;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.event.entity.item.ItemExpireEvent;
import net.minecraftforge.fluids.BlockFluidClassic;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.IFluidBlock;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.IFMLLoadingPlugin.Name;

@Mod(modid = PlasmaCraftRedux.MODID, name="fluidizationcraft", version = "0.7.1023b")
//@Ver(value = PlasmaCraftRedux.version)

public class PlasmaCraftRedux {

	//Seed: 5618737769661604307

	public static final String MODID = "fluidizationcraft";
	
	public static final ReduxCreativeTab reduxTab = new ReduxCreativeTab();

	@Instance(MODID)
	public static PlasmaCraftRedux instance = new PlasmaCraftRedux();

	static{
		FluidRegistry.enableUniversalBucket(); // Must be called before preInit
	}
	
	//TODO: Create a subscribe event to give enemies caustic weapons. Level the playing field a little!
	//These may have to be toned down a little.
	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		Config.load(event);
		ReduxBlocks.registerBlocks();
		ReduxFluids.registerFluids();
		ReduxItems.registerItems();
		ReduxEntities.registerEntities();
		
		ReduxModelManager.INSTANCE.registerAllModels();

		CommonProxy.INSTANCE.RegisterGuis();
	}

	@EventHandler
	public void init(FMLInitializationEvent event) {
		ReduxItems.setBucketsTabs();
		reduxTab.setTabIcon(ReduxItems.acidBucket);
		RecipeHandler.initRecipes();
		ReduxWorldGen.init();
	}
	
	@EventHandler
	public void postInit(FMLPostInitializationEvent event) {
	}
	
	//Move this to a common proxy class!
}
