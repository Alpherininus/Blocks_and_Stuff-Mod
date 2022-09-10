package com.alpherininus.basmod.core.init;

import com.alpherininus.basmod.Basmod;
import com.alpherininus.basmod.common.effekts.FreezeEffect;
import com.alpherininus.basmod.common.effekts.ManaEffect;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class EffectInit {
    public static final DeferredRegister<Effect> POTIONS = DeferredRegister.create(ForgeRegistries.POTIONS, Basmod.MOD_ID);

    public static final RegistryObject<Effect> FREEZE = POTIONS.register("freeze", () -> new FreezeEffect(EffectType.HARMFUL, 3124687));

    public static final RegistryObject<Effect> MANA = POTIONS.register("mana", () -> new ManaEffect(EffectType.BENEFICIAL, 3124687));


    public static void register(IEventBus eventBus) {
        POTIONS.register(eventBus);
    }

}
