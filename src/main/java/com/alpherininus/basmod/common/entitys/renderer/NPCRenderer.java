package com.alpherininus.basmod.common.entitys.renderer;

import com.alpherininus.basmod.Basmod;
import com.alpherininus.basmod.common.entitys.NPCEntity;
import com.alpherininus.basmod.common.entitys.model.NPCModel;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.RabbitRenderer;
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
    private static final ResourceLocation NPC_CORINNA = new ResourceLocation(Basmod.MOD_ID, "textures/entity/npc_types/corinna.png");


    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public NPCRenderer(EntityRendererManager renderManagerIn) {
        super(renderManagerIn, new NPCModel<>(), 0.5F);

    }

    @Override
    public ResourceLocation getEntityTexture(NPCEntity entity) {
        final ResourceLocation[] myNPC = {DEFAULT_NPC, NPC_GUNTER, NPC_FLADIMIR, NPC_OLAF,
                NPC_FRANK, NPC_JONNY, NPC_XANDER, NPC_RUDOLF, NPC_MAX, NPC_STRAFNUR,
                NPC_TIMMY, NPC_ROMEY, NPC_LEO, NPC_FRANZ, NPC_SEV, NPC_OLI, NPC_STEFAN,
                NPC_JAKOB, NPC_LUCA, NPC_LUKAS, NPC_NICO, NPC_PHILLIP, NPC_THEODOR,
                NPC_JACK, NPC_LU, NPC_SOPHI, NPC_MIST, NPC_ASUKA, NPC_KURATA, NPC_SOLEIL,
                NPC_TITANIA, NPC_ANNA, NPC_ERIKA, NPC_HISOKA, NPC_AIMI, NPC_RYOKO, NPC_MEI,
                NPC_ANNE, NPC_MI, NPC_LUCY, NPC_NICOLLE, NPC_NORA, NPC_INGRID, NPC_ELLA,
                NPC_HANA, NPC_ISABELLA, NPC_SCARLETT, NPC_AMY, NPC_TIM, NPC_FINN, NPC_KIRSTAN,
                NPC_CORINNA
        };

        String txt = TextFormatting.getTextWithoutFormattingCodes(entity.getName().getString());
        return myNPC[entity.getNPCEntityType()];
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////

}
