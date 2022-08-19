package com.alpherininus.basmod.common.items;

import com.alpherininus.basmod.Basmod;
import com.alpherininus.basmod.core.init.ItemInit;
import net.minecraft.client.renderer.model.ModelResourceLocation;
import net.minecraft.entity.LivingEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.BowItem;
import net.minecraft.item.ItemStack;

public class FailnaughtBowItem extends BowItem {
    public FailnaughtBowItem(Properties builder) {
        super(builder);
        // this.setRegistryName("failnaught_bow");
    }

    @Override
    public void onUsingTick(ItemStack stack, LivingEntity player, int count) {
        ModelResourceLocation mrl = new ModelResourceLocation(Basmod.MOD_ID, "failnaught_bow");

        ItemStack mainhand = player.getItemStackFromSlot(EquipmentSlotType.MAINHAND);

        boolean stackUsed = mainhand.getItem() == ItemInit.FAILNAUGHT_BOW.get();

        if (stackUsed) {
            if (count >= 18) {
                mrl = new ModelResourceLocation(Basmod.MOD_ID, "failnaught_bow_pull_2");
            } else if (count > 13) {
                mrl = new ModelResourceLocation(Basmod.MOD_ID, "failnaught_bow_pull_1");
            } else if (count > 0) {
                mrl = new ModelResourceLocation(Basmod.MOD_ID, "failnaught_bow_pull_0");

            }
        }
    }

}
