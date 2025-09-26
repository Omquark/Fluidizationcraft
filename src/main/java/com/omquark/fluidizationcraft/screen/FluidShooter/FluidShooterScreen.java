package com.omquark.fluidizationcraft.screen.FluidShooter;

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
public class FluidShooterScreen extends ModScreen<FluidShooterMenu> {

    public FluidShooterScreen(FluidShooterMenu menu, Inventory inventory, Component component) {
        super(menu, inventory, component);
        texture("textures/gui/fluid_shooter.png");
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
        renderTank(graphics, x, y);
    }

    private void renderTank(GuiGraphics graphics, int x, int y) {
        int tank = menu.getScaledProgress();
        graphics.blit(texture, x + 80, y + 71 - tank, 180, 90 - tank, 25, tank);
    }

    @Override
    public void render(GuiGraphics graphics, int mouseX, int mouseY, float delta) {
        renderMenuBackground(graphics);
        super.render(graphics, mouseX, mouseY, delta);
        renderTooltip(graphics, mouseX, mouseY);
    }

    protected void renderTooltip(GuiGraphics pGuiGraphics, int mouseX, int mouseY) {
        if (this.menu.getCarried().isEmpty() && this.hoveredSlot != null && this.hoveredSlot.hasItem()) {
            ItemStack itemstack = this.hoveredSlot.getItem();
            pGuiGraphics.renderTooltip(this.font, this.getTooltipFromContainerItem(itemstack), itemstack.getTooltipImage(), itemstack, mouseX, mouseY);
            return;
        }

        int x = (width - imageWidth) / 2;
        int y = (height - imageHeight) / 2;

        if(mouseX > x + 80 && mouseX < x + 97 &&
                mouseY > y + 21 && mouseY < y + 71){
            List<Component> tooltip = new ArrayList<>();
            tooltip.add(Component.literal("Acid"));
            tooltip.add(Component.literal(menu.getDataFrom(0) + "mB /" + menu.getDataFrom(1) + "mB"));
            pGuiGraphics.renderTooltip(this.font, tooltip, Optional.empty(), mouseX, mouseY);
        }
    }
}
