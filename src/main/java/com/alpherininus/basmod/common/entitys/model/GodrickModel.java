package com.alpherininus.basmod.common.entitys.model;// Made with Blockbench 4.7.4
// Exported for Minecraft version 1.15 - 1.16 with MCP mappings
// Paste this class into your mod and generate all required imports


import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.CreatureEntity;
import net.minecraft.util.math.MathHelper;

public class GodrickModel<C extends CreatureEntity> extends EntityModel<C> {
	private final ModelRenderer head;
	private final ModelRenderer body;
	private final ModelRenderer right_arm;
	private final ModelRenderer left_arm;
	private final ModelRenderer jaw;
	private final ModelRenderer right_leg;
	private final ModelRenderer left_leg;

	public GodrickModel() {
		textureWidth = 128;
		textureHeight = 128;

		head = new ModelRenderer(this);
		head.setRotationPoint(0.0F, -7.0F, -4.0F);


		ModelRenderer head_r1 = new ModelRenderer(this);
		head_r1.setRotationPoint(0.0F, 0.0F, 0.0F);
		head.addChild(head_r1);
		setRotationAngle(head_r1, 1.5708F, 0.0F, 0.0F);
		head_r1.setTextureOffset(78, 60).addBox(-4.0F, -6.0F, -1.5F, 8.0F, 8.0F, 8.0F, 0.0F, false);

		body = new ModelRenderer(this);
		body.setRotationPoint(0.0F, 25.0F, 0.0F);
		body.setTextureOffset(0, 32).addBox(-9.0F, -34.0F, -6.0F, 18.0F, 12.0F, 11.0F, 0.0F, false);
		body.setTextureOffset(0, 32).addBox(-9.0F, -34.4F, -6.4F, 18.0F, 12.0F, 11.0F, 0.0F, false);
		body.setTextureOffset(0, 55).addBox(-8.0F, -33.0F, -5.0F, 16.0F, 14.0F, 9.0F, 0.0F, false);
		body.setTextureOffset(82, 36).addBox(-4.5F, -22.0F, -3.0F, 9.0F, 5.0F, 6.0F, 0.5F, false);

		right_arm = new ModelRenderer(this);
		right_arm.setRotationPoint(7.0F, -7.0F, 0.0F);
		right_arm.setTextureOffset(58, 84).addBox(-20.0F, -2.5F, -3.0F, 4.0F, 19.0F, 6.0F, 0.0F, false);

		ModelRenderer right_arm_r1 = new ModelRenderer(this);
		right_arm_r1.setRotationPoint(-18.0F, 0.0F, 0.0F);
		right_arm.addChild(right_arm_r1);
		setRotationAngle(right_arm_r1, -0.3054F, 0.0F, 2.0508F);
		right_arm_r1.setTextureOffset(100, 94).addBox(-3.0F, -2.5F, -3.0F, 4.0F, 17.0F, 3.0F, 0.0F, false);

		ModelRenderer right_arm_r2 = new ModelRenderer(this);
		right_arm_r2.setRotationPoint(-18.0F, 0.0F, 0.0F);
		right_arm.addChild(right_arm_r2);
		setRotationAngle(right_arm_r2, 0.4363F, 0.0F, 2.1817F);
		right_arm_r2.setTextureOffset(75, 106).addBox(-3.0F, -2.5F, 0.0F, 4.0F, 14.0F, 3.0F, 0.0F, false);

		ModelRenderer right_arm_r3 = new ModelRenderer(this);
		right_arm_r3.setRotationPoint(-18.0F, 0.0F, 0.0F);
		right_arm.addChild(right_arm_r3);
		setRotationAngle(right_arm_r3, 0.0F, 0.0F, -2.9234F);
		right_arm_r3.setTextureOffset(102, 47).addBox(-3.0F, -2.5F, 0.0F, 4.0F, 17.0F, 3.0F, 0.0F, false);

		ModelRenderer right_arm_r4 = new ModelRenderer(this);
		right_arm_r4.setRotationPoint(-15.0F, 13.0F, 0.0F);
		right_arm.addChild(right_arm_r4);
		setRotationAngle(right_arm_r4, 0.0077F, -0.1744F, 0.5666F);
		right_arm_r4.setTextureOffset(54, 60).addBox(4.6897F, 8.859F, -21.0F, 2.0F, 10.0F, 8.0F, 0.0F, false);
		right_arm_r4.setTextureOffset(30, 60).addBox(4.6897F, 12.859F, -13.0F, 2.0F, 2.0F, 20.0F, 0.0F, false);
		right_arm_r4.setTextureOffset(48, 0).addBox(-10.3103F, 14.859F, -21.0F, 13.0F, 2.0F, 8.0F, 0.0F, false);
		right_arm_r4.setTextureOffset(54, 62).addBox(-5.3103F, 14.859F, -13.0F, 2.0F, 2.0F, 20.0F, 0.0F, false);
		right_arm_r4.setTextureOffset(0, 96).addBox(-6.3103F, -1.141F, -1.0F, 4.0F, 19.0F, 4.0F, 0.0F, false);

		ModelRenderer right_arm_r5 = new ModelRenderer(this);
		right_arm_r5.setRotationPoint(-18.0F, 17.0F, 0.0F);
		right_arm.addChild(right_arm_r5);
		setRotationAngle(right_arm_r5, -0.0076F, -0.0869F, -0.0433F);
		right_arm_r5.setTextureOffset(100, 76).addBox(-2.0F, -1.5F, -3.0F, 4.0F, 14.0F, 4.0F, 0.0F, false);

		left_arm = new ModelRenderer(this);
		left_arm.setRotationPoint(-8.0F, -7.0F, 0.0F);
		left_arm.setTextureOffset(38, 82).addBox(17.0F, -2.5F, -3.0F, 4.0F, 20.0F, 6.0F, 0.0F, false);

		ModelRenderer left_arm_r1 = new ModelRenderer(this);
		left_arm_r1.setRotationPoint(21.0F, 0.0F, 0.0F);
		left_arm.addChild(left_arm_r1);
		setRotationAngle(left_arm_r1, 0.0F, 0.0F, 2.7925F);
		left_arm_r1.setTextureOffset(0, 0).addBox(0.0F, -2.5F, -1.0F, 4.0F, 12.0F, 4.0F, 0.0F, false);

		ModelRenderer left_arm_r2 = new ModelRenderer(this);
		left_arm_r2.setRotationPoint(21.0F, 0.0F, 0.0F);
		left_arm.addChild(left_arm_r2);
		setRotationAngle(left_arm_r2, 0.0F, 0.0F, -2.3562F);
		left_arm_r2.setTextureOffset(16, 96).addBox(0.0F, -2.5F, -3.0F, 4.0F, 16.0F, 4.0F, 0.0F, false);

		ModelRenderer left_arm2 = new ModelRenderer(this);
		left_arm2.setRotationPoint(19.4F, 13.7F, 0.0F);
		left_arm.addChild(left_arm2);
		setRotationAngle(left_arm2, 0.0F, -1.5708F, 1.5708F);
		left_arm2.setTextureOffset(104, 21).addBox(-5.0F, -18.0F, 5.0F, 2.0F, 4.0F, 6.0F, 0.0F, false);
		left_arm2.setTextureOffset(48, 10).addBox(-5.0F, -9.0F, -13.0F, 2.0F, 2.0F, 4.0F, 0.0F, false);
		left_arm2.setTextureOffset(60, 10).addBox(3.0F, -9.0F, -13.0F, 2.0F, 2.0F, 4.0F, 0.0F, false);
		left_arm2.setTextureOffset(105, 0).addBox(3.0F, -18.0F, 5.0F, 2.0F, 4.0F, 6.0F, 0.0F, false);
		left_arm2.setTextureOffset(0, 0).addBox(-8.0F, -14.0F, -1.0F, 16.0F, 16.0F, 16.0F, 0.0F, false);
		left_arm2.setTextureOffset(42, 39).addBox(-6.0F, -7.0F, -15.0F, 12.0F, 5.0F, 16.0F, 0.0F, false);

		jaw = new ModelRenderer(this);
		jaw.setRotationPoint(0.0F, 0.0F, 0.0F);
		left_arm2.addChild(jaw);


		ModelRenderer jaw_r1 = new ModelRenderer(this);
		jaw_r1.setRotationPoint(0.0F, 0.0F, 0.0F);
		jaw.addChild(jaw_r1);
		setRotationAngle(jaw_r1, 0.0436F, 0.0F, 0.0F);
		jaw_r1.setTextureOffset(48, 16).addBox(-6.0F, -2.0F, -15.0F, 12.0F, 4.0F, 16.0F, 0.0F, false);

		right_leg = new ModelRenderer(this);
		right_leg.setRotationPoint(3.0F, 8.0F, 0.0F);
		right_leg.setTextureOffset(88, 5).addBox(-9.5F, 0.0F, -3.0F, 6.0F, 17.0F, 5.0F, 0.0F, false);

		left_leg = new ModelRenderer(this);
		left_leg.setRotationPoint(-3.0F, 8.0F, 0.0F);
		left_leg.setTextureOffset(78, 84).addBox(3.5F, 0.0F, -3.0F, 6.0F, 17.0F, 5.0F, 0.0F, false);
	}

	@Override
	public void setRotationAngles(C entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch){
		//previously the render function, render code was moved to a method below
		this.head.rotateAngleX = headPitch * ((float)Math.PI / 180F);
		this.head.rotateAngleY = netHeadYaw * ((float)Math.PI / 180F);
		this.right_leg.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + (float)Math.PI) * 1.4F * limbSwingAmount;
		this.left_leg.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
		this.right_arm.rotateAngleX = MathHelper.cos(limbSwing * 0.5552F + (float)Math.PI) * 0.9F * limbSwingAmount;
		this.left_arm.rotateAngleX = MathHelper.cos(limbSwing * 0.5552F) * 0.9F * limbSwingAmount;

		this.jaw.rotateAngleZ = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;

	}

	@Override
	public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha){
		head.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
		body.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
		right_arm.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
		left_arm.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
		right_leg.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
		left_leg.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
	}

	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}
}