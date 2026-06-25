package net.tlotd.compat.rei;

import me.shedaniel.rei.api.client.plugins.REIClientPlugin;
import me.shedaniel.rei.api.client.registry.category.CategoryRegistry;
import me.shedaniel.rei.api.client.registry.display.DisplayRegistry;
import me.shedaniel.rei.api.client.registry.entry.CollapsibleEntryRegistry;
import me.shedaniel.rei.api.client.registry.entry.EntryRegistry;
import me.shedaniel.rei.api.common.util.EntryStacks;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.tlotd.TLOTD;
import net.tlotd.block.ModBlocks;
import net.tlotd.compat.CompatModsCheck;
import net.tlotd.fluid.ModFluids;
import net.tlotd.item.ModItems;
import net.tlotd.recipe.*;

public class TLOTDREIClientPlugin implements REIClientPlugin {

    @Override
    public void registerCategories(CategoryRegistry registry) {
        registry.add(new DwarvenForgingCategory());
        registry.add(new NetheriteSmithingCategory());
        registry.add(new MithrilSmithingCategory());
        registry.add(new WitchingTableCategory());
        registry.add(new AugmentingTableCategory());
        registry.add(new IncubatorCategory());
        registry.addWorkstations(DwarvenForgingCategory.DWARVEN_FORGING, EntryStacks.of(ModBlocks.DWARVEN_FORGE));
        registry.addWorkstations(NetheriteSmithingCategory.METALWORKING, EntryStacks.of(ModBlocks.NETHERITE_ANVIL));
        registry.addWorkstations(NetheriteSmithingCategory.METALWORKING, EntryStacks.of(ModBlocks.MITHRIL_ANVIL));
        registry.addWorkstations(MithrilSmithingCategory.MITHRIL_METALWORKING, EntryStacks.of(ModBlocks.MITHRIL_ANVIL));
        registry.addWorkstations(WitchingTableCategory.WITCHING, EntryStacks.of(ModBlocks.WITCHING_TABLE));
        registry.addWorkstations(AugmentingTableCategory.AUGMENTING, EntryStacks.of(ModBlocks.AUGMENTATION_TABLE));
        registry.addWorkstations(IncubatorCategory.INCUBATING, EntryStacks.of(ModBlocks.INCUBATOR));
    }

    @Override
    public void registerDisplays(DisplayRegistry registry) {
        registry.registerRecipeFiller(DwarvenForgingRecipe.class, DwarvenForgingRecipe.Type.INSTANCE, DwarvenForgingDisplay::new);
        registry.registerRecipeFiller(NetheriteSmithingRecipe.class, NetheriteSmithingRecipe.Type.INSTANCE, NetheriteSmithingDisplay::new);
        registry.registerRecipeFiller(MithrilSmithingRecipe.class, MithrilSmithingRecipe.Type.INSTANCE, MithrilSmithingDisplay::new);
        registry.registerRecipeFiller(WitchingRecipe.class, WitchingRecipe.Type.INSTANCE, WitchingTableDisplay::new);
        registry.registerRecipeFiller(AugmentationRecipe.class, AugmentationRecipe.Type.INSTANCE, AugmentingTableDisplay::new);
        registry.registerRecipeFiller(IncubatonRecipe.class, IncubatonRecipe.Type.INSTANCE, IncubatorDisplay::new);
    }

