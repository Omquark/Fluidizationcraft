package com.omquark.fluidizationcraft.dataComponents;

import com.omquark.fluidizationcraft.FluidizationCraft;
import com.omquark.fluidizationcraft.capabilities.FluidShooterState;
import net.minecraft.core.component.DataComponentMap;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.registries.Registries;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModDataComponents {
    public static final DeferredRegister<DataComponentType<?>> COMPONENTS =
            DeferredRegister.create(Registries.DATA_COMPONENT_TYPE, FluidizationCraft.MODID);

    public static final DeferredHolder<DataComponentType<?>, DataComponentType<FluidShooterState>> FLUID_SHOOTER_STATE =
            COMPONENTS.register("fluid_shooter_state", () -> DataComponentType.<FluidShooterState>builder()
                    .persistent(FluidShooterState.CODEC)
                    .networkSynchronized(FluidShooterState.STREAM_CODEC)
                    .build()
            );

    public static void register(IEventBus bus){
        COMPONENTS.register(bus);
    }
}
