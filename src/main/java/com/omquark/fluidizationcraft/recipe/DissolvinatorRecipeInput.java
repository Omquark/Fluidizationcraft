package com.omquark.fluidizationcraft.recipe;

import com.omquark.fluidizationcraft.util.EverythingNonNullByDefault;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeInput;

@EverythingNonNullByDefault
public record DissolvinatorRecipeInput(ItemStack stack) implements RecipeInput {

    @Override
    public ItemStack getItem(int pIndex) {
        return stack;
    }

    @Override
    public int size() {
        return 1;
    }
}
