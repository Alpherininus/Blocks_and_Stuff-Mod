package com.alpherininus.basmod.common.items.armor;

import com.alpherininus.basmod.core.init.EffectInit;
import com.alpherininus.basmod.core.init.ItemInit;
import com.alpherininus.basmod.core.util.materials.BasmodArmorMaterial;
import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.ai.attributes.Attribute;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.potion.Effects;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nullable;
import java.util.List;
import java.util.UUID;

public class BossSlayerArmorItem extends ArmorItem {
    private static final UUID[] ARMOR_MODIFIERS = new UUID[]{UUID.fromString("845DB27C-C624-495F-8C9F-6020A9A58B6B"), UUID.fromString("D8499B04-0E66-4726-AB29-64469D734E0D"), UUID.fromString("9F3D476D-C118-4544-8365-64846904B48E"), UUID.fromString("2AD3F246-FEE1-4E67-B886-69FD380BB150")};
    private final Multimap<Attribute, AttributeModifier> attributemodifi;

    public BossSlayerArmorItem(BasmodArmorMaterial armorMaterial, EquipmentSlotType slotType, Properties properties) {
        super(armorMaterial, slotType, properties);
        float healthBoost1 = armorMaterial.getHealthBoost();

        ImmutableMultimap.Builder<Attribute, AttributeModifier> builder = ImmutableMultimap.builder();
        UUID uuid = ARMOR_MODIFIERS[slot.getIndex()];
        builder.put(Attributes.MAX_HEALTH, new AttributeModifier(uuid, "Armor modifier", (double) healthBoost1, AttributeModifier.Operation.ADDITION));
        this.attributemodifi = builder.build();
    }

    @Override
    public void onArmorTick(ItemStack stack, World world, PlayerEntity player) {
        ItemStack head = player.getItemStackFromSlot(EquipmentSlotType.HEAD);
        ItemStack chest = player.getItemStackFromSlot(EquipmentSlotType.CHEST);
        ItemStack legs = player.getItemStackFromSlot(EquipmentSlotType.LEGS);
        ItemStack feet = player.getItemStackFromSlot(EquipmentSlotType.FEET);

        //Full Set
        if (head.getItem() == ItemInit.EXPERIMENTAL_HELMET.get() &&
                chest.getItem() == ItemInit.EXPERIMENTAL_CHESTPLATTE.get() &&
                legs.getItem() == ItemInit.EXPERIMENTAL_LEGGING.get() &&
                feet.getItem() == ItemInit.EXPERIMENTAL_BOOTS.get()) {

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
        super.onArmorTick(stack, world, player);
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
        if (Screen.hasControlDown()) {
            tooltip.add(new StringTextComponent("\u00A77If the player has equipped all pieces of armor,\nthe following effects apply:\n`Immune to all negative effects`"));

        } else {
            tooltip.add(new StringTextComponent("Hold \u00A76CONTROL \u00A7ffor more Information"));
        }
        super.addInformation(stack, worldIn, tooltip, flagIn);
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    @Override
    public boolean getIsRepairable(ItemStack toRepair, ItemStack repair) {
        return repair.getItem() == Items.NETHERITE_INGOT;
    }

    @Override
    public boolean isRepairable(ItemStack isRepairable) {
        return true;
    }

    @Override
    public float getXpRepairRatio(ItemStack stack) {
        return 5.5f;
    }

    public Multimap<Attribute, AttributeModifier> getAttributeModifiers(EquipmentSlotType equipmentSlot) {
        return equipmentSlot == this.slot ? this.attributemodifi : super.getAttributeModifiers(equipmentSlot);
    }
}
