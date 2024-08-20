package net.Pandarix.betterarcheology.datagen.provider;

import net.Pandarix.betterarcheology.block.ModBlocks;
import net.Pandarix.betterarcheology.util.ModTags;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.BlockTags;

import java.util.concurrent.CompletableFuture;

public class ModBlockTagProvider extends FabricTagProvider.BlockTagProvider
{
    public ModBlockTagProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture)
    {
        super(output, registriesFuture);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup arg)
    {
        getOrCreateTagBuilder(ModTags.Blocks.ROTTEN_LOGS)
                .add(ModBlocks.ROTTEN_LOG);

        getOrCreateTagBuilder(BlockTags.PICKAXE_MINEABLE)
                .add(
                        ModBlocks.CRACKED_MUD_BRICKS,
                        ModBlocks.CRACKED_MUD_BRICK_SLAB,
                        ModBlocks.CRACKED_MUD_BRICK_STAIRS,
                        ModBlocks.VILLAGER_FOSSIL,
                        ModBlocks.VILLAGER_FOSSIL_BODY,
                        ModBlocks.VILLAGER_FOSSIL_HEAD,
                        ModBlocks.OCELOT_FOSSIL,
                        ModBlocks.OCELOT_FOSSIL_BODY,
                        ModBlocks.OCELOT_FOSSIL_HEAD,
                        ModBlocks.SHEEP_FOSSIL,
                        ModBlocks.SHEEP_FOSSIL_BODY,
                        ModBlocks.SHEEP_FOSSIL_HEAD,
                        ModBlocks.CHICKEN_FOSSIL,
                        ModBlocks.CHICKEN_FOSSIL_BODY,
                        ModBlocks.CHICKEN_FOSSIL_HEAD,
                        ModBlocks.CREEPER_FOSSIL,
                        ModBlocks.CREEPER_FOSSIL_BODY,
                        ModBlocks.CREEPER_FOSSIL_HEAD,
                        ModBlocks.WOLF_FOSSIL,
                        ModBlocks.WOLF_FOSSIL_BODY,
                        ModBlocks.WOLF_FOSSIL_HEAD,
                        ModBlocks.GUARDIAN_FOSSIL,
                        ModBlocks.GUARDIAN_FOSSIL_BODY,
                        ModBlocks.GUARDIAN_FOSSIL_HEAD,
                        ModBlocks.EVOKER_TRAP,
                        ModBlocks.CHISELED_BONE_BLOCK,
                        ModBlocks.RADIANCE_TOTEM
                );

        getOrCreateTagBuilder(BlockTags.AXE_MINEABLE)
                .add(
                        ModBlocks.ROTTEN_LOG,
                        ModBlocks.ROTTEN_PLANKS,
                        ModBlocks.ROTTEN_STAIRS,
                        ModBlocks.ROTTEN_SLAB,
                        ModBlocks.ROTTEN_FENCE,
                        ModBlocks.ROTTEN_FENCE_GATE,
                        ModBlocks.ROTTEN_TRAPDOOR,
                        ModBlocks.ROTTEN_DOOR,
                        ModBlocks.ROTTEN_PRESSURE_PLATE,
                        ModBlocks.ARCHEOLOGY_TABLE
                );

        getOrCreateTagBuilder(BlockTags.SHOVEL_MINEABLE)
                .add(ModBlocks.FOSSILIFEROUS_DIRT);
        getOrCreateTagBuilder(BlockTags.BAMBOO_PLANTABLE_ON)
                .add(ModBlocks.FOSSILIFEROUS_DIRT);
        getOrCreateTagBuilder(BlockTags.DIRT)
                .add(ModBlocks.FOSSILIFEROUS_DIRT);

        getOrCreateTagBuilder(BlockTags.LUSH_GROUND_REPLACEABLE)
                .add(ModBlocks.FOSSILIFEROUS_DIRT);
        getOrCreateTagBuilder(BlockTags.FLOWERS)
                .add(ModBlocks.GROWTH_TOTEM);
        getOrCreateTagBuilder(BlockTags.SMALL_FLOWERS)
                .add(ModBlocks.GROWTH_TOTEM);

        getOrCreateTagBuilder(BlockTags.DOORS)
                .add(ModBlocks.ROTTEN_DOOR);
        getOrCreateTagBuilder(BlockTags.WOODEN_DOORS)
                .add(ModBlocks.ROTTEN_DOOR);
        getOrCreateTagBuilder(BlockTags.FENCE_GATES)
                .add(ModBlocks.ROTTEN_FENCE_GATE);
        getOrCreateTagBuilder(BlockTags.FENCES)
                .add(ModBlocks.ROTTEN_FENCE);
        getOrCreateTagBuilder(BlockTags.WOODEN_FENCES)
                .add(ModBlocks.ROTTEN_FENCE);
        getOrCreateTagBuilder(BlockTags.LOGS)
                .add(ModBlocks.ROTTEN_LOG);
        getOrCreateTagBuilder(BlockTags.LOGS_THAT_BURN)
                .add(ModBlocks.ROTTEN_LOG);
        getOrCreateTagBuilder(BlockTags.PLANKS)
                .add(ModBlocks.ROTTEN_PLANKS);
        getOrCreateTagBuilder(BlockTags.PRESSURE_PLATES)
                .add(ModBlocks.ROTTEN_PRESSURE_PLATE);
        getOrCreateTagBuilder(BlockTags.WOODEN_PRESSURE_PLATES)
                .add(ModBlocks.ROTTEN_PRESSURE_PLATE);
        getOrCreateTagBuilder(BlockTags.WALL_POST_OVERRIDE)
                .add(ModBlocks.ROTTEN_PRESSURE_PLATE);
        getOrCreateTagBuilder(BlockTags.SLABS)
                .add(ModBlocks.ROTTEN_SLAB);
        getOrCreateTagBuilder(BlockTags.WOODEN_SLABS)
                .add(ModBlocks.ROTTEN_SLAB);
        getOrCreateTagBuilder(BlockTags.STAIRS)
                .add(ModBlocks.ROTTEN_STAIRS);
        getOrCreateTagBuilder(BlockTags.TRAPDOORS)
                .add(ModBlocks.ROTTEN_TRAPDOOR);
        getOrCreateTagBuilder(BlockTags.WOODEN_TRAPDOORS)
                .add(ModBlocks.ROTTEN_TRAPDOOR);
        getOrCreateTagBuilder(BlockTags.UNSTABLE_BOTTOM_CENTER)
                .add(ModBlocks.ROTTEN_FENCE_GATE);
    }
}
