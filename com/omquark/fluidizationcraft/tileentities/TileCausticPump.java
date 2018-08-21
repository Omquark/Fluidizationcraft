package com.omquark.fluidizationcraft.tileentities;

import java.util.ArrayList;
import java.util.List;

import com.omquark.fluidizationcraft.Fluids.ReduxBaseFluid;
import com.omquark.fluidizationcraft.init.ReduxFluids;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.common.util.Constants;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.IFluidHandler;
import net.minecraftforge.fluids.capability.IFluidTankProperties;

public class TileCausticPump extends BaseTileEntity implements ITickable, IFluidHandler/**, ISidedInventory*/{
	
	private List<BlockPos> affectedBlocks = new ArrayList<BlockPos>();
	private FluidStack contents = new FluidStack(ReduxFluids.acid, 0);
	private static final int capacity = 20000; //capacity, in mB
	private boolean printOnce;
	//How long to wait before attempting to activate, this will give the pump time to poll
	private static final int placingCooldown = 200;
	//How long in between extraction attempts
	private static final int pumpCooldown = 20;
	//Set to the placing cooldown to not activate prematurely
	private int timeToCooldown = placingCooldown;
	
	public TileCausticPump() {
		active = false;
	}
	
	/**
	 * @return - A copy of the fluid contents. This stack should never be changed outside of this class.
	 */
	public FluidStack getFluidStack() {
		return contents.copy();
	}

	@Override
	public void func_73660_a() {
		
		timeToCooldown--;
		if(timeToCooldown > 0) return;
		timeToCooldown = pumpCooldown;

		Block centerBlock;
		ReduxBaseFluid centerFluid;

		if(affectedBlocks.isEmpty()) {
			
			int i;
			for(i = 1; ; i++) {
				
				centerBlock = field_145850_b.func_180495_p(field_174879_c.func_177979_c(i)).func_177230_c();
				
				if(centerBlock instanceof ReduxBaseFluid) { 
					centerFluid = (ReduxBaseFluid) centerBlock;
					break;
				}
				
				else if(centerBlock.func_149680_a(centerBlock, Blocks.field_150350_a)) {
					continue;
				}
				
				else {
					break;
				}
			}
			
			checkAndAddNeighbors(field_174879_c.func_177979_c(i));
		}

		else {
			
			//Check that the block hasn't changed since polling.
			Block pickupBlock = field_145850_b.func_180495_p(affectedBlocks.get(0)).func_177230_c(); 
			
			if(pickupBlock instanceof ReduxBaseFluid) {
				FluidStack pickupStack = new FluidStack(((ReduxBaseFluid) pickupBlock).getFluid(), 1000);
				
				if(contents.amount == 0) {
					
					contents = pickupStack;
					field_145850_b.func_175698_g(affectedBlocks.get(0));
					affectedBlocks.remove(0);
				}
				
				else if(contents.isFluidEqual(pickupStack) && contents.amount + 1000 <= capacity) {
					
					contents.amount += 1000;
					field_145850_b.func_175698_g(affectedBlocks.get(0));
					affectedBlocks.remove(0);
				}
			}
		}
		
		detectAndFillNeighbors();
	}
	
	private void detectAndFillNeighbors(){
		
		TileEntity entityTank;
		
		entityTank = field_145850_b.func_175625_s(field_174879_c.func_177984_a());
		if(entityTank instanceof IFluidHandler) {
			contents.amount -= ((IFluidHandler) entityTank).fill(contents, true);
		}
	}
	
