package net.tlotd.world;

import com.google.common.collect.ImmutableList;
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
import net.minecraft.world.gen.foliage.JungleFoliagePlacer;
import net.minecraft.world.gen.stateprovider.BlockStateProvider;
import net.minecraft.world.gen.treedecorator.TrunkVineTreeDecorator;
import net.minecraft.world.gen.trunk.GiantTrunkPlacer;
import net.minecraft.world.gen.trunk.StraightTrunkPlacer;
import net.tlotd.TLOTD;
import net.tlotd.block.ModBlocks;
import net.tlotd.world.tree.ShelfMushroomTreeDecorator;

import java.util.List;

public class ModConfiguredFeatures {

    public static final RegistryKey<ConfiguredFeature<?,?>> MARBLE_KEY = registerKey("marble");
    public static final RegistryKey<ConfiguredFeature<?,?>> LIMESTONE_KEY = registerKey("limestone");
    public static final RegistryKey<ConfiguredFeature<?,?>> RED_DEEPSLATE_KEY = registerKey("red_deepslate");

    public static final RegistryKey<ConfiguredFeature<?,?>> NETHER_SULFUR_ORE_KEY = registerKey("nether_sulfur_ore");

    public static final RegistryKey<ConfiguredFeature<?,?>> ENDSTONE_ENDURIUM_ORE_KEY = registerKey("endstone_endurium_ore");

    public static final RegistryKey<ConfiguredFeature<?,?>> ALUMINIUM_ORE_KEY = registerKey("aluminium_ore");

    public static final RegistryKey<ConfiguredFeature<?,?>> LEAD_ORE_KEY = registerKey("lead_ore");

    public static final RegistryKey<ConfiguredFeature<?,?>> DEEPSLATE_URANIUM_ORE_KEY = registerKey("deepslate_uranium_ore");
    public static final RegistryKey<ConfiguredFeature<?,?>> DEEPSLATE_FOSSIL_KEY = registerKey("deepslate_fossil");
    public static final RegistryKey<ConfiguredFeature<?,?>> HELIORITE_ORE_KEY = registerKey("heliorite_ore");
    public static final RegistryKey<ConfiguredFeature<?,?>> PALLADIUM_ORE_KEY = registerKey("palladium_ore");
    public static final RegistryKey<ConfiguredFeature<?,?>> JURASSOLINE_ORE_KEY = registerKey("jurassoline_ore");
    public static final RegistryKey<ConfiguredFeature<?,?>> CINNABAR_ORE_KEY = registerKey("cinnabar_ore");
    public static final RegistryKey<ConfiguredFeature<?,?>> NEBULAR_ORE_KEY = registerKey("nebular_ore");

    public static final RegistryKey<ConfiguredFeature<?,?>> MITHRIL_ORE_KEY = registerKey("mithril_ore");

    public static final RegistryKey<ConfiguredFeature<?, ?>> GINKGO_KEY = registerKey("ginkgo");
    public static final RegistryKey<ConfiguredFeature<?, ?>> MEGA_GINKGO_KEY = registerKey("mega_ginkgo");

    public static final RegistryKey<ConfiguredFeature<?,?>> RED_GRAVEL_KEY = registerKey("red_gravel");
    public static final RegistryKey<ConfiguredFeature<?,?>> RED_DEEPSLATE_MARBLE_KEY = registerKey("red_deepslate_marble");
    public static final RegistryKey<ConfiguredFeature<?,?>> RED_DEEPSLATE_LIMESTONE_KEY = registerKey("red_deepslate_limestone");

    public static final RegistryKey<ConfiguredFeature<?,?>> RED_DEEPSLATE_FOSSIL_KEY = registerKey("red_deepslate_fossil");

    public static final RegistryKey<ConfiguredFeature<?,?>> RED_DEEPSLATE_SULFUR_ORE_KEY = registerKey("red_deepslate_sulfur_ore");
    public static final RegistryKey<ConfiguredFeature<?,?>> RED_DEEPSLATE_QUARTZ_ORE_KEY = registerKey("red_deepslate_quartz_ore");
    public static final RegistryKey<ConfiguredFeature<?,?>> RED_DEEPSLATE_IRON_ORE_KEY = registerKey("red_deepslate_iron_ore");
    public static final RegistryKey<ConfiguredFeature<?,?>> RED_DEEPSLATE_COPPER_ORE_KEY = registerKey("red_deepslate_copper_ore");
    public static final RegistryKey<ConfiguredFeature<?,?>> RED_DEEPSLATE_GOLD_ORE_KEY = registerKey("red_deepslate_gold_ore");
    public static final RegistryKey<ConfiguredFeature<?,?>> RED_DEEPSLATE_REDSTONE_ORE_KEY = registerKey("red_deepslate_redstone_ore");
    public static final RegistryKey<ConfiguredFeature<?,?>> RED_DEEPSLATE_EMERALD_ORE_KEY = registerKey("red_deepslate_emerald_ore");
    public static final RegistryKey<ConfiguredFeature<?,?>> RED_DEEPSLATE_LAPIS_ORE_KEY = registerKey("red_deepslate_lapis_ore");
    public static final RegistryKey<ConfiguredFeature<?,?>> RED_DEEPSLATE_DIAMOND_ORE_KEY = registerKey("red_deepslate_diamond_ore");

