package com.omquark.fluidizationcraft.items;

import java.awt.List;
import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;

import com.omquark.fluidizationcraft.PlasmaCraftRedux;
import com.omquark.fluidizationcraft.Fluids.ReduxBaseFluid;
import com.omquark.fluidizationcraft.init.ReduxFluids;
import com.omquark.fluidizationcraft.init.ReduxItems;
import com.omquark.fluidizationcraft.util.Constants;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.ItemMeshDefinition;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.UniversalBucket;
import net.minecraftforge.fluids.capability.IFluidHandler;
import net.minecraftforge.fluids.capability.IFluidTankProperties;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.items.CapabilityItemHandler;

public class ItemVial extends ItemMultiBase{
	
	//May need to be moved to a Util class for reference. This map allows to not have  
	protected static final HashMap<Integer, ReduxBaseFluid> fluidMap = new HashMap<Integer, ReduxBaseFluid>();
	
	public ItemVial(String name) {
		super(name);
		
		nameMap.put(ReduxBaseFluid.FluidDamageValues.EMPTY_DAMAGE_VALUE.ordinal(), "vialEmpty");
		nameMap.put(ReduxBaseFluid.FluidDamageValues.ACID_DAMAGE_VALUE.ordinal(), "vialAcid");
		nameMap.put(ReduxBaseFluid.FluidDamageValues.CRYONITE_DAMAGE_VALUE.ordinal(), "vialCryonite");
		nameMap.put(ReduxBaseFluid.FluidDamageValues.NEPTUNIUM_DAMAGE_VALUE.ordinal(), "vialNeptunium");
		nameMap.put(ReduxBaseFluid.FluidDamageValues.NETHERFLOW_DAMAGE_VALUE.ordinal(), "vialNetherflow");
		nameMap.put(ReduxBaseFluid.FluidDamageValues.PLUTONIUM_DAMAGE_VALUE.ordinal(), "vialPlutonium");
		nameMap.put(ReduxBaseFluid.FluidDamageValues.PYRONITE_DAMAGE_VALUE.ordinal(), "vialPyronite");
		nameMap.put(ReduxBaseFluid.FluidDamageValues.RADIONITE_DAMAGE_VALUE.ordinal(), "vialRadionite");
		nameMap.put(ReduxBaseFluid.FluidDamageValues.URANIUM_DAMAGE_VALUE.ordinal(), "vialUranium");
		nameMap.put(ReduxBaseFluid.FluidDamageValues.WATER_DAMAGE_VALUE.ordinal(), "vialWater");
		
		fluidMap.put(ReduxBaseFluid.FluidDamageValues.EMPTY_DAMAGE_VALUE.ordinal(), ReduxFluids.acidFluidBlock);
		fluidMap.put(ReduxBaseFluid.FluidDamageValues.ACID_DAMAGE_VALUE.ordinal(), ReduxFluids.acidFluidBlock);
		fluidMap.put(ReduxBaseFluid.FluidDamageValues.CRYONITE_DAMAGE_VALUE.ordinal(), ReduxFluids.cryoniteFluidBlock);
		fluidMap.put(ReduxBaseFluid.FluidDamageValues.NEPTUNIUM_DAMAGE_VALUE.ordinal(), ReduxFluids.neptuniumFluidBlock);
		fluidMap.put(ReduxBaseFluid.FluidDamageValues.NETHERFLOW_DAMAGE_VALUE.ordinal(), ReduxFluids.netherflowFluidBlock);
		fluidMap.put(ReduxBaseFluid.FluidDamageValues.PLUTONIUM_DAMAGE_VALUE.ordinal(), ReduxFluids.plutoniumFluidBlock);
		fluidMap.put(ReduxBaseFluid.FluidDamageValues.PYRONITE_DAMAGE_VALUE.ordinal(), ReduxFluids.pyroniteFluidBlock);
		fluidMap.put(ReduxBaseFluid.FluidDamageValues.RADIONITE_DAMAGE_VALUE.ordinal(), ReduxFluids.radioniteFluidBlock);
		fluidMap.put(ReduxBaseFluid.FluidDamageValues.URANIUM_DAMAGE_VALUE.ordinal(), ReduxFluids.uraniumFluidBlock);

		func_77625_d(16);
		func_77627_a(true);
		func_77656_e(nameMap.size());
	}

	@Override
	public ActionResult<ItemStack> func_77659_a(World world, EntityPlayer player, EnumHand hand) {
		
		ItemStack newItem = new ItemStack(Items.field_190931_a);
		ItemStack heldItem = player.func_184586_b(hand);
		//FluidStack fluidStack;
		RayTraceResult result = null;
		
		if(heldItem.func_77973_b() instanceof ItemVial) {
			if(((ItemVial) heldItem.func_77973_b()).func_77647_b(heldItem.func_77952_i()) == ReduxBaseFluid.FluidDamageValues.EMPTY_DAMAGE_VALUE.ordinal()) {
				result = this.func_77621_a(world, player, true);
			}
			else {
				result = this.func_77621_a(world, player, false);
			}
		}
		
		if(result == null || result.field_72313_a != RayTraceResult.Type.BLOCK) {
				return new ActionResult<>(EnumActionResult.PASS, heldItem);
		}
		
		BlockPos pos = result.func_178782_a();
		
		Block block = world.func_180495_p(pos).func_177230_c();
		
		if(heldItem.func_77960_j() == ReduxBaseFluid.FluidDamageValues.EMPTY_DAMAGE_VALUE.ordinal()) {
			
			if(block instanceof ReduxBaseFluid){
				
				ReduxBaseFluid modBlock = (ReduxBaseFluid) block;
				newItem = new ItemStack(ReduxItems.vialEmpty, 1, modBlock.damageValue);
				
			}
			
			else if(block == Blocks.field_150355_j) {
				
				newItem = new ItemStack(ReduxItems.vialEmpty, 1, ReduxBaseFluid.FluidDamageValues.WATER_DAMAGE_VALUE.ordinal());
			}
			
			else {
				return new ActionResult<>(EnumActionResult.FAIL, heldItem);
			}
		}
		
		else {
			
			pos = result.func_178782_a().func_177972_a(result.field_178784_b);
			newItem = new ItemStack(ReduxItems.vialEmpty, 1, ReduxBaseFluid.FluidDamageValues.EMPTY_DAMAGE_VALUE.ordinal());
			int intDamage = heldItem.func_77960_j();
			
			if(intDamage == ReduxBaseFluid.FluidDamageValues.WATER_DAMAGE_VALUE.ordinal()) {
				
				world.func_175656_a(pos, Blocks.field_150355_j.func_176223_P());
				world.func_180497_b(pos, Blocks.field_150355_j, 1, 1);
				//world.notifyBlockUpdate(pos, Blocks.AIR.getDefaultState(), Blocks.WATER.getDefaultState(), 1);
			}
			else {
				
				world.func_175656_a(pos, fluidMap.get(heldItem.func_77960_j()).func_176223_P());
			}
			
			if(!player.func_184812_l_()) {
				
				heldItem.func_190918_g(1);
				player.func_191521_c(newItem);
			}
			
			return new ActionResult<>(EnumActionResult.SUCCESS, heldItem);
		}

		if(!player.func_184812_l_()) {
			
			heldItem.func_190918_g(1);;
			player.func_191521_c(newItem);
		}
		
		world.func_175656_a(pos, Blocks.field_150350_a.func_176223_P());
		
		return new ActionResult<>(EnumActionResult.SUCCESS, heldItem);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void setModels() {
		super.setModels();
	}

}
