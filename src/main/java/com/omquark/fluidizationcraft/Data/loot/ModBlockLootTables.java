package com.omquark.fluidizationcraft.data.loot;

import com.omquark.fluidizationcraft.blocks.FluidizationBlocks;
import com.omquark.fluidizationcraft.Items.FluidizationItems;
import net.minecraft.MethodsReturnNonnullByDefault;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.RegistryObject;

import java.util.Set;

@MethodsReturnNonnullByDefault
public class ModBlockLootTables extends BlockLootSubProvider {
    public ModBlockLootTables() {
        super(Set.of(), FeatureFlags.REGISTRY.allFlags());
    }

    @Override
    protected void generate() {
        add(FluidizationBlocks.ALUMINUM_ORE_BLOCK.get(),
                block -> createOreDrop(FluidizationBlocks.ALUMINUM_ORE_BLOCK.get(), FluidizationItems.RAW_ALUMINUM.get()));
        add(FluidizationBlocks.LEAD_ORE_BLOCK.get(),
                block -> createOreDrop(FluidizationBlocks.LEAD_ORE_BLOCK.get(), FluidizationItems.RAW_LEAD.get()));
        add(FluidizationBlocks.NEPTUNIUM_ORE_BLOCK.get(),
                block -> createOreDrop(FluidizationBlocks.NEPTUNIUM_ORE_BLOCK.get(), FluidizationItems.RAW_NEPTUNIUM.get()));
        add(FluidizationBlocks.PLUTONIUM_ORE_BLOCK.get(),
                block -> createOreDrop(FluidizationBlocks.PLUTONIUM_ORE_BLOCK.get(), FluidizationItems.RAW_PLUTONIUM.get()));
        add(FluidizationBlocks.RADIONITE_ORE_BLOCK.get(),
                block -> createOreDrop(FluidizationBlocks.RADIONITE_ORE_BLOCK.get(), FluidizationItems.RAW_RADIONITE.get()));
        add(FluidizationBlocks.TIN_ORE_BLOCK.get(),
                block -> createOreDrop(FluidizationBlocks.TIN_ORE_BLOCK.get(), FluidizationItems.RAW_TIN.get()));
        add(FluidizationBlocks.URANIUM_ORE_BLOCK.get(),
                block -> createOreDrop(FluidizationBlocks.URANIUM_ORE_BLOCK.get(), FluidizationItems.RAW_URANIUM.get()));

        dropSelf(FluidizationBlocks.FROZEN_ACID_BLOCK.get());
        dropSelf(FluidizationBlocks.FROZEN_CRYONITE_BLOCK.get());
        dropSelf(FluidizationBlocks.DISSOLVINATOR_BLOCK.get());
        dropSelf(FluidizationBlocks.REINFORCED_GLASS.get());
        dropSelf(FluidizationBlocks.ACID_TNT.get());
    }

    @Override
    protected Iterable<Block> getKnownBlocks() {
        return FluidizationBlocks.BLOCKS.getEntries().stream().map(RegistryObject::get)::iterator;
    }
}
