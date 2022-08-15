package com.alpherininus.basmod.common.entitys.renderer;

import com.alpherininus.basmod.Basmod;
import com.alpherininus.basmod.common.entitys.SeiorShellArmorEntity;
import com.alpherininus.basmod.common.entitys.model.SeieorShellModel;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;

public class SeieorShellRenderer extends MobRenderer<SeiorShellArmorEntity, SeieorShellModel<SeiorShellArmorEntity>> {

    protected static final ResourceLocation TEXTURES =
            new ResourceLocation(Basmod.MOD_ID, "textures/entity/texture.png");

    public SeieorShellRenderer(EntityRendererManager renderManagerIn) {
        super(renderManagerIn, new SeieorShellModel<>(), 0.9F);
    }

    @Override
    public ResourceLocation getEntityTexture(SeiorShellArmorEntity entity) {
        return TEXTURES;
    }

}
