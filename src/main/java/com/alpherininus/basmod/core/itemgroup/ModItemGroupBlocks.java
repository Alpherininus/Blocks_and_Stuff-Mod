package com.alpherininus.basmod.core.itemgroup;

import com.alpherininus.basmod.core.init.ItemInit;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;

public class ModItemGroupBlocks extends ItemGroup {

	public static final ModItemGroupBlocks BAS_MOD_BLOCKS = new ModItemGroupBlocks(ItemGroup.GROUPS.length, "bas_mod_block");

	public ModItemGroupBlocks(int index, String label) {
		super(index, label);
	}

	@Override
	public ItemStack createIcon() {
		return new ItemStack(ItemInit.BASMOD_BOOK_BLOCKS.get());
	}

}
