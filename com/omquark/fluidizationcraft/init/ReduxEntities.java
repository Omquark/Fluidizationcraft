package com.omquark.fluidizationcraft.init;

import com.omquark.fluidizationcraft.PlasmaCraftRedux;
import com.omquark.fluidizationcraft.Entities.EntityAcidGrenade;
import com.omquark.fluidizationcraft.Entities.EntityAcidShot;
import com.omquark.fluidizationcraft.Entities.EntityCryoBlast;
import com.omquark.fluidizationcraft.util.Constants;

import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.EntityRegistry;

public class ReduxEntities {
	
	private static int ID = 0;
	
	public static void registerEntities(){

		registerEntity("acidnade", EntityAcidGrenade.class, 64, 20, true);
		registerEntity("cryoblast", EntityCryoBlast.class, 64, 20, true);
		registerEntity("acidshot", EntityAcidShot.class, 64, 20, true);
	}
	
	private static void registerEntity(String name, Class<? extends Entity> entityClass, int trackingRange, int updateFrequency, boolean sendVelocityUpdate){

		EntityRegistry.registerModEntity(new ResourceLocation(Constants.RESOURCE_PREFIX + name), entityClass, name, ID++, PlasmaCraftRedux.instance, trackingRange, updateFrequency, sendVelocityUpdate);
		
	}
}
