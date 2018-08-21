package com.omquark.fluidizationcraft.Container;

import java.util.ArrayList;

import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class CustomSlot extends Slot{
	
	private ArrayList<ItemStack> allowableInputs = new ArrayList<ItemStack>();
	private boolean isOutOnly;

	public CustomSlot(IInventory inventoryIn, int index, int xPosition, int yPosition) {
		super(inventoryIn, index, xPosition, yPosition);
		isOutOnly = false;
	}
	
	/**
	 * Adds the given item to a list of allowable inputs
	 * 
	 * @param stack - the ItemStack to add to the allowable inputs
	 */
	public void addInput(ItemStack stack) {
		if(!allowableInputs.contains(stack))
				allowableInputs.add(stack);
	}
	
	public void setOutOnly(boolean output) {
		isOutOnly = output;
	}
	
	public boolean func_75214_a(ItemStack stack) {

		if(isOutOnly) return false;
		for(ItemStack test : allowableInputs) {
			if(stack.func_77969_a(test)) return true;
		}
		return false;
	}
}
