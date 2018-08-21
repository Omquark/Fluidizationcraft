package com.omquark.fluidizationcraft.Entities;

import com.omquark.fluidizationcraft.config.Config;
import com.omquark.fluidizationcraft.init.ReduxItems;
import com.omquark.fluidizationcraft.util.ModExplosion;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IProjectile;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.item.ItemExpireEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.IThrowableEntity;

public class EntityAcidGrenade extends EntityModArrow{

	public EntityAcidGrenade(World worldIn) {
		super(worldIn);
	}
	
	public EntityAcidGrenade(World world, EntityLivingBase shooter) {
		super(world, shooter);
	}

	/**
	 * Causes an explosion when the grenade lands
	 *
	 * this will still alert listeners
	 */
	@Override
	protected void func_70184_a(RayTraceResult result) {
		
		if(result.field_72313_a == RayTraceResult.Type.ENTITY) {
			if(delay > 0) {
				return;
			}
		}
		
		ModExplosion explode = new ModExplosion(field_70170_p, this, result.field_72307_f.field_72450_a, result.field_72307_f.field_72448_b, result.field_72307_f.field_72449_c,
				Config.AcidGrenadeRadius, false, Config.AcidGrenadesDestroyBlocks);

		if(!net.minecraftforge.event.ForgeEventFactory.onExplosionStart(field_70170_p, explode)) {

			explode.func_77278_a();
			explode.doModExplosionB(true, false);
		}

		func_70106_y();
	}
}
