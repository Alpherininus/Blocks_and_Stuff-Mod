package com.alpherininus.basmod.common.items.animated.models;

import com.alpherininus.basmod.Basmod;
import com.alpherininus.basmod.common.items.animated.MagicalStaffItem;
import net.minecraft.util.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class MagicalStaffItemModel extends AnimatedGeoModel<MagicalStaffItem> {
    @Override
    public ResourceLocation getModelLocation(MagicalStaffItem magicalStaffItem) {
        return new ResourceLocation(Basmod.MOD_ID, "geo/magical_staff.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(MagicalStaffItem magicalStaffItem) {
        return new ResourceLocation(Basmod.MOD_ID, "textures/items/magical_staff.png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(MagicalStaffItem magicalStaffItem) {
        return new ResourceLocation(Basmod.MOD_ID, "animations/magical_staff.animation.json");
    }
}
