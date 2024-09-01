package com.omquark.fluidizationcraft.entity;

import com.omquark.fluidizationcraft.FluidizationCraft;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.minecraft.world.entity.MobCategory;

public class ModEntities {
    private ModEntities(){}

    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES =
            DeferredRegister.create(BuiltInRegistries.ENTITY_TYPE, FluidizationCraft.MODID);

    public static final DeferredHolder<EntityType<?>, EntityType<? extends AbstractArrow>> ACID_PROJECTILE =
            ENTITY_TYPES.register("acid_shot", () -> EntityType.Builder.<AbstractArrow>of(AcidShotProjectile::new,
                    MobCategory.MISC).build("acid_shot"));

    public static final DeferredHolder<EntityType<?>, EntityType<? extends AbstractArrow>> CRYO_PROJECTILE =
            ENTITY_TYPES.register("cryo_shot", () -> EntityType.Builder.<AbstractArrow>of(CryoShotProjectile::new,
                    MobCategory.MISC).build("cryo_shot"));


    public static void register(IEventBus eventBus){
        ENTITY_TYPES.register(eventBus);
    }
}
