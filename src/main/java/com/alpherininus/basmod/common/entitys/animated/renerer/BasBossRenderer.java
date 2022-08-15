package com.alpherininus.basmod.common.entitys.animated.renerer;

import com.alpherininus.basmod.Basmod;
import com.alpherininus.basmod.common.entitys.animated.BasBossEntity;
import com.alpherininus.basmod.common.entitys.animated.model.BasBossModel;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.util.ResourceLocation;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

import javax.annotation.Nullable;

public class BasBossRenderer extends GeoEntityRenderer<BasBossEntity> {

    public BasBossRenderer(EntityRendererManager renderManager) {
        super(renderManager, new BasBossModel());
        this.shadowSize = 0.9F;
    }

    @Override
    public ResourceLocation getEntityTexture(BasBossEntity entity) {
        return new ResourceLocation(Basmod.MOD_ID, "textures/entity/basmod_boss.png");
    }

    @Override
    public RenderType getRenderType(BasBossEntity animatable, float partialTicks, MatrixStack stack, @Nullable IRenderTypeBuffer renderTypeBuffer, @Nullable IVertexBuilder vertexBuilder, int packedLightIn, ResourceLocation textureLocation) {

        stack.scale(1.0f, 1.0f, 1.0f);

        return super.getRenderType(animatable, partialTicks, stack, renderTypeBuffer, vertexBuilder, packedLightIn, textureLocation);
    }
}
