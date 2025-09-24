package com.omquark.fluidizationcraft.data.fluid.interactions;

import com.google.gson.JsonObject;
import net.minecraft.resources.ResourceLocation;

import java.util.Map;

public record FluidInteractionJson(
        ResourceLocation fluid,
        Map<ResourceLocation, ResourceLocation> blockInteractions,
        Map<ResourceLocation, ResourceLocation> fluidInteractions
) {
    public JsonObject toJson() {
        JsonObject obj = new JsonObject();
        obj.addProperty("fluid", fluid.toString());

        JsonObject blocks = new JsonObject();
        blockInteractions.forEach((from, to) -> blocks.addProperty(from.toString(), to.toString()));
        obj.add("block_interactions", blocks);

        JsonObject fluids = new JsonObject();
        blockInteractions.forEach((from, to) -> fluids.addProperty(from.toString(), to.toString()));
        obj.add("fluid_interactions", blocks);

        return obj;
    }

}
