package com.omquark.fluidizationcraft.Entities;

import net.minecraft.entity.Entity;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

public abstract class BaseEntity extends Entity{

	public BaseEntity(World worldIn) {
		super(worldIn);
	}

	@Override
	protected void func_70088_a() {
	}

	@Override
	protected void func_70037_a(NBTTagCompound compound) {
		super.func_70020_e(compound);
	}

	@Override
	protected void func_70014_b(NBTTagCompound compound) {
		super.func_189511_e(compound);
	}
}
