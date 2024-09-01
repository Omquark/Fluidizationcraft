package com.omquark.fluidizationcraft.recipe;

import com.omquark.fluidizationcraft.FluidizationCraft;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModeRecipeSerializer {
    private ModeRecipeSerializer(){}

    public static final DeferredRegister<RecipeSerializer<?>> RECIPE_SERIALIZER = DeferredRegister.create(Registries.RECIPE_SERIALIZER, FluidizationCraft.MODID);

}
