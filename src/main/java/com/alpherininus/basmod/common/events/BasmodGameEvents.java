package com.alpherininus.basmod.common.events;

import com.alpherininus.basmod.Basmod;
import com.alpherininus.basmod.core.init.ItemInit;
import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.vector.Quaternion;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Basmod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class BasmodGameEvents {

    @SubscribeEvent
    public static void onRenderGameOverlay(RenderGameOverlayEvent event) {
        Minecraft mc = Minecraft.getInstance();
        assert mc.player != null;

        ItemStack mainhand = mc.player.getItemStackFromSlot(EquipmentSlotType.MAINHAND);

        boolean ignoreGamemode = !mc.player.abilities.isCreativeMode && !mc.player.isSpectator();

        boolean modifiExperience = !event.isCancelable() && event.getType() == RenderGameOverlayEvent.ElementType.EXPERIENCE;
        boolean modifiPotionIcon = !event.isCancelable() && event.getType() == RenderGameOverlayEvent.ElementType.POTION_ICONS;

        if (modifiExperience) {
            if (ignoreGamemode) {

                //int posXWidth = event.getWindow().getScaledWidth() / 2 + 10;
                //int posYHeight = event.getWindow().getScaledHeight() - 48;

                int posXWidth = event.getWindow().getScaledWidth() / 2 + 10;
                int posYHeight = event.getWindow().getScaledHeight() - 49;

                int textureWidth = 90;
                int textureHeight = 9;

                int barWidth = 69; // TODO barWidth => refill Manabar, default is 88 -> 4, 8, 12, 16, 20, 24, 28, 32, 36, 40, 44, 48, 52, 56, 60, 64, 68, 72, 76, 80, 84, 88.
                int barHeight = 7;

                mc.getTextureManager().bindTexture(new ResourceLocation(Basmod.MOD_ID, "textures/gui/hud/mana_bar.png"));

                // TODO Manabar Outline
                mc.ingameGUI.blit(new MatrixStack(),posXWidth, posYHeight, 0, 0, textureWidth, textureHeight);

                // TODO Manabar
                mc.ingameGUI.blit(new MatrixStack(),posXWidth + 1, posYHeight + 1, 0, 9, barWidth, barHeight);

            }

        }

        if (mainhand.getItem() == ItemInit.ANIMATED_MAGICAL_STAFF.get()) {
            if (modifiExperience) {
                if (ignoreGamemode) {

                    int posXWidth = event.getWindow().getScaledWidth() / 2 + 10;
                    int posYHeight = event.getWindow().getScaledHeight() - 59;

                    int textureWidth = 90;
                    int textureHeight = 9;

                    int barWidth = 88; // TODO barWidth => refill Manabar, default is 88 -> 4, 8, 12, 16, 20, 24, 28, 32, 36, 40, 44, 48, 52, 56, 60, 64, 68, 72, 76, 80, 84, 88.
                    int barHeight = 7;

                    mc.getTextureManager().bindTexture(new ResourceLocation(Basmod.MOD_ID, "textures/gui/hud/mana_bar.png"));

                    // TODO Manabar Outline
                    mc.ingameGUI.blit(new MatrixStack(),posXWidth, posYHeight, 0, 18, textureWidth, textureHeight);

                    // TODO Manabar
                    mc.ingameGUI.blit(new MatrixStack(),posXWidth + 1, posYHeight + 1, 0, 28, barWidth, barHeight);

                }

            }
        }


    }

}

