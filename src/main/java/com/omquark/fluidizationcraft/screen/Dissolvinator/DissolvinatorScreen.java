package com.omquark.fluidizationcraft.screen.Dissolvinator;

import com.omquark.fluidizationcraft.screen.ModScreen;
import com.omquark.fluidizationcraft.util.EverythingNonNullByDefault;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.item.ItemStack;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@EverythingNonNullByDefault
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
    protected void renderTooltip(GuiGraphics pGuiGraphics, int mouseX, int mouseY) {
        if (this.menu.getCarried().isEmpty() && this.hoveredSlot != null && this.hoveredSlot.hasItem()) {
            ItemStack itemstack = this.hoveredSlot.getItem();
            pGuiGraphics.renderTooltip(this.font, this.getTooltipFromContainerItem(itemstack), itemstack.getTooltipImage(), itemstack, mouseX, mouseY);
            return;
        }

        int x = (width - imageWidth) / 2;
        int y = (height - imageHeight) / 2;

        if(mouseX > x + 144 && mouseX < x + 161 &&
            mouseY > y + 19 && mouseY < y + 69){
            List<Component> tooltip = new ArrayList<>();
            tooltip.add(Component.literal("Acid"));
            tooltip.add(Component.literal(menu.getDataFrom(2) + "mB /" + menu.getDataFrom(3) + "mB"));
            pGuiGraphics.renderTooltip(this.font, tooltip, Optional.empty(), mouseX, mouseY);
        }
    }

    @Override
    protected void renderBg(GuiGraphics graphics, float partialTick, int mouseX, int mouseY) {
        super.renderBg(graphics, partialTick, mouseX, mouseY);
        int x = (width - imageWidth) / 2;
        int y = (height - imageHeight) / 2;
        renderProgressArrow(graphics, x, y);
        renderTank(graphics, x, y);
    }

    private void renderProgressArrow(GuiGraphics graphics, int x, int y){
        if(menu.isCrafting()){
            int progress = menu.getScaledProgress();
            //This renders the arrow from the top left, using the progress, we change the anchor point
            //so it appears to render from the bottom up, showing the progress bubbles from small to large
            graphics.blit(texture, x + 81, y + 41 - progress, 181, 30 - progress, 16, progress);
            //This renders top down
//            graphics.blit(texture, x + 81, y + 21, 181, 10, 16, menu.getScaledProgress());
        }
    }

    private void renderTank(GuiGraphics graphics, int x, int y){
        int tank = menu.getScaledFuel();
        graphics.blit(texture, x + 145, y + 70 - tank, 181, 90 - tank, 16, tank);
    }

    @Override
    public void render(GuiGraphics graphics, int mouseX, int mouseY, float delta){
        renderMenuBackground(graphics);
        super.render(graphics, mouseX, mouseY, delta);
        renderTooltip(graphics, mouseX, mouseY);
    }
}
