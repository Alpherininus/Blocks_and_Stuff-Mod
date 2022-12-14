package com.alpherininus.basmod.client.events;

import com.alpherininus.basmod.Basmod;
import com.alpherininus.basmod.core.init.ItemInit;
import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.Minecraft;
import net.minecraft.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.client.event.RenderHandEvent;
import net.minecraftforge.client.event.RenderTooltipEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

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
