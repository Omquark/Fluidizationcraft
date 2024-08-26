package com.omquark.fluidizationcraft.blocks.blockEntity;

import com.omquark.fluidizationcraft.blocks.FluidizationBlocks;
import com.omquark.fluidizationcraft.FluidizationCraft;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModBlockEntities {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES =
            DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, FluidizationCraft.MODID);

    public static final RegistryObject<BlockEntityType<DissolvinatorBlockEntity>> DISSOLVINATOR_ENTITY =
            BLOCK_ENTITIES.register("dissolvinator_entity", () ->
                    BlockEntityType.Builder.of(DissolvinatorBlockEntity::new,
                            FluidizationBlocks.DISSOLVINATOR_BLOCK.get()).build(null));

    public static void register(IEventBus eventBus){
        BLOCK_ENTITIES.register(eventBus);
    }
}
