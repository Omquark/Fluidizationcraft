package com.omquark.fluidizationcraft.world.gen;

import java.util.Random;

import com.omquark.fluidizationcraft.config.Config;
import com.omquark.fluidizationcraft.init.ReduxFluids;

import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraft.world.gen.feature.WorldGenLakes;
import net.minecraft.world.gen.feature.WorldGenLiquids;
import net.minecraftforge.fml.common.IWorldGenerator;

public class PlasmaCraftLakesGen implements IWorldGenerator{

	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator,
			IChunkProvider chunkProvider) {
		
		Random rand = new Random(System.currentTimeMillis());
		
		if(world.field_73011_w.getDimension() == 0) {
			genLake(ReduxFluids.acidFluidBlock.func_176223_P(), world, rand, chunkX * 16 + 8, chunkZ * 16 + 8, 
					Config.AcidLakeMinY, Config.AcidLakeMaxY, Config.AcidLakeCount, Config.AcidLakeChance);
			
			genLiquid(ReduxFluids.acidFluidBlock.func_176223_P(), world, rand, chunkX * 16 + 8, chunkZ * 16 + 8, 
					Config.AcidSpoutMinY, Config.AcidSpoutMaxY, Config.AcidSpoutCount, Config.AcidSpoutChance);
			
			if((!(Config.CryoniteColdOnly)) || world.func_180494_b(new BlockPos((chunkX * 16) + 8, 80, (chunkZ * 16) + 8)).func_185353_n() <= 0.15f) {
				genLake(ReduxFluids.cryoniteFluidBlock.func_176223_P(), world, rand, chunkX * 16 + 8, chunkZ * 16 + 8, 
						Config.CryoniteLakeMinY, Config.CryoniteLakeMaxY, Config.CryoniteLakeCount, Config.CryoniteLakeChance);
			}
		}
		else if(world.field_73011_w.getDimension() == -1){
			genLake(ReduxFluids.netherflowFluidBlock.func_176223_P(), world, world.field_73012_v, chunkX * 16, chunkZ * 16,
					Config.NetherflowLakeMinY, Config.NetherflowLakeMaxY, Config.NetherflowLakeCount, Config.NetherflowLakeChance);
		}
		else if(world.field_73011_w.getDimension() == 1){
			
		}
	}
	
	public void genLake(IBlockState blockState, World world, Random random, int x, int z, int minY, int maxY, int count, int chance) {
		
		int y = random.nextInt(maxY - minY) + minY;
		WorldGenLakes wgl = new WorldGenLakes(blockState.func_177230_c());
		int lakeCount = 0;
		
		if(Config.Debug) System.out.println("Lake @ " + (x + 8) + ", " + y + ", " + (z + 8));
		
		for(int i = 0; i <= 2; i++) {
			if(random.nextInt(100) <= chance) {
				wgl.func_180709_b(world, random, new BlockPos(x + 8, y, z + 8));
				lakeCount++;
			}
		}
	}
	
	public void genLiquid(IBlockState blockState, World world, Random random, int x, int z, int minY, int maxY, int count, int chance) {
		int y = random.nextInt(maxY - minY) + minY;
		WorldGenLiquids liq = new WorldGenLiquids(blockState.func_177230_c());
		
		if(Config.Debug) System.out.println("Lake @ " + (x + 8) + ", " + y + ", " + (z + 8));
		
		for(int i = 0; i < count; i++) { 
			if(random.nextInt(100) <= chance) {
				liq.func_180709_b(world, random, new BlockPos(x + 8, y, z + 8));
			}
		}
		
	}
}
