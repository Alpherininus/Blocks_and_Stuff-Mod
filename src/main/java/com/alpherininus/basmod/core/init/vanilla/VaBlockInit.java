package com.alpherininus.basmod.core.init.vanilla;

import com.alpherininus.basmod.common.blocks.vanilla.PackedIce;
import com.alpherininus.basmod.core.init.ItemInit;
import com.alpherininus.basmod.core.util.itemgroup.ModItemGroupBlocks;
import com.alpherininus.basmod.core.util.materials.blocktier.BlockHardness;
import com.alpherininus.basmod.core.util.materials.blocktier.BlockResistance;
import com.alpherininus.basmod.core.util.materials.blocktier.BlockToolLevel;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.function.Supplier;

public class VaBlockInit {
    public static final DeferredRegister<Block> VANILLA_BLOCK_REGISTER = DeferredRegister.create(ForgeRegistries.BLOCKS, "minecraft");

    public static final RegistryObject<Block> MODIFI_BEDROCK = VANILLA_BLOCK_REGISTER.register("bedrock",
            ()-> new Block(AbstractBlock.Properties.create((Material.IRON), MaterialColor.GREEN)
                    .hardnessAndResistance(BlockHardness.getIron(), BlockResistance.getIron())
                    .harvestTool(ToolType.HOE)
                    .harvestLevel(BlockToolLevel.getDiamond())
                    .sound(SoundType.SLIME)));

    public static final RegistryObject<Block> MODIFI_ICE = VANILLA_BLOCK_REGISTER.register("packed_ice", PackedIce::new);

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////

}
