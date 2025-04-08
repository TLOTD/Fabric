package net.tlotd.world;

import net.minecraft.block.Blocks;
import net.minecraft.registry.Registerable;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.structure.rule.BlockMatchRuleTest;
import net.minecraft.structure.rule.RuleTest;
import net.minecraft.structure.rule.TagMatchRuleTest;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.intprovider.ConstantIntProvider;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.feature.size.TwoLayersFeatureSize;
import net.minecraft.world.gen.foliage.BlobFoliagePlacer;
import net.minecraft.world.gen.stateprovider.BlockStateProvider;
import net.minecraft.world.gen.trunk.StraightTrunkPlacer;
import net.tlotd.TLOTD;
import net.tlotd.block.ModBlocks;

import java.util.List;

public class ModConfiguredFeatures {

    public static final RegistryKey<ConfiguredFeature<?,?>> MARBLE_KEY = registerKey("marble");
    public static final RegistryKey<ConfiguredFeature<?,?>> LIMESTONE_KEY = registerKey("limestone");
    public static final RegistryKey<ConfiguredFeature<?,?>> RED_DEEPSLATE_KEY = registerKey("red_deepslate");

    public static final RegistryKey<ConfiguredFeature<?,?>> NETHER_SULFUR_ORE_KEY = registerKey("nether_sulfur_ore");

    public static final RegistryKey<ConfiguredFeature<?,?>> ENDSTONE_ENDURIUM_ORE_KEY = registerKey("endstone_endurium_ore");

    public static final RegistryKey<ConfiguredFeature<?,?>> LEAD_ORE_KEY = registerKey("lead_ore");

    public static final RegistryKey<ConfiguredFeature<?,?>> DEEPSLATE_URANIUM_ORE_KEY = registerKey("deepslate_uranium_ore");
    public static final RegistryKey<ConfiguredFeature<?,?>> DEEPSLATE_FOSSIL_KEY = registerKey("deepslate_fossil");
    public static final RegistryKey<ConfiguredFeature<?,?>> HELIORITE_ORE_KEY = registerKey("heliorite_ore");
    public static final RegistryKey<ConfiguredFeature<?,?>> PALLADIUM_ORE_KEY = registerKey("palladium_ore");
    public static final RegistryKey<ConfiguredFeature<?,?>> JURASSOLINE_ORE_KEY = registerKey("jurassoline_ore");
    public static final RegistryKey<ConfiguredFeature<?,?>> CINNABAR_ORE_KEY = registerKey("cinnabar_ore");
    public static final RegistryKey<ConfiguredFeature<?,?>> NEBULAR_ORE_KEY = registerKey("nebular_ore");

    public static final RegistryKey<ConfiguredFeature<?,?>> MITHRIL_ORE_KEY = registerKey("mithril_ore");

    public static final RegistryKey<ConfiguredFeature<?,?>> GINKGO_KEY = registerKey("ginkgo");

