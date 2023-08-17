package com.alpherininus.basmod.common.items;

import com.alpherininus.basmod.core.init.EffectInit;
import com.alpherininus.basmod.core.init.ItemInit;
import net.minecraft.block.BlockState;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.IArmorMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Effects;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nullable;
import java.util.List;

public class TotemOfTakeover extends ArmorItem {
    public TotemOfTakeover(IArmorMaterial armorMaterial, EquipmentSlotType slot, Properties properties) {
        super(armorMaterial, slot, properties);
    }

    @Override
    public boolean hasEffect(ItemStack stack) {
        return true;
    }

    @Override
    public void onArmorTick(ItemStack stack, World world, PlayerEntity player) {
        ItemStack chest = player.getItemStackFromSlot(EquipmentSlotType.CHEST);

        if (chest.getItem() == ItemInit.TOTEM_OF_TAKEOVER.get()) {

            if (chest.isEnchanted() == Enchantments.BINDING_CURSE.isCurse()) {

                if (player.isPotionActive(Effects.WITHER)) {
                    player.removePotionEffect(Effects.WITHER);
                }
                if (player.isPotionActive(Effects.BAD_OMEN)) {
                    player.removePotionEffect(Effects.BAD_OMEN);
                }
                if (player.isPotionActive(Effects.BLINDNESS)) {
                    player.removePotionEffect(Effects.BLINDNESS);
                }
                if (player.isPotionActive(Effects.INSTANT_DAMAGE)) {
                    player.removePotionEffect(Effects.INSTANT_DAMAGE);
                }
                if (player.isPotionActive(Effects.MINING_FATIGUE)) {
                    player.removePotionEffect(Effects.MINING_FATIGUE);
                }
                if (player.isPotionActive(Effects.WEAKNESS)) {
                    player.removePotionEffect(Effects.WEAKNESS);
                }
                if (player.isPotionActive(Effects.LEVITATION)) {
                    player.removePotionEffect(Effects.LEVITATION);
                }
                if (player.isPotionActive(Effects.UNLUCK)) {
                    player.removePotionEffect(Effects.UNLUCK);
                }
                if (player.isPotionActive(Effects.NAUSEA)) {
                    player.removePotionEffect(Effects.NAUSEA);
                }
                if (player.isPotionActive(Effects.HUNGER)) {
                    player.removePotionEffect(Effects.HUNGER);
                }
                if (player.isPotionActive(Effects.POISON)) {
                    player.removePotionEffect(Effects.POISON);
                }
                if (player.isPotionActive(Effects.SLOWNESS)) {
                    player.removePotionEffect(Effects.SLOWNESS);
                }
                if (player.isPotionActive(EffectInit.FREEZE.get())) {
                    player.removePotionEffect(EffectInit.FREEZE.get());
                }
            }


        }

        super.onArmorTick(stack, world, player);
    }

    @Override
    public boolean canPlayerBreakBlockWhileHolding(BlockState blockState, World world, BlockPos pos, PlayerEntity player) {
        return false;
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
        if (Screen.hasControlDown()) {
            tooltip.add(new StringTextComponent("\u00A77This is a Armoritem"));

            if (stack.isEnchanted() == Enchantments.BINDING_CURSE.isCurse()) {
                tooltip.add(new StringTextComponent("\u00A77\nIf the item is enchanted with Curse of Binding\nthe player is resistant to ALL negative effects."));
            } else {
                tooltip.add(new StringTextComponent("\u00A77An equip totem of unimaginable power."));
            }
        } else {
            tooltip.add(new StringTextComponent("Hold \u00A76CONTROL \u00A7ffor more Information"));
        }
        super.addInformation(stack, worldIn, tooltip, flagIn);
    }
}
