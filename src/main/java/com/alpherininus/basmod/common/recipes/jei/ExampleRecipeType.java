package com.alpherininus.basmod.common.recipes.jei;

import com.alpherininus.basmod.Basmod;
import net.minecraft.item.crafting.IRecipeType;

public class ExampleRecipeType implements IRecipeType<ExampleRecipe> {

    @Override
    public String toString() {
        return Basmod.MOD_ID + ":example_recipe";
    }

}