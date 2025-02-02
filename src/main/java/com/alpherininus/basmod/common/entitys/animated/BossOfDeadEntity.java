package com.alpherininus.basmod.common.entitys.animated;

import com.alpherininus.basmod.common.entitys.NPCEntity;
import net.minecraft.block.BlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.merchant.villager.AbstractVillagerEntity;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.passive.IronGolemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.ShootableItem;
import net.minecraft.network.play.server.SChangeGameStatePacket;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.EffectUtils;
import net.minecraft.potion.Effects;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.BossInfo;
import net.minecraft.world.Difficulty;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerBossInfo;
import net.minecraft.world.server.ServerWorld;
import software.bernie.geckolib3.core.AnimationState;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;

public class BossOfDeadEntity extends MonsterEntity implements IAnimatable {

    private int getArmSwingAnimationEnd() {
        if (EffectUtils.hasMiningSpeedup(this)) {
            return 6 - (1 + EffectUtils.getMiningSpeedup(this));
        } else {
            return this.isPotionActive(Effects.MINING_FATIGUE) ? 6 + (1 + Objects.requireNonNull(this.getActivePotionEffect(Effects.MINING_FATIGUE)).getAmplifier()) * 2 : 6;
        }
    }

    private static final EntityPredicate PLAYER_INVADER_CONDITION = (new EntityPredicate()).setDistance(64.0D);
    private static final Predicate<LivingEntity> NOT_UNDEAD = (p_213797_0_) -> p_213797_0_.getCreatureAttribute() != CreatureAttribute.UNDEAD && p_213797_0_.attackable();

    private final ServerBossInfo bossInfo1Phase = new ServerBossInfo(new StringTextComponent("Dance of Death"), BossInfo.Color.PINK, BossInfo.Overlay.PROGRESS);

    private final Minecraft mc = Minecraft.getInstance();

    private static final Predicate<LivingEntity> field_213627_bA = (p_213626_0_) -> p_213626_0_ instanceof MobEntity;

    private final AnimationFactory factory = new AnimationFactory(this);

    public BossOfDeadEntity(EntityType<? extends MonsterEntity> entityType, World worldIn) {
        super(entityType, worldIn);
    }

