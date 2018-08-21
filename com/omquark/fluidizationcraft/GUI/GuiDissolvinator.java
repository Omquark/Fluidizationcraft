package com.omquark.fluidizationcraft.GUI;

import java.util.ArrayList;
import java.util.List;

import com.omquark.fluidizationcraft.PlasmaCraftRedux;
import com.omquark.fluidizationcraft.Container.ContainerDissolvinator;
import com.omquark.fluidizationcraft.blocks.BlockDissolvinator;
import com.omquark.fluidizationcraft.tileentities.TileDissolvinator;
import com.omquark.fluidizationcraft.tileentities.TileDissolvinator.PropsEnum;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.network.IGuiHandler;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class GuiDissolvinator extends GuiContainer{
	
	private FontRenderer fontRenderer;
    private static final ResourceLocation dissolvinatorGuiTextures = new ResourceLocation(PlasmaCraftRedux.MODID + ":gui/dissolvinator.png");
    private InventoryPlayer inventoryPlayer;
    private IInventory tileDissolvinator;

    public GuiDissolvinator(InventoryPlayer invPlayer, IInventory invDissolvinator){
    	super(new ContainerDissolvinator(invPlayer, invDissolvinator));
        inventoryPlayer = invPlayer;
        tileDissolvinator = invDissolvinator;
        fontRenderer = new FontRenderer(Minecraft.func_71410_x().field_71474_y, dissolvinatorGuiTextures, 
        		Minecraft.func_71410_x().func_110434_K(), false);
    }

    @Override
    protected void func_146979_b(int mouseX, int mouseY)
    {

        //super.drawGuiContainerForegroundLayer(mouseX, mouseY);

        int marginHorizontal = ((field_146294_l - field_146999_f) / 2) - 1;
        int marginVertical = ((field_146295_m - field_147000_g) / 2) - 32;

        String s = "Dissolvinator";
        //fontRenderer.drawString("Hoe bag!", mouseX, mouseY, 0xFF0000);
        
        func_73732_a(fontRenderer, "Hoe Bag!",  mouseX, mouseY, 0x404040);
        
        //fontRenderer.drawString(s, xSize / 2-fontRenderer.getStringWidth(s) / 2, 6, 4210752);

        //fontRenderer.drawString(inventoryPlayer.getDisplayName().getUnformattedText(), 8, ySize - 96 + 2, 4210752);

        ItemStack tempStack = ItemStack.field_190927_a;

        //Draw tooltips for items
        if(getSlotUnderMouse() != null) {
        	tempStack = getSlotUnderMouse().func_75211_c();
        }

        if(!(tempStack.func_190926_b())){
        	List<String> textLines = func_191927_a(tempStack);
        	if(textLines == null) return;
        	func_146283_a(textLines, mouseX - marginHorizontal, mouseY - marginVertical);
        }
        
        //Tank tooltip
        if(mouseX > marginHorizontal + 145 && mouseX < marginHorizontal + 145 + 16 &&
        		mouseY > marginVertical + 50 && mouseY < marginVertical + 102) {
        	
        	List<String> textLines = new ArrayList<String>();
        	textLines.add("Acid");
        	textLines.add(tileDissolvinator.func_174887_a_(PropsEnum.FUEL_LEFT.ordinal()) + "mB /" + tileDissolvinator.func_174887_a_(PropsEnum.MAX_FUEL.ordinal()) + "mB");
        	
        	func_146283_a(textLines, mouseX - marginHorizontal, mouseY - marginVertical);
        }

    }

    /**
     * Args : renderPartialTicks, mouseX, mouseY
     */
    @Override
    protected void func_146976_a(float partialTicks, int mouseX, int mouseY)
    {
    	func_146276_q_();
        GlStateManager.func_179131_c(1.0F, 1.0F, 1.0F, 1.0F);
        field_146297_k.func_110434_K().func_110577_a(dissolvinatorGuiTextures);
        int marginHorizontal = ((field_146294_l - field_146999_f) / 2) - 1;
        int marginVertical = ((field_146295_m - field_147000_g) / 2) - 32;
        func_73729_b(marginHorizontal, marginVertical + 32, 0, 0, field_146999_f, 200);

        // Draw progress indicator
        int progressLevel = getFillLevel(24,
        		tileDissolvinator.func_174887_a_(TileDissolvinator.PropsEnum.TIME_ELAPSED.ordinal()),
        		tileDissolvinator.func_174887_a_(TileDissolvinator.PropsEnum.TIME_PER_ITEM.ordinal()));
        func_73729_b(marginHorizontal + 78, marginVertical + 57, 178, 14, progressLevel, 16);
        
        //Draw the tank
        progressLevel = getFillLevel(48,
        	tileDissolvinator.func_174887_a_(TileDissolvinator.PropsEnum.FUEL_LEFT.ordinal()),
        	tileDissolvinator.func_174887_a_(TileDissolvinator.PropsEnum.MAX_FUEL.ordinal()));
        func_73729_b(marginHorizontal + 145, marginVertical + 101 - progressLevel, 181, 40, 16, progressLevel);
    }
    
/**
 * Determines a fill level, in pixels.
 * @param progressWidth - How many pixels the progress bar is
 * @param current - The progress of the current operation
 * @param max - The max progress of the current operation, this must be greater than 0
 * @return - A value indicationg how many pixels will be drawn in the progress bar
 */
    private int getFillLevel(int progressWidth, int current, int max)
    {
    	if(max == 0) return 0;
    	float progress = (float) current / max;
    	return (int) Math.ceil(progress * progressWidth);
    }
}
