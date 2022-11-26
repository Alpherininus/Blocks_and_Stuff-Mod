package com.alpherininus.basmod.client.events;

import com.alpherininus.basmod.Basmod;
import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.GuiOpenEvent;
import net.minecraftforge.client.event.GuiScreenEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Basmod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE, value = Dist.CLIENT)
public class BasmodGameEvents {

    protected static final ResourceLocation MANA_BARS = new ResourceLocation(Basmod.MOD_ID, "textures/gui/hud/mana_bar.png");

    private static final Minecraft minecraft = Minecraft.getInstance();
    private FontRenderer fontRenderer;

    @SubscribeEvent
    public static void onRenderGameOverlay(RenderGameOverlayEvent event) throws Exception {

        assert minecraft.player != null;
        boolean ignoreGamemode = !minecraft.player.abilities.isCreativeMode && !minecraft.player.isSpectator();
        boolean notIgnoreGamemode = minecraft.player.abilities.isCreativeMode && !minecraft.player.isSpectator();

        boolean isNotInWater = !minecraft.player.isInWater();
        boolean isInWater = minecraft.player.isInWater();

        boolean modifiExperience = !event.isCancelable() && event.getType() == RenderGameOverlayEvent.ElementType.EXPERIENCE;

        if (isNotInWater) {
            if (modifiExperience) {
                if (ignoreGamemode) {

                    // int posXWidth = event.getWindow().getScaledWidth() / 2 + 10;
                    // int posYHeight = event.getWindow().getScaledHeight() - 48;

                    int posXWidth = event.getWindow().getScaledWidth() / 2 + 10;
                    int posYHeight = event.getWindow().getScaledHeight() - 49;

                    int textureWidth = 90;
                    int textureHeight = 9;

                    // TODO barWidth => refill Manabar, default is 88 -> 8, 16, 24, 32, 40, 48, 56, 64, 72, 80, 88. / ( 22 = 25% / 44 = 50% / 66 = 75% / 88 = 100% )

                    assert false;
                    int barWidth = 8;
                    int barHeight = 7;

                    minecraft.getTextureManager().bindTexture(MANA_BARS);

                    // TODO Manabar Outline
                    minecraft.ingameGUI.blit(new MatrixStack(), posXWidth, posYHeight, 0, 0, textureWidth, textureHeight);

                    // TODO Manabar
                    minecraft.ingameGUI.blit(new MatrixStack(), posXWidth + 1, posYHeight + 1, 0, 9, barWidth, barHeight);

                }
            }
        }

        // TODO When Player is Sleeping.
        if (minecraft.player.isSleeping()) {
            event.getWindow().setWindowTitle("Good Night :D!");
        }

    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    @SubscribeEvent
    public void onGuiOpened(GuiOpenEvent event) {

    }

    @SubscribeEvent
    public void onGuiScreened(GuiScreenEvent event) {

    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////

}

