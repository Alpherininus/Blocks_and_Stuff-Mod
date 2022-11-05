package com.alpherininus.basmod.common.entitys;

import com.alpherininus.basmod.common.entitys.ai.AttackGoal;
import com.alpherininus.basmod.common.materials.BasmodArmorMaterial;
import com.alpherininus.basmod.core.init.ItemInit;
import net.minecraft.block.BlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.boss.WitherEntity;
import net.minecraft.entity.monster.*;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.passive.RabbitEntity;
import net.minecraft.entity.passive.WolfEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.particles.BasicParticleType;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.stats.Stats;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.IServerWorld;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;

import javax.annotation.Nullable;
import java.util.function.Predicate;


public class NPCEntity extends CreatureEntity {

    private static final DataParameter<Integer> NPC_TYPE = EntityDataManager.createKey(NPCEntity.class, DataSerializers.VARINT);
    private static final ResourceLocation GUNTER_NPC = new ResourceLocation("gunter");
    private static final ResourceLocation LU_NPC = new ResourceLocation("lu");
    private static final ResourceLocation SOPHI_NPC = new ResourceLocation("sophi");
    private static final ResourceLocation ESEL_NPC = new ResourceLocation("esel");

    private static final Predicate<LivingEntity> field_213627_bA = (p_213626_0_) -> p_213626_0_ instanceof MobEntity;

    public NPCEntity(EntityType<? extends CreatureEntity> type, World worldIn) {
        super(type, worldIn);
    }

    public static AttributeModifierMap.MutableAttribute registerNPCAttributes() {
        return CreatureEntity.func_233666_p_()
                .createMutableAttribute(Attributes.MOVEMENT_SPEED, 0.5D)
                .createMutableAttribute(Attributes.MAX_HEALTH, 20.0D)
                .createMutableAttribute(Attributes.FOLLOW_RANGE, 30.0D);
    }

    @Override
    protected void registerGoals() {
        super.registerGoals();
        this.goalSelector.addGoal(0, new PanicGoal(this, 0.81D));
        this.addSwimGoals();
        this.goalSelector.addGoal(2, new RandomWalkingGoal(this, 0.5));
        this.addLookGoals();
    }

    protected void addPlayerAttackGoals() {
        this.goalSelector.addGoal(1, new AttackGoal(this, 1.2D, true));
        this.targetSelector.addGoal(1, new NearestAttackableTargetGoal<>(this, PlayerEntity.class, 10, true, false, field_213627_bA));

    }

    protected void addSwimGoals() {
        this.goalSelector.addGoal(0, new SwimGoal(this));
        this.goalSelector.addGoal(0, new WaterAvoidingRandomWalkingGoal(this, 2.5D));
    }

    protected void addLookGoals() {
        this.goalSelector.addGoal(1, new LookAtGoal(this, PlayerEntity.class, 16.0F));
        this.goalSelector.addGoal(2, new LookAtWithoutMovingGoal(this, PlayerEntity.class, 15.5F, 0.5F));
        this.goalSelector.addGoal(1, new LookAtGoal(this, CreatureEntity.class, 16.0F));
        this.goalSelector.addGoal(2, new LookAtWithoutMovingGoal(this, CreatureEntity.class, 15.5F, 2.5F));
        this.goalSelector.addGoal(1, new LookAtGoal(this, MonsterEntity.class, 16.0F));
        this.goalSelector.addGoal(2, new LookAtWithoutMovingGoal(this, MonsterEntity.class, 16.5F, 3.0F));
        this.goalSelector.addGoal(1, new LookAtGoal(this, AnimalEntity.class, 16.0F));
        this.goalSelector.addGoal(2, new LookAtWithoutMovingGoal(this, AnimalEntity.class, 15.5F, 1.5F));
        this.goalSelector.addGoal(1, new LookAtGoal(this, AbstractSkeletonEntity.class, 16.0F));
        this.goalSelector.addGoal(2, new LookAtWithoutMovingGoal(this, AbstractSkeletonEntity.class, 16.5F, 3.0F));
    }

    protected void addMobAttackGoals() {
        this.goalSelector.addGoal(1, new AttackGoal(this, 1.2D, true));
        this.targetSelector.addGoal(1, (new HurtByTargetGoal(this)).setCallsForHelp());
        this.targetSelector.addGoal(1, new NearestAttackableTargetGoal<>(this, MonsterEntity.class, 5, true, false, field_213627_bA));
        this.targetSelector.addGoal(1, new NearestAttackableTargetGoal<>(this, CreatureEntity.class, 5, true, false, field_213627_bA));
        this.targetSelector.addGoal(1, new NearestAttackableTargetGoal<>(this, AnimalEntity.class, 5, true, false, field_213627_bA));
        this.targetSelector.addGoal(1, new NearestAttackableTargetGoal<>(this, AbstractSkeletonEntity.class, 5, true, false, field_213627_bA));

    }

