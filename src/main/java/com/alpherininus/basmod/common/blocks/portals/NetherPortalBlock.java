package com.alpherininus.basmod.common.blocks.portals;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.particles.BlockParticleData;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.shapes.IBooleanFunction;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.sound.SoundEvent;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Random;

public class NetherPortalBlock extends Block {

    public NetherPortalBlock(Properties properties) {
        super(properties);
    }

    @Override
    public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit) {
        RegistryKey<World> registrykey = worldIn.getDimensionKey() == World.THE_NETHER ? World.OVERWORLD : World.THE_NETHER;
        ServerWorld serverworld = ((ServerWorld)worldIn).getServer().getWorld(registrykey);
        if (serverworld == null) {
            return null;
        }

        player.changeDimension(serverworld);

        player.playSound(SoundEvents.BLOCK_PORTAL_TRAVEL, SoundCategory.BLOCKS, 50, 1);

        return super.onBlockActivated(state, worldIn, pos, player, handIn, hit);
    }

    @OnlyIn(Dist.CLIENT)
    public void animateTick(BlockState stateIn, World worldIn, BlockPos pos, Random rand) {
        float chance = 0.35f;
        if(chance < rand.nextFloat()) {
            worldIn.addParticle(ParticleTypes.FLAME, pos.getX() + rand.nextDouble(),
                    pos.getY() + 0.5D, pos.getZ() + rand.nextDouble(),
                    0d,0.05d,0d);

            worldIn.addParticle(new BlockParticleData(ParticleTypes.FALLING_DUST, stateIn), pos.getX() + rand.nextDouble(),
                    pos.getY() + 0.5D, pos.getZ() + rand.nextDouble(),
                    0.0D, 0.05D, 0.0D);

            worldIn.addParticle(ParticleTypes.BUBBLE, pos.getX() + rand.nextDouble(),
                    pos.getY() + 0.5D, pos.getZ() + rand.nextDouble(),
                    0d,0.05d,0d);

        }
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable IBlockReader worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {

        if (Screen.hasShiftDown()) {
            tooltip.add(new StringTextComponent("\u00A7fWalk onto the block to teleport"));

        } else {
            tooltip.add(new StringTextComponent("Hold \u00A76SHIFT \u00A7ffor more Information!"));

        }

        super.addInformation(stack, worldIn, tooltip, flagIn);
    }

}