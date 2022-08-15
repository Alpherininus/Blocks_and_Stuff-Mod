package com.alpherininus.basmod.common.entitys;

import com.alpherininus.basmod.core.init.ItemInit;
import net.minecraft.block.BlockState;
import net.minecraft.dispenser.IPosition;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.merchant.villager.VillagerEntity;
import net.minecraft.entity.passive.FoxEntity;
import net.minecraft.entity.passive.GolemEntity;
import net.minecraft.entity.passive.TameableEntity;
import net.minecraft.entity.passive.WolfEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.network.play.client.CJigsawBlockGeneratePacket;
import net.minecraft.particles.BlockParticleData;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

import javax.annotation.Nullable;
import java.util.EnumSet;
import java.util.Random;

public class CopperGolemEntity extends GolemEntity {

    public CopperGolemEntity(EntityType<? extends GolemEntity> type, World worldIn) {
        super(type, worldIn);
    }

    public static AttributeModifierMap.MutableAttribute setCustomCopperGolemAttributes() {
        return MobEntity.func_233666_p_()
                .createMutableAttribute(Attributes.MAX_HEALTH, 10.00D)
                .createMutableAttribute(Attributes.ARMOR, 3.00D)
                .createMutableAttribute(Attributes.FOLLOW_RANGE, 50.00D)
                .createMutableAttribute(Attributes.ATTACK_KNOCKBACK, 0.25D);
    }

    @Override
    protected void registerGoals() {
        TemptGoal temptGoal = new TemptGoal(this, 0.6D, true, Ingredient.fromItems(ItemInit.RELICS_ITEM.get()));
        super.registerGoals();
        this.goalSelector.addGoal(2, new WaterAvoidingRandomWalkingGoal(this, 0.25D, 1.0f));
        this.goalSelector.addGoal(6, new LookAtWithoutMovingGoal(this, PlayerEntity.class, 2.0f, 1.5f));
        this.goalSelector.addGoal(2, new LookRandomlyGoal(this));
        this.goalSelector.addGoal(3, temptGoal);
        this.goalSelector.addGoal(5, new RandomWalkingGoal(this, 2.0f));
        this.goalSelector.addGoal(2, new InteractDoorGoal(this) {
            @Override
            public boolean shouldExecute() {
                return true;
            }
        });
        this.goalSelector.addGoal(12, new LookAtGoal(this, PlayerEntity.class, 10.0F));
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    @Override
    protected int getExperiencePoints(PlayerEntity player) {
        return 3 + this.world.rand.nextInt(5);
    }

    public boolean onLivingFall(float distance, float damageMultiplier) {
        return false;
    }

    @Nullable
    protected SoundEvent getAmbientSound() {
        if (!world.isThundering()) {
            playSound(SoundEvents.ENTITY_VILLAGER_YES, 25, 0);
        } else {
            playSound(SoundEvents.ENTITY_PARROT_IMITATE_GHAST, 100, 2);
        }
        return null;
    }

    @Nullable
    protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
        return SoundEvents.ENTITY_IRON_GOLEM_HURT;
    }

    @Nullable
    protected SoundEvent getDeathSound() {
        return SoundEvents.ENTITY_IRON_GOLEM_DEATH;
    }

    @Override
    public SoundEvent getEatSound(ItemStack itemStackIn) {
        return SoundEvents.ENTITY_GENERIC_EAT;
    }

    public int getTalkInterval() {
        return 125;
    }

    public boolean canDespawn(double distanceToClosestPlayer) {
        return false;
    }

    @Override
    protected ActionResultType getEntityInteractionResult(PlayerEntity playerIn, Hand hand) {
        if (!world.isRemote()) {
            playSound(SoundEvents.BLOCK_NOTE_BLOCK_XYLOPHONE, 50, 1);

            if (playerIn.isSneaking()) {
                playSound(SoundEvents.BLOCK_NOTE_BLOCK_IRON_XYLOPHONE, 100, 1);
            }
        }
        return super.getEntityInteractionResult(playerIn, hand);
    }

    @Override
    public void livingTick() {

    }
}

