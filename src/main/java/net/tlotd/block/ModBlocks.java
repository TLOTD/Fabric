package net.tlotd.block;

import net.minecraft.core.block.Block;
import net.minecraft.core.block.Blocks;
import net.minecraft.core.block.material.Material;
import net.minecraft.core.block.tag.BlockTags;
import net.tlotd.TLOTD;
import net.tlotd.block.custom.*;
import turniplabs.halplibe.helper.BlockBuilder;

import static net.minecraft.core.item.tool.ItemToolPickaxe.miningLevels;

public class ModBlocks {

	public static int blockId = TLOTD.CFG.getInt("IDs.starting_block_id");

	public static BlockBuilder fullBlock = new BlockBuilder(TLOTD.MOD_ID);

	public static Block<?> STONE_FOSSIL;
	public static Block<?> BASALT_FOSSIL;
	public static Block<?> LIMESTONE_FOSSIL;
	public static Block<?> GRANITE_FOSSIL;
	public static Block<?> PERMAFROST_FOSSIL;
	public static Block<?> RED_DEEPSLATE_FOSSIL;

	public static Block<?> HELIORITE_STONE_ORE;
	public static Block<?> HELIORITE_BASALT_ORE;
	public static Block<?> HELIORITE_LIMESTONE_ORE;
	public static Block<?> HELIORITE_GRANITE_ORE;
	public static Block<?> HELIORITE_PERMAFROST_ORE;
	public static Block<?> HELIORITE_RED_DEEPSLATE_ORE;
	public static Block<?> HELIORITE_COMB_BLOCK;
	public static Block<?> HELIORITE_BLOCK;

	public static Block<?> ENDURIUM_STONE_ORE;
	public static Block<?> ENDURIUM_BASALT_ORE;
	public static Block<?> ENDURIUM_LIMESTONE_ORE;
	public static Block<?> ENDURIUM_GRANITE_ORE;
	public static Block<?> ENDURIUM_PERMAFROST_ORE;
	public static Block<?> ENDURIUM_RED_DEEPSLATE_ORE;
	public static Block<?> RAW_ENDURIUM_BLOCK;
	public static Block<?> ENDURIUM_BLOCK;

	public static Block<?> PALLADIUM_STONE_ORE;
	public static Block<?> PALLADIUM_BASALT_ORE;
	public static Block<?> PALLADIUM_LIMESTONE_ORE;
	public static Block<?> PALLADIUM_GRANITE_ORE;
	public static Block<?> PALLADIUM_PERMAFROST_ORE;
	public static Block<?> PALLADIUM_RED_DEEPSLATE_ORE;
	public static Block<?> RAW_PALLADIUM_BLOCK;
	public static Block<?> PALLADIUM_BLOCK;

	public static Block<?> JURASSOLINE_STONE_ORE;
	public static Block<?> JURASSOLINE_BASALT_ORE;
	public static Block<?> JURASSOLINE_LIMESTONE_ORE;
	public static Block<?> JURASSOLINE_GRANITE_ORE;
	public static Block<?> JURASSOLINE_PERMAFROST_ORE;
	public static Block<?> JURASSOLINE_RED_DEEPSLATE_ORE;
	public static Block<?> JURASSOLINE_CRYSTAL_BLOCK;
	public static Block<?> JURASSOLINE_BLOCK;

	public static Block<?> CINNABAR_STONE_ORE;
	public static Block<?> CINNABAR_BASALT_ORE;
	public static Block<?> CINNABAR_LIMESTONE_ORE;
	public static Block<?> CINNABAR_GRANITE_ORE;
	public static Block<?> CINNABAR_PERMAFROST_ORE;
	public static Block<?> CINNABAR_RED_DEEPSLATE_ORE;
	public static Block<?> CINNABAR_CRYSTAL_BLOCK;
	public static Block<?> CINNABAR_BLOCK;

	public static Block<?> NEBULAR_STONE_ORE;
	public static Block<?> NEBULAR_BASALT_ORE;
	public static Block<?> NEBULAR_LIMESTONE_ORE;
	public static Block<?> NEBULAR_GRANITE_ORE;
	public static Block<?> NEBULAR_PERMAFROST_ORE;
	public static Block<?> NEBULAR_RED_DEEPSLATE_ORE;
	public static Block<?> RAW_NEBULAR_BLOCK;
	public static Block<?> NEBULAR_BLOCK;

