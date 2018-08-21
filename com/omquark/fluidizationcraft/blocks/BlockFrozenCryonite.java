package com.omquark.fluidizationcraft.blocks;

import java.util.Random;

import com.omquark.fluidizationcraft.Fluids.AcidFluidBlock;
import com.omquark.fluidizationcraft.init.ReduxFluids;

import net.minecraft.block.SoundType;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BlockFrozenCryonite extends ReduxBaseBlock{
		
	public BlockFrozenCryonite(Material material, MapColor mapColor){
		super(material, mapColor);
		func_149672_a(SoundType.field_185851_d);
		func_149675_a(true);
	}
	
	@Override
	public void func_180650_b(World world, BlockPos pos, IBlockState state, Random rand) {
		if(world.func_180494_b(pos).func_185353_n() > 0f) {
			if(rand.nextInt(100) < 101) {
				world.func_175656_a(pos, ReduxFluids.cryoniteFluidBlock.func_176223_P());
			}
		}
	}

	@Override
	public void func_180645_a(World world, BlockPos pos, IBlockState state, Random rand) {
		if(world.func_180494_b(pos).func_185353_n() > 0.15f) {
			if(rand.nextInt(100) < 30  && world.func_175710_j(pos)) {
				world.func_175656_a(pos, ReduxFluids.cryoniteFluidBlock.func_176223_P());
			}
		}
	}
}
