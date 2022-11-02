package com.alpherininus.basmod.client.events;

import com.alpherininus.basmod.Basmod;
import com.alpherininus.basmod.common.world.gen.EntityGeneration;
import com.alpherininus.basmod.common.world.gen.FlowerGeneration;
import com.alpherininus.basmod.common.world.gen.StructureGeneration;
import com.alpherininus.basmod.common.world.gen.TreeGeneration;
import com.alpherininus.basmod.core.init.BlockInit;
import com.alpherininus.basmod.core.init.ItemInit;
import com.alpherininus.basmod.core.init.StructureInit;
import com.alpherininus.basmod.core.init.villager.CustomTrades;
import com.alpherininus.basmod.core.init.villager.ProfessionsInit;
import com.mojang.serialization.Codec;
import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import net.minecraft.entity.merchant.villager.VillagerProfession;
import net.minecraft.entity.merchant.villager.VillagerTrades;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.MerchantOffer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.World;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.FlatChunkGenerator;
import net.minecraft.world.gen.feature.structure.Structure;
import net.minecraft.world.gen.settings.DimensionStructuresSettings;
import net.minecraft.world.gen.settings.StructureSeparationSettings;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.common.BasicTrade;
import net.minecraftforge.event.village.VillagerTradesEvent;
import net.minecraftforge.event.village.WandererTradesEvent;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.event.world.WorldEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.ObfuscationReflectionHelper;
import org.apache.logging.log4j.LogManager;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Mod.EventBusSubscriber(modid = Basmod.MOD_ID)
public class BasmodWorldEvents {

    private static Map<Structure<?>, StructureSeparationSettings> field_236193_d_;

    @SubscribeEvent
    public static void biomeLoadingEvent(final BiomeLoadingEvent event) {

        EntityGeneration.onEntitySpawn(event);
        FlowerGeneration.generateFlowers(event);
        TreeGeneration.generateTrees(event);
        StructureGeneration.generateStructures(event);

    }

    @SubscribeEvent
    public static void addCustomTrades(VillagerTradesEvent event) {

        CustomTrades.customVillagerTrades(event);
        //
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

    @SubscribeEvent
    public static void addCustomGunderTreades(WandererTradesEvent event) {
        event.getGenericTrades().add(new BasicTrade(5, new ItemStack(ItemInit.RUBY_SWORD.get(), 5), 2, 10));
        event.getGenericTrades().add(new BasicTrade(16, new ItemStack(ItemInit.SEIEORSHELL_SPAWN_EGG.get(), 5), 1, 20));

    }

    @SubscribeEvent
    public static void addDimensionalSpacing(final WorldEvent.Load event) {
        if (event.getWorld() instanceof ServerWorld) {
            ServerWorld serverWorld = (ServerWorld) event.getWorld();

            try {
                Method GETCODEC_METHOD = ObfuscationReflectionHelper.findMethod(ChunkGenerator.class, "func_230347_a_"); // field_235948_a_
                ResourceLocation cgRL = Registry.CHUNK_GENERATOR_CODEC.getKey((Codec<? extends ChunkGenerator>) GETCODEC_METHOD.invoke(serverWorld.getChunkProvider().generator));
                if (cgRL != null && cgRL.getNamespace().equals("terraforged")) {
                    return;
                }
            } catch (Exception e) {
                LogManager.getLogger().error("Was unable to check if " + serverWorld.getDimensionKey().getLocation()
                        + " is using Terraforged's ChunkGenerator.");
            }

            if (serverWorld.getChunkProvider().generator instanceof FlatChunkGenerator && serverWorld.getDimensionKey().equals(World.OVERWORLD)) {
                return;
            }

            Map<Structure<?>, StructureSeparationSettings> tempMap = new HashMap<>(serverWorld.getChunkProvider().generator.func_235957_b_().func_236195_a_());
            tempMap.putIfAbsent(StructureInit.MAGICAL_WITCH_HOUSE.get(), DimensionStructuresSettings.field_236191_b_.get(StructureInit.MAGICAL_WITCH_HOUSE.get()));
            field_236193_d_ = tempMap;
        }

    }

}