    protected void registerGoals() {
        this.addSwimGoals();
        this.addAttackGoal();

        this.goalSelector.addGoal(5, new LookAtGoal(this, PlayerEntity.class, 16.0F));
        this.goalSelector.addGoal(6, new LookRandomlyGoal(this));
        this.goalSelector.addGoal(2, new LookAtWithoutMovingGoal(this, PlayerEntity.class, 15.5F, 1.0F));

        this.targetSelector.addGoal(1, new HurtByTargetGoal(this));
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, PlayerEntity.class, 10, true, false, field_213627_bA));
        this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, IronGolemEntity.class, true));
        this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, AbstractVillagerEntity.class, false));
    }

    protected void addAttackGoal() {
        this.goalSelector.addGoal(2, new BossOfDeadEntity.EvilAttackGoal(this));
        this.targetSelector.addGoal(1, (new HurtByTargetGoal(this)).setCallsForHelp());
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, PlayerEntity.class, true));
    }

    protected void addSwimGoals() {
        this.goalSelector.addGoal(0, new SwimGoal(this));
        this.goalSelector.addGoal(4, new WaterAvoidingRandomWalkingGoal(this, 2.0D));
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public static AttributeModifierMap setCustomBossOfDeadAttributes() {
        return MobEntity.func_233666_p_()
                .createMutableAttribute(Attributes.MAX_HEALTH, 1500.0D)
                .createMutableAttribute(Attributes.ATTACK_DAMAGE, 15.0f)
                .createMutableAttribute(Attributes.ATTACK_SPEED, 1.3f)
                .createMutableAttribute(Attributes.MOVEMENT_SPEED, 0.25D)
                .createMutableAttribute(Attributes.ZOMBIE_SPAWN_REINFORCEMENTS, 128.0D)
                .createMutableAttribute(Attributes.FOLLOW_RANGE, 128.0D)
                .createMutableAttribute(Attributes.ATTACK_KNOCKBACK, 1.0f).create();

    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    @Override
    public ItemStack findAmmo(ItemStack shootable) {
        if (shootable.getItem() instanceof ShootableItem) {
            Predicate<ItemStack> predicate = ((ShootableItem)shootable.getItem()).getAmmoPredicate();
            ItemStack itemstack = ShootableItem.getHeldAmmo(this, predicate);
            return itemstack.isEmpty() ? new ItemStack(Items.ARROW) : itemstack;
        } else {
            return ItemStack.EMPTY;
        }
    }

    @Override
    protected int getExperiencePoints(PlayerEntity player) {
        return 5 + this.world.rand.nextInt(11);
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    private <E extends IAnimatable> PlayState predicate(AnimationEvent<E> event) {
        event.getController().setAnimation(new AnimationBuilder().addAnimation("idle", true));
        return PlayState.CONTINUE;

    }

    private PlayState attackPredicate(AnimationEvent event) {
        if (this.isSwingInProgress && event.getController().getAnimationState().equals(AnimationState.Stopped)) {
            event.getController().markNeedsReload();
            event.getController().setAnimation(new AnimationBuilder().addAnimation("attack", false));

            this.isSwingInProgress = true;

        }

        return PlayState.CONTINUE;
    }

    @Override
    public void registerControllers(AnimationData data) {

        data.addAnimationController(
                new AnimationController(this, "ontroller_idle", 0, this::predicate));

        data.addAnimationController(
                new AnimationController(this, "attack_controller", 0, this::attackPredicate));

    }



    @Override
    public AnimationFactory getFactory() {
        return this.factory;
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    @Override
    protected void playStepSound(BlockPos pos, BlockState blockIn) {
        this.playSound(SoundEvents.BLOCK_METAL_STEP, 0.50F, 1.0F);
    }

    @Override
    protected SoundEvent getAmbientSound() {
        if (this.isInWater()) {
            return SoundEvents.ENTITY_WITCH_HURT;
        } else {
            if (this.isBurning()) {
                return SoundEvents.ENTITY_LIGHTNING_BOLT_IMPACT;
            } else {
                return SoundEvents.ENTITY_WITCH_AMBIENT;

            }
        }
    }

    @Nullable
    @Override
    protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
        return SoundEvents.ENTITY_WITCH_HURT;
    }

    @Nullable
    @Override
    protected SoundEvent getDeathSound() {
        return SoundEvents.ENTITY_WITHER_DEATH;
    }

    @Override
    protected float getSoundVolume() {
        return 5.5F;
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

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

        if (this.ticksExisted % 20 == 0) {
            this.heal(1.5F);
        }

        if (this.getHealth() <= 10.5F) {
            this.heal(500.5F);
        }

        int i = 1200;
        if ((this.ticksExisted + this.getEntityId()) % 1200 == 0) {
            Effect effect1 = Effects.WEAKNESS;
            Effect effect2 = Effects.UNLUCK;
            Effect effect3 = Effects.MINING_FATIGUE;

            List<ServerPlayerEntity> list = ((ServerWorld)this.world).getPlayers((p_210138_1_) -> this.getDistanceSq(p_210138_1_) < 2500.0D && p_210138_1_.interactionManager.survivalOrAdventure());
            int j = 2;
            int k = 6000;
            int l = 1200;

            for(ServerPlayerEntity serverplayerentity : list) {
                if (!serverplayerentity.isPotionActive(effect1) || serverplayerentity.getActivePotionEffect(effect1).getAmplifier() < 2 || serverplayerentity.getActivePotionEffect(effect1).getDuration() < 1200) {
                    serverplayerentity.connection.sendPacket(new SChangeGameStatePacket(SChangeGameStatePacket.HIT_PLAYER_ARROW, this.isSilent() ? 0.0F : 1.0F));
                    serverplayerentity.addPotionEffect(new EffectInstance(effect1, 6000, 2));
                }

                if (this.hurtTime % 10 == 0) {
                    if (!serverplayerentity.isPotionActive(effect2) || serverplayerentity.getActivePotionEffect(effect2).getAmplifier() < 2 || serverplayerentity.getActivePotionEffect(effect2).getDuration() < 1200) {
                        serverplayerentity.connection.sendPacket(new SChangeGameStatePacket(SChangeGameStatePacket.HIT_PLAYER_ARROW, this.isSilent() ? 0.0F : 1.0F));
                        serverplayerentity.addPotionEffect(new EffectInstance(effect2, 6000, 2));
                    }

                }

                if (this.deathTime % 30 == 0) {
                    if (!serverplayerentity.isPotionActive(effect3) || serverplayerentity.getActivePotionEffect(effect3).getAmplifier() < 2 || serverplayerentity.getActivePotionEffect(effect3).getDuration() < 1200) {
                        serverplayerentity.connection.sendPacket(new SChangeGameStatePacket(SChangeGameStatePacket.HIT_PLAYER_ARROW, this.isSilent() ? 0.0F : 1.0F));
                        serverplayerentity.addPotionEffect(new EffectInstance(effect3, 6000, 2));
                    }
                }
            }
        }
        this.bossInfo1Phase.setPercent(this.getHealth() / this.getMaxHealth());

    }

    public void addTrackingPlayer(ServerPlayerEntity player) {
        super.addTrackingPlayer(player);
        this.bossInfo1Phase.addPlayer(player);
    }

    public void removeTrackingPlayer(ServerPlayerEntity player) {
        super.removeTrackingPlayer(player);
        this.bossInfo1Phase.addPlayer(player);
    }

    protected void updateArmSwingProgress() {
        int i = this.getArmSwingAnimationEnd();
        if (this.isSwingInProgress) {
            ++this.swingProgressInt;
            if (this.swingProgressInt >= i) {
                this.swingProgressInt = 0;
                this.isSwingInProgress = false;
            }
        } else {
            this.swingProgressInt = 0;
        }

        this.swingProgress = (float)this.swingProgressInt / (float)i;
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    @Override
    public boolean canBeHitWithPotion() {
        return false;
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public void checkDespawn() {
        assert mc.player != null;

        if (this.world.getDifficulty() == Difficulty.PEACEFUL && this.isDespawnPeaceful()) {
            this.remove();
        } else {
            this.idleTime = 0;
        }

    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    @Override
    public void livingTick() {
        super.livingTick();
    }

    static class EvilAttackGoal extends MeleeAttackGoal {
        public EvilAttackGoal(BossOfDeadEntity boss) {
            super(boss, 1.4D, true);
        }

        protected double getAttackReachSqr(LivingEntity attackTarget) {
            return (double)(4.0F + attackTarget.getWidth());
        }
    }

}
