package com.alpherininus.basmod.client.entity;

import com.alpherininus.basmod.client.events.BasmodGameEvents;
import com.google.common.collect.Maps;
import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.MainWindow;
import net.minecraft.entity.CreatureAttribute;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.attributes.*;
import net.minecraft.item.ItemStack;
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
import net.minecraft.util.Direction;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.RenderGameOverlayEvent;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Collection;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.Map;

public abstract class BasmodLivingEntity extends LivingEntity {
    private static final LivingEntity livingEntity = null;
    private static final DataParameter<Integer> MANA = EntityDataManager.createKey(BasmodLivingEntity.class, DataSerializers.VARINT);

    private static final DataParameter<Float> HEALTH = EntityDataManager.createKey(LivingEntity.class, DataSerializers.FLOAT);
    private static final DataParameter<Integer> POTION_EFFECTS = EntityDataManager.createKey(LivingEntity.class, DataSerializers.VARINT);
    private static final DataParameter<Boolean> HIDE_PARTICLES = EntityDataManager.createKey(LivingEntity.class, DataSerializers.BOOLEAN);

    private final AttributeModifierManager attributes;
    private final Map<Effect, EffectInstance> activePotionsMap = Maps.newHashMap();
    private boolean potionsNeedUpdate;

    protected BasmodLivingEntity(EntityType<? extends LivingEntity> type, World worldIn) {
        super(type, worldIn);
        this.attributes = new AttributeModifierManager(GlobalEntityTypeAttributes.getAttributesForEntity(type));
        this.setMana(this.getMaxMana());
    }

    protected void registerData() {
        this.dataManager.register(MANA, 1);
        this.dataManager.register(HEALTH, 1.0F);
    }

    public static AttributeModifierMap.MutableAttribute registerAttributes() {
        return AttributeModifierMap.createMutableAttribute().createMutableAttribute(Attributes.MAX_HEALTH).createMutableAttribute(Attributes.KNOCKBACK_RESISTANCE).createMutableAttribute(Attributes.MOVEMENT_SPEED).createMutableAttribute(Attributes.ARMOR).createMutableAttribute(Attributes.ARMOR_TOUGHNESS).createMutableAttribute(net.minecraftforge.common.ForgeMod.SWIM_SPEED.get()).createMutableAttribute(net.minecraftforge.common.ForgeMod.NAMETAG_DISTANCE.get()).createMutableAttribute(net.minecraftforge.common.ForgeMod.ENTITY_GRAVITY.get());
    }

    public void writeAdditional(CompoundNBT compound) {
        compound.putInt("Mana", this.getMana());
        compound.putFloat("Health", this.getHealth());
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

        if (compound.contains("Mana", 99)) {
            this.setMana(compound.getInt("Mana"));
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

    public void mana(int manaAmount) {
        int f = this.getMana();
        if (f > 0.0F) {
            this.setMana(f + manaAmount);
        }
    }

    public int getMana() {
        return this.dataManager.get(MANA);
    }

    public int setMana(int mana) {
        this.dataManager.set(MANA, MathHelper.clamp(mana, 0, this.getMaxMana()));
        return 0;
    }

    public final int getMaxMana() {
        return 88;
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
