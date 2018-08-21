package com.omquark.fluidizationcraft.Fluids;


import java.util.HashMap;
import java.util.Random;

import com.omquark.fluidizationcraft.PlasmaCraftRedux;
import com.omquark.fluidizationcraft.blocks.ReduxBaseBlock;
import com.omquark.fluidizationcraft.config.Config;
import com.omquark.fluidizationcraft.init.ReduxBlocks;
import com.omquark.fluidizationcraft.items.ItemVial;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLiving;
import net.minecraft.init.Blocks;
import net.minecraft.block.Block;
import net.minecraft.block.BlockDirt;
import net.minecraft.block.BlockLiquid;
import net.minecraft.block.BlockStaticLiquid;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.Explosion;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fluids.BlockFluidBase;
import net.minecraftforge.fluids.BlockFluidClassic;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.IFluidBlock;

public class ReduxBaseFluid extends BlockFluidClassic implements IFluidBlock{
	
	public static final DamageSource acidDamage = new DamageSource("acid");
	public static final DamageSource cryoniteDamage = new DamageSource("cryonite");
	public static final DamageSource neptuniumDamage = new DamageSource("neptunium");
	public static final DamageSource netherflowDamage = new DamageSource("netherflow");
	public static final DamageSource plutoniumDamage = new DamageSource("plutonium");
	public static final DamageSource pyroniteDamage = new DamageSource("pyronite");
	public static final DamageSource radioniteDamage = new DamageSource("radionite");
	public static final DamageSource uraniumDamage = new DamageSource("uranium");
	
	public static enum FluidDamageValues{
		EMPTY_DAMAGE_VALUE,
		ACID_DAMAGE_VALUE,
		CRYONITE_DAMAGE_VALUE,
		NEPTUNIUM_DAMAGE_VALUE,
		NETHERFLOW_DAMAGE_VALUE,
		PLUTONIUM_DAMAGE_VALUE,
		PYRONITE_DAMAGE_VALUE,
		RADIONITE_DAMAGE_VALUE,
		URANIUM_DAMAGE_VALUE,
		WATER_DAMAGE_VALUE
	}

	public int damageValue;
	
	//Used to test against other non-fluid blocks
	protected final HashMap<IBlockState, IBlockState> blockInteract = new HashMap<IBlockState, IBlockState>();
	//Also for testing against other blocks, used for random checks
	protected final HashMap<IBlockState, IBlockState> blockRandInteract = new HashMap<IBlockState, IBlockState>();
	//Used to test against other fluids only, used so that each level does not have to be checked 
	protected final HashMap<Block, IBlockState> fluidInteract = new HashMap<Block, IBlockState>();

	public ReduxBaseFluid(Fluid fluid, Material material) {
		super(fluid, material);

		blockRandInteract.put(Blocks.field_150349_c.func_176223_P(), Blocks.field_150346_d.func_176223_P());
		blockRandInteract.put(Blocks.field_150346_d.func_176223_P(), Blocks.field_150346_d.func_176223_P().func_177226_a(BlockDirt.field_176386_a, BlockDirt.DirtType.COARSE_DIRT));

		displacements.put(Blocks.field_150355_j, false);
		displacements.put(Blocks.field_150353_l, false);
		displacements.put(this, false);
	}
	
	@Override
	public float getExplosionResistance(World world, BlockPos pos, Entity exploder, Explosion explosion) {
		return 100.0f;
	}
	
	@Override
	public boolean canDisplace(IBlockAccess world, BlockPos pos) {

		if (world.func_180495_p(pos).func_185904_a().func_76224_d()) {
			return false;
		}
		return super.canDisplace(world, pos);
	}

	@Override
	public boolean displaceIfPossible(World world, BlockPos pos) {

		if (world.func_180495_p(pos).func_185904_a().func_76224_d()) {
			return false;
		}
		return super.displaceIfPossible(world, pos);
	}

	/**
	 * Adds an @IBlockState to check against during interaction. This should only be used on Blocks.  
	 * 
	 * @param otherBlock The other block to check against. This cannot be a @BlockFluidBase or any extension of such.
	 * @param outcomeBlock What will this block turn into?
	 * @param rand Used to set if this check should be done at random, or always.
	 * @return False if otherBlock already exists or is an extension of @BlockFluidBase, true otherwise.
	 */
	public boolean addBlockInteraction(IBlockState otherBlock, IBlockState outcomeBlock, boolean rand){
		assert(!(otherBlock.func_177230_c() instanceof BlockLiquid));
		
		if(rand) {
			if(blockRandInteract.containsKey(otherBlock)) {
				return false;
			}
			return blockRandInteract.put(otherBlock, outcomeBlock) != null;
		}
		
		if(blockInteract.containsKey(outcomeBlock)) {
			return false;
		}
		return blockInteract.put(otherBlock, outcomeBlock) != null;
	}
	
