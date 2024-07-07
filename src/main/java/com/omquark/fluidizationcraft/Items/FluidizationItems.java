package com.omquark.fluidizationcraft.Items;

import com.omquark.fluidizationcraft.FluidizationCraft;
import com.omquark.fluidizationcraft.Fluids.FluidizationFluids;
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
            () -> new FluidizationVial(() -> Fluids.EMPTY, new Item.Properties().stacksTo(16)));
    public static final RegistryObject<Item> VIAL_ACID = ITEMS.register("vial_acid",
            () -> new FluidizationVial(FluidizationFluids.SOURCE_ACID, new Item.Properties().stacksTo(16)));
    public static final RegistryObject<Item> VIAL_CRYONITE = ITEMS.register("vial_cryonite",
            () -> new FluidizationVial(FluidizationFluids.SOURCE_CRYONITE, new Item.Properties().stacksTo(16)));
    public static final RegistryObject<Item> VIAL_NEPTUNIUM = ITEMS.register("vial_neptunium",
            () -> new FluidizationVial(FluidizationFluids.SOURCE_NEPTUNIUM, new Item.Properties().stacksTo(16)));
    public static final RegistryObject<Item> VIAL_NETHERFLOW = ITEMS.register("vial_netherflow",
            () -> new FluidizationVial(FluidizationFluids.SOURCE_NETHERFLOW, new Item.Properties().stacksTo(16)));
    public static final RegistryObject<Item> VIAL_PLUTONIUM = ITEMS.register("vial_plutonium",
            () -> new FluidizationVial(FluidizationFluids.SOURCE_PLUTONIUM, new Item.Properties().stacksTo(16)));
    public static final RegistryObject<Item> VIAL_PYRONITE = ITEMS.register("vial_pyronite",
            () -> new FluidizationVial(FluidizationFluids.SOURCE_PYRONITE, new Item.Properties().stacksTo(16)));
    public static final RegistryObject<Item> VIAL_RADIONITE = ITEMS.register("vial_radionite",
            () -> new FluidizationVial(FluidizationFluids.SOURCE_RADIONITE, new Item.Properties().stacksTo(16)));
    public static final RegistryObject<Item> VIAL_URANIUM = ITEMS.register("vial_uranium",
            () -> new FluidizationVial(FluidizationFluids.SOURCE_URANIUM, new Item.Properties().stacksTo(16)));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
