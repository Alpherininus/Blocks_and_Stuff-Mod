package com.alpherininus.basmod.client.entity;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.mojang.datafixers.util.Pair;
import com.mojang.serialization.DataResult;
import com.mojang.serialization.Dynamic;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.block.*;
import net.minecraft.command.arguments.EntityAnchorArgument;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.enchantment.FrostWalkerEnchantment;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.attributes.*;
import net.minecraft.entity.ai.brain.Brain;
import net.minecraft.entity.boss.WitherEntity;
import net.minecraft.entity.item.ExperienceOrbEntity;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.entity.projectile.AbstractArrowEntity;
import net.minecraft.fluid.Fluid;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.loot.LootContext;
import net.minecraft.loot.LootParameterSets;
import net.minecraft.loot.LootParameters;
import net.minecraft.loot.LootTable;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.INBT;
import net.minecraft.nbt.ListNBT;
import net.minecraft.nbt.NBTDynamicOps;
import net.minecraft.network.IPacket;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.network.play.server.SCollectItemPacket;
import net.minecraft.network.play.server.SEntityEquipmentPacket;
import net.minecraft.network.play.server.SEntityStatusPacket;
import net.minecraft.network.play.server.SSpawnMobPacket;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.potion.PotionUtils;
import net.minecraft.scoreboard.ScorePlayerTeam;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.*;
import net.minecraft.util.math.*;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.GameRules;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.*;

public abstract class BasmodLivingEntity extends Entity {
    private static final LivingEntity livingEntity = null;
    private static final DataParameter<Float> MANA = EntityDataManager.createKey(BasmodLivingEntity.class, DataSerializers.FLOAT);

    private static final UUID SPRINTING_SPEED_BOOST_ID = UUID.fromString("662A6B8D-DA3E-4C1C-8813-96EA6097278D");
    private static final UUID SOUL_SPEED_BOOT_ID = UUID.fromString("87f46a96-686f-4796-b035-22e16ee9e038");
    private static final UUID SLOW_FALLING_ID = UUID.fromString("A5B6CF2A-2F7C-31EF-9022-7C3E7D5E6ABA");
    private static final AttributeModifier SPRINTING_SPEED_BOOST = new AttributeModifier(SPRINTING_SPEED_BOOST_ID, "Sprinting speed boost", (double)0.3F, AttributeModifier.Operation.MULTIPLY_TOTAL);
    private static final AttributeModifier SLOW_FALLING = new AttributeModifier(SLOW_FALLING_ID, "Slow falling acceleration reduction", -0.07, AttributeModifier.Operation.ADDITION); // Add -0.07 to 0.08 so we get the vanilla default of 0.01
    protected static final DataParameter<Byte> LIVING_FLAGS = EntityDataManager.createKey(LivingEntity.class, DataSerializers.BYTE);
    private static final DataParameter<Float> HEALTH = EntityDataManager.createKey(LivingEntity.class, DataSerializers.FLOAT);
    private static final DataParameter<Integer> POTION_EFFECTS = EntityDataManager.createKey(LivingEntity.class, DataSerializers.VARINT);
    private static final DataParameter<Boolean> HIDE_PARTICLES = EntityDataManager.createKey(LivingEntity.class, DataSerializers.BOOLEAN);
    private static final DataParameter<Integer> ARROW_COUNT_IN_ENTITY = EntityDataManager.createKey(LivingEntity.class, DataSerializers.VARINT);
    private static final DataParameter<Integer> BEE_STING_COUNT = EntityDataManager.createKey(LivingEntity.class, DataSerializers.VARINT);
    private static final DataParameter<Optional<BlockPos>> BED_POSITION = EntityDataManager.createKey(LivingEntity.class, DataSerializers.OPTIONAL_BLOCK_POS);
    protected static final EntitySize SLEEPING_SIZE = EntitySize.fixed(0.2F, 0.2F);
    private final AttributeModifierManager attributes;
    private final CombatTracker combatTracker = new CombatTracker(livingEntity);
    private final Map<Effect, EffectInstance> activePotionsMap = Maps.newHashMap();
    private final NonNullList<ItemStack> handInventory = NonNullList.withSize(2, ItemStack.EMPTY);
    private final NonNullList<ItemStack> armorArray = NonNullList.withSize(4, ItemStack.EMPTY);
    public boolean isSwingInProgress;
    public Hand swingingHand;
    public int swingProgressInt;
    public int arrowHitTimer;
    public int beeStingRemovalCooldown;
    public int hurtTime;
    public int maxHurtTime;
    public float attackedAtYaw;
    public int deathTime;
    public float prevSwingProgress;
    public float swingProgress;
    protected int ticksSinceLastSwing;
    public float prevLimbSwingAmount;
    public float limbSwingAmount;
    public float limbSwing;
    public final int maxHurtResistantTime = 20;
    public final float randomUnused2;
    public final float randomUnused1;
    public float renderYawOffset;
    public float prevRenderYawOffset;
    public float rotationYawHead;
    public float prevRotationYawHead;
    public float jumpMovementFactor = 0.02F;
    @Nullable
    protected PlayerEntity attackingPlayer;
    protected int recentlyHit;
    protected boolean dead;
    protected int idleTime;
    protected float prevOnGroundSpeedFactor;
    protected float onGroundSpeedFactor;
    protected float movedDistance;
    protected float prevMovedDistance;
    protected float unused180;
    protected int scoreValue;
    protected float lastDamage;
    protected boolean isJumping;
    public float moveStrafing;
    public float moveVertical;
    public float moveForward;
    protected int newPosRotationIncrements;
    protected double interpTargetX;
    protected double interpTargetY;
    protected double interpTargetZ;
    protected double interpTargetYaw;
    protected double interpTargetPitch;
    protected double interpTargetHeadYaw;
    protected int interpTicksHead;
    private boolean potionsNeedUpdate = true;
    @Nullable
    private LivingEntity revengeTarget;
    private int revengeTimer;
    private LivingEntity lastAttackedEntity;
    private int lastAttackedEntityTime;
    private float landMovementFactor;
    private int jumpTicks;
    private float absorptionAmount;
    protected ItemStack activeItemStack = ItemStack.EMPTY;
    protected int activeItemStackUseCount;
    protected int ticksElytraFlying;
    private BlockPos prevBlockpos;
    private Optional<BlockPos> field_233624_bE_ = Optional.empty();
    private DamageSource lastDamageSource;
    private long lastDamageStamp;
    protected int spinAttackDuration;
    private float swimAnimation;
    private float lastSwimAnimation;
    protected Brain<?> brain;

