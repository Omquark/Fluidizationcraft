package com.omquark.fluidizationcraft.guiHandler;

import com.omquark.fluidizationcraft.PlasmaCraftRedux;
import com.omquark.fluidizationcraft.Container.ContainerDissolvinator;
import com.omquark.fluidizationcraft.GUI.GuiDissolvinator;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.fml.relauncher.Side;

@SideOnly(Side.CLIENT)
public class FluidizationGuiHandler implements IGuiHandler
{

	public static enum GUI_ENUM{
		GUI_DISSOLVINATOR
	};
	
    @Override
    public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z){
    	
        TileEntity tileEntity = world.func_175625_s(new BlockPos(x, y, z));

        if (tileEntity != null){
            if (ID == GUI_ENUM.GUI_DISSOLVINATOR.ordinal()){
                return new ContainerDissolvinator(player.field_71071_by, (IInventory)tileEntity);
            }
//            if (ID == BlockSmith.GUI_ENUM.COMPACTOR.ordinal()){
//                return new ContainerCompactor(player.inventory, 
//                      (IInventory)tileEntity);
//            }

        }
        return null;
    }

    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
    	
        TileEntity tileEntity = world.func_175625_s(new BlockPos(x, y, z));

        if (tileEntity != null){
            if (ID == GUI_ENUM.GUI_DISSOLVINATOR.ordinal()){
                return new GuiDissolvinator(player.field_71071_by, (IInventory)tileEntity);
            }
//            if (ID == BlockSmith.GUI_ENUM.COMPACTOR.ordinal()){
//                return new GuiCompactor(player.inventory, 
//                      (IInventory)tileEntity);
//            }
//            if (ID == BlockSmith.GUI_ENUM.DECONSTRUCTOR.ordinal()){
//            	return new GuiDeconstructor(player.inventory, world, 
//            			I18n.format("tile.deconstructor.name"), x, y, z);
//            }
        }
        return null;
    }
}
