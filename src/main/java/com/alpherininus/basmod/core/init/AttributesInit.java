package com.alpherininus.basmod.core.init;

import com.alpherininus.basmod.Basmod;
import net.minecraft.entity.ai.attributes.Attribute;
import net.minecraft.entity.ai.attributes.RangedAttribute;
import net.minecraft.util.registry.Registry;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class AttributesInit {
    public static final DeferredRegister<Attribute> ATTRIBUTES_DEFERRED_REGISTER = DeferredRegister.create(ForgeRegistries.ATTRIBUTES, Basmod.MOD_ID);

    public static final Attribute MAX_MANA = registerAttribute("magic.max_mana", (new RangedAttribute("attribute.name.magic.max_mana", 0.0D, 8.0D, 88.0D)).setShouldWatch(true));

    private static Attribute registerAttribute(String id, Attribute attribute) {
        return Registry.register(Registry.ATTRIBUTE, id, attribute);
    }

    public static void register(IEventBus eventBus) {
        ATTRIBUTES_DEFERRED_REGISTER.register(eventBus);
    }

}
