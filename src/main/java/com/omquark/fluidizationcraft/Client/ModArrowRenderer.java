package com.omquark.fluidizationcraft.client;

import com.omquark.fluidizationcraft.FluidizationCraft;
import net.minecraft.MethodsReturnNonnullByDefault;
import net.minecraft.client.renderer.entity.ArrowRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.projectile.AbstractArrow;

import javax.annotation.ParametersAreNonnullByDefault;
import java.io.FileNotFoundException;

@MethodsReturnNonnullByDefault
@ParametersAreNonnullByDefault
public class ModArrowRenderer extends ArrowRenderer<AbstractArrow> {
    private final String resourceLocation;

    public ModArrowRenderer(EntityRendererProvider.Context context, String resourceLocation) {
        super(context);
        this.resourceLocation = resourceLocation;
    }

    @Override
    public ResourceLocation getTextureLocation(AbstractArrow renderer) {
        ResourceLocation textureLocation = ResourceLocation.tryBuild(FluidizationCraft.MODID, resourceLocation);
        assert textureLocation != null;
        return textureLocation;
    }
}
