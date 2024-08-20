package com.omquark.fluidizationcraft.Screen;

import com.omquark.fluidizationcraft.FluidizationCraft;
import com.omquark.fluidizationcraft.Screen.Dissolvinator.DissolvinatorMenu;
import com.omquark.fluidizationcraft.Screen.FluidShooter.FluidShooterMenu;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraftforge.common.extensions.IForgeMenuType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.network.IContainerFactory;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModMenuTypes {
    public static final DeferredRegister<MenuType<?>> MENU_TYPES =
            DeferredRegister.create(ForgeRegistries.MENU_TYPES, FluidizationCraft.MODID);

    public static final RegistryObject<MenuType<DissolvinatorMenu>> DISSOLVINATOR_MENU =
            registerMenuType("dissolvinator_menu", DissolvinatorMenu::new);

    public static final RegistryObject<MenuType<FluidShooterMenu>> FLUID_SHOOTER_MENU =
            registerMenuType("fluid_shooter_menu", FluidShooterMenu::new);

    private static <T extends AbstractContainerMenu> RegistryObject<MenuType<T>> registerMenuType(String name, IContainerFactory<T> factory) {
        return MENU_TYPES.register(name, () -> IForgeMenuType.create(factory));
    }

    public static void register(IEventBus eventBus) {
        MENU_TYPES.register(eventBus);
    }
}