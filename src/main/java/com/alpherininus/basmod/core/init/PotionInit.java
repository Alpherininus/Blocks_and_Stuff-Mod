package com.alpherininus.basmod.core.init;

import com.alpherininus.basmod.Basmod;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Potion;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class PotionInit {
    public static final DeferredRegister<Potion> POTIONS = DeferredRegister.create(ForgeRegistries.POTION_TYPES, Basmod.MOD_ID);

    public static final RegistryObject<Potion> FREEZE_POTION = POTIONS.register("freeze_potion",
            () -> new Potion(new EffectInstance(EffectInit.FREEZE.get(), 200, 0)));

    public static final RegistryObject<Potion> MANA = POTIONS.register("mana_potion",
            () -> new Potion(new EffectInstance(EffectInit.MANA.get(), 100, 0)));



    public static void register(IEventBus eventBus) {
        POTIONS.register(eventBus);
    }

}