    public static void bootstrap(Registerable<ConfiguredFeature<?,?>> context) {
        RuleTest stoneReplacables = new TagMatchRuleTest(BlockTags.STONE_ORE_REPLACEABLES);
        RuleTest deepslateReplacables = new TagMatchRuleTest(BlockTags.DEEPSLATE_ORE_REPLACEABLES);
        RuleTest bedrockReplacables = new BlockMatchRuleTest(Blocks.BEDROCK);
        RuleTest netherReplacables = new TagMatchRuleTest(BlockTags.BASE_STONE_NETHER);
        RuleTest endReplacables = new BlockMatchRuleTest(Blocks.END_STONE);

        List<OreFeatureConfig.Target> stoneMarble =
                List.of(OreFeatureConfig.createTarget(stoneReplacables, ModBlocks.MARBLE.getDefaultState()));

        List<OreFeatureConfig.Target> deepslateLimestone =
                List.of(OreFeatureConfig.createTarget(deepslateReplacables, ModBlocks.LIMESTONE.getDefaultState()));

        List<OreFeatureConfig.Target> deepslateRedDeepslate =
                List.of(OreFeatureConfig.createTarget(deepslateReplacables, ModBlocks.RED_DEEPSLATE.getDefaultState()));

        List<OreFeatureConfig.Target> netherSulfurOre =
                List.of(OreFeatureConfig.createTarget(netherReplacables, ModBlocks.NETHER_SULFUR_ORE.getDefaultState()));

        List<OreFeatureConfig.Target> endstoneEnduriumOre =
                List.of(OreFeatureConfig.createTarget(endReplacables, ModBlocks.END_STONE_ENDURIUM_ORE.getDefaultState()));

        List<OreFeatureConfig.Target> leadOre =
                List.of(OreFeatureConfig.createTarget(deepslateReplacables, ModBlocks.LEAD_ORE.getDefaultState()));

        List<OreFeatureConfig.Target> deepslateUraniumOre =
                List.of(OreFeatureConfig.createTarget(deepslateReplacables, ModBlocks.DEEPSLATE_URANIUM_ORE.getDefaultState()));

        List<OreFeatureConfig.Target> deepslateFossil =
                List.of(OreFeatureConfig.createTarget(deepslateReplacables, ModBlocks.DEEPSLATE_FOSSIL.getDefaultState()));

        List<OreFeatureConfig.Target> deepslateHelioriteOres =
                List.of(OreFeatureConfig.createTarget(deepslateReplacables, ModBlocks.HELIORITE_ORE.getDefaultState()));
        List<OreFeatureConfig.Target> deepslatePalladiumOres =
                List.of(OreFeatureConfig.createTarget(deepslateReplacables, ModBlocks.PALLADIUM_ORE.getDefaultState()));
        List<OreFeatureConfig.Target> deepslateJurassolineOres =
                List.of(OreFeatureConfig.createTarget(deepslateReplacables, ModBlocks.JURASSOLINE_ORE.getDefaultState()));
        List<OreFeatureConfig.Target> deepslateCinnabarOres =
                List.of(OreFeatureConfig.createTarget(deepslateReplacables, ModBlocks.CINNABAR_ORE.getDefaultState()));
        List<OreFeatureConfig.Target> deepslateNebularOres =
                List.of(OreFeatureConfig.createTarget(deepslateReplacables, ModBlocks.NEBULAR_ORE.getDefaultState()));

        List<OreFeatureConfig.Target> bedrockMithrilOres =
                List.of(OreFeatureConfig.createTarget(bedrockReplacables, ModBlocks.MITHRIL_ORE.getDefaultState()));

        register(context, MARBLE_KEY, Feature.ORE, new OreFeatureConfig(stoneMarble, 32));
        register(context, LIMESTONE_KEY, Feature.ORE, new OreFeatureConfig(deepslateLimestone, 32));
        register(context, RED_DEEPSLATE_KEY, Feature.ORE, new OreFeatureConfig(deepslateRedDeepslate, 32));

        register(context, NETHER_SULFUR_ORE_KEY, Feature.ORE, new OreFeatureConfig(netherSulfurOre, 8));
        register(context, ENDSTONE_ENDURIUM_ORE_KEY, Feature.ORE, new OreFeatureConfig(endstoneEnduriumOre, 8));

        register(context, LEAD_ORE_KEY, Feature.ORE, new OreFeatureConfig(leadOre, 8));
        register(context, DEEPSLATE_URANIUM_ORE_KEY, Feature.ORE, new OreFeatureConfig(deepslateUraniumOre, 8));

        register(context, DEEPSLATE_FOSSIL_KEY, Feature.ORE, new OreFeatureConfig(deepslateFossil, 4));
        register(context, HELIORITE_ORE_KEY, Feature.ORE, new OreFeatureConfig(deepslateHelioriteOres, 8));
        register(context, PALLADIUM_ORE_KEY, Feature.ORE, new OreFeatureConfig(deepslatePalladiumOres, 8));
        register(context, JURASSOLINE_ORE_KEY, Feature.ORE, new OreFeatureConfig(deepslateJurassolineOres, 8));
        register(context, CINNABAR_ORE_KEY, Feature.ORE, new OreFeatureConfig(deepslateCinnabarOres, 8));
        register(context, NEBULAR_ORE_KEY, Feature.ORE, new OreFeatureConfig(deepslateNebularOres, 8));

        register(context, MITHRIL_ORE_KEY, Feature.ORE, new OreFeatureConfig(bedrockMithrilOres, 8));

        register(context, GINKGO_KEY, Feature.TREE, new TreeFeatureConfig.Builder(
                BlockStateProvider.of(ModBlocks.GINKGO_LOG),
                new StraightTrunkPlacer(5, 4, 3),

                BlockStateProvider.of(ModBlocks.GINKGO_LEAVES),
                new BlobFoliagePlacer(ConstantIntProvider.create(3), ConstantIntProvider.create(2), 4),

                new TwoLayersFeatureSize(1, 0, 2)).build());
    }

    public static RegistryKey<ConfiguredFeature<?,?>> registerKey(String name) {
        return RegistryKey.of(RegistryKeys.CONFIGURED_FEATURE, new Identifier(TLOTD.MOD_ID, name));
    }

    private static <FC extends FeatureConfig, F extends Feature<FC>> void register(Registerable<ConfiguredFeature<?,?>> context, RegistryKey<ConfiguredFeature<?,?>> key, F feature, FC configuration) {
        context.register(key, new ConfiguredFeature<>(feature, configuration));
    }
}
