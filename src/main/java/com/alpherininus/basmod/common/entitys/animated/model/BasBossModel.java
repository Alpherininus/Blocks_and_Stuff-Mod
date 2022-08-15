package com.alpherininus.basmod.common.entitys.animated.model;

import com.alpherininus.basmod.Basmod;
import com.alpherininus.basmod.common.entitys.animated.BasBossEntity;
import net.minecraft.util.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class BasBossModel extends AnimatedGeoModel<BasBossEntity> {
    @Override
    public ResourceLocation getModelLocation(BasBossEntity basBossEntity) {
        return new ResourceLocation(Basmod.MOD_ID, "geo/basmod_boss.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(BasBossEntity basBossEntity) {
        return new ResourceLocation(Basmod.MOD_ID, "textures/entity/basmod_boss.png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(BasBossEntity basBossEntity) {
        return new ResourceLocation(Basmod.MOD_ID, "animations/basmod_boss.animation.json");
    }
}
