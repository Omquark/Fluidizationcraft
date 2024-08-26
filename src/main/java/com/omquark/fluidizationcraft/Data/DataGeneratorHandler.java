package com.omquark.fluidizationcraft.data;

import com.omquark.fluidizationcraft.damageTypes.FluidizationDamageTypes;
import com.omquark.fluidizationcraft.FluidizationCraft;
import net.minecraft.core.Cloner;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.RegistryAccess;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraft.data.registries.RegistriesDatapackGenerator;
import net.minecraft.resources.RegistryDataLoader;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.HashSet;
import java.util.Set;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, modid = FluidizationCraft.MODID)
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
                new RegistriesDatapackGenerator(packOutput,
                        event
                                .getLookupProvider()
                                .thenApply(r -> constructRegistries(r, BUILDER)),
                        Set.of(FluidizationCraft.MODID)));
    }

    private static final RegistrySetBuilder BUILDER = new RegistrySetBuilder()
            .add(Registries.DAMAGE_TYPE, FluidizationDamageTypes::bootstrap);
//            .add(Registries.DAMAGE_TYPE, (context) -> bootstrap("cryonite", CRYONITE_DAMAGE, context));

    private static HolderLookup.Provider constructRegistries(HolderLookup.Provider original, RegistrySetBuilder datapackEntriesBuilder) {
        Cloner.Factory clonerFactory = new Cloner.Factory();
        var builderKeys = new HashSet<>(datapackEntriesBuilder.getEntryKeys());
        RegistryDataLoader.WORLDGEN_REGISTRIES.forEach(data -> {
            if (!builderKeys.contains(data.key()))
                datapackEntriesBuilder.add(data.key(), context -> {
                });

            data.runWithArguments(clonerFactory::addCodec);
        });

        return datapackEntriesBuilder.buildPatch(RegistryAccess.fromRegistryOfRegistries(BuiltInRegistries.REGISTRY), original, clonerFactory).patches();
    }
}
