package com.alpherininus.basmod.client.entity;

import com.mojang.authlib.GameProfile;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.Difficulty;
import net.minecraft.world.GameRules;
import net.minecraft.world.World;

public class BasmodPlayerEntity extends PlayerEntity {
    private static final DataParameter<Integer> MANA = EntityDataManager.createKey(BasmodPlayerEntity.class, DataSerializers.VARINT);

    public BasmodPlayerEntity(World world, BlockPos pos, float yaw, GameProfile profile) {
        super(world, pos, yaw, profile);
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    protected void registerData() {
        this.dataManager.register(MANA, 0);
    }

    public void readAdditional(CompoundNBT compound) {
        compound.putInt("Mana", this.getMana());
    }

    public void writeAdditional(CompoundNBT compound) {
        // compound.putInt("Mana", this.getMana());
        if (compound.contains("Mana", 0)) {
            this.setMana(compound.getInt("Mana"));
        }
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    @Override
    public boolean isSpectator() {
        return false;
    }

    @Override
    public boolean isCreative() {
        return false;
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public void livingTick() {

        if (this.world.getDifficulty() == Difficulty.PEACEFUL && this.world.getGameRules().getBoolean(GameRules.NATURAL_REGENERATION)) {
            if (this.getMana() < this.getMaxMana() && this.ticksExisted % 20 == 0) {
                this.mana((int) 1.0F);
            }
        }
    }

    // Basmod

    public void mana(int manaAmount) {
        int f = this.getMana();
        if (f > 0.0F) {
            this.setMana(f + manaAmount);
        }
    }

    public int getMana() {
        return this.dataManager.get(MANA);
    }

    public void setMana(int mana) {
        this.dataManager.set(MANA, MathHelper.clamp(mana, 0, this.getMaxMana()));
    }

    public final int getMaxMana() {
        return 88;
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////

}
