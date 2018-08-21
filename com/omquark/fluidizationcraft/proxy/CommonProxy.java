package com.omquark.fluidizationcraft.proxy;

import com.omquark.fluidizationcraft.PlasmaCraftRedux;
import com.omquark.fluidizationcraft.guiHandler.FluidizationGuiHandler;

import net.minecraftforge.fml.common.network.NetworkRegistry;

public class CommonProxy {
	
	public static CommonProxy INSTANCE = new CommonProxy();
	
	public static enum GUI_ENUM{
		GUI_DISSOLVINATOR
	};

	private CommonProxy() {
		
	}
	
	public static void RegisterGuis() {
		NetworkRegistry.INSTANCE.registerGuiHandler(PlasmaCraftRedux.instance, new FluidizationGuiHandler());  
	}

}
