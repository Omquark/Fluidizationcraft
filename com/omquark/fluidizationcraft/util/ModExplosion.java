package com.omquark.fluidizationcraft.util;

import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import javax.annotation.Nullable;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.omquark.fluidizationcraft.Entities.EntityAcidGrenade;
import com.omquark.fluidizationcraft.config.Config;
import com.omquark.fluidizationcraft.init.ReduxBlocks;
import com.omquark.fluidizationcraft.init.ReduxFluids;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.enchantment.EnchantmentProtection;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityTNTPrimed;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.SoundEvents;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/**
 * 
 * This class is a simple copy of the super class explosion.
 * This class copies DoExplosionB into doModExplosionB and removes the lines so to prevent drops
 * and also modifies a few lines as to generate some acid in place of the explosion
 * More may be needed for this class to spawn acid in it's place.
 *
 * This class will also handle most of this mods explosions, since a lot of them replace random
 * blocks with fluids/blocks from this mod (Cryoblaster, Acid Launcher, etc.);
 */

public class ModExplosion extends Explosion{
	/** this is used to not generate more than one fluid block per explosion */
	private boolean generatedFluid;
    /** whether or not the explosion sets fire to blocks around it */
    private final boolean causesFire;
    /** whether or not this explosion spawns smoke particles */
    private final boolean damagesTerrain;
    private final Random random;
    private final World world;
    private final double x;
    private final double y;
    private final double z;
    private final Entity exploder;
    private final float size;
    /** A list of ChunkPositions of blocks affected by this explosion */
    private final List<BlockPos> affectedBlockPositions;
    /** Maps players to the knockback vector applied by the explosion, to send to the client */
    private final Map<EntityPlayer, Vec3d> playerKnockbackMap;
    private final Vec3d position;

    @SideOnly(Side.CLIENT)
    public ModExplosion(World worldIn, Entity entityIn, double x, double y, double z, float size, List<BlockPos> affectedPositions)
    {
        this(worldIn, entityIn, x, y, z, size, false, true, affectedPositions);
    }

    @SideOnly(Side.CLIENT)
    public ModExplosion(World worldIn, Entity entityIn, double x, double y, double z, float size, boolean causesFire, boolean damagesTerrain, List<BlockPos> affectedPositions)
    {
        this(worldIn, entityIn, x, y, z, size, causesFire, damagesTerrain);
        this.affectedBlockPositions.addAll(affectedPositions);
    }

    public ModExplosion(World worldIn, Entity entityIn, double x, double y, double z, float size, boolean flaming, boolean damagesTerrain)
    {
    	super(worldIn, entityIn, x, y, z, size, flaming, damagesTerrain);
        this.random = new Random();
        this.affectedBlockPositions = Lists.<BlockPos>newArrayList();
        this.playerKnockbackMap = Maps.<EntityPlayer, Vec3d>newHashMap();
        this.world = worldIn;
        this.exploder = entityIn;
        this.size = size;
        this.x = x;
        this.y = y;
        this.z = z;
        this.causesFire = flaming;
        this.damagesTerrain = damagesTerrain;
        this.position = new Vec3d(this.x, this.y, this.z);
    }
    
