package com.omquark.fluidizationcraft.client.model;

import org.lwjgl.opengl.GL11;

import com.omquark.fluidizationcraft.PlasmaCraftRedux;
import com.omquark.fluidizationcraft.Entities.EntityAcidShot;
import com.omquark.fluidizationcraft.Entities.EntityCryoBlast;
import com.omquark.fluidizationcraft.util.Constants;

import net.minecraft.client.model.ModelPig;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderArrow;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.RenderTippedArrow;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.entity.Entity;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.entity.projectile.EntityTippedArrow;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderBolt<T extends EntityThrowable> extends Render<EntityThrowable> {
	private static ResourceLocation ENTITY_TEXTURE;

	public RenderBolt(RenderManager renderManager) {
		super(renderManager);
		ENTITY_TEXTURE = new ResourceLocation(PlasmaCraftRedux.MODID, "textures/entity/railgunbolt.png");
	}

	public RenderBolt(RenderManager renderManager, ResourceLocation boltTexture) {
		this(renderManager);
		ENTITY_TEXTURE = boltTexture;
	}
	
	@Override
	protected ResourceLocation func_110775_a(EntityThrowable entity) {
		return ENTITY_TEXTURE;
	}
	
	@Override
	public void func_76986_a(EntityThrowable entity, double x, double y, double z, float entityYaw, float partialTicks) {

		//This should not need changed, mostly, and is mostly self explanatory
        this.func_180548_c(entity);
        if(entity instanceof EntityCryoBlast) {
        	GlStateManager.func_179131_c(0.1f, 0.1f, 1.0f, 1.0f);
        }
        if(entity instanceof EntityAcidShot) {
    		GlStateManager.func_179131_c(0.1F, 1.0F, 0.1F, 1.0F);
        }
        else {
        	GlStateManager.func_179131_c(1.0F, 1.0F, 1.0F, 1.0F);
        }
        //Make sure to pop for every push!
        GlStateManager.func_179094_E();
        GlStateManager.func_179140_f();
        GlStateManager.func_179109_b((float)x, (float)y, (float)z);
        GlStateManager.func_179114_b(entity.field_70126_B + (entity.field_70177_z - entity.field_70126_B) * partialTicks - 90.0F, 0.0F, 1.0F, 0.0F);
        GlStateManager.func_179114_b(entity.field_70127_C + (entity.field_70125_A - entity.field_70127_C) * partialTicks, 0.0F, 0.0F, 1.0F);
        Tessellator tessellator = Tessellator.func_178181_a();
        BufferBuilder bufferbuilder = tessellator.func_178180_c();
        GlStateManager.func_179091_B();

        GlStateManager.func_179114_b(45.0F, 1.0F, 0.0F, 0.0F);
        GlStateManager.func_179152_a(0.15625F, 0.15625F, 0.15625F);
//        GlStateManager.scale(0.05625F, 0.05625F, 0.05625F);
        GlStateManager.func_179109_b(-4.0F, 0.0F, 0.0F);

        if (this.field_188301_f)
        {
            GlStateManager.func_179142_g();
            GlStateManager.func_187431_e(this.func_188298_c(entity));
        }

        GlStateManager.func_187432_a(0.05625F, 0.0F, 0.0F);
        bufferbuilder.func_181668_a(7, DefaultVertexFormats.field_181707_g);
        bufferbuilder.func_181662_b(-6.0D, -2.0D, -2.0D).func_187315_a(0.0D, 0.15625D).func_181675_d();
        bufferbuilder.func_181662_b(-6.0D, -2.0D, 2.0D).func_187315_a(0.15625D, 0.15625D).func_181675_d();
        bufferbuilder.func_181662_b(-6.0D, 2.0D, 2.0D).func_187315_a(0.15625D, 0.3125D).func_181675_d();
        bufferbuilder.func_181662_b(-6.0D, 2.0D, -2.0D).func_187315_a(0.0D, 0.3125D).func_181675_d();
        tessellator.func_78381_a();
        
        GlStateManager.func_187432_a(-0.05625F, 0.0F, 0.0F);
        bufferbuilder.func_181668_a(7, DefaultVertexFormats.field_181707_g);
        bufferbuilder.func_181662_b(-1.0D, 2.0D, -2.0D).func_187315_a(0.0D, 0.15625D).func_181675_d();
        bufferbuilder.func_181662_b(-1.0D, 2.0D, 2.0D).func_187315_a(0.15625D, 0.15625D).func_181675_d();
        bufferbuilder.func_181662_b(-1.0D, -2.0D, 2.0D).func_187315_a(0.15625D, 0.3125D).func_181675_d();
        bufferbuilder.func_181662_b(-1.0D, -2.0D, -2.0D).func_187315_a(0.0D, 0.3125D).func_181675_d();
        tessellator.func_78381_a();

        for (int j = 0; j < 4; ++j)
        {
            GlStateManager.func_179114_b(90.0F, 1.0F, 0.0F, 0.0F);
            GlStateManager.func_187432_a(0.0F, 0.0F, 0.05625F);
            bufferbuilder.func_181668_a(7, DefaultVertexFormats.field_181707_g);
            bufferbuilder.func_181662_b(-8.0D, -2.0D, 0.0D).func_187315_a(0.0D, 0.0D).func_181675_d();
            bufferbuilder.func_181662_b(8.0D, -2.0D, 0.0D).func_187315_a(0.5D, 0.0D).func_181675_d();
            bufferbuilder.func_181662_b(8.0D, 2.0D, 0.0D).func_187315_a(0.5D, 0.15625D).func_181675_d();
            bufferbuilder.func_181662_b(-8.0D, 2.0D, 0.0D).func_187315_a(0.0D, 0.15625D).func_181675_d();
            tessellator.func_78381_a();
        }

        if (this.field_188301_f)
        {
            GlStateManager.func_187417_n();
            GlStateManager.func_179119_h();
        }

        GlStateManager.func_179101_C();
        GlStateManager.func_179145_e();
        GlStateManager.func_179121_F();
        super.func_76986_a(entity, x, y, z, entityYaw, partialTicks);
    }
}
