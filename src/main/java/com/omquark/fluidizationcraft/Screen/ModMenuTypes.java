package com.omquark.fluidizationcraft.screen;

import com.omquark.fluidizationcraft.FluidizationCraft;
import com.omquark.fluidizationcraft.screen.Dissolvinator.DissolvinatorMenu;
import com.omquark.fluidizationcraft.screen.FluidShooter.FluidShooterMenu;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.network.IContainerFactory;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModMenuTypes {
    public static final DeferredRegister<MenuType<?>> MENU_TYPES =
            DeferredRegister.create(BuiltInRegistries.MENU, FluidizationCraft.MODID);

    public static final DeferredHolder<MenuType<?>, MenuType<DissolvinatorMenu>> DISSOLVINATOR_MENU =
            registerMenuType("dissolvinator_menu", DissolvinatorMenu::new);

    public static final DeferredHolder<MenuType<?>, MenuType<FluidShooterMenu>> FLUID_SHOOTER_MENU =
            registerMenuType("fluid_shooter_menu", FluidShooterMenu::new);

    private static <T extends AbstractContainerMenu> DeferredHolder<MenuType<?>, MenuType<T>> registerMenuType(String name, IContainerFactory<T> factory) {
        return MENU_TYPES.register(name, () -> new MenuType<>(factory, FeatureFlags.DEFAULT_FLAGS));
    }

    public static void register(IEventBus eventBus) {
        MENU_TYPES.register(eventBus);
    }
}