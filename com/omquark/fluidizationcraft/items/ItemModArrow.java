package com.omquark.fluidizationcraft.items;

import javax.annotation.Nonnull;

import com.omquark.fluidizationcraft.PlasmaCraftRedux;
import com.omquark.fluidizationcraft.Entities.EntityCryoBlast;
import com.omquark.fluidizationcraft.Entities.EntityModArrow;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemArrow;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.world.World;

public class ItemModArrow extends ItemArrow {

	protected final ItemStack ammo;
	
	ItemModArrow(String name, @Nonnull ItemStack ammunition){
		setRegistryName(name);
		
		if(ammunition == null)
			ammo = ItemStack.field_190927_a;
		else
			ammo = ammunition;
		
		func_77637_a(PlasmaCraftRedux.reduxTab);
	}


	protected ItemStack getAmmoSlot(EntityPlayer player) {
		
		if(ammo.func_190926_b()) return ammo;
		ItemStack ammoSlot = new ItemStack(Blocks.field_150350_a);
		IInventory inv = player.field_71071_by;

		for(int i = 0; i < inv.func_70302_i_(); i++) {
		
			if(inv.func_70301_a(i).func_185136_b(ammo)) {
			
				ammoSlot = inv.func_70301_a(i);
				break;
			}
		}
		
		return ammoSlot;
	}
	
	/**
	 * Fire an arrow with the specified charge.
	 *
	 * @param world   The firing player's World
	 * @param shooter The player firing the bow
	 * @param charge  The charge of the arrow
	 */
	protected void shootItem(World world, EntityLivingBase shooter, EntityModArrow entityArrow, float velocity, float inaccuracy) {
		
		//if (!(shooter instanceof EntityLivingBase)) return;

		//final EntityPlayer player = (EntityPlayer) shooter;

		if (!world.field_72995_K) {
			
			
			//entityArrow.setThrowableHeading(shooter.posX, shooter.posY, shooter.posZ, arrowVelocity * 20, inaccuracy);
			entityArrow.setHeadingFromThrower(shooter, shooter.field_70125_A, shooter.field_70177_z, 10.0f, velocity, inaccuracy);
			world.func_72838_d(entityArrow);

			world.func_184148_a(null, shooter.field_70165_t, shooter.field_70163_u, shooter.field_70161_v, SoundEvents.field_187737_v, SoundCategory.NEUTRAL, 1.0F, 1.0F / (field_77697_d.nextFloat() * 0.4F + 1.2F) + velocity);
		}
	}
	
	public ActionResult<ItemStack> attemptShot(World world, EntityPlayer player, EntityModArrow shot, EnumHand hand, int damageCount, float velocity, float inaccuracy){

		ItemStack stack = player.func_184586_b(hand);
		IInventory inv = player.field_71071_by;
		ItemStack ammunition = getAmmoSlot(player);
		
		if(player.func_184812_l_()) {
			
			shootItem(world, player, shot, velocity, inaccuracy);
		}
		else if(!ammunition.func_190926_b()) {
			
			if(ammunition.func_77952_i() > 0) {
				
				shootItem(world, player, shot, velocity, inaccuracy);
				ammunition.func_77972_a(damageCount, player);
			}
		}
		else {
			
			return new ActionResult<>(EnumActionResult.FAIL, stack);
		}
		
		player.func_184598_c(hand);
		return new ActionResult<>(EnumActionResult.SUCCESS, stack);
	}

}
