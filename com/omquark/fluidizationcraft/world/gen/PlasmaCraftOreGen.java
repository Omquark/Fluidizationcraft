package com.omquark.fluidizationcraft.world.gen;

import java.util.Random;

import com.omquark.fluidizationcraft.config.Config;
import com.omquark.fluidizationcraft.init.ReduxBlocks;
import com.omquark.fluidizationcraft.init.ReduxItems;

import net.minecraft.block.state.IBlockState;
import net.minecraft.block.state.pattern.BlockMatcher;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraftforge.fml.common.IWorldGenerator;

public class PlasmaCraftOreGen implements IWorldGenerator{
	
	public PlasmaCraftOreGen() {
		
	}

	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator,
			IChunkProvider chunkProvider) {
		
		int x = chunkX * 16;
		int z = chunkZ * 16;
		
		if(world.field_73011_w.getDimension() == 0) { 
			genOreOverworld(ReduxBlocks.blockOreAluminum.func_176223_P(), world, random, chunkX * 16, chunkZ * 16, 
					Config.AluminumOreMinY, Config.AluminumOreMaxY, Config.AluminumOreSize);
			genOreOverworld(ReduxBlocks.blockOreCopper.func_176223_P(), world, random, chunkX * 16, chunkZ * 16, 
					Config.CopperOreMinY, Config.CopperOreMaxY, Config.CopperOreSize);
			genOreOverworld(ReduxBlocks.blockOreLead.func_176223_P(), world, random, chunkX * 16, chunkZ * 16, 
					Config.LeadOreMinY, Config.LeadOreMaxY, Config.LeadOreSize);
			genOreOverworld(ReduxBlocks.blockOreTin.func_176223_P(), world, random, chunkX * 16, chunkZ * 16, 
					Config.TinOreMinY, Config.TinOreMaxY, Config.TinOreSize);
		}
	}
	
	public void genOreOverworld(IBlockState blockState, World world, Random random, int x, int z,
								int minY, int maxY, int size) {
		
		
		

		WorldGenMinable ore = new WorldGenMinable(blockState, size, BlockMatcher.func_177642_a(Blocks.field_150348_b));
		int y;
		
		for(int i = 0; i < 10; i++) {
			y = random.nextInt(maxY - minY) + minY;
			int newX = x + random.nextInt(16);
			int newZ = z + random.nextInt(16);
			ore.func_180709_b(world, random, new BlockPos(newX, y, newZ));
		}
	}
}
