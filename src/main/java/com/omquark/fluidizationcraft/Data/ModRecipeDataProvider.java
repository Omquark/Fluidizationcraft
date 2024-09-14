package com.omquark.fluidizationcraft.data;

import com.omquark.fluidizationcraft.FluidizationCraft;
import com.omquark.fluidizationcraft.recipe.DissolvinatorRecipe;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.RecipeType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModRecipeDataProvider {

    public static final DeferredRegister<RecipeType<?>> RECIPE_TYPES =
            DeferredRegister.create(Registries.RECIPE_TYPE, FluidizationCraft.MODID);

    public static final Supplier<RecipeType<DissolvinatorRecipe>> DISSOLVINATOR_RECIPE =
            RECIPE_TYPES.register(
                    "dissolvinator_recipe",
                    () -> RecipeType.simple(ResourceLocation.fromNamespaceAndPath(FluidizationCraft.MODID,
                            "dissolvinator_recipe"))
            );

    public static void register(IEventBus eventBus){
        RECIPE_TYPES.register(eventBus);
    }
}
