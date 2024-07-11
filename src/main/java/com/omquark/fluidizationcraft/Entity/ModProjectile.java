package com.omquark.fluidizationcraft.Entity;

import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.level.Level;

public class ModProjectile extends Projectile {
    private static final EntityDataAccessor<Byte> ID_FLAGS = SynchedEntityData.defineId(ModProjectile.class, EntityDataSerializers.BYTE);
    protected ModProjectile(EntityType<? extends Projectile> p_37248_, Level p_37249_) {
        super(p_37248_, p_37249_);
    }

    @Override
    protected void defineSynchedData(SynchedEntityData.Builder syncedEntityData) {
        syncedEntityData.define(ID_FLAGS, (byte)0);
    }
}
