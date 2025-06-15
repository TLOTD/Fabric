package net.tlotd.item;

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.tlotd.TLOTD;
import net.tlotd.block.ModBlocks;
import net.tlotd.fluid.ModFluids;

public class ModItemGroups {
    public static final ItemGroup TLOTD_1_MATERIALS_GROUP = Registry.register(Registries.ITEM_GROUP,
            new Identifier(TLOTD.MOD_ID, "1_materials"),
            FabricItemGroup.builder().displayName(Text.translatable("itemgroup.tlotd.materials"))
                    .icon(() -> new ItemStack(ModItems.GUIDEBOOK)).entries((displayContext, entries) -> {
                        entries.add(ModItems.GUIDEBOOK);

                        entries.add(ModItems.SPELL_BOOK);
                        entries.add(ModItems.FORBIDDEN_SPELL_BOOK);
                        entries.add(ModItems.PLANCHETTE);

                        entries.add(ModItems.DRAGON_BANNER_PATTERN);
                        entries.add(ModItems.LOTR_BANNER_PATTERN);

                        entries.add(ModItems.OXYGEN_TANK);

                        entries.add(ModFluids.BEER_BUCKET);
                        entries.add(ModFluids.OIL_BUCKET);
                        entries.add(ModFluids.HOT_MILK_BUCKET);
                        entries.add(ModFluids.HOT_CHOCOLATE_BUCKET);
                        entries.add(ModFluids.BLOOD_BUCKET);
                        entries.add(ModFluids.CHEMICAL_WASTE_BUCKET);

                        entries.add(ModItems.COPPER_WIRE);
                        entries.add(ModItems.GOLD_WIRE);
                        entries.add(ModItems.SUPERCONDUCTING_WIRE);
                        entries.add(ModItems.CIRCUIT_BOARD);
                        entries.add(ModItems.ADVANCED_CIRCUIT_BOARD);
                        entries.add(ModItems.FUTURISTIC_CIRCUIT_BOARD);
                        entries.add(ModItems.INTEGRATED_CIRCUIT);
                        entries.add(ModItems.CATHODE_RAY_TUBE);
                        entries.add(ModItems.LIQUID_CRYSTAL_DISPLAY_PANEL);
                        entries.add(ModItems.HOLOGRAPHIC_PROJECTOR);

                        entries.add(ModItems.STEEL_INGREDIENTS);
                        entries.add(ModItems.STEEL_NUGGET);
                        entries.add(ModItems.STEEL_INGOT);
                        entries.add(ModItems.STEEL_ROD);
                        entries.add(ModItems.REINFORCED_TOOL_ROD);
                        entries.add(ModItems.FANCY_TOOL_ROD);

                        entries.add(ModItems.FOSSILIZED_BONE);

                        entries.add(ModItems.SULFUR);

                        entries.add(ModItems.RAW_LEAD);
                        entries.add(ModItems.LEAD_NUGGET);
                        entries.add(ModItems.LEAD_INGOT);
                        entries.add(ModItems.URANIUM);
                        entries.add(ModItems.URANIUM_NUGGET);
                        entries.add(ModItems.URANIUM_INGOT);

                        entries.add(ModItems.HELIORITE_COMB);
                        entries.add(ModItems.HELIORITE_NUGGET);
                        entries.add(ModItems.HELIORITE_INGOT);

                        entries.add(ModItems.HELIORITE_PICKAXE_HEAD);
                        entries.add(ModItems.HELIORITE_UPGRADE_SMITHING_TEMPLATE);

                        entries.add(ModItems.ENDURIUM_CRYSTAL);
                        entries.add(ModItems.ENDURIUM_NUGGET);
                        entries.add(ModItems.ENDURIUM_INGOT);

                        entries.add(ModItems.ENDURIUM_PICKAXE_HEAD);
                        entries.add(ModItems.ENDURIUM_UPGRADE_SMITHING_TEMPLATE);

                        entries.add(ModItems.RAW_PALLADIUM);
                        entries.add(ModItems.PALLADIUM_NUGGET);
                        entries.add(ModItems.PALLADIUM_INGOT);

                        entries.add(ModItems.JURASSOLINE_CRYSTAL);
                        entries.add(ModItems.JURASSOLINE_NUGGET);
                        entries.add(ModItems.JURASSOLINE_INGOT);

                        entries.add(ModItems.CINNABAR_CRYSTAL);
                        entries.add(ModItems.CINNABAR_NUGGET);
                        entries.add(ModItems.CINNABAR_INGOT);

                        entries.add(ModItems.NEBULAR_CRYSTAL);
                        entries.add(ModItems.NEBULAR_NUGGET);
                        entries.add(ModItems.NEBULAR_INGOT);

                        entries.add(ModItems.RAW_MITHRIL);
                        entries.add(ModItems.MITHRIL_NUGGET);
                        entries.add(ModItems.MITHRIL_INGOT);

                        entries.add(ModItems.ASTRAL_NUGGET);
                        entries.add(ModItems.ASTRAL_INGOT);

                        entries.add(ModItems.XEN_CRYSTAL);
                        entries.add(ModItems.METEORITE_CHUNK);
                        entries.add(ModItems.STAR_FRAGMENT);
                        entries.add(ModItems.LUNAR_CALLAINUS_LUMP);

                        entries.add(ModItems.SOUL_MIRROR);

                        entries.add(ModItems.DAYBREAK_DOMAIN_FRAGMENTS);
                        entries.add(ModItems.SOMBER_BLOOD_ORBS);
                        entries.add(ModItems.OTHERWORLDLY_WHISPERS);

                        entries.add(ModItems.BLOOD_BOTTLE);

                        entries.add(ModItems.STICK_CROSS);
                        entries.add(ModItems.STICK_EFFIGY);
                        entries.add(ModItems.STICK_FIGURE);

                        entries.add(ModItems.TINTED_GLASS_FLASK);
                        entries.add(ModItems.SOUL_FLASK);
                        entries.add(ModItems.CURSED_SOUL_FLASK);
                        entries.add(ModItems.SOUL_FLASK_OF_THE_ABYSS);

                        entries.add(ModItems.EDELWEISS_PETALS);
                        entries.add(ModItems.MISTLETOE);
                        entries.add(ModItems.CURED_MEAT);
                        entries.add(ModItems.COOKED_MEAT);

                        entries.add(ModItems.APPLE_JUICE_BOTTLE);

                        entries.add(ModItems.STRAWBERRY_SEEDS);
                        entries.add(ModItems.STRAWBERRY);
                        entries.add(ModItems.CHOCOLATE_STRAWBERRY);
                        entries.add(ModBlocks.STRAWBERRY_CAKE);

                        entries.add(ModItems.ORANGE_SEEDS);
                        entries.add(ModItems.ORANGE);
                        entries.add(ModItems.ORANGE_JUICE_BOTTLE);
                        entries.add(ModBlocks.ORANGE_CAKE);

                        entries.add(ModBlocks.PRESERVES_JAR);
                        entries.add(ModBlocks.SWEET_BERRY_JAM_JAR);
                        entries.add(ModBlocks.GLOW_BERRY_JAM_JAR);
                        entries.add(ModBlocks.STRAWBERRY_JAM_JAR);
                        entries.add(ModBlocks.ORANGE_MARMELADE_JAR);
                        entries.add(ModBlocks.BLUE_BERRY_JAM_JAR);
                        entries.add(ModBlocks.ANCIENT_SOULBERRY_JAM_JAR);
                        entries.add(ModItems.TOAST);
                        entries.add(ModItems.SWEET_BERRY_JAM_TOAST);
                        entries.add(ModItems.GLOW_BERRY_JAM_TOAST);
                        entries.add(ModItems.STRAWBERRY_JAM_TOAST);
                        entries.add(ModItems.ORANGE_MARMELADE_TOAST);
                        entries.add(ModItems.BLUE_BERRY_JAM_TOAST);
                        entries.add(ModItems.ANCIENT_SOULBERRY_JAM_TOAST);

                        entries.add(ModItems.SPEZI_BOTTLE);

                        entries.add(ModItems.BEER_BOTTLE);
                        entries.add(ModItems.BEER_GOAT_HORN);

                        entries.add(ModItems.DINOSAUR_HIDE);
                        entries.add(ModItems.DINOSAUR_MEAT);
                        entries.add(ModItems.COOKED_DINOSAUR_MEAT);

                        entries.add(ModItems.FLOUR);
                        entries.add(ModItems.BREADCRUMBS);
                        entries.add(ModItems.RAW_SCHNITZEL);
                        entries.add(ModItems.SCHNITZEL);

                        entries.add(ModItems.SQUID);
                        entries.add(ModItems.CALAMARI);
                        entries.add(ModItems.FRIED_CALAMARI);

                        entries.add(ModItems.MAULTASCHE);
                        entries.add(ModItems.MAULTASCHEN_BROTH);

                        entries.add(ModItems.PORRIDGE);

                        entries.add(ModItems.MUSIC_DISC_1);
                        entries.add(ModItems.MUSIC_DISC_2);
                        entries.add(ModItems.MUSIC_DISC_3);
                        entries.add(ModItems.MUSIC_DISC_4);

                        entries.add(ModItems.VHS_CASSETTE);
                        entries.add(ModItems.VHS_CASSETTE_1);
                        entries.add(ModItems.VHS_CASSETTE_2);
                        entries.add(ModItems.VHS_CASSETTE_3);
                        entries.add(ModItems.VHS_CASSETTE_4);
                        entries.add(ModItems.VHS_CASSETTE_5);
                        entries.add(ModItems.VHS_CASSETTE_6);
                        entries.add(ModItems.VHS_CASSETTE_7);
                        entries.add(ModItems.VHS_CASSETTE_8);
                        entries.add(ModItems.VHS_CASSETTE_9);
                        entries.add(ModItems.VHS_CASSETTE_BROKEN);

                        entries.add(ModItems.GAME_CARTRIDGE);
                        entries.add(ModItems.GAME_CARTRIDGE_1);
                        entries.add(ModItems.GAME_CARTRIDGE_2);
                        entries.add(ModItems.GAME_CARTRIDGE_3);

                        entries.add(ModItems.OAK_BARK);
                        entries.add(ModItems.SPRUCE_BARK);
                        entries.add(ModItems.BIRCH_BARK);
                        entries.add(ModItems.JUNGLE_BARK);
                        entries.add(ModItems.ACACIA_BARK);
                        entries.add(ModItems.DARK_OAK_BARK);
                        entries.add(ModItems.MANGROVE_BARK);
                        entries.add(ModItems.CHERRY_BARK);
                        entries.add(ModItems.GINKGO_BARK);

                        entries.add(ModItems.SKYROOT_BARK);

                        entries.add(ModItems.PEWEN_BARK);
                        entries.add(ModItems.THORNWOOD_BARK);

                        entries.add(ModItems.FIR_BARK);
                        entries.add(ModItems.REDWOOD_BARK);
                        entries.add(ModItems.MAHOGANY_BARK);
                        entries.add(ModItems.JACARANCA_BARK);
                        entries.add(ModItems.PALM_BARK);
                        entries.add(ModItems.WILLOW_BARK);
                        entries.add(ModItems.DEAD_BARK);
                        entries.add(ModItems.MAGIC_BARK);
                        entries.add(ModItems.UMBRAN_BARK);
                        entries.add(ModItems.HELLBARK_BARK);

                        entries.add(ModItems.ASHEN_BARK);
                        entries.add(ModItems.AZALEA_BARK);
                        entries.add(ModItems.TRUMPET_BARK);

                        entries.add(ModItems.RUBBERWOOD_BARK);

                        entries.add(ModItems.TWILIGHT_OAK_BARK);
                        entries.add(ModItems.CANOPY_TREE_BARK);
                        entries.add(ModItems.TWILIGHT_MANGROVE_BARK);
                        entries.add(ModItems.DARKWOOD_BARK);
                        entries.add(ModItems.TIMEWOOD_BARK);
                        entries.add(ModItems.TRANSWOOD_BARK);
                        entries.add(ModItems.MINEWOOD_BARK);
                        entries.add(ModItems.SORTINGWOOD_BARK);
                    }).build());

