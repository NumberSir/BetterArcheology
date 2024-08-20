package net.Pandarix.betterarcheology;

import com.supermartijn642.configlib.api.ConfigBuilders;
import com.supermartijn642.configlib.api.IConfigBuilder;

import java.util.function.Supplier;

/**
 * Config depending on supermartijn642's ConfigLib
 * Allows for easy editing and automatic reloading and syncing using suppliers
 */
public class BetterArcheologyConfig
{
    //ARTIFACTS
    public static final Supplier<Boolean> artifactsEnabled;
    public static final Supplier<Boolean> penetratingStrikeEnabled;
    public static final Supplier<Double> penetratingStrikeIgnorance;
    public static final Supplier<Boolean> soaringWindsEnabled;
    public static final Supplier<Double> soaringWindsBoost;
    public static final Supplier<Boolean> tunnelingEnabled;
    public static final Supplier<Boolean> tunnelingAxeEnabled;
    //TOTEMS
    public static final Supplier<Boolean> totemsEnabled;

    public static final Supplier<Boolean> radianceTotemEnabled;
    public static final Supplier<Boolean> radianceTotemDamageEnabled;
    public static final Supplier<Integer> radianceTotemDamage;
    public static final Supplier<Integer> radianceTotemDamageTickAverage;
    public static final Supplier<Integer> radianceTotemRadius;

    public static final Supplier<Boolean> torrentTotemEnabled;
    public static final Supplier<Double> torrentTotemBoost;
    public static final Supplier<Boolean> torrentTotemUpwardsBoost;

    public static final Supplier<Boolean> growthTotemEnabled;
    public static final Supplier<Integer> growthTotemGrowRadius;
    public static final Supplier<Integer> growthTotemGrowChance;
    //FOSSILS
    public static final Supplier<Boolean> fossilEffectsEnabled;
    public static final Supplier<Integer> fossilFleeRange;
    public static final Supplier<Boolean> chickenFossilEffectsEnabled;
    public static final Supplier<Boolean> ocelotFossilEffectsEnabled;
    public static final Supplier<Boolean> wolfFossilEffectsEnabled;
    public static final Supplier<Boolean> guardianFossilEffectsEnabled;
    //MISC
    public static final Supplier<Boolean> shootBombsFromDispensers;

    static
    {
        IConfigBuilder builder = ConfigBuilders.newTomlConfig("betterarcheology", null, false);

        builder.push("Artifacts");
            artifactsEnabled = builder.comment("Set to true or false to enable or disable effects.").define("artifactEnchantmentsEnabled", true);

            builder.push("PenetratingStrike");
                penetratingStrikeEnabled = builder.comment("En-/Disables the effects of the Penetrating Strike enchantment.").define("penetratingStrikeEnabled", true);

                penetratingStrikeIgnorance = builder.comment("Set to % of damage-reduction from Protection Enchantments that should be ignored.").define("penetratingStrikeProtectionIgnorance", 0.33d, 0d, 1d);
            builder.pop();

            builder.push("Tunneling");
                tunnelingEnabled = builder.comment("En-/Disables the effects of the Tunneling enchantment.").define("tunnelingEnabled", true);

                tunnelingAxeEnabled = builder.comment("En-/Disables the tunneling enchantment on the Axe. Already enchanted axes still work but enchantment cannot be applied to future axes.").define("tunnelingAxeEnabled", false);
            builder.pop();

            builder.push("SoaringWinds");
                soaringWindsEnabled = builder.comment("En-/Disables the effects of the Soaring Winds enchantment.").define("soaringWindsEnabled", true);

                soaringWindsBoost = builder.comment("Set to movement speed multiplier, that should be applied when starting to fly").define("soaringWindsBoost", 0.5d, 0.1d, 3d);
            builder.pop();
        builder.pop();

        builder.push("Totems");
            totemsEnabled = builder.comment("En-/Disables ALL of the Totems' effects.").define("totemsEnabled", true);

            builder.push("Radiance");
                radianceTotemEnabled = builder.comment("En-/Disables ALL of the Radiance Totem's effects.").define("radianceTotemEnabled", true);

                radianceTotemDamageEnabled = builder.comment("En-/Disables the Radiance Totem damaging hostile mobs around it.").define("radianceTotemDamageEnabled", true);

                radianceTotemDamage = builder.comment("Sets the damage in 1/2 hearts that will be dealt to hostile mobs when a damage tick occurs.").define("radianceTotemDamage", 4, 1, 40);

                radianceTotemDamageTickAverage = builder.comment("Sets the average time between damage ticks of the Radiance Totem in seconds. The totem will still damage mobs randomly, but the average time between damage ticks will be this value.").define("radianceTotemDamageTickAverage", 3, 1, 60);

                radianceTotemRadius = builder.comment("Sets the radius around the radiance totem within which entities will be affected by the glowing and damaging effects.").define("radianceTotemRadius", 10, 1, 90);
            builder.pop();

            builder.push("Torrent");
                torrentTotemEnabled = builder.comment("En-/Disables the Torrent Totem's effects.").define("torrentTotemEnabled", true);

                torrentTotemBoost = builder.comment("Sets the relative strength of the boost the player will get when using the Torrent Totem.").define("torrentTotemBoost", 1d, .1d, 3d);

                torrentTotemUpwardsBoost = builder.comment("En-/Disables giving a player an upwards boost when using the Torrent Totem.").define("torrentTotemUpwardsBoost", false);
            builder.pop();

            builder.push("Growth");
                growthTotemEnabled = builder.comment("En-/Disables the Growth Totem's effects.").define("growthTotemEnabled", true);

                growthTotemGrowRadius = builder.comment("Sets the radius around the growth totem within which crops will be randomly bonemealed.").define("growthTotemGrowRadius", 5, 1, 50);

                growthTotemGrowChance = builder.comment("The growth totem uses the randomTick to determine when it should grow crops. This value determines the chance in % that a random tick actually grows crops to potentially decrease its yield. For example, a 20% chance bonemeals a crop ~10.5 times an hour").define("growthTotemGrowChance", 20, 1, 100);
            builder.pop();
        builder.pop();

        builder.push("Fossils");
            fossilEffectsEnabled = builder.comment("En-/Disables the effects of the Fossils like PotionEffects etc.").define("fossilEffectsEnabled", true);

            fossilFleeRange = builder.comment("Range in Blocks that the Ocelot/Wolf Fossils scare their corresponding mobs away.").define("fossilFleeRange", 20, 10, 50);

            chickenFossilEffectsEnabled = builder.comment("En-/Disables the slow falling effect of the chicken fossil.").define("chickenFossilEffectsEnabled", true);

            ocelotFossilEffectsEnabled = builder.comment("En-/Disables Creepers fleeing from the ocelot fossil.").define("ocelotFossilEffectsEnabled", true);

            wolfFossilEffectsEnabled = builder.comment("En-/Disables Skeletons fleeing from the ocelot fossil.").define("wolfFossilEffectsEnabled", true);

            guardianFossilEffectsEnabled = builder.comment("En-/Disables the water breathing effect of the guardian fossil.").define("guardianFossilEffectsEnabled", true);
        builder.pop();

        builder.push("Misc");
            shootBombsFromDispensers = builder.comment("En-/Disables the ability to shoot bombs from dispensers.").define("shootBombsFromDispensers", false);
        builder.pop();

        builder.build();
    }

    public static void init()
    {
    }
}
