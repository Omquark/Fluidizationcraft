package com.omquark.fluidizationcraft.Fluids;

import com.omquark.fluidizationcraft.Blocks.FluidizationBlocks;
import com.omquark.fluidizationcraft.FluidizationCraft;
import com.omquark.fluidizationcraft.Items.FluidizationItems;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.material.FlowingFluid;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.Fluids;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fluids.FluidInteractionRegistry;
import net.minecraftforge.fluids.ForgeFlowingFluid;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class FluidizationFluids {
    public static final DeferredRegister<Fluid> FLUIDS =
            DeferredRegister.create(ForgeRegistries.FLUIDS, FluidizationCraft.MODID);

    public static final RegistryObject<FlowingFluid> SOURCE_ACID = FLUIDS.register("acid_fluid",
            () -> new AcidFluid.Source(FluidizationFluids.ACID_FLUID_PROPERTIES));
    public static final RegistryObject<FlowingFluid> FLOWING_ACID = FLUIDS.register("acid_flowing",
            () -> new AcidFluid.Flowing(FluidizationFluids.ACID_FLUID_PROPERTIES));
    public static final RegistryObject<FlowingFluid> SOURCE_CRYONITE = FLUIDS.register("cryonite_fluid",
            () -> new CryoniteFluid.Source(FluidizationFluids.CRYONITE_FLUID_PROPERTIES));
    public static final RegistryObject<FlowingFluid> FLOWING_CRYONITE = FLUIDS.register("cryonite_flowing",
            () -> new CryoniteFluid.Flowing(FluidizationFluids.CRYONITE_FLUID_PROPERTIES));
    public static final ForgeFlowingFluid.Properties ACID_FLUID_PROPERTIES = new ForgeFlowingFluid.Properties(
            FluidizationFluidTypes.ACID_FLUID_TYPE, SOURCE_ACID, FLOWING_ACID)
            .slopeFindDistance(2).levelDecreasePerBlock(1).block(FluidizationBlocks.ACID_BLOCK)
            .bucket(FluidizationItems.BUCKET_ACID);
    public static final ForgeFlowingFluid.Properties CRYONITE_FLUID_PROPERTIES = new ForgeFlowingFluid.Properties(
            FluidizationFluidTypes.CRYONITE_FLUID_TYPE, SOURCE_CRYONITE, FLOWING_CRYONITE)
            .slopeFindDistance(2).levelDecreasePerBlock(1).block(FluidizationBlocks.CRYONITE_BLOCK)
            .bucket(FluidizationItems.BUCKET_CRYONITE);

    public static void register(IEventBus eventBus) {
        FLUIDS.register(eventBus);
    }

    public static void buildFluidInteractions() {
        FluidInteractionRegistry.addInteraction(Fluids.WATER.getFluidType(),
                new FluidInteractionRegistry.InteractionInformation(
                        FluidizationFluidTypes.ACID_FLUID_TYPE.get(), Blocks.CLAY.defaultBlockState()));
        FluidInteractionRegistry.addInteraction(Fluids.WATER.getFluidType(),
                new FluidInteractionRegistry.InteractionInformation(
                        FluidizationFluidTypes.CRYONITE_FLUID_TYPE.get(), FluidizationBlocks.FROZEN_ACID_BLOCK.get().defaultBlockState()));
        FluidInteractionRegistry.addInteraction(FluidizationFluidTypes.CRYONITE_FLUID_TYPE.get(),
                new FluidInteractionRegistry.InteractionInformation(
                        Fluids.WATER.getFluidType(), Blocks.ICE.defaultBlockState()));
    }
}
