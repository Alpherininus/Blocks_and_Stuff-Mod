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

public class TNTAttack extends Enchantment {
    public TNTAttack(Rarity rarityIn, EnchantmentType typeIn, EquipmentSlotType... slots) {
        super(rarityIn, typeIn, slots);
    }

    @Override
    public int getMaxLevel() {
        return 3;
    }

    @Override
    public int getMinEnchantability(int enchantmentLevel) {
        return 300;
    }
    @Override
    public int getMaxEnchantability(int enchantmentLevel) {
        return 550;
    }

    @Override
    public void onEntityDamaged(LivingEntity user, Entity target, int level) {

        if (!user.world.isRemote()) {
            ServerWorld world = (ServerWorld) user.world;
            ServerPlayerEntity player = ((ServerPlayerEntity) user);
            BlockPos pos = target.getPosition();

            if (level == 1) {
                // IItemProvider itemProv;
                EntityType.TNT.spawn(world, null, player, pos.up(4), SpawnReason.TRIGGERED, true, true);

            }

            if (level == 2) {
                EntityType.TNT.spawn(world, null, player, pos.up(4).north(1),
                        SpawnReason.TRIGGERED, true, true);
                EntityType.TNT.spawn(world, null, player, pos.up(4).east(1),
                        SpawnReason.TRIGGERED, true, true);
                EntityType.TNT.spawn(world, null, player, pos.up(4).south(1),
                        SpawnReason.TRIGGERED, true, true);
                EntityType.TNT.spawn(world, null, player, pos.up(4).west(1),
                        SpawnReason.TRIGGERED, true, true);
            }

            if (level == 3) {
                EntityType.TNT.spawn(world, null, player, pos.up(10).north(1),
                        SpawnReason.TRIGGERED, true, true);
                EntityType.TNT.spawn(world, null, player, pos.up(10).east(1),
                        SpawnReason.TRIGGERED, true, true);
                EntityType.TNT.spawn(world, null, player, pos.up(10).south(1),
                        SpawnReason.TRIGGERED, true, true);
                EntityType.TNT.spawn(world, null, player, pos.up(10).west(1),
                        SpawnReason.TRIGGERED, true, true);

                EntityType.TNT.spawn(world, null, player, pos.up(10).north(3),
                        SpawnReason.TRIGGERED, true, true);
                EntityType.TNT.spawn(world, null, player, pos.up(10).east(3),
                        SpawnReason.TRIGGERED, true, true);
                EntityType.TNT.spawn(world, null, player, pos.up(10).south(3),
                        SpawnReason.TRIGGERED, true, true);
                EntityType.TNT.spawn(world, null, player, pos.up(10).west(3),
                        SpawnReason.TRIGGERED, true, true);

                EntityType.TNT.spawn(world, null, player, pos.up(10).north(1),
                        SpawnReason.TRIGGERED, true, true);
                EntityType.TNT.spawn(world, null, player, pos.up(10).east(1),
                        SpawnReason.TRIGGERED, true, true);
                EntityType.TNT.spawn(world, null, player, pos.up(10).south(1),
                        SpawnReason.TRIGGERED, true, true);
                EntityType.TNT.spawn(world, null, player, pos.up(10).west(1),
                        SpawnReason.TRIGGERED, true, true);

                EntityType.TNT.spawn(world, null, player, pos.up(10).north(3),
                        SpawnReason.TRIGGERED, true, true);
                EntityType.TNT.spawn(world, null, player, pos.up(10).east(3),
                        SpawnReason.TRIGGERED, true, true);
                EntityType.TNT.spawn(world, null, player, pos.up(10).south(3),
                        SpawnReason.TRIGGERED, true, true);
                EntityType.TNT.spawn(world, null, player, pos.up(10).west(3),
                        SpawnReason.TRIGGERED, true, true);
            }
        }

        super.onEntityDamaged(user, target, level);
    }

    @Override
    public ITextComponent getDisplayName(int level) {

        IFormattableTextComponent iformattabletextcomponent = new TranslationTextComponent(this.getName());

        if (level == 1) {

            if (this.isCurse()) {
                iformattabletextcomponent.mergeStyle(TextFormatting.DARK_RED);
            } else {
                iformattabletextcomponent.mergeStyle(TextFormatting.GREEN);
            }
        }
        if (level == 2) {

            if (this.isCurse()) {
                iformattabletextcomponent.mergeStyle(TextFormatting.DARK_RED);
            } else {
                iformattabletextcomponent.mergeStyle(TextFormatting.YELLOW);
            }
        }

        if (level == 3) {

            if (this.isCurse()) {
                iformattabletextcomponent.mergeStyle(TextFormatting.DARK_RED);
            } else {
                iformattabletextcomponent.mergeStyle(TextFormatting.RED);
            }
        }

        if (level != 1 || this.getMaxLevel() != 1) {
            iformattabletextcomponent.appendString(" ").appendSibling(new TranslationTextComponent("enchantment.level." + level));
        }
        return iformattabletextcomponent;
    }

    @Override
    public void onUserHurt(LivingEntity user, Entity attacker, int level) {
        super.onUserHurt(user, attacker, level);
    }
}
