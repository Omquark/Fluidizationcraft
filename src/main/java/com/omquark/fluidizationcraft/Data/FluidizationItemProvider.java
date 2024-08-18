package com.omquark.fluidizationcraft.Data;

import com.omquark.fluidizationcraft.Items.FluidizationItems;
import net.minecraft.data.PackOutput;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

public class FluidizationItemProvider extends ItemModelProvider {
    public FluidizationItemProvider(PackOutput output, String modid, ExistingFileHelper existingFileHelper) {
        super(output, modid, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        basicItem(FluidizationItems.VIAL_EMPTY.get());
        basicItem(FluidizationItems.VIAL_ACID.get());
        basicItem(FluidizationItems.VIAL_CRYONITE.get());
        basicItem(FluidizationItems.VIAL_NEPTUNIUM.get());
        basicItem(FluidizationItems.VIAL_NETHERFLOW.get());
        basicItem(FluidizationItems.VIAL_PLUTONIUM.get());
        basicItem(FluidizationItems.VIAL_PYRONITE.get());
        basicItem(FluidizationItems.VIAL_RADIONITE.get());
        basicItem(FluidizationItems.VIAL_URANIUM.get());
        basicItem(FluidizationItems.CELL_ACID.get());
        basicItem(FluidizationItems.GUN_ACID.get());
        basicItem(FluidizationItems.GUN_CRYO.get());
        basicItem(FluidizationItems.RAW_ALUMINUM.get());
        basicItem(FluidizationItems.RAW_LEAD.get());
        basicItem(FluidizationItems.RAW_NEPTUNIUM.get());
        basicItem(FluidizationItems.RAW_PLUTONIUM.get());
        basicItem(FluidizationItems.RAW_RADIONITE.get());
        basicItem(FluidizationItems.RAW_TIN.get());
        basicItem(FluidizationItems.RAW_URANIUM.get());
    }
}
