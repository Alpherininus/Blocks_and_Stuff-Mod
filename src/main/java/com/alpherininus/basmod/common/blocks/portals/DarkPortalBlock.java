package com.alpherininus.basmod.common.blocks.portals;

import net.minecraft.block.*;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraft.particles.BlockParticleData;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.IBooleanFunction;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Random;

public class DarkPortalBlock extends Block {

    public DarkPortalBlock(Properties properties) {
        super(properties);
    }

    @Override
    public void onEntityWalk(World worldIn, BlockPos pos, Entity entityIn) {
        BlockState state = worldIn.getBlockState(pos);

        if (worldIn instanceof ServerWorld && !entityIn.isPassenger() && !entityIn.isBeingRidden() && entityIn.canChangeDimension() && VoxelShapes.compare(VoxelShapes.create(entityIn.getBoundingBox().offset((double)(-pos.getX()), (double)(-pos.getY()), (double)(-pos.getZ()))), state.getShape(worldIn, pos), IBooleanFunction.AND)) {
            RegistryKey<World> registrykey = worldIn.getDimensionKey() == World.THE_END ? World.OVERWORLD : World.THE_END;
            ServerWorld serverworld = ((ServerWorld)worldIn).getServer().getWorld(registrykey);
            if (serverworld == null) {
                return;
            }

            entityIn.changeDimension(serverworld);
        }


        super.onEntityWalk(worldIn, pos, entityIn);
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