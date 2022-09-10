package com.alpherininus.basmod.common.effekts;

import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.gui.AbstractGui;
import net.minecraft.client.gui.DisplayEffectsScreen;
import net.minecraft.entity.LivingEntity;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.EffectType;

public class FreezeEffect extends Effect {
    public FreezeEffect(EffectType typeIn, int liquidColorIn) {
        super(typeIn, liquidColorIn);
    }

    @Override
    public void performEffect(LivingEntity entityLivingBaseIn, int amplifier) {
        if (!entityLivingBaseIn.world.isRemote()) {
            Double x = entityLivingBaseIn.getPosX();
            Double y = entityLivingBaseIn.getPosY();
            Double z = entityLivingBaseIn.getPosZ();

            entityLivingBaseIn.teleportKeepLoaded(x, y, z);
            entityLivingBaseIn.setMotion(0, 0, 0);
        }
        super.performEffect(entityLivingBaseIn, amplifier);
    }

    @Override
    public boolean isReady(int duration, int amplifier) {
        return true;
    }

    @Override
    public void renderHUDEffect(EffectInstance effect, AbstractGui gui, MatrixStack mStack, int x, int y, float z, float alpha) {
        super.renderHUDEffect(effect, gui, mStack, x, y, z, alpha);
    }

    @Override
    public void renderInventoryEffect(EffectInstance effect, DisplayEffectsScreen<?> gui, MatrixStack mStack, int x, int y, float z) {
        super.renderInventoryEffect(effect, gui, mStack, x, y, z);
    }
}
