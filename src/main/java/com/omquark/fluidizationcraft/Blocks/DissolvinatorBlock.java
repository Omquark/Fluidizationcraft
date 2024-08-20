package com.omquark.fluidizationcraft.Blocks;

import com.mojang.serialization.MapCodec;
import com.omquark.fluidizationcraft.Blocks.Entity.DissolvinatorBlockEntity;
import com.omquark.fluidizationcraft.Blocks.Entity.ModBlockEntities;
import net.minecraft.MethodsReturnNonnullByDefault;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.phys.BlockHitResult;
import org.jetbrains.annotations.Nullable;

import javax.annotation.ParametersAreNonnullByDefault;

@ParametersAreNonnullByDefault
@MethodsReturnNonnullByDefault
public class DissolvinatorBlock extends BaseEntityBlock {
    public static final MapCodec<DissolvinatorBlock> CODEC = simpleCodec(block -> new DissolvinatorBlock());
//    public static final VoxelShape SHAPE = Block.box(0.0, 0.0, 0.0, 16.0, 16.0, 16.0);
    public DissolvinatorBlock() {
        super(BlockBehaviour.Properties.of()
                .mapColor(MapColor.STONE)
                .sound(SoundType.METAL)
                .noOcclusion());
    }

    @Override
    protected MapCodec<? extends BaseEntityBlock> codec() {
        return CODEC;
    }

//    @Override
//    protected VoxelShape getShape(BlockState state, BlockGetter getter, BlockPos blockPos, CollisionContext collisionContext) {
//        return SHAPE;
//    }

    @Override
    protected void tick(BlockState state, ServerLevel serverLevel, BlockPos blockPos, RandomSource random) {
        super.tick(state, serverLevel, blockPos, random);
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos blockPos, BlockState state) {
        return new DissolvinatorBlockEntity(blockPos, state);
    }

    @Override
    protected InteractionResult useWithoutItem(BlockState state, Level level, BlockPos blockPos, Player player, BlockHitResult hitResult) {
        return use(state, level, blockPos, player, hitResult);
    }

    protected InteractionResult use(BlockState state, Level level, BlockPos blockPos, Player player, BlockHitResult hitResult){
        if(!level.isClientSide() && player instanceof ServerPlayer serverPlayer){
            BlockEntity blockEntity = level.getBlockEntity(blockPos);
            if(blockEntity instanceof DissolvinatorBlockEntity dissolvinatorBlockEntity){
                serverPlayer.openMenu(dissolvinatorBlockEntity, blockPos);
            } else {
                throw new IllegalStateException("The container provider is missing!");
            }
        }
        return InteractionResult.sidedSuccess(level.isClientSide());
    }

    @Override
    protected void onRemove(BlockState state, Level level, BlockPos blockPos, BlockState newState, boolean isMoving) {
        if(state.getBlock() != newState.getBlock()){
            BlockEntity blockEntity = level.getBlockEntity(blockPos);
            if(blockEntity instanceof DissolvinatorBlockEntity dissolvinatorEntity){
                dissolvinatorEntity.drops();
            }
        }
        super.onRemove(state, level, blockPos, newState, isMoving);
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState state, BlockEntityType<T> type) {
        if(level.isClientSide){
            return null;
        }
        return createTickerHelper(type, ModBlockEntities.DISSOLVINATOR_ENTITY.get(),
                (level1, pos, state1, blockEntity) -> blockEntity.tick(level1, pos, state1));
    }
}
