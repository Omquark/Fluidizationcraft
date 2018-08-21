package com.omquark.fluidizationcraft.blocks;

import com.omquark.fluidizationcraft.PlasmaCraftRedux;
import com.omquark.fluidizationcraft.guiHandler.FluidizationGuiHandler;
import com.omquark.fluidizationcraft.init.ReduxFluids;
import com.omquark.fluidizationcraft.init.ReduxItems;
import com.omquark.fluidizationcraft.items.ItemVial;
import com.omquark.fluidizationcraft.tileentities.TileDissolvinator;

import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fluids.FluidStack;

public class BlockDissolvinator extends BlockContainer{
	
	public BlockDissolvinator(Material material, MapColor mapColor) {
		super(material, mapColor);
		func_149647_a(PlasmaCraftRedux.reduxTab);
	}

	@Override
	public boolean func_149716_u() {
		return true;
	}
	
	@Override
    public boolean func_180639_a(World world, BlockPos blockPos, IBlockState blockState,
    		EntityPlayer player, EnumHand hand, EnumFacing side, float hitX, float hitY, float hitZ) {

		if (!world.field_72995_K) {
			if(world.func_175625_s(blockPos) instanceof TileDissolvinator) {

				TileDissolvinator tileDissolvinator = (TileDissolvinator) world.func_175625_s(blockPos);
				
				ItemStack heldItem = player.func_184586_b(hand);
				FluidStack fluidStack = new FluidStack(ReduxFluids.acid, 1000);
			
				if((heldItem.func_77969_a(ReduxItems.itemVialAcid) || heldItem.func_77969_a(ReduxItems.acidBucket)) && tileDissolvinator.fill(fluidStack, false) == fluidStack.amount) {

					tileDissolvinator.fill(fluidStack, true);
					
					if(!heldItem.func_77969_a(ReduxItems.acidBucket) && !player.func_184812_l_()) {
						player.func_191521_c(ReduxItems.itemVialEmpty.func_77946_l());
					}
					heldItem.func_190918_g(1);
					return true;
				}
				
				else {
					player.openGui(PlasmaCraftRedux.instance, FluidizationGuiHandler.GUI_ENUM.GUI_DISSOLVINATOR.ordinal(), world, blockPos.func_177958_n(), blockPos.func_177956_o(), blockPos.func_177952_p());
				}
			}
   		}
   		return true;
    }
	
	@Override
	public void func_176208_a(World world, BlockPos pos, IBlockState state, EntityPlayer player) {
		
		if(!(world.func_175625_s(pos) instanceof TileDissolvinator)) {
			return;
		}

		TileDissolvinator tileEntity = (TileDissolvinator) world.func_175625_s(pos);
		if(!player.func_184812_l_()) {
			for(int i = 0; i < tileEntity.func_70302_i_(); i++) {
				player.func_71019_a(tileEntity.func_70301_a(i), false);
				EntityItem entityItem = new EntityItem(world, pos.func_177958_n(), pos.func_177956_o(), pos.func_177952_p());
				world.func_72838_d(entityItem);
			}
		}
	}

	@Override
	public TileEntity func_149915_a(World worldIn, int meta) {
		return new TileDissolvinator();
	}
	
	@Override
	public EnumBlockRenderType func_149645_b(IBlockState blockState) {
		return EnumBlockRenderType.MODEL;
	}
}
