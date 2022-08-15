package com.alpherininus.basmod.common.world.gen;

import com.alpherininus.basmod.core.init.BlockInit;
import net.minecraft.block.BlockState;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.OreFeatureConfig;
import net.minecraft.world.gen.feature.template.RuleTest;
import net.minecraft.world.gen.feature.template.TagMatchRuleTest;
import net.minecraft.world.gen.placement.Placement;
import net.minecraft.world.gen.placement.TopSolidRangeConfig;
import net.minecraftforge.event.world.BiomeLoadingEvent;

public class OreGeneration {
    public static void addOres(final BiomeLoadingEvent event) {
        addOre(event, OreFeatureConfig.FillerBlockType.BASE_STONE_OVERWORLD,
                BlockInit.UMBRAL_STEEL_ORE.get().getDefaultState(), 4,15, 65, 15);

        addOre(event, OreFeatureConfig.FillerBlockType.BASE_STONE_OVERWORLD,
                BlockInit.RUBY_ORE.get().getDefaultState(), 4,15, 65, 8);

        addOre(event, OreFeatureConfig.FillerBlockType.BASE_STONE_OVERWORLD,
                BlockInit.DARK_STEEL_ORE.get().getDefaultState(), 4,5, 65, 8);

        addOre(event, OreFeatureConfig.FillerBlockType.BASE_STONE_OVERWORLD,
                BlockInit.STEEL_ORE.get().getDefaultState(), 5,5, 75, 15);

        addOre(event, OreFeatureConfig.FillerBlockType.BASE_STONE_OVERWORLD,
                BlockInit.RELICS_ORE.get().getDefaultState(), 3,5, 65, 4);

		// addOre(event, OreFeatureConfig.FillerBlockType.NATURAL_STONE,
        // BlockInit.RUBY_ORE.get().defaultBlockState(), 4,0, 60, 10);
        // addOre(event, new BlockMatchRuleTest(BlockInit.RUBY_ORE.get()),
        // BlockInit.RUBY_ORE.get().defaultBlockState(), 4,0, 60, 10);
        // addOre(event, new TagMatchRuleTest(BlockTags.GOLD_ORES),
        // BlockInit.UMBRAL_STEEL_ORE.get().getDefaultState(), 4,15, 65, 15);
        // addOre(event, new TagMatchRuleTest(BlockTags.GOLD_ORES),
        // BlockInit.RUBY_ORE.get().getDefaultState(), 4,15, 65, 8);
        // addOre(event, new TagMatchRuleTest(BlockTags.GOLD_ORES),
        // BlockInit.DARK_STEEL_ORE.get().getDefaultState(), 3,5, 65, 8);
        // addOre(event, new TagMatchRuleTest(BlockTags.GOLD_ORES),
        // BlockInit.STEEL_ORE.get().getDefaultState(), 5,5, 75, 15);
        // addOre(event, new TagMatchRuleTest(BlockTags.GOLD_ORES),
        // BlockInit.RELICS_ORE.get().getDefaultState(), 3,5, 65, 4);

    }

    public static void addOre(final BiomeLoadingEvent event, RuleTest rule, BlockState state, int veinSize,
                              int minHeight, int maxHeight, int amount) {
        event.getGeneration().withFeature(GenerationStage.Decoration.UNDERGROUND_ORES, Feature.ORE.withConfiguration(new OreFeatureConfig(rule, state, veinSize))
                .withPlacement(Placement.RANGE.configure(new TopSolidRangeConfig(minHeight, 0, maxHeight))
                        .square().count(amount)));
    }
}