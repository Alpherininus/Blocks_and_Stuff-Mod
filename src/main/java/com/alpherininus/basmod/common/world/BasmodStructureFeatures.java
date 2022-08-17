package com.alpherininus.basmod.common.world;

import com.alpherininus.basmod.core.init.StructureInit;
import net.minecraft.util.registry.WorldGenRegistries;
import net.minecraft.world.gen.feature.IFeatureConfig;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraft.world.gen.feature.StructureFeature;
import net.minecraft.world.gen.feature.structure.Structure;

public class BasmodStructureFeatures {

    public static final StructureFeature<NoFeatureConfig, ? extends Structure<NoFeatureConfig>> MAGICAL_WITCH_HOUSE_FEATURE =
            register("magicalhouse", StructureInit.MAGICAL_WITCH_HOUSE.get().withConfiguration(NoFeatureConfig.NO_FEATURE_CONFIG));

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    private static <FC extends IFeatureConfig, F extends Structure<FC>> StructureFeature<FC, F> register(String name, StructureFeature<FC, F> structure) {
        return WorldGenRegistries.register(WorldGenRegistries.CONFIGURED_STRUCTURE_FEATURE, name, structure);
    }
}
