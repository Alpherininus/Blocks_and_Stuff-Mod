package com.alpherininus.basmod.common.entitys.animated;

import net.minecraft.block.BlockState;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.merchant.villager.AbstractVillagerEntity;
import net.minecraft.entity.merchant.villager.VillagerEntity;
import net.minecraft.entity.monster.CreeperEntity;
import net.minecraft.entity.monster.SpiderEntity;
import net.minecraft.entity.passive.IronGolemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.potion.EffectInstance;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BossInfo;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerBossInfo;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;

import javax.annotation.Nullable;
import java.util.function.Predicate;

public class BossOfDeadEntity extends SpiderEntity implements IAnimatable {
    private static final EntityPredicate PLAYER_INVADER_CONDITION = (new EntityPredicate()).setDistance(64.0D);

    private static final Predicate<LivingEntity> NOT_UNDEAD = (p_213797_0_) -> p_213797_0_.getCreatureAttribute() != CreatureAttribute.UNDEAD && p_213797_0_.attackable();
    private final ServerBossInfo bossInfo = (ServerBossInfo)(new ServerBossInfo(this.getDisplayName(), BossInfo.Color.YELLOW, BossInfo.Overlay.PROGRESS)).setDarkenSky(true);

    private AnimationFactory factory = new AnimationFactory(this);

    public BossOfDeadEntity(EntityType<? extends SpiderEntity> entityType, World worldIn) {
        super(entityType, worldIn);
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(1, new LookAtGoal(this, PlayerEntity.class, 64.0f));
        this.goalSelector.addGoal(2, new LookAtGoal(this, IronGolemEntity.class, 32.0f));
        this.goalSelector.addGoal(3, new LookAtGoal(this, VillagerEntity.class, 32.0f));
        this.goalSelector.addGoal(5, new LookAtGoal(this, PlayerEntity.class, 64.0f));

        this.goalSelector.addGoal(2, new SwimGoal(this));
        this.goalSelector.addGoal(3, new WaterAvoidingRandomWalkingGoal(this, 1.0D));
        this.goalSelector.addGoal(4, new LookRandomlyGoal(this));

        this.targetSelector.addGoal(1, new NearestAttackableTargetGoal<>(this, PlayerEntity.class, true));
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, IronGolemEntity.class, true));
        this.targetSelector.addGoal(4, new NearestAttackableTargetGoal<>(this, AbstractVillagerEntity.class, false));
        this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, VillagerEntity.class, true));
        this.targetSelector.addGoal(5, new MoveTowardsTargetGoal(this, 1.0D, 32.0F));

        super.registerGoals();
    }

    public static AttributeModifierMap setCustomBasbossAttributes() {
        return MobEntity.func_233666_p_()
                .createMutableAttribute(Attributes.MAX_HEALTH, 500.0D)
                .createMutableAttribute(Attributes.ATTACK_DAMAGE, 3.5f)
                .createMutableAttribute(Attributes.ATTACK_SPEED, 2.0f)
                .createMutableAttribute(Attributes.MOVEMENT_SPEED, 0.50D)
                .createMutableAttribute(Attributes.FOLLOW_RANGE, 35.0D)
                .createMutableAttribute(Attributes.ATTACK_KNOCKBACK, 1.5f).create();

    }

    @Override
    public boolean isAggressive() {
        return true;
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    private <E extends IAnimatable>PlayState predicate(AnimationEvent<E> event) {

        if (event.isMoving()) {
            event.getController().setAnimation(new AnimationBuilder().addAnimation("walk", true));
            return PlayState.CONTINUE;
        }

        event.getController().setAnimation(new AnimationBuilder().addAnimation("idle", true));
        return PlayState.CONTINUE;

    }

    private <E extends IAnimatable>PlayState predicateAttack(AnimationEvent<E> event) {

        if (isSpinAttacking()) {
            event.getController().setAnimation(new AnimationBuilder().addAnimation("attack", true));
            return PlayState.CONTINUE;
        }

        return PlayState.CONTINUE;
    }

    @Override
    public void registerControllers(AnimationData animationData) {
        animationData.addAnimationController(
                new AnimationController(this, "basRobotBossController", 0, this::predicate));

        animationData.addAnimationController(
                new AnimationController(this, "attackController", 0, this::predicateAttack));


    }

    @Override
    public AnimationFactory getFactory() {
        return this.factory;
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    @Override
    protected void playStepSound(BlockPos pos, BlockState blockIn) {
        this.playSound(SoundEvents.BLOCK_METAL_STEP, 0.50F, 1.0F);
    }

    @Nullable
    @Override
    protected SoundEvent getAmbientSound() {
        return SoundEvents.EVENT_RAID_HORN;
    }

    @Nullable
    @Override
    protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
        return SoundEvents.ENTITY_VEX_HURT;
    }

    @Nullable
    @Override
    protected SoundEvent getDeathSound() {
        return SoundEvents.ENTITY_IRON_GOLEM_DEATH;
    }

    @Override
    protected float getSoundVolume() {
        return 10.5F;
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    @Override
    protected void dropSpecialItems(DamageSource source, int looting, boolean recentlyHitIn) {
        super.dropSpecialItems(source, looting, recentlyHitIn);
        Entity entity = source.getTrueSource();
        ItemEntity itementity = this.entityDropItem(Items.IRON_BLOCK);
        if (itementity != null) {
            itementity.setNoDespawn();
        }
        if (entity instanceof CreeperEntity) {
            CreeperEntity creeperentity = (CreeperEntity)entity;
            if (creeperentity.ableToCauseSkullDrop()) {
                ItemStack itemstack = this.getSkullDrop();
                if (!itemstack.isEmpty()) {
                    creeperentity.incrementDroppedSkulls();
                    this.entityDropItem(itemstack);
                }
            }
        }
    }

    protected ItemStack getSkullDrop() {
        return new ItemStack(Items.ZOMBIE_HEAD);
    }

    public CreatureAttribute getCreatureAttribute() {
        return CreatureAttribute.UNDEAD;
    }

    public boolean onLivingFall(float distance, float damageMultiplier) {
        return false;
    }

    @Override
    public boolean addPotionEffect(EffectInstance effectInstanceIn) {
        return false;
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    @Override
    protected void updateAITasks() {
        this.bossInfo.setPercent(this.getHealth() / this.getMaxHealth());
    }

    public void addTrackingPlayer(ServerPlayerEntity player) {
        super.addTrackingPlayer(player);
        this.bossInfo.addPlayer(player);
    }

    public void removeTrackingPlayer(ServerPlayerEntity player) {
        super.removeTrackingPlayer(player);
        this.bossInfo.removePlayer(player);
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    @Override
    public boolean isBurning() {
        return false;
    }

    @Override
    protected boolean isDespawnPeaceful() {
        return false;
    }

    @Override
    public boolean canBeHitWithPotion() {
        return false;
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

}
