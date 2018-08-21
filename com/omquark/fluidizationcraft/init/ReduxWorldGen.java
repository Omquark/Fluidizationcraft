package com.omquark.fluidizationcraft.init;

import com.omquark.fluidizationcraft.world.gen.PlasmaCraftLakesGen;
import com.omquark.fluidizationcraft.world.gen.PlasmaCraftOreGen;

import net.minecraftforge.fml.common.registry.GameRegistry;

public class ReduxWorldGen {
	
	public static final PlasmaCraftOreGen oreGen = new PlasmaCraftOreGen();
	public static final PlasmaCraftLakesGen lakesGen = new PlasmaCraftLakesGen();

	public ReduxWorldGen() {
		init();
	}
	
	public static void init() {
		GameRegistry.registerWorldGenerator(oreGen, 0);
		GameRegistry.registerWorldGenerator(lakesGen, 0);
	}

	
}
