package com.alpherininus.basmod.core.util.materials;

import net.minecraft.item.IItemTier;
import net.minecraft.item.Items;
import net.minecraft.item.crafting.Ingredient;

import java.util.function.Supplier;

public enum BasmodToolMaterial implements IItemTier {

	A_TOOL_MATERIAL(0, 60, 15.0f, 0.0f, 15, () -> Ingredient.fromItems(Items.IRON_INGOT)),
	B_TOOL_MATERIAL(0, 120, 15.0f, 0.0f, 15, () -> Ingredient.fromItems(Items.IRON_INGOT)),
	C_TOOL_MATERIAL(0, 240, 15.0f, 0.0f, 15, () -> Ingredient.fromItems(Items.IRON_INGOT)),
	D_TOOL_MATERIAL(0, 480, 15.0f, 0.0f, 15, () -> Ingredient.fromItems(Items.IRON_INGOT)),
	E_TOOL_MATERIAL(0, 720, 15.0f, 0.0f, 15, () -> Ingredient.fromItems(Items.IRON_INGOT)),
	F_TOOL_MATERIAL(0, 960, 15.0f, 0.0f, 5, () -> Ingredient.fromItems(Items.IRON_INGOT)),
	G_TOOL_MATERIAL(0, 1200, 15.0f, 0.0f, 5, () -> Ingredient.fromItems(Items.IRON_INGOT)),
	H_TOOL_MATERIAL(0, 1440, 15.0f, 0.0f, 5, () -> Ingredient.fromItems(Items.IRON_INGOT)),
	I_TOOL_MATERIAL(0, 1680, 15.0f, 0.0f, 5, () -> Ingredient.fromItems(Items.IRON_INGOT)),
	J_TOOL_MATERIAL(0, 1920, 15.0f, 0.0f, 5, () -> Ingredient.fromItems(Items.IRON_INGOT)),
	K_TOOL_MATERIAL(0, 2160, 15.0f, 0.0f, 14, () -> Ingredient.fromItems(Items.IRON_INGOT)),
	L_TOOL_MATERIAL(0, 2400, 15.0f, 0.0f, 14, () -> Ingredient.fromItems(Items.IRON_INGOT)),
	M_TOOL_MATERIAL(0, 2640, 15.0f, 0.0f, 14, () -> Ingredient.fromItems(Items.IRON_INGOT)),
	N_TOOL_MATERIAL(0, 2280, 15.0f, 0.0f, 14, () -> Ingredient.fromItems(Items.IRON_INGOT)),
	O_TOOL_MATERIAL(0, 3120, 15.0f, 0.0f, 14, () -> Ingredient.fromItems(Items.IRON_INGOT)),
	P_TOOL_MATERIAL(0, 3360, 15.0f, 0.0f, 10, () -> Ingredient.fromItems(Items.IRON_INGOT)),
	Q_TOOL_MATERIAL(0, 3600, 15.0f, 0.0f, 10, () -> Ingredient.fromItems(Items.IRON_INGOT)),
	R_TOOL_MATERIAL(0, 3840, 15.0f, 0.0f, 10, () -> Ingredient.fromItems(Items.IRON_INGOT)),
	S_TOOL_MATERIAL(0, 4080, 15.0f, 0.0f, 10, () -> Ingredient.fromItems(Items.IRON_INGOT)),
	T_TOOL_MATERIAL(0, 4320, 15.0f, 0.0f, 10, () -> Ingredient.fromItems(Items.IRON_INGOT)),
	U_TOOL_MATERIAL(0, 4560, 15.0f, 0.0f, 10, () -> Ingredient.fromItems(Items.IRON_INGOT)),
	V_TOOL_MATERIAL(0, 4800, 15.0f, 0.0f, 10, () -> Ingredient.fromItems(Items.IRON_INGOT)),
	W_TOOL_MATERIAL(0, 5040, 15.0f, 0.0f, 10, () -> Ingredient.fromItems(Items.IRON_INGOT)),
	X_TOOL_MATERIAL(0, 5280, 15.0f, 0.0f, 10, () -> Ingredient.fromItems(Items.IRON_INGOT)),
	Y_TOOL_MATERIAL(0, 5520, 15.0f, 0.0f, 10, () -> Ingredient.fromItems(Items.IRON_INGOT)),
	Z_TOOL_MATERIAL(0, 5760, 15.0f, 0.0f, 22, () -> Ingredient.fromItems(Items.IRON_INGOT)),
	ZATOOL_MATERIAL(0, 6000, 15.0f, 0.0f, 22, () -> Ingredient.fromItems(Items.IRON_INGOT)),

	SUBLIME_SWORD_MATERIAL(0, 50, 15.0f, 0.0f, 15, () -> Ingredient.fromItems(Items.IRON_INGOT)),
	CREATOR_SWORD_MATERIAL(0, 25, 10.0f, 0.0f, 5, () -> Ingredient.fromItems(Items.IRON_INGOT)),

	CSWORD_MATERIAL(0, 2500, 10.0f, 0.0f, 5, () -> Ingredient.fromItems(Items.IRON_INGOT));


	private final int harvestLevel;
	private final int maxUses;
	private final float efficiency;
	private final float attackDamage;
	private final int enchantability;
	private final Ingredient repairMaterial;

	BasmodToolMaterial(int harvestLevel, int maxUses, float efficiency, float attackDamage, int enchantability,
					   Supplier<Ingredient> repairMaterial) {
		this.harvestLevel = harvestLevel;
		this.maxUses = maxUses;
		this.efficiency = efficiency;
		this.attackDamage = attackDamage;
		this.enchantability = enchantability;
		this.repairMaterial = repairMaterial.get();

	}

	@Override
	public int getMaxUses() {
		return this.maxUses;
	}

	@Override
	public float getEfficiency() {
		return this.efficiency;
	}

	@Override
	public float getAttackDamage() {
		return this.attackDamage;
	}

	@Override
	public int getHarvestLevel() {
		return this.harvestLevel;
	}

	@Override
	public int getEnchantability() {
		return this.enchantability;
	}

	@Override
	public Ingredient getRepairMaterial() {
		return this.repairMaterial;
	}

}
