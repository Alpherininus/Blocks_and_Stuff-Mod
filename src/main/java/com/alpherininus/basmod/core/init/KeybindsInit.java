package com.alpherininus.basmod.core.init;

import net.minecraft.client.settings.KeyBinding;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

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
        eYKeyBinding = create("keyY", 89);
        eXKeyBinding = create("keyX", 88);
        eVKeyBinding = create("keyV", 86);
        eNKeyBinding = create("keyN", 78);
        eMKeyBinding = create("keyM", 77);
        ClientRegistry.registerKeyBinding(eYKeyBinding);
        ClientRegistry.registerKeyBinding(eXKeyBinding);
        ClientRegistry.registerKeyBinding(eVKeyBinding);
        ClientRegistry.registerKeyBinding(eNKeyBinding);
        ClientRegistry.registerKeyBinding(eMKeyBinding);
    }

    private static KeyBinding create(String name, int key) {
        return new KeyBinding("key.basmod." + name, key, "key.category.basmod");
    }
}
