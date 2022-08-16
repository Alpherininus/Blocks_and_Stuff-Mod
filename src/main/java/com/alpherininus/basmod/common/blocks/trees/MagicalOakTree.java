package com.alpherininus.basmod.common.blocks.trees;

import com.alpherininus.basmod.common.world.BasmodConfiguredFeatures;
import net.minecraft.block.trees.Tree;
import net.minecraft.world.gen.feature.BaseTreeFeatureConfig;
import net.minecraft.world.gen.feature.ConfiguredFeature;

import javax.annotation.Nullable;
import java.util.Random;

public class MagicalOakTree extends Tree {
    @Nullable
    @Override
    protected ConfiguredFeature<BaseTreeFeatureConfig, ?> getTreeFeature(Random randomIn, boolean largeHive) {
        return BasmodConfiguredFeatures.MAGICAL_OAK_TREE;
    }

}
