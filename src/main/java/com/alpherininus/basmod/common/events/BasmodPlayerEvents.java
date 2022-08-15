package com.alpherininus.basmod.common.events;

import com.alpherininus.basmod.Basmod;
import com.alpherininus.basmod.core.util.BasmodConfig;
import com.mojang.authlib.yggdrasil.request.JoinMinecraftServerRequest;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.*;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.EntityLeaveWorldEvent;
import net.minecraftforge.event.entity.item.ItemTossEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.living.LivingFallEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.apache.logging.log4j.LogManager;

import java.util.UUID;

@Mod.EventBusSubscriber(modid = Basmod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class BasmodPlayerEvents {
    private static int flyTime = 0;


    @SubscribeEvent
    public static void onPlayerTick(TickEvent.PlayerTickEvent event) {

    }

    @SubscribeEvent
    public static void onPlayerToss(ItemTossEvent event) {
        System.out.println("Integer:" + BasmodConfig.config_integer.get());
        System.out.println("String:" + BasmodConfig.config_string.get());

    }

    @SubscribeEvent
    public static void onPlayerEvent(PlayerEvent event) {

    }

    @SubscribeEvent
    public static void onPlayerJoinEvent(EntityJoinWorldEvent event) {
        Minecraft mc = Minecraft.getInstance();

        if ((event.getEntity() instanceof PlayerEntity)) {
            LogManager.getLogger().info("INFO || Blocks and Stuff MOD -> Player Joined the World!");

        }
    }

    @SubscribeEvent
    public static void onPlayerLeaveEvent(EntityLeaveWorldEvent event) {
        Minecraft mc = Minecraft.getInstance();

        if ((event.getEntity() instanceof PlayerEntity)) {
            LogManager.getLogger().info("INFO || Blocks and Stuff MOD -> Player Leave the World!, have a great time :)");
        }

    }

}

