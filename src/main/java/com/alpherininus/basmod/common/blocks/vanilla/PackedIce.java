package com.alpherininus.basmod.common.blocks.vanilla;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;

public class PackedIce extends Block {

    public PackedIce() {
        super(Properties
                .create(Material.PACKED_ICE)
                .slipperiness(1.98F)
                .hardnessAndResistance(0.5F)
                .sound(SoundType.GLASS));
    }

}
