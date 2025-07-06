package net.tlotd.item;

import com.terraformersmc.terraform.boat.api.item.TerraformBoatItemHelper;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.*;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;
import net.minecraft.util.math.Direction;
import net.tlotd.TLOTD;
import net.tlotd.banner.ModBanners;
import net.tlotd.block.ModBlocks;
import net.tlotd.entity.ModBoats;
import net.tlotd.entity.ModEntities;
import net.tlotd.item.compat.*;
import net.tlotd.item.compat.biomancy.BiologicalCircuitBoardItem;
import net.tlotd.item.compat.create.SequencedAssemblyItem;
import net.tlotd.item.compat.spore.CalamitySpawnItem;
import net.tlotd.item.custom.*;
import net.tlotd.sound.ModSounds;

import java.util.List;

public class ModItems {
    static Identifier emptySlotPickaxeTexture = new Identifier("item/empty_slot_pickaxe");
    static Identifier emptySlotPickaxeHeadTexture = new Identifier(TLOTD.MOD_ID,"item/empty_slot_pickaxe_head");

    public static final Item SULFUR_TORCH = registerItem("sulfur_torch", new VerticallyAttachableBlockItem(ModBlocks.SULFUR_TORCH, ModBlocks.SULFUR_WALL_TORCH, new FabricItemSettings(), Direction.DOWN));

    public static final Item OAK_BARK = registerItem("oak_bark", new Item(new FabricItemSettings()));
    public static final Item SPRUCE_BARK = registerItem("spruce_bark", new Item(new FabricItemSettings()));
    public static final Item BIRCH_BARK = registerItem("birch_bark", new Item(new FabricItemSettings()));
    public static final Item JUNGLE_BARK = registerItem("jungle_bark", new Item(new FabricItemSettings()));
    public static final Item ACACIA_BARK = registerItem("acacia_bark", new Item(new FabricItemSettings()));
    public static final Item DARK_OAK_BARK = registerItem("dark_oak_bark", new Item(new FabricItemSettings()));
    public static final Item MANGROVE_BARK = registerItem("mangrove_bark", new Item(new FabricItemSettings()));
    public static final Item CHERRY_BARK = registerItem("cherry_bark", new Item(new FabricItemSettings()));
    public static final Item GINKGO_BARK = registerItem("ginkgo_bark", new Item(new FabricItemSettings()));

    public static final Item SKYROOT_BARK = registerItem("skyroot_bark", new CompatItem(new FabricItemSettings(),"aet"));

    public static final Item PEWEN_BARK = registerItem("pewen_bark", new CompatItem(new FabricItemSettings(),"axc"));
    public static final Item THORNWOOD_BARK = registerItem("thornwood_bark", new CompatItem(new FabricItemSettings(),"axc"));

    public static final Item FIR_BARK = registerItem("fir_bark", new CompatItem(new FabricItemSettings(),"bop"));
    public static final Item REDWOOD_BARK = registerItem("redwood_bark", new CompatItem(new FabricItemSettings(),"bop"));
    public static final Item MAHOGANY_BARK = registerItem("mahogany_bark", new CompatItem(new FabricItemSettings(),"bop"));
    public static final Item JACARANCA_BARK = registerItem("jacaranda_bark", new CompatItem(new FabricItemSettings(),"bop"));
    public static final Item PALM_BARK = registerItem("palm_bark", new CompatItem(new FabricItemSettings(),"bop"));
    public static final Item WILLOW_BARK = registerItem("willow_bark", new CompatItem(new FabricItemSettings(),"bop"));
    public static final Item DEAD_BARK = registerItem("dead_bark", new CompatItem(new FabricItemSettings(),"bop"));
    public static final Item MAGIC_BARK = registerItem("magic_bark", new CompatItem(new FabricItemSettings(),"bop"));
    public static final Item UMBRAN_BARK = registerItem("umbran_bark", new CompatItem(new FabricItemSettings(),"bop"));
    public static final Item HELLBARK_BARK = registerItem("hellbark_bark", new CompatItem(new FabricItemSettings(),"bop"));

    public static final Item ASHEN_BARK = registerItem("ashen_bark", new CompatItem(new FabricItemSettings(),"qrk"));
    public static final Item AZALEA_BARK = registerItem("azalea_bark", new CompatItem(new FabricItemSettings(),"qrk"));
    public static final Item TRUMPET_BARK = registerItem("trumpet_bark", new CompatItem(new FabricItemSettings(),"qrk"));

    public static final Item RUBBERWOOD_BARK = registerItem("rubberwood_bark", new CompatItem(new FabricItemSettings(),"thr"));

    public static final Item TWILIGHT_OAK_BARK = registerItem("twilight_oak_bark", new CompatItem(new FabricItemSettings(),"tlf"));
    public static final Item CANOPY_TREE_BARK = registerItem("canopy_tree_bark", new CompatItem(new FabricItemSettings(),"tlf"));
    public static final Item TWILIGHT_MANGROVE_BARK = registerItem("twilight_mangrove_bark", new CompatItem(new FabricItemSettings(),"tlf"));
    public static final Item DARKWOOD_BARK = registerItem("darkwood_bark", new CompatItem(new FabricItemSettings(),"tlf"));
    public static final Item TIMEWOOD_BARK = registerItem("timewood_bark", new CompatItem(new FabricItemSettings(),"tlf"));
    public static final Item TRANSWOOD_BARK = registerItem("transwood_bark", new CompatItem(new FabricItemSettings(),"tlf"));
    public static final Item MINEWOOD_BARK = registerItem("minewood_bark", new CompatItem(new FabricItemSettings(),"tlf"));
    public static final Item SORTINGWOOD_BARK = registerItem("sortingwood_bark", new CompatItem(new FabricItemSettings(),"tlf"));

    public static final Item IRRADIATED_ICON = registerItem("irradiated_icon", new Item(new FabricItemSettings()));

    public static final Item APPLE_JUICE_BOTTLE = registerItem("apple_juice_bottle", new DrinkableBottle(new FabricItemSettings().food(ModFoodComponents.WOODEN_LIQUID_STEIN).maxCount(16).recipeRemainder(Items.GLASS_BOTTLE)));

    public static final Item CANNABIS_COOKIE = registerItem("cannabis_cookie", new CannabisCookieItem(new FabricItemSettings().food(ModFoodComponents.CANNABIS_COOKIE)));

    public static final Item TOAST = registerItem("toast", new Item(new FabricItemSettings().food(ModFoodComponents.TOAST)));

    public static final Item SWEET_BERRY_JAM_TOAST = registerItem("sweet_berry_jam_toast", new Item(new FabricItemSettings().food(ModFoodComponents.JAM_TOAST)));
    public static final Item GLOW_BERRY_JAM_TOAST = registerItem("glow_berry_jam_toast", new Item(new FabricItemSettings().food(ModFoodComponents.JAM_TOAST)));

    public static final Item STRAWBERRY_SEEDS = registerItem("strawberry_seeds", new AliasedBlockItem(ModBlocks.STRAWBERRY_BUSH, new FabricItemSettings()));
    public static final Item STRAWBERRY = registerItem("strawberry", new Item(new FabricItemSettings().food(ModFoodComponents.STRAWBERRY)));
    public static final Item CHOCOLATE_STRAWBERRY = registerItem("chocolate_strawberry", new Item(new FabricItemSettings().food(ModFoodComponents.CHOCOLATE_STRAWBERRY)));
    public static final Item STRAWBERRY_JAM_TOAST = registerItem("strawberry_jam_toast", new Item(new FabricItemSettings().food(ModFoodComponents.JAM_TOAST)));

