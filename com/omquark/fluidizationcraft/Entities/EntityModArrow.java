package com.omquark.fluidizationcraft.Entities;

import com.omquark.fluidizationcraft.init.ReduxItems;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.stats.StatList;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.IThrowableEntity;

public abstract class EntityModArrow extends EntityThrowable{
	
	public EntityLivingBase throwingEntity;
	protected int delay = 0;

	public EntityModArrow(World worldIn) {
		super(worldIn);
		init();
	}
	
	public EntityModArrow(World world, EntityLivingBase shooter) {
		super(world, shooter);
		init();
	}

	/**
	 * Inits variables into what they should be for this particular entity
	 * Simply call from the constructor(s)
	 */
	private void init() {
		delay = 5;
	}
	
	public void func_70071_h_() {
		delay--;
		super.func_70071_h_();
	}

	@Override
	protected void func_70088_a() {
		super.func_70088_a();
	}

	@Override
	public void func_70037_a(NBTTagCompound compound) {
		super.func_70037_a(compound);
	}

	@Override
	public void func_70014_b(NBTTagCompound compound) {
		super.func_70014_b(compound);
	}
	
	public void setHeadingFromThrower(EntityLivingBase entityThrower, float pitch, float yaw, float pitchOffset, float velocity, float inaccuracy) {
		super.func_184538_a(entityThrower, pitch, yaw, pitchOffset, velocity, inaccuracy);
	}
}
