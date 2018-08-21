package com.omquark.fluidizationcraft.config;

import com.omquark.fluidizationcraft.*;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.config.Property;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

//TODO: Ramp up the ore gen, they don't spawn often enough

public class Config {

	public static Configuration config;
	
	public static boolean CryoniteColdOnly;
	public static boolean EasyMode;
	public static boolean SafeMode;
	public static boolean TerrainDamage;
	
	public static int AluminumOreChance;
	public static int AluminumOreMaxY;
	public static int AluminumOreMinY;
	public static int AluminumOreSize;

	public static int CopperOreChance;
	public static int CopperOreMaxY;
	public static int CopperOreMinY;
	public static int CopperOreSize;
	
	public static int LeadOreChance;
	public static int LeadOreMaxY;
	public static int LeadOreMinY;
	public static int LeadOreSize;
	
	public static int TinOreChance;
	public static int TinOreMaxY;
	public static int TinOreMinY;
	public static int TinOreSize;
	
	public static int AcidLakeChance;
	public static int AcidLakeCount;
	public static int AcidLakeMaxY;
	public static int AcidLakeMinY;

	public static int AcidSpoutChance;
	public static int AcidSpoutCount;
	public static int AcidSpoutMaxY;
	public static int AcidSpoutMinY;

	public static int CryoniteLakeChance;
	public static int CryoniteLakeCount;
	public static int CryoniteLakeMaxY;
	public static int CryoniteLakeMinY;
	
	public static int NetherflowLakeChance;
	public static int NetherflowLakeCount;
	public static int NetherflowLakeMaxY;
	public static int NetherflowLakeMinY;
	
	public static int DissolvinatorMaxFuel;
	public static int DissolvinatorFuelUsed;
	public static int DissolvinatorTimePerItem;
	
	public static boolean AcidGrenadesDestroyBlocks;
	public static boolean AcidGrenadesGenerateAcid;
	public static float AcidGrenadeRadius;
	
	public static boolean CryoBlasterGenCryonite;
	public static float CryoBlastRadius;
	public static int CryoBlastLifeSpan;
	
	public static boolean Debug = false;
	
	public static void load(FMLPreInitializationEvent event) {
		config = new Configuration(event.getSuggestedConfigurationFile());
		
		setFluids();
		setOres();
		setBlasters();
		setMachines();
		setOther();
		
		config.hasChanged();
		reloadConfig();

		MinecraftForge.EVENT_BUS.register(config);
	}

	private static void reloadConfig() {
		if (config.hasChanged()) {
			config.save();
		}
	}

	@SubscribeEvent
	public void onConfigChanged(ConfigChangedEvent.OnConfigChangedEvent event) {
		if (event.getModID().equals(PlasmaCraftRedux.MODID)) {
			reloadConfig();
		}
	}
	
	/**
	 * Sets various misc properties
	 */
	private static void setOther() {
		Property prop;
		
		prop = config.get(Configuration.CATEGORY_GENERAL, "EasyMode", false, "This will cause all fluids to set off the max light value. Default = false");
		EasyMode = prop.getBoolean();
		
		prop = config.get(Configuration.CATEGORY_GENERAL, "SafeMode", true, "Causes fluids to do no damage. Default = true");
		SafeMode = prop.getBoolean();
		
		prop = config.get(Configuration.CATEGORY_GENERAL, "TerrainDamage", true, "Causes fluids to no longer interact with blocks. "
				+ "\nIt will still interact with other fluids, but this causes far less destruction.");
		TerrainDamage = prop.getBoolean();
		
	}
	
	/**
	 * TNT Config, only for AcidTNT
	 */
	private static void setTNTConfig() {
		Property prop;
		prop = config.get(Configuration.CATEGORY_GENERAL, "TNTDamageBlocks", true, "If true, AcidTNT will damage blocks. Default = true");
	}
	
