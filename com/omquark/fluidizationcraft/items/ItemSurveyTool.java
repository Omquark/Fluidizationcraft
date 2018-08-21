package com.omquark.fluidizationcraft.items;

import java.util.ArrayList;

import com.omquark.fluidizationcraft.init.ReduxItems;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;
import net.minecraft.world.chunk.Chunk;

public class ItemSurveyTool extends BaseItem{

	public ItemSurveyTool(String name) {
		super(name);
	}
	
	public ActionResult<ItemStack> func_77659_a(World world, EntityPlayer player, EnumHand hand) {

		ItemStack heldItem = player.func_184586_b(hand);
		
		OreCountPair oreCount = new OreCountPair();

		if(!world.field_72995_K) {

			TextComponentString string = new TextComponentString("Testing! this may take some time...");
			player.func_145747_a((ITextComponent) string);
			
			Chunk chunk = world.func_175726_f(player.func_180425_c());
			 
			BlockPos pos = new BlockPos(chunk.field_76635_g * 16, 0, chunk.field_76647_h * 16);
			
			for(int x = 0; x < 16; x++) {
				for(int y = 0; y < 256; y++) {
					for(int z = 0; z < 16; z++){
						Block block = world.func_180495_p(pos.func_177982_a(x, y, z)).func_177230_c();
						
						if(oreCount.blocks.contains(block)) {
							int i = oreCount.blocks.indexOf(block);
							oreCount.count.set(i, oreCount.count.get(i) + 1);
						}
						else{
							oreCount.blocks.add(block);
							oreCount.count.add(1);
						}
					}
				}
			}
			
			for(int i = 0; i < oreCount.blocks.size(); i++) {
				System.out.println(oreCount.count.get(i) + " blocks of " + oreCount.blocks.get(i).func_149732_F());
			}
		}
		
		return new ActionResult<>(EnumActionResult.SUCCESS, heldItem);
	}

	
	private class OreCountPair{
		
		private ArrayList<Block> blocks;
		private ArrayList<Integer> count;
		
		private OreCountPair() {
			blocks = new ArrayList<Block>();
			count = new ArrayList<Integer>();
		}
	}
}
