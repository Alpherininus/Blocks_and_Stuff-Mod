package com.alpherininus.basmod.common.items.armor.models;

import com.alpherininus.basmod.Basmod;
import com.alpherininus.basmod.common.items.armor.JetPackArmorItem;
import net.minecraft.util.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class JetPackArmorModel extends AnimatedGeoModel<JetPackArmorItem> {
    @Override
    public ResourceLocation getModelLocation(JetPackArmorItem jetPackArmorItem) {
        return new ResourceLocation(Basmod.MOD_ID, "geo/jetpack_armor.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(JetPackArmorItem jetPackArmorItem) {
        return new ResourceLocation(Basmod.MOD_ID, "textures/models/armor/jetpack_armor.png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(JetPackArmorItem jetPackArmorItem) {
        return new ResourceLocation(Basmod.MOD_ID, "animations/jetpack_armor.animation.json");

    }
}
