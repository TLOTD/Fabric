package net.tlotd.block;

import com.terraformersmc.terraform.sign.block.TerraformHangingSignBlock;
import com.terraformersmc.terraform.sign.block.TerraformSignBlock;
import com.terraformersmc.terraform.sign.block.TerraformWallHangingSignBlock;
import com.terraformersmc.terraform.sign.block.TerraformWallSignBlock;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.*;
import net.minecraft.block.enums.Instrument;
import net.minecraft.block.piston.PistonBehavior;
import net.minecraft.data.family.BlockFamilies;
import net.minecraft.data.family.BlockFamily;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;
import net.tlotd.TLOTD;
import net.tlotd.block.custom.*;
import net.tlotd.item.ModFoodComponents;
import net.tlotd.item.compat.CompatBlockItem;
import net.tlotd.item.custom.*;
import net.tlotd.world.tree.GinkgoSaplingGenerator;

public class ModBlocks {

    public static final Block STICK_CROSS = registerBlock("stick_cross",
            new StickCrossBlock(FabricBlockSettings.create().mapColor(MapColor.BROWN).breakInstantly().sounds(BlockSoundGroup.WOOD).pistonBehavior(PistonBehavior.DESTROY).collidable(false).nonOpaque()));

    public static final Block WHITE_PUMPKIN = registerBlock("white_pumpkin",
            new WhitePumkinBlock(FabricBlockSettings.create().mapColor(MapColor.WHITE).instrument(Instrument.DIDGERIDOO).strength(1f, 1f).sounds(BlockSoundGroup.WOOD).pistonBehavior(PistonBehavior.DESTROY)));
    public static final Block CARVED_WHITE_PUMPKIN = registerBlock("carved_white_pumpkin",
            new WearableCarvedPumpkinBlock(FabricBlockSettings.create().mapColor(MapColor.WHITE).strength(1f, 1f).sounds(BlockSoundGroup.WOOD).allowsSpawning(Blocks::always).pistonBehavior(PistonBehavior.DESTROY)));
    public static final Block WHITE_JACK_O_LANTERN = registerBlock("white_jack_o_lantern",
            new CarvedPumpkinBlock(FabricBlockSettings.create().mapColor(MapColor.WHITE).strength(1f, 1f).sounds(BlockSoundGroup.WOOD).luminance(14).allowsSpawning(Blocks::always).pistonBehavior(PistonBehavior.DESTROY)));

    public static final Block SULFUR_TORCH = registerBlockWithoutItem("sulfur_torch", new TorchBlock(FabricBlockSettings.create().noCollision().breakInstantly().luminance(13).sounds(BlockSoundGroup.WOOD).pistonBehavior(PistonBehavior.DESTROY), ParticleTypes.SOUL_FIRE_FLAME));
    public static final Block SULFUR_WALL_TORCH = registerBlockWithoutItem("sulfur_wall_torch", new WallTorchBlock(FabricBlockSettings.create().noCollision().breakInstantly().luminance(13).sounds(BlockSoundGroup.WOOD).pistonBehavior(PistonBehavior.DESTROY), ParticleTypes.SOUL_FIRE_FLAME));

    public static final Block EXTINGUISHED_TORCH = registerBlockWithoutItem("extinguished_torch", new ExtinguishedTorchBlock(FabricBlockSettings.create().noCollision().breakInstantly().sounds(BlockSoundGroup.WOOD).pistonBehavior(PistonBehavior.DESTROY), ParticleTypes.ASH));
    public static final Block EXTINGUISHED_WALL_TORCH = registerBlockWithoutItem("extinguished_wall_torch", new ExtinguishedWallTorchBlock(FabricBlockSettings.create().noCollision().breakInstantly().sounds(BlockSoundGroup.WOOD).pistonBehavior(PistonBehavior.DESTROY), ParticleTypes.ASH));
    public static final Block EXTINGUISHED_SOUL_TORCH = registerBlockWithoutItem("extinguished_soul_torch", new ExtinguishedTorchBlock(FabricBlockSettings.create().noCollision().breakInstantly().sounds(BlockSoundGroup.WOOD).pistonBehavior(PistonBehavior.DESTROY), ParticleTypes.ASH));
    public static final Block EXTINGUISHED_SOUL_WALL_TORCH = registerBlockWithoutItem("extinguished_soul_wall_torch", new ExtinguishedWallTorchBlock(FabricBlockSettings.create().noCollision().breakInstantly().sounds(BlockSoundGroup.WOOD).pistonBehavior(PistonBehavior.DESTROY), ParticleTypes.ASH));
    public static final Block EXTINGUISHED_SULFUR_TORCH = registerBlockWithoutItem("extinguished_sulfur_torch", new ExtinguishedTorchBlock(FabricBlockSettings.create().noCollision().breakInstantly().sounds(BlockSoundGroup.WOOD).pistonBehavior(PistonBehavior.DESTROY), ParticleTypes.ASH));
    public static final Block EXTINGUISHED_SULFUR_WALL_TORCH = registerBlockWithoutItem("extinguished_sulfur_wall_torch", new ExtinguishedWallTorchBlock(FabricBlockSettings.create().noCollision().breakInstantly().sounds(BlockSoundGroup.WOOD).pistonBehavior(PistonBehavior.DESTROY), ParticleTypes.ASH));

    public static final Block SULFUR_LANTERN = registerBlock("sulfur_lantern", new LanternBlock(FabricBlockSettings.create().mapColor(MapColor.IRON_GRAY).solid().requiresTool().strength(3.5F).sounds(BlockSoundGroup.LANTERN).luminance(14).nonOpaque().pistonBehavior(PistonBehavior.DESTROY)));
    public static final Block SULFUR_CAMPFIRE = registerNyiBlock("sulfur_campfire", new CampfireBlock(false, 2, FabricBlockSettings.create().mapColor(MapColor.SPRUCE_BROWN).instrument(Instrument.BASS).strength(2.0f).sounds(BlockSoundGroup.WOOD).luminance(Blocks.createLightLevelFromLitBlockState(14)).nonOpaque().burnable()));

    public static final Block PRESERVES_JAR = registerSmallStackableBlock("preserves_jar",
            new PreservesJarBlock(FabricBlockSettings.create().mapColor(MapColor.WHITE).strength(0.3f, 0.3f).sounds(BlockSoundGroup.GLASS).nonOpaque()));

    public static final Block SWEET_BERRY_JAM_JAR = registerJamJarBlock("sweet_berry_jam_jar",
            new PreservesJarBlock(FabricBlockSettings.create().mapColor(MapColor.WHITE).strength(0.3f, 0.3f).sounds(BlockSoundGroup.GLASS).nonOpaque()));
    public static final Block GLOW_BERRY_JAM_JAR = registerJamJarBlock("glow_berry_jam_jar",
            new PreservesJarBlock(FabricBlockSettings.create().mapColor(MapColor.WHITE).strength(0.3f, 0.3f).sounds(BlockSoundGroup.GLASS).nonOpaque()));
    public static final Block STRAWBERRY_JAM_JAR = registerJamJarBlock("strawberry_jam_jar",
            new PreservesJarBlock(FabricBlockSettings.create().mapColor(MapColor.WHITE).strength(0.3f, 0.3f).sounds(BlockSoundGroup.GLASS).nonOpaque()));
    public static final Block ORANGE_MARMELADE_JAR = registerJamJarBlock("orange_marmelade_jar",
            new PreservesJarBlock(FabricBlockSettings.create().mapColor(MapColor.WHITE).strength(0.3f, 0.3f).sounds(BlockSoundGroup.GLASS).nonOpaque()));

    public static final Block BLUE_BERRY_JAM_JAR = registerCompatJamJarBlock("blue_berry_jam_jar",
            new PreservesJarBlock(FabricBlockSettings.create().mapColor(MapColor.WHITE).strength(0.3f, 0.3f).sounds(BlockSoundGroup.GLASS).nonOpaque()),"aet");

    public static final Block ANCIENT_SOULBERRY_JAM_JAR = registerCompatJamJarBlock("ancient_soulberry_jam_jar",
            new PreservesJarBlock(FabricBlockSettings.create().mapColor(MapColor.WHITE).strength(0.3f, 0.3f).sounds(BlockSoundGroup.GLASS).nonOpaque()),"atm");

    public static final Block TREX_EGG = registerBlock("t-rex_egg",
            new TRexEggBlock(FabricBlockSettings.create().mapColor(MapColor.BROWN).strength(0.5f, 0.5f).sounds(BlockSoundGroup.METAL).nonOpaque()));

    public static final Block BW_STICKER = registerBlock("bw_sticker",
            new StickerBlock(FabricBlockSettings.create().mapColor(MapColor.WHITE).breakInstantly().sounds(BlockSoundGroup.GRASS).pistonBehavior(PistonBehavior.DESTROY).collidable(false).nonOpaque()));
    public static final Block TLOTD_STICKER = registerBlock("tlotd_sticker",
            new StickerBlock(FabricBlockSettings.create().mapColor(MapColor.WHITE).breakInstantly().sounds(BlockSoundGroup.GRASS).pistonBehavior(PistonBehavior.DESTROY).collidable(false).nonOpaque()));

    public static final Block PRESENT = registerBlock("present",
            new PresentBlock(FabricBlockSettings.create().mapColor(MapColor.RED).strength(0.8F, 0.8F).sounds(BlockSoundGroup.WOOL).pistonBehavior(PistonBehavior.DESTROY).nonOpaque()));
    public static final Block FESTIVE_LIGHTS = registerBlock("festive_lights",
            new FestiveLightsBlock(FabricBlockSettings.create().mapColor(MapColor.BLACK).strength(0.8F, 0.8F).sounds(BlockSoundGroup.WOOL).pistonBehavior(PistonBehavior.DESTROY).collidable(false).nonOpaque().luminance(12)));

    public static final Block APPARATUS = registerIrradiatedBlock("apparatus",
            new ApparatusBlock(FabricBlockSettings.create().mapColor(MapColor.PALE_YELLOW).strength(2.0F, 2.0F).sounds(BlockSoundGroup.GLASS).nonOpaque().luminance(15)));

    public static final Block BLOOD_CAULDRON = registerBlockWithoutItem("blood_cauldron",
            new BloodCauldronBlock(FabricBlockSettings.create().mapColor(MapColor.STONE_GRAY).requiresTool().strength(2.0F, 2.0F).nonOpaque()));

    public static final Block STRAWBERRY_BUSH = registerBlockWithoutItem("strawberry_bush",
            new StrawberryBushBlock(FabricBlockSettings.create().mapColor(MapColor.DARK_GREEN).noCollision().ticksRandomly().breakInstantly().sounds(BlockSoundGroup.CROP).pistonBehavior(PistonBehavior.DESTROY)));
    public static final Block ORANGE_TREE = registerBlockWithoutItem("orange_tree",
            new OrangeTreeBlock(FabricBlockSettings.create().mapColor(MapColor.DARK_GREEN).noCollision().ticksRandomly().breakInstantly().sounds(BlockSoundGroup.CROP).pistonBehavior(PistonBehavior.DESTROY)));
    public static final Block PIPE_WEED_PLANT = registerBlockWithoutItem("pipe_weed_plant",
            new PipeWeedPlantBlock(FabricBlockSettings.create().mapColor(MapColor.DARK_GREEN).noCollision().ticksRandomly().breakInstantly().sounds(BlockSoundGroup.CROP).pistonBehavior(PistonBehavior.DESTROY)));

    public static final Block STRAWBERRY_CAKE = registerBlock("strawberry_cake", new CakeBlock(FabricBlockSettings.copyOf(Blocks.CAKE)));
    public static final Block ORANGE_CAKE = registerBlock("orange_cake", new CakeBlock(FabricBlockSettings.copyOf(Blocks.CAKE)));

    public static final Block GRAVESTONE = registerBlock("gravestone",
            new GravestoneBlock(FabricBlockSettings.create().mapColor(MapColor.STONE_GRAY).requiresTool().strength(2.0F, 3.0F)));
    public static final Block MOSSY_GRAVESTONE = registerBlock("mossy_gravestone",
            new GravestoneBlock(FabricBlockSettings.create().mapColor(MapColor.STONE_GRAY).requiresTool().strength(2.0F, 3.0F)));
    public static final Block GRAVESTONE_CROSS = registerBlock("gravestone_cross",
            new GravestoneBlock(FabricBlockSettings.create().mapColor(MapColor.STONE_GRAY).requiresTool().strength(2.0F, 3.0F)));
    public static final Block MOSSY_GRAVESTONE_CROSS = registerBlock("mossy_gravestone_cross",
            new GravestoneBlock(FabricBlockSettings.create().mapColor(MapColor.STONE_GRAY).requiresTool().strength(2.0F, 3.0F)));

    public static final Block SMALL_GRAVESTONE = registerBlock("small_gravestone",
            new SmallGravestoneBlock(FabricBlockSettings.create().mapColor(MapColor.STONE_GRAY).requiresTool().strength(2.0F, 3.0F)));

