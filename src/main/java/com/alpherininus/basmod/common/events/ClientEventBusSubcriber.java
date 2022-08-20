package com.alpherininus.basmod.common.events;

import com.alpherininus.basmod.Basmod;
import com.alpherininus.basmod.core.init.KeybindsInit;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@Mod.EventBusSubscriber(modid = Basmod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ClientEventBusSubcriber {

    @SubscribeEvent
    public static void clientSetup(FMLClientSetupEvent event) {

        KeybindsInit.register(event);
    }


}
