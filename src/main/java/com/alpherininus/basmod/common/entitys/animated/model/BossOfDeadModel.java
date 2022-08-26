package com.alpherininus.basmod.common.entitys.animated.model;

import com.alpherininus.basmod.Basmod;
import com.alpherininus.basmod.common.entitys.animated.BasBossEntity;
import com.alpherininus.basmod.common.entitys.animated.BossOfDeadEntity;
import net.minecraft.util.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class BossOfDeadModel extends AnimatedGeoModel<BossOfDeadEntity> {
    @Override
    public ResourceLocation getModelLocation(BossOfDeadEntity bossOfDeadEntity) {
        return new ResourceLocation(Basmod.MOD_ID, "geo/boss_of_dead.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(BossOfDeadEntity bossOfDeadEntity) {
        return new ResourceLocation(Basmod.MOD_ID, "textures/entity/boss_of_dead.png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(BossOfDeadEntity bossOfDeadEntity) {
        return new ResourceLocation(Basmod.MOD_ID, "animations/boss_of_dead.animation.json");
    }
}
