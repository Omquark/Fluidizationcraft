package com.omquark.fluidizationcraft.Items;

import com.omquark.fluidizationcraft.FluidizationCraft;
import net.minecraft.MethodsReturnNonnullByDefault;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BucketItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.material.Fluid;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.function.Supplier;

@ParametersAreNonnullByDefault
@MethodsReturnNonnullByDefault
public class FluidizationVial extends BucketItem implements FluidizationBaseItem {

    public FluidizationVial(Supplier<? extends Fluid> supplier, Properties builder) {
        super(supplier, builder);
    }

    //TODO: Override the result and add a stack if gathering an fluid of that vial, or empty vial when emptying.
    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        InteractionResultHolder<ItemStack> resultHolder = super.use(level, player, hand);
        FluidizationCraft.LOGGER.debug("result object = {}", resultHolder.getObject().getItem().getName(resultHolder.getObject()));
        if(resultHolder.getResult() == InteractionResult.SUCCESS){
            resultHolder.getObject().shrink(1);
        }
        return resultHolder;
    }
}