    public static final Block SKELETON = registerBlock("skeleton",
            new SkeletonBlock(FabricBlockSettings.create().mapColor(MapColor.WHITE).noCollision().sounds(BlockSoundGroup.BONE).pistonBehavior(PistonBehavior.DESTROY).strength(1.0F, 1.0F)));
    public static final Block EMERGING_SKELETON = registerBlock("emerging_skeleton",
            new SkeletonBlock(FabricBlockSettings.create().mapColor(MapColor.WHITE).noCollision().sounds(BlockSoundGroup.BONE).pistonBehavior(PistonBehavior.DESTROY).strength(1.0F, 1.0F)));

    public static final Block EFFIGIES = registerBlock("effigies",
            new EffigiesBlock(FabricBlockSettings.create().mapColor(MapColor.BROWN).strength(2.0F, 3.0F).sounds(BlockSoundGroup.WOOD).pistonBehavior(PistonBehavior.DESTROY).nonOpaque().collidable(false).ticksRandomly()));

    public static final Block WITCHING_TABLE = registerBlock("witching_table",
            new WitchingTableBlock(FabricBlockSettings.create().mapColor(MapColor.BROWN).strength(2.0F, 3.0F).sounds(BlockSoundGroup.WOOD).nonOpaque()));

    public static final Block OUIJA_BOARD = registerBlock("ouija_board",
            new OuijaBoardBlock(FabricBlockSettings.create().mapColor(MapColor.OAK_TAN).strength(1.0F, 1.0F).sounds(BlockSoundGroup.WOOL).pistonBehavior(PistonBehavior.DESTROY)));

    public static final Block GOAT_HEAD = registerRarityBlock("goat_head",
            new GoatHeadBlock(FabricBlockSettings.create().mapColor(MapColor.WHITE).strength(2.0F, 3.0F).nonOpaque().pistonBehavior(PistonBehavior.DESTROY)), Rarity.UNCOMMON);

    public static final Block MARBLE = registerBlock("marble",
            new Block(FabricBlockSettings.create().mapColor(MapColor.WHITE).requiresTool().strength(2.75F, 6.0F)));
    public static final Block MARBLE_STAIRS = registerBlock("marble_stairs",
            new StairsBlock(ModBlocks.MARBLE.getDefaultState(), FabricBlockSettings.create().mapColor(MapColor.WHITE).requiresTool().strength(2.75F, 6.0F)));
    public static final Block MARBLE_SLAB = registerBlock("marble_slab",
            new SlabBlock(FabricBlockSettings.create().mapColor(MapColor.WHITE).requiresTool().strength(2.75F, 6.0F)));
    public static final Block MARBLE_WALL = registerBlock("marble_wall",
            new WallBlock(FabricBlockSettings.create().mapColor(MapColor.WHITE).requiresTool().strength(2.75F, 6.0F)));
    public static final Block MARBLE_BUTTON = registerBlock("marble_button",
            new ButtonBlock(FabricBlockSettings.create().mapColor(MapColor.WHITE).requiresTool().strength(0.5F, 0.5F).collidable(false), BlockSetType.STONE, 20, false));
    public static final Block MARBLE_PRESSURE_PLATE = registerBlock("marble_pressure_plate",
            new PressurePlateBlock(PressurePlateBlock.ActivationRule.MOBS, FabricBlockSettings.create().mapColor(MapColor.WHITE).requiresTool().strength(0.5F, 0.5F).collidable(false), BlockSetType.STONE));

    public static final Block LIMESTONE = registerBlock("limestone",
            new Block(FabricBlockSettings.create().mapColor(MapColor.PALE_YELLOW).requiresTool().strength(1.75F, 3.0F)));
    public static final Block LIMESTONE_STAIRS = registerBlock("limestone_stairs",
            new StairsBlock(ModBlocks.LIMESTONE.getDefaultState(), FabricBlockSettings.create().mapColor(MapColor.PALE_YELLOW).requiresTool().strength(1.75F, 3.0F)));
    public static final Block LIMESTONE_SLAB = registerBlock("limestone_slab",
            new SlabBlock(FabricBlockSettings.create().mapColor(MapColor.PALE_YELLOW).requiresTool().strength(1.75F, 3.0F)));
    public static final Block LIMESTONE_WALL = registerBlock("limestone_wall",
            new WallBlock(FabricBlockSettings.create().mapColor(MapColor.PALE_YELLOW).requiresTool().strength(1.75F, 3.0F)));
    public static final Block LIMESTONE_BUTTON = registerBlock("limestone_button",
            new ButtonBlock(FabricBlockSettings.create().mapColor(MapColor.PALE_YELLOW).requiresTool().strength(0.5F, 0.5F).collidable(false), BlockSetType.STONE, 20, false));
    public static final Block LIMESTONE_PRESSURE_PLATE = registerBlock("limestone_pressure_plate",
            new PressurePlateBlock(PressurePlateBlock.ActivationRule.MOBS, FabricBlockSettings.create().mapColor(MapColor.PALE_YELLOW).requiresTool().strength(0.5F, 0.5F).collidable(false), BlockSetType.STONE));

    public static final Block RICH_DIRT = registerBlock("rich_dirt",
            new RichDirtBlock(FabricBlockSettings.create().mapColor(MapColor.BROWN).requiresTool().strength(0.5F, 0.5F).sounds(BlockSoundGroup.GRAVEL)));
    public static final Block RICH_FARMLAND = registerBlock("rich_farmland",
            new RichFarmlandBlock(FabricBlockSettings.create().mapColor(MapColor.BROWN).requiresTool().strength(0.5F, 0.5F).sounds(BlockSoundGroup.GRAVEL)));
    public static final Block RICH_DIRT_PATH = registerBlock("rich_dirt_path",
            new RichDirtPathBlock(FabricBlockSettings.create().mapColor(MapColor.BROWN).requiresTool().strength(0.5F, 0.5F).sounds(BlockSoundGroup.GRAVEL)));
    public static final Block RICH_GRASS_BLOCK = registerBlock("rich_grass_block",
            new RichGrassBlock(FabricBlockSettings.create().mapColor(MapColor.PALE_GREEN).requiresTool().strength(0.6F, 0.6F).sounds(BlockSoundGroup.GRASS).ticksRandomly()));

    public static final Block RED_GRAVEL = registerBlock("red_gravel",
            new Block(FabricBlockSettings.create().mapColor(MapColor.DULL_RED).requiresTool().strength(0.6F, 0.6F).sounds(BlockSoundGroup.GRAVEL)));
    public static final Block RED_SANDY_DEEPSLATE = registerBlock("red_sandy_deepslate",
            new Block(FabricBlockSettings.create().mapColor(MapColor.DULL_RED).requiresTool().strength(2.75F, 6.0F).sounds(BlockSoundGroup.SAND)));

    public static final Block RED_DEEPSLATE = registerBlock("red_deepslate",
            new Block(FabricBlockSettings.create().mapColor(MapColor.DULL_RED).requiresTool().strength(2.75F, 6.0F).sounds(BlockSoundGroup.DEEPSLATE)));
    public static final Block RED_DEEPSLATE_STAIRS = registerBlock("red_deepslate_stairs",
            new StairsBlock(ModBlocks.RED_DEEPSLATE.getDefaultState(), FabricBlockSettings.create().mapColor(MapColor.DULL_RED).requiresTool().strength(2.75F, 6.0F).sounds(BlockSoundGroup.DEEPSLATE)));
    public static final Block RED_DEEPSLATE_SLAB = registerBlock("red_deepslate_slab",
            new SlabBlock(FabricBlockSettings.create().mapColor(MapColor.DULL_RED).requiresTool().strength(2.75F, 6.0F).sounds(BlockSoundGroup.DEEPSLATE)));
    public static final Block RED_DEEPSLATE_WALL = registerBlock("red_deepslate_wall",
            new WallBlock(FabricBlockSettings.create().mapColor(MapColor.DULL_RED).requiresTool().strength(2.75F, 6.0F).sounds(BlockSoundGroup.DEEPSLATE)));
    public static final Block RED_DEEPSLATE_BUTTON = registerBlock("red_deepslate_button",
            new ButtonBlock(FabricBlockSettings.create().mapColor(MapColor.DULL_RED).requiresTool().strength(0.5F, 0.5F).collidable(false), BlockSetType.STONE, 20, false));
    public static final Block RED_DEEPSLATE_PRESSURE_PLATE = registerBlock("red_deepslate_pressure_plate",
            new PressurePlateBlock(PressurePlateBlock.ActivationRule.MOBS, FabricBlockSettings.create().mapColor(MapColor.DULL_RED).requiresTool().strength(0.5F, 0.5F).collidable(false), BlockSetType.STONE));
    public static final Block POLISHED_RED_DEEPSLATE = registerBlock("polished_red_deepslate",
            new Block(FabricBlockSettings.create().mapColor(MapColor.DULL_RED).requiresTool().strength(2.75F, 6.0F).sounds(BlockSoundGroup.DEEPSLATE)));
    public static final Block RED_DEEPSLATE_BRICKS = registerBlock("red_deepslate_bricks",
            new Block(FabricBlockSettings.create().mapColor(MapColor.DULL_RED).requiresTool().strength(2.75F, 6.0F).sounds(BlockSoundGroup.DEEPSLATE)));
    public static final Block RED_DEEPSLATE_BRICK_STAIRS = registerBlock("red_deepslate_brick_stairs",
            new StairsBlock(ModBlocks.RED_DEEPSLATE_BRICKS.getDefaultState(), FabricBlockSettings.create().mapColor(MapColor.DULL_RED).requiresTool().strength(2.75F, 6.0F).sounds(BlockSoundGroup.DEEPSLATE)));
    public static final Block RED_DEEPSLATE_BRICK_SLAB = registerBlock("red_deepslate_brick_slab",
            new SlabBlock(FabricBlockSettings.create().mapColor(MapColor.DULL_RED).requiresTool().strength(2.75F, 6.0F).sounds(BlockSoundGroup.DEEPSLATE)));
    public static final Block RED_DEEPSLATE_BRICK_WALL = registerBlock("red_deepslate_brick_wall",
            new WallBlock(FabricBlockSettings.create().mapColor(MapColor.DULL_RED).requiresTool().strength(2.75F, 6.0F).sounds(BlockSoundGroup.DEEPSLATE)));
    public static final Block CRACKED_RED_DEEPSLATE_BRICKS = registerBlock("cracked_red_deepslate_bricks",
            new Block(FabricBlockSettings.create().mapColor(MapColor.DULL_RED).requiresTool().strength(2.75F, 6.0F).sounds(BlockSoundGroup.DEEPSLATE)));
    public static final Block MOSSY_RED_DEEPSLATE_BRICKS = registerBlock("mossy_red_deepslate_bricks",
            new Block(FabricBlockSettings.create().mapColor(MapColor.DULL_RED).requiresTool().strength(2.75F, 6.0F).sounds(BlockSoundGroup.DEEPSLATE)));
    public static final Block COBBLED_RED_DEEPSLATE = registerBlock("cobbled_red_deepslate",
            new Block(FabricBlockSettings.create().mapColor(MapColor.DULL_RED).requiresTool().strength(2.75F, 6.0F).sounds(BlockSoundGroup.DEEPSLATE)));
    public static final Block COBBLED_RED_DEEPSLATE_STAIRS = registerBlock("cobbled_red_deepslate_stairs",
            new StairsBlock(ModBlocks.COBBLED_RED_DEEPSLATE.getDefaultState(), FabricBlockSettings.create().mapColor(MapColor.DULL_RED).requiresTool().strength(2.75F, 6.0F).sounds(BlockSoundGroup.DEEPSLATE)));
    public static final Block COBBLED_RED_DEEPSLATE_SLAB = registerBlock("cobbled_red_deepslate_slab",
            new SlabBlock(FabricBlockSettings.create().mapColor(MapColor.DULL_RED).requiresTool().strength(2.75F, 6.0F).sounds(BlockSoundGroup.DEEPSLATE)));
    public static final Block COBBLED_RED_DEEPSLATE_WALL = registerBlock("cobbled_red_deepslate_wall",
            new WallBlock(FabricBlockSettings.create().mapColor(MapColor.DULL_RED).requiresTool().strength(2.75F, 6.0F).sounds(BlockSoundGroup.DEEPSLATE)));
    public static final Block POLISHED_COBBLED_RED_DEEPSLATE = registerBlock("polished_cobbled_red_deepslate",
            new Block(FabricBlockSettings.create().mapColor(MapColor.DULL_RED).requiresTool().strength(2.75F, 6.0F).sounds(BlockSoundGroup.DEEPSLATE)));
    public static final Block COBBLED_RED_DEEPSLATE_BRICKS = registerBlock("cobbled_red_deepslate_bricks",
            new Block(FabricBlockSettings.create().mapColor(MapColor.DULL_RED).requiresTool().strength(2.75F, 6.0F).sounds(BlockSoundGroup.DEEPSLATE)));
    public static final Block COBBLED_RED_DEEPSLATE_BRICK_STAIRS = registerBlock("cobbled_red_deepslate_brick_stairs",
            new StairsBlock(ModBlocks.COBBLED_RED_DEEPSLATE_BRICKS.getDefaultState(), FabricBlockSettings.create().mapColor(MapColor.DULL_RED).requiresTool().strength(2.75F, 6.0F).sounds(BlockSoundGroup.DEEPSLATE)));
    public static final Block COBBLED_RED_DEEPSLATE_BRICK_SLAB = registerBlock("cobbled_red_deepslate_brick_slab",
            new SlabBlock(FabricBlockSettings.create().mapColor(MapColor.DULL_RED).requiresTool().strength(2.75F, 6.0F).sounds(BlockSoundGroup.DEEPSLATE)));
    public static final Block COBBLED_RED_DEEPSLATE_BRICK_WALL = registerBlock("cobbled_red_deepslate_brick_wall",
            new WallBlock(FabricBlockSettings.create().mapColor(MapColor.DULL_RED).requiresTool().strength(2.75F, 6.0F).sounds(BlockSoundGroup.DEEPSLATE)));
    public static final Block CRACKED_COBBLED_RED_DEEPSLATE_BRICKS = registerBlock("cracked_cobbled_red_deepslate_bricks",
            new Block(FabricBlockSettings.create().mapColor(MapColor.DULL_RED).requiresTool().strength(2.75F, 6.0F).sounds(BlockSoundGroup.DEEPSLATE)));
    public static final Block MOSSY_COBBLED_RED_DEEPSLATE_BRICKS = registerBlock("mossy_cobbled_red_deepslate_bricks",
            new Block(FabricBlockSettings.create().mapColor(MapColor.DULL_RED).requiresTool().strength(2.75F, 6.0F).sounds(BlockSoundGroup.DEEPSLATE)));
    public static final Block REINFORCED_RED_DEEPSLATE = registerBlock("reinforced_red_deepslate",
            new Block(FabricBlockSettings.create().mapColor(MapColor.DULL_RED).requiresTool().strength(55.0F, 1200.0F).sounds(BlockSoundGroup.DEEPSLATE)));

