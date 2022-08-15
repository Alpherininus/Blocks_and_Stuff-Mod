package com.alpherininus.basmod.common.items.animated.models;

import com.alpherininus.basmod.Basmod;
import com.alpherininus.basmod.common.items.animated.TeleportStaffItem;
import net.minecraft.util.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class TeleportStaffItemModel extends AnimatedGeoModel<TeleportStaffItem> {
    @Override
    public ResourceLocation getModelLocation(TeleportStaffItem tele) {
        return new ResourceLocation(Basmod.MOD_ID, "geo/teleport_staff.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(TeleportStaffItem tele) {
        return new ResourceLocation(Basmod.MOD_ID, "textures/items/teleport_staff.png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(TeleportStaffItem tele) {
        return new ResourceLocation(Basmod.MOD_ID, "animations/teleport_staff.animation.json");
    }
}
