package com.alpherininus.basmod.common.events;

import com.alpherininus.basmod.Basmod;
import com.alpherininus.basmod.core.util.BasmodConfig;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.text.StringTextComponent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.EntityLeaveWorldEvent;
import net.minecraftforge.event.entity.item.ItemTossEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.entity.player.PlayerSleepInBedEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod.EventBusSubscriber(modid = Basmod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class BasmodPlayerEvents {

    private static final Logger LOGGER = LogManager.getLogger();

    @SubscribeEvent
    public static void onPlayerTick(TickEvent.PlayerTickEvent event) {

    }

    @SubscribeEvent
    public static void onPlayerToss(ItemTossEvent event) {
    }

    @SubscribeEvent
    public static void onPlayerEvent(PlayerEvent event) {
    }

    @SubscribeEvent
    public static void onPlayerJoinEvent(EntityJoinWorldEvent event) {
        Minecraft mc = Minecraft.getInstance();
        if ((event.getEntity() instanceof PlayerEntity)) {
        }
    }

    @SubscribeEvent
    public static void onPlayerLeaveEvent(EntityLeaveWorldEvent event) {
        Minecraft mc = Minecraft.getInstance();
        if ((event.getEntity() instanceof PlayerEntity)) {
        }
    }

    @SubscribeEvent
    public void onPlayerSleep(PlayerSleepInBedEvent event) {
        if (!event.getPlayer().world.isDaytime() && !event.getPlayer().world.isRemote) {
            event.getPlayer().sendStatusMessage(new StringTextComponent("is day you fool"), true);
        }
    }

}

