package com.alpherininus.basmod.client.events;

import com.alpherininus.basmod.Basmod;
import com.alpherininus.basmod.core.init.ItemInit;
import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.fonts.FontTexture;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.ITextProperties;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RenderHandEvent;
import net.minecraftforge.client.event.RenderTooltipEvent;
import net.minecraftforge.event.entity.player.PlayerDestroyItemEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.List;

@Mod.EventBusSubscriber(modid = Basmod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE, value = Dist.CLIENT)
public class BasmodClientEvents {

    @SubscribeEvent
    public static void renderHand(final RenderHandEvent event) {
        ItemStack itemStack = event.getItemStack();
        MatrixStack stack = event.getMatrixStack();
        int i = event.getLight();

        if (itemStack.getItem().equals(ItemInit.SOLEILS_SHINE.get())) {
            i = 10;
        }
    }

    @SubscribeEvent
    public static void renderTooltip(final RenderTooltipEvent event) {

    }

}
