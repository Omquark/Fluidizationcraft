package com.omquark.fluidizationcraft.Items;

import com.omquark.fluidizationcraft.FluidizationCraft;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BucketItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.phys.BlockHitResult;
import org.jetbrains.annotations.Nullable;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.function.Supplier;

@ParametersAreNonnullByDefault
public class FluidizationBucket extends BucketItem implements FluidizationBaseItem {
    public FluidizationBucket(Supplier<? extends Fluid> supplier, Properties builder) {
        super(supplier, builder);
    }

    @Override
    public boolean emptyContents(@Nullable Player player, Level level, BlockPos pos, @Nullable BlockHitResult blockHitResult, @Nullable ItemStack container) {
        if (super.emptyContents(player, level, pos, blockHitResult, container)) {
            if (container != null && player != null && !player.hasInfiniteMaterials()) {
//                container.setCount(0);
                container.shrink(Integer.MAX_VALUE);
//                container.consume(Integer.MAX_VALUE, player);
            }
//            return true;
        }
        return false;
    }
}