	/**
	 * Ore Config for ore generation.
	 */
	private static void setOres() {
		Property prop;
		
		/**
		 * Aluminum ore
		 */
		prop = config.get(Configuration.CATEGORY_GENERAL, "AluminumOreChance", 25, "Default = 25");
		AluminumOreChance = prop.getInt();
		prop = config.get(Configuration.CATEGORY_GENERAL, "AluminumOreMaxY", 64, "Default = 64");
		AluminumOreMaxY = prop.getInt();
		prop = config.get(Configuration.CATEGORY_GENERAL, "AluminumOreMinY", 16, "Default = 16");
		AluminumOreMinY = prop.getInt();
		prop = config.get(Configuration.CATEGORY_GENERAL, "AluminumOreSize", 6, "Default = 6");
		AluminumOreSize = prop.getInt();

		/**
		 * Copper ore
		 */
		prop = config.get(Configuration.CATEGORY_GENERAL, "CopperOreChance", 25, "Default = 25");
		CopperOreChance = prop.getInt();
		prop = config.get(Configuration.CATEGORY_GENERAL, "CopperOreMaxY", 64, "Default = 64");
		CopperOreMaxY = prop.getInt();
		prop = config.get(Configuration.CATEGORY_GENERAL, "CopperOreMinY", 16, "Default = 16");
		CopperOreMinY = prop.getInt();
		prop = config.get(Configuration.CATEGORY_GENERAL, "CopperOreSize", 6, "Default = 6");
		CopperOreSize = prop.getInt();

		/**
		 * Lead ore
		 */
		prop = config.get(Configuration.CATEGORY_GENERAL, "LeadOreChance", 25, "Default = 25");
		LeadOreChance = prop.getInt();
		prop = config.get(Configuration.CATEGORY_GENERAL, "LeadOreMaxY", 64, "Default = 64");
		LeadOreMaxY = prop.getInt();
		prop = config.get(Configuration.CATEGORY_GENERAL, "LeadOreMinY", 16, "Default = 16");
		LeadOreMinY = prop.getInt();
		prop = config.get(Configuration.CATEGORY_GENERAL, "LeadOreSize", 6, "Default = 6");
		LeadOreSize = prop.getInt();
	
		/**
		 * Tin ore
		 */
		prop = config.get(Configuration.CATEGORY_GENERAL, "TinOreChance", 25, "Default = 25");
		TinOreChance = prop.getInt();
		prop = config.get(Configuration.CATEGORY_GENERAL, "TinOreMaxY", 64, "Default = 64");
		TinOreMaxY = prop.getInt();
		prop = config.get(Configuration.CATEGORY_GENERAL, "TinOreMinY", 16, "Default = 16");
		TinOreMinY = prop.getInt();
		prop = config.get(Configuration.CATEGORY_GENERAL, "TinOreSize", 6, "Default = 6");
		TinOreSize = prop.getInt();
	}
	
	/**
	 * Config for guns. How destructive guns and explosives can be. 
	 */
	private static void setBlasters() {
		
		Property prop;
		
		prop = config.get(Configuration.CATEGORY_GENERAL, "AcidGrenadesDestroyBlocks", true, "Acid grenades destroy blocks. (Default = true)");
		AcidGrenadesDestroyBlocks = prop.getBoolean();

		prop = config.get(Configuration.CATEGORY_GENERAL, "AcidGrenadesGenerateAcid", true, "Acid grenades generate acid. (Default = true)");
		AcidGrenadesGenerateAcid = prop.getBoolean();

		prop = config.get(Configuration.CATEGORY_GENERAL, "AcidGrenadeRadius", 2.0, "The size of the acid grenade explosion. (Default = 2.0)");
		AcidGrenadeRadius = (float) prop.getDouble();

		prop = config.get(Configuration.CATEGORY_GENERAL, "CryoBlasterGenCryonite", true, "Cryonite blaster will generate cryoblast. (Default = true)");
		CryoBlasterGenCryonite = prop.getBoolean();

		prop = config.get(Configuration.CATEGORY_GENERAL, "CryoBlastRadius", 3.0, "The size of the Cryonite blaster explosion. " +
															"This explosion will generate snow and ice, and cryonite if applicable. (Default = 3.0)");
		CryoBlastRadius = (float) prop.getDouble();
		
		prop = config.get(Configuration.CATEGORY_GENERAL, "CryoBlastLifeSpan", 50, "The lifespan of a Cryonite blaster bullet with a max of 50. (Default = 50)");
		CryoBlastLifeSpan = prop.getInt();
		
	}
	
