package net.tlotd.compat.emi;

import dev.emi.emi.api.EmiPlugin;
import dev.emi.emi.api.EmiRegistry;
import dev.emi.emi.api.recipe.EmiWorldInteractionRecipe;
import dev.emi.emi.api.stack.EmiIngredient;
import dev.emi.emi.api.stack.EmiStack;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.Items;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;
import net.tlotd.TLOTD;
import net.tlotd.block.ModBlocks;
import net.tlotd.compat.CompatModsCheck;
import net.tlotd.fluid.ModFluids;
import net.tlotd.item.ModItems;
import net.tlotd.recipe.*;

public class TLOTDEMIPlugin implements EmiPlugin {

    @Override
    public void register(EmiRegistry registry) {

        registry.addCategory(DwarvenForgingEMICategory.CATEGORY);
        registry.addCategory(NetheriteSmithingEMICategory.CATEGORY);
        registry.addCategory(MithrilSmithingEMICategory.CATEGORY);
        registry.addCategory(WitchingTableEMICategory.CATEGORY);
        registry.addCategory(AugmentingTableEMICategory.CATEGORY);
        registry.addCategory(IncubatorEMICategory.CATEGORY);

        registry.addWorkstation(DwarvenForgingEMICategory.CATEGORY, EmiStack.of(ModBlocks.DWARVEN_FORGE));
        registry.addWorkstation(NetheriteSmithingEMICategory.CATEGORY, EmiStack.of(ModBlocks.NETHERITE_ANVIL));
        registry.addWorkstation(NetheriteSmithingEMICategory.CATEGORY, EmiStack.of(ModBlocks.MITHRIL_ANVIL));
        registry.addWorkstation(MithrilSmithingEMICategory.CATEGORY, EmiStack.of(ModBlocks.MITHRIL_ANVIL));
        registry.addWorkstation(WitchingTableEMICategory.CATEGORY, EmiStack.of(ModBlocks.WITCHING_TABLE));
        registry.addWorkstation(AugmentingTableEMICategory.CATEGORY, EmiStack.of(ModBlocks.AUGMENTATION_TABLE));
        registry.addWorkstation(IncubatorEMICategory.CATEGORY, EmiStack.of(ModBlocks.INCUBATOR));

        registry.getRecipeManager().listAllOfType(DwarvenForgingRecipe.Type.INSTANCE).forEach(recipe -> registry.addRecipe(new DwarvenForgingEMIRecipe(recipe)));
        registry.getRecipeManager().listAllOfType(NetheriteSmithingRecipe.Type.INSTANCE).forEach(recipe -> registry.addRecipe(new NetheriteSmithingEMIRecipe(recipe)));
        registry.getRecipeManager().listAllOfType(MithrilSmithingRecipe.Type.INSTANCE).forEach(recipe -> registry.addRecipe(new MithrilSmithingEMIRecipe(recipe)));
        registry.getRecipeManager().listAllOfType(WitchingRecipe.Type.INSTANCE).forEach(recipe -> registry.addRecipe(new WitchingTableEMIRecipe(recipe)));
        registry.getRecipeManager().listAllOfType(AugmentationRecipe.Type.INSTANCE).forEach(recipe -> registry.addRecipe(new AugmentingTableEMIRecipe(recipe)));
        registry.getRecipeManager().listAllOfType(IncubatonRecipe.Type.INSTANCE).forEach(recipe -> registry.addRecipe(new IncubatorEMIRecipe(recipe)));

        registry.addRecipe(EmiWorldInteractionRecipe.builder().id(new Identifier(TLOTD.MOD_ID, "/carved_white_pumpkin")).leftInput(EmiStack.of(ModBlocks.WHITE_PUMPKIN)).rightInput(EmiIngredient.of(TagKey.of(RegistryKeys.ITEM, new Identifier("c", "shears"))), true).output(EmiStack.of(Items.PUMPKIN_SEEDS, 4)).output(EmiStack.of(ModBlocks.CARVED_WHITE_PUMPKIN)).build());

        registry.addRecipe(EmiWorldInteractionRecipe.builder().id(new Identifier(TLOTD.MOD_ID, "/refined_raw_mithril_lava_cleansing")).leftInput(EmiStack.of(ModItems.RAW_MITHRIL)).rightInput(EmiStack.of(Fluids.LAVA), true).output(EmiStack.of(ModItems.REFINED_RAW_MITHRIL)).build());
        registry.addRecipe(EmiWorldInteractionRecipe.builder().id(new Identifier(TLOTD.MOD_ID, "/refined_raw_mithril_block_lava_cleansing")).leftInput(EmiStack.of(ModBlocks.RAW_MITHRIL_BLOCK)).rightInput(EmiStack.of(Fluids.LAVA), true).output(EmiStack.of(ModBlocks.REFINED_RAW_MITHRIL_BLOCK)).build());

        registry.addRecipe(EmiWorldInteractionRecipe.builder().id(new Identifier(TLOTD.MOD_ID, "/rich_farmland_rich_grass_hoeing")).leftInput(EmiStack.of(ModBlocks.RICH_GRASS_BLOCK)).rightInput(EmiIngredient.of(TagKey.of(RegistryKeys.ITEM, new Identifier("c", "hoes"))), true).output(EmiStack.of(ModBlocks.RICH_FARMLAND)).build());
        registry.addRecipe(EmiWorldInteractionRecipe.builder().id(new Identifier(TLOTD.MOD_ID, "/rich_farmland_rich_dirt_hoeing")).leftInput(EmiStack.of(ModBlocks.RICH_DIRT)).rightInput(EmiIngredient.of(TagKey.of(RegistryKeys.ITEM, new Identifier("c", "hoes"))), true).output(EmiStack.of(ModBlocks.RICH_FARMLAND)).build());
        registry.addRecipe(EmiWorldInteractionRecipe.builder().id(new Identifier(TLOTD.MOD_ID, "/rich_farmland_rich_path_hoeing")).leftInput(EmiStack.of(ModBlocks.RICH_DIRT_PATH)).rightInput(EmiIngredient.of(TagKey.of(RegistryKeys.ITEM, new Identifier("c", "hoes"))), true).output(EmiStack.of(ModBlocks.RICH_FARMLAND)).build());
        registry.addRecipe(EmiWorldInteractionRecipe.builder().id(new Identifier(TLOTD.MOD_ID, "/rich_path_rich_grass_pathing")).leftInput(EmiStack.of(ModBlocks.RICH_GRASS_BLOCK)).rightInput(EmiIngredient.of(TagKey.of(RegistryKeys.ITEM, new Identifier("c", "shovels"))), true).output(EmiStack.of(ModBlocks.RICH_DIRT_PATH)).build());
        registry.addRecipe(EmiWorldInteractionRecipe.builder().id(new Identifier(TLOTD.MOD_ID, "/rich_path_rich_dirt_pathing")).leftInput(EmiStack.of(ModBlocks.RICH_DIRT)).rightInput(EmiIngredient.of(TagKey.of(RegistryKeys.ITEM, new Identifier("c", "shovels"))), true).output(EmiStack.of(ModBlocks.RICH_DIRT_PATH)).build());

        registry.addRecipe(EmiWorldInteractionRecipe.builder().id(new Identifier(TLOTD.MOD_ID, "/soul_flask_soul_extraction")).leftInput(EmiStack.of(ModItems.TINTED_GLASS_FLASK)).rightInput(EmiIngredient.of(BlockTags.SOUL_FIRE_BASE_BLOCKS), false).output(EmiStack.of(ModItems.SOUL_FLASK)).build());

        registry.addRecipe(EmiWorldInteractionRecipe.builder().id(new Identifier(TLOTD.MOD_ID, "/blue_rose_archeology_table")).leftInput(EmiStack.of(ModItems.PLANT_FOSSIL)).rightInput(EmiStack.of(ModBlocks.ARCHAEOLOGY_TABLE), true).output(EmiStack.of(ModBlocks.BLUE_ROSE)).build());
        registry.addRecipe(EmiWorldInteractionRecipe.builder().id(new Identifier(TLOTD.MOD_ID, "/paeonia_archeology_table")).leftInput(EmiStack.of(ModItems.PLANT_FOSSIL)).rightInput(EmiStack.of(ModBlocks.ARCHAEOLOGY_TABLE), true).output(EmiStack.of(ModBlocks.PAEONIA)).build());
        registry.addRecipe(EmiWorldInteractionRecipe.builder().id(new Identifier(TLOTD.MOD_ID, "/iris_archeology_table")).leftInput(EmiStack.of(ModItems.PLANT_FOSSIL)).rightInput(EmiStack.of(ModBlocks.ARCHAEOLOGY_TABLE), true).output(EmiStack.of(ModBlocks.IRIS)).build());
        registry.addRecipe(EmiWorldInteractionRecipe.builder().id(new Identifier(TLOTD.MOD_ID, "/edelweiss_archeology_table")).leftInput(EmiStack.of(ModItems.PLANT_FOSSIL)).rightInput(EmiStack.of(ModBlocks.ARCHAEOLOGY_TABLE), true).output(EmiStack.of(ModBlocks.EDELWEISS)).build());
        registry.addRecipe(EmiWorldInteractionRecipe.builder().id(new Identifier(TLOTD.MOD_ID, "/athelas_archeology_table")).leftInput(EmiStack.of(ModItems.PLANT_FOSSIL)).rightInput(EmiStack.of(ModBlocks.ARCHAEOLOGY_TABLE), true).output(EmiStack.of(ModBlocks.ATHELAS)).build());
        registry.addRecipe(EmiWorldInteractionRecipe.builder().id(new Identifier(TLOTD.MOD_ID, "/ginkgo_sapling_archeology_table")).leftInput(EmiStack.of(ModItems.PLANT_FOSSIL)).rightInput(EmiStack.of(ModBlocks.ARCHAEOLOGY_TABLE), true).output(EmiStack.of(ModBlocks.GINKGO_SAPLING)).build());
        registry.addRecipe(EmiWorldInteractionRecipe.builder().id(new Identifier(TLOTD.MOD_ID, "/strawberry_seeds_archeology_table")).leftInput(EmiStack.of(ModItems.PLANT_FOSSIL)).rightInput(EmiStack.of(ModBlocks.ARCHAEOLOGY_TABLE), true).output(EmiStack.of(ModItems.STRAWBERRY_SEEDS)).build());
        registry.addRecipe(EmiWorldInteractionRecipe.builder().id(new Identifier(TLOTD.MOD_ID, "/orange_seeds_archeology_table")).leftInput(EmiStack.of(ModItems.PLANT_FOSSIL)).rightInput(EmiStack.of(ModBlocks.ARCHAEOLOGY_TABLE), true).output(EmiStack.of(ModItems.ORANGE_SEEDS)).build());
        registry.addRecipe(EmiWorldInteractionRecipe.builder().id(new Identifier(TLOTD.MOD_ID, "/coffee_beans_archeology_table")).leftInput(EmiStack.of(ModItems.PLANT_FOSSIL)).rightInput(EmiStack.of(ModBlocks.ARCHAEOLOGY_TABLE), true).output(EmiStack.of(ModItems.COFFEE_BEANS)).build());
        registry.addRecipe(EmiWorldInteractionRecipe.builder().id(new Identifier(TLOTD.MOD_ID, "/pipe_weed_seeds_archeology_table")).leftInput(EmiStack.of(ModItems.PLANT_FOSSIL)).rightInput(EmiStack.of(ModBlocks.ARCHAEOLOGY_TABLE), true).output(EmiStack.of(ModItems.PIPE_WEED_SEEDS)).build());
        registry.addRecipe(EmiWorldInteractionRecipe.builder().id(new Identifier(TLOTD.MOD_ID, "/torchflower_archeology_table")).leftInput(EmiStack.of(ModItems.PLANT_FOSSIL)).rightInput(EmiStack.of(ModBlocks.ARCHAEOLOGY_TABLE), true).output(EmiStack.of(Items.TORCHFLOWER)).build());
        registry.addRecipe(EmiWorldInteractionRecipe.builder().id(new Identifier(TLOTD.MOD_ID, "/pitcher_plant_seeds_archeology_table")).leftInput(EmiStack.of(ModItems.PLANT_FOSSIL)).rightInput(EmiStack.of(ModBlocks.ARCHAEOLOGY_TABLE), true).output(EmiStack.of(Items.PITCHER_PLANT)).build());

        registry.addRecipe(EmiWorldInteractionRecipe.builder().id(new Identifier(TLOTD.MOD_ID, "/brew_beer_cauldron")).leftInput(EmiStack.of(Items.WHEAT)).rightInput(EmiStack.of(Items.CAULDRON), true).rightInput(EmiStack.of(Fluids.WATER), false).output(EmiStack.of(ModFluids.STILL_BEER)).build());
        registry.addRecipe(EmiWorldInteractionRecipe.builder().id(new Identifier(TLOTD.MOD_ID, "/brew_mead_cauldron")).leftInput(EmiStack.of(Items.HONEY_BOTTLE, 2)).rightInput(EmiStack.of(Items.CAULDRON), true).rightInput(EmiStack.of(Fluids.WATER), false).output(EmiStack.of(ModFluids.STILL_MEAD)).build());
        registry.addRecipe(EmiWorldInteractionRecipe.builder().id(new Identifier(TLOTD.MOD_ID, "/brew_coffee_cauldron")).leftInput(EmiStack.of(ModItems.GROUND_COFFEE_BEANS)).rightInput(EmiStack.of(Items.CAULDRON), true).rightInput(EmiStack.of(ModFluids.STILL_BOILING_WATER), false).output(EmiStack.of(ModFluids.STILL_HOT_COFFEE)).build());
        registry.addRecipe(EmiWorldInteractionRecipe.builder().id(new Identifier(TLOTD.MOD_ID, "/brew_hot_chocolate_cauldron")).leftInput(EmiStack.of(Items.COCOA_BEANS)).rightInput(EmiStack.of(Items.CAULDRON), true).rightInput(EmiStack.of(ModFluids.STILL_HOT_MILK), false).output(EmiStack.of(ModFluids.STILL_HOT_CHOCOLATE)).build());

        registry.addRecipe(EmiWorldInteractionRecipe.builder().id(new Identifier(TLOTD.MOD_ID, "/collect_effigies_blood_cauldron")).leftInput(EmiStack.of(Items.CAULDRON)).rightInput(EmiStack.of(ModBlocks.EFFIGIES), true).output(EmiStack.of(Items.CAULDRON)).output(EmiStack.of(ModFluids.STILL_BLOOD)).build());

        registry.removeEmiStacks(EmiStack.of(ModItems.MOD_ICON));
        registry.removeEmiStacks(EmiStack.of(ModItems.IRRADIATED_ICON));
        registry.removeEmiStacks(EmiStack.of(ModItems.BACKROOMS_SIGNAL));
        registry.removeEmiStacks(EmiStack.of(ModItems.INCOMPLETE_ALUMINIUM_WIRE));
        registry.removeEmiStacks(EmiStack.of(ModItems.INCOMPLETE_COPPER_WIRE));
        registry.removeEmiStacks(EmiStack.of(ModItems.INCOMPLETE_GOLD_WIRE));
        registry.removeEmiStacks(EmiStack.of(ModItems.INCOMPLETE_MITHRIL_WIRE));
        registry.removeEmiStacks(EmiStack.of(ModItems.INCOMPLETE_INTEGRATED_CIRCUIT));
        registry.removeEmiStacks(EmiStack.of(ModItems.INCOMPLETE_CIRCUIT_BOARD));
        registry.removeEmiStacks(EmiStack.of(ModItems.INCOMPLETE_ADVANCED_CIRCUIT_BOARD));
        registry.removeEmiStacks(EmiStack.of(ModItems.INCOMPLETE_TRANSCENDENT_CIRCUIT_BOARD));
        registry.removeEmiStacks(EmiStack.of(ModItems.INCOMPLETE_FUTURISTIC_CIRCUIT_BOARD));
        registry.removeEmiStacks(EmiStack.of(ModItems.INCOMPLETE_ARCANE_CIRCUIT_BOARD));
        registry.removeEmiStacks(EmiStack.of(ModItems.INCOMPLETE_BIOLOGICAL_CIRCUIT_BOARD));
        registry.removeEmiStacks(EmiStack.of(ModItems.INCOMPLETE_RADIO));
        registry.removeEmiStacks(EmiStack.of(ModItems.INCOMPLETE_TELEVISION));
        registry.removeEmiStacks(EmiStack.of(ModItems.INCOMPLETE_VIDEOCASSETTE_RECORDER));
        registry.removeEmiStacks(EmiStack.of(ModItems.INCOMPLETE_SIGNAL_TRANSMITTER));
        registry.removeEmiStacks(EmiStack.of(ModItems.INCOMPLETE_COMPUTER));
        registry.removeEmiStacks(EmiStack.of(ModItems.INCOMPLETE_KEYCARD_PROGRAMMER));
        registry.removeEmiStacks(EmiStack.of(ModItems.INCOMPLETE_KEYCARD_READER));
        registry.removeEmiStacks(EmiStack.of(ModItems.INCOMPLETE_OXYGEN_COLLECTOR));
        registry.removeEmiStacks(EmiStack.of(ModItems.INCOMPLETE_MITHRIL_MIRROR));

        if (!CompatModsCheck.AETHER) {
            registry.removeEmiStacks(EmiStack.of(ModBlocks.BLUE_BERRY_JAM_JAR));
            registry.removeEmiStacks(EmiStack.of(ModItems.BLUE_BERRY_JAM_TOAST));
            registry.removeEmiStacks(EmiStack.of(ModBlocks.WOODEN_BLUE_BERRY_MILKSHAKE_STEIN));
            registry.removeEmiStacks(EmiStack.of(ModItems.SKYROOT_BARK));
        }
        if (!CompatModsCheck.ALEXSCAVES) {
            registry.removeEmiStacks(EmiStack.of(ModItems.PEWEN_BARK));
            registry.removeEmiStacks(EmiStack.of(ModItems.THORNWOOD_BARK));
        }
        if (!CompatModsCheck.ATM) {
            registry.removeEmiStacks(EmiStack.of(ModBlocks.ANCIENT_SOULBERRY_JAM_JAR));
            registry.removeEmiStacks(EmiStack.of(ModItems.ANCIENT_SOULBERRY_JAM_TOAST));
        }
        if (!CompatModsCheck.BIOMESOPLENTY) {
            registry.removeEmiStacks(EmiStack.of(ModItems.FIR_BARK));
            registry.removeEmiStacks(EmiStack.of(ModItems.PINE_BARK));
            registry.removeEmiStacks(EmiStack.of(ModItems.MAPLE_BARK));
            registry.removeEmiStacks(EmiStack.of(ModItems.REDWOOD_BARK));
            registry.removeEmiStacks(EmiStack.of(ModItems.MAHOGANY_BARK));
            registry.removeEmiStacks(EmiStack.of(ModItems.JACARANCA_BARK));
            registry.removeEmiStacks(EmiStack.of(ModItems.PALM_BARK));
            registry.removeEmiStacks(EmiStack.of(ModItems.WILLOW_BARK));
            registry.removeEmiStacks(EmiStack.of(ModItems.DEAD_BARK));
            registry.removeEmiStacks(EmiStack.of(ModItems.MAGIC_BARK));
            registry.removeEmiStacks(EmiStack.of(ModItems.UMBRAN_BARK));
            registry.removeEmiStacks(EmiStack.of(ModItems.HELLBARK_BARK));
            registry.removeEmiStacks(EmiStack.of(ModItems.EMPYREAL_BARK));
        }
        if (!CompatModsCheck.CREATE) {
            registry.removeEmiStacks(EmiStack.of(ModBlocks.RED_DEEPSLATE_ZINC_ORE));
            registry.removeEmiStacks(EmiStack.of(ModFluids.BOILING_WATER_BUCKET));
            registry.removeEmiStacks(EmiStack.of(ModFluids.HOT_COFFEE_BUCKET));
            registry.removeEmiStacks(EmiStack.of(ModFluids.SPEZI_BUCKET));
            registry.removeEmiStacks(EmiStack.of(ModFluids.STILL_SPEZI));
            registry.removeEmiStacks(EmiStack.of(ModFluids.SWEET_BERRY_JAM_BUCKET));
            registry.removeEmiStacks(EmiStack.of(ModFluids.STILL_SWEET_BERRY_JAM));
            registry.removeEmiStacks(EmiStack.of(ModFluids.GLOW_BERRY_JAM_BUCKET));
            registry.removeEmiStacks(EmiStack.of(ModFluids.STILL_GLOW_BERRY_JAM));
            registry.removeEmiStacks(EmiStack.of(ModFluids.STRAWBERRY_JAM_BUCKET));
            registry.removeEmiStacks(EmiStack.of(ModFluids.STILL_STRAWBERRY_JAM));
            registry.removeEmiStacks(EmiStack.of(ModFluids.ORANGE_MARMELADE_BUCKET));
            registry.removeEmiStacks(EmiStack.of(ModFluids.STILL_ORANGE_MARMELADE));
        }
        if (!(CompatModsCheck.CREATE && CompatModsCheck.AETHER)) {
            registry.removeEmiStacks(EmiStack.of(ModFluids.BLUE_BERRY_JAM_BUCKET));
            registry.removeEmiStacks(EmiStack.of(ModFluids.STILL_BLUE_BERRY_JAM));
        }
        if (!(CompatModsCheck.CREATE && CompatModsCheck.ATM)) {
            registry.removeEmiStacks(EmiStack.of(ModFluids.ANCIENT_SOULBERRY_JAM_BUCKET));
            registry.removeEmiStacks(EmiStack.of(ModFluids.STILL_ANCIENT_SOULBERRY_JAM));
        }
        if (!(CompatModsCheck.CREATE && CompatModsCheck.UNDERGARDEN)) {
            registry.removeEmiStacks(EmiStack.of(ModFluids.DROOPFRUIT_JAM_BUCKET));
            registry.removeEmiStacks(EmiStack.of(ModFluids.STILL_DROOPFRUIT_JAM));
        }
        if (!CompatModsCheck.QUARK) {
            registry.removeEmiStacks(EmiStack.of(ModItems.ASHEN_BARK));
            registry.removeEmiStacks(EmiStack.of(ModItems.AZALEA_BARK));
            registry.removeEmiStacks(EmiStack.of(ModItems.TRUMPET_BARK));
        }
        if (!CompatModsCheck.SCULKHORDE) {
            registry.removeEmiStacks(EmiStack.of(ModBlocks.SCULK_TREX_HEAD));
        }
        if (!CompatModsCheck.SPORE) {
            registry.removeEmiStacks(EmiStack.of(ModBlocks.INFECTED_TREX_EGG));
            registry.removeEmiStacks(EmiStack.of(ModBlocks.INFECTED_TREX_HEAD));
        }
        if (!CompatModsCheck.THERMAL) {
            registry.removeEmiStacks(EmiStack.of(ModItems.RUBBERWOOD_BARK));
        }
        if (!CompatModsCheck.TCONSTRUCT) {
            registry.removeEmiStacks(EmiStack.of(ModFluids.MOLTEN_MITHRIL_BUCKET));
            registry.removeEmiStacks(EmiStack.of(ModFluids.STILL_MOLTEN_MITHRIL));
        }
        if (!CompatModsCheck.TOUGHASNAILS) {
            registry.removeEmiStacks(EmiStack.of(ModItems.AUGMENT_THERMAL_HEATING));
            registry.removeEmiStacks(EmiStack.of(ModItems.AUGMENT_THERMAL_COOLING));
        }
        if (!CompatModsCheck.TWILIGHTFOREST) {
            registry.removeEmiStacks(EmiStack.of(ModBlocks.WOODEN_TORCHBERRY_MILKSHAKE_STEIN));
            registry.removeEmiStacks(EmiStack.of(ModItems.TWILIGHT_OAK_BARK));
            registry.removeEmiStacks(EmiStack.of(ModItems.CANOPY_TREE_BARK));
            registry.removeEmiStacks(EmiStack.of(ModItems.TWILIGHT_MANGROVE_BARK));
            registry.removeEmiStacks(EmiStack.of(ModItems.DARKWOOD_BARK));
            registry.removeEmiStacks(EmiStack.of(ModItems.TIMEWOOD_BARK));
            registry.removeEmiStacks(EmiStack.of(ModItems.TRANSWOOD_BARK));
            registry.removeEmiStacks(EmiStack.of(ModItems.MINEWOOD_BARK));
            registry.removeEmiStacks(EmiStack.of(ModItems.SORTINGWOOD_BARK));
        }
        if (!CompatModsCheck.UNDERGARDEN) {
            registry.removeEmiStacks(EmiStack.of(ModBlocks.DROOPFRUIT_JAM_JAR));
            registry.removeEmiStacks(EmiStack.of(ModItems.DROOPFRUIT_JAM_TOAST));
        }
        if (!CompatModsCheck.WITHERSTORMMOD) {
            registry.removeEmiStacks(EmiStack.of(ModBlocks.SICKENED_TREX_HEAD));
            registry.removeEmiStacks(EmiStack.of(ModItems.MITHRIL_COMMAND_BLOCK_SWORD));
            registry.removeEmiStacks(EmiStack.of(ModItems.MITHRIL_COMMAND_BLOCK_PICKAXE));
            registry.removeEmiStacks(EmiStack.of(ModItems.MITHRIL_COMMAND_BLOCK_AXE));
            registry.removeEmiStacks(EmiStack.of(ModItems.MITHRIL_COMMAND_BLOCK_SHOVEL));
            registry.removeEmiStacks(EmiStack.of(ModItems.MITHRIL_COMMAND_BLOCK_HOE));
        }
    }
}