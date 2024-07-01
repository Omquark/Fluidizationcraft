package com.omquark.fluidizationcraft.DamageTypes;

import com.omquark.fluidizationcraft.FluidizationCraft;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.damagesource.DamageType;

public class FluidizationDamageTypes {
    private FluidizationDamageTypes() {}

    public static final ResourceKey<DamageType> ACID_DAMAGE = register("acid");
    public static final ResourceKey<DamageType> CRYONITE_DAMAGE = register("cryonite");
    public static final ResourceKey<DamageType> NEPTUNIUM_DAMAGE = register("neptunium");
    public static final ResourceKey<DamageType> NETHERFLOW_DAMAGE = register("netherflow");

    private static ResourceKey<DamageType> register(String name){
        return ResourceKey.create(Registries.DAMAGE_TYPE, new ResourceLocation(FluidizationCraft.MODID, name));
    }

    public static void bootstrap(BootstrapContext<DamageType> context){
        context.register(ACID_DAMAGE, new DamageType("acid", 0.1f));
        context.register(CRYONITE_DAMAGE, new DamageType("cryonite", 0.1f));
        context.register(NEPTUNIUM_DAMAGE, new DamageType("neptunium", 0.1f));
        context.register(NETHERFLOW_DAMAGE, new DamageType("netherflow", 0.1f));
    }
}
