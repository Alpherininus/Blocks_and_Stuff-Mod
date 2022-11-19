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

    private static final ResourceLocation KILLER_GUENTER = new ResourceLocation(Basmod.MOD_ID,"textures/entity/npc_types/guenter.png");
    private static final ResourceLocation KILLER_WALTER = new ResourceLocation(Basmod.MOD_ID,"textures/entity/npc_types/walter.png");

    private static final ResourceLocation NPC_GUNTER = new ResourceLocation(Basmod.MOD_ID,"textures/entity/npc_types/gunter.png");
    private static final ResourceLocation NPC_LU = new ResourceLocation(Basmod.MOD_ID,"textures/entity/npc_types/lu.png");
    private static final ResourceLocation NPC_SOPHI = new ResourceLocation(Basmod.MOD_ID,"textures/entity/npc_types/sophi.png");
    private static final ResourceLocation NPC_ESEL = new ResourceLocation(Basmod.MOD_ID,"textures/entity/npc_types/esel.png");
    private static final ResourceLocation NPC_FLADIMIR = new ResourceLocation(Basmod.MOD_ID,"textures/entity/npc_types/fladimir.png");
    private static final ResourceLocation NPC_OLAF = new ResourceLocation(Basmod.MOD_ID,"textures/entity/npc_types/olaf.png");
    private static final ResourceLocation NPC_FRANK = new ResourceLocation(Basmod.MOD_ID,"textures/entity/npc_types/frank.png");
    private static final ResourceLocation NPC_ASUKA = new ResourceLocation(Basmod.MOD_ID,"textures/entity/npc_types/asuka.png");
    private static final ResourceLocation NPC_KURATA = new ResourceLocation(Basmod.MOD_ID,"textures/entity/npc_types/kurata.png");
    private static final ResourceLocation NPC_JONNY = new ResourceLocation(Basmod.MOD_ID,"textures/entity/npc_types/jonny.png");


    public NPCRenderer(EntityRendererManager renderManagerIn) {
        super(renderManagerIn, new NPCModel<>(), 0.5F);
    }

    @Override
    public ResourceLocation getEntityTexture(NPCEntity entity) {
        String str = TextFormatting.getTextWithoutFormattingCodes(entity.getName().getString());

        if (str != null && "Gunter".equals(str)) {
            return NPC_GUNTER;

        } else if (str != null && "Esel".equals(str)) {
            return NPC_ESEL;

        } else {
            switch (entity.getNPCEntityType()) {
                case 0:
                default:
                    return DEFAULT_NPC;

                case 1:
                    return NPC_GUNTER;
                case 2:
                    return NPC_LU;
                case 3:
                    return NPC_SOPHI;
                case 4:
                    return NPC_FLADIMIR;
                case 5:
                    return NPC_FRANK;
                case 6:
                    return NPC_OLAF;
                case 7:
                    return NPC_KURATA;
                case 8:
                    return NPC_JONNY;
                case 9:
                case 10:
                case 11:
                case 12:
                case 13:
                case 14:
                case 15:
                case 16:
                case 17:
                case 18:
                case 19:
                case 20:
                case 21:
                case 22:
                case 23:
                case 24:
                case 25:
                case 26:
                case 27:
                case 28:
                case 29:
                case 30:
                case 31:
                case 32:
                case 33:
                case 34:
                case 35:
                case 36:
                case 37:
                case 39:
                case 40:
                case 41:
                case 42:
                    return NPC_ASUKA;
                case 43:
                case 44:
                case 45:
                case 46:
                case 47:
                case 48:
                case 49:
                case 50:
                case 51:
                case 52:
                case 53:
                case 54:
                case 55:
                case 56:
                case 57:
                case 58:
                case 59:
                case 60:
                case 61:
                case 62:
                case 63:
                case 64:
                case 65:
                case 66:
                case 67:
                case 68:
                case 69:
                case 70:
                case 71:
                case 72:
                case 73:
                case 74:
                case 75:
                case 76:
                case 77:
                case 78:
                case 79:
                case 80:
                case 81:
                case 82:
                case 83:
                case 84:
                case 85:
                case 86:
                case 87:
                case 88:
                case 89:
                case 90:
                case 91:
                case 92:
                case 93:
                case 94:
                case 95:
                case 96:
                case 97:

                case 98:
                    return KILLER_WALTER;
                case 99:
                    return KILLER_GUENTER;
            }
        }
    }
}
