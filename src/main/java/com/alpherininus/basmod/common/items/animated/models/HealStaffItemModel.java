package com.alpherininus.basmod.common.items.animated.models;

import com.alpherininus.basmod.Basmod;
import com.alpherininus.basmod.common.items.animated.HealStaffItem;
import com.alpherininus.basmod.common.items.animated.MagicalStaffItem;
import net.minecraft.util.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class HealStaffItemModel extends AnimatedGeoModel<HealStaffItem> {
    @Override
    public ResourceLocation getModelLocation(HealStaffItem heal) {
        return new ResourceLocation(Basmod.MOD_ID, "geo/heal_staff.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(HealStaffItem heal) {
        return new ResourceLocation(Basmod.MOD_ID, "textures/items/heal_staff.png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(HealStaffItem heal) {
        return new ResourceLocation(Basmod.MOD_ID, "animations/heal_staff.animation.json");
    }
}
