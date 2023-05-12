package com.alpherininus.basmod.common.entitys;

import net.minecraft.block.Blocks;
import net.minecraft.block.SoundType;
import net.minecraft.entity.AgeableEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.goal.SwimGoal;
import net.minecraft.entity.ai.goal.WaterAvoidingRandomWalkingGoal;
import net.minecraft.entity.passive.horse.MuleEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.*;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class SeiorShellArmorEntity extends MuleEntity {
    private boolean allowStandSliding;

    public SeiorShellArmorEntity(EntityType<? extends MuleEntity> type, World worldIn) {
        super(type, worldIn);
    }

    public static AttributeModifierMap.MutableAttribute setCustomAttributes() {
        return MobEntity.func_233666_p_()
                .createMutableAttribute(Attributes.MAX_HEALTH, 40.0D)
                .createMutableAttribute(Attributes.HORSE_JUMP_STRENGTH, 0.5D)
                .createMutableAttribute(Attributes.MOVEMENT_SPEED, 0.25D)
                .createMutableAttribute(Attributes.ARMOR_TOUGHNESS, 100.0D);
    }


    @Override
    protected void registerGoals() {
        super.registerGoals();
        this.goalSelector.addGoal(0, new SwimGoal(this));
        this.goalSelector.addGoal(1, new WaterAvoidingRandomWalkingGoal(this, 0.3D));
    }

    @Override
    protected int getExperiencePoints(PlayerEntity player) {
        return 3 + this.world.rand.nextInt(5);
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    @Override
    protected double getModifiedMovementSpeed() {
        return 0.25D;
    }

    @Override
    protected boolean isMovementBlocked() {
        return true;
    }

    @Override
    public boolean isImmuneToFire() {
        return true;
    }

    @Override
    public boolean isArmor(ItemStack stack) {
        return true;
    }

    public boolean isHorseSaddled() {
        return true;
    }

    @Override
    public boolean hasChest() {
        return true;
    }

    @Override
    public boolean isTame() {
        return true;
    }

    @Override
    public boolean isAggressive() {
        return false;
    }

    @Override
    public AgeableEntity createChild(ServerWorld world, AgeableEntity mate) {
        return null;
    }

    @Override
    public boolean isEatingHaystack() {
        return false;
    }

    @Override
    public boolean canBreatheUnderwater() {
        return true;
    }

    @Override
    public boolean canEatGrass() {
        return false;
    }

    protected void dropInventory() {
        super.dropInventory();
        if (this.hasChest()) {
            if (!this.world.isRemote) {
                this.entityDropItem(this.activeItemStack.getContainerItem());
            }

            this.setChested(false);
        }

    }

    @Override
    protected boolean canDropLoot() {
        return true;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    @OnlyIn(Dist.CLIENT)
    public void setJumpPower(int jumpPowerIn) {
        if (this.isHorseSaddled()) {
            if (jumpPowerIn < 0) {
                jumpPowerIn = 0;
            } else {
                this.allowStandSliding = true;
            }

            if (jumpPowerIn >= 90) {
                this.jumpPower = 1.0F;
            } else {
                this.jumpPower = 0.4F + 0.4F * (float)jumpPowerIn / 90.0F;
            }

        }
    }

    public boolean canJump() {
        return true;
    }

    public void handleStartJump(int jumpPower) {
        this.allowStandSliding = true;
        this.playJumpSound();
    }

    public void handleStopJump() {
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public boolean canDespawn(double distanceToClosestPlayer) {
        return false;
    }

    public boolean onLivingFall(float distance, float damageMultiplier) {
        return false;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    protected void playGallopSound(SoundType p_190680_1_) {
        super.playGallopSound(p_190680_1_);
        if (this.rand.nextInt(10) == 0) {
            this.playSound(SoundEvents.ITEM_ARMOR_EQUIP_CHAIN, p_190680_1_.getVolume() * 0.6F, p_190680_1_.getPitch());
        }

        ItemStack stack = this.horseChest.getStackInSlot(1);
        if (isArmor(stack)) stack.onHorseArmorTick(world, this);
    }

    protected SoundEvent getAmbientSound() {
        super.getAmbientSound();
        return SoundEvents.ITEM_ARMOR_EQUIP_IRON;
    }

    protected SoundEvent getDeathSound() {
        super.getDeathSound();
        return SoundEvents.ITEM_ARMOR_EQUIP_ELYTRA;
    }

    protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
        super.getHurtSound(damageSourceIn);
        return SoundEvents.ENTITY_PLAYER_HURT;
    }

    protected SoundEvent getAngrySound() {
        super.getAngrySound();
        return SoundEvents.ENTITY_DONKEY_ANGRY;
    }

    @Override
    public SoundEvent getEatSound(ItemStack itemStackIn) {
        super.getEatSound(itemStackIn);
        return SoundEvents.BLOCK_ANVIL_DESTROY;
    }

    protected float getSoundVolume() {
        return 0.8F;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public boolean func_230276_fq_() {
        return true;
    }

    public boolean replaceItemInInventory(int inventorySlot, ItemStack itemStackIn) {
        if (inventorySlot == 499) {
            if (this.hasChest() && itemStackIn.isEmpty()) {
                this.setChested(false);
                this.initHorseChest();
                return true;
            }

            if (!this.hasChest() && itemStackIn.getItem() == Blocks.SHULKER_BOX.asItem()) {
                this.setChested(true);
                this.initHorseChest();
                return true;
            }
        }

        return super.replaceItemInInventory(inventorySlot, itemStackIn);
    }

    protected int getInventorySize() {
        return this.hasChest() ? 17 : super.getInventorySize();
    }


    public int getInventoryColumns() {
        return 5;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public ActionResultType getEntityInteractionResult(PlayerEntity playerIn, Hand hand) {
        ItemStack itemstack = playerIn.getHeldItem(hand);
        if (!this.isChild()) {
            if (this.isTame() && playerIn.isSecondaryUseActive()) {
                this.openGUI(playerIn);
                return ActionResultType.func_233537_a_(this.world.isRemote);
            }

            if (this.isBeingRidden()) {
                return super.getEntityInteractionResult(playerIn, hand);
            }
        }

        if (!itemstack.isEmpty()) {
            if (this.isBreedingItem(itemstack)) {
                return this.func_241395_b_(playerIn, itemstack);
            }

            if (!this.isTame()) {
                this.makeMad();
                return ActionResultType.func_233537_a_(this.world.isRemote);
            }

            if (!this.hasChest() && itemstack.getItem() == Blocks.SHULKER_BOX.asItem()) {
                this.setChested(true);
                this.playChestEquipSound();
                if (!playerIn.abilities.isCreativeMode) {
                    itemstack.shrink(1);
                }

                this.initHorseChest();
                return ActionResultType.func_233537_a_(this.world.isRemote);
            }

            if (!this.isChild() && !this.isHorseSaddled() && itemstack.getItem() == Items.SADDLE) {
                this.openGUI(playerIn);
                return ActionResultType.func_233537_a_(this.world.isRemote);
            }
        }

        if (this.isChild()) {
            return super.getEntityInteractionResult(playerIn, hand);
        } else {
            this.mountTo(playerIn);
            return ActionResultType.func_233537_a_(this.world.isRemote);
        }
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    private void eatingHorse() {
        if (!this.isSilent()) {
            SoundEvent soundevent = this.getEatSound(ItemStack.EMPTY);
            if (soundevent != null) {
                this.world.playSound(null, this.getPosX(), this.getPosY(), this.getPosZ(), soundevent, this.getSoundCategory(), 1.0F, 1.0F + (this.rand.nextFloat() - this.rand.nextFloat()) * 0.2F);
            }
        }

    }

    protected boolean handleEating(PlayerEntity player, ItemStack stack) {
        boolean flag = false;
        float f = 0.0F;
        int j = 0;
        Item item = stack.getItem();
        if (item == Items.IRON_INGOT) {
            f = 2.0F;
        } else if (item == Items.GOLD_INGOT) {
            f = 3.0F;
        } else if (item == Items.GOLD_NUGGET) {
            f = 1.0F;
        }

        if (this.getHealth() < this.getMaxHealth() && f > 0.0F) {
            this.heal(f);
            flag = true;
        }

        if (flag) {
            this.eatingHorse();
        }

        return flag;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////



}
