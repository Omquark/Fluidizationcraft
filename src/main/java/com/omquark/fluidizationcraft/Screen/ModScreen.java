package com.omquark.fluidizationcraft.screen;

import com.mojang.blaze3d.systems.RenderSystem;
import com.omquark.fluidizationcraft.FluidizationCraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractContainerMenu;

abstract public class ModScreen<T extends AbstractContainerMenu> extends AbstractContainerScreen<T> {

    protected static ResourceLocation texture;

    protected ModScreen(T screen, Inventory inventory, Component component) {
        super(screen, inventory, component);
    }

    public void texture(String textureLocation){
        texture = ResourceLocation.fromNamespaceAndPath(FluidizationCraft.MODID, textureLocation);
    }

    @Override
    protected void renderBg(GuiGraphics graphics, float partialTick, int mouseX, int mouseY) {
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1.0f, 1.0f, 1.0f, 1.0f);
        RenderSystem.setShaderTexture(0, texture);
        int x = (width - imageWidth) / 2;
        int y = (height - imageHeight) / 2;

        graphics.blit(texture, x, y, 0, 0, imageWidth, imageHeight);
    }
}
