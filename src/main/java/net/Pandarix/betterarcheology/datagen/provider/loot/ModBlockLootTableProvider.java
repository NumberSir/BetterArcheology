package net.Pandarix.betterarcheology.datagen.provider.loot;

import net.Pandarix.betterarcheology.block.ModBlocks;
import net.Pandarix.betterarcheology.item.ModItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.entry.LeafEntry;
import net.minecraft.loot.entry.LootTableEntry;
import net.minecraft.loot.function.SetCountLootFunction;
import net.minecraft.loot.provider.number.ConstantLootNumberProvider;
import net.minecraft.loot.provider.number.UniformLootNumberProvider;
import net.minecraft.registry.Registries;

public class ModBlockLootTableProvider extends FabricBlockLootTableProvider
{
    public ModBlockLootTableProvider(FabricDataOutput dataOutput)
    {
        super(dataOutput);
    }

    @Override
    public void generate()
    {
        //generate dropSelf for all Fossils
        Registries.BLOCK.stream()
                .filter(block -> block != ModBlocks.RADIANCE_TOTEM && ModItems.isFossil(block))
                .forEach(this::addDrop);

        //GENERIC
        addDrop(ModBlocks.ARCHEOLOGY_TABLE);
        addDrop(ModBlocks.CRACKED_MUD_BRICKS);
        addDrop(ModBlocks.CRACKED_MUD_BRICK_STAIRS);
        addDrop(ModBlocks.CRACKED_MUD_BRICK_SLAB);
        addDrop(ModBlocks.EVOKER_TRAP);
        addDrop(ModBlocks.GROWTH_TOTEM);
        addDrop(ModBlocks.RADIANCE_TOTEM);
        addDrop(ModBlocks.VASE);
        addDrop(ModBlocks.VASE_CREEPER);
        addDrop(ModBlocks.VASE_GREEN);

        //MISC
        addDrop(ModBlocks.INFESTED_MUD_BRICKS, dropsWithSilkTouch(ModBlocks.INFESTED_MUD_BRICKS));
        addDrop(ModBlocks.CHISELED_BONE_BLOCK,
                dropsWithSilkTouch(ModBlocks.CHISELED_BONE_BLOCK, uniformItemEntry(Items.BONE, 2, 3)));
        addDrop(ModBlocks.SUSPICIOUS_DIRT, dropsNothing());
        addDrop(ModBlocks.SUSPICIOUS_RED_SAND, dropsNothing());
        addDrop(ModBlocks.FOSSILIFEROUS_DIRT, Items.BONE);

        //ROTTEN
        addDrop(ModBlocks.ROTTEN_DOOR, block ->
                doorDrops(ModBlocks.ROTTEN_DOOR));
        addDrop(ModBlocks.ROTTEN_FENCE, block ->
                dropsWithSilkTouch(block, exactItemEntry(Items.STICK, 4)));
        addDrop(ModBlocks.ROTTEN_FENCE_GATE, block ->
                dropsWithSilkTouch(block, exactItemEntry(Items.STICK, 2)));
        addDrop(ModBlocks.ROTTEN_LOG, block ->
                dropsWithSilkTouch(block, exactItemEntry(Items.STICK, 8)));
        addDrop(ModBlocks.ROTTEN_PLANKS, block ->
                dropsWithSilkTouch(block, exactItemEntry(Items.STICK, 2)));
        addDrop(ModBlocks.ROTTEN_PRESSURE_PLATE, block ->
                dropsWithSilkTouch(block, exactItemEntry(Items.STICK, 1)));
        addDrop(ModBlocks.ROTTEN_SLAB, block ->
                dropsWithSilkTouch(block, exactItemEntry(Items.STICK, 2)));
        addDrop(ModBlocks.ROTTEN_STAIRS, block ->
                dropsWithSilkTouch(block, exactItemEntry(Items.STICK, 3)));
        addDrop(ModBlocks.ROTTEN_TRAPDOOR, block ->
                dropsWithSilkTouch(block, exactItemEntry(Items.STICK, 4)));
        
        //VASES
        addDrop(ModBlocks.LOOT_VASE, block ->
                LootTable.builder()
                        .pool(LootPool.builder().rolls(ConstantLootNumberProvider.create(1)).with(ItemEntry.builder(ModBlocks.VASE).conditionally(WITH_SILK_TOUCH)))
                        .pool(LootPool.builder().rolls(ConstantLootNumberProvider.create(1))
                                .with(LootTableEntry.builder(ModLootTableProvider.SUPPLY_LOOTTABLE_ID))
                                .with(LootTableEntry.builder(ModLootTableProvider.TREASURE_LOOTTABLE_ID))
                                .conditionally(WITHOUT_SILK_TOUCH))
        );

        addDrop(ModBlocks.LOOT_VASE_CREEPER, block ->
                LootTable.builder()
                        .pool(LootPool.builder().rolls(ConstantLootNumberProvider.create(1)).with(ItemEntry.builder(ModBlocks.VASE_CREEPER).conditionally(WITH_SILK_TOUCH)))
                        .pool(LootPool.builder().rolls(ConstantLootNumberProvider.create(1))
                                .with(LootTableEntry.builder(ModLootTableProvider.SUPPLY_LOOTTABLE_ID))
                                .with(LootTableEntry.builder(ModLootTableProvider.TREASURE_LOOTTABLE_ID))
                                .conditionally(WITHOUT_SILK_TOUCH))
        );

        addDrop(ModBlocks.LOOT_VASE_GREEN, block ->
                LootTable.builder()
                        .pool(LootPool.builder().rolls(ConstantLootNumberProvider.create(1)).with(ItemEntry.builder(ModBlocks.VASE_GREEN).conditionally(WITH_SILK_TOUCH)))
                        .pool(LootPool.builder().rolls(ConstantLootNumberProvider.create(1))
                                .with(LootTableEntry.builder(ModLootTableProvider.SUPPLY_LOOTTABLE_ID))
                                .with(LootTableEntry.builder(ModLootTableProvider.GREEN_TREASURE_LOOTTABLE_ID))
                                .conditionally(WITHOUT_SILK_TOUCH))
        );
    }

    private static LeafEntry.Builder<?> uniformItemEntry(Item item, int min, int max)
    {
        return ItemEntry.builder(item)
                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(min, max)));
    }

    private static LeafEntry.Builder<?> exactItemEntry(Item item, int count)
    {
        return ItemEntry.builder(item)
                .apply(SetCountLootFunction.builder(ConstantLootNumberProvider.create(count)));
    }
}
