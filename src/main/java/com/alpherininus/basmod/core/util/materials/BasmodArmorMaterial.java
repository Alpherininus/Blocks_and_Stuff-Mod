package com.alpherininus.basmod.core.util.materials;

import com.alpherininus.basmod.Basmod;
import com.alpherininus.basmod.core.init.ItemInit;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.IArmorMaterial;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.LazyValue;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.function.Supplier;

public enum BasmodArmorMaterial implements IArmorMaterial {

    EXPERIMENTALITEM("experi", 7, new int[] {2, 5, 6, 2}, 12,
            SoundEvents.ITEM_ARMOR_EQUIP_CHAIN, 1.0f, 0.0f, () -> Ingredient.fromItems(ItemInit.EXPERIMENTAL_ITEM.get())),

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    A_ARMOR_MATERIAL("material_a", 7, new int[] {1, 4, 5, 1}, 9,
    SoundEvents.ITEM_ARMOR_EQUIP_CHAIN, 0.0f, 0.03f, () -> Ingredient.fromItems(ItemInit.EXPERIMENTAL_ITEM.get())),

    B_ARMOR_MATERIAL("material_b", 7, new int[] {1, 4, 5, 1}, 9,
    SoundEvents.ITEM_ARMOR_EQUIP_CHAIN, 0.0f, 0.05f, () -> Ingredient.fromItems(ItemInit.EXPERIMENTAL_ITEM.get())),

    C_ARMOR_MATERIAL("material_c", 7, new int[] {1, 4, 5, 1}, 9,
    SoundEvents.ITEM_ARMOR_EQUIP_CHAIN, 0.0f, 0.08f, () -> Ingredient.fromItems(ItemInit.EXPERIMENTAL_ITEM.get())),

    D_ARMOR_MATERIAL("material_d", 7, new int[] {2, 5, 6, 2}, 9,
    SoundEvents.ITEM_ARMOR_EQUIP_CHAIN, 0.0f, 0.10f, () -> Ingredient.fromItems(ItemInit.EXPERIMENTAL_ITEM.get())),

    E_ARMOR_MATERIAL("material_e", 7, new int[] {2, 5, 6, 2}, 9,
            SoundEvents.ITEM_ARMOR_EQUIP_CHAIN, 0.0f, 0.13f, () -> Ingredient.fromItems(ItemInit.EXPERIMENTAL_ITEM.get())),

    F_ARMOR_MATERIAL("material_f", 7, new int[] {2, 5, 6, 2}, 25,
            SoundEvents.ITEM_ARMOR_EQUIP_CHAIN, 0.0f, 0.15f, () -> Ingredient.fromItems(ItemInit.EXPERIMENTAL_ITEM.get())),

    G_ARMOR_MATERIAL("material_g", 7, new int[] {2, 5, 6, 2}, 25,
            SoundEvents.ITEM_ARMOR_EQUIP_CHAIN, 1.0f, 0.18f, () -> Ingredient.fromItems(ItemInit.EXPERIMENTAL_ITEM.get())),

    H_ARMOR_MATERIAL("material_h", 7, new int[] {3, 6, 7, 3}, 25,
            SoundEvents.ITEM_ARMOR_EQUIP_CHAIN, 1.0f, 0.20f, () -> Ingredient.fromItems(ItemInit.EXPERIMENTAL_ITEM.get())),

    I_ARMOR_MATERIAL("material_i", 7, new int[] {3, 6, 7, 3}, 25,
            SoundEvents.ITEM_ARMOR_EQUIP_CHAIN, 1.0f, 0.23f, () -> Ingredient.fromItems(ItemInit.EXPERIMENTAL_ITEM.get())),

    J_ARMOR_MATERIAL("material_j", 7, new int[] {3, 6, 7, 3}, 10,
            SoundEvents.ITEM_ARMOR_EQUIP_CHAIN, 1.0f, 0.25f, () -> Ingredient.fromItems(ItemInit.EXPERIMENTAL_ITEM.get())),

    K_ARMOR_MATERIAL("material_k", 7, new int[] {4, 7, 8, 4}, 10,
            SoundEvents.ITEM_ARMOR_EQUIP_CHAIN, 1.0f, 0.28f, () -> Ingredient.fromItems(ItemInit.EXPERIMENTAL_ITEM.get())),

    L_ARMOR_MATERIAL("material_l", 7, new int[] {4, 7, 8, 4}, 10,
            SoundEvents.ITEM_ARMOR_EQUIP_CHAIN, 1.0f, 0.30f, () -> Ingredient.fromItems(ItemInit.EXPERIMENTAL_ITEM.get())),

    M_ARMOR_MATERIAL("material_m", 7, new int[] {4, 7, 8, 4}, 10,
            SoundEvents.ITEM_ARMOR_EQUIP_CHAIN, 2.0f, 0.33f, () -> Ingredient.fromItems(ItemInit.EXPERIMENTAL_ITEM.get())),