	/**
	 * Polls neighbor blocks for extraction, only affects ReduxFluids.
	 * Will also add the position passed to this method.
	 * It will get any connected source blocks with a radius up to 16 square.
	 * This will only capture the layer of the pos passed on the y plane.
	 * 
	 * @param blockPos - The first block to check/add
	 */
	private void checkAndAddNeighbors(BlockPos blockPos) {
		
		if((!affectedBlocks.contains(blockPos)) && field_145850_b.func_180495_p(blockPos).func_177230_c() instanceof ReduxBaseFluid) {
			if(((ReduxBaseFluid)field_145850_b.func_180495_p(blockPos).func_177230_c()).isSourceBlock(field_145850_b, blockPos)) {
				
				affectedBlocks.add(blockPos);
			}
			
			else {
				return;
			}
			
			if(blockPos.func_185332_f(field_174879_c.func_177958_n(), field_174879_c.func_177956_o(), field_174879_c.func_177952_p()) > 16) return;
			
			checkAndAddNeighbors(blockPos.func_177974_f());
			checkAndAddNeighbors(blockPos.func_177978_c());
			checkAndAddNeighbors(blockPos.func_177976_e());
			checkAndAddNeighbors(blockPos.func_177968_d());
		}
	}
	
	@Override
	public void func_145839_a(NBTTagCompound compound) {
		
		super.func_145839_a(compound);
	}

	@Override
	public NBTTagCompound func_189515_b(NBTTagCompound compound) {
		
		super.func_189515_b(compound);
 		func_70296_d();
		return compound;
	}


	@Override
	public IFluidTankProperties[] getTankProperties() {
		return null;
	}

	@Override
	public int fill(FluidStack resource, boolean doFill) {
		return 0;
	}

	/**
	 * Calling this will attempt to drain 1000 mB of fluid from the tank.
	 * It will remove all the fluid if this is not possible
	 * 
	 *  @param resource - The fluid to drain. Since the pump can only hold one fluid, this value is somewhat meaningless
	 *  @param doDrain - Simulates the drain when true. Useful for checks before draining, or creative mode tanks.
	 */
	@Override
	public FluidStack drain(FluidStack resource, boolean doDrain) {
		return drain(1000, doDrain);
	}

	/**
	 * Same as drain(FluidStack, boolean), but you can specify an amount to drain.
	 * 
	 * @param maxDrain - The amount to drain. If this amount is more than in the container, the rest will be drained
	 * @param doDrain - Simulates the drain when true. Useful for checks before draining, or creative mode tanks.
	 * 
	 */
	@Override
	public FluidStack drain(int maxDrain, boolean doDrain) {
		if(contents.amount > maxDrain) {
			if(doDrain) contents.amount -= maxDrain;
			return new FluidStack(contents.getFluid(), maxDrain);
		}
		FluidStack fluidStack = new FluidStack(contents.getFluid(), contents.amount);
		if(doDrain) contents.amount = 0;
		return contents;
	}

	@Override
	public int func_70302_i_() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean func_191420_l() {
		return contents.amount == 0 ? true : false;
	}

	@Override
	public ItemStack func_70301_a(int index) {
		return ItemStack.field_190927_a;
	}

	@Override
	public ItemStack func_70298_a(int index, int count) {
		return ItemStack.field_190927_a;
	}

	@Override
	public ItemStack func_70304_b(int index) {
		return ItemStack.field_190927_a;
	}

	@Override
	public void func_70299_a(int index, ItemStack stack) {
	}

	@Override
	public boolean func_70300_a(EntityPlayer player) {
		return true;
	}

	@Override
	public void func_174889_b(EntityPlayer player) {
	}

	@Override
	public void func_174886_c(EntityPlayer player) {
	}

	@Override
	public boolean func_94041_b(int index, ItemStack stack) {
		return false;
	}

	@Override
	public int func_174887_a_(int id) {
		return 0;
	}

	@Override
	public void func_174885_b(int id, int value) {
	}

	@Override
	public int func_174890_g() {
		return 0;
	}

	@Override
	public void func_174888_l() {
	}

	@Override
	public String func_70005_c_() {
		return "Caustic Pump";
	}

	@Override
	public int[] func_180463_a(EnumFacing side) {
		int[] i = new int [1];
		i[0] = 0;
		return i;
	}

	@Override
	public boolean func_180462_a(int index, ItemStack itemStackIn, EnumFacing direction) {
		return false;
	}

	@Override
	public boolean func_180461_b(int index, ItemStack stack, EnumFacing direction) {
		return false;
	}
}