    public static final Item ORANGE_SEEDS = registerItem("orange_seeds", new AliasedBlockItem(ModBlocks.ORANGE_TREE, new FabricItemSettings()));
    public static final Item ORANGE = registerItem("orange", new Item(new FabricItemSettings().food(ModFoodComponents.ORANGE)));
    public static final Item ORANGE_MARMELADE_TOAST = registerItem("orange_marmelade_toast", new Item(new FabricItemSettings().food(ModFoodComponents.JAM_TOAST)));
    public static final Item ORANGE_JUICE_BOTTLE = registerItem("orange_juice_bottle", new DrinkableBottle(new FabricItemSettings().food(ModFoodComponents.WOODEN_LIQUID_STEIN).maxCount(16).recipeRemainder(Items.GLASS_BOTTLE)));
    public static final Item SPEZI_BOTTLE = registerItem("spezi_bottle", new DrinkableBottle(new FabricItemSettings().food(ModFoodComponents.WOODEN_LIQUID_STEIN).maxCount(16).recipeRemainder(Items.GLASS_BOTTLE)));

    public static final Item CANNABIS_SEEDS = registerItem("cannabis_seeds", new AliasedBlockItem(ModBlocks.CANNABIS_PLANT, new FabricItemSettings()));
    public static final Item CANNABIS = registerItem("cannabis", new Item(new FabricItemSettings()));

    public static final Item BLUE_BERRY_JAM_TOAST = registerItem("blue_berry_jam_toast", new CompatItem(new FabricItemSettings().food(ModFoodComponents.JAM_TOAST),"aether"));
    public static final Item ANCIENT_SOULBERRY_JAM_TOAST = registerItem("ancient_soulberry_jam_toast", new CompatItem(new FabricItemSettings().food(ModFoodComponents.ANCIENT_SOULBERRY_JAM_TOAST),"atm"));

    public static final Item BLOOD_BOTTLE = registerItem("blood_bottle", new Item(new FabricItemSettings().recipeRemainder(Items.GLASS_BOTTLE).maxCount(1)));

    public static final Item STICK_EFFIGY = registerItem("stick_effigy", new Item(new FabricItemSettings()));
    public static final Item STICK_FIGURE = registerItem("stick_figure", new Item(new FabricItemSettings()));
    public static final Item EDELWEISS_PETALS = registerItem("edelweiss_petals", new Item(new FabricItemSettings()));
    public static final Item MISTLETOE = registerItem("mistletoe", new Item(new FabricItemSettings()));
    public static final Item CURED_MEAT = registerItem("cured_meat", new Item(new FabricItemSettings().food(ModFoodComponents.CURED_MEAT)));
    public static final Item COOKED_MEAT = registerItem("cooked_meat", new Item(new FabricItemSettings().food(ModFoodComponents.COOKED_MEAT)));

    public static final Item GUIDEBOOK = registerItem("guidebook", new GuidebookItem(new FabricItemSettings().rarity(Rarity.UNCOMMON).maxCount(1)));
    public static final Item SPELL_BOOK = registerItem("spell_book", new SpellBookItem(new FabricItemSettings().rarity(Rarity.EPIC).maxCount(1).fireproof()));
    public static final Item FORBIDDEN_SPELL_BOOK = registerItem("forbidden_spell_book", new ForbiddenSpellBookItem(new FabricItemSettings().rarity(Rarity.EPIC).maxCount(1).fireproof()));

    public static final Item PLANCHETTE = registerItem("planchette", new PlanchetteItem(new FabricItemSettings().maxCount(1)));

    public static final Item MUSIC_DISC_1 = registerItem("music_disc_1", new MusicDiscItem(1, ModSounds.RADIO_FREQUENCY_1, new FabricItemSettings().rarity(Rarity.RARE).maxCount(1),66));
    public static final Item MUSIC_DISC_2 = registerItem("music_disc_2", new MusicDiscItem(1, ModSounds.RADIO_FREQUENCY_2, new FabricItemSettings().rarity(Rarity.RARE).maxCount(1),227));
    public static final Item MUSIC_DISC_3 = registerItem("music_disc_3", new MusicDiscItem(1, ModSounds.RADIO_FREQUENCY_3, new FabricItemSettings().rarity(Rarity.RARE).maxCount(1),43));
    public static final Item MUSIC_DISC_4 = registerItem("music_disc_4", new MusicDiscItem(1, ModSounds.RADIO_FREQUENCY_4, new FabricItemSettings().rarity(Rarity.RARE).maxCount(1),131));


    public static final Item VHS_CASSETTE = registerItem("vhs_cassette", new VHSCassetteItem(new FabricItemSettings().rarity(Rarity.RARE).maxCount(1)));
    public static final Item VHS_CASSETTE_1 = registerItem("vhs_cassette_1", new VHSCassetteItem(new FabricItemSettings().rarity(Rarity.RARE).maxCount(1)));
    public static final Item VHS_CASSETTE_2 = registerItem("vhs_cassette_2", new VHSCassetteItem(new FabricItemSettings().rarity(Rarity.RARE).maxCount(1)));
    public static final Item VHS_CASSETTE_3 = registerItem("vhs_cassette_3", new VHSCassetteItem(new FabricItemSettings().rarity(Rarity.RARE).maxCount(1)));
    public static final Item VHS_CASSETTE_4 = registerItem("vhs_cassette_4", new VHSCassetteItem(new FabricItemSettings().rarity(Rarity.RARE).maxCount(1)));
    public static final Item VHS_CASSETTE_5 = registerItem("vhs_cassette_5", new VHSCassetteItem(new FabricItemSettings().rarity(Rarity.RARE).maxCount(1)));
    public static final Item VHS_CASSETTE_6 = registerItem("vhs_cassette_6", new VHSCassetteItem(new FabricItemSettings().rarity(Rarity.RARE).maxCount(1)));
    public static final Item VHS_CASSETTE_7 = registerItem("vhs_cassette_7", new VHSCassetteItem(new FabricItemSettings().rarity(Rarity.RARE).maxCount(1)));
    public static final Item VHS_CASSETTE_8 = registerItem("vhs_cassette_8", new VHSCassetteItem(new FabricItemSettings().rarity(Rarity.RARE).maxCount(1)));
    public static final Item VHS_CASSETTE_9 = registerItem("vhs_cassette_9", new VHSCassetteItem(new FabricItemSettings().rarity(Rarity.RARE).maxCount(1)));
    public static final Item VHS_CASSETTE_BROKEN = registerItem("vhs_cassette_broken", new VHSCassetteItem(new FabricItemSettings().rarity(Rarity.RARE).maxCount(1)));

    public static final Item GAME_CARTRIDGE = registerItem("game_cartridge", new GameCartridgeItem(new FabricItemSettings().rarity(Rarity.RARE).maxCount(1)));
    public static final Item GAME_CARTRIDGE_1 = registerItem("game_cartridge_1", new GameCartridgeItem(new FabricItemSettings().rarity(Rarity.RARE).maxCount(1)));
    public static final Item GAME_CARTRIDGE_2 = registerItem("game_cartridge_2", new GameCartridgeItem(new FabricItemSettings().rarity(Rarity.RARE).maxCount(1)));
    public static final Item GAME_CARTRIDGE_3 = registerItem("game_cartridge_3", new GameCartridgeItem(new FabricItemSettings().rarity(Rarity.RARE).maxCount(1)));

