package com.omquark.fluidizationcraft.Entities;

import javax.annotation.Nullable;

import com.omquark.fluidizationcraft.config.Config;
import com.omquark.fluidizationcraft.util.ModExplosion;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.Vec3i;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.IThrowableEntity;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class EntityCryoBlast extends EntityModArrow{
	
    private int lifeSpan; //Time to live, in ticks

	public EntityCryoBlast(World worldIn) {
		super(worldIn);
		init();
	}

	public EntityCryoBlast(World worldIn, EntityLivingBase shooter) {
		super(worldIn, shooter);
		init();
	}
	
	private void init() {
		//todo: if no gravity is set, this entity must have a life span! Probably should have one anyway...
		//setNoGravity(true); 
		lifeSpan = Config.CryoBlastLifeSpan;
		if(lifeSpan > 50) lifeSpan = 50;
	}
	
	//Unaffected by water
	@Override
	public boolean func_70090_H() {
		return false;
	}

	/**
	 * This function will only produce an explosion, and links into the forge event system
	 * to allow cancellation where applicable
	 * 
	 * @param result - Where the explosion happens. If result is null, it will use the entity's current position
	 */
	protected void doExplosion(@Nullable RayTraceResult result) {

		double hitX = field_70165_t;
		double hitY = field_70163_u;
		double hitZ = field_70161_v;
		
		if(result != null) {
			hitX = result.field_72307_f.field_72450_a;
			hitY = result.field_72307_f.field_72448_b;
			hitZ = result.field_72307_f.field_72449_c;
		}
		
		ModExplosion explode = new ModExplosion(field_70170_p, this, hitX, hitY, hitZ, Config.CryoBlastRadius, false, true);

		//System.out.println(Blocks.DIRT.getExplosionResistance(world, new BlockPos(result.hitVec.x, result.hitVec.y, result.hitVec.z), this, explode));

		if(!net.minecraftforge.event.ForgeEventFactory.onExplosionStart(field_70170_p, explode)) {
			explode.func_77278_a();
			explode.doModExplosionB(false, true);
		}
		
	}

	@Override
	protected void func_70184_a(RayTraceResult result) {		
		
		if(result.field_72313_a == RayTraceResult.Type.ENTITY) {
			if(delay > 0) {
				return;
			}
		}

		doExplosion(result);
		func_70106_y();
	}
}
