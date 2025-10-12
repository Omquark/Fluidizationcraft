package com.omquark.fluidizationcraft.items;

import com.omquark.fluidizationcraft.FluidizationCraft;
import com.omquark.fluidizationcraft.fluids.ModFluids;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.material.Fluids;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModItems {

    private ModItems() {
    }

    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(BuiltInRegistries.ITEM, FluidizationCraft.MODID);
    public static final DeferredHolder<Item, ? extends Item> BUCKET_ACID = ITEMS.register("bucket_acid",
            () -> new ModBucket(ModFluids.SOURCE_ACID.get(), new Item.Properties().stacksTo(1)));
    public static final DeferredHolder<Item, ? extends Item> BUCKET_CRYONITE = ITEMS.register("bucket_cryonite",
            () -> new ModBucket(ModFluids.SOURCE_CRYONITE.get(), new Item.Properties().stacksTo(1)));
    public static final DeferredHolder<Item, ? extends Item> BUCKET_NEPTUNIUM = ITEMS.register("bucket_neptunium",
            () -> new ModBucket(ModFluids.SOURCE_NEPTUNIUM.get(), new Item.Properties().stacksTo(1)));
    public static final DeferredHolder<Item, ? extends Item> BUCKET_NETHERFLOW = ITEMS.register("bucket_netherflow",
            () -> new ModBucket(ModFluids.SOURCE_NETHERFLOW.get(), new Item.Properties().stacksTo(1)));
    public static final DeferredHolder<Item, ? extends Item> BUCKET_PLUTONIUM = ITEMS.register("bucket_plutonium",
            () -> new ModBucket(ModFluids.SOURCE_PLUTONIUM.get(), new Item.Properties().stacksTo(1)));
    public static final DeferredHolder<Item, ? extends Item> BUCKET_PYRONITE = ITEMS.register("bucket_pyronite",
            () -> new ModBucket(ModFluids.SOURCE_PYRONITE.get(), new Item.Properties().stacksTo(1)));
    public static final DeferredHolder<Item, ? extends Item> BUCKET_RADIONITE = ITEMS.register("bucket_radionite",
            () -> new ModBucket(ModFluids.SOURCE_RADIONITE.get(), new Item.Properties().stacksTo(1)));
    public static final DeferredHolder<Item, ? extends Item> BUCKET_URANIUM = ITEMS.register("bucket_uranium",
            () -> new ModBucket(ModFluids.SOURCE_URANIUM.get(), new Item.Properties().stacksTo(1)));
    public static final DeferredHolder<Item, ? extends Item> VIAL_EMPTY = ITEMS.register("vial_empty",
            () -> new ItemVial(() -> Fluids.EMPTY, new Item.Properties().stacksTo(16)));
    public static final DeferredHolder<Item, ? extends Item> VIAL_ACID = ITEMS.register("vial_acid",
            () -> new ItemVial(ModFluids.SOURCE_ACID, new Item.Properties().stacksTo(16)));
    public static final DeferredHolder<Item, ? extends Item> VIAL_CRYONITE = ITEMS.register("vial_cryonite",
            () -> new ItemVial(ModFluids.SOURCE_CRYONITE, new Item.Properties().stacksTo(16)));
    public static final DeferredHolder<Item, ? extends Item> VIAL_NEPTUNIUM = ITEMS.register("vial_neptunium",
            () -> new ItemVial(ModFluids.SOURCE_NEPTUNIUM, new Item.Properties().stacksTo(16)));
    public static final DeferredHolder<Item, ? extends Item> VIAL_NETHERFLOW = ITEMS.register("vial_netherflow",
            () -> new ItemVial(ModFluids.SOURCE_NETHERFLOW, new Item.Properties().stacksTo(16)));
    public static final DeferredHolder<Item, ? extends Item> VIAL_PLUTONIUM = ITEMS.register("vial_plutonium",
            () -> new ItemVial(ModFluids.SOURCE_PLUTONIUM, new Item.Properties().stacksTo(16)));
    public static final DeferredHolder<Item, ? extends Item> VIAL_PYRONITE = ITEMS.register("vial_pyronite",
            () -> new ItemVial(ModFluids.SOURCE_PYRONITE, new Item.Properties().stacksTo(16)));
    public static final DeferredHolder<Item, ? extends Item> VIAL_RADIONITE = ITEMS.register("vial_radionite",
            () -> new ItemVial(ModFluids.SOURCE_RADIONITE, new Item.Properties().stacksTo(16)));
    public static final DeferredHolder<Item, ? extends Item> VIAL_URANIUM = ITEMS.register("vial_uranium",
            () -> new ItemVial(ModFluids.SOURCE_URANIUM, new Item.Properties().stacksTo(16)));
    public static final DeferredHolder<Item, ? extends Item> CELL_ACID = ITEMS.register("cell_acid",
            () -> new ItemCell(new Item.Properties().durability(16).stacksTo(1)));
    public static final DeferredHolder<Item, ? extends Item> GUN_ACID = ITEMS.register("gun_acid",
            () -> new ItemGunFluid(new Item.Properties().durability(16).stacksTo(1)));
    public static final DeferredHolder<Item, ? extends Item> GUN_CRYO = ITEMS.register("gun_cryo",
            () -> new ItemGunCryo(new Item.Properties().durability(16).stacksTo(1)));
    public static final DeferredHolder<Item, ? extends Item> DUST_IRON = ITEMS.register("dust_iron",
            () -> new Item(new Item.Properties()));
    public static final DeferredHolder<Item, ? extends Item> DUST_GOLD = ITEMS.register("dust_gold",
            () -> new Item(new Item.Properties()));
    public static final DeferredHolder<Item, ? extends Item> DUST_COPPER = ITEMS.register("dust_copper",
            () -> new Item(new Item.Properties()));
    public static final DeferredHolder<Item, ? extends Item> RAW_ALUMINUM = ITEMS.register("raw_aluminum",
            () -> new Item(new Item.Properties()));
    public static final DeferredHolder<Item, ? extends Item> DUST_ALUMINUM = ITEMS.register("dust_aluminum",
            () -> new Item(new Item.Properties()));
    public static final DeferredHolder<Item, ? extends Item> RAW_LEAD = ITEMS.register("raw_lead",
            () -> new Item(new Item.Properties()));
    public static final DeferredHolder<Item, ? extends Item> DUST_LEAD = ITEMS.register("dust_lead",
            () -> new Item(new Item.Properties()));
    public static final DeferredHolder<Item, ? extends Item> RAW_NEPTUNIUM = ITEMS.register("raw_neptunium",
            () -> new Item(new Item.Properties()));
    public static final DeferredHolder<Item, ? extends Item> DUST_NEPTUNIUM = ITEMS.register("dust_neptunium",
            () -> new Item(new Item.Properties()));
    public static final DeferredHolder<Item, ? extends Item> RAW_PLUTONIUM = ITEMS.register("raw_plutonium",
            () -> new Item(new Item.Properties()));
    public static final DeferredHolder<Item, ? extends Item> DUST_PLUTONIUM = ITEMS.register("dust_plutonium",
            () -> new Item(new Item.Properties()));
    public static final DeferredHolder<Item, ? extends Item> RAW_RADIONITE = ITEMS.register("raw_radionite",
            () -> new Item(new Item.Properties()));
    public static final DeferredHolder<Item, ? extends Item> DUST_RADIONITE = ITEMS.register("dust_radionite",
            () -> new Item(new Item.Properties()));
    public static final DeferredHolder<Item, ? extends Item> RAW_TIN = ITEMS.register("raw_tin",
            () -> new Item(new Item.Properties()));
    public static final DeferredHolder<Item, ? extends Item> DUST_TIN = ITEMS.register("dust_tin",
            () -> new Item(new Item.Properties()));
    public static final DeferredHolder<Item, ? extends Item> RAW_URANIUM = ITEMS.register("raw_uranium",
            () -> new Item(new Item.Properties()));
    public static final DeferredHolder<Item, ? extends Item> DUST_URANIUM = ITEMS.register("dust_uranium",
            () -> new Item(new Item.Properties()));
    public static final DeferredHolder<Item, ? extends Item> INGOT_ALUMINUM = ITEMS.register("ingot_aluminum",
            () -> new Item(new Item.Properties()));
    public static final DeferredHolder<Item, ? extends Item> INGOT_LEAD = ITEMS.register("ingot_lead",
            () -> new Item(new Item.Properties()));
    public static final DeferredHolder<Item, ? extends Item> INGOT_NEPTUNIUM = ITEMS.register("ingot_neptunium",
            () -> new Item(new Item.Properties()));
    public static final DeferredHolder<Item, ? extends Item> INGOT_PLUTONIUM = ITEMS.register("ingot_plutonium",
            () -> new Item(new Item.Properties()));
    public static final DeferredHolder<Item, ? extends Item> INGOT_RADIONITE = ITEMS.register("ingot_radionite",
            () -> new Item(new Item.Properties()));
    public static final DeferredHolder<Item, ? extends Item> INGOT_TIN = ITEMS.register("ingot_tin",
            () -> new Item(new Item.Properties()));
    public static final DeferredHolder<Item, ? extends Item> INGOT_URANIUM = ITEMS.register("ingot_uranium",
            () -> new Item(new Item.Properties()));
    public static final DeferredHolder<Item, ? extends Item> GOOP_ACID = ITEMS.register("goop_acid",
            () -> new Item(new Item.Properties()));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
