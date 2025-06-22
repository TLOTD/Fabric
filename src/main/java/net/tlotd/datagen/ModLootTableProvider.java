package net.tlotd.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;
import net.minecraft.item.Items;
import net.tlotd.block.ModBlocks;
import net.tlotd.item.ModItems;

public class ModLootTableProvider extends FabricBlockLootTableProvider {
    public ModLootTableProvider(FabricDataOutput dataOutput) {
        super(dataOutput);
    }

    @Override
    public void generate() {
        addDrop(ModBlocks.PRESERVES_JAR);
        addDrop(ModBlocks.SWEET_BERRY_JAM_JAR);
        addDrop(ModBlocks.GLOW_BERRY_JAM_JAR);
        addDrop(ModBlocks.STRAWBERRY_JAM_JAR);
        addDrop(ModBlocks.ORANGE_MARMELADE_JAR);
        addDrop(ModBlocks.BLUE_BERRY_JAM_JAR);
        addDrop(ModBlocks.ANCIENT_SOULBERRY_JAM_JAR);

        addDrop(ModBlocks.FRAMED_GLASS);
        addDrop(ModBlocks.FRAMED_GLASS_PANE);
        addDrop(ModBlocks.FRAMED_GLASS_TOP);
        addDrop(ModBlocks.FRAMED_GLASS_TOP_PANE);
        addDrop(ModBlocks.FRAMED_GLASS_MIDDLE);
        addDrop(ModBlocks.FRAMED_GLASS_MIDDLE_PANE);
        addDrop(ModBlocks.FRAMED_GLASS_BOTTOM);
        addDrop(ModBlocks.FRAMED_GLASS_BOTTOM_PANE);
        addDrop(ModBlocks.FRAMED_GLASS_SPLIT);
        addDrop(ModBlocks.FRAMED_GLASS_SPLIT_PANE);
        addDrop(ModBlocks.FRAMED_GLASS_TILED);
        addDrop(ModBlocks.FRAMED_GLASS_TILED_PANE);

        addDrop(ModBlocks.WHITE_PUMPKIN);
        addDrop(ModBlocks.CARVED_WHITE_PUMPKIN);
        addDrop(ModBlocks.WHITE_JACK_O_LANTERN);

        addDrop(ModBlocks.SULFUR_TORCH, drops(ModItems.SULFUR_TORCH));
        addDrop(ModBlocks.SULFUR_WALL_TORCH, drops(ModItems.SULFUR_TORCH));

        addDrop(ModBlocks.BW_STICKER);
        addDrop(ModBlocks.TLOTD_STICKER);

        addDrop(ModBlocks.APPARATUS);
        addDrop(ModBlocks.FESTIVE_LIGHTS);

        addDrop(ModBlocks.GRAVESTONE);
        addDrop(ModBlocks.GRAVESTONE_CROSS);
        addDrop(ModBlocks.MOSSY_GRAVESTONE);
        addDrop(ModBlocks.MOSSY_GRAVESTONE_CROSS);

        addDrop(ModBlocks.SKELETON);
        addDrop(ModBlocks.EMERGING_SKELETON);

        addDrop(ModBlocks.EFFIGIES);
        addDrop(ModBlocks.BLOOD_CAULDRON, drops(Items.CAULDRON));
        addDrop(ModBlocks.WITCHING_TABLE);
        addDrop(ModBlocks.OUIJA_BOARD);
        addDrop(ModBlocks.GOAT_HEAD);

        addDrop(ModBlocks.MARBLE);
        addDrop(ModBlocks.MARBLE_STAIRS);
        addDrop(ModBlocks.MARBLE_SLAB, slabDrops(ModBlocks.MARBLE_SLAB));
        addDrop(ModBlocks.MARBLE_WALL);
        addDrop(ModBlocks.MARBLE_PRESSURE_PLATE);
        addDrop(ModBlocks.MARBLE_BUTTON);
        addDrop(ModBlocks.LIMESTONE);
        addDrop(ModBlocks.LIMESTONE_STAIRS);
        addDrop(ModBlocks.LIMESTONE_SLAB, slabDrops(ModBlocks.MARBLE_SLAB));
        addDrop(ModBlocks.LIMESTONE_WALL);
        addDrop(ModBlocks.LIMESTONE_PRESSURE_PLATE);
        addDrop(ModBlocks.LIMESTONE_BUTTON);

        addDrop(ModBlocks.RICH_GRASS_BLOCK, drops(ModBlocks.RICH_GRASS_BLOCK, ModBlocks.RICH_DIRT));
        addDrop(ModBlocks.RICH_DIRT);
        addDrop(ModBlocks.RICH_DIRT_PATH, drops(ModBlocks.RICH_DIRT_PATH, ModBlocks.RICH_DIRT));
        addDrop(ModBlocks.RICH_FARMLAND, drops(ModBlocks.RICH_FARMLAND, ModBlocks.RICH_DIRT));

        addDrop(ModBlocks.RED_GRAVEL);
        addDrop(ModBlocks.RED_SANDY_DEEPSLATE);

        addDrop(ModBlocks.RED_DEEPSLATE, drops(ModBlocks.RED_DEEPSLATE, ModBlocks.COBBLED_RED_DEEPSLATE));
        addDrop(ModBlocks.RED_DEEPSLATE_STAIRS);
        addDrop(ModBlocks.RED_DEEPSLATE_SLAB, slabDrops(ModBlocks.RED_DEEPSLATE_SLAB));
        addDrop(ModBlocks.POLISHED_RED_DEEPSLATE);
        addDrop(ModBlocks.RED_DEEPSLATE_BRICKS);
        addDrop(ModBlocks.RED_DEEPSLATE_BRICK_STAIRS);
        addDrop(ModBlocks.RED_DEEPSLATE_BRICK_SLAB, slabDrops(ModBlocks.RED_DEEPSLATE_BRICK_SLAB));
        addDrop(ModBlocks.MOSSY_RED_DEEPSLATE_BRICKS);
        addDrop(ModBlocks.CRACKED_RED_DEEPSLATE_BRICKS);

        addDrop(ModBlocks.COBBLED_RED_DEEPSLATE);
        addDrop(ModBlocks.COBBLED_RED_DEEPSLATE_STAIRS);
        addDrop(ModBlocks.COBBLED_RED_DEEPSLATE_SLAB, slabDrops(ModBlocks.COBBLED_RED_DEEPSLATE_SLAB));
        addDrop(ModBlocks.POLISHED_COBBLED_RED_DEEPSLATE);
        addDrop(ModBlocks.COBBLED_RED_DEEPSLATE_BRICKS);
        addDrop(ModBlocks.COBBLED_RED_DEEPSLATE_BRICK_STAIRS);
        addDrop(ModBlocks.COBBLED_RED_DEEPSLATE_BRICK_SLAB, slabDrops(ModBlocks.COBBLED_RED_DEEPSLATE_BRICK_SLAB));
        addDrop(ModBlocks.MOSSY_COBBLED_RED_DEEPSLATE_BRICKS);
        addDrop(ModBlocks.CRACKED_COBBLED_RED_DEEPSLATE_BRICKS);

        addDrop(ModBlocks.REINFORCED_RED_DEEPSLATE);

        addDrop(ModBlocks.RED_DEEPSLATE_IRON_ORE, drops(ModBlocks.RED_DEEPSLATE_IRON_ORE, Items.RAW_IRON));
        addDrop(ModBlocks.RED_DEEPSLATE_COPPER_ORE, drops(ModBlocks.RED_DEEPSLATE_COPPER_ORE, Items.RAW_COPPER));
        addDrop(ModBlocks.RED_DEEPSLATE_GOLD_ORE, drops(ModBlocks.RED_DEEPSLATE_GOLD_ORE, Items.RAW_GOLD));
        addDrop(ModBlocks.RED_DEEPSLATE_REDSTONE_ORE, drops(ModBlocks.RED_DEEPSLATE_REDSTONE_ORE, Items.REDSTONE));
        addDrop(ModBlocks.RED_DEEPSLATE_EMERALD_ORE, drops(ModBlocks.RED_DEEPSLATE_EMERALD_ORE, Items.EMERALD));
        addDrop(ModBlocks.RED_DEEPSLATE_LAPIS_ORE, drops(ModBlocks.RED_DEEPSLATE_LAPIS_ORE, Items.LAPIS_LAZULI));
        addDrop(ModBlocks.RED_DEEPSLATE_DIAMOND_ORE, drops(ModBlocks.RED_DEEPSLATE_DIAMOND_ORE, Items.DIAMOND));

        addDrop(ModBlocks.RED_DEEPSLATE_SULFUR_ORE, drops(ModBlocks.RED_DEEPSLATE_SULFUR_ORE, ModItems.SULFUR));
        addDrop(ModBlocks.RED_DEEPSLATE_LEAD_ORE, drops(ModBlocks.RED_DEEPSLATE_LEAD_ORE, ModItems.RAW_LEAD));
        addDrop(ModBlocks.RED_DEEPSLATE_URANIUM_ORE, drops(ModBlocks.RED_DEEPSLATE_URANIUM_ORE, ModItems.URANIUM));
        addDrop(ModBlocks.RED_DEEPSLATE_HELIORITE_ORE, drops(ModBlocks.RED_DEEPSLATE_HELIORITE_ORE, ModItems.HELIORITE_COMB));
        addDrop(ModBlocks.RED_DEEPSLATE_PALLADIUM_ORE, drops(ModBlocks.RED_DEEPSLATE_PALLADIUM_ORE, ModItems.RAW_PALLADIUM));
        addDrop(ModBlocks.RED_DEEPSLATE_JURASSOLINE_ORE, drops(ModBlocks.RED_DEEPSLATE_JURASSOLINE_ORE, ModItems.JURASSOLINE_CRYSTAL));
        addDrop(ModBlocks.RED_DEEPSLATE_CINNABAR_ORE, drops(ModBlocks.RED_DEEPSLATE_CINNABAR_ORE, ModItems.CINNABAR_CRYSTAL));
        addDrop(ModBlocks.RED_DEEPSLATE_NEBULAR_ORE, drops(ModBlocks.RED_DEEPSLATE_NEBULAR_ORE, ModItems.NEBULAR_CRYSTAL));

        addDrop(ModBlocks.PREHISTORIC_DEBRIS, drops(Items.ANCIENT_DEBRIS));

        addDrop(ModBlocks.XEN_CRYSTAL_CLUSTER, drops(ModBlocks.XEN_CRYSTAL_CLUSTER, ModItems.XEN_CRYSTAL));
        addDrop(ModBlocks.XEN_CRYSTAL_BLOCK);

        addDrop(ModBlocks.MOON_ROCK);
        addDrop(ModBlocks.MOON_ROCK_COAL_ORE, drops(ModBlocks.MOON_ROCK_COAL_ORE, Items.COAL));
        addDrop(ModBlocks.MOON_ROCK_IRON_ORE, drops(ModBlocks.MOON_ROCK_IRON_ORE, Items.RAW_IRON));
        addDrop(ModBlocks.MOON_ROCK_LUNAR_CALLAINUS_ORE, drops(ModBlocks.MOON_ROCK_LUNAR_CALLAINUS_ORE, ModItems.LUNAR_CALLAINUS_LUMP));

        addDrop(ModBlocks.STEEL_BLOCK);

        addDrop(ModBlocks.NETHER_SULFUR_ORE, drops(ModBlocks.NETHER_SULFUR_ORE, ModItems.SULFUR));
        addDrop(ModBlocks.SULFUR_BLOCK);

        addDrop(ModBlocks.LEAD_ORE, drops(ModBlocks.LEAD_ORE, ModItems.RAW_LEAD));
        addDrop(ModBlocks.RAW_LEAD_BLOCK);
        addDrop(ModBlocks.LEAD_BLOCK);

        addDrop(ModBlocks.DEEPSLATE_URANIUM_ORE, drops(ModBlocks.DEEPSLATE_URANIUM_ORE, ModItems.URANIUM));
        addDrop(ModBlocks.RAW_URANIUM_BLOCK);
        addDrop(ModBlocks.URANIUM_BLOCK);

        addDrop(ModBlocks.DEEPSLATE_HELIORITE_ORE, drops(ModBlocks.DEEPSLATE_HELIORITE_ORE, ModItems.HELIORITE_COMB));
        addDrop(ModBlocks.HELIORITE_COMB_BLOCK);
        addDrop(ModBlocks.HELIORITE_BLOCK);

        addDrop(ModBlocks.END_ENDURIUM_ORE, drops(ModBlocks.END_ENDURIUM_ORE, ModItems.ENDURIUM_CRYSTAL));
        addDrop(ModBlocks.RAW_ENDURIUM_BLOCK);
        addDrop(ModBlocks.ENDURIUM_BLOCK);

        addDrop(ModBlocks.DEEPSLATE_PALLADIUM_ORE, drops(ModBlocks.DEEPSLATE_PALLADIUM_ORE, ModItems.RAW_PALLADIUM));
        addDrop(ModBlocks.RAW_PALLADIUM_BLOCK);
        addDrop(ModBlocks.PALLADIUM_BLOCK);

        addDrop(ModBlocks.DEEPSLATE_JURASSOLINE_ORE, drops(ModBlocks.DEEPSLATE_JURASSOLINE_ORE, ModItems.JURASSOLINE_CRYSTAL));
        addDrop(ModBlocks.JURASSOLINE_CRYSTAL_BLOCK);
        addDrop(ModBlocks.JURASSOLINE_BLOCK);

        addDrop(ModBlocks.DEEPSLATE_CINNABAR_ORE, drops(ModBlocks.DEEPSLATE_CINNABAR_ORE, ModItems.CINNABAR_CRYSTAL));
        addDrop(ModBlocks.CINNABAR_CRYSTAL_BLOCK);
        addDrop(ModBlocks.CINNABAR_BLOCK);

        addDrop(ModBlocks.DEEPSLATE_NEBULAR_ORE, drops(ModBlocks.DEEPSLATE_NEBULAR_ORE, ModItems.NEBULAR_CRYSTAL));
        addDrop(ModBlocks.RAW_NEBULAR_BLOCK);
        addDrop(ModBlocks.NEBULAR_BLOCK);

        addDrop(ModBlocks.BEDROCK_MITHRIL_ORE, drops(ModBlocks.BEDROCK_MITHRIL_ORE, ModItems.RAW_MITHRIL));
        addDrop(ModBlocks.RAW_MITHRIL_BLOCK);
        addDrop(ModBlocks.MITHRIL_BLOCK);

        addDrop(ModBlocks.MITHRIL_ANVIL);
        addDrop(ModBlocks.MITHRIL_BARS);

        addDrop(ModBlocks.WOODEN_STEIN);
        addDrop(ModBlocks.WOODEN_WATER_STEIN);
        addDrop(ModBlocks.WOODEN_APPLE_JUICE_STEIN);
        addDrop(ModBlocks.WOODEN_ORANGE_JUICE_STEIN);
        addDrop(ModBlocks.WOODEN_BEER_STEIN);
        addDrop(ModBlocks.WOODEN_MILK_STEIN);
        addDrop(ModBlocks.WOODEN_STRAWBERRY_MILKSHAKE_STEIN);
        addDrop(ModBlocks.WOODEN_ORANGE_MILKSHAKE_STEIN);
        addDrop(ModBlocks.WOODEN_BLUE_BERRY_MILKSHAKE_STEIN);
        addDrop(ModBlocks.WOODEN_CHOCOLATE_MILKSHAKE_STEIN);
        addDrop(ModBlocks.HOT_WOODEN_MILK_STEIN);
        addDrop(ModBlocks.WOODEN_HOT_CHOCOLATE_STEIN);

        addDrop(ModBlocks.GLOBUS_CRUCIGER);

        addDrop(ModBlocks.GARBAGE_CAN);

        addDrop(ModBlocks.CREEPER_PLUSHIE);
        addDrop(ModBlocks.ZOMBIE_PLUSHIE);
        addDrop(ModBlocks.PLAYER_PLUSHIE);

        addDrop(ModBlocks.RADIO);
        addDrop(ModBlocks.RADIO_ON, drops(ModBlocks.RADIO));
        addDrop(ModBlocks.TELEVISION);
        addDrop(ModBlocks.TELEVISION_ON, drops(ModBlocks.TELEVISION));
        addDrop(ModBlocks.COMPUTER);
        addDrop(ModBlocks.COMPUTER_ON, drops(ModBlocks.COMPUTER));
        addDrop(ModBlocks.INTERDIMENSIONAL_RECEIVER);
        addDrop(ModBlocks.VIDEOCASSETTE_RECORDER);
        addDrop(ModBlocks.OXYGEN_COLLECTOR);

        addDrop(ModBlocks.SIGNAL_TRANSMITTER_ANTENNA);
        addDrop(ModBlocks.SIGNAL_TRANSMITTER);

        addDrop(ModBlocks.MINING_ELEVATOR_CONTROLLER);
        addDrop(ModBlocks.MINING_ELEVATOR_BASE);
        addDrop(ModBlocks.WOODEN_ELEVATOR_CONTROLLER);
        addDrop(ModBlocks.WOODEN_ELEVATOR_BASE);
        addDrop(ModBlocks.QUARTZ_ELEVATOR_CONTROLLER);
        addDrop(ModBlocks.QUARTZ_ELEVATOR_BASE);
        addDrop(ModBlocks.GLASS_ELEVATOR_CONTROLLER);
        addDrop(ModBlocks.GLASS_ELEVATOR_BASE);

        addDrop(ModBlocks.TREX_HEAD);
        addDrop(ModBlocks.GREEN_TREX_HEAD);
        addDrop(ModBlocks.GRAY_TREX_HEAD);
        addDrop(ModBlocks.SCULK_TREX_HEAD);
        addDrop(ModBlocks.INFECTED_TREX_HEAD);
        addDrop(ModBlocks.SICKENED_TREX_HEAD);

        addDrop(ModBlocks.ROSE);
        addPottedPlantDrops(ModBlocks.POTTED_ROSE);
        addDrop(ModBlocks.IRIS);
        addPottedPlantDrops(ModBlocks.POTTED_IRIS);
        addDrop(ModBlocks.EDELWEISS);
        addPottedPlantDrops(ModBlocks.POTTED_EDELWEISS);
        addDrop(ModBlocks.ATHELAS);
        addPottedPlantDrops(ModBlocks.POTTED_ATHELAS);

        addDrop(ModBlocks.GLASS_TRAPDOOR);
        addDrop(ModBlocks.GLASS_DOOR, doorDrops(ModBlocks.GLASS_DOOR));

        addDrop(ModBlocks.GINKGO_SAPLING);
        addPottedPlantDrops(ModBlocks.POTTED_GINKGO_SAPLING);
        addDrop(ModBlocks.GINKGO_LEAVES, leavesDrops(ModBlocks.GINKGO_LEAVES, ModBlocks.GINKGO_SAPLING, 0.05f));
        addDrop(ModBlocks.GINKGO_LOG);
        addDrop(ModBlocks.GINKGO_WOOD);
        addDrop(ModBlocks.STRIPPED_GINKGO_LOG);
        addDrop(ModBlocks.STRIPPED_GINKGO_WOOD);
        addDrop(ModBlocks.GINKGO_PLANKS);

        addDrop(ModBlocks.GINKGO_STAIRS);
        addDrop(ModBlocks.GINKGO_FENCE);
        addDrop(ModBlocks.GINKGO_FENCE_GATE);
        addDrop(ModBlocks.GINKGO_BUTTON);
        addDrop(ModBlocks.GINKGO_PRESSURE_PLATE);
        addDrop(ModBlocks.GINKGO_TRAPDOOR);

        addDrop(ModBlocks.GINKGO_SLAB, slabDrops(ModBlocks.GINKGO_SLAB));
        addDrop(ModBlocks.GINKGO_DOOR, doorDrops(ModBlocks.GINKGO_DOOR));

        addDrop(ModBlocks.FANCY_OAK_PLANKS);
        addDrop(ModBlocks.FANCY_OAK_STAIRS);
        addDrop(ModBlocks.FANCY_OAK_SLAB, slabDrops(ModBlocks.FANCY_OAK_SLAB));
        addDrop(ModBlocks.FANCY_OAK_TRAPDOOR);

        addDrop(ModBlocks.FANCY_SPRUCE_PLANKS);
        addDrop(ModBlocks.FANCY_SPRUCE_STAIRS);
        addDrop(ModBlocks.FANCY_SPRUCE_SLAB, slabDrops(ModBlocks.FANCY_SPRUCE_SLAB));
        addDrop(ModBlocks.FANCY_SPRUCE_TRAPDOOR);

        addDrop(ModBlocks.FANCY_BIRCH_PLANKS);
        addDrop(ModBlocks.FANCY_BIRCH_STAIRS);
        addDrop(ModBlocks.FANCY_BIRCH_SLAB, slabDrops(ModBlocks.FANCY_BIRCH_SLAB));
        addDrop(ModBlocks.FANCY_BIRCH_TRAPDOOR);

        addDrop(ModBlocks.FANCY_JUNGLE_PLANKS);
        addDrop(ModBlocks.FANCY_JUNGLE_STAIRS);
        addDrop(ModBlocks.FANCY_JUNGLE_SLAB, slabDrops(ModBlocks.FANCY_JUNGLE_SLAB));
        addDrop(ModBlocks.FANCY_JUNGLE_TRAPDOOR);

        addDrop(ModBlocks.FANCY_ACACIA_PLANKS);
        addDrop(ModBlocks.FANCY_ACACIA_STAIRS);
        addDrop(ModBlocks.FANCY_ACACIA_SLAB, slabDrops(ModBlocks.FANCY_ACACIA_SLAB));
        addDrop(ModBlocks.FANCY_ACACIA_TRAPDOOR);

        addDrop(ModBlocks.FANCY_DARK_OAK_PLANKS);
        addDrop(ModBlocks.FANCY_DARK_OAK_STAIRS);
        addDrop(ModBlocks.FANCY_DARK_OAK_SLAB, slabDrops(ModBlocks.FANCY_DARK_OAK_SLAB));
        addDrop(ModBlocks.FANCY_DARK_OAK_TRAPDOOR);

        addDrop(ModBlocks.FANCY_MANGROVE_PLANKS);
        addDrop(ModBlocks.FANCY_MANGROVE_STAIRS);
        addDrop(ModBlocks.FANCY_MANGROVE_SLAB, slabDrops(ModBlocks.FANCY_MANGROVE_SLAB));
        addDrop(ModBlocks.FANCY_MANGROVE_TRAPDOOR);

        addDrop(ModBlocks.FANCY_CHERRY_PLANKS);
        addDrop(ModBlocks.FANCY_CHERRY_STAIRS);
        addDrop(ModBlocks.FANCY_CHERRY_SLAB, slabDrops(ModBlocks.FANCY_CHERRY_SLAB));
        addDrop(ModBlocks.FANCY_CHERRY_TRAPDOOR);

        addDrop(ModBlocks.FANCY_PALE_OAK_PLANKS);
        addDrop(ModBlocks.FANCY_PALE_OAK_STAIRS);
        addDrop(ModBlocks.FANCY_PALE_OAK_SLAB, slabDrops(ModBlocks.FANCY_PALE_OAK_SLAB));
        addDrop(ModBlocks.FANCY_PALE_OAK_TRAPDOOR);

        addDrop(ModBlocks.FANCY_BAMBOO_PLANKS);
        addDrop(ModBlocks.FANCY_BAMBOO_STAIRS);
        addDrop(ModBlocks.FANCY_BAMBOO_SLAB, slabDrops(ModBlocks.FANCY_BAMBOO_SLAB));
        addDrop(ModBlocks.FANCY_BAMBOO_TRAPDOOR);

        addDrop(ModBlocks.FANCY_CRIMSON_PLANKS);
        addDrop(ModBlocks.FANCY_CRIMSON_STAIRS);
        addDrop(ModBlocks.FANCY_CRIMSON_SLAB, slabDrops(ModBlocks.FANCY_CRIMSON_SLAB));
        addDrop(ModBlocks.FANCY_CRIMSON_TRAPDOOR);

        addDrop(ModBlocks.FANCY_WARPED_PLANKS);
        addDrop(ModBlocks.FANCY_WARPED_STAIRS);
        addDrop(ModBlocks.FANCY_WARPED_SLAB, slabDrops(ModBlocks.FANCY_WARPED_SLAB));
        addDrop(ModBlocks.FANCY_WARPED_TRAPDOOR);

        addDrop(ModBlocks.FANCY_GINKGO_PLANKS);
        addDrop(ModBlocks.FANCY_GINKGO_STAIRS);
        addDrop(ModBlocks.FANCY_GINKGO_SLAB, slabDrops(ModBlocks.FANCY_GINKGO_SLAB));
        addDrop(ModBlocks.FANCY_GINKGO_TRAPDOOR);

        addDrop(ModBlocks.FANCY_CHARRED_PLANKS);
        addDrop(ModBlocks.FANCY_CHARRED_STAIRS);
        addDrop(ModBlocks.FANCY_CHARRED_SLAB, slabDrops(ModBlocks.FANCY_CHARRED_SLAB));
        addDrop(ModBlocks.FANCY_CHARRED_TRAPDOOR);
        addDrop(ModBlocks.RITUALISTIC_FANCY_CHARRED_PLANKS, drops(ModBlocks.FANCY_CHARRED_PLANKS));

        addDrop(ModBlocks.ARCHAEOLOGY_TABLE);
    }
}
