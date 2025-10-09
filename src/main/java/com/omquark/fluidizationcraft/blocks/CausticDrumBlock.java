package com.omquark.fluidizationcraft.blocks;

import com.mojang.serialization.MapCodec;
import com.omquark.fluidizationcraft.blocks.blockEntity.CausticDrumBlockEntity;
import net.minecraft.MethodsReturnNonnullByDefault;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.neoforged.neoforge.client.extensions.common.IClientFluidTypeExtensions;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.annotation.ParametersAreNonnullByDefault;

@MethodsReturnNonnullByDefault
@ParametersAreNonnullByDefault
public class CausticDrumBlock extends BaseEntityBlock {
    public static final MapCodec<CausticDrumBlock> CODEC = simpleCodec(CausticDrumBlock::new);
    private static final DirectionProperty FACING = BlockStateProperties.FACING;
    private static final VoxelShape SHAPE = Block.box(3, 3, 1, 13, 13, 14);


    public CausticDrumBlock(Properties props) {
        super(props);
        this.registerDefaultState(this.getStateDefinition().any()
                .setValue(FACING, Direction.NORTH));

        Minecraft.getInstance().getBlockColors().register(
                (state, level, pos, tintIndex) -> {
                    if (level != null && pos != null) {
                        BlockEntity entity = level.getBlockEntity(pos);
                        if (entity instanceof CausticDrumBlockEntity drum && !drum.getTank(null).getFluidInTank(0).isEmpty()) {
                            var fluidExtensions = IClientFluidTypeExtensions.of(drum.getTank(null).getFluidInTank(0).getFluid());
                            return fluidExtensions.getTintColor();
                        }
                    }
                    return 0xFFFFFF;
                }
        );
    }

    @Override
    public @Nullable BlockState getStateForPlacement(BlockPlaceContext pContext) {
        return this.defaultBlockState()
                .setValue(FACING, pContext.getHorizontalDirection().getOpposite());
    }

    @NotNull
    @Override
    protected MapCodec<? extends BaseEntityBlock> codec() {
        return CODEC;
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder) {
        pBuilder.add(FACING);
    }

    @Override
    public @Nullable BlockEntity newBlockEntity(BlockPos pPos, BlockState pState) {
        return new CausticDrumBlockEntity(pPos, pState);
    }

    @Override
    protected BlockState mirror(BlockState pState, Mirror pMirror) {
        return pState.rotate(pMirror.getRotation(pState.getValue(FACING)));
    }

    @Override
    protected BlockState rotate(BlockState pState, Rotation pRotation) {
        return pState.setValue(FACING, pRotation.rotate(pState.getValue(FACING)));
    }

    @Override
    protected RenderShape getRenderShape(BlockState pState) {
        return RenderShape.MODEL;
    }

    @Override
    protected VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return SHAPE;
    }

    @Override
    protected InteractionResult useWithoutItem(BlockState pState, Level pLevel, BlockPos pPos, Player pPlayer, BlockHitResult pHitResult) {
        return use(pState, pLevel, pPos, pPlayer, pHitResult);
    }

    protected InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, BlockHitResult hitResult) {
        player.displayClientMessage(Component.literal("Used CausticDrum"), false);
        BlockEntity entity = level.getBlockEntity(pos);
        if (entity instanceof CausticDrumBlockEntity causticDrumBlockEntity) {
            player.displayClientMessage(Component.literal("Entity exists and is expected type"), false);
            player.displayClientMessage(Component.literal("Tank: " + causticDrumBlockEntity.getTank(null).getTankCapacity(0)), false);
        } else {
            player.displayClientMessage(Component.literal("Entity does not exist or is not of expected type"), false);
        }
        return InteractionResult.SUCCESS;
    }

    @Override
    public @Nullable <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level pLevel, BlockState pState, BlockEntityType<T> pBlockEntityType) {
        return super.getTicker(pLevel, pState, pBlockEntityType);
    }
}