    public static final Block RED_DEEPSLATE_FOSSIL = registerBlock("red_deepslate_fossil",
            new Block(FabricBlockSettings.create().mapColor(MapColor.PALE_YELLOW).requiresTool().strength(3.0F, 3.0F).sounds(BlockSoundGroup.DEEPSLATE)));
    public static final Block RED_DEEPSLATE_IRON_ORE = registerBlock("red_deepslate_iron_ore",
            new Block(FabricBlockSettings.create().mapColor(MapColor.RAW_IRON_PINK).requiresTool().strength(3.0F, 3.0F).sounds(BlockSoundGroup.DEEPSLATE)));
    public static final Block RED_DEEPSLATE_COPPER_ORE = registerBlock("red_deepslate_copper_ore",
            new Block(FabricBlockSettings.create().mapColor(MapColor.ORANGE).requiresTool().strength(3.0F, 3.0F).sounds(BlockSoundGroup.DEEPSLATE)));
    public static final Block RED_DEEPSLATE_GOLD_ORE = registerBlock("red_deepslate_gold_ore",
            new Block(FabricBlockSettings.create().mapColor(MapColor.GOLD).requiresTool().strength(3.0F, 3.0F).sounds(BlockSoundGroup.DEEPSLATE)));
    public static final Block RED_DEEPSLATE_REDSTONE_ORE = registerBlock("red_deepslate_redstone_ore",
            new Block(FabricBlockSettings.create().mapColor(MapColor.RED).requiresTool().strength(3.0F, 3.0F).sounds(BlockSoundGroup.DEEPSLATE)));
    public static final Block RED_DEEPSLATE_EMERALD_ORE = registerBlock("red_deepslate_emerald_ore",
            new Block(FabricBlockSettings.create().mapColor(MapColor.EMERALD_GREEN).requiresTool().strength(3.0F, 3.0F).sounds(BlockSoundGroup.DEEPSLATE)));
    public static final Block RED_DEEPSLATE_LAPIS_ORE = registerBlock("red_deepslate_lapis_ore",
            new Block(FabricBlockSettings.create().mapColor(MapColor.LAPIS_BLUE).requiresTool().strength(3.0F, 3.0F).sounds(BlockSoundGroup.DEEPSLATE)));
    public static final Block RED_DEEPSLATE_DIAMOND_ORE = registerBlock("red_deepslate_diamond_ore",
            new Block(FabricBlockSettings.create().mapColor(MapColor.DIAMOND_BLUE).requiresTool().strength(3.0F, 3.0F).sounds(BlockSoundGroup.DEEPSLATE)));

    public static final Block RED_DEEPSLATE_ZINC_ORE = registerCompatBlock("red_deepslate_zinc_ore",
            new Block(FabricBlockSettings.create().mapColor(MapColor.LIGHT_GRAY).requiresTool().strength(3.0F, 3.0F).sounds(BlockSoundGroup.DEEPSLATE)),Rarity.COMMON,"cre");

    public static final Block PREHISTORIC_DEBRIS = registerBlock("prehistoric_debris",
            new Block(FabricBlockSettings.create().mapColor(MapColor.BROWN).requiresTool().strength(30.0F, 1200.0F).sounds(BlockSoundGroup.DEEPSLATE)));

    public static final Block RED_DEEPSLATE_SULFUR_ORE = registerBlock("red_deepslate_sulfur_ore",
            new Block(FabricBlockSettings.create().mapColor(MapColor.PALE_YELLOW).requiresTool().strength(3.0F, 3.0F).sounds(BlockSoundGroup.DEEPSLATE)));
    public static final Block RED_DEEPSLATE_QUARTZ_ORE = registerBlock("red_deepslate_quartz_ore",
            new Block(FabricBlockSettings.create().mapColor(MapColor.OFF_WHITE).requiresTool().strength(3.0F, 3.0F).sounds(BlockSoundGroup.DEEPSLATE)));
    public static final Block RED_DEEPSLATE_LEAD_ORE = registerBlock("red_deepslate_lead_ore",
            new Block(FabricBlockSettings.create().mapColor(MapColor.GRAY).requiresTool().strength(3.0F, 3.0F).sounds(BlockSoundGroup.DEEPSLATE)));
    public static final Block RED_DEEPSLATE_URANIUM_ORE = registerIrradiatedBlock("red_deepslate_uranium_ore",
            new Block(FabricBlockSettings.create().mapColor(MapColor.EMERALD_GREEN).requiresTool().strength(3.0F, 3.0F).sounds(BlockSoundGroup.DEEPSLATE)));
    public static final Block RED_DEEPSLATE_HELIORITE_ORE = registerBlock("red_deepslate_heliorite_ore",
            new Block(FabricBlockSettings.create().mapColor(MapColor.MAGENTA).requiresTool().strength(16.0F, 1200.0F).sounds(BlockSoundGroup.DEEPSLATE)));
    public static final Block RED_DEEPSLATE_PALLADIUM_ORE = registerBlock("red_deepslate_palladium_ore",
            new Block(FabricBlockSettings.create().mapColor(MapColor.MAGENTA).requiresTool().strength(16.0F, 1200.0F).sounds(BlockSoundGroup.DEEPSLATE)));
    public static final Block RED_DEEPSLATE_JURASSOLINE_ORE = registerBlock("red_deepslate_jurassoline_ore",
            new Block(FabricBlockSettings.create().mapColor(MapColor.PALE_GREEN).requiresTool().strength(16.0F, 1200.0F).sounds(BlockSoundGroup.DEEPSLATE)));
    public static final Block RED_DEEPSLATE_CINNABAR_ORE = registerBlock("red_deepslate_cinnabar_ore",
            new Block(FabricBlockSettings.create().mapColor(MapColor.DARK_RED).requiresTool().strength(16.0F, 1200.0F).sounds(BlockSoundGroup.DEEPSLATE)));
    public static final Block RED_DEEPSLATE_NEBULAR_ORE = registerBlock("red_deepslate_nebular_ore",
            new Block(FabricBlockSettings.create().mapColor(MapColor.DARK_RED).requiresTool().strength(16.0F, 1200.0F).sounds(BlockSoundGroup.DEEPSLATE)));
    public static final Block XEN_CRYSTAL_CLUSTER = registerRarityBlock("xen_crystal_cluster",
            new AmethystClusterBlock(7, 3, FabricBlockSettings.create().mapColor(MapColor.ORANGE).requiresTool().strength(1.5F, 1.5F).sounds(BlockSoundGroup.AMETHYST_CLUSTER).nonOpaque()),Rarity.UNCOMMON);
    public static final Block XEN_CRYSTAL_BLOCK = registerRarityBlock("xen_crystal_block",
            new Block(FabricBlockSettings.create().mapColor(MapColor.ORANGE).requiresTool().strength(1.5F, 1.5F).sounds(BlockSoundGroup.AMETHYST_BLOCK)),Rarity.UNCOMMON);

    public static final Block LUNAR_REGOLITH = registerBlock("lunar_regolith",
            new Block(FabricBlockSettings.create().mapColor(MapColor.WHITE).strength(3.0F, 9.0F).sounds(BlockSoundGroup.SAND)));

    public static final Block MEGAREGOLITH = registerBlock("megaregolith",
            new Block(FabricBlockSettings.create().mapColor(MapColor.WHITE).requiresTool().strength(3.0F, 9.0F)));
    public static final Block MEGAREGOLITH_COAL_ORE = registerBlock("megaregolith_coal_ore",
            new Block(FabricBlockSettings.create().mapColor(MapColor.WHITE).requiresTool().strength(3.0F, 9.0F)));
    public static final Block MEGAREGOLITH_IRON_ORE = registerBlock("megaregolith_iron_ore",
            new Block(FabricBlockSettings.create().mapColor(MapColor.WHITE).requiresTool().strength(3.0F, 9.0F)));
    public static final Block MEGAREGOLITH_LUNAR_CALLAINUS_ORE = registerBlock("megaregolith_lunar_callainus_ore",
            new Block(FabricBlockSettings.create().mapColor(MapColor.WHITE).requiresTool().strength(3.0F, 9.0F)));

    public static final Block STEEL_BLOCK = registerBlock("steel_block",
            new Block(FabricBlockSettings.create().mapColor(MapColor.DEEPSLATE_GRAY).requiresTool().strength(6.0F, 12.0F).sounds(BlockSoundGroup.METAL)));

    public static final Block NETHER_SULFUR_ORE = registerBlock("nether_sulfur_ore",
            new Block(FabricBlockSettings.create().mapColor(MapColor.PALE_YELLOW).requiresTool().strength(3.0F, 3.0F).sounds(BlockSoundGroup.NETHER_ORE)));
    public static final Block SULFUR_BLOCK = registerBlock("sulfur_block",
            new Block(FabricBlockSettings.create().mapColor(MapColor.PALE_YELLOW).requiresTool().strength(3.0F, 3.0F)));

    public static final Block LEAD_ORE = registerBlock("lead_ore",
            new Block(FabricBlockSettings.create().mapColor(MapColor.GRAY).requiresTool().strength(3.0F, 3.0F)));
    public static final Block DEEPSLATE_LEAD_ORE = registerBlock("deepslate_lead_ore",
            new Block(FabricBlockSettings.create().mapColor(MapColor.GRAY).requiresTool().strength(3.0F, 3.0F).sounds(BlockSoundGroup.DEEPSLATE)));
    public static final Block RAW_LEAD_BLOCK = registerBlock("raw_lead_block",
            new Block(FabricBlockSettings.create().mapColor(MapColor.GRAY).requiresTool().strength(3.0F, 3.0F)));
    public static final Block LEAD_BLOCK = registerBlock("lead_block",
            new Block(FabricBlockSettings.create().mapColor(MapColor.GRAY).requiresTool().strength(3.0F, 3.0F)));

    public static final Block URANIUM_ORE = registerIrradiatedBlock("uranium_ore",
            new Block(FabricBlockSettings.create().mapColor(MapColor.GRAY).requiresTool().strength(3.0F, 3.0F)));
    public static final Block DEEPSLATE_URANIUM_ORE = registerIrradiatedBlock("deepslate_uranium_ore",
            new Block(FabricBlockSettings.create().mapColor(MapColor.GRAY).requiresTool().strength(3.0F, 3.0F).sounds(BlockSoundGroup.DEEPSLATE)));
    public static final Block RAW_URANIUM_BLOCK = registerIrradiatedBlock("raw_uranium_block",
            new Block(FabricBlockSettings.create().mapColor(MapColor.GREEN).requiresTool().strength(3.0F, 3.0F)));
    public static final Block URANIUM_BLOCK = registerIrradiatedBlock("uranium_block",
            new Block(FabricBlockSettings.create().mapColor(MapColor.GREEN).requiresTool().strength(3.0F, 3.0F)));

    public static final Block FOSSIL = registerBlock("fossil",
            new Block(FabricBlockSettings.create().mapColor(MapColor.PALE_YELLOW).requiresTool().strength(3.0F, 3.0F)));
    public static final Block DEEPSLATE_FOSSIL = registerBlock("deepslate_fossil",
            new Block(FabricBlockSettings.create().mapColor(MapColor.PALE_YELLOW).requiresTool().strength(3.0F, 3.0F).sounds(BlockSoundGroup.DEEPSLATE)));

