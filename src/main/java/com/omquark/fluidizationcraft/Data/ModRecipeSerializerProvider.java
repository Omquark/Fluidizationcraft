package com.omquark.fluidizationcraft.data;

import com.omquark.fluidizationcraft.FluidizationCraft;
import com.omquark.fluidizationcraft.recipe.DissolvinatorRecipe;
import com.omquark.fluidizationcraft.recipe.DissolvinatorRecipeSerializer;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModRecipeSerializerProvider {

    public static final DeferredRegister<RecipeSerializer<?>> RECIPE_SERIALIZERS =
            DeferredRegister.create(Registries.RECIPE_SERIALIZER, FluidizationCraft.MODID);

    public static final Supplier<RecipeSerializer<DissolvinatorRecipe>> DISSOLVINATOR_SERIALIZER =
            RECIPE_SERIALIZERS.register("dissolvinator_recipe", DissolvinatorRecipeSerializer::new);

    public static void register(IEventBus eventBus){
        RECIPE_SERIALIZERS.register(eventBus);
    }
}
