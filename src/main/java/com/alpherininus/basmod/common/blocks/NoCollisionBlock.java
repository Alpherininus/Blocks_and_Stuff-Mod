package com.alpherininus.basmod.common.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.StairsBlock;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;

public class NoCollisionBlock extends Block {
    public NoCollisionBlock(Properties p) {
        super(p);
    }

    @Override
    public VoxelShape getCollisionShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        Entity entity = context.getEntity();
        if (entity instanceof PlayerEntity && entity.isCrouching()) {
            return VoxelShapes.empty(); // Spieler sneakt, keine Kollision
        }
        return super.getCollisionShape(state, worldIn, pos, context);
    }

    @Override
    public void onEntityCollision(BlockState state, net.minecraft.world.World worldIn, BlockPos pos, Entity entityIn) {
        // Aktionen ausführen, wenn eine Entität mit dem Block kollidiert
    }
}