    protected BasmodLivingEntity(EntityType<? extends LivingEntity> type, World worldIn) {
        super(type, worldIn);
        this.attributes = new AttributeModifierManager(GlobalEntityTypeAttributes.getAttributesForEntity(type));
        this.setHealth(this.getMaxHealth());
        this.preventEntitySpawning = true;
        this.randomUnused1 = (float)((Math.random() + 1.0D) * (double)0.01F);
        this.recenterBoundingBox();
        this.randomUnused2 = (float)Math.random() * 12398.0F;
        this.rotationYaw = (float)(Math.random() * (double)((float)Math.PI * 2F));
        this.rotationYawHead = this.rotationYaw;
        this.stepHeight = 0.6F;
        NBTDynamicOps nbtdynamicops = NBTDynamicOps.INSTANCE;
    }

    public boolean canAttack(EntityType<?> typeIn) {
        return true;
    }

    protected void registerData() {
        this.dataManager.register(LIVING_FLAGS, (byte)0);
        this.dataManager.register(POTION_EFFECTS, 0);
        this.dataManager.register(HIDE_PARTICLES, false);
        this.dataManager.register(ARROW_COUNT_IN_ENTITY, 0);
        this.dataManager.register(BEE_STING_COUNT, 0);
        this.dataManager.register(HEALTH, 1.0F);
        this.dataManager.register(BED_POSITION, Optional.empty());

        this.dataManager.register(MANA, 1.0F);
    }

    public static AttributeModifierMap.MutableAttribute registerAttributes() {
        return AttributeModifierMap.createMutableAttribute().createMutableAttribute(Attributes.MAX_HEALTH).createMutableAttribute(Attributes.KNOCKBACK_RESISTANCE).createMutableAttribute(Attributes.MOVEMENT_SPEED).createMutableAttribute(Attributes.ARMOR).createMutableAttribute(Attributes.ARMOR_TOUGHNESS).createMutableAttribute(net.minecraftforge.common.ForgeMod.SWIM_SPEED.get()).createMutableAttribute(net.minecraftforge.common.ForgeMod.NAMETAG_DISTANCE.get()).createMutableAttribute(net.minecraftforge.common.ForgeMod.ENTITY_GRAVITY.get());
    }

    protected boolean func_230296_cM_() {
        return this.world.getBlockState(this.getPositionUnderneath()).isIn(BlockTags.SOUL_SPEED_BLOCKS);
    }

    protected float getSpeedFactor() {
        return this.func_230296_cM_() && EnchantmentHelper.getMaxEnchantmentLevel(Enchantments.SOUL_SPEED, livingEntity) > 0 ? 1.0F : super.getSpeedFactor();
    }

    protected boolean func_230295_b_(BlockState p_230295_1_) {
        return !p_230295_1_.isAir() || this.isElytraFlying();
    }

    protected void func_233641_cN_() {
        ModifiableAttributeInstance modifiableattributeinstance = this.getAttribute(Attributes.MOVEMENT_SPEED);
        if (modifiableattributeinstance != null) {
            if (modifiableattributeinstance.getModifier(SOUL_SPEED_BOOT_ID) != null) {
                modifiableattributeinstance.removeModifier(SOUL_SPEED_BOOT_ID);
            }

        }
    }

    public boolean isChild() {
        return false;
    }

    public float getRenderScale() {
        return this.isChild() ? 0.5F : 1.0F;
    }

    protected boolean func_241208_cS_() {
        return true;
    }

    public boolean canBeRiddenInWater() {
        return false;
    }

    protected boolean canDropLoot() {
        return !this.isChild();
    }

    protected boolean isAdult() {
        return !this.isChild();
    }

    protected int determineNextAir(int currentAir) {
        return Math.min(currentAir + 4, this.getMaxAir());
    }

    protected int getExperiencePoints(PlayerEntity player) {
        return 0;
    }

    protected boolean isPlayer() {
        return false;
    }

    public Random getRNG() {
        return this.rand;
    }

    @Nullable
    public LivingEntity getRevengeTarget() {
        return this.revengeTarget;
    }

    public int getRevengeTimer() {
        return this.revengeTimer;
    }

    public void setAttackingPlayer(@Nullable PlayerEntity p_230246_1_) {
        this.attackingPlayer = p_230246_1_;
        this.recentlyHit = this.ticksExisted;
    }

    public void setRevengeTarget(@Nullable LivingEntity livingBase) {
        this.revengeTarget = livingBase;
        this.revengeTimer = this.ticksExisted;
    }

    @Nullable
    public LivingEntity getLastAttackedEntity() {
        return this.lastAttackedEntity;
    }

    public int getLastAttackedEntityTime() {
        return this.lastAttackedEntityTime;
    }

    public void setLastAttackedEntity(Entity entityIn) {
        if (entityIn instanceof LivingEntity) {
            this.lastAttackedEntity = (LivingEntity)entityIn;
        } else {
            this.lastAttackedEntity = null;
        }

        this.lastAttackedEntityTime = this.ticksExisted;
    }

    public int getIdleTime() {
        return this.idleTime;
    }

    public void setIdleTime(int idleTimeIn) {
        this.idleTime = idleTimeIn;
    }

    protected void playEquipSound(ItemStack stack) {
        if (!stack.isEmpty()) {
            SoundEvent soundevent = SoundEvents.ITEM_ARMOR_EQUIP_GENERIC;
            Item item = stack.getItem();
            if (item instanceof ArmorItem) {
                soundevent = ((ArmorItem)item).getArmorMaterial().getSoundEvent();
            } else if (item == Items.ELYTRA) {
                soundevent = SoundEvents.ITEM_ARMOR_EQUIP_ELYTRA;
            }

            this.playSound(soundevent, 1.0F, 1.0F);
        }
    }

