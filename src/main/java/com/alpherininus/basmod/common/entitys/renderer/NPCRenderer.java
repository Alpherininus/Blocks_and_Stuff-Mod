package com.alpherininus.basmod.common.entitys.renderer;

import com.alpherininus.basmod.Basmod;
import com.alpherininus.basmod.common.entitys.NPCEntity;
import com.alpherininus.basmod.common.entitys.model.NPCModel;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class NPCRenderer extends MobRenderer<NPCEntity, NPCModel<NPCEntity>> {
    private static final ResourceLocation DEFAULT_NPC = new ResourceLocation(Basmod.MOD_ID, "textures/entity/npc_types/npc_type.png");
    private static final ResourceLocation NPC_GUNTER = new ResourceLocation(Basmod.MOD_ID,"textures/entity/npc_types/gunter.png");
    private static final ResourceLocation NPC_LU = new ResourceLocation(Basmod.MOD_ID,"textures/entity/npc_types/lu.png");
    private static final ResourceLocation NPC_SOPHI = new ResourceLocation(Basmod.MOD_ID,"textures/entity/npc_types/sophi.png");
    private static final ResourceLocation NPC_ESEL = new ResourceLocation(Basmod.MOD_ID,"textures/entity/npc_types/esel.png");

    public NPCRenderer(EntityRendererManager renderManagerIn) {
        super(renderManagerIn, new NPCModel<>(), 0.5F);
    }

    @Override
    public ResourceLocation getEntityTexture(NPCEntity entity) {
        String str = TextFormatting.getTextWithoutFormattingCodes(entity.getName().getString());

        if (str != null && "Gunter".equals(str)) {
            return NPC_GUNTER;
        } else if (str != null && "Lu".equals(str)) {
            return NPC_LU;
        } else if (str != null && "Sophi".equals(str)) {
            return NPC_SOPHI;
        } else if (str != null && "Esel".equals(str)) {
            return NPC_ESEL;

        } else {
            switch (entity.getNPCEntityType()) {
                case 0:
                default:
                    return DEFAULT_NPC;
            }
        }
    }


}
