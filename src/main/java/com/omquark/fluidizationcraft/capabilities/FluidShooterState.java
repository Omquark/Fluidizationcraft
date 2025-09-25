package com.omquark.fluidizationcraft.capabilities;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;

import javax.annotation.Nullable;
import java.util.Optional;

public record FluidShooterState(
        @Nullable ItemStack input,
        @Nullable ItemStack output,
        @Nullable ResourceLocation fluidId,
        int amount
) {
    public static final FluidShooterState EMPTY = new FluidShooterState(ItemStack.EMPTY, ItemStack.EMPTY, null, 0);

    public static final Codec<FluidShooterState> CODEC = RecordCodecBuilder.create(inst -> inst.group(
            ItemStack.CODEC.optionalFieldOf("input", ItemStack.EMPTY).forGetter(FluidShooterState::input),
            ItemStack.CODEC.optionalFieldOf("output", ItemStack.EMPTY).forGetter(FluidShooterState::output),
            ResourceLocation.CODEC.optionalFieldOf("fluid").forGetter(s -> Optional.ofNullable(s.fluidId())),
            Codec.INT.fieldOf("amount").forGetter(FluidShooterState::amount)
    ).apply(inst, (input, output, fluid, amount) ->
            new FluidShooterState(input, output, fluid.orElse(null), amount)));

    public static final StreamCodec<RegistryFriendlyByteBuf, FluidShooterState> STREAM_CODEC = StreamCodec.of((buf, state) -> {
                ItemStack.STREAM_CODEC.encode(buf, state.input);
                ItemStack.STREAM_CODEC.encode(buf, state.output);
                ByteBufCodecs.optional(ResourceLocation.STREAM_CODEC).encode(buf, Optional.ofNullable((state.fluidId())));
                buf.writeInt(state.amount);
            },
            buf -> {
                ItemStack input = ItemStack.STREAM_CODEC.decode(buf);
                ItemStack output = ItemStack.STREAM_CODEC.decode(buf);
                Optional<ResourceLocation> fluid = ByteBufCodecs.optional(ResourceLocation.STREAM_CODEC).decode(buf);
                int amount = buf.readInt();
                return new FluidShooterState(input, output, fluid.orElse(null), amount);
            });
}
