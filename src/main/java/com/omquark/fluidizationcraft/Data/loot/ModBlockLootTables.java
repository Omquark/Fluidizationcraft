package com.omquark.fluidizationcraft.data.loot;

import com.omquark.fluidizationcraft.blocks.FluidizationBlocks;
import com.omquark.fluidizationcraft.Items.FluidizationItems;
import com.omquark.fluidizationcraft.util.EverythingNonNullByDefault;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.LootTable;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@EverythingNonNullByDefault
public class ModBlockLootTables extends BlockLootSubProvider {

    List<Block> blocks = new ArrayList<>();

    public ModBlockLootTables(HolderLookup.Provider provider) {
        super(Set.of(), FeatureFlags.REGISTRY.allFlags(), provider);
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
        dropSelf(FluidizationBlocks.ACID_TANK.get());
        dropSelf(FluidizationBlocks.DISSOLVINATOR_BLOCK.get());
        dropSelf(FluidizationBlocks.TRANSPARENT_ALUMINUM.get());
        dropSelf(FluidizationBlocks.ACID_BARRIER.get());
        dropSelf(FluidizationBlocks.ACID_TNT.get());
    }

    @Override
    protected void add(Block pBlock, LootTable.Builder pBuilder) {
        super.add(pBlock, pBuilder);
        blocks.add(pBlock);
    }

    @Override
    protected Iterable<Block> getKnownBlocks() {
        return blocks;
    }
}
