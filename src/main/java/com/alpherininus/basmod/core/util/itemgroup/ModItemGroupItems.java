package com.alpherininus.basmod.core.util.itemgroup;

import com.alpherininus.basmod.core.init.ItemInit;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;

public class ModItemGroupItems extends ItemGroup {

	public static final ModItemGroupItems BAS_MOD_ITEMS = new ModItemGroupItems(ItemGroup.GROUPS.length, "bas_mod_item");

	public ModItemGroupItems(int index, String label) {
		super(index, label);
	}

	@Override
	public ItemStack createIcon() {
		return new ItemStack(ItemInit.BASMOD_BOOK_ITEMS.get());
	}

}
