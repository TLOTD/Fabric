package net.tlotd.banner;

import net.minecraft.block.entity.BannerPattern;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;
import net.tlotd.TLOTD;

public class ModBanners {

    public static final RegistryKey<BannerPattern> EUROPA = ofPattern("europa");
    public static final RegistryKey<BannerPattern> TLOTD_BANNER = ofPattern("tlotd");
    public static final RegistryKey<BannerPattern> BLIND_EYE = ofPattern("blind_eye");

    public static final RegistryKey<BannerPattern> DRAGON_LEFT = ofPattern("dragon_left");
    public static final RegistryKey<BannerPattern> DRAGON_RIGHT = ofPattern("dragon_right");
    public static final RegistryKey<BannerPattern> DRAGON_CROSS_LEFT = ofPattern("dragon_cross_left");
    public static final RegistryKey<BannerPattern> DRAGON_CROSS_RIGHT = ofPattern("dragon_cross_right");
    public static final RegistryKey<BannerPattern> FANCY_DRAGON_CROSS_LEFT = ofPattern("fancy_dragon_cross_left");
    public static final RegistryKey<BannerPattern> FANCY_DRAGON_CROSS_RIGHT = ofPattern("fancy_dragon_cross_right");

    public static final RegistryKey<BannerPattern> GONDOR = ofPattern("gondor");
    public static final RegistryKey<BannerPattern> ROHAN = ofPattern("rohan");
    public static final RegistryKey<BannerPattern> ELVEN = ofPattern("elven");
    public static final RegistryKey<BannerPattern> MORDOR = ofPattern("mordor");
    public static final RegistryKey<BannerPattern> ISENGARD = ofPattern("isengard");
    public static final RegistryKey<BannerPattern> ANGMAR = ofPattern("angmar");

    public static final TagKey<BannerPattern> DRAGON_PATTERN_ITEM = of("pattern_item/dragon");

    public static final TagKey<BannerPattern> LOTR_PATTERN_ITEM = of("pattern_item/lotr");

    private static RegistryKey<BannerPattern> ofPattern(String name) {
        return RegistryKey.of(RegistryKeys.BANNER_PATTERN, Identifier.of(TLOTD.MOD_ID, name));
    }

    private static TagKey<BannerPattern> of(String name) {
        return TagKey.of(RegistryKeys.BANNER_PATTERN, Identifier.of(TLOTD.MOD_ID, name));
    }

    public static void registerBanners() {
        TLOTD.LOGGER.debug("Registering banner pattern keys for " + TLOTD.MOD_ID);
    }
}