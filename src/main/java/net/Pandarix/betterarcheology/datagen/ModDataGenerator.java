package net.Pandarix.betterarcheology.datagen;

import net.Pandarix.betterarcheology.datagen.provider.ModBlockTagProvider;
import net.Pandarix.betterarcheology.datagen.provider.ModItemTagProvider;
import net.Pandarix.betterarcheology.datagen.provider.ModRecipeProvider;
import net.Pandarix.betterarcheology.datagen.provider.loot.ModBlockLootTableProvider;
import net.Pandarix.betterarcheology.datagen.provider.loot.ModLootTableProvider;
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;

public class ModDataGenerator implements DataGeneratorEntrypoint
{
    @Override
    public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
        FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();

        pack.addProvider(ModBlockTagProvider::new);
        pack.addProvider(ModItemTagProvider::new);
        pack.addProvider(ModLootTableProvider::new);
        pack.addProvider(ModBlockLootTableProvider::new);
        pack.addProvider(ModRecipeProvider::new);
    }
}