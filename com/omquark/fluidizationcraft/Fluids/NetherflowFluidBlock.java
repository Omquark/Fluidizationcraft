package com.omquark.fluidizationcraft.Fluids;

import java.util.Random;

import com.omquark.fluidizationcraft.Fluids.ReduxBaseFluid.FluidDamageValues;
import com.omquark.fluidizationcraft.config.Config;
import com.omquark.fluidizationcraft.init.ReduxFluids;
import com.omquark.fluidizationcraft.items.ItemVial;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLiving;
import net.minecraft.init.Blocks;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fluids.Fluid;

public class NetherflowFluidBlock extends ReduxBaseFluid {

	public NetherflowFluidBlock(Fluid fluid, Material material) {
		super(fluid, material);
		
		addFluidInteraction(Blocks.field_150355_j, Blocks.field_150424_aL.func_176223_P());
		//addFluidInteraction(Blocks.LAVA, Blocks.NETHERRACK.getDefaultState());
		addFluidInteraction(ReduxFluids.neptuniumFluidBlock, Blocks.field_150424_aL.func_176223_P());

		addBlockInteraction(Blocks.field_150343_Z.func_176223_P(), Blocks.field_150424_aL.func_176223_P(), false);
		
		addBlockInteraction(Blocks.field_150348_b.func_176223_P(), Blocks.field_150424_aL.func_176223_P(),  true);
		addBlockInteraction(Blocks.field_150351_n.func_176223_P(), Blocks.field_150424_aL.func_176223_P(),  true);
		addBlockInteraction(Blocks.field_150354_m.func_176223_P(), Blocks.field_150424_aL.func_176223_P(),  true);
		
		damageValue = FluidDamageValues.NETHERFLOW_DAMAGE_VALUE.ordinal();
	}
	
	@Override
    public void func_180634_a(World world, BlockPos pos, IBlockState state, Entity entity){
		entityCollideCheck(world, pos, state, entity, netherflowDamage);
	}
}
