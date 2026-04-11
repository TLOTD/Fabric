package net.tlotd.config;

import com.mojang.datafixers.util.Pair;
import net.tlotd.TLOTD;

public class ModConfigs {
    public static SimpleConfig CONFIG;
    private static ModConfigProvider configs;

    public static String CONFIG_VERSION;
    public static int LUNAR_SEED;
    public static int PREHISTORIC_SEED;

    public static void registerConfigs() {
        configs = new ModConfigProvider();
        createConfigs();

        CONFIG = SimpleConfig.of(TLOTD.MOD_ID + "_config").provider(configs).request();

        assignConfigs();
    }

    private static void createConfigs() {
        configs.addKeyValuePair(new Pair<>("configVersion", "0.4.4"), "String [0.4.4] | shows version the config was made in");
        configs.addKeyValuePair(new Pair<>("lunarSeed", 21071969), "int [21071969] | chunk generator seed for the moon");
        configs.addKeyValuePair(new Pair<>("prehistoricSeed", 18011871), "int [18011871] | chunk generator seed for the prehistoric");
    }

    private static void assignConfigs() {
        CONFIG_VERSION = CONFIG.getOrDefault("configVersion", "0.4.1");
        LUNAR_SEED = CONFIG.getOrDefault("lunarSeed", 21071969);
        PREHISTORIC_SEED = CONFIG.getOrDefault("prehistoricSeed", 18011871);

        System.out.println("All " + configs.getConfigsList().size() + " " + TLOTD.MOD_ID + " configs have been set properly");
    }
}