    public static final Item COPPER_WIRE = registerItem("copper_wire", new Item(new FabricItemSettings()));
    public static final Item GOLD_WIRE = registerItem("gold_wire", new Item(new FabricItemSettings()));
    public static final Item SUPERCONDUCTING_WIRE = registerItem("superconducting_wire", new Item(new FabricItemSettings().rarity(Rarity.UNCOMMON)));
    public static final Item CIRCUIT_BOARD = registerItem("circuit_board", new Item(new FabricItemSettings()));
    public static final Item ADVANCED_CIRCUIT_BOARD = registerItem("advanced_circuit_board", new Item(new FabricItemSettings()));
    public static final Item FUTURISTIC_CIRCUIT_BOARD = registerItem("futuristic_circuit_board", new FuturisticCircuitBoardItem(new FabricItemSettings().rarity(Rarity.UNCOMMON)));
    public static final Item BIOLOGICAL_CIRCUIT_BOARD = registerItem("biological_circuit_board", new BiologicalCircuitBoardItem(new FabricItemSettings()));
    public static final Item INTEGRATED_CIRCUIT = registerItem("integrated_circuit", new Item(new FabricItemSettings()));
    public static final Item CATHODE_RAY_TUBE = registerItem("cathode_ray_tube", new Item(new FabricItemSettings()));
    public static final Item LIQUID_CRYSTAL_DISPLAY_PANEL = registerItem("liquid_crystal_display_panel", new Item(new FabricItemSettings()));
    public static final Item HOLOGRAPHIC_PROJECTOR = registerItem("holographic_projector", new Item(new FabricItemSettings().rarity(Rarity.UNCOMMON)));

    public static final Item INCOMPLETE_COPPER_WIRE = registerItem("incomplete_copper_wire", new SequencedAssemblyItem(new FabricItemSettings().maxCount(1)));
    public static final Item INCOMPLETE_GOLD_WIRE = registerItem("incomplete_gold_wire", new SequencedAssemblyItem(new FabricItemSettings().maxCount(1)));
    public static final Item INCOMPLETE_INTEGRATED_CIRCUIT = registerItem("incomplete_integrated_circuit", new SequencedAssemblyItem(new FabricItemSettings().maxCount(1)));
    public static final Item INCOMPLETE_CIRCUIT_BOARD = registerItem("incomplete_circuit_board", new SequencedAssemblyItem(new FabricItemSettings().maxCount(1)));
    public static final Item INCOMPLETE_ADVANCED_CIRCUIT_BOARD = registerItem("incomplete_advanced_circuit_board", new SequencedAssemblyItem(new FabricItemSettings().maxCount(1)));
    public static final Item INCOMPLETE_FUTURISTIC_CIRCUIT_BOARD = registerItem("incomplete_futuristic_circuit_board", new SequencedAssemblyItem(new FabricItemSettings().maxCount(1).rarity(Rarity.UNCOMMON)));

    public static final Item INCOMPLETE_RADIO = registerItem("incomplete_radio", new SequencedAssemblyItem(new FabricItemSettings().maxCount(1)));
    public static final Item INCOMPLETE_TELEVISION = registerItem("incomplete_television", new SequencedAssemblyItem(new FabricItemSettings().maxCount(1)));
    public static final Item INCOMPLETE_VIDEOCASSETTE_RECORDER = registerItem("incomplete_videocassette_recorder", new SequencedAssemblyItem(new FabricItemSettings().maxCount(1)));
    public static final Item INCOMPLETE_SIGNAL_TRANSMITTER = registerItem("incomplete_signal_transmitter", new SequencedAssemblyItem(new FabricItemSettings().maxCount(1)));
    public static final Item INCOMPLETE_COMPUTER = registerItem("incomplete_computer", new SequencedAssemblyItem(new FabricItemSettings().maxCount(1)));
    public static final Item INCOMPLETE_KEYCARD_PROGRAMMER = registerItem("incomplete_keycard_programmer", new SequencedAssemblyItem(new FabricItemSettings().maxCount(1)));
    public static final Item INCOMPLETE_KEYCARD_READER = registerItem("incomplete_keycard_reader", new SequencedAssemblyItem(new FabricItemSettings().maxCount(1)));
    public static final Item INCOMPLETE_OXYGEN_COLLECTOR = registerItem("incomplete_oxygen_collector", new SequencedAssemblyItem(new FabricItemSettings().maxCount(1)));
    public static final Item INCOMPLETE_MITHRIL_MIRROR = registerItem("incomplete_mithril_mirror", new SequencedAssemblyItem(new FabricItemSettings().maxCount(1).rarity(Rarity.UNCOMMON)));

    public static final Item COPPER_SICKLE = registerItem("copper_sickle", new SickleItem(new FabricItemSettings().maxDamage(117)));
    public static final Item GOLDEN_SICKLE = registerItem("golden_sickle", new SickleItem(new FabricItemSettings().maxDamage(53)));
    public static final Item NETHERITE_SICKLE = registerItem("netherite_sickle", new SickleItem(new FabricItemSettings().maxDamage(2025).fireproof()));
    public static final Item NETHERITE_FORGING_HAMMER = registerItem("netherite_forging_hammer", new ForgingHammerItem(new FabricItemSettings().maxDamage(10).fireproof()));

    public static final Item KEYCARD = registerItem("keycard", new KeycardItem(new FabricItemSettings().maxCount(1)));

    public static final Item STEEL_INGREDIENTS = registerItem("steel_ingredients", new Item(new FabricItemSettings()));
    public static final Item STEEL_NUGGET = registerItem("steel_nugget", new Item(new FabricItemSettings()));
    public static final Item STEEL_INGOT = registerItem("steel_ingot", new Item(new FabricItemSettings()));
    public static final Item STEEL_ROD = registerItem("steel_rod", new Item(new FabricItemSettings()));
    public static final Item REINFORCED_TOOL_ROD = registerItem("reinforced_tool_rod", new Item(new FabricItemSettings()));

    public static final Item STEEL_SICKLE = registerItem("steel_sickle", new SickleItem(new FabricItemSettings().maxDamage(512)));

    public static final Item XEN_CRYSTAL = registerItem("xen_crystal", new Item(new FabricItemSettings().rarity(Rarity.UNCOMMON).fireproof()));
    public static final Item METEORITE_CHUNK = registerItem("meteorite_chunk", new Item(new FabricItemSettings().rarity(Rarity.UNCOMMON).fireproof()));
    public static final Item STAR_FRAGMENT = registerItem("star_fragment", new Item(new FabricItemSettings().rarity(Rarity.UNCOMMON).fireproof()));
    public static final Item LUNAR_CALLAINUS_LUMP = registerItem("lunar_callainus_lump", new Item(new FabricItemSettings().rarity(Rarity.UNCOMMON).fireproof()));
    public static final Item ALIEN_METAL = registerItem("alien_metal", new Item(new FabricItemSettings().rarity(Rarity.UNCOMMON).fireproof()));

    public static final Item FOSSILIZED_BONE = registerItem("fossilized_bone", new Item(new FabricItemSettings()));
    public static final Item PLANT_FOSSIL = registerItem("plant_fossil", new Item(new FabricItemSettings()));

    public static final Item FOSSIL_AND_STEEL = registerItem("fossil_and_steel", new Item(new FabricItemSettings().maxDamage(64)));

    public static final Item DRAGON_BANNER_PATTERN = registerItem("dragon_banner_pattern", new BannerPatternItem(ModBanners.DRAGON_PATTERN_ITEM, new FabricItemSettings().maxCount(1).rarity(Rarity.UNCOMMON)));
    public static final Item LOTR_BANNER_PATTERN = registerItem("lotr_banner_pattern", new BannerPatternItem(ModBanners.LOTR_PATTERN_ITEM, new FabricItemSettings().maxCount(1).rarity(Rarity.UNCOMMON)));

    public static final Item COPPER_NUGGET = registerItem("copper_nugget", new Item(new FabricItemSettings()));

    public static final Item SULFUR = registerItem("sulfur", new Item(new FabricItemSettings()));

    public static final Item RAW_LEAD = registerItem("raw_lead", new Item(new FabricItemSettings()));
    public static final Item LEAD_NUGGET = registerItem("lead_nugget", new Item(new FabricItemSettings()));
    public static final Item LEAD_INGOT = registerItem("lead_ingot", new Item(new FabricItemSettings()));

    public static final Item URANIUM = registerItem("uranium", new IrradiatedItem(new FabricItemSettings()));
    public static final Item URANIUM_NUGGET = registerItem("uranium_nugget", new IrradiatedItem(new FabricItemSettings()));
    public static final Item URANIUM_INGOT = registerItem("uranium_ingot", new IrradiatedItem(new FabricItemSettings()));

