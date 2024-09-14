package com.omquark.fluidizationcraft.recipe;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.omquark.fluidizationcraft.util.EverythingNonNullByDefault;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.neoforged.neoforge.common.crafting.SizedIngredient;

@EverythingNonNullByDefault
public class DissolvinatorRecipeSerializer implements RecipeSerializer<DissolvinatorRecipe>{

    public static final MapCodec<DissolvinatorRecipe> CODEC = RecordCodecBuilder.mapCodec(inst -> inst.group(
            SizedIngredient.FLAT_CODEC.fieldOf("ingredient").forGetter(DissolvinatorRecipe::getInputItem),
            ItemStack.CODEC.fieldOf("result").forGetter(DissolvinatorRecipe::getResult))
            .apply(inst, DissolvinatorRecipe::new));

    public static final StreamCodec<RegistryFriendlyByteBuf, DissolvinatorRecipe> STREAM_CODEC =
            StreamCodec.composite(
                    SizedIngredient.STREAM_CODEC, DissolvinatorRecipe::getInputItem,
                    ItemStack.STREAM_CODEC, DissolvinatorRecipe::getResult,
                    DissolvinatorRecipe::new
            );

    @Override
    public MapCodec<DissolvinatorRecipe> codec() {
        return CODEC;
    }

    @Override
    public StreamCodec<RegistryFriendlyByteBuf, DissolvinatorRecipe> streamCodec() {
        return STREAM_CODEC;
    }
}
