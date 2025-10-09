package com.omquark.fluidizationcraft.blocks.blockEntityRenderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import com.omquark.fluidizationcraft.blocks.blockEntity.CausticDrumBlockEntity;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.core.Direction;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.neoforged.neoforge.fluids.FluidStack;

public class CausticDrumRenderer implements BlockEntityRenderer<CausticDrumBlockEntity> {

    public CausticDrumRenderer(BlockEntityRendererProvider.Context ctx){}

    @Override
    public void render(CausticDrumBlockEntity blockEntity, float partialTick, PoseStack poseStack, MultiBufferSource bufferSource, int packedLight, int packedOverlay) {
        FluidStack fluidStack = blockEntity.getTank(null).getFluidInTank(0);
        String fluidName;
        int amount;
        int capacity = blockEntity.getTank(null).getTankCapacity(0);
        Direction facing = blockEntity.getBlockState().getValue(BlockStateProperties.FACING);

        if(blockEntity.getTank(null).getFluidInTank(0).isEmpty()){
            fluidName = "Empty";
            amount = 0;
        } else {
            fluidName = fluidStack.getHoverName().toString();
            amount = fluidStack.getAmount();
        }

        String fluidDisplay = String.format("%s", fluidName);
        String fluidAmount = String.format("%d / %d mB", amount, capacity);

        poseStack.pushPose();

        int directionToNorth = facing.compareTo(Direction.NORTH);
        float translateX = (directionToNorth & 2) == 0 ? 0.5f : directionToNorth & 1;
        float translateY = 0.65f;
        float translateZ = (directionToNorth & 2) == 2 ? 0.5f : directionToNorth & 1;

        poseStack.translate(translateX, translateY, translateZ);
        poseStack.mulPose(Axis.YP.rotationDegrees(-facing.toYRot()));
        poseStack.scale(0.01f, -0.01f, 0.01f);

        Minecraft mc = Minecraft.getInstance();
        Font font = mc.font;
        font.drawInBatch(fluidDisplay, -font.width(fluidDisplay) / 2f, 0, 0XFFFFFF,
                false, poseStack.last().pose(), bufferSource,
                Font.DisplayMode.NORMAL, 0, packedLight);

        font.drawInBatch(fluidAmount, -font.width(fluidAmount) / 2f, font.lineHeight, 0XFFFFFF,
                false, poseStack.last().pose(), bufferSource,
                Font.DisplayMode.NORMAL, 0, packedLight);

        poseStack.popPose();
    }

    // Identical to super
//    @Override
//    public boolean shouldRenderOffScreen(CausticDrumBlockEntity blockEntity){
//        return false;
//    }
}
