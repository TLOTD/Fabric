package net.tlotd.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.item.Items;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.ItemTags;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;
import net.tlotd.block.ModBlocks;
import net.tlotd.fluid.ModFluids;
import net.tlotd.item.ModItems;
import net.tlotd.util.ModTags;

import java.util.concurrent.CompletableFuture;

public class ModItemTagProvider extends FabricTagProvider.ItemTagProvider {
    public ModItemTagProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> completableFuture) {
        super(output, completableFuture);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup wrapperLookup) {

        getOrCreateTagBuilder(ModTags.Items.FORGING_HAMMERS)
                .addTag(ModTags.Items.FORGING_HAMMERS_TIER1)
        ;

        getOrCreateTagBuilder(ModTags.Items.FORGING_HAMMERS_TIER1)
                .addTag(ModTags.Items.FORGING_HAMMERS_TIER2)
        ;

        getOrCreateTagBuilder(ModTags.Items.FORGING_HAMMERS_TIER2)
                .addTag(ModTags.Items.FORGING_HAMMERS_TIER3)
        ;

        getOrCreateTagBuilder(ModTags.Items.FORGING_HAMMERS_TIER3)
                .addTag(ModTags.Items.FORGING_HAMMERS_TIER4)
        ;

        getOrCreateTagBuilder(ModTags.Items.FORGING_HAMMERS_TIER4)
                .add(ModItems.NETHERITE_FORGING_HAMMER)
                .addTag(ModTags.Items.FORGING_HAMMERS_TIER5)
        ;

        getOrCreateTagBuilder(ModTags.Items.FORGING_HAMMERS_TIER5)
                .add(ModItems.HELIORITE_FORGING_HAMMER)
                .add(ModItems.ENDURIUM_FORGING_HAMMER)
                .addTag(ModTags.Items.FORGING_HAMMERS_TIER6)
        ;

        getOrCreateTagBuilder(ModTags.Items.FORGING_HAMMERS_TIER6)
                .add(ModItems.PALLADIUM_FORGING_HAMMER)
                .add(ModItems.JURASSOLINE_FORGING_HAMMER)
                .addTag(ModTags.Items.FORGING_HAMMERS_TIER7)
        ;

        getOrCreateTagBuilder(ModTags.Items.FORGING_HAMMERS_TIER7)
                .add(ModItems.CINNABAR_FORGING_HAMMER)
                .add(ModItems.NEBULAR_FORGING_HAMMER)
                .addTag(ModTags.Items.FORGING_HAMMERS_TIER8)
        ;

        getOrCreateTagBuilder(ModTags.Items.FORGING_HAMMERS_TIER8)
                .add(ModItems.MITHRIL_FORGING_HAMMER)
                .addTag(ModTags.Items.FORGING_HAMMERS_TIER9)
        ;

        getOrCreateTagBuilder(ModTags.Items.FORGING_HAMMERS_TIER9)
                .add(ModItems.ASTRAL_FORGING_HAMMER)
        ;

        getOrCreateTagBuilder(ModTags.Items.WOODEN_BARK)
                .add(ModItems.OAK_BARK)
                .add(ModItems.SPRUCE_BARK)
                .add(ModItems.BIRCH_BARK)
                .add(ModItems.JUNGLE_BARK)
                .add(ModItems.ACACIA_BARK)
                .add(ModItems.DARK_OAK_BARK)
                .add(ModItems.MANGROVE_BARK)
                .add(ModItems.CHERRY_BARK)
                .add(ModItems.GINKGO_BARK)

                .add(ModItems.SKYROOT_BARK)

                .add(ModItems.PEWEN_BARK)
                .add(ModItems.THORNWOOD_BARK)

                .add(ModItems.FIR_BARK)
                .add(ModItems.REDWOOD_BARK)
                .add(ModItems.MAHOGANY_BARK)
                .add(ModItems.JACARANCA_BARK)
                .add(ModItems.PALM_BARK)
                .add(ModItems.WILLOW_BARK)
                .add(ModItems.DEAD_BARK)
                .add(ModItems.MAGIC_BARK)
                .add(ModItems.UMBRAN_BARK)
                .add(ModItems.HELLBARK_BARK)

                .add(ModItems.ASHEN_BARK)
                .add(ModItems.AZALEA_BARK)
                .add(ModItems.TRUMPET_BARK)

                .add(ModItems.RUBBERWOOD_BARK)

                .add(ModItems.TWILIGHT_OAK_BARK)
                .add(ModItems.CANOPY_TREE_BARK)
                .add(ModItems.TWILIGHT_MANGROVE_BARK)
                .add(ModItems.DARKWOOD_BARK)
                .add(ModItems.TIMEWOOD_BARK)
                .add(ModItems.TRANSWOOD_BARK)
                .add(ModItems.MINEWOOD_BARK)
                .add(ModItems.SORTINGWOOD_BARK)
        ;

        getOrCreateTagBuilder(ItemTags.LOGS_THAT_BURN)
                .addTag(ModTags.Items.GINKGO_LOGS)
        ;

        getOrCreateTagBuilder(ModTags.Items.GINKGO_LOGS)
                .add(ModBlocks.GINKGO_LOG.asItem())
                .add(ModBlocks.GINKGO_WOOD.asItem())
                .add(ModBlocks.STRIPPED_GINKGO_LOG.asItem())
                .add(ModBlocks.STRIPPED_GINKGO_WOOD.asItem())
        ;

        getOrCreateTagBuilder(ItemTags.LEAVES)
                .add(ModBlocks.GINKGO_LEAVES.asItem())
        ;

        getOrCreateTagBuilder(ItemTags.SAPLINGS)
                .add(ModBlocks.GINKGO_SAPLING.asItem())
        ;

        getOrCreateTagBuilder(ItemTags.PLANKS)
                .add(ModBlocks.GINKGO_PLANKS.asItem())
                .addOptionalTag(Identifier.tryParse("c:fancy_planks"))
        ;

        getOrCreateTagBuilder(ItemTags.WOODEN_STAIRS)
                .add(ModBlocks.GINKGO_STAIRS.asItem())
                .add(ModBlocks.FANCY_OAK_STAIRS.asItem())
                .add(ModBlocks.FANCY_SPRUCE_STAIRS.asItem())
                .add(ModBlocks.FANCY_BIRCH_STAIRS.asItem())
                .add(ModBlocks.FANCY_JUNGLE_STAIRS.asItem())
                .add(ModBlocks.FANCY_ACACIA_STAIRS.asItem())
                .add(ModBlocks.FANCY_DARK_OAK_STAIRS.asItem())
                .add(ModBlocks.FANCY_MANGROVE_STAIRS.asItem())
                .add(ModBlocks.FANCY_CHERRY_STAIRS.asItem())
                .add(ModBlocks.FANCY_PALE_OAK_STAIRS.asItem())
                .add(ModBlocks.FANCY_BAMBOO_STAIRS.asItem())
                .add(ModBlocks.FANCY_CRIMSON_STAIRS.asItem())
                .add(ModBlocks.FANCY_WARPED_STAIRS.asItem())
                .add(ModBlocks.FANCY_GINKGO_STAIRS.asItem())
                .add(ModBlocks.FANCY_CHARRED_STAIRS.asItem())
        ;

        getOrCreateTagBuilder(ItemTags.WOODEN_SLABS)
                .add(ModBlocks.GINKGO_SLAB.asItem())
                .add(ModBlocks.FANCY_OAK_SLAB.asItem())
                .add(ModBlocks.FANCY_SPRUCE_SLAB.asItem())
                .add(ModBlocks.FANCY_BIRCH_SLAB.asItem())
                .add(ModBlocks.FANCY_JUNGLE_SLAB.asItem())
                .add(ModBlocks.FANCY_ACACIA_SLAB.asItem())
                .add(ModBlocks.FANCY_DARK_OAK_SLAB.asItem())
                .add(ModBlocks.FANCY_MANGROVE_SLAB.asItem())
                .add(ModBlocks.FANCY_CHERRY_SLAB.asItem())
                .add(ModBlocks.FANCY_PALE_OAK_SLAB.asItem())
                .add(ModBlocks.FANCY_BAMBOO_SLAB.asItem())
                .add(ModBlocks.FANCY_CRIMSON_SLAB.asItem())
                .add(ModBlocks.FANCY_WARPED_SLAB.asItem())
                .add(ModBlocks.FANCY_GINKGO_SLAB.asItem())
                .add(ModBlocks.FANCY_CHARRED_SLAB.asItem())
        ;

        getOrCreateTagBuilder(ItemTags.WOODEN_FENCES)
                .add(ModBlocks.GINKGO_FENCE.asItem())
        ;

        getOrCreateTagBuilder(ItemTags.FENCE_GATES)
                .add(ModBlocks.GINKGO_FENCE_GATE.asItem())
        ;

        getOrCreateTagBuilder(ItemTags.WOODEN_DOORS)
                .add(ModBlocks.GINKGO_DOOR.asItem())
        ;

        getOrCreateTagBuilder(ItemTags.WOODEN_TRAPDOORS)
                .add(ModBlocks.GINKGO_TRAPDOOR.asItem())
                .add(ModBlocks.FANCY_OAK_TRAPDOOR.asItem())
                .add(ModBlocks.FANCY_SPRUCE_TRAPDOOR.asItem())
                .add(ModBlocks.FANCY_BIRCH_TRAPDOOR.asItem())
                .add(ModBlocks.FANCY_JUNGLE_TRAPDOOR.asItem())
                .add(ModBlocks.FANCY_ACACIA_TRAPDOOR.asItem())
                .add(ModBlocks.FANCY_DARK_OAK_TRAPDOOR.asItem())
                .add(ModBlocks.FANCY_MANGROVE_TRAPDOOR.asItem())
                .add(ModBlocks.FANCY_CHERRY_TRAPDOOR.asItem())
                .add(ModBlocks.FANCY_PALE_OAK_TRAPDOOR.asItem())
                .add(ModBlocks.FANCY_BAMBOO_TRAPDOOR.asItem())
                .add(ModBlocks.FANCY_CRIMSON_TRAPDOOR.asItem())
                .add(ModBlocks.FANCY_WARPED_TRAPDOOR.asItem())
                .add(ModBlocks.FANCY_GINKGO_TRAPDOOR.asItem())
                .add(ModBlocks.FANCY_CHARRED_TRAPDOOR.asItem())
        ;

        getOrCreateTagBuilder(ItemTags.WOODEN_PRESSURE_PLATES)
                .add(ModBlocks.GINKGO_PRESSURE_PLATE.asItem())
        ;

        getOrCreateTagBuilder(ItemTags.WOODEN_BUTTONS)
                .add(ModBlocks.GINKGO_BUTTON.asItem())
        ;

        getOrCreateTagBuilder(ItemTags.SIGNS)
                .add(ModItems.GINKGO_SIGN)
        ;

        getOrCreateTagBuilder(ItemTags.HANGING_SIGNS)
                .add(ModItems.HANGING_GINKGO_SIGN)
        ;

        getOrCreateTagBuilder(ItemTags.TRIMMABLE_ARMOR)
                .add(ModItems.HELIORITE_HELMET, ModItems.HELIORITE_CHESTPLATE, ModItems.HELIORITE_LEGGINGS, ModItems.HELIORITE_BOOTS)
                .add(ModItems.ENDURIUM_HELMET, ModItems.ENDURIUM_CHESTPLATE, ModItems.ENDURIUM_LEGGINGS, ModItems.ENDURIUM_BOOTS)
                .add(ModItems.PALLADIUM_HELMET, ModItems.PALLADIUM_CHESTPLATE, ModItems.PALLADIUM_LEGGINGS, ModItems.PALLADIUM_BOOTS)
                .add(ModItems.JURASSOLINE_HELMET, ModItems.JURASSOLINE_CHESTPLATE, ModItems.JURASSOLINE_LEGGINGS, ModItems.JURASSOLINE_BOOTS)
                .add(ModItems.CINNABAR_HELMET, ModItems.CINNABAR_CHESTPLATE, ModItems.CINNABAR_LEGGINGS, ModItems.CINNABAR_BOOTS)
                .add(ModItems.NEBULAR_HELMET, ModItems.NEBULAR_CHESTPLATE, ModItems.NEBULAR_LEGGINGS, ModItems.NEBULAR_BOOTS)
                .add(ModItems.MITHRIL_HELMET, ModItems.MITHRIL_CHESTPLATE, ModItems.MITHRIL_LEGGINGS, ModItems.MITHRIL_BOOTS)
        ;

        getOrCreateTagBuilder(ItemTags.BOOKSHELF_BOOKS)
                .add(ModItems.GUIDEBOOK)
                .add(ModItems.SPELL_BOOK)
                .add(ModItems.FORBIDDEN_SPELL_BOOK)
        ;

        getOrCreateTagBuilder(ItemTags.SMALL_FLOWERS)
                .add(ModBlocks.ROSE.asItem())
                .add(ModBlocks.IRIS.asItem())
                .add(ModBlocks.EDELWEISS.asItem())
                .add(ModBlocks.ATHELAS.asItem())
        ;

        getOrCreateTagBuilder(ItemTags.BOATS)
                .add(ModItems.GINKGO_BOAT)
        ;

        getOrCreateTagBuilder(ItemTags.CHEST_BOATS)
                .add(ModItems.GINKGO_CHEST_BOAT)
        ;

        getOrCreateTagBuilder(ItemTags.STONE_BUTTONS)
                .add(ModBlocks.MARBLE_BUTTON.asItem())
                .add(ModBlocks.LIMESTONE_BUTTON.asItem())
                .add(ModBlocks.RED_DEEPSLATE_BUTTON.asItem())
        ;

        getOrCreateTagBuilder(ItemTags.STONE_CRAFTING_MATERIALS)
                .add(ModBlocks.MARBLE.asItem())
                .add(ModBlocks.LIMESTONE.asItem())
                .add(ModBlocks.RED_DEEPSLATE.asItem())
                .add(ModBlocks.COBBLED_RED_DEEPSLATE.asItem())
        ;

        getOrCreateTagBuilder(ItemTags.STONE_TOOL_MATERIALS)
                .add(ModBlocks.MARBLE.asItem())
                .add(ModBlocks.LIMESTONE.asItem())
                .add(ModBlocks.RED_DEEPSLATE.asItem())
                .add(ModBlocks.COBBLED_RED_DEEPSLATE.asItem())
        ;

        getOrCreateTagBuilder(ItemTags.BEACON_PAYMENT_ITEMS)
                .add(ModItems.HELIORITE_INGOT)
                .add(ModItems.ENDURIUM_INGOT)
                .add(ModItems.PALLADIUM_INGOT)
                .add(ModItems.JURASSOLINE_INGOT)
                .add(ModItems.CINNABAR_INGOT)
                .add(ModItems.NEBULAR_INGOT)
                .add(ModItems.MITHRIL_INGOT)
                .add(ModItems.ASTRAL_INGOT)
        ;

        getOrCreateTagBuilder(ItemTags.WOOL)
                .add(ModBlocks.CURSED_WOOL.asItem())
        ;

        getOrCreateTagBuilder(ItemTags.WOOL_CARPETS)
                .add(ModBlocks.CURSED_CARPET.asItem())
        ;

        getOrCreateTagBuilder(ItemTags.PIGLIN_LOVED)
                .add(ModBlocks.GLOBUS_CRUCIGER.asItem())
                .add(ModItems.EMPERORS_CROWN)
        ;

        getOrCreateTagBuilder(ItemTags.FOX_FOOD)
                .add(ModItems.STRAWBERRY)
        ;

        getOrCreateTagBuilder(ItemTags.SMALL_FLOWERS)
                .add(ModBlocks.ROSE.asItem())
                .add(ModBlocks.IRIS.asItem())
                .add(ModBlocks.ROSE.asItem())
                .add(ModBlocks.ATHELAS.asItem())
        ;

        getOrCreateTagBuilder(ItemTags.CREEPER_DROP_MUSIC_DISCS)
                .add(ModItems.MUSIC_DISC_1)
                .add(ModItems.MUSIC_DISC_2)
                .add(ModItems.MUSIC_DISC_3)
                .add(ModItems.MUSIC_DISC_4)
                .add(ModItems.VHS_CASSETTE)
        ;

        getOrCreateTagBuilder(ItemTags.MUSIC_DISCS)
                .add(ModItems.MUSIC_DISC_1)
                .add(ModItems.MUSIC_DISC_2)
                .add(ModItems.MUSIC_DISC_3)
                .add(ModItems.MUSIC_DISC_4)
        ;

        getOrCreateTagBuilder(ModTags.Items.MUSIC_DISCS)
                .add(ModItems.MUSIC_DISC_1)
                .add(ModItems.MUSIC_DISC_2)
                .add(ModItems.MUSIC_DISC_3)
                .add(ModItems.MUSIC_DISC_4)
        ;

        getOrCreateTagBuilder(ModTags.Items.STICK_EFFIGIES)
                .add(ModBlocks.STICK_CROSS.asItem())
                .add(ModItems.STICK_EFFIGY)
                .add(ModItems.STICK_FIGURE)
        ;

        getOrCreateTagBuilder(ModTags.Items.BEER_CONTAINER)
                .add(ModItems.BEER_GOAT_HORN)
                .add(ModFluids.BEER_BUCKET)
                .add(ModBlocks.WOODEN_BEER_STEIN.asItem())
        ;

        getOrCreateTagBuilder(ModTags.Items.HOT_MILK_CONTAINER)
                .add(ModFluids.HOT_MILK_BUCKET)
                .add(ModBlocks.HOT_WOODEN_MILK_STEIN.asItem())
        ;

        getOrCreateTagBuilder(ModTags.Items.EXTRACTION_PICKAXES)
                .add(ModItems.CINNABAR_PICKAXE)
                .add(ModItems.NEBULAR_PICKAXE)
                .add(ModItems.MITHRIL_PICKAXE)
                .add(ModItems.ASTRAL_PICKAXE)
                .add(ModItems.DIVINE_PICKAXE)
                .add(ModItems.CATACLYSMIC_PICKAXE)
        ;

        getOrCreateTagBuilder(ModTags.Items.EXTRACTION_II_PICKAXES)
                .add(ModItems.MITHRIL_PICKAXE)
                .add(ModItems.ASTRAL_PICKAXE)
                .add(ModItems.DIVINE_PICKAXE)
                .add(ModItems.CATACLYSMIC_PICKAXE)
                .add(ModItems.MITHRIL_COMMAND_BLOCK_PICKAXE)
        ;

        getOrCreateTagBuilder(ModTags.Items.EXTRACTION_III_PICKAXES)
                .add(ModItems.ASTRAL_PICKAXE)
                .add(ModItems.DIVINE_PICKAXE)
                .add(ModItems.CATACLYSMIC_PICKAXE)
        ;

        getOrCreateTagBuilder(ModTags.Items.MOUTH_OF_THE_ABYSS)
                .add(ModItems.ELDRITCH_PICKAXE)
        ;

        getOrCreateTagBuilder(ModTags.Items.WOODEN_LIQUID_STEINS)
                .add(ModBlocks.WOODEN_WATER_STEIN.asItem())
                .add(ModBlocks.WOODEN_BEER_STEIN.asItem())
                .add(ModBlocks.WOODEN_APPLE_JUICE_STEIN.asItem())
                .add(ModBlocks.WOODEN_ORANGE_JUICE_STEIN.asItem())
                .add(ModBlocks.WOODEN_MILK_STEIN.asItem())
                .add(ModBlocks.WOODEN_CHOCOLATE_MILKSHAKE_STEIN.asItem())
                .add(ModBlocks.WOODEN_STRAWBERRY_MILKSHAKE_STEIN.asItem())
                .add(ModBlocks.WOODEN_ORANGE_MILKSHAKE_STEIN.asItem())
                .add(ModBlocks.WOODEN_BLUE_BERRY_MILKSHAKE_STEIN.asItem())
                .add(ModBlocks.WOODEN_TORCHBERRY_MILKSHAKE_STEIN.asItem())
                .add(ModBlocks.HOT_WOODEN_MILK_STEIN.asItem())
                .add(ModBlocks.WOODEN_HOT_CHOCOLATE_STEIN.asItem())
        ;

        getOrCreateTagBuilder(ModTags.Items.VHS_CASSETTES)
                .add(ModItems.VHS_CASSETTE)
                .add(ModItems.VHS_CASSETTE_1)
                .add(ModItems.VHS_CASSETTE_2)
                .add(ModItems.VHS_CASSETTE_3)
                .add(ModItems.VHS_CASSETTE_4)
                .add(ModItems.VHS_CASSETTE_5)
                .add(ModItems.VHS_CASSETTE_6)
                .add(ModItems.VHS_CASSETTE_7)
                .add(ModItems.VHS_CASSETTE_8)
                .add(ModItems.VHS_CASSETTE_9)
                .add(ModItems.VHS_CASSETTE_BROKEN)
        ;

        getOrCreateTagBuilder(ModTags.Items.GAME_CARTRIDGES)
                .add(ModItems.GAME_CARTRIDGE)
                .add(ModItems.GAME_CARTRIDGE_1)
                .add(ModItems.GAME_CARTRIDGE_2)
                .add(ModItems.GAME_CARTRIDGE_3)
        ;

        getOrCreateTagBuilder(ModTags.Items.RADIATION_PROTECTION_WITHOUT_HELMET)
                .add(ModItems.HEV_SUIT_CHESTPLATE)
                .add(ModItems.HEV_SUIT_LEGGINGS)
                .add(ModItems.HEV_SUIT_BOOTS)
        ;

        getOrCreateTagBuilder(ModTags.Items.RADIATION_PROTECTION)
                .add(ModItems.HEV_SUIT_CHESTPLATE)
                .add(ModItems.HEV_SUIT_LEGGINGS)
                .add(ModItems.HEV_SUIT_BOOTS)
                .addOptional(Identifier.tryParse("alexscaves:hazmat_mask"))
                .addOptional(Identifier.tryParse("alexscaves:hazmat_chestplate"))
                .addOptional(Identifier.tryParse("alexscaves:hazmat_leggings"))
                .addOptional(Identifier.tryParse("alexscaves:hazmat_boots"))
                .addOptional(Identifier.tryParse("mekanism:hazmat_mask"))
                .addOptional(Identifier.tryParse("mekanism:hazmat_gown"))
                .addOptional(Identifier.tryParse("mekanism:hazmat_pants"))
                .addOptional(Identifier.tryParse("mekanism:hazmat_boots"))
        ;

        getOrCreateTagBuilder(ModTags.Items.OXYGEN_CHARGABLE)
                .add(ModItems.OXYGEN_TANK)
                .add(ModItems.SPACE_SUIT_CHESTPLATE)
                .addOptional(Identifier.tryParse("ad_astra:space_suit"))
        ;

        getOrCreateTagBuilder(ModTags.Items.HYPOXIA_PROTECTION)
                .add(ModItems.ASTRONAUT_HELMET)
                .add(ModItems.SPACE_SUIT_CHESTPLATE)
                .add(ModItems.SPACE_SUIT_LEGGINGS)
                .add(ModItems.SPACE_SUIT_BOOTS)
                .addOptional(Identifier.tryParse("ad_astra:space_helmet"))
                .addOptional(Identifier.tryParse("ad_astra:space_suit"))
                .addOptional(Identifier.tryParse("ad_astra:space_pants"))
                .addOptional(Identifier.tryParse("ad_astra:space_boots"))
        ;

        getOrCreateTagBuilder(ModTags.Items.CIRCUIT_BOARDS)
                .add(ModItems.CIRCUIT_BOARD)
                .addTag(ModTags.Items.ADVANCED_CIRCUIT_BOARDS)
        ;

        getOrCreateTagBuilder(ModTags.Items.ADVANCED_CIRCUIT_BOARDS)
                .add(ModItems.ADVANCED_CIRCUIT_BOARD)
                .addTag(ModTags.Items.TRANSCENDENT_CIRCUIT_BOARDS)
        ;

        getOrCreateTagBuilder(ModTags.Items.TRANSCENDENT_CIRCUIT_BOARDS)
                .add(ModItems.TRANSCENDENT_CIRCUIT_BOARD)
                .add(ModItems.FUTURISTIC_CIRCUIT_BOARD)
                .add(ModItems.ARCANE_CIRCUIT_BOARD)
                .add(ModItems.BIOLOGICAL_CIRCUIT_BOARD)
        ;

        getOrCreateTagBuilder(ModTags.Items.KEYCARDS)
                .add(ModItems.KEYCARD)
        ;

        getOrCreateTagBuilder(ModTags.Items.TREX_HEADS)
                .add(ModBlocks.TREX_HEAD.asItem())
                .add(ModBlocks.GREEN_TREX_HEAD.asItem())
                .add(ModBlocks.GRAY_TREX_HEAD.asItem())

                .add(ModBlocks.INFECTED_TREX_HEAD.asItem())
                .add(ModBlocks.SCULK_TREX_HEAD.asItem())
                .add(ModBlocks.SICKENED_TREX_HEAD.asItem())
        ;

        getOrCreateTagBuilder(ModTags.Items.SICKLES)
                .add(ModItems.COPPER_SICKLE)
                .add(ModItems.GOLDEN_SICKLE)
                .add(ModItems.STEEL_SICKLE)
                .add(ModItems.NETHERITE_SICKLE)
                .add(ModItems.HELIORITE_SICKLE)
                .add(ModItems.ENDURIUM_SICKLE)
                .add(ModItems.PALLADIUM_SICKLE)
                .add(ModItems.JURASSOLINE_SICKLE)
                .add(ModItems.CINNABAR_SICKLE)
                .add(ModItems.NEBULAR_SICKLE)
                .add(ModItems.MITHRIL_SICKLE)
                .add(ModItems.ASTRAL_SICKLE)
        ;

        getOrCreateTagBuilder(TagKey.of(RegistryKeys.ITEM, new Identifier("c", "swords")))
                .add(ModItems.BAMBOO_SWORD)
                .add(ModItems.HELIORITE_SWORD)
                .add(ModItems.ENDURIUM_SWORD)
                .add(ModItems.PALLADIUM_SWORD)
                .add(ModItems.JURASSOLINE_SWORD)
                .add(ModItems.CINNABAR_SWORD)
                .add(ModItems.NEBULAR_SWORD)
                .add(ModItems.MITHRIL_SWORD)
                .add(ModItems.MITHRIL_COMMAND_BLOCK_SWORD)
                .add(ModItems.ASTRAL_SWORD)
                .add(ModItems.NARSIL_HANDLE)
                .add(ModItems.ANDURIL)
        ;

        getOrCreateTagBuilder(TagKey.of(RegistryKeys.ITEM, new Identifier("c", "pickaxes")))
                .add(ModItems.BAMBOO_PICKAXE)
                .add(ModItems.HELIORITE_PICKAXE)
                .add(ModItems.ENDURIUM_PICKAXE)
                .add(ModItems.PALLADIUM_PICKAXE)
                .add(ModItems.JURASSOLINE_PICKAXE)
                .add(ModItems.CINNABAR_PICKAXE)
                .add(ModItems.NEBULAR_PICKAXE)
                .add(ModItems.MITHRIL_PICKAXE)
                .add(ModItems.MITHRIL_COMMAND_BLOCK_PICKAXE)
                .add(ModItems.ASTRAL_PICKAXE)
                .add(ModItems.DIVINE_PICKAXE)
                .add(ModItems.CATACLYSMIC_PICKAXE)
                .add(ModItems.ELDRITCH_PICKAXE)
        ;

        getOrCreateTagBuilder(TagKey.of(RegistryKeys.ITEM, new Identifier("c", "axes")))
                .add(ModItems.BAMBOO_AXE)
                .add(ModItems.HELIORITE_AXE)
                .add(ModItems.ENDURIUM_AXE)
                .add(ModItems.PALLADIUM_AXE)
                .add(ModItems.JURASSOLINE_AXE)
                .add(ModItems.CINNABAR_AXE)
                .add(ModItems.NEBULAR_AXE)
                .add(ModItems.MITHRIL_AXE)
                .add(ModItems.MITHRIL_COMMAND_BLOCK_AXE)
                .add(ModItems.ASTRAL_AXE)
        ;

        getOrCreateTagBuilder(TagKey.of(RegistryKeys.ITEM, new Identifier("c", "shovels")))
                .add(ModItems.BAMBOO_SHOVEL)
                .add(ModItems.HELIORITE_SHOVEL)
                .add(ModItems.ENDURIUM_SHOVEL)
                .add(ModItems.JURASSOLINE_SHOVEL)
                .add(ModItems.PALLADIUM_SHOVEL)
                .add(ModItems.CINNABAR_SHOVEL)
                .add(ModItems.NEBULAR_SHOVEL)
                .add(ModItems.MITHRIL_SHOVEL)
                .add(ModItems.MITHRIL_COMMAND_BLOCK_SHOVEL)
                .add(ModItems.ASTRAL_SHOVEL)
        ;

        getOrCreateTagBuilder(TagKey.of(RegistryKeys.ITEM, new Identifier("c", "hoes")))
                .add(ModItems.BAMBOO_HOE)
                .add(ModItems.HELIORITE_HOE)
                .add(ModItems.ENDURIUM_HOE)
                .add(ModItems.PALLADIUM_HOE)
                .add(ModItems.JURASSOLINE_HOE)
                .add(ModItems.CINNABAR_HOE)
                .add(ModItems.NEBULAR_HOE)
                .add(ModItems.MITHRIL_HOE)
                .add(ModItems.MITHRIL_COMMAND_BLOCK_HOE)
                .add(ModItems.ASTRAL_HOE)
        ;

        getOrCreateTagBuilder(TagKey.of(RegistryKeys.ITEM, new Identifier("c", "quartz_blocks")))
                .add(Items.QUARTZ_BLOCK)
                .add(Items.SMOOTH_QUARTZ)
        ;

        getOrCreateTagBuilder(TagKey.of(RegistryKeys.ITEM, new Identifier("c", "colorless_glass_panes")))
                .add(Items.GLASS_PANE)
        ;

        getOrCreateTagBuilder(TagKey.of(RegistryKeys.ITEM, new Identifier("c", "colorless_glass")))
                .add(Items.GLASS)
        ;

        getOrCreateTagBuilder(TagKey.of(RegistryKeys.ITEM, new Identifier("c", "bones")))
                .add(ModItems.FOSSILIZED_BONE)
        ;

        getOrCreateTagBuilder(TagKey.of(RegistryKeys.ITEM, new Identifier("c", "seeds")))
                .add(ModItems.STRAWBERRY_SEEDS)
                .add(ModItems.ORANGE_SEEDS)
                .add(ModItems.PIPE_WEED_SEEDS)
        ;

        getOrCreateTagBuilder(ItemTags.TRIM_MATERIALS)
                .add(ModItems.HELIORITE_INGOT)
                .add(ModItems.ENDURIUM_INGOT)
                .add(ModItems.PALLADIUM_INGOT)
                .add(ModItems.JURASSOLINE_INGOT)
                .add(ModItems.CINNABAR_INGOT)
                .add(ModItems.NEBULAR_INGOT)
                .add(ModItems.MITHRIL_INGOT)
        ;

        getOrCreateTagBuilder(ItemTags.VILLAGER_PLANTABLE_SEEDS)
                .add(ModItems.STRAWBERRY_SEEDS)
                .add(ModItems.ORANGE_SEEDS)
                .add(ModItems.PIPE_WEED_SEEDS)
        ;

        getOrCreateTagBuilder(TagKey.of(RegistryKeys.ITEM, new Identifier("c", "flour")))
                .add(ModItems.FLOUR)
        ;

        getOrCreateTagBuilder(TagKey.of(RegistryKeys.ITEM, new Identifier("c", "eggs")))
                .add(Items.EGG)
        ;

        getOrCreateTagBuilder(TagKey.of(RegistryKeys.ITEM, new Identifier("c", "sugar")))
                .add(Items.SUGAR)
        ;

        getOrCreateTagBuilder(TagKey.of(RegistryKeys.ITEM, new Identifier("c", "milk")))
                .add(Items.MILK_BUCKET)
                .add(ModBlocks.WOODEN_MILK_STEIN.asItem())
        ;

        getOrCreateTagBuilder(TagKey.of(RegistryKeys.ITEM, new Identifier("c", "sulfur_ores")))
                .add(ModBlocks.RED_DEEPSLATE_SULFUR_ORE.asItem())
                .add(ModBlocks.NETHER_SULFUR_ORE.asItem())
        ;

        getOrCreateTagBuilder(TagKey.of(RegistryKeys.ITEM, new Identifier("c", "coal_ores")))
                .add(ModBlocks.MOON_ROCK_COAL_ORE.asItem())
        ;

        getOrCreateTagBuilder(TagKey.of(RegistryKeys.ITEM, new Identifier("c", "copper_ores")))
                .add(ModBlocks.RED_DEEPSLATE_COPPER_ORE.asItem())
        ;

        getOrCreateTagBuilder(TagKey.of(RegistryKeys.ITEM, new Identifier("c", "iron_ores")))
                .add(ModBlocks.RED_DEEPSLATE_IRON_ORE.asItem())
                .add(ModBlocks.MOON_ROCK_IRON_ORE.asItem())
        ;

        getOrCreateTagBuilder(TagKey.of(RegistryKeys.ITEM, new Identifier("c", "gold_ores")))
                .add(ModBlocks.RED_DEEPSLATE_GOLD_ORE.asItem())
        ;

        getOrCreateTagBuilder(TagKey.of(RegistryKeys.ITEM, new Identifier("c", "lapis_ores")))
                .add(ModBlocks.RED_DEEPSLATE_LAPIS_ORE.asItem())
        ;

        getOrCreateTagBuilder(TagKey.of(RegistryKeys.ITEM, new Identifier("c", "redstone_ores")))
                .add(ModBlocks.RED_DEEPSLATE_REDSTONE_ORE.asItem())
        ;

        getOrCreateTagBuilder(TagKey.of(RegistryKeys.ITEM, new Identifier("c", "diamond_ores")))
                .add(ModBlocks.RED_DEEPSLATE_DIAMOND_ORE.asItem())
        ;

        getOrCreateTagBuilder(TagKey.of(RegistryKeys.ITEM, new Identifier("c", "emerald_ores")))
                .add(ModBlocks.RED_DEEPSLATE_EMERALD_ORE.asItem())
        ;

        getOrCreateTagBuilder(TagKey.of(RegistryKeys.ITEM, new Identifier("c", "uranium_ores")))
                .add(ModBlocks.DEEPSLATE_URANIUM_ORE.asItem())
                .add(ModBlocks.RED_DEEPSLATE_URANIUM_ORE.asItem())
        ;

        getOrCreateTagBuilder(TagKey.of(RegistryKeys.ITEM, new Identifier("c", "palladium_ores")))
                .add(ModBlocks.DEEPSLATE_PALLADIUM_ORE.asItem())
                .add(ModBlocks.RED_DEEPSLATE_PALLADIUM_ORE.asItem())
        ;

        getOrCreateTagBuilder(TagKey.of(RegistryKeys.ITEM, new Identifier("c", "cinnabar_ores")))
                .add(ModBlocks.DEEPSLATE_CINNABAR_ORE.asItem())
                .add(ModBlocks.RED_DEEPSLATE_CINNABAR_ORE.asItem())
        ;

        getOrCreateTagBuilder(TagKey.of(RegistryKeys.ITEM, new Identifier("c", "copper_nuggets")))
                .add(ModItems.COPPER_NUGGET)
        ;

        getOrCreateTagBuilder(TagKey.of(RegistryKeys.ITEM, new Identifier("c", "lead_raw_materials")))
                .add(ModItems.RAW_LEAD)
        ;

        getOrCreateTagBuilder(TagKey.of(RegistryKeys.ITEM, new Identifier("c", "lead_nuggets")))
                .add(ModItems.LEAD_NUGGET)
        ;

        getOrCreateTagBuilder(TagKey.of(RegistryKeys.ITEM, new Identifier("c", "lead_ingots")))
                .add(ModItems.LEAD_INGOT)
        ;

        getOrCreateTagBuilder(TagKey.of(RegistryKeys.ITEM, new Identifier("c", "raw_lead_blocks")))
                .add(ModBlocks.RAW_LEAD_BLOCK.asItem())
        ;

        getOrCreateTagBuilder(TagKey.of(RegistryKeys.ITEM, new Identifier("c", "lead_blocks")))
                .add(ModBlocks.LEAD_BLOCK.asItem())
        ;

        getOrCreateTagBuilder(TagKey.of(RegistryKeys.ITEM, new Identifier("c", "lead_ores")))
                .add(ModBlocks.LEAD_ORE.asItem())
                .add(ModBlocks.RED_DEEPSLATE_LEAD_ORE.asItem())
        ;

        getOrCreateTagBuilder(TagKey.of(RegistryKeys.ITEM, new Identifier("c", "sulfur_dusts")))
                .add(ModItems.SULFUR)
        ;

        getOrCreateTagBuilder(TagKey.of(RegistryKeys.ITEM, new Identifier("c", "raw_uranium")))
                .add(ModItems.URANIUM)
        ;

        getOrCreateTagBuilder(TagKey.of(RegistryKeys.ITEM, new Identifier("forge", "raw_materials/uranium")))
                .add(ModItems.URANIUM)
        ;

        getOrCreateTagBuilder(TagKey.of(RegistryKeys.ITEM, new Identifier("c", "uranium_nuggets")))
                .add(ModItems.URANIUM_NUGGET)
        ;

        getOrCreateTagBuilder(TagKey.of(RegistryKeys.ITEM, new Identifier("c", "uranium_ingots")))
                .add(ModItems.URANIUM_INGOT)
        ;

        getOrCreateTagBuilder(TagKey.of(RegistryKeys.ITEM, new Identifier("c", "raw_uranium_blocks")))
                .add(ModBlocks.RAW_URANIUM_BLOCK.asItem())
        ;

        getOrCreateTagBuilder(TagKey.of(RegistryKeys.ITEM, new Identifier("c", "uranium_blocks")))
                .add(ModBlocks.URANIUM_BLOCK.asItem())
        ;

        getOrCreateTagBuilder(TagKey.of(RegistryKeys.ITEM, new Identifier("c", "wooden_rods")))
                .add(Items.STICK)
        ;

        getOrCreateTagBuilder(TagKey.of(RegistryKeys.ITEM, new Identifier("c", "copper_ingots")))
                .add(Items.COPPER_INGOT)
        ;

        getOrCreateTagBuilder(TagKey.of(RegistryKeys.ITEM, new Identifier("c", "iron_nuggets")))
                .add(Items.IRON_NUGGET)
        ;

        getOrCreateTagBuilder(TagKey.of(RegistryKeys.ITEM, new Identifier("c", "iron_ingots")))
                .add(Items.IRON_INGOT)
        ;

        getOrCreateTagBuilder(TagKey.of(RegistryKeys.ITEM, new Identifier("c", "gold_nuggets")))
                .add(Items.GOLD_NUGGET)
        ;

        getOrCreateTagBuilder(TagKey.of(RegistryKeys.ITEM, new Identifier("c", "gold_ingots")))
                .add(Items.GOLD_INGOT)
        ;

        getOrCreateTagBuilder(TagKey.of(RegistryKeys.ITEM, new Identifier("c", "steel_raw_materials")))
                .add(ModItems.STEEL_INGREDIENTS)
        ;

        getOrCreateTagBuilder(TagKey.of(RegistryKeys.ITEM, new Identifier("c", "steel_nuggets")))
                .add(ModItems.STEEL_NUGGET)
        ;

        getOrCreateTagBuilder(TagKey.of(RegistryKeys.ITEM, new Identifier("c", "steel_ingots")))
                .add(ModItems.STEEL_INGOT)
        ;

        getOrCreateTagBuilder(TagKey.of(RegistryKeys.ITEM, new Identifier("c", "steel_rods")))
                .add(ModItems.STEEL_ROD)
        ;

        getOrCreateTagBuilder(TagKey.of(RegistryKeys.ITEM, new Identifier("c", "steel_blocks")))
                .add(ModBlocks.STEEL_BLOCK.asItem())
        ;

        getOrCreateTagBuilder(TagKey.of(RegistryKeys.ITEM, new Identifier("c", "mithril_raw_materials")))
                .add(ModItems.RAW_MITHRIL)
        ;

        getOrCreateTagBuilder(TagKey.of(RegistryKeys.ITEM, new Identifier("c", "mithril_nuggets")))
                .add(ModItems.MITHRIL_NUGGET)
        ;

        getOrCreateTagBuilder(TagKey.of(RegistryKeys.ITEM, new Identifier("c", "mithril_ingots")))
                .add(ModItems.MITHRIL_INGOT)
        ;

        getOrCreateTagBuilder(TagKey.of(RegistryKeys.ITEM, new Identifier("c", "raw_mithril_blocks")))
                .add(ModBlocks.RAW_MITHRIL_BLOCK.asItem())
        ;

        getOrCreateTagBuilder(TagKey.of(RegistryKeys.ITEM, new Identifier("c", "mithril_blocks")))
                .add(ModBlocks.MITHRIL_BLOCK.asItem())
        ;

        getOrCreateTagBuilder(TagKey.of(RegistryKeys.ITEM, new Identifier("c", "oil_buckets")))
                .add(ModFluids.OIL_BUCKET)
        ;

        getOrCreateTagBuilder(TagKey.of(RegistryKeys.ITEM, new Identifier("c", "fossils")))
                .add(ModItems.FOSSILIZED_BONE)
                .add(ModItems.PLANT_FOSSIL)
        ;

        getOrCreateTagBuilder(TagKey.of(RegistryKeys.ITEM, new Identifier("c", "shears")))
                .add(ModItems.COPPER_SICKLE)
                .add(ModItems.GOLDEN_SICKLE)
                .add(ModItems.STEEL_SICKLE)
                .add(ModItems.NETHERITE_SICKLE)
                .add(ModItems.HELIORITE_SICKLE)
                .add(ModItems.ENDURIUM_SICKLE)
                .add(ModItems.PALLADIUM_SICKLE)
                .add(ModItems.JURASSOLINE_SICKLE)
                .add(ModItems.CINNABAR_SICKLE)
                .add(ModItems.NEBULAR_SICKLE)
                .add(ModItems.MITHRIL_SICKLE)
                .add(ModItems.ASTRAL_SICKLE)
        ;

        getOrCreateTagBuilder(TagKey.of(RegistryKeys.ITEM, new Identifier("c", "foods")))
                .add(ModBlocks.WOODEN_WATER_STEIN.asItem())
                .add(ModBlocks.WOODEN_MILK_STEIN.asItem())
                .add(ModBlocks.WOODEN_BEER_STEIN.asItem())
                .add(ModBlocks.WOODEN_APPLE_JUICE_STEIN.asItem())
                .add(ModBlocks.WOODEN_ORANGE_JUICE_STEIN.asItem())
                .add(ModBlocks.WOODEN_STRAWBERRY_MILKSHAKE_STEIN.asItem())
                .add(ModBlocks.WOODEN_ORANGE_MILKSHAKE_STEIN.asItem())
                .add(ModBlocks.WOODEN_BLUE_BERRY_MILKSHAKE_STEIN.asItem())
                .add(ModBlocks.WOODEN_TORCHBERRY_MILKSHAKE_STEIN.asItem())
                .add(ModBlocks.WOODEN_CHOCOLATE_MILKSHAKE_STEIN.asItem())
                .add(ModBlocks.HOT_WOODEN_MILK_STEIN.asItem())
                .add(ModBlocks.WOODEN_HOT_CHOCOLATE_STEIN.asItem())
                .add(ModItems.STRAWBERRY)
                .add(ModItems.CHOCOLATE_STRAWBERRY)
                .add(ModItems.ORANGE)
                .add(ModItems.HEMP_COOKIE)
                .add(ModItems.TOAST)
                .add(ModItems.STRAWBERRY_JAM_TOAST)
                .add(ModItems.ORANGE_MARMELADE_TOAST)
                .add(ModItems.SWEET_BERRY_JAM_TOAST)
                .add(ModItems.GLOW_BERRY_JAM_TOAST)
                .add(ModItems.BLUE_BERRY_JAM_TOAST)
                .add(ModItems.ANCIENT_SOULBERRY_JAM_TOAST)
                .add(ModItems.MAULTASCHE)
                .add(ModItems.MAULTASCHEN_BROTH)
                .add(ModItems.RAW_SCHNITZEL)
                .add(ModItems.SCHNITZEL)
                .add(ModItems.CALAMARI)
                .add(ModItems.FRIED_CALAMARI)
                .add(ModItems.DINOSAUR_MEAT)
                .add(ModItems.COOKED_DINOSAUR_MEAT)
                .add(ModItems.CURED_MEAT)
                .add(ModItems.COOKED_MEAT)
                .add(ModItems.PORRIDGE)
        ;
    }
}
