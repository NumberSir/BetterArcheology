package net.Pandarix.betterarcheology.datagen.provider;

import net.Pandarix.betterarcheology.block.ModBlocks;
import net.Pandarix.betterarcheology.item.ModItems;
import net.Pandarix.betterarcheology.util.ModTags;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.item.Items;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.ItemTags;

import java.util.concurrent.CompletableFuture;

public class ModItemTagProvider extends FabricTagProvider.ItemTagProvider
{
    public ModItemTagProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> completableFuture)
    {
        super(output, completableFuture);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup arg)
    {
        getOrCreateTagBuilder(ModTags.Items.ROTTEN_LOGS)
                .add(ModBlocks.ROTTEN_LOG.asItem());

        getOrCreateTagBuilder(ModTags.Items.BRUSHES)
                .add(Items.BRUSH, ModItems.IRON_BRUSH, ModItems.DIAMOND_BRUSH, ModItems.NETHERITE_BRUSH);

        getOrCreateTagBuilder(ItemTags.DIRT)
                .add(ModBlocks.FOSSILIFEROUS_DIRT.asItem());

        getOrCreateTagBuilder(ItemTags.FLOWERS)
                .add(ModBlocks.GROWTH_TOTEM.asItem());
        getOrCreateTagBuilder(ItemTags.SMALL_FLOWERS)
                .add(ModBlocks.GROWTH_TOTEM.asItem());

        getOrCreateTagBuilder(ItemTags.DOORS)
                .add(ModBlocks.ROTTEN_DOOR.asItem());
        getOrCreateTagBuilder(ItemTags.WOODEN_DOORS)
                .add(ModBlocks.ROTTEN_DOOR.asItem());
        getOrCreateTagBuilder(ItemTags.FENCE_GATES)
                .add(ModBlocks.ROTTEN_FENCE_GATE.asItem());
        getOrCreateTagBuilder(ItemTags.FENCES)
                .add(ModBlocks.ROTTEN_FENCE.asItem());
        getOrCreateTagBuilder(ItemTags.WOODEN_FENCES)
                .add(ModBlocks.ROTTEN_FENCE.asItem());
        getOrCreateTagBuilder(ItemTags.LOGS)
                .add(ModBlocks.ROTTEN_LOG.asItem());
        getOrCreateTagBuilder(ItemTags.LOGS_THAT_BURN)
                .add(ModBlocks.ROTTEN_LOG.asItem());
        getOrCreateTagBuilder(ItemTags.PLANKS)
                .add(ModBlocks.ROTTEN_PLANKS.asItem());
        getOrCreateTagBuilder(ItemTags.WOODEN_PRESSURE_PLATES)
                .add(ModBlocks.ROTTEN_PRESSURE_PLATE.asItem());
        getOrCreateTagBuilder(ItemTags.SLABS)
                .add(ModBlocks.ROTTEN_SLAB.asItem());
        getOrCreateTagBuilder(ItemTags.WOODEN_SLABS)
                .add(ModBlocks.ROTTEN_SLAB.asItem());
        getOrCreateTagBuilder(ItemTags.STAIRS)
                .add(ModBlocks.ROTTEN_STAIRS.asItem());
        getOrCreateTagBuilder(ItemTags.TRAPDOORS)
                .add(ModBlocks.ROTTEN_TRAPDOOR.asItem());
        getOrCreateTagBuilder(ItemTags.WOODEN_TRAPDOORS)
                .add(ModBlocks.ROTTEN_TRAPDOOR.asItem());
    }
}