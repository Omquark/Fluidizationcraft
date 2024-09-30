package com.omquark.fluidizationcraft.data;

import com.omquark.fluidizationcraft.FluidizationCraft;
import com.omquark.fluidizationcraft.data.items.FluidShooter;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.neoforge.capabilities.ItemCapability;

public class Capability {

    public static final ItemCapability<FluidShooter, Void> FLUID_SHOOTER_HANDLER =
            ItemCapability.createVoid(ResourceLocation.fromNamespaceAndPath(FluidizationCraft.MODID, "fluid_shooter"),
                    FluidShooter.class);
}
