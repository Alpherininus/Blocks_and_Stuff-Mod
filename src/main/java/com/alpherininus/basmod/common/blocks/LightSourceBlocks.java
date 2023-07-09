package com.alpherininus.basmod.common.blocks;

import com.alpherininus.basmod.core.util.materials.blocktier.BlockToolLevel;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraftforge.common.ToolType;

import javax.annotation.Nullable;

public class LightSourceBlocks extends Block {
    public LightSourceBlocks() {
        super(AbstractBlock.Properties
                .create((Material.IRON), MaterialColor.GRAY)
                .hardnessAndResistance(2, 5)
                .harvestTool(ToolType.PICKAXE)
                .harvestLevel(BlockToolLevel.getStone())
                .sound(SoundType.BONE)
                .setLightLevel(BlockState -> 15)); // 1 - 15
    }

    @Override
    public boolean isAir(BlockState state, IBlockReader world, BlockPos pos) {
        return false;
    }

    @Override
    public boolean isBed(BlockState state, IBlockReader world, BlockPos pos, @Nullable Entity player) {
        return false;
    }

}