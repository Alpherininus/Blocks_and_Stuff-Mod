package com.alpherininus.basmod.common.materials;

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

    MODELARMORS("model", 7, new int[] {2, 5, 6, 2}, 12,
    SoundEvents.ITEM_ARMOR_EQUIP_CHAIN, 1.0f, 0.0f, () -> Ingredient.fromItems(ItemInit.EXPERIMENTAL_ITEM.get())),

    A_ARMOR_MATERIAL("material_a", 7, new int[] {2, 5, 6, 2}, 12,
    SoundEvents.ITEM_ARMOR_EQUIP_CHAIN, 1.0f, 0.0f, () -> Ingredient.fromItems(ItemInit.EXPERIMENTAL_ITEM.get())),

    B_ARMOR_MATERIAL("material_b", 7, new int[] {2, 5, 6, 2}, 12,
    SoundEvents.ITEM_ARMOR_EQUIP_CHAIN, 1.0f, 0.0f, () -> Ingredient.fromItems(ItemInit.EXPERIMENTAL_ITEM.get())),

    C_ARMOR_MATERIAL("material_c", 7, new int[] {2, 5, 6, 2}, 12,
    SoundEvents.ITEM_ARMOR_EQUIP_CHAIN, 1.0f, 0.0f, () -> Ingredient.fromItems(ItemInit.EXPERIMENTAL_ITEM.get())),

    D_ARMOR_MATERIAL("material_d", 7, new int[] {2, 5, 6, 2}, 12,
    SoundEvents.ITEM_ARMOR_EQUIP_CHAIN, 1.0f, 0.0f, () -> Ingredient.fromItems(ItemInit.EXPERIMENTAL_ITEM.get())),

    E_ARMOR_MATERIAL("material_e", 7, new int[] {2, 5, 6, 2}, 12,
            SoundEvents.ITEM_ARMOR_EQUIP_CHAIN, 1.0f, 0.0f, () -> Ingredient.fromItems(ItemInit.EXPERIMENTAL_ITEM.get())),

    F_ARMOR_MATERIAL("material_f", 7, new int[] {2, 5, 6, 2}, 12,
            SoundEvents.ITEM_ARMOR_EQUIP_CHAIN, 1.0f, 0.0f, () -> Ingredient.fromItems(ItemInit.EXPERIMENTAL_ITEM.get())),

    G_ARMOR_MATERIAL("material_g", 7, new int[] {2, 5, 6, 2}, 12,
            SoundEvents.ITEM_ARMOR_EQUIP_CHAIN, 1.0f, 0.0f, () -> Ingredient.fromItems(ItemInit.EXPERIMENTAL_ITEM.get())),

    H_ARMOR_MATERIAL("material_h", 7, new int[] {2, 5, 6, 2}, 12,
            SoundEvents.ITEM_ARMOR_EQUIP_CHAIN, 1.0f, 0.0f, () -> Ingredient.fromItems(ItemInit.EXPERIMENTAL_ITEM.get())),

    I_ARMOR_MATERIAL("material_i", 7, new int[] {2, 5, 6, 2}, 12,
            SoundEvents.ITEM_ARMOR_EQUIP_CHAIN, 1.0f, 0.0f, () -> Ingredient.fromItems(ItemInit.EXPERIMENTAL_ITEM.get())),

    J_ARMOR_MATERIAL("material_j", 7, new int[] {2, 5, 6, 2}, 12,
            SoundEvents.ITEM_ARMOR_EQUIP_CHAIN, 1.0f, 0.0f, () -> Ingredient.fromItems(ItemInit.EXPERIMENTAL_ITEM.get())),

    K_ARMOR_MATERIAL("material_k", 7, new int[] {2, 5, 6, 2}, 12,
            SoundEvents.ITEM_ARMOR_EQUIP_CHAIN, 1.0f, 0.0f, () -> Ingredient.fromItems(ItemInit.EXPERIMENTAL_ITEM.get())),

    L_ARMOR_MATERIAL("material_l", 7, new int[] {2, 5, 6, 2}, 12,
            SoundEvents.ITEM_ARMOR_EQUIP_CHAIN, 1.0f, 0.0f, () -> Ingredient.fromItems(ItemInit.EXPERIMENTAL_ITEM.get())),

    M_ARMOR_MATERIAL("material_m", 7, new int[] {2, 5, 6, 2}, 12,
            SoundEvents.ITEM_ARMOR_EQUIP_CHAIN, 1.0f, 0.0f, () -> Ingredient.fromItems(ItemInit.EXPERIMENTAL_ITEM.get())),

    N_ARMOR_MATERIAL("material_n", 7, new int[] {2, 5, 6, 2}, 12,
            SoundEvents.ITEM_ARMOR_EQUIP_CHAIN, 1.0f, 0.0f, () -> Ingredient.fromItems(ItemInit.EXPERIMENTAL_ITEM.get())),

    O_ARMOR_MATERIAL("material_o", 7, new int[] {2, 5, 6, 2}, 12,
            SoundEvents.ITEM_ARMOR_EQUIP_CHAIN, 1.0f, 0.0f, () -> Ingredient.fromItems(ItemInit.EXPERIMENTAL_ITEM.get())),

    P_ARMOR_MATERIAL("material_p", 7, new int[] {2, 5, 6, 2}, 12,
            SoundEvents.ITEM_ARMOR_EQUIP_CHAIN, 1.0f, 0.0f, () -> Ingredient.fromItems(ItemInit.EXPERIMENTAL_ITEM.get())),

    Q_ARMOR_MATERIAL("material_q", 7, new int[] {2, 5, 6, 2}, 12,
            SoundEvents.ITEM_ARMOR_EQUIP_CHAIN, 1.0f, 0.0f, () -> Ingredient.fromItems(ItemInit.EXPERIMENTAL_ITEM.get())),

    R_ARMOR_MATERIAL("material_r", 7, new int[] {2, 5, 6, 2}, 12,
            SoundEvents.ITEM_ARMOR_EQUIP_CHAIN, 1.0f, 0.0f, () -> Ingredient.fromItems(ItemInit.EXPERIMENTAL_ITEM.get())),

    S_ARMOR_MATERIAL("material_s", 7, new int[] {2, 5, 6, 2}, 12,
            SoundEvents.ITEM_ARMOR_EQUIP_CHAIN, 1.0f, 0.0f, () -> Ingredient.fromItems(ItemInit.EXPERIMENTAL_ITEM.get())),

    T_ARMOR_MATERIAL("material_t", 7, new int[] {2, 5, 6, 2}, 12,
            SoundEvents.ITEM_ARMOR_EQUIP_CHAIN, 1.0f, 0.0f, () -> Ingredient.fromItems(ItemInit.EXPERIMENTAL_ITEM.get())),

    U_ARMOR_MATERIAL("material_u", 7, new int[] {2, 5, 6, 2}, 12,
            SoundEvents.ITEM_ARMOR_EQUIP_CHAIN, 1.0f, 0.0f, () -> Ingredient.fromItems(ItemInit.EXPERIMENTAL_ITEM.get())),

    V_ARMOR_MATERIAL("material_v", 7, new int[] {2, 5, 6, 2}, 12,
            SoundEvents.ITEM_ARMOR_EQUIP_CHAIN, 1.0f, 0.0f, () -> Ingredient.fromItems(ItemInit.EXPERIMENTAL_ITEM.get())),

    W_ARMOR_MATERIAL("material_w", 7, new int[] {2, 5, 6, 2}, 12,
            SoundEvents.ITEM_ARMOR_EQUIP_CHAIN, 1.0f, 0.0f, () -> Ingredient.fromItems(ItemInit.EXPERIMENTAL_ITEM.get())),

    X_ARMOR_MATERIAL("material_x", 7, new int[] {2, 5, 6, 2}, 12,
            SoundEvents.ITEM_ARMOR_EQUIP_CHAIN, 1.0f, 0.0f, () -> Ingredient.fromItems(ItemInit.EXPERIMENTAL_ITEM.get())),

    Y_ARMOR_MATERIAL("material_y", 7, new int[] {2, 5, 6, 2}, 12,
            SoundEvents.ITEM_ARMOR_EQUIP_CHAIN, 1.0f, 0.0f, () -> Ingredient.fromItems(ItemInit.EXPERIMENTAL_ITEM.get())),

    Z_ARMOR_MATERIAL("material_z", 7, new int[] {2, 5, 6, 2}, 12,
    SoundEvents.ITEM_ARMOR_EQUIP_CHAIN, 1.0f, 0.0f, () -> Ingredient.fromItems(ItemInit.EXPERIMENTAL_ITEM.get()));

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

