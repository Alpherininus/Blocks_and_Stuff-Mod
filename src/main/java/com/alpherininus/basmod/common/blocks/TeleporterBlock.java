package com.alpherininus.basmod.common.blocks;

import com.alpherininus.basmod.core.util.materials.blocktier.BlockHardness;
import com.alpherininus.basmod.core.util.materials.blocktier.BlockResistance;
import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.client.Minecraft;
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
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.*;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.common.util.ITeleporter;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Random;
import java.util.function.Function;

public class TeleporterBlock extends Block {

    public TeleporterBlock() {
        super(AbstractBlock.Properties
                .create((Material.IRON), MaterialColor.GRAY)
                .hardnessAndResistance(BlockHardness.getIron(), BlockResistance.getIron())
                .harvestTool(ToolType.PICKAXE)
                .harvestLevel(1)
                .sound(SoundType.BONE).notSolid()
                .setLightLevel(BlockState -> 13));
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
            tooltip.add(new StringTextComponent("\uEff0 #\u00A76RIGHTCLICK \u00A7fto Teleport to Dimension."));

        } else {
            tooltip.add(new StringTextComponent("Hold \u00A76SHIFT \u00A7ffor more Information!"));

        }

        super.addInformation(stack, worldIn, tooltip, flagIn);
    }
}
