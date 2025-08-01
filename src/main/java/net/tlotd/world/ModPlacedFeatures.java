package net.tlotd.world;

import net.minecraft.registry.Registerable;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.Identifier;
import net.minecraft.world.gen.YOffset;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.PlacedFeature;
import net.minecraft.world.gen.placementmodifier.HeightRangePlacementModifier;
import net.minecraft.world.gen.placementmodifier.PlacementModifier;
import net.tlotd.TLOTD;

import java.util.List;

public class ModPlacedFeatures {
    public static final RegistryKey<PlacedFeature> MARBLE_PLACED_KEY = registerKey("marble_placed");
    public static final RegistryKey<PlacedFeature> LIMESTONE_PLACED_KEY = registerKey("limestone_placed");
    public static final RegistryKey<PlacedFeature> RED_DEEPSLATE_PLACED_KEY = registerKey("red_deepslate_placed");

    public static final RegistryKey<PlacedFeature> NETHER_SULFUR_ORE_PLACED_KEY = registerKey("nether_sulfur_ore_placed");
    public static final RegistryKey<PlacedFeature> ENDSTONE_ENDURIUM_ORE_PLACED_KEY = registerKey("endstone_endurium_ore_placed");

    public static final RegistryKey<PlacedFeature> LEAD_ORE_PLACED_KEY = registerKey("lead_ore_placed");
    public static final RegistryKey<PlacedFeature> DEEPSLATE_URANIUM_ORE_PLACED_KEY = registerKey("deepslate_uranium_ore_placed");

    public static final RegistryKey<PlacedFeature> DEEPSLATE_FOSSIL_PLACED_KEY = registerKey("deepslate_fossil_placed");
    public static final RegistryKey<PlacedFeature> HELIORITE_ORE_PLACED_KEY = registerKey("heliorite_ore_placed");
    public static final RegistryKey<PlacedFeature> PALLADIUM_ORE_PLACED_KEY = registerKey("palladium_ore_placed");
    public static final RegistryKey<PlacedFeature> JURASSOLINE_ORE_PLACED_KEY = registerKey("jurassoline_ore_placed");
    public static final RegistryKey<PlacedFeature> CINNABAR_ORE_PLACED_KEY = registerKey("cinnabar_ore_placed");
    public static final RegistryKey<PlacedFeature> NEBULAR_ORE_PLACED_KEY = registerKey("nebular_ore_placed");

    public static final RegistryKey<PlacedFeature> MITHRIL_ORE_PLACED_KEY = registerKey("mithril_ore_placed");

    public static final RegistryKey<PlacedFeature> RED_GRAVEL_PLACED_KEY = registerKey("red_gravel_placed");
    public static final RegistryKey<PlacedFeature> RED_DEEPSLATE_MARBLE_PLACED_KEY = registerKey("red_deepslate_marble_placed");
    public static final RegistryKey<PlacedFeature> RED_DEEPSLATE_LIMESTONE_PLACED_KEY = registerKey("red_deepslate_limestone_placed");

    public static final RegistryKey<PlacedFeature> RED_DEEPSLATE_SULFUR_ORE_PLACED_KEY = registerKey("red_deepslate_sulfur_ore_placed");
    public static final RegistryKey<PlacedFeature> RED_DEEPSLATE_QUARTZ_ORE_PLACED_KEY = registerKey("red_deepslate_quartz_ore_placed");
    public static final RegistryKey<PlacedFeature> RED_DEEPSLATE_IRON_ORE_PLACED_KEY = registerKey("red_deepslate_iron_ore_placed");
    public static final RegistryKey<PlacedFeature> RED_DEEPSLATE_COPPER_ORE_PLACED_KEY = registerKey("red_deepslate_copper_ore_placed");
    public static final RegistryKey<PlacedFeature> RED_DEEPSLATE_GOLD_ORE_PLACED_KEY = registerKey("red_deepslate_gold_ore_placed");
    public static final RegistryKey<PlacedFeature> RED_DEEPSLATE_REDSTONE_ORE_PLACED_KEY = registerKey("red_deepslate_redstone_ore_placed");
    public static final RegistryKey<PlacedFeature> RED_DEEPSLATE_EMERALD_ORE_PLACED_KEY = registerKey("red_deepslate_emerald_ore_placed");
    public static final RegistryKey<PlacedFeature> RED_DEEPSLATE_LAPIS_ORE_PLACED_KEY = registerKey("red_deepslate_lapis_ore_placed");
    public static final RegistryKey<PlacedFeature> RED_DEEPSLATE_DIAMOND_ORE_PLACED_KEY = registerKey("red_deepslate_diamond_ore_placed");

