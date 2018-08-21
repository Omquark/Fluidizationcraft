package com.omquark.fluidizationcraft.tileentities;

import java.util.ArrayList;

import javax.annotation.Nullable;

import com.omquark.fluidizationcraft.util.RecipeHandler.RecipeHandler;
import com.omquark.fluidizationcraft.util.RecipeHandler.RecipeHandler.Recipe;
import com.omquark.fluidizationcraft.config.Config;
import com.omquark.fluidizationcraft.init.ReduxBlocks;
import com.omquark.fluidizationcraft.init.ReduxFluids;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.util.text.ITextComponent;
import net.minecraftforge.common.util.Constants;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public abstract class BaseTileEntity extends TileEntity implements ITickable, ISidedInventory{
	
	public static enum SlotFace{
		NORTH,
		EAST,
		SOUTH,
		WEST,
		UP,
		DOWN
	};
	
	protected boolean active = false;
	
	public BaseTileEntity() {
		//TODO: Nothing!
		super();
	}

	@Override
	public void func_73660_a() {
	}
	
	protected void sendUpdates() {
		field_145850_b.func_175704_b(field_174879_c, field_174879_c);
		field_145850_b.func_184138_a(field_174879_c, field_145850_b.func_180495_p(field_174879_c), field_145850_b.func_180495_p(field_174879_c), 3);
		field_145850_b.func_180497_b(field_174879_c, func_145838_q(), 0, 0);
		func_70296_d();
	}
	
	@Override
	public void func_145839_a(NBTTagCompound compound) {
		super.func_145839_a(compound);
	}

	@Override
	public NBTTagCompound func_189515_b(NBTTagCompound compound) {
		return super.func_189515_b(compound);
	}

	@Override
	public boolean func_145818_k_() {
		return false;
	}

	@Override
	public int func_70297_j_() {
		return 64;
	}

	@Override
	@Nullable
	public SPacketUpdateTileEntity func_189518_D_() {
		return new SPacketUpdateTileEntity(field_174879_c, 3, this.func_189517_E_());
	}


	@Override
	public NBTTagCompound func_189517_E_() {
		return this.func_189515_b(new NBTTagCompound());
	}
	
	@Override
	public void onDataPacket(NetworkManager net, SPacketUpdateTileEntity pkt) {
		super.onDataPacket(net, pkt);
		handleUpdateTag(pkt.func_148857_g());
	}
}
