package com.omquark.fluidizationcraft.init;

import java.util.HashSet;

import com.omquark.fluidizationcraft.PlasmaCraftRedux;
import com.omquark.fluidizationcraft.Entities.EntityAcidGrenade;
import com.omquark.fluidizationcraft.Entities.EntityCryoBlast;
import com.omquark.fluidizationcraft.Fluids.ReduxBaseFluid;
import com.omquark.fluidizationcraft.items.BaseItem;
import com.omquark.fluidizationcraft.items.ItemCellAcid;
import com.omquark.fluidizationcraft.items.ItemGunAcid;
import com.omquark.fluidizationcraft.items.ItemAcidGrenade;
import com.omquark.fluidizationcraft.items.ItemGunCryo;
import com.omquark.fluidizationcraft.items.ItemMaterial;
import com.omquark.fluidizationcraft.items.ItemSurveyTool;
import com.omquark.fluidizationcraft.items.ItemVial;
import com.omquark.fluidizationcraft.items.TestItem;
import com.omquark.fluidizationcraft.util.Constants;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.entity.EntityEvent;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidUtil;
import net.minecraftforge.fml.common.registry.EntityEntry;
import net.minecraftforge.fml.common.registry.EntityRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.oredict.OreDictionary;

public class ReduxItems {
	public static final HashSet<Item> reduxItemList = new HashSet<Item>();
	
	public static ItemStack testDebugItem = new ItemStack(new TestItem("testItem"));
	public static ItemStack itemSurveyTool = new ItemStack(new ItemSurveyTool("itemSurveyTool"));
	
	//Register the ammo
	public static ItemStack itemAcidCell = new ItemStack(new ItemCellAcid("itemCellAcid"));
	
	//Register the guns
	public static ItemStack itemAcidGrenade = new ItemStack(new ItemAcidGrenade("itemGrenadeAcid", itemAcidCell));
	public static ItemStack itemCryoBlaster = new ItemStack(new ItemGunCryo("itemGunCryo", itemAcidCell));
	public static ItemStack itemAcidShooter = new ItemStack(new ItemGunAcid("itemGunAcid", itemAcidCell));
	
	//Multi textured items
	public static final ItemVial vialEmpty = new ItemVial("itemVialEmpty");
	public static final ItemMaterial baseMaterial = new ItemMaterial("itemMaterial");
	
	//Itemstacks for reference
	public static final ItemStack itemVialEmpty = new ItemStack(vialEmpty, 1, ReduxBaseFluid.FluidDamageValues.EMPTY_DAMAGE_VALUE.ordinal());
	public static final ItemStack itemVialAcid = new ItemStack(vialEmpty, 1, ReduxBaseFluid.FluidDamageValues.ACID_DAMAGE_VALUE.ordinal());
	public static final ItemStack itemVialCryonite = new ItemStack(vialEmpty, 1, ReduxBaseFluid.FluidDamageValues.CRYONITE_DAMAGE_VALUE.ordinal());
	public static final ItemStack itemVialNeptunium = new ItemStack(vialEmpty, 1, ReduxBaseFluid.FluidDamageValues.NEPTUNIUM_DAMAGE_VALUE.ordinal());
	public static final ItemStack itemVialNetherflow = new ItemStack(vialEmpty, 1, ReduxBaseFluid.FluidDamageValues.NETHERFLOW_DAMAGE_VALUE.ordinal());
	public static final ItemStack itemVialPlutonium = new ItemStack(vialEmpty, 1, ReduxBaseFluid.FluidDamageValues.PLUTONIUM_DAMAGE_VALUE.ordinal());
	public static final ItemStack itemVialRadionite = new ItemStack(vialEmpty, 1, ReduxBaseFluid.FluidDamageValues.RADIONITE_DAMAGE_VALUE.ordinal());
	public static final ItemStack itemVialUranium = new ItemStack(vialEmpty, 1, ReduxBaseFluid.FluidDamageValues.URANIUM_DAMAGE_VALUE.ordinal());
	
	public static final ItemStack itemDustIron = new ItemStack(baseMaterial, 1, 0);
	public static final ItemStack itemDustGold = new ItemStack(baseMaterial, 1, 1);
	public static final ItemStack itemDustAluminum = new ItemStack(baseMaterial, 1, 2);
	public static final ItemStack itemDustBronze = new ItemStack(baseMaterial, 1, 3);
	public static final ItemStack itemDustCopper = new ItemStack(baseMaterial, 1, 4);
	public static final ItemStack itemDustGlass = new ItemStack(baseMaterial, 1, 5);
	public static final ItemStack itemDustLead = new ItemStack(baseMaterial, 1, 6);
	public static final ItemStack itemDustTin = new ItemStack(baseMaterial, 1, 7);

