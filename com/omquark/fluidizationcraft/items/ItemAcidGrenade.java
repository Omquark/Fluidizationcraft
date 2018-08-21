package com.omquark.fluidizationcraft.items;

import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.entity.projectile.EntityTippedArrow;
import net.minecraft.init.Blocks;
import net.minecraft.init.Enchantments;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemArrow;
import net.minecraft.item.ItemBow;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.StatList;
import net.minecraft.util.*;
import net.minecraft.world.World;
import net.minecraftforge.event.ForgeEventFactory;
import net.minecraftforge.event.entity.item.ItemExpireEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.IItemHandlerModifiable;
import net.minecraftforge.items.ItemStackHandler;
import net.minecraftforge.items.wrapper.PlayerOffhandInvWrapper;
import net.minecraftforge.items.wrapper.RangedWrapper;

import java.util.function.Predicate;

import com.omquark.fluidizationcraft.PlasmaCraftRedux;
import com.omquark.fluidizationcraft.Entities.BaseEntity;
import com.omquark.fluidizationcraft.Entities.EntityAcidGrenade;
import com.omquark.fluidizationcraft.Entities.EntityCryoBlast;
import com.omquark.fluidizationcraft.Entities.EntityModArrow;
import com.omquark.fluidizationcraft.init.ReduxItems;

public class ItemAcidGrenade extends ItemModArrow{

	public ItemAcidGrenade(String name, ItemStack ammunition) {
		super(name, ammunition);
	}

	@Override
	public ActionResult<ItemStack> func_77659_a(World world, EntityPlayer player, EnumHand hand) {
		return attemptShot(world, player, new EntityAcidGrenade(world, player), hand, 1, 1.0f, 0.0f);
	}
}