    @Override
    public void registerEntries(EntryRegistry registry) {
        registry.removeEntry(EntryStacks.of(ModItems.MOD_ICON));
        registry.removeEntry(EntryStacks.of(ModItems.IRRADIATED_ICON));
        registry.removeEntry(EntryStacks.of(ModItems.BACKROOMS_SIGNAL));
        registry.removeEntry(EntryStacks.of(ModItems.INCOMPLETE_ALUMINIUM_WIRE));
        registry.removeEntry(EntryStacks.of(ModItems.INCOMPLETE_COPPER_WIRE));
        registry.removeEntry(EntryStacks.of(ModItems.INCOMPLETE_GOLD_WIRE));
        registry.removeEntry(EntryStacks.of(ModItems.INCOMPLETE_MITHRIL_WIRE));
        registry.removeEntry(EntryStacks.of(ModItems.INCOMPLETE_INTEGRATED_CIRCUIT));
        registry.removeEntry(EntryStacks.of(ModItems.INCOMPLETE_CIRCUIT_BOARD));
        registry.removeEntry(EntryStacks.of(ModItems.INCOMPLETE_ADVANCED_CIRCUIT_BOARD));
        registry.removeEntry(EntryStacks.of(ModItems.INCOMPLETE_TRANSCENDENT_CIRCUIT_BOARD));
        registry.removeEntry(EntryStacks.of(ModItems.INCOMPLETE_FUTURISTIC_CIRCUIT_BOARD));
        registry.removeEntry(EntryStacks.of(ModItems.INCOMPLETE_ARCANE_CIRCUIT_BOARD));
        registry.removeEntry(EntryStacks.of(ModItems.INCOMPLETE_BIOLOGICAL_CIRCUIT_BOARD));
        registry.removeEntry(EntryStacks.of(ModItems.INCOMPLETE_RADIO));
        registry.removeEntry(EntryStacks.of(ModItems.INCOMPLETE_TELEVISION));
        registry.removeEntry(EntryStacks.of(ModItems.INCOMPLETE_VIDEOCASSETTE_RECORDER));
        registry.removeEntry(EntryStacks.of(ModItems.INCOMPLETE_SIGNAL_TRANSMITTER));
        registry.removeEntry(EntryStacks.of(ModItems.INCOMPLETE_COMPUTER));
        registry.removeEntry(EntryStacks.of(ModItems.INCOMPLETE_KEYCARD_PROGRAMMER));
        registry.removeEntry(EntryStacks.of(ModItems.INCOMPLETE_KEYCARD_READER));
        registry.removeEntry(EntryStacks.of(ModItems.INCOMPLETE_OXYGEN_COLLECTOR));
        registry.removeEntry(EntryStacks.of(ModItems.INCOMPLETE_MITHRIL_MIRROR));


        if (!CompatModsCheck.AETHER) {
            registry.removeEntry(EntryStacks.of(ModBlocks.BLUE_BERRY_JAM_JAR));
            registry.removeEntry(EntryStacks.of(ModItems.BLUE_BERRY_JAM_TOAST));
            registry.removeEntry(EntryStacks.of(ModBlocks.WOODEN_BLUE_BERRY_MILKSHAKE_STEIN));
            registry.removeEntry(EntryStacks.of(ModItems.SKYROOT_BARK));
        }
        if (!CompatModsCheck.ALEXSCAVES) {
            registry.removeEntry(EntryStacks.of(ModItems.PEWEN_BARK));
            registry.removeEntry(EntryStacks.of(ModItems.THORNWOOD_BARK));
        }
        if (!CompatModsCheck.ATM) {
            registry.removeEntry(EntryStacks.of(ModBlocks.ANCIENT_SOULBERRY_JAM_JAR));
            registry.removeEntry(EntryStacks.of(ModItems.ANCIENT_SOULBERRY_JAM_TOAST));
        }
        if (!CompatModsCheck.BIOMESOPLENTY) {
            registry.removeEntry(EntryStacks.of(ModItems.FIR_BARK));
            registry.removeEntry(EntryStacks.of(ModItems.PINE_BARK));
            registry.removeEntry(EntryStacks.of(ModItems.MAPLE_BARK));
            registry.removeEntry(EntryStacks.of(ModItems.REDWOOD_BARK));
            registry.removeEntry(EntryStacks.of(ModItems.MAHOGANY_BARK));
            registry.removeEntry(EntryStacks.of(ModItems.JACARANCA_BARK));
            registry.removeEntry(EntryStacks.of(ModItems.PALM_BARK));
            registry.removeEntry(EntryStacks.of(ModItems.WILLOW_BARK));
            registry.removeEntry(EntryStacks.of(ModItems.DEAD_BARK));
            registry.removeEntry(EntryStacks.of(ModItems.MAGIC_BARK));
            registry.removeEntry(EntryStacks.of(ModItems.UMBRAN_BARK));
            registry.removeEntry(EntryStacks.of(ModItems.HELLBARK_BARK));
            registry.removeEntry(EntryStacks.of(ModItems.EMPYREAL_BARK));
        }
        if (!CompatModsCheck.CREATE) {
            registry.removeEntry(EntryStacks.of(ModBlocks.RED_DEEPSLATE_ZINC_ORE));
            registry.removeEntry(EntryStacks.of(ModFluids.SPEZI_BUCKET));
            registry.removeEntry(EntryStacks.of(ModFluids.STILL_SPEZI));
            registry.removeEntry(EntryStacks.of(ModFluids.SWEET_BERRY_JAM_BUCKET));
            registry.removeEntry(EntryStacks.of(ModFluids.STILL_SWEET_BERRY_JAM));
            registry.removeEntry(EntryStacks.of(ModFluids.GLOW_BERRY_JAM_BUCKET));
            registry.removeEntry(EntryStacks.of(ModFluids.STILL_GLOW_BERRY_JAM));
            registry.removeEntry(EntryStacks.of(ModFluids.STRAWBERRY_JAM_BUCKET));
            registry.removeEntry(EntryStacks.of(ModFluids.STILL_STRAWBERRY_JAM));
            registry.removeEntry(EntryStacks.of(ModFluids.ORANGE_MARMELADE_BUCKET));
            registry.removeEntry(EntryStacks.of(ModFluids.STILL_ORANGE_MARMELADE));
        }

        if (!(CompatModsCheck.CREATE && CompatModsCheck.AETHER)) {
            registry.removeEntry(EntryStacks.of(ModFluids.BLUE_BERRY_JAM_BUCKET));
            registry.removeEntry(EntryStacks.of(ModFluids.STILL_BLUE_BERRY_JAM));
        }
        if (!(CompatModsCheck.CREATE && CompatModsCheck.ATM)) {
            registry.removeEntry(EntryStacks.of(ModFluids.ANCIENT_SOULBERRY_JAM_BUCKET));
            registry.removeEntry(EntryStacks.of(ModFluids.STILL_ANCIENT_SOULBERRY_JAM));
        }
        if (!(CompatModsCheck.CREATE && CompatModsCheck.UNDERGARDEN)) {
            registry.removeEntry(EntryStacks.of(ModFluids.DROOPFRUIT_JAM_BUCKET));
            registry.removeEntry(EntryStacks.of(ModFluids.STILL_DROOPFRUIT_JAM));
        }

        if (!CompatModsCheck.QUARK) {
            registry.removeEntry(EntryStacks.of(ModItems.ASHEN_BARK));
            registry.removeEntry(EntryStacks.of(ModItems.AZALEA_BARK));
            registry.removeEntry(EntryStacks.of(ModItems.TRUMPET_BARK));
        }
        if (!CompatModsCheck.SCULKHORDE) {
            registry.removeEntry(EntryStacks.of(ModBlocks.SCULK_TREX_HEAD));
        }
        if (!CompatModsCheck.SPORE) {
            registry.removeEntry(EntryStacks.of(ModBlocks.INFECTED_TREX_EGG));
            registry.removeEntry(EntryStacks.of(ModBlocks.INFECTED_TREX_HEAD));
        }
        if (!CompatModsCheck.THERMAL) {
            registry.removeEntry(EntryStacks.of(ModItems.RUBBERWOOD_BARK));
        }
        if (!CompatModsCheck.TCONSTRUCT) {
            registry.removeEntry(EntryStacks.of(ModFluids.MOLTEN_MITHRIL_BUCKET));
            registry.removeEntry(EntryStacks.of(ModFluids.STILL_MOLTEN_MITHRIL));
        }
        if (!CompatModsCheck.TOUGHASNAILS) {
            registry.removeEntry(EntryStacks.of(ModItems.AUGMENT_THERMAL_HEATING));
            registry.removeEntry(EntryStacks.of(ModItems.AUGMENT_THERMAL_COOLING));
        }
        if (!CompatModsCheck.TWILIGHTFOREST) {
            registry.removeEntry(EntryStacks.of(ModBlocks.WOODEN_TORCHBERRY_MILKSHAKE_STEIN));
            registry.removeEntry(EntryStacks.of(ModItems.TWILIGHT_OAK_BARK));
            registry.removeEntry(EntryStacks.of(ModItems.CANOPY_TREE_BARK));
            registry.removeEntry(EntryStacks.of(ModItems.TWILIGHT_MANGROVE_BARK));
            registry.removeEntry(EntryStacks.of(ModItems.DARKWOOD_BARK));
            registry.removeEntry(EntryStacks.of(ModItems.TIMEWOOD_BARK));
            registry.removeEntry(EntryStacks.of(ModItems.TRANSWOOD_BARK));
            registry.removeEntry(EntryStacks.of(ModItems.MINEWOOD_BARK));
            registry.removeEntry(EntryStacks.of(ModItems.SORTINGWOOD_BARK));
        }
        if (!CompatModsCheck.UNDERGARDEN) {
            registry.removeEntry(EntryStacks.of(ModBlocks.DROOPFRUIT_JAM_JAR));
            registry.removeEntry(EntryStacks.of(ModItems.DROOPFRUIT_JAM_TOAST));
        }
        if (!CompatModsCheck.WITHERSTORMMOD) {
            registry.removeEntry(EntryStacks.of(ModBlocks.SICKENED_TREX_HEAD));
            registry.removeEntry(EntryStacks.of(ModItems.MITHRIL_COMMAND_BLOCK_SWORD));
            registry.removeEntry(EntryStacks.of(ModItems.MITHRIL_COMMAND_BLOCK_PICKAXE));
            registry.removeEntry(EntryStacks.of(ModItems.MITHRIL_COMMAND_BLOCK_AXE));
            registry.removeEntry(EntryStacks.of(ModItems.MITHRIL_COMMAND_BLOCK_SHOVEL));
            registry.removeEntry(EntryStacks.of(ModItems.MITHRIL_COMMAND_BLOCK_HOE));
        }
    }

