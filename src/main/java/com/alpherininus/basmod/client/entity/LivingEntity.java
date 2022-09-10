package com.alpherininus.basmod.client.entity;

import com.alpherininus.basmod.core.init.SoundInit;
import com.mojang.authlib.GameProfile;
import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public abstract class LivingEntity extends PlayerEntity {


    public LivingEntity(World p_i241920_1_, BlockPos p_i241920_2_, float p_i241920_3_, GameProfile p_i241920_4_) {
        super(p_i241920_1_, p_i241920_2_, p_i241920_3_, p_i241920_4_);
    }

    @Override
    protected SoundEvent getDeathSound() {
        return SoundEvents.ENTITY_PLAYER_DEATH;
    }

    @Override
    protected SoundEvent getSwimSound() {
        return SoundEvents.ENTITY_PLAYER_SWIM;
    }

    @Override
    protected SoundEvent getSplashSound() {
        return SoundEvents.ENTITY_PLAYER_SPLASH;
    }

    @Override
    protected SoundEvent getHighspeedSplashSound() {
        return SoundEvents.ENTITY_PLAYER_SPLASH_HIGH_SPEED;
    }

    @Override
    protected SoundEvent getDrinkSound(ItemStack stack) {
        return super.getDrinkSound(stack);
    }

    @Override
    public SoundEvent getEatSound(ItemStack itemStackIn) {
        return super.getEatSound(itemStackIn);
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
        if (damageSourceIn == DamageSource.ON_FIRE) {
            return SoundEvents.ENTITY_PLAYER_HURT_ON_FIRE;
        } else if (damageSourceIn == DamageSource.DROWN) {
            return SoundEvents.ENTITY_PLAYER_HURT_DROWN;
        } else {
            return damageSourceIn == DamageSource.SWEET_BERRY_BUSH ? SoundEvents.ENTITY_PLAYER_HURT_SWEET_BERRY_BUSH : SoundInit.RANDOM_OLD_HURT.get();
        }
    }
    @Override
    protected SoundEvent getFallSound(int heightIn) {
        return heightIn > 4 ? SoundEvents.ENTITY_PLAYER_BIG_FALL : SoundEvents.ENTITY_PLAYER_SMALL_FALL;
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    @Override
    protected float getSoundPitch() {
        return super.getSoundPitch();
    }

    @Override
    protected float getSoundVolume() {
        return super.getSoundVolume();
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////

}