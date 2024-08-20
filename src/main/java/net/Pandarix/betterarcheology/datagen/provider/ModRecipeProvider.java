package net.Pandarix.betterarcheology.datagen.provider;

import net.Pandarix.betterarcheology.block.ModBlocks;
import net.Pandarix.betterarcheology.item.ModItems;
import net.Pandarix.betterarcheology.util.ModTags;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.block.Blocks;
import net.minecraft.data.server.recipe.CookingRecipeJsonBuilder;
import net.minecraft.data.server.recipe.RecipeJsonProvider;
import net.minecraft.data.server.recipe.ShapedRecipeJsonBuilder;
import net.minecraft.data.server.recipe.ShapelessRecipeJsonBuilder;
import net.minecraft.item.Items;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.registry.tag.ItemTags;

import java.util.function.Consumer;

public class ModRecipeProvider extends FabricRecipeProvider
{
    public ModRecipeProvider(FabricDataOutput output)
    {
        super(output);
    }

    @Override
    public void generate(Consumer<RecipeJsonProvider> exporter)
    {
        buildFossilRecipes(exporter);
        buildBrickRecipes(exporter);
        buildMiscRecipes(exporter);
        buildRottenRecipes(exporter);
    }

    private static void buildRottenRecipes(Consumer<RecipeJsonProvider> exporter)
    {
        offerPlanksRecipe(exporter, ModBlocks.ROTTEN_PLANKS, ModTags.Items.ROTTEN_LOGS, 4);

        ShapedRecipeJsonBuilder.create(RecipeCategory.REDSTONE, ModBlocks.ROTTEN_DOOR, 3)
                .pattern("PP")
                .pattern("PP")
                .pattern("PP")
                .input('P', ModBlocks.ROTTEN_PLANKS)
                .group("wooden_door")
                .criterion(hasItem(ModBlocks.ROTTEN_PLANKS), conditionsFromItem(ModBlocks.ROTTEN_PLANKS))
                .offerTo(exporter);

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModBlocks.ROTTEN_FENCE, 3)
                .pattern("W#W")
                .pattern("W#W")
                .input('#', Items.STICK)
                .input('W', ModBlocks.ROTTEN_PLANKS)
                .group("wooden_fence")
                .criterion(hasItem(ModBlocks.ROTTEN_PLANKS), conditionsFromItem(ModBlocks.ROTTEN_PLANKS))
                .offerTo(exporter);

