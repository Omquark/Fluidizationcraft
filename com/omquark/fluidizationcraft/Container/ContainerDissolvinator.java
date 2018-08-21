package com.omquark.fluidizationcraft.Container;

import com.omquark.fluidizationcraft.init.ReduxBlocks;
import com.omquark.fluidizationcraft.init.ReduxItems;
import com.omquark.fluidizationcraft.tileentities.TileDissolvinator;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IContainerListener;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ContainerDissolvinator extends Container{
	    
		private final IInventory tileDissolvinator;
		private final int playerInventorySlots;
		private final int hotbarSlots;

		private final int sizeInventory;

	    public ContainerDissolvinator(InventoryPlayer inventoryPlayer, IInventory iInventory)
	    {
	    	hotbarSlots = 4;
	    	playerInventorySlots = 13;
	    	
	        tileDissolvinator = iInventory;
	        sizeInventory = tileDissolvinator.func_70302_i_();
	        
	        CustomSlot slot = new CustomSlot(tileDissolvinator, TileDissolvinator.SlotEnum.INPUT_SLOT.ordinal(), 54, 24);
	        
	        slot.addInput(new ItemStack(ReduxBlocks.blockOreAluminum));
	        slot.addInput(new ItemStack(ReduxBlocks.blockOreCopper));
	        slot.addInput(new ItemStack(ReduxBlocks.blockOreLead));
	        slot.addInput(new ItemStack(ReduxBlocks.blockOreTin));
	        slot.addInput(new ItemStack(Blocks.field_150352_o));
	        slot.addInput(new ItemStack(Blocks.field_150366_p));
	        slot.addInput(new ItemStack(Blocks.field_150350_a));
	        
	        func_75146_a(slot);

	        slot = new CustomSlot(tileDissolvinator, TileDissolvinator.SlotEnum.OUTPUT_SLOT.ordinal(), 113, 24);
	        slot.setOutOnly(true);
	        func_75146_a(slot);
	        
	        slot = new CustomSlot(tileDissolvinator, TileDissolvinator.SlotEnum.FUEL_SLOT.ordinal(), 62, 57);
	        slot.addInput(ReduxItems.acidBucket);
	        slot.addInput(ReduxItems.itemVialAcid);
	        slot.addInput(new ItemStack(Blocks.field_150350_a));

	        func_75146_a(slot);
	        
	        slot = new CustomSlot(tileDissolvinator, TileDissolvinator.SlotEnum.OUTPUT_FUEL_SLOT.ordinal(), 98, 57);
	        slot.setOutOnly(true);
	        func_75146_a(slot);

	        int i;

	        // add hotbar slots
	        for (i = 0; i < 9; ++i) {
	            func_75146_a(new Slot(inventoryPlayer, i, 8 + i * 18, 142));
	        }

	        //add inventory slots
	        for (i = 0; i < 3; ++i) {
	            for (int j = 0; j < 9; ++j) {
	               func_75146_a(new Slot(inventoryPlayer, j + i * 9 + 9, 8 + j * 18, 85 + i * 18));
	            }
	        }
	    }

	    @Override
	    public ItemStack func_82846_b(EntityPlayer player, int index){
	    	
	    	int destIndex;
	    	ItemStack destStack = new ItemStack(Blocks.field_150350_a);
	    	ItemStack sourceStack = new ItemStack(Blocks.field_150350_a);
    		destIndex = -1;
    		final int sourceIndex = index;
	    	
	    	if(!player.func_130014_f_().field_72995_K) {
	    		
	    		destStack = ItemStack.field_190927_a;
	    		sourceStack = field_75153_a.get(sourceIndex);
    			boolean flag = false;
	    		
	    		//Processing slots vs inventory
	    		if(sourceIndex < hotbarSlots) {
	    			if(func_75135_a(sourceStack, hotbarSlots, 40, true)) {
	    				field_75151_b.get(sourceIndex).func_75215_d(sourceStack);
	    				//inventoryItemStacks.get(sourceIndex) = ItemStack.EMPTY;
	    			}
	    		}
	    		//otherslots vs input slots
	    		else{
    				//FIXME: Me no likey copypasta, makes bad meals
	    			destIndex = TileDissolvinator.SlotEnum.FUEL_SLOT.ordinal();
    				destStack = field_75153_a.get(destIndex);
    				if(field_75151_b.get(destIndex).func_75214_a(sourceStack)) {
    					if(destStack.func_77969_a(sourceStack) || destStack.func_190926_b()) {
    						if(destStack.func_190926_b()) {
    							destStack = sourceStack.func_77946_l();
    							sourceStack = ItemStack.field_190927_a;
    						}
    						else destStack.func_190917_f(sourceStack.func_190916_E());
    						if(destStack.func_190916_E() > destStack.func_77976_d()) {
    							sourceStack.func_190920_e(destStack.func_190916_E() - destStack.func_77976_d());
    						}
        					field_75151_b.get(destIndex).func_75215_d(destStack);
        					field_75151_b.get(sourceIndex).func_75215_d(sourceStack);
    					}
    				}
    				
	    			destIndex = TileDissolvinator.SlotEnum.INPUT_SLOT.ordinal();
    				destStack = field_75153_a.get(destIndex);
    				if(field_75151_b.get(destIndex).func_75214_a(sourceStack)) {
    					if(destStack.func_77969_a(sourceStack) || destStack.func_190926_b()) {
    						if(destStack.func_190926_b()) {
    							destStack = sourceStack.func_77946_l();
    							sourceStack = ItemStack.field_190927_a;
    						}
    						else destStack.func_190917_f(sourceStack.func_190916_E());
    						if(destStack.func_190916_E() > destStack.func_77976_d()) {
    							sourceStack.func_190920_e(destStack.func_190916_E() - destStack.func_77976_d());
    						}
        					field_75151_b.get(destIndex).func_75215_d(destStack);
        					field_75151_b.get(sourceIndex).func_75215_d(sourceStack);
    					}
    				}
    				if(flag) {
    				}
    			}
		    	func_75142_b();
    		}

	    	return sourceStack;
	    }
	    
	    @Override
		public boolean func_75145_c(EntityPlayer playerIn) {
			return true;
	    }
}
