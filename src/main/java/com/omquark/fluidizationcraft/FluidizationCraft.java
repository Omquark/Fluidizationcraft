package com.omquark.fluidizationcraft;

import com.mojang.logging.LogUtils;
import com.omquark.fluidizationcraft.blocks.DissolvinatorBlock;
import com.omquark.fluidizationcraft.blocks.blockEntity.DissolvinatorBlockEntity;
import com.omquark.fluidizationcraft.blocks.blockEntity.ModBlockEntities;
import com.omquark.fluidizationcraft.blocks.FluidizationBlocks;
import com.omquark.fluidizationcraft.client.ModArrowRenderer;
import com.omquark.fluidizationcraft.entity.ModEntities;
import com.omquark.fluidizationcraft.fluids.FluidizationFluidTypes;
import com.omquark.fluidizationcraft.fluids.FluidizationFluids;
import com.omquark.fluidizationcraft.Items.FluidizationItems;
import com.omquark.fluidizationcraft.screen.Dissolvinator.DissolvinatorMenu;
import com.omquark.fluidizationcraft.screen.Dissolvinator.DissolvinatorScreen;
import com.omquark.fluidizationcraft.screen.FluidShooter.FluidShooterScreen;
import com.omquark.fluidizationcraft.screen.ModMenuTypes;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.*;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.*;
import net.minecraft.world.level.block.Blocks;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.capabilities.Capabilities;
import net.neoforged.neoforge.capabilities.IBlockCapabilityProvider;
import net.neoforged.neoforge.capabilities.RegisterCapabilitiesEvent;
import net.neoforged.neoforge.client.event.RegisterMenuScreensEvent;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import net.neoforged.neoforge.event.server.ServerStartingEvent;
import net.neoforged.neoforge.fluids.capability.wrappers.FluidBucketWrapper;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import org.slf4j.Logger;

import java.util.List;
import java.util.Map;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(FluidizationCraft.MODID)
public class FluidizationCraft
{
    public static final String MODID = "fluidizationcraft";
    public static final Logger LOGGER = LogUtils.getLogger();
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS_REGISTER = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, MODID);

    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> FLUIDIZATION_CRAFT_TAB = CREATIVE_MODE_TABS_REGISTER.register("example_tab", () -> CreativeModeTab.builder()
            .withTabsBefore(CreativeModeTabs.COMBAT)
            .icon(() -> FluidizationBlocks.FROZEN_ACID_BLOCK.get().asItem().getDefaultInstance())
            .displayItems((parameters, output) -> {
                output.accept(FluidizationBlocks.FROZEN_ACID_BLOCK.get());
                output.accept(FluidizationBlocks.FROZEN_CRYONITE_BLOCK.get());
                output.accept(FluidizationBlocks.DISSOLVINATOR_BLOCK.get());
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
                output.accept(FluidizationItems.GUN_CRYO.get());
                output.accept(FluidizationBlocks.ALUMINUM_ORE_BLOCK.get());
//                output.accept(FluidizationBlocks.ARGENTITE_ORE_BLOCK.get());
                output.accept(FluidizationBlocks.LEAD_ORE_BLOCK.get());
                output.accept(FluidizationBlocks.NEPTUNIUM_ORE_BLOCK.get());
                output.accept(FluidizationBlocks.PLUTONIUM_ORE_BLOCK.get());
                output.accept(FluidizationBlocks.RADIONITE_ORE_BLOCK.get());
                output.accept(FluidizationBlocks.TIN_ORE_BLOCK.get());
                output.accept(FluidizationBlocks.URANIUM_ORE_BLOCK.get());
                output.accept(FluidizationBlocks.REINFORCED_GLASS.get());
                output.accept(FluidizationBlocks.ACID_TNT.get());
                output.accept(FluidizationItems.RAW_ALUMINUM.get());
                output.accept(FluidizationItems.RAW_LEAD.get());
                output.accept(FluidizationItems.RAW_NEPTUNIUM.get());
                output.accept(FluidizationItems.RAW_PLUTONIUM.get());
                output.accept(FluidizationItems.RAW_RADIONITE.get());
                output.accept(FluidizationItems.RAW_TIN.get());
                output.accept(FluidizationItems.RAW_URANIUM.get());
                output.accept(FluidizationItems.INGOT_ALUMINUM.get());
                output.accept(FluidizationItems.INGOT_LEAD.get());
                output.accept(FluidizationItems.INGOT_NEPTUNIUM.get());
                output.accept(FluidizationItems.INGOT_PLUTONIUM.get());
                output.accept(FluidizationItems.INGOT_RADIONITE.get());
                output.accept(FluidizationItems.INGOT_TIN.get());
                output.accept(FluidizationItems.INGOT_URANIUM.get());

            })
            .title(Component.literal("Fluidization Craft"))
            .build());

    public FluidizationCraft(IEventBus modEventBus, ModContainer modContainer)
    {

//        IEventBus modEventBus = NeoForge.EVENT_BUS;
//        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
//        modEventBus.register(DataGeneratorHandler.class);

        modEventBus.addListener(this::commonSetup);

        FluidizationBlocks.register(modEventBus);
        FluidizationItems.register(modEventBus);
        FluidizationFluidTypes.registerWithWaterRL(modEventBus);
        FluidizationFluids.register(modEventBus);
        ModEntities.register(modEventBus);
        ModBlockEntities.register(modEventBus);
        ModMenuTypes.register(modEventBus);

        // Register the Deferred Register to the mod event bus so tabs get registered
        CREATIVE_MODE_TABS_REGISTER.register(modEventBus);

        NeoForge.EVENT_BUS.register(this);

        // Register the item to a creative tab
        modEventBus.addListener(this::addCreative);

        // Register our mod's ForgeConfigSpec so that Forge can create and load the config file for us
//        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, Config.SPEC);
        modContainer.registerConfig(ModConfig.Type.COMMON, Config.SPEC);

    }

    private void commonSetup(final FMLCommonSetupEvent event)
    {
        // Some common setup code
        LOGGER.info("HELLO FROM COMMON SETUP");

        if (Config.logDirtBlock)
            LOGGER.info("DIRT BLOCK >> {}", BuiltInRegistries.BLOCK.getKey(Blocks.DIRT));

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
    @EventBusSubscriber(modid = MODID, bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents
    {

        @SubscribeEvent
        public static void registerCapabilities(RegisterCapabilitiesEvent event){
            event.registerBlockEntity(
                    DissolvinatorBlockEntity.ITEM_HANDLER_BLOCK,
                    ModBlockEntities.DISSOLVINATOR_ENTITY.get(),
                    (entity, context) -> ((DissolvinatorBlockEntity) entity).getItemStackHandler()
            );
        }

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
            EntityRenderers.register(ModEntities.ACID_PROJECTILE.get(),
                    context -> new ModArrowRenderer(context, "textures/entity/plasmaball.png"));
            EntityRenderers.register(ModEntities.CRYO_PROJECTILE.get(),
                    context -> new ModArrowRenderer(context, "textures/entity/railgunbolt.png"));
            FluidizationFluids.createInteractions();
        }

        @SubscribeEvent
        public static void registerScreen(RegisterMenuScreensEvent event){
            event.register(ModMenuTypes.DISSOLVINATOR_MENU.get(), DissolvinatorScreen::new);
            event.register(ModMenuTypes.FLUID_SHOOTER_MENU.get(), FluidShooterScreen::new);
        }
    }
}
