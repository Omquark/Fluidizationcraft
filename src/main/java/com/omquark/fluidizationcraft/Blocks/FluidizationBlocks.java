package com.omquark.fluidizationcraft.Blocks;

import com.omquark.fluidizationcraft.DamageTypes.FluidizationDamageTypes;
import com.omquark.fluidizationcraft.FluidizationCraft;
import com.omquark.fluidizationcraft.Items.FluidizationItems;
import com.omquark.fluidizationcraft.Fluids.FluidizationFluids;
import net.minecraft.world.entity.Crackiness;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
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
    public static final RegistryObject<ModLiquidBlock> ACID_BLOCK = registerBlock("acid_block",
            () -> new ModLiquidBlock(FluidizationFluids.SOURCE_ACID, BlockBehaviour.Properties.of()
                    .mapColor(MapColor.COLOR_LIGHT_GREEN).replaceable().noCollission().strength(100f)
                    .pushReaction(PushReaction.DESTROY).noLootTable().liquid().sound(SoundType.EMPTY).randomTicks(),
                    FluidizationDamageTypes.ACID_DAMAGE));
    public static final RegistryObject<LiquidBlock> CRYONITE_BLOCK = registerBlock("cryonite_block",
            () -> new ModLiquidBlock(FluidizationFluids.SOURCE_CRYONITE, BlockBehaviour.Properties.of()
                    .mapColor(MapColor.COLOR_LIGHT_BLUE).replaceable().noCollission().strength(100f)
                    .pushReaction(PushReaction.DESTROY).noLootTable().liquid().sound(SoundType.EMPTY).randomTicks(),
                    FluidizationDamageTypes.CRYONITE_DAMAGE));
    public static final RegistryObject<LiquidBlock> NEPTUNIUM_FLUID_BLOCK = registerBlock("neptunium_fluid_block",
            () -> new ModLiquidBlock(FluidizationFluids.SOURCE_NEPTUNIUM, BlockBehaviour.Properties.of()
                    .mapColor(MapColor.COLOR_LIGHT_BLUE).replaceable().noCollission().strength(100f)
                    .pushReaction(PushReaction.DESTROY).noLootTable().liquid().sound(SoundType.EMPTY).randomTicks(),
                    FluidizationDamageTypes.NEPTUNIUM_DAMAGE));
    public static final RegistryObject<LiquidBlock> NETHERFLOW_FLUID_BLOCK = registerBlock("netherflow_fluid_block",
            () -> new ModLiquidBlock(FluidizationFluids.SOURCE_NETHERFLOW, BlockBehaviour.Properties.of()
                    .mapColor(MapColor.COLOR_LIGHT_BLUE).replaceable().noCollission().strength(100f)
                    .pushReaction(PushReaction.DESTROY).noLootTable().liquid().sound(SoundType.EMPTY).randomTicks(),
                    FluidizationDamageTypes.NETHERFLOW_DAMAGE));
    public static final RegistryObject<LiquidBlock> PLUTONIUM_FLUID_BLOCK = registerBlock("plutonium_fluid_block",
            () -> new ModLiquidBlock(FluidizationFluids.SOURCE_PLUTONIUM, BlockBehaviour.Properties.of()
                    .mapColor(MapColor.COLOR_LIGHT_BLUE).replaceable().noCollission().strength(100f)
                    .pushReaction(PushReaction.DESTROY).noLootTable().liquid().sound(SoundType.EMPTY).randomTicks(),
                    FluidizationDamageTypes.PLUTONIUM_DAMAGE));
    public static final RegistryObject<LiquidBlock> PYRONITE_FLUID_BLOCK = registerBlock("pyronite_fluid_block",
            () -> new ModLiquidBlock(FluidizationFluids.SOURCE_PYRONITE, BlockBehaviour.Properties.of()
                    .mapColor(MapColor.COLOR_LIGHT_BLUE).replaceable().noCollission().strength(100f)
                    .pushReaction(PushReaction.DESTROY).noLootTable().liquid().sound(SoundType.EMPTY).randomTicks(),
                    FluidizationDamageTypes.PYRONITE_DAMAGE));
    public static final RegistryObject<LiquidBlock> RADIONITE_FLUID_BLOCK = registerBlock("radionite_fluid_block",
            () -> new ModLiquidBlock(FluidizationFluids.SOURCE_RADIONITE, BlockBehaviour.Properties.of()
                    .mapColor(MapColor.COLOR_LIGHT_BLUE).replaceable().noCollission().strength(100f)
                    .pushReaction(PushReaction.DESTROY).noLootTable().liquid().sound(SoundType.EMPTY).randomTicks(),
                    FluidizationDamageTypes.RADIONITE_DAMAGE));
    public static final RegistryObject<LiquidBlock> URANIUM_FLUID_BLOCK = registerBlock("uranium_fluid_block",
            () -> new ModLiquidBlock(FluidizationFluids.SOURCE_URANIUM, BlockBehaviour.Properties.of()
                    .mapColor(MapColor.COLOR_LIGHT_BLUE).replaceable().noCollission().strength(100f)
                    .pushReaction(PushReaction.DESTROY).noLootTable().liquid().sound(SoundType.EMPTY).randomTicks(),
                    FluidizationDamageTypes.URANIUM_DAMAGE));
    public static final RegistryObject<Block> ALUMINUM_ORE_BLOCK = registerBlock("aluminum_ore",
            () -> new Block(BlockBehaviour.Properties.of().strength(2f)
                    .pushReaction(PushReaction.NORMAL).sound(SoundType.STONE).mapColor(MapColor.STONE)
                    .requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> LEAD_ORE_BLOCK = registerBlock("lead_ore",
            () -> new Block(BlockBehaviour.Properties.of().strength(10f)
                    .pushReaction(PushReaction.NORMAL).sound(SoundType.STONE).mapColor(MapColor.STONE)
                    .requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> NEPTUNIUM_ORE_BLOCK = registerBlock("neptunium_ore",
            () -> new Block(BlockBehaviour.Properties.of().strength(10f)
                    .pushReaction(PushReaction.NORMAL).sound(SoundType.STONE).mapColor(MapColor.STONE)
                    .requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> PLUTONIUM_ORE_BLOCK = registerBlock("plutonium_ore",
            () -> new Block(BlockBehaviour.Properties.of().strength(10f)
                    .pushReaction(PushReaction.NORMAL).sound(SoundType.STONE).mapColor(MapColor.STONE)
                    .requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> RADIONITE_ORE_BLOCK = registerBlock("radionite_ore",
            () -> new Block(BlockBehaviour.Properties.of().strength(10f)
                    .pushReaction(PushReaction.NORMAL).sound(SoundType.STONE).mapColor(MapColor.STONE)
                    .requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> TIN_ORE_BLOCK = registerBlock("tin_ore",
            () -> new Block(BlockBehaviour.Properties.of().strength(10f)
                    .pushReaction(PushReaction.NORMAL).sound(SoundType.STONE).mapColor(MapColor.STONE)
                    .requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> URANIUM_ORE_BLOCK = registerBlock("uranium_ore",
            () -> new Block(BlockBehaviour.Properties.of().strength(10f)
                    .pushReaction(PushReaction.NORMAL).sound(SoundType.STONE).mapColor(MapColor.STONE)
                    .requiresCorrectToolForDrops()));
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
