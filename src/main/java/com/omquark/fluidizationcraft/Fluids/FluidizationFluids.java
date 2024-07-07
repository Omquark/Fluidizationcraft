package com.omquark.fluidizationcraft.Fluids;

import com.omquark.fluidizationcraft.Blocks.FluidizationBlocks;
import com.omquark.fluidizationcraft.FluidizationCraft;
import com.omquark.fluidizationcraft.Items.FluidizationItems;
import net.minecraft.world.level.material.FlowingFluid;
import net.minecraft.world.level.material.Fluid;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class FluidizationFluids {
    public static final DeferredRegister<Fluid> FLUIDS =
            DeferredRegister.create(ForgeRegistries.FLUIDS, FluidizationCraft.MODID);

    public static final RegistryObject<FlowingFluid> SOURCE_ACID = FLUIDS.register("acid_fluid",
            () -> new ModFluid.Source(FluidizationFluids.ACID_FLUID_PROPERTIES));
    public static final RegistryObject<FlowingFluid> FLOWING_ACID = FLUIDS.register("acid_flowing",
            () -> new ModFluid.Flowing(FluidizationFluids.ACID_FLUID_PROPERTIES));
    public static final RegistryObject<FlowingFluid> SOURCE_CRYONITE = FLUIDS.register("cryonite_fluid",
            () -> new ModFluid.Source(FluidizationFluids.CRYONITE_FLUID_PROPERTIES));
    public static final RegistryObject<FlowingFluid> FLOWING_CRYONITE = FLUIDS.register("cryonite_flowing",
            () -> new ModFluid.Flowing(FluidizationFluids.CRYONITE_FLUID_PROPERTIES));
    public static final RegistryObject<FlowingFluid> SOURCE_NEPTUNIUM = FLUIDS.register("neptunium_fluid",
            () -> new ModFluid.Source(FluidizationFluids.NEPTUNIUM_FLUID_PROPERTIES));
    public static final RegistryObject<FlowingFluid> FLOWING_NEPTUNIUM = FLUIDS.register("neptunium_flowing",
            () -> new ModFluid.Flowing(FluidizationFluids.NEPTUNIUM_FLUID_PROPERTIES));
    public static final RegistryObject<FlowingFluid> SOURCE_NETHERFLOW = FLUIDS.register("netherflow_fluid",
            () -> new ModFluid.Source(FluidizationFluids.NETHERFLOW_FLUID_PROPERTIES));
    public static final RegistryObject<FlowingFluid> FLOWING_NETHERFLOW = FLUIDS.register("netherflow_flowing",
            () -> new ModFluid.Flowing(FluidizationFluids.NETHERFLOW_FLUID_PROPERTIES));
    public static final RegistryObject<FlowingFluid> SOURCE_PLUTONIUM = FLUIDS.register("plutonium_fluid",
            () -> new ModFluid.Source(FluidizationFluids.PLUTONIUM_FLUID_PROPERTIES));
    public static final RegistryObject<FlowingFluid> FLOWING_PLUTONIUM = FLUIDS.register("plutonium_flowing",
            () -> new ModFluid.Flowing(FluidizationFluids.PLUTONIUM_FLUID_PROPERTIES));
    public static final RegistryObject<FlowingFluid> SOURCE_PYRONITE = FLUIDS.register("pyronite_fluid",
            () -> new ModFluid.Source(FluidizationFluids.PYRONITE_FLUID_PROPERTIES));
    public static final RegistryObject<FlowingFluid> FLOWING_PYRONITE = FLUIDS.register("pyronite_flowing",
            () -> new ModFluid.Flowing(FluidizationFluids.PYRONITE_FLUID_PROPERTIES));
    public static final RegistryObject<FlowingFluid> SOURCE_RADIONITE = FLUIDS.register("radionite_fluid",
            () -> new ModFluid.Source(FluidizationFluids.RADIONITE_FLUID_PROPERTIES));
    public static final RegistryObject<FlowingFluid> FLOWING_RADIONITE = FLUIDS.register("radionite_flowing",
            () -> new ModFluid.Flowing(FluidizationFluids.RADIONITE_FLUID_PROPERTIES));
    public static final RegistryObject<FlowingFluid> SOURCE_URANIUM = FLUIDS.register("uranium_fluid",
            () -> new ModFluid.Source(FluidizationFluids.URANIUM_FLUID_PROPERTIES));
    public static final RegistryObject<FlowingFluid> FLOWING_URANIUM = FLUIDS.register("uranium_flowing",
            () -> new ModFluid.Flowing(FluidizationFluids.URANIUM_FLUID_PROPERTIES));
    public static final ModFluid.ModProperties ACID_FLUID_PROPERTIES = (ModFluid.ModProperties) new ModFluid.ModProperties(
            FluidizationFluidTypes.ACID_FLUID_TYPE, SOURCE_ACID, FLOWING_ACID)
            .vial(FluidizationItems.VIAL_ACID)
            .slopeFindDistance(2).levelDecreasePerBlock(1).block(FluidizationBlocks.ACID_BLOCK)
            .bucket(FluidizationItems.BUCKET_ACID).tickRate(2);
    public static final ModFluid.ModProperties CRYONITE_FLUID_PROPERTIES = (ModFluid.ModProperties) new ModFluid.ModProperties(
            FluidizationFluidTypes.CRYONITE_FLUID_TYPE, SOURCE_CRYONITE, FLOWING_CRYONITE)
            .vial(FluidizationItems.VIAL_CRYONITE)
            .slopeFindDistance(2).levelDecreasePerBlock(1).block(FluidizationBlocks.CRYONITE_BLOCK)
            .bucket(FluidizationItems.BUCKET_CRYONITE).tickRate(16);
    public static final ModFluid.ModProperties NETHERFLOW_FLUID_PROPERTIES = (ModFluid.ModProperties) new ModFluid.ModProperties(
            FluidizationFluidTypes.NETHERFLOW_FLUID_TYPE, SOURCE_NETHERFLOW, FLOWING_NETHERFLOW)
            .vial(FluidizationItems.VIAL_NETHERFLOW)
            .slopeFindDistance(2).levelDecreasePerBlock(1).block(FluidizationBlocks.NETHERFLOW_FLUID_BLOCK)
            .bucket(FluidizationItems.BUCKET_NETHERFLOW).tickRate(12);
    public static final ModFluid.ModProperties NEPTUNIUM_FLUID_PROPERTIES = (ModFluid.ModProperties) new ModFluid.ModProperties(
            FluidizationFluidTypes.NEPTUNIUM_FLUID_TYPE, SOURCE_NEPTUNIUM, FLOWING_NEPTUNIUM)
            .vial(FluidizationItems.VIAL_NEPTUNIUM)
            .slopeFindDistance(2).levelDecreasePerBlock(1).block(FluidizationBlocks.NEPTUNIUM_FLUID_BLOCK)
            .bucket(FluidizationItems.BUCKET_NEPTUNIUM);
    public static final ModFluid.ModProperties PLUTONIUM_FLUID_PROPERTIES = (ModFluid.ModProperties) new ModFluid.ModProperties(
            FluidizationFluidTypes.PLUTONIUM_FLUID_TYPE, SOURCE_PLUTONIUM, FLOWING_PLUTONIUM)
            .vial(FluidizationItems.VIAL_PLUTONIUM)
            .slopeFindDistance(2).levelDecreasePerBlock(1).block(FluidizationBlocks.PLUTONIUM_FLUID_BLOCK)
            .bucket(FluidizationItems.BUCKET_PLUTONIUM);
    public static final ModFluid.ModProperties PYRONITE_FLUID_PROPERTIES = (ModFluid.ModProperties) new ModFluid.ModProperties(
            FluidizationFluidTypes.PYRONITE_FLUID_TYPE, SOURCE_PYRONITE, FLOWING_PYRONITE)
            .vial(FluidizationItems.VIAL_PYRONITE)
            .slopeFindDistance(2).levelDecreasePerBlock(1).block(FluidizationBlocks.PYRONITE_FLUID_BLOCK)
            .bucket(FluidizationItems.BUCKET_PYRONITE);
    public static final ModFluid.ModProperties RADIONITE_FLUID_PROPERTIES = (ModFluid.ModProperties) new ModFluid.ModProperties(
            FluidizationFluidTypes.RADIONITE_FLUID_TYPE, SOURCE_RADIONITE, FLOWING_RADIONITE)
            .vial(FluidizationItems.VIAL_RADIONITE)
            .slopeFindDistance(2).levelDecreasePerBlock(1).block(FluidizationBlocks.RADIONITE_FLUID_BLOCK)
            .bucket(FluidizationItems.BUCKET_RADIONITE);
    public static final ModFluid.ModProperties URANIUM_FLUID_PROPERTIES = (ModFluid.ModProperties) new ModFluid.ModProperties(
            FluidizationFluidTypes.URANIUM_FLUID_TYPE, SOURCE_URANIUM, FLOWING_URANIUM)
            .vial(FluidizationItems.VIAL_URANIUM)
            .slopeFindDistance(2).levelDecreasePerBlock(1).block(FluidizationBlocks.URANIUM_FLUID_BLOCK)
            .bucket(FluidizationItems.BUCKET_URANIUM);

    public static void register(IEventBus eventBus) {
        FLUIDS.register(eventBus);
    }
}
