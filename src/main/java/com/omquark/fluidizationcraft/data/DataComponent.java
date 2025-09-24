package com.omquark.fluidizationcraft.data;

import com.omquark.fluidizationcraft.FluidizationCraft;
import com.omquark.fluidizationcraft.data.items.FluidShooter;
import com.omquark.fluidizationcraft.util.EverythingNonNullByDefault;
import net.minecraft.core.component.DataComponentType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

@EverythingNonNullByDefault
public class DataComponent {

    public static final DeferredRegister.DataComponents DATA_COMPONENTS_REGISTER =
            DeferredRegister.createDataComponents(FluidizationCraft.MODID);

    public static final DeferredHolder<DataComponentType<?>, DataComponentType<FluidShooter>> FLUID_SHOOTER =
            DATA_COMPONENTS_REGISTER.registerComponentType(
                    "fluid_shooter",
                    builder -> builder
                            .persistent(FluidShooter.CODEC)
                            .networkSynchronized(FluidShooter.STREAM_CODEC)
            );

    public static void register(IEventBus eventBus){
        DATA_COMPONENTS_REGISTER.register(eventBus);
    }
}
