package com.omquark.fluidizationcraft.blocks.blockEntity;

import com.omquark.fluidizationcraft.blocks.DissolvinatorBlock;
import com.omquark.fluidizationcraft.blocks.FluidizationBlocks;
import com.omquark.fluidizationcraft.FluidizationCraft;
import com.omquark.fluidizationcraft.entity.PrimedAcidTNTEntity;
import net.minecraft.Util;
import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.util.datafix.fixes.References;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.Objects;

public class ModBlockEntities {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES =
            DeferredRegister.create(BuiltInRegistries.BLOCK_ENTITY_TYPE, FluidizationCraft.MODID);

    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<?>> DISSOLVINATOR_ENTITY =
            BLOCK_ENTITIES.register("dissolvinator_entity", () ->
                    BlockEntityType.Builder.of(DissolvinatorBlockEntity::new,
                            FluidizationBlocks.DISSOLVINATOR_BLOCK.get())
                            .build(Util.fetchChoiceType(References.BLOCK_ENTITY, "DissolvinatorBlock")));

    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<?>> CAUSTIC_DRUM_ENTITY =
            BLOCK_ENTITIES.register("caustic_drum_entity", () ->
                    BlockEntityType.Builder.of(CausticDrumBlockEntity::new,
                            FluidizationBlocks.CAUSTIC_DRUM_BLOCK.get())
                            .build(Util.fetchChoiceType(References.BLOCK_ENTITY, "CausticDrumBlock")));
    public static void register(IEventBus eventBus){
        BLOCK_ENTITIES.register(eventBus);
    }
}
