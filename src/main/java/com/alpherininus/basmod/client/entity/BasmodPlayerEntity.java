package com.alpherininus.basmod.client.entity;

import com.mojang.authlib.GameProfile;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.ListNBT;
import net.minecraft.network.IPacket;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.network.play.server.SSpawnMobPacket;
import net.minecraft.util.SharedConstants;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

import javax.annotation.Nonnull;
import java.util.Objects;

public class BasmodPlayerEntity extends PlayerEntity {
    private static final DataParameter<Integer> MANA = EntityDataManager.createKey(BasmodPlayerEntity.class, DataSerializers.VARINT);

    private short sleepTimer;

    public BasmodPlayerEntity(World world, BlockPos pos, float yaw, GameProfile profile) {
        super(world, pos, yaw, profile);
    }

    protected void registerData() {
        this.dataManager.register(MANA, 1);
    }

    public void readAdditional(CompoundNBT compound) {
        super.readAdditional(compound);
        compound.putInt("Mana", this.getMana());

        this.setUniqueId(getUUID(this.getGameProfile()));
        ListNBT listnbt = compound.getList("Inventory", 10);
        this.inventory.read(listnbt);
        this.inventory.currentItem = compound.getInt("SelectedItemSlot");
        this.sleepTimer = compound.getShort("SleepTimer");
        this.experience = compound.getFloat("XpP");
        this.experienceLevel = compound.getInt("XpLevel");
        this.experienceTotal = compound.getInt("XpTotal");
        this.xpSeed = compound.getInt("XpSeed");
        if (this.xpSeed == 0) {
            this.xpSeed = this.rand.nextInt();
        }

        this.setScore(compound.getInt("Score"));
        this.foodStats.read(compound);
        this.abilities.read(compound);
        Objects.requireNonNull(this.getAttribute(Attributes.MOVEMENT_SPEED)).setBaseValue(this.abilities.getWalkSpeed());
        if (compound.contains("EnderItems", 9)) {
            this.enterChestInventory.read(compound.getList("EnderItems", 10));
        }

        if (compound.contains("ShoulderEntityLeft", 10)) {
            this.setLeftShoulderEntity(compound.getCompound("ShoulderEntityLeft"));
        }

        if (compound.contains("ShoulderEntityRight", 10)) {
            this.setRightShoulderEntity(compound.getCompound("ShoulderEntityRight"));
        }

    }

    public void writeAdditional(CompoundNBT compound) {
        super.writeAdditional(compound);
        // compound.putInt("Mana", this.getMana());

        compound.putInt("DataVersion", SharedConstants.getVersion().getWorldVersion());
        compound.put("Inventory", this.inventory.write(new ListNBT()));
        compound.putInt("SelectedItemSlot", this.inventory.currentItem);
        compound.putShort("SleepTimer", this.sleepTimer);
        compound.putFloat("XpP", this.experience);
        compound.putInt("XpLevel", this.experienceLevel);
        compound.putInt("XpTotal", this.experienceTotal);
        compound.putInt("XpSeed", this.xpSeed);
        compound.putInt("Score", this.getScore());
        this.foodStats.write(compound);
        this.abilities.write(compound);
        compound.put("EnderItems", this.enterChestInventory.write());
        if (!this.getLeftShoulderEntity().isEmpty()) {
            compound.put("ShoulderEntityLeft", this.getLeftShoulderEntity());
        }

        if (!this.getRightShoulderEntity().isEmpty()) {
            compound.put("ShoulderEntityRight", this.getRightShoulderEntity());
        }

        if (compound.contains("Mana", 99)) {
            this.setMana(compound.getInt("Mana"));
        }

    }

    @Override
    public boolean isSpectator() {
        return false;
    }

    @Override
    public boolean isCreative() {
        return false;
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
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

    public int setMana(int mana) {
        this.dataManager.set(MANA, MathHelper.clamp(mana, 0, this.getMaxMana()));
        return 0;
    }

    public final int getMaxMana() {
        return 88;
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    @Nonnull
    @Override
    public IPacket<?> createSpawnPacket() {
        LivingEntity entityIn = null;
        assert false;
        return new SSpawnMobPacket(entityIn);
    }
}
