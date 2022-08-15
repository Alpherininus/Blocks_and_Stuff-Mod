package com.alpherininus.basmod.common.events;

import com.alpherininus.basmod.Basmod;
import com.alpherininus.basmod.common.world.gen.EntityGeneration;
import com.alpherininus.basmod.common.world.gen.FlowerGeneration;
import com.alpherininus.basmod.core.init.ItemInit;
import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import net.minecraft.entity.merchant.villager.VillagerProfession;
import net.minecraft.entity.merchant.villager.VillagerTrades;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.MerchantOffer;
import net.minecraftforge.event.village.VillagerTradesEvent;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.List;

@Mod.EventBusSubscriber(modid = Basmod.MOD_ID)
public class BasmodWorldEvents {

    @SubscribeEvent
    public static void biomeLoadingEvent(final BiomeLoadingEvent event) {

        EntityGeneration.onEntitySpawn(event);
        FlowerGeneration.generateFlowers(event);

    }

    @SubscribeEvent
    public  static void addCustomTrades(VillagerTradesEvent event) {
        int villagerStoneLevel = 1;
        int villagerIronLevel = 2;
        int villagerGoldLevel = 3;
        int villagerEmeraldLevel = 4;
        int villagerDiamondLevel = 5; // max 1 - 5.

        if (event.getType() == VillagerProfession.TOOLSMITH) {
            Int2ObjectMap<List<VillagerTrades.ITrade>> trades = event.getTrades();

            ItemStack stack = new ItemStack(ItemInit.NOHR_SHIELD_RED.get(), 1);

            trades.get(villagerStoneLevel).add(((trader, rand) -> new MerchantOffer(
                    new ItemStack(Items.EMERALD, 3), stack, 10, 8, 0.02F)));
        }

        if (event.getType() == VillagerProfession.WEAPONSMITH) {
            Int2ObjectMap<List<VillagerTrades.ITrade>> trades = event.getTrades();

            ItemStack stackCSS = new ItemStack(ItemInit.SUBLIME_CREATOR_SWORD.get(), 1);
            ItemStack stackSSS = new ItemStack(ItemInit.CREATOR_SWORD.get(), 1);
            ItemStack stackS = new ItemStack(ItemInit.SOLEILS_SHINE.get(), 1);
            ItemStack stackSP = new ItemStack(ItemInit.STEEL_PICKAXT.get(), 1);
            ItemStack stackRI = new ItemStack(ItemInit.RELICS_ITEM.get(), 3);

            trades.get(villagerDiamondLevel).add(((trader, rand) -> new MerchantOffer(
                    new ItemStack(Items.EMERALD, 32), new ItemStack(ItemInit.RUBY_ITEM.get(), 5), stackCSS, 1, 48, 0.09F)));

            trades.get(villagerEmeraldLevel).add(((trader, rand) -> new MerchantOffer(
                    new ItemStack(Items.EMERALD, 32), new ItemStack(ItemInit.RUBY_ITEM.get(), 2), stackSSS, 1, 24, 0.09F)));

            trades.get(villagerIronLevel).add(((trader, rand) -> new MerchantOffer(
                    new ItemStack(Items.EMERALD, 18), stackS, 5, 12, 0.02F)));

            trades.get(villagerStoneLevel).add(((trader, rand) -> new MerchantOffer(
                    new ItemStack(Items.EMERALD, 9), stackSP, 10, 8, 0.02F)));

            trades.get(villagerGoldLevel).add(((trader, rand) -> new MerchantOffer(
                    new ItemStack(Items.EMERALD, 32), stackRI, 3, 12, 0.09F)));
        }

    }

}

