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
import net.minecraft.entity.*;
import net.minecraft.entity.effect.LightningBoltEntity;
import net.minecraft.entity.item.ArmorStandEntity;
import net.minecraft.entity.item.TNTEntity;
import net.minecraft.entity.monster.*;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.entity.projectile.ArrowEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.particles.BlockParticleData;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.potion.Effect;
import net.minecraft.potion.Effects;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.FoodStats;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;
import java.util.List;
import java.util.Random;

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

        TNTEntity armorStand = new TNTEntity(EntityType.TNT, worldIn);
        armorStand.setPosition(pos.getX(), pos.getY(), pos.getZ());

        armorStand.setFire(0);
        armorStand.setFuse(0);

        armorStand.setInvisible(false);

        if (worldIn instanceof ServerWorld && entityIn instanceof ArrowEntity) {
            worldIn.destroyBlock(new BlockPos(pos), true, entityIn);
            worldIn.addEntity(armorStand);
        }
        if (worldIn instanceof ServerWorld && entityIn instanceof ZombieEntity) {
            worldIn.destroyBlock(new BlockPos(pos), true, entityIn);
            worldIn.addEntity(armorStand);
        }
        if (worldIn instanceof ServerWorld && entityIn instanceof SkeletonEntity) {
            worldIn.destroyBlock(new BlockPos(pos), true, entityIn);
            worldIn.addEntity(armorStand);
        }
        if (worldIn instanceof ServerWorld && entityIn instanceof CreeperEntity) {
            worldIn.destroyBlock(new BlockPos(pos), true, entityIn);
            worldIn.addEntity(armorStand);
        }
        if (worldIn instanceof ServerWorld && entityIn instanceof SpiderEntity) {
            worldIn.destroyBlock(new BlockPos(pos), true, entityIn);
            worldIn.addEntity(armorStand);
        }

    }

    @Override
    public void onEntityWalk(World worldIn, BlockPos pos, Entity entityIn) {
        super.onEntityWalk(worldIn, pos, entityIn);
    }

    @OnlyIn(Dist.CLIENT)
    public void animateTick(BlockState stateIn, World worldIn, BlockPos pos, Random rand) {

        float chance = 0.35f;
        if (chance < rand.nextFloat()) {
            worldIn.addParticle(ParticleTypes.FLAME, pos.getX() + rand.nextDouble(),
                    pos.getY() + 0.5D, pos.getZ() + rand.nextDouble(),
                    0d, 0.05d, 0d);
            worldIn.addParticle(ParticleTypes.FLASH, pos.getX() + rand.nextDouble(),
                    pos.getY() + 0.5D, pos.getZ() + rand.nextDouble(),
                    0d, 0.03d, 0d);
        }
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    @Override
    public void addInformation(ItemStack stack, @Nullable IBlockReader worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
        tooltip.add(new StringTextComponent("\u00A7fSummon a TNT, by Breaking the Block with Arrows."));

        super.addInformation(stack, worldIn, tooltip, flagIn);
    }
}
