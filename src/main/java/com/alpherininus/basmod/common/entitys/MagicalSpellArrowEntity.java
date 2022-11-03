package com.alpherininus.basmod.common.entitys;

import com.alpherininus.basmod.core.init.BlockInit;
import com.alpherininus.basmod.core.init.ItemInit;
import net.minecraft.block.BlockState;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.AbstractArrowEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.network.IPacket;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkHooks;

public class MagicalSpellArrowEntity extends AbstractArrowEntity {
    protected int timeInGround;

    public MagicalSpellArrowEntity(EntityType<? extends AbstractArrowEntity> type, World worldIn) {
        super(type, worldIn);
    }

    protected MagicalSpellArrowEntity(EntityType<? extends AbstractArrowEntity> type, double x, double y, double z, World worldIn) {
        super(type, x, y, z, worldIn);
    }

    public MagicalSpellArrowEntity(EntityType<? extends AbstractArrowEntity> type, LivingEntity shooter, World worldIn) {
        super(type, shooter, worldIn);
    }

    @Override
    protected ItemStack getArrowStack() {
        return new ItemStack(ItemInit.MAGICAL_SPELL_ARROWITEM.get());
    }

    protected boolean canTriggerWalking() {
        return true;
    }

    @Override
    protected void onInsideBlock(BlockState state) {
        BlockPos pos = new BlockPos(this.getPosX(), this.getPosY(), this.getPosZ());

        if (state.getBlock() == BlockInit.BOMB_BOX_LVL0.get()) {
            world.destroyBlock(pos, false);
        } else if (state.getBlock() == BlockInit.BOMB_BOX_LVL1.get()) {
            world.destroyBlock(pos, false);
        } else if (state.getBlock() == BlockInit.BOMB_BOX_LVL2.get()) {
            world.destroyBlock(pos, false);
        } else {
            return;
        }

        super.onInsideBlock(state);
    }

    @Override
    protected void onImpact(RayTraceResult result) {
        super.onImpact(result);
    }

    @Override
    public void tick() {

        if (this.timeInGround > 60) {
            this.remove();
        }
        super.tick();
    }

    @Override
    public IPacket<?> createSpawnPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }

}
