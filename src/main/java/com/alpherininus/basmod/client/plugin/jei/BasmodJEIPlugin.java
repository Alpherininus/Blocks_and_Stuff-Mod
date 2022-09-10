package com.alpherininus.basmod.client.plugin.jei;

import com.alpherininus.basmod.Basmod;
import com.alpherininus.basmod.common.recipes.jei.ExampleRecipe;
import com.alpherininus.basmod.core.init.RecipeInit;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.registration.IRecipeCategoryRegistration;
import mezz.jei.api.registration.IRecipeRegistration;
import net.minecraft.client.Minecraft;
import net.minecraft.item.crafting.RecipeManager;
import net.minecraft.util.ResourceLocation;

import java.util.Objects;
import java.util.stream.Collectors;

@JeiPlugin
public class BasmodJEIPlugin implements IModPlugin {

    private static final ResourceLocation PLUGIN_ID = new ResourceLocation(Basmod.MOD_ID, "jei_plugin");

    @Override
    public ResourceLocation getPluginUid() {
        return PLUGIN_ID;
    }

    @SuppressWarnings("resource")
    @Override
    public void registerCategories(IRecipeCategoryRegistration registration) {
        IGuiHelper helper = registration.getJeiHelpers().getGuiHelper();

        registration.addRecipeCategories(new ExampleRecipeCategory(helper));
    }

    @Override
    public void registerRecipes(IRecipeRegistration registration) {
        RecipeManager manager = Objects.requireNonNull(Minecraft.getInstance().world).getRecipeManager();

        registration.addRecipes(manager.getRecipesForType(RecipeInit.EXAMPLE_RECIPE).stream()
                .filter(r -> r instanceof ExampleRecipe)
                .collect(Collectors.toList()), ExampleRecipeCategory.ID);
    }

    //private static Collection<?> getRecipes(RecipeManager manager, IRecipeType<?> type) {
    //    return manager.getRecipes().parallelStream()
    //            .filter(recipe -> recipe.getType() == type).collect(Collectors.toList());
    //}

}