	public static final ItemStack itemIngotAluminum = new ItemStack(baseMaterial, 1, 8);
	public static final ItemStack itemIngotBronze = new ItemStack(baseMaterial, 1, 9);
	public static final ItemStack itemIngotCopper = new ItemStack(baseMaterial, 1, 10);
	public static final ItemStack itemIngotLead = new ItemStack(baseMaterial, 1, 11);
	public static final ItemStack itemIngotTin = new ItemStack(baseMaterial, 1, 12);
	
	public static ItemStack acidBucket;
	public static ItemStack cryoniteBucket;
	public static ItemStack neptuniumBucket;
	public static ItemStack netherflowBucket;
	public static ItemStack plutoniumBucket;
	public static ItemStack radioniteBucket;
	public static ItemStack uraniumBucket;

	public static void registerItems() {

		registerItem("testItem", testDebugItem.func_77973_b(), true);
		registerItem("itemSurveyTool", itemSurveyTool.func_77973_b(), true);

		registerItem("itemCellAcid", itemAcidCell.func_77973_b(), true);

		registerItem("itemGrenadeAcid", itemAcidGrenade.func_77973_b(), true);
		registerItem("itemGunCryo", itemCryoBlaster.func_77973_b(), true);
		registerItem("itemGunAcid", itemAcidShooter.func_77973_b(), true);
		
		registerItem("itemVialEmpty", vialEmpty, false);
		registerItem("itemMaterial", baseMaterial, false);

		vialEmpty.addSubsToOreDictionary();
		baseMaterial.addSubsToOreDictionary();
}
	
	@SideOnly(Side.CLIENT)
	public static void setBucketsTabs() {
		acidBucket = FluidUtil.getFilledBucket(new FluidStack(ReduxFluids.acid, 1000));
		cryoniteBucket = FluidUtil.getFilledBucket(new FluidStack(ReduxFluids.cryonite, 1000));
		neptuniumBucket = FluidUtil.getFilledBucket(new FluidStack(ReduxFluids.neptunium, 1000));
		netherflowBucket = FluidUtil.getFilledBucket(new FluidStack(ReduxFluids.netherflow, 1000));
		plutoniumBucket = FluidUtil.getFilledBucket(new FluidStack(ReduxFluids.plutonium, 1000));
		radioniteBucket = FluidUtil.getFilledBucket(new FluidStack(ReduxFluids.radionite, 1000));
		uraniumBucket = FluidUtil.getFilledBucket(new FluidStack(ReduxFluids.uranium, 1000));

//		acidBucket.getItem().setCreativeTab(PlasmaCraftRedux.reduxTab);
//		cryoniteBucket.getItem().setCreativeTab(PlasmaCraftRedux.reduxTab);
//		neptuniumBucket.getItem().setCreativeTab(PlasmaCraftRedux.reduxTab);
//		netherflowBucket.getItem().setCreativeTab(PlasmaCraftRedux.reduxTab);
//		plutoniumBucket.getItem().setCreativeTab(PlasmaCraftRedux.reduxTab);
//		radioniteBucket.getItem().setCreativeTab(PlasmaCraftRedux.reduxTab);
//		uraniumBucket.getItem().setCreativeTab(PlasmaCraftRedux.reduxTab);

//		OreDictionary.registerOre("bucketAcid", acidBucket.getItem());
//		OreDictionary.registerOre("bucketCryonite", cryoniteBucket.getItem());
//		OreDictionary.registerOre("bucketNeptunium", neptuniumBucket.getItem());
//		OreDictionary.registerOre("bucketNetherflow", netherflowBucket.getItem());
//		OreDictionary.registerOre("bucketPlutonium", plutoniumBucket.getItem());
//		OreDictionary.registerOre("bucketRadionite", radioniteBucket.getItem());
//		OreDictionary.registerOre("bucketUranium", uraniumBucket.getItem());
}
	
	private static void registerAsOre(String name, ItemStack itemStack) {
		registerItem(name, itemStack.func_77973_b(), true);
		OreDictionary.registerOre(name, itemStack);
	}
	
	private static boolean registerItem(String name, Item item, boolean addToList)
	{
		if(reduxItemList.contains(item) && addToList)
		{
			return false;
		}
		
		name = PlasmaCraftRedux.MODID + "." + name;
		item.func_77655_b(name);
		
		Constants.ITEM_REGISTRY.register(item);
		if(addToList) reduxItemList.add(item);
		return true;
	}
}