    public void writeAdditional(CompoundNBT compound) {
        compound.putFloat("Mana", this.getMana());
        compound.putFloat("Health", this.getHealth());
        compound.putShort("HurtTime", (short)this.hurtTime);
        compound.putInt("HurtByTimestamp", this.revengeTimer);
        compound.putShort("DeathTime", (short)this.deathTime);
        compound.putFloat("AbsorptionAmount", this.getAbsorptionAmount());
        compound.put("Attributes", this.getAttributeManager().serialize());
        if (!this.activePotionsMap.isEmpty()) {
            ListNBT listnbt = new ListNBT();

            for(EffectInstance effectinstance : this.activePotionsMap.values()) {
                listnbt.add(effectinstance.write(new CompoundNBT()));
            }

            compound.put("ActiveEffects", listnbt);
        }
    }

    public void readAdditional(CompoundNBT compound) {
        this.setAbsorptionAmount(compound.getFloat("AbsorptionAmount"));
        if (compound.contains("Attributes", 9) && this.world != null && !this.world.isRemote) {
            this.getAttributeManager().deserialize(compound.getList("Attributes", 10));
        }

        if (compound.contains("ActiveEffects", 9)) {
            ListNBT listnbt = compound.getList("ActiveEffects", 10);

            for (int i = 0; i < listnbt.size(); ++i) {
                CompoundNBT compoundnbt = listnbt.getCompound(i);
                EffectInstance effectinstance = EffectInstance.read(compoundnbt);
                if (effectinstance != null) {
                    this.activePotionsMap.put(effectinstance.getPotion(), effectinstance);
                }
            }
        }

        if (compound.contains("Health", 99)) {
            this.setHealth(compound.getFloat("Health"));
        }

        this.hurtTime = compound.getShort("HurtTime");
        this.deathTime = compound.getShort("DeathTime");
        this.revengeTimer = compound.getInt("HurtByTimestamp");
        if (compound.contains("Team", 8)) {
            String s = compound.getString("Team");
            ScorePlayerTeam scoreplayerteam = this.world.getScoreboard().getTeam(s);
            boolean flag = scoreplayerteam != null && this.world.getScoreboard().addPlayerToTeam(this.getCachedUniqueIdString(), scoreplayerteam);
            if (!flag) {
                LOGGER.warn("Unable to add mob to team \"{}\" (that team probably doesn't exist)", (Object) s);
            }
        }

        if (compound.getBoolean("FallFlying")) {
            this.setFlag(7, true);
        }
    }

    protected void updatePotionEffects() {
        Iterator<Effect> iterator = this.activePotionsMap.keySet().iterator();

        try {
            while(iterator.hasNext()) {
                Effect effect = iterator.next();
                EffectInstance effectinstance = this.activePotionsMap.get(effect);
                if (!effectinstance.tick(livingEntity, () -> {
                    this.onChangedPotionEffect(effectinstance, true);
                })) {
                    if (!this.world.isRemote && !net.minecraftforge.common.MinecraftForge.EVENT_BUS.post(new net.minecraftforge.event.entity.living.PotionEvent.PotionExpiryEvent(livingEntity, effectinstance))) {
                        iterator.remove();
                        this.onFinishedPotionEffect(effectinstance);
                    }
                } else if (effectinstance.getDuration() % 600 == 0) {
                    this.onChangedPotionEffect(effectinstance, false);
                }
            }
        } catch (ConcurrentModificationException concurrentmodificationexception) {
        }

        if (this.potionsNeedUpdate) {
            if (!this.world.isRemote) {
                this.updatePotionMetadata();
            }

            this.potionsNeedUpdate = false;
        }

        int i = this.dataManager.get(POTION_EFFECTS);
        boolean flag1 = this.dataManager.get(HIDE_PARTICLES);
        if (i > 0) {
            boolean flag;
            if (this.isInvisible()) {
                flag = this.rand.nextInt(15) == 0;
            } else {
                flag = this.rand.nextBoolean();
            }

            if (flag1) {
                flag &= this.rand.nextInt(5) == 0;
            }

            if (flag && i > 0) {
                double d0 = (double)(i >> 16 & 255) / 255.0D;
                double d1 = (double)(i >> 8 & 255) / 255.0D;
                double d2 = (double)(i >> 0 & 255) / 255.0D;
                this.world.addParticle(flag1 ? ParticleTypes.AMBIENT_ENTITY_EFFECT : ParticleTypes.ENTITY_EFFECT, this.getPosXRandom(0.5D), this.getPosYRandom(), this.getPosZRandom(0.5D), d0, d1, d2);
            }
        }

    }

    protected void updatePotionMetadata() {
        if (this.activePotionsMap.isEmpty()) {
            this.resetPotionEffectMetadata();
            this.setInvisible(false);
        } else {
            Collection<EffectInstance> collection = this.activePotionsMap.values();
            net.minecraftforge.event.entity.living.PotionColorCalculationEvent event = new net.minecraftforge.event.entity.living.PotionColorCalculationEvent(livingEntity, PotionUtils.getPotionColorFromEffectList(collection), areAllPotionsAmbient(collection), collection);
            net.minecraftforge.common.MinecraftForge.EVENT_BUS.post(event);
            this.dataManager.set(HIDE_PARTICLES, event.areParticlesHidden());
            this.dataManager.set(POTION_EFFECTS, event.getColor());
            this.setInvisible(this.isPotionActive(Effects.INVISIBILITY));
        }

    }

    public static boolean areAllPotionsAmbient(Collection<EffectInstance> potionEffects) {
        for(EffectInstance effectinstance : potionEffects) {
            if (!effectinstance.isAmbient()) {
                return false;
            }
        }

        return true;
    }

    protected void resetPotionEffectMetadata() {
        this.dataManager.set(HIDE_PARTICLES, false);
        this.dataManager.set(POTION_EFFECTS, 0);
    }

    public boolean clearActivePotions() {
        if (this.world.isRemote) {
            return false;
        } else {
            Iterator<EffectInstance> iterator = this.activePotionsMap.values().iterator();

            boolean flag;
            for(flag = false; iterator.hasNext(); flag = true) {
                EffectInstance effect = iterator.next();
                if(net.minecraftforge.common.MinecraftForge.EVENT_BUS.post(new net.minecraftforge.event.entity.living.PotionEvent.PotionRemoveEvent(livingEntity, effect))) continue;
                this.onFinishedPotionEffect(effect);
                iterator.remove();
            }

            return flag;
        }
    }

