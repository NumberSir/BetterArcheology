package net.Pandarix.betterarcheology.enchantment;

import net.Pandarix.betterarcheology.BetterArcheologyConfig;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ItemStack;

public class ArtifactEnchantment extends Enchantment
{
    protected ArtifactEnchantment(Rarity weight, EnchantmentTarget target, EquipmentSlot... slotTypes)
    {
        super(weight, target, slotTypes);
    }

    //Restricting any other access to the Enchantment than Archeology-----------------------------------//
    @Override
    public boolean isAvailableForEnchantedBookOffer()
    {
        return false;
    }

    @Override
    public boolean isTreasure()
    {
        return false;
    }

    @Override
    public boolean isAvailableForRandomSelection()
    {
        return false;
    }

    @Override
    public Rarity getRarity()
    {
        return Rarity.VERY_RARE;
    }

    @Override
    public boolean isAcceptableItem(ItemStack stack)
    {
        return BetterArcheologyConfig.artifactsEnabled.get() && super.isAcceptableItem(stack);

    }
}
