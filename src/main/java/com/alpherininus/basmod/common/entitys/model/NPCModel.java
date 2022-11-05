package com.alpherininus.basmod.common.entitys.model;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.CreatureEntity;
import net.minecraft.util.math.MathHelper;

public class NPCModel<C extends CreatureEntity> extends EntityModel<C> {
	private final ModelRenderer head;
	private final ModelRenderer body;
	private final ModelRenderer left_arm;
	private final ModelRenderer right_arm;
	private final ModelRenderer left_leg;
	private final ModelRenderer right_leg;

	public NPCModel() {
		textureWidth = 64;
		textureHeight = 64;

		head = new ModelRenderer(this);
		head.setRotationPoint(0.0F, 0.0F, 0.0F);
		head.setTextureOffset(0, 0).addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, 0.0F, false);

		ModelRenderer head2 = new ModelRenderer(this);
		head2.setRotationPoint(0.0F, 0.0F, 0.0F);
		head.addChild(head2);
		head2.setTextureOffset(24, 0).addBox(3.0F, -9.0F, 3.0F, 2.0F, 2.0F, 2.0F, 0.0F, false);
		head2.setTextureOffset(24, 4).addBox(-5.0F, -9.0F, 3.0F, 2.0F, 2.0F, 2.0F, 0.0F, false);
		head2.setTextureOffset(32, 5).addBox(-6.0F, -8.0F, 3.0F, 3.0F, 9.0F, 2.0F, 0.0F, true);
		head2.setTextureOffset(32, 5).addBox(3.0F, -8.0F, 3.0F, 3.0F, 9.0F, 2.0F, 0.0F, true);

		ModelRenderer head3 = new ModelRenderer(this);
		head3.setRotationPoint(0.0F, 0.0F, 0.0F);
		head.addChild(head3);
		head3.setTextureOffset(46, 34).addBox(0.0F, -13.0F, -3.0F, 0.0F, 9.0F, 9.0F, 0.0F, false);

		ModelRenderer head4 = new ModelRenderer(this);
		head4.setRotationPoint(0.0F, 0.0F, 0.0F);
		head.addChild(head4);
		head4.setTextureOffset(0, 32).addBox(-4.5F, -8.5F, -1.0F, 9.0F, 5.0F, 2.0F, 0.0F, false);
		head4.setTextureOffset(0, 40).addBox(-5.0F, -6.0F, -1.5F, 10.0F, 3.0F, 3.0F, 0.0F, false);

		ModelRenderer head5 = new ModelRenderer(this);
		head5.setRotationPoint(0.0F, 0.0F, 0.0F);
		head.addChild(head5);
		head5.setTextureOffset(0, 0).addBox(2.0F, -13.0F, -1.0F, 3.0F, 6.0F, 0.0F, 0.0F, false);
		head5.setTextureOffset(0, 0).addBox(-5.0F, -13.0F, -1.0F, 3.0F, 6.0F, 0.0F, 0.0F, false);

		body = new ModelRenderer(this);
		body.setRotationPoint(0.0F, 0.0F, 0.0F);
		body.setTextureOffset(16, 16).addBox(-4.0F, 0.0F, -2.0F, 8.0F, 12.0F, 4.0F, 0.0F, false);

		left_arm = new ModelRenderer(this);
		left_arm.setRotationPoint(-6.0F, 2.0F, 0.0F);
		left_arm.setTextureOffset(32, 48).addBox(10.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, 0.0F, false);

		right_arm = new ModelRenderer(this);
		right_arm.setRotationPoint(6.0F, 2.0F, 0.0F);
		right_arm.setTextureOffset(40, 16).addBox(-14.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, 0.0F, false);

		left_leg = new ModelRenderer(this);
		left_leg.setRotationPoint(-2.0F, 12.0F, 0.0F);
		left_leg.setTextureOffset(16, 48).addBox(2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, 0.0F, false);

		right_leg = new ModelRenderer(this);
		right_leg.setRotationPoint(2.0F, 12.0F, 0.0F);
		right_leg.setTextureOffset(0, 16).addBox(-6.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, 0.0F, false);
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
		right_arm.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
		left_leg.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
		right_leg.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
	}

	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}
}