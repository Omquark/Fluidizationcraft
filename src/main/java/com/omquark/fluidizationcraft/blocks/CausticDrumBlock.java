package com.omquark.fluidizationcraft.blocks;

import com.mojang.serialization.MapCodec;
import com.omquark.fluidizationcraft.blocks.blockEntity.CausticDrumBlockEntity;
import net.minecraft.MethodsReturnNonnullByDefault;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.annotation.ParametersAreNonnullByDefault;

@MethodsReturnNonnullByDefault
@ParametersAreNonnullByDefault
public class CausticDrumBlock extends BaseEntityBlock {
    public static final MapCodec<CausticDrumBlock> CODEC = simpleCodec(CausticDrumBlock::new);

    public CausticDrumBlock(Properties props) {
        super(props);
    }

    @NotNull
    @Override
    protected MapCodec<? extends BaseEntityBlock> codec() {
        return CODEC;
    }

    @Override
    public @Nullable BlockEntity newBlockEntity(BlockPos pPos, BlockState pState) {
        return new CausticDrumBlockEntity(pPos, pState);
    }

    @Override
    protected RenderShape getRenderShape(BlockState pState) {

        return super.getRenderShape(pState);
    }

    @Override
    protected InteractionResult useWithoutItem(BlockState pState, Level pLevel, BlockPos pPos, Player pPlayer, BlockHitResult pHitResult) {
        return use(pState, pLevel, pPos, pPlayer, pHitResult);
    }

    protected InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, BlockHitResult hitResult){
        player.displayClientMessage(Component.literal("Used CausticDrum"), false);
        BlockEntity entity = level.getBlockEntity(pos);
        if(entity instanceof CausticDrumBlockEntity causticDrumBlockEntity){
            player.displayClientMessage(Component.literal("Entity exists and is expected type"), false);
            player.displayClientMessage(Component.literal("Tank: " + causticDrumBlockEntity.getTank().getTankCapacity(0)), false);
        }
        else{
            player.displayClientMessage(Component.literal("Entity does not exist or is not of expected type"), false);
        }
        return InteractionResult.SUCCESS;
    }

    @Override
    public @Nullable <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level pLevel, BlockState pState, BlockEntityType<T> pBlockEntityType) {
        return super.getTicker(pLevel, pState, pBlockEntityType);
    }
}
