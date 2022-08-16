package com.alpherininus.basmod.common.world;

import com.alpherininus.basmod.core.init.BlockInit;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.WorldGenRegistries;
import net.minecraft.world.gen.blockplacer.SimpleBlockPlacer;
import net.minecraft.world.gen.blockstateprovider.SimpleBlockStateProvider;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.foliageplacer.BlobFoliagePlacer;
import net.minecraft.world.gen.trunkplacer.StraightTrunkPlacer;

public class BasmodConfiguredFeatures {


    public static final ConfiguredFeature<?, ?> MAGICALFLOWERS_CONFIG = Feature.FLOWER.withConfiguration((
                    new BlockClusterFeatureConfig.Builder(new SimpleBlockStateProvider(BlockInit.MAGICAL_FLOWER.get().getDefaultState()),
                            SimpleBlockPlacer.PLACER)).tries(5).build())
            .withPlacement(Features.Placements.HEIGHTMAP_PLACEMENT).count(3);

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public static final ConfiguredFeature<BaseTreeFeatureConfig, ?> MAGICAL_OAK_TREE =
            register("magicaloak", Feature.TREE.withConfiguration((
                    new BaseTreeFeatureConfig.Builder(
                            new SimpleBlockStateProvider(BlockInit.MAGICAL_OAK_LOG.get().getDefaultState()),
                            new SimpleBlockStateProvider(BlockInit.MAGICAL_OAK_LEAVES.get().getDefaultState()),
                            new BlobFoliagePlacer(FeatureSpread.create(2), FeatureSpread.create(0), 3),
                            new StraightTrunkPlacer(6, 4, 3),
                            new TwoLayerFeature(1, 0, 1))).setIgnoreVines().build()));

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    private static <FC extends IFeatureConfig> ConfiguredFeature<FC, ?> register(String key, ConfiguredFeature<FC, ?> configuredFeature) {
        return Registry.register(WorldGenRegistries.CONFIGURED_FEATURE, key, configuredFeature);
    }

}
