package com.alpherininus.basmod.core.util.materials;

import com.alpherininus.basmod.Basmod;
import com.alpherininus.basmod.core.init.ItemInit;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.IArmorMaterial;
import net.minecraft.item.Items;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.LazyValue;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.function.Supplier;

public enum BasmodArmorMaterial implements IArmorMaterial {

    EXPERIMENTALITEM("experi", 7, new int[] {420, 666, 69, 777}, 12,
            SoundEvents.ITEM_ARMOR_EQUIP_CHAIN, 1.0f, 0.0f, () -> Ingredient.fromItems(ItemInit.EXPERIMENTAL_ITEM.get()), 50.0f),

    BOSSSLAYER("bossslayer", 9, new int[] {999, 999, 999, 999}, 9,
            SoundEvents.ENTITY_WITHER_AMBIENT, 9.9f, 9.9f, () -> Ingredient.fromItems(Items.NETHERITE_INGOT), 5.0f),

    TOTEMOFTAKEOVER("totemoftakeover", 7, new int[] {0, 0, 9, 0}, 9,
            SoundEvents.ENTITY_VILLAGER_AMBIENT, -10.0f, 0.1f, () -> Ingredient.fromItems(ItemInit.EXPERIMENTAL_ITEM.get()), 0.0f),

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    A_ARMOR_MATERIAL("material_a", 7, new int[] {1, 4, 5, 1}, 9,
    SoundEvents.ITEM_ARMOR_EQUIP_CHAIN, 0.0f, 0.03f, () -> Ingredient.fromItems(ItemInit.EXPERIMENTAL_ITEM.get()), 0.0f),

    B_ARMOR_MATERIAL("material_b", 7, new int[] {1, 4, 5, 1}, 9,
    SoundEvents.ITEM_ARMOR_EQUIP_CHAIN, 0.0f, 0.05f, () -> Ingredient.fromItems(ItemInit.EXPERIMENTAL_ITEM.get()), 0.0f),

    C_ARMOR_MATERIAL("material_c", 7, new int[] {1, 4, 5, 1}, 9,
    SoundEvents.ITEM_ARMOR_EQUIP_CHAIN, 0.0f, 0.08f, () -> Ingredient.fromItems(ItemInit.EXPERIMENTAL_ITEM.get()), 0.0f),

    D_ARMOR_MATERIAL("material_d", 7, new int[] {2, 5, 6, 2}, 9,
    SoundEvents.ITEM_ARMOR_EQUIP_CHAIN, 0.0f, 0.10f, () -> Ingredient.fromItems(ItemInit.EXPERIMENTAL_ITEM.get()), 0.0f),

    E_ARMOR_MATERIAL("material_e", 7, new int[] {2, 5, 6, 2}, 9,
            SoundEvents.ITEM_ARMOR_EQUIP_CHAIN, 0.0f, 0.13f, () -> Ingredient.fromItems(ItemInit.EXPERIMENTAL_ITEM.get()), 0.0f),

    F_ARMOR_MATERIAL("material_f", 7, new int[] {2, 5, 6, 2}, 25,
            SoundEvents.ITEM_ARMOR_EQUIP_CHAIN, 0.0f, 0.15f, () -> Ingredient.fromItems(ItemInit.EXPERIMENTAL_ITEM.get()), 0.0f),

    G_ARMOR_MATERIAL("material_g", 7, new int[] {2, 5, 6, 2}, 25,
            SoundEvents.ITEM_ARMOR_EQUIP_CHAIN, 1.0f, 0.18f, () -> Ingredient.fromItems(ItemInit.EXPERIMENTAL_ITEM.get()), 0.0f),

    H_ARMOR_MATERIAL("material_h", 7, new int[] {3, 6, 7, 3}, 25,
            SoundEvents.ITEM_ARMOR_EQUIP_CHAIN, 1.0f, 0.20f, () -> Ingredient.fromItems(ItemInit.EXPERIMENTAL_ITEM.get()), 0.0f),

