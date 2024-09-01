package com.omquark.fluidizationcraft.data;

import com.omquark.fluidizationcraft.FluidizationCraft;
import com.omquark.fluidizationcraft.damageTypes.FluidizationDamageTypes;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.DataProvider;
import net.minecraft.data.PackOutput;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.common.data.DatapackBuiltinEntriesProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.data.event.GatherDataEvent;

import java.util.HashSet;
import java.util.Set;

@EventBusSubscriber(bus = EventBusSubscriber.Bus.MOD, modid = FluidizationCraft.MODID)
public class DataGeneratorHandler {

    @SubscribeEvent
    public static void gatherData(GatherDataEvent event) {
        DataGenerator generator = event.getGenerator();
        PackOutput packOutput = generator.getPackOutput();
        ExistingFileHelper existingFileHelper = event.getExistingFileHelper();

        generator.addProvider(true, new FluidizationBlockStateProvider(packOutput, existingFileHelper));
        generator.addProvider(true, new FluidizationItemProvider(packOutput, FluidizationCraft.MODID, existingFileHelper));
        generator.addProvider(true, new ModRecipeProvider(packOutput, event.getLookupProvider()));
        generator.addProvider(true, ModLootTableProvider.create(packOutput, event.getLookupProvider()));
        generator.addProvider(true, new ModBlockDataGenerator(packOutput, event.getLookupProvider(), existingFileHelper));

        generator.addProvider(event.includeServer(),
                (DataProvider.Factory<DatapackBuiltinEntriesProvider>) (output) -> new DatapackBuiltinEntriesProvider
                        (output, event.getLookupProvider(), new RegistrySetBuilder()
                                .add(Registries.DAMAGE_TYPE, FluidizationDamageTypes::bootstrap),
                        Set.of(FluidizationCraft.MODID)));
    }
}
