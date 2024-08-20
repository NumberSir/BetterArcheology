package net.Pandarix.betterarcheology.item;

import net.Pandarix.betterarcheology.BetterArcheologyConfig;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.UseAction;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class TorrentTotemItem extends Item
{
    public TorrentTotemItem(Settings settings)
    {
        super(settings);
    }

    private static final int speed = 2;

    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand)
    {
        ItemStack itemStack = user.getStackInHand(hand);
        user.setCurrentHand(hand);

        // if feature is disabled, notify the user and skip
        if (!BetterArcheologyConfig.torrentTotemEnabled.get() || !BetterArcheologyConfig.totemsEnabled.get())
        {
            if (world.isClient())
            {
                user.sendMessage(Text.translatableWithFallback("config.notify.disabled", "This feature has been disabled in the config!"), true);
            }
            return TypedActionResult.pass(itemStack);
        }

        //user setup
        Vec3d rotationVector = user.getRotationVector();
        Vec3d velocity = user.getVelocity();
        double boostX = 2 * BetterArcheologyConfig.torrentTotemBoost.get();
        double boostY = BetterArcheologyConfig.torrentTotemUpwardsBoost.get() ? 0.5 * BetterArcheologyConfig.torrentTotemBoost.get() : 0;

        //dash
        user.setVelocity(velocity.add(
                rotationVector.x * 0.1D + (rotationVector.x * 1.5D - velocity.x) * boostX,
                (rotationVector.y * 0.1D + (rotationVector.y * 1.5D - velocity.y)) * boostY,
                rotationVector.z * 0.1D + (rotationVector.z * 1.5D - velocity.z) * boostX)
        );
        user.useRiptide(8);

        //sounds
        world.playSoundFromEntity(null, user, SoundEvents.BLOCK_WATER_AMBIENT, SoundCategory.NEUTRAL, 0.1f, (float) world.getRandom().nextDouble() * 0.5f + 0.5f);
        world.playSoundFromEntity(null, user, SoundEvents.ENTITY_PLAYER_SPLASH_HIGH_SPEED, SoundCategory.NEUTRAL, 0.25F, 0.35F / (world.getRandom().nextFloat() * 0.4F + 0.8F));

        //tool action completion
        user.getItemCooldownManager().set(this, 120);
        itemStack.damage(1, user, (p) ->
                p.sendToolBreakStatus(hand));

        return TypedActionResult.consume(itemStack);
    }

    @Override
    public boolean isEnchantable(ItemStack stack)
    {
        return false;
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context)
    {
        tooltip.add(Text.translatable(this.getTranslationKey() + "_description").formatted(Formatting.DARK_AQUA));
        super.appendTooltip(stack, world, tooltip, context);

    }

    @Override
    public UseAction getUseAction(ItemStack stack)
    {
        return UseAction.BOW;
    }

    @Override
    public int getMaxUseTime(ItemStack stack)
    {
        return 0;
    }
}