    public Collection<EffectInstance> getActivePotionEffects() {
        return this.activePotionsMap.values();
    }

    public Map<Effect, EffectInstance> getActivePotionMap() {
        return this.activePotionsMap;
    }

    public boolean isPotionActive(Effect potionIn) {
        return this.activePotionsMap.containsKey(potionIn);
    }

    @Nullable
    public EffectInstance getActivePotionEffect(Effect potionIn) {
        return this.activePotionsMap.get(potionIn);
    }

    public boolean addPotionEffect(EffectInstance effectInstanceIn) {
        if (!this.isPotionApplicable(effectInstanceIn)) {
            return false;
        } else {
            EffectInstance effectinstance = this.activePotionsMap.get(effectInstanceIn.getPotion());
            net.minecraftforge.common.MinecraftForge.EVENT_BUS.post(new net.minecraftforge.event.entity.living.PotionEvent.PotionAddedEvent(livingEntity, effectinstance, effectInstanceIn));
            if (effectinstance == null) {
                this.activePotionsMap.put(effectInstanceIn.getPotion(), effectInstanceIn);
                this.onNewPotionEffect(effectInstanceIn);
                return true;
            } else if (effectinstance.combine(effectInstanceIn)) {
                this.onChangedPotionEffect(effectinstance, true);
                return true;
            } else {
                return false;
            }
        }
    }

    public boolean isPotionApplicable(EffectInstance potioneffectIn) {
        net.minecraftforge.event.entity.living.PotionEvent.PotionApplicableEvent event = new net.minecraftforge.event.entity.living.PotionEvent.PotionApplicableEvent(livingEntity, potioneffectIn);
        net.minecraftforge.common.MinecraftForge.EVENT_BUS.post(event);
        if (event.getResult() != net.minecraftforge.eventbus.api.Event.Result.DEFAULT) return event.getResult() == net.minecraftforge.eventbus.api.Event.Result.ALLOW;
        if (this.getCreatureAttribute() == CreatureAttribute.UNDEAD) {
            Effect effect = potioneffectIn.getPotion();
            if (effect == Effects.REGENERATION || effect == Effects.POISON) {
                return false;
            }
        }

        return true;
    }

    @OnlyIn(Dist.CLIENT)
    public void func_233646_e_(EffectInstance p_233646_1_) {
        if (this.isPotionApplicable(p_233646_1_)) {
            EffectInstance effectinstance = this.activePotionsMap.put(p_233646_1_.getPotion(), p_233646_1_);
            if (effectinstance == null) {
                this.onNewPotionEffect(p_233646_1_);
            } else {
                this.onChangedPotionEffect(p_233646_1_, true);
            }

        }
    }

    public boolean isEntityUndead() {
        return this.getCreatureAttribute() == CreatureAttribute.UNDEAD;
    }

    @Nullable
    public EffectInstance removeActivePotionEffect(@Nullable Effect potioneffectin) {
        return this.activePotionsMap.remove(potioneffectin);
    }

    public boolean removePotionEffect(Effect effectIn) {
        if (net.minecraftforge.common.MinecraftForge.EVENT_BUS.post(new net.minecraftforge.event.entity.living.PotionEvent.PotionRemoveEvent(livingEntity, effectIn))) return false;
        EffectInstance effectinstance = this.removeActivePotionEffect(effectIn);
        if (effectinstance != null) {
            this.onFinishedPotionEffect(effectinstance);
            return true;
        } else {
            return false;
        }
    }

    protected void onNewPotionEffect(EffectInstance id) {
        this.potionsNeedUpdate = true;
        if (!this.world.isRemote) {
            id.getPotion().applyAttributesModifiersToEntity(livingEntity, this.getAttributeManager(), id.getAmplifier());
        }

    }

    protected void onChangedPotionEffect(EffectInstance id, boolean reapply) {
        this.potionsNeedUpdate = true;
        if (reapply && !this.world.isRemote) {
            Effect effect = id.getPotion();
            effect.removeAttributesModifiersFromEntity(livingEntity, this.getAttributeManager(), id.getAmplifier());
            effect.applyAttributesModifiersToEntity(livingEntity, this.getAttributeManager(), id.getAmplifier());
        }

    }

    protected void onFinishedPotionEffect(EffectInstance effect) {
        this.potionsNeedUpdate = true;
        if (!this.world.isRemote) {
            effect.getPotion().removeAttributesModifiersFromEntity(livingEntity, this.getAttributeManager(), effect.getAmplifier());
        }

    }

    public void heal(float healAmount) {
        healAmount = net.minecraftforge.event.ForgeEventFactory.onLivingHeal(livingEntity, healAmount);
        if (healAmount <= 0) return;
        float f = this.getHealth();
        if (f > 0.0F) {
            this.setHealth(f + healAmount);
        }

    }

    public float getHealth() {
        return this.dataManager.get(HEALTH);
    }

    public void setHealth(float health) {
        this.dataManager.set(HEALTH, MathHelper.clamp(health, 0.0F, this.getMaxHealth()));
    }

    @Nullable
    public DamageSource getLastDamageSource() {
        if (this.world.getGameTime() - this.lastDamageStamp > 40L) {
            this.lastDamageSource = null;
        }

        return this.lastDamageSource;
    }

    protected void playHurtSound(DamageSource source) {
        SoundEvent soundevent = this.getHurtSound(source);
        if (soundevent != null) {
            this.playSound(soundevent, this.getSoundVolume(), this.getSoundPitch());
        }

    }

    protected void createWitherRose(@Nullable LivingEntity entitySource) {
        if (!this.world.isRemote) {
            boolean flag = false;
            if (entitySource instanceof WitherEntity) {
                if (net.minecraftforge.event.ForgeEventFactory.getMobGriefingEvent(this.world, this)) {
                    BlockPos blockpos = this.getPosition();
                    BlockState blockstate = Blocks.WITHER_ROSE.getDefaultState();
                    if (this.world.isAirBlock(blockpos) && blockstate.isValidPosition(this.world, blockpos)) {
                        this.world.setBlockState(blockpos, blockstate, 3);
                        flag = true;
                    }
                }

                if (!flag) {
                    ItemEntity itementity = new ItemEntity(this.world, this.getPosX(), this.getPosY(), this.getPosZ(), new ItemStack(Items.WITHER_ROSE));
                    this.world.addEntity(itementity);
                }
            }

        }
    }

