package com.alpherininus.basmod.common.effekts;

import com.alpherininus.basmod.client.entity.BasmodLivingEntity;
import com.alpherininus.basmod.core.init.EffectInit;
import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.AbstractGui;
import net.minecraft.client.gui.DisplayEffectsScreen;
import net.minecraft.entity.LivingEntity;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.EffectType;
import net.minecraft.potion.Effects;
import net.minecraft.util.math.MathHelper;

public class ManaEffect extends Effect {


    public ManaEffect(EffectType typeIn, int liquidColorIn) {
        super(typeIn, liquidColorIn);
    }

    @Override
    public void performEffect(LivingEntity entityLivingBaseIn, int amplifier) {
        // Effect funktion
        BasmodLivingEntity basmodLiving = null;
        if (this == EffectInit.MANA.get()) {
            assert false;
            if (basmodLiving.getMana() < basmodLiving.getMaxMana()) {
                basmodLiving.mana(1.0F);
            }
        }
        super.performEffect(entityLivingBaseIn, amplifier);
    }

    @Override
    public boolean isReady(int duration, int amplifier) {
        return true;
    }

    @Override
    public void renderHUDEffect(EffectInstance effect, AbstractGui gui, MatrixStack mStack, int x, int y, float z, float alpha) {
        effect.getEffectInstance().getPotion().renderHUDEffect(effect.getEffectInstance(), gui, mStack, x, y, z, alpha);

        super.renderHUDEffect(effect, gui, mStack, x, y, z, alpha);
    }

    @Override
    public void renderInventoryEffect(EffectInstance effect, DisplayEffectsScreen<?> gui, MatrixStack mStack, int x, int y, float z) {
        effect.getEffectInstance().getPotion().renderInventoryEffect(effect.getEffectInstance(), gui, mStack, x, y, z);
    }

    @Override
    public boolean shouldRenderHUD(EffectInstance effect) {
        return effect.getEffectInstance().getPotion().shouldRenderHUD(effect.getEffectInstance());
    }
}
