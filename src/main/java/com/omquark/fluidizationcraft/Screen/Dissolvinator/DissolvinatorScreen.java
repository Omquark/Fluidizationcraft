package com.omquark.fluidizationcraft.screen.Dissolvinator;

import com.omquark.fluidizationcraft.screen.ModScreen;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;

import javax.annotation.ParametersAreNonnullByDefault;

@ParametersAreNonnullByDefault
public class DissolvinatorScreen extends ModScreen<DissolvinatorMenu> {
    public DissolvinatorScreen(DissolvinatorMenu menu, Inventory inventory, Component component) {
        super(menu, inventory, component);
        texture("textures/gui/dissolvinator.png");
    }

    @Override
    protected void init() {
        super.init();
        this.inventoryLabelY = 10000;
        this.titleLabelY = 10000;
    }

    @Override
    protected void renderBg(GuiGraphics graphics, float partialTick, int mouseX, int mouseY) {
        super.renderBg(graphics, partialTick, mouseX, mouseY);
        int x = (width - imageWidth) / 2;
        int y = (height - imageHeight) / 2;

        renderProgressArrow(graphics, x, y);
    }

    private void renderProgressArrow(GuiGraphics graphics, int x, int y){
        if(menu.isCrafting()){
            graphics.blit(texture, x + 85, y + 30, 176, 0, 8, menu.getScaledProgress());
        }
    }

    @Override
    public void render(GuiGraphics graphics, int mouseX, int mouseY, float delta){
        renderMenuBackground(graphics);
        super.render(graphics, mouseX, mouseY, delta);
        renderTooltip(graphics, mouseX, mouseY);
    }
}
