package com.omquark.fluidizationcraft.fluids;

import com.omquark.fluidizationcraft.blocks.ModBlocks;
import com.omquark.fluidizationcraft.FluidizationCraft;
import com.omquark.fluidizationcraft.items.ModItems;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.material.Fluid;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModFluids {
    public static final DeferredRegister<Fluid> FLUIDS =
            DeferredRegister.create(BuiltInRegistries.FLUID, FluidizationCraft.MODID);

    public static final DeferredHolder<Fluid, ? extends ModFluid> SOURCE_ACID = FLUIDS.register("acid_fluid",
            () -> new ModFluidSourceAcid(ModFluids.ACID_FLUID_PROPERTIES));
    public static final DeferredHolder<Fluid, ? extends ModFluid> FLOWING_ACID = FLUIDS.register("acid_flowing",
            () -> new ModFluid.Flowing(ModFluids.ACID_FLUID_PROPERTIES));
    public static final DeferredHolder<Fluid, ? extends ModFluid> SOURCE_CRYONITE = FLUIDS.register("cryonite_fluid",
            () -> new ModFluid.Source(ModFluids.CRYONITE_FLUID_PROPERTIES));
    public static final DeferredHolder<Fluid, ? extends ModFluid> FLOWING_CRYONITE = FLUIDS.register("cryonite_flowing",
            () -> new ModFluid.Flowing(ModFluids.CRYONITE_FLUID_PROPERTIES));
    public static final DeferredHolder<Fluid, ? extends ModFluid> SOURCE_NEPTUNIUM = FLUIDS.register("neptunium_fluid",
            () -> new ModFluid.Source(ModFluids.NEPTUNIUM_FLUID_PROPERTIES));
    public static final DeferredHolder<Fluid, ? extends ModFluid> FLOWING_NEPTUNIUM = FLUIDS.register("neptunium_flowing",
            () -> new ModFluid.Flowing(ModFluids.NEPTUNIUM_FLUID_PROPERTIES));
    public static final DeferredHolder<Fluid, ? extends ModFluid> SOURCE_NETHERFLOW = FLUIDS.register("netherflow_fluid",
            () -> new ModFluid.Source(ModFluids.NETHERFLOW_FLUID_PROPERTIES));
    public static final DeferredHolder<Fluid, ? extends ModFluid> FLOWING_NETHERFLOW = FLUIDS.register("netherflow_flowing",
            () -> new ModFluid.Flowing(ModFluids.NETHERFLOW_FLUID_PROPERTIES));
    public static final DeferredHolder<Fluid, ? extends ModFluid> SOURCE_PLUTONIUM = FLUIDS.register("plutonium_fluid",
            () -> new ModFluid.Source(ModFluids.PLUTONIUM_FLUID_PROPERTIES));
    public static final DeferredHolder<Fluid, ? extends ModFluid> FLOWING_PLUTONIUM = FLUIDS.register("plutonium_flowing",
            () -> new ModFluid.Flowing(ModFluids.PLUTONIUM_FLUID_PROPERTIES));
    public static final DeferredHolder<Fluid, ? extends ModFluid> SOURCE_PYRONITE = FLUIDS.register("pyronite_fluid",
            () -> new ModFluid.Source(ModFluids.PYRONITE_FLUID_PROPERTIES));
    public static final DeferredHolder<Fluid, ? extends ModFluid> FLOWING_PYRONITE = FLUIDS.register("pyronite_flowing",
            () -> new ModFluid.Flowing(ModFluids.PYRONITE_FLUID_PROPERTIES));
    public static final DeferredHolder<Fluid, ? extends ModFluid> SOURCE_RADIONITE = FLUIDS.register("radionite_fluid",
            () -> new ModFluid.Source(ModFluids.RADIONITE_FLUID_PROPERTIES));
    public static final DeferredHolder<Fluid, ? extends ModFluid> FLOWING_RADIONITE = FLUIDS.register("radionite_flowing",
            () -> new ModFluid.Flowing(ModFluids.RADIONITE_FLUID_PROPERTIES));
    public static final DeferredHolder<Fluid, ? extends ModFluid> SOURCE_URANIUM = FLUIDS.register("uranium_fluid",
            () -> new ModFluid.Source(ModFluids.URANIUM_FLUID_PROPERTIES));
    public static final DeferredHolder<Fluid, ? extends ModFluid> FLOWING_URANIUM = FLUIDS.register("uranium_flowing",
            () -> new ModFluid.Flowing(ModFluids.URANIUM_FLUID_PROPERTIES));
    public static final ModFluid.ModProperties ACID_FLUID_PROPERTIES = (ModFluid.ModProperties) new ModFluid.ModProperties(
            ModFluidTypes.ACID_FLUID_TYPE, SOURCE_ACID, FLOWING_ACID)
            .vial(ModItems.VIAL_ACID)
            .slopeFindDistance(2).levelDecreasePerBlock(1).block(ModBlocks.ACID_BLOCK)
            .bucket(ModItems.BUCKET_ACID).tickRate(2);
    public static final ModFluid.ModProperties CRYONITE_FLUID_PROPERTIES = (ModFluid.ModProperties) new ModFluid.ModProperties(
            ModFluidTypes.CRYONITE_FLUID_TYPE, SOURCE_CRYONITE, FLOWING_CRYONITE)
            .vial(ModItems.VIAL_CRYONITE)
            .slopeFindDistance(2).levelDecreasePerBlock(2).block(ModBlocks.CRYONITE_BLOCK)
            .bucket(ModItems.BUCKET_CRYONITE).tickRate(16);
    public static final ModFluid.ModProperties NETHERFLOW_FLUID_PROPERTIES = (ModFluid.ModProperties) new ModFluid.ModProperties(
            ModFluidTypes.NETHERFLOW_FLUID_TYPE, SOURCE_NETHERFLOW, FLOWING_NETHERFLOW)
            .vial(ModItems.VIAL_NETHERFLOW)
            .slopeFindDistance(2).levelDecreasePerBlock(1).block(ModBlocks.NETHERFLOW_FLUID_BLOCK)
            .bucket(ModItems.BUCKET_NETHERFLOW).tickRate(12);
    public static final ModFluid.ModProperties NEPTUNIUM_FLUID_PROPERTIES = (ModFluid.ModProperties) new ModFluid.ModProperties(
            ModFluidTypes.NEPTUNIUM_FLUID_TYPE, SOURCE_NEPTUNIUM, FLOWING_NEPTUNIUM)
            .vial(ModItems.VIAL_NEPTUNIUM)
            .slopeFindDistance(2).levelDecreasePerBlock(1).block(ModBlocks.NEPTUNIUM_FLUID_BLOCK)
            .bucket(ModItems.BUCKET_NEPTUNIUM);
    public static final ModFluid.ModProperties PLUTONIUM_FLUID_PROPERTIES = (ModFluid.ModProperties) new ModFluid.ModProperties(
            ModFluidTypes.PLUTONIUM_FLUID_TYPE, SOURCE_PLUTONIUM, FLOWING_PLUTONIUM)
            .vial(ModItems.VIAL_PLUTONIUM)
            .slopeFindDistance(2).levelDecreasePerBlock(1).block(ModBlocks.PLUTONIUM_FLUID_BLOCK)
            .bucket(ModItems.BUCKET_PLUTONIUM);
    public static final ModFluid.ModProperties PYRONITE_FLUID_PROPERTIES = (ModFluid.ModProperties) new ModFluid.ModProperties(
            ModFluidTypes.PYRONITE_FLUID_TYPE, SOURCE_PYRONITE, FLOWING_PYRONITE)
            .vial(ModItems.VIAL_PYRONITE)
            .slopeFindDistance(2).levelDecreasePerBlock(1).block(ModBlocks.PYRONITE_FLUID_BLOCK)
            .bucket(ModItems.BUCKET_PYRONITE);
    public static final ModFluid.ModProperties RADIONITE_FLUID_PROPERTIES = (ModFluid.ModProperties) new ModFluid.ModProperties(
            ModFluidTypes.RADIONITE_FLUID_TYPE, SOURCE_RADIONITE, FLOWING_RADIONITE)
            .vial(ModItems.VIAL_RADIONITE)
            .slopeFindDistance(2).levelDecreasePerBlock(1).block(ModBlocks.RADIONITE_FLUID_BLOCK)
            .bucket(ModItems.BUCKET_RADIONITE);
    public static final ModFluid.ModProperties URANIUM_FLUID_PROPERTIES = (ModFluid.ModProperties) new ModFluid.ModProperties(
            ModFluidTypes.URANIUM_FLUID_TYPE, SOURCE_URANIUM, FLOWING_URANIUM)
            .vial(ModItems.VIAL_URANIUM)
            .slopeFindDistance(2).levelDecreasePerBlock(1).block(ModBlocks.URANIUM_FLUID_BLOCK)
            .bucket(ModItems.BUCKET_URANIUM);

    public static void register(IEventBus eventBus) {
        FLUIDS.register(eventBus);
    }

    public static void createInteractions() {
//        HashMap<Block, BlockState> acidBlockInteractions = new HashMap<>();
//        HashMap<FluidType, BlockState> acidFluidInteractions = new HashMap<>();
//        acidFluidInteractions.put(Fluids.WATER.getFluidType(), Blocks.CLAY.defaultBlockState());
//        acidBlockInteractions.put(Blocks.DIRT, Blocks.COARSE_DIRT.defaultBlockState());
//        acidBlockInteractions.put(Blocks.GRASS_BLOCK, Blocks.DIRT.defaultBlockState());
//        acidBlockInteractions.put(Blocks.SANDSTONE, Blocks.SAND.defaultBlockState());
//        acidBlockInteractions.put(Blocks.STONE, Blocks.COBBLESTONE.defaultBlockState());
//        acidBlockInteractions.put(Blocks.COBBLESTONE, Blocks.GRAVEL.defaultBlockState());
//        acidBlockInteractions.put(Blocks.OBSIDIAN, Blocks.COBBLESTONE.defaultBlockState());
//        acidBlockInteractions.put(Blocks.GLOWSTONE, Blocks.NETHERRACK.defaultBlockState());
//
//        acidBlockInteractions.put(Blocks.OAK_LOG, Blocks.STRIPPED_OAK_LOG.defaultBlockState());
//        acidBlockInteractions.put(Blocks.BIRCH_LOG, Blocks.STRIPPED_BIRCH_LOG.defaultBlockState());
//        acidBlockInteractions.put(Blocks.CHERRY_LOG, Blocks.STRIPPED_CHERRY_LOG.defaultBlockState());
//        acidBlockInteractions.put(Blocks.ACACIA_LOG, Blocks.STRIPPED_ACACIA_LOG.defaultBlockState());
//        acidBlockInteractions.put(Blocks.DARK_OAK_LOG, Blocks.STRIPPED_DARK_OAK_LOG.defaultBlockState());
//
//        acidBlockInteractions.put(Blocks.OAK_WOOD, Blocks.STRIPPED_OAK_WOOD.defaultBlockState());
//        acidBlockInteractions.put(Blocks.BIRCH_WOOD, Blocks.STRIPPED_BIRCH_WOOD.defaultBlockState());
//        acidBlockInteractions.put(Blocks.CHERRY_WOOD, Blocks.STRIPPED_CHERRY_WOOD.defaultBlockState());
//        acidBlockInteractions.put(Blocks.ACACIA_WOOD, Blocks.STRIPPED_ACACIA_WOOD.defaultBlockState());
//        acidBlockInteractions.put(Blocks.DARK_OAK_WOOD, Blocks.STRIPPED_DARK_OAK_WOOD.defaultBlockState());
//
//        acidBlockInteractions.put(Blocks.OAK_PLANKS, Blocks.AIR.defaultBlockState());
//        acidBlockInteractions.put(Blocks.BIRCH_PLANKS, Blocks.AIR.defaultBlockState());
//        acidBlockInteractions.put(Blocks.CHERRY_PLANKS, Blocks.AIR.defaultBlockState());
//        acidBlockInteractions.put(Blocks.ACACIA_PLANKS, Blocks.AIR.defaultBlockState());
//        acidBlockInteractions.put(Blocks.DARK_OAK_PLANKS, Blocks.AIR.defaultBlockState());
//
//        acidBlockInteractions.put(Blocks.OAK_LEAVES, Blocks.AIR.defaultBlockState());
//        acidBlockInteractions.put(Blocks.BIRCH_LEAVES, Blocks.AIR.defaultBlockState());
//        acidBlockInteractions.put(Blocks.CHERRY_LEAVES, Blocks.AIR.defaultBlockState());
//        acidBlockInteractions.put(Blocks.ACACIA_LEAVES, Blocks.AIR.defaultBlockState());
//        acidBlockInteractions.put(Blocks.DARK_OAK_LEAVES, Blocks.AIR.defaultBlockState());
//
//        SOURCE_ACID.get().setBlockInteractions(acidBlockInteractions);
//        SOURCE_ACID.get().setFluidInteractions(acidFluidInteractions);
//        FLOWING_ACID.get().setBlockInteractions(acidBlockInteractions);
//        FLOWING_ACID.get().setFluidInteractions(acidFluidInteractions);
    }
}
