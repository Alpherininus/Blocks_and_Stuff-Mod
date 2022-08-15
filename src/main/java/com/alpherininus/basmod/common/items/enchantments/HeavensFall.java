package com.alpherininus.basmod.common.items.enchantments;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentType;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.IFormattableTextComponent;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.server.ServerWorld;

public class HeavensFall extends Enchantment {

    public HeavensFall(Rarity rarityIn, EnchantmentType typeIn, EquipmentSlotType... slots) {
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

                EntityType.TRIDENT.spawn(world, null, player, pos.up(4).north(1),
                        SpawnReason.TRIGGERED, true, true);
                EntityType.TRIDENT.spawn(world, null, player, pos.up(4).east(1),
                        SpawnReason.TRIGGERED, true, true);
                EntityType.TRIDENT.spawn(world, null, player, pos.up(4).south(1),
                        SpawnReason.TRIGGERED, true, true);
                EntityType.TRIDENT.spawn(world, null, player, pos.up(4).west(1),
                        SpawnReason.TRIGGERED, true, true);

                EntityType.TRIDENT.spawn(world, null, player, pos.up(4).north(2),
                        SpawnReason.TRIGGERED, true, true);
                EntityType.TRIDENT.spawn(world, null, player, pos.up(4).east(2),
                        SpawnReason.TRIGGERED, true, true);
                EntityType.TRIDENT.spawn(world, null, player, pos.up(4).south(2),
                        SpawnReason.TRIGGERED, true, true);
                EntityType.TRIDENT.spawn(world, null, player, pos.up(4).west(2),
                        SpawnReason.TRIGGERED, true, true);

                EntityType.TRIDENT.spawn(world, null, player, pos.up(8).north(1),
                        SpawnReason.TRIGGERED, true, true);
                EntityType.TRIDENT.spawn(world, null, player, pos.up(8).east(1),
                        SpawnReason.TRIGGERED, true, true);
                EntityType.TRIDENT.spawn(world, null, player, pos.up(8).south(1),
                        SpawnReason.TRIGGERED, true, true);
                EntityType.TRIDENT.spawn(world, null, player, pos.up(8).west(1),
                        SpawnReason.TRIGGERED, true, true);

                EntityType.TRIDENT.spawn(world, null, player, pos.up(8).north(2),
                        SpawnReason.TRIGGERED, true, true);
                EntityType.TRIDENT.spawn(world, null, player, pos.up(8).east(2),
                        SpawnReason.TRIGGERED, true, true);
                EntityType.TRIDENT.spawn(world, null, player, pos.up(8).south(2),
                        SpawnReason.TRIGGERED, true, true);
                EntityType.TRIDENT.spawn(world, null, player, pos.up(8).west(2),
                        SpawnReason.TRIGGERED, true, true);
            }

        }

        super.onEntityDamaged(user, target, level);
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
    public void onUserHurt(LivingEntity user, Entity attacker, int level) {
    }

    @Override
    public int calcModifierDamage(int level, DamageSource source) {
        return 1;
    }

}