    public static final Block HELIORITE_ORE = registerBlock("heliorite_ore",
            new Block(FabricBlockSettings.create().mapColor(MapColor.MAGENTA).requiresTool().strength(16.0F, 1200.0F)));
    public static final Block DEEPSLATE_HELIORITE_ORE = registerBlock("deepslate_heliorite_ore",
            new Block(FabricBlockSettings.create().mapColor(MapColor.MAGENTA).requiresTool().strength(16.0F, 1200.0F).sounds(BlockSoundGroup.DEEPSLATE)));
    public static final Block HELIORITE_COMB_BLOCK = registerBlock("heliorite_comb_block",
            new Block(FabricBlockSettings.create().mapColor(MapColor.MAGENTA).requiresTool().strength(16.0F, 1200.0F)));
    public static final Block HELIORITE_BLOCK = registerBlock("heliorite_block",
            new Block(FabricBlockSettings.create().mapColor(MapColor.MAGENTA).requiresTool().strength(16.0F, 1200.0F).sounds(BlockSoundGroup.NETHERITE)));

    public static final Block END_ENDURIUM_ORE = registerBlock("end_endurium_ore",
            new Block(FabricBlockSettings.create().mapColor(MapColor.DARK_AQUA).requiresTool().strength(16.0F, 1200.0F)));
    public static final Block RAW_ENDURIUM_BLOCK = registerBlock("raw_endurium_block",
            new Block(FabricBlockSettings.create().mapColor(MapColor.DARK_AQUA).requiresTool().strength(16.0F, 1200.0F)));
    public static final Block ENDURIUM_BLOCK = registerBlock("endurium_block",
            new Block(FabricBlockSettings.create().mapColor(MapColor.DARK_AQUA).requiresTool().strength(16.0F, 1200.0F).sounds(BlockSoundGroup.NETHERITE)));

    public static final Block PALLADIUM_ORE = registerBlock("palladium_ore",
            new Block(FabricBlockSettings.create().mapColor(MapColor.ORANGE).requiresTool().strength(16.0F, 1200.0F)));
    public static final Block DEEPSLATE_PALLADIUM_ORE = registerBlock("deepslate_palladium_ore",
            new Block(FabricBlockSettings.create().mapColor(MapColor.ORANGE).requiresTool().strength(16.0F, 1200.0F).sounds(BlockSoundGroup.DEEPSLATE)));
    public static final Block RAW_PALLADIUM_BLOCK = registerBlock("raw_palladium_block",
            new Block(FabricBlockSettings.create().mapColor(MapColor.ORANGE).requiresTool().strength(16.0F, 1200.0F)));
    public static final Block PALLADIUM_BLOCK = registerBlock("palladium_block",
            new Block(FabricBlockSettings.create().mapColor(MapColor.ORANGE).requiresTool().strength(16.0F, 1200.0F).sounds(BlockSoundGroup.NETHERITE)));

    public static final Block JURASSOLINE_ORE = registerBlock("jurassoline_ore",
            new Block(FabricBlockSettings.create().mapColor(MapColor.PALE_GREEN).requiresTool().strength(16.0F, 1200.0F)));
    public static final Block DEEPSLATE_JURASSOLINE_ORE = registerBlock("deepslate_jurassoline_ore",
            new Block(FabricBlockSettings.create().mapColor(MapColor.PALE_GREEN).requiresTool().strength(16.0F, 1200.0F).sounds(BlockSoundGroup.DEEPSLATE)));
    public static final Block JURASSOLINE_CRYSTAL_BLOCK = registerBlock("jurassoline_crystal_block",
            new Block(FabricBlockSettings.create().mapColor(MapColor.PALE_GREEN).requiresTool().strength(16.0F, 1200.0F).sounds(BlockSoundGroup.AMETHYST_BLOCK)));
    public static final Block JURASSOLINE_BLOCK = registerBlock("jurassoline_block",
            new Block(FabricBlockSettings.create().mapColor(MapColor.PALE_GREEN).requiresTool().strength(16.0F, 1200.0F).sounds(BlockSoundGroup.NETHERITE)));

    public static final Block CINNABAR_ORE = registerBlock("cinnabar_ore",
            new Block(FabricBlockSettings.create().mapColor(MapColor.DARK_RED).requiresTool().strength(16.0F, 1200.0F)));
    public static final Block DEEPSLATE_CINNABAR_ORE = registerBlock("deepslate_cinnabar_ore",
            new Block(FabricBlockSettings.create().mapColor(MapColor.DARK_RED).requiresTool().strength(16.0F, 1200.0F).sounds(BlockSoundGroup.DEEPSLATE)));
    public static final Block CINNABAR_CRYSTAL_BLOCK = registerBlock("cinnabar_crystal_block",
            new Block(FabricBlockSettings.create().mapColor(MapColor.DARK_RED).requiresTool().strength(16.0F, 1200.0F).sounds(BlockSoundGroup.AMETHYST_BLOCK)));
    public static final Block CINNABAR_BLOCK = registerBlock("cinnabar_block",
            new Block(FabricBlockSettings.create().mapColor(MapColor.DARK_RED).requiresTool().strength(16.0F, 1200.0F).sounds(BlockSoundGroup.NETHERITE)));

    public static final Block NEBULAR_ORE = registerBlock("nebular_ore",
            new Block(FabricBlockSettings.create().mapColor(MapColor.DARK_AQUA).requiresTool().strength(16.0F, 1200.0F)));
    public static final Block DEEPSLATE_NEBULAR_ORE = registerBlock("deepslate_nebular_ore",
            new Block(FabricBlockSettings.create().mapColor(MapColor.DARK_AQUA).requiresTool().strength(16.0F, 1200.0F).sounds(BlockSoundGroup.DEEPSLATE)));
    public static final Block RAW_NEBULAR_BLOCK = registerBlock("raw_nebular_block",
            new Block(FabricBlockSettings.create().mapColor(MapColor.DARK_AQUA).requiresTool().strength(16.0F, 1200.0F)));
    public static final Block NEBULAR_BLOCK = registerBlock("nebular_block",
            new Block(FabricBlockSettings.create().mapColor(MapColor.DARK_AQUA).requiresTool().strength(16.0F, 1200.0F).sounds(BlockSoundGroup.NETHERITE)));

    public static final Block BEDROCK_MITHRIL_ORE = registerRarityBlock("bedrock_mithril_ore",
            new MithrilBlock(FabricBlockSettings.create().mapColor(MapColor.WHITE_GRAY).requiresTool().strength(-1.0F, 3600000.0F)), Rarity.UNCOMMON);
    public static final Block RAW_MITHRIL_BLOCK = registerRarityBlock("raw_mithril_block",
            new MithrilBlock(FabricBlockSettings.create().mapColor(MapColor.WHITE_GRAY).requiresTool().strength(10.0F, 1200.0F).sounds(BlockSoundGroup.NETHERITE)), Rarity.UNCOMMON);
    public static final Block MITHRIL_BLOCK = registerRarityBlock("mithril_block",
            new MithrilBlock(FabricBlockSettings.create().mapColor(MapColor.WHITE_GRAY).requiresTool().strength(10.0F, 1200.0F).sounds(BlockSoundGroup.NETHERITE)), Rarity.UNCOMMON);

    public static final Block MITHRIL_ANVIL = registerRarityBlock("mithril_anvil",
            new MithrilAnvilBlock(FabricBlockSettings.create().mapColor(MapColor.WHITE_GRAY).requiresTool().strength(10.0F, 1200.0F).sounds(BlockSoundGroup.ANVIL)), Rarity.UNCOMMON);

    public static final Block MITHRIL_BARS = registerRarityBlock("mithril_bars",
            new PaneBlock(FabricBlockSettings.create().mapColor(MapColor.WHITE_GRAY).requiresTool().strength(10.0F, 1200.0F).sounds(BlockSoundGroup.NETHERITE).nonOpaque()), Rarity.UNCOMMON);

    public static final Block WOODEN_STEIN = registerWoodenSteinBlock("wooden_stein",
            new WoodenSteinBlock(FabricBlockSettings.create().mapColor(MapColor.BROWN).strength(2.0F, 2.0F).sounds(BlockSoundGroup.WOOD).pistonBehavior(PistonBehavior.DESTROY).nonOpaque()));
    public static final Block WOODEN_WATER_STEIN = registerDrinkableWoodenSteinBlock("wooden_water_stein",
            new WoodenSteinBlock(FabricBlockSettings.create().mapColor(MapColor.BROWN).strength(2.0F, 2.0F).sounds(BlockSoundGroup.WOOD).pistonBehavior(PistonBehavior.DESTROY).nonOpaque()),"");
    public static final Block WOODEN_APPLE_JUICE_STEIN = registerDrinkableWoodenSteinBlock("wooden_apple_juice_stein",
            new WoodenSteinBlock(FabricBlockSettings.create().mapColor(MapColor.BROWN).strength(2.0F, 2.0F).sounds(BlockSoundGroup.WOOD).pistonBehavior(PistonBehavior.DESTROY).nonOpaque()),"");
    public static final Block WOODEN_ORANGE_JUICE_STEIN = registerDrinkableWoodenSteinBlock("wooden_orange_juice_stein",
            new WoodenSteinBlock(FabricBlockSettings.create().mapColor(MapColor.BROWN).strength(2.0F, 2.0F).sounds(BlockSoundGroup.WOOD).pistonBehavior(PistonBehavior.DESTROY).nonOpaque()),"");
    public static final Block WOODEN_BEER_STEIN = registerRemainderSteinBlock("wooden_beer_stein",
            new WoodenSteinBlock(FabricBlockSettings.create().mapColor(MapColor.BROWN).strength(2.0F, 2.0F).sounds(BlockSoundGroup.WOOD).pistonBehavior(PistonBehavior.DESTROY).nonOpaque()),"");
    public static final Block WOODEN_MEAD_STEIN = registerRemainderSteinBlock("wooden_mead_stein",
            new WoodenSteinBlock(FabricBlockSettings.create().mapColor(MapColor.BROWN).strength(2.0F, 2.0F).sounds(BlockSoundGroup.WOOD).pistonBehavior(PistonBehavior.DESTROY).nonOpaque()),"");
    public static final Block WOODEN_MILK_STEIN = registerDrinkableWoodenSteinBlock("wooden_milk_stein",
            new WoodenSteinBlock(FabricBlockSettings.create().mapColor(MapColor.BROWN).strength(2.0F, 2.0F).sounds(BlockSoundGroup.WOOD).pistonBehavior(PistonBehavior.DESTROY).nonOpaque()),"");
    public static final Block WOODEN_CHOCOLATE_MILKSHAKE_STEIN = registerDrinkableWoodenSteinBlock("wooden_chocolate_milkshake_stein",
            new WoodenSteinBlock(FabricBlockSettings.create().mapColor(MapColor.BROWN).strength(2.0F, 2.0F).sounds(BlockSoundGroup.WOOD).pistonBehavior(PistonBehavior.DESTROY).nonOpaque()),"");
    public static final Block WOODEN_STRAWBERRY_MILKSHAKE_STEIN = registerDrinkableWoodenSteinBlock("wooden_strawberry_milkshake_stein",
            new WoodenSteinBlock(FabricBlockSettings.create().mapColor(MapColor.BROWN).strength(2.0F, 2.0F).sounds(BlockSoundGroup.WOOD).pistonBehavior(PistonBehavior.DESTROY).nonOpaque()),"");
    public static final Block WOODEN_ORANGE_MILKSHAKE_STEIN = registerDrinkableWoodenSteinBlock("wooden_orange_milkshake_stein",
            new WoodenSteinBlock(FabricBlockSettings.create().mapColor(MapColor.BROWN).strength(2.0F, 2.0F).sounds(BlockSoundGroup.WOOD).pistonBehavior(PistonBehavior.DESTROY).nonOpaque()),"");
    public static final Block WOODEN_BLUE_BERRY_MILKSHAKE_STEIN = registerDrinkableWoodenSteinBlock("wooden_blue_berry_milkshake_stein",
            new WoodenSteinBlock(FabricBlockSettings.create().mapColor(MapColor.BROWN).strength(2.0F, 2.0F).sounds(BlockSoundGroup.WOOD).pistonBehavior(PistonBehavior.DESTROY).nonOpaque()),"aet");
    public static final Block WOODEN_TORCHBERRY_MILKSHAKE_STEIN = registerDrinkableWoodenSteinBlock("wooden_torchberry_milkshake_stein",
            new WoodenSteinBlock(FabricBlockSettings.create().mapColor(MapColor.BROWN).strength(2.0F, 2.0F).sounds(BlockSoundGroup.WOOD).pistonBehavior(PistonBehavior.DESTROY).nonOpaque()),"tlf");
    public static final Block HOT_WOODEN_MILK_STEIN = registerRemainderSteinBlock("hot_wooden_milk_stein",
            new WoodenSteinBlock(FabricBlockSettings.create().mapColor(MapColor.BROWN).strength(2.0F, 2.0F).sounds(BlockSoundGroup.WOOD).pistonBehavior(PistonBehavior.DESTROY).nonOpaque()),"");
    public static final Block WOODEN_HOT_CHOCOLATE_STEIN = registerDrinkableWoodenSteinBlock("wooden_hot_chocolate_stein",
            new WoodenSteinBlock(FabricBlockSettings.create().mapColor(MapColor.BROWN).strength(2.0F, 2.0F).sounds(BlockSoundGroup.WOOD).pistonBehavior(PistonBehavior.DESTROY).nonOpaque()),"");

