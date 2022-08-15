package com.alpherininus.basmod.common.items.enchantments;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentType;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.IFormattableTextComponent;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.server.ServerWorld;

public class MjoenirAttack extends Enchantment {

    public MjoenirAttack(Rarity rarityIn, EnchantmentType typeIn, EquipmentSlotType... slots) {
        super(rarityIn, EnchantmentType.WEAPON, slots);
    }

    public int getMinEnchantability(int enchantmentLevel) {
        return 100 + enchantmentLevel * 70;
    }

    public int getMaxEnchantability(int enchantmentLevel) {
        return 500;
    }

    @Override
    public int getMaxLevel() {
        return 1;
    }

    @Override
    public void onEntityDamaged(LivingEntity user, Entity target, int level) {

        if (!user.world.isRemote()) {
            ServerWorld world = (ServerWorld) user.world;
            ServerPlayerEntity player = ((ServerPlayerEntity) user);
            BlockPos pos = target.getPosition();

            if (level == 1) {

                if (world.isThundering()) {
                    EntityType.LIGHTNING_BOLT.spawn(world, null, player, pos,
                            SpawnReason.TRIGGERED, true, true);
                }

            }
        }

    }

    @Override
    public ITextComponent getDisplayName(int level) {

        IFormattableTextComponent iformattabletextcomponent = new TranslationTextComponent(this.getName());

        if (this.isCurse()) {
            iformattabletextcomponent.mergeStyle(TextFormatting.OBFUSCATED);
        } else {
            iformattabletextcomponent.mergeStyle(TextFormatting.LIGHT_PURPLE);
        }

        if (level != 1 || this.getMaxLevel() != 1) {
            iformattabletextcomponent.appendString(" ").appendSibling(new TranslationTextComponent("enchantment.level." + level));
        }
        return iformattabletextcomponent;
    }

    @Override
    public boolean canVillagerTrade() {
        return true;
    }

}

