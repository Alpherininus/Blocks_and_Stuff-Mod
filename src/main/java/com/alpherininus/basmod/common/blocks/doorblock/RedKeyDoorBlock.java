package com.alpherininus.basmod.common.blocks.doorblock;

import com.alpherininus.basmod.core.init.ItemInit;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.DoorBlock;
import net.minecraft.block.material.Material;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
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

public class RedKeyDoorBlock extends DoorBlock {

    private int getCloseSound() {
        return this.material == Material.IRON ? 1011 : 1012;
    }
    private int getOpenSound() {
        return this.material == Material.IRON ? 1005 : 1006;
    }

    public RedKeyDoorBlock(Properties builder) {
        super(builder);
    }

    @Override
    public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit) {

        ItemStack mainhand = player.getItemStackFromSlot(EquipmentSlotType.MAINHAND);

        if (mainhand.getItem() == ItemInit.DOOR_REDKEY_ITEM.get() && this.material == Material.IRON) {
            state = state.cycleValue(OPEN);
            worldIn.setBlockState(pos, state, 10);
            worldIn.playEvent(player, state.get(OPEN) ? this.getOpenSound() : this.getCloseSound(), pos, 0);
            return ActionResultType.func_233537_a_(worldIn.isRemote);
        } else {
            return ActionResultType.PASS;
        }
    }

    @Override
    public void neighborChanged(BlockState state, World worldIn, BlockPos pos, Block blockIn, BlockPos fromPos, boolean isMoving) {
        boolean flag = false;
        if (blockIn != this && flag != state.get(POWERED)) {
            if (flag != state.get(OPEN)) {
                this.playSound(worldIn, pos, flag);
            }

            worldIn.setBlockState(pos, state.with(POWERED, Boolean.valueOf(flag)).with(OPEN, Boolean.valueOf(flag)), 2);
        }
    }

    private void playSound(World worldIn, BlockPos pos, boolean isOpening) {
        worldIn.playEvent((PlayerEntity)null, isOpening ? this.getOpenSound() : this.getCloseSound(), pos, 0);
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    @Override
    public void addInformation(ItemStack stack, @Nullable IBlockReader worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {

        tooltip.add(new StringTextComponent("\u00A7cRed Door Key"));
    }
}