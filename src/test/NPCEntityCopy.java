package com.alpherininus.basmod.common.entitys.custom;

import com.alpherininus.basmod.core.init.ItemInit;
import net.minecraft.block.BlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.monster.ZombieEntity;
import net.minecraft.entity.passive.ParrotEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.particles.BasicParticleType;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.stats.Stats;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;


public class NPCEntity extends CreatureEntity {

    private static final DataParameter<Integer> VARIANT = EntityDataManager.createKey(ParrotEntity.class, DataSerializers.VARINT);

    public NPCEntity(EntityType<? extends CreatureEntity> type, World worldIn) {
        super(type, worldIn);
    }

    public static AttributeModifierMap.MutableAttribute registerCustomAttributes() {
        return CreatureEntity.func_233666_p_()
                .createMutableAttribute(Attributes.MOVEMENT_SPEED, 0.5D)
                .createMutableAttribute(Attributes.MAX_HEALTH, 20.0D)
                .createMutableAttribute(Attributes.FOLLOW_RANGE, 30.0D);
    }

    @Override
    protected void registerGoals() {
        super.registerGoals();

        this.goalSelector.addGoal(0, new SwimGoal(this));
        this.goalSelector.addGoal(1, new PanicGoal(this, 0.81D));

        this.goalSelector.addGoal(2, new TemptGoal(this, 1.0D, Ingredient.fromItems(ItemInit.RUBY.get()), true));
        this.targetSelector.addGoal(2, new MoveTowardsRestrictionGoal(this, 1.0));

        this.goalSelector.addGoal(1, new RandomWalkingGoal(this, 0.5));

        this.goalSelector.addGoal(1, new LookAtGoal(this, PlayerEntity.class, 0.8F));
        this.goalSelector.addGoal(1, new LookRandomlyGoal(this));
        this.goalSelector.addGoal(1, new LookAtWithoutMovingGoal(this, PlayerEntity.class, 15.0F, 10F));

        this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, ZombieEntity.class, true));
        this.goalSelector.addGoal(2, new WaterAvoidingRandomWalkingGoal(this, 0.5D));

        this.targetSelector.addGoal(2, new LookAtGoal(this, PlayerEntity.class, 0.8F));
        this.targetSelector.addGoal(2, new LookRandomlyGoal(this));
        this.targetSelector.addGoal(2, new LookAtWithoutMovingGoal(this, PlayerEntity.class, 15.0F, 10F));
    }

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

    public ActionResultType getEntityInteractionResult(PlayerEntity playerIn, Hand hand) {

        Minecraft mc = Minecraft.getInstance();
        ItemStack itemstack = playerIn.getHeldItem(hand);

        if (!world.isRemote()) {
            assert mc.world != null;

            playerIn.addStat(Stats.TALKED_TO_VILLAGER);

        }

        return ActionResultType.func_233537_a_(world.isRemote);
    }

    public int getVariant() {
        return MathHelper.clamp(this.dataManager.get(VARIANT), 0, 10);
    }
}
