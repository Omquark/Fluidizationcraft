package com.omquark.fluidizationcraft;

import com.mojang.logging.LogUtils;
import com.omquark.fluidizationcraft.biomes.AcidWastes;
import com.omquark.fluidizationcraft.blocks.ModBlocks;
import com.omquark.fluidizationcraft.blocks.blockEntity.DissolvinatorBlockEntity;
import com.omquark.fluidizationcraft.blocks.blockEntityRenderer.CausticDrumRenderer;
import com.omquark.fluidizationcraft.dataComponents.ModDataComponents;
import com.omquark.fluidizationcraft.blocks.blockEntity.ModBlockEntities;
import com.omquark.fluidizationcraft.client.ModArrowRenderer;
import com.omquark.fluidizationcraft.data.ModRecipeDataProvider;
import com.omquark.fluidizationcraft.data.ModRecipeSerializerProvider;
import com.omquark.fluidizationcraft.data.fluid.interactions.FluidInteractionLoader;
import com.omquark.fluidizationcraft.entity.ModEntities;
import com.omquark.fluidizationcraft.fluids.ModFluidTypes;
import com.omquark.fluidizationcraft.fluids.ModFluids;
import com.omquark.fluidizationcraft.items.ModItems;
import com.omquark.fluidizationcraft.region.AcidWastesRegion;
import com.omquark.fluidizationcraft.screen.Dissolvinator.DissolvinatorScreen;
import com.omquark.fluidizationcraft.screen.FluidShooter.FluidShooterScreen;
import com.omquark.fluidizationcraft.screen.ModMenuTypes;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.*;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.*;
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
import net.neoforged.neoforge.capabilities.RegisterCapabilitiesEvent;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.neoforge.client.event.RegisterMenuScreensEvent;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.AddReloadListenerEvent;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import net.neoforged.neoforge.event.server.ServerStartingEvent;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import org.slf4j.Logger;
import terrablender.api.Regions;
import terrablender.api.SurfaceRuleManager;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(FluidizationCraft.MODID)
public class FluidizationCraft {
    public static final String MODID = "fluidizationcraft";
    public static final Logger LOGGER = LogUtils.getLogger();
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS_REGISTER = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, MODID);

    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> FLUIDIZATION_CRAFT_TAB = CREATIVE_MODE_TABS_REGISTER.register("example_tab", () -> CreativeModeTab.builder()
            .withTabsBefore(CreativeModeTabs.COMBAT)
            .icon(() -> ModBlocks.FROZEN_ACID_BLOCK.get().asItem().getDefaultInstance())
            .displayItems((parameters, output) -> {
                output.accept(ModBlocks.FROZEN_ACID_BLOCK.get());
                output.accept(ModBlocks.FROZEN_CRYONITE_BLOCK.get());
                output.accept(ModBlocks.ACID_BARRIER.get());
                output.accept(ModBlocks.ACID_TANK.get());
                output.accept(ModBlocks.DISSOLVINATOR_BLOCK.get());
                output.accept(ModFluids.SOURCE_ACID.get().getBucket());
                output.accept(ModFluids.SOURCE_CRYONITE.get().getBucket());
                output.accept(ModFluids.SOURCE_NEPTUNIUM.get().getBucket());
                output.accept(ModFluids.SOURCE_NETHERFLOW.get().getBucket());
                output.accept(ModFluids.SOURCE_PLUTONIUM.get().getBucket());
                output.accept(ModFluids.SOURCE_PYRONITE.get().getBucket());
                output.accept(ModFluids.SOURCE_RADIONITE.get().getBucket());
                output.accept(ModFluids.SOURCE_URANIUM.get().getBucket());
                output.accept(ModItems.VIAL_EMPTY.get());
                output.accept(ModItems.VIAL_ACID.get());
                output.accept(ModItems.VIAL_CRYONITE.get());
                output.accept(ModItems.VIAL_NEPTUNIUM.get());
                output.accept(ModItems.VIAL_NETHERFLOW.get());
                output.accept(ModItems.VIAL_PLUTONIUM.get());
                output.accept(ModItems.VIAL_PYRONITE.get());
                output.accept(ModItems.VIAL_RADIONITE.get());
                output.accept(ModItems.VIAL_URANIUM.get());
                output.accept(ModItems.CELL_ACID.get());
                output.accept(ModItems.GUN_ACID.get());
                output.accept(ModItems.GUN_CRYO.get());
                output.accept(ModBlocks.ALUMINUM_ORE_BLOCK.get());
//                output.accept(FluidizationBlocks.ARGENTITE_ORE_BLOCK.get());
                output.accept(ModBlocks.LEAD_ORE_BLOCK.get());
                output.accept(ModBlocks.NEPTUNIUM_ORE_BLOCK.get());
                output.accept(ModBlocks.PLUTONIUM_ORE_BLOCK.get());
                output.accept(ModBlocks.RADIONITE_ORE_BLOCK.get());
                output.accept(ModBlocks.TIN_ORE_BLOCK.get());
                output.accept(ModBlocks.URANIUM_ORE_BLOCK.get());
                output.accept(ModBlocks.TRANSPARENT_ALUMINUM.get());
                output.accept(ModBlocks.ACID_TNT.get());
                output.accept(ModBlocks.CAUSTIC_DRUM_BLOCK.get());
                output.accept(ModItems.DUST_IRON.get());
                output.accept(ModItems.DUST_GOLD.get());
                output.accept(ModItems.DUST_COPPER.get());
                output.accept(ModItems.RAW_ALUMINUM.get());
                output.accept(ModItems.RAW_LEAD.get());
                output.accept(ModItems.RAW_NEPTUNIUM.get());
                output.accept(ModItems.RAW_PLUTONIUM.get());
                output.accept(ModItems.RAW_RADIONITE.get());
                output.accept(ModItems.RAW_TIN.get());
                output.accept(ModItems.RAW_URANIUM.get());
                output.accept(ModItems.DUST_ALUMINUM.get());
                output.accept(ModItems.DUST_LEAD.get());
                output.accept(ModItems.DUST_NEPTUNIUM.get());
                output.accept(ModItems.DUST_PLUTONIUM.get());
                output.accept(ModItems.DUST_RADIONITE.get());
                output.accept(ModItems.DUST_TIN.get());
                output.accept(ModItems.DUST_URANIUM.get());
                output.accept(ModItems.INGOT_ALUMINUM.get());
                output.accept(ModItems.INGOT_LEAD.get());
                output.accept(ModItems.INGOT_NEPTUNIUM.get());
                output.accept(ModItems.INGOT_PLUTONIUM.get());
                output.accept(ModItems.INGOT_RADIONITE.get());
                output.accept(ModItems.INGOT_TIN.get());
                output.accept(ModItems.INGOT_URANIUM.get());
                output.accept(ModItems.GOOP_ACID.get());

            })
            .title(Component.literal("Fluidization Craft"))
            .build());

    public FluidizationCraft(IEventBus modEventBus, ModContainer modContainer) {

        modEventBus.addListener(this::commonSetup);

        ModBlocks.register(modEventBus);
        ModItems.register(modEventBus);
        ModFluidTypes.registerWithWaterRL(modEventBus);
        ModFluids.register(modEventBus);
        ModEntities.register(modEventBus);
        ModBlockEntities.register(modEventBus);
        ModMenuTypes.register(modEventBus);
        ModRecipeDataProvider.register(modEventBus);
        ModRecipeSerializerProvider.register(modEventBus);
        ModDataComponents.register(modEventBus);


        // Register the Deferred Register to the mod event bus so tabs get registered
        CREATIVE_MODE_TABS_REGISTER.register(modEventBus);

        NeoForge.EVENT_BUS.register(this);

        modEventBus.addListener(this::addCreative);
        modEventBus.addListener(this::registerCapabilities);

        // Register our mod's ForgeConfigSpec so that Forge can create and load the config file for us
//        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, Config.SPEC);
        modContainer.registerConfig(ModConfig.Type.COMMON, Config.SPEC);

    }

    public void registerCapabilities(final RegisterCapabilitiesEvent event){
        event.registerBlockEntity(Capabilities.FluidHandler.BLOCK, ModBlockEntities.CAUSTIC_DRUM_ENTITY.get(), (entity, dir) -> entity.getTank(dir));
        event.registerBlockEntity(Capabilities.FluidHandler.BLOCK, ModBlockEntities.DISSOLVINATOR_ENTITY.get(), DissolvinatorBlockEntity::getTank);
    }

    private void commonSetup(final FMLCommonSetupEvent event) {

        event.enqueueWork(() -> {
            Regions.register(new AcidWastesRegion(ResourceLocation.fromNamespaceAndPath(MODID, "acid_waste"), 2));

            SurfaceRuleManager.addSurfaceRules(SurfaceRuleManager.RuleCategory.OVERWORLD, MODID, AcidWastes.makeRules());
        });
    }

    // Add the example block item to the building blocks tab
    private void addCreative(BuildCreativeModeTabContentsEvent event) {
        if (event.getTabKey() == CreativeModeTabs.BUILDING_BLOCKS)
            event.accept(ModBlocks.FROZEN_ACID_BLOCK.get().asItem().getDefaultInstance());
    }

    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {

    }

    // You can use EventBusSubscriber to automatically register all static methods in the class annotated with @SubscribeEvent
    @EventBusSubscriber(modid = MODID, bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents {

        @SubscribeEvent
        public static void registerEntityRenderers(EntityRenderersEvent.RegisterRenderers event){
            event.registerBlockEntityRenderer(ModBlockEntities.CAUSTIC_DRUM_ENTITY.get(), CausticDrumRenderer::new);
        }

        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event) {
            ItemBlockRenderTypes.setRenderLayer(ModFluids.SOURCE_ACID.get(), RenderType.translucent());
            ItemBlockRenderTypes.setRenderLayer(ModFluids.FLOWING_ACID.get(), RenderType.translucent());
            ItemBlockRenderTypes.setRenderLayer(ModFluids.SOURCE_CRYONITE.get(), RenderType.translucent());
            ItemBlockRenderTypes.setRenderLayer(ModFluids.FLOWING_CRYONITE.get(), RenderType.translucent());
            ItemBlockRenderTypes.setRenderLayer(ModFluids.SOURCE_NEPTUNIUM.get(), RenderType.translucent());
            ItemBlockRenderTypes.setRenderLayer(ModFluids.FLOWING_NEPTUNIUM.get(), RenderType.translucent());
            ItemBlockRenderTypes.setRenderLayer(ModFluids.SOURCE_NETHERFLOW.get(), RenderType.translucent());
            ItemBlockRenderTypes.setRenderLayer(ModFluids.FLOWING_NETHERFLOW.get(), RenderType.translucent());
            ItemBlockRenderTypes.setRenderLayer(ModFluids.SOURCE_PLUTONIUM.get(), RenderType.translucent());
            ItemBlockRenderTypes.setRenderLayer(ModFluids.FLOWING_PLUTONIUM.get(), RenderType.translucent());
            ItemBlockRenderTypes.setRenderLayer(ModFluids.SOURCE_PYRONITE.get(), RenderType.translucent());
            ItemBlockRenderTypes.setRenderLayer(ModFluids.FLOWING_PYRONITE.get(), RenderType.translucent());
            ItemBlockRenderTypes.setRenderLayer(ModFluids.SOURCE_RADIONITE.get(), RenderType.translucent());
            ItemBlockRenderTypes.setRenderLayer(ModFluids.FLOWING_RADIONITE.get(), RenderType.translucent());
            ItemBlockRenderTypes.setRenderLayer(ModFluids.SOURCE_URANIUM.get(), RenderType.translucent());
            ItemBlockRenderTypes.setRenderLayer(ModFluids.FLOWING_URANIUM.get(), RenderType.translucent());
            EntityRenderers.register(ModEntities.ACID_PROJECTILE.get(),
                    context -> new ModArrowRenderer(context, "textures/entity/plasmaball.png"));
            EntityRenderers.register(ModEntities.CRYO_PROJECTILE.get(),
                    context -> new ModArrowRenderer(context, "textures/entity/railgunbolt.png"));
            ModFluids.createInteractions();
        }

        @SubscribeEvent
        public static void registerScreen(RegisterMenuScreensEvent event) {
            event.register(ModMenuTypes.DISSOLVINATOR_MENU.get(), DissolvinatorScreen::new);
            event.register(ModMenuTypes.FLUID_SHOOTER_MENU.get(), FluidShooterScreen::new);
        }
    }

    @EventBusSubscriber(modid = MODID, bus = EventBusSubscriber.Bus.GAME)
    public static class ModReloadListeners {
        @SubscribeEvent
        public static void onAddReloadListeners(AddReloadListenerEvent event) {
            event.addListener(FluidInteractionLoader.INSTANCE);
        }
    }
}
