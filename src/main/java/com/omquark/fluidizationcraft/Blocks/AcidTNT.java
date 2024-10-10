package com.omquark.fluidizationcraft.blocks;

import com.omquark.fluidizationcraft.util.EverythingNonNullByDefault;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.MapColor;

@EverythingNonNullByDefault
public class AcidTNT extends Block {
    public AcidTNT() {
        super(BlockBehaviour.Properties.of()
                .mapColor(MapColor.COLOR_LIGHT_GREEN)
                .sound(SoundType.WOOL));
    }

    @Override
    protected void onPlace(BlockState state1, Level level, BlockPos pos, BlockState state2, boolean bool) {
        super.onPlace(state1, level, pos, state2, bool);
    }
}
