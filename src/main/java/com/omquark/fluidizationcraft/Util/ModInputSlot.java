package com.omquark.fluidizationcraft.util;

import com.omquark.fluidizationcraft.Items.FluidizationItems;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.items.IItemHandler;
import net.neoforged.neoforge.items.SlotItemHandler;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ModInputSlot extends SlotItemHandler {

    Set<Item> allowedItems = new HashSet<>();

    public ModInputSlot(IItemHandler itemHandler, int index, int xPosition, int yPosition) {
        super(itemHandler, index, xPosition, yPosition);
    }

    public void addAllowableItem(Item allowedItem){
        allowedItems.add(allowedItem);
    }

    public void addAllowableItems(List<Item> allowedItems){
        this.allowedItems.addAll(allowedItems);
    }

    @Override
    public boolean mayPlace(ItemStack stack) {
        return allowedItems.contains(stack.getItem());
    }
}
