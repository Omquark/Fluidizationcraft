package com.omquark.fluidizationcraft.blocks.blockEntity;

import com.omquark.fluidizationcraft.blocks.ModBlocks;
import com.omquark.fluidizationcraft.FluidizationCraft;
import net.minecraft.Util;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.util.datafix.fixes.References;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModBlockEntities {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES =
            DeferredRegister.create(BuiltInRegistries.BLOCK_ENTITY_TYPE, FluidizationCraft.MODID);

    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<?>> DISSOLVINATOR_ENTITY =
            BLOCK_ENTITIES.register("dissolvinator_entity", () ->
                    BlockEntityType.Builder.of(DissolvinatorBlockEntity::new,
                            ModBlocks.DISSOLVINATOR_BLOCK.get())
                            .build(Util.fetchChoiceType(References.BLOCK_ENTITY, "DissolvinatorBlock")));

    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<CausticDrumBlockEntity>> CAUSTIC_DRUM_ENTITY =
            BLOCK_ENTITIES.register("caustic_drum_entity", () ->
                    BlockEntityType.Builder.of(CausticDrumBlockEntity::new,
                            ModBlocks.CAUSTIC_DRUM_BLOCK.get())
                            .build(Util.fetchChoiceType(References.BLOCK_ENTITY, "CausticDrumBlock")));

    public static void register(IEventBus eventBus){
        BLOCK_ENTITIES.register(eventBus);
    }
}
