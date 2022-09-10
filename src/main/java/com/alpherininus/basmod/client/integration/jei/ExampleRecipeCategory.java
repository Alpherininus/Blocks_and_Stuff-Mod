package com.alpherininus.basmod.client.integration.jei;

import com.alpherininus.basmod.Basmod;
import com.alpherininus.basmod.common.recipes.jei.ExampleRecipe;
import com.alpherininus.basmod.core.init.ItemInit;
import com.mojang.blaze3d.matrix.MatrixStack;
import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.gui.IRecipeLayout;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.gui.ingredient.IGuiItemStackGroup;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.category.IRecipeCategory;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TranslationTextComponent;

public class ExampleRecipeCategory implements IRecipeCategory<ExampleRecipe> {

    public static final ResourceLocation ID = new ResourceLocation(Basmod.MOD_ID, "example_recipe_category");

    public final static ResourceLocation TEXTURE =
            new ResourceLocation(Basmod.MOD_ID, "textures/gui/example_recipe_category.png");

    private final IDrawable back;
    private final IDrawable icon;

    public ExampleRecipeCategory(IGuiHelper helper) {
        this.back = helper.createDrawable(TEXTURE, 0, 0, 180, 100);
        this.icon = helper.createDrawableIngredient(new ItemStack(ItemInit.EXPERIMENTAL_ITEM.get()));
    }

    @Override
    public ResourceLocation getUid() {
        return ID;
    }

    @Override
    public Class<? extends ExampleRecipe> getRecipeClass() {
        return ExampleRecipe.class;
    }

    @Override
    public String getTitle() {
        return new TranslationTextComponent("category." + Basmod.MOD_ID + ".example_recipe").getString();
    }

    @Override
    public IDrawable getBackground() {
        return this.back;
    }

    @Override
    public IDrawable getIcon() {
        return this.icon;
    }

    @Override
    public void setIngredients(ExampleRecipe recipe, IIngredients ingredients) {
        ingredients.setInputIngredients(recipe.getIngredients());
        ingredients.setOutput(VanillaTypes.ITEM, recipe.getRecipeOutput());
    }

    @Override
    public void draw(ExampleRecipe recipe, MatrixStack matrixStack, double mouseX, double mouseY) {
        IRecipeCategory.super.draw(recipe, matrixStack, mouseX, mouseY);
    }

    @Override
    public void setRecipe(IRecipeLayout recipeLayout, ExampleRecipe recipe, IIngredients ingredients) {
        IGuiItemStackGroup itemStackGroup = recipeLayout.getItemStacks();

        itemStackGroup.init(0, true, 0, 0);
        itemStackGroup.init(1, true, 0, 36);
        itemStackGroup.init(2, false, 60, 18);

        itemStackGroup.set(ingredients);
    }

}