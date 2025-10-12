package com.omquark.fluidizationcraft.data.loot;

import com.omquark.fluidizationcraft.blocks.ModBlocks;
import com.omquark.fluidizationcraft.items.ModItems;
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
        add(ModBlocks.ALUMINUM_ORE_BLOCK.get(),
                block -> createOreDrop(ModBlocks.ALUMINUM_ORE_BLOCK.get(), ModItems.RAW_ALUMINUM.get()));
        add(ModBlocks.LEAD_ORE_BLOCK.get(),
                block -> createOreDrop(ModBlocks.LEAD_ORE_BLOCK.get(), ModItems.RAW_LEAD.get()));
        add(ModBlocks.NEPTUNIUM_ORE_BLOCK.get(),
                block -> createOreDrop(ModBlocks.NEPTUNIUM_ORE_BLOCK.get(), ModItems.RAW_NEPTUNIUM.get()));
        add(ModBlocks.PLUTONIUM_ORE_BLOCK.get(),
                block -> createOreDrop(ModBlocks.PLUTONIUM_ORE_BLOCK.get(), ModItems.RAW_PLUTONIUM.get()));
        add(ModBlocks.RADIONITE_ORE_BLOCK.get(),
                block -> createOreDrop(ModBlocks.RADIONITE_ORE_BLOCK.get(), ModItems.RAW_RADIONITE.get()));
        add(ModBlocks.TIN_ORE_BLOCK.get(),
                block -> createOreDrop(ModBlocks.TIN_ORE_BLOCK.get(), ModItems.RAW_TIN.get()));
        add(ModBlocks.URANIUM_ORE_BLOCK.get(),
                block -> createOreDrop(ModBlocks.URANIUM_ORE_BLOCK.get(), ModItems.RAW_URANIUM.get()));

        dropSelf(ModBlocks.FROZEN_ACID_BLOCK.get());
        dropSelf(ModBlocks.FROZEN_CRYONITE_BLOCK.get());
        dropSelf(ModBlocks.ACID_TANK.get());
        dropSelf(ModBlocks.DISSOLVINATOR_BLOCK.get());
        dropSelf(ModBlocks.TRANSPARENT_ALUMINUM.get());
        dropSelf(ModBlocks.ACID_BARRIER.get());
        dropSelf(ModBlocks.ACID_TNT.get());
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