    public static final ItemGroup TLOTD_2_WEAPONS_TOOLS_UTILITIES_GROUP = Registry.register(Registries.ITEM_GROUP,
            new Identifier(TLOTD.MOD_ID, "2_weapons_tools_and_utilities"),
            FabricItemGroup.builder().displayName(Text.translatable("itemgroup.tlotd.weapons_tools_and_utilities"))
                    .icon(() -> new ItemStack(ModItems.ENDURIUM_PICKAXE)).entries((displayContext, entries) -> {

                        entries.add(ModItems.MONOCLE);
                        entries.add(ModItems.VICTORIAN_SUIT);
                        entries.add(ModItems.VICTORIAN_PANTS);
                        entries.add(ModItems.VICTORIAN_BOOTS);

                        entries.add(ModItems.PICKELHAUBE);
                        entries.add(ModItems.IMPERIAL_GERMAN_UNIFORM_MANTLE);
                        entries.add(ModItems.IMPERIAL_GERMAN_UNIFORM_PANTS);
                        entries.add(ModItems.IMPERIAL_GERMAN_UNIFORM_BOOTS);

                        entries.add(ModItems.ASTRONAUT_HELMET);
                        entries.add(ModItems.SPACE_SUIT_CHESTPLATE);
                        entries.add(ModItems.SPACE_SUIT_LEGGINGS);
                        entries.add(ModItems.SPACE_SUIT_BOOTS);

                        entries.add(ModItems.CTHONAUT_HELMET);
                        entries.add(ModItems.DEPTH_SUIT_CHESTPLATE);
                        entries.add(ModItems.DEPTH_SUIT_LEGGINGS);
                        entries.add(ModItems.DEPTH_SUIT_BOOTS);

                        entries.add(ModItems.SCIENTIST_GLASSES);
                        entries.add(ModItems.HEV_SUIT_CHESTPLATE);
                        entries.add(ModItems.HEV_SUIT_LEGGINGS);
                        entries.add(ModItems.HEV_SUIT_BOOTS);

                        entries.add(ModItems.COPPER_SICKLE);
                        entries.add(ModItems.GOLDEN_SICKLE);
                        entries.add(ModItems.STEEL_SICKLE);
                        entries.add(ModItems.NETHERITE_SICKLE);
                        entries.add(ModItems.NETHERITE_FORGING_HAMMER);

                        entries.add(ModItems.BAMBOO_SWORD);
                        entries.add(ModItems.BAMBOO_PICKAXE);
                        entries.add(ModItems.BAMBOO_AXE);
                        entries.add(ModItems.BAMBOO_SHOVEL);
                        entries.add(ModItems.BAMBOO_HOE);

                        entries.add(ModItems.HELIORITE_SWORD);
                        entries.add(ModItems.HELIORITE_PICKAXE);
                        entries.add(ModItems.HELIORITE_AXE);
                        entries.add(ModItems.HELIORITE_SHOVEL);
                        entries.add(ModItems.HELIORITE_HOE);

                        entries.add(ModItems.HELIORITE_SICKLE);

                        entries.add(ModItems.HELIORITE_HELMET);
                        entries.add(ModItems.HELIORITE_CHESTPLATE);
                        entries.add(ModItems.HELIORITE_LEGGINGS);
                        entries.add(ModItems.HELIORITE_BOOTS);

                        entries.add(ModItems.ENDURIUM_SWORD);
                        entries.add(ModItems.ENDURIUM_PICKAXE);
                        entries.add(ModItems.ENDURIUM_AXE);
                        entries.add(ModItems.ENDURIUM_SHOVEL);
                        entries.add(ModItems.ENDURIUM_HOE);

                        entries.add(ModItems.ENDURIUM_SICKLE);

                        entries.add(ModItems.ENDURIUM_HELMET);
                        entries.add(ModItems.ENDURIUM_CHESTPLATE);
                        entries.add(ModItems.ENDURIUM_LEGGINGS);
                        entries.add(ModItems.ENDURIUM_BOOTS);

                        entries.add(ModItems.PALLADIUM_SWORD);
                        entries.add(ModItems.PALLADIUM_PICKAXE);
                        entries.add(ModItems.PALLADIUM_AXE);
                        entries.add(ModItems.PALLADIUM_SHOVEL);
                        entries.add(ModItems.PALLADIUM_HOE);

                        entries.add(ModItems.PALLADIUM_SICKLE);

                        entries.add(ModItems.PALLADIUM_HELMET);
                        entries.add(ModItems.PALLADIUM_CHESTPLATE);
                        entries.add(ModItems.PALLADIUM_LEGGINGS);
                        entries.add(ModItems.PALLADIUM_BOOTS);

                        entries.add(ModItems.JURASSOLINE_SWORD);
                        entries.add(ModItems.JURASSOLINE_PICKAXE);
                        entries.add(ModItems.JURASSOLINE_AXE);
                        entries.add(ModItems.JURASSOLINE_SHOVEL);
                        entries.add(ModItems.JURASSOLINE_HOE);

                        entries.add(ModItems.JURASSOLINE_SICKLE);

                        entries.add(ModItems.JURASSOLINE_HELMET);
                        entries.add(ModItems.JURASSOLINE_CHESTPLATE);
                        entries.add(ModItems.JURASSOLINE_LEGGINGS);
                        entries.add(ModItems.JURASSOLINE_BOOTS);

                        entries.add(ModItems.CINNABAR_SWORD);
                        entries.add(ModItems.CINNABAR_PICKAXE);
                        entries.add(ModItems.CINNABAR_AXE);
                        entries.add(ModItems.CINNABAR_SHOVEL);
                        entries.add(ModItems.CINNABAR_HOE);

                        entries.add(ModItems.CINNABAR_SICKLE);

                        entries.add(ModItems.CINNABAR_HELMET);
                        entries.add(ModItems.CINNABAR_CHESTPLATE);
                        entries.add(ModItems.CINNABAR_LEGGINGS);
                        entries.add(ModItems.CINNABAR_BOOTS);

                        entries.add(ModItems.NEBULAR_SWORD);
                        entries.add(ModItems.NEBULAR_PICKAXE);
                        entries.add(ModItems.NEBULAR_AXE);
                        entries.add(ModItems.NEBULAR_SHOVEL);
                        entries.add(ModItems.NEBULAR_HOE);

                        entries.add(ModItems.NEBULAR_SICKLE);

                        entries.add(ModItems.NEBULAR_HELMET);
                        entries.add(ModItems.NEBULAR_CHESTPLATE);
                        entries.add(ModItems.NEBULAR_LEGGINGS);
                        entries.add(ModItems.NEBULAR_BOOTS);

                        entries.add(ModItems.MITHRIL_SWORD);
                        entries.add(ModItems.MITHRIL_PICKAXE);
                        entries.add(ModItems.MITHRIL_AXE);
                        entries.add(ModItems.MITHRIL_SHOVEL);
                        entries.add(ModItems.MITHRIL_HOE);

                        entries.add(ModItems.MITHRIL_SICKLE);
                        entries.add(ModItems.MITHRIL_FORGING_HAMMER);
                        entries.add(ModItems.MITHRIL_STAR_CATCHER);
                        entries.add(ModItems.MITHRIL_MIRROR);

                        entries.add(ModItems.MITHRIL_HELMET);
                        entries.add(ModItems.MITHRIL_CHESTPLATE);
                        entries.add(ModItems.MITHRIL_LEGGINGS);
                        entries.add(ModItems.MITHRIL_BOOTS);

                        entries.add(ModItems.MITHRIL_HORSE_ARMOR);

                        entries.add(ModItems.ASTRAL_SWORD);
                        entries.add(ModItems.ASTRAL_PICKAXE);
                        entries.add(ModItems.ASTRAL_AXE);
                        entries.add(ModItems.ASTRAL_SHOVEL);
                        entries.add(ModItems.ASTRAL_HOE);

                        entries.add(ModItems.ASTRAL_SICKLE);
                        entries.add(ModItems.ASTRAL_FORGING_HAMMER);

                        entries.add(ModItems.DIVINE_PICKAXE);
                        entries.add(ModItems.CATACLYSMIC_PICKAXE);
                        entries.add(ModItems.ELDRITCH_PICKAXE);

                        entries.add(ModItems.NARSIL_HANDLE);
                        entries.add(ModItems.ANDURIL);

                        entries.add(ModItems.GINKGO_BOAT);
                        entries.add(ModItems.GINKGO_CHEST_BOAT);

                        entries.add(ModItems.EMPERORS_CROWN);
                        entries.add(ModBlocks.GLOBUS_CRUCIGER);

                        entries.add(ModItems.FOSSIL_AND_STEEL);
                        entries.add(ModItems.TREX_SPAWN_EGG);
                        entries.add(ModItems.INFECTED_TREX_SPAWN_EGG);
                    }).build());