        ShapedRecipeJsonBuilder.create(RecipeCategory.REDSTONE, ModBlocks.ROTTEN_FENCE_GATE)
                .pattern("#W#")
                .pattern("#W#")
                .input('#', Items.STICK)
                .input('W', ModBlocks.ROTTEN_PLANKS)
                .group("wooden_fence_gate")
                .criterion(hasItem(ModBlocks.ROTTEN_PLANKS), conditionsFromItem(ModBlocks.ROTTEN_PLANKS))
                .offerTo(exporter);

        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, ModBlocks.ROTTEN_SLAB, 6)
                .pattern("WWW")
                .input('W', ModBlocks.ROTTEN_PLANKS)
                .group("wooden_slab")
                .criterion(hasItem(ModBlocks.ROTTEN_PLANKS), conditionsFromItem(ModBlocks.ROTTEN_PLANKS))
                .offerTo(exporter);

        ShapedRecipeJsonBuilder.create(RecipeCategory.REDSTONE, ModBlocks.ROTTEN_PRESSURE_PLATE)
                .pattern("WW")
                .input('W', ModBlocks.ROTTEN_PLANKS)
                .group("wooden_pressure_plate")
                .criterion(hasItem(ModBlocks.ROTTEN_PLANKS), conditionsFromItem(ModBlocks.ROTTEN_PLANKS))
                .offerTo(exporter);

        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, ModBlocks.ROTTEN_STAIRS, 4)
                .pattern("W  ")
                .pattern("WW ")
                .pattern("WWW")
                .input('W', ModBlocks.ROTTEN_PLANKS)
                .group("wooden_stairs")
                .criterion(hasItem(ModBlocks.ROTTEN_PLANKS), conditionsFromItem(ModBlocks.ROTTEN_PLANKS))
                .offerTo(exporter);

        ShapedRecipeJsonBuilder.create(RecipeCategory.REDSTONE, ModBlocks.ROTTEN_TRAPDOOR, 2)
                .pattern("WWW")
                .pattern("WWW")
                .input('W', ModBlocks.ROTTEN_PLANKS)
                .group("wooden_trapdoor")
                .criterion(hasItem(ModBlocks.ROTTEN_PLANKS), conditionsFromItem(ModBlocks.ROTTEN_PLANKS))
                .offerTo(exporter);
    }

    private static void buildMiscRecipes(Consumer<RecipeJsonProvider> exporter)
    {
        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.UNIDENTIFIED_ARTIFACT)
                .pattern("###")
                .pattern("###")
                .pattern("###")
                .input('#', ModItems.ARTIFACT_SHARDS)
                .criterion(hasItem(ModItems.ARTIFACT_SHARDS), conditionsFromItem(ModItems.ARTIFACT_SHARDS))
                .offerTo(exporter);

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModBlocks.ARCHEOLOGY_TABLE)
                .pattern("BS")
                .pattern("WW")
                .pattern("WW")
                .input('B', ModTags.Items.BRUSHES)
                .input('S', ModItems.ARTIFACT_SHARDS)
                .input('W', ItemTags.PLANKS)
                .criterion(hasItem(ModItems.ARTIFACT_SHARDS), conditionsFromItem(ModItems.ARTIFACT_SHARDS))
                .offerTo(exporter);

        ShapedRecipeJsonBuilder.create(RecipeCategory.COMBAT, ModItems.BOMB_ITEM)
                .pattern("III")
                .pattern("IGI")
                .pattern("III")
                .input('I', Items.IRON_NUGGET)
                .input('G', Items.GUNPOWDER)
                .criterion(hasItem(Items.GUNPOWDER), conditionsFromItem(Items.GUNPOWDER))
                .offerTo(exporter);

        ShapedRecipeJsonBuilder.create(RecipeCategory.TOOLS, ModItems.IRON_BRUSH)
                .pattern("F")
                .pattern("I")
                .pattern("S")
                .input('F', Items.FEATHER)
                .input('I', Items.IRON_INGOT)
                .input('S', Items.STICK)
                .criterion(hasItem(Items.IRON_INGOT), conditionsFromItem(Items.IRON_INGOT))
                .offerTo(exporter);

        ShapedRecipeJsonBuilder.create(RecipeCategory.TOOLS, ModItems.DIAMOND_BRUSH)
                .pattern("F")
                .pattern("D")
                .pattern("S")
                .input('F', Items.FEATHER)
                .input('D', Items.DIAMOND)
                .input('S', Items.STICK)
                .criterion(hasItem(Items.DIAMOND), conditionsFromItem(Items.DIAMOND))
                .offerTo(exporter);

        offerNetheriteUpgradeRecipe(exporter, ModItems.DIAMOND_BRUSH, RecipeCategory.TOOLS, ModItems.NETHERITE_BRUSH);
    }

    private static void buildBrickRecipes(Consumer<RecipeJsonProvider> exporter)
    {
        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, ModBlocks.CRACKED_MUD_BRICK_SLAB, 6)
                .pattern("###")
                .input('#', ModBlocks.CRACKED_MUD_BRICKS)
                .criterion(hasItem(ModBlocks.CRACKED_MUD_BRICKS), conditionsFromItem(ModBlocks.CRACKED_MUD_BRICKS))
                .offerTo(exporter);

        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.CRACKED_MUD_BRICK_SLAB, ModBlocks.CRACKED_MUD_BRICKS, 2);

        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, ModBlocks.CRACKED_MUD_BRICK_STAIRS, 4)
                .pattern("#  ")
                .pattern("## ")
                .pattern("###")
                .input('#', ModBlocks.CRACKED_MUD_BRICKS)
                .criterion(hasItem(ModBlocks.CRACKED_MUD_BRICKS), conditionsFromItem(ModBlocks.CRACKED_MUD_BRICKS))
                .offerTo(exporter);

        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.CRACKED_MUD_BRICK_STAIRS, ModBlocks.CRACKED_MUD_BRICKS, 1);

        CookingRecipeJsonBuilder.createSmelting(
                        Ingredient.ofItems(Blocks.MUD_BRICKS),
                        RecipeCategory.BUILDING_BLOCKS, ModBlocks.CRACKED_MUD_BRICKS.asItem(),
                        0.1F, 200
                ).criterion(hasItem(Blocks.MUD_BRICKS), conditionsFromItem(Blocks.MUD_BRICKS)).offerTo(exporter);
    }

    private static void buildFossilRecipes(Consumer<RecipeJsonProvider> exporter)
    {
        ShapelessRecipeJsonBuilder.create(RecipeCategory.DECORATIONS, ModBlocks.CHICKEN_FOSSIL)
                .group("ba_fossils")
                .input(ModBlocks.CHICKEN_FOSSIL_BODY)
                .input(ModBlocks.CHICKEN_FOSSIL_HEAD)
                .criterion(hasItem(ModBlocks.CHICKEN_FOSSIL_BODY), conditionsFromItem(ModBlocks.CHICKEN_FOSSIL_BODY))
                .criterion(hasItem(ModBlocks.CHICKEN_FOSSIL_HEAD), conditionsFromItem(ModBlocks.CHICKEN_FOSSIL_HEAD))
                .offerTo(exporter);

        ShapelessRecipeJsonBuilder.create(RecipeCategory.DECORATIONS, ModBlocks.CREEPER_FOSSIL)
                .group("ba_fossils")
                .input(ModBlocks.CREEPER_FOSSIL_BODY)
                .input(ModBlocks.CREEPER_FOSSIL_HEAD)
                .criterion(hasItem(ModBlocks.CREEPER_FOSSIL_BODY), conditionsFromItem(ModBlocks.CREEPER_FOSSIL_BODY))
                .criterion(hasItem(ModBlocks.CREEPER_FOSSIL_HEAD), conditionsFromItem(ModBlocks.CREEPER_FOSSIL_HEAD))
                .offerTo(exporter);

        ShapelessRecipeJsonBuilder.create(RecipeCategory.DECORATIONS, ModBlocks.GUARDIAN_FOSSIL)
                .group("ba_fossils")
                .input(ModBlocks.GUARDIAN_FOSSIL_HEAD)
                .input(ModBlocks.GUARDIAN_FOSSIL_BODY)
                .criterion(hasItem(ModBlocks.GUARDIAN_FOSSIL_BODY), conditionsFromItem(ModBlocks.GUARDIAN_FOSSIL_BODY))
                .criterion(hasItem(ModBlocks.GUARDIAN_FOSSIL_HEAD), conditionsFromItem(ModBlocks.GUARDIAN_FOSSIL_HEAD))
                .offerTo(exporter);

        ShapelessRecipeJsonBuilder.create(RecipeCategory.DECORATIONS, ModBlocks.OCELOT_FOSSIL)
                .group("ba_fossils")
                .input(ModBlocks.OCELOT_FOSSIL_HEAD)
                .input(ModBlocks.OCELOT_FOSSIL_BODY)
                .criterion(hasItem(ModBlocks.OCELOT_FOSSIL_BODY), conditionsFromItem(ModBlocks.OCELOT_FOSSIL_BODY))
                .criterion(hasItem(ModBlocks.OCELOT_FOSSIL_HEAD), conditionsFromItem(ModBlocks.OCELOT_FOSSIL_HEAD))
                .offerTo(exporter);

        ShapelessRecipeJsonBuilder.create(RecipeCategory.DECORATIONS, ModBlocks.SHEEP_FOSSIL)
                .group("ba_fossils")
                .input(ModBlocks.SHEEP_FOSSIL_HEAD)
                .input(ModBlocks.SHEEP_FOSSIL_BODY)
                .criterion(hasItem(ModBlocks.SHEEP_FOSSIL_BODY), conditionsFromItem(ModBlocks.SHEEP_FOSSIL_BODY))
                .criterion(hasItem(ModBlocks.SHEEP_FOSSIL_HEAD), conditionsFromItem(ModBlocks.SHEEP_FOSSIL_HEAD))
                .offerTo(exporter);

        ShapelessRecipeJsonBuilder.create(RecipeCategory.DECORATIONS, ModBlocks.VILLAGER_FOSSIL)
                .group("ba_fossils")
                .input(ModBlocks.VILLAGER_FOSSIL_HEAD)
                .input(ModBlocks.VILLAGER_FOSSIL_BODY)
                .criterion(hasItem(ModBlocks.VILLAGER_FOSSIL_BODY), conditionsFromItem(ModBlocks.VILLAGER_FOSSIL_BODY))
                .criterion(hasItem(ModBlocks.VILLAGER_FOSSIL_HEAD), conditionsFromItem(ModBlocks.VILLAGER_FOSSIL_HEAD))
                .offerTo(exporter);

        ShapelessRecipeJsonBuilder.create(RecipeCategory.DECORATIONS, ModBlocks.WOLF_FOSSIL)
                .group("ba_fossils")
                .input(ModBlocks.WOLF_FOSSIL_HEAD)
                .input(ModBlocks.WOLF_FOSSIL_BODY)
                .criterion(hasItem(ModBlocks.WOLF_FOSSIL_BODY), conditionsFromItem(ModBlocks.WOLF_FOSSIL_BODY))
                .criterion(hasItem(ModBlocks.WOLF_FOSSIL_HEAD), conditionsFromItem(ModBlocks.WOLF_FOSSIL_HEAD))
                .offerTo(exporter);
    }
}