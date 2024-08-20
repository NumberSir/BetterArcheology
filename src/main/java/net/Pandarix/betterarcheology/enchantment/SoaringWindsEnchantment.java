package net.Pandarix.betterarcheology.enchantment;

import net.Pandarix.betterarcheology.BetterArcheologyConfig;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ElytraItem;
import net.minecraft.item.ItemStack;

public class SoaringWindsEnchantment extends ArtifactEnchantment
{
    protected SoaringWindsEnchantment(Rarity weight, EnchantmentTarget target, EquipmentSlot... slotTypes)
    {
        super(weight, target, slotTypes);
    }

    @Override
    public int getMaxLevel()
    {
        return 1;
    }

    @Override
    public boolean isAcceptableItem(ItemStack stack) {
        if (BetterArcheologyConfig.soaringWindsEnabled.get() && BetterArcheologyConfig.artifactsEnabled.get())
        {
            return stack.getItem() instanceof ElytraItem;
        }
        return false;
    }

}
