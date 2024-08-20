package net.Pandarix.betterarcheology.compat.jei;

import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeIngredientRole;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.recipe.category.IRecipeCategory;
import net.Pandarix.betterarcheology.BetterArcheology;
import net.Pandarix.betterarcheology.block.ModBlocks;
import net.Pandarix.betterarcheology.compat.jei.recipe.IdentifyingRecipe;
import net.Pandarix.betterarcheology.item.ModItems;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class IdentifyingCategory implements IRecipeCategory<IdentifyingRecipe>
{
    public static final Identifier UID = new Identifier(BetterArcheology.MOD_ID, "identifying");
    public static final Identifier TEXTURE = new Identifier(BetterArcheology.MOD_ID,
            "textures/gui/archeology_table_overlay.png");

    public static final RecipeType<IdentifyingRecipe> IDENTIFYING_RECIPE_TYPE = new RecipeType<>(UID, IdentifyingRecipe.class);

    private final IDrawable background;
    private final IDrawable icon;

    public IdentifyingCategory(IGuiHelper helper)
    {
        this.background = helper.createDrawable(TEXTURE, 0, 0, 176, 85);
        this.icon = helper.createDrawableIngredient(VanillaTypes.ITEM_STACK, new ItemStack(ModBlocks.ARCHEOLOGY_TABLE));
    }

    @Override
    @NotNull
    public RecipeType<IdentifyingRecipe> getRecipeType()
    {
        return IDENTIFYING_RECIPE_TYPE;
    }

    @Override
    @NotNull
    public Text getTitle()
    {
        return Text.translatable("block.betterarcheology.archeology_table");
    }

    @Override
    @NotNull
    public IDrawable getBackground()
    {
        return this.background;
    }

    @Override
    @NotNull
    public IDrawable getIcon()
    {
        return this.icon;
    }

    @Override
    public void setRecipe(IRecipeLayoutBuilder builder, IdentifyingRecipe recipe, @NotNull IFocusGroup focuses)
    {
        builder.addSlot(RecipeIngredientRole.INPUT, 80, 20).addItemStacks(
                List.of(Items.BRUSH.getDefaultStack(), ModItems.IRON_BRUSH.getDefaultStack(), ModItems.DIAMOND_BRUSH.getDefaultStack(), ModItems.NETHERITE_BRUSH.getDefaultStack())
        );

        builder.addSlot(RecipeIngredientRole.INPUT, 26, 48).addIngredients(recipe.getIngredients().get(0));

        builder.addSlot(RecipeIngredientRole.OUTPUT, 134, 48).addItemStack(recipe.getResult());
    }
}