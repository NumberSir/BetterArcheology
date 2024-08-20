package net.Pandarix.betterarcheology.util;

import net.Pandarix.betterarcheology.BetterArcheology;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;

public class ModTags
{
    public static class Blocks
    {
        public static final TagKey<Block> ROTTEN_LOGS = TagKey.of(RegistryKeys.BLOCK, new Identifier(BetterArcheology.MOD_ID,"rotten_logs"));
    }

    public static class Items
    {
        public static final TagKey<Item> BRUSHES = TagKey.of(RegistryKeys.ITEM, new Identifier("c", "brushes"));

        public static final TagKey<Item> ROTTEN_LOGS = TagKey.of(RegistryKeys.ITEM, new Identifier(BetterArcheology.MOD_ID,"rotten_logs"));
    }
}