	public static Block<?> MITHRIL_STONE_ORE;
	public static Block<?> MITHRIL_BASALT_ORE;
	public static Block<?> MITHRIL_LIMESTONE_ORE;
	public static Block<?> MITHRIL_GRANITE_ORE;
	public static Block<?> MITHRIL_PERMAFROST_ORE;
	public static Block<?> MITHRIL_RED_DEEPSLATE_ORE;
	public static Block<?> MITHRIL_BEDROCK_ORE;
	public static Block<?> RAW_MITHRIL_BLOCK;
	public static Block<?> MITHRIL_BLOCK;

	public static Block<?> RED_DEEPSLATE;
	public static Block<?> RED_DEEPSLATE_BRICKS;
	public static Block<?> COBBLED_RED_DEEPSLATE;
	public static Block<?> COBBLED_RED_DEEPSLATE_BRICKS;

	public void initializeBlockDetails() {
		miningLevels.put(ModBlocks.STONE_FOSSIL, 2);
		miningLevels.put(ModBlocks.BASALT_FOSSIL, 2);
		miningLevels.put(ModBlocks.LIMESTONE_FOSSIL, 2);
		miningLevels.put(ModBlocks.GRANITE_FOSSIL, 2);
		miningLevels.put(ModBlocks.PERMAFROST_FOSSIL, 2);
		miningLevels.put(ModBlocks.RED_DEEPSLATE_FOSSIL, 2);

		miningLevels.put(ModBlocks.HELIORITE_STONE_ORE, 3);
		miningLevels.put(ModBlocks.HELIORITE_BASALT_ORE, 3);
		miningLevels.put(ModBlocks.HELIORITE_LIMESTONE_ORE, 3);
		miningLevels.put(ModBlocks.HELIORITE_GRANITE_ORE, 3);
		miningLevels.put(ModBlocks.HELIORITE_PERMAFROST_ORE, 3);
		miningLevels.put(ModBlocks.HELIORITE_RED_DEEPSLATE_ORE, 3);
		miningLevels.put(ModBlocks.HELIORITE_COMB_BLOCK, 3);
		miningLevels.put(ModBlocks.HELIORITE_BLOCK, 3);

		miningLevels.put(ModBlocks.ENDURIUM_STONE_ORE, 3);
		miningLevels.put(ModBlocks.ENDURIUM_BASALT_ORE, 3);
		miningLevels.put(ModBlocks.ENDURIUM_LIMESTONE_ORE, 3);
		miningLevels.put(ModBlocks.ENDURIUM_GRANITE_ORE, 3);
		miningLevels.put(ModBlocks.ENDURIUM_PERMAFROST_ORE, 3);
		miningLevels.put(ModBlocks.ENDURIUM_RED_DEEPSLATE_ORE, 3);
		miningLevels.put(ModBlocks.RAW_ENDURIUM_BLOCK, 3);
		miningLevels.put(ModBlocks.ENDURIUM_BLOCK, 3);

		miningLevels.put(ModBlocks.PALLADIUM_STONE_ORE, 4);
		miningLevels.put(ModBlocks.PALLADIUM_BASALT_ORE, 4);
		miningLevels.put(ModBlocks.PALLADIUM_LIMESTONE_ORE, 4);
		miningLevels.put(ModBlocks.PALLADIUM_GRANITE_ORE, 4);
		miningLevels.put(ModBlocks.PALLADIUM_PERMAFROST_ORE, 4);
		miningLevels.put(ModBlocks.PALLADIUM_RED_DEEPSLATE_ORE, 4);
		miningLevels.put(ModBlocks.RAW_PALLADIUM_BLOCK, 4);
		miningLevels.put(ModBlocks.PALLADIUM_BLOCK, 4);

		miningLevels.put(ModBlocks.JURASSOLINE_STONE_ORE, 4);
		miningLevels.put(ModBlocks.JURASSOLINE_BASALT_ORE, 4);
		miningLevels.put(ModBlocks.JURASSOLINE_LIMESTONE_ORE, 4);
		miningLevels.put(ModBlocks.JURASSOLINE_GRANITE_ORE, 4);
		miningLevels.put(ModBlocks.JURASSOLINE_PERMAFROST_ORE, 4);
		miningLevels.put(ModBlocks.JURASSOLINE_RED_DEEPSLATE_ORE, 4);
		miningLevels.put(ModBlocks.JURASSOLINE_CRYSTAL_BLOCK, 4);
		miningLevels.put(ModBlocks.JURASSOLINE_BLOCK, 4);

		miningLevels.put(ModBlocks.CINNABAR_STONE_ORE, 5);
		miningLevels.put(ModBlocks.CINNABAR_BASALT_ORE, 5);
		miningLevels.put(ModBlocks.CINNABAR_LIMESTONE_ORE, 5);
		miningLevels.put(ModBlocks.CINNABAR_GRANITE_ORE, 5);
		miningLevels.put(ModBlocks.CINNABAR_PERMAFROST_ORE, 5);
		miningLevels.put(ModBlocks.CINNABAR_RED_DEEPSLATE_ORE, 5);
		miningLevels.put(ModBlocks.CINNABAR_CRYSTAL_BLOCK, 5);
		miningLevels.put(ModBlocks.CINNABAR_BLOCK, 5);

		miningLevels.put(ModBlocks.NEBULAR_STONE_ORE, 5);
		miningLevels.put(ModBlocks.NEBULAR_BASALT_ORE, 5);
		miningLevels.put(ModBlocks.NEBULAR_LIMESTONE_ORE, 5);
		miningLevels.put(ModBlocks.NEBULAR_GRANITE_ORE, 5);
		miningLevels.put(ModBlocks.NEBULAR_PERMAFROST_ORE, 5);
		miningLevels.put(ModBlocks.NEBULAR_RED_DEEPSLATE_ORE, 5);
		miningLevels.put(ModBlocks.RAW_NEBULAR_BLOCK, 5);
		miningLevels.put(ModBlocks.NEBULAR_BLOCK, 5);

		miningLevels.put(ModBlocks.MITHRIL_STONE_ORE, 6);
		miningLevels.put(ModBlocks.MITHRIL_BASALT_ORE, 6);
		miningLevels.put(ModBlocks.MITHRIL_LIMESTONE_ORE, 6);
		miningLevels.put(ModBlocks.MITHRIL_GRANITE_ORE, 6);
		miningLevels.put(ModBlocks.MITHRIL_PERMAFROST_ORE, 6);
		miningLevels.put(ModBlocks.MITHRIL_RED_DEEPSLATE_ORE, 6);
		miningLevels.put(ModBlocks.MITHRIL_BEDROCK_ORE, 6);
		miningLevels.put(ModBlocks.RAW_MITHRIL_BLOCK, 6);
		miningLevels.put(ModBlocks.MITHRIL_BLOCK, 6);
	}

