package net.tlotd.config;

import com.mojang.datafixers.util.Pair;
import net.tlotd.TLOTD;

public class ModConfigs {
    public static SimpleConfig CONFIG;
    private static ModConfigProvider configs;

    public static boolean AXE_STRIPPING_DROPS_BARK;
    public static int ELEVATOR_MAX_DISTANCE;
    public static boolean MITHRIL_ANVIL_NEEDS_DIRECT_MOONLIGHT;
    public static boolean WITCHING_TABLE_NEEDS_BLOOD;
    public static boolean WITCHING_TABLE_NEEDS_SOULS;
    public static boolean FORMER_TLOTD_REWARDS;

    public static void registerConfigs() {
        configs = new ModConfigProvider();
        createConfigs();

        CONFIG = SimpleConfig.of(TLOTD.MOD_ID + "_config").provider(configs).request();

        assignConfigs();
    }

    private static void createConfigs() {
        configs.addKeyValuePair(new Pair<>("axeStrippingDropsBark", true), "boolean");
        configs.addKeyValuePair(new Pair<>("elevatorMaxDistance", 100), "int");
        configs.addKeyValuePair(new Pair<>("mithrilAnvilNeedsDirectMoonlight", true), "boolean");
        configs.addKeyValuePair(new Pair<>("witchingTableNeedsBlood", true), "boolean");
        configs.addKeyValuePair(new Pair<>("witchingTableNeedsSouls", true), "boolean");
        configs.addKeyValuePair(new Pair<>("formerTlotdRewards", false), "boolean");
    }

    private static void assignConfigs() {
        AXE_STRIPPING_DROPS_BARK = CONFIG.getOrDefault("axeStrippingDropsBark", true);
        ELEVATOR_MAX_DISTANCE = CONFIG.getOrDefault("elevatorMaxDistance", 100);
        MITHRIL_ANVIL_NEEDS_DIRECT_MOONLIGHT = CONFIG.getOrDefault("mithrilAnvilNeedsDirectMoonlight", true);
        WITCHING_TABLE_NEEDS_BLOOD = CONFIG.getOrDefault("witchingTableNeedsBlood", true);
        WITCHING_TABLE_NEEDS_SOULS = CONFIG.getOrDefault("witchingTableNeedsSouls", true);
        FORMER_TLOTD_REWARDS = CONFIG.getOrDefault("formerTlotdRewards", false);

        System.out.println("All " + configs.getConfigsList().size() + " " + TLOTD.MOD_ID + " configs have been set properly");
    }
}
