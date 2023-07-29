package com.alpherininus.basmod.common.entitys.renderer;

import com.alpherininus.basmod.Basmod;
import com.alpherininus.basmod.common.entitys.GodrickEntity;
import com.alpherininus.basmod.common.entitys.model.GodrickModel;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.entity.MobEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class GodrickRenderer extends MobRenderer<GodrickEntity, GodrickModel<GodrickEntity>> {

    protected static final ResourceLocation TEXTURES =
            new ResourceLocation(Basmod.MOD_ID, "textures/entity/godrick/godrick_txt.png");

    public GodrickRenderer(EntityRendererManager renderManagerIn) {
        super(renderManagerIn, new GodrickModel<>(), 1.9F);
    }

    @Override
    public ResourceLocation getEntityTexture(GodrickEntity entity) {
        return TEXTURES;
    }

}
