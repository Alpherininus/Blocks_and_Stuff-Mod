package com.alpherininus.basmod.common.effekts;

import com.google.common.collect.ImmutableMultimap;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.attributes.Attribute;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectType;

public class kEffect extends Effect {
    private ImmutableMultimap<Attribute, AttributeModifier> attributeModifiers;

    public kEffect(EffectType typeIn, int liquidColorIn) {
        super(typeIn, liquidColorIn);
    }

    @Override
    public void performEffect(LivingEntity entityLivingBaseIn, int amplifier) {

        super.performEffect(entityLivingBaseIn, amplifier);
    }

    @Override
    public boolean isReady(int duration, int amplifier) {
        return true;
    }

    @Override
    public Effect addAttributesModifier(Attribute p_220304_1_, String p_220304_2_, double p_220304_3_, AttributeModifier.Operation p_220304_5_) {

        return super.addAttributesModifier(p_220304_1_, p_220304_2_, p_220304_3_, p_220304_5_);
    }

    @Override
    public double getAttributeModifierAmount(int p_111183_1_, AttributeModifier p_111183_2_) {
        return super.getAttributeModifierAmount(p_111183_1_, p_111183_2_);
    }
}
