package com.omquark.fluidizationcraft.Items;

import com.omquark.fluidizationcraft.fluids.ModFluid;
import com.omquark.fluidizationcraft.util.EverythingNonNullByDefault;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BucketItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ItemUtils;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.BucketPickup;
import net.minecraft.world.level.block.LiquidBlockContainer;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;
import org.jetbrains.annotations.Nullable;

import java.util.function.Supplier;

@EverythingNonNullByDefault
public class ModVial extends BucketItem {
    private final Supplier<? extends Fluid> fluidSupplier;

    public ModVial(Supplier<? extends Fluid> supplier, Properties builder) {
        super(supplier.get(), builder);
        this.fluidSupplier = supplier;
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        ItemStack itemstack = player.getItemInHand(hand);
        BlockHitResult blockhitresult = getPlayerPOVHitResult(level, player, this.fluidSupplier.get() == Fluids.EMPTY ? ClipContext.Fluid.SOURCE_ONLY : ClipContext.Fluid.NONE);
//        InteractionResultHolder<ItemStack> ret = net.minecraftforge.event.ForgeEventFactory.onBucketUse(player, level, itemstack, blockhitresult);
//        if (ret != null) return ret;
        if (blockhitresult.getType() == HitResult.Type.MISS) {
            return InteractionResultHolder.pass(itemstack);
        } else if (blockhitresult.getType() != HitResult.Type.BLOCK) {
            return InteractionResultHolder.pass(itemstack);
        } else {
            BlockPos blockpos = blockhitresult.getBlockPos();
            net.minecraft.core.Direction direction = blockhitresult.getDirection();
            BlockPos blockpos1 = blockpos.relative(direction);
            if (!level.mayInteract(player, blockpos) || !player.mayUseItemAt(blockpos1, direction, itemstack)) {
                return InteractionResultHolder.fail(itemstack);
            } else if (this.fluidSupplier.get() == Fluids.EMPTY) {
                BlockState blockstate1 = level.getBlockState(blockpos);
                if (blockstate1.getBlock() instanceof BucketPickup bucketpickup) {
                    if(blockstate1.getBlock() == Blocks.WATER)
                        return InteractionResultHolder.fail(itemstack);
                    ItemStack itemstack2 = bucketpickup.pickupBlock(player, level, blockpos, blockstate1);

                    if (!itemstack2.isEmpty()) {
                        player.awardStat(Stats.ITEM_USED.get(this));
                        bucketpickup.getPickupSound(blockstate1).ifPresent(p_150709_ -> player.playSound(p_150709_, 1.0F, 1.0F));
                        itemstack2 = swapBucketForVial(itemstack2);
                        ItemStack itemstack1 = ItemUtils.createFilledResult(itemstack, player, itemstack2);
                        itemstack1 = swapBucketForVial(itemstack1);

                        return InteractionResultHolder.consume(itemstack1);
                    }
                }

                return InteractionResultHolder.fail(itemstack);
            } else {
                BlockState blockstate = level.getBlockState(blockpos);
                BlockPos blockpos2 = canBlockContainFluid(player, level, blockpos, blockstate) ? blockpos : blockpos1;
                if (this.emptyContents(player, level, blockpos2, blockhitresult, itemstack)) {
                    this.checkExtraContent(player, level, itemstack, blockpos2);
                    if (player instanceof ServerPlayer) {
                        CriteriaTriggers.PLACED_BLOCK.trigger((ServerPlayer) player, blockpos2, itemstack);
                    }

                    player.awardStat(Stats.ITEM_USED.get(this));
                    player.addItem(new ItemStack(FluidizationItems.VIAL_EMPTY.get()));
                    player.getMainHandItem().shrink(1);
                    return InteractionResultHolder.consume(itemstack);
                } else {
                    return InteractionResultHolder.fail(itemstack);
                }
            }
        }
    }

    public static ItemStack swapBucketForVial(ItemStack itemStack){
        ItemStack stack = itemStack;
        if (itemStack.getItem() instanceof FluidizationBucket) {
            if (((FluidizationBucket) itemStack.getItem()).getFluid() instanceof ModFluid) {
                FluidizationBucket bucket = (FluidizationBucket) itemStack.getItem();
                ModFluid fluid = (ModFluid) bucket.getFluid();
                stack = new ItemStack(fluid.getVial(), itemStack.getCount());
            }
        }
        return stack;
    }

    @Override
    protected boolean canBlockContainFluid(@Nullable Player player, Level worldIn, BlockPos posIn, BlockState blockstate) {
        if (blockstate.getBlock() instanceof LiquidBlockContainer liquid) {
            if (liquid.canPlaceLiquid(null, worldIn, posIn, blockstate, this.fluidSupplier.get())) {
                return true;
            }
        }
        return false;
    }
}