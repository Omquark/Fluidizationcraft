package com.omquark.fluidizationcraft.capabilities;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.omquark.fluidizationcraft.data.items.FluidShooter;
import it.unimi.dsi.fastutil.bytes.Byte2BooleanArrayMap;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.material.Fluids;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;
import javax.swing.text.html.Option;
import java.util.Optional;

public record FluidShooterState(
        @NotNull Optional<ItemStack> input,
        @NotNull Optional<ItemStack> output,
        @Nullable ResourceLocation fluidId,
        int amount
) {
    //    public static final FluidShooterState EMPTY = new FluidShooterState(ItemStack.EMPTY, ItemStack.EMPTY, null, 0);
    public static final FluidShooterState EMPTY = new FluidShooterState(Optional.empty(), Optional.empty(), null, 0);

    public static final Codec<FluidShooterState> CODEC = RecordCodecBuilder.create(inst -> inst.group(
            ItemStack.CODEC.optionalFieldOf("input").forGetter(FluidShooterState::input),
            ItemStack.CODEC.optionalFieldOf("output").forGetter(FluidShooterState::output),
            ResourceLocation.CODEC.optionalFieldOf("fluid").forGetter(s -> Optional.ofNullable(s.fluidId())),
            Codec.INT.fieldOf("amount").forGetter(FluidShooterState::amount)
    ).apply(inst, (input, output, fluid, amount) ->
            new FluidShooterState(input, output, fluid.orElse(null), amount)));

    public static final StreamCodec<RegistryFriendlyByteBuf, FluidShooterState> STREAM_CODEC = StreamCodec.of((buf, state) -> {

                ByteBufCodecs.optional(ItemStack.STREAM_CODEC).encode(buf, state.input());
                ByteBufCodecs.optional(ItemStack.STREAM_CODEC).encode(buf, state.output());

//                buf.writeOptional(state.input(), ByteBufCodecs.fromCodec(ItemStack.OPTIONAL_CODEC));
//                buf.writeOptional(state.output(), ByteBufCodecs.fromCodec(ItemStack.OPTIONAL_CODEC));
//                ItemStack.STREAM_CODEC.encode(buf, state.input().orElse(ItemStack.EMPTY));
//                ItemStack.STREAM_CODEC.encode(buf, state.output().orElse(ItemStack.EMPTY));
                ByteBufCodecs.optional(ResourceLocation.STREAM_CODEC).encode(buf, Optional.ofNullable(state.fluidId()));
                buf.writeInt(state.amount);
            },
            buf -> {
                Optional<ItemStack> input = ByteBufCodecs.optional(ItemStack.STREAM_CODEC).decode(buf);
                Optional<ItemStack> output = ByteBufCodecs.optional(ItemStack.STREAM_CODEC).decode(buf);
//                Optional<ItemStack> input = buf.readOptional(ByteBufCodecs.fromCodec(ItemStack.OPTIONAL_CODEC));
//                Optional<ItemStack> output = buf.readOptional(ByteBufCodecs.fromCodec(ItemStack.OPTIONAL_CODEC));
//                ItemStack input = ItemStack.STREAM_CODEC.decode(buf);
//                ItemStack output = ItemStack.STREAM_CODEC.decode(buf);
                Optional<ResourceLocation> fluid = ByteBufCodecs.optional(ResourceLocation.STREAM_CODEC).decode(buf);
                int amount = buf.readInt();
                return new FluidShooterState(input, output, fluid.orElse(null), amount);
            });
}
