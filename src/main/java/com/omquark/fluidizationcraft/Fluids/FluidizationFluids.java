package com.omquark.fluidizationcraft.fluids;

import com.omquark.fluidizationcraft.blocks.FluidizationBlocks;
import com.omquark.fluidizationcraft.FluidizationCraft;
import com.omquark.fluidizationcraft.Items.FluidizationItems;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.Fluids;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.fluids.FluidType;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import com.omquark.fluidizationcraft.fluids.ModFluid;
import com.omquark.fluidizationcraft.fluids.FluidizationFluidTypes;

import java.util.HashMap;

public class FluidizationFluids {
    public static final DeferredRegister<Fluid> FLUIDS =
            DeferredRegister.create(BuiltInRegistries.FLUID, FluidizationCraft.MODID);

    public static final DeferredHolder<Fluid, ? extends ModFluid> SOURCE_ACID = FLUIDS.register("acid_fluid",
            () -> new ModFluidSourceAcid(FluidizationFluids.ACID_FLUID_PROPERTIES));
    public static final DeferredHolder<Fluid, ? extends ModFluid> FLOWING_ACID = FLUIDS.register("acid_flowing",
            () -> new ModFluid.Flowing(FluidizationFluids.ACID_FLUID_PROPERTIES));
    public static final DeferredHolder<Fluid, ? extends ModFluid> SOURCE_CRYONITE = FLUIDS.register("cryonite_fluid",
            () -> new ModFluid.Source(FluidizationFluids.CRYONITE_FLUID_PROPERTIES));
    public static final DeferredHolder<Fluid, ? extends ModFluid> FLOWING_CRYONITE = FLUIDS.register("cryonite_flowing",
            () -> new ModFluid.Flowing(FluidizationFluids.CRYONITE_FLUID_PROPERTIES));
    public static final DeferredHolder<Fluid, ? extends ModFluid> SOURCE_NEPTUNIUM = FLUIDS.register("neptunium_fluid",
            () -> new ModFluid.Source(FluidizationFluids.NEPTUNIUM_FLUID_PROPERTIES));
    public static final DeferredHolder<Fluid, ? extends ModFluid> FLOWING_NEPTUNIUM = FLUIDS.register("neptunium_flowing",
            () -> new ModFluid.Flowing(FluidizationFluids.NEPTUNIUM_FLUID_PROPERTIES));
    public static final DeferredHolder<Fluid, ? extends ModFluid> SOURCE_NETHERFLOW = FLUIDS.register("netherflow_fluid",
            () -> new ModFluid.Source(FluidizationFluids.NETHERFLOW_FLUID_PROPERTIES));
    public static final DeferredHolder<Fluid, ? extends ModFluid> FLOWING_NETHERFLOW = FLUIDS.register("netherflow_flowing",
            () -> new ModFluid.Flowing(FluidizationFluids.NETHERFLOW_FLUID_PROPERTIES));
    public static final DeferredHolder<Fluid, ? extends ModFluid> SOURCE_PLUTONIUM = FLUIDS.register("plutonium_fluid",
            () -> new ModFluid.Source(FluidizationFluids.PLUTONIUM_FLUID_PROPERTIES));
    public static final DeferredHolder<Fluid, ? extends ModFluid> FLOWING_PLUTONIUM = FLUIDS.register("plutonium_flowing",
            () -> new ModFluid.Flowing(FluidizationFluids.PLUTONIUM_FLUID_PROPERTIES));
    public static final DeferredHolder<Fluid, ? extends ModFluid> SOURCE_PYRONITE = FLUIDS.register("pyronite_fluid",
            () -> new ModFluid.Source(FluidizationFluids.PYRONITE_FLUID_PROPERTIES));
    public static final DeferredHolder<Fluid, ? extends ModFluid> FLOWING_PYRONITE = FLUIDS.register("pyronite_flowing",
            () -> new ModFluid.Flowing(FluidizationFluids.PYRONITE_FLUID_PROPERTIES));
    public static final DeferredHolder<Fluid, ? extends ModFluid> SOURCE_RADIONITE = FLUIDS.register("radionite_fluid",
            () -> new ModFluid.Source(FluidizationFluids.RADIONITE_FLUID_PROPERTIES));
    public static final DeferredHolder<Fluid, ? extends ModFluid> FLOWING_RADIONITE = FLUIDS.register("radionite_flowing",
            () -> new ModFluid.Flowing(FluidizationFluids.RADIONITE_FLUID_PROPERTIES));
    public static final DeferredHolder<Fluid, ? extends ModFluid> SOURCE_URANIUM = FLUIDS.register("uranium_fluid",
            () -> new ModFluid.Source(FluidizationFluids.URANIUM_FLUID_PROPERTIES));
    public static final DeferredHolder<Fluid, ? extends ModFluid> FLOWING_URANIUM = FLUIDS.register("uranium_flowing",
            () -> new ModFluid.Flowing(FluidizationFluids.URANIUM_FLUID_PROPERTIES));
    public static final ModFluid.ModProperties ACID_FLUID_PROPERTIES = (ModFluid.ModProperties) new ModFluid.ModProperties(
            FluidizationFluidTypes.ACID_FLUID_TYPE, SOURCE_ACID, FLOWING_ACID)
            .vial(FluidizationItems.VIAL_ACID)
            .slopeFindDistance(2).levelDecreasePerBlock(1).block(FluidizationBlocks.ACID_BLOCK)
            .bucket(FluidizationItems.BUCKET_ACID).tickRate(2);
    public static final ModFluid.ModProperties CRYONITE_FLUID_PROPERTIES = (ModFluid.ModProperties) new ModFluid.ModProperties(
            FluidizationFluidTypes.CRYONITE_FLUID_TYPE, SOURCE_CRYONITE, FLOWING_CRYONITE)
            .vial(FluidizationItems.VIAL_CRYONITE)
            .slopeFindDistance(2).levelDecreasePerBlock(2).block(FluidizationBlocks.CRYONITE_BLOCK)
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

