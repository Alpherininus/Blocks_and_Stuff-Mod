package com.alpherininus.basmod.core.util;

import net.minecraft.entity.ai.attributes.Attribute;
import net.minecraft.entity.ai.attributes.RangedAttribute;
import net.minecraft.util.registry.Registry;

public class BasmodAttributes {
    public static final Attribute MAX_MANA = register("magic.max_mana", (new RangedAttribute("attribute.name.magic.max_mana", 0.0D, 8.0D, 88.0D)).setShouldWatch(true));

    private static Attribute register(String id, Attribute attribute) {
        return Registry.register(Registry.ATTRIBUTE, id, attribute);
    }
}
