package com.alpherininus.basmod.common.entitys.renderer;

import com.alpherininus.basmod.Basmod;
import com.alpherininus.basmod.common.entitys.ThrowingAxeEntity;
import com.alpherininus.basmod.common.entitys.model.ThrowingAxeModel;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.TridentRenderer;
import net.minecraft.client.renderer.entity.model.TridentModel;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.entity.projectile.TridentEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.vector.Vector3f;

public class ThrowingAxeRenderer extends EntityRenderer<ThrowingAxeEntity> {

    public static final ResourceLocation THROWAX =
            new ResourceLocation(Basmod.MOD_ID, "textures/entity/throw_ax.png");
    private final ThrowingAxeModel tridentModel = new ThrowingAxeModel();

    public ThrowingAxeRenderer(EntityRendererManager renderManagerIn) {
        super(renderManagerIn);
    }

    public void render(ThrowingAxeEntity entityIn, float entityYaw, float partialTicks, MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int packedLightIn) {
        matrixStackIn.push();
        matrixStackIn.rotate(Vector3f.YP.rotationDegrees(MathHelper.lerp(partialTicks, entityIn.prevRotationYaw, entityIn.rotationYaw) - 90.0F));
        matrixStackIn.rotate(Vector3f.ZP.rotationDegrees(MathHelper.lerp(partialTicks, entityIn.prevRotationPitch, entityIn.rotationPitch) + 90.0F));
        IVertexBuilder ivertexbuilder = net.minecraft.client.renderer.ItemRenderer.getEntityGlintVertexBuilder(bufferIn, this.tridentModel.getRenderType(this.getEntityTexture(entityIn)), false, entityIn.func_226572_w_());
        this.tridentModel.render(matrixStackIn, ivertexbuilder, packedLightIn, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);
        matrixStackIn.pop();
        super.render(entityIn, entityYaw, partialTicks, matrixStackIn, bufferIn, packedLightIn);
    }

    public ResourceLocation getEntityTexture(ThrowingAxeEntity entity) {
        return THROWAX;
    }

}
