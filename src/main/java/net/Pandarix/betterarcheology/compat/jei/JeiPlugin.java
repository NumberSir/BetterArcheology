package net.Pandarix.betterarcheology.compat.jei;

import mezz.jei.api.IModPlugin;
import mezz.jei.api.registration.IGuiHandlerRegistration;
import mezz.jei.api.registration.IRecipeCatalystRegistration;
import mezz.jei.api.registration.IRecipeCategoryRegistration;
import mezz.jei.api.registration.IRecipeRegistration;
import net.Pandarix.betterarcheology.BetterArcheology;
import net.Pandarix.betterarcheology.block.ModBlocks;
import net.Pandarix.betterarcheology.compat.jei.recipe.IdentifyingRecipe;
import net.Pandarix.betterarcheology.screen.IdentifyingScreen;
import net.minecraft.client.MinecraftClient;
import net.minecraft.item.ItemStack;
import net.minecraft.recipe.RecipeManager;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class JeiPlugin implements IModPlugin
{
    @Override
    @NotNull
    public Identifier getPluginUid()
    {
        return new Identifier(BetterArcheology.MOD_ID, "jei_plugin");
    }

    @Override
    public void registerCategories(IRecipeCategoryRegistration registration)
    {
        registration.addRecipeCategories(new IdentifyingCategory(registration.getJeiHelpers().getGuiHelper()));
    }

    @Override
    public void registerRecipes(@NotNull IRecipeRegistration registration)
    {
        if (MinecraftClient.getInstance().world != null)
        {
            RecipeManager recipeManager = MinecraftClient.getInstance().world.getRecipeManager();

            List<IdentifyingRecipe> identifyingRecipes = new ArrayList<>(recipeManager.listAllOfType(IdentifyingRecipe.Type.INSTANCE));

            registration.addRecipes(IdentifyingCategory.IDENTIFYING_RECIPE_TYPE, identifyingRecipes);
        }
    }

    @Override
    public void registerGuiHandlers(IGuiHandlerRegistration registration)
    {
        registration.addRecipeClickArea(IdentifyingScreen.class, 51, 48, 74, 24,
                IdentifyingCategory.IDENTIFYING_RECIPE_TYPE);
    }

    @Override
    public void registerRecipeCatalysts(@NotNull IRecipeCatalystRegistration registration)
    {
        IModPlugin.super.registerRecipeCatalysts(registration);
        registration.addRecipeCatalyst(new ItemStack(ModBlocks.ARCHEOLOGY_TABLE), IdentifyingCategory.IDENTIFYING_RECIPE_TYPE);
    }
}