package com.omquark.fluidizationcraft.blocks.blockEntity;

import com.omquark.fluidizationcraft.blocks.CausticDrumBlock;
import com.omquark.fluidizationcraft.fluids.FluidizationFluids;
import net.minecraft.MethodsReturnNonnullByDefault;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.component.DataComponentMap;
import net.minecraft.network.chat.Component;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Fluids;
import net.neoforged.neoforge.capabilities.BlockCapability;
import net.neoforged.neoforge.fluids.FluidStack;
import net.neoforged.neoforge.fluids.capability.IFluidHandler;
import net.neoforged.neoforge.fluids.capability.templates.FluidTank;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;

@MethodsReturnNonnullByDefault
public class CausticDrumBlockEntity extends BlockEntity {
    public final FluidTank tank;

    public CausticDrumBlockEntity(BlockPos pPos, BlockState pBlockState) {
        super(ModBlockEntities.CAUSTIC_DRUM_ENTITY.get(), pPos, pBlockState);
        tank = new FluidTank(1000 * 32);
    }

    public IFluidHandler getTank() {
        return tank;
    }

    public IFluidHandler getTank(@NotNull Direction direction) {
        return tank;
    }

    @Override
    protected void applyImplicitComponents(@NotNull DataComponentInput pComponentInput) {
        super.applyImplicitComponents(pComponentInput);
    }

    @Override
    protected void collectImplicitComponents(@NotNull DataComponentMap.Builder pComponents) {
        super.collectImplicitComponents(pComponents);
    }

    public static @Nullable IFluidHandler getTank(BlockEntity o, @Nullable Direction direction) {
        if (o instanceof CausticDrumBlockEntity causticEntity) {
            return direction != null ? causticEntity.getTank(direction) : causticEntity.getTank();
        }
        return null;
    }
}