    public static final Block GLOBUS_CRUCIGER = registerBlock("globus_cruciger",
            new GlobusCrucigerBlock(FabricBlockSettings.create().mapColor(MapColor.GOLD).strength(2.0F, 2.0F).sounds(BlockSoundGroup.METAL).pistonBehavior(PistonBehavior.DESTROY).nonOpaque()));

    public static final Block CREEPER_PLUSHIE = registerBlock("creeper_plushie",
            new CreeperPlushieBlock(FabricBlockSettings.create().strength(0.8F, 0.8F).sounds(BlockSoundGroup.WOOL).pistonBehavior(PistonBehavior.DESTROY).nonOpaque()));
    public static final Block ZOMBIE_PLUSHIE = registerBlock("zombie_plushie",
            new HumanPlushieBlock(FabricBlockSettings.create().strength(0.8F, 0.8F).sounds(BlockSoundGroup.WOOL).pistonBehavior(PistonBehavior.DESTROY).nonOpaque()));
    public static final Block PLAYER_PLUSHIE = registerBlock("player_plushie",
            new PlayerPlushieBlock(FabricBlockSettings.create().strength(0.8F, 0.8F).sounds(BlockSoundGroup.WOOL).pistonBehavior(PistonBehavior.DESTROY).nonOpaque()));

    public static final Block SIGNAL_TRANSMITTER = registerBlock("signal_transmitter",
            new SignalTransmitterBlock(FabricBlockSettings.create().mapColor(MapColor.ORANGE).requiresTool().strength(3.0F, 3.0F).sounds(BlockSoundGroup.METAL)));
    public static final Block SIGNAL_TRANSMITTER_ANTENNA = registerBlock("signal_transmitter_antenna",
            new SignalTransmitterAntennaBlock(FabricBlockSettings.create().mapColor(MapColor.ORANGE).requiresTool().strength(3.0F, 3.0F).sounds(BlockSoundGroup.METAL)));

    public static final Block GARBAGE_CAN = registerBlock("garbage_can",
            new GarbageCanBlock(FabricBlockSettings.create().mapColor(MapColor.GRAY).requiresTool().strength(5.0F, 1200.0F).nonOpaque()));
    public static final Block BENCH = registerBlock("bench",
            new BenchBlock(FabricBlockSettings.create().mapColor(MapColor.BROWN).requiresTool().strength(2.0F, 2.0F).nonOpaque()));

    public static final Block RADIO = registerBlock("radio",
            new RadioBlock(FabricBlockSettings.create().mapColor(MapColor.BROWN).requiresTool().strength(2.0F, 2.0F).nonOpaque()));
    public static final Block RADIO_ON = registerBlockWithoutItem("radio_on",
            new RadioBlock(FabricBlockSettings.create().mapColor(MapColor.BROWN).requiresTool().strength(2.0F, 2.0F).nonOpaque().luminance(7)));
    public static final Block TELEVISION = registerBlock("television",
            new TelevisionBlock(FabricBlockSettings.create().mapColor(MapColor.BLACK).requiresTool().strength(2.0F, 2.0F)));
    public static final Block TELEVISION_ON = registerBlockWithoutItem("television_on",
            new TelevisionBlock(FabricBlockSettings.create().mapColor(MapColor.BLACK).requiresTool().strength(2.0F, 2.0F).luminance(10)));
    public static final Block COMPUTER = registerBlock("computer",
            new ComputerBlock(FabricBlockSettings.create().mapColor(MapColor.WHITE).requiresTool().strength(2.0F, 2.0F)));
    public static final Block COMPUTER_ON = registerBlockWithoutItem("computer_on",
            new ComputerBlock(FabricBlockSettings.create().mapColor(MapColor.WHITE).requiresTool().strength(2.0F, 2.0F).luminance(10)));

    public static final Block KEYCARD_PROGRAMMER = registerBlock("keycard_programmer",
            new KeycardProgrammerBlock(FabricBlockSettings.create().mapColor(MapColor.WHITE).requiresTool().strength(2.0F, 2.0F)));
    public static final Block KEYCARD_READER = registerBlock("keycard_reader",
            new KeycardReaderBlock(FabricBlockSettings.create().requiresTool().strength(0.5f,0.5f).pistonBehavior(PistonBehavior.DESTROY),BlockSetType.IRON,100,false));

    public static final Block HEV_CHARGER = registerRarityBlock("hev_charger",
            new HEVChargerBlock(FabricBlockSettings.create().requiresTool().strength(0.5f,0.5f).pistonBehavior(PistonBehavior.DESTROY)), Rarity.UNCOMMON);

    public static final Block INTERDIMENSIONAL_RECEIVER = registerRarityBlock("interdimensional_receiver",
            new InterdimensionalReceiverBlock(FabricBlockSettings.create().mapColor(MapColor.GRAY).requiresTool().strength(2.0F, 2.0F).pistonBehavior(PistonBehavior.DESTROY).luminance(4)), Rarity.RARE);

    public static final Block TELEPORTER = registerRarityBlock("teleporter",
            new TeleporterBlock(FabricBlockSettings.create().mapColor(MapColor.BLACK).requiresTool().strength(3.0F, 9.0F).pistonBehavior(PistonBehavior.BLOCK).luminance(4)), Rarity.RARE);

    public static final Block ALIEN_CONTROL_PANEL = registerRarityBlock("alien_control_panel",
            new AlienControlPanelBlock(FabricBlockSettings.create().mapColor(MapColor.BLACK).requiresTool().strength(3.0F, 9.0F).pistonBehavior(PistonBehavior.BLOCK).luminance(4)), Rarity.EPIC);

    public static final Block VIDEOCASSETTE_RECORDER = registerVCRBlock("videocassette_recorder",
            new VideocassetteRecorderBlock(FabricBlockSettings.create().mapColor(MapColor.WHITE).strength(2.0F, 2.0F).pistonBehavior(PistonBehavior.DESTROY)));

    public static final Block VIDEOCASSETTE_RECORDER_BOOKSHELF = registerBlockWithoutItem("videocassette_recorder_bookshelf",
            new VideocassetteRecorderBlock(FabricBlockSettings.create().mapColor(MapColor.OAK_TAN).strength(2.0F, 2.0F).sounds(BlockSoundGroup.WOOD)));

    public static final Block OXYGEN_COLLECTOR = registerBlock("oxygen_collector",
            new OxygenCollectorBlock(FabricBlockSettings.create().mapColor(MapColor.GRAY).requiresTool().strength(2.0F, 2.0F).pistonBehavior(PistonBehavior.DESTROY).luminance(7)));

    public static final Block INCUBATOR = registerBlock("incubator",
            new IncubatorBlock(FabricBlockSettings.create().mapColor(MapColor.BLACK).requiresTool().strength(3.0F, 9.0F).pistonBehavior(PistonBehavior.BLOCK).luminance(7)));

    public static final Block MINING_ELEVATOR_CONTROLLER = registerBlock("mining_elevator_controller",
            new ElevatorControllerBlock(FabricBlockSettings.create().mapColor(MapColor.BROWN).requiresTool().strength(5.0F, 1200.0F).luminance(15).nonOpaque()));
    public static final Block MINING_ELEVATOR_BASE = registerBlock("mining_elevator_base",
            new ElevatorBaseBlock(FabricBlockSettings.create().mapColor(MapColor.BROWN).requiresTool().strength(5.0F, 1200.0F).nonOpaque()));
    public static final Block WOODEN_ELEVATOR_CONTROLLER = registerBlock("wooden_elevator_controller",
            new ElevatorControllerBlock(FabricBlockSettings.create().mapColor(MapColor.BROWN).requiresTool().strength(5.0F, 1200.0F).luminance(15)));
    public static final Block WOODEN_ELEVATOR_BASE = registerBlock("wooden_elevator_base",
            new ElevatorBaseBlock(FabricBlockSettings.create().mapColor(MapColor.BROWN).requiresTool().strength(5.0F, 1200.0F)));
    public static final Block QUARTZ_ELEVATOR_CONTROLLER = registerBlock("quartz_elevator_controller",
            new ElevatorControllerBlock(FabricBlockSettings.create().mapColor(MapColor.WHITE).requiresTool().strength(5.0F, 1200.0F).luminance(15)));
    public static final Block QUARTZ_ELEVATOR_BASE = registerBlock("quartz_elevator_base",
            new ElevatorBaseBlock(FabricBlockSettings.create().mapColor(MapColor.WHITE).requiresTool().strength(5.0F, 1200.0F)));
    public static final Block GLASS_ELEVATOR_CONTROLLER = registerBlock("glass_elevator_controller",
            new ElevatorControllerBlock(FabricBlockSettings.create().mapColor(MapColor.BROWN).requiresTool().strength(5.0F, 1200.0F).luminance(15).nonOpaque()));
    public static final Block GLASS_ELEVATOR_BASE = registerBlock("glass_elevator_base",
            new ElevatorBaseBlock(FabricBlockSettings.create().mapColor(MapColor.BROWN).requiresTool().strength(5.0F, 1200.0F).nonOpaque()));

    public static final Block TREX_HEAD = registerRarityBlock("t-rex_head",
            new TRexHeadBlock(FabricBlockSettings.create().mapColor(MapColor.BROWN).strength(1.0F, 1.0F).nonOpaque()), Rarity.UNCOMMON);
    public static final Block GREEN_TREX_HEAD = registerRarityBlock("green_t-rex_head",
            new TRexHeadBlock(FabricBlockSettings.create().mapColor(MapColor.BROWN).strength(1.0F, 1.0F).nonOpaque()), Rarity.UNCOMMON);
    public static final Block GRAY_TREX_HEAD = registerRarityBlock("gray_t-rex_head",
            new TRexHeadBlock(FabricBlockSettings.create().mapColor(MapColor.BROWN).strength(1.0F, 1.0F).nonOpaque()), Rarity.UNCOMMON);

    public static final Block INFECTED_TREX_HEAD = registerCompatBlock("infected_t-rex_head",
            new TRexHeadBlock(FabricBlockSettings.create().mapColor(MapColor.BROWN).strength(1.0F, 1.0F).nonOpaque()), Rarity.UNCOMMON, "spr");
    public static final Block SCULK_TREX_HEAD = registerCompatBlock("sculk_t-rex_head",
            new TRexHeadBlock(FabricBlockSettings.create().mapColor(MapColor.BROWN).strength(1.0F, 1.0F).nonOpaque()), Rarity.UNCOMMON, "skh");
    public static final Block SICKENED_TREX_HEAD = registerCompatBlock("sickened_t-rex_head",
            new TRexHeadBlock(FabricBlockSettings.create().mapColor(MapColor.BROWN).strength(1.0F, 1.0F).nonOpaque()), Rarity.UNCOMMON, "wsm");

    public static final Block ROSE = registerBlock("rose",
            new ModFlowerBlock(StatusEffects.INSTANT_HEALTH, 10, FabricBlockSettings.create().breakInstantly().nonOpaque().sounds(BlockSoundGroup.GRASS).noCollision().pistonBehavior(PistonBehavior.DESTROY)));
    public static final Block POTTED_ROSE = Registry.register(Registries.BLOCK, new Identifier(TLOTD.MOD_ID, "potted_rose"),
            new FlowerPotBlock(ROSE, FabricBlockSettings.create().breakInstantly().nonOpaque().pistonBehavior(PistonBehavior.DESTROY)));

    public static final Block IRIS = registerBlock("iris",
            new ModFlowerBlock(StatusEffects.POISON, 10, FabricBlockSettings.create().breakInstantly().nonOpaque().sounds(BlockSoundGroup.GRASS).noCollision().pistonBehavior(PistonBehavior.DESTROY)));
    public static final Block POTTED_IRIS = Registry.register(Registries.BLOCK, new Identifier(TLOTD.MOD_ID, "potted_iris"),
            new FlowerPotBlock(IRIS, FabricBlockSettings.create().breakInstantly().nonOpaque().pistonBehavior(PistonBehavior.DESTROY)));

    public static final Block EDELWEISS = registerBlock("edelweiss",
            new ModFlowerBlock(StatusEffects.JUMP_BOOST, 10, FabricBlockSettings.create().breakInstantly().nonOpaque().sounds(BlockSoundGroup.GRASS).noCollision().pistonBehavior(PistonBehavior.DESTROY)));
    public static final Block POTTED_EDELWEISS = Registry.register(Registries.BLOCK, new Identifier(TLOTD.MOD_ID, "potted_edelweiss"),
            new FlowerPotBlock(EDELWEISS, FabricBlockSettings.create().breakInstantly().nonOpaque().pistonBehavior(PistonBehavior.DESTROY)));

    public static final Block ATHELAS = registerBlock("athelas",
            new ModFlowerBlock(StatusEffects.REGENERATION, 10, FabricBlockSettings.create().breakInstantly().nonOpaque().sounds(BlockSoundGroup.GRASS).noCollision().pistonBehavior(PistonBehavior.DESTROY)));
    public static final Block POTTED_ATHELAS = Registry.register(Registries.BLOCK, new Identifier(TLOTD.MOD_ID, "potted_athelas"),
            new FlowerPotBlock(ATHELAS, FabricBlockSettings.create().breakInstantly().nonOpaque().pistonBehavior(PistonBehavior.DESTROY)));

