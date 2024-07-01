package com.omquark.fluidizationcraft.Items;

import com.omquark.fluidizationcraft.FluidizationCraft;
import com.omquark.fluidizationcraft.Fluids.FluidizationFluids;
import net.minecraft.world.item.BucketItem;
import net.minecraft.world.item.Item;
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

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
