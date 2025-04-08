package net.tlotd.banner;

import net.minecraft.block.entity.BannerPattern;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;
import net.tlotd.TLOTD;

public class ModBanners {

    public static final BannerPattern EUROPA = registerBanner("europa", new BannerPattern("europa"));
    public static final BannerPattern TLOTD_BANNER = registerBanner("tlotd", new BannerPattern("tlotd"));
    public static final BannerPattern BLIND_EYE = registerBanner("blind_eye", new BannerPattern("blind_eye"));

    public static final BannerPattern DRAGON_LEFT = registerBanner("dragon_left", new BannerPattern("dragon_left"));
    public static final BannerPattern DRAGON_RIGHT = registerBanner("dragon_right", new BannerPattern("dragon_right"));
    public static final BannerPattern DRAGON_CROSS_LEFT = registerBanner("dragon_cross_left", new BannerPattern("dragon_cross_left"));
    public static final BannerPattern DRAGON_CROSS_RIGHT = registerBanner("dragon_cross_right", new BannerPattern("dragon_cross_right"));
    public static final BannerPattern FANCY_DRAGON_CROSS_LEFT = registerBanner("fancy_dragon_cross_left", new BannerPattern("fancy_dragon_cross_left"));
    public static final BannerPattern FANCY_DRAGON_CROSS_RIGHT = registerBanner("fancy_dragon_cross_right", new BannerPattern("fancy_dragon_cross_right"));

    public static final BannerPattern GONDOR = registerBanner("gondor", new BannerPattern("gondor"));
    public static final BannerPattern ROHAN = registerBanner("rohan", new BannerPattern("rohan"));
    public static final BannerPattern MORDOR = registerBanner("mordor", new BannerPattern("mordor"));
    public static final BannerPattern ISENGARD = registerBanner("isengard", new BannerPattern("isengard"));
    public static final BannerPattern ANGMAR = registerBanner("angmar", new BannerPattern("angmar"));

    private static BannerPattern registerBanner(String name, BannerPattern bannerPattern) {
        return Registry.register(Registries.BANNER_PATTERN, new Identifier(TLOTD.MOD_ID, name), bannerPattern);
    }

    public static final TagKey<BannerPattern> DRAGON_PATTERN_ITEM = ModBanners.of("pattern_item/dragon");
    public static final TagKey<BannerPattern> LOTR_PATTERN_ITEM = ModBanners.of("pattern_item/lotr");

    private static TagKey<BannerPattern> of(String name) {
        return TagKey.of(RegistryKeys.BANNER_PATTERN, new Identifier(TLOTD.MOD_ID, name));
    }

    public static void registerBanners() {
        TLOTD.LOGGER.debug("Registering Banners for " + TLOTD.MOD_ID);
    }
}