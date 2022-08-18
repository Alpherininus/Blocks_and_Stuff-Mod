package com.alpherininus.basmod.common.entitys;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.AbstractArrowEntity;
import net.minecraft.entity.projectile.TridentEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.world.World;

public class ThrowingAxeEntity extends TridentEntity {
    private static final DataParameter<Byte> LOYALTY_LEVEL = EntityDataManager.createKey(ThrowingAxeEntity.class, DataSerializers.BYTE);

    public ThrowingAxeEntity(EntityType<? extends TridentEntity> type, World worldIn) {
        super(type, worldIn);
    }

    public ThrowingAxeEntity(World worldIn, LivingEntity thrower, ItemStack thrownStackIn) {
        super(worldIn, thrower, thrownStackIn);
    }

    public ThrowingAxeEntity(World worldIn, double x, double y, double z) {
        super(worldIn, x, y, z);
    }

    protected SoundEvent getHitEntitySound() {
        return SoundEvents.ENTITY_ARROW_HIT_PLAYER;
    }

    public void func_225516_i_() {
        int i = this.dataManager.get(LOYALTY_LEVEL);
        if (this.pickupStatus != AbstractArrowEntity.PickupStatus.ALLOWED || i <= 0) {
            super.func_225516_i_();
        }

    }

}
