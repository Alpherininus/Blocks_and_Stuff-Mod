package com.alpherininus.basmod.core.init.villager;


import com.alpherininus.basmod.core.init.BlockInit;
import com.alpherininus.basmod.core.init.ItemInit;
import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import net.minecraft.block.Blocks;
import net.minecraft.entity.merchant.villager.VillagerTrades;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.MerchantOffer;
import net.minecraftforge.event.village.VillagerTradesEvent;

import java.util.List;
import java.util.Random;

public class CustomTrades {

    public static void customVillagerTrades(final VillagerTradesEvent event) {

        if (event.getType() == ProfessionsInit.BONER_VILLAGER_PROF.get()) {
            Int2ObjectMap<List<VillagerTrades.ITrade>> trades = event.getTrades();

            ItemStack stoneA = new ItemStack(Items.STICK, 2);
            ItemStack stoneB = new ItemStack(Items.APPLE, 3);
            ItemStack stoneC = new ItemStack(Items.ARROW, 5);
            ItemStack stoneD = new ItemStack(Items.GOLD_NUGGET, 3);
            ItemStack stoneE = new ItemStack(Items.FLINT, 2);
            ItemStack stoneF = new ItemStack(Items.IRON_NUGGET, 2);
            ItemStack stoneG = new ItemStack(Items.BLACKSTONE, 3);
            ItemStack stoneH = new ItemStack(Items.QUARTZ, 1);

            int villagerStoneLevel = 1;
            trades.get(villagerStoneLevel).add(((trader, rand) -> new MerchantOffer(
                    new ItemStack(Items.BONE, 3), stoneA, 10, 0, 0.12F)));
            trades.get(villagerStoneLevel).add(((trader, rand) -> new MerchantOffer(
                    new ItemStack(Items.BONE_MEAL, 10), stoneB, 10, 0, 0.05F)));
            trades.get(villagerStoneLevel).add(((trader, rand) -> new MerchantOffer(
                    new ItemStack(Items.BONE, 10), stoneC, 10, 0, 0.12F)));
            trades.get(villagerStoneLevel).add(((trader, rand) -> new MerchantOffer(
                    new ItemStack(Items.BONE_MEAL, 17), stoneD, 10, 0, 0.05F)));
            trades.get(villagerStoneLevel).add(((trader, rand) -> new MerchantOffer(
                    new ItemStack(Items.BONE, 23), stoneE, 10, 0, 0.12F)));
            trades.get(villagerStoneLevel).add(((trader, rand) -> new MerchantOffer(
                    new ItemStack(Items.BONE_MEAL, 32), stoneF, 10, 0, 0.05F)));
            trades.get(villagerStoneLevel).add(((trader, rand) -> new MerchantOffer(
                    new ItemStack(Items.BONE, 64), stoneG, 10, 0, 0.12F)));
            trades.get(villagerStoneLevel).add(((trader, rand) -> new MerchantOffer(
                    new ItemStack(Items.BONE_MEAL, 64), stoneH, 10, 0, 0.05F)));
        }

        if (event.getType() == ProfessionsInit.JUNKY_VILLAGER_PROF.get()) {
            Int2ObjectMap<List<VillagerTrades.ITrade>> trades = event.getTrades();

            ItemStack stoneA = new ItemStack(Items.STICK, 2);
            ItemStack stoneB = new ItemStack(BlockInit.MAGICAL_FLOWER.get(), 1);
            ItemStack stoneC = new ItemStack(BlockInit.MAGICAL_OAK_SAPLING.get(), 1);
            ItemStack stoneD = new ItemStack(Items.SNOWBALL, 3);
            ItemStack stoneE = new ItemStack(Items.OAK_SAPLING, 2);
            ItemStack stoneF = new ItemStack(ItemInit.SOLEILS_SHINE.get(), 1);

            int villagerStoneLevel = 1;
            trades.get(villagerStoneLevel).add(((trader, rand) -> new MerchantOffer(
                    new ItemStack(Items.DIRT, 1), stoneA, 10, 8, 0.02F)));
            trades.get(villagerStoneLevel).add(((trader, rand) -> new MerchantOffer(
                    new ItemStack(Items.DIRT, 25), stoneB, 10, 8, 0.02F)));
            trades.get(villagerStoneLevel).add(((trader, rand) -> new MerchantOffer(
                    new ItemStack(Items.DIRT, 50), stoneC, 10, 8, 0.02F)));
            trades.get(villagerStoneLevel).add(((trader, rand) -> new MerchantOffer(
                    new ItemStack(Items.DIRT, 1), stoneD, 10, 8, 0.02F)));
            trades.get(villagerStoneLevel).add(((trader, rand) -> new MerchantOffer(
                    new ItemStack(Items.DIRT, 3), stoneE, 10, 8, 0.02F)));
            trades.get(villagerStoneLevel).add(((trader, rand) -> new MerchantOffer(
                    new ItemStack(Items.DIRT, 64), stoneF, 10, 8, 0.02F)));
            //
            ItemStack ironA = new ItemStack(Items.STICK, 2);
            ItemStack ironB = new ItemStack(BlockInit.MAGICAL_FLOWER.get(), 1);
            ItemStack ironC = new ItemStack(BlockInit.MAGICAL_OAK_SAPLING.get(), 1);
            ItemStack ironD = new ItemStack(Items.SNOWBALL, 5);
            ItemStack ironE = new ItemStack(Items.OAK_SAPLING, 2);
            ItemStack ironF = new ItemStack(Items.WOODEN_SWORD, 1);

            int villagerIronLevel = 2;
            trades.get(villagerIronLevel).add(((trader, rand) -> new MerchantOffer(
                    new ItemStack(Items.DIRT, 1), ironA, 10, 8, 0.02F)));
            trades.get(villagerIronLevel).add(((trader, rand) -> new MerchantOffer(
                    new ItemStack(Items.DIRT, 20), ironB, 10, 8, 0.02F)));
            trades.get(villagerIronLevel).add(((trader, rand) -> new MerchantOffer(
                    new ItemStack(Items.DIRT, 45), ironC, 10, 8, 0.02F)));
            trades.get(villagerIronLevel).add(((trader, rand) -> new MerchantOffer(
                    new ItemStack(Items.DIRT, 2), ironD, 10, 8, 0.02F)));
            trades.get(villagerIronLevel).add(((trader, rand) -> new MerchantOffer(
                    new ItemStack(Items.DIRT, 3), ironE, 10, 8, 0.02F)));
            trades.get(villagerIronLevel).add(((trader, rand) -> new MerchantOffer(
                    new ItemStack(Items.DIRT, 32), ironF, 10, 8, 0.02F)));
            //
            ItemStack goldA = new ItemStack(Items.STICK, 2);
            ItemStack goldB = new ItemStack(BlockInit.MAGICAL_FLOWER.get(), 1);
            ItemStack goldC = new ItemStack(BlockInit.MAGICAL_OAK_SAPLING.get(), 1);
            ItemStack goldD = new ItemStack(Items.SNOWBALL, 3);
            ItemStack goldE = new ItemStack(Items.OAK_SAPLING, 2);
            ItemStack goldF = new ItemStack(ItemInit.SOLEILS_SHINE.get(), 1);

            int villagerGoldLevel = 3;
            trades.get(villagerGoldLevel).add(((trader, rand) -> new MerchantOffer(
                    new ItemStack(Items.DIRT, 1), goldA, 10, 8, 0.02F)));
            trades.get(villagerGoldLevel).add(((trader, rand) -> new MerchantOffer(
                    new ItemStack(Items.DIRT, 15), goldB, 10, 8, 0.02F)));
            trades.get(villagerGoldLevel).add(((trader, rand) -> new MerchantOffer(
                    new ItemStack(Items.DIRT, 40), goldC, 10, 8, 0.02F)));
            trades.get(villagerGoldLevel).add(((trader, rand) -> new MerchantOffer(
                    new ItemStack(Items.DIRT, 1), goldD, 10, 8, 0.02F)));
            trades.get(villagerGoldLevel).add(((trader, rand) -> new MerchantOffer(
                    new ItemStack(Items.DIRT, 3), goldE, 10, 8, 0.02F)));
            trades.get(villagerGoldLevel).add(((trader, rand) -> new MerchantOffer(
                    new ItemStack(Items.DIRT, 64), goldF, 10, 8, 0.02F)));
            //
            ItemStack emeraldA = new ItemStack(Items.STICK, 2);
            ItemStack emeraldB = new ItemStack(BlockInit.MAGICAL_FLOWER.get(), 1);
            ItemStack emeraldC = new ItemStack(BlockInit.MAGICAL_OAK_SAPLING.get(), 1);
            ItemStack emeraldD = new ItemStack(Items.SNOWBALL, 3);
            ItemStack emeraldE = new ItemStack(Items.OAK_SAPLING, 2);
            ItemStack emeraldF = new ItemStack(ItemInit.SOLEILS_SHINE.get(), 1);

            int villagerEmeraldLevel = 4;
            trades.get(villagerEmeraldLevel).add(((trader, rand) -> new MerchantOffer(
                    new ItemStack(Items.DIRT, 1), emeraldA, 10, 8, 0.02F)));
            trades.get(villagerEmeraldLevel).add(((trader, rand) -> new MerchantOffer(
                    new ItemStack(Items.DIRT, 10), emeraldB, 10, 8, 0.02F)));
            trades.get(villagerEmeraldLevel).add(((trader, rand) -> new MerchantOffer(
                    new ItemStack(Items.DIRT, 35), emeraldC, 10, 8, 0.02F)));
            trades.get(villagerEmeraldLevel).add(((trader, rand) -> new MerchantOffer(
                    new ItemStack(Items.DIRT, 15), emeraldD, 10, 8, 0.02F)));
            trades.get(villagerEmeraldLevel).add(((trader, rand) -> new MerchantOffer(
                    new ItemStack(Items.DIRT, 3), emeraldE, 10, 8, 0.02F)));
            trades.get(villagerEmeraldLevel).add(((trader, rand) -> new MerchantOffer(
                    new ItemStack(Items.DIRT, 64), emeraldF, 10, 8, 0.02F)));
            //
            ItemStack diamondA = new ItemStack(Items.STICK, 2);
            ItemStack diamondB = new ItemStack(BlockInit.MAGICAL_FLOWER.get(), 1);
            ItemStack diamondC = new ItemStack(Blocks.CHEST, 1);
            ItemStack diamondD = new ItemStack(Items.NAME_TAG, 2);
            ItemStack diamondE = new ItemStack(Items.EGG, 2);
            ItemStack diamondF = new ItemStack(Items.DIAMOND, 1);

            int villagerDiamondLevel = 5;
            trades.get(villagerDiamondLevel).add(((trader, rand) -> new MerchantOffer(
                    new ItemStack(Items.DIRT, 1), diamondA, 10, 8, 0.02F)));
            trades.get(villagerDiamondLevel).add(((trader, rand) -> new MerchantOffer(
                    new ItemStack(Items.DIRT, 5), diamondB, 10, 8, 0.02F)));
            trades.get(villagerDiamondLevel).add(((trader, rand) -> new MerchantOffer(
                    new ItemStack(Items.DIRT, 30), diamondC, 10, 8, 0.02F)));
            trades.get(villagerDiamondLevel).add(((trader, rand) -> new MerchantOffer(
                    new ItemStack(Items.DIRT, 10), diamondD, 2, 3, 0.02F)));
            trades.get(villagerDiamondLevel).add(((trader, rand) -> new MerchantOffer(
                    new ItemStack(Items.DIRT, 3), diamondE, 10, 8, 0.02F)));
            trades.get(villagerDiamondLevel).add(((trader, rand) -> new MerchantOffer(
                    new ItemStack(Items.DIRT, 64), diamondF, 5, 10, 0.05F)));
        }

    }
}
