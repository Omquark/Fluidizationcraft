package com.omquark.fluidizationcraft.tileentities;

import java.util.ArrayList;

import javax.annotation.Nullable;

import com.omquark.fluidizationcraft.util.RecipeHandler.RecipeHandler;
import com.omquark.fluidizationcraft.util.RecipeHandler.RecipeHandler.Recipe;
import com.omquark.fluidizationcraft.config.Config;
import com.omquark.fluidizationcraft.init.ReduxBlocks;
import com.omquark.fluidizationcraft.init.ReduxFluids;
import com.omquark.fluidizationcraft.init.ReduxItems;

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
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.IFluidHandler;
import net.minecraftforge.fluids.capability.IFluidTankProperties;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class TileDissolvinator extends BaseTileEntity implements ITickable, ISidedInventory, IFluidHandler{
	
	public static enum SlotEnum{
		INPUT_SLOT,
		OUTPUT_SLOT,
		FUEL_SLOT,
		OUTPUT_FUEL_SLOT
	};

	/**
	 * The FUEL_LEFT Enum should not be used internally
	 * It is only used to retrieve and set the amount externally.
	 * Instead internally set the contents.amount directly. 
	 */
	public static enum PropsEnum{
		FUEL_LEFT, 
		MAX_FUEL,
		FUEL_USED,
		TIME_ELAPSED,
		TIME_PER_ITEM
	};
	
	protected final ItemStack[] slots = new ItemStack[SlotEnum.values().length];
	/**
	 * Variables for fuel left, max fuel, etc
	 * Do not use this array to define fuel left, as it is not conistant.
	 * Used contents.amount
	 */
	protected final int[] props = new int[PropsEnum.values().length]; 
	protected Recipe currentRecipe = null;
	
	private FluidStack contents; 

	public TileDissolvinator() {
		super();
		
		contents = new FluidStack(ReduxFluids.acid, 0);
		
		for(int i = 0; i < slots.length; i++) {
			slots[i] = ItemStack.field_190927_a;
		}
		
		props[PropsEnum.TIME_ELAPSED.ordinal()] = 0;
		props[PropsEnum.MAX_FUEL.ordinal()] = Config.DissolvinatorMaxFuel;
		props[PropsEnum.FUEL_USED.ordinal()] = Config.DissolvinatorFuelUsed;
		props[PropsEnum.TIME_PER_ITEM.ordinal()] = Config.DissolvinatorTimePerItem;
	}
	
	@Override
	public void func_73660_a() {
		
		if(contents.amount >= props[PropsEnum.FUEL_USED.ordinal()]) { 

			ItemStack inStack = slots[SlotEnum.INPUT_SLOT.ordinal()];
			ItemStack outStack = slots[SlotEnum.OUTPUT_SLOT.ordinal()];
			Recipe tempRecipe = RecipeHandler.getDissolvinatorRecipe(slots[SlotEnum.INPUT_SLOT.ordinal()]);
			active = false;
			if(slots[SlotEnum.INPUT_SLOT.ordinal()] != ItemStack.field_190927_a) {
				active = true;
				if(tempRecipe == null) {
					active = false;
				}
				if(active && tempRecipe != currentRecipe) {
					currentRecipe = tempRecipe;
					props[PropsEnum.TIME_ELAPSED.ordinal()] = 0;
					active = false;
				}
				
				if(active && !(slots[SlotEnum.OUTPUT_SLOT.ordinal()].func_77969_a(currentRecipe.getOutput()))){
					if(!slots[SlotEnum.OUTPUT_SLOT.ordinal()].func_190926_b()) {
						active = false;
					}
				}
				if(active && props[PropsEnum.FUEL_USED.ordinal()] > contents.amount) {
					active = false;
				}
			}
			
			//At this point, there is a recipe, the input is valid, we have the fuel
			//The only unknown is if the recipe output is empty or no.
			//If it is not empty, it has the same item as the output
			
			if(active) {
				props[PropsEnum.TIME_ELAPSED.ordinal()]++;
				if(props[PropsEnum.TIME_ELAPSED.ordinal()] >= props[PropsEnum.TIME_PER_ITEM.ordinal()]) {
					if(slots[SlotEnum.OUTPUT_SLOT.ordinal()].func_190926_b()) {
						slots[SlotEnum.OUTPUT_SLOT.ordinal()] = currentRecipe.getOutput().func_77946_l();
					}
					else {
						slots[SlotEnum.OUTPUT_SLOT.ordinal()].func_190920_e(slots[SlotEnum.OUTPUT_SLOT.ordinal()].func_190916_E() + currentRecipe.getOutput().func_190916_E());
					}
				
					contents.amount -= props[PropsEnum.FUEL_USED.ordinal()];
					slots[SlotEnum.INPUT_SLOT.ordinal()].func_190920_e(slots[SlotEnum.INPUT_SLOT.ordinal()].func_190916_E() - currentRecipe.getInput().func_190916_E());
					active = false;
					sendUpdates();
				}
			}
		}
		
		if(!active) {
			props[PropsEnum.TIME_ELAPSED.ordinal()] = 0;
		}
	}

	@Override
	public void func_145839_a(NBTTagCompound compound) {
		
		super.func_145839_a(compound);

		props[PropsEnum.TIME_ELAPSED.ordinal()] = compound.func_74762_e("timeElapsed");
		contents.amount = compound.func_74762_e("fuelLeft");
		props[PropsEnum.FUEL_USED.ordinal()] = compound.func_74762_e("fuelUsed");

		NBTTagList tagList = compound.func_150295_c("items", Constants.NBT.TAG_COMPOUND);
		
		slots[compound.func_74771_c("slot")] = new ItemStack(tagList.func_150305_b(0)); 
		
		for(int i = 0; i < tagList.func_74745_c(); i++) {
			slots[i] = new ItemStack(tagList.func_150305_b(i));
        }
	}

	@Override
	public NBTTagCompound func_189515_b(NBTTagCompound compound) {

		NBTTagList tagList = new NBTTagList();

		compound.func_74774_a("slot", (byte) 0);
		
		for(int i = 0; i < slots.length; i++) {
			NBTTagCompound cmp = new NBTTagCompound();
			cmp.func_74774_a("slot", (byte) i);
			slots[i].func_77955_b(cmp);
			tagList.func_74742_a(cmp);
		}
		
		compound.func_74782_a("items", tagList);
		
		compound.func_74768_a("timeElapsed", props[PropsEnum.TIME_ELAPSED.ordinal()]);
		compound.func_74768_a("fuelLeft", contents.amount);
		compound.func_74768_a("fuelUsed", props[PropsEnum.FUEL_USED.ordinal()]);
		
		super.func_189515_b(compound);
 		this.func_70296_d();
		return compound;
	}

	@Override
	public String func_70005_c_() {
		return "Dissolvinator";
	}

	@Override
	public boolean func_145818_k_() {
		return false;
	}

	@Override
	public int func_70302_i_() {
		return slots.length;
	}

	@Override
	public boolean func_191420_l() {
		
		for(int i = 0; i < slots.length; i++) {
			
			if(!slots[i].func_190926_b()) return false;
		}
		return true;
	}

	@Override
	public ItemStack func_70301_a(int index) {
		
		if(index >= slots.length) return ItemStack.field_190927_a;
		
		if(index == SlotEnum.FUEL_SLOT.ordinal()) {

			if(slots[index].func_77969_a(ReduxItems.acidBucket)) {
				return ItemStack.field_190927_a;
			}
		}
		
		if(!(slots[index].func_190926_b()))
			return slots[index];
		return ItemStack.field_190927_a;
	}

	@Override
	public ItemStack func_70298_a(int index, int count) {

		ItemStack tempStack;

		if(count >= slots[index].func_190916_E()) {
			tempStack = slots[index];
			slots[index] = ItemStack.field_190927_a;
		}
		else {
			tempStack = slots[index].func_77979_a(count);
		}
		return tempStack;
	}

	@Override
	public ItemStack func_70304_b(int index) {
		
		if(slots[index].func_190926_b()) return ItemStack.field_190927_a;
		if(SlotEnum.INPUT_SLOT.ordinal() == index) {
			currentRecipe = null;
		}
		
		ItemStack tempStack = slots[index];
		slots[index] = ItemStack.field_190927_a;
		//sendUpdates();
		return tempStack;
	}

	@Override
	public void func_70299_a(int index, ItemStack stack) {
		
		if(index == SlotEnum.OUTPUT_SLOT.ordinal()) {
			slots[SlotEnum.OUTPUT_SLOT.ordinal()].func_190918_g(stack.func_190916_E());
		}
		
		slots[index] = stack;
		
		boolean flag = false;

		if(index == SlotEnum.FUEL_SLOT.ordinal()) {
		
			if(stack.func_77969_a(ReduxItems.acidBucket)) {
			
				if(contents.amount + 1000 <= props[PropsEnum.MAX_FUEL.ordinal()]) {
				
					contents.amount += 1000;
					slots[SlotEnum.FUEL_SLOT.ordinal()].func_190918_g(1);
				}
			}
			
			if(stack.func_77969_a(ReduxItems.itemVialAcid)) {
				
				ItemStack outStack = ReduxItems.itemVialEmpty.func_77946_l();
				
				while(contents.amount + 1000 <= props[PropsEnum.MAX_FUEL.ordinal()]) {

					contents.amount += 1000;
					slots[SlotEnum.FUEL_SLOT.ordinal()].func_190918_g(1);
					outStack.func_190917_f(1);
					
					if(slots[SlotEnum.FUEL_SLOT.ordinal()].func_190916_E() == 0) break;
				}
				
				outStack.func_190918_g(1);
				slots[SlotEnum.OUTPUT_FUEL_SLOT.ordinal()] = outStack;
			}
		}
		sendUpdates();
	}
	
	@Override
	public int func_70297_j_() {
		return 64;
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
		if(id == PropsEnum.FUEL_LEFT.ordinal()) return contents.amount;
		return props[id];
	}

	@Override
	public void func_174885_b(int id, int value) {
		if(id == PropsEnum.FUEL_LEFT.ordinal()) {
			contents.amount = value;
		}
		props[id] = value;
	}

	@Override
	public int func_174890_g() {
		return props.length;
	}

	@Override
	public void func_174888_l() {
	}

	@Override
	public int[] func_180463_a(EnumFacing side) {
		//TODO implement this when possible
		return null;
	}

	@Override
	public boolean func_180462_a(int index, ItemStack itemStackIn, EnumFacing direction) {
		//TODO implement this when possible
		return false;
	}

	@Override
	public boolean func_180461_b(int index, ItemStack stack, EnumFacing direction) {
		//TODO implement this when possible
		return false;
	}
	
	@Override
	@Nullable
	public SPacketUpdateTileEntity func_189518_D_() {
		return new SPacketUpdateTileEntity(this.field_174879_c, 3, this.func_189517_E_());
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

	@Override
	public IFluidTankProperties[] getTankProperties() {
		//TODO implement this when possible
		return null;
	}

	@Override
	public int fill(FluidStack resource, boolean doFill) {

		if(!resource.isFluidEqual(new FluidStack(ReduxFluids.acid, 1000))){return 0;}
		
		int fillAmount = resource.amount;
		final int originalAmount = contents.amount;
		
		if(fillAmount > 1000) fillAmount = 1000;
		
		contents.amount += fillAmount;
		if(contents.amount > props[PropsEnum.MAX_FUEL.ordinal()]) {
			fillAmount -= (contents.amount - props[PropsEnum.MAX_FUEL.ordinal()]);
			contents.amount = originalAmount + fillAmount;
			sendUpdates();
		}
		
		if(!doFill) contents.amount = originalAmount;
		
		return fillAmount;
	}

	@Override
	public FluidStack drain(FluidStack resource, boolean doDrain) {
		//TODO implement this when possible
		return null;
	}

	@Override
	public FluidStack drain(int maxDrain, boolean doDrain) {
		//TODO implement this when possible
		return null;
	}
}
