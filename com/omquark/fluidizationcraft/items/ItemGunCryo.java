package com.omquark.fluidizationcraft.items;

import com.omquark.fluidizationcraft.Entities.EntityAcidGrenade;
import com.omquark.fluidizationcraft.Entities.EntityCryoBlast;
import com.omquark.fluidizationcraft.init.ReduxItems;

import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.StatList;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.world.World;

public class ItemGunCryo extends ItemModArrow{

	public ItemGunCryo(String name, ItemStack ammunition) {
		super(name, ammunition);
	}

	@Override
	public ActionResult<ItemStack> func_77659_a(World world, EntityPlayer player, EnumHand hand) {
		return attemptShot(world, player, new EntityCryoBlast(world, player), hand, 5, 4.0f, 0.0f);
	}
}