    public static final Item BAMBOO_SWORD = registerItem("bamboo_sword", new SwordItem(ModToolMaterial.BAMBOO, 3, -2.4f, new FabricItemSettings()));
    public static final Item BAMBOO_PICKAXE = registerItem("bamboo_pickaxe", new PickaxeItem(ModToolMaterial.BAMBOO, 1, -2.8f, new FabricItemSettings()));
    public static final Item BAMBOO_AXE = registerItem("bamboo_axe", new AxeItem(ModToolMaterial.BAMBOO, 6, -3.2f, new FabricItemSettings()));
    public static final Item BAMBOO_SHOVEL = registerItem("bamboo_shovel", new ShovelItem(ModToolMaterial.BAMBOO, 1.5f, -3.0f, new FabricItemSettings()));
    public static final Item BAMBOO_HOE = registerItem("bamboo_hoe", new HoeItem(ModToolMaterial.BAMBOO, 0, -3, new FabricItemSettings()));

    public static final Item HELIORITE_COMB = registerItem("heliorite_comb", new Item(new FabricItemSettings()));
    public static final Item HELIORITE_NUGGET = registerItem("heliorite_nugget", new Item(new FabricItemSettings()));
    public static final Item HELIORITE_INGOT = registerItem("heliorite_ingot", new Item(new FabricItemSettings()));

    public static final Item HELIORITE_UPGRADE_SMITHING_TEMPLATE = registerItem("heliorite_upgrade_smithing_template", new SmithingTemplateItem(Text.translatable("item.tlotd.heliorite_upgrade_smithing_template.tooltip_1").formatted(Formatting.BLUE),Text.translatable("item.tlotd.heliorite_upgrade_smithing_template.tooltip_2").formatted(Formatting.BLUE),Text.translatable("item.tlotd.heliorite_upgrade_smithing_template.tooltip").formatted(Formatting.GRAY),Text.translatable("item.tlotd.heliorite_upgrade_smithing_template.tooltip_3"),Text.translatable("item.tlotd.heliorite_upgrade_smithing_template.tooltip_4"), List.of(emptySlotPickaxeTexture), List.of(emptySlotPickaxeHeadTexture)));

    public static final Item HELIORITE_PICKAXE_HEAD = registerItem("heliorite_pickaxe_head", new Item(new FabricItemSettings().maxCount(1)));

    public static final Item HELIORITE_SWORD = registerItem("heliorite_sword", new SwordItem(ModToolMaterial.HELIORITE, 4, -2.4f, new FabricItemSettings()));
    public static final Item HELIORITE_PICKAXE = registerItem("heliorite_pickaxe", new UpgradedPickaxeItem(ModToolMaterial.HELIORITE, 2, -2.8f, new FabricItemSettings()));
    public static final Item HELIORITE_AXE = registerItem("heliorite_axe", new AxeItem(ModToolMaterial.HELIORITE, 6, -3.0f, new FabricItemSettings()));
    public static final Item HELIORITE_SHOVEL = registerItem("heliorite_shovel", new ShovelItem(ModToolMaterial.HELIORITE, 2.5f, -3.0f, new FabricItemSettings()));
    public static final Item HELIORITE_HOE = registerItem("heliorite_hoe", new HoeItem(ModToolMaterial.HELIORITE, -4, 0.0f, new FabricItemSettings()));

    public static final Item HELIORITE_SICKLE = registerItem("heliorite_sickle", new SickleItem(new FabricItemSettings().maxDamage(2319)));

    public static final Item HELIORITE_HELMET = registerItem("heliorite_helmet", new ArmorItem(ModArmorMaterial.HELIORITE, ArmorItem.Type.HELMET, new FabricItemSettings()));
    public static final Item HELIORITE_CHESTPLATE = registerItem("heliorite_chestplate", new ArmorItem(ModArmorMaterial.HELIORITE, ArmorItem.Type.CHESTPLATE, new FabricItemSettings()));
    public static final Item HELIORITE_LEGGINGS = registerItem("heliorite_leggings", new ArmorItem(ModArmorMaterial.HELIORITE, ArmorItem.Type.LEGGINGS, new FabricItemSettings()));
    public static final Item HELIORITE_BOOTS = registerItem("heliorite_boots", new ArmorItem(ModArmorMaterial.HELIORITE, ArmorItem.Type.BOOTS, new FabricItemSettings()));

    public static final Item ENDURIUM_CRYSTAL = registerItem("endurium_crystal", new Item(new FabricItemSettings()));
    public static final Item ENDURIUM_NUGGET = registerItem("endurium_nugget", new Item(new FabricItemSettings()));
    public static final Item ENDURIUM_INGOT = registerItem("endurium_ingot", new Item(new FabricItemSettings()));

    public static final Item ENDURIUM_UPGRADE_SMITHING_TEMPLATE = registerItem("endurium_upgrade_smithing_template", new SmithingTemplateItem(Text.translatable("item.tlotd.endurium_upgrade_smithing_template.tooltip_1").formatted(Formatting.BLUE),Text.translatable("item.tlotd.endurium_upgrade_smithing_template.tooltip_2").formatted(Formatting.BLUE),Text.translatable("item.tlotd.endurium_upgrade_smithing_template.tooltip").formatted(Formatting.GRAY),Text.translatable("item.tlotd.endurium_upgrade_smithing_template.tooltip_3"),Text.translatable("item.tlotd.endurium_upgrade_smithing_template.tooltip_4"), List.of(emptySlotPickaxeTexture), List.of(emptySlotPickaxeHeadTexture)));
    
    public static final Item ENDURIUM_PICKAXE_HEAD = registerItem("endurium_pickaxe_head", new Item(new FabricItemSettings().maxCount(1)));

    public static final Item ENDURIUM_SWORD = registerItem("endurium_sword", new SwordItem(ModToolMaterial.ENDURIUM, 4, -2.4f, new FabricItemSettings()));
    public static final Item ENDURIUM_PICKAXE = registerItem("endurium_pickaxe", new UpgradedPickaxeItem(ModToolMaterial.ENDURIUM, 2, -2.8f, new FabricItemSettings()));
    public static final Item ENDURIUM_AXE = registerItem("endurium_axe", new AxeItem(ModToolMaterial.ENDURIUM, 6, -3.0f, new FabricItemSettings()));
    public static final Item ENDURIUM_SHOVEL = registerItem("endurium_shovel", new ShovelItem(ModToolMaterial.ENDURIUM, 2.5f, -3.0f, new FabricItemSettings()));
    public static final Item ENDURIUM_HOE = registerItem("endurium_hoe", new HoeItem(ModToolMaterial.ENDURIUM, -4, 0.0f, new FabricItemSettings()));

    public static final Item ENDURIUM_SICKLE = registerItem("endurium_sickle", new SickleItem(new FabricItemSettings().maxDamage(2319)));

    public static final Item ENDURIUM_HELMET = registerItem("endurium_helmet", new ArmorItem(ModArmorMaterial.ENDURIUM, ArmorItem.Type.HELMET, new FabricItemSettings()));
    public static final Item ENDURIUM_CHESTPLATE = registerItem("endurium_chestplate", new ArmorItem(ModArmorMaterial.ENDURIUM, ArmorItem.Type.CHESTPLATE, new FabricItemSettings()));
    public static final Item ENDURIUM_LEGGINGS = registerItem("endurium_leggings", new ArmorItem(ModArmorMaterial.ENDURIUM, ArmorItem.Type.LEGGINGS, new FabricItemSettings()));
    public static final Item ENDURIUM_BOOTS = registerItem("endurium_boots", new ArmorItem(ModArmorMaterial.ENDURIUM, ArmorItem.Type.BOOTS, new FabricItemSettings()));

