package com.alpherininus.basmod.common.blocks;

import com.alpherininus.basmod.common.entitys.animated.BasBossEntity;
import com.alpherininus.basmod.core.init.BlockInit;
import com.alpherininus.basmod.core.init.EntityTypesInit;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.FireBlock;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.crash.CrashReport;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.Pose;
import net.minecraft.entity.effect.LightningBoltEntity;
import net.minecraft.entity.item.ArmorStandEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.ArrowEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;
import java.util.List;

public class BombBox extends Block {

    public BombBox(Properties properties) {
        super(properties);
    }

    @Override
    public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit) {
        worldIn.destroyBlock(pos, false);
        return super.onBlockActivated(state, worldIn, pos, player, handIn, hit);
    }

    @Override
    @ParametersAreNonnullByDefault
    public void onEntityCollision(BlockState state, World worldIn, BlockPos pos, Entity entityIn) {
        if (worldIn instanceof ServerWorld && entityIn instanceof ArrowEntity) {
            worldIn.destroyBlock(new BlockPos(pos), true, entityIn);

            LightningBoltEntity armorStand = new LightningBoltEntity(EntityType.LIGHTNING_BOLT, worldIn);
            armorStand.setPosition(pos.getX(), pos.getY(), pos.getZ());
            worldIn.addEntity(armorStand);
        } else if (worldIn instanceof ServerWorld && entityIn instanceof LivingEntity) {
            worldIn.destroyBlock(new BlockPos(pos), true, entityIn);

            LightningBoltEntity armorStand = new LightningBoltEntity(EntityType.LIGHTNING_BOLT, worldIn);
            armorStand.setPosition(pos.getX(), pos.getY(), pos.getZ());
            worldIn.addEntity(armorStand);
        }
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable IBlockReader worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
        tooltip.add(new StringTextComponent("\u00A7fSummon a Lightningbolt, by Breaking the Block with Arrows."));

        super.addInformation(stack, worldIn, tooltip, flagIn);
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////

}
