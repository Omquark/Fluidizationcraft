package com.omquark.fluidizationcraft.Items;

import com.omquark.fluidizationcraft.FluidizationCraft;
import com.omquark.fluidizationcraft.fluids.FluidizationFluids;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.material.Fluids;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class FluidizationItems {

    private FluidizationItems() {
    }

    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, FluidizationCraft.MODID);
    public static final RegistryObject<Item> BUCKET_ACID = ITEMS.register("bucket_acid",
            () -> new FluidizationBucket(FluidizationFluids.SOURCE_ACID, new Item.Properties().stacksTo(1)));
    public static final RegistryObject<Item> BUCKET_CRYONITE = ITEMS.register("bucket_cryonite",
            () -> new FluidizationBucket(FluidizationFluids.SOURCE_CRYONITE, new Item.Properties().stacksTo(1)));
    public static final RegistryObject<Item> BUCKET_NEPTUNIUM = ITEMS.register("bucket_neptunium",
            () -> new FluidizationBucket(FluidizationFluids.SOURCE_NEPTUNIUM, new Item.Properties().stacksTo(1)));
    public static final RegistryObject<Item> BUCKET_NETHERFLOW = ITEMS.register("bucket_netherflow",
            () -> new FluidizationBucket(FluidizationFluids.SOURCE_NETHERFLOW, new Item.Properties().stacksTo(1)));
    public static final RegistryObject<Item> BUCKET_PLUTONIUM = ITEMS.register("bucket_plutonium",
            () -> new FluidizationBucket(FluidizationFluids.SOURCE_PLUTONIUM, new Item.Properties().stacksTo(1)));
    public static final RegistryObject<Item> BUCKET_PYRONITE = ITEMS.register("bucket_pyronite",
            () -> new FluidizationBucket(FluidizationFluids.SOURCE_PYRONITE, new Item.Properties().stacksTo(1)));
    public static final RegistryObject<Item> BUCKET_RADIONITE = ITEMS.register("bucket_radionite",
            () -> new FluidizationBucket(FluidizationFluids.SOURCE_RADIONITE, new Item.Properties().stacksTo(1)));
    public static final RegistryObject<Item> BUCKET_URANIUM = ITEMS.register("bucket_uranium",
            () -> new FluidizationBucket(FluidizationFluids.SOURCE_URANIUM, new Item.Properties().stacksTo(1)));
    public static final RegistryObject<Item> VIAL_EMPTY = ITEMS.register("vial_empty",
            () -> new ModVial(() -> Fluids.EMPTY, new Item.Properties().stacksTo(16)));
    public static final RegistryObject<Item> VIAL_ACID = ITEMS.register("vial_acid",
            () -> new ModVial(FluidizationFluids.SOURCE_ACID, new Item.Properties().stacksTo(16)));
    public static final RegistryObject<Item> VIAL_CRYONITE = ITEMS.register("vial_cryonite",
            () -> new ModVial(FluidizationFluids.SOURCE_CRYONITE, new Item.Properties().stacksTo(16)));
    public static final RegistryObject<Item> VIAL_NEPTUNIUM = ITEMS.register("vial_neptunium",
            () -> new ModVial(FluidizationFluids.SOURCE_NEPTUNIUM, new Item.Properties().stacksTo(16)));
    public static final RegistryObject<Item> VIAL_NETHERFLOW = ITEMS.register("vial_netherflow",
            () -> new ModVial(FluidizationFluids.SOURCE_NETHERFLOW, new Item.Properties().stacksTo(16)));
    public static final RegistryObject<Item> VIAL_PLUTONIUM = ITEMS.register("vial_plutonium",
            () -> new ModVial(FluidizationFluids.SOURCE_PLUTONIUM, new Item.Properties().stacksTo(16)));
    public static final RegistryObject<Item> VIAL_PYRONITE = ITEMS.register("vial_pyronite",
            () -> new ModVial(FluidizationFluids.SOURCE_PYRONITE, new Item.Properties().stacksTo(16)));
    public static final RegistryObject<Item> VIAL_RADIONITE = ITEMS.register("vial_radionite",
            () -> new ModVial(FluidizationFluids.SOURCE_RADIONITE, new Item.Properties().stacksTo(16)));
    public static final RegistryObject<Item> VIAL_URANIUM = ITEMS.register("vial_uranium",
            () -> new ModVial(FluidizationFluids.SOURCE_URANIUM, new Item.Properties().stacksTo(16)));
    public static final RegistryObject<Item> CELL_ACID = ITEMS.register("cell_acid",
            () -> new ModCell(new Item.Properties().durability(16).stacksTo(1)));
    public static final RegistryObject<Item> GUN_ACID = ITEMS.register("gun_acid",
            () -> new ItemGunFluid(new Item.Properties().durability(16).stacksTo(1)));
    public static final RegistryObject<Item> GUN_CRYO = ITEMS.register("gun_cryo",
            () -> new ItemGunCryo(new Item.Properties().durability(16).stacksTo(1)));

    public static final RegistryObject<Item> RAW_ALUMINUM = ITEMS.register("raw_aluminum",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> RAW_LEAD = ITEMS.register("raw_lead",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> RAW_NEPTUNIUM = ITEMS.register("raw_neptunium",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> RAW_PLUTONIUM = ITEMS.register("raw_plutonium",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> RAW_RADIONITE = ITEMS.register("raw_radionite",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> RAW_TIN = ITEMS.register("raw_tin",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> RAW_URANIUM = ITEMS.register("raw_uranium",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> INGOT_ALUMINUM = ITEMS.register("ingot_aluminum",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> INGOT_LEAD = ITEMS.register("ingot_lead",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> INGOT_NEPTUNIUM = ITEMS.register("ingot_neptunium",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> INGOT_PLUTONIUM = ITEMS.register("ingot_plutonium",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> INGOT_RADIONITE = ITEMS.register("ingot_radionite",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> INGOT_TIN = ITEMS.register("ingot_tin",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> INGOT_URANIUM = ITEMS.register("ingot_uranium",
            () -> new Item(new Item.Properties()));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
