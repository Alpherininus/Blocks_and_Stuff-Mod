package com.alpherininus.basmod.core.init;

import com.alpherininus.basmod.Basmod;
import com.alpherininus.basmod.common.effekts.FreezeEffect;
import com.alpherininus.basmod.common.effekts.ManaEffect;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectType;
import net.minecraft.potion.HealthBoostEffect;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.UUID;

public class EffectInit {
    public static final DeferredRegister<Effect> POTIONS = DeferredRegister.create(ForgeRegistries.POTIONS, Basmod.MOD_ID);

    public static final RegistryObject<Effect> FREEZE = POTIONS.register("freeze", () -> new FreezeEffect(EffectType.HARMFUL, 137207240));

    // public static final RegistryObject<Effect> MANA = POTIONS.register("mana", () -> new ManaEffect(EffectType.BENEFICIAL, 1360255).addAttributesModifier(AttributesInit.MAX_MANA.get(), "0502a004-c44a-4af8-866c-283bebdef6de", 8.0D, AttributeModifier.Operation.ADDITION));
    public static final RegistryObject<Effect> MANA = POTIONS.register("mana", () -> new ManaEffect(EffectType.BENEFICIAL, 1360255));


    public static void register(IEventBus eventBus) {
        POTIONS.register(eventBus);
    }

}