    public static void createInteractions() {
        HashMap<Block, BlockState> acidBlockInteractions = new HashMap<>();
        HashMap<FluidType, BlockState> acidFluidInteractions = new HashMap<>();
        acidFluidInteractions.put(Fluids.WATER.getFluidType(), Blocks.CLAY.defaultBlockState());
        acidBlockInteractions.put(Blocks.DIRT, Blocks.COARSE_DIRT.defaultBlockState());
        acidBlockInteractions.put(Blocks.GRASS_BLOCK, Blocks.DIRT.defaultBlockState());
        acidBlockInteractions.put(Blocks.SANDSTONE, Blocks.SAND.defaultBlockState());
        acidBlockInteractions.put(Blocks.STONE, Blocks.COBBLESTONE.defaultBlockState());
        acidBlockInteractions.put(Blocks.COBBLESTONE, Blocks.GRAVEL.defaultBlockState());
        acidBlockInteractions.put(Blocks.OBSIDIAN, Blocks.COBBLESTONE.defaultBlockState());
        acidBlockInteractions.put(Blocks.GLOWSTONE, Blocks.NETHERRACK.defaultBlockState());

        acidBlockInteractions.put(Blocks.OAK_LOG, Blocks.STRIPPED_OAK_LOG.defaultBlockState());
        acidBlockInteractions.put(Blocks.BIRCH_LOG, Blocks.STRIPPED_BIRCH_LOG.defaultBlockState());
        acidBlockInteractions.put(Blocks.CHERRY_LOG, Blocks.STRIPPED_CHERRY_LOG.defaultBlockState());
        acidBlockInteractions.put(Blocks.ACACIA_LOG, Blocks.STRIPPED_ACACIA_LOG.defaultBlockState());
        acidBlockInteractions.put(Blocks.DARK_OAK_LOG, Blocks.STRIPPED_DARK_OAK_LOG.defaultBlockState());

        acidBlockInteractions.put(Blocks.OAK_WOOD, Blocks.STRIPPED_OAK_WOOD.defaultBlockState());
        acidBlockInteractions.put(Blocks.BIRCH_WOOD, Blocks.STRIPPED_BIRCH_WOOD.defaultBlockState());
        acidBlockInteractions.put(Blocks.CHERRY_WOOD, Blocks.STRIPPED_CHERRY_WOOD.defaultBlockState());
        acidBlockInteractions.put(Blocks.ACACIA_WOOD, Blocks.STRIPPED_ACACIA_WOOD.defaultBlockState());
        acidBlockInteractions.put(Blocks.DARK_OAK_WOOD, Blocks.STRIPPED_DARK_OAK_WOOD.defaultBlockState());

        acidBlockInteractions.put(Blocks.OAK_PLANKS, Blocks.AIR.defaultBlockState());
        acidBlockInteractions.put(Blocks.BIRCH_PLANKS, Blocks.AIR.defaultBlockState());
        acidBlockInteractions.put(Blocks.CHERRY_PLANKS, Blocks.AIR.defaultBlockState());
        acidBlockInteractions.put(Blocks.ACACIA_PLANKS, Blocks.AIR.defaultBlockState());
        acidBlockInteractions.put(Blocks.DARK_OAK_PLANKS, Blocks.AIR.defaultBlockState());

        acidBlockInteractions.put(Blocks.OAK_LEAVES, Blocks.AIR.defaultBlockState());
        acidBlockInteractions.put(Blocks.BIRCH_LEAVES, Blocks.AIR.defaultBlockState());
        acidBlockInteractions.put(Blocks.CHERRY_LEAVES, Blocks.AIR.defaultBlockState());
        acidBlockInteractions.put(Blocks.ACACIA_LEAVES, Blocks.AIR.defaultBlockState());
        acidBlockInteractions.put(Blocks.DARK_OAK_LEAVES, Blocks.AIR.defaultBlockState());

        SOURCE_ACID.get().setBlockInteractions(acidBlockInteractions);
        SOURCE_ACID.get().setFluidInteractions(acidFluidInteractions);
        FLOWING_ACID.get().setBlockInteractions(acidBlockInteractions);
        FLOWING_ACID.get().setFluidInteractions(acidFluidInteractions);
    }
}
