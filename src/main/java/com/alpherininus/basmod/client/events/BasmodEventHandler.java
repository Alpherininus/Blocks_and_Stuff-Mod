package com.alpherininus.basmod.client.events;

import com.alpherininus.basmod.Basmod;
import com.alpherininus.basmod.core.init.ItemInit;
import net.minecraft.block.BlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.item.ArmorStandEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.player.ArrowLooseEvent;
import net.minecraftforge.event.entity.player.ArrowNockEvent;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

// TODO && und , || oder ,


@Mod.EventBusSubscriber(modid = Basmod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class BasmodEventHandler {

    @SubscribeEvent
    public static void onBlockBreak(final BlockEvent.BreakEvent event) {
        IWorld world = event.getWorld();
        BlockPos pos = event.getPos();
        BlockState state = event.getState();

        Minecraft mc = Minecraft.getInstance();
        assert mc.player != null;
        ItemStack head = mc.player.getItemStackFromSlot(EquipmentSlotType.HEAD);

        if (head.getItem() == ItemInit.DIVING_HELMET_HELMET.get() && world instanceof World) {
            ArmorStandEntity armorStand = new ArmorStandEntity(EntityType.ARMOR_STAND, (World) world);
            armorStand.setPosition(pos.getX(), pos.getY(), pos.getZ());
            world.addEntity(armorStand);
        }
    }

    @SubscribeEvent
    // TODO ArrowLooseEvent is fired when a player stops using a bow.
    public static void onStopUsingBow(final ArrowLooseEvent event) {
    }
    @SubscribeEvent
    // TODO ArrowNockEvent is fired when a player begins using a bow.
    public static void onUsingBow(final ArrowNockEvent event) {
    }

}