    public static final ItemGroup TLOTD_3_BLOCKS_GROUP = Registry.register(Registries.ITEM_GROUP,
            new Identifier(TLOTD.MOD_ID, "3_blocks"),
            FabricItemGroup.builder().displayName(Text.translatable("itemgroup.tlotd.blocks"))
                    .icon(() -> new ItemStack(ModBlocks.RICH_GRASS_BLOCK)).entries((displayContext, entries) -> {

                        entries.add(ModBlocks.BW_STICKER);
                        entries.add(ModBlocks.TLOTD_STICKER);

                        entries.add(ModBlocks.GRAVESTONE);
                        entries.add(ModBlocks.MOSSY_GRAVESTONE);
                        entries.add(ModBlocks.GRAVESTONE_CROSS);
                        entries.add(ModBlocks.MOSSY_GRAVESTONE_CROSS);

                        entries.add(ModBlocks.SKELETON);
                        entries.add(ModBlocks.EMERGING_SKELETON);

                        entries.add(ModBlocks.EFFIGIES);
                        entries.add(ModBlocks.WITCHING_TABLE);
                        entries.add(ModBlocks.OUIJA_BOARD);
                        entries.add(ModBlocks.GOAT_HEAD);

                        entries.add(ModBlocks.APPARATUS);

                        entries.add(ModBlocks.GARBAGE_CAN);

                        entries.add(ModBlocks.SIGNAL_TRANSMITTER_ANTENNA);
                        entries.add(ModBlocks.SIGNAL_TRANSMITTER);

                        entries.add(ModBlocks.RADIO);
                        entries.add(ModBlocks.INTERDIMENSIONAL_RECEIVER);
                        entries.add(ModBlocks.TELEVISION);
                        entries.add(ModBlocks.VIDEOCASSETTE_RECORDER);
                        entries.add(ModBlocks.COMPUTER);
                        entries.add(ModBlocks.OXYGEN_COLLECTOR);

                        entries.add(ModBlocks.CREEPER_PLUSHIE);
                        entries.add(ModBlocks.ZOMBIE_PLUSHIE);
                        entries.add(ModBlocks.PLAYER_PLUSHIE);

                        entries.add(ModBlocks.TREX_EGG);
                        entries.add(ModBlocks.TREX_HEAD);
                        entries.add(ModBlocks.GREEN_TREX_HEAD);
                        entries.add(ModBlocks.GRAY_TREX_HEAD);
                        entries.add(ModBlocks.SCULK_TREX_HEAD);
                        entries.add(ModBlocks.INFECTED_TREX_HEAD);
                        entries.add(ModBlocks.SICKENED_TREX_HEAD);

                        entries.add(ModBlocks.WOODEN_STEIN);
                        entries.add(ModBlocks.WOODEN_WATER_STEIN);
                        entries.add(ModBlocks.WOODEN_APPLE_JUICE_STEIN);
                        entries.add(ModBlocks.WOODEN_ORANGE_JUICE_STEIN);
                        entries.add(ModBlocks.WOODEN_BEER_STEIN);
                        entries.add(ModBlocks.WOODEN_MILK_STEIN);
                        entries.add(ModBlocks.WOODEN_CHOCOLATE_MILKSHAKE_STEIN);
                        entries.add(ModBlocks.WOODEN_STRAWBERRY_MILKSHAKE_STEIN);
                        entries.add(ModBlocks.WOODEN_ORANGE_MILKSHAKE_STEIN);
                        entries.add(ModBlocks.HOT_WOODEN_MILK_STEIN);
                        entries.add(ModBlocks.WOODEN_HOT_CHOCOLATE_STEIN);

                        entries.add(ModBlocks.MINING_ELEVATOR_CONTROLLER);
                        entries.add(ModBlocks.MINING_ELEVATOR_BASE);
                        entries.add(ModBlocks.WOODEN_ELEVATOR_CONTROLLER);
                        entries.add(ModBlocks.WOODEN_ELEVATOR_BASE);
                        entries.add(ModBlocks.QUARTZ_ELEVATOR_CONTROLLER);
                        entries.add(ModBlocks.QUARTZ_ELEVATOR_BASE);
                        entries.add(ModBlocks.GLASS_ELEVATOR_CONTROLLER);
                        entries.add(ModBlocks.GLASS_ELEVATOR_BASE);

                        entries.add(ModBlocks.XEN_CRYSTAL_CLUSTER);
                        entries.add(ModBlocks.XEN_CRYSTAL_BLOCK);

                        entries.add(ModBlocks.WHITE_PUMPKIN);
                        entries.add(ModBlocks.CARVED_WHITE_PUMPKIN);
                        entries.add(ModBlocks.WHITE_JACK_O_LANTERN);

                        entries.add(ModBlocks.MOON_ROCK);
                        entries.add(ModBlocks.MOON_ROCK_COAL_ORE);
                        entries.add(ModBlocks.MOON_ROCK_IRON_ORE);
                        entries.add(ModBlocks.MOON_ROCK_LUNAR_CALLAINUS_ORE);

                        entries.add(ModBlocks.MARBLE);
                        entries.add(ModBlocks.MARBLE_STAIRS);
                        entries.add(ModBlocks.MARBLE_SLAB);
                        entries.add(ModBlocks.MARBLE_WALL);
                        entries.add(ModBlocks.MARBLE_PRESSURE_PLATE);
                        entries.add(ModBlocks.MARBLE_BUTTON);
                        entries.add(ModBlocks.LIMESTONE);
                        entries.add(ModBlocks.LIMESTONE_STAIRS);
                        entries.add(ModBlocks.LIMESTONE_SLAB);
                        entries.add(ModBlocks.LIMESTONE_WALL);
                        entries.add(ModBlocks.LIMESTONE_PRESSURE_PLATE);
                        entries.add(ModBlocks.LIMESTONE_BUTTON);

                        entries.add(ModBlocks.RICH_GRASS_BLOCK);
                        entries.add(ModBlocks.RICH_DIRT);
                        entries.add(ModBlocks.RICH_DIRT_PATH);
                        entries.add(ModBlocks.RICH_FARMLAND);

                        entries.add(ModBlocks.RED_GRAVEL);
                        entries.add(ModBlocks.RED_SANDY_DEEPSLATE);

                        entries.add(ModBlocks.RED_DEEPSLATE);
                        entries.add(ModBlocks.RED_DEEPSLATE_STAIRS);
                        entries.add(ModBlocks.RED_DEEPSLATE_SLAB);
                        entries.add(ModBlocks.RED_DEEPSLATE_WALL);
                        entries.add(ModBlocks.RED_DEEPSLATE_PRESSURE_PLATE);
                        entries.add(ModBlocks.RED_DEEPSLATE_BUTTON);
                        entries.add(ModBlocks.POLISHED_RED_DEEPSLATE);
                        entries.add(ModBlocks.RED_DEEPSLATE_BRICKS);
                        entries.add(ModBlocks.RED_DEEPSLATE_BRICK_STAIRS);
                        entries.add(ModBlocks.RED_DEEPSLATE_BRICK_SLAB);
                        entries.add(ModBlocks.RED_DEEPSLATE_BRICK_WALL);
                        entries.add(ModBlocks.MOSSY_RED_DEEPSLATE_BRICKS);
                        entries.add(ModBlocks.CRACKED_RED_DEEPSLATE_BRICKS);

                        entries.add(ModBlocks.COBBLED_RED_DEEPSLATE);
                        entries.add(ModBlocks.COBBLED_RED_DEEPSLATE_STAIRS);
                        entries.add(ModBlocks.COBBLED_RED_DEEPSLATE_SLAB);
                        entries.add(ModBlocks.COBBLED_RED_DEEPSLATE_WALL);
                        entries.add(ModBlocks.POLISHED_COBBLED_RED_DEEPSLATE);
                        entries.add(ModBlocks.COBBLED_RED_DEEPSLATE_BRICKS);
                        entries.add(ModBlocks.COBBLED_RED_DEEPSLATE_BRICK_STAIRS);
                        entries.add(ModBlocks.COBBLED_RED_DEEPSLATE_BRICK_SLAB);
                        entries.add(ModBlocks.COBBLED_RED_DEEPSLATE_BRICK_WALL);
                        entries.add(ModBlocks.MOSSY_COBBLED_RED_DEEPSLATE_BRICKS);
                        entries.add(ModBlocks.CRACKED_COBBLED_RED_DEEPSLATE_BRICKS);

                        entries.add(ModBlocks.REINFORCED_RED_DEEPSLATE);

                        entries.add(ModBlocks.RED_DEEPSLATE_IRON_ORE);
                        entries.add(ModBlocks.RED_DEEPSLATE_COPPER_ORE);
                        entries.add(ModBlocks.RED_DEEPSLATE_GOLD_ORE);
                        entries.add(ModBlocks.RED_DEEPSLATE_REDSTONE_ORE);
                        entries.add(ModBlocks.RED_DEEPSLATE_EMERALD_ORE);
                        entries.add(ModBlocks.RED_DEEPSLATE_LAPIS_ORE);
                        entries.add(ModBlocks.RED_DEEPSLATE_DIAMOND_ORE);

                        entries.add(ModBlocks.RED_DEEPSLATE_SULFUR_ORE);
                        entries.add(ModBlocks.RED_DEEPSLATE_LEAD_ORE);
                        entries.add(ModBlocks.RED_DEEPSLATE_URANIUM_ORE);
                        entries.add(ModBlocks.RED_DEEPSLATE_HELIORITE_ORE);
                        entries.add(ModBlocks.RED_DEEPSLATE_PALLADIUM_ORE);
                        entries.add(ModBlocks.RED_DEEPSLATE_JURASSOLINE_ORE);
                        entries.add(ModBlocks.RED_DEEPSLATE_CINNABAR_ORE);
                        entries.add(ModBlocks.RED_DEEPSLATE_NEBULAR_ORE);

                        entries.add(ModBlocks.PREHISTORIC_DEBRIS);

                        entries.add(ModBlocks.STEEL_BLOCK);

                        entries.add(ModBlocks.NETHER_SULFUR_ORE);
                        entries.add(ModBlocks.SULFUR_BLOCK);
                        entries.add(ModItems.SULFUR_TORCH);
                        entries.add(ModBlocks.SULFUR_LANTERN);

                        entries.add(ModBlocks.LEAD_ORE);
                        entries.add(ModBlocks.RAW_LEAD_BLOCK);
                        entries.add(ModBlocks.LEAD_BLOCK);

                        entries.add(ModBlocks.DEEPSLATE_URANIUM_ORE);
                        entries.add(ModBlocks.RAW_URANIUM_BLOCK);
                        entries.add(ModBlocks.URANIUM_BLOCK);

                        entries.add(ModBlocks.DEEPSLATE_FOSSIL);

                        entries.add(ModBlocks.DEEPSLATE_HELIORITE_ORE);
                        entries.add(ModBlocks.HELIORITE_COMB_BLOCK);
                        entries.add(ModBlocks.HELIORITE_BLOCK);

                        entries.add(ModBlocks.END_ENDURIUM_ORE);
                        entries.add(ModBlocks.RAW_ENDURIUM_BLOCK);
                        entries.add(ModBlocks.ENDURIUM_BLOCK);

                        entries.add(ModBlocks.DEEPSLATE_PALLADIUM_ORE);
                        entries.add(ModBlocks.RAW_PALLADIUM_BLOCK);
                        entries.add(ModBlocks.PALLADIUM_BLOCK);

                        entries.add(ModBlocks.DEEPSLATE_JURASSOLINE_ORE);
                        entries.add(ModBlocks.JURASSOLINE_CRYSTAL_BLOCK);
                        entries.add(ModBlocks.JURASSOLINE_BLOCK);

                        entries.add(ModBlocks.DEEPSLATE_CINNABAR_ORE);
                        entries.add(ModBlocks.CINNABAR_CRYSTAL_BLOCK);
                        entries.add(ModBlocks.CINNABAR_BLOCK);

                        entries.add(ModBlocks.DEEPSLATE_NEBULAR_ORE);
                        entries.add(ModBlocks.RAW_NEBULAR_BLOCK);
                        entries.add(ModBlocks.NEBULAR_BLOCK);

                        entries.add(ModBlocks.BEDROCK_MITHRIL_ORE);
                        entries.add(ModBlocks.RAW_MITHRIL_BLOCK);
                        entries.add(ModBlocks.MITHRIL_BLOCK);

                        entries.add(ModBlocks.MITHRIL_ANVIL);

                        entries.add(ModBlocks.CURSED_WOOL);
                        entries.add(ModBlocks.CURSED_CARPET);

                        entries.add(ModBlocks.ROSE);
                        entries.add(ModBlocks.IRIS);
                        entries.add(ModBlocks.EDELWEISS);
                        entries.add(ModBlocks.ATHELAS);

                        entries.add(ModBlocks.PRESENT);
                        entries.add(ModBlocks.FESTIVE_LIGHTS);

                        entries.add(ModBlocks.GLASS_DOOR);
                        entries.add(ModBlocks.GLASS_TRAPDOOR);

                        entries.add(ModBlocks.GINKGO_SAPLING);
                        entries.add(ModBlocks.GINKGO_LEAVES);
                        entries.add(ModBlocks.GINKGO_LOG);
                        entries.add(ModBlocks.GINKGO_WOOD);
                        entries.add(ModBlocks.STRIPPED_GINKGO_LOG);
                        entries.add(ModBlocks.STRIPPED_GINKGO_WOOD);
                        entries.add(ModBlocks.GINKGO_PLANKS);

                        entries.add(ModBlocks.GINKGO_STAIRS);
                        entries.add(ModBlocks.GINKGO_SLAB);
                        entries.add(ModBlocks.GINKGO_BUTTON);
                        entries.add(ModBlocks.GINKGO_PRESSURE_PLATE);
                        entries.add(ModBlocks.GINKGO_FENCE);
                        entries.add(ModBlocks.GINKGO_FENCE_GATE);
                        entries.add(ModBlocks.GINKGO_DOOR);
                        entries.add(ModBlocks.GINKGO_TRAPDOOR);

                        entries.add(ModItems.GINKGO_SIGN);
                        entries.add(ModItems.HANGING_GINKGO_SIGN);

                        entries.add(ModBlocks.FRAMED_GLASS);
                        entries.add(ModBlocks.FRAMED_GLASS_BOTTOM);
                        entries.add(ModBlocks.FRAMED_GLASS_MIDDLE);
                        entries.add(ModBlocks.FRAMED_GLASS_TOP);
                        entries.add(ModBlocks.FRAMED_GLASS_SPLIT);
                        entries.add(ModBlocks.FRAMED_GLASS_TILED);

                        entries.add(ModBlocks.FRAMED_GLASS_PANE);
                        entries.add(ModBlocks.FRAMED_GLASS_BOTTOM_PANE);
                        entries.add(ModBlocks.FRAMED_GLASS_MIDDLE_PANE);
                        entries.add(ModBlocks.FRAMED_GLASS_TOP_PANE);
                        entries.add(ModBlocks.FRAMED_GLASS_SPLIT_PANE);
                        entries.add(ModBlocks.FRAMED_GLASS_TILED_PANE);

                        entries.add(ModBlocks.FANCY_OAK_PLANKS);
                        entries.add(ModBlocks.FANCY_OAK_STAIRS);
                        entries.add(ModBlocks.FANCY_OAK_SLAB);
                        entries.add(ModBlocks.FANCY_OAK_TRAPDOOR);

                        entries.add(ModBlocks.FANCY_SPRUCE_PLANKS);
                        entries.add(ModBlocks.FANCY_SPRUCE_STAIRS);
                        entries.add(ModBlocks.FANCY_SPRUCE_SLAB);
                        entries.add(ModBlocks.FANCY_SPRUCE_TRAPDOOR);

                        entries.add(ModBlocks.FANCY_BIRCH_PLANKS);
                        entries.add(ModBlocks.FANCY_BIRCH_STAIRS);
                        entries.add(ModBlocks.FANCY_BIRCH_SLAB);
                        entries.add(ModBlocks.FANCY_BIRCH_TRAPDOOR);

                        entries.add(ModBlocks.FANCY_JUNGLE_PLANKS);
                        entries.add(ModBlocks.FANCY_JUNGLE_STAIRS);
                        entries.add(ModBlocks.FANCY_JUNGLE_SLAB);
                        entries.add(ModBlocks.FANCY_JUNGLE_TRAPDOOR);

                        entries.add(ModBlocks.FANCY_ACACIA_PLANKS);
                        entries.add(ModBlocks.FANCY_ACACIA_STAIRS);
                        entries.add(ModBlocks.FANCY_ACACIA_SLAB);
                        entries.add(ModBlocks.FANCY_ACACIA_TRAPDOOR);

                        entries.add(ModBlocks.FANCY_DARK_OAK_PLANKS);
                        entries.add(ModBlocks.FANCY_DARK_OAK_STAIRS);
                        entries.add(ModBlocks.FANCY_DARK_OAK_SLAB);
                        entries.add(ModBlocks.FANCY_DARK_OAK_TRAPDOOR);

                        entries.add(ModBlocks.FANCY_MANGROVE_PLANKS);
                        entries.add(ModBlocks.FANCY_MANGROVE_STAIRS);
                        entries.add(ModBlocks.FANCY_MANGROVE_SLAB);
                        entries.add(ModBlocks.FANCY_MANGROVE_TRAPDOOR);

                        entries.add(ModBlocks.FANCY_CHERRY_PLANKS);
                        entries.add(ModBlocks.FANCY_CHERRY_STAIRS);
                        entries.add(ModBlocks.FANCY_CHERRY_SLAB);
                        entries.add(ModBlocks.FANCY_CHERRY_TRAPDOOR);

                        entries.add(ModBlocks.FANCY_PALE_OAK_PLANKS);
                        entries.add(ModBlocks.FANCY_PALE_OAK_STAIRS);
                        entries.add(ModBlocks.FANCY_PALE_OAK_SLAB);
                        entries.add(ModBlocks.FANCY_PALE_OAK_TRAPDOOR);

                        entries.add(ModBlocks.FANCY_BAMBOO_PLANKS);
                        entries.add(ModBlocks.FANCY_BAMBOO_STAIRS);
                        entries.add(ModBlocks.FANCY_BAMBOO_SLAB);
                        entries.add(ModBlocks.FANCY_BAMBOO_TRAPDOOR);

                        entries.add(ModBlocks.FANCY_CRIMSON_PLANKS);
                        entries.add(ModBlocks.FANCY_CRIMSON_STAIRS);
                        entries.add(ModBlocks.FANCY_CRIMSON_SLAB);
                        entries.add(ModBlocks.FANCY_CRIMSON_TRAPDOOR);

                        entries.add(ModBlocks.FANCY_WARPED_PLANKS);
                        entries.add(ModBlocks.FANCY_WARPED_STAIRS);
                        entries.add(ModBlocks.FANCY_WARPED_SLAB);
                        entries.add(ModBlocks.FANCY_WARPED_TRAPDOOR);

                        entries.add(ModBlocks.FANCY_GINKGO_PLANKS);
                        entries.add(ModBlocks.FANCY_GINKGO_STAIRS);
                        entries.add(ModBlocks.FANCY_GINKGO_SLAB);
                        entries.add(ModBlocks.FANCY_GINKGO_TRAPDOOR);

                        entries.add(ModBlocks.FANCY_CHARRED_PLANKS);
                        entries.add(ModBlocks.FANCY_CHARRED_STAIRS);
                        entries.add(ModBlocks.FANCY_CHARRED_SLAB);
                        entries.add(ModBlocks.FANCY_CHARRED_TRAPDOOR);

                        entries.add(ModBlocks.ARCHAEOLOGY_TABLE);
                    }).build());