    I_ARMOR_MATERIAL("material_i", 7, new int[] {3, 6, 7, 3}, 25,
            SoundEvents.ITEM_ARMOR_EQUIP_CHAIN, 1.0f, 0.23f, () -> Ingredient.fromItems(ItemInit.EXPERIMENTAL_ITEM.get()), 0.0f),

    J_ARMOR_MATERIAL("material_j", 7, new int[] {3, 6, 7, 3}, 10,
            SoundEvents.ITEM_ARMOR_EQUIP_CHAIN, 1.0f, 0.25f, () -> Ingredient.fromItems(ItemInit.EXPERIMENTAL_ITEM.get()), 0.0f),

    K_ARMOR_MATERIAL("material_k", 7, new int[] {4, 7, 8, 4}, 10,
            SoundEvents.ITEM_ARMOR_EQUIP_CHAIN, 1.0f, 0.28f, () -> Ingredient.fromItems(ItemInit.EXPERIMENTAL_ITEM.get()), 0.0f),

    L_ARMOR_MATERIAL("material_l", 7, new int[] {4, 7, 8, 4}, 10,
            SoundEvents.ITEM_ARMOR_EQUIP_CHAIN, 1.0f, 0.30f, () -> Ingredient.fromItems(ItemInit.EXPERIMENTAL_ITEM.get()), 0.0f),

    M_ARMOR_MATERIAL("material_m", 7, new int[] {4, 7, 8, 4}, 10,
            SoundEvents.ITEM_ARMOR_EQUIP_CHAIN, 2.0f, 0.33f, () -> Ingredient.fromItems(ItemInit.EXPERIMENTAL_ITEM.get()), 0.0f),

    N_ARMOR_MATERIAL("material_n", 7, new int[] {5, 8, 9, 5}, 12,
            SoundEvents.ITEM_ARMOR_EQUIP_CHAIN, 2.0f, 0.35f, () -> Ingredient.fromItems(ItemInit.EXPERIMENTAL_ITEM.get()), 0.0f),

    O_ARMOR_MATERIAL("material_o", 7, new int[] {5, 8, 9, 5}, 12,
            SoundEvents.ITEM_ARMOR_EQUIP_CHAIN, 2.0f, 0.38f, () -> Ingredient.fromItems(ItemInit.EXPERIMENTAL_ITEM.get()), 0.0f),

    P_ARMOR_MATERIAL("material_p", 7, new int[] {5, 8, 9, 5}, 12,
            SoundEvents.ITEM_ARMOR_EQUIP_CHAIN, 2.0f, 0.40f, () -> Ingredient.fromItems(ItemInit.EXPERIMENTAL_ITEM.get()), 0.0f),

    Q_ARMOR_MATERIAL("material_q", 7, new int[] {6, 9, 10, 6}, 12,
            SoundEvents.ITEM_ARMOR_EQUIP_CHAIN, 2.0f, 43f, () -> Ingredient.fromItems(ItemInit.EXPERIMENTAL_ITEM.get()), 0.0f),

    R_ARMOR_MATERIAL("material_r", 7, new int[] {6, 9, 10, 6}, 15,
            SoundEvents.ITEM_ARMOR_EQUIP_CHAIN, 2.0f, 0.45f, () -> Ingredient.fromItems(ItemInit.EXPERIMENTAL_ITEM.get()), 0.0f),

    S_ARMOR_MATERIAL("material_s", 7, new int[] {6, 9, 10, 6}, 15,
            SoundEvents.ITEM_ARMOR_EQUIP_CHAIN, 3.0f, 0.48f, () -> Ingredient.fromItems(ItemInit.EXPERIMENTAL_ITEM.get()), 0.0f),

    T_ARMOR_MATERIAL("material_t", 7, new int[] {7, 10, 11, 7}, 15,
            SoundEvents.ITEM_ARMOR_EQUIP_CHAIN, 3.0f, 0.50f, () -> Ingredient.fromItems(ItemInit.EXPERIMENTAL_ITEM.get()), 0.0f),

