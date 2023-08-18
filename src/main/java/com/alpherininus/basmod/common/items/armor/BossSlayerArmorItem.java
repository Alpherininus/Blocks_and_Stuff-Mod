package com.alpherininus.basmod.common.items.armor;

import com.alpherininus.basmod.core.init.ItemInit;
import com.alpherininus.basmod.core.util.materials.BasmodArmorMaterial;
import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.ai.attributes.Attribute;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.IArmorMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.potion.Effect;
import net.minecraft.potion.Effects;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nullable;
import java.util.List;
import java.util.UUID;

public class BossSlayerArmorItem extends ArmorItem {
    private static final UUID[] ARMOR_MODIFIERS = new UUID[]{UUID.fromString("845DB27C-C624-495F-8C9F-6020A9A58B6B"), UUID.fromString("D8499B04-0E66-4726-AB29-64469D734E0D"), UUID.fromString("9F3D476D-C118-4544-8365-64846904B48E"), UUID.fromString("2AD3F246-FEE1-4E67-B886-69FD380BB150")};
    private static final Effect[] NEGATIV_EFFECTS = {Effects.WITHER, Effects.BAD_OMEN, Effects.BLINDNESS, Effects.INSTANT_DAMAGE, Effects.MINING_FATIGUE, Effects.WEAKNESS, Effects.LEVITATION, Effects.UNLUCK, Effects.NAUSEA, Effects.HUNGER, Effects.POISON, Effects.SLOWNESS};
    private final Multimap<Attribute, AttributeModifier> attributemodifi;
    protected final EquipmentSlotType slot;
    protected final float knockbackResistance;
    protected final IArmorMaterial material;
    private final float damageReduceAmount;
    private final float toughness;
    private final float healthBoost;

    public BossSlayerArmorItem(BasmodArmorMaterial armorMaterial, EquipmentSlotType slotType, Properties properties) {
        super(armorMaterial, slotType, properties);
        this.material = armorMaterial;
        this.slot = slotType;
        this.damageReduceAmount = armorMaterial.getDamageReductionAmount(slot);
        this.toughness = armorMaterial.getToughness();
        this.knockbackResistance = armorMaterial.getKnockbackResistance();
        this.healthBoost = armorMaterial.getHealthBoost();

        ImmutableMultimap.Builder<Attribute, AttributeModifier> builder = ImmutableMultimap.builder();
        UUID uuid = ARMOR_MODIFIERS[slot.getIndex()];
        builder.put(Attributes.ARMOR, new AttributeModifier(uuid, "Armor modifier", this.damageReduceAmount, AttributeModifier.Operation.ADDITION));
        builder.put(Attributes.ARMOR_TOUGHNESS, new AttributeModifier(uuid, "Armor toughness", this.toughness, AttributeModifier.Operation.ADDITION));
        if (this.knockbackResistance > 0) {
            builder.put(Attributes.KNOCKBACK_RESISTANCE, new AttributeModifier(uuid, "Armor knockback resistance", this.knockbackResistance, AttributeModifier.Operation.ADDITION));
        }
        builder.put(Attributes.MAX_HEALTH, new AttributeModifier(uuid, "Armor health boost", this.healthBoost, AttributeModifier.Operation.ADDITION));

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

            if (player.isPotionActive(NEGATIV_EFFECTS[slot.getIndex()])) {
                player.removePotionEffect(NEGATIV_EFFECTS[slot.getIndex()]);
            }
        }
        super.onArmorTick(stack, world, player);
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
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
