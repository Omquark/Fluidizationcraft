package com.omquark.fluidizationcraft.init;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.function.Consumer;
import java.util.function.Function;

import com.omquark.fluidizationcraft.PlasmaCraftRedux;
import com.omquark.fluidizationcraft.Fluids.AcidFluidBlock;
import com.omquark.fluidizationcraft.Fluids.CryoniteFluidBlock;
import com.omquark.fluidizationcraft.Fluids.NeptuniumFluidBlock;
import com.omquark.fluidizationcraft.Fluids.NetherflowFluidBlock;
import com.omquark.fluidizationcraft.Fluids.PlutoniumFluidBlock;
import com.omquark.fluidizationcraft.Fluids.PyroniteFluidBlock;
import com.omquark.fluidizationcraft.Fluids.RadioniteFluidBlock;
import com.omquark.fluidizationcraft.Fluids.ReduxBaseFluid;
import com.omquark.fluidizationcraft.Fluids.UraniumFluidBlock;
import com.omquark.fluidizationcraft.blocks.*;
import com.omquark.fluidizationcraft.config.Config;
import com.omquark.fluidizationcraft.util.Constants;

import net.minecraft.block.Block;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialLiquid;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.block.statemap.StateMapperBase;
import net.minecraft.enchantment.Enchantment.Rarity;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.common.ForgeModContainer;
import net.minecraftforge.fluids.*;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public final class ReduxFluids {
	
	public static Fluid acid;
	public static Fluid cryonite;
	public static Fluid neptunium;
	public static Fluid netherflow;
	public static Fluid plutonium;
	public static Fluid pyronite;
	public static Fluid radionite;
	public static Fluid uranium;
	
	public static AcidFluidBlock acidFluidBlock;
	public static CryoniteFluidBlock cryoniteFluidBlock;
	public static NeptuniumFluidBlock neptuniumFluidBlock;
	public static NetherflowFluidBlock netherflowFluidBlock;
	public static PlutoniumFluidBlock plutoniumFluidBlock;
	public static PyroniteFluidBlock pyroniteFluidBlock;
	public static RadioniteFluidBlock radioniteFluidBlock;
	public static UraniumFluidBlock uraniumFluidBlock;
	
	/**
	 * The fluids registered by this mod. Includes fluids that were already registered by another mod.
	 */
	public static final List<Fluid> reduxFluidList = new ArrayList<Fluid>();

	/**
	 * The fluid blocks from this mod only. Doesn't include blocks for fluids that were already registered by another mod.
	 */
	public static final Set<IFluidBlock> reduxFluidBlockList = new HashSet<>();
	/**
	 * The bucket list for the fluids included only in this mod
	 */
	public static final Set<Item> reduxFluidBucketList = new HashSet<>();
	
	public static void registerFluids(){
		
		acid = createFluid("acid", acid).setDensity(1000).setViscosity(750).setTemperature(300);
		
		cryonite = createFluid("cryonite", cryonite).setDensity(4000).setViscosity(6000).setTemperature(20);

		neptunium = createFluid("neptunium", neptunium);
		
		netherflow = createFluid("netherflow", netherflow).setDensity(3000).setViscosity(4000).setTemperature(2000);

		plutonium = createFluid("plutonium", plutonium);

		pyronite = createFluid("pyronite", pyronite);

		radionite = createFluid("radionite", radionite);

		uranium = createFluid("uranium", uranium);
		
		if(Config.EasyMode) {
			for(Fluid fluid: reduxFluidList) {
				fluid.setLuminosity(15);
			}
		}

		acidFluidBlock = new AcidFluidBlock(acid, Material.field_151586_h);
		registerFluidBlock("acid", acidFluidBlock);
		cryoniteFluidBlock = new CryoniteFluidBlock(cryonite, Material.field_151586_h);
		registerFluidBlock("cryonite", cryoniteFluidBlock);
		neptuniumFluidBlock = new NeptuniumFluidBlock(neptunium, Material.field_151586_h);
		registerFluidBlock("neptunium", neptuniumFluidBlock);
		netherflowFluidBlock = new NetherflowFluidBlock(netherflow, Material.field_151586_h);
		registerFluidBlock("netherflow", netherflowFluidBlock);
		plutoniumFluidBlock = new PlutoniumFluidBlock(plutonium, Material.field_151586_h);
		registerFluidBlock("plutonium", plutoniumFluidBlock);
		pyroniteFluidBlock = new PyroniteFluidBlock(pyronite, Material.field_151586_h);
		registerFluidBlock("pyronite", pyroniteFluidBlock);
		radioniteFluidBlock = new RadioniteFluidBlock(radionite, Material.field_151586_h);
		registerFluidBlock("radionite", radioniteFluidBlock);
		uraniumFluidBlock = new UraniumFluidBlock(uranium, Material.field_151586_h);
		registerFluidBlock("uranium", uraniumFluidBlock);
	}
	
	public static Fluid createFluid(String name, Fluid fluid){
		
		if(reduxFluidList.contains(fluid)){return null;};
		ResourceLocation fluidStill = new ResourceLocation(PlasmaCraftRedux.MODID + ":blocks/fluid_" + name + "_still");
		ResourceLocation fluidFlow = new ResourceLocation(PlasmaCraftRedux.MODID + ":blocks/fluid_" + name + "_flow");

		fluid = new Fluid(name, fluidStill, fluidFlow);

		FluidRegistry.registerFluid(fluid);
		FluidRegistry.addBucketForFluid(fluid);

		reduxFluidList.add(fluid);
		return fluid;
	}

	public static boolean registerFluidBlock(String name, ReduxBaseFluid fluidBlock) {

		if(reduxFluidBlockList.contains(fluidBlock)) return false;
		
		fluidBlock.setRegistryName(PlasmaCraftRedux.MODID, name);
		fluidBlock.func_149663_c(name);
		
		Constants.BLOCK_REGISTRY.register(fluidBlock);
		
		reduxFluidBlockList.add(fluidBlock);
		ItemBlock fluidItemBlock = new ItemBlock(fluidBlock);
		fluidItemBlock.setRegistryName(fluidBlock.getRegistryName().toString());
		Constants.ITEM_REGISTRY.register(fluidItemBlock);
		fluidBlock.func_149647_a(PlasmaCraftRedux.reduxTab);
		
		return true;
	}
}