    U_ARMOR_MATERIAL("material_u", 7, new int[] {7, 10, 11, 7}, 15,
            SoundEvents.ITEM_ARMOR_EQUIP_CHAIN, 3.0f, 0.53f, () -> Ingredient.fromItems(ItemInit.EXPERIMENTAL_ITEM.get()), 0.0f),

    V_ARMOR_MATERIAL("material_v", 7, new int[] {7, 10, 11, 7}, 15,
            SoundEvents.ITEM_ARMOR_EQUIP_CHAIN, 3.0f, 0.55f, () -> Ingredient.fromItems(ItemInit.EXPERIMENTAL_ITEM.get()), 0.0f),

    W_ARMOR_MATERIAL("material_w", 7, new int[] {8, 11, 12, 8}, 15,
            SoundEvents.ITEM_ARMOR_EQUIP_CHAIN, 3.0f, 0.58f, () -> Ingredient.fromItems(ItemInit.EXPERIMENTAL_ITEM.get()), 0.0f),

    X_ARMOR_MATERIAL("material_x", 7, new int[] {8, 11, 12, 8}, 15,
            SoundEvents.ITEM_ARMOR_EQUIP_CHAIN, 3.0f, 0.60f, () -> Ingredient.fromItems(ItemInit.EXPERIMENTAL_ITEM.get()), 0.0f),

    Y_ARMOR_MATERIAL("material_y", 7, new int[] {8, 11, 12, 8}, 15,
            SoundEvents.ITEM_ARMOR_EQUIP_CHAIN, 3.5f, 0.63f, () -> Ingredient.fromItems(ItemInit.EXPERIMENTAL_ITEM.get()), 0.0f),

    Z_ARMOR_MATERIAL("material_z", 7, new int[] {9, 13, 14, 9}, 15,
            SoundEvents.ITEM_ARMOR_EQUIP_CHAIN, 3.5f, 0.65f, () -> Ingredient.fromItems(ItemInit.EXPERIMENTAL_ITEM.get()), 0.0f);

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    private static final int[] MAX_DAMAGE_ARRAY = new int[]{13, 15, 16, 11};
    private final String name;
    private final int maxDamageFactor;
    private final int[] damageReductionAmountArray;
    private final int enchantability;
    private final SoundEvent soundEvent;
    private final float toughness;
    private final float knockbackResistance;
    private final LazyValue<Ingredient> repairMaterial;
    private final float healthboost;

    BasmodArmorMaterial(String name,
                        int maxDamageFactor,
                        int[] damageReductionAmountArray,
                        int enchantability,
                        SoundEvent soundEvent,
                        float toughness,
                        float knockbackResistance,
                        Supplier<Ingredient> repairMaterial,
                        float healthboost
    ) {

        this.name = name;
        this.maxDamageFactor = maxDamageFactor;
        this.damageReductionAmountArray = damageReductionAmountArray;
        this.enchantability = enchantability;
        this.soundEvent = soundEvent;
        this.toughness = toughness;
        this.knockbackResistance = knockbackResistance;
        this.repairMaterial = new LazyValue<>(repairMaterial);
        this.healthboost = healthboost;
    }

    public int getDurability(EquipmentSlotType slotIn) {
        return MAX_DAMAGE_ARRAY[slotIn.getIndex()] * this.maxDamageFactor;
    }

    public int getDamageReductionAmount(EquipmentSlotType slotIn) {
        return this.damageReductionAmountArray[slotIn.getIndex()];
    }

    public int getEnchantability() {
        return this.enchantability;
    }

    public SoundEvent getSoundEvent() {
        return this.soundEvent;
    }

    public Ingredient getRepairMaterial() {
        return this.repairMaterial.getValue();
    }

    @OnlyIn(Dist.CLIENT)
    public String getName() {
        return Basmod.MOD_ID + ":" + this.name;
    }

    public float getToughness() {
        return this.toughness;
    }

    public float getKnockbackResistance() {
        return this.knockbackResistance;
    }

    public float getHealthBoost() {
        return this.healthboost + 0.0f;
    }

}

