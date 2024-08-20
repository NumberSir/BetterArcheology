package net.Pandarix.betterarcheology.enchantment;

import net.Pandarix.betterarcheology.BetterArcheologyConfig;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.*;

public class TunnelingEnchantment extends ArtifactEnchantment
{
    public TunnelingEnchantment(Rarity weight, EnchantmentTarget target, EquipmentSlot... slotTypes)
    {
        super(weight, target, slotTypes);
    }

    @Override
    public boolean isAcceptableItem(ItemStack stack)
    {
        if (BetterArcheologyConfig.tunnelingEnabled.get() && BetterArcheologyConfig.artifactsEnabled.get())
        {
            return stack.getItem() instanceof PickaxeItem || stack.getItem() instanceof ShovelItem || stack.getItem() instanceof HoeItem || (stack.getItem() instanceof AxeItem && BetterArcheologyConfig.tunnelingAxeEnabled.get());
        }
        return false;
    }
}
