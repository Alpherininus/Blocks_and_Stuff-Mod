package com.alpherininus.basmod.common.entitys.model;

import com.alpherininus.basmod.common.entitys.CopperGolemEntity;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.passive.GolemEntity;
import net.minecraft.util.math.MathHelper;


public class CopperGolemModel<C extends CopperGolemEntity> extends EntityModel<C> {
	private final ModelRenderer head;
	private final ModelRenderer body;
	private final ModelRenderer left_arm;
	private final ModelRenderer right_leg;
	private final ModelRenderer right_arm;
	private final ModelRenderer left_leg;

	public CopperGolemModel() {
		textureWidth = 64;
		textureHeight = 64;

		head = new ModelRenderer(this);
		head.setRotationPoint(0.0F, 15.0F, 0.0F);
		head.setTextureOffset(41, 7).addBox(-2.0F, -14.0F, -2.5F, 4.0F, 4.0F, 4.0F, 0.0F, false);
		head.setTextureOffset(0, 0).addBox(-1.0F, -10.0F, -1.5F, 2.0F, 4.0F, 2.0F, 0.0F, true);
		head.setTextureOffset(0, 0).addBox(-5.5F, -6.0F, -5.0F, 11.0F, 6.0F, 9.0F, 0.0F, false);
		head.setTextureOffset(32, 0).addBox(-1.5F, -3.0F, -6.5F, 3.0F, 4.0F, 2.0F, 0.0F, false);

		body = new ModelRenderer(this);
		body.setRotationPoint(0.0F, 24.0F, 0.0F);
		body.setTextureOffset(16, 16).addBox(-4.5F, -9.0F, -4.0F, 9.0F, 5.0F, 7.0F, 0.0F, true);

		left_arm = new ModelRenderer(this);
		left_arm.setRotationPoint(4.5F, 16.0F, 0.0F);
		left_arm.setTextureOffset(49, 25).addBox(0.0F, -1.5F, -2.0F, 3.0F, 9.0F, 4.0F, 0.0F, false);

		right_leg = new ModelRenderer(this);
		right_leg.setRotationPoint(-2.5F, 20.0F, 0.0F);
		right_leg.setTextureOffset(15, 29).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 4.0F, 4.0F, 0.0F, false);

		right_arm = new ModelRenderer(this);
		right_arm.setRotationPoint(-4.5F, 16.0F, 0.0F);
		right_arm.setTextureOffset(0, 24).addBox(-3.0F, -1.5F, -2.0F, 3.0F, 9.0F, 4.0F, 0.0F, false);

		left_leg = new ModelRenderer(this);
		left_leg.setRotationPoint(2.5F, 20.0F, 0.0F);
		left_leg.setTextureOffset(32, 30).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 4.0F, 4.0F, 0.0F, false);
	}

	@Override
	public void setRotationAngles(C entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		this.head.rotateAngleX = headPitch * ((float)Math.PI / 180F);
		this.head.rotateAngleY = netHeadYaw * ((float)Math.PI / 180F);
		this.right_leg.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + (float)Math.PI) * 1.4F * limbSwingAmount;
		this.left_leg.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
		this.right_arm.rotateAngleX = MathHelper.cos(limbSwing * 0.5552F + (float)Math.PI) * 0.9F * limbSwingAmount;
		this.left_arm.rotateAngleX = MathHelper.cos(limbSwing * 0.5552F) * 0.9F * limbSwingAmount;

	}

	@Override
	public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha){
		head.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
		body.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
		left_arm.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
		right_leg.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
		right_arm.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
		left_leg.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
	}

	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}
}