	public void registerBlocks() {

		STONE_FOSSIL = fullBlock
			.setTags(BlockTags.MINEABLE_BY_PICKAXE)
			.setHardness(3f)
			.build("stone_fossil", blockId++, b -> new BlockLogicFossil(b, Blocks.STONE, Material.stone)).withBlastResistance(5.0F);
		BASALT_FOSSIL = fullBlock
			.setTags(BlockTags.MINEABLE_BY_PICKAXE)
			.setHardness(3f)
			.build("basalt_fossil", blockId++, b -> new BlockLogicFossil(b, Blocks.BASALT, Material.basalt)).withBlastResistance(5.0F);
		LIMESTONE_FOSSIL = fullBlock
			.setTags(BlockTags.MINEABLE_BY_PICKAXE)
			.setHardness(3f)
			.build("limestone_fossil", blockId++, b -> new BlockLogicFossil(b, Blocks.LIMESTONE, Material.limestone)).withBlastResistance(5.0F);
		GRANITE_FOSSIL = fullBlock
			.setTags(BlockTags.MINEABLE_BY_PICKAXE)
			.setHardness(3f)
			.build("granite_fossil", blockId++, b -> new BlockLogicFossil(b, Blocks.GRANITE, Material.granite)).withBlastResistance(5.0F);
		PERMAFROST_FOSSIL = fullBlock
			.setTags(BlockTags.MINEABLE_BY_PICKAXE)
			.setHardness(3f)
			.build("permafrost_fossil", blockId++, b -> new BlockLogicFossil(b, Blocks.PERMAFROST, Material.permafrost)).withBlastResistance(5.0F);
		RED_DEEPSLATE_FOSSIL = fullBlock
			.setTags(BlockTags.MINEABLE_BY_PICKAXE)
			.setHardness(3f)
			.build("red_deepslate_fossil", blockId++, b -> new BlockLogicFossil(b, ModBlocks.RED_DEEPSLATE, Material.slate)).withBlastResistance(5.0F);

		HELIORITE_STONE_ORE = fullBlock
			.setTags(BlockTags.MINEABLE_BY_PICKAXE)
			.setHardness(3f)
			.build("heliorite_stone_ore", blockId++, b -> new BlockLogicHelioriteOre(b, Blocks.STONE, Material.stone)).withBlastResistance(5.0F);
		HELIORITE_BASALT_ORE = fullBlock
			.setTags(BlockTags.MINEABLE_BY_PICKAXE)
			.setHardness(3f)
			.build("heliorite_basalt_ore", blockId++, b -> new BlockLogicHelioriteOre(b, Blocks.BASALT, Material.basalt)).withBlastResistance(5.0F);
		HELIORITE_LIMESTONE_ORE = fullBlock
			.setTags(BlockTags.MINEABLE_BY_PICKAXE)
			.setHardness(3f)
			.build("heliorite_limestone_ore", blockId++, b -> new BlockLogicHelioriteOre(b, Blocks.LIMESTONE, Material.limestone)).withBlastResistance(5.0F);
		HELIORITE_GRANITE_ORE = fullBlock
			.setTags(BlockTags.MINEABLE_BY_PICKAXE)
			.setHardness(3f)
			.build("heliorite_granite_ore", blockId++, b -> new BlockLogicHelioriteOre(b, Blocks.GRANITE, Material.granite)).withBlastResistance(5.0F);
		HELIORITE_PERMAFROST_ORE = fullBlock
			.setTags(BlockTags.MINEABLE_BY_PICKAXE)
			.setHardness(3f)
			.build("heliorite_permafrost_ore", blockId++, b -> new BlockLogicHelioriteOre(b, Blocks.PERMAFROST, Material.permafrost)).withBlastResistance(5.0F);
		HELIORITE_RED_DEEPSLATE_ORE = fullBlock
			.setTags(BlockTags.MINEABLE_BY_PICKAXE)
			.setHardness(3f)
			.build("heliorite_red_deepslate_ore", blockId++, b -> new BlockLogicHelioriteOre(b, ModBlocks.RED_DEEPSLATE, Material.slate)).withBlastResistance(5.0F);
		HELIORITE_COMB_BLOCK = fullBlock
			.setTags(BlockTags.MINEABLE_BY_PICKAXE)
			.setHardness(3f)
			.build("heliorite_comb_block", blockId++, b -> new BlockLogicOreBlock(b)).withBlastResistance(5.0F);
		HELIORITE_BLOCK = fullBlock
			.setTags(BlockTags.MINEABLE_BY_PICKAXE)
			.setHardness(3f)
			.build("heliorite_block", blockId++, b -> new BlockLogicOreBlock(b)).withBlastResistance(5.0F);

		ENDURIUM_STONE_ORE = fullBlock
			.setTags(BlockTags.MINEABLE_BY_PICKAXE)
			.setHardness(3f)
			.build("endurium_stone_ore", blockId++, b -> new BlockLogicEnduriumOre(b, Blocks.STONE, Material.stone)).withBlastResistance(5.0F);
		ENDURIUM_BASALT_ORE = fullBlock
			.setTags(BlockTags.MINEABLE_BY_PICKAXE)
			.setHardness(3f)
			.build("endurium_basalt_ore", blockId++, b -> new BlockLogicEnduriumOre(b, Blocks.BASALT, Material.basalt)).withBlastResistance(5.0F);
		ENDURIUM_LIMESTONE_ORE = fullBlock
			.setTags(BlockTags.MINEABLE_BY_PICKAXE)
			.setHardness(3f)
			.build("endurium_limestone_ore", blockId++, b -> new BlockLogicEnduriumOre(b, Blocks.LIMESTONE, Material.limestone)).withBlastResistance(5.0F);
		ENDURIUM_GRANITE_ORE = fullBlock
			.setTags(BlockTags.MINEABLE_BY_PICKAXE)
			.setHardness(3f)
			.build("endurium_granite_ore", blockId++, b -> new BlockLogicEnduriumOre(b, Blocks.GRANITE, Material.granite)).withBlastResistance(5.0F);
		ENDURIUM_PERMAFROST_ORE = fullBlock
			.setTags(BlockTags.MINEABLE_BY_PICKAXE)
			.setHardness(3f)
			.build("endurium_permafrost_ore", blockId++, b -> new BlockLogicEnduriumOre(b, Blocks.PERMAFROST, Material.permafrost)).withBlastResistance(5.0F);
		ENDURIUM_RED_DEEPSLATE_ORE = fullBlock
			.setTags(BlockTags.MINEABLE_BY_PICKAXE)
			.setHardness(3f)
			.build("endurium_red_deepslate_ore", blockId++, b -> new BlockLogicEnduriumOre(b, ModBlocks.RED_DEEPSLATE, Material.slate)).withBlastResistance(5.0F);
		RAW_ENDURIUM_BLOCK = fullBlock
			.setTags(BlockTags.MINEABLE_BY_PICKAXE)
			.setHardness(3f)
			.build("raw_endurium_block", blockId++, b -> new BlockLogicOreBlock(b)).withBlastResistance(5.0F);
		ENDURIUM_BLOCK = fullBlock
			.setTags(BlockTags.MINEABLE_BY_PICKAXE)
			.setHardness(3f)
			.build("endurium_block", blockId++, b -> new BlockLogicOreBlock(b)).withBlastResistance(5.0F);

		PALLADIUM_STONE_ORE = fullBlock
			.setTags(BlockTags.MINEABLE_BY_PICKAXE)
			.setHardness(3f)
			.build("palladium_stone_ore", blockId++, b -> new BlockLogicPalladiumOre(b, Blocks.STONE, Material.stone)).withBlastResistance(5.0F);
		PALLADIUM_BASALT_ORE = fullBlock
			.setTags(BlockTags.MINEABLE_BY_PICKAXE)
			.setHardness(3f)
			.build("palladium_basalt_ore", blockId++, b -> new BlockLogicPalladiumOre(b, Blocks.BASALT, Material.basalt)).withBlastResistance(5.0F);
		PALLADIUM_LIMESTONE_ORE = fullBlock
			.setTags(BlockTags.MINEABLE_BY_PICKAXE)
			.setHardness(3f)
			.build("palladium_limestone_ore", blockId++, b -> new BlockLogicPalladiumOre(b, Blocks.LIMESTONE, Material.limestone)).withBlastResistance(5.0F);
		PALLADIUM_GRANITE_ORE = fullBlock
			.setTags(BlockTags.MINEABLE_BY_PICKAXE)
			.setHardness(3f)
			.build("palladium_granite_ore", blockId++, b -> new BlockLogicPalladiumOre(b, Blocks.GRANITE, Material.granite)).withBlastResistance(5.0F);
		PALLADIUM_PERMAFROST_ORE = fullBlock
			.setTags(BlockTags.MINEABLE_BY_PICKAXE)
			.setHardness(3f)
			.build("palladium_permafrost_ore", blockId++, b -> new BlockLogicPalladiumOre(b, Blocks.PERMAFROST, Material.permafrost)).withBlastResistance(5.0F);
		PALLADIUM_RED_DEEPSLATE_ORE = fullBlock
			.setTags(BlockTags.MINEABLE_BY_PICKAXE)
			.setHardness(3f)
			.build("palladium_red_deepslate_ore", blockId++, b -> new BlockLogicPalladiumOre(b, ModBlocks.RED_DEEPSLATE, Material.slate)).withBlastResistance(5.0F);
		RAW_PALLADIUM_BLOCK = fullBlock
			.setTags(BlockTags.MINEABLE_BY_PICKAXE)
			.setHardness(3f)
			.build("raw_palladium_block", blockId++, b -> new BlockLogicOreBlock(b)).withBlastResistance(5.0F);
		PALLADIUM_BLOCK = fullBlock
			.setTags(BlockTags.MINEABLE_BY_PICKAXE)
			.setHardness(3f)
			.build("palladium_block", blockId++, b -> new BlockLogicOreBlock(b)).withBlastResistance(5.0F);

		JURASSOLINE_STONE_ORE = fullBlock
			.setTags(BlockTags.MINEABLE_BY_PICKAXE)
			.setHardness(3f)
			.build("jurassoline_stone_ore", blockId++, b -> new BlockLogicJurassolineOre(b, Blocks.STONE, Material.stone)).withBlastResistance(5.0F);
		JURASSOLINE_BASALT_ORE = fullBlock
			.setTags(BlockTags.MINEABLE_BY_PICKAXE)
			.setHardness(3f)
			.build("jurassoline_basalt_ore", blockId++, b -> new BlockLogicJurassolineOre(b, Blocks.BASALT, Material.basalt)).withBlastResistance(5.0F);
		JURASSOLINE_LIMESTONE_ORE = fullBlock
			.setTags(BlockTags.MINEABLE_BY_PICKAXE)
			.setHardness(3f)
			.build("jurassoline_limestone_ore", blockId++, b -> new BlockLogicJurassolineOre(b, Blocks.LIMESTONE, Material.limestone)).withBlastResistance(5.0F);
		JURASSOLINE_GRANITE_ORE = fullBlock
			.setTags(BlockTags.MINEABLE_BY_PICKAXE)
			.setHardness(3f)
			.build("jurassoline_granite_ore", blockId++, b -> new BlockLogicJurassolineOre(b, Blocks.GRANITE, Material.granite)).withBlastResistance(5.0F);
		JURASSOLINE_PERMAFROST_ORE = fullBlock
			.setTags(BlockTags.MINEABLE_BY_PICKAXE)
			.setHardness(3f)
			.build("jurassoline_permafrost_ore", blockId++, b -> new BlockLogicJurassolineOre(b, Blocks.PERMAFROST, Material.permafrost)).withBlastResistance(5.0F);
		JURASSOLINE_RED_DEEPSLATE_ORE = fullBlock
			.setTags(BlockTags.MINEABLE_BY_PICKAXE)
			.setHardness(3f)
			.build("jurassoline_red_deepslate_ore", blockId++, b -> new BlockLogicJurassolineOre(b, ModBlocks.RED_DEEPSLATE, Material.slate)).withBlastResistance(5.0F);
		JURASSOLINE_CRYSTAL_BLOCK = fullBlock
			.setTags(BlockTags.MINEABLE_BY_PICKAXE)
			.setHardness(3f)
			.build("jurassoline_crystal_block", blockId++, b -> new BlockLogicOreBlock(b)).withBlastResistance(5.0F);
		JURASSOLINE_BLOCK = fullBlock
			.setTags(BlockTags.MINEABLE_BY_PICKAXE)
			.setHardness(3f)
			.build("jurassoline_block", blockId++, b -> new BlockLogicOreBlock(b)).withBlastResistance(5.0F);

		CINNABAR_STONE_ORE = fullBlock
			.setTags(BlockTags.MINEABLE_BY_PICKAXE)
			.setHardness(3f)
			.build("cinnabar_stone_ore", blockId++, b -> new BlockLogicCinnabarOre(b, Blocks.STONE, Material.stone)).withBlastResistance(5.0F);
		CINNABAR_BASALT_ORE = fullBlock
			.setTags(BlockTags.MINEABLE_BY_PICKAXE)
			.setHardness(3f)
			.build("cinnabar_basalt_ore", blockId++, b -> new BlockLogicCinnabarOre(b, Blocks.BASALT, Material.basalt)).withBlastResistance(5.0F);
		CINNABAR_LIMESTONE_ORE = fullBlock
			.setTags(BlockTags.MINEABLE_BY_PICKAXE)
			.setHardness(3f)
			.build("cinnabar_limestone_ore", blockId++, b -> new BlockLogicCinnabarOre(b, Blocks.LIMESTONE, Material.limestone)).withBlastResistance(5.0F);
		CINNABAR_GRANITE_ORE = fullBlock
			.setTags(BlockTags.MINEABLE_BY_PICKAXE)
			.setHardness(3f)
			.build("cinnabar_granite_ore", blockId++, b -> new BlockLogicCinnabarOre(b, Blocks.GRANITE, Material.granite)).withBlastResistance(5.0F);
		CINNABAR_PERMAFROST_ORE = fullBlock
			.setTags(BlockTags.MINEABLE_BY_PICKAXE)
			.setHardness(3f)
			.build("cinnabar_permafrost_ore", blockId++, b -> new BlockLogicCinnabarOre(b, Blocks.PERMAFROST, Material.permafrost)).withBlastResistance(5.0F);
		CINNABAR_RED_DEEPSLATE_ORE = fullBlock
			.setTags(BlockTags.MINEABLE_BY_PICKAXE)
			.setHardness(3f)
			.build("cinnabar_red_deepslate_ore", blockId++, b -> new BlockLogicCinnabarOre(b, ModBlocks.RED_DEEPSLATE, Material.slate)).withBlastResistance(5.0F);
		CINNABAR_CRYSTAL_BLOCK = fullBlock
			.setTags(BlockTags.MINEABLE_BY_PICKAXE)
			.setHardness(3f)
			.build("cinnabar_crystal_block", blockId++, b -> new BlockLogicOreBlock(b)).withBlastResistance(5.0F);
		CINNABAR_BLOCK = fullBlock
			.setTags(BlockTags.MINEABLE_BY_PICKAXE)
			.setHardness(3f)
			.build("cinnabar_block", blockId++, b -> new BlockLogicOreBlock(b)).withBlastResistance(5.0F);

		NEBULAR_STONE_ORE = fullBlock
			.setTags(BlockTags.MINEABLE_BY_PICKAXE)
			.setHardness(3f)
			.build("nebular_stone_ore", blockId++, b -> new BlockLogicNebularOre(b, Blocks.STONE, Material.stone)).withBlastResistance(5.0F);
		NEBULAR_BASALT_ORE = fullBlock
			.setTags(BlockTags.MINEABLE_BY_PICKAXE)
			.setHardness(3f)
			.build("nebular_basalt_ore", blockId++, b -> new BlockLogicNebularOre(b, Blocks.BASALT, Material.basalt)).withBlastResistance(5.0F);
		NEBULAR_LIMESTONE_ORE = fullBlock
			.setTags(BlockTags.MINEABLE_BY_PICKAXE)
			.setHardness(3f)
			.build("nebular_limestone_ore", blockId++, b -> new BlockLogicNebularOre(b, Blocks.LIMESTONE, Material.limestone)).withBlastResistance(5.0F);
		NEBULAR_GRANITE_ORE = fullBlock
			.setTags(BlockTags.MINEABLE_BY_PICKAXE)
			.setHardness(3f)
			.build("nebular_granite_ore", blockId++, b -> new BlockLogicNebularOre(b, Blocks.GRANITE, Material.granite)).withBlastResistance(5.0F);
		NEBULAR_PERMAFROST_ORE = fullBlock
			.setTags(BlockTags.MINEABLE_BY_PICKAXE)
			.setHardness(3f)
			.build("nebular_permafrost_ore", blockId++, b -> new BlockLogicNebularOre(b, Blocks.PERMAFROST, Material.permafrost)).withBlastResistance(5.0F);
		NEBULAR_RED_DEEPSLATE_ORE = fullBlock
			.setTags(BlockTags.MINEABLE_BY_PICKAXE)
			.setHardness(3f)
			.build("nebular_red_deepslate_ore", blockId++, b -> new BlockLogicNebularOre(b, ModBlocks.RED_DEEPSLATE, Material.slate)).withBlastResistance(5.0F);
		RAW_NEBULAR_BLOCK = fullBlock
			.setTags(BlockTags.MINEABLE_BY_PICKAXE)
			.setHardness(3f)
			.build("raw_nebular_block", blockId++, b -> new BlockLogicOreBlock(b)).withBlastResistance(5.0F);
		NEBULAR_BLOCK = fullBlock
			.setTags(BlockTags.MINEABLE_BY_PICKAXE)
			.setHardness(3f)
			.build("nebular_block", blockId++, b -> new BlockLogicOreBlock(b)).withBlastResistance(5.0F);

		MITHRIL_STONE_ORE = fullBlock
			.setTags(BlockTags.MINEABLE_BY_PICKAXE)
			.setHardness(3f)
			.build("mithril_stone_ore", blockId++, b -> new BlockLogicMithrilOre(b, Blocks.STONE, Material.stone)).withBlastResistance(5.0F);
		MITHRIL_BASALT_ORE = fullBlock
			.setTags(BlockTags.MINEABLE_BY_PICKAXE)
			.setHardness(3f)
			.build("mithril_basalt_ore", blockId++, b -> new BlockLogicMithrilOre(b, Blocks.BASALT, Material.basalt)).withBlastResistance(5.0F);
		MITHRIL_LIMESTONE_ORE = fullBlock
			.setTags(BlockTags.MINEABLE_BY_PICKAXE)
			.setHardness(3f)
			.build("mithril_limestone_ore", blockId++, b -> new BlockLogicMithrilOre(b, Blocks.LIMESTONE, Material.limestone)).withBlastResistance(5.0F);
		MITHRIL_GRANITE_ORE = fullBlock
			.setTags(BlockTags.MINEABLE_BY_PICKAXE)
			.setHardness(3f)
			.build("mithril_granite_ore", blockId++, b -> new BlockLogicMithrilOre(b, Blocks.GRANITE, Material.granite)).withBlastResistance(5.0F);
		MITHRIL_PERMAFROST_ORE = fullBlock
			.setTags(BlockTags.MINEABLE_BY_PICKAXE)
			.setHardness(3f)
			.build("mithril_permafrost_ore", blockId++, b -> new BlockLogicMithrilOre(b, Blocks.PERMAFROST, Material.permafrost)).withBlastResistance(5.0F);
		MITHRIL_RED_DEEPSLATE_ORE = fullBlock
			.setTags(BlockTags.MINEABLE_BY_PICKAXE)
			.setHardness(3f)
			.build("mithril_red_deepslate_ore", blockId++, b -> new BlockLogicMithrilOre(b, ModBlocks.RED_DEEPSLATE, Material.slate)).withBlastResistance(5.0F);
		MITHRIL_BEDROCK_ORE = fullBlock
			.setTags(BlockTags.PISTON_CRUSHING)
			.setUnbreakable()
			.build("mithril_bedrock_ore", blockId++, b -> new BlockLogicMithrilOre(b, Blocks.BEDROCK, Material.stone)).withSetUnbreakable().withBlastResistance(6000000.0F).withImmovableFlagSet().withTags(BlockTags.PISTON_CRUSHING);
		RAW_MITHRIL_BLOCK = fullBlock
			.setTags(BlockTags.MINEABLE_BY_PICKAXE)
			.setHardness(3f)
			.build("raw_mithril_block", blockId++, b -> new BlockLogicOreBlock(b)).withBlastResistance(5.0F);
		MITHRIL_BLOCK = fullBlock
			.setTags(BlockTags.MINEABLE_BY_PICKAXE)
			.setHardness(3f)
			.build("mithril_block", blockId++, b -> new BlockLogicOreBlock(b)).withBlastResistance(5.0F);

		RED_DEEPSLATE = fullBlock
			.setTags(BlockTags.MINEABLE_BY_PICKAXE)
			.setHardness(3f)
			.build("red_deepslate", blockId++, b -> new BlockLogicRedDeepslate(b)).withBlastResistance(5.0F);
		RED_DEEPSLATE_BRICKS = fullBlock
			.setTags(BlockTags.MINEABLE_BY_PICKAXE)
			.setHardness(3f)
			.build("red_deepslate_bricks", blockId++, b -> new BlockLogicOreBlock(b)).withBlastResistance(5.0F);
		COBBLED_RED_DEEPSLATE = fullBlock
			.setTags(BlockTags.MINEABLE_BY_PICKAXE)
			.setHardness(3f)
			.build("cobbled_red_deepslate", blockId++, b -> new BlockLogicOreBlock(b)).withBlastResistance(5.0F);
		COBBLED_RED_DEEPSLATE_BRICKS = fullBlock
			.setTags(BlockTags.MINEABLE_BY_PICKAXE)
			.setHardness(3f)
			.build("cobbled_red_deepslate_bricks", blockId++, b -> new BlockLogicOreBlock(b)).withBlastResistance(5.0F);
	}
}
