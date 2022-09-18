package com.alpherininus.basmod.common.world.gen;

import com.alpherininus.basmod.common.entitys.animated.BasWanderingTraderEntity;
import com.alpherininus.basmod.core.init.BiomeInit;
import com.alpherininus.basmod.core.init.EntityTypesInit;
import net.minecraft.entity.EntityType;
import net.minecraft.util.RegistryKey;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.Biomes;
import net.minecraft.world.biome.MobSpawnInfo;
import net.minecraftforge.common.world.MobSpawnInfoBuilder;
import net.minecraftforge.event.world.BiomeLoadingEvent;

import java.util.Arrays;
import java.util.List;

public class EntityGeneration {

    public static void onEntitySpawn(final BiomeLoadingEvent event) {
        //addEntityToSpecificBiomes(event, BasmodEntityTypes.ASUKA_NPC.get(), 2, 0, 1,
        //         Biomes.PLAINS, Biomes.FOREST);

        addEntityToSpecificBiomes(event, EntityTypesInit.BASMOD_WANDERINGTRADER.get(), 6, 1, 3, Biomes.PLAINS, Biomes.FOREST);
        //add method, addEntityToAllBiomesExceptThese/ addEntityToSpecificBiomes/ addEntityToAllBiomes
    }

    @SafeVarargs
    private static void addEntityToAllBiomesExceptThese(BiomeLoadingEvent event, EntityType<?> type,
                                                        int weight, int minCount, int maxCount, RegistryKey<Biome>... biomes) {
        // Goes through each entry in the biomes and sees if it matches the current biome we are loading
        boolean isBiomeSelected = Arrays.stream(biomes).map(RegistryKey::getLocation)
                .map(Object::toString).anyMatch(s -> s.equals(event.getName().toString()));

        if(!isBiomeSelected) {
            addEntityToAllBiomes(event.getSpawns(), type, weight, minCount, maxCount);
        }
    }

    @SafeVarargs
    private static void addEntityToSpecificBiomes(BiomeLoadingEvent event, EntityType<?> type,
                                                  int weight, int minCount, int maxCount, RegistryKey<Biome>... biomes) {
        // Goes through each entry in the biomes and sees if it matches the current biome we are loading
        boolean isBiomeSelected = Arrays.stream(biomes).map(RegistryKey::getLocation)
                .map(Object::toString).anyMatch(s -> s.equals(event.getName().toString()));

        if(isBiomeSelected) {
            addEntityToAllBiomes(event.getSpawns(), type, weight, minCount, maxCount);
        }
    }

    private static void addEntityToAllBiomes(MobSpawnInfoBuilder spawns, EntityType<?> type,
                                             int weight, int minCount, int maxCount) {
        List<MobSpawnInfo.Spawners> base = spawns.getSpawner(type.getClassification());
        base.add(new MobSpawnInfo.Spawners(type,weight, minCount, maxCount));
    }
}
