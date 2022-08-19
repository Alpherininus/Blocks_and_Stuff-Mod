package com.alpherininus.basmod.core.init;

import com.alpherininus.basmod.Basmod;
import net.minecraft.client.settings.KeyBinding;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

import java.awt.event.KeyEvent;

@OnlyIn(Dist.CLIENT)
public class KeybindsInit {

    public static KeyBinding eYKeyBinding;
    public static KeyBinding eXKeyBinding;
    public static KeyBinding eVKeyBinding;
    public static KeyBinding eNKeyBinding;
    public static KeyBinding eMKeyBinding;

    public KeybindsInit() {
    }

    public static void register(FMLClientSetupEvent event) {
        eYKeyBinding = create("keyY", KeyEvent.VK_Y);
        eXKeyBinding = create("keyX", KeyEvent.VK_X);
        eVKeyBinding = create("keyV", KeyEvent.VK_V);
        eNKeyBinding = create("keyN", KeyEvent.VK_N);
        eMKeyBinding = create("keyM", KeyEvent.VK_M);
        ClientRegistry.registerKeyBinding(eYKeyBinding);
        ClientRegistry.registerKeyBinding(eXKeyBinding);
        ClientRegistry.registerKeyBinding(eVKeyBinding);
        ClientRegistry.registerKeyBinding(eNKeyBinding);
        ClientRegistry.registerKeyBinding(eMKeyBinding);
    }

    private static KeyBinding create(String name, int key) {
        return new KeyBinding("key." + Basmod.MOD_ID + "." + name, key, "key.category.basmod");
    }
}
