package com.alpherininus.basmod.common.effekts;

import com.alpherininus.basmod.client.entity.BasmodPlayerEntity;
import com.alpherininus.basmod.core.init.EffectInit;
import net.minecraft.entity.LivingEntity;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectType;

public class ManaEffect extends Effect {


    public ManaEffect(EffectType typeIn, int liquidColorIn) {
        super(typeIn, liquidColorIn);
    }

    @Override
    public void performEffect(LivingEntity entityLivBaseIn, int amplifier) {

        BasmodPlayerEntity entityLivingBaseIn = (BasmodPlayerEntity) entityLivBaseIn.getEntity();

        if (this == EffectInit.MANA.get()) {
            assert false;
            if (entityLivingBaseIn.getMana() < entityLivingBaseIn.getMaxMana()) {
                entityLivingBaseIn.mana(amplifier);
            }
        }
    }

    @Override
    public boolean isReady(int duration, int amplifier) {
        return true;
    }

}
