package com.alpherininus.basmod.common.entitys.renderer;

import com.alpherininus.basmod.Basmod;
import com.alpherininus.basmod.common.entitys.CopperGolemEntity;
import com.alpherininus.basmod.common.entitys.model.CopperGolemModel;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;


public class CopperGolemRenderer extends MobRenderer<CopperGolemEntity, CopperGolemModel<CopperGolemEntity>> {

    protected static final ResourceLocation COPPER_TEXTURES =
            new ResourceLocation(Basmod.MOD_ID, "textures/entity/copper_golem.png");

    public CopperGolemRenderer(EntityRendererManager renderManagerIn) {
        super(renderManagerIn, new CopperGolemModel<>(), 0.7F);
    }

    @Override
    public ResourceLocation getEntityTexture(CopperGolemEntity entity) {
        return COPPER_TEXTURES;
    }

}