    protected void dropInventory() {
    }

    protected void dropSpecialItems(DamageSource source, int looting, boolean recentlyHitIn) {
    }

    public ResourceLocation getLootTableResourceLocation() {
        return this.getType().getLootTable();
    }

    protected void dropLoot(DamageSource damageSourceIn, boolean attackedRecently) {
        ResourceLocation resourcelocation = this.getLootTableResourceLocation();
        LootTable loottable = this.world.getServer().getLootTableManager().getLootTableFromLocation(resourcelocation);
        LootContext.Builder lootcontext$builder = this.getLootContextBuilder(attackedRecently, damageSourceIn);
        LootContext ctx = lootcontext$builder.build(LootParameterSets.ENTITY);
        loottable.generate(ctx).forEach(this::entityDropItem);
    }

    protected LootContext.Builder getLootContextBuilder(boolean attackedRecently, DamageSource damageSourceIn) {
        LootContext.Builder lootcontext$builder = (new LootContext.Builder((ServerWorld)this.world)).withRandom(this.rand).withParameter(LootParameters.THIS_ENTITY, this).withParameter(LootParameters.ORIGIN, this.getPositionVec()).withParameter(LootParameters.DAMAGE_SOURCE, damageSourceIn).withNullableParameter(LootParameters.KILLER_ENTITY, damageSourceIn.getTrueSource()).withNullableParameter(LootParameters.DIRECT_KILLER_ENTITY, damageSourceIn.getImmediateSource());
        if (attackedRecently && this.attackingPlayer != null) {
            lootcontext$builder = lootcontext$builder.withParameter(LootParameters.LAST_DAMAGE_PLAYER, this.attackingPlayer).withLuck(this.attackingPlayer.getLuck());
        }

        return lootcontext$builder;
    }

