package com.alpherininus.basmod.common.events;

import com.alpherininus.basmod.Basmod;
import com.alpherininus.basmod.core.init.KeybindsInit;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


@Mod.EventBusSubscriber(modid = Basmod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE, value = Dist.CLIENT)
public class BasmodInputEvents {

    private static Logger LOGGER = getLOGGER();

    public BasmodInputEvents() {
    }

    public static Logger getLOGGER() {
        return LOGGER = LogManager.getLogger();
    }

    @SubscribeEvent
    public static void onKeyPress(InputEvent.KeyInputEvent event) {
        Minecraft mc = Minecraft.getInstance();
        if (mc.world != null) {
            onInput(mc, event.getKey(), event.getAction());
        }
    }

    @SubscribeEvent
    public static void onMouseClick(InputEvent.MouseInputEvent event) {
        Minecraft mc = Minecraft.getInstance();
        if (mc.world != null) {
            onInput(mc, event.getButton(), event.getAction());
        }
    }

    private static void onInput(Minecraft mc, int key, int action) {
        PlayerEntity playerIn = null;
        if (mc.currentScreen == null && KeybindsInit.eMKeyBinding.isPressed()) {
            // BasmodNetwork.SIMPLE_CHANNEL.sendToServer(new InputMessage(key));

        }

        if (mc.currentScreen == null && KeybindsInit.eNKeyBinding.isPressed()) {
        }

        if (mc.currentScreen == null && KeybindsInit.eYKeyBinding.isPressed()) {
            LOGGER.fatal("\n\n\n########\nINFO || Y Y Y Y Y Y Y Y Y Y Y Y Y ");
        }

        if (mc.currentScreen == null && KeybindsInit.eXKeyBinding.isPressed()) {
        }

        if (mc.currentScreen == null && KeybindsInit.eVKeyBinding.isPressed()) {
        }

    }
}
