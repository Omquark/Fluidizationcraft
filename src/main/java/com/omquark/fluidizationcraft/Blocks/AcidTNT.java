package com.omquark.fluidizationcraft.blocks;

import com.mojang.serialization.MapCodec;
import com.omquark.fluidizationcraft.FluidizationCraft;
import net.minecraft.MethodsReturnNonnullByDefault;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.MapColor;
import org.jetbrains.annotations.Nullable;

import javax.annotation.ParametersAreNonnullByDefault;

@ParametersAreNonnullByDefault
@MethodsReturnNonnullByDefault
public class AcidTNT extends Block {
    public AcidTNT() {
        super(BlockBehaviour.Properties.of()
                .mapColor(MapColor.COLOR_LIGHT_GREEN)
                .sound(SoundType.WOOL));
    }

    @Override
    protected void onPlace(BlockState state1, Level level, BlockPos pos, BlockState state2, boolean bool) {
        super.onPlace(state1, level, pos, state2, bool);
        FluidizationCraft.LOGGER.debug("state1 {}", state1.getBlock().toString());
        FluidizationCraft.LOGGER.debug("state2 {}", state2.getBlock().toString());
    }
}
