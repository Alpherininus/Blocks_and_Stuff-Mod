package com.alpherininus.basmod.common.blocks;

import com.alpherininus.basmod.core.util.materials.blocktier.BlockHardness;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;

public class BouncSourceBlockRed extends Block {

    public BouncSourceBlockRed() {
        super(AbstractBlock.Properties.create(Material.CLAY, MaterialColor.BLUE)
                .sound(SoundType.SLIME)
                .hardnessAndResistance(BlockHardness.getWood())
                .slipperiness(0.8F)
                .notSolid());
    }

    // Slime / Bed Properties
    public void onFallenUpon(World worldIn, BlockPos pos, Entity entityIn, float fallDistance) {
        if (entityIn.isSuppressingBounce()) {
            super.onFallenUpon(worldIn, pos, entityIn, fallDistance);
        } else {
            entityIn.onLivingFall(fallDistance, 0.0F);
        }

    }

    public void onLanded(IBlockReader worldIn, Entity entityIn) {
        if (entityIn.isSuppressingBounce()) {
            super.onLanded(worldIn, entityIn);
        } else {
            this.bounceEntity(entityIn);
        }

    }

    private void bounceEntity(Entity entity) {
        Vector3d vector3d = entity.getMotion();
        if (vector3d.y < 0.0D) {
            double d0 = entity instanceof LivingEntity ? 1.3D : 1.1D;
            entity.setMotion(vector3d.x, -vector3d.y * d0, vector3d.z);
        }

    }

    public void onEntityWalk(World worldIn, BlockPos pos, Entity entityIn) {
        double d0 = Math.abs(entityIn.getMotion().y);
        if (d0 < 0.1D && !entityIn.isSteppingCarefully()) {
            double d1 = 0.4D + d0 * 0.2D;
            entityIn.setMotion(entityIn.getMotion().mul(d1, 10.1D, d1));
        }

        super.onEntityWalk(worldIn, pos, entityIn);
    }
    //

    @Override
    public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit) {
        Minecraft mc = Minecraft.getInstance();

        return super.onBlockActivated(state, worldIn, pos, player, handIn, hit);
    }

}