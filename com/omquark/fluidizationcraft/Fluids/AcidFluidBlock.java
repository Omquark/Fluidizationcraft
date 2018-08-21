package com.omquark.fluidizationcraft.Fluids;

import java.util.Timer;

import com.omquark.fluidizationcraft.Fluids.ReduxBaseFluid.FluidDamageValues;
import com.omquark.fluidizationcraft.config.Config;
import com.omquark.fluidizationcraft.init.ReduxBlocks;
import com.omquark.fluidizationcraft.items.ItemVial;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockDirt;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.statemap.StateMap;
import net.minecraft.entity.Entity;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.init.Blocks;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraft.world.Explosion;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.IFluidBlock;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import net.minecraft.block.properties.*;

public class AcidFluidBlock extends ReduxBaseFluid{

	public AcidFluidBlock(Fluid fluid, Material material) {
		super(fluid, material);
		
		fluidInteract.put(Blocks.field_150355_j, Blocks.field_150435_aG.func_176223_P());
		fluidInteract.put(Blocks.field_150353_l, Blocks.field_150343_Z.func_176223_P());

		blockRandInteract.put(Blocks.field_150435_aG.func_176223_P(), Blocks.field_150350_a.func_176223_P());
		blockRandInteract.put(Blocks.field_150348_b.func_176223_P(), Blocks.field_150347_e.func_176223_P());
		blockRandInteract.put(Blocks.field_150347_e.func_176223_P(), Blocks.field_150351_n.func_176223_P());
		blockRandInteract.put(Blocks.field_150351_n.func_176223_P(), Blocks.field_150354_m.func_176223_P());
		blockRandInteract.put(Blocks.field_150343_Z.func_176223_P(), Blocks.field_150350_a.func_176223_P());
		blockRandInteract.put(Blocks.field_150365_q.func_176223_P(), Blocks.field_150347_e.func_176223_P());
		blockRandInteract.put(Blocks.field_150339_S.func_176223_P(), Blocks.field_150350_a.func_176223_P());
		blockRandInteract.put(Blocks.field_150340_R.func_176223_P(), Blocks.field_150350_a.func_176223_P());
		blockRandInteract.put(Blocks.field_150451_bX.func_176223_P(), Blocks.field_150350_a.func_176223_P());
		blockRandInteract.put(Blocks.field_150484_ah.func_176223_P(), Blocks.field_150350_a.func_176223_P());
		blockRandInteract.put(Blocks.field_150368_y.func_176223_P(), Blocks.field_150350_a.func_176223_P());
		
		//TODO: values for wooden materials, dissolves wood and derived materials
		
		damageValue = FluidDamageValues.ACID_DAMAGE_VALUE.ordinal();
	}
	
	@Override
	public void func_176213_c(World world, BlockPos pos, IBlockState state) {
		super.func_176213_c(world, pos, state);
		canFreeze(world, pos, world.field_73012_v);
	}
	
	@Override
    public void func_180634_a(World world, BlockPos pos, IBlockState state, Entity entity){
		entityCollideCheck(world, pos, state, entity, acidDamage);
	}
	
	@Override
	public void func_180650_b(World world, BlockPos pos, IBlockState state, Random rand) {
   		checkInteraction(world, pos, false);
   		if(canFreeze(world, pos, rand)) {
			world.func_175656_a(pos, ReduxBlocks.blockFrozenAcid.func_176223_P());
			return;
   		}
   		
		super.func_180650_b(world, pos, state, rand);
	}
	
	@Override
	public void func_180645_a(World world, BlockPos pos, IBlockState state, Random rand) {
		if(canFreeze(world, pos, rand)) {
			world.func_175656_a(pos, ReduxBlocks.blockFrozenAcid.func_176223_P());
			return;
		}
		if(rand.nextInt(100) < 20) {
			checkInteraction(world, pos, true);
			return;
		}

		super.func_180645_a(world, pos, state, rand);
	}
	
	private boolean canFreeze(World world, BlockPos pos, Random rand) {
   		if(world.func_180494_b(pos).func_185353_n() <= .2f && 
   				isSourceBlock(world, pos) &&
   				world.func_175710_j(pos)) {
   			
   			if(rand.nextInt(100) <= 10) {
   				return true;
   			}
   		}
   		return false;
	}
}
