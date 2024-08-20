package net.Pandarix.betterarcheology.compat.jei.recipe;

import net.Pandarix.betterarcheology.BetterArcheology;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModRecipeSerializers
{
    private static final RecipeSerializer<IdentifyingRecipe> IDENTIFYING_SERIALIZER =
            Registry.register(Registries.RECIPE_SERIALIZER, new Identifier(BetterArcheology.MOD_ID, "identifying"),
                    IdentifyingRecipe.Serializer.INSTANCE);

    public static void registerSerializers()
    {
        BetterArcheology.LOGGER.info("Registering JEI Recipe Serializers for " + BetterArcheology.MOD_ID);
    }
}