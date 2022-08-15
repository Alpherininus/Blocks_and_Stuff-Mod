package com.alpherininus.basmod.common.entitys.model;
//SeiðrShellModel

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.passive.horse.AbstractHorseEntity;
import net.minecraft.entity.passive.horse.MuleEntity;
import net.minecraft.util.math.MathHelper;

public class SeieorShellModel<C extends MuleEntity> extends EntityModel<C> {
	private final ModelRenderer body;
	private final ModelRenderer left_mec_arm;
	private final ModelRenderer left_mec_arm_r1;
	private final ModelRenderer right_mec_arm;
	private final ModelRenderer right_mec_arm_r1;
	private final ModelRenderer front_left_leg;
	private final ModelRenderer front_right_leg;
	private final ModelRenderer side_left_leg;
	private final ModelRenderer side_right_leg;
	// private final ModelRenderer saddle;
	private final ModelRenderer tail;

	public SeieorShellModel() {
		textureWidth = 128;
		textureHeight = 128;

		left_mec_arm = new ModelRenderer(this);
		left_mec_arm.setRotationPoint(7.0F, -6.0F, 2.0F);
		left_mec_arm.setTextureOffset(80, 46).addBox(-1.0F, 8.0F, -7.0F, 5.0F, 5.0F, 7.0F, 0.05F, false);
		left_mec_arm.setTextureOffset(78, 10).addBox(-1.0F, 2.0F, -4.0F, 6.0F, 7.0F, 7.0F, 0.05F, false);

		left_mec_arm_r1 = new ModelRenderer(this);
		left_mec_arm_r1.setRotationPoint(-7.0F, 30.0F, 5.0F);
		left_mec_arm.addChild(left_mec_arm_r1);
		setRotationAngle(left_mec_arm_r1, 0.0F, 0.48F, 0.0F);
		left_mec_arm_r1.setTextureOffset(0, 26).addBox(13.0F, -23.0F, -7.0F, 3.0F, 7.0F, 3.0F, 0.05F, false);
		left_mec_arm_r1.setTextureOffset(64, 33).addBox(16.0F, -7.0F, -8.0F, 1.0F, 1.0F, 5.0F, 0.05F, false);
		left_mec_arm_r1.setTextureOffset(18, 0).addBox(16.0F, -31.0F, -13.0F, 1.0F, 17.0F, 1.0F, 0.05F, false);
		left_mec_arm_r1.setTextureOffset(71, 95).addBox(16.0F, -31.0F, 1.0F, 1.0F, 17.0F, 1.0F, 0.05F, false);
		left_mec_arm_r1.setTextureOffset(33, 26).addBox(16.0F, -13.0F, -2.0F, 1.0F, 4.0F, 2.0F, 0.05F, false);
		left_mec_arm_r1.setTextureOffset(0, 49).addBox(16.0F, -13.0F, -11.0F, 1.0F, 4.0F, 2.0F, 0.05F, false);
		left_mec_arm_r1.setTextureOffset(91, 79).addBox(16.0F, -32.0F, -12.0F, 1.0F, 19.0F, 3.0F, 0.05F, false);
		left_mec_arm_r1.setTextureOffset(16, 92).addBox(16.0F, -32.0F, -2.0F, 1.0F, 19.0F, 3.0F, 0.05F, false);
		left_mec_arm_r1.setTextureOffset(59, 62).addBox(16.0F, -33.0F, -9.0F, 1.0F, 26.0F, 7.0F, 0.05F, false);

		right_mec_arm = new ModelRenderer(this);
		right_mec_arm.setRotationPoint(-7.0F, -6.0F, 2.0F);
		right_mec_arm.setTextureOffset(33, 26).addBox(-4.0F, 8.0F, -7.0F, 5.0F, 5.0F, 7.0F, 0.05F, false);
		right_mec_arm.setTextureOffset(70, 32).addBox(-5.0F, 2.0F, -4.0F, 6.0F, 7.0F, 7.0F, 0.05F, false);

		right_mec_arm_r1 = new ModelRenderer(this);
		right_mec_arm_r1.setRotationPoint(7.0F, 30.0F, 5.0F);
		right_mec_arm.addChild(right_mec_arm_r1);
		setRotationAngle(right_mec_arm_r1, 0.48F, 0.0F, 0.0F);
		right_mec_arm_r1.setTextureOffset(12, 64).addBox(-10.0F, -25.0F, -6.0F, 2.0F, 2.0F, 8.0F, 0.05F, false);
		right_mec_arm_r1.setTextureOffset(57, 32).addBox(-10.0F, -47.0F, -4.0F, 2.0F, 2.0F, 4.0F, 0.05F, false);
		right_mec_arm_r1.setTextureOffset(75, 79).addBox(-10.0F, -45.0F, -5.0F, 2.0F, 19.0F, 6.0F, 0.05F, false);
		right_mec_arm_r1.setTextureOffset(12, 19).addBox(-10.0F, -26.0F, -3.0F, 2.0F, 1.0F, 2.0F, 0.05F, false);
		right_mec_arm_r1.setTextureOffset(42, 0).addBox(-10.0F, -23.0F, -3.0F, 2.0F, 7.0F, 2.0F, 0.05F, false);

		body = new ModelRenderer(this);
		body.setRotationPoint(0.0F, 24.0F, 3.0F);
		body.setTextureOffset(0, 0).addBox(-5.0F, -15.0F, -7.0F, 10.0F, 4.0F, 22.0F, 0.05F, false);
		body.setTextureOffset(89, 0).addBox(-5.0F, -17.0F, -7.0F, 10.0F, 6.0F, 1.0F, 0.05F, false);
		body.setTextureOffset(42, 17).addBox(-4.0F, -21.0F, -7.0F, 8.0F, 4.0F, 1.0F, 0.05F, false);
		body.setTextureOffset(0, 19).addBox(-3.0F, -22.0F, -7.0F, 6.0F, 1.0F, 1.0F, 0.05F, false);
		body.setTextureOffset(0, 26).addBox(-5.0F, -20.0F, 3.0F, 10.0F, 10.0F, 13.0F, 0.05F, false);
		body.setTextureOffset(75, 58).addBox(-5.0F, -39.0F, 3.0F, 10.0F, 18.0F, 3.0F, 0.05F, false);
		body.setTextureOffset(26, 58).addBox(-5.0F, -40.0F, -4.0F, 10.0F, 1.0F, 10.0F, 0.05F, false);
		body.setTextureOffset(22, 69).addBox(5.0F, -39.0F, -6.0F, 1.0F, 16.0F, 10.0F, 0.05F, false);
		body.setTextureOffset(72, 0).addBox(6.0F, -33.0F, -6.0F, 5.0F, 2.0F, 7.0F, 0.05F, false);
		body.setTextureOffset(24, 95).addBox(5.0F, -33.0F, 2.0F, 2.0F, 2.0F, 7.0F, 0.05F, false);
		body.setTextureOffset(92, 94).addBox(-7.0F, -33.0F, 2.0F, 2.0F, 2.0F, 7.0F, 0.05F, false);
		body.setTextureOffset(92, 94).addBox(-7.0F, -36.0F, 2.0F, 2.0F, 2.0F, 7.0F, 0.05F, false);
		body.setTextureOffset(92, 94).addBox(5.0F, -36.0F, 2.0F, 2.0F, 2.0F, 7.0F, 0.05F, true);
		body.setTextureOffset(53, 17).addBox(6.0F, -32.0F, -7.0F, 7.0F, 4.0F, 11.0F, 0.05F, false);
		body.setTextureOffset(0, 49).addBox(-13.0F, -32.0F, -7.0F, 7.0F, 4.0F, 11.0F, 0.05F, false);
		body.setTextureOffset(34, 69).addBox(-11.0F, -33.0F, -6.0F, 5.0F, 2.0F, 7.0F, 0.05F, false);
		body.setTextureOffset(89, 103).addBox(5.0F, -38.0F, -8.0F, 1.0F, 14.0F, 2.0F, 0.05F, false);
		body.setTextureOffset(50, 78).addBox(-6.0F, -38.0F, -8.0F, 1.0F, 14.0F, 2.0F, 0.05F, false);
		body.setTextureOffset(0, 64).addBox(-6.0F, -39.0F, -6.0F, 1.0F, 16.0F, 10.0F, 0.05F, false);
		body.setTextureOffset(95, 103).addBox(-6.0F, -24.0F, 2.0F, 1.0F, 13.0F, 2.0F, 0.05F, false);
		body.setTextureOffset(103, 86).addBox(5.0F, -24.0F, 2.0F, 1.0F, 13.0F, 2.0F, 0.05F, false);
		body.setTextureOffset(42, 0).addBox(-5.0F, -21.0F, 4.0F, 10.0F, 7.0F, 10.0F, 0.05F, false);
		body.setTextureOffset(36, 39).addBox(-6.0F, -20.0F, 4.0F, 12.0F, 9.0F, 10.0F, 0.05F, false);

		front_left_leg = new ModelRenderer(this);
		front_left_leg.setRotationPoint(3.0F, 14.0F, -1.0F);
		front_left_leg.setTextureOffset(38, 100).addBox(-2.0F, -1.0F, -2.9F, 4.0F, 7.0F, 4.0F, 0.0F, false);
		front_left_leg.setTextureOffset(101, 54).addBox(-1.0F, 3.0F, -3.9F, 4.0F, 7.0F, 4.0F, 0.0F, false);

		front_right_leg = new ModelRenderer(this);
		front_right_leg.setRotationPoint(-3.0F, 14.0F, -2.0F);
		front_right_leg.setTextureOffset(97, 37).addBox(-2.0F, -1.0F, -1.9F, 4.0F, 7.0F, 4.0F, 0.0F, false);
		front_right_leg.setTextureOffset(99, 75).addBox(-3.0F, 3.0F, -2.9F, 4.0F, 7.0F, 4.0F, 0.0F, false);

		side_left_leg = new ModelRenderer(this);
		side_left_leg.setRotationPoint(5.0F, 11.4F, 16.1F);
		side_left_leg.setTextureOffset(0, 90).addBox(-2.0F, -3.4F, -2.0F, 4.0F, 12.0F, 4.0F, 0.0F, false);
		side_left_leg.setTextureOffset(89, 24).addBox(-1.0F, 3.6F, -3.0F, 4.0F, 9.0F, 4.0F, 0.0F, false);

		side_right_leg = new ModelRenderer(this);
		side_right_leg.setRotationPoint(-5.0F, 10.4F, 16.1F);
		side_right_leg.setTextureOffset(0, 90).addBox(-2.0F, -2.4F, -2.0F, 4.0F, 12.0F, 4.0F, 0.0F, true);
		side_right_leg.setTextureOffset(89, 24).addBox(-3.0F, 4.6F, -3.0F, 4.0F, 9.0F, 4.0F, 0.0F, true);

		tail = new ModelRenderer(this);
		tail.setRotationPoint(0.0F, 5.0F, 18.0F);
		tail.setTextureOffset(35, 86).addBox(-1.5F, -2.0F, 0.0F, 3.0F, 3.0F, 9.0F, 0.0F, false);
	}

