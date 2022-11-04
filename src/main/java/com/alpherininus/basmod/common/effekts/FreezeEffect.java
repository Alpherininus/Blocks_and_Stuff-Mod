package com.alpherininus.basmod.common.effekts;

import net.minecraft.entity.LivingEntity;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectType;

public class FreezeEffect extends Effect {
    public FreezeEffect(EffectType typeIn, int liquidColorIn) {
        super(typeIn, liquidColorIn);
    }

    @Override
    public void performEffect(LivingEntity entityLivingBaseIn, int amplifier) {
        if (!entityLivingBaseIn.world.isRemote()) {
            double x = entityLivingBaseIn.getPosX();
            double y = entityLivingBaseIn.getPosY();
            double z = entityLivingBaseIn.getPosZ();

            entityLivingBaseIn.teleportKeepLoaded(x, y, z);
            entityLivingBaseIn.setMotion(0, 0, 0);
        }
        super.performEffect(entityLivingBaseIn, amplifier);
    }

    @Override
    public boolean isReady(int duration, int amplifier) {
        return true;
    }

}
