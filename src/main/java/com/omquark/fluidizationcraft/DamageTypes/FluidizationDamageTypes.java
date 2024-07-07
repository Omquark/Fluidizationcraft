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
    public static final ResourceKey<DamageType> PLUTONIUM_DAMAGE = register("plutonium");
    public static final ResourceKey<DamageType> PYRONITE_DAMAGE = register("pyronite");
    public static final ResourceKey<DamageType> RADIONITE_DAMAGE = register("radionite");
    public static final ResourceKey<DamageType> URANIUM_DAMAGE = register("uranium");

    private static ResourceKey<DamageType> register(String name){
        return ResourceKey.create(Registries.DAMAGE_TYPE, new ResourceLocation(FluidizationCraft.MODID, name));
    }

    public static void bootstrap(BootstrapContext<DamageType> context){
        context.register(ACID_DAMAGE, new DamageType("acid", 0.1f));
        context.register(CRYONITE_DAMAGE, new DamageType("cryonite", 0.1f));
        context.register(NEPTUNIUM_DAMAGE, new DamageType("neptunium", 0.1f));
        context.register(NETHERFLOW_DAMAGE, new DamageType("netherflow", 0.1f));
        context.register(PLUTONIUM_DAMAGE, new DamageType("pltonium", 0.1f));
        context.register(PYRONITE_DAMAGE, new DamageType("pyronite", 0.1f));
        context.register(RADIONITE_DAMAGE, new DamageType("radionite", 0.1f));
        context.register(URANIUM_DAMAGE, new DamageType("uranium", 0.1f));
    }
}
