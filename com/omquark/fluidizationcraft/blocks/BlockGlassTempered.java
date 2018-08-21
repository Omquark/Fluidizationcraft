package com.omquark.fluidizationcraft.blocks;

import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockGlassTempered extends ReduxBaseBlock{

	public BlockGlassTempered(Material material, MapColor mapColor) {
		super(material, mapColor);
		field_149785_s = true;
		field_149786_r = 0;
	}
	
	@Override
	public boolean canHarvestBlock(IBlockAccess world, BlockPos pos, EntityPlayer player) {
		return false;
	}

	@Override
	public boolean canRenderInLayer(IBlockState state, BlockRenderLayer layer) {
		return layer == BlockRenderLayer.TRANSLUCENT;
	}
	
	@Override
	public boolean doesSideBlockRendering(IBlockState state, IBlockAccess blockAccess, BlockPos pos, EnumFacing facing) {
		if(blockAccess.func_180495_p(pos.func_177972_a(facing)).func_185914_p()) {
			return false;
		}
		return true;
	}
	
	public boolean func_149662_c(IBlockState state) {
		return false;
	}
	

	@Override
	public int getLightOpacity(IBlockState state, IBlockAccess world, BlockPos pos) {
		return 3;
	}
	
	@Override
	public boolean func_149751_l(IBlockState state) {
		return true;
	}
}
