package com.omquark.fluidizationcraft.Blocks;

import com.omquark.fluidizationcraft.FluidizationCraft;
import com.omquark.fluidizationcraft.Items.FluidizationItems;
import com.omquark.fluidizationcraft.Fluids.FluidizationFluids;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

public class FluidizationBlocks {

    private FluidizationBlocks() {}
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, FluidizationCraft.MODID);
    public static final RegistryObject<Block> FROZEN_ACID_BLOCK = registerBlock("acid_frozen", () -> new AcidFrozen(null));
    public static final RegistryObject<LiquidBlock> ACID_BLOCK = registerBlock("acid_block",
            () -> new AcidBlock(FluidizationFluids.SOURCE_ACID, BlockBehaviour.Properties.of()
                    .mapColor(MapColor.COLOR_LIGHT_GREEN).replaceable().noCollission().strength(100f)
                    .pushReaction(PushReaction.DESTROY).noLootTable().liquid().sound(SoundType.EMPTY).randomTicks()));
    public static final RegistryObject<LiquidBlock> CRYONITE_BLOCK = registerBlock("cryonite_block",
            () -> new CryoniteBlock(FluidizationFluids.SOURCE_CRYONITE, BlockBehaviour.Properties.of()
                    .mapColor(MapColor.COLOR_LIGHT_BLUE).replaceable().noCollission().strength(100f)
                    .pushReaction(PushReaction.DESTROY).noLootTable().liquid().sound(SoundType.EMPTY).randomTicks()));
    private static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<T> block){
        RegistryObject<T> registeredBlock = BLOCKS.register(name, block);
        registerBlockItem(name, registeredBlock);
        return registeredBlock;
    }

    private static <T extends Block> void registerBlockItem(String name, RegistryObject<T> block){
        FluidizationItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties()));
    }

    public static void register(IEventBus eventBus){
        BLOCKS.register(eventBus);
    }
}
