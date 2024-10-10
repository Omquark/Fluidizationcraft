package com.omquark.fluidizationcraft.recipe;

import com.omquark.fluidizationcraft.data.ModRecipeDataProvider;
import com.omquark.fluidizationcraft.data.ModRecipeSerializerProvider;
import com.omquark.fluidizationcraft.util.EverythingNonNullByDefault;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.NonNullList;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.Level;
import net.neoforged.neoforge.common.crafting.SizedIngredient;


@EverythingNonNullByDefault
public class DissolvinatorRecipe implements Recipe<DissolvinatorRecipeInput> {

    private final SizedIngredient inputItem;
    private final ItemStack result;

    public DissolvinatorRecipe(SizedIngredient input, ItemStack output){
        this.inputItem = input;
        this.result = output;
    }

    public SizedIngredient getInputItem() {
        return inputItem;
    }

    @Override
    public NonNullList<Ingredient> getIngredients() {
        NonNullList<Ingredient> list = NonNullList.create();
        list.add(inputItem.ingredient());
        return list;
    }

    @Override
    public boolean matches(DissolvinatorRecipeInput pInput, Level pLevel) {
        return this.inputItem.test(pInput.getItem(0));
    }

    @Override
    public ItemStack assemble(DissolvinatorRecipeInput pInput, HolderLookup.Provider pRegistries) {
        return this.result.copy();
    }

    @Override
    public boolean canCraftInDimensions(int pWidth, int pHeight) {
        return pWidth * pHeight >= 1;
    }

    public ItemStack getResult() {
        return result;
    }

    @Override
    public ItemStack getResultItem(HolderLookup.Provider pRegistries) {
        return this.result;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return ModRecipeSerializerProvider.DISSOLVINATOR_SERIALIZER.get();
    }

    @Override
    public RecipeType<?> getType() {
        return ModRecipeDataProvider.DISSOLVINATOR_RECIPE.get();
    }
}
