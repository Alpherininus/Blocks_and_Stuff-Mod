package com.alpherininus.basmod.common.entitys;

import com.alpherininus.basmod.common.entitys.ai.AttackGoal;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.boss.dragon.EnderDragonEntity;
import net.minecraft.entity.monster.*;
import net.minecraft.entity.passive.PigEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;

import java.util.UUID;
import java.util.function.Predicate;

public class NPCEntity extends CreatureEntity {

    private static final DataParameter<Integer> NPC_TYPE = EntityDataManager.createKey(NPCEntity.class, DataSerializers.VARINT);

    private static final Predicate<LivingEntity> field_213627_bA = (p_213626_0_) -> p_213626_0_ instanceof MobEntity;

    private static final ResourceLocation KILLER_GUENTER = new ResourceLocation("killer_guenter");
    private static final ResourceLocation NORMAL_GUENTER = new ResourceLocation("normal_guenter");


    public NPCEntity(EntityType<? extends CreatureEntity> p_i50247_1_, World p_i50247_2_) {
        super(p_i50247_1_, p_i50247_2_);
    }

    public static AttributeModifierMap.MutableAttribute registerNPCAttributes() {
        return CreatureEntity.func_233666_p_()
                .createMutableAttribute(Attributes.MOVEMENT_SPEED, 0.25D)
                .createMutableAttribute(Attributes.MAX_HEALTH, 20.0D)
                .createMutableAttribute(Attributes.FOLLOW_RANGE, 30.0D);
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    protected void registerGoals() {
        this.goalSelector.addGoal(1, new SwimGoal(this));
        this.goalSelector.addGoal(6, new WaterAvoidingRandomWalkingGoal(this, 0.6D));
        this.goalSelector.addGoal(11, new LookAtGoal(this, PlayerEntity.class, 10.0F));
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    protected void addPlayerAttackGoals() {
        this.goalSelector.addGoal(1, new AttackGoal(this, 1.0D, true));
        this.targetSelector.addGoal(1, new NearestAttackableTargetGoal<>(this, PlayerEntity.class, 10, true, false, field_213627_bA));

    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    protected void registerData() {
        super.registerData();
        this.dataManager.register(NPC_TYPE, 0);

    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public void updateAITasks() {

    }

    public void writeAdditional(CompoundNBT compound) {
        super.writeAdditional(compound);
        compound.putInt("NPCType", this.getNPCEntityType());

    }

    public void readAdditional(CompoundNBT compound) {
        super.readAdditional(compound);
        this.setNPCType(compound.getInt("NPCType"));

    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    protected SoundEvent getAmbientSound() {
        if (this.getNPCEntityType() == 69) {
            this.playSound(SoundEvents.ENTITY_VILLAGER_YES, 50.0F, (this.rand.nextFloat() - this.rand.nextFloat()) * 0.2F + 1.0F);
        } else {
            return SoundEvents.ENTITY_RABBIT_AMBIENT;
        }
        return null;
    }

    protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
        if (this.getNPCEntityType() == 69) {
            this.playSound(SoundEvents.ENTITY_VILLAGER_HURT, 50.0F, (this.rand.nextFloat() - this.rand.nextFloat()) * 0.2F + 1.0F);
        } else {
            return SoundEvents.ENTITY_RABBIT_HURT;
        }
        return null;
    }

    protected SoundEvent getDeathSound() {
        return SoundEvents.ENTITY_RABBIT_DEATH;
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public boolean attackEntityAsMob(Entity entityIn) {
        if (this.getNPCEntityType() == 99) {
            this.playSound(SoundEvents.ENTITY_RABBIT_ATTACK, 1.0F, (this.rand.nextFloat() - this.rand.nextFloat()) * 0.2F + 1.0F);
            return entityIn.attackEntityFrom(DamageSource.causeMobDamage(this), 8.0F);
        } else {
            return entityIn.attackEntityFrom(DamageSource.causeMobDamage(this), 3.0F);
        }
    }

    public boolean attackEntityFrom(DamageSource source, float amount) {
        return !this.isInvulnerableTo(source) && super.attackEntityFrom(source, amount);
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public int getNPCEntityType() {
        return this.dataManager.get(NPC_TYPE);
    }

    public void setNPCType(int npcTypeId) {
        if (npcTypeId == 99) {
            this.getAttribute(Attributes.ARMOR).setBaseValue(12.0D);
            this.goalSelector.addGoal(4, new NPCEntity.EvilAttackGoal(this));
            this.targetSelector.addGoal(1, (new HurtByTargetGoal(this)).setCallsForHelp());
            this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, PlayerEntity.class, true));
            if (!this.hasCustomName()) {
                this.setCustomName(new TranslationTextComponent(Util.makeTranslationKey("entity", KILLER_GUENTER)));
            }
        }

        if (npcTypeId == 96) {
            this.getAttribute(Attributes.ARMOR).setBaseValue(20.0D);
            this.goalSelector.addGoal(4, new NPCEntity.EvilAttackGoal(this));
            this.targetSelector.addGoal(1, (new HurtByTargetGoal(this)).setCallsForHelp());
            this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, SkeletonEntity.class, true));
            this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, AbstractIllagerEntity.class, true));
            this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, AbstractRaiderEntity.class, true));
            this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, SpiderEntity.class, true));
            this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, ZombieEntity.class, true));
            if (!this.hasCustomName()) {
                this.setCustomName(new TranslationTextComponent(Util.makeTranslationKey("entity.npc_knight.", KILLER_GUENTER)));
            }
        }
        if (npcTypeId == 95) {
            this.getAttribute(Attributes.ARMOR).setBaseValue(30.0D);
            this.goalSelector.addGoal(4, new NPCEntity.EvilAttackGoal(this));
            this.targetSelector.addGoal(1, (new HurtByTargetGoal(this)).setCallsForHelp());
            this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, SkeletonEntity.class, true));
            this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, PillagerEntity.class, true));
            this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, SpiderEntity.class, true));
            this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, ZombieEntity.class, true));
            if (!this.hasCustomName()) {
                this.setCustomName(new TranslationTextComponent(Util.makeTranslationKey("entity.npc_dark_knight.", KILLER_GUENTER)));
            }
        }
        if (npcTypeId == 94) {
            this.getAttribute(Attributes.ARMOR).setBaseValue(30.0D);
            this.goalSelector.addGoal(4, new NPCEntity.EvilAttackGoal(this));
            this.targetSelector.addGoal(1, (new HurtByTargetGoal(this)).setCallsForHelp());
            this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, SkeletonEntity.class, true));
            this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, PillagerEntity.class, true));
            this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, SpiderEntity.class, true));
            this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, ZombieEntity.class, true));
            if (!this.hasCustomName()) {
                this.setCustomName(new TranslationTextComponent(Util.makeTranslationKey("entity.npc_knight_kavilier.", KILLER_GUENTER)));
            }
        }
        if (npcTypeId == 93) {
            this.getAttribute(Attributes.ARMOR).setBaseValue(10.0D);
            this.goalSelector.addGoal(4, new NPCEntity.EvilAttackGoal(this));
            this.targetSelector.addGoal(1, (new HurtByTargetGoal(this)).setCallsForHelp());
            this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, SkeletonEntity.class, true));
            this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, PillagerEntity.class, true));
            this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, SpiderEntity.class, true));
            this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, ZombieEntity.class, true));

            if (!this.hasCustomName()) {
                this.setCustomName(new TranslationTextComponent(Util.makeTranslationKey("entity.npc_knight_magier.", KILLER_GUENTER)));
            }
        }
        if (npcTypeId == 69) {
            this.getAttribute(Attributes.ARMOR).setBaseValue(100.0D);
            this.getAttribute(Attributes.MAX_HEALTH).setBaseValue(+ 55.5D);
            this.getAttribute(Attributes.KNOCKBACK_RESISTANCE).setBaseValue(10000.0D);
            this.goalSelector.addGoal(4, new NPCEntity.EvilAttackGoal(this));
            this.targetSelector.addGoal(1, (new HurtByTargetGoal(this)).setCallsForHelp());
            this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, MonsterEntity.class, true));
            this.targetSelector.addGoal(4, new NearestAttackableTargetGoal<>(this, EnderDragonEntity.class, true));
            this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, EndermanEntity.class, true));

            if (!this.hasCustomName()) {
                this.setCustomName(new TranslationTextComponent(Util.makeTranslationKey("entity.npc_gigachad.", KILLER_GUENTER)));
            }
        }

        ///////////////////////////////////////////////////////////////////////////////////////////////////////////////

        if (npcTypeId == 1) {
            this.getAttribute(Attributes.ARMOR).setBaseValue(15.0D);
            if (!this.hasCustomName()) {
                String NPC_NAME = "gunter";
                this.setCustomName(new TranslationTextComponent(Util.makeTranslationKey("entity.npc_" + NPC_NAME, NORMAL_GUENTER)));
            }
        }

        if (npcTypeId == 2) {
            this.getAttribute(Attributes.ARMOR).setBaseValue(15.0D);
            if (!this.hasCustomName()) {
                String NPC_NAME = "lu";
                this.setCustomName(new TranslationTextComponent(Util.makeTranslationKey("entity.npc_" + NPC_NAME, NORMAL_GUENTER)));
            }
        }

        if (npcTypeId == 3) {
            this.getAttribute(Attributes.ARMOR).setBaseValue(15.0D);
            if (!this.hasCustomName()) {
                String NPC_NAME = "sophi";
                this.setCustomName(new TranslationTextComponent(Util.makeTranslationKey("entity.npc_" + NPC_NAME, NORMAL_GUENTER)));
            }
        }

        if (npcTypeId == 4) {
            this.getAttribute(Attributes.ARMOR).setBaseValue(15.0D);
            if (!this.hasCustomName()) {
                String NPC_NAME = "fladimir";
                this.setCustomName(new TranslationTextComponent(Util.makeTranslationKey("entity.npc_" + NPC_NAME, NORMAL_GUENTER)));
            }
        }

        if (npcTypeId == 5) {
            this.getAttribute(Attributes.ARMOR).setBaseValue(15.0D);
            if (!this.hasCustomName()) {
                String NPC_NAME = "frank";
                this.setCustomName(new TranslationTextComponent(Util.makeTranslationKey("entity.npc_" + NPC_NAME, NORMAL_GUENTER)));
            }
        }

        if (npcTypeId == 6) {
            this.getAttribute(Attributes.ARMOR).setBaseValue(15.0D);
            if (!this.hasCustomName()) {
                String NPC_NAME = "olaf";
                this.setCustomName(new TranslationTextComponent(Util.makeTranslationKey("entity.npc_" + NPC_NAME, NORMAL_GUENTER)));
            }
        }

        if (npcTypeId == 7) {
            this.getAttribute(Attributes.ARMOR).setBaseValue(15.0D);
            if (!this.hasCustomName()) {
                String NPC_NAME = "kurata";
                this.setCustomName(new TranslationTextComponent(Util.makeTranslationKey("entity.npc_" + NPC_NAME, NORMAL_GUENTER)));
            }
        }

        if (npcTypeId == 8) {
            this.getAttribute(Attributes.ARMOR).setBaseValue(15.0D);
            if (!this.hasCustomName()) {
                String NPC_NAME = "jonny";
                this.setCustomName(new TranslationTextComponent(Util.makeTranslationKey("entity.npc_" + NPC_NAME, NORMAL_GUENTER)));
            }
        }

        if (npcTypeId == 9) {
            this.getAttribute(Attributes.ARMOR).setBaseValue(15.0D);
            if (!this.hasCustomName()) {
                String NPC_NAME = "anne";
                this.setCustomName(new TranslationTextComponent(Util.makeTranslationKey("entity.npc_" + NPC_NAME, NORMAL_GUENTER)));
            }
        }

        if (npcTypeId == 10) {
            this.getAttribute(Attributes.ARMOR).setBaseValue(15.0D);
            if (!this.hasCustomName()) {
                String NPC_NAME = "aimi";
                this.setCustomName(new TranslationTextComponent(Util.makeTranslationKey("entity.npc_" + NPC_NAME, NORMAL_GUENTER)));
            }
        }

        if (npcTypeId == 11) {
            this.getAttribute(Attributes.ARMOR).setBaseValue(15.0D);
            if (!this.hasCustomName()) {
                String NPC_NAME = "erika";
                this.setCustomName(new TranslationTextComponent(Util.makeTranslationKey("entity.npc_" + NPC_NAME, NORMAL_GUENTER)));
            }
        }

        if (npcTypeId == 12) {
            this.getAttribute(Attributes.ARMOR).setBaseValue(15.0D);
            if (!this.hasCustomName()) {
                String NPC_NAME = "max";
                this.setCustomName(new TranslationTextComponent(Util.makeTranslationKey("entity.npc_" + NPC_NAME, NORMAL_GUENTER)));
            }
        }

        if (npcTypeId == 13) {
            this.getAttribute(Attributes.ARMOR).setBaseValue(15.0D);
            if (!this.hasCustomName()) {
                String NPC_NAME = "mei";
                this.setCustomName(new TranslationTextComponent(Util.makeTranslationKey("entity.npc_" + NPC_NAME, NORMAL_GUENTER)));
            }
        }

        if (npcTypeId == 14) {
            this.getAttribute(Attributes.ARMOR).setBaseValue(15.0D);
            if (!this.hasCustomName()) {
                String NPC_NAME = "strafnur";
                this.setCustomName(new TranslationTextComponent(Util.makeTranslationKey("entity.npc_" + NPC_NAME, NORMAL_GUENTER)));
            }
        }

        if (npcTypeId == 15) {
            this.getAttribute(Attributes.ARMOR).setBaseValue(15.0D);
            if (!this.hasCustomName()) {
                String NPC_NAME = "timmy";
                this.setCustomName(new TranslationTextComponent(Util.makeTranslationKey("entity.npc_" + NPC_NAME, NORMAL_GUENTER)));
            }
        }

        if (npcTypeId == 16) {
            this.getAttribute(Attributes.ARMOR).setBaseValue(15.0D);
            if (!this.hasCustomName()) {
                String NPC_NAME = "titania";
                this.setCustomName(new TranslationTextComponent(Util.makeTranslationKey("entity.npc_" + NPC_NAME, NORMAL_GUENTER)));
            }
        }

        if (npcTypeId == 17) {
            this.getAttribute(Attributes.ARMOR).setBaseValue(15.0D);
            if (!this.hasCustomName()) {
                String NPC_NAME = "xander";
                this.setCustomName(new TranslationTextComponent(Util.makeTranslationKey("entity.npc_" + NPC_NAME, NORMAL_GUENTER)));
            }
        }

        if (npcTypeId == 18) {
            this.getAttribute(Attributes.ARMOR).setBaseValue(15.0D);
            if (!this.hasCustomName()) {
                String NPC_NAME = "mist";
                this.setCustomName(new TranslationTextComponent(Util.makeTranslationKey("entity.npc_" + NPC_NAME, NORMAL_GUENTER)));
            }
        }

        if (npcTypeId == 19) {
            this.getAttribute(Attributes.ARMOR).setBaseValue(15.0D);
            if (!this.hasCustomName()) {
                String NPC_NAME = "soleil";
                this.setCustomName(new TranslationTextComponent(Util.makeTranslationKey("entity.npc_" + NPC_NAME, NORMAL_GUENTER)));
            }
        }



        this.dataManager.set(NPC_TYPE, npcTypeId);
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    @Override
    public boolean hitByEntity(Entity entityIn) {
        return super.hitByEntity(entityIn);
    }

    @Override
    protected ActionResultType getEntityInteractionResult(PlayerEntity playerIn, Hand hand) {

        if (!world.isRemote()) {

            if (this.getNPCEntityType() == 1) {

                if (playerIn.experienceLevel == 2) {
                    playerIn.sendStatusMessage(new StringTextComponent("Hallo"), true);

                } else if (playerIn.experienceLevel == 4) {
                    BlockPos pos = playerIn.getPosition();
                    World worldIn = playerIn.getEntityWorld();
                    PigEntity pig = EntityType.PIG.create(worldIn);

                    pig.setPosition(pos.getX() + this.rand.nextInt(10) - 5, pos.getY(), pos.getZ() + this.rand.nextInt(10) - 5);
                    pig.addTag("ruediger");
                    worldIn.addEntity(pig);

                }

                playerIn.sendMessage(new StringTextComponent("Hallo"), UUID.randomUUID());

            }

            if (this.getNPCEntityType() == 2) {
                String tagname = "message";

                if (playerIn.experienceLevel == 1) {
                    playerIn.addTag(tagname);
                }
                if (playerIn.getTags().add(tagname)) {
                    playerIn.sendMessage(new TranslationTextComponent("lu.message.talk1"), UUID.randomUUID());

                    playerIn.removeTag(tagname);
                }
                if (playerIn.getTags().add(tagname)) {
                    playerIn.sendMessage(new TranslationTextComponent("lu.message.talk2"), UUID.randomUUID());

                    playerIn.removeTag(tagname);
                }

            }


            return ActionResultType.SUCCESS;
        }

        return super.getEntityInteractionResult(playerIn, hand);
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    static class EvilAttackGoal extends MeleeAttackGoal {
        public EvilAttackGoal(NPCEntity rabbit) {
            super(rabbit, 1.4D, true);
        }

        protected double getAttackReachSqr(LivingEntity attackTarget) {
            return (double)(4.0F + attackTarget.getWidth());
        }
    }

    public static class NPCData extends AgeableEntity.AgeableData {
        public final int typeData;

        public NPCData(int type) {
            super(1.0F);
            this.typeData = type;
        }
    }

}
