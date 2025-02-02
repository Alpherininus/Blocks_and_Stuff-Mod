package com.alpherininus.basmod.common.items.animated.models;

import com.alpherininus.basmod.Basmod;
import com.alpherininus.basmod.common.items.animated.SpinBladeItem;
import net.minecraft.util.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;


public class SpinBladeModel extends AnimatedGeoModel<SpinBladeItem> {
    @Override
    public ResourceLocation getModelLocation(SpinBladeItem spinBladeItem) {
        return new ResourceLocation(Basmod.MOD_ID, "geo/spin_blade.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(SpinBladeItem spinBladeItem) {
        return new ResourceLocation(Basmod.MOD_ID, "textures/items/spin_blade.png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(SpinBladeItem spinBladeItem) {
        return new ResourceLocation(Basmod.MOD_ID, "animations/spin_blade.animation.json");
    }
}