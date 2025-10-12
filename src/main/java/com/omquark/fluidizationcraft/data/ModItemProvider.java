package com.omquark.fluidizationcraft.data;

import com.omquark.fluidizationcraft.items.ModItems;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.client.model.generators.ItemModelProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

public class ModItemProvider extends ItemModelProvider {
    public ModItemProvider(PackOutput output, String modid, ExistingFileHelper existingFileHelper) {
        super(output, modid, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        basicItem(ModItems.VIAL_EMPTY.get());
        basicItem(ModItems.VIAL_ACID.get());
        basicItem(ModItems.VIAL_CRYONITE.get());
        basicItem(ModItems.VIAL_NEPTUNIUM.get());
        basicItem(ModItems.VIAL_NETHERFLOW.get());
        basicItem(ModItems.VIAL_PLUTONIUM.get());
        basicItem(ModItems.VIAL_PYRONITE.get());
        basicItem(ModItems.VIAL_RADIONITE.get());
        basicItem(ModItems.VIAL_URANIUM.get());
        basicItem(ModItems.CELL_ACID.get());
        basicItem(ModItems.GUN_ACID.get());
        basicItem(ModItems.GUN_CRYO.get());
        basicItem(ModItems.RAW_ALUMINUM.get());
        basicItem(ModItems.RAW_LEAD.get());
        basicItem(ModItems.RAW_NEPTUNIUM.get());
        basicItem(ModItems.RAW_PLUTONIUM.get());
        basicItem(ModItems.RAW_RADIONITE.get());
        basicItem(ModItems.RAW_TIN.get());
        basicItem(ModItems.RAW_URANIUM.get());
        basicItem(ModItems.DUST_IRON.get());
        basicItem(ModItems.DUST_GOLD.get());
        basicItem(ModItems.DUST_COPPER.get());
        basicItem(ModItems.DUST_ALUMINUM.get());
        basicItem(ModItems.DUST_LEAD.get());
        basicItem(ModItems.DUST_NEPTUNIUM.get());
        basicItem(ModItems.DUST_PLUTONIUM.get());
        basicItem(ModItems.DUST_RADIONITE.get());
        basicItem(ModItems.DUST_TIN.get());
        basicItem(ModItems.DUST_URANIUM.get());
        basicItem(ModItems.INGOT_ALUMINUM.get());
        basicItem(ModItems.INGOT_LEAD.get());
        basicItem(ModItems.INGOT_NEPTUNIUM.get());
        basicItem(ModItems.INGOT_PLUTONIUM.get());
        basicItem(ModItems.INGOT_RADIONITE.get());
        basicItem(ModItems.INGOT_TIN.get());
        basicItem(ModItems.INGOT_URANIUM.get());
        basicItem(ModItems.GOOP_ACID.get());
    }
}
