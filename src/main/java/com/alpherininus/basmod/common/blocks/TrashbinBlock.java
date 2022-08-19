package com.alpherininus.basmod.common.blocks;

import com.alpherininus.basmod.core.init.BlockInit;
import com.alpherininus.basmod.core.init.FluidInit;
import com.alpherininus.basmod.core.init.ItemInit;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.material.Material;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
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

import static net.minecraft.state.properties.BlockStateProperties.LIT;

public class TrashbinBlock extends Block {

    public TrashbinBlock(Properties properties) {
        super(properties);
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable IBlockReader worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
        if (Screen.hasShiftDown()) {
            tooltip.add(new StringTextComponent("Is a Trashbin"));
        } else {
            tooltip.add(new StringTextComponent("Hold \u00A76SHIFT \u00A7ffor more Information"));
        }
        super.addInformation(stack, worldIn, tooltip, flagIn);
    }

    public void onEntityWalk(World worldIn, BlockPos pos, Entity entityIn) {

        PlayerEntity playerIn = null;
        Hand handIn = null;
        assert false;
        ItemStack stackIn = playerIn.getHeldItem(handIn);
        BlockPos blockpos = playerIn.getPosition();
        BlockState blockstate = worldIn.getBlockState(blockpos);

        boolean mana = blockstate.getBlock() == BlockInit.TRASHBIN.get().getBlock();
        if (mana) {
            if (playerIn.shouldHeal()) {
                playerIn.heal(1.0F);
            }
            stackIn.setDamage(stackIn.getDamage() + 1);
            if (stackIn.getDamage() >= stackIn.getMaxDamage()) stackIn.setCount(0);

        }
    }

    private void deleteItemstack(BlockState blockState, ItemStack stack, PlayerEntity player, World worldIn, BlockPos pos) {
        ItemStack mainhand = player.getItemStackFromSlot(EquipmentSlotType.MAINHAND);
        Item getitem = mainhand.getItem();

        boolean test = getitem.isDamaged(mainhand);

        if (test) {
            stack.setDamage(stack.getDamage() + 1);
            if (stack.getDamage() >= stack.getMaxDamage()) stack.setCount(0);

        }
    }

    public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit) {
        if (worldIn.isRemote) {

        } else {
            activate(state, worldIn, pos);
        }

        ItemStack itemstack = player.getHeldItem(handIn);
        return itemstack.getItem() instanceof BlockItem && (new BlockItemUseContext(player, handIn, itemstack, hit)).canPlace() ? ActionResultType.PASS : ActionResultType.SUCCESS;
    }

    private static void activate(BlockState state, World world, BlockPos pos) {
        if (!state.get(LIT)) {
            world.setBlockState(pos, state.with(LIT, Boolean.TRUE), 3);
        }
    }

}
