package net.tlotd.bta.datagen;

import net.minecraft.client.render.EntityRendererDispatcher;
import net.minecraft.client.render.TileEntityRenderDispatcher;
import net.minecraft.client.render.block.color.BlockColorDispatcher;
import net.minecraft.client.render.block.model.BlockModelDispatcher;
import net.minecraft.client.render.block.model.BlockModelStandard;
import net.minecraft.client.render.item.model.ItemModelDispatcher;
import net.minecraft.client.render.item.model.ItemModelStandard;
import net.minecraft.core.util.helper.Side;
import net.tlotd.bta.block.ModBlocks;
import net.tlotd.bta.item.ModItems;

public class ModModelProvider {

	public void initBlockModels(BlockModelDispatcher dispatcher) {
		dispatcher.addDispatch(
			ModBlocks.STONE_FOSSIL, new BlockModelStandard<>(ModBlocks.STONE_FOSSIL)
				.setTex("tlotd:block/stone_fossil", Side.sides)
		);
		dispatcher.addDispatch(
			ModBlocks.BASALT_FOSSIL, new BlockModelStandard<>(ModBlocks.BASALT_FOSSIL)
				.setTex("tlotd:block/basalt_fossil", Side.sides)
		);
		dispatcher.addDispatch(
			ModBlocks.LIMESTONE_FOSSIL, new BlockModelStandard<>(ModBlocks.LIMESTONE_FOSSIL)
				.setTex("tlotd:block/limestone_fossil", Side.sides)
		);
		dispatcher.addDispatch(
			ModBlocks.GRANITE_FOSSIL, new BlockModelStandard<>(ModBlocks.GRANITE_FOSSIL)
				.setTex("tlotd:block/granite_fossil", Side.sides)
		);
		dispatcher.addDispatch(
			ModBlocks.PERMAFROST_FOSSIL, new BlockModelStandard<>(ModBlocks.PERMAFROST_FOSSIL)
				.setTex("tlotd:block/permafrost_fossil", Side.sides)
		);
		dispatcher.addDispatch(
			ModBlocks.RED_DEEPSLATE_FOSSIL, new BlockModelStandard<>(ModBlocks.RED_DEEPSLATE_FOSSIL)
				.setTex("tlotd:block/red_deepslate_fossil", Side.sides)
		);

		dispatcher.addDispatch(
			ModBlocks.HELIORITE_STONE_ORE, new BlockModelStandard<>(ModBlocks.HELIORITE_STONE_ORE)
				.setTex("tlotd:block/heliorite_stone_ore", Side.sides)
		);
		dispatcher.addDispatch(
			ModBlocks.HELIORITE_BASALT_ORE, new BlockModelStandard<>(ModBlocks.HELIORITE_BASALT_ORE)
				.setTex("tlotd:block/heliorite_basalt_ore", Side.sides)
		);
		dispatcher.addDispatch(
			ModBlocks.HELIORITE_LIMESTONE_ORE, new BlockModelStandard<>(ModBlocks.HELIORITE_LIMESTONE_ORE)
				.setTex("tlotd:block/heliorite_limestone_ore", Side.sides)
		);
		dispatcher.addDispatch(
			ModBlocks.HELIORITE_GRANITE_ORE, new BlockModelStandard<>(ModBlocks.HELIORITE_GRANITE_ORE)
				.setTex("tlotd:block/heliorite_granite_ore", Side.sides)
		);
		dispatcher.addDispatch(
			ModBlocks.HELIORITE_PERMAFROST_ORE, new BlockModelStandard<>(ModBlocks.HELIORITE_PERMAFROST_ORE)
				.setTex("tlotd:block/heliorite_permafrost_ore", Side.sides)
		);
		dispatcher.addDispatch(
			ModBlocks.HELIORITE_RED_DEEPSLATE_ORE, new BlockModelStandard<>(ModBlocks.HELIORITE_RED_DEEPSLATE_ORE)
				.setTex("tlotd:block/heliorite_red_deepslate_ore", Side.sides)
		);
		dispatcher.addDispatch(
			ModBlocks.HELIORITE_COMB_BLOCK, new BlockModelStandard<>(ModBlocks.HELIORITE_COMB_BLOCK)
				.setTex("tlotd:block/heliorite_comb_block", Side.sides)
		);
		dispatcher.addDispatch(
			ModBlocks.HELIORITE_BLOCK, new BlockModelStandard<>(ModBlocks.HELIORITE_BLOCK)
				.setTex("tlotd:block/heliorite_block", Side.sides)
		);

		dispatcher.addDispatch(
			ModBlocks.ENDURIUM_STONE_ORE, new BlockModelStandard<>(ModBlocks.ENDURIUM_STONE_ORE)
				.setTex("tlotd:block/endurium_stone_ore", Side.sides)
		);
		dispatcher.addDispatch(
			ModBlocks.ENDURIUM_BASALT_ORE, new BlockModelStandard<>(ModBlocks.ENDURIUM_BASALT_ORE)
				.setTex("tlotd:block/endurium_basalt_ore", Side.sides)
		);
		dispatcher.addDispatch(
			ModBlocks.ENDURIUM_LIMESTONE_ORE, new BlockModelStandard<>(ModBlocks.ENDURIUM_LIMESTONE_ORE)
				.setTex("tlotd:block/endurium_limestone_ore", Side.sides)
		);
		dispatcher.addDispatch(
			ModBlocks.ENDURIUM_GRANITE_ORE, new BlockModelStandard<>(ModBlocks.ENDURIUM_GRANITE_ORE)
				.setTex("tlotd:block/endurium_granite_ore", Side.sides)
		);
		dispatcher.addDispatch(
			ModBlocks.ENDURIUM_PERMAFROST_ORE, new BlockModelStandard<>(ModBlocks.ENDURIUM_PERMAFROST_ORE)
				.setTex("tlotd:block/endurium_permafrost_ore", Side.sides)
		);
		dispatcher.addDispatch(
			ModBlocks.ENDURIUM_RED_DEEPSLATE_ORE, new BlockModelStandard<>(ModBlocks.ENDURIUM_RED_DEEPSLATE_ORE)
				.setTex("tlotd:block/endurium_red_deepslate_ore", Side.sides)
		);
		dispatcher.addDispatch(
			ModBlocks.RAW_ENDURIUM_BLOCK, new BlockModelStandard<>(ModBlocks.RAW_ENDURIUM_BLOCK)
				.setTex("tlotd:block/raw_endurium_block", Side.sides)
		);
		dispatcher.addDispatch(
			ModBlocks.ENDURIUM_BLOCK, new BlockModelStandard<>(ModBlocks.ENDURIUM_BLOCK)
				.setTex("tlotd:block/endurium_block", Side.sides)
		);

		dispatcher.addDispatch(
			ModBlocks.PALLADIUM_STONE_ORE, new BlockModelStandard<>(ModBlocks.PALLADIUM_STONE_ORE)
				.setTex("tlotd:block/palladium_stone_ore", Side.sides)
		);
		dispatcher.addDispatch(
			ModBlocks.PALLADIUM_BASALT_ORE, new BlockModelStandard<>(ModBlocks.PALLADIUM_BASALT_ORE)
				.setTex("tlotd:block/palladium_basalt_ore", Side.sides)
		);
		dispatcher.addDispatch(
			ModBlocks.PALLADIUM_LIMESTONE_ORE, new BlockModelStandard<>(ModBlocks.PALLADIUM_LIMESTONE_ORE)
				.setTex("tlotd:block/palladium_limestone_ore", Side.sides)
		);
		dispatcher.addDispatch(
			ModBlocks.PALLADIUM_GRANITE_ORE, new BlockModelStandard<>(ModBlocks.PALLADIUM_GRANITE_ORE)
				.setTex("tlotd:block/palladium_granite_ore", Side.sides)
		);
		dispatcher.addDispatch(
			ModBlocks.PALLADIUM_PERMAFROST_ORE, new BlockModelStandard<>(ModBlocks.PALLADIUM_PERMAFROST_ORE)
				.setTex("tlotd:block/palladium_permafrost_ore", Side.sides)
		);
		dispatcher.addDispatch(
			ModBlocks.PALLADIUM_RED_DEEPSLATE_ORE, new BlockModelStandard<>(ModBlocks.PALLADIUM_RED_DEEPSLATE_ORE)
				.setTex("tlotd:block/palladium_red_deepslate_ore", Side.sides)
		);
		dispatcher.addDispatch(
			ModBlocks.RAW_PALLADIUM_BLOCK, new BlockModelStandard<>(ModBlocks.RAW_PALLADIUM_BLOCK)
				.setTex("tlotd:block/raw_palladium_block", Side.sides)
		);
		dispatcher.addDispatch(
			ModBlocks.PALLADIUM_BLOCK, new BlockModelStandard<>(ModBlocks.PALLADIUM_BLOCK)
				.setTex("tlotd:block/palladium_block", Side.sides)
		);

		dispatcher.addDispatch(
			ModBlocks.JURASSOLINE_STONE_ORE, new BlockModelStandard<>(ModBlocks.JURASSOLINE_STONE_ORE)
				.setTex("tlotd:block/jurassoline_stone_ore", Side.sides)
		);
		dispatcher.addDispatch(
			ModBlocks.JURASSOLINE_BASALT_ORE, new BlockModelStandard<>(ModBlocks.JURASSOLINE_BASALT_ORE)
				.setTex("tlotd:block/jurassoline_basalt_ore", Side.sides)
		);
		dispatcher.addDispatch(
			ModBlocks.JURASSOLINE_LIMESTONE_ORE, new BlockModelStandard<>(ModBlocks.JURASSOLINE_LIMESTONE_ORE)
				.setTex("tlotd:block/jurassoline_limestone_ore", Side.sides)
		);
		dispatcher.addDispatch(
			ModBlocks.JURASSOLINE_GRANITE_ORE, new BlockModelStandard<>(ModBlocks.JURASSOLINE_GRANITE_ORE)
				.setTex("tlotd:block/jurassoline_granite_ore", Side.sides)
		);
		dispatcher.addDispatch(
			ModBlocks.JURASSOLINE_PERMAFROST_ORE, new BlockModelStandard<>(ModBlocks.JURASSOLINE_PERMAFROST_ORE)
				.setTex("tlotd:block/jurassoline_permafrost_ore", Side.sides)
		);
		dispatcher.addDispatch(
			ModBlocks.JURASSOLINE_RED_DEEPSLATE_ORE, new BlockModelStandard<>(ModBlocks.JURASSOLINE_RED_DEEPSLATE_ORE)
				.setTex("tlotd:block/jurassoline_red_deepslate_ore", Side.sides)
		);
		dispatcher.addDispatch(
			ModBlocks.JURASSOLINE_CRYSTAL_BLOCK, new BlockModelStandard<>(ModBlocks.JURASSOLINE_CRYSTAL_BLOCK)
				.setTex("tlotd:block/jurassoline_crystal_block", Side.sides)
		);
		dispatcher.addDispatch(
			ModBlocks.JURASSOLINE_BLOCK, new BlockModelStandard<>(ModBlocks.JURASSOLINE_BLOCK)
				.setTex("tlotd:block/jurassoline_block", Side.sides)
		);

		dispatcher.addDispatch(
			ModBlocks.CINNABAR_STONE_ORE, new BlockModelStandard<>(ModBlocks.CINNABAR_STONE_ORE)
				.setTex("tlotd:block/cinnabar_stone_ore", Side.sides)
		);
		dispatcher.addDispatch(
			ModBlocks.CINNABAR_BASALT_ORE, new BlockModelStandard<>(ModBlocks.CINNABAR_BASALT_ORE)
				.setTex("tlotd:block/cinnabar_basalt_ore", Side.sides)
		);
		dispatcher.addDispatch(
			ModBlocks.CINNABAR_LIMESTONE_ORE, new BlockModelStandard<>(ModBlocks.CINNABAR_LIMESTONE_ORE)
				.setTex("tlotd:block/cinnabar_limestone_ore", Side.sides)
		);
		dispatcher.addDispatch(
			ModBlocks.CINNABAR_GRANITE_ORE, new BlockModelStandard<>(ModBlocks.CINNABAR_GRANITE_ORE)
				.setTex("tlotd:block/cinnabar_granite_ore", Side.sides)
		);
		dispatcher.addDispatch(
			ModBlocks.CINNABAR_PERMAFROST_ORE, new BlockModelStandard<>(ModBlocks.CINNABAR_PERMAFROST_ORE)
				.setTex("tlotd:block/cinnabar_permafrost_ore", Side.sides)
		);
		dispatcher.addDispatch(
			ModBlocks.CINNABAR_RED_DEEPSLATE_ORE, new BlockModelStandard<>(ModBlocks.CINNABAR_RED_DEEPSLATE_ORE)
				.setTex("tlotd:block/cinnabar_red_deepslate_ore", Side.sides)
		);
		dispatcher.addDispatch(
			ModBlocks.CINNABAR_CRYSTAL_BLOCK, new BlockModelStandard<>(ModBlocks.CINNABAR_CRYSTAL_BLOCK)
				.setTex("tlotd:block/cinnabar_crystal_block", Side.sides)
		);
		dispatcher.addDispatch(
			ModBlocks.CINNABAR_BLOCK, new BlockModelStandard<>(ModBlocks.CINNABAR_BLOCK)
				.setTex("tlotd:block/cinnabar_block", Side.sides)
		);

		dispatcher.addDispatch(
			ModBlocks.NEBULAR_STONE_ORE, new BlockModelStandard<>(ModBlocks.NEBULAR_STONE_ORE)
				.setTex("tlotd:block/nebular_stone_ore", Side.sides)
		);
		dispatcher.addDispatch(
			ModBlocks.NEBULAR_BASALT_ORE, new BlockModelStandard<>(ModBlocks.NEBULAR_BASALT_ORE)
				.setTex("tlotd:block/nebular_basalt_ore", Side.sides)
		);
		dispatcher.addDispatch(
			ModBlocks.NEBULAR_LIMESTONE_ORE, new BlockModelStandard<>(ModBlocks.NEBULAR_LIMESTONE_ORE)
				.setTex("tlotd:block/nebular_limestone_ore", Side.sides)
		);
		dispatcher.addDispatch(
			ModBlocks.NEBULAR_GRANITE_ORE, new BlockModelStandard<>(ModBlocks.NEBULAR_GRANITE_ORE)
				.setTex("tlotd:block/nebular_granite_ore", Side.sides)
		);
		dispatcher.addDispatch(
			ModBlocks.NEBULAR_PERMAFROST_ORE, new BlockModelStandard<>(ModBlocks.NEBULAR_PERMAFROST_ORE)
				.setTex("tlotd:block/nebular_permafrost_ore", Side.sides)
		);
		dispatcher.addDispatch(
			ModBlocks.NEBULAR_RED_DEEPSLATE_ORE, new BlockModelStandard<>(ModBlocks.NEBULAR_RED_DEEPSLATE_ORE)
				.setTex("tlotd:block/nebular_red_deepslate_ore", Side.sides)
		);
		dispatcher.addDispatch(
			ModBlocks.RAW_NEBULAR_BLOCK, new BlockModelStandard<>(ModBlocks.RAW_NEBULAR_BLOCK)
				.setTex("tlotd:block/raw_nebular_block", Side.sides)
		);
		dispatcher.addDispatch(
			ModBlocks.NEBULAR_BLOCK, new BlockModelStandard<>(ModBlocks.NEBULAR_BLOCK)
				.setTex("tlotd:block/nebular_block", Side.sides)
		);

		dispatcher.addDispatch(
			ModBlocks.MITHRIL_STONE_ORE, new BlockModelStandard<>(ModBlocks.MITHRIL_STONE_ORE)
				.setTex("tlotd:block/mithril_stone_ore", Side.sides)
		);
		dispatcher.addDispatch(
			ModBlocks.MITHRIL_BASALT_ORE, new BlockModelStandard<>(ModBlocks.MITHRIL_BASALT_ORE)
				.setTex("tlotd:block/mithril_basalt_ore", Side.sides)
		);
		dispatcher.addDispatch(
			ModBlocks.MITHRIL_LIMESTONE_ORE, new BlockModelStandard<>(ModBlocks.MITHRIL_LIMESTONE_ORE)
				.setTex("tlotd:block/mithril_limestone_ore", Side.sides)
		);
		dispatcher.addDispatch(
			ModBlocks.MITHRIL_GRANITE_ORE, new BlockModelStandard<>(ModBlocks.MITHRIL_GRANITE_ORE)
				.setTex("tlotd:block/mithril_granite_ore", Side.sides)
		);
		dispatcher.addDispatch(
			ModBlocks.MITHRIL_PERMAFROST_ORE, new BlockModelStandard<>(ModBlocks.MITHRIL_PERMAFROST_ORE)
				.setTex("tlotd:block/mithril_permafrost_ore", Side.sides)
		);
		dispatcher.addDispatch(
			ModBlocks.MITHRIL_RED_DEEPSLATE_ORE, new BlockModelStandard<>(ModBlocks.MITHRIL_RED_DEEPSLATE_ORE)
				.setTex("tlotd:block/mithril_red_deepslate_ore", Side.sides)
		);
		dispatcher.addDispatch(
			ModBlocks.MITHRIL_BEDROCK_ORE, new BlockModelStandard<>(ModBlocks.MITHRIL_BEDROCK_ORE)
				.setTex("tlotd:block/mithril_bedrock_ore", Side.sides)
		);
		dispatcher.addDispatch(
			ModBlocks.RAW_MITHRIL_BLOCK, new BlockModelStandard<>(ModBlocks.RAW_MITHRIL_BLOCK)
				.setTex("tlotd:block/raw_mithril_block", Side.sides)
		);
		dispatcher.addDispatch(
			ModBlocks.MITHRIL_BLOCK, new BlockModelStandard<>(ModBlocks.MITHRIL_BLOCK)
				.setTex("tlotd:block/mithril_block", Side.sides)
		);

		dispatcher.addDispatch(
			ModBlocks.RED_DEEPSLATE, new BlockModelStandard<>(ModBlocks.RED_DEEPSLATE)
				.setTex("tlotd:block/red_deepslate", Side.sides)
		);
		dispatcher.addDispatch(
			ModBlocks.RED_DEEPSLATE_BRICKS, new BlockModelStandard<>(ModBlocks.RED_DEEPSLATE_BRICKS)
				.setTex("tlotd:block/red_deepslate_bricks", Side.sides)
		);
		dispatcher.addDispatch(
			ModBlocks.COBBLED_RED_DEEPSLATE, new BlockModelStandard<>(ModBlocks.COBBLED_RED_DEEPSLATE)
				.setTex("tlotd:block/cobbled_red_deepslate", Side.sides)
		);
		dispatcher.addDispatch(
			ModBlocks.COBBLED_RED_DEEPSLATE_BRICKS, new BlockModelStandard<>(ModBlocks.COBBLED_RED_DEEPSLATE_BRICKS)
				.setTex("tlotd:block/cobbled_red_deepslate_bricks", Side.sides)
		);
	}

