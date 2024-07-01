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
    public static final ResourceLocation ACID_OVERLAY_RL = new ResourceLocation(FluidizationCraft.MODID, "block/acid");
    public static final ResourceLocation CRYONITE_OVERLAY_RL = new ResourceLocation(FluidizationCraft.MODID, "block/cryonite");

    public static final DeferredRegister<FluidType> FLUID_TYPES =
            DeferredRegister.create(ForgeRegistries.FLUID_TYPES, FluidizationCraft.MODID);
    public static final RegistryObject<FluidType> ACID_FLUID_TYPE = register("acid_fluid", ACID_OVERLAY_RL, 0xA17BF957,
            new Vector3f(123f / 255f, 249f / 255f, 87f / 255f),
            FluidType.Properties.create().density(1000).viscosity(1000).sound(SoundAction.get("drink"), SoundEvents.HONEY_DRINK));

    public static final RegistryObject<FluidType> CRYONITE_FLUID_TYPE = register("cryonite_fluid", CRYONITE_OVERLAY_RL, 0xA157E8FF,
            new Vector3f(87f / 255f, 232f / 255f, 255f / 255f),
            FluidType.Properties.create().density(3000).viscosity(6000).sound(SoundAction.get("drink"), SoundEvents.HONEY_DRINK));

    public static RegistryObject<FluidType> register(String name, ResourceLocation resourceOverlay, int tintColor,
                                                     Vector3f fogColor, FluidType.Properties properties) {
        return FLUID_TYPES.register(name, () -> new ModFluidType(WATER_STILL_RL, WATER_FLOWING_RL, resourceOverlay,
                tintColor, fogColor, properties));
    }

    public static void register(IEventBus eventBus) {
        FLUID_TYPES.register(eventBus);
    }
}
