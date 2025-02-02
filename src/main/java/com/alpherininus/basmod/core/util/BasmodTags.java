package com.alpherininus.basmod.core.util;

import com.alpherininus.basmod.Basmod;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.Tags;

public class BasmodTags {
    public static String BLOKS = "blocks/";
    public static String ITMS = "items/";

    public static class Blocks {

        public static final Tags.IOptionalNamedTag<Block> CLICKABLE_BLOCKS_FFIRE = createBlockTag(BLOKS + "clickable_blocks_for_fire");
        public static final Tags.IOptionalNamedTag<Block> TRASHABLE_BLOCKS = createBlockTag(BLOKS + "trashable_blocks");

        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        private static Tags.IOptionalNamedTag<Block> createBlockTag(String name) {
            return BlockTags.createOptional(new ResourceLocation(Basmod.MOD_ID, name));
        }
        private static Tags.IOptionalNamedTag<Block> createForgeTag(String name) {
            return BlockTags.createOptional(new ResourceLocation("forge", name));
        }
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


    public static class Items {

        public static final Tags.IOptionalNamedTag<Item> TRASHABLE_ITEM = createItemTag(ITMS + "trashable_items");

        public static final Tags.IOptionalNamedTag<Item> REPAIR_MOD_SWORDS = createItemTag(ITMS + "repair_swords");
        public static final Tags.IOptionalNamedTag<Item> CRAFTING_WHITE_DYE = createItemTag(ITMS + "crafting_white_dye");
        public static final Tags.IOptionalNamedTag<Item> CRAFTING_BLUE_DYE = createItemTag(ITMS + "crafting_blue_dye");
        public static final Tags.IOptionalNamedTag<Item> CRAFTING_BOW = createItemTag(ITMS + "crafting_bow");

        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        private static Tags.IOptionalNamedTag<Item> createItemTag(String name) {
            return ItemTags.createOptional(new ResourceLocation(Basmod.MOD_ID, name));
        }
        private static Tags.IOptionalNamedTag<Item> createForgeTag(String name) {
            return ItemTags.createOptional(new ResourceLocation("forge", name));
        }
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
}