	public void initItemModels(ItemModelDispatcher dispatcher) {

		dispatcher.addDispatch(
			ModItems.STEEL_ROD, new ItemModelStandard(ModItems.STEEL_ROD, true).setDisplayPos("firstperson_righthand", ItemModelDispatcher.HANDHELD_FIRST_PERSON_RIGHT_HAND).setDisplayPos("firstperson_lefthand", ItemModelDispatcher.HANDHELD_FIRST_PERSON_LEFT_HAND).setDisplayPos("thirdperson_righthand", ItemModelDispatcher.HANDHELD_THIRD_PERSON_RIGHT_HAND).setDisplayPos("thirdperson_lefthand", ItemModelDispatcher.HANDHELD_THIRD_PERSON_LEFT_HAND)
		);
		dispatcher.addDispatch(
			ModItems.FOSSILIZED_BONE, new ItemModelStandard(ModItems.FOSSILIZED_BONE, true).setDisplayPos("firstperson_righthand", ItemModelDispatcher.HANDHELD_FIRST_PERSON_RIGHT_HAND).setDisplayPos("firstperson_lefthand", ItemModelDispatcher.HANDHELD_FIRST_PERSON_LEFT_HAND).setDisplayPos("thirdperson_righthand", ItemModelDispatcher.HANDHELD_THIRD_PERSON_RIGHT_HAND).setDisplayPos("thirdperson_lefthand", ItemModelDispatcher.HANDHELD_THIRD_PERSON_LEFT_HAND)
		);
		dispatcher.addDispatch(
			ModItems.REINFORCED_TOOL_ROD, new ItemModelStandard(ModItems.REINFORCED_TOOL_ROD, true).setDisplayPos("firstperson_righthand", ItemModelDispatcher.HANDHELD_FIRST_PERSON_RIGHT_HAND).setDisplayPos("firstperson_lefthand", ItemModelDispatcher.HANDHELD_FIRST_PERSON_LEFT_HAND).setDisplayPos("thirdperson_righthand", ItemModelDispatcher.HANDHELD_THIRD_PERSON_RIGHT_HAND).setDisplayPos("thirdperson_lefthand", ItemModelDispatcher.HANDHELD_THIRD_PERSON_LEFT_HAND)
		);
		dispatcher.addDispatch(
			ModItems.FANCY_TOOL_ROD, new ItemModelStandard(ModItems.FANCY_TOOL_ROD, true).setDisplayPos("firstperson_righthand", ItemModelDispatcher.HANDHELD_FIRST_PERSON_RIGHT_HAND).setDisplayPos("firstperson_lefthand", ItemModelDispatcher.HANDHELD_FIRST_PERSON_LEFT_HAND).setDisplayPos("thirdperson_righthand", ItemModelDispatcher.HANDHELD_THIRD_PERSON_RIGHT_HAND).setDisplayPos("thirdperson_lefthand", ItemModelDispatcher.HANDHELD_THIRD_PERSON_LEFT_HAND)
		);
		dispatcher.addDispatch(
			ModItems.HELIORITE_COMB, new ItemModelStandard(ModItems.HELIORITE_COMB, true)
		);
		dispatcher.addDispatch(
			ModItems.HELIORITE_NUGGET, new ItemModelStandard(ModItems.HELIORITE_NUGGET, true)
		);
		dispatcher.addDispatch(
			ModItems.HELIORITE_INGOT, new ItemModelStandard(ModItems.HELIORITE_INGOT, true)
		);
		dispatcher.addDispatch(
			ModItems.HELIORITE_SWORD, new ItemModelStandard(ModItems.HELIORITE_SWORD, true).setDisplayPos("firstperson_righthand", ItemModelDispatcher.HANDHELD_FIRST_PERSON_RIGHT_HAND).setDisplayPos("firstperson_lefthand", ItemModelDispatcher.HANDHELD_FIRST_PERSON_LEFT_HAND).setDisplayPos("thirdperson_righthand", ItemModelDispatcher.HANDHELD_THIRD_PERSON_RIGHT_HAND).setDisplayPos("thirdperson_lefthand", ItemModelDispatcher.HANDHELD_THIRD_PERSON_LEFT_HAND)
		);
		dispatcher.addDispatch(
			ModItems.HELIORITE_PICKAXE, new ItemModelStandard(ModItems.HELIORITE_PICKAXE, true).setDisplayPos("firstperson_righthand", ItemModelDispatcher.HANDHELD_FIRST_PERSON_RIGHT_HAND).setDisplayPos("firstperson_lefthand", ItemModelDispatcher.HANDHELD_FIRST_PERSON_LEFT_HAND).setDisplayPos("thirdperson_righthand", ItemModelDispatcher.HANDHELD_THIRD_PERSON_RIGHT_HAND).setDisplayPos("thirdperson_lefthand", ItemModelDispatcher.HANDHELD_THIRD_PERSON_LEFT_HAND)
		);
		dispatcher.addDispatch(
			ModItems.HELIORITE_AXE, new ItemModelStandard(ModItems.HELIORITE_AXE, true).setDisplayPos("firstperson_righthand", ItemModelDispatcher.HANDHELD_FIRST_PERSON_RIGHT_HAND).setDisplayPos("firstperson_lefthand", ItemModelDispatcher.HANDHELD_FIRST_PERSON_LEFT_HAND).setDisplayPos("thirdperson_righthand", ItemModelDispatcher.HANDHELD_THIRD_PERSON_RIGHT_HAND).setDisplayPos("thirdperson_lefthand", ItemModelDispatcher.HANDHELD_THIRD_PERSON_LEFT_HAND)
		);
		dispatcher.addDispatch(
			ModItems.HELIORITE_SHOVEL, new ItemModelStandard(ModItems.HELIORITE_SHOVEL, true).setDisplayPos("firstperson_righthand", ItemModelDispatcher.HANDHELD_FIRST_PERSON_RIGHT_HAND).setDisplayPos("firstperson_lefthand", ItemModelDispatcher.HANDHELD_FIRST_PERSON_LEFT_HAND).setDisplayPos("thirdperson_righthand", ItemModelDispatcher.HANDHELD_THIRD_PERSON_RIGHT_HAND).setDisplayPos("thirdperson_lefthand", ItemModelDispatcher.HANDHELD_THIRD_PERSON_LEFT_HAND)
		);
		dispatcher.addDispatch(
			ModItems.HELIORITE_HOE, new ItemModelStandard(ModItems.HELIORITE_HOE, true).setDisplayPos("firstperson_righthand", ItemModelDispatcher.HANDHELD_FIRST_PERSON_RIGHT_HAND).setDisplayPos("firstperson_lefthand", ItemModelDispatcher.HANDHELD_FIRST_PERSON_LEFT_HAND).setDisplayPos("thirdperson_righthand", ItemModelDispatcher.HANDHELD_THIRD_PERSON_RIGHT_HAND).setDisplayPos("thirdperson_lefthand", ItemModelDispatcher.HANDHELD_THIRD_PERSON_LEFT_HAND)
		);
		dispatcher.addDispatch(
			ModItems.HELIORITE_PAXEL, new ItemModelStandard(ModItems.HELIORITE_PAXEL, true).setDisplayPos("firstperson_righthand", ItemModelDispatcher.HANDHELD_FIRST_PERSON_RIGHT_HAND).setDisplayPos("firstperson_lefthand", ItemModelDispatcher.HANDHELD_FIRST_PERSON_LEFT_HAND).setDisplayPos("thirdperson_righthand", ItemModelDispatcher.HANDHELD_THIRD_PERSON_RIGHT_HAND).setDisplayPos("thirdperson_lefthand", ItemModelDispatcher.HANDHELD_THIRD_PERSON_LEFT_HAND)
		);
		dispatcher.addDispatch(
			ModItems.HELIORITE_HELMET, new ItemModelStandard(ModItems.HELIORITE_HELMET, true)
		);
		dispatcher.addDispatch(
			ModItems.HELIORITE_CHESTPLATE, new ItemModelStandard(ModItems.HELIORITE_CHESTPLATE, true)
		);
		dispatcher.addDispatch(
			ModItems.HELIORITE_LEGGINGS, new ItemModelStandard(ModItems.HELIORITE_LEGGINGS, true)
		);
		dispatcher.addDispatch(
			ModItems.HELIORITE_BOOTS, new ItemModelStandard(ModItems.HELIORITE_BOOTS, true)
		);
		dispatcher.addDispatch(
			ModItems.ENDURIUM_CRYSTAL, new ItemModelStandard(ModItems.ENDURIUM_CRYSTAL, true)
		);
		dispatcher.addDispatch(
			ModItems.ENDURIUM_NUGGET, new ItemModelStandard(ModItems.ENDURIUM_NUGGET, true)
		);
		dispatcher.addDispatch(
			ModItems.ENDURIUM_INGOT, new ItemModelStandard(ModItems.ENDURIUM_INGOT, true)
		);
		dispatcher.addDispatch(
			ModItems.ENDURIUM_SWORD, new ItemModelStandard(ModItems.ENDURIUM_SWORD, true).setDisplayPos("firstperson_righthand", ItemModelDispatcher.HANDHELD_FIRST_PERSON_RIGHT_HAND).setDisplayPos("firstperson_lefthand", ItemModelDispatcher.HANDHELD_FIRST_PERSON_LEFT_HAND).setDisplayPos("thirdperson_righthand", ItemModelDispatcher.HANDHELD_THIRD_PERSON_RIGHT_HAND).setDisplayPos("thirdperson_lefthand", ItemModelDispatcher.HANDHELD_THIRD_PERSON_LEFT_HAND)
		);
		dispatcher.addDispatch(
			ModItems.ENDURIUM_PICKAXE, new ItemModelStandard(ModItems.ENDURIUM_PICKAXE, true).setDisplayPos("firstperson_righthand", ItemModelDispatcher.HANDHELD_FIRST_PERSON_RIGHT_HAND).setDisplayPos("firstperson_lefthand", ItemModelDispatcher.HANDHELD_FIRST_PERSON_LEFT_HAND).setDisplayPos("thirdperson_righthand", ItemModelDispatcher.HANDHELD_THIRD_PERSON_RIGHT_HAND).setDisplayPos("thirdperson_lefthand", ItemModelDispatcher.HANDHELD_THIRD_PERSON_LEFT_HAND)
		);
		dispatcher.addDispatch(
			ModItems.ENDURIUM_AXE, new ItemModelStandard(ModItems.ENDURIUM_AXE, true).setDisplayPos("firstperson_righthand", ItemModelDispatcher.HANDHELD_FIRST_PERSON_RIGHT_HAND).setDisplayPos("firstperson_lefthand", ItemModelDispatcher.HANDHELD_FIRST_PERSON_LEFT_HAND).setDisplayPos("thirdperson_righthand", ItemModelDispatcher.HANDHELD_THIRD_PERSON_RIGHT_HAND).setDisplayPos("thirdperson_lefthand", ItemModelDispatcher.HANDHELD_THIRD_PERSON_LEFT_HAND)
		);
		dispatcher.addDispatch(
			ModItems.ENDURIUM_SHOVEL, new ItemModelStandard(ModItems.ENDURIUM_SHOVEL, true).setDisplayPos("firstperson_righthand", ItemModelDispatcher.HANDHELD_FIRST_PERSON_RIGHT_HAND).setDisplayPos("firstperson_lefthand", ItemModelDispatcher.HANDHELD_FIRST_PERSON_LEFT_HAND).setDisplayPos("thirdperson_righthand", ItemModelDispatcher.HANDHELD_THIRD_PERSON_RIGHT_HAND).setDisplayPos("thirdperson_lefthand", ItemModelDispatcher.HANDHELD_THIRD_PERSON_LEFT_HAND)
		);
		dispatcher.addDispatch(
			ModItems.ENDURIUM_HOE, new ItemModelStandard(ModItems.ENDURIUM_HOE, true).setDisplayPos("firstperson_righthand", ItemModelDispatcher.HANDHELD_FIRST_PERSON_RIGHT_HAND).setDisplayPos("firstperson_lefthand", ItemModelDispatcher.HANDHELD_FIRST_PERSON_LEFT_HAND).setDisplayPos("thirdperson_righthand", ItemModelDispatcher.HANDHELD_THIRD_PERSON_RIGHT_HAND).setDisplayPos("thirdperson_lefthand", ItemModelDispatcher.HANDHELD_THIRD_PERSON_LEFT_HAND)
		);
		dispatcher.addDispatch(
			ModItems.ENDURIUM_PAXEL, new ItemModelStandard(ModItems.ENDURIUM_PAXEL, true).setDisplayPos("firstperson_righthand", ItemModelDispatcher.HANDHELD_FIRST_PERSON_RIGHT_HAND).setDisplayPos("firstperson_lefthand", ItemModelDispatcher.HANDHELD_FIRST_PERSON_LEFT_HAND).setDisplayPos("thirdperson_righthand", ItemModelDispatcher.HANDHELD_THIRD_PERSON_RIGHT_HAND).setDisplayPos("thirdperson_lefthand", ItemModelDispatcher.HANDHELD_THIRD_PERSON_LEFT_HAND)
		);
		dispatcher.addDispatch(
			ModItems.ENDURIUM_HELMET, new ItemModelStandard(ModItems.ENDURIUM_HELMET, true)
		);
		dispatcher.addDispatch(
			ModItems.ENDURIUM_CHESTPLATE, new ItemModelStandard(ModItems.ENDURIUM_CHESTPLATE, true)
		);
		dispatcher.addDispatch(
			ModItems.ENDURIUM_LEGGINGS, new ItemModelStandard(ModItems.ENDURIUM_LEGGINGS, true)
		);
		dispatcher.addDispatch(
			ModItems.ENDURIUM_BOOTS, new ItemModelStandard(ModItems.ENDURIUM_BOOTS, true)
		);
		dispatcher.addDispatch(
			ModItems.RAW_PALLADIUM, new ItemModelStandard(ModItems.RAW_PALLADIUM, true)
		);
		dispatcher.addDispatch(
			ModItems.PALLADIUM_NUGGET, new ItemModelStandard(ModItems.PALLADIUM_NUGGET, true)
		);
		dispatcher.addDispatch(
			ModItems.PALLADIUM_INGOT, new ItemModelStandard(ModItems.PALLADIUM_INGOT, true)
		);
		dispatcher.addDispatch(
			ModItems.PALLADIUM_SWORD, new ItemModelStandard(ModItems.PALLADIUM_SWORD, true).setDisplayPos("firstperson_righthand", ItemModelDispatcher.HANDHELD_FIRST_PERSON_RIGHT_HAND).setDisplayPos("firstperson_lefthand", ItemModelDispatcher.HANDHELD_FIRST_PERSON_LEFT_HAND).setDisplayPos("thirdperson_righthand", ItemModelDispatcher.HANDHELD_THIRD_PERSON_RIGHT_HAND).setDisplayPos("thirdperson_lefthand", ItemModelDispatcher.HANDHELD_THIRD_PERSON_LEFT_HAND)
		);
		dispatcher.addDispatch(
			ModItems.PALLADIUM_PICKAXE, new ItemModelStandard(ModItems.PALLADIUM_PICKAXE, true).setDisplayPos("firstperson_righthand", ItemModelDispatcher.HANDHELD_FIRST_PERSON_RIGHT_HAND).setDisplayPos("firstperson_lefthand", ItemModelDispatcher.HANDHELD_FIRST_PERSON_LEFT_HAND).setDisplayPos("thirdperson_righthand", ItemModelDispatcher.HANDHELD_THIRD_PERSON_RIGHT_HAND).setDisplayPos("thirdperson_lefthand", ItemModelDispatcher.HANDHELD_THIRD_PERSON_LEFT_HAND)
		);
		dispatcher.addDispatch(
			ModItems.PALLADIUM_AXE, new ItemModelStandard(ModItems.PALLADIUM_AXE, true).setDisplayPos("firstperson_righthand", ItemModelDispatcher.HANDHELD_FIRST_PERSON_RIGHT_HAND).setDisplayPos("firstperson_lefthand", ItemModelDispatcher.HANDHELD_FIRST_PERSON_LEFT_HAND).setDisplayPos("thirdperson_righthand", ItemModelDispatcher.HANDHELD_THIRD_PERSON_RIGHT_HAND).setDisplayPos("thirdperson_lefthand", ItemModelDispatcher.HANDHELD_THIRD_PERSON_LEFT_HAND)
		);
		dispatcher.addDispatch(
			ModItems.PALLADIUM_SHOVEL, new ItemModelStandard(ModItems.PALLADIUM_SHOVEL, true).setDisplayPos("firstperson_righthand", ItemModelDispatcher.HANDHELD_FIRST_PERSON_RIGHT_HAND).setDisplayPos("firstperson_lefthand", ItemModelDispatcher.HANDHELD_FIRST_PERSON_LEFT_HAND).setDisplayPos("thirdperson_righthand", ItemModelDispatcher.HANDHELD_THIRD_PERSON_RIGHT_HAND).setDisplayPos("thirdperson_lefthand", ItemModelDispatcher.HANDHELD_THIRD_PERSON_LEFT_HAND)
		);
		dispatcher.addDispatch(
			ModItems.PALLADIUM_HOE, new ItemModelStandard(ModItems.PALLADIUM_HOE, true).setDisplayPos("firstperson_righthand", ItemModelDispatcher.HANDHELD_FIRST_PERSON_RIGHT_HAND).setDisplayPos("firstperson_lefthand", ItemModelDispatcher.HANDHELD_FIRST_PERSON_LEFT_HAND).setDisplayPos("thirdperson_righthand", ItemModelDispatcher.HANDHELD_THIRD_PERSON_RIGHT_HAND).setDisplayPos("thirdperson_lefthand", ItemModelDispatcher.HANDHELD_THIRD_PERSON_LEFT_HAND)
		);
		dispatcher.addDispatch(
			ModItems.PALLADIUM_PAXEL, new ItemModelStandard(ModItems.PALLADIUM_PAXEL, true).setDisplayPos("firstperson_righthand", ItemModelDispatcher.HANDHELD_FIRST_PERSON_RIGHT_HAND).setDisplayPos("firstperson_lefthand", ItemModelDispatcher.HANDHELD_FIRST_PERSON_LEFT_HAND).setDisplayPos("thirdperson_righthand", ItemModelDispatcher.HANDHELD_THIRD_PERSON_RIGHT_HAND).setDisplayPos("thirdperson_lefthand", ItemModelDispatcher.HANDHELD_THIRD_PERSON_LEFT_HAND)
		);
		dispatcher.addDispatch(
			ModItems.PALLADIUM_HELMET, new ItemModelStandard(ModItems.PALLADIUM_HELMET, true)
		);
		dispatcher.addDispatch(
			ModItems.PALLADIUM_CHESTPLATE, new ItemModelStandard(ModItems.PALLADIUM_CHESTPLATE, true)
		);
		dispatcher.addDispatch(
			ModItems.PALLADIUM_LEGGINGS, new ItemModelStandard(ModItems.PALLADIUM_LEGGINGS, true)
		);
		dispatcher.addDispatch(
			ModItems.PALLADIUM_BOOTS, new ItemModelStandard(ModItems.PALLADIUM_BOOTS, true)
		);
		dispatcher.addDispatch(
			ModItems.JURASSOLINE_CRYSTAL, new ItemModelStandard(ModItems.JURASSOLINE_CRYSTAL, true)
		);
		dispatcher.addDispatch(
			ModItems.JURASSOLINE_NUGGET, new ItemModelStandard(ModItems.JURASSOLINE_NUGGET, true)
		);
		dispatcher.addDispatch(
			ModItems.JURASSOLINE_INGOT, new ItemModelStandard(ModItems.JURASSOLINE_INGOT, true)
		);
		dispatcher.addDispatch(
			ModItems.JURASSOLINE_SWORD, new ItemModelStandard(ModItems.JURASSOLINE_SWORD, true).setDisplayPos("firstperson_righthand", ItemModelDispatcher.HANDHELD_FIRST_PERSON_RIGHT_HAND).setDisplayPos("firstperson_lefthand", ItemModelDispatcher.HANDHELD_FIRST_PERSON_LEFT_HAND).setDisplayPos("thirdperson_righthand", ItemModelDispatcher.HANDHELD_THIRD_PERSON_RIGHT_HAND).setDisplayPos("thirdperson_lefthand", ItemModelDispatcher.HANDHELD_THIRD_PERSON_LEFT_HAND)
		);
		dispatcher.addDispatch(
			ModItems.JURASSOLINE_PICKAXE, new ItemModelStandard(ModItems.JURASSOLINE_PICKAXE, true).setDisplayPos("firstperson_righthand", ItemModelDispatcher.HANDHELD_FIRST_PERSON_RIGHT_HAND).setDisplayPos("firstperson_lefthand", ItemModelDispatcher.HANDHELD_FIRST_PERSON_LEFT_HAND).setDisplayPos("thirdperson_righthand", ItemModelDispatcher.HANDHELD_THIRD_PERSON_RIGHT_HAND).setDisplayPos("thirdperson_lefthand", ItemModelDispatcher.HANDHELD_THIRD_PERSON_LEFT_HAND)
		);
		dispatcher.addDispatch(
			ModItems.JURASSOLINE_AXE, new ItemModelStandard(ModItems.JURASSOLINE_AXE, true).setDisplayPos("firstperson_righthand", ItemModelDispatcher.HANDHELD_FIRST_PERSON_RIGHT_HAND).setDisplayPos("firstperson_lefthand", ItemModelDispatcher.HANDHELD_FIRST_PERSON_LEFT_HAND).setDisplayPos("thirdperson_righthand", ItemModelDispatcher.HANDHELD_THIRD_PERSON_RIGHT_HAND).setDisplayPos("thirdperson_lefthand", ItemModelDispatcher.HANDHELD_THIRD_PERSON_LEFT_HAND)
		);
		dispatcher.addDispatch(
			ModItems.JURASSOLINE_SHOVEL, new ItemModelStandard(ModItems.JURASSOLINE_SHOVEL, true).setDisplayPos("firstperson_righthand", ItemModelDispatcher.HANDHELD_FIRST_PERSON_RIGHT_HAND).setDisplayPos("firstperson_lefthand", ItemModelDispatcher.HANDHELD_FIRST_PERSON_LEFT_HAND).setDisplayPos("thirdperson_righthand", ItemModelDispatcher.HANDHELD_THIRD_PERSON_RIGHT_HAND).setDisplayPos("thirdperson_lefthand", ItemModelDispatcher.HANDHELD_THIRD_PERSON_LEFT_HAND)
		);
		dispatcher.addDispatch(
			ModItems.JURASSOLINE_HOE, new ItemModelStandard(ModItems.JURASSOLINE_HOE, true).setDisplayPos("firstperson_righthand", ItemModelDispatcher.HANDHELD_FIRST_PERSON_RIGHT_HAND).setDisplayPos("firstperson_lefthand", ItemModelDispatcher.HANDHELD_FIRST_PERSON_LEFT_HAND).setDisplayPos("thirdperson_righthand", ItemModelDispatcher.HANDHELD_THIRD_PERSON_RIGHT_HAND).setDisplayPos("thirdperson_lefthand", ItemModelDispatcher.HANDHELD_THIRD_PERSON_LEFT_HAND)
		);
		dispatcher.addDispatch(
			ModItems.JURASSOLINE_PAXEL, new ItemModelStandard(ModItems.JURASSOLINE_PAXEL, true).setDisplayPos("firstperson_righthand", ItemModelDispatcher.HANDHELD_FIRST_PERSON_RIGHT_HAND).setDisplayPos("firstperson_lefthand", ItemModelDispatcher.HANDHELD_FIRST_PERSON_LEFT_HAND).setDisplayPos("thirdperson_righthand", ItemModelDispatcher.HANDHELD_THIRD_PERSON_RIGHT_HAND).setDisplayPos("thirdperson_lefthand", ItemModelDispatcher.HANDHELD_THIRD_PERSON_LEFT_HAND)
		);
		dispatcher.addDispatch(
			ModItems.JURASSOLINE_HELMET, new ItemModelStandard(ModItems.JURASSOLINE_HELMET, true)
		);
		dispatcher.addDispatch(
			ModItems.JURASSOLINE_CHESTPLATE, new ItemModelStandard(ModItems.JURASSOLINE_CHESTPLATE, true)
		);
		dispatcher.addDispatch(
			ModItems.JURASSOLINE_LEGGINGS, new ItemModelStandard(ModItems.JURASSOLINE_LEGGINGS, true)
		);
		dispatcher.addDispatch(
			ModItems.JURASSOLINE_BOOTS, new ItemModelStandard(ModItems.JURASSOLINE_BOOTS, true)
		);
		dispatcher.addDispatch(
			ModItems.CINNABAR_CRYSTAL, new ItemModelStandard(ModItems.CINNABAR_CRYSTAL, true)
		);
		dispatcher.addDispatch(
			ModItems.CINNABAR_NUGGET, new ItemModelStandard(ModItems.CINNABAR_NUGGET, true)
		);
		dispatcher.addDispatch(
			ModItems.CINNABAR_INGOT, new ItemModelStandard(ModItems.CINNABAR_INGOT, true)
		);
		dispatcher.addDispatch(
			ModItems.CINNABAR_SWORD, new ItemModelStandard(ModItems.CINNABAR_SWORD, true).setDisplayPos("firstperson_righthand", ItemModelDispatcher.HANDHELD_FIRST_PERSON_RIGHT_HAND).setDisplayPos("firstperson_lefthand", ItemModelDispatcher.HANDHELD_FIRST_PERSON_LEFT_HAND).setDisplayPos("thirdperson_righthand", ItemModelDispatcher.HANDHELD_THIRD_PERSON_RIGHT_HAND).setDisplayPos("thirdperson_lefthand", ItemModelDispatcher.HANDHELD_THIRD_PERSON_LEFT_HAND)
		);
		dispatcher.addDispatch(
			ModItems.CINNABAR_PICKAXE, new ItemModelStandard(ModItems.CINNABAR_PICKAXE, true).setDisplayPos("firstperson_righthand", ItemModelDispatcher.HANDHELD_FIRST_PERSON_RIGHT_HAND).setDisplayPos("firstperson_lefthand", ItemModelDispatcher.HANDHELD_FIRST_PERSON_LEFT_HAND).setDisplayPos("thirdperson_righthand", ItemModelDispatcher.HANDHELD_THIRD_PERSON_RIGHT_HAND).setDisplayPos("thirdperson_lefthand", ItemModelDispatcher.HANDHELD_THIRD_PERSON_LEFT_HAND)
		);
		dispatcher.addDispatch(
			ModItems.CINNABAR_AXE, new ItemModelStandard(ModItems.CINNABAR_AXE, true).setDisplayPos("firstperson_righthand", ItemModelDispatcher.HANDHELD_FIRST_PERSON_RIGHT_HAND).setDisplayPos("firstperson_lefthand", ItemModelDispatcher.HANDHELD_FIRST_PERSON_LEFT_HAND).setDisplayPos("thirdperson_righthand", ItemModelDispatcher.HANDHELD_THIRD_PERSON_RIGHT_HAND).setDisplayPos("thirdperson_lefthand", ItemModelDispatcher.HANDHELD_THIRD_PERSON_LEFT_HAND)
		);
		dispatcher.addDispatch(
			ModItems.CINNABAR_SHOVEL, new ItemModelStandard(ModItems.CINNABAR_SHOVEL, true).setDisplayPos("firstperson_righthand", ItemModelDispatcher.HANDHELD_FIRST_PERSON_RIGHT_HAND).setDisplayPos("firstperson_lefthand", ItemModelDispatcher.HANDHELD_FIRST_PERSON_LEFT_HAND).setDisplayPos("thirdperson_righthand", ItemModelDispatcher.HANDHELD_THIRD_PERSON_RIGHT_HAND).setDisplayPos("thirdperson_lefthand", ItemModelDispatcher.HANDHELD_THIRD_PERSON_LEFT_HAND)
		);
		dispatcher.addDispatch(
			ModItems.CINNABAR_HOE, new ItemModelStandard(ModItems.CINNABAR_HOE, true).setDisplayPos("firstperson_righthand", ItemModelDispatcher.HANDHELD_FIRST_PERSON_RIGHT_HAND).setDisplayPos("firstperson_lefthand", ItemModelDispatcher.HANDHELD_FIRST_PERSON_LEFT_HAND).setDisplayPos("thirdperson_righthand", ItemModelDispatcher.HANDHELD_THIRD_PERSON_RIGHT_HAND).setDisplayPos("thirdperson_lefthand", ItemModelDispatcher.HANDHELD_THIRD_PERSON_LEFT_HAND)
		);
		dispatcher.addDispatch(
			ModItems.CINNABAR_PAXEL, new ItemModelStandard(ModItems.CINNABAR_PAXEL, true).setDisplayPos("firstperson_righthand", ItemModelDispatcher.HANDHELD_FIRST_PERSON_RIGHT_HAND).setDisplayPos("firstperson_lefthand", ItemModelDispatcher.HANDHELD_FIRST_PERSON_LEFT_HAND).setDisplayPos("thirdperson_righthand", ItemModelDispatcher.HANDHELD_THIRD_PERSON_RIGHT_HAND).setDisplayPos("thirdperson_lefthand", ItemModelDispatcher.HANDHELD_THIRD_PERSON_LEFT_HAND)
		);
		dispatcher.addDispatch(
			ModItems.CINNABAR_HELMET, new ItemModelStandard(ModItems.CINNABAR_HELMET, true)
		);
		dispatcher.addDispatch(
			ModItems.CINNABAR_CHESTPLATE, new ItemModelStandard(ModItems.CINNABAR_CHESTPLATE, true)
		);
		dispatcher.addDispatch(
			ModItems.CINNABAR_LEGGINGS, new ItemModelStandard(ModItems.CINNABAR_LEGGINGS, true)
		);
		dispatcher.addDispatch(
			ModItems.CINNABAR_BOOTS, new ItemModelStandard(ModItems.CINNABAR_BOOTS, true)
		);
		dispatcher.addDispatch(
			ModItems.NEBULAR_CRYSTAL, new ItemModelStandard(ModItems.NEBULAR_CRYSTAL, true)
		);
		dispatcher.addDispatch(
			ModItems.NEBULAR_NUGGET, new ItemModelStandard(ModItems.NEBULAR_NUGGET, true)
		);
		dispatcher.addDispatch(
			ModItems.NEBULAR_INGOT, new ItemModelStandard(ModItems.NEBULAR_INGOT, true)
		);
		dispatcher.addDispatch(
			ModItems.NEBULAR_SWORD, new ItemModelStandard(ModItems.NEBULAR_SWORD, true).setDisplayPos("firstperson_righthand", ItemModelDispatcher.HANDHELD_FIRST_PERSON_RIGHT_HAND).setDisplayPos("firstperson_lefthand", ItemModelDispatcher.HANDHELD_FIRST_PERSON_LEFT_HAND).setDisplayPos("thirdperson_righthand", ItemModelDispatcher.HANDHELD_THIRD_PERSON_RIGHT_HAND).setDisplayPos("thirdperson_lefthand", ItemModelDispatcher.HANDHELD_THIRD_PERSON_LEFT_HAND)
		);
		dispatcher.addDispatch(
			ModItems.NEBULAR_PICKAXE, new ItemModelStandard(ModItems.NEBULAR_PICKAXE, true).setDisplayPos("firstperson_righthand", ItemModelDispatcher.HANDHELD_FIRST_PERSON_RIGHT_HAND).setDisplayPos("firstperson_lefthand", ItemModelDispatcher.HANDHELD_FIRST_PERSON_LEFT_HAND).setDisplayPos("thirdperson_righthand", ItemModelDispatcher.HANDHELD_THIRD_PERSON_RIGHT_HAND).setDisplayPos("thirdperson_lefthand", ItemModelDispatcher.HANDHELD_THIRD_PERSON_LEFT_HAND)
		);
		dispatcher.addDispatch(
			ModItems.NEBULAR_AXE, new ItemModelStandard(ModItems.NEBULAR_AXE, true).setDisplayPos("firstperson_righthand", ItemModelDispatcher.HANDHELD_FIRST_PERSON_RIGHT_HAND).setDisplayPos("firstperson_lefthand", ItemModelDispatcher.HANDHELD_FIRST_PERSON_LEFT_HAND).setDisplayPos("thirdperson_righthand", ItemModelDispatcher.HANDHELD_THIRD_PERSON_RIGHT_HAND).setDisplayPos("thirdperson_lefthand", ItemModelDispatcher.HANDHELD_THIRD_PERSON_LEFT_HAND)
		);
		dispatcher.addDispatch(
			ModItems.NEBULAR_SHOVEL, new ItemModelStandard(ModItems.NEBULAR_SHOVEL, true).setDisplayPos("firstperson_righthand", ItemModelDispatcher.HANDHELD_FIRST_PERSON_RIGHT_HAND).setDisplayPos("firstperson_lefthand", ItemModelDispatcher.HANDHELD_FIRST_PERSON_LEFT_HAND).setDisplayPos("thirdperson_righthand", ItemModelDispatcher.HANDHELD_THIRD_PERSON_RIGHT_HAND).setDisplayPos("thirdperson_lefthand", ItemModelDispatcher.HANDHELD_THIRD_PERSON_LEFT_HAND)
		);
		dispatcher.addDispatch(
			ModItems.NEBULAR_HOE, new ItemModelStandard(ModItems.NEBULAR_HOE, true).setDisplayPos("firstperson_righthand", ItemModelDispatcher.HANDHELD_FIRST_PERSON_RIGHT_HAND).setDisplayPos("firstperson_lefthand", ItemModelDispatcher.HANDHELD_FIRST_PERSON_LEFT_HAND).setDisplayPos("thirdperson_righthand", ItemModelDispatcher.HANDHELD_THIRD_PERSON_RIGHT_HAND).setDisplayPos("thirdperson_lefthand", ItemModelDispatcher.HANDHELD_THIRD_PERSON_LEFT_HAND)
		);
		dispatcher.addDispatch(
			ModItems.NEBULAR_PAXEL, new ItemModelStandard(ModItems.NEBULAR_PAXEL, true).setDisplayPos("firstperson_righthand", ItemModelDispatcher.HANDHELD_FIRST_PERSON_RIGHT_HAND).setDisplayPos("firstperson_lefthand", ItemModelDispatcher.HANDHELD_FIRST_PERSON_LEFT_HAND).setDisplayPos("thirdperson_righthand", ItemModelDispatcher.HANDHELD_THIRD_PERSON_RIGHT_HAND).setDisplayPos("thirdperson_lefthand", ItemModelDispatcher.HANDHELD_THIRD_PERSON_LEFT_HAND)
		);
		dispatcher.addDispatch(
			ModItems.NEBULAR_HELMET, new ItemModelStandard(ModItems.NEBULAR_HELMET, true)
		);
		dispatcher.addDispatch(
			ModItems.NEBULAR_CHESTPLATE, new ItemModelStandard(ModItems.NEBULAR_CHESTPLATE, true)
		);
		dispatcher.addDispatch(
			ModItems.NEBULAR_LEGGINGS, new ItemModelStandard(ModItems.NEBULAR_LEGGINGS, true)
		);
		dispatcher.addDispatch(
			ModItems.NEBULAR_BOOTS, new ItemModelStandard(ModItems.NEBULAR_BOOTS, true)
		);
		dispatcher.addDispatch(
			ModItems.RAW_MITHRIL, new ItemModelStandard(ModItems.RAW_MITHRIL, true)
		);
		dispatcher.addDispatch(
			ModItems.MITHRIL_NUGGET, new ItemModelStandard(ModItems.MITHRIL_NUGGET, true)
		);
		dispatcher.addDispatch(
			ModItems.MITHRIL_INGOT, new ItemModelStandard(ModItems.MITHRIL_INGOT, true)
		);
		dispatcher.addDispatch(
			ModItems.MITHRIL_SWORD, new ItemModelStandard(ModItems.MITHRIL_SWORD, true).setDisplayPos("firstperson_righthand", ItemModelDispatcher.HANDHELD_FIRST_PERSON_RIGHT_HAND).setDisplayPos("firstperson_lefthand", ItemModelDispatcher.HANDHELD_FIRST_PERSON_LEFT_HAND).setDisplayPos("thirdperson_righthand", ItemModelDispatcher.HANDHELD_THIRD_PERSON_RIGHT_HAND).setDisplayPos("thirdperson_lefthand", ItemModelDispatcher.HANDHELD_THIRD_PERSON_LEFT_HAND)
		);
		dispatcher.addDispatch(
			ModItems.MITHRIL_PICKAXE, new ItemModelStandard(ModItems.MITHRIL_PICKAXE, true).setDisplayPos("firstperson_righthand", ItemModelDispatcher.HANDHELD_FIRST_PERSON_RIGHT_HAND).setDisplayPos("firstperson_lefthand", ItemModelDispatcher.HANDHELD_FIRST_PERSON_LEFT_HAND).setDisplayPos("thirdperson_righthand", ItemModelDispatcher.HANDHELD_THIRD_PERSON_RIGHT_HAND).setDisplayPos("thirdperson_lefthand", ItemModelDispatcher.HANDHELD_THIRD_PERSON_LEFT_HAND)
		);
		dispatcher.addDispatch(
			ModItems.MITHRIL_AXE, new ItemModelStandard(ModItems.MITHRIL_AXE, true).setDisplayPos("firstperson_righthand", ItemModelDispatcher.HANDHELD_FIRST_PERSON_RIGHT_HAND).setDisplayPos("firstperson_lefthand", ItemModelDispatcher.HANDHELD_FIRST_PERSON_LEFT_HAND).setDisplayPos("thirdperson_righthand", ItemModelDispatcher.HANDHELD_THIRD_PERSON_RIGHT_HAND).setDisplayPos("thirdperson_lefthand", ItemModelDispatcher.HANDHELD_THIRD_PERSON_LEFT_HAND)
		);
		dispatcher.addDispatch(
			ModItems.MITHRIL_SHOVEL, new ItemModelStandard(ModItems.MITHRIL_SHOVEL, true).setDisplayPos("firstperson_righthand", ItemModelDispatcher.HANDHELD_FIRST_PERSON_RIGHT_HAND).setDisplayPos("firstperson_lefthand", ItemModelDispatcher.HANDHELD_FIRST_PERSON_LEFT_HAND).setDisplayPos("thirdperson_righthand", ItemModelDispatcher.HANDHELD_THIRD_PERSON_RIGHT_HAND).setDisplayPos("thirdperson_lefthand", ItemModelDispatcher.HANDHELD_THIRD_PERSON_LEFT_HAND)
		);
		dispatcher.addDispatch(
			ModItems.MITHRIL_HOE, new ItemModelStandard(ModItems.MITHRIL_HOE, true).setDisplayPos("firstperson_righthand", ItemModelDispatcher.HANDHELD_FIRST_PERSON_RIGHT_HAND).setDisplayPos("firstperson_lefthand", ItemModelDispatcher.HANDHELD_FIRST_PERSON_LEFT_HAND).setDisplayPos("thirdperson_righthand", ItemModelDispatcher.HANDHELD_THIRD_PERSON_RIGHT_HAND).setDisplayPos("thirdperson_lefthand", ItemModelDispatcher.HANDHELD_THIRD_PERSON_LEFT_HAND)
		);
		dispatcher.addDispatch(
			ModItems.MITHRIL_PAXEL, new ItemModelStandard(ModItems.MITHRIL_PAXEL, true).setDisplayPos("firstperson_righthand", ItemModelDispatcher.HANDHELD_FIRST_PERSON_RIGHT_HAND).setDisplayPos("firstperson_lefthand", ItemModelDispatcher.HANDHELD_FIRST_PERSON_LEFT_HAND).setDisplayPos("thirdperson_righthand", ItemModelDispatcher.HANDHELD_THIRD_PERSON_RIGHT_HAND).setDisplayPos("thirdperson_lefthand", ItemModelDispatcher.HANDHELD_THIRD_PERSON_LEFT_HAND)
		);
		dispatcher.addDispatch(
			ModItems.NARSIL_HANDLE, new ItemModelStandard(ModItems.NARSIL_HANDLE, true).setDisplayPos("firstperson_righthand", ItemModelDispatcher.HANDHELD_FIRST_PERSON_RIGHT_HAND).setDisplayPos("firstperson_lefthand", ItemModelDispatcher.HANDHELD_FIRST_PERSON_LEFT_HAND).setDisplayPos("thirdperson_righthand", ItemModelDispatcher.HANDHELD_THIRD_PERSON_RIGHT_HAND).setDisplayPos("thirdperson_lefthand", ItemModelDispatcher.HANDHELD_THIRD_PERSON_LEFT_HAND)
		);
		dispatcher.addDispatch(
			ModItems.ANDURIL, new ItemModelStandard(ModItems.ANDURIL, true).setDisplayPos("firstperson_righthand", ItemModelDispatcher.HANDHELD_FIRST_PERSON_RIGHT_HAND).setDisplayPos("firstperson_lefthand", ItemModelDispatcher.HANDHELD_FIRST_PERSON_LEFT_HAND).setDisplayPos("thirdperson_righthand", ItemModelDispatcher.HANDHELD_THIRD_PERSON_RIGHT_HAND).setDisplayPos("thirdperson_lefthand", ItemModelDispatcher.HANDHELD_THIRD_PERSON_LEFT_HAND)
		);

		dispatcher.addDispatch(
			ModItems.MITHRIL_HELMET, new ItemModelStandard(ModItems.MITHRIL_HELMET, true)
		);
		dispatcher.addDispatch(
			ModItems.MITHRIL_CHESTPLATE, new ItemModelStandard(ModItems.MITHRIL_CHESTPLATE, true)
		);
		dispatcher.addDispatch(
			ModItems.MITHRIL_LEGGINGS, new ItemModelStandard(ModItems.MITHRIL_LEGGINGS, true)
		);
		dispatcher.addDispatch(
			ModItems.MITHRIL_BOOTS, new ItemModelStandard(ModItems.MITHRIL_BOOTS, true)
		);

		dispatcher.addDispatch(
			ModItems.FLOUR, new ItemModelStandard(ModItems.FLOUR, true)
		);
		dispatcher.addDispatch(
			ModItems.BREADCRUMBS, new ItemModelStandard(ModItems.BREADCRUMBS, true)
		);
		dispatcher.addDispatch(
			ModItems.RAW_SCHNITZEL, new ItemModelStandard(ModItems.RAW_SCHNITZEL, true)
		);
		dispatcher.addDispatch(
			ModItems.SCHNITZEL, new ItemModelStandard(ModItems.SCHNITZEL, true)
		);
		dispatcher.addDispatch(
			ModItems.MAULTASCHE, new ItemModelStandard(ModItems.MAULTASCHE, true)
		);
		dispatcher.addDispatch(
			ModItems.MAULTASCHEN_BROTH, new ItemModelStandard(ModItems.MAULTASCHEN_BROTH, true)
		);

		dispatcher.addDispatch(
			ModItems.PRESERVES_JAR, new ItemModelStandard(ModItems.PRESERVES_JAR, true)
		);
		dispatcher.addDispatch(
			ModItems.CHERRY_JAM_JAR, new ItemModelStandard(ModItems.CHERRY_JAM_JAR, true)
		);
		dispatcher.addDispatch(
			ModItems.TOAST, new ItemModelStandard(ModItems.TOAST, true)
		);
		dispatcher.addDispatch(
			ModItems.CHERRY_JAM_TOAST, new ItemModelStandard(ModItems.CHERRY_JAM_TOAST, true)
		);
	}

	public void initEntityModels(EntityRendererDispatcher dispatcher) {

	}

	public void initTileEntityModels(TileEntityRenderDispatcher dispatcher) {

	}

	public void initBlockColors(BlockColorDispatcher dispatcher) {

	}
}
