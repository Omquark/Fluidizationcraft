package com.omquark.fluidizationcraft.recipe;

import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.AdvancementRequirements;
import net.minecraft.advancements.AdvancementRewards;
import net.minecraft.advancements.critereon.RecipeUnlockedTrigger;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.neoforged.neoforge.common.crafting.SizedIngredient;

public class DissolvinatorRecipeBuilder extends SimpleRecipeBuilder {
    private final SizedIngredient inputItem;

    public DissolvinatorRecipeBuilder(SizedIngredient inputItem, ItemStack result) {
        super(result);
        this.inputItem = inputItem;
    }

    @Override
    public void save(RecipeOutput pRecipeOutput, ResourceLocation pId) {
        Advancement.Builder advancement = pRecipeOutput.advancement()
                .addCriterion("has_the_recipe", RecipeUnlockedTrigger.unlocked(pId))
                .rewards(AdvancementRewards.Builder.recipe(pId))
                .requirements(AdvancementRequirements.Strategy.OR);
        this.criteria.forEach(advancement::addCriterion);
        DissolvinatorRecipe recipe = new DissolvinatorRecipe(this.inputItem, this.result);
        pRecipeOutput.accept(pId, recipe, advancement.build(pId.withPrefix("recipe/")));
    }
}
