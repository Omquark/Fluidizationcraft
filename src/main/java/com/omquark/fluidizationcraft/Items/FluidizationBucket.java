package com.omquark.fluidizationcraft.Items;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BucketItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.phys.BlockHitResult;
import org.jetbrains.annotations.Nullable;

import javax.annotation.ParametersAreNonnullByDefault;

@ParametersAreNonnullByDefault
public class FluidizationBucket extends BucketItem {
    public FluidizationBucket(Fluid fluid, Properties builder) {
        super(fluid, builder);
    }

    @Override
    public boolean emptyContents(@Nullable Player player, Level level, BlockPos pos, @Nullable BlockHitResult blockHitResult, @Nullable ItemStack container) {
        if (super.emptyContents(player, level, pos, blockHitResult, container)) {
            if (container != null && player != null && !player.hasInfiniteMaterials()) {
                container.shrink(Integer.MAX_VALUE);
            }
        }
        return false;
    }

    public Fluid getFluid(){
        return this.content;
    }
}
