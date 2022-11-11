package com.alpherininus.basmod.common.entitys;

import com.alpherininus.basmod.common.entitys.ai.AttackGoal;
import net.minecraft.block.BlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.monster.AbstractSkeletonEntity;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.particles.BasicParticleType;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.stats.Stats;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;

import java.util.UUID;
import java.util.function.Predicate;


public class NPCEntityCc extends CreatureEntity {

    private static final DataParameter<Integer> NPC_TYPE = EntityDataManager.createKey(NPCEntitycc.class, DataSerializers.VARINT);
    private static final Predicate<LivingEntity> field_213627_bA = (p_213626_0_) -> p_213626_0_ instanceof MobEntity;

    public NPCEntityCc(EntityType<? extends CreatureEntity> type, World worldIn) {
        super(type, worldIn);
    }

    public static AttributeModifierMap.MutableAttribute registerNPCAttributes() {
        return CreatureEntity.func_233666_p_()
                .createMutableAttribute(Attributes.MOVEMENT_SPEED, 0.25D)
                .createMutableAttribute(Attributes.MAX_HEALTH, 20.0D)
                .createMutableAttribute(Attributes.FOLLOW_RANGE, 30.0D);
    }

    @Override
    protected void registerGoals() {
        super.registerGoals();
        this.goalSelector.addGoal(3, new PanicGoal(this, 0.75D));
        this.addSwimGoals();
        this.addLookGoals();

    }

    protected void addPlayerAttackGoals() {
        this.goalSelector.addGoal(1, new AttackGoal(this, 1.0D, true));
        this.targetSelector.addGoal(1, new NearestAttackableTargetGoal<>(this, PlayerEntity.class, 10, true, false, field_213627_bA));

    }

    protected void addSwimGoals() {
        this.goalSelector.addGoal(0, new SwimGoal(this));
        this.goalSelector.addGoal(0, new WaterAvoidingRandomWalkingGoal(this, 2.0D));
    }

    protected void addLookGoals() {
        this.goalSelector.addGoal(1, new LookAtGoal(this, PlayerEntity.class, 6.0F));
        this.goalSelector.addGoal(2, new LookAtWithoutMovingGoal(this, PlayerEntity.class, 6.0F, 0.5F));
        this.goalSelector.addGoal(1, new LookAtGoal(this, CreatureEntity.class, 16.0F));
        this.goalSelector.addGoal(2, new LookAtWithoutMovingGoal(this, CreatureEntity.class, 10.5F, 0.5F));
        this.goalSelector.addGoal(1, new LookAtGoal(this, MonsterEntity.class, 16.0F));
        this.goalSelector.addGoal(2, new LookAtWithoutMovingGoal(this, MonsterEntity.class, 16.0F, 3.0F));
        this.goalSelector.addGoal(1, new LookAtGoal(this, AnimalEntity.class, 10.0F));
        this.goalSelector.addGoal(2, new LookAtWithoutMovingGoal(this, AnimalEntity.class, 10.5F, 2.0F));
    }

    protected void addMobAttackGoals() {
        this.goalSelector.addGoal(1, new AttackGoal(this, 1.0D, true));
        this.targetSelector.addGoal(1, (new HurtByTargetGoal(this)).setCallsForHelp());
        this.targetSelector.addGoal(1, new NearestAttackableTargetGoal<>(this, MonsterEntity.class, 5, true, false, field_213627_bA));
        this.targetSelector.addGoal(1, new NearestAttackableTargetGoal<>(this, CreatureEntity.class, 5, true, false, field_213627_bA));
        this.targetSelector.addGoal(1, new NearestAttackableTargetGoal<>(this, AnimalEntity.class, 5, true, false, field_213627_bA));
        this.targetSelector.addGoal(1, new NearestAttackableTargetGoal<>(this, AbstractSkeletonEntity.class, 5, true, false, field_213627_bA));

    }

    static class EvilAttackGoal extends MeleeAttackGoal {
        public EvilAttackGoal(NPCEntitycc npcsIn) {
            super(npcsIn, 1.4D, true);
        }

        protected double getAttackReachSqr(LivingEntity attackTarget) {
            return (double)(6.0F + attackTarget.getWidth());
        }
    }
    AnimalEntity
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////



    @Override
    protected void registerData() {
        super.registerData();
        this.dataManager.register(NPC_TYPE, 0);
    }

    public int getNPCEntityType() {
        return this.dataManager.get(NPC_TYPE);
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    @Override
    protected int getExperiencePoints(PlayerEntity player) {
        return 3 + this.world.rand.nextInt(5);
    }

    // Sounds
    @Override
    protected SoundEvent getAmbientSound() {
        if (this.isInWater()) {
            return SoundEvents.ENTITY_SLIME_JUMP;
        } else {
            if (this.isBurning()) {
                return SoundEvents.ENTITY_LIGHTNING_BOLT_IMPACT;
            } else {
                return SoundEvents.ENTITY_VILLAGER_AMBIENT;

            }
        }
    }

    @Override
    protected SoundEvent getDeathSound() {
        return SoundEvents.ENTITY_VILLAGER_DEATH;
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
        return SoundEvents.ENTITY_VILLAGER_HURT;
    }

    @Override
    protected void playStepSound(BlockPos pos, BlockState blockIn) {
        this.playSound(SoundEvents.ENTITY_WOLF_STEP, 0.20F, 0.5F);
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // ...
    public boolean canDespawn(double distanceToClosestPlayer) {
        return false;
    }

    // Interactions
    public boolean hitByEntity(Entity entityIn) {

        Minecraft mc = Minecraft.getInstance();

        if (!world.isRemote()) {

            assert mc.world != null;
            mc.world.addParticle(new BasicParticleType(true), ParticleTypes.ANGRY_VILLAGER.getAlwaysShow(), 1, 1, 0, 0, 2, 2);

        }
        return false;
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public ActionResultType getEntityInteractionResult(PlayerEntity playerIn, Hand hand) {
        NPCEntitycc entity = null;
        assert false;
        String str = TextFormatting.getTextWithoutFormattingCodes(entity.getName().getString());

        Minecraft mc = Minecraft.getInstance();
        ItemStack itemstack = playerIn.getHeldItem(hand);

        if (!world.isRemote()) {
            assert mc.world != null;

            if (str != null && "Gunter".equals(str)) {
                mc.player.sendMessage(new StringTextComponent("Hallo ich bin Gunter."), UUID.randomUUID());
            } else {
            }

            playerIn.addStat(Stats.TALKED_TO_VILLAGER);

        }

        return ActionResultType.func_233537_a_(world.isRemote);
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////

}
