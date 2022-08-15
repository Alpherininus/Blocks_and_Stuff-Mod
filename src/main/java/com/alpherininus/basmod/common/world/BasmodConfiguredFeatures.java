package com.alpherininus.basmod.common.world;

import com.alpherininus.basmod.core.init.BlockInit;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.WorldGenRegistries;
import net.minecraft.world.gen.blockplacer.SimpleBlockPlacer;
import net.minecraft.world.gen.blockstateprovider.SimpleBlockStateProvider;
import net.minecraft.world.gen.feature.*;

public class BasmodConfiguredFeatures {


    public static final ConfiguredFeature<?, ?> MAGICALFLOWERS_CONFIG = Feature.FLOWER.withConfiguration((
                    new BlockClusterFeatureConfig.Builder(new SimpleBlockStateProvider(BlockInit.MAGICAL_FLOWER.get().getDefaultState()),
                            SimpleBlockPlacer.PLACER)).tries(5).build())
            .withPlacement(Features.Placements.HEIGHTMAP_PLACEMENT).count(3);


    private static <FC extends IFeatureConfig> ConfiguredFeature<FC, ?> register(String key, ConfiguredFeature<FC, ?> configuredFeature) {
        return Registry.register(WorldGenRegistries.CONFIGURED_FEATURE, key, configuredFeature);
    }

}
