package com.alpherininus.basmod.common.items.models;

import net.minecraft.item.Item;
import net.minecraft.item.ItemModelsProperties;
import net.minecraft.util.ResourceLocation;

public class BasmodItemModel {

    public static void makeBow(Item item) {
        ItemModelsProperties.registerProperty(item, new ResourceLocation("pull"), (stack, clientWorld, livingEntity) -> {
            if (livingEntity == null) {
                return 0.0F;
            } else {
                if (livingEntity.getActiveItemStack() != stack) {
                    return 0.0F;
                } else {
                    return (float)(stack.getUseDuration() - livingEntity.getItemInUseCount()) / 20.0F;
                }
            }
        });

        ItemModelsProperties.registerProperty(item, new ResourceLocation("pulling"),
                (stack, clientWorld, livingEntity) -> livingEntity != null && livingEntity.isHandActive() && livingEntity.getActiveItemStack() == stack ? 1.0F : 0.0F);
    }
}
