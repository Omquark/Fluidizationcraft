package com.omquark.fluidizationcraft.capabilities;

import com.omquark.fluidizationcraft.dataComponents.ModDataComponents;
import net.minecraft.MethodsReturnNonnullByDefault;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;

import javax.swing.text.html.Option;
import java.util.Optional;

@MethodsReturnNonnullByDefault
public class FluidShooterStateUtil {
    public static FluidShooterState get(@NotNull ItemStack gun) {
        return gun.getOrDefault(ModDataComponents.FLUID_SHOOTER_STATE, FluidShooterState.EMPTY);
    }

    public static void set(@NotNull ItemStack gun, FluidShooterState s) {
        Optional<ItemStack> input;
        Optional<ItemStack> output;

        if (s.input().isPresent() && !s.input().get().isEmpty()) {
            input = s.input();
        } else {
            input = Optional.empty();
        }
        if (s.output().isPresent() && !s.output().get().isEmpty()) {
            output = s.output();
        } else {
            output = Optional.empty();
        }

        FluidShooterState state = new FluidShooterState(input, output, s.fluidId(), s.amount());
        gun.set(ModDataComponents.FLUID_SHOOTER_STATE.get(), state);
    }
}
