package com.omquark.fluidizationcraft.data.items;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.NonNullList;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.item.ItemStack;

public record FluidShooter(ItemStack input, ItemStack output, int amount) {


    public static final Codec<FluidShooter> CODEC = RecordCodecBuilder.create(inst ->
            inst.group(
                    ItemStack.CODEC.fieldOf("input_item").forGetter(item -> item.input),
                    ItemStack.CODEC.fieldOf("output_item").forGetter(item -> item.output),
                    Codec.INT.fieldOf("amount").forGetter(item -> item.amount)
            ).apply(inst, FluidShooter::new)
    );

    public static final StreamCodec<RegistryFriendlyByteBuf, FluidShooter> STREAM_CODEC = StreamCodec.composite(
            ItemStack.STREAM_CODEC, FluidShooter::input,
            ItemStack.STREAM_CODEC, FluidShooter::output,
            ByteBufCodecs.INT, FluidShooter::amount,
            FluidShooter::new
    );

    public static FluidShooter fromItems(ItemStack input, ItemStack output, int amount){
        return new FluidShooter(input, output, amount);
    }

    public FluidShooter updateSlot(FluidShooter.Slot slot){
        ItemStack input = slot.index == 0 ? slot.item : ItemStack.EMPTY;
        ItemStack output = slot.index == 1 ? slot.item : ItemStack.EMPTY;
        int amount = 0;

        return new FluidShooter(input, output, amount);
    }

    public record Slot(int index, ItemStack item){
        public static final Codec<FluidShooter.Slot> CODEC = RecordCodecBuilder.create(inst ->
                inst.group(
                        Codec.intRange(0, 1).fieldOf("slot").forGetter(FluidShooter.Slot::index),
                        ItemStack.OPTIONAL_CODEC.fieldOf("item").forGetter(FluidShooter.Slot::item)
                ).apply(inst, FluidShooter.Slot::new));
    }
}
