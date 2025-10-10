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

        public static final TagKey<Block> TELEVISIONS_ON =
                createTag("televisions_on");

        public static final TagKey<Block> TELEVISIONS_OFF =
                createTag("televisions_off");

        public static final TagKey<Block> INTERDIMENSIONAL_RECEIVERS =
                createTag("interdimensional_receivers");

        public static final TagKey<Block> COMPUTER_ACCESSORIES =
                createTag("computer_accessories");

        public static final TagKey<Block> LOGS_WITH_BARK =
                createTag("logs_with_bark");

        public static final TagKey<Block> OXYGEN_PROVIDERS =
                createTag("oxygen_providers");

        public static final TagKey<Block> INFINIBURN_PREHISTORIC =
                createTag("infiniburn_prehistoric");

        public static final TagKey<Block> INFINIBURN_LUNA =
                createTag("infiniburn_luna");

        public static final TagKey<Block> TOOL_LEVEL_4 =
                createTag("fabric", "needs_tool_level_4");
        public static final TagKey<Block> TOOL_LEVEL_5 =
                createTag("fabric", "needs_tool_level_5");
        public static final TagKey<Block> TOOL_LEVEL_6 =
                createTag("fabric", "needs_tool_level_6");
        public static final TagKey<Block> TOOL_LEVEL_7 =
                createTag("fabric", "needs_tool_level_7");
        public static final TagKey<Block> TOOL_LEVEL_8 =
                createTag("fabric", "needs_tool_level_8");
        public static final TagKey<Block> TOOL_LEVEL_9 =
                createTag("fabric", "needs_tool_level_9");
        public static final TagKey<Block> TOOL_LEVEL_10 =
                createTag("fabric", "needs_tool_level_10");

        public static final TagKey<Block> PAXEL_MINEABLE =
                createTag("c", "paxel_mineable");

        private static TagKey<Block> createTag(String name) {
            return TagKey.of(RegistryKeys.BLOCK, new Identifier(TLOTD.MOD_ID, name));
        }

        private static TagKey<Block> createTag(String identifirer, String name) {
            return TagKey.of(RegistryKeys.BLOCK, new Identifier(identifirer, name));
        }
    }

    public static class Items {

        public static final TagKey<Item> DIVINE_ITEMS =
                createTag("divine_items");

        public static final TagKey<Item> OCCULT_ITEMS =
                createTag("occult_items");

        public static final TagKey<Item> ELDRITCH_ITEMS =
                createTag("eldritch_items");

        public static final TagKey<Item> BLOOD_BOTTLES =
                createTag("blood_bottles");

        public static final TagKey<Item> BLOOD_BUCKETS =
                createTag("blood_buckets");

        public static final TagKey<Item> BULLET_PROOF_ARMOR =
                createTag("bullet_proof_armor");

        public static final TagKey<Item> BULLET_PROOF_ARMOR_II =
                createTag("bullet_proof_armor_ii");

        public static final TagKey<Item> BULLET_PROOF_ARMOR_III =
                createTag("bullet_proof_armor_iii");

        public static final TagKey<Item> EGGS =
                createTag("eggs");

        public static final TagKey<Item> HATCHABLE_EGGS =
                createTag("hatchable_eggs");

        public static final TagKey<Item> FORGING_HAMMERS =
                createTag("forging_hammers");

        public static final TagKey<Item> FORGING_HAMMERS_TIER1 =
                createTag("forging_hammers_tier1");

        public static final TagKey<Item> FORGING_HAMMERS_TIER2 =
                createTag("forging_hammers_tier2");

        public static final TagKey<Item> FORGING_HAMMERS_TIER3 =
                createTag("forging_hammers_tier3");

        public static final TagKey<Item> FORGING_HAMMERS_TIER4 =
                createTag("forging_hammers_tier4");

        public static final TagKey<Item> FORGING_HAMMERS_TIER5 =
                createTag("forging_hammers_tier5");

        public static final TagKey<Item> FORGING_HAMMERS_TIER6 =
                createTag("forging_hammers_tier6");

        public static final TagKey<Item> FORGING_HAMMERS_TIER7 =
                createTag("forging_hammers_tier7");

        public static final TagKey<Item> FORGING_HAMMERS_TIER8 =
                createTag("forging_hammers_tier8");

        public static final TagKey<Item> FORGING_HAMMERS_TIER9 =
                createTag("forging_hammers_tier9");

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

        public static final TagKey<Item> MEAD_CONTAINER =
                createTag("mead_container");

        public static final TagKey<Item> HOT_MILK_CONTAINER =
                createTag("hot_milk_container");

        public static final TagKey<Item> HEV_CHARGER_CHARGABLE =
                createTag("hev_charger_chargable");

        public static final TagKey<Item> RADIATION_PROTECTION =
                createTag("radiation_protection");

        public static final TagKey<Item> RADIATION_PROTECTION_WITHOUT_HELMET =
                createTag("radiation_protection_without_helmet");

        public static final TagKey<Item> OXYGEN_CHARGABLE =
                createTag("oxygen_chargable");

        public static final TagKey<Item> HYPOXIA_PROTECTION =
                createTag("hypoxia_protection");

        public static final TagKey<Item> IRON_OR_STEEL =
                createTag("iron_or_steel");

        public static final TagKey<Item> CINNABAR_OR_NEBULAR =
                createTag("cinnabar_or_nebular");

        public static final TagKey<Item> SICKLES =
                createTag("sickles");

        public static final TagKey<Item> VHS_CASSETTES =
                createTag("vhs_cassettes");

        public static final TagKey<Item> MUSIC_DISCS =
                createTag("music_discs");

        public static final TagKey<Item> TRANSMITTABLE_SIGNALS =
                createTag("transmittable_signals");

        public static final TagKey<Item> CIRCUIT_BOARDS =
                createTag("circuit_boards");

        public static final TagKey<Item> ADVANCED_CIRCUIT_BOARDS =
                createTag("advanced_circuit_boards");

        public static final TagKey<Item> TRANSCENDENT_CIRCUIT_BOARDS =
                createTag("transcendent_circuit_boards");

        public static final TagKey<Item> GAME_CARTRIDGES =
                createTag("game_cartridges");

        public static final TagKey<Item> KEYCARDS =
                createTag("keycards");

        public static final TagKey<Item> TREX_HEADS =
                createTag("t-rex_heads");

        public static final TagKey<Item> PAXELS =
                createTag("c", "paxels");

        private static TagKey<Item> createTag(String name) {
            return TagKey.of(RegistryKeys.ITEM, new Identifier(TLOTD.MOD_ID, name));
        }

        private static TagKey<Item> createTag(String identifier, String name) {
            return TagKey.of(RegistryKeys.ITEM, new Identifier(identifier, name));
        }
    }
}