    public static final Item RAW_PALLADIUM = registerItem("raw_palladium", new Item(new FabricItemSettings()));
    public static final Item PALLADIUM_NUGGET = registerItem("palladium_nugget", new Item(new FabricItemSettings()));
    public static final Item PALLADIUM_INGOT = registerItem("palladium_ingot", new Item(new FabricItemSettings()));

    public static final Item PALLADIUM_SWORD = registerItem("palladium_sword", new SwordItem(ModToolMaterial.PALLADIUM, 4, -2.4f, new FabricItemSettings()));
    public static final Item PALLADIUM_PICKAXE = registerItem("palladium_pickaxe", new PickaxeItem(ModToolMaterial.PALLADIUM, 2, -2.8f, new FabricItemSettings()));
    public static final Item PALLADIUM_AXE = registerItem("palladium_axe", new AxeItem(ModToolMaterial.PALLADIUM, 6, -3.0f, new FabricItemSettings()));
    public static final Item PALLADIUM_SHOVEL = registerItem("palladium_shovel", new ShovelItem(ModToolMaterial.PALLADIUM, 2.5f, -3.0f, new FabricItemSettings()));
    public static final Item PALLADIUM_HOE = registerItem("palladium_hoe", new HoeItem(ModToolMaterial.PALLADIUM, -5, 0.0f, new FabricItemSettings()));

    public static final Item PALLADIUM_SICKLE = registerItem("palladium_sickle", new SickleItem(new FabricItemSettings().maxDamage(2612)));

    public static final Item PALLADIUM_HELMET = registerItem("palladium_helmet", new ArmorItem(ModArmorMaterial.PALLADIUM, ArmorItem.Type.HELMET, new FabricItemSettings()));
    public static final Item PALLADIUM_CHESTPLATE = registerItem("palladium_chestplate", new ArmorItem(ModArmorMaterial.PALLADIUM, ArmorItem.Type.CHESTPLATE, new FabricItemSettings()));
    public static final Item PALLADIUM_LEGGINGS = registerItem("palladium_leggings", new ArmorItem(ModArmorMaterial.PALLADIUM, ArmorItem.Type.LEGGINGS, new FabricItemSettings()));
    public static final Item PALLADIUM_BOOTS = registerItem("palladium_boots", new ArmorItem(ModArmorMaterial.PALLADIUM, ArmorItem.Type.BOOTS, new FabricItemSettings()));

    public static final Item JURASSOLINE_CRYSTAL = registerItem("jurassoline_crystal", new Item(new FabricItemSettings()));
    public static final Item JURASSOLINE_NUGGET = registerItem("jurassoline_nugget", new Item(new FabricItemSettings()));
    public static final Item JURASSOLINE_INGOT = registerItem("jurassoline_ingot", new Item(new FabricItemSettings()));

    public static final Item JURASSOLINE_SWORD = registerItem("jurassoline_sword", new SwordItem(ModToolMaterial.JURASSOLINE, 4, -2.4f, new FabricItemSettings()));
    public static final Item JURASSOLINE_PICKAXE = registerItem("jurassoline_pickaxe", new PickaxeItem(ModToolMaterial.JURASSOLINE, 2, -2.8f, new FabricItemSettings()));
    public static final Item JURASSOLINE_AXE = registerItem("jurassoline_axe", new AxeItem(ModToolMaterial.JURASSOLINE, 6, -3.0f, new FabricItemSettings()));
    public static final Item JURASSOLINE_SHOVEL = registerItem("jurassoline_shovel", new ShovelItem(ModToolMaterial.JURASSOLINE, 2.5f, -3.0f, new FabricItemSettings()));
    public static final Item JURASSOLINE_HOE = registerItem("jurassoline_hoe", new HoeItem(ModToolMaterial.JURASSOLINE, -5, 0.0f, new FabricItemSettings()));

    public static final Item JURASSOLINE_SICKLE = registerItem("jurassoline_sickle", new SickleItem(new FabricItemSettings().maxDamage(2612)));

    public static final Item JURASSOLINE_HELMET = registerItem("jurassoline_helmet", new ArmorItem(ModArmorMaterial.JURASSOLINE, ArmorItem.Type.HELMET, new FabricItemSettings()));
    public static final Item JURASSOLINE_CHESTPLATE = registerItem("jurassoline_chestplate", new ArmorItem(ModArmorMaterial.JURASSOLINE, ArmorItem.Type.CHESTPLATE, new FabricItemSettings()));
    public static final Item JURASSOLINE_LEGGINGS = registerItem("jurassoline_leggings", new ArmorItem(ModArmorMaterial.JURASSOLINE, ArmorItem.Type.LEGGINGS, new FabricItemSettings()));
    public static final Item JURASSOLINE_BOOTS = registerItem("jurassoline_boots", new ArmorItem(ModArmorMaterial.JURASSOLINE, ArmorItem.Type.BOOTS, new FabricItemSettings()));

    public static final Item CINNABAR_CRYSTAL = registerItem("cinnabar_crystal", new Item(new FabricItemSettings()));
    public static final Item CINNABAR_NUGGET = registerItem("cinnabar_nugget", new Item(new FabricItemSettings()));
    public static final Item CINNABAR_INGOT = registerItem("cinnabar_ingot", new Item(new FabricItemSettings()));

    public static final Item CINNABAR_SWORD = registerItem("cinnabar_sword", new SwordItem(ModToolMaterial.CINNABAR, 4, -2.4f, new FabricItemSettings()));
    public static final Item CINNABAR_PICKAXE = registerItem("cinnabar_pickaxe", new ExtractionPickaxeItem(ModToolMaterial.CINNABAR, 2, -2.8f, new FabricItemSettings()));
    public static final Item CINNABAR_AXE = registerItem("cinnabar_axe", new AxeItem(ModToolMaterial.CINNABAR, 6, -3.0f, new FabricItemSettings()));
    public static final Item CINNABAR_SHOVEL = registerItem("cinnabar_shovel", new ShovelItem(ModToolMaterial.CINNABAR, 2.5f, -3.0f, new FabricItemSettings()));
    public static final Item CINNABAR_HOE = registerItem("cinnabar_hoe", new HoeItem(ModToolMaterial.CINNABAR, -6, 0.0f, new FabricItemSettings()));

    public static final Item CINNABAR_SICKLE = registerItem("cinnabar_sickle", new SickleItem(new FabricItemSettings().maxDamage(2612)));

    public static final Item CINNABAR_HELMET = registerItem("cinnabar_helmet", new ArmorItem(ModArmorMaterial.CINNABAR, ArmorItem.Type.HELMET, new FabricItemSettings()));
    public static final Item CINNABAR_CHESTPLATE = registerItem("cinnabar_chestplate", new ArmorItem(ModArmorMaterial.CINNABAR, ArmorItem.Type.CHESTPLATE, new FabricItemSettings()));
    public static final Item CINNABAR_LEGGINGS = registerItem("cinnabar_leggings", new ArmorItem(ModArmorMaterial.CINNABAR, ArmorItem.Type.LEGGINGS, new FabricItemSettings()));
    public static final Item CINNABAR_BOOTS = registerItem("cinnabar_boots", new ArmorItem(ModArmorMaterial.CINNABAR, ArmorItem.Type.BOOTS, new FabricItemSettings()));

    public static final Item NEBULAR_CRYSTAL = registerItem("nebular_crystal", new Item(new FabricItemSettings()));
    public static final Item NEBULAR_NUGGET = registerItem("nebular_nugget", new Item(new FabricItemSettings()));
    public static final Item NEBULAR_INGOT = registerItem("nebular_ingot", new Item(new FabricItemSettings()));

