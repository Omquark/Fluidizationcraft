package com.omquark.fluidizationcraft.blocks;

import java.util.Random;

import com.omquark.fluidizationcraft.Fluids.AcidFluidBlock;

import net.minecraft.block.SoundType;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BlockFrozenAcid extends ReduxBaseBlock 
{
	
	private static int cooldown;
	
	public BlockFrozenAcid(Material material, MapColor mapColor){
		super(material, mapColor);
		func_149672_a(SoundType.field_185851_d);
		cooldown = 0;
	}
	
	@Override
	public void func_180650_b(World world, BlockPos pos, IBlockState state, Random rand) {

	}
	
	@Override
	public void func_176199_a(World world, BlockPos pos, Entity entity) {
		if(cooldown <= 0) {
			if(entity instanceof EntityLiving && !entity.func_190530_aW()) {
				entity.func_70097_a(AcidFluidBlock.acidDamage, 1.0f);
				cooldown = 20;
			}
		}
		else {
			cooldown--;
		}
	}
	
	@Override
	public void func_180634_a(World world, BlockPos pos, IBlockState state, Entity entity) {
		if(cooldown <= 0) {
			if(entity instanceof EntityLiving && !entity.func_190530_aW()) {
				entity.func_70097_a(AcidFluidBlock.acidDamage, 1.0f);
			}
		}
		else {
			cooldown--;
		}
	}
}