    @Override
    protected void registerData() {
        super.registerData();
        this.dataManager.register(NPC_TYPE, 0);
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public void writeAdditional(CompoundNBT compound) {
        super.writeAdditional(compound);
        compound.putInt("BasNPCType", this.getNPCEntityType());
    }

    public void readAdditional(CompoundNBT compound) {
        super.readAdditional(compound);
        this.setTypeEsel(compound.getInt("BasNPCType"), this);
        this.setTypeGunter(compound.getInt("BasNPCType"), this);
        this.setTypeLu(compound.getInt("BasNPCType"), this);
        this.setTypeSophi(compound.getInt("BasNPCType"), this);

    }

    public int getNPCEntityType() {
        return this.dataManager.get(NPC_TYPE);
    }

    // ID 0 bis 99
    public void setTypeGunter(int typeGunterID, NPCEntity entity) {
        String str = TextFormatting.getTextWithoutFormattingCodes(entity.getName().getString());

        if (str != null && "Gunter".equals(str)) {
            this.getAttribute(Attributes.ARMOR).setBaseValue(8.0D);
            this.addMobAttackGoals();
            if (!this.hasCustomName()) {
                this.setCustomName(new TranslationTextComponent(Util.makeTranslationKey("entity", GUNTER_NPC)));
            }
        }

        this.dataManager.set(NPC_TYPE, typeGunterID);
    }

    public void setTypeLu(int typeLuID, NPCEntity entity) {
        String str = TextFormatting.getTextWithoutFormattingCodes(entity.getName().getString());
        if (str != null && "Lu".equals(str)) {
            this.getAttribute(Attributes.ARMOR).setBaseValue(8.0D);
            if (!this.hasCustomName()) {
                this.setCustomName(new TranslationTextComponent(Util.makeTranslationKey("entity", LU_NPC)));
            }
        }

        this.dataManager.set(NPC_TYPE, typeLuID);
    }

    public void setTypeSophi(int typeSophiID, NPCEntity entity) {
        String str = TextFormatting.getTextWithoutFormattingCodes(entity.getName().getString());
        if (str != null && "Sophi".equals(str)) {
            this.getAttribute(Attributes.ARMOR).setBaseValue(8.0D);
            this.goalSelector.addGoal(1, new TemptGoal(this, 1.0D, Ingredient.fromItems(ItemInit.RUBY_ITEM.get()), true));
            if (!this.hasCustomName()) {
                this.setCustomName(new TranslationTextComponent(Util.makeTranslationKey("entity", SOPHI_NPC)));
            }
        }

        this.dataManager.set(NPC_TYPE, typeSophiID);
    }

    public void setTypeEsel(int typeEselID, NPCEntity entity) {
        String str = TextFormatting.getTextWithoutFormattingCodes(entity.getName().getString());
        if (str != null && "Esel".equals(str)) {
            this.getAttribute(Attributes.ARMOR).setBaseValue(8.0D);
            this.goalSelector.addGoal(4, new NPCEntity.EvilAttackGoal(this));
            this.addPlayerAttackGoals();
            if (!this.hasCustomName()) {
                this.setCustomName(new TranslationTextComponent(Util.makeTranslationKey("entity", ESEL_NPC)));
            }
        }

        this.dataManager.set(NPC_TYPE, typeEselID);
    }

    static class EvilAttackGoal extends MeleeAttackGoal {
        public EvilAttackGoal(NPCEntity npcsIn) {
            super(npcsIn, 2.4D, true);
        }

        protected double getAttackReachSqr(LivingEntity attackTarget) {
            return (double)(6.0F + attackTarget.getWidth());
        }
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

    @Override
    public boolean attackEntityAsMob(Entity entityIn) {
        if (this.getNPCEntityType() == 99) {
            this.playSound(SoundEvents.ENTITY_PLAYER_ATTACK_CRIT, 1.0F, (this.rand.nextFloat() - this.rand.nextFloat()) * 0.2F + 1.0F);
            return entityIn.attackEntityFrom(DamageSource.causeMobDamage(this), 8.0F);

        } else if (this.getNPCEntityType() == 96) {
            this.playSound(SoundEvents.ENTITY_PLAYER_ATTACK_STRONG, 1.0F, (this.rand.nextFloat() - this.rand.nextFloat()) * 0.2F + 1.0F);
            return entityIn.attackEntityFrom(DamageSource.causeMobDamage(this), 8.0F);

        } else {
            return entityIn.attackEntityFrom(DamageSource.causeMobDamage(this), 3.0F);
        }
    }

    @Override
    public SoundCategory getSoundCategory() {
        return this.getNPCEntityType() == 99 && this.getNPCEntityType() == 96 ? SoundCategory.HOSTILE : SoundCategory.NEUTRAL;
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

        Minecraft mc = Minecraft.getInstance();
        ItemStack itemstack = playerIn.getHeldItem(hand);

        if (!world.isRemote()) {
            assert mc.world != null;

            playerIn.addStat(Stats.TALKED_TO_VILLAGER);

        }

        return ActionResultType.func_233537_a_(world.isRemote);
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////

}
