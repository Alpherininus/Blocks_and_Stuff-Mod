package com.alpherininus.basmod.core.util;

import com.alpherininus.basmod.Basmod;
import com.alpherininus.basmod.core.init.ItemInit;
import net.minecraft.block.AbstractBannerBlock;
import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.client.util.InputMappings;
import net.minecraft.item.ItemStack;
import net.minecraft.item.UseAction;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.client.settings.KeyConflictContext;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import org.lwjgl.glfw.GLFW;

import java.awt.event.KeyEvent;

@OnlyIn(Dist.CLIENT)
public class BasmodKeyBinding {

    public static final String DEFAULT_KEY_CATEGORY = "key.category.basmod.default";

    public static final String KEY_DRINK_WATER = "key.basmod.drink_water";
    public static final String KEYBIND_N = "key.basmod.key_n";
    public static final String KEYBIND_M = "key.basmod.key_m";

    public static final String KEYBIND_MOUSE3 = "key.basmod.key_mouse3";
    public static final String KEYBIND_MOUSE4 = "key.basmod.key_mouse4";


    public static final KeyBinding DRINKING_KEY = new KeyBinding(KEY_DRINK_WATER, KeyConflictContext.IN_GAME,
            InputMappings.Type.KEYSYM, GLFW.GLFW_KEY_X, DEFAULT_KEY_CATEGORY);

    public static final KeyBinding KEY_N = new KeyBinding(KEYBIND_N, KeyConflictContext.IN_GAME,
            InputMappings.Type.KEYSYM, GLFW.GLFW_KEY_N, DEFAULT_KEY_CATEGORY);

    public static final KeyBinding KEY_M = new KeyBinding(KEYBIND_M, KeyConflictContext.IN_GAME,
            InputMappings.Type.KEYSYM, GLFW.GLFW_KEY_M, DEFAULT_KEY_CATEGORY);

    public static final KeyBinding KEY_MOUSE3 = new KeyBinding(KEYBIND_MOUSE3, KeyConflictContext.IN_GAME,
            InputMappings.Type.MOUSE, GLFW.GLFW_MOUSE_BUTTON_4, DEFAULT_KEY_CATEGORY); // 3

    public static final KeyBinding KEY_MOUSE4 = new KeyBinding(KEYBIND_MOUSE4, KeyConflictContext.IN_GAME,
            InputMappings.Type.MOUSE, GLFW.GLFW_MOUSE_BUTTON_5, DEFAULT_KEY_CATEGORY); // 4

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    @Mod.EventBusSubscriber(modid = Basmod.MOD_ID, value = Dist.CLIENT)
    public static class ClientForgeEvents {
        @SubscribeEvent
        public static void onKeyInput(InputEvent.KeyInputEvent event) {
            if (BasmodKeyBinding.DRINKING_KEY.isPressed()) {
                Minecraft.getInstance().player.sendChatMessage("Pressed a X Key!");
            }

            if (BasmodKeyBinding.KEY_M.isPressed()) {
                Minecraft.getInstance().player.sendChatMessage("Pressed a M Key!");
            }

            if (BasmodKeyBinding.KEY_N.isPressed()) {
                Minecraft.getInstance().player.sendChatMessage("Pressed a N Key!");
            }
        }

        @SubscribeEvent
        public static void onMouseInput(InputEvent.MouseInputEvent event) {
            if (BasmodKeyBinding.KEY_MOUSE3.isPressed()) {

                ItemStack itemStack = null;

                if (itemStack.getItem().equals(ItemInit.ASUKA_AXT.get())) {

                    UseAction block = UseAction.BLOCK;
                } else {
                    Minecraft.getInstance().player.sendChatMessage("Pressed a MOUSE 4!");

                }
            }


            if (BasmodKeyBinding.KEY_MOUSE4.isPressed()) {
                Minecraft.getInstance().player.sendChatMessage("Pressed a MOUSE 5!");
            }
        }
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    @Mod.EventBusSubscriber(modid = Basmod.MOD_ID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
    public static class ClientModBusEvents {
        @SubscribeEvent
        public static void onKeyRegister(FMLClientSetupEvent event) {
            ClientRegistry.registerKeyBinding(BasmodKeyBinding.DRINKING_KEY); // X
            ClientRegistry.registerKeyBinding(BasmodKeyBinding.KEY_M); // M
            ClientRegistry.registerKeyBinding(BasmodKeyBinding.KEY_N); // N
            ClientRegistry.registerKeyBinding(BasmodKeyBinding.KEY_MOUSE3); // Button 4
            ClientRegistry.registerKeyBinding(BasmodKeyBinding.KEY_MOUSE4); // Button 5


        }
    }

}
