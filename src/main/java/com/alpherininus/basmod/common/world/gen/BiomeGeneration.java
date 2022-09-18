package com.alpherininus.basmod.common.world.gen;

import com.alpherininus.basmod.core.init.BiomeInit;
import net.minecraft.util.RegistryKey;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeManager;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.Objects;

import static net.minecraftforge.common.BiomeDictionary.Type.*;

public class BiomeGeneration {
    public static void generateBiome() {
        addBiome(BiomeInit.BAS_MAGICAL_FOREST_BIOME.get(), BiomeManager.BiomeType.WARM, 10, HOT, DEAD, DRY);
        addBiome(BiomeInit.BAS_DARK_FOREST_BIOME.get(), BiomeManager.BiomeType.COOL, 10, HOT, DEAD, DRY);
        addBiome(BiomeInit.BAS_DARK_PLAINS_BIOME.get(), BiomeManager.BiomeType.ICY, 10, HOT, DEAD, DRY);

    }

    private static void addBiome(Biome biome, BiomeManager.BiomeType type, int weight, BiomeDictionary.Type... types) {
        RegistryKey<Biome> key = RegistryKey.getOrCreateKey(ForgeRegistries.Keys.BIOMES,
                Objects.requireNonNull(ForgeRegistries.BIOMES.getKey(biome)));

        BiomeDictionary.addTypes(key, types);
        BiomeManager.addBiome(type, new BiomeManager.BiomeEntry(key, weight));
    }
}
