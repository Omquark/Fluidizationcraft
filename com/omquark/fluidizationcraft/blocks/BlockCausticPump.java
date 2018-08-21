package com.omquark.fluidizationcraft.blocks;

import com.omquark.fluidizationcraft.PlasmaCraftRedux;
import com.omquark.fluidizationcraft.Fluids.ReduxBaseFluid;
import com.omquark.fluidizationcraft.config.Config;
import com.omquark.fluidizationcraft.guiHandler.FluidizationGuiHandler;
import com.omquark.fluidizationcraft.init.ReduxFluids;
import com.omquark.fluidizationcraft.init.ReduxItems;
import com.omquark.fluidizationcraft.items.ItemVial;
import com.omquark.fluidizationcraft.tileentities.TileCausticPump;
import com.omquark.fluidizationcraft.tileentities.TileDissolvinator;
import com.omquark.fluidizationcraft.util.ModExplosion;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;
import net.minecraftforge.fluids.FluidStack;

public class BlockCausticPump extends BlockContainer{

	public BlockCausticPump(Material material, MapColor mapColor) {
		super(material, mapColor);
		func_149647_a(PlasmaCraftRedux.reduxTab);
	}
	
	public boolean func_149716_u() {
		return true;
	}
	
	@Override
    public boolean func_180639_a(World world, BlockPos blockPos, IBlockState blockState,
    		EntityPlayer player, EnumHand hand, EnumFacing side, float hitX, float hitY, float hitZ) {
		
		if(!world.field_72995_K) {
		
			TileCausticPump pumpEntity = (TileCausticPump) world.func_175625_s(blockPos);
		
			ItemStack heldItem = player.func_184586_b(hand);
			if(heldItem.func_77960_j() == ReduxBaseFluid.FluidDamageValues.EMPTY_DAMAGE_VALUE.ordinal()
					&& heldItem.func_77973_b() instanceof ItemVial) {
			
				FluidStack drainedFluid = pumpEntity.drain(null, false);
				int fluidType = ((ReduxBaseFluid) drainedFluid.getFluid().getBlock()).damageValue;
			
				if(drainedFluid.amount >= 1000) {
					ItemStack vial = new ItemStack(ReduxItems.vialEmpty, 1, fluidType);
					if(!player.func_184812_l_()) {
						player.func_191521_c(vial);
						heldItem.func_190918_g(1);
					}
					pumpEntity.drain(1000, true);
				}
			}
			else {
				FluidStack fluidStack = pumpEntity.getFluidStack();
				TextComponentString string = new TextComponentString(fluidStack.amount + " mB of " + fluidStack.getFluid().getName());
				player.func_145747_a((ITextComponent) string);
			}
		}

   		return true;
    }
	
	@Override
	public void func_176208_a(World world, BlockPos pos, IBlockState state, EntityPlayer player) {
		//TODO: Maybe spill some fluid if there is any when broken(replace with some caustic fluid)
		if(world.func_175625_s(pos) instanceof TileCausticPump) {
			TileCausticPump entityPump = (TileCausticPump) world.func_175625_s(pos);
			if(entityPump.drain(1000, false).amount >= 1000 && !player.func_184812_l_()){
				//dropBlockAsItem(world, pos, getDefaultState(), 0);
				world.func_175656_a(pos.func_177984_a(), ReduxFluids.acidFluidBlock.func_176223_P());
			}
		}
	}

	@Override
	public TileEntity func_149915_a(World worldIn, int meta) {
		return new TileCausticPump();
	}
	
	@Override
	public EnumBlockRenderType func_149645_b(IBlockState blockState) {
		return EnumBlockRenderType.MODEL;
	}

}