    @Override
    public void registerCollapsibleEntries(CollapsibleEntryRegistry registry) {
        registry.group(
                Identifier.of(TLOTD.MOD_ID, "wooden_steins"),
                Text.translatable("rei.category.wooden_steins"),
                EntryStacks.of(ModBlocks.WOODEN_STEIN.asItem()),
                EntryStacks.of(ModBlocks.WOODEN_WATER_STEIN.asItem()),
                EntryStacks.of(ModBlocks.WOODEN_BEER_STEIN.asItem()),
                EntryStacks.of(ModBlocks.WOODEN_MEAD_STEIN.asItem()),
                EntryStacks.of(ModBlocks.WOODEN_APPLE_JUICE_STEIN.asItem()),
                EntryStacks.of(ModBlocks.WOODEN_ORANGE_JUICE_STEIN.asItem()),
                EntryStacks.of(ModBlocks.WOODEN_MILK_STEIN.asItem()),
                EntryStacks.of(ModBlocks.WOODEN_CHOCOLATE_MILKSHAKE_STEIN.asItem()),
                EntryStacks.of(ModBlocks.WOODEN_CARAMEL_MILKSHAKE_STEIN.asItem()),
                EntryStacks.of(ModBlocks.WOODEN_STRAWBERRY_MILKSHAKE_STEIN.asItem()),
                EntryStacks.of(ModBlocks.WOODEN_ORANGE_MILKSHAKE_STEIN.asItem()),
                EntryStacks.of(ModBlocks.WOODEN_BLUE_BERRY_MILKSHAKE_STEIN.asItem()),
                EntryStacks.of(ModBlocks.WOODEN_TORCHBERRY_MILKSHAKE_STEIN.asItem()),
                EntryStacks.of(ModBlocks.HOT_WOODEN_MILK_STEIN.asItem()),
                EntryStacks.of(ModBlocks.WOODEN_HOT_CHOCOLATE_STEIN.asItem())
        );
        registry.group(
                Identifier.of(TLOTD.MOD_ID, "preserves_jars"),
                Text.translatable("rei.category.preserves_jars"),
                EntryStacks.of(ModBlocks.PRESERVES_JAR.asItem()),
                EntryStacks.of(ModBlocks.SWEET_BERRY_JAM_JAR.asItem()),
                EntryStacks.of(ModBlocks.GLOW_BERRY_JAM_JAR.asItem()),
                EntryStacks.of(ModBlocks.STRAWBERRY_JAM_JAR.asItem()),
                EntryStacks.of(ModBlocks.ORANGE_MARMELADE_JAR.asItem()),
                EntryStacks.of(ModBlocks.BLUE_BERRY_JAM_JAR.asItem()),
                EntryStacks.of(ModBlocks.DROOPFRUIT_JAM_JAR.asItem()),
                EntryStacks.of(ModBlocks.ANCIENT_SOULBERRY_JAM_JAR.asItem())
        );
        registry.group(
                Identifier.of(TLOTD.MOD_ID, "vhs_cassettes"),
                Text.translatable("rei.category.vhs_cassettes"),
                EntryStacks.of(ModItems.VHS_CASSETTE),
                EntryStacks.of(ModItems.VHS_CASSETTE_BROKEN),
                EntryStacks.of(ModItems.VHS_CASSETTE_1),
                EntryStacks.of(ModItems.VHS_CASSETTE_2),
                EntryStacks.of(ModItems.VHS_CASSETTE_3),
                EntryStacks.of(ModItems.VHS_CASSETTE_4),
                EntryStacks.of(ModItems.VHS_CASSETTE_5),
                EntryStacks.of(ModItems.VHS_CASSETTE_6),
                EntryStacks.of(ModItems.VHS_CASSETTE_7),
                EntryStacks.of(ModItems.VHS_CASSETTE_8),
                EntryStacks.of(ModItems.VHS_CASSETTE_9),
                EntryStacks.of(ModItems.VHS_CASSETTE_PROJECT_KV31)
        );
        registry.group(
                Identifier.of(TLOTD.MOD_ID, "game_cartridges"),
                Text.translatable("rei.category.game_cartridges"),
                EntryStacks.of(ModItems.GAME_CARTRIDGE),
                EntryStacks.of(ModItems.GAME_CARTRIDGE_1),
                EntryStacks.of(ModItems.GAME_CARTRIDGE_2),
                EntryStacks.of(ModItems.GAME_CARTRIDGE_3)
        );
        registry.group(
                Identifier.of(TLOTD.MOD_ID, "tree_barks"),
                Text.translatable("rei.category.tree_barks"),
                EntryStacks.of(ModItems.OAK_BARK),
                EntryStacks.of(ModItems.SPRUCE_BARK),
                EntryStacks.of(ModItems.BIRCH_BARK),
                EntryStacks.of(ModItems.JUNGLE_BARK),
                EntryStacks.of(ModItems.ACACIA_BARK),
                EntryStacks.of(ModItems.DARK_OAK_BARK),
                EntryStacks.of(ModItems.MANGROVE_BARK),
                EntryStacks.of(ModItems.CHERRY_BARK),
                EntryStacks.of(ModItems.GINKGO_BARK),

                EntryStacks.of(ModItems.SKYROOT_BARK),

                EntryStacks.of(ModItems.PEWEN_BARK),
                EntryStacks.of(ModItems.THORNWOOD_BARK),

                EntryStacks.of(ModItems.FIR_BARK),
                EntryStacks.of(ModItems.PINE_BARK),
                EntryStacks.of(ModItems.MAPLE_BARK),
                EntryStacks.of(ModItems.REDWOOD_BARK),
                EntryStacks.of(ModItems.MAHOGANY_BARK),
                EntryStacks.of(ModItems.JACARANCA_BARK),
                EntryStacks.of(ModItems.PALM_BARK),
                EntryStacks.of(ModItems.WILLOW_BARK),
                EntryStacks.of(ModItems.DEAD_BARK),
                EntryStacks.of(ModItems.MAGIC_BARK),
                EntryStacks.of(ModItems.UMBRAN_BARK),
                EntryStacks.of(ModItems.HELLBARK_BARK),
                EntryStacks.of(ModItems.EMPYREAL_BARK),

                EntryStacks.of(ModItems.ASHEN_BARK),
                EntryStacks.of(ModItems.AZALEA_BARK),
                EntryStacks.of(ModItems.TRUMPET_BARK),

                EntryStacks.of(ModItems.RUBBERWOOD_BARK),

                EntryStacks.of(ModItems.TWILIGHT_OAK_BARK),
                EntryStacks.of(ModItems.CANOPY_TREE_BARK),
                EntryStacks.of(ModItems.TWILIGHT_MANGROVE_BARK),
                EntryStacks.of(ModItems.DARKWOOD_BARK),
                EntryStacks.of(ModItems.TIMEWOOD_BARK),
                EntryStacks.of(ModItems.TRANSWOOD_BARK),
                EntryStacks.of(ModItems.MINEWOOD_BARK),
                EntryStacks.of(ModItems.SORTINGWOOD_BARK),

                EntryStacks.of(ModItems.YELLOW_WALLPAPER)
        );
        registry.group(
                Identifier.of(TLOTD.MOD_ID, "augments"),
                Text.translatable("rei.category.augments"),
                EntryStacks.of(ModItems.AUGMENT_SLOT_EXPANSION),

                EntryStacks.of(ModItems.AUGMENT_ELDER_DAYS_ELVEN_FORGED),

                EntryStacks.of(ModItems.AUGMENT_PHOTOSYNTHESIS),
                EntryStacks.of(ModItems.AUGMENT_STARLIGHT_BLESSING),

                EntryStacks.of(ModItems.AUGMENT_EXTRACTION),
                EntryStacks.of(ModItems.AUGMENT_BATTERY_PACK),
                EntryStacks.of(ModItems.AUGMENT_ENERGY_SHIELD),
                EntryStacks.of(ModItems.AUGMENT_MITHRIL_CHAINMAIL),
                EntryStacks.of(ModItems.AUGMENT_DRAGON_SCALE_PLATING),
                EntryStacks.of(ModItems.AUGMENT_LEAD_PLATING),
                EntryStacks.of(ModItems.AUGMENT_DIMENSIONAL_COHESION),
                EntryStacks.of(ModItems.AUGMENT_OXYGEN_TANK),
                EntryStacks.of(ModItems.AUGMENT_AIRTIGHT_SEALS),
                EntryStacks.of(ModItems.AUGMENT_BULLET_RESISTANCE),

                EntryStacks.of(ModItems.AUGMENT_THERMAL_HEATING),
                EntryStacks.of(ModItems.AUGMENT_THERMAL_COOLING)
        );
    }
}