    N_ARMOR_MATERIAL("material_n", 7, new int[] {5, 8, 9, 5}, 12,
            SoundEvents.ITEM_ARMOR_EQUIP_CHAIN, 2.0f, 0.35f, () -> Ingredient.fromItems(ItemInit.EXPERIMENTAL_ITEM.get())),

    O_ARMOR_MATERIAL("material_o", 7, new int[] {5, 8, 9, 5}, 12,
            SoundEvents.ITEM_ARMOR_EQUIP_CHAIN, 2.0f, 0.38f, () -> Ingredient.fromItems(ItemInit.EXPERIMENTAL_ITEM.get())),

    P_ARMOR_MATERIAL("material_p", 7, new int[] {5, 8, 9, 5}, 12,
            SoundEvents.ITEM_ARMOR_EQUIP_CHAIN, 2.0f, 0.40f, () -> Ingredient.fromItems(ItemInit.EXPERIMENTAL_ITEM.get())),

    Q_ARMOR_MATERIAL("material_q", 7, new int[] {6, 9, 10, 6}, 12,
            SoundEvents.ITEM_ARMOR_EQUIP_CHAIN, 2.0f, 43f, () -> Ingredient.fromItems(ItemInit.EXPERIMENTAL_ITEM.get())),

    R_ARMOR_MATERIAL("material_r", 7, new int[] {6, 9, 10, 6}, 15,
            SoundEvents.ITEM_ARMOR_EQUIP_CHAIN, 2.0f, 0.45f, () -> Ingredient.fromItems(ItemInit.EXPERIMENTAL_ITEM.get())),

    S_ARMOR_MATERIAL("material_s", 7, new int[] {6, 9, 10, 6}, 15,
            SoundEvents.ITEM_ARMOR_EQUIP_CHAIN, 3.0f, 0.48f, () -> Ingredient.fromItems(ItemInit.EXPERIMENTAL_ITEM.get())),

    T_ARMOR_MATERIAL("material_t", 7, new int[] {7, 10, 11, 7}, 15,
            SoundEvents.ITEM_ARMOR_EQUIP_CHAIN, 3.0f, 0.50f, () -> Ingredient.fromItems(ItemInit.EXPERIMENTAL_ITEM.get())),

    U_ARMOR_MATERIAL("material_u", 7, new int[] {7, 10, 11, 7}, 15,
            SoundEvents.ITEM_ARMOR_EQUIP_CHAIN, 3.0f, 0.53f, () -> Ingredient.fromItems(ItemInit.EXPERIMENTAL_ITEM.get())),

    V_ARMOR_MATERIAL("material_v", 7, new int[] {7, 10, 11, 7}, 15,
            SoundEvents.ITEM_ARMOR_EQUIP_CHAIN, 3.0f, 0.55f, () -> Ingredient.fromItems(ItemInit.EXPERIMENTAL_ITEM.get())),

    W_ARMOR_MATERIAL("material_w", 7, new int[] {8, 11, 12, 8}, 15,
            SoundEvents.ITEM_ARMOR_EQUIP_CHAIN, 3.0f, 0.58f, () -> Ingredient.fromItems(ItemInit.EXPERIMENTAL_ITEM.get())),

    X_ARMOR_MATERIAL("material_x", 7, new int[] {8, 11, 12, 8}, 15,
            SoundEvents.ITEM_ARMOR_EQUIP_CHAIN, 3.0f, 0.60f, () -> Ingredient.fromItems(ItemInit.EXPERIMENTAL_ITEM.get())),

    Y_ARMOR_MATERIAL("material_y", 7, new int[] {8, 11, 12, 8}, 15,
            SoundEvents.ITEM_ARMOR_EQUIP_CHAIN, 3.5f, 0.63f, () -> Ingredient.fromItems(ItemInit.EXPERIMENTAL_ITEM.get())),

    Z_ARMOR_MATERIAL("material_z", 7, new int[] {9, 13, 14, 9}, 15,
            SoundEvents.ITEM_ARMOR_EQUIP_CHAIN, 3.5f, 0.65f, () -> Ingredient.fromItems(ItemInit.EXPERIMENTAL_ITEM.get()));

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

    BasmodArmorMaterial(String name,
                        int maxDamageFactor,
                        int[] damageReductionAmountArray,
                        int enchantability,
                        SoundEvent soundEvent,
                        float toughness,
                        float knockbackResistance,
                        Supplier<Ingredient> repairMaterial) {

        this.name = name;
        this.maxDamageFactor = maxDamageFactor;
        this.damageReductionAmountArray = damageReductionAmountArray;
        this.enchantability = enchantability;
        this.soundEvent = soundEvent;
        this.toughness = toughness;
        this.knockbackResistance = knockbackResistance;
        this.repairMaterial = new LazyValue<>(repairMaterial);
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

}