    public static final RegistryKey<ConfiguredFeature<?,?>> RED_DEEPSLATE_ZINC_ORE_KEY = registerKey("red_deepslate_zinc_ore");

    public static final RegistryKey<ConfiguredFeature<?,?>> MOON_ROCK_COAL_ORE_KEY = registerKey("moon_rock_coal_ore");
    public static final RegistryKey<ConfiguredFeature<?,?>> MOON_ROCK_IRON_ORE_KEY = registerKey("moon_rock_iron_ore");
    public static final RegistryKey<ConfiguredFeature<?,?>> MOON_ROCK_LUNAR_CALLAINUS_ORE_KEY = registerKey("moon_rock_lunar_callainus_ore");

    public static void bootstrap(Registerable<ConfiguredFeature<?,?>> context) {
        RuleTest stoneReplacables = new TagMatchRuleTest(BlockTags.STONE_ORE_REPLACEABLES);
        RuleTest deepslateReplacables = new TagMatchRuleTest(BlockTags.DEEPSLATE_ORE_REPLACEABLES);
        RuleTest bedrockReplacables = new BlockMatchRuleTest(Blocks.BEDROCK);
        RuleTest netherReplacables = new TagMatchRuleTest(BlockTags.BASE_STONE_NETHER);
        RuleTest endReplacables = new BlockMatchRuleTest(Blocks.END_STONE);

        RuleTest redDeepslateReplacables = new BlockMatchRuleTest(ModBlocks.RED_DEEPSLATE);
        RuleTest lunarReplacables = new BlockMatchRuleTest(ModBlocks.MEGAREGOLITH);

        List<OreFeatureConfig.Target> redGravel =
                List.of(OreFeatureConfig.createTarget(redDeepslateReplacables, ModBlocks.RED_GRAVEL.getDefaultState()));
        List<OreFeatureConfig.Target> redDeepslateMarble =
                List.of(OreFeatureConfig.createTarget(redDeepslateReplacables, ModBlocks.MARBLE.getDefaultState()));
        List<OreFeatureConfig.Target> redDeepslateLimestone =
                List.of(OreFeatureConfig.createTarget(redDeepslateReplacables, ModBlocks.LIMESTONE.getDefaultState()));

        List<OreFeatureConfig.Target> redDeepslateFossil =
                List.of(OreFeatureConfig.createTarget(redDeepslateReplacables, ModBlocks.RED_DEEPSLATE_FOSSIL.getDefaultState()));

        List<OreFeatureConfig.Target> redDeepslateSulfurOre =
                List.of(OreFeatureConfig.createTarget(redDeepslateReplacables, ModBlocks.RED_DEEPSLATE_SULFUR_ORE.getDefaultState()));
        List<OreFeatureConfig.Target> redDeepslateQuartzOre =
                List.of(OreFeatureConfig.createTarget(redDeepslateReplacables, ModBlocks.RED_DEEPSLATE_QUARTZ_ORE.getDefaultState()));

        List<OreFeatureConfig.Target> redDeepslateIronOre =
                List.of(OreFeatureConfig.createTarget(redDeepslateReplacables, ModBlocks.RED_DEEPSLATE_IRON_ORE.getDefaultState()));
        List<OreFeatureConfig.Target> redDeepslateCopperOre =
                List.of(OreFeatureConfig.createTarget(redDeepslateReplacables, ModBlocks.RED_DEEPSLATE_COPPER_ORE.getDefaultState()));
        List<OreFeatureConfig.Target> redDeepslateGoldOre =
                List.of(OreFeatureConfig.createTarget(redDeepslateReplacables, ModBlocks.RED_DEEPSLATE_GOLD_ORE.getDefaultState()));
        List<OreFeatureConfig.Target> redDeepslateRedstoneOre =
                List.of(OreFeatureConfig.createTarget(redDeepslateReplacables, ModBlocks.RED_DEEPSLATE_REDSTONE_ORE.getDefaultState()));
        List<OreFeatureConfig.Target> redDeepslateEmeraldOre =
                List.of(OreFeatureConfig.createTarget(redDeepslateReplacables, ModBlocks.RED_DEEPSLATE_EMERALD_ORE.getDefaultState()));
        List<OreFeatureConfig.Target> redDeepslateLapisOre =
                List.of(OreFeatureConfig.createTarget(redDeepslateReplacables, ModBlocks.RED_DEEPSLATE_LAPIS_ORE.getDefaultState()));
        List<OreFeatureConfig.Target> redDeepslateDiamondOre =
                List.of(OreFeatureConfig.createTarget(redDeepslateReplacables, ModBlocks.RED_DEEPSLATE_DIAMOND_ORE.getDefaultState()));

        List<OreFeatureConfig.Target> redDeepslateZincOre =
                List.of(OreFeatureConfig.createTarget(redDeepslateReplacables, ModBlocks.RED_DEEPSLATE_ZINC_ORE.getDefaultState()));

        List<OreFeatureConfig.Target> stoneMarble =
                List.of(OreFeatureConfig.createTarget(stoneReplacables, ModBlocks.MARBLE.getDefaultState()));

        List<OreFeatureConfig.Target> deepslateLimestone =
                List.of(OreFeatureConfig.createTarget(deepslateReplacables, ModBlocks.LIMESTONE.getDefaultState()));

        List<OreFeatureConfig.Target> deepslateRedDeepslate =
                List.of(OreFeatureConfig.createTarget(deepslateReplacables, ModBlocks.RED_DEEPSLATE.getDefaultState()));

        List<OreFeatureConfig.Target> netherSulfurOre =
                List.of(OreFeatureConfig.createTarget(netherReplacables, ModBlocks.NETHER_SULFUR_ORE.getDefaultState()));

        List<OreFeatureConfig.Target> endstoneEnduriumOre =
                List.of(OreFeatureConfig.createTarget(endReplacables, ModBlocks.END_ENDURIUM_ORE.getDefaultState()));

        List<OreFeatureConfig.Target> aluminiumOre =
                List.of(
                        OreFeatureConfig.createTarget(deepslateReplacables, ModBlocks.ALUMINIUM_ORE.getDefaultState()),
                        OreFeatureConfig.createTarget(redDeepslateReplacables, ModBlocks.RED_DEEPSLATE_ALUMINIUM_ORE.getDefaultState())
                );

        List<OreFeatureConfig.Target> leadOre =
                List.of(
                        OreFeatureConfig.createTarget(deepslateReplacables, ModBlocks.LEAD_ORE.getDefaultState()),
                        OreFeatureConfig.createTarget(redDeepslateReplacables, ModBlocks.RED_DEEPSLATE_LEAD_ORE.getDefaultState())
                );

        List<OreFeatureConfig.Target> deepslateUraniumOre =
                List.of(
                        OreFeatureConfig.createTarget(deepslateReplacables, ModBlocks.DEEPSLATE_URANIUM_ORE.getDefaultState()),
                        OreFeatureConfig.createTarget(redDeepslateReplacables, ModBlocks.RED_DEEPSLATE_URANIUM_ORE.getDefaultState())
                );

        List<OreFeatureConfig.Target> deepslateFossil =
                List.of(OreFeatureConfig.createTarget(deepslateReplacables, ModBlocks.DEEPSLATE_FOSSIL.getDefaultState()));

        List<OreFeatureConfig.Target> deepslateHelioriteOres =
                List.of(
                        OreFeatureConfig.createTarget(deepslateReplacables, ModBlocks.DEEPSLATE_HELIORITE_ORE.getDefaultState()),
                        OreFeatureConfig.createTarget(redDeepslateReplacables, ModBlocks.RED_DEEPSLATE_HELIORITE_ORE.getDefaultState())
                );
        List<OreFeatureConfig.Target> deepslatePalladiumOres =
                List.of(
                        OreFeatureConfig.createTarget(deepslateReplacables, ModBlocks.DEEPSLATE_PALLADIUM_ORE.getDefaultState()),
                        OreFeatureConfig.createTarget(redDeepslateReplacables, ModBlocks.RED_DEEPSLATE_PALLADIUM_ORE.getDefaultState())
                );
        List<OreFeatureConfig.Target> deepslateJurassolineOres =
                List.of(
                        OreFeatureConfig.createTarget(deepslateReplacables, ModBlocks.DEEPSLATE_JURASSOLINE_ORE.getDefaultState()),
                        OreFeatureConfig.createTarget(redDeepslateReplacables, ModBlocks.RED_DEEPSLATE_JURASSOLINE_ORE.getDefaultState())
                );
        List<OreFeatureConfig.Target> deepslateCinnabarOres =
                List.of(
                        OreFeatureConfig.createTarget(deepslateReplacables, ModBlocks.DEEPSLATE_CINNABAR_ORE.getDefaultState()),
                        OreFeatureConfig.createTarget(redDeepslateReplacables, ModBlocks.RED_DEEPSLATE_CINNABAR_ORE.getDefaultState())
                );
        List<OreFeatureConfig.Target> deepslateNebularOres =
                List.of(
                        OreFeatureConfig.createTarget(deepslateReplacables, ModBlocks.DEEPSLATE_NEBULAR_ORE.getDefaultState()),
                        OreFeatureConfig.createTarget(redDeepslateReplacables, ModBlocks.RED_DEEPSLATE_NEBULAR_ORE.getDefaultState())
                );

        List<OreFeatureConfig.Target> bedrockMithrilOres =
                List.of(OreFeatureConfig.createTarget(bedrockReplacables, ModBlocks.BEDROCK_MITHRIL_ORE.getDefaultState()));

        List<OreFeatureConfig.Target> moonRockCoalOre =
                List.of(OreFeatureConfig.createTarget(lunarReplacables, ModBlocks.MEGAREGOLITH_COAL_ORE.getDefaultState()));
        List<OreFeatureConfig.Target> moonRockIronOre =
                List.of(OreFeatureConfig.createTarget(lunarReplacables, ModBlocks.MEGAREGOLITH_IRON_ORE.getDefaultState()));
        List<OreFeatureConfig.Target> moonRockLunarCallainusOre =
                List.of(OreFeatureConfig.createTarget(lunarReplacables, ModBlocks.MEGAREGOLITH_LUNAR_CALLAINUS_ORE.getDefaultState()));

        register(context, MARBLE_KEY, Feature.ORE, new OreFeatureConfig(stoneMarble, 32));
        register(context, LIMESTONE_KEY, Feature.ORE, new OreFeatureConfig(deepslateLimestone, 32));
        register(context, RED_DEEPSLATE_KEY, Feature.ORE, new OreFeatureConfig(deepslateRedDeepslate, 32));

        register(context, NETHER_SULFUR_ORE_KEY, Feature.ORE, new OreFeatureConfig(netherSulfurOre, 8));
        register(context, ENDSTONE_ENDURIUM_ORE_KEY, Feature.ORE, new OreFeatureConfig(endstoneEnduriumOre, 8));

        register(context, ALUMINIUM_ORE_KEY, Feature.ORE, new OreFeatureConfig(aluminiumOre, 8));

        register(context, LEAD_ORE_KEY, Feature.ORE, new OreFeatureConfig(leadOre, 8));
        register(context, DEEPSLATE_URANIUM_ORE_KEY, Feature.ORE, new OreFeatureConfig(deepslateUraniumOre, 8));

        register(context, DEEPSLATE_FOSSIL_KEY, Feature.ORE, new OreFeatureConfig(deepslateFossil, 4));
        register(context, HELIORITE_ORE_KEY, Feature.ORE, new OreFeatureConfig(deepslateHelioriteOres, 8));
        register(context, PALLADIUM_ORE_KEY, Feature.ORE, new OreFeatureConfig(deepslatePalladiumOres, 8));
        register(context, JURASSOLINE_ORE_KEY, Feature.ORE, new OreFeatureConfig(deepslateJurassolineOres, 8));
        register(context, CINNABAR_ORE_KEY, Feature.ORE, new OreFeatureConfig(deepslateCinnabarOres, 8));
        register(context, NEBULAR_ORE_KEY, Feature.ORE, new OreFeatureConfig(deepslateNebularOres, 8));

        register(context, MITHRIL_ORE_KEY, Feature.ORE, new OreFeatureConfig(bedrockMithrilOres, 8));

        register(context, RED_GRAVEL_KEY, Feature.ORE, new OreFeatureConfig(redGravel, 32));
        register(context, RED_DEEPSLATE_MARBLE_KEY, Feature.ORE, new OreFeatureConfig(redDeepslateMarble, 32));
        register(context, RED_DEEPSLATE_LIMESTONE_KEY, Feature.ORE, new OreFeatureConfig(redDeepslateLimestone, 32));

        register(context, RED_DEEPSLATE_FOSSIL_KEY, Feature.ORE, new OreFeatureConfig(redDeepslateFossil, 4));

        register(context, RED_DEEPSLATE_SULFUR_ORE_KEY, Feature.ORE, new OreFeatureConfig(redDeepslateSulfurOre, 8));
        register(context, RED_DEEPSLATE_QUARTZ_ORE_KEY, Feature.ORE, new OreFeatureConfig(redDeepslateQuartzOre, 8));
        register(context, RED_DEEPSLATE_IRON_ORE_KEY, Feature.ORE, new OreFeatureConfig(redDeepslateIronOre, 8));
        register(context, RED_DEEPSLATE_COPPER_ORE_KEY, Feature.ORE, new OreFeatureConfig(redDeepslateCopperOre, 8));
        register(context, RED_DEEPSLATE_GOLD_ORE_KEY, Feature.ORE, new OreFeatureConfig(redDeepslateGoldOre, 8));
        register(context, RED_DEEPSLATE_REDSTONE_ORE_KEY, Feature.ORE, new OreFeatureConfig(redDeepslateRedstoneOre, 8));
        register(context, RED_DEEPSLATE_EMERALD_ORE_KEY, Feature.ORE, new OreFeatureConfig(redDeepslateEmeraldOre, 8));
        register(context, RED_DEEPSLATE_LAPIS_ORE_KEY, Feature.ORE, new OreFeatureConfig(redDeepslateLapisOre, 8));
        register(context, RED_DEEPSLATE_DIAMOND_ORE_KEY, Feature.ORE, new OreFeatureConfig(redDeepslateDiamondOre, 8));

        register(context, RED_DEEPSLATE_ZINC_ORE_KEY, Feature.ORE, new OreFeatureConfig(redDeepslateZincOre, 8));

        register(context, MOON_ROCK_COAL_ORE_KEY, Feature.ORE, new OreFeatureConfig(moonRockCoalOre, 8));
        register(context, MOON_ROCK_IRON_ORE_KEY, Feature.ORE, new OreFeatureConfig(moonRockIronOre, 8));
        register(context, MOON_ROCK_LUNAR_CALLAINUS_ORE_KEY, Feature.ORE, new OreFeatureConfig(moonRockLunarCallainusOre, 4));

        register(context, GINKGO_KEY, Feature.TREE, new TreeFeatureConfig.Builder(
                        BlockStateProvider.of(ModBlocks.GINKGO_LOG),
                        new StraightTrunkPlacer(7, 3, 2),
                        BlockStateProvider.of(ModBlocks.GINKGO_LEAVES),
                        new JungleFoliagePlacer(
                                ConstantIntProvider.create(1),
                                ConstantIntProvider.create(0),
                                2
                        ),
                        new TwoLayersFeatureSize(1, 1, 2))
                        .dirtProvider(BlockStateProvider.of(ModBlocks.RICH_DIRT))
                        .decorators(ImmutableList.of(
                                ShelfMushroomTreeDecorator.INSTANCE,
                                TrunkVineTreeDecorator.INSTANCE
                        ))
                        .ignoreVines()
                        .build()
        );

        register(context, MEGA_GINKGO_KEY, Feature.TREE, new TreeFeatureConfig.Builder(
                        BlockStateProvider.of(ModBlocks.GINKGO_LOG),
                        new GiantTrunkPlacer(10, 4, 8),
                        BlockStateProvider.of(ModBlocks.GINKGO_LEAVES),
                        new JungleFoliagePlacer(
                                ConstantIntProvider.create(2),
                                ConstantIntProvider.create(0),
                                2
                        ),
                        new TwoLayersFeatureSize(1, 1, 2))
                        .dirtProvider(BlockStateProvider.of(ModBlocks.RICH_DIRT))
                        .decorators(ImmutableList.of(
                                ShelfMushroomTreeDecorator.INSTANCE,
                                TrunkVineTreeDecorator.INSTANCE
                        ))
                        .ignoreVines()
                        .build()
        );
    }

    public static RegistryKey<ConfiguredFeature<?,?>> registerKey(String name) {
        return RegistryKey.of(RegistryKeys.CONFIGURED_FEATURE, new Identifier(TLOTD.MOD_ID, name));
    }

    private static <FC extends FeatureConfig, F extends Feature<FC>> void register(Registerable<ConfiguredFeature<?,?>> context, RegistryKey<ConfiguredFeature<?,?>> key, F feature, FC configuration) {
        context.register(key, new ConfiguredFeature<>(feature, configuration));
    }
}
