package com.alpherininus.basmod.common.items;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BowItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.UseAction;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;

public class FailnaughtBowItem extends BowItem {
    public FailnaughtBowItem(Properties builder) {
        super(builder);
    }

    public int getUseDuration(ItemStack stack) {
        return 72000;
    }

    public UseAction getUseAction(ItemStack stack) {
        return UseAction.CROSSBOW;
    }

    public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {
        ItemStack itemstack = playerIn.getHeldItem(handIn);
        boolean flag = !playerIn.findAmmo(itemstack).isEmpty();

        ActionResult<ItemStack> ret = net.minecraftforge.event.ForgeEventFactory.onArrowNock(itemstack, worldIn, playerIn, handIn, flag);
        if (ret != null) return ret;

        if (!playerIn.abilities.isCreativeMode && !flag) {
            return ActionResult.resultFail(itemstack);
        } else {
            playerIn.setActiveHand(handIn);
            return ActionResult.resultConsume(itemstack);
        }
    }

    @Override
    public boolean isCrossbow(ItemStack stack) {
        return true;
    }
}