    public static final Item NEBULAR_SWORD = registerItem("nebular_sword", new SwordItem(ModToolMaterial.NEBULAR, 4, -2.4f, new FabricItemSettings()));
    public static final Item NEBULAR_PICKAXE = registerItem("nebular_pickaxe", new ExtractionPickaxeItem(ModToolMaterial.NEBULAR, 2, -2.8f, new FabricItemSettings()));
    public static final Item NEBULAR_AXE = registerItem("nebular_axe", new AxeItem(ModToolMaterial.NEBULAR, 6, -3.0f, new FabricItemSettings()));
    public static final Item NEBULAR_SHOVEL = registerItem("nebular_shovel", new ShovelItem(ModToolMaterial.NEBULAR, 2.5f, -3.0f, new FabricItemSettings()));
    public static final Item NEBULAR_HOE = registerItem("nebular_hoe", new HoeItem(ModToolMaterial.NEBULAR, -6, 0.0f, new FabricItemSettings()));

    public static final Item NEBULAR_SICKLE = registerItem("nebular_sickle", new SickleItem(new FabricItemSettings().maxDamage(2612)));

    public static final Item NEBULAR_HELMET = registerItem("nebular_helmet", new ArmorItem(ModArmorMaterial.NEBULAR, ArmorItem.Type.HELMET, new FabricItemSettings()));
    public static final Item NEBULAR_CHESTPLATE = registerItem("nebular_chestplate", new ArmorItem(ModArmorMaterial.NEBULAR, ArmorItem.Type.CHESTPLATE, new FabricItemSettings()));
    public static final Item NEBULAR_LEGGINGS = registerItem("nebular_leggings", new ArmorItem(ModArmorMaterial.NEBULAR, ArmorItem.Type.LEGGINGS, new FabricItemSettings()));
    public static final Item NEBULAR_BOOTS = registerItem("nebular_boots", new ArmorItem(ModArmorMaterial.NEBULAR, ArmorItem.Type.BOOTS, new FabricItemSettings()));

    public static final Item FANCY_TOOL_ROD = registerItem("fancy_tool_rod", new Item(new FabricItemSettings()));

    public static final Item RAW_MITHRIL = registerItem("raw_mithril", new Item(new FabricItemSettings().rarity(Rarity.UNCOMMON).fireproof()));
    public static final Item MITHRIL_NUGGET = registerItem("mithril_nugget", new Item(new FabricItemSettings().rarity(Rarity.UNCOMMON).fireproof()));
    public static final Item MITHRIL_INGOT = registerItem("mithril_ingot", new Item(new FabricItemSettings().rarity(Rarity.UNCOMMON).fireproof()));

    public static final Item MITHRIL_SWORD = registerItem("mithril_sword", new SwordItem(ModToolMaterial.MITHRIL, 4, -2.4f, new FabricItemSettings().rarity(Rarity.UNCOMMON).fireproof()));
    public static final Item MITHRIL_PICKAXE = registerItem("mithril_pickaxe", new ExtractionPickaxeItem(ModToolMaterial.MITHRIL, 2, -2.8f, new FabricItemSettings().rarity(Rarity.UNCOMMON).fireproof()));
    public static final Item MITHRIL_AXE = registerItem("mithril_axe", new AxeItem(ModToolMaterial.MITHRIL, 6, -3.0f, new FabricItemSettings().rarity(Rarity.UNCOMMON).fireproof()));
    public static final Item MITHRIL_SHOVEL = registerItem("mithril_shovel", new ShovelItem(ModToolMaterial.MITHRIL, 2.5f, -3.0f, new FabricItemSettings().rarity(Rarity.UNCOMMON).fireproof()));
    public static final Item MITHRIL_HOE = registerItem("mithril_hoe", new HoeItem(ModToolMaterial.MITHRIL, -7, 0.0f, new FabricItemSettings().rarity(Rarity.UNCOMMON).fireproof()));

    public static final Item MITHRIL_SICKLE = registerItem("mithril_sickle", new SickleItem(new FabricItemSettings().maxDamage(3077).rarity(Rarity.UNCOMMON).fireproof()));
    public static final Item MITHRIL_FORGING_HAMMER = registerItem("mithril_forging_hammer", new ForgingHammerItem(new FabricItemSettings().rarity(Rarity.UNCOMMON).maxDamage(30).fireproof()));
    public static final Item MITHRIL_STAR_CATCHER = registerItem("mithril_star_catcher", new StarCatcherItem(new FabricItemSettings().rarity(Rarity.UNCOMMON).maxDamage(30).fireproof()));
    public static final Item SOUL_MIRROR = registerItem("soul_mirror", new Item(new FabricItemSettings().rarity(Rarity.RARE).maxCount(1)));
    public static final Item MITHRIL_MIRROR = registerItem("mithril_mirror", new MithrilMirrorItem(new FabricItemSettings().rarity(Rarity.UNCOMMON).maxCount(1)));
    public static final Item FOGGY_MITHRIL_MIRROR = registerItem("foggy_mithril_mirror", new Item(new FabricItemSettings().rarity(Rarity.UNCOMMON).maxCount(1)));

    public static final Item MITHRIL_HELMET = registerItem("mithril_helmet", new ArmorItem(ModArmorMaterial.MITHRIL, ArmorItem.Type.HELMET, new FabricItemSettings().rarity(Rarity.UNCOMMON).fireproof()));
    public static final Item MITHRIL_CHESTPLATE = registerItem("mithril_chestplate", new ArmorItem(ModArmorMaterial.MITHRIL, ArmorItem.Type.CHESTPLATE, new FabricItemSettings().rarity(Rarity.UNCOMMON).fireproof()));
    public static final Item MITHRIL_LEGGINGS = registerItem("mithril_leggings", new ArmorItem(ModArmorMaterial.MITHRIL, ArmorItem.Type.LEGGINGS, new FabricItemSettings().rarity(Rarity.UNCOMMON).fireproof()));
    public static final Item MITHRIL_BOOTS = registerItem("mithril_boots", new ArmorItem(ModArmorMaterial.MITHRIL, ArmorItem.Type.BOOTS, new FabricItemSettings().rarity(Rarity.UNCOMMON).fireproof()));

    public static final Item MITHRIL_HORSE_ARMOR = registerItem("mithril_horse_armor", new HorseArmorItem(20,"mithril", new FabricItemSettings().maxCount(1).rarity(Rarity.UNCOMMON).fireproof()));

    public static final Item MITHRIL_COMMAND_BLOCK_SWORD = registerItem("mithril_command_block_sword", new CompatSwordItem(ModToolMaterial.ELDRITCH, 4, -2.4f, new FabricItemSettings().rarity(Rarity.EPIC).fireproof(),"wsm"));
    public static final Item MITHRIL_COMMAND_BLOCK_PICKAXE = registerItem("mithril_command_block_pickaxe", new CompatPickaxeItem(ModToolMaterial.ELDRITCH, 2, -2.8f, new FabricItemSettings().rarity(Rarity.EPIC).fireproof(),"wsm"));
    public static final Item MITHRIL_COMMAND_BLOCK_AXE = registerItem("mithril_command_block_axe", new CompatAxeItem(ModToolMaterial.ELDRITCH, 6, -3.0f, new FabricItemSettings().rarity(Rarity.EPIC).fireproof(),"wsm"));
    public static final Item MITHRIL_COMMAND_BLOCK_SHOVEL = registerItem("mithril_command_block_shovel", new CompatShovelItem(ModToolMaterial.ELDRITCH, 2.5f, -3.0f, new FabricItemSettings().rarity(Rarity.EPIC).fireproof(),"wsm"));
    public static final Item MITHRIL_COMMAND_BLOCK_HOE = registerItem("mithril_command_block_hoe", new CompatHoeItem(ModToolMaterial.ELDRITCH, -7, 0.0f, new FabricItemSettings().rarity(Rarity.EPIC).fireproof(),"wsm"));

