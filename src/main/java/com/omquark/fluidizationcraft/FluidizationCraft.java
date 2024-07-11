package com.omquark.fluidizationcraft;

import com.mojang.logging.LogUtils;
import com.omquark.fluidizationcraft.Blocks.FluidizationBlocks;
import com.omquark.fluidizationcraft.Data.DataGeneratorHandler;
import com.omquark.fluidizationcraft.Fluids.FluidizationFluidTypes;
import com.omquark.fluidizationcraft.Fluids.FluidizationFluids;
import com.omquark.fluidizationcraft.Items.FluidizationItems;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.*;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.slf4j.Logger;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(FluidizationCraft.MODID)
public class FluidizationCraft
{
    public static final String MODID = "fluidizationcraft";
    public static final Logger LOGGER = LogUtils.getLogger();
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS_REGISTER = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, MODID);

    public static final RegistryObject<CreativeModeTab> FLUIDIZATION_CRAFT_TAB = CREATIVE_MODE_TABS_REGISTER.register("example_tab", () -> CreativeModeTab.builder()
            .withTabsBefore(CreativeModeTabs.COMBAT)
            .icon(() -> FluidizationBlocks.FROZEN_ACID_BLOCK.get().asItem().getDefaultInstance())
            .displayItems((parameters, output) -> {
                output.accept(FluidizationBlocks.FROZEN_ACID_BLOCK.get().asItem());
                output.accept(FluidizationFluids.SOURCE_ACID.get().getBucket());
                output.accept(FluidizationFluids.SOURCE_CRYONITE.get().getBucket());
                output.accept(FluidizationFluids.SOURCE_NEPTUNIUM.get().getBucket());
                output.accept(FluidizationFluids.SOURCE_NETHERFLOW.get().getBucket());
                output.accept(FluidizationFluids.SOURCE_PLUTONIUM.get().getBucket());
                output.accept(FluidizationFluids.SOURCE_PYRONITE.get().getBucket());
                output.accept(FluidizationFluids.SOURCE_RADIONITE.get().getBucket());
                output.accept(FluidizationFluids.SOURCE_URANIUM.get().getBucket());
                output.accept(FluidizationItems.VIAL_EMPTY.get());
                output.accept(FluidizationItems.VIAL_ACID.get());
                output.accept(FluidizationItems.VIAL_CRYONITE.get());
                output.accept(FluidizationItems.VIAL_NEPTUNIUM.get());
                output.accept(FluidizationItems.VIAL_NETHERFLOW.get());
                output.accept(FluidizationItems.VIAL_PLUTONIUM.get());
                output.accept(FluidizationItems.VIAL_PYRONITE.get());
                output.accept(FluidizationItems.VIAL_RADIONITE.get());
                output.accept(FluidizationItems.VIAL_URANIUM.get());
                output.accept(FluidizationItems.CELL_ACID.get());
                output.accept(FluidizationItems.GUN_ACID.get());
                output.accept(FluidizationBlocks.ALUMINUM_ORE_BLOCK.get());
                output.accept(FluidizationBlocks.ARGENTITE_ORE_BLOCK.get());
                output.accept(FluidizationBlocks.LEAD_ORE_BLOCK.get());
                output.accept(FluidizationBlocks.NEPTUNIUM_ORE_BLOCK.get());
                output.accept(FluidizationBlocks.PLUTONIUM_ORE_BLOCK.get());
                output.accept(FluidizationBlocks.RADIONITE_ORE_BLOCK.get());
                output.accept(FluidizationBlocks.TIN_ORE_BLOCK.get());
                output.accept(FluidizationBlocks.URANIUM_ORE_BLOCK.get());
            })
            .title(Component.literal("Fluidization Craft"))
            .build());

    public FluidizationCraft()
    {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        modEventBus.register(DataGeneratorHandler.class);

        modEventBus.addListener(this::commonSetup);

        FluidizationItems.register(modEventBus);
        FluidizationBlocks.register(modEventBus);
        FluidizationFluidTypes.registerWithWaterRL(modEventBus);
        FluidizationFluids.register(modEventBus);

        // Register the Deferred Register to the mod event bus so tabs get registered
        CREATIVE_MODE_TABS_REGISTER.register(modEventBus);

        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);

        // Register the item to a creative tab
        modEventBus.addListener(this::addCreative);

        // Register our mod's ForgeConfigSpec so that Forge can create and load the config file for us
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, Config.SPEC);
    }

    private void commonSetup(final FMLCommonSetupEvent event)
    {
        // Some common setup code
        LOGGER.info("HELLO FROM COMMON SETUP");

        if (Config.logDirtBlock)
            LOGGER.info("DIRT BLOCK >> {}", ForgeRegistries.BLOCKS.getKey(Blocks.DIRT));

        LOGGER.info(Config.magicNumberIntroduction + Config.magicNumber);

        Config.items.forEach((item) -> LOGGER.info("ITEM >> {}", item.toString()));
    }

    // Add the example block item to the building blocks tab
    private void addCreative(BuildCreativeModeTabContentsEvent event)
    {
        if (event.getTabKey() == CreativeModeTabs.BUILDING_BLOCKS)
            event.accept(FluidizationBlocks.FROZEN_ACID_BLOCK.get().asItem().getDefaultInstance());
    }

    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event)
    {

    }

    // You can use EventBusSubscriber to automatically register all static methods in the class annotated with @SubscribeEvent
    @Mod.EventBusSubscriber(modid = MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents
    {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event)
        {
            ItemBlockRenderTypes.setRenderLayer(FluidizationFluids.SOURCE_ACID.get(), RenderType.translucent());
            ItemBlockRenderTypes.setRenderLayer(FluidizationFluids.FLOWING_ACID.get(), RenderType.translucent());
            ItemBlockRenderTypes.setRenderLayer(FluidizationFluids.SOURCE_CRYONITE.get(), RenderType.translucent());
            ItemBlockRenderTypes.setRenderLayer(FluidizationFluids.FLOWING_CRYONITE.get(), RenderType.translucent());
            ItemBlockRenderTypes.setRenderLayer(FluidizationFluids.SOURCE_NEPTUNIUM.get(), RenderType.translucent());
            ItemBlockRenderTypes.setRenderLayer(FluidizationFluids.FLOWING_NEPTUNIUM.get(), RenderType.translucent());
            ItemBlockRenderTypes.setRenderLayer(FluidizationFluids.SOURCE_NETHERFLOW.get(), RenderType.translucent());
            ItemBlockRenderTypes.setRenderLayer(FluidizationFluids.FLOWING_NETHERFLOW.get(), RenderType.translucent());
            ItemBlockRenderTypes.setRenderLayer(FluidizationFluids.SOURCE_PLUTONIUM.get(), RenderType.translucent());
            ItemBlockRenderTypes.setRenderLayer(FluidizationFluids.FLOWING_PLUTONIUM.get(), RenderType.translucent());
            ItemBlockRenderTypes.setRenderLayer(FluidizationFluids.SOURCE_PYRONITE.get(), RenderType.translucent());
            ItemBlockRenderTypes.setRenderLayer(FluidizationFluids.FLOWING_PYRONITE.get(), RenderType.translucent());
            ItemBlockRenderTypes.setRenderLayer(FluidizationFluids.SOURCE_RADIONITE.get(), RenderType.translucent());
            ItemBlockRenderTypes.setRenderLayer(FluidizationFluids.FLOWING_RADIONITE.get(), RenderType.translucent());
            ItemBlockRenderTypes.setRenderLayer(FluidizationFluids.SOURCE_URANIUM.get(), RenderType.translucent());
            ItemBlockRenderTypes.setRenderLayer(FluidizationFluids.FLOWING_URANIUM.get(), RenderType.translucent());
//            AcidFluid.addInteractions();
        }
    }
}
