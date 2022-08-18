package com.alpherininus.basmod.common.entitys.model;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ThrowingAxeModel extends EntityModel<Entity> {
	private final ModelRenderer axe;

	public ThrowingAxeModel() {
		textureWidth = 16;
		textureHeight = 16;

		axe = new ModelRenderer(this);
		axe.setRotationPoint(0.0F, 24.0F, 0.0F);

		ModelRenderer throw_ax = new ModelRenderer(this);
		throw_ax.setRotationPoint(-8.0F, -8.0F, 8.0F);
		axe.addChild(throw_ax);
		
	}

	@Override
	public void setRotationAngles(Entity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		// no setRotationAngles
	}

	@Override
	public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha){
		axe.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
	}

	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}
}