    public static final Item NARSIL_HANDLE = registerItem("narsil_handle", new SwordItem(ModToolMaterial.MITHRIL, 0, -2.4f, new FabricItemSettings().rarity(Rarity.UNCOMMON).fireproof()));
    public static final Item ANDURIL = registerItem("anduril", new AndurilItem(ModToolMaterial.MITHRIL, 7, -2.4f, new FabricItemSettings().rarity(Rarity.UNCOMMON).fireproof()));

    public static final Item GONDORIAN_SHIELD = registerItem("gondorian_shield", new ShieldItem(new FabricItemSettings().maxDamage(2612).rarity(Rarity.UNCOMMON).fireproof()));
    public static final Item GONDORIAN_TOWER_SHIELD = registerItem("gondorian_tower_shield", new ShieldItem(new FabricItemSettings().maxDamage(2612).rarity(Rarity.UNCOMMON).fireproof()));
    public static final Item GONDORIAN_KINGS_GUARD_TOWER_SHIELD = registerItem("gondorian_kings_guard_tower_shield", new ShieldItem(new FabricItemSettings().maxDamage(2612).rarity(Rarity.UNCOMMON).fireproof()));
    public static final Item GONDORIAN_KNIGHT_SHIELD = registerItem("gondorian_knight_shield", new ShieldItem(new FabricItemSettings().maxDamage(3418).rarity(Rarity.UNCOMMON).fireproof()));
    public static final Item GONDORIAN_ORNAMENTED_KNIGHT_SHIELD = registerItem("gondorian_ornamented_knight_shield", new ShieldItem(new FabricItemSettings().maxDamage(4096).rarity(Rarity.UNCOMMON).fireproof()));

    public static final Item ASTRAL_NUGGET = registerItem("astral_nugget", new Item(new FabricItemSettings().rarity(Rarity.EPIC).fireproof()));
    public static final Item ASTRAL_INGOT = registerItem("astral_ingot", new Item(new FabricItemSettings().rarity(Rarity.EPIC).fireproof()));

    public static final Item ASTRAL_SWORD = registerItem("astral_sword", new SwordItem(ModToolMaterial.ASTRAL, 4, -2.4f, new FabricItemSettings().rarity(Rarity.EPIC).fireproof()));
    public static final Item ASTRAL_PICKAXE = registerItem("astral_pickaxe", new ExtractionPickaxeItem(ModToolMaterial.ASTRAL, 2, -2.8f, new FabricItemSettings().rarity(Rarity.EPIC).fireproof()));
    public static final Item ASTRAL_AXE = registerItem("astral_axe", new AxeItem(ModToolMaterial.ASTRAL, 6, -3.0f, new FabricItemSettings().rarity(Rarity.EPIC).fireproof()));
    public static final Item ASTRAL_SHOVEL = registerItem("astral_shovel", new ShovelItem(ModToolMaterial.ASTRAL, 2.5f, -3.0f, new FabricItemSettings().rarity(Rarity.EPIC).fireproof()));
    public static final Item ASTRAL_HOE = registerItem("astral_hoe", new HoeItem(ModToolMaterial.ASTRAL, -8, 0.0f, new FabricItemSettings().rarity(Rarity.EPIC).fireproof()));

    public static final Item ASTRAL_SICKLE = registerItem("astral_sickle", new SickleItem(new FabricItemSettings().maxDamage(5018).rarity(Rarity.EPIC).fireproof()));
    public static final Item ASTRAL_FORGING_HAMMER = registerItem("astral_forging_hammer", new ForgingHammerItem(new FabricItemSettings().rarity(Rarity.EPIC).maxDamage(100).fireproof()));

    public static final Item DAYBREAK_DOMAIN_FRAGMENTS = registerItem("daybreak_domain_fragments", new Item(new FabricItemSettings().rarity(Rarity.EPIC).fireproof()));
    public static final Item SOMBER_BLOOD_ORBS = registerItem("somber_blood_orbs", new Item(new FabricItemSettings().rarity(Rarity.EPIC).fireproof()));
    public static final Item OTHERWORLDLY_WHISPERS = registerItem("otherworldly_whispers", new OtherworldlyWhispersItem(new FabricItemSettings().rarity(Rarity.EPIC).fireproof()));

    public static final Item DIVINE_PICKAXE = registerItem("divine_pickaxe", new ExtractionPickaxeItem(ModToolMaterial.DIVINE, 2, -2.8f, new FabricItemSettings().rarity(Rarity.EPIC).fireproof()));
    public static final Item CATACLYSMIC_PICKAXE = registerItem("cataclysmic_pickaxe", new ExtractionPickaxeItem(ModToolMaterial.CATACLYSMIC, 2, -2.8f, new FabricItemSettings().rarity(Rarity.EPIC).fireproof()));
    public static final Item ELDRITCH_PICKAXE = registerItem("eldritch_pickaxe", new ExtractionPickaxeItem(ModToolMaterial.ELDRITCH, 2, -2.8f, new FabricItemSettings().rarity(Rarity.EPIC).fireproof()));

    public static final Item GINKGO_SIGN = registerItem("ginkgo_sign", new SignItem(new FabricItemSettings().maxCount(16), ModBlocks.STANDING_GINKGO_SIGN, ModBlocks.WALL_GINKGO_SIGN));
    public static final Item HANGING_GINKGO_SIGN = registerItem("ginkgo_hanging_sign", new HangingSignItem(ModBlocks.HANGING_GINKGO_SIGN, ModBlocks.WALL_HANGING_GINKGO_SIGN, new FabricItemSettings().maxCount(16)));

    public static final Item GINKGO_BOAT = TerraformBoatItemHelper.registerBoatItem(ModBoats.GINKGO_BOAT_ID, ModBoats.GINKGO_BOAT_KEY, false);
    public static final Item GINKGO_CHEST_BOAT = TerraformBoatItemHelper.registerBoatItem(ModBoats.GINKGO_CHEST_BOAT_ID, ModBoats.GINKGO_BOAT_KEY, true);

    public static final Item TREX_SPAWN_EGG = registerItem("t-rex_spawn_egg", new SpawnEggItem(ModEntities.TREX, 0x573722, 0x705341, new FabricItemSettings()));
    public static final Item INFECTED_TREX_SPAWN_EGG = registerItem("infected_t-rex_spawn_egg", new CalamitySpawnItem(ModEntities.INFECTED_TREX, 0x9d8071, 0xffffff, new FabricItemSettings()));

    public static final Item DINOSAUR_HIDE = registerItem("dinosaur_hide", new Item(new FabricItemSettings()));
    public static final Item DINOSAUR_MEAT = registerItem("dinosaur_meat", new Item(new FabricItemSettings().food(ModFoodComponents.DINOSAUR_MEAT)));
    public static final Item COOKED_DINOSAUR_MEAT = registerItem("cooked_dinosaur_meat", new Item(new FabricItemSettings().food(ModFoodComponents.DINOSAUR_MEAT_COOKED)));

    public static final Item PORRIDGE = registerItem("porridge", new StewItem(new FabricItemSettings().maxCount(1).food(ModFoodComponents.PORRIDGE).recipeRemainder(Items.BOWL)));

    public static final Item FLOUR = registerItem("flour", new Item(new FabricItemSettings()));
    public static final Item BREADCRUMBS = registerItem("breadcrumbs", new Item(new FabricItemSettings()));
    public static final Item RAW_SCHNITZEL = registerItem("raw_schnitzel", new Item(new FabricItemSettings().food(ModFoodComponents.RAW_SCHNITZEL)));
    public static final Item SCHNITZEL = registerItem("schnitzel", new Item(new FabricItemSettings().food(ModFoodComponents.SCHNITZEL)));

    public static final Item SQUID = registerItem("squid", new Item(new FabricItemSettings()));
    public static final Item CALAMARI = registerItem("calamari", new Item(new FabricItemSettings().food(ModFoodComponents.CALAMARI)));
    public static final Item FRIED_CALAMARI = registerItem("fried_calamari", new Item(new FabricItemSettings().food(ModFoodComponents.FRIED_CALAMARI)));

