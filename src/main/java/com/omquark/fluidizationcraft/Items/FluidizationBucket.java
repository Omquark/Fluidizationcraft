package com.omquark.fluidizationcraft.Items;

import com.omquark.fluidizationcraft.util.EverythingNonNullByDefault;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BucketItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.phys.BlockHitResult;
import org.jetbrains.annotations.Nullable;

@EverythingNonNullByDefault
public class FluidizationBucket extends BucketItem {
    public FluidizationBucket(Fluid fluid, Properties builder) {
        super(fluid, builder);
    }

    @Override
    public boolean emptyContents(@Nullable Player player, Level level, BlockPos pos, @Nullable BlockHitResult blockHitResult, @Nullable ItemStack container) {
        if (super.emptyContents(player, level, pos, blockHitResult, container)) {
            if (container != null && player != null && !player.hasInfiniteMaterials()) {
                container.shrink(Integer.MAX_VALUE);
                if (level.isClientSide()) {
                    player.displayClientMessage(
                            Component.literal("Your bucket dissolved! You should use a vial to handle caustic fluids."),
                            false

                    );
                }
            }
        }
        return false;
    }

    public Fluid getFluid() {
        return this.content;
    }
}