	@Override
	public void setRotationAngles(C entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch){
		this.tail.rotateAngleX = headPitch * ((float)Math.PI / 90F);
		this.tail.rotateAngleY = netHeadYaw * ((float)Math.PI / 90F);
		// legs
		this.front_right_leg.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + (float)Math.PI) * 1.4F * limbSwingAmount;
		this.front_left_leg.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;

		this.side_right_leg.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + (float)Math.PI) * 1.4F * limbSwingAmount;
		this.side_left_leg.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
		// arms
		this.right_mec_arm.rotateAngleX = MathHelper.cos(limbSwing * 0.5552F + (float)Math.PI) * 0.9F * limbSwingAmount;
		this.left_mec_arm.rotateAngleX = MathHelper.cos(limbSwing * 0.5552F) * 0.9F * limbSwingAmount;
	}

	@Override
	public void render(MatrixStack matrixStackIn, IVertexBuilder bufferIn, int packedLightIn, int packedOverlayIn, float red, float green, float blue, float alpha){
		body.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
		left_mec_arm.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
		right_mec_arm.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
		front_left_leg.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
		front_right_leg.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
		side_left_leg.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
		side_right_leg.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
		// saddle.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
		tail.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
	}

	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}

	@Override
	public void setLivingAnimations(C entityIn, float limbSwing, float limbSwingAmount, float partialTick) {
		this.right_mec_arm.rotateAngleX = MathHelper.cos(limbSwing * 0.5552F + (float)Math.PI) * 0.6F * limbSwingAmount;
		this.left_mec_arm.rotateAngleX = MathHelper.cos(limbSwing * 0.5552F) * 0.6F * limbSwingAmount;

		super.setLivingAnimations(entityIn, limbSwing, limbSwingAmount, partialTick);
	}
}