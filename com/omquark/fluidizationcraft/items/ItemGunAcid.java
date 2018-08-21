package com.omquark.fluidizationcraft.items;

import com.omquark.fluidizationcraft.Entities.EntityAcidShot;
import com.omquark.fluidizationcraft.Entities.EntityCryoBlast;
import com.omquark.fluidizationcraft.Entities.EntityModArrow;
import com.omquark.fluidizationcraft.init.ReduxItems;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;

public class ItemGunAcid extends ItemModArrow{

	public ItemGunAcid(String name, ItemStack ammunition) {
		super(name, ammunition);
	}

	public ActionResult<ItemStack> func_77659_a(World world, EntityPlayer player, EnumHand hand) {
		return attemptShot(world, player, new EntityAcidShot(world, player), hand, 1, 3.0f, 0.0f);
	}
}
