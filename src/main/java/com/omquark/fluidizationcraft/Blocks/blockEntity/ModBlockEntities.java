package com.omquark.fluidizationcraft.blocks.blockEntity;

import com.omquark.fluidizationcraft.blocks.DissolvinatorBlock;
import com.omquark.fluidizationcraft.blocks.FluidizationBlocks;
import com.omquark.fluidizationcraft.FluidizationCraft;
import net.minecraft.Util;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.util.datafix.fixes.References;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
//import net.minecraftforge.eventbus.api.IEventBus;
//import net.minecraftforge.registries.DeferredRegister;
//import net.minecraftforge.registries.ForgeRegistries;
//import net.minecraftforge.registries.RegistryObject;

public class ModBlockEntities {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES =
            DeferredRegister.create(BuiltInRegistries.BLOCK_ENTITY_TYPE, FluidizationCraft.MODID);

    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<?>> DISSOLVINATOR_ENTITY =
            BLOCK_ENTITIES.register("dissolvinator_entity", () ->
                    BlockEntityType.Builder.of(DissolvinatorBlockEntity::new,
                            FluidizationBlocks.DISSOLVINATOR_BLOCK.get())
                            .build(Util.fetchChoiceType(References.BLOCK_ENTITY, "DissolvinatorBlock")));

    public static void register(IEventBus eventBus){
        BLOCK_ENTITIES.register(eventBus);
    }
}
