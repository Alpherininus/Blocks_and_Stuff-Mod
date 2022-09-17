package com.alpherininus.basmod.client.entity;

import com.google.common.collect.Maps;
import net.minecraft.block.BlockState;
import net.minecraft.block.SoundType;
import net.minecraft.entity.CreatureAttribute;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.attributes.*;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.ListNBT;
import net.minecraft.network.IPacket;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.network.play.server.SSpawnMobPacket;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.potion.PotionUtils;
import net.minecraft.scoreboard.ScorePlayerTeam;
import net.minecraft.util.DamageSource;
import net.minecraft.util.Direction;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.*;

public abstract class BasmodLivingEntity extends Entity {
    private static final LivingEntity livingEntity = null;
    private static final DataParameter<Float> MANA = EntityDataManager.createKey(BasmodLivingEntity.class, DataSerializers.FLOAT);

    private static final UUID SOUL_SPEED_BOOT_ID = UUID.fromString("87f46a96-686f-4796-b035-22e16ee9e038");
    protected static final DataParameter<Byte> LIVING_FLAGS = EntityDataManager.createKey(LivingEntity.class, DataSerializers.BYTE);
    private static final DataParameter<Float> HEALTH = EntityDataManager.createKey(LivingEntity.class, DataSerializers.FLOAT);
    private static final DataParameter<Integer> POTION_EFFECTS = EntityDataManager.createKey(LivingEntity.class, DataSerializers.VARINT);
    private static final DataParameter<Boolean> HIDE_PARTICLES = EntityDataManager.createKey(LivingEntity.class, DataSerializers.BOOLEAN);
    private static final DataParameter<Integer> ARROW_COUNT_IN_ENTITY = EntityDataManager.createKey(LivingEntity.class, DataSerializers.VARINT);
    private static final DataParameter<Integer> BEE_STING_COUNT = EntityDataManager.createKey(LivingEntity.class, DataSerializers.VARINT);
    private static final DataParameter<Optional<BlockPos>> BED_POSITION = EntityDataManager.createKey(LivingEntity.class, DataSerializers.OPTIONAL_BLOCK_POS);
    private final AttributeModifierManager attributes;
    private final Map<Effect, EffectInstance> activePotionsMap = Maps.newHashMap();
    public int hurtTime;
    public int deathTime;
    public final float randomUnused2;
    public final float randomUnused1;
    public float rotationYawHead;
    protected int idleTime;
    private boolean potionsNeedUpdate = true;
    private int revengeTimer;

    protected BasmodLivingEntity(EntityType<? extends LivingEntity> type, World worldIn) {
        super(type, worldIn);
        this.attributes = new AttributeModifierManager(GlobalEntityTypeAttributes.getAttributesForEntity(type));
        this.setHealth(this.getMaxHealth());
        this.setMana(this.getMaxMana());
        this.preventEntitySpawning = true;
        this.randomUnused1 = (float)((Math.random() + 1.0D) * (double)0.01F);
        this.recenterBoundingBox();
        this.randomUnused2 = (float)Math.random() * 12398.0F;
        this.rotationYaw = (float)(Math.random() * (double)((float)Math.PI * 2F));
        this.rotationYawHead = this.rotationYaw;
        this.stepHeight = 0.6F;
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

    protected void func_233641_cN_() {
        ModifiableAttributeInstance modifiableattributeinstance = this.getAttribute(Attributes.MOVEMENT_SPEED);
        if (modifiableattributeinstance != null) {
            if (modifiableattributeinstance.getModifier(SOUL_SPEED_BOOT_ID) != null) {
                modifiableattributeinstance.removeModifier(SOUL_SPEED_BOOT_ID);
            }

        }
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

    public final float getMaxHealth() {
        return (float)this.getAttributeValue(Attributes.MAX_HEALTH);
    }

    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // TODO Wichtig

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

    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////

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

    public void setMana(float mana) {
        this.dataManager.set(MANA, MathHelper.clamp(mana, 0.0F, this.getMaxMana()));
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
