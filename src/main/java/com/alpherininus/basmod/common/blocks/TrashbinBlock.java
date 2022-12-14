package com.alpherininus.basmod.common.blocks;

import com.alpherininus.basmod.core.util.BasmodTags;
import net.minecraft.block.BlockState;
import net.minecraft.block.RotatedPillarBlock;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.BlockItem;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.List;

public class TrashbinBlock extends RotatedPillarBlock {

    public TrashbinBlock(Properties properties) {
        super(properties);
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable IBlockReader worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
        if (Screen.hasShiftDown()) {
            tooltip.add(new StringTextComponent("Trashbin for DIRT, ANDESITE, GRANITE, DIORITE, COARSE_DIRT, ..."));
        } else {
            tooltip.add(new StringTextComponent("Hold \u00A76SHIFT \u00A7ffor more Information"));
        }
        super.addInformation(stack, worldIn, tooltip, flagIn);
    }

    public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit) {

        ItemStack itemstack1 = player.getHeldItem(handIn);
        ItemStack mainhand = player.getItemStackFromSlot(EquipmentSlotType.MAINHAND);

        if (worldIn.isRemote) {
            if (itemstack1.isEmpty()) {
                System.out.println("## Trashing has failed, No Blocks or Items in your Hand. ##");
            }
        } else {
            if (state.isIn(BasmodTags.Blocks.TRASHABLE_BLOCKS)) {
                itemstack1.setDamage(itemstack1.getDamage() + 1);
                if (itemstack1.getDamage() >= itemstack1.getMaxDamage()) itemstack1.setCount(0);
            }

            ItemStack itemstack = player.getHeldItem(handIn);
            return itemstack.getItem() instanceof BlockItem && (new BlockItemUseContext(player, handIn, itemstack, hit)).canPlace() ? ActionResultType.PASS : ActionResultType.SUCCESS;
        }
        ItemStack itemstack = player.getHeldItem(handIn);
        return itemstack.getItem() instanceof BlockItem && (new BlockItemUseContext(player, handIn, itemstack, hit)).canPlace() ? ActionResultType.PASS : ActionResultType.SUCCESS;
    }
}

