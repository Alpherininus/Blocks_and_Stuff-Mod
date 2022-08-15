package com.alpherininus.basmod.common.entitys.model;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class JetpackArmorChestplatteModel extends EntityModel<Entity> {
	private final ModelRenderer body;
	private final ModelRenderer left_arm;
	private final ModelRenderer right_arm;

	public JetpackArmorChestplatteModel() {
		textureWidth = 64;
		textureHeight = 64;

		body = new ModelRenderer(this);
		body.setRotationPoint(0.0F, 0.0F, 0.0F);
		body.setTextureOffset(0, 22).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 11.0F, 5.0F, 0.0F, false);
		body.setTextureOffset(40, 2).addBox(-2.0F, 1.0F, 3.0F, 4.0F, 2.0F, 3.0F, 0.0F, false);
		body.setTextureOffset(34, 0).addBox(-4.0F, -1.0F, 5.0F, 8.0F, 1.0F, 1.0F, 0.0F, false);
		body.setTextureOffset(13, 11).addBox(-6.0F, 0.0F, 3.0F, 4.0F, 11.0F, 5.0F, 0.0F, false);
		body.setTextureOffset(0, 38).addBox(-5.0F, -2.0F, 4.0F, 2.0F, 11.0F, 3.0F, 0.0F, false);
		body.setTextureOffset(37, 7).addBox(3.0F, -2.0F, 4.0F, 2.0F, 11.0F, 3.0F, 0.0F, false);
		body.setTextureOffset(0, 0).addBox(2.0F, 0.0F, 3.0F, 4.0F, 11.0F, 5.0F, 0.0F, false);
		body.setTextureOffset(32, 31).addBox(2.0F, 0.0F, -2.0F, 2.0F, 10.0F, 5.0F, 0.0F, false);
		body.setTextureOffset(18, 31).addBox(-4.0F, 0.0F, -2.0F, 2.0F, 10.0F, 5.0F, 0.0F, false);

		left_arm = new ModelRenderer(this);
		left_arm.setRotationPoint(-5.0F, 2.0F, 0.0F);
		left_arm.setTextureOffset(18, 0).addBox(9.0F, -2.0F, -3.0F, 5.0F, 4.0F, 6.0F, 0.0F, false);
		left_arm.setTextureOffset(0, 16).addBox(10.0F, -3.0F, -1.0F, 3.0F, 1.0F, 2.0F, 0.0F, false);

		right_arm = new ModelRenderer(this);
		right_arm.setRotationPoint(5.0F, 2.0F, 0.0F);
		right_arm.setTextureOffset(25, 21).addBox(-14.0F, -2.0F, -3.0F, 5.0F, 4.0F, 6.0F, 0.0F, false);
		right_arm.setTextureOffset(13, 0).addBox(-13.0F, -3.0F, -1.0F, 3.0F, 1.0F, 2.0F, 0.0F, false);
	}

	@Override
	public void setRotationAngles(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch){
		//previously the render function, render code was moved to a method below
	}

	@Override
	public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha){
		body.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
		left_arm.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
		right_arm.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
	}

	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}
}