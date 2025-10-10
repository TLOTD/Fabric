package net.tlotd.config;

import com.mojang.datafixers.util.Pair;
import net.tlotd.TLOTD;

public class ModConfigs {
    public static SimpleConfig CONFIG;
    private static ModConfigProvider configs;

    public static String CONFIG_VERSION;
    public static boolean MITHRIL_ANVIL_NEEDS_DIRECT_MOONLIGHT;
    public static boolean WITCHING_TABLE_NEEDS_BLOOD;
    public static boolean WITCHING_TABLE_NEEDS_SOULS;
    public static boolean ALL_SIGNALS_UNLOCKED;
    public static int TERRA_WARP_HEIGHT_THRESHOLD;
    public static int LUNAR_WARP_HEIGHT_THRESHOLD;
    public static int TERRA_WARP_DESTINATION_HEIGHT;
    public static int LUNAR_WARP_DESTINATION_HEIGHT;
    public static int TERRA_FALL_DISTANCE_RESISTANCE;
    public static int LUNAR_SEED;
    public static int PREHISTORIC_SEED;
    public static boolean FORMER_TLOTD_REWARDS; //its only there for the item description atm

    public static void registerConfigs() {
        configs = new ModConfigProvider();
        createConfigs();

        CONFIG = SimpleConfig.of(TLOTD.MOD_ID + "_config").provider(configs).request();

        assignConfigs();
    }

    private static void createConfigs() {
        configs.addKeyValuePair(new Pair<>("configVersion", "0.4.0"), "String [0.4.0] | shows version the config was made in");
        configs.addKeyValuePair(new Pair<>("mithrilAnvilNeedsDirectMoonlight", true), "boolean [true]");
        configs.addKeyValuePair(new Pair<>("witchingTableNeedsBlood", true), "boolean [true]");
        configs.addKeyValuePair(new Pair<>("witchingTableNeedsSouls", true), "boolean [true]");
        configs.addKeyValuePair(new Pair<>("allSignalsUnlocked", false), "boolean [false] | enables all signals on the radio or televison out of the box");
        configs.addKeyValuePair(new Pair<>("terraWarpHeightThreshold", 1000), "int [1000] | y-level to initiate warp in the Overworld");
        configs.addKeyValuePair(new Pair<>("lunarWarpHeightThreshold", 1000), "int [1000] | y-level to initiate warp on the Moon");
        configs.addKeyValuePair(new Pair<>("terraWarpDestinationHeight", 300), "int [300] | y-level in the Overworld after warp");
        configs.addKeyValuePair(new Pair<>("lunarWarpDestinationHeight", 100), "int [100] | y-level on the Moon after warp");
        configs.addKeyValuePair(new Pair<>("terraFallDistanceResistance", 400), "int [400] | resistence effect time in ticks (0 to disable)");
        configs.addKeyValuePair(new Pair<>("lunarSeed", 21071969), "int [21071969] | chunk generator seed for the moon");
        configs.addKeyValuePair(new Pair<>("prehistoricSeed", 18011871), "int [18011871] | chunk generator seed for the prehistoric");
        configs.addKeyValuePair(new Pair<>("formerTlotdRewards", false), "boolean [false] | effects the skins of the player plushie when placed by them");
    }

    private static void assignConfigs() {
        CONFIG_VERSION = CONFIG.getOrDefault("configVersion", "0.4.0");
        MITHRIL_ANVIL_NEEDS_DIRECT_MOONLIGHT = CONFIG.getOrDefault("mithrilAnvilNeedsDirectMoonlight", true);
        WITCHING_TABLE_NEEDS_BLOOD = CONFIG.getOrDefault("witchingTableNeedsBlood", true);
        WITCHING_TABLE_NEEDS_SOULS = CONFIG.getOrDefault("witchingTableNeedsSouls", true);
        ALL_SIGNALS_UNLOCKED = CONFIG.getOrDefault("allSignalsUnlocked", false);
        TERRA_WARP_HEIGHT_THRESHOLD = CONFIG.getOrDefault("terraWarpHeightThreshold", 1000);
        LUNAR_WARP_HEIGHT_THRESHOLD = CONFIG.getOrDefault("lunarWarpHeightThreshold", 1000);
        TERRA_WARP_DESTINATION_HEIGHT = CONFIG.getOrDefault("terraWarpDestinationHeight", 300);
        LUNAR_WARP_DESTINATION_HEIGHT = CONFIG.getOrDefault("lunarWarpDestinationHeight", 100);
        TERRA_FALL_DISTANCE_RESISTANCE = CONFIG.getOrDefault("terraFallDistanceResistance", 400);
        LUNAR_SEED = CONFIG.getOrDefault("lunarSeed", 21071969);
        PREHISTORIC_SEED = CONFIG.getOrDefault("prehistoricSeed", 18011871);
        FORMER_TLOTD_REWARDS = CONFIG.getOrDefault("formerTlotdRewards", false);

        System.out.println("All " + configs.getConfigsList().size() + " " + TLOTD.MOD_ID + " configs have been set properly");
    }
}
