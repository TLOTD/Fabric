package net.tlotd.util;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;
import net.tlotd.TLOTD;

public class ModTags {
    public static class Blocks {
        public static final TagKey<Block> EXTRACTABLE_BLOCKS =
                createTag("extractable_blocks");
        public static final TagKey<Block> STONE_EXTRACTABLE_BLOCKS =
                createTag("stone_extractable_blocks");
        public static final TagKey<Block> ANDESITE_EXTRACTABLE_BLOCKS =
                createTag("andesite_extractable_blocks");
        public static final TagKey<Block> DIORITE_EXTRACTABLE_BLOCKS =
                createTag("diorite_extractable_blocks");
        public static final TagKey<Block> GRANITE_EXTRACTABLE_BLOCKS =
                createTag("granite_extractable_blocks");
        public static final TagKey<Block> TUFF_EXTRACTABLE_BLOCKS =
                createTag("tuff_extractable_blocks");
        public static final TagKey<Block> CALCITE_EXTRACTABLE_BLOCKS =
                createTag("calcite_extractable_blocks");
        public static final TagKey<Block> DEEPSLATE_EXTRACTABLE_BLOCKS =
                createTag("deepslate_extractable_blocks");
        public static final TagKey<Block> RED_DEEPSLATE_EXTRACTABLE_BLOCKS =
                createTag("red_deepslate_extractable_blocks");
        public static final TagKey<Block> BEDROCK_EXTRACTABLE_BLOCKS =
                createTag("bedrock_extractable_blocks");
        public static final TagKey<Block> END_STONE_EXTRACTABLE_BLOCKS =
                createTag("end_stone_extractable_blocks");
        public static final TagKey<Block> MOON_ROCK_EXTRACTABLE_BLOCKS =
                createTag("moon_rock_extractable_blocks");
        public static final TagKey<Block> NETHERRACK_EXTRACTABLE_BLOCKS =
                createTag("netherrack_extractable_blocks");
        public static final TagKey<Block> BASALT_EXTRACTABLE_BLOCKS =
                createTag("basalt_extractable_blocks");
        public static final TagKey<Block> BLACKSTONE_EXTRACTABLE_BLOCKS =
                createTag("blackstone_extractable_blocks");

        public static final TagKey<Block> WITCHING_TABLE_BASE_BLOCKS =
                createTag("witching_table_base_blocks");

        public static final TagKey<Block> ELEVATOR_CONTROLLERS =
                createTag("elevator_controllers");
        public static final TagKey<Block> ELEVATOR_BASES =
                createTag("elevator_bases");

        public static final TagKey<Block> GINKGO_LOGS =
                createTag("ginkgo_logs");

        public static final TagKey<Block> WOODEN_STEINS =
                createTag("wooden_steins");

        public static final TagKey<Block> TELEVISIONS =
                createTag("televisions");

        public static final TagKey<Block> LOGS_WITH_BARK =
                createTag("logs_with_bark");

        public static final TagKey<Block> OXYGEN_PROVIDERS =
                createTag("oxygen_providers");

        private static TagKey<Block> createTag(String name) {
            return TagKey.of(RegistryKeys.BLOCK, new Identifier(TLOTD.MOD_ID, name));
        }
    }

    public static class Items {
        public static final TagKey<Item> WOODEN_BARK =
                createTag("wooden_bark");

        public static final TagKey<Item> GINKGO_LOGS =
                createTag("ginkgo_logs");

        public static final TagKey<Item> WOODEN_LIQUID_STEINS =
                createTag("wooden_liquid_steins");

        public static final TagKey<Item> EXTRACTION_PICKAXES =
                createTag("extraction_pickaxes");

        public static final TagKey<Item> EXTRACTION_II_PICKAXES =
                createTag("extraction_ii_pickaxes");

        public static final TagKey<Item> EXTRACTION_III_PICKAXES =
                createTag("extraction_iii_pickaxes");

        public static final TagKey<Item> MOUTH_OF_THE_ABYSS =
                createTag("mouth_of_the_abyss");

        public static final TagKey<Item> STICK_EFFIGIES =
                createTag("stick_effigies");

        public static final TagKey<Item> RAW_MEAT =
                createTag("raw_meat");

        public static final TagKey<Item> BEER_CONTAINER =
                createTag("beer_container");

        public static final TagKey<Item> HOT_MILK_CONTAINER =
                createTag("hot_milk_container");

        public static final TagKey<Item> RADIATION_PROTECTION =
                createTag("radiation_protection");

        public static final TagKey<Item> RADIATION_PROTECTION_WITHOUT_HELMET =
                createTag("radiation_protection_without_helmet");

        public static final TagKey<Item> HYPOXIA_PROTECTION =
                createTag("hypoxia_protection");

        public static final TagKey<Item> SICKLES =
                createTag("sickles");

        public static final TagKey<Item> VHS_CASSETTES =
                createTag("vhs_cassettes");

        public static final TagKey<Item> MUSIC_DISCS =
                createTag("music_discs");

        public static final TagKey<Item> GAME_CARTRIDGES =
                createTag("game_cartridges");

        private static TagKey<Item> createTag(String name) {
            return TagKey.of(RegistryKeys.ITEM, new Identifier(TLOTD.MOD_ID, name));
        }
    }
}
