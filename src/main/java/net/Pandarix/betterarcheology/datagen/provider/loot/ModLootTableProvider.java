package net.Pandarix.betterarcheology.datagen.provider.loot;

import net.Pandarix.betterarcheology.BetterArcheology;
import net.Pandarix.betterarcheology.item.ModItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.SimpleFabricLootTableProvider;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.Items;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.condition.RandomChanceLootCondition;
import net.minecraft.loot.context.LootContextTypes;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.function.SetCountLootFunction;
import net.minecraft.loot.function.SetStewEffectLootFunction;
import net.minecraft.loot.provider.number.ConstantLootNumberProvider;
import net.minecraft.loot.provider.number.UniformLootNumberProvider;
import net.minecraft.util.Identifier;

import java.util.function.BiConsumer;

public class ModLootTableProvider extends SimpleFabricLootTableProvider
{
    //references for other loot tables
    protected static final Identifier SUPPLY_LOOTTABLE_ID = new Identifier(BetterArcheology.MOD_ID, "blocks/supply_loot_from_loot_vase");
    protected static final Identifier TREASURE_LOOTTABLE_ID = new Identifier(BetterArcheology.MOD_ID, "blocks/treasure_loot_from_loot_vase");
    protected static final Identifier GREEN_TREASURE_LOOTTABLE_ID = new Identifier(BetterArcheology.MOD_ID, "blocks/treasure_loot_from_green_loot_vase");

    //universal shard drop rate
    protected static final LootPool.Builder SHARD_POOL_BUILDER = LootPool.builder().rolls(ConstantLootNumberProvider.create(1))
            .with(ItemEntry.builder(ModItems.ARTIFACT_SHARDS).conditionally(RandomChanceLootCondition.builder(.5F)));
    
    public ModLootTableProvider(FabricDataOutput output)
    {
        super(output, LootContextTypes.BLOCK);
    }

    @Override
    public void accept(BiConsumer<Identifier, LootTable.Builder> exporter)
    {
        exporter.accept(SUPPLY_LOOTTABLE_ID, LootTable.builder()
                .pool(LootPool.builder().rolls(UniformLootNumberProvider.create(2, 3))
                        .with(ItemEntry.builder(Items.BONE).weight(10).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1, 3))))
                        .with(ItemEntry.builder(Items.ROTTEN_FLESH).weight(15).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(2, 4))))
                        .with(ItemEntry.builder(Items.MELON_SEEDS).weight(10).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(2, 4))))
                        .with(ItemEntry.builder(Items.PUMPKIN_SEEDS).weight(10).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(2, 4))))
                        .with(ItemEntry.builder(Items.BEETROOT_SEEDS).weight(10).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(2, 4))))
                        .with(ItemEntry.builder(Items.SUSPICIOUS_STEW).weight(7).apply(SetStewEffectLootFunction.builder()
                                        .withEffect(StatusEffects.NIGHT_VISION, UniformLootNumberProvider.create(7.0F, 10.0F))
                                        .withEffect(StatusEffects.JUMP_BOOST, UniformLootNumberProvider.create(7.0F, 10.0F))
                                        .withEffect(StatusEffects.WEAKNESS, UniformLootNumberProvider.create(6.0F, 8.0F))
                                        .withEffect(StatusEffects.BLINDNESS, UniformLootNumberProvider.create(5.0F, 7.0F))
                                        .withEffect(StatusEffects.SATURATION, UniformLootNumberProvider.create(7.0F, 10.0F))
                                        .withEffect(StatusEffects.HASTE, UniformLootNumberProvider.create(10.0F, 15.0F))
                                        .withEffect(StatusEffects.LEVITATION, UniformLootNumberProvider.create(2.0F, 4.0F))
                                        .withEffect(StatusEffects.SPEED, UniformLootNumberProvider.create(4.0F, 7.0F))
                                )
                        )
                        .with(ItemEntry.builder(Items.POTATO).weight(10).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(2, 4))))
                        .with(ItemEntry.builder(Items.CARROT).weight(10).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(2, 4))))
                        .with(ItemEntry.builder(Items.WHEAT).weight(10).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(5, 10))))
                )
        );


        //LOOT VASE TREASURE LOOT-------
        //NORMAL
        exporter.accept(TREASURE_LOOTTABLE_ID, LootTable.builder()
                .pool(LootPool.builder().rolls(UniformLootNumberProvider.create(2, 3))
                        .with(ItemEntry.builder(Items.IRON_NUGGET).weight(5).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(3, 8))))
                        .with(ItemEntry.builder(Items.GOLD_NUGGET).weight(5).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(3, 8))))
                        .with(ItemEntry.builder(Items.REDSTONE).weight(5).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(4, 9))))
                        .with(ItemEntry.builder(Items.LAPIS_LAZULI).weight(5).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(4, 9))))
                        .with(ItemEntry.builder(Items.DIAMOND).weight(2))
                        .with(ItemEntry.builder(Items.COAL).weight(10).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(3, 8))))
                )
                .pool(SHARD_POOL_BUILDER)
        );

        //GREEN
        exporter.accept(GREEN_TREASURE_LOOTTABLE_ID, LootTable.builder()
                .pool(LootPool.builder().rolls(ConstantLootNumberProvider.create(2))
                        .with(ItemEntry.builder(Items.GUNPOWDER).weight(5).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(2, 4))))
                        .with(ItemEntry.builder(Items.CLAY_BALL).weight(5).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(3, 7))))
                        .with(ItemEntry.builder(Items.LAPIS_LAZULI).weight(5).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(4, 9))))
                        .with(ItemEntry.builder(Items.QUARTZ).weight(10).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(3, 6))))
                        .with(ItemEntry.builder(Items.DIAMOND).weight(2))
                        .with(ItemEntry.builder(Items.RABBIT_FOOT).weight(2))
                )
                .pool(SHARD_POOL_BUILDER)
        );
    }
}
