package com.omquark.fluidizationcraft.Screen.Dissolvinator;

import com.mojang.blaze3d.systems.RenderSystem;
import com.omquark.fluidizationcraft.FluidizationCraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;

import javax.annotation.ParametersAreNonnullByDefault;

@ParametersAreNonnullByDefault
public class DissolvinatorScreen extends AbstractContainerScreen<DissolvinatorMenu> {
    private static final ResourceLocation TEXTURE =
            new ResourceLocation(FluidizationCraft.MODID, "textures/gui/dissolvinator.png");
    public DissolvinatorScreen(DissolvinatorMenu menu, Inventory inventory, Component component) {
        super(menu, inventory, component);
    }

    @Override
    protected void init() {
        super.init();
        this.inventoryLabelY = 10000;
        this.titleLabelY = 10000;
    }

    @Override
    protected void renderBg(GuiGraphics graphics, float partialTick, int mouseX, int mouseY) {
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1.0f, 1.0f, 1.0f, 1.0f);
        RenderSystem.setShaderTexture(0, TEXTURE);
        int x = (width - imageWidth) / 2;
        int y = (height - imageHeight) / 2;

        graphics.blit(TEXTURE, x, y, 0, 0, imageWidth, imageHeight);

        renderProgressArrow(graphics, x, y);
    }

    private void renderProgressArrow(GuiGraphics graphics, int x, int y){
        if(menu.isCrafting()){
            graphics.blit(TEXTURE, x + 85, y + 30, 176, 0, 8, menu.getScaledProgress());
        }
    }

    @Override
    public void render(GuiGraphics graphics, int mouseX, int mouseY, float delta){
        renderMenuBackground(graphics);
        super.render(graphics, mouseX, mouseY, delta);
        renderTooltip(graphics, mouseX, mouseY);
    }
}
