package com.alpherininus.basmod.common.blocks;

import com.alpherininus.basmod.core.util.materials.blocktier.BlockHardness;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.HorizontalBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.passive.PigEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.World;

public class BasmodBlock extends HorizontalBlock {

    public BasmodBlock() {
        super(AbstractBlock.Properties.create(Material.CLAY, MaterialColor.BLUE)
                .sound(SoundType.SLIME)
                .hardnessAndResistance(BlockHardness.getWood())
                .slipperiness(0.8F)
                .notSolid());
    }

    @Override
    public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit) {

        if (!worldIn.isRemote) {
            if (player.experienceLevel <= 10 && !player.isCreative()) {
                worldIn.playSound(player, pos, SoundEvents.BLOCK_ANVIL_BREAK, SoundCategory.PLAYERS, 1.0f, 1.0f);
                return ActionResultType.FAIL;
            }

            if (!player.isCreative())
                player.giveExperiencePoints(-5);

            worldIn.playSound(player, pos, SoundEvents.AMBIENT_UNDERWATER_LOOP, SoundCategory.BLOCKS, 1.0f, 1.0f);
            for (int index = 0; index < this.RANDOM.nextInt(10) + 7; index++) {
                PigEntity pig = EntityType.PIG.create(worldIn);
                pig.setPosition(pos.getX() + this.RANDOM.nextInt(10) - 5,
                        pos.getY(), pos.getZ() + this.RANDOM.nextInt(10) - 5);
                pig.addTag("BasBlock-0");
                worldIn.addEntity(pig);
            }
            return ActionResultType.SUCCESS;
        }
        return ActionResultType.FAIL;
    }

}
