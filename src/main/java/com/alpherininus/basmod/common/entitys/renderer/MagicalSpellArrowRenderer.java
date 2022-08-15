package com.alpherininus.basmod.common.entitys.renderer;

import com.alpherininus.basmod.Basmod;
import com.alpherininus.basmod.common.entitys.MagicalSpellArrowEntity;
import net.minecraft.client.renderer.entity.ArrowRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.util.ResourceLocation;

public class MagicalSpellArrowRenderer extends ArrowRenderer<MagicalSpellArrowEntity> {

    public static final ResourceLocation TEXTURE = new ResourceLocation(Basmod.MOD_ID, "textures/entity/magical_spell_arrow.png");


    public MagicalSpellArrowRenderer(EntityRendererManager renderManagerIn) {
        super(renderManagerIn);
    }

    @Override
    public ResourceLocation getEntityTexture(MagicalSpellArrowEntity entity) {
        return TEXTURE;
    }
}
