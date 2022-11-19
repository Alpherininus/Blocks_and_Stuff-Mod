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
public class NPCOtherRenderer extends MobRenderer<NPCEntity, NPCModel<NPCEntity>> {
    private static final ResourceLocation DEFAULT_NPC = new ResourceLocation(Basmod.MOD_ID, "textures/entity/npc_types/npc_type.png");

    private static final ResourceLocation HEAD_PHONES_A = new ResourceLocation(Basmod.MOD_ID,"textures/entity/npc_types/hat_types/head_phones_a.png");
    private static final ResourceLocation HEAD_PHONES_B = new ResourceLocation(Basmod.MOD_ID,"textures/entity/npc_types/hat_types/head_phones_b.png");
    private static final ResourceLocation HEAD_PHONES_C = new ResourceLocation(Basmod.MOD_ID,"textures/entity/npc_types/hat_types/head_phones_c.png");
    private static final ResourceLocation HEAD_PHONES_D = new ResourceLocation(Basmod.MOD_ID,"textures/entity/npc_types/hat_types/head_phones_d.png");
    private static final ResourceLocation HEAD_PHONES_E = new ResourceLocation(Basmod.MOD_ID,"textures/entity/npc_types/hat_types/head_phones_e.png");

    private static final ResourceLocation NPC_GUNTER = new ResourceLocation(Basmod.MOD_ID,"textures/entity/npc_types/gunter.png");

    public NPCOtherRenderer(EntityRendererManager renderManagerIn) {
        super(renderManagerIn, new NPCModel<>(), 0.5F);
    }

    @Override
    public ResourceLocation getEntityTexture(NPCEntity entity) {
        String str = TextFormatting.getTextWithoutFormattingCodes(entity.getName().getString());

        if (str != null && "Gunter".equals(str)) {
            return NPC_GUNTER;

        } else {
            switch (entity.getNPCEntityTypeHats()) {
                case 0:
                default:
                    return DEFAULT_NPC;

                case 1:
                    return HEAD_PHONES_A;
                case 2:
                    return HEAD_PHONES_B;
                case 3:
                    return HEAD_PHONES_C;
                case 4:
                    return HEAD_PHONES_D;
                case 5:
                    return HEAD_PHONES_E;
            }
        }
    }
}
