package com.omquark.fluidizationcraft.blocks;

import com.mojang.serialization.MapCodec;
import com.omquark.fluidizationcraft.damageTypes.FluidizationDamageTypes;
import com.omquark.fluidizationcraft.util.EverythingNonNullByDefault;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.stats.Stats;
import net.minecraft.tags.EnchantmentTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.MapColor;

import javax.annotation.Nullable;

@EverythingNonNullByDefault
public class AcidFrozen extends IceBlock {
    public static final MapCodec<AcidFrozen> CODEC = simpleCodec(block -> new AcidFrozen());

    @Override
    public MapCodec<? extends AcidFrozen> codec() {
        return CODEC;
    }

    public static BlockState meltsInto() {
        return FluidizationBlocks.ACID_BLOCK.get().defaultBlockState();
    }

    public AcidFrozen() {
        super(BlockBehaviour.Properties.of()
                .mapColor(MapColor.COLOR_LIGHT_GREEN)
                .sound(SoundType.GLASS)
                .friction(.95f)
                .isViewBlocking((blockState, blockGetter, blockPos) -> false)
                .noOcclusion()
                .randomTicks());
    }

    @Override
    public void stepOn(Level level, BlockPos pos, BlockState state, Entity entity) {
        entity.hurt(level.damageSources().source(FluidizationDamageTypes.ACID_DAMAGE), 1f);
        super.stepOn(level, pos, state, entity);
    }

    @Override
    public void playerDestroy(Level pLevel, Player pPlayer, BlockPos pPos, BlockState pState, @Nullable BlockEntity pTe, ItemStack pStack) {
        pPlayer.awardStat(Stats.BLOCK_MINED.get(this));
        pPlayer.causeFoodExhaustion(0.005f);
        if (!EnchantmentHelper.hasTag(pStack, EnchantmentTags.PREVENTS_ICE_MELTING)) {
            if (pLevel.dimensionType().ultraWarm()) {
                pLevel.removeBlock(pPos, false);
                return;
            }

            BlockState blockstate = pLevel.getBlockState(pPos.below());
            if (blockstate.blocksMotion() || blockstate.liquid()) {
                pLevel.setBlockAndUpdate(pPos, meltsInto());
            }
        }
    }


    @Override
    protected void randomTick(BlockState pState, ServerLevel pLevel, BlockPos pPos, RandomSource pRandom) {
        if (!pLevel.getBiome(pPos).value().coldEnoughToSnow(pPos) && pRandom.nextIntBetweenInclusive(0, 99) < 20) {
            melt(pState, pLevel, pPos);
            return;
        }
        super.randomTick(pState, pLevel, pPos, pRandom);
    }

    @Override
    protected void melt(BlockState pState, Level pLevel, BlockPos pPos) {
        if (pLevel.dimensionType().ultraWarm()) {
            pLevel.removeBlock(pPos, false);
        } else {
            pLevel.setBlockAndUpdate(pPos, meltsInto());
            pLevel.neighborChanged(pPos, meltsInto().getBlock(), pPos);
        }
    }
}
