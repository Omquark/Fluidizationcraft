package com.omquark.fluidizationcraft.Data;

import com.omquark.fluidizationcraft.Items.FluidizationItems;
import net.minecraft.data.PackOutput;
import net.minecraft.world.item.Item;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryObject;

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
    }

//    private void basicItem(RegistryObject<Item> item){
//        super.basicItem(item.get());
//    }
}
