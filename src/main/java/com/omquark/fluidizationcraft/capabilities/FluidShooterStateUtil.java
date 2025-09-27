package com.omquark.fluidizationcraft.capabilities;

import com.omquark.fluidizationcraft.dataComponents.ModDataComponents;
import net.minecraft.MethodsReturnNonnullByDefault;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;

@MethodsReturnNonnullByDefault
public class FluidShooterStateUtil {
    public static FluidShooterState get(@NotNull ItemStack gun){
        return gun.getOrDefault(ModDataComponents.FLUID_SHOOTER_STATE, FluidShooterState.EMPTY);
    }

    public static void set(@NotNull ItemStack gun, FluidShooterState s){
        gun.set(ModDataComponents.FLUID_SHOOTER_STATE.get(), s);
    }
}