	/**
	 * Adds a @Block to check against during interaction. This can only be used against @BlockFluidBase or extensions of such
	 * 
	 * @param otherBlock The other @BlockFluidBase to check against.
	 * @param outcomeBlock What will this block turn into.
	 * @return False if otherBlock already exists or is not
	 */
	public boolean addFluidInteraction(Block otherBlock, IBlockState outcomeBlock){
		assert(otherBlock instanceof BlockLiquid);
		
		if(fluidInteract.containsKey(otherBlock)) {
			return false;
		}
		
		return fluidInteract.put(otherBlock, outcomeBlock) != null;
	}
	
	private boolean checkOtherBlock(World world, IBlockState otherState, BlockPos pos, boolean rand) {

		if(otherState.func_177230_c() instanceof BlockLiquid) {
			if(fluidInteract.containsKey(otherState.func_177230_c())) {
				world.func_180501_a(pos, fluidInteract.get(otherState.func_177230_c()), 3);
				return true;
			}
		}
		else if(rand) {
			if(blockRandInteract.containsKey(otherState) && !Config.TerrainDamage) {
				world.func_180501_a(pos, blockRandInteract.get(otherState), 3);
				return true;
			}
		}
		else {
			if(blockInteract.containsKey(otherState) && !Config.TerrainDamage) {
				world.func_180501_a(pos, blockInteract.get(otherState), 3);
				return true;
			}
		}
		return false;
	}
		
	/**
	 * This will check for interactions against the @Block and change into the outcome block 
	 * that has already been defined using one of the add interactions Methods
	 * 
	 * @param world The world this block exists in.
	 * @param pos The position of the source block.
	 * @param rand Set this if this is a random check.
	 * @return True if this interaction exists.
	 */
	public void checkInteraction(World world, BlockPos pos, boolean rand){
		
		IBlockState otherBlockState;
		
		otherBlockState = world.func_180495_p(pos.func_177977_b());
		checkOtherBlock(world, otherBlockState, pos.func_177977_b(), rand);

		otherBlockState = world.func_180495_p(pos.func_177978_c());
		checkOtherBlock(world, otherBlockState, pos.func_177978_c(), rand);
		
		otherBlockState = world.func_180495_p(pos.func_177968_d());
		checkOtherBlock(world, otherBlockState, pos.func_177968_d(), rand);
		
		otherBlockState = world.func_180495_p(pos.func_177974_f());
		checkOtherBlock(world, otherBlockState, pos.func_177974_f(), rand);
		
		otherBlockState = world.func_180495_p(pos.func_177976_e());
		checkOtherBlock(world, otherBlockState, pos.func_177976_e(), rand);
	}
	
//	public boolean checkDecay(World world, BlockPos pos) {
//		
//		
//		boolean decay = false;
//		
//		Block block = world.getBlockState(pos.up()).getBlock();
//		if(block instanceof ReduxBaseFluid) decay = false;
//		
//		block = world.getBlockState(pos.east()).getBlock();
//		if(block instanceof ReduxBaseFluid) {
//			if(((ReduxBaseFluid) block).getQuantaValue(world, pos.east()) < this.getQuantaValue(world, pos) &&
//			((ReduxBaseFluid) block).getQuantaValue(world, pos.west()) < this.getQuantaValue(world, pos) &&
//			((ReduxBaseFluid) block).getQuantaValue(world, pos.north()) < this.getQuantaValue(world, pos) &&
//			((ReduxBaseFluid) block).getQuantaValue(world, pos.south()) < this.getQuantaValue(world, pos)) {
//				decay = true;
//			}
//		}
//		
//		if(isSourceBlock(world, pos)) decay = false;
//		
//		if(decay) world.setBlockState(pos, Blocks.AIR.getDefaultState());
//
//		return decay;
//	}
	
	@Override
	public void func_180650_b(World world, BlockPos pos, IBlockState state, Random rand) {
		super.func_180650_b(world, pos, state, rand);
	}
	
	public void func_180645_a(World world, BlockPos pos, IBlockState state, Random rand) {
		super.func_180645_a(world, pos, state, rand);
	}
	
	protected void entityCollideCheck(World world, BlockPos pos, IBlockState state, Entity entity, DamageSource source) {
		if(entity.func_70089_S() && entity instanceof EntityLiving) {
			if(!entity.func_190530_aW()) {
				entity.func_70097_a(source,  1.0f);
			}
		}
		super.func_180634_a(world, pos, state, entity);
	}
	
	@Override
    public void func_189540_a(IBlockState state, World world, BlockPos pos, Block blockIn, BlockPos neighborPos){
		super.func_189540_a(state, world, pos, blockIn, neighborPos);
		checkInteraction(world, pos, false);
    }
	
    @Override
    public void func_176213_c(World world, BlockPos pos, IBlockState state){
    	super.func_176213_c(world, pos, state);
    	checkInteraction(world, pos, false);
    }

}