    @Nullable
    protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
        return SoundEvents.ENTITY_GENERIC_HURT;
    }

    @Nullable
    protected SoundEvent getDeathSound() {
        return SoundEvents.ENTITY_GENERIC_DEATH;
    }

    protected SoundEvent getFallSound(int heightIn) {
        return heightIn > 4 ? SoundEvents.ENTITY_GENERIC_BIG_FALL : SoundEvents.ENTITY_GENERIC_SMALL_FALL;
    }

    protected SoundEvent getDrinkSound(ItemStack stack) {
        return stack.getDrinkSound();
    }

    public SoundEvent getEatSound(ItemStack itemStackIn) {
        return itemStackIn.getEatSound();
    }

    public void setOnGround(boolean grounded) {
        super.setOnGround(grounded);
        if (grounded) {
            this.field_233624_bE_ = Optional.empty();
        }

    }

    public Optional<BlockPos> func_233644_dn_() {
        return this.field_233624_bE_;
    }

    public BlockState getBlockState() {
        return this.world.getBlockState(this.getPosition());
    }

    private boolean canGoThroughtTrapDoorOnLadder(BlockPos pos, BlockState state) {
        if (state.get(TrapDoorBlock.OPEN)) {
            BlockState blockstate = this.world.getBlockState(pos.down());
            if (blockstate.matchesBlock(Blocks.LADDER) && blockstate.get(LadderBlock.FACING) == state.get(TrapDoorBlock.HORIZONTAL_FACING)) {
                return true;
            }
        }

        return false;
    }

    public boolean isAlive() {
        return !this.removed && this.getHealth() > 0.0F;
    }

    protected int calculateFallDamage(float distance, float damageMultiplier) {
        EffectInstance effectinstance = this.getActivePotionEffect(Effects.JUMP_BOOST);
        float f = effectinstance == null ? 0.0F : (float)(effectinstance.getAmplifier() + 1);
        return MathHelper.ceil((distance - 3.0F - f) * damageMultiplier);
    }

    protected void playFallSound() {
        if (!this.isSilent()) {
            int i = MathHelper.floor(this.getPosX());
            int j = MathHelper.floor(this.getPosY() - (double)0.2F);
            int k = MathHelper.floor(this.getPosZ());
            BlockPos pos = new BlockPos(i, j, k);
            BlockState blockstate = this.world.getBlockState(pos);
            if (!blockstate.isAir(this.world, pos)) {
                SoundType soundtype = blockstate.getSoundType(world, pos, this);
                this.playSound(soundtype.getFallSound(), soundtype.getVolume() * 0.5F, soundtype.getPitch() * 0.75F);
            }

        }
    }

    @OnlyIn(Dist.CLIENT)
    public void performHurtAnimation() {
        this.maxHurtTime = 10;
        this.hurtTime = this.maxHurtTime;
        this.attackedAtYaw = 0.0F;
    }

    public CombatTracker getCombatTracker() {
        return this.combatTracker;
    }

    @Nullable
    public LivingEntity getAttackingEntity() {
        if (this.combatTracker.getBestAttacker() != null) {
            return this.combatTracker.getBestAttacker();
        } else if (this.attackingPlayer != null) {
            return this.attackingPlayer;
        } else {
            return this.revengeTarget != null ? this.revengeTarget : null;
        }
    }

    public final float getMaxHealth() {
        return (float)this.getAttributeValue(Attributes.MAX_HEALTH);
    }

    public final int getArrowCountInEntity() {
        return this.dataManager.get(ARROW_COUNT_IN_ENTITY);
    }

    public final void setArrowCountInEntity(int count) {
        this.dataManager.set(ARROW_COUNT_IN_ENTITY, count);
    }

    public final int getBeeStingCount() {
        return this.dataManager.get(BEE_STING_COUNT);
    }

    public final void setBeeStingCount(int p_226300_1_) {
        this.dataManager.set(BEE_STING_COUNT, p_226300_1_);
    }


    protected void outOfWorld() {
        this.attackEntityFrom(DamageSource.OUT_OF_WORLD, 4.0F);
    }


    @Nullable
    public ModifiableAttributeInstance getAttribute(Attribute attribute) {
        return this.getAttributeManager().createInstanceIfAbsent(attribute);
    }

    public double getAttributeValue(Attribute attribute) {
        return this.getAttributeManager().getAttributeValue(attribute);
    }

    public double getBaseAttributeValue(Attribute attribute) {
        return this.getAttributeManager().getAttributeBaseValue(attribute);
    }

    public AttributeModifierManager getAttributeManager() {
        return this.attributes;
    }

    public CreatureAttribute getCreatureAttribute() {
        return CreatureAttribute.UNDEFINED;
    }


    protected float getSoundVolume() {
        return 1.0F;
    }

    protected float getSoundPitch() {
        return this.isChild() ? (this.rand.nextFloat() - this.rand.nextFloat()) * 0.2F + 1.5F : (this.rand.nextFloat() - this.rand.nextFloat()) * 0.2F + 1.0F;
    }

    @OnlyIn(Dist.CLIENT)
    public boolean getAlwaysRenderNameTagForRender() {
        return this.isCustomNameVisible();
    }

    public boolean func_230285_a_(Fluid p_230285_1_) {
        return false;
    }

    public void func_233629_a_(LivingEntity p_233629_1_, boolean p_233629_2_) {
        p_233629_1_.prevLimbSwingAmount = p_233629_1_.limbSwingAmount;
        double d0 = p_233629_1_.getPosX() - p_233629_1_.prevPosX;
        double d1 = p_233629_2_ ? p_233629_1_.getPosY() - p_233629_1_.prevPosY : 0.0D;
        double d2 = p_233629_1_.getPosZ() - p_233629_1_.prevPosZ;
        float f = MathHelper.sqrt(d0 * d0 + d1 * d1 + d2 * d2) * 4.0F;
        if (f > 1.0F) {
            f = 1.0F;
        }

        p_233629_1_.limbSwingAmount += (f - p_233629_1_.limbSwingAmount) * 0.4F;
        p_233629_1_.limbSwing += p_233629_1_.limbSwingAmount;
    }

    public Vector3d func_233626_a_(double p_233626_1_, boolean p_233626_3_, Vector3d p_233626_4_) {
        if (!this.hasNoGravity() && !this.isSprinting()) {
            double d0;
            if (p_233626_3_ && Math.abs(p_233626_4_.y - 0.005D) >= 0.003D && Math.abs(p_233626_4_.y - p_233626_1_ / 16.0D) < 0.003D) {
                d0 = -0.003D;
            } else {
                d0 = p_233626_4_.y - p_233626_1_ / 16.0D;
            }

            return new Vector3d(p_233626_4_.x, d0, p_233626_4_.z);
        } else {
            return p_233626_4_;
        }
    }

    private float getRelevantMoveFactor(float p_213335_1_) {
        return this.onGround ? this.getAIMoveSpeed() * (0.21600002F / (p_213335_1_ * p_213335_1_ * p_213335_1_)) : this.jumpMovementFactor;
    }

    public float getAIMoveSpeed() {
        return this.landMovementFactor;
    }

    public void setAIMoveSpeed(float speedIn) {
        this.landMovementFactor = speedIn;
    }

    public boolean attackEntityAsMob(Entity entityIn) {
        this.setLastAttackedEntity(entityIn);
        return false;
    }

    private void func_241342_a_(Map<EquipmentSlotType, ItemStack> p_241342_1_) {
        ItemStack itemstack = p_241342_1_.get(EquipmentSlotType.MAINHAND);
        ItemStack itemstack1 = p_241342_1_.get(EquipmentSlotType.OFFHAND);
        if (itemstack != null && itemstack1 != null && ItemStack.areItemStacksEqual(itemstack, this.getItemInHand(EquipmentSlotType.OFFHAND)) && ItemStack.areItemStacksEqual(itemstack1, this.getItemInHand(EquipmentSlotType.MAINHAND))) {
            ((ServerWorld)this.world).getChunkProvider().sendToAllTracking(this, new SEntityStatusPacket(this, (byte)55));
            p_241342_1_.remove(EquipmentSlotType.MAINHAND);
            p_241342_1_.remove(EquipmentSlotType.OFFHAND);
            this.setItemInHand(EquipmentSlotType.MAINHAND, itemstack.copy());
            this.setItemInHand(EquipmentSlotType.OFFHAND, itemstack1.copy());
        }

    }

    private void func_241344_b_(Map<EquipmentSlotType, ItemStack> p_241344_1_) {
        List<Pair<EquipmentSlotType, ItemStack>> list = Lists.newArrayListWithCapacity(p_241344_1_.size());
        p_241344_1_.forEach((p_241341_2_, p_241341_3_) -> {
            ItemStack itemstack = p_241341_3_.copy();
            list.add(Pair.of(p_241341_2_, itemstack));
            switch(p_241341_2_.getSlotType()) {
                case HAND:
                    this.setItemInHand(p_241341_2_, itemstack);
                    break;
                case ARMOR:
                    this.setArmorInSlot(p_241341_2_, itemstack);
            }

        });
        ((ServerWorld)this.world).getChunkProvider().sendToAllTracking(this, new SEntityEquipmentPacket(this.getEntityId(), list));
    }

    private ItemStack getArmorInSlot(EquipmentSlotType slot) {
        return this.armorArray.get(slot.getIndex());
    }

    private void setArmorInSlot(EquipmentSlotType slot, ItemStack stack) {
        this.armorArray.set(slot.getIndex(), stack);
    }

    private ItemStack getItemInHand(EquipmentSlotType slot) {
        return this.handInventory.get(slot.getIndex());
    }

    private void setItemInHand(EquipmentSlotType slot, ItemStack stack) {
        this.handInventory.set(slot.getIndex(), stack);
    }

    protected float updateDistance(float p_110146_1_, float p_110146_2_) {
        float f = MathHelper.wrapDegrees(p_110146_1_ - this.renderYawOffset);
        this.renderYawOffset += f * 0.3F;
        float f1 = MathHelper.wrapDegrees(this.rotationYaw - this.renderYawOffset);
        boolean flag = f1 < -90.0F || f1 >= 90.0F;
        if (f1 < -75.0F) {
            f1 = -75.0F;
        }

        if (f1 >= 75.0F) {
            f1 = 75.0F;
        }

        this.renderYawOffset = this.rotationYaw - f1;
        if (f1 * f1 > 2500.0F) {
            this.renderYawOffset += f1 * 0.2F;
        }

        if (flag) {
            p_110146_2_ *= -1.0F;
        }

        return p_110146_2_;
    }

    protected void updateEntityActionState() {
    }

    protected void collideWithNearbyEntities() {
        List<Entity> list = this.world.getEntitiesInAABBexcluding(this, this.getBoundingBox(), EntityPredicates.pushableBy(this));
        if (!list.isEmpty()) {
            int i = this.world.getGameRules().getInt(GameRules.MAX_ENTITY_CRAMMING);
            if (i > 0 && list.size() > i - 1 && this.rand.nextInt(4) == 0) {
                int j = 0;

                for(int k = 0; k < list.size(); ++k) {
                    if (!list.get(k).isPassenger()) {
                        ++j;
                    }
                }

                if (j > i - 1) {
                    this.attackEntityFrom(DamageSource.CRAMMING, 6.0F);
                }
            }

            for(int l = 0; l < list.size(); ++l) {
                Entity entity = list.get(l);
                this.collideWithEntity(entity);
            }
        }

    }

    protected void updateSpinAttack(AxisAlignedBB p_204801_1_, AxisAlignedBB p_204801_2_) {
        AxisAlignedBB axisalignedbb = p_204801_1_.union(p_204801_2_);
        List<Entity> list = this.world.getEntitiesWithinAABBExcludingEntity(this, axisalignedbb);
        if (!list.isEmpty()) {
            for(int i = 0; i < list.size(); ++i) {
                Entity entity = list.get(i);
                if (entity instanceof LivingEntity) {
                    this.spinAttack((LivingEntity)entity);
                    this.spinAttackDuration = 0;
                    this.setMotion(this.getMotion().scale(-0.2D));
                    break;
                }
            }
        } else if (this.collidedHorizontally) {
            this.spinAttackDuration = 0;
        }

        if (!this.world.isRemote && this.spinAttackDuration <= 0) {
            this.setLivingFlag(4, false);
        }

    }

    protected void collideWithEntity(Entity entityIn) {
        entityIn.applyEntityCollision(this);
    }

    protected void spinAttack(LivingEntity p_204804_1_) {
    }

    public void startSpinAttack(int p_204803_1_) {
        this.spinAttackDuration = p_204803_1_;
        if (!this.world.isRemote) {
            this.setLivingFlag(4, true);
        }

    }

    public boolean isSpinAttacking() {
        return (this.dataManager.get(LIVING_FLAGS) & 4) != 0;
    }

    @OnlyIn(Dist.CLIENT)
    public void setPositionAndRotationDirect(double x, double y, double z, float yaw, float pitch, int posRotationIncrements, boolean teleport) {
        this.interpTargetX = x;
        this.interpTargetY = y;
        this.interpTargetZ = z;
        this.interpTargetYaw = (double)yaw;
        this.interpTargetPitch = (double)pitch;
        this.newPosRotationIncrements = posRotationIncrements;
    }

    @OnlyIn(Dist.CLIENT)
    public void setHeadRotation(float yaw, int pitch) {
        this.interpTargetHeadYaw = (double)yaw;
        this.interpTicksHead = pitch;
    }

    public void triggerItemPickupTrigger(ItemEntity item) {
        PlayerEntity playerentity = item.getThrowerId() != null ? this.world.getPlayerByUuid(item.getThrowerId()) : null;
        if (playerentity instanceof ServerPlayerEntity) {
            CriteriaTriggers.THROWN_ITEM_PICKED_UP_BY_ENTITY.test((ServerPlayerEntity)playerentity, item.getItem(), this);
        }

    }

    public void onItemPickup(Entity entityIn, int quantity) {
        if (!entityIn.removed && !this.world.isRemote && (entityIn instanceof ItemEntity || entityIn instanceof AbstractArrowEntity || entityIn instanceof ExperienceOrbEntity)) {
            ((ServerWorld)this.world).getChunkProvider().sendToAllTracking(entityIn, new SCollectItemPacket(entityIn.getEntityId(), this.getEntityId(), quantity));
        }

    }

    public boolean canEntityBeSeen(Entity entityIn) {
        Vector3d vector3d = new Vector3d(this.getPosX(), this.getPosYEye(), this.getPosZ());
        Vector3d vector3d1 = new Vector3d(entityIn.getPosX(), entityIn.getPosYEye(), entityIn.getPosZ());
        if (entityIn.world != this.world || vector3d1.squareDistanceTo(vector3d) > 128.0D * 128.0D) return false; //Forge Backport MC-209819
        return this.world.rayTraceBlocks(new RayTraceContext(vector3d, vector3d1, RayTraceContext.BlockMode.COLLIDER, RayTraceContext.FluidMode.NONE, this)).getType() == RayTraceResult.Type.MISS;
    }

    public float getYaw(float partialTicks) {
        return partialTicks == 1.0F ? this.rotationYawHead : MathHelper.lerp(partialTicks, this.prevRotationYawHead, this.rotationYawHead);
    }

    public boolean isServerWorld() {
        return !this.world.isRemote;
    }

    public boolean canBeCollidedWith() {
        return !this.removed;
    }

    public boolean canBePushed() {
        return this.isAlive() && !this.isSpectator();
    }

    protected void markVelocityChanged() {
        this.velocityChanged = this.rand.nextDouble() >= this.getAttributeValue(Attributes.KNOCKBACK_RESISTANCE);
    }

    public float getRotationYawHead() {
        return this.rotationYawHead;
    }

    public void setRotationYawHead(float rotation) {
        this.rotationYawHead = rotation;
    }

    public void setRenderYawOffset(float offset) {
        this.renderYawOffset = offset;
    }

    protected Vector3d func_241839_a(Direction.Axis axis, TeleportationRepositioner.Result result) {
        return func_242288_h(super.func_241839_a(axis, result));
    }

    public static Vector3d func_242288_h(Vector3d p_242288_0_) {
        return new Vector3d(p_242288_0_.x, p_242288_0_.y, 0.0D);
    }

    public float getAbsorptionAmount() {
        return this.absorptionAmount;
    }

    public void setAbsorptionAmount(float amount) {
        if (amount < 0.0F) {
            amount = 0.0F;
        }

        this.absorptionAmount = amount;
    }

    public void sendEnterCombat() {
    }

    public void sendEndCombat() {
    }

    private void updateSwimAnimation() {
        this.lastSwimAnimation = this.swimAnimation;
        if (this.isActualySwimming()) {
            this.swimAnimation = Math.min(1.0F, this.swimAnimation + 0.09F);
        } else {
            this.swimAnimation = Math.max(0.0F, this.swimAnimation - 0.09F);
        }

    }

    protected void setLivingFlag(int key, boolean value) {
        int i = this.dataManager.get(LIVING_FLAGS);
        if (value) {
            i = i | key;
        } else {
            i = i & ~key;
        }

        this.dataManager.set(LIVING_FLAGS, (byte)i);
    }

    public void lookAt(EntityAnchorArgument.Type anchor, Vector3d target) {
        super.lookAt(anchor, target);
        this.prevRotationYawHead = this.rotationYawHead;
        this.renderYawOffset = this.rotationYawHead;
        this.prevRenderYawOffset = this.renderYawOffset;
    }

    public boolean hasStoppedClimbing() {
        return this.isSneaking();
    }

    public boolean isElytraFlying() {
        return this.getFlag(7);
    }

    public boolean isActualySwimming() {
        return super.isActualySwimming() || !this.isElytraFlying() && this.getPose() == Pose.FALL_FLYING;
    }

    @OnlyIn(Dist.CLIENT)
    public int getTicksElytraFlying() {
        return this.ticksElytraFlying;
    }

    public boolean canBeHitWithPotion() {
        return true;
    }

    public boolean attackable() {
        return true;
    }

    protected final float getEyeHeight(Pose poseIn, EntitySize sizeIn) {
        return poseIn == Pose.SLEEPING ? 0.2F : this.getStandingEyeHeight(poseIn, sizeIn);
    }

    protected float getStandingEyeHeight(Pose poseIn, EntitySize sizeIn) {
        return super.getEyeHeight(poseIn, sizeIn);
    }

    public ItemStack findAmmo(ItemStack shootable) {
        return ItemStack.EMPTY;
    }

    private void applyFoodEffects(ItemStack p_213349_1_, World p_213349_2_, LivingEntity p_213349_3_) {
        Item item = p_213349_1_.getItem();
        if (item.isFood()) {
            for(Pair<EffectInstance, Float> pair : item.getFood().getEffects()) {
                if (!p_213349_2_.isRemote && pair.getFirst() != null && p_213349_2_.rand.nextFloat() < pair.getSecond()) {
                    p_213349_3_.addPotionEffect(new EffectInstance(pair.getFirst()));
                }
            }
        }

    }

    private static byte equipmentSlotToEntityState(EquipmentSlotType p_213350_0_) {
        switch(p_213350_0_) {
            case MAINHAND:
                return 47;
            case OFFHAND:
                return 48;
            case HEAD:
                return 49;
            case CHEST:
                return 50;
            case FEET:
                return 52;
            case LEGS:
                return 51;
            default:
                return 47;
        }
    }

    public void sendBreakAnimation(EquipmentSlotType p_213361_1_) {
        this.world.setEntityState(this, equipmentSlotToEntityState(p_213361_1_));
    }

    public void sendBreakAnimation(Hand p_213334_1_) {
        this.sendBreakAnimation(p_213334_1_ == Hand.MAIN_HAND ? EquipmentSlotType.MAINHAND : EquipmentSlotType.OFFHAND);
    }

    public boolean curePotionEffects(ItemStack curativeItem) {
        if (this.world.isRemote)
            return false;
        boolean ret = false;
        Iterator<EffectInstance> itr = this.activePotionsMap.values().iterator();
        while (itr.hasNext()) {
            EffectInstance effect = itr.next();
            if (effect.isCurativeItem(curativeItem) && !net.minecraftforge.common.MinecraftForge.EVENT_BUS.post(new net.minecraftforge.event.entity.living.PotionEvent.PotionRemoveEvent(livingEntity, effect))) {
                this.onFinishedPotionEffect(effect);
                itr.remove();
                ret = true;
                this.potionsNeedUpdate = true;
            }
        }
        return ret;
    }

    private final net.minecraftforge.common.util.LazyOptional<?>[] handlers = net.minecraftforge.items.wrapper.EntityEquipmentInvWrapper.create(livingEntity);

    @Override
    public <T> net.minecraftforge.common.util.LazyOptional<T> getCapability(net.minecraftforge.common.capabilities.Capability<T> capability, @Nullable Direction facing) {
        if (this.isAlive() && capability == net.minecraftforge.items.CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
            if (facing == null) return handlers[2].cast();
            else if (facing.getAxis().isVertical()) return handlers[0].cast();
            else if (facing.getAxis().isHorizontal()) return handlers[1].cast();
        }
        return super.getCapability(capability, facing);
    }

    @Override
    protected void invalidateCaps() {
        super.invalidateCaps();
        for (int x = 0; x < handlers.length; x++)
            handlers[x].invalidate();
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // Basmod

    public void mana(float manaAmount) {
        LivingEntity living = null;

        manaAmount = net.minecraftforge.event.ForgeEventFactory.onLivingHeal(living, manaAmount);
        if (manaAmount <= 0) return;
        float f = this.getMana();
        if (f > 0.0F) {
            this.setMana(f + manaAmount);
        }
    }

    public float getMana() {
        return this.dataManager.get(MANA);
    }

    public int setMana(float mana) {
        this.dataManager.set(MANA, MathHelper.clamp(mana, 0.0F, this.getMaxMana()));
        return 0;
    }

    public final float getMaxMana() {
        return 88.0F;
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    @Nonnull
    @Override
    public IPacket<?> createSpawnPacket() {
        LivingEntity entityIn = null;
        assert false;
        return new SSpawnMobPacket(entityIn);
    }

}
