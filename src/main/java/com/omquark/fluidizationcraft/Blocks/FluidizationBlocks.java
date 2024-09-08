package com.omquark.fluidizationcraft.blocks;

import com.omquark.fluidizationcraft.damageTypes.FluidizationDamageTypes;
import com.omquark.fluidizationcraft.FluidizationCraft;
import com.omquark.fluidizationcraft.Items.FluidizationItems;
import com.omquark.fluidizationcraft.fluids.FluidizationFluids;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class FluidizationBlocks {

    private FluidizationBlocks() {}
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(BuiltInRegistries.BLOCK, FluidizationCraft.MODID);
    public static final DeferredHolder<Block, Block> FROZEN_ACID_BLOCK = registerBlock("acid_frozen", AcidFrozen::new);
    public static final DeferredHolder<Block, Block> FROZEN_CRYONITE_BLOCK = registerBlock("cryonite_frozen", CryoniteFrozen::new);
    public static final DeferredHolder<Block, Block> TRANSPARENT_ALUMINUM = registerBlock("transparent_aluminum",
            () -> new Block(BlockBehaviour.Properties.of().strength(10f)
                    .pushReaction(PushReaction.NORMAL).sound(SoundType.GLASS).mapColor(MapColor.COLOR_GRAY)
                    .noOcclusion()));
    public static final DeferredHolder<Block, Block> ACID_BARRIER = registerBlock("acid_barrier", AcidBarrier::new);
    public static final DeferredHolder<Block, Block> DISSOLVINATOR_BLOCK = registerBlock("dissolvinator", DissolvinatorBlock::new);
    public static final DeferredHolder<Block, ModLiquidBlock> ACID_BLOCK = registerBlock("acid_block",
            () -> new ModLiquidBlock(FluidizationFluids.SOURCE_ACID.get(), BlockBehaviour.Properties.of()
                    .mapColor(MapColor.COLOR_LIGHT_GREEN).replaceable().noCollission().strength(100f)
                    .pushReaction(PushReaction.DESTROY).noLootTable().liquid().sound(SoundType.EMPTY).randomTicks(),
                    FluidizationDamageTypes.ACID_DAMAGE));
    public static final DeferredHolder<Block, ModLiquidBlock> CRYONITE_BLOCK = registerBlock("cryonite_block",
            () -> new ModLiquidBlock(FluidizationFluids.SOURCE_CRYONITE.get(), BlockBehaviour.Properties.of()
                    .mapColor(MapColor.COLOR_LIGHT_BLUE).replaceable().noCollission().strength(100f)
                    .pushReaction(PushReaction.DESTROY).noLootTable().liquid().sound(SoundType.EMPTY).randomTicks(),
                    FluidizationDamageTypes.CRYONITE_DAMAGE));
    public static final DeferredHolder<Block, ModLiquidBlock> NEPTUNIUM_FLUID_BLOCK = registerBlock("neptunium_fluid_block",
            () -> new ModLiquidBlock(FluidizationFluids.SOURCE_NEPTUNIUM.get(), BlockBehaviour.Properties.of()
                    .mapColor(MapColor.COLOR_LIGHT_BLUE).replaceable().noCollission().strength(100f)
                    .pushReaction(PushReaction.DESTROY).noLootTable().liquid().sound(SoundType.EMPTY).randomTicks(),
                    FluidizationDamageTypes.NEPTUNIUM_DAMAGE));
    public static final DeferredHolder<Block, ModLiquidBlock> NETHERFLOW_FLUID_BLOCK = registerBlock("netherflow_fluid_block",
            () -> new ModLiquidBlock(FluidizationFluids.SOURCE_NETHERFLOW.get(), BlockBehaviour.Properties.of()
                    .mapColor(MapColor.COLOR_LIGHT_BLUE).replaceable().noCollission().strength(100f)
                    .pushReaction(PushReaction.DESTROY).noLootTable().liquid().sound(SoundType.EMPTY).randomTicks(),
                    FluidizationDamageTypes.NETHERFLOW_DAMAGE));
    public static final DeferredHolder<Block, ModLiquidBlock> PLUTONIUM_FLUID_BLOCK = registerBlock("plutonium_fluid_block",
            () -> new ModLiquidBlock(FluidizationFluids.SOURCE_PLUTONIUM.get(), BlockBehaviour.Properties.of()
                    .mapColor(MapColor.COLOR_LIGHT_BLUE).replaceable().noCollission().strength(100f)
                    .pushReaction(PushReaction.DESTROY).noLootTable().liquid().sound(SoundType.EMPTY).randomTicks(),
                    FluidizationDamageTypes.PLUTONIUM_DAMAGE));
    public static final DeferredHolder<Block, ModLiquidBlock> PYRONITE_FLUID_BLOCK = registerBlock("pyronite_fluid_block",
            () -> new ModLiquidBlock(FluidizationFluids.SOURCE_PYRONITE.get(), BlockBehaviour.Properties.of()
                    .mapColor(MapColor.COLOR_LIGHT_BLUE).replaceable().noCollission().strength(100f)
                    .pushReaction(PushReaction.DESTROY).noLootTable().liquid().sound(SoundType.EMPTY).randomTicks(),
                    FluidizationDamageTypes.PYRONITE_DAMAGE));
    public static final DeferredHolder<Block, ModLiquidBlock> RADIONITE_FLUID_BLOCK = registerBlock("radionite_fluid_block",
            () -> new ModLiquidBlock(FluidizationFluids.SOURCE_RADIONITE.get(), BlockBehaviour.Properties.of()
                    .mapColor(MapColor.COLOR_LIGHT_BLUE).replaceable().noCollission().strength(100f)
                    .pushReaction(PushReaction.DESTROY).noLootTable().liquid().sound(SoundType.EMPTY).randomTicks(),
                    FluidizationDamageTypes.RADIONITE_DAMAGE));
    public static final DeferredHolder<Block, ModLiquidBlock> URANIUM_FLUID_BLOCK = registerBlock("uranium_fluid_block",
            () -> new ModLiquidBlock(FluidizationFluids.SOURCE_URANIUM.get(), BlockBehaviour.Properties.of()
                    .mapColor(MapColor.COLOR_LIGHT_BLUE).replaceable().noCollission().strength(100f)
                    .pushReaction(PushReaction.DESTROY).noLootTable().liquid().sound(SoundType.EMPTY).randomTicks(),
                    FluidizationDamageTypes.URANIUM_DAMAGE));
    public static final DeferredHolder<Block, Block> ALUMINUM_ORE_BLOCK = registerBlock("aluminum_ore",
            () -> new Block(BlockBehaviour.Properties.of().strength(2f)
                    .pushReaction(PushReaction.NORMAL).sound(SoundType.STONE).mapColor(MapColor.STONE)
                    .requiresCorrectToolForDrops()));
    public static final DeferredHolder<Block, Block> LEAD_ORE_BLOCK = registerBlock("lead_ore",
            () -> new Block(BlockBehaviour.Properties.of().strength(10f)
                    .pushReaction(PushReaction.NORMAL).sound(SoundType.STONE).mapColor(MapColor.STONE)
                    .requiresCorrectToolForDrops()));
    public static final DeferredHolder<Block, Block> NEPTUNIUM_ORE_BLOCK = registerBlock("neptunium_ore",
            () -> new Block(BlockBehaviour.Properties.of().strength(10f)
                    .pushReaction(PushReaction.NORMAL).sound(SoundType.STONE).mapColor(MapColor.STONE)
                    .requiresCorrectToolForDrops()));
    public static final DeferredHolder<Block, Block> PLUTONIUM_ORE_BLOCK = registerBlock("plutonium_ore",
            () -> new Block(BlockBehaviour.Properties.of().strength(10f)
                    .pushReaction(PushReaction.NORMAL).sound(SoundType.STONE).mapColor(MapColor.STONE)
                    .requiresCorrectToolForDrops()));
    public static final DeferredHolder<Block, Block> RADIONITE_ORE_BLOCK = registerBlock("radionite_ore",
            () -> new Block(BlockBehaviour.Properties.of().strength(10f)
                    .pushReaction(PushReaction.NORMAL).sound(SoundType.STONE).mapColor(MapColor.STONE)
                    .requiresCorrectToolForDrops()));
    public static final DeferredHolder<Block, Block> TIN_ORE_BLOCK = registerBlock("tin_ore",
            () -> new Block(BlockBehaviour.Properties.of().strength(10f)
                    .pushReaction(PushReaction.NORMAL).sound(SoundType.STONE).mapColor(MapColor.STONE)
                    .requiresCorrectToolForDrops()));
    public static final DeferredHolder<Block, Block> URANIUM_ORE_BLOCK = registerBlock("uranium_ore",
            () -> new Block(BlockBehaviour.Properties.of().strength(10f)
                    .pushReaction(PushReaction.NORMAL).sound(SoundType.STONE).mapColor(MapColor.STONE)
                    .requiresCorrectToolForDrops()));

    public static final DeferredHolder<Block, Block> ACID_TNT = registerBlock("acid_tnt", AcidTNT::new);

    private static <T extends Block> DeferredHolder<Block, T> registerBlockNoItem(String name, Supplier<T> block){
        DeferredHolder<Block, T> registeredBlock = BLOCKS.register(name, block);
        return registeredBlock;
    }
    private static <T extends Block> DeferredHolder<Block, T> registerBlock(String name, Supplier<T> block){
        DeferredHolder<Block, T> registeredBlock = BLOCKS.register(name, block);
        registerBlockItem(name, registeredBlock);
        return registeredBlock;
    }

    private static <T extends Block> void registerBlockItem(String name, DeferredHolder<Block, T> block){
        FluidizationItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties()));
    }

    public static void register(IEventBus eventBus){
        BLOCKS.register(eventBus);
    }
}
