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

    private static final ResourceLocation NPC_GUNTER = new ResourceLocation(Basmod.MOD_ID, "textures/entity/npc_types/gunter.png");
    private static final ResourceLocation NPC_FLADIMIR = new ResourceLocation(Basmod.MOD_ID, "textures/entity/npc_types/fladimir.png");
    private static final ResourceLocation NPC_OLAF = new ResourceLocation(Basmod.MOD_ID, "textures/entity/npc_types/olaf.png");
    private static final ResourceLocation NPC_FRANK = new ResourceLocation(Basmod.MOD_ID, "textures/entity/npc_types/frank.png");
    private static final ResourceLocation NPC_JONNY = new ResourceLocation(Basmod.MOD_ID, "textures/entity/npc_types/jonny.png");
    private static final ResourceLocation NPC_XANDER = new ResourceLocation(Basmod.MOD_ID, "textures/entity/npc_types/xander.png");
    private static final ResourceLocation NPC_RUDOLF = new ResourceLocation(Basmod.MOD_ID, "textures/entity/npc_types/rudolf.png");
    private static final ResourceLocation NPC_MAX = new ResourceLocation(Basmod.MOD_ID, "textures/entity/npc_types/max.png");
    private static final ResourceLocation NPC_STRAFNUR = new ResourceLocation(Basmod.MOD_ID, "textures/entity/npc_types/strafnur.png");
    private static final ResourceLocation NPC_TIMMY = new ResourceLocation(Basmod.MOD_ID, "textures/entity/npc_types/timmy.png");
    private static final ResourceLocation NPC_ROMEY = new ResourceLocation(Basmod.MOD_ID, "textures/entity/npc_types/romey.png");
    private static final ResourceLocation NPC_LEO = new ResourceLocation(Basmod.MOD_ID, "textures/entity/npc_types/leo.png");
    private static final ResourceLocation NPC_FRANZ = new ResourceLocation(Basmod.MOD_ID, "textures/entity/npc_types/franz.png");
    private static final ResourceLocation NPC_SEV = new ResourceLocation(Basmod.MOD_ID, "textures/entity/npc_types/sev.png");
    private static final ResourceLocation NPC_OLI = new ResourceLocation(Basmod.MOD_ID, "textures/entity/npc_types/oli.png");
    private static final ResourceLocation NPC_STEFAN = new ResourceLocation(Basmod.MOD_ID, "textures/entity/npc_types/stefan.png");
    private static final ResourceLocation NPC_JAKOB = new ResourceLocation(Basmod.MOD_ID, "textures/entity/npc_types/jakob.png");
    private static final ResourceLocation NPC_LUCA = new ResourceLocation(Basmod.MOD_ID, "textures/entity/npc_types/luca.png");
    private static final ResourceLocation NPC_LUKAS = new ResourceLocation(Basmod.MOD_ID, "textures/entity/npc_types/lukas.png");
    private static final ResourceLocation NPC_NICO = new ResourceLocation(Basmod.MOD_ID, "textures/entity/npc_types/nico.png");
    private static final ResourceLocation NPC_PHILLIP = new ResourceLocation(Basmod.MOD_ID, "textures/entity/npc_types/phillip.png");
    private static final ResourceLocation NPC_THEODOR = new ResourceLocation(Basmod.MOD_ID, "textures/entity/npc_types/theodor.png");
    private static final ResourceLocation NPC_JACK = new ResourceLocation(Basmod.MOD_ID, "textures/entity/npc_types/jack.png");
    private static final ResourceLocation NPC_LU = new ResourceLocation(Basmod.MOD_ID, "textures/entity/npc_types/lu.png");
    private static final ResourceLocation NPC_SOPHI = new ResourceLocation(Basmod.MOD_ID, "textures/entity/npc_types/sophi.png");
    private static final ResourceLocation NPC_MIST = new ResourceLocation(Basmod.MOD_ID, "textures/entity/npc_types/mist.png");
    private static final ResourceLocation NPC_ASUKA = new ResourceLocation(Basmod.MOD_ID, "textures/entity/npc_types/asuka.png");
    private static final ResourceLocation NPC_KURATA = new ResourceLocation(Basmod.MOD_ID, "textures/entity/npc_types/kurata.png");
    private static final ResourceLocation NPC_SOLEIL = new ResourceLocation(Basmod.MOD_ID, "textures/entity/npc_types/soleil.png");
    private static final ResourceLocation NPC_TITANIA = new ResourceLocation(Basmod.MOD_ID, "textures/entity/npc_types/titania.png");
    private static final ResourceLocation NPC_ANNA = new ResourceLocation(Basmod.MOD_ID, "textures/entity/npc_types/anna.png");
    private static final ResourceLocation NPC_ERIKA = new ResourceLocation(Basmod.MOD_ID, "textures/entity/npc_types/erika.png");
    private static final ResourceLocation NPC_HISOKA = new ResourceLocation(Basmod.MOD_ID, "textures/entity/npc_types/hisoka.png");
    private static final ResourceLocation NPC_AIMI = new ResourceLocation(Basmod.MOD_ID, "textures/entity/npc_types/aimi.png");
    private static final ResourceLocation NPC_RYOKO = new ResourceLocation(Basmod.MOD_ID, "textures/entity/npc_types/ryoko.png");
    private static final ResourceLocation NPC_MEI = new ResourceLocation(Basmod.MOD_ID, "textures/entity/npc_types/mei.png");
    private static final ResourceLocation NPC_ANNE = new ResourceLocation(Basmod.MOD_ID, "textures/entity/npc_types/anne.png");
    private static final ResourceLocation NPC_MI = new ResourceLocation(Basmod.MOD_ID, "textures/entity/npc_types/mi.png");
    private static final ResourceLocation NPC_LUCY = new ResourceLocation(Basmod.MOD_ID, "textures/entity/npc_types/lucy.png");
    private static final ResourceLocation NPC_NICOLLE = new ResourceLocation(Basmod.MOD_ID, "textures/entity/npc_types/nicolle.png");
    private static final ResourceLocation NPC_NORA = new ResourceLocation(Basmod.MOD_ID, "textures/entity/npc_types/nora.png");
    private static final ResourceLocation NPC_INGRID = new ResourceLocation(Basmod.MOD_ID, "textures/entity/npc_types/ingrid.png");
    private static final ResourceLocation NPC_ELLA = new ResourceLocation(Basmod.MOD_ID, "textures/entity/npc_types/ella.png");
    private static final ResourceLocation NPC_HANA = new ResourceLocation(Basmod.MOD_ID, "textures/entity/npc_types/hana.png");
    private static final ResourceLocation NPC_ISABELLA = new ResourceLocation(Basmod.MOD_ID, "textures/entity/npc_types/isabella.png");
    private static final ResourceLocation NPC_SCARLETT = new ResourceLocation(Basmod.MOD_ID, "textures/entity/npc_types/scarlett.png");
    private static final ResourceLocation NPC_AMY = new ResourceLocation(Basmod.MOD_ID, "textures/entity/npc_types/amy.png");
    private static final ResourceLocation NPC_TIM = new ResourceLocation(Basmod.MOD_ID, "textures/entity/npc_types/tim.png");
    private static final ResourceLocation NPC_FINN = new ResourceLocation(Basmod.MOD_ID, "textures/entity/npc_types/finn.png");
    private static final ResourceLocation NPC_KIRSTAN = new ResourceLocation(Basmod.MOD_ID, "textures/entity/npc_types/kirstan.png");

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public NPCRenderer(EntityRendererManager renderManagerIn) {
        super(renderManagerIn, new NPCModel<>(), 0.5F);
    }

    @Override
    public ResourceLocation getEntityTexture(NPCEntity entity) {
        String s = TextFormatting.getTextWithoutFormattingCodes(entity.getName().getString());
        if (s != null && "Guenter".equals(s)) {
            return NPC_GUNTER;

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
                    return NPC_ANNE;
                case 10:
                    return NPC_AIMI;
                case 11:
                    return NPC_ERIKA;
                case 12:
                    return NPC_MAX;
                case 13:
                    return NPC_MEI;
                case 14:
                    return NPC_STRAFNUR;
                case 15:
                    return NPC_TIMMY;
                case 16:
                    return NPC_TITANIA;
                case 17:
                    return NPC_XANDER;
                case 18:
                    return NPC_MIST;
                case 19:
                    return NPC_SOLEIL;
                case 20:
                    return NPC_HISOKA;
                case 21:
                    return NPC_RUDOLF;
                case 22:
                    return NPC_ANNA;
                case 23:
                    return NPC_RYOKO;
                case 24:
                    return NPC_ROMEY;
                case 25:
                    return NPC_SCARLETT;
                case 26:
                    return NPC_SEV;
                case 27:
                    return NPC_THEODOR;
                case 28:
                    return NPC_LEO;
                case 29:
                    return NPC_LUCA;
                case 30:
                    return NPC_LUCY;
                case 31:
                    return NPC_LUKAS;
                case 32:
                    return NPC_STEFAN;
                case 33:
                    return NPC_PHILLIP;
                case 34:
                    return NPC_OLI;
                case 35:
                    return NPC_AMY;
                case 36:
                    return NPC_NICO;
                case 37:
                    return NPC_NICOLLE;
                case 38:
                    return NPC_NORA;
                case 39:
                    return NPC_TIM;
                case 40:
                    return NPC_JAKOB;
                case 41:
                    return NPC_JACK;
                case 42:
                    return NPC_ASUKA;
                case 43:
                    return NPC_ISABELLA;
                case 44:
                    return NPC_INGRID;
                case 45:
                    return NPC_HANA;
                case 46:
                    return NPC_FRANZ;
                case 47:
                    return NPC_MI;
                case 48:
                    return NPC_ELLA;
                case 49:
                    return NPC_FINN;
                case 50:
                    return NPC_KIRSTAN;
            }
        }
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////

}
