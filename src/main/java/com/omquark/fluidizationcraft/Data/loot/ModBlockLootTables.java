package com.omquark.fluidizationcraft.Data.loot;

import com.omquark.fluidizationcraft.Blocks.FluidizationBlocks;
import com.omquark.fluidizationcraft.Items.FluidizationItems;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.RegistryObject;

import java.util.Set;

public class ModBlockLootTables extends BlockLootSubProvider {
    protected ModBlockLootTables() {
        super(Set.of(), FeatureFlags.REGISTRY.allFlags());
    }

    @Override
    protected void generate() {
        this.dropSelf(FluidizationBlocks.ALUMINUM_ORE_BLOCK.get());

//        this.add(FluidizationBlocks.ALUMINUM_ORE_BLOCK.get(),
//                block -> createOreDrop(FluidizationBlocks.ALUMINUM_ORE_BLOCK.get(), FluidizationItems.RAW_ALUMINUM.get()));
    }

    @Override
    protected Iterable<Block> getKnownBlocks() {
        return FluidizationBlocks.BLOCKS.getEntries().stream().map(RegistryObject::get)::iterator;
    }
}
