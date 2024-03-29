package com.alpherininus.basmod.common.world.gen;

import com.alpherininus.basmod.core.init.BiomeInit;
import com.alpherininus.basmod.core.init.StructureInit;
import net.minecraft.util.RegistryKey;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.feature.IFeatureConfig;
import net.minecraft.world.gen.feature.StructureFeature;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.event.world.BiomeLoadingEvent;

import java.util.List;
import java.util.Set;
import java.util.function.Supplier;

public class StructureGeneration {
    public static void generateStructures(final BiomeLoadingEvent event) {
        RegistryKey<Biome> key = RegistryKey.getOrCreateKey(Registry.BIOME_KEY, event.getName());
        Set<BiomeDictionary.Type> types = BiomeDictionary.getTypes(key);
        List<Supplier<StructureFeature<?, ?>>> structures = event.getGeneration().getStructures();

        if(types.contains(BiomeDictionary.Type.PLAINS)) {
            structures.add(() -> StructureInit.MAGICAL_WITCH_HOUSE.get().withConfiguration(IFeatureConfig.NO_FEATURE_CONFIG));
        }

        if (types.contains(BiomeDictionary.Type.PLAINS)) {
            structures.add(() -> StructureInit.GRATERLOL.get().withConfiguration(IFeatureConfig.NO_FEATURE_CONFIG));
        }

        if(types.contains(BiomeDictionary.Type.WATER)) {
            //
        }

    }
}
