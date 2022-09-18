package com.alpherininus.basmod.common.items.theme;

import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BowItem;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.List;

public class FailnaughtBowItem extends BowItem {
    public FailnaughtBowItem(Properties builder) {
        super(builder);
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////


    @Override
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
        if (Screen.hasShiftDown()) {
            tooltip.add(new StringTextComponent("\u00A77Failnaught Bow is a Heroes' Relics, wielded by Claude von Riegan.\nInspired by the Fire Emblem universe."));

        } else {
            tooltip.add(new StringTextComponent("Hold \u00A76SHIFT \u00A7ffor more Information"));

        }
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {



        return super.onItemRightClick(worldIn, playerIn, handIn);
    }
}