    public static final Block GINKGO_SAPLING = registerBlock("ginkgo_sapling",
            new SaplingBlock(new GinkgoSaplingGenerator(), FabricBlockSettings.create().mapColor(MapColor.PALE_GREEN).noCollision().strength(0F, 0F).sounds(BlockSoundGroup.GRASS).nonOpaque().ticksRandomly().pistonBehavior(PistonBehavior.DESTROY)));
    public static final Block POTTED_GINKGO_SAPLING = Registry.register(Registries.BLOCK, new Identifier(TLOTD.MOD_ID, "potted_ginkgo_sapling"),
            new FlowerPotBlock(GINKGO_SAPLING, FabricBlockSettings.create().breakInstantly().nonOpaque().pistonBehavior(PistonBehavior.DESTROY)));

    public static final Block GINKGO_LEAVES = registerBlock("ginkgo_leaves",
            new LeavesBlock(FabricBlockSettings.create().mapColor(MapColor.PALE_GREEN).strength(0.2F, 0.2F).sounds(BlockSoundGroup.CHERRY_LEAVES).nonOpaque()));

    public static final Block GINKGO_LOG = registerBlock("ginkgo_log",
            new PillarBlock(FabricBlockSettings.create().mapColor(MapColor.PALE_GREEN).strength(2.0F, 2.0F).sounds(BlockSoundGroup.WOOD)));
    public static final Block GINKGO_WOOD = registerBlock("ginkgo_wood",
            new PillarBlock(FabricBlockSettings.create().mapColor(MapColor.PALE_GREEN).strength(2.0F, 2.0F).sounds(BlockSoundGroup.WOOD)));
    public static final Block STRIPPED_GINKGO_LOG = registerBlock("stripped_ginkgo_log",
            new PillarBlock(FabricBlockSettings.create().mapColor(MapColor.PALE_GREEN).strength(2.0F, 2.0F).sounds(BlockSoundGroup.WOOD)));
    public static final Block STRIPPED_GINKGO_WOOD = registerBlock("stripped_ginkgo_wood",
            new PillarBlock(FabricBlockSettings.create().mapColor(MapColor.PALE_GREEN).strength(2.0F, 2.0F).sounds(BlockSoundGroup.WOOD)));

    public static final Block GINKGO_PLANKS = registerBlock("ginkgo_planks",
            new Block(FabricBlockSettings.create().mapColor(MapColor.PALE_GREEN).strength(2.0F, 3.0F).sounds(BlockSoundGroup.WOOD)));

    public static final Block GINKGO_STAIRS = registerBlock("ginkgo_stairs",
            new StairsBlock(ModBlocks.GINKGO_PLANKS.getDefaultState(), FabricBlockSettings.create().mapColor(MapColor.PALE_GREEN).strength(2.0F, 3.0F).sounds(BlockSoundGroup.WOOD)));
    public static final Block GINKGO_SLAB = registerBlock("ginkgo_slab",
            new SlabBlock(FabricBlockSettings.create().mapColor(MapColor.PALE_GREEN).strength(2.0F, 3.0F).sounds(BlockSoundGroup.WOOD)));
    public static final Block GINKGO_BUTTON = registerBlock("ginkgo_button",
            new ButtonBlock(FabricBlockSettings.create().mapColor(MapColor.PALE_GREEN).strength(0.5F, 0.5F).sounds(BlockSoundGroup.WOOD).collidable(false), BlockSetType.OAK, 30, true));
    public static final Block GINKGO_PRESSURE_PLATE = registerBlock("ginkgo_pressure_plate",
            new PressurePlateBlock(PressurePlateBlock.ActivationRule.EVERYTHING, FabricBlockSettings.create().mapColor(MapColor.PALE_GREEN).strength(0.5F, 0.5F).collidable(false).sounds(BlockSoundGroup.WOOD), BlockSetType.OAK));
    public static final Block GINKGO_FENCE = registerBlock("ginkgo_fence",
            new FenceBlock(FabricBlockSettings.create().mapColor(MapColor.PALE_GREEN).strength(2.0F, 3.0F).sounds(BlockSoundGroup.WOOD)));
    public static final Block GINKGO_FENCE_GATE = registerBlock("ginkgo_fence_gate",
            new FenceGateBlock(FabricBlockSettings.create().mapColor(MapColor.PALE_GREEN).strength(2.0F, 3.0F).sounds(BlockSoundGroup.WOOD), WoodType.OAK));
    public static final Block GINKGO_DOOR = registerBlock("ginkgo_door",
            new DoorBlock(FabricBlockSettings.create().mapColor(MapColor.PALE_GREEN).strength(2.0F, 3.0F).sounds(BlockSoundGroup.WOOD).nonOpaque(), BlockSetType.OAK));
    public static final Block GINKGO_TRAPDOOR = registerBlock("ginkgo_trapdoor",
            new TrapdoorBlock(FabricBlockSettings.create().mapColor(MapColor.PALE_GREEN).strength(2.0F, 3.0F).sounds(BlockSoundGroup.WOOD).nonOpaque(), BlockSetType.OAK));

    public static final Identifier GINKGO_SIGN_TEXTURE = new Identifier(TLOTD.MOD_ID, "entity/signs/ginkgo");
    public static final Identifier GINKGO_HANGING_SIGN_TEXTURE = new Identifier(TLOTD.MOD_ID, "entity/signs/hanging/ginkgo");
    public static final Identifier GINKGO_HANGING_GUI_SIGN_TEXTURE = new Identifier(TLOTD.MOD_ID, "textures/gui/hanging_signs/ginkgo");

    public static final Block STANDING_GINKGO_SIGN = Registry.register(Registries.BLOCK, new Identifier(TLOTD.MOD_ID, "ginkgo_standing_sign"),
            new TerraformSignBlock(GINKGO_SIGN_TEXTURE, FabricBlockSettings.copyOf(Blocks.OAK_SIGN)));
    public static final Block WALL_GINKGO_SIGN = Registry.register(Registries.BLOCK, new Identifier(TLOTD.MOD_ID, "ginkgo_wall_sign"),
            new TerraformWallSignBlock(GINKGO_SIGN_TEXTURE, FabricBlockSettings.copyOf(Blocks.OAK_WALL_SIGN)));
    public static final Block HANGING_GINKGO_SIGN = Registry.register(Registries.BLOCK, new Identifier(TLOTD.MOD_ID, "ginkgo_hanging_sign"),
            new TerraformHangingSignBlock(GINKGO_HANGING_SIGN_TEXTURE, GINKGO_HANGING_GUI_SIGN_TEXTURE, FabricBlockSettings.copyOf(Blocks.OAK_HANGING_SIGN)));
    public static final Block WALL_HANGING_GINKGO_SIGN = Registry.register(Registries.BLOCK, new Identifier(TLOTD.MOD_ID, "ginkgo_wall_hanging_sign"),
            new TerraformWallHangingSignBlock(GINKGO_HANGING_SIGN_TEXTURE, GINKGO_HANGING_GUI_SIGN_TEXTURE, FabricBlockSettings.copyOf(Blocks.OAK_WALL_HANGING_SIGN)));

    public static final BlockFamily GINKGO_FAMILY = BlockFamilies.register(ModBlocks.GINKGO_PLANKS)
            .sign(ModBlocks.STANDING_GINKGO_SIGN, ModBlocks.HANGING_GINKGO_SIGN)
            .group("wooden").unlockCriterionName("has_planks").build();

    public static final Block GLASS_DOOR = registerBlock("glass_door",
            new DoorBlock(FabricBlockSettings.create().mapColor(MapColor.LIGHT_BLUE).strength(0.3F, 0.3F).sounds(BlockSoundGroup.GLASS).nonOpaque(), BlockSetType.OAK));
    public static final Block GLASS_TRAPDOOR = registerBlock("glass_trapdoor",
            new TrapdoorBlock(FabricBlockSettings.create().mapColor(MapColor.LIGHT_BLUE).strength(0.3F, 0.3F).sounds(BlockSoundGroup.GLASS).nonOpaque(), BlockSetType.OAK));

    public static final Block ARCHAEOLOGY_TABLE = registerBlock("archaeology_table",
            new ArchaeologyTableBlock(FabricBlockSettings.create().mapColor(MapColor.BROWN).strength(2.0F, 3.0F).sounds(BlockSoundGroup.WOOD)));

    public static final Block CURSED_WOOL = registerBlock("cursed_wool",
            new CursedWoolBlock(FabricBlockSettings.create().mapColor(MapColor.ORANGE).strength(0.8F, 0.8F).sounds(BlockSoundGroup.WOOL)));
    public static final Block CURSED_CARPET = registerBlock("cursed_carpet",
            new CursedCarpetBlock(FabricBlockSettings.create().mapColor(MapColor.ORANGE).strength(0.1F, 0.1F).sounds(BlockSoundGroup.WOOL)));

    public static final Block FRAMED_GLASS = registerBlock("framed_glass",
            new GlassBlock(FabricBlockSettings.create().mapColor(MapColor.GRAY).strength(1.0F, 1.0F).sounds(BlockSoundGroup.GLASS).nonOpaque()));
    public static final Block FRAMED_GLASS_PANE = registerBlock("framed_glass_pane",
            new PaneBlock(FabricBlockSettings.create().mapColor(MapColor.GRAY).strength(1.0F, 1.0F).sounds(BlockSoundGroup.GLASS).nonOpaque()));

    public static final Block FRAMED_GLASS_BOTTOM = registerBlock("framed_glass_bottom",
            new GlassBlock(FabricBlockSettings.create().mapColor(MapColor.GRAY).strength(1.0F, 1.0F).sounds(BlockSoundGroup.GLASS).nonOpaque()));
    public static final Block FRAMED_GLASS_BOTTOM_PANE = registerBlock("framed_glass_bottom_pane",
            new PaneBlock(FabricBlockSettings.create().mapColor(MapColor.GRAY).strength(1.0F, 1.0F).sounds(BlockSoundGroup.GLASS).nonOpaque()));
    public static final Block FRAMED_GLASS_MIDDLE = registerBlock("framed_glass_middle",
            new GlassBlock(FabricBlockSettings.create().mapColor(MapColor.GRAY).strength(1.0F, 1.0F).sounds(BlockSoundGroup.GLASS).nonOpaque()));
    public static final Block FRAMED_GLASS_MIDDLE_PANE = registerBlock("framed_glass_middle_pane",
            new PaneBlock(FabricBlockSettings.create().mapColor(MapColor.GRAY).strength(1.0F, 1.0F).sounds(BlockSoundGroup.GLASS).nonOpaque()));
    public static final Block FRAMED_GLASS_TOP = registerBlock("framed_glass_top",
            new GlassBlock(FabricBlockSettings.create().mapColor(MapColor.GRAY).strength(1.0F, 1.0F).sounds(BlockSoundGroup.GLASS).nonOpaque()));
    public static final Block FRAMED_GLASS_TOP_PANE = registerBlock("framed_glass_top_pane",
            new PaneBlock(FabricBlockSettings.create().mapColor(MapColor.GRAY).strength(1.0F, 1.0F).sounds(BlockSoundGroup.GLASS).nonOpaque()));

    public static final Block FRAMED_GLASS_SPLIT = registerBlock("framed_glass_split",
            new GlassBlock(FabricBlockSettings.create().mapColor(MapColor.GRAY).strength(1.0F, 1.0F).sounds(BlockSoundGroup.GLASS).nonOpaque()));
    public static final Block FRAMED_GLASS_SPLIT_PANE = registerBlock("framed_glass_split_pane",
            new PaneBlock(FabricBlockSettings.create().mapColor(MapColor.GRAY).strength(1.0F, 1.0F).sounds(BlockSoundGroup.GLASS).nonOpaque()));

    public static final Block FRAMED_GLASS_TILED = registerBlock("framed_glass_tiled",
            new GlassBlock(FabricBlockSettings.create().mapColor(MapColor.GRAY).strength(1.0F, 1.0F).sounds(BlockSoundGroup.GLASS).nonOpaque()));
    public static final Block FRAMED_GLASS_TILED_PANE = registerBlock("framed_glass_tiled_pane",
            new PaneBlock(FabricBlockSettings.create().mapColor(MapColor.GRAY).strength(1.0F, 1.0F).sounds(BlockSoundGroup.GLASS).nonOpaque()));
    