	/**
	 * Config for fluids lake and spout generations.
	 */
	private static void setFluids() {
		Property prop;
		
		
		/**
		 * Acid lake chances
		 */
		prop = config.get(Configuration.CATEGORY_GENERAL,
				"AcidLakeChance", 10, "Chance of an acid lake spawning in each pass out of 100. (Default = 8)");
		AcidLakeChance = prop.getInt();
		
		prop = config.get(Configuration.CATEGORY_GENERAL,
				"AcidLakeMaxY", 150, "Max Y an acid lake will spawn, will not spawn above the surface. (Default = 150)");
		AcidLakeMaxY = prop.getInt(AcidLakeMaxY);
		
		prop = config.get(Configuration.CATEGORY_GENERAL,
				"AcidLakeMinY", 24, "Min Y an acid lake will spawn, Min = 10. (Default = 20)");
		AcidLakeMinY = prop.getInt(AcidLakeMinY);
		if(AcidLakeMinY < 10) AcidLakeMinY = 10;
		
		prop = config.get(Configuration.CATEGORY_GENERAL,
				"AcidLakeCount", 2, "Attempts per chunk to try to create an acid lake, 0 will turn off lakes. (Default = 2)");
		AcidLakeCount = prop.getInt(AcidLakeCount);
		
		/**
		 * Acid spout chances
		 */
		prop = config.get(Configuration.CATEGORY_GENERAL,
				"AcidSpoutChance", 50, "Chance of an acid spout spawning in each pass out of 100. (Default = 50)");
		AcidSpoutChance = prop.getInt();
		
		prop = config.get(Configuration.CATEGORY_GENERAL,
				"AcidSpoutMaxY", 80, "Max Y an acid spout will spawn, will not spawn above the surface. (Default = 80)");
		AcidSpoutMaxY = prop.getInt(AcidSpoutMaxY);
		
		prop = config.get(Configuration.CATEGORY_GENERAL,
				"AcidSpoutMinY", 20, "Min Y an acid spout will spawn, Min = 10. (Default = 20)");
		AcidSpoutMinY = prop.getInt(AcidSpoutMinY);
		if(AcidSpoutMinY < 10) AcidSpoutMinY = 10;
		
		prop = config.get(Configuration.CATEGORY_GENERAL,
				"AcidSpoutCount", 20, "Attempts per chunk to try to create an acid spout, 0 will turn off spouts. (Default = 20)");
		AcidSpoutCount = prop.getInt(AcidSpoutCount);
		
		/**
		 * Cryonite lake chances
		 */
		prop = config.get(Configuration.CATEGORY_GENERAL,
				"CryoniteLakeChance", 10, "Chance of a cryonite lake spawning in each pass out of 100. (Default = 10)");
		CryoniteLakeChance = prop.getInt();
		
		prop = config.get(Configuration.CATEGORY_GENERAL,
				"CryoniteLakeMaxY", 150, "Max Y a cryonite lake will spawn, will not spawn above the surface. (Default = 80)");
		CryoniteLakeMaxY = prop.getInt(CryoniteLakeMaxY);
		
		prop = config.get(Configuration.CATEGORY_GENERAL,
				"CryoniteLakeMinY", 20, "Min Y a cryonite lake will spawn, Min = 10. (Default = 20)");
		CryoniteLakeMinY = prop.getInt(CryoniteLakeMinY);
		if(CryoniteLakeMinY < 10) CryoniteLakeMinY = 10;
		
		prop = config.get(Configuration.CATEGORY_GENERAL,
				"CryoniteLakeCount", 2, "Attempts per chunk to try to create a cryonite lake, 0 will turn off lakes. (Default = 2)");
		CryoniteLakeCount = prop.getInt(CryoniteLakeCount);

		prop = config.get(Configuration.CATEGORY_GENERAL,
				"CryoniteColdOnly", true, "If Cryonite will only spawn in cold biomes. (Default = true)");
		CryoniteColdOnly = prop.getBoolean(CryoniteColdOnly);

		/**
		 * Netherflow lake chance
		 */
		prop = config.get(Configuration.CATEGORY_GENERAL,
				"NetherflowLakeChance", 10, "Chance of an netherflow lake spawning in each pass out of 100. (Default = 25)");
		NetherflowLakeChance = prop.getInt();
		
		prop = config.get(Configuration.CATEGORY_GENERAL,
				"NetherflowLakeMaxY", 120, "Max Y an netherflow lake will spawn, will not spawn above the surface. (Default = 120)");
		NetherflowLakeMaxY = prop.getInt(NetherflowLakeMaxY);
		
		prop = config.get(Configuration.CATEGORY_GENERAL,
				"NetherflowLakeMinY", 25, "Min Y a netherflow lake will spawn, Min = 10. (Default = 25)");
		NetherflowLakeMinY = prop.getInt(NetherflowLakeMinY);
		if(NetherflowLakeMinY < 10) NetherflowLakeMinY = 10;
		
		prop = config.get(Configuration.CATEGORY_GENERAL,
				"NetherflowLakeCount", 5, "Attempts per chunk to try to create an netherflow lake, 0 will turn off lakes. (Default = 5)");
		NetherflowLakeCount = prop.getInt(NetherflowLakeCount);
	}
	
	public static void setMachines() {
		
		Property prop;
		
		prop = config.get(Configuration.CATEGORY_GENERAL,
				"DissolvinatorMaxFuel", 10000, "How much fuel the Dissolvinator can hold, in mB. (Default = 10000)");
		DissolvinatorMaxFuel = prop.getInt(DissolvinatorMaxFuel);

		prop = config.get(Configuration.CATEGORY_GENERAL,
				"DissolvinatorFuelused", 125, "How much fuel is used int the Dissolvinator per operation, in mB. (Default = 125)");
		DissolvinatorFuelUsed = prop.getInt(DissolvinatorFuelUsed);

		prop = config.get(Configuration.CATEGORY_GENERAL,
				"DissolvinatorTimePerItem", 200, "How many ticks elapse for each operation, in mB. \nThe defult is the same speed as a Minecraft furnace.(Default = 200)");
		DissolvinatorTimePerItem = prop.getInt(DissolvinatorTimePerItem);
	}
}
