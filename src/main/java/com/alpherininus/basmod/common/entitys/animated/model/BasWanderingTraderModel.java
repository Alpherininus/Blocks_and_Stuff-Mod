package com.alpherininus.basmod.common.entitys.animated.model;

import com.alpherininus.basmod.Basmod;
import com.alpherininus.basmod.common.entitys.animated.BasBossEntity;
import com.alpherininus.basmod.common.entitys.animated.BasWanderingTraderEntity;
import net.minecraft.util.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class BasWanderingTraderModel extends AnimatedGeoModel<BasWanderingTraderEntity> {
    @Override
    public ResourceLocation getModelLocation(BasWanderingTraderEntity basBossEntity) {
        return new ResourceLocation(Basmod.MOD_ID, "geo/basmod_trader.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(BasWanderingTraderEntity basBossEntity) {
        return new ResourceLocation(Basmod.MOD_ID, "textures/entity/basmod_trader.png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(BasWanderingTraderEntity basBossEntity) {
        return new ResourceLocation(Basmod.MOD_ID, "animations/basmod_trader.animation.json");
    }
}
