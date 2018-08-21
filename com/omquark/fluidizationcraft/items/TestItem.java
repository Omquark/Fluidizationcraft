package com.omquark.fluidizationcraft.items;

import com.omquark.fluidizationcraft.client.model.ReduxModelManager;
import com.omquark.fluidizationcraft.init.ReduxBlocks;
import com.omquark.fluidizationcraft.init.ReduxItems;

import net.minecraft.block.Block;
import net.minecraft.block.BlockFurnace;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class TestItem extends BaseItem{
	
	public TestItem(String name){
		super(name);
	}
	
	@Override
	public ActionResult<ItemStack> func_77659_a(World world, EntityPlayer player, EnumHand hand) {

		ItemStack heldItem = player.func_184586_b(hand);

		RayTraceResult result = func_77621_a(world, player, true);
		
		if(result == null || result.field_72313_a != RayTraceResult.Type.BLOCK) {
				return new ActionResult<>(EnumActionResult.PASS, heldItem);
		}
		
		//System.out.println(world.getBlockState(result.getBlockPos()).getActualState(world, result.getBlockPos()).toString());
		System.out.println(Blocks.field_150460_al.getClass());
		
		if(!world.field_72995_K) {
//			Block resultBlock = world.getBlockState(result.getBlockPos()).getBlock();
//			TextComponentString string = new TextComponentString("Block class: " + resultBlock.getClass().getName());
//			player.sendMessage((ITextComponent) string);
			
		}
		
		return new ActionResult<>(EnumActionResult.SUCCESS, heldItem);
	}

}
