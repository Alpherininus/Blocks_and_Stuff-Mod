package com.alpherininus.basmod.core.util.itemgroup;

import com.alpherininus.basmod.core.init.ItemInit;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;

public class ModItemGroupWapons extends ItemGroup {

	public static final ModItemGroupWapons BAS_MOD_WAPONS = new ModItemGroupWapons(ItemGroup.GROUPS.length, "bas_mod_wapons");


	public ModItemGroupWapons(int index, String label) {
		super(index, label);
	}

	@Override
	public ItemStack createIcon() {
		return new ItemStack(ItemInit.BASMOD_BOOK_WAPONS.get());
	}

}
