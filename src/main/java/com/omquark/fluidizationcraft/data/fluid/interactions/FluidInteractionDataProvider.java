package com.omquark.fluidizationcraft.data.fluid.interactions;

import com.omquark.fluidizationcraft.FluidizationCraft;
import net.minecraft.MethodsReturnNonnullByDefault;
import net.minecraft.data.CachedOutput;
import net.minecraft.data.DataProvider;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.material.Fluids;
import org.checkerframework.checker.nullness.qual.NonNull;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@MethodsReturnNonnullByDefault
public class FluidInteractionDataProvider implements DataProvider {
    private final PackOutput output;

    public FluidInteractionDataProvider(PackOutput output) {
        this.output = output;
    }

    @Override
    public CompletableFuture<?> run(@NonNull CachedOutput cache) {
        List<CompletableFuture<?>> futures = new ArrayList<>();

        FluidInteractionJson acid = new FluidInteractionBuilder(
                ResourceLocation.fromNamespaceAndPath(FluidizationCraft.MODID, "acid")
        )
                .block(Blocks.DIRT, Blocks.COARSE_DIRT)
                .block(Blocks.GRASS_BLOCK, Blocks.DIRT)
                .block(Blocks.STONE, Blocks.COBBLESTONE)
                .fluid(Fluids.WATER, Blocks.CLAY)
                .build();

        Path path = this.output.getOutputFolder(PackOutput.Target.DATA_PACK)
                .resolve(Path.of(FluidizationCraft.MODID, "fluid_interactions", "acid.json"));

        futures.add(DataProvider.saveStable(cache, acid.toJson(), path));

        return CompletableFuture.allOf(futures.toArray(CompletableFuture[]::new));
    }

    @Override
    public String getName() {
        return "Fluid Interactions";
    }
}
