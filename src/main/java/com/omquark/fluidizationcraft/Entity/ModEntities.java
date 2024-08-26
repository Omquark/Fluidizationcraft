package com.omquark.fluidizationcraft.entity;

import com.omquark.fluidizationcraft.FluidizationCraft;
import net.minecraft.world.entity.EntityType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModEntities {
    private ModEntities(){}

    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES =
            DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, FluidizationCraft.MODID);

    public static final RegistryObject<EntityType<AcidShotProjectile>> ACID_PROJECTILE =
            ENTITY_TYPES.register("acid_shot", () -> EntityType.Builder.<AcidShotProjectile>of(AcidShotProjectile::new,
                    net.minecraft.world.entity.MobCategory.MISC).build("acid_shot"));

    public static final RegistryObject<EntityType<CryoShotProjectile>> CRYO_PROJECTILE =
            ENTITY_TYPES.register("cryo_shot", () -> EntityType.Builder.<CryoShotProjectile>of(CryoShotProjectile::new,
                    net.minecraft.world.entity.MobCategory.MISC).build("cryo_shot"));


    public static void register(IEventBus eventBus){
        ENTITY_TYPES.register(eventBus);
    }
}
