package com.omquark.fluidizationcraft.Entities;

import javax.annotation.Nullable;

import com.omquark.fluidizationcraft.config.Config;
import com.omquark.fluidizationcraft.init.ReduxFluids;
import com.omquark.fluidizationcraft.util.ModExplosion;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.IThrowableEntity;

public class EntityAcidShot extends EntityModArrow{
	
	public EntityAcidShot(World worldIn) {
		super(worldIn);
		init();
	}

	public EntityAcidShot(World worldIn, EntityLivingBase shooter) {
		super(worldIn, shooter);
		init();
	}
	
	private void init() {
	}
	
	//Unaffected by water
	@Override
	public boolean func_70090_H() {
		return false;
	}

	@Override
	protected void func_70184_a(RayTraceResult result) {
		
		if(result.field_72313_a == RayTraceResult.Type.ENTITY) {
			if(delay > 0) {
				return;
			}
		}
		
		double hitX = result.field_72307_f.field_72450_a;
		double hitY = result.field_72307_f.field_72448_b;
		double hitZ = result.field_72307_f.field_72449_c;

		BlockPos pos = null;
		if(result.field_72313_a == RayTraceResult.Type.ENTITY) {
			pos = new BlockPos(hitX, hitY, hitZ);
		}
		else if(result.field_72313_a == RayTraceResult.Type.BLOCK) {
			pos = result.func_178782_a().func_177972_a(result.field_178784_b);
		}
		if(pos != null) {
			if(field_70170_p.func_180495_p(pos).func_177230_c() == Blocks.field_150350_a) {
				field_70170_p.func_175656_a(pos, ReduxFluids.acidFluidBlock.func_176223_P());
			}
		}
		func_70106_y();
	}
}
