package com.omquark.fluidizationcraft.Fluids;

import com.omquark.fluidizationcraft.FluidizationCraft;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraftforge.common.SoundAction;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fluids.FluidType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.joml.Vector3f;

public class FluidizationFluidTypes {
    public static final ResourceLocation WATER_STILL_RL = new ResourceLocation("block/water_still");
    public static final ResourceLocation WATER_FLOWING_RL = new ResourceLocation("block/water_flow");
    public static final ResourceLocation LAVA_STILL_RL = new ResourceLocation("block/lava_still");
    public static final ResourceLocation LAVA_FLOWING_RL = new ResourceLocation("block/lava_flow");
    public static final ResourceLocation ACID_OVERLAY_RL = new ResourceLocation(FluidizationCraft.MODID, "block/acid");
    public static final ResourceLocation CRYONITE_OVERLAY_RL = new ResourceLocation(FluidizationCraft.MODID, "block/cryonite");
    public static final ResourceLocation NEPTUNIUM_OVERLAY_RL = new ResourceLocation(FluidizationCraft.MODID, "block/cryonite");
    public static final ResourceLocation NETHERFLOW_OVERLAY_RL = new ResourceLocation(FluidizationCraft.MODID, "block/cryonite");
    public static final ResourceLocation PLUTONIUM_OVERLAY_RL = new ResourceLocation(FluidizationCraft.MODID, "block/cryonite");
    public static final ResourceLocation PYRONITE_OVERLAY_RL = new ResourceLocation(FluidizationCraft.MODID, "block/cryonite");
    public static final ResourceLocation RADIONITE_OVERLAY_RL = new ResourceLocation(FluidizationCraft.MODID, "block/cryonite");
    public static final ResourceLocation URANIUM_OVERLAY_RL = new ResourceLocation(FluidizationCraft.MODID, "block/cryonite");

    public static final DeferredRegister<FluidType> FLUID_TYPES =
            DeferredRegister.create(ForgeRegistries.FLUID_TYPES, FluidizationCraft.MODID);
    public static final RegistryObject<FluidType> ACID_FLUID_TYPE = registerWithWaterRL("acid_fluid", ACID_OVERLAY_RL, 0xA17BF957,
            new Vector3f(123f / 255f, 249f / 255f, 87f / 255f),
            FluidType.Properties.create().density(1000).viscosity(1000).sound(SoundAction.get("drink"), SoundEvents.HONEY_DRINK));
    public static final RegistryObject<FluidType> CRYONITE_FLUID_TYPE = registerWithWaterRL("cryonite_fluid", CRYONITE_OVERLAY_RL, 0xA157E8FF,
            new Vector3f(87f / 255f, 232f / 255f, 255f / 255f),
            FluidType.Properties.create().density(3000).viscosity(6000).sound(SoundAction.get("drink"), SoundEvents.HONEY_DRINK));
    public static final RegistryObject<FluidType> NEPTUNIUM_FLUID_TYPE = registerWithWaterRL("neptunium_fluid", NEPTUNIUM_OVERLAY_RL, 0xA1F9C65E,
            new Vector3f(249f / 255f, 198f / 255f, 95f / 255f),
            FluidType.Properties.create().density(3000).viscosity(6000).sound(SoundAction.get("drink"), SoundEvents.HONEY_DRINK));
    public static final RegistryObject<FluidType> NETHERFLOW_FLUID_TYPE = registerWithWaterRL("netherflow_fluid", NETHERFLOW_OVERLAY_RL, 0xA1F83E26,
            new Vector3f(248f / 255f, 62f / 255f, 38f / 255f),
            FluidType.Properties.create().density(3000).viscosity(6000).sound(SoundAction.get("drink"), SoundEvents.HONEY_DRINK));
    public static final RegistryObject<FluidType> PLUTONIUM_FLUID_TYPE = registerWithWaterRL("plutonium_fluid", PLUTONIUM_OVERLAY_RL, 0xA18C835C,
            new Vector3f(140f / 255f, 131f / 255f, 92f / 255f),
            FluidType.Properties.create().density(3000).viscosity(6000).sound(SoundAction.get("drink"), SoundEvents.HONEY_DRINK));
    public static final RegistryObject<FluidType> PYRONITE_FLUID_TYPE = registerWithLavaRL("pyronite_fluid", PYRONITE_OVERLAY_RL, 0xFF741402,
            new Vector3f(116f / 255f, 23f / 255f, 2f / 255f),
            FluidType.Properties.create().density(3000).viscosity(6000).sound(SoundAction.get("drink"), SoundEvents.HONEY_DRINK));
    public static final RegistryObject<FluidType> RADIONITE_FLUID_TYPE = registerWithWaterRL("radionite_fluid", RADIONITE_OVERLAY_RL, 0xA13D1271,
            new Vector3f(61f / 255f, 18f / 255f, 113f / 255f),
            FluidType.Properties.create().density(3000).viscosity(6000).sound(SoundAction.get("drink"), SoundEvents.HONEY_DRINK));
    public static final RegistryObject<FluidType> URANIUM_FLUID_TYPE = registerWithWaterRL("uranium_fluid", URANIUM_OVERLAY_RL, 0xA1ADAB34,
            new Vector3f(173f / 255f, 171f / 255f, 52f / 255f),
            FluidType.Properties.create().density(3000).viscosity(6000).sound(SoundAction.get("drink"), SoundEvents.HONEY_DRINK));

    public static RegistryObject<FluidType> registerWithWaterRL(String name, ResourceLocation resourceOverlay, int tintColor,
                                                                Vector3f fogColor, FluidType.Properties properties) {
        return FLUID_TYPES.register(name, () -> new ModFluidType(WATER_STILL_RL, WATER_FLOWING_RL, resourceOverlay,
                tintColor, fogColor, properties));
    }

    public static RegistryObject<FluidType> registerWithLavaRL(String name, ResourceLocation resourceOverlay, int tintColor,
                                                                Vector3f fogColor, FluidType.Properties properties) {
        return FLUID_TYPES.register(name, () -> new ModFluidType(LAVA_STILL_RL, LAVA_FLOWING_RL, resourceOverlay,
                tintColor, fogColor, properties));
    }

    public static void registerWithWaterRL(IEventBus eventBus) {
        FLUID_TYPES.register(eventBus);
    }
}