    public static final ItemGroup TLOTD_9_COMPAT_GROUP = Registry.register(Registries.ITEM_GROUP,
            new Identifier(TLOTD.MOD_ID, "9_compat"),
            FabricItemGroup.builder().displayName(Text.translatable("itemgroup.tlotd.compat"))
                    .icon(() -> new ItemStack(ModBlocks.INFECTED_TREX_HEAD)).entries((displayContext, entries) -> {
                        entries.add(ModItems.INCOMPLETE_COPPER_WIRE);
                        entries.add(ModItems.INCOMPLETE_GOLD_WIRE);
                        entries.add(ModItems.INCOMPLETE_INTEGRATED_CIRCUIT);
                        entries.add(ModItems.INCOMPLETE_CIRCUIT_BOARD);
                        entries.add(ModItems.INCOMPLETE_ADVANCED_CIRCUIT_BOARD);
                        entries.add(ModItems.INCOMPLETE_RADIO);
                        entries.add(ModItems.INCOMPLETE_TELEVISION);
                        entries.add(ModItems.INCOMPLETE_VIDEOCASSETTE_RECORDER);
                        entries.add(ModItems.INCOMPLETE_SIGNAL_TRANSMITTER);
                        entries.add(ModItems.INCOMPLETE_COMPUTER);
                        entries.add(ModItems.INCOMPLETE_OXYGEN_COLLECTOR);
                        entries.add(ModItems.INCOMPLETE_MITHRIL_MIRROR);
                        entries.add(ModFluids.SWEET_BERRY_JAM_BUCKET);
                        entries.add(ModFluids.GLOW_BERRY_JAM_BUCKET);
                        entries.add(ModFluids.STRAWBERRY_JAM_BUCKET);
                        entries.add(ModFluids.ORANGE_MARMELADE_BUCKET);
                        entries.add(ModFluids.BLUE_BERRY_JAM_BUCKET);
                        entries.add(ModBlocks.BLUE_BERRY_JAM_JAR);
                        entries.add(ModItems.BLUE_BERRY_JAM_TOAST);
                        entries.add(ModFluids.ANCIENT_SOULBERRY_JAM_BUCKET);
                        entries.add(ModBlocks.ANCIENT_SOULBERRY_JAM_JAR);
                        entries.add(ModItems.ANCIENT_SOULBERRY_JAM_TOAST);
                        entries.add(ModItems.INFECTED_TREX_SPAWN_EGG);
                        entries.add(ModBlocks.INFECTED_TREX_HEAD);
                        entries.add(ModItems.SKYROOT_BARK);
                        entries.add(ModItems.PEWEN_BARK);
                        entries.add(ModItems.THORNWOOD_BARK);
                        entries.add(ModItems.FIR_BARK);
                        entries.add(ModItems.REDWOOD_BARK);
                        entries.add(ModItems.MAHOGANY_BARK);
                        entries.add(ModItems.JACARANCA_BARK);
                        entries.add(ModItems.PALM_BARK);
                        entries.add(ModItems.WILLOW_BARK);
                        entries.add(ModItems.DEAD_BARK);
                        entries.add(ModItems.MAGIC_BARK);
                        entries.add(ModItems.UMBRAN_BARK);
                        entries.add(ModItems.HELLBARK_BARK);
                        entries.add(ModItems.ASHEN_BARK);
                        entries.add(ModItems.AZALEA_BARK);
                        entries.add(ModItems.TRUMPET_BARK);
                        entries.add(ModItems.RUBBERWOOD_BARK);
                        entries.add(ModItems.TWILIGHT_OAK_BARK);
                        entries.add(ModItems.CANOPY_TREE_BARK);
                        entries.add(ModItems.TWILIGHT_MANGROVE_BARK);
                        entries.add(ModItems.DARKWOOD_BARK);
                        entries.add(ModItems.TIMEWOOD_BARK);
                        entries.add(ModItems.TRANSWOOD_BARK);
                        entries.add(ModItems.MINEWOOD_BARK);
                        entries.add(ModItems.SORTINGWOOD_BARK);
                    }).build());

    public static void registerItemGroups(){
        TLOTD.LOGGER.info("Registering Item Groups for " + TLOTD.MOD_ID);
    }

}