package com.alpherininus.basmod.client.events;

import com.alpherininus.basmod.Basmod;
import com.alpherininus.basmod.common.entitys.NPCEntity;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.inventory.InventoryScreen;
import net.minecraft.client.gui.widget.button.Button;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraftforge.client.event.GuiScreenEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.EntityLeaveWorldEvent;
import net.minecraftforge.event.entity.item.ItemTossEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.event.entity.player.PlayerSleepInBedEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.UUID;

@Mod.EventBusSubscriber(modid = Basmod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class BasmodPlayerEvents {

    private static final Logger LOGGER = LogManager.getLogger();
    private static final Minecraft mc = Minecraft.getInstance();

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
            event.getPos().up(1);
        }
    }

}

