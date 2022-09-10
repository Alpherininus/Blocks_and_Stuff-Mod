package com.alpherininus.basmod.common.recipes;

import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.Ingredient;
import net.minecraftforge.common.brewing.IBrewingRecipe;

import javax.annotation.Nonnull;

public class BasmodBrewing implements IBrewingRecipe {
    private final Ingredient input;
    private final Ingredient ingredient;
    private final ItemStack output;

    public BasmodBrewing(Ingredient input, Ingredient ingredient, ItemStack output) {
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
        return isInput(input) && isIngredient(ingredient) ? getOutput().copy() : ItemStack.EMPTY;
    }

    public Ingredient getInput()
    {
        return input;
    }

    public Ingredient getIngredient()
    {
        return ingredient;
    }

    public ItemStack getOutput()
    {
        return output;
    }

    @Override
    public boolean isIngredient(ItemStack ingredient)
    {
        return this.ingredient.test(ingredient);
    }
}