    public static final Item MAULTASCHE = registerItem("maultasche", new Item(new FabricItemSettings().food(ModFoodComponents.MAULTASCHE)));
    public static final Item MAULTASCHEN_BROTH = registerItem("maultaschen_broth", new StewItem(new FabricItemSettings().maxCount(1).food(ModFoodComponents.MAULTASCHEN_BROTH).recipeRemainder(Items.BOWL)));
    public static final Item SUSPICIOUS_MAULTASCHEN_BROTH = registerItem("suspicious_maultaschen_broth", new StewItem(new FabricItemSettings().maxCount(1).food(ModFoodComponents.SUSPICIOUS_MAULTASCHEN_BROTH).recipeRemainder(Items.BOWL)));

    public static final Item BEER_BOTTLE = registerItem("beer_bottle", new DrinkableBottle(new FabricItemSettings().food(ModFoodComponents.WOODEN_LIQUID_STEIN).maxCount(16).recipeRemainder(Items.GLASS_BOTTLE)));
    public static final Item BEER_GOAT_HORN = registerItem("beer_goat_horn", new DrinkableHorn(new FabricItemSettings().food(ModFoodComponents.WOODEN_LIQUID_STEIN).maxCount(16).recipeRemainder(Items.GOAT_HORN)));

    public static final Item TINTED_GLASS_FLASK = registerItem("tinted_glass_flask", new EmptyFlaskItem(new FabricItemSettings()));
    public static final Item SOUL_FLASK = registerItem("soul_flask", new SoulFlaskItem(new FabricItemSettings().recipeRemainder(ModItems.TINTED_GLASS_FLASK).rarity(Rarity.RARE).maxCount(16)));
    public static final Item CURSED_SOUL_FLASK = registerItem("cursed_soul_flask", new CursedFlaskItem(new FabricItemSettings().recipeRemainder(ModItems.TINTED_GLASS_FLASK).rarity(Rarity.EPIC).maxCount(16)));
    public static final Item SOUL_FLASK_OF_THE_ABYSS = registerItem("soul_flask_of_the_abyss", new AbyssFlaskItem(new FabricItemSettings().recipeRemainder(ModItems.TINTED_GLASS_FLASK).rarity(Rarity.EPIC).maxCount(16)));

    public static final Item EMPERORS_CROWN = registerItem("emperors_crown", new ArmorItem(ModArmorMaterial.EMPEROR, ArmorItem.Type.HELMET, new FabricItemSettings().rarity(Rarity.UNCOMMON)));

    public static final Item MONOCLE = registerItem("monocle", new ArmorItem(ModArmorMaterial.MONOCLE, ArmorItem.Type.HELMET, new FabricItemSettings()));
    public static final Item VICTORIAN_SUIT = registerItem("victorian_suit", new ArmorItem(ModArmorMaterial.VICTORIAN, ArmorItem.Type.CHESTPLATE, new FabricItemSettings()));
    public static final Item VICTORIAN_PANTS = registerItem("victorian_pants", new ArmorItem(ModArmorMaterial.VICTORIAN, ArmorItem.Type.LEGGINGS, new FabricItemSettings()));
    public static final Item VICTORIAN_BOOTS = registerItem("victorian_boots", new ArmorItem(ModArmorMaterial.VICTORIAN, ArmorItem.Type.BOOTS, new FabricItemSettings()));

    public static final Item PICKELHAUBE = registerItem("pickelhaube", new ArmorItem(ModArmorMaterial.PICKELHAUBE, ArmorItem.Type.HELMET, new FabricItemSettings()));
    public static final Item IMPERIAL_GERMAN_UNIFORM_MANTLE = registerItem("imperial_german_uniform_mantle", new ArmorItem(ModArmorMaterial.IMPERIAL_GERMAN_UNIFORM, ArmorItem.Type.CHESTPLATE, new FabricItemSettings()));
    public static final Item IMPERIAL_GERMAN_UNIFORM_PANTS = registerItem("imperial_german_uniform_pants", new ArmorItem(ModArmorMaterial.IMPERIAL_GERMAN_UNIFORM, ArmorItem.Type.LEGGINGS, new FabricItemSettings()));
    public static final Item IMPERIAL_GERMAN_UNIFORM_BOOTS = registerItem("imperial_german_uniform_boots", new ArmorItem(ModArmorMaterial.IMPERIAL_GERMAN_UNIFORM, ArmorItem.Type.BOOTS, new FabricItemSettings()));

    public static final Item OXYGEN_TANK = registerItem("oxygen_tank", new OxygenTankItem(new FabricItemSettings().maxCount(1)));
    public static final Item ASTRONAUT_HELMET = registerItem("astronaut_helmet", new HypoxiaProofArmorItem(ModArmorMaterial.ASTRONAUT, ArmorItem.Type.HELMET, new FabricItemSettings()));
    public static final Item SPACE_SUIT_CHESTPLATE = registerItem("space_suit_chestplate", new SpaceSuitArmorItem(ModArmorMaterial.SPACE_SUIT, ArmorItem.Type.CHESTPLATE, new FabricItemSettings()));
    public static final Item SPACE_SUIT_LEGGINGS = registerItem("space_suit_leggings", new HypoxiaProofArmorItem(ModArmorMaterial.SPACE_SUIT, ArmorItem.Type.LEGGINGS, new FabricItemSettings()));
    public static final Item SPACE_SUIT_BOOTS = registerItem("space_suit_boots", new HypoxiaProofArmorItem(ModArmorMaterial.SPACE_SUIT, ArmorItem.Type.BOOTS, new FabricItemSettings()));

    public static final Item CTHONAUT_HELMET = registerItem("cthonaut_helmet", new ArmorItem(ModArmorMaterial.CTHONAUT, ArmorItem.Type.HELMET, new FabricItemSettings().fireproof()));
    public static final Item DEPTH_SUIT_CHESTPLATE = registerItem("depth_suit_chestplate", new ArmorItem(ModArmorMaterial.DEPTH_SUIT, ArmorItem.Type.CHESTPLATE, new FabricItemSettings().fireproof()));
    public static final Item DEPTH_SUIT_LEGGINGS = registerItem("depth_suit_leggings", new ArmorItem(ModArmorMaterial.DEPTH_SUIT, ArmorItem.Type.LEGGINGS, new FabricItemSettings().fireproof()));
    public static final Item DEPTH_SUIT_BOOTS = registerItem("depth_suit_boots", new ArmorItem(ModArmorMaterial.DEPTH_SUIT, ArmorItem.Type.BOOTS, new FabricItemSettings().fireproof()));

    public static final Item SCIENTIST_GLASSES = registerItem("scientist_glasses", new ArmorItem(ModArmorMaterial.SCIENTIST, ArmorItem.Type.HELMET, new FabricItemSettings()));
    public static final Item HEV_SUIT_CHESTPLATE = registerItem("hev_suit_chestplate", new RadiationProofArmorItem(ModArmorMaterial.HEV_SUIT, ArmorItem.Type.CHESTPLATE, new FabricItemSettings().fireproof()));
    public static final Item HEV_SUIT_LEGGINGS = registerItem("hev_suit_leggings", new RadiationProofArmorItem(ModArmorMaterial.HEV_SUIT, ArmorItem.Type.LEGGINGS, new FabricItemSettings().fireproof()));
    public static final Item HEV_SUIT_BOOTS = registerItem("hev_suit_boots", new RadiationProofArmorItem(ModArmorMaterial.HEV_SUIT, ArmorItem.Type.BOOTS, new FabricItemSettings().fireproof()));

    private static Item registerItem(String name, Item item) {
        return Registry.register(Registries.ITEM, new Identifier(TLOTD.MOD_ID, name), item);
    }

    public static void registerModItems() {
        TLOTD.LOGGER.info("Registering Mod Items for " + TLOTD.MOD_ID);
    }
}