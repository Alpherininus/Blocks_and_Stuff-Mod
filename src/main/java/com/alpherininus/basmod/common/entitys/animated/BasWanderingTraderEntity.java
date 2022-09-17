package com.alpherininus.basmod.common.entitys.animated;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.item.ExperienceOrbEntity;
import net.minecraft.entity.merchant.villager.VillagerTrades;
import net.minecraft.entity.merchant.villager.WanderingTraderEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.NBTUtil;
import net.minecraft.stats.Stats;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;

import javax.annotation.Nullable;

public class BasWanderingTraderEntity extends WanderingTraderEntity implements IAnimatable {

    @Nullable
    private BlockPos wanderTarget;
    private int despawnDelay;

    private <E extends IAnimatable> PlayState predicate(AnimationEvent<E> event) {
        event.getController().setAnimation(new AnimationBuilder().addAnimation("idle", true));
        return PlayState.CONTINUE;
    }

    private final AnimationFactory factory = new AnimationFactory(this);

    public BasWanderingTraderEntity(EntityType<? extends WanderingTraderEntity> type, World worldIn) {
        super(type, worldIn);
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    protected void registerGoals() {

        this.goalSelector.addGoal(1, new PanicGoal(this, 0.5D));

        this.addSwimGoals();
        this.addLookGoals();
    }

    protected void addSwimGoals() {
        this.goalSelector.addGoal(0, new SwimGoal(this));
        this.goalSelector.addGoal(0, new WaterAvoidingRandomWalkingGoal(this, 1.0D));
    }

    protected void addLookGoals() {
        this.goalSelector.addGoal(1, new LookAtGoal(this, PlayerEntity.class, 8.0F));
        this.goalSelector.addGoal(2, new LookRandomlyGoal(this));
        this.goalSelector.addGoal(3, new LookAtWithoutMovingGoal(this, PlayerEntity.class, 3.0F, 1.0F));

    }

    public boolean hasXPBar() {
        return false;
    }

    public boolean canDespawn(double distanceToClosestPlayer) {
        return false;
    }



    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public static AttributeModifierMap setBasTraderAttributes() {
        return MobEntity.func_233666_p_()
                .createMutableAttribute(Attributes.MAX_HEALTH, 23.0D)
                .createMutableAttribute(Attributes.ATTACK_DAMAGE, 5.0f)
                .createMutableAttribute(Attributes.ATTACK_SPEED, 2.0f)
                .createMutableAttribute(Attributes.MOVEMENT_SPEED, 0.15D)
                .createMutableAttribute(Attributes.FOLLOW_RANGE, 64.0D)
                .createMutableAttribute(Attributes.ATTACK_KNOCKBACK, 1.0f).create();
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    @Override
    public void registerControllers(AnimationData data) {
        data.addAnimationController(
                new AnimationController(this, "IDLEAnimateController", 0, this::predicate));

    }

    @Override
    public AnimationFactory getFactory() {
        return this.factory;
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public ActionResultType getEntityInteractionResult(PlayerEntity playerIn, Hand hand) {
        ItemStack itemstack = playerIn.getHeldItem(hand);
        if (itemstack.getItem() != Items.VILLAGER_SPAWN_EGG && this.isAlive() && !this.hasCustomer() && !this.isChild()) {
            if (hand == Hand.MAIN_HAND) {
                playerIn.addStat(Stats.TALKED_TO_VILLAGER);
            }

            if (this.getOffers().isEmpty()) {
                return ActionResultType.func_233537_a_(this.world.isRemote);
            } else {
                if (!this.world.isRemote) {
                    this.setCustomer(playerIn);
                    this.openMerchantContainer(playerIn, this.getDisplayName(), 1);
                }

                return ActionResultType.func_233537_a_(this.world.isRemote);
            }
        } else {
            return super.getEntityInteractionResult(playerIn, hand);
        }
    }

    protected void populateTradeData() {
        VillagerTrades.ITrade[] avillagertrades$itrade = VillagerTrades.field_221240_b.get(1);
        VillagerTrades.ITrade[] avillagertrades$itrade1 = VillagerTrades.field_221240_b.get(2);
        if (avillagertrades$itrade != null && avillagertrades$itrade1 != null) {
            MerchantOffers merchantoffers = this.getOffers();
            this.addTrades(merchantoffers, avillagertrades$itrade, 5);
            int i = this.rand.nextInt(avillagertrades$itrade1.length);
            VillagerTrades.ITrade villagertrades$itrade = avillagertrades$itrade1[i];
            MerchantOffer merchantoffer = villagertrades$itrade.getOffer(this, this.rand);
            if (merchantoffer != null) {
                merchantoffers.add(merchantoffer);
            }

        }
    }

    public void writeAdditional(CompoundNBT compound) {
        super.writeAdditional(compound);
        compound.putInt("DespawnDelay", this.despawnDelay);
        if (this.wanderTarget != null) {
            compound.put("WanderTarget", NBTUtil.writeBlockPos(this.wanderTarget));
        }

    }

    public void readAdditional(CompoundNBT compound) {
        super.readAdditional(compound);
        if (compound.contains("DespawnDelay", 99)) {
            this.despawnDelay = compound.getInt("DespawnDelay");
        }

        if (compound.contains("WanderTarget")) {
            this.wanderTarget = NBTUtil.readBlockPos(compound.getCompound("WanderTarget"));
        }

        this.setGrowingAge(Math.max(0, this.getGrowingAge()));
    }

    protected void onVillagerTrade(MerchantOffer offer) {
        if (offer.getDoesRewardExp()) {
            int i = 3 + this.rand.nextInt(4);
            this.world.addEntity(new ExperienceOrbEntity(this.world, this.getPosX(), this.getPosY() + 0.5D, this.getPosZ(), i));
        }

    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    protected SoundEvent getAmbientSound() {
        return this.hasCustomer() ? SoundEvents.ENTITY_WANDERING_TRADER_TRADE : SoundEvents.ENTITY_WANDERING_TRADER_AMBIENT;
    }

    protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
        return SoundEvents.ENTITY_WANDERING_TRADER_HURT;
    }

    protected SoundEvent getDeathSound() {
        return SoundEvents.ENTITY_WANDERING_TRADER_DEATH;
    }

    protected SoundEvent getVillagerYesNoSound(boolean getYesSound) {
        return getYesSound ? SoundEvents.ENTITY_WANDERING_TRADER_YES : SoundEvents.ENTITY_WANDERING_TRADER_NO;
    }

    public SoundEvent getYesSound() {
        return SoundEvents.ENTITY_WANDERING_TRADER_YES;
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public void setDespawnDelay(int delay) {
        this.despawnDelay = delay;
    }

    public int getDespawnDelay() {
        return this.despawnDelay;
    }

    public void livingTick() {
        super.livingTick();
        if (!this.world.isRemote) {
            this.handleDespawn();
        }

    }

    private void handleDespawn() {
        if (this.despawnDelay > 0 && !this.hasCustomer() && --this.despawnDelay == 0) {
            this.remove();
        }

    }

}
