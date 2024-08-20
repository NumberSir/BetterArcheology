package net.Pandarix.betterarcheology.mixin;

import net.Pandarix.betterarcheology.BetterArcheologyConfig;
import net.Pandarix.betterarcheology.enchantment.ModEnchantments;
import net.minecraft.block.BlockState;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.MiningToolItem;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(MiningToolItem.class)
public class TunnelingEnchantmentMixin
{
    @Inject(method = "postMine", at = @At(value = "INVOKE", target = "Lnet/minecraft/item/ItemStack;damage(ILnet/minecraft/entity/LivingEntity;Ljava/util/function/Consumer;)V", shift = At.Shift.AFTER))
    private void injectMethod(ItemStack stack, World world, BlockState state, BlockPos pos, LivingEntity miner, CallbackInfoReturnable<Boolean> cir)
    {
        //if it is enabled in the config and the stack exists, has Enchantments & is Tunneling
        if (BetterArcheologyConfig.artifactsEnabled.get() && BetterArcheologyConfig.tunnelingEnabled.get() && !miner.isSneaking() && !stack.isEmpty() && stack.hasEnchantments() && EnchantmentHelper.getLevel(ModEnchantments.TUNNELING, stack) == 1)
        {
            //if the tool is right for the block that should be broken
            //if the difference of the hardness of the block below is not more than 3,75
            BlockState blockStateBelow = world.getBlockState(pos.down());

            if (stack.isSuitableFor(state) && stack.isSuitableFor(blockStateBelow) && Math.abs((world.getBlockState(pos.down()).getHardness(world, pos.down()) - world.getBlockState(pos).getHardness(world, pos))) <= 3.75)
            {
                if (miner instanceof ServerPlayerEntity serverPlayer)
                {
                    serverPlayer.interactionManager.tryBreakBlock(pos.down());
                }
            }
        }
    }
}