    /**
     * Used for cryonite explosions, a copy of ExplosionB
     * These may be able to be combined, then use a third Explosion stage for block generation
     */
    public void doModExplosionB(boolean spawnParticles, boolean cryoExplosion)
    {
    	this.affectedBlockPositions.addAll(super.func_180343_e());
        this.world.func_184148_a((EntityPlayer)null, this.x, this.y, this.z, SoundEvents.field_187539_bB, SoundCategory.BLOCKS, 4.0F, (1.0F + (this.world.field_73012_v.nextFloat() - this.world.field_73012_v.nextFloat()) * 0.2F) * 0.7F);

        if (this.size >= 2.0F && this.damagesTerrain)
        {
            this.world.func_175688_a(EnumParticleTypes.EXPLOSION_HUGE, this.x, this.y, this.z, 1.0D, 0.0D, 0.0D);
        }
        else
        {
            this.world.func_175688_a(EnumParticleTypes.EXPLOSION_LARGE, this.x, this.y, this.z, 1.0D, 0.0D, 0.0D);
        }

        //This was changed so to always activate
        if (true)
        {
        	generatedFluid = false;
            for (BlockPos blockpos : this.affectedBlockPositions)
            {
                IBlockState iblockstate = this.world.func_180495_p(blockpos);
                Block block = iblockstate.func_177230_c();

                if (spawnParticles)
                {
                    double d0 = (double)((float)blockpos.func_177958_n() + this.world.field_73012_v.nextFloat());
                    double d1 = (double)((float)blockpos.func_177956_o() + this.world.field_73012_v.nextFloat());
                    double d2 = (double)((float)blockpos.func_177952_p() + this.world.field_73012_v.nextFloat());
                    double d3 = d0 - this.x;
                    double d4 = d1 - this.y;
                    double d5 = d2 - this.z;
                    double d6 = (double)MathHelper.func_76133_a(d3 * d3 + d4 * d4 + d5 * d5);
                    d3 = d3 / d6;
                    d4 = d4 / d6;
                    d5 = d5 / d6;
                    double d7 = 0.5D / (d6 / (double)this.size + 0.1D);
                    d7 = d7 * (double)(this.world.field_73012_v.nextFloat() * this.world.field_73012_v.nextFloat() + 0.3F);
                    d3 = d3 * d7;
                    d4 = d4 * d7;
                    d5 = d5 * d7;
                    this.world.func_175688_a(EnumParticleTypes.EXPLOSION_NORMAL, (d0 + this.x) / 2.0D, (d1 + this.y) / 2.0D, (d2 + this.z) / 2.0D, d3, d4, d5);
                    this.world.func_175688_a(EnumParticleTypes.SMOKE_NORMAL, d0, d1, d2, d3, d4, d5);
                }
                
                //This is the start of the custom block
        		IBlockState newState = iblockstate;
                
        		if(cryoExplosion) {
                	
        			newState = Blocks.field_150432_aD.func_176223_P();
                  	int chance = random.nextInt(100);
                   	
                  	if(chance <= 85) {
                  		
                  		newState = Blocks.field_150433_aE.func_176223_P();
                  	}
                   	else if(chance >= 99 && Config.CryoBlasterGenCryonite) {
                   		if(!generatedFluid) {
                   			newState = ReduxBlocks.blockFrozenCryonite.func_176223_P();
                   			generatedFluid = true;
                   		}
                   	}
              	}
               	
        		else {
               		
               		if(iblockstate.func_185904_a() != Material.field_151579_a) {
               			
               			block.onBlockExploded(this.world, blockpos, this);

               			if(this.random.nextInt(1000) < 10 && Config.AcidGrenadesGenerateAcid && !generatedFluid) {
           					
               				newState = ReduxFluids.acid.getBlock().func_176223_P();
               				
           					if(this.world.func_180494_b(blockpos).func_185353_n() < .15 || blockpos.func_177956_o() > 160)
           						newState = ReduxBlocks.blockFrozenAcid.func_176223_P();

           					generatedFluid = true;
               			}
               		}
               	}
                if(!newState.equals(iblockstate))
                	this.world.func_175656_a(blockpos, newState);
                //End of block
           	}
        }

        if (this.causesFire)
        {
            for (BlockPos blockpos1 : this.affectedBlockPositions)
            {
                if (this.world.func_180495_p(blockpos1).func_185904_a() == Material.field_151579_a && this.world.func_180495_p(blockpos1.func_177977_b()).func_185913_b() && this.random.nextInt(3) == 0)
                {
                    this.world.func_175656_a(blockpos1, Blocks.field_150480_ab.func_176223_P());
                }
            }
        }
    }
}