    public static final RegistryKey<PlacedFeature> RED_DEEPSLATE_ZINC_ORE_PLACED_KEY = registerKey("red_deepslate_zinc_ore_placed");

    public static final RegistryKey<PlacedFeature> MOON_ROCK_COAL_ORE_PLACED_KEY = registerKey("moon_rock_coal_ore_placed");
    public static final RegistryKey<PlacedFeature> MOON_ROCK_IRON_ORE_PLACED_KEY = registerKey("moon_rock_iron_ore_placed");
    public static final RegistryKey<PlacedFeature> MOON_ROCK_LUNAR_CALLAINUS_ORE_PLACED_KEY = registerKey("moon_rock_lunar_callainus_ore_placed");

    public static void bootstrap(Registerable<PlacedFeature> context) {
        var configuredFeatureRegistryEntryLookup = context.getRegistryLookup(RegistryKeys.CONFIGURED_FEATURE);
        register(context, NETHER_SULFUR_ORE_PLACED_KEY, configuredFeatureRegistryEntryLookup.getOrThrow(ModConfiguredFeatures.NETHER_SULFUR_ORE_KEY), ModOrePlacement.modifiersWithCount(8, HeightRangePlacementModifier.uniform(YOffset.fixed(0), YOffset.fixed(128))));
        register(context, ENDSTONE_ENDURIUM_ORE_PLACED_KEY, configuredFeatureRegistryEntryLookup.getOrThrow(ModConfiguredFeatures.ENDSTONE_ENDURIUM_ORE_KEY), ModOrePlacement.modifiersWithCount(8, HeightRangePlacementModifier.uniform(YOffset.fixed(0), YOffset.fixed(128))));

        register(context, MARBLE_PLACED_KEY, configuredFeatureRegistryEntryLookup.getOrThrow(ModConfiguredFeatures.MARBLE_KEY), ModOrePlacement.modifiersWithCount(16, HeightRangePlacementModifier.uniform(YOffset.fixed(0), YOffset.fixed(320))));
        register(context, LIMESTONE_PLACED_KEY, configuredFeatureRegistryEntryLookup.getOrThrow(ModConfiguredFeatures.LIMESTONE_KEY), ModOrePlacement.modifiersWithCount(16, HeightRangePlacementModifier.uniform(YOffset.fixed(-64), YOffset.fixed(320))));
        register(context, RED_DEEPSLATE_PLACED_KEY, configuredFeatureRegistryEntryLookup.getOrThrow(ModConfiguredFeatures.RED_DEEPSLATE_KEY), ModOrePlacement.modifiersWithCount(8, HeightRangePlacementModifier.uniform(YOffset.fixed(-64), YOffset.fixed(0))));

        register(context, LEAD_ORE_PLACED_KEY, configuredFeatureRegistryEntryLookup.getOrThrow(ModConfiguredFeatures.LEAD_ORE_KEY), ModOrePlacement.modifiersWithCount(4, HeightRangePlacementModifier.uniform(YOffset.fixed(0), YOffset.fixed(128))));
        register(context, DEEPSLATE_URANIUM_ORE_PLACED_KEY, configuredFeatureRegistryEntryLookup.getOrThrow(ModConfiguredFeatures.DEEPSLATE_URANIUM_ORE_KEY), ModOrePlacement.modifiersWithCount(2, HeightRangePlacementModifier.uniform(YOffset.fixed(-64), YOffset.fixed(0))));

        register(context, DEEPSLATE_FOSSIL_PLACED_KEY, configuredFeatureRegistryEntryLookup.getOrThrow(ModConfiguredFeatures.DEEPSLATE_FOSSIL_KEY), ModOrePlacement.modifiersWithCount(2, HeightRangePlacementModifier.uniform(YOffset.fixed(-64), YOffset.fixed(0))));
        register(context, HELIORITE_ORE_PLACED_KEY, configuredFeatureRegistryEntryLookup.getOrThrow(ModConfiguredFeatures.HELIORITE_ORE_KEY), ModOrePlacement.modifiersWithCount(2, HeightRangePlacementModifier.uniform(YOffset.fixed(-64), YOffset.fixed(0))));
        register(context, JURASSOLINE_ORE_PLACED_KEY, configuredFeatureRegistryEntryLookup.getOrThrow(ModConfiguredFeatures.JURASSOLINE_ORE_KEY), ModOrePlacement.modifiersWithCount(2, HeightRangePlacementModifier.uniform(YOffset.fixed(-64), YOffset.fixed(0))));
        register(context, PALLADIUM_ORE_PLACED_KEY, configuredFeatureRegistryEntryLookup.getOrThrow(ModConfiguredFeatures.PALLADIUM_ORE_KEY), ModOrePlacement.modifiersWithCount(2, HeightRangePlacementModifier.uniform(YOffset.fixed(-64), YOffset.fixed(0))));
        register(context, CINNABAR_ORE_PLACED_KEY, configuredFeatureRegistryEntryLookup.getOrThrow(ModConfiguredFeatures.CINNABAR_ORE_KEY), ModOrePlacement.modifiersWithCount(2, HeightRangePlacementModifier.uniform(YOffset.fixed(-64), YOffset.fixed(0))));
        register(context, NEBULAR_ORE_PLACED_KEY, configuredFeatureRegistryEntryLookup.getOrThrow(ModConfiguredFeatures.NEBULAR_ORE_KEY), ModOrePlacement.modifiersWithCount(2, HeightRangePlacementModifier.uniform(YOffset.fixed(-64), YOffset.fixed(0))));
        register(context, MITHRIL_ORE_PLACED_KEY, configuredFeatureRegistryEntryLookup.getOrThrow(ModConfiguredFeatures.MITHRIL_ORE_KEY), ModOrePlacement.modifiersWithCount(12, HeightRangePlacementModifier.uniform(YOffset.fixed(-64), YOffset.fixed(128))));

        register(context, RED_GRAVEL_PLACED_KEY, configuredFeatureRegistryEntryLookup.getOrThrow(ModConfiguredFeatures.RED_GRAVEL_KEY), ModOrePlacement.modifiersWithCount(16, HeightRangePlacementModifier.uniform(YOffset.fixed(-64), YOffset.fixed(320))));
        register(context, RED_DEEPSLATE_MARBLE_PLACED_KEY, configuredFeatureRegistryEntryLookup.getOrThrow(ModConfiguredFeatures.RED_DEEPSLATE_MARBLE_KEY), ModOrePlacement.modifiersWithCount(16, HeightRangePlacementModifier.uniform(YOffset.fixed(-64), YOffset.fixed(320))));
        register(context, RED_DEEPSLATE_LIMESTONE_PLACED_KEY, configuredFeatureRegistryEntryLookup.getOrThrow(ModConfiguredFeatures.RED_DEEPSLATE_LIMESTONE_KEY), ModOrePlacement.modifiersWithCount(16, HeightRangePlacementModifier.uniform(YOffset.fixed(-64), YOffset.fixed(320))));

        register(context, RED_DEEPSLATE_SULFUR_ORE_PLACED_KEY, configuredFeatureRegistryEntryLookup.getOrThrow(ModConfiguredFeatures.RED_DEEPSLATE_SULFUR_ORE_KEY), ModOrePlacement.modifiersWithCount(16, HeightRangePlacementModifier.uniform(YOffset.fixed(-64), YOffset.fixed(320))));
        register(context, RED_DEEPSLATE_QUARTZ_ORE_PLACED_KEY, configuredFeatureRegistryEntryLookup.getOrThrow(ModConfiguredFeatures.RED_DEEPSLATE_QUARTZ_ORE_KEY), ModOrePlacement.modifiersWithCount(16, HeightRangePlacementModifier.uniform(YOffset.fixed(-64), YOffset.fixed(320))));
        register(context, RED_DEEPSLATE_IRON_ORE_PLACED_KEY, configuredFeatureRegistryEntryLookup.getOrThrow(ModConfiguredFeatures.RED_DEEPSLATE_IRON_ORE_KEY), ModOrePlacement.modifiersWithCount(16, HeightRangePlacementModifier.uniform(YOffset.fixed(-64), YOffset.fixed(320))));
        register(context, RED_DEEPSLATE_COPPER_ORE_PLACED_KEY, configuredFeatureRegistryEntryLookup.getOrThrow(ModConfiguredFeatures.RED_DEEPSLATE_COPPER_ORE_KEY), ModOrePlacement.modifiersWithCount(16, HeightRangePlacementModifier.uniform(YOffset.fixed(-64), YOffset.fixed(320))));
        register(context, RED_DEEPSLATE_GOLD_ORE_PLACED_KEY, configuredFeatureRegistryEntryLookup.getOrThrow(ModConfiguredFeatures.RED_DEEPSLATE_GOLD_ORE_KEY), ModOrePlacement.modifiersWithCount(16, HeightRangePlacementModifier.uniform(YOffset.fixed(-64), YOffset.fixed(320))));
        register(context, RED_DEEPSLATE_REDSTONE_ORE_PLACED_KEY, configuredFeatureRegistryEntryLookup.getOrThrow(ModConfiguredFeatures.RED_DEEPSLATE_REDSTONE_ORE_KEY), ModOrePlacement.modifiersWithCount(16, HeightRangePlacementModifier.uniform(YOffset.fixed(-64), YOffset.fixed(320))));
        register(context, RED_DEEPSLATE_EMERALD_ORE_PLACED_KEY, configuredFeatureRegistryEntryLookup.getOrThrow(ModConfiguredFeatures.RED_DEEPSLATE_EMERALD_ORE_KEY), ModOrePlacement.modifiersWithCount(16, HeightRangePlacementModifier.uniform(YOffset.fixed(-64), YOffset.fixed(320))));
        register(context, RED_DEEPSLATE_LAPIS_ORE_PLACED_KEY, configuredFeatureRegistryEntryLookup.getOrThrow(ModConfiguredFeatures.RED_DEEPSLATE_LAPIS_ORE_KEY), ModOrePlacement.modifiersWithCount(16, HeightRangePlacementModifier.uniform(YOffset.fixed(-64), YOffset.fixed(320))));
        register(context, RED_DEEPSLATE_DIAMOND_ORE_PLACED_KEY, configuredFeatureRegistryEntryLookup.getOrThrow(ModConfiguredFeatures.RED_DEEPSLATE_DIAMOND_ORE_KEY), ModOrePlacement.modifiersWithCount(16, HeightRangePlacementModifier.uniform(YOffset.fixed(-64), YOffset.fixed(320))));

        register(context, RED_DEEPSLATE_ZINC_ORE_PLACED_KEY, configuredFeatureRegistryEntryLookup.getOrThrow(ModConfiguredFeatures.RED_DEEPSLATE_ZINC_ORE_KEY), ModOrePlacement.modifiersWithCount(16, HeightRangePlacementModifier.uniform(YOffset.fixed(-64), YOffset.fixed(320))));

        register(context, MOON_ROCK_COAL_ORE_PLACED_KEY, configuredFeatureRegistryEntryLookup.getOrThrow(ModConfiguredFeatures.MOON_ROCK_COAL_ORE_KEY), ModOrePlacement.modifiersWithCount(24, HeightRangePlacementModifier.uniform(YOffset.fixed(-64), YOffset.fixed(320))));
        register(context, MOON_ROCK_IRON_ORE_PLACED_KEY, configuredFeatureRegistryEntryLookup.getOrThrow(ModConfiguredFeatures.MOON_ROCK_IRON_ORE_KEY), ModOrePlacement.modifiersWithCount(32, HeightRangePlacementModifier.uniform(YOffset.fixed(-64), YOffset.fixed(320))));
        register(context, MOON_ROCK_LUNAR_CALLAINUS_ORE_PLACED_KEY, configuredFeatureRegistryEntryLookup.getOrThrow(ModConfiguredFeatures.MOON_ROCK_LUNAR_CALLAINUS_ORE_KEY), ModOrePlacement.modifiersWithCount(12, HeightRangePlacementModifier.uniform(YOffset.fixed(-64), YOffset.fixed(320))));
    }

    public static RegistryKey<PlacedFeature> registerKey(String name) {
        return RegistryKey.of(RegistryKeys.PLACED_FEATURE, new Identifier(TLOTD.MOD_ID, name));
    }

    private static void register(Registerable<PlacedFeature> context, RegistryKey<PlacedFeature> key, RegistryEntry<ConfiguredFeature<?, ?>> configuration, List<PlacementModifier> modifiers) {
        context.register(key, new PlacedFeature(configuration, List.copyOf(modifiers)));
    }
}