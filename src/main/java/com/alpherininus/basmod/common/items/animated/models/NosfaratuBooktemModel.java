package com.alpherininus.basmod.common.items.animated.models;

import com.alpherininus.basmod.Basmod;
import com.alpherininus.basmod.common.items.animated.HealStaffItem;
import com.alpherininus.basmod.common.items.animated.NosfaratuBookItem;
import net.minecraft.util.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class NosfaratuBooktemModel extends AnimatedGeoModel<NosfaratuBookItem> {
    @Override
    public ResourceLocation getModelLocation(NosfaratuBookItem nosfaratuBookItem) {
        return new ResourceLocation(Basmod.MOD_ID, "geo/nosfaratu_book.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(NosfaratuBookItem nosfaratuBookItem) {
        return new ResourceLocation(Basmod.MOD_ID, "textures/items/nosfaratu_book.png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(NosfaratuBookItem nosfaratuBookItem) {
        return new ResourceLocation(Basmod.MOD_ID, "animations/nosfaratu_book.animation.json");
    }
}