    public static final Block FANCY_OAK_PLANKS = registerBlock("fancy_oak_planks",
            new Block(FabricBlockSettings.create().mapColor(MapColor.OAK_TAN).instrument(Instrument.BASS).strength(2.0F, 3.0F).sounds(BlockSoundGroup.WOOD).burnable()));
    public static final Block FANCY_OAK_STAIRS = registerBlock("fancy_oak_stairs",
            new StairsBlock(ModBlocks.FANCY_OAK_PLANKS.getDefaultState(), FabricBlockSettings.create().mapColor(MapColor.OAK_TAN).instrument(Instrument.BASS).strength(2.0F, 3.0F).sounds(BlockSoundGroup.WOOD).burnable()));
    public static final Block FANCY_OAK_SLAB = registerBlock("fancy_oak_slab",
            new SlabBlock(FabricBlockSettings.create().mapColor(MapColor.OAK_TAN).instrument(Instrument.BASS).strength(2.0F, 3.0F).sounds(BlockSoundGroup.WOOD).burnable()));
    public static final Block FANCY_OAK_TRAPDOOR = registerBlock("fancy_oak_trapdoor",
            new TrapdoorBlock(FabricBlockSettings.create().mapColor(MapColor.OAK_TAN).instrument(Instrument.BASS).strength(2.0F, 3.0F).sounds(BlockSoundGroup.WOOD).burnable(), BlockSetType.OAK));

    public static final Block FANCY_SPRUCE_PLANKS = registerBlock("fancy_spruce_planks",
            new Block(FabricBlockSettings.create().mapColor(MapColor.SPRUCE_BROWN).instrument(Instrument.BASS).strength(2.0F, 3.0F).sounds(BlockSoundGroup.WOOD).burnable()));
    public static final Block FANCY_SPRUCE_STAIRS = registerBlock("fancy_spruce_stairs",
            new StairsBlock(ModBlocks.FANCY_SPRUCE_PLANKS.getDefaultState(), FabricBlockSettings.create().mapColor(MapColor.SPRUCE_BROWN).instrument(Instrument.BASS).strength(2.0F, 3.0F).sounds(BlockSoundGroup.WOOD).burnable()));
    public static final Block FANCY_SPRUCE_SLAB = registerBlock("fancy_spruce_slab",
            new SlabBlock(FabricBlockSettings.create().mapColor(MapColor.SPRUCE_BROWN).instrument(Instrument.BASS).strength(2.0F, 3.0F).sounds(BlockSoundGroup.WOOD).burnable()));
    public static final Block FANCY_SPRUCE_TRAPDOOR = registerBlock("fancy_spruce_trapdoor",
            new TrapdoorBlock(FabricBlockSettings.create().mapColor(MapColor.SPRUCE_BROWN).instrument(Instrument.BASS).strength(2.0F, 3.0F).sounds(BlockSoundGroup.WOOD).burnable(), BlockSetType.SPRUCE));

    public static final Block FANCY_BIRCH_PLANKS = registerBlock("fancy_birch_planks",
            new Block(FabricBlockSettings.create().mapColor(MapColor.PALE_YELLOW).instrument(Instrument.BASS).strength(2.0F, 3.0F).sounds(BlockSoundGroup.WOOD).burnable()));
    public static final Block FANCY_BIRCH_STAIRS = registerBlock("fancy_birch_stairs",
            new StairsBlock(ModBlocks.FANCY_BIRCH_PLANKS.getDefaultState(), FabricBlockSettings.create().mapColor(MapColor.PALE_YELLOW).instrument(Instrument.BASS).strength(2.0F, 3.0F).sounds(BlockSoundGroup.WOOD).burnable()));
    public static final Block FANCY_BIRCH_SLAB = registerBlock("fancy_birch_slab",
            new SlabBlock(FabricBlockSettings.create().mapColor(MapColor.PALE_YELLOW).instrument(Instrument.BASS).strength(2.0F, 3.0F).sounds(BlockSoundGroup.WOOD).burnable()));
    public static final Block FANCY_BIRCH_TRAPDOOR = registerBlock("fancy_birch_trapdoor",
            new TrapdoorBlock(FabricBlockSettings.create().mapColor(MapColor.PALE_YELLOW).instrument(Instrument.BASS).strength(2.0F, 3.0F).sounds(BlockSoundGroup.WOOD).burnable(), BlockSetType.BIRCH));

    public static final Block FANCY_JUNGLE_PLANKS = registerBlock("fancy_jungle_planks",
            new Block(FabricBlockSettings.create().mapColor(MapColor.DIRT_BROWN).instrument(Instrument.BASS).strength(2.0F, 3.0F).sounds(BlockSoundGroup.WOOD).burnable()));
    public static final Block FANCY_JUNGLE_STAIRS = registerBlock("fancy_jungle_stairs",
            new StairsBlock(ModBlocks.FANCY_JUNGLE_PLANKS.getDefaultState(), FabricBlockSettings.create().mapColor(MapColor.DIRT_BROWN).instrument(Instrument.BASS).strength(2.0F, 3.0F).sounds(BlockSoundGroup.WOOD).burnable()));
    public static final Block FANCY_JUNGLE_SLAB = registerBlock("fancy_jungle_slab",
            new SlabBlock(FabricBlockSettings.create().mapColor(MapColor.DIRT_BROWN).instrument(Instrument.BASS).strength(2.0F, 3.0F).sounds(BlockSoundGroup.WOOD).burnable()));
    public static final Block FANCY_JUNGLE_TRAPDOOR = registerBlock("fancy_jungle_trapdoor",
            new TrapdoorBlock(FabricBlockSettings.create().mapColor(MapColor.DIRT_BROWN).instrument(Instrument.BASS).strength(2.0F, 3.0F).sounds(BlockSoundGroup.WOOD).burnable(), BlockSetType.JUNGLE));

    public static final Block FANCY_ACACIA_PLANKS = registerBlock("fancy_acacia_planks",
            new Block(FabricBlockSettings.create().mapColor(MapColor.ORANGE).instrument(Instrument.BASS).strength(2.0F, 3.0F).sounds(BlockSoundGroup.WOOD).burnable()));
    public static final Block FANCY_ACACIA_STAIRS = registerBlock("fancy_acacia_stairs",
            new StairsBlock(ModBlocks.FANCY_ACACIA_PLANKS.getDefaultState(), FabricBlockSettings.create().mapColor(MapColor.ORANGE).instrument(Instrument.BASS).strength(2.0F, 3.0F).sounds(BlockSoundGroup.WOOD).burnable()));
    public static final Block FANCY_ACACIA_SLAB = registerBlock("fancy_acacia_slab",
            new SlabBlock(FabricBlockSettings.create().mapColor(MapColor.ORANGE).instrument(Instrument.BASS).strength(2.0F, 3.0F).sounds(BlockSoundGroup.WOOD).burnable()));
    public static final Block FANCY_ACACIA_TRAPDOOR = registerBlock("fancy_acacia_trapdoor",
            new TrapdoorBlock(FabricBlockSettings.create().mapColor(MapColor.ORANGE).instrument(Instrument.BASS).strength(2.0F, 3.0F).sounds(BlockSoundGroup.WOOD).burnable(), BlockSetType.ACACIA));

    public static final Block FANCY_DARK_OAK_PLANKS = registerBlock("fancy_dark_oak_planks",
            new Block(FabricBlockSettings.create().mapColor(MapColor.BROWN).instrument(Instrument.BASS).strength(2.0F, 3.0F).sounds(BlockSoundGroup.WOOD).burnable()));
    public static final Block FANCY_DARK_OAK_STAIRS = registerBlock("fancy_dark_oak_stairs",
            new StairsBlock(ModBlocks.FANCY_DARK_OAK_PLANKS.getDefaultState(), FabricBlockSettings.create().mapColor(MapColor.BROWN).instrument(Instrument.BASS).strength(2.0F, 3.0F).sounds(BlockSoundGroup.WOOD).burnable()));
    public static final Block FANCY_DARK_OAK_SLAB = registerBlock("fancy_dark_oak_slab",
            new SlabBlock(FabricBlockSettings.create().mapColor(MapColor.BROWN).instrument(Instrument.BASS).strength(2.0F, 3.0F).sounds(BlockSoundGroup.WOOD).burnable()));
    public static final Block FANCY_DARK_OAK_TRAPDOOR = registerBlock("fancy_dark_oak_trapdoor",
            new TrapdoorBlock(FabricBlockSettings.create().mapColor(MapColor.BROWN).instrument(Instrument.BASS).strength(2.0F, 3.0F).sounds(BlockSoundGroup.WOOD).burnable(), BlockSetType.DARK_OAK));

    public static final Block FANCY_MANGROVE_PLANKS = registerBlock("fancy_mangrove_planks",
            new Block(FabricBlockSettings.create().mapColor(MapColor.RED).instrument(Instrument.BASS).strength(2.0F, 3.0F).sounds(BlockSoundGroup.WOOD).burnable()));
    public static final Block FANCY_MANGROVE_STAIRS = registerBlock("fancy_mangrove_stairs",
            new StairsBlock(ModBlocks.FANCY_MANGROVE_PLANKS.getDefaultState(), FabricBlockSettings.create().mapColor(MapColor.RED).instrument(Instrument.BASS).strength(2.0F, 3.0F).sounds(BlockSoundGroup.WOOD).burnable()));
    public static final Block FANCY_MANGROVE_SLAB = registerBlock("fancy_mangrove_slab",
            new SlabBlock(FabricBlockSettings.create().mapColor(MapColor.RED).instrument(Instrument.BASS).strength(2.0F, 3.0F).sounds(BlockSoundGroup.WOOD).burnable()));
    public static final Block FANCY_MANGROVE_TRAPDOOR = registerBlock("fancy_mangrove_trapdoor",
            new TrapdoorBlock(FabricBlockSettings.create().mapColor(MapColor.RED).instrument(Instrument.BASS).strength(2.0F, 3.0F).sounds(BlockSoundGroup.WOOD).burnable(), BlockSetType.MANGROVE));

    public static final Block FANCY_CHERRY_PLANKS = registerBlock("fancy_cherry_planks",
            new Block(FabricBlockSettings.create().mapColor(MapColor.TERRACOTTA_WHITE).instrument(Instrument.BASS).strength(2.0F, 3.0F).sounds(BlockSoundGroup.WOOD).burnable()));
    public static final Block FANCY_CHERRY_STAIRS = registerBlock("fancy_cherry_stairs",
            new StairsBlock(ModBlocks.FANCY_CHERRY_PLANKS.getDefaultState(), FabricBlockSettings.create().mapColor(MapColor.TERRACOTTA_WHITE).instrument(Instrument.BASS).strength(2.0F, 3.0F).sounds(BlockSoundGroup.WOOD).burnable()));
    public static final Block FANCY_CHERRY_SLAB = registerBlock("fancy_cherry_slab",
            new SlabBlock(FabricBlockSettings.create().mapColor(MapColor.TERRACOTTA_WHITE).instrument(Instrument.BASS).strength(2.0F, 3.0F).sounds(BlockSoundGroup.WOOD).burnable()));
    public static final Block FANCY_CHERRY_TRAPDOOR = registerBlock("fancy_cherry_trapdoor",
            new TrapdoorBlock(FabricBlockSettings.create().mapColor(MapColor.TERRACOTTA_WHITE).instrument(Instrument.BASS).strength(2.0F, 3.0F).sounds(BlockSoundGroup.WOOD).burnable(), BlockSetType.CHERRY));

    public static final Block FANCY_PALE_OAK_PLANKS = registerBlock("fancy_pale_oak_planks",
            new Block(FabricBlockSettings.create().mapColor(MapColor.WHITE).instrument(Instrument.BASS).strength(2.0F, 3.0F).sounds(BlockSoundGroup.WOOD).burnable()));
    public static final Block FANCY_PALE_OAK_STAIRS = registerBlock("fancy_pale_oak_stairs",
            new StairsBlock(ModBlocks.FANCY_PALE_OAK_PLANKS.getDefaultState(), FabricBlockSettings.create().mapColor(MapColor.WHITE).instrument(Instrument.BASS).strength(2.0F, 3.0F).sounds(BlockSoundGroup.WOOD).burnable()));
    public static final Block FANCY_PALE_OAK_SLAB = registerBlock("fancy_pale_oak_slab",
            new SlabBlock(FabricBlockSettings.create().mapColor(MapColor.WHITE).instrument(Instrument.BASS).strength(2.0F, 3.0F).sounds(BlockSoundGroup.WOOD).burnable()));
    public static final Block FANCY_PALE_OAK_TRAPDOOR = registerBlock("fancy_pale_oak_trapdoor",
            new TrapdoorBlock(FabricBlockSettings.create().mapColor(MapColor.WHITE).instrument(Instrument.BASS).strength(2.0F, 3.0F).sounds(BlockSoundGroup.WOOD).burnable(), BlockSetType.OAK));

    public static final Block FANCY_BAMBOO_PLANKS = registerBlock("fancy_bamboo_planks",
            new Block(FabricBlockSettings.create().mapColor(MapColor.YELLOW).instrument(Instrument.BASS).strength(2.0F, 3.0F).sounds(BlockSoundGroup.WOOD).burnable()));
    public static final Block FANCY_BAMBOO_STAIRS = registerBlock("fancy_bamboo_stairs",
            new StairsBlock(ModBlocks.FANCY_BAMBOO_PLANKS.getDefaultState(), FabricBlockSettings.create().mapColor(MapColor.YELLOW).instrument(Instrument.BASS).strength(2.0F, 3.0F).sounds(BlockSoundGroup.WOOD).burnable()));
    public static final Block FANCY_BAMBOO_SLAB = registerBlock("fancy_bamboo_slab",
            new SlabBlock(FabricBlockSettings.create().mapColor(MapColor.YELLOW).instrument(Instrument.BASS).strength(2.0F, 3.0F).sounds(BlockSoundGroup.WOOD).burnable()));
    public static final Block FANCY_BAMBOO_TRAPDOOR = registerBlock("fancy_bamboo_trapdoor",
            new TrapdoorBlock(FabricBlockSettings.create().mapColor(MapColor.YELLOW).instrument(Instrument.BASS).strength(2.0F, 3.0F).sounds(BlockSoundGroup.WOOD).burnable(), BlockSetType.BAMBOO));

