package com.alpherininus.basmod.common.recipes;

import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.potion.Potion;
import net.minecraftforge.common.brewing.IBrewingRecipe;

import javax.annotation.Nonnull;

public class BasmodBrewingPotion implements IBrewingRecipe {
    private final Ingredient input;
    private final Ingredient ingredient;
    private final Potion output;

    public BasmodBrewingPotion(Ingredient input, Ingredient ingredient, Potion output) {
        this.input = input;
        this.ingredient = ingredient;
        this.output = output;
    }

    @Override
    public boolean isInput(@Nonnull ItemStack stack)
    {
        return this.input.test(stack);
    }

    @Override
    public ItemStack getOutput(ItemStack input, ItemStack ingredient)
    {
        return input;
    }

    public Ingredient getInput()
    {
        return input;
    }

    public Ingredient getIngredient()
    {
        return ingredient;
    }

    @Override
    public boolean isIngredient(ItemStack ingredient)
    {
        return this.ingredient.test(ingredient);
    }
}
