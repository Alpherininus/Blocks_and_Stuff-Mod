package com.alpherininus.basmod.client.entity;

import com.alpherininus.basmod.core.init.AttributesInit;
import com.google.common.collect.Maps;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.attributes.*;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.ListNBT;
import net.minecraft.network.IPacket;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.network.play.server.SSpawnMobPacket;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectInstance;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Map;

public abstract class BasmodLivingEntity extends Entity {
    private static final DataParameter<Float> MANA = EntityDataManager.createKey(BasmodLivingEntity.class, DataSerializers.FLOAT);
    private final Map<Effect, EffectInstance> activePotionsMap = Maps.newHashMap();
    private final AttributeModifierManager attributes;


    protected BasmodLivingEntity(EntityType<? extends LivingEntity> entityTypeIn, World worldIn) {
        super(entityTypeIn, worldIn);
        this.attributes = new AttributeModifierManager(GlobalEntityTypeAttributes.getAttributesForEntity(entityTypeIn));
        this.setMana(this.getMaxMana());

    }

    @Override
    protected void registerData() {
        this.dataManager.register(MANA, 1.0F);

    }

    public static void registerBasmodLivingEntityAttributes() {
        AttributeModifierMap.createMutableAttribute()
                .createMutableAttribute(Attributes.MAX_HEALTH)
                .createMutableAttribute(Attributes.KNOCKBACK_RESISTANCE)
                .createMutableAttribute(Attributes.MOVEMENT_SPEED)
                .createMutableAttribute(Attributes.ARMOR)
                .createMutableAttribute(Attributes.ARMOR_TOUGHNESS)
                .createMutableAttribute(net.minecraftforge.common.ForgeMod.SWIM_SPEED.get())
                .createMutableAttribute(net.minecraftforge.common.ForgeMod.NAMETAG_DISTANCE.get())
                .createMutableAttribute(net.minecraftforge.common.ForgeMod.ENTITY_GRAVITY.get())
                .createMutableAttribute(AttributesInit.MAX_MANA);
    }

    @Override
    protected void readAdditional(CompoundNBT compound) {

    }

    @Override
    protected void writeAdditional(CompoundNBT compound) {
        compound.putFloat("Mana", this.getMana());

        if (!this.activePotionsMap.isEmpty()) {
            ListNBT listnbt = new ListNBT();

            for(EffectInstance effectinstance : this.activePotionsMap.values()) {
                listnbt.add(effectinstance.write(new CompoundNBT()));
            }

            compound.put("ActiveEffects", listnbt);
        }

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
        return (float)this.getAttributeValue(AttributesInit.MAX_MANA);
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////

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

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    @Nonnull
    @Override
    public IPacket<?> createSpawnPacket() {
        LivingEntity entityIn = null;
        assert false;
        return new SSpawnMobPacket(entityIn);
    }

}