    public static final Block FANCY_CRIMSON_PLANKS = registerBlock("fancy_crimson_planks",
            new Block(FabricBlockSettings.create().mapColor(MapColor.DULL_PINK).instrument(Instrument.BASS).strength(2.0F, 3.0F).sounds(BlockSoundGroup.WOOD)));
    public static final Block FANCY_CRIMSON_STAIRS = registerBlock("fancy_crimson_stairs",
            new StairsBlock(ModBlocks.FANCY_CRIMSON_PLANKS.getDefaultState(), FabricBlockSettings.create().mapColor(MapColor.DULL_PINK).instrument(Instrument.BASS).strength(2.0F, 3.0F).sounds(BlockSoundGroup.WOOD)));
    public static final Block FANCY_CRIMSON_SLAB = registerBlock("fancy_crimson_slab",
            new SlabBlock(FabricBlockSettings.create().mapColor(MapColor.DULL_PINK).instrument(Instrument.BASS).strength(2.0F, 3.0F).sounds(BlockSoundGroup.WOOD)));
    public static final Block FANCY_CRIMSON_TRAPDOOR = registerBlock("fancy_crimson_trapdoor",
            new TrapdoorBlock(FabricBlockSettings.create().mapColor(MapColor.DULL_PINK).instrument(Instrument.BASS).strength(2.0F, 3.0F).sounds(BlockSoundGroup.WOOD), BlockSetType.CRIMSON));

    public static final Block FANCY_WARPED_PLANKS = registerBlock("fancy_warped_planks",
            new Block(FabricBlockSettings.create().mapColor(MapColor.DARK_AQUA).instrument(Instrument.BASS).strength(2.0F, 3.0F).sounds(BlockSoundGroup.WOOD)));
    public static final Block FANCY_WARPED_STAIRS = registerBlock("fancy_warped_stairs",
            new StairsBlock(ModBlocks.FANCY_WARPED_PLANKS.getDefaultState(), FabricBlockSettings.create().mapColor(MapColor.DARK_AQUA).instrument(Instrument.BASS).strength(2.0F, 3.0F).sounds(BlockSoundGroup.WOOD)));
    public static final Block FANCY_WARPED_SLAB = registerBlock("fancy_warped_slab",
            new SlabBlock(FabricBlockSettings.create().mapColor(MapColor.DARK_AQUA).instrument(Instrument.BASS).strength(2.0F, 3.0F).sounds(BlockSoundGroup.WOOD)));
    public static final Block FANCY_WARPED_TRAPDOOR = registerBlock("fancy_warped_trapdoor",
            new TrapdoorBlock(FabricBlockSettings.create().mapColor(MapColor.DARK_AQUA).instrument(Instrument.BASS).strength(2.0F, 3.0F).sounds(BlockSoundGroup.WOOD), BlockSetType.WARPED));

    public static final Block FANCY_GINKGO_PLANKS = registerBlock("fancy_ginkgo_planks",
            new Block(FabricBlockSettings.create().mapColor(MapColor.PALE_GREEN).instrument(Instrument.BASS).strength(2.0F, 3.0F).sounds(BlockSoundGroup.WOOD).burnable()));
    public static final Block FANCY_GINKGO_STAIRS = registerBlock("fancy_ginkgo_stairs",
            new StairsBlock(ModBlocks.FANCY_GINKGO_PLANKS.getDefaultState(), FabricBlockSettings.create().mapColor(MapColor.PALE_GREEN).instrument(Instrument.BASS).strength(2.0F, 3.0F).sounds(BlockSoundGroup.WOOD).burnable()));
    public static final Block FANCY_GINKGO_SLAB = registerBlock("fancy_ginkgo_slab",
            new SlabBlock(FabricBlockSettings.create().mapColor(MapColor.PALE_GREEN).instrument(Instrument.BASS).strength(2.0F, 3.0F).sounds(BlockSoundGroup.WOOD).burnable()));
    public static final Block FANCY_GINKGO_TRAPDOOR = registerBlock("fancy_ginkgo_trapdoor",
            new TrapdoorBlock(FabricBlockSettings.create().mapColor(MapColor.PALE_GREEN).instrument(Instrument.BASS).strength(2.0F, 3.0F).sounds(BlockSoundGroup.WOOD).burnable(), BlockSetType.OAK));

    public static final Block FANCY_CHARRED_PLANKS = registerBlock("fancy_charred_planks",
            new Block(FabricBlockSettings.create().mapColor(MapColor.DEEPSLATE_GRAY).instrument(Instrument.BASS).strength(1.0F, 1.5F).sounds(BlockSoundGroup.WOOD)));
    public static final Block FANCY_CHARRED_STAIRS = registerBlock("fancy_charred_stairs",
            new StairsBlock(ModBlocks.FANCY_CHARRED_PLANKS.getDefaultState(), FabricBlockSettings.create().mapColor(MapColor.DEEPSLATE_GRAY).instrument(Instrument.BASS).strength(1.0F, 1.5F).sounds(BlockSoundGroup.WOOD)));
    public static final Block FANCY_CHARRED_SLAB = registerBlock("fancy_charred_slab",
            new SlabBlock(FabricBlockSettings.create().mapColor(MapColor.DEEPSLATE_GRAY).instrument(Instrument.BASS).strength(1.0F, 1.5F).sounds(BlockSoundGroup.WOOD)));
    public static final Block FANCY_CHARRED_TRAPDOOR = registerBlock("fancy_charred_trapdoor",
            new TrapdoorBlock(FabricBlockSettings.create().mapColor(MapColor.DEEPSLATE_GRAY).instrument(Instrument.BASS).strength(1.0F, 1.5F).sounds(BlockSoundGroup.WOOD), BlockSetType.OAK));
    public static final Block RITUALISTIC_FANCY_CHARRED_PLANKS = registerBlockWithoutItem("ritualistic_fancy_charred_planks",
            new RitualisticCircleBlock(FabricBlockSettings.create().mapColor(MapColor.DEEPSLATE_GRAY).instrument(Instrument.BASS).strength(1.0F, 1.5F).sounds(BlockSoundGroup.WOOD)));

    public static final Block BEDROCK = registerBlockWithoutItem("bedrock",
            new DataSaverBlock(FabricBlockSettings.create().mapColor(MapColor.GRAY).strength(-1.0F, 3600000.0F)));

    private static Block registerBlock(String name, Block block) {
        registerBlockItem(name, block);
        return Registry.register(Registries.BLOCK, new Identifier(TLOTD.MOD_ID, name), block);
    }

    private static Block registerRarityBlock(String name, Block block, Rarity rarity) {
        registerRarityBlockItem(name, block, rarity);
        return Registry.register(Registries.BLOCK, new Identifier(TLOTD.MOD_ID, name), block);
    }

    private static Block registerSmallStackableBlock(String name, Block block) {
        registerSmallStackBlockItem(name, block);
        return Registry.register(Registries.BLOCK, new Identifier(TLOTD.MOD_ID, name), block);
    }

    private static Block registerJamJarBlock(String name, Block block) {
        registerJamJarBlockItem(name, block);
        return Registry.register(Registries.BLOCK, new Identifier(TLOTD.MOD_ID, name), block);
    }

    private static Block registerCompatJamJarBlock(String name, Block block, String compat) {
        registerCompatJamJarBlockItem(name, block, compat);
        return Registry.register(Registries.BLOCK, new Identifier(TLOTD.MOD_ID, name), block);
    }

    private static Block registerWoodenSteinBlock(String name, Block block) {
        registerWoodenSteinBlockItem(name, block);
        return Registry.register(Registries.BLOCK, new Identifier(TLOTD.MOD_ID, name), block);
    }

    private static Block registerDrinkableWoodenSteinBlock(String name, Block block, String compat) {
        registerDrinkableWoodenSteinBlockItem(name, block, compat);
        return Registry.register(Registries.BLOCK, new Identifier(TLOTD.MOD_ID, name), block);
    }

    private static Block registerRemainderSteinBlock(String name, Block block, String compat) {
        registerRemainderSteinBlockItem(name, block, compat);
        return Registry.register(Registries.BLOCK, new Identifier(TLOTD.MOD_ID, name), block);
    }

    private static Block registerIrradiatedBlock(String name, Block block) {
        registerIrradiatedBlockItem(name, block);
        return Registry.register(Registries.BLOCK, new Identifier(TLOTD.MOD_ID, name), block);
    }

    private static Block registerNyiBlock(String name, Block block) {
        registerNyiBlockItem(name, block);
        return Registry.register(Registries.BLOCK, new Identifier(TLOTD.MOD_ID, name), block);
    }

    private static Block registerVCRBlock(String name, Block block) {
        registerVCRBlockItem(name, block);
        return Registry.register(Registries.BLOCK, new Identifier(TLOTD.MOD_ID, name), block);
    }

    private static Block registerBlockWithoutItem(String name, Block block) {
        return Registry.register(Registries.BLOCK, new Identifier(TLOTD.MOD_ID, name), block);
    }

    private static Block registerCompatBlock(String name, Block block, Rarity rarity, String compat) {
        registerCompatBlockItem(name, block, rarity, compat);
        return Registry.register(Registries.BLOCK, new Identifier(TLOTD.MOD_ID, name), block);
    }

    private static Item registerBlockItem(String name, Block block) {
        return Registry.register(Registries.ITEM, new Identifier(TLOTD.MOD_ID, name),
                new BlockItem(block, new FabricItemSettings()));
    }

    private static Item registerRarityBlockItem(String name, Block block, Rarity rarity) {
        return Registry.register(Registries.ITEM, new Identifier(TLOTD.MOD_ID, name),
                new BlockItem(block, new FabricItemSettings().rarity(rarity)));
    }

    private static Item registerCompatBlockItem(String name, Block block, Rarity rarity, String compat) {
        return Registry.register(Registries.ITEM, new Identifier(TLOTD.MOD_ID, name),
                new CompatBlockItem(block, new FabricItemSettings().rarity(rarity), compat));
    }

    private static Item registerSmallStackBlockItem(String name, Block block) {
        return Registry.register(Registries.ITEM, new Identifier(TLOTD.MOD_ID, name),
                new BlockItem(block, new FabricItemSettings().maxCount(16)));
    }

    private static Item registerJamJarBlockItem(String name, Block block) {
        return Registry.register(Registries.ITEM, new Identifier(TLOTD.MOD_ID, name),
                new BlockItem(block, new FabricItemSettings().maxCount(16).recipeRemainder(ModBlocks.PRESERVES_JAR.asItem())));
    }

    private static Item registerCompatJamJarBlockItem(String name, Block block, String compat) {
        return Registry.register(Registries.ITEM, new Identifier(TLOTD.MOD_ID, name),
                new CompatBlockItem(block, new FabricItemSettings().maxCount(16).recipeRemainder(ModBlocks.PRESERVES_JAR.asItem()),compat));
    }

    private static Item registerWoodenSteinBlockItem(String name, Block block) {
        return Registry.register(Registries.ITEM, new Identifier(TLOTD.MOD_ID, name),
                new WoodenSteinBlockItem(block, new FabricItemSettings().maxCount(16)));
    }

    private static Item registerDrinkableWoodenSteinBlockItem(String name, Block block, String compat) {
        return Registry.register(Registries.ITEM, new Identifier(TLOTD.MOD_ID, name),
                new DrinkableWoodenSteinBlockItem(block, new FabricItemSettings().food(ModFoodComponents.WOODEN_LIQUID_STEIN).maxCount(16),compat));
    }

    private static Item registerRemainderSteinBlockItem(String name, Block block, String compat) {
        return Registry.register(Registries.ITEM, new Identifier(TLOTD.MOD_ID, name),
                new DrinkableWoodenSteinBlockItem(block, new FabricItemSettings().food(ModFoodComponents.WOODEN_LIQUID_STEIN).maxCount(16).recipeRemainder(WOODEN_STEIN.asItem()),compat));
    }

    private static Item registerVCRBlockItem(String name, Block block) {
        return Registry.register(Registries.ITEM, new Identifier(TLOTD.MOD_ID, name),
                new VCRBlockItem(block, new FabricItemSettings()));
    }

    private static Item registerIrradiatedBlockItem(String name, Block block) {
        return Registry.register(Registries.ITEM, new Identifier(TLOTD.MOD_ID, name),
                new IrradiatedBlockItem(block, new FabricItemSettings()));
    }

    private static Item registerNyiBlockItem(String name, Block block) {
        return Registry.register(Registries.ITEM, new Identifier(TLOTD.MOD_ID, name),
                new NyiBlockItem(block, new FabricItemSettings()));
    }

    public static void registerModBlocks() {
        TLOTD.LOGGER.info("Registering ModBlocks for " + TLOTD.MOD_ID);
    }
}
