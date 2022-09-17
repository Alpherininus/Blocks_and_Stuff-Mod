package com.alpherininus.basmod.client.events;

import com.alpherininus.basmod.Basmod;
import com.alpherininus.basmod.client.entity.BasmodPlayerEntity;
import com.alpherininus.basmod.core.init.ItemInit;
import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.GuiOpenEvent;
import net.minecraftforge.client.event.GuiScreenEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Basmod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class BasmodGameEvents {

    protected static final ResourceLocation MANA_BARS = new ResourceLocation(Basmod.MOD_ID, "textures/gui/hud/mana_bar.png");

    private static final Minecraft mc = Minecraft.getInstance();
    
    private static final BasmodPlayerEntity basmodplayer = null;
    private static final LivingEntity user = null;
    private static final ServerPlayerEntity serverPlayer = ((ServerPlayerEntity) user);

    private static FontRenderer fontRenderer;

    @SubscribeEvent
    public static void onRenderGameOverlay(RenderGameOverlayEvent event) throws Exception {

        assert mc.player != null;
        ItemStack mainhand = mc.player.getItemStackFromSlot(EquipmentSlotType.MAINHAND);
        ItemStack offhand = mc.player.getItemStackFromSlot(EquipmentSlotType.OFFHAND);
        ItemStack head = mc.player.getItemStackFromSlot(EquipmentSlotType.HEAD);
        ItemStack chest = mc.player.getItemStackFromSlot(EquipmentSlotType.CHEST);
        ItemStack leg = mc.player.getItemStackFromSlot(EquipmentSlotType.LEGS);
        ItemStack feet = mc.player.getItemStackFromSlot(EquipmentSlotType.FEET);

        boolean ignoreGamemode = !mc.player.abilities.isCreativeMode && !mc.player.isSpectator();
        boolean notIgnoreGamemode = mc.player.abilities.isCreativeMode && !mc.player.isSpectator();

        boolean isNotInWater = !mc.player.isInWater();
        boolean isInWater = mc.player.isInWater();

        boolean modifiExperience = !event.isCancelable() && event.getType() == RenderGameOverlayEvent.ElementType.EXPERIENCE;

        if (isNotInWater) {
            if (modifiExperience) {
                if (ignoreGamemode) {

                    //int posXWidth = event.getWindow().getScaledWidth() / 2 + 10;
                    //int posYHeight = event.getWindow().getScaledHeight() - 48;

                    int posXWidth = event.getWindow().getScaledWidth() / 2 + 10;
                    int posYHeight = event.getWindow().getScaledHeight() - 49;

                    int textureWidth = 90;
                    int textureHeight = 9;

                    assert false;
                    // TODO barWidth => refill Manabar, default is 88 -> 8, 16, 24, 32, 40, 48, 56, 64, 72, 80, 88. / ( 22 = 25% / 44 = 50% / 66 = 75% / 88 = 100% )

                    int barWidth = 8;
                    int barHeight = 7;

                    mc.getTextureManager().bindTexture(MANA_BARS);

                    // TODO Manabar Outline
                    mc.ingameGUI.blit(new MatrixStack(), posXWidth, posYHeight, 0, 0, textureWidth, textureHeight);

                    // TODO Manabar
                    mc.ingameGUI.blit(new MatrixStack(), posXWidth + 1, posYHeight + 1, 0, 9, barWidth, barHeight);

                }
            }
        }
        // TODO Eqip Items
        if (mainhand.getItem() == ItemInit.ANIMATED_MAGICAL_STAFF.get()) {

            if (modifiExperience) {
                if (ignoreGamemode) {

                    int posXWidth = event.getWindow().getScaledWidth() / 2 + 10;
                    int posYHeight = event.getWindow().getScaledHeight() - 59;

                    int textureWidth = 90;
                    int textureHeight = 9;

                    int barWidth = 88; // TODO barWidth => refill Manabar, default is 88 -> 4, 8, 12, 16, 20, 24, 28, 32, 36, 40, 44, 48, 52, 56, 60, 64, 68, 72, 76, 80, 84, 88.
                    int barHeight = 7;

                    mc.getTextureManager().bindTexture(MANA_BARS);

                    // TODO Manabar Outline
                    mc.ingameGUI.blit(new MatrixStack(), posXWidth, posYHeight, 0, 18, textureWidth, textureHeight);

                    // TODO Manabar
                    mc.ingameGUI.blit(new MatrixStack(), posXWidth + 1, posYHeight + 1, 0, 28, barWidth, barHeight);

                }

            }
        }

        if (mainhand.getItem() == ItemInit.ANIMATED_HEAL_STAFF.get()) {
            if (modifiExperience) {
                if (ignoreGamemode) {

                    int posXWidth = event.getWindow().getScaledWidth() / 2 + 10;
                    int posYHeight = event.getWindow().getScaledHeight() - 59;

                    int textureWidth = 90;
                    int textureHeight = 9;

                    int barWidth = 88; // TODO barWidth => refill Manabar, default is 88 -> 4, 8, 12, 16, 20, 24, 28, 32, 36, 40, 44, 48, 52, 56, 60, 64, 68, 72, 76, 80, 84, 88.
                    int barHeight = 7;

                    mc.getTextureManager().bindTexture(MANA_BARS);

                    // TODO Manabar Outline
                    mc.ingameGUI.blit(new MatrixStack(), posXWidth, posYHeight, 0, 36, textureWidth, textureHeight);

                    // TODO Manabar
                    mc.ingameGUI.blit(new MatrixStack(), posXWidth + 1, posYHeight + 1, 0, 46, barWidth, barHeight);

                }
            }
        }

        // TODO When Player is Sleeping.
        if (mc.player.isSleeping()) {
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

