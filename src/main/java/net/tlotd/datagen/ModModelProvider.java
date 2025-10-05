package net.tlotd.datagen;

import net.minecraft.client.render.EntityRenderDispatcher;
import net.minecraft.client.render.TileEntityRenderDispatcher;
import net.minecraft.client.render.block.color.BlockColorDispatcher;
import net.minecraft.client.render.block.model.BlockModelDispatcher;
import net.minecraft.client.render.block.model.BlockModelStandard;
import net.minecraft.client.render.item.model.ItemModelDispatcher;
import net.minecraft.client.render.item.model.ItemModelStandard;
import net.minecraft.client.render.texture.stitcher.TextureRegistry;
import net.minecraft.core.util.helper.Side;
import net.tlotd.TLOTD;
import net.tlotd.block.ModBlocks;
import net.tlotd.item.ModItems;
import turniplabs.halplibe.helper.ModelHelper;
import turniplabs.halplibe.util.ModelEntrypoint;

public class ModModelProvider implements ModelEntrypoint {
	@Override
	public void initBlockModels(BlockModelDispatcher blockModelDispatcher) {
		ModelHelper.setBlockModel(
			ModBlocks.STONE_FOSSIL,
			() -> new BlockModelStandard<>(ModBlocks.STONE_FOSSIL)
				.setTex(0, "tlotd:block/stone_fossil", Side.sides)
		);
		ModelHelper.setBlockModel(
			ModBlocks.BASALT_FOSSIL,
			() -> new BlockModelStandard<>(ModBlocks.BASALT_FOSSIL)
				.setTex(0, "tlotd:block/basalt_fossil", Side.sides)
		);
		ModelHelper.setBlockModel(
			ModBlocks.LIMESTONE_FOSSIL,
			() -> new BlockModelStandard<>(ModBlocks.LIMESTONE_FOSSIL)
				.setTex(0, "tlotd:block/limestone_fossil", Side.sides)
		);
		ModelHelper.setBlockModel(
			ModBlocks.GRANITE_FOSSIL,
			() -> new BlockModelStandard<>(ModBlocks.GRANITE_FOSSIL)
				.setTex(0, "tlotd:block/granite_fossil", Side.sides)
		);
		ModelHelper.setBlockModel(
			ModBlocks.PERMAFROST_FOSSIL,
			() -> new BlockModelStandard<>(ModBlocks.PERMAFROST_FOSSIL)
				.setTex(0, "tlotd:block/permafrost_fossil", Side.sides)
		);
		ModelHelper.setBlockModel(
			ModBlocks.RED_DEEPSLATE_FOSSIL,
			() -> new BlockModelStandard<>(ModBlocks.RED_DEEPSLATE_FOSSIL)
				.setTex(0, "tlotd:block/red_deepslate_fossil", Side.sides)
		);

		ModelHelper.setBlockModel(
			ModBlocks.HELIORITE_STONE_ORE,
			() -> new BlockModelStandard<>(ModBlocks.HELIORITE_STONE_ORE)
				.setTex(0, "tlotd:block/heliorite_stone_ore", Side.sides)
		);
		ModelHelper.setBlockModel(
			ModBlocks.HELIORITE_BASALT_ORE,
			() -> new BlockModelStandard<>(ModBlocks.HELIORITE_BASALT_ORE)
				.setTex(0, "tlotd:block/heliorite_basalt_ore", Side.sides)
		);
		ModelHelper.setBlockModel(
			ModBlocks.HELIORITE_LIMESTONE_ORE,
			() -> new BlockModelStandard<>(ModBlocks.HELIORITE_LIMESTONE_ORE)
				.setTex(0, "tlotd:block/heliorite_limestone_ore", Side.sides)
		);
		ModelHelper.setBlockModel(
			ModBlocks.HELIORITE_GRANITE_ORE,
			() -> new BlockModelStandard<>(ModBlocks.HELIORITE_GRANITE_ORE)
				.setTex(0, "tlotd:block/heliorite_granite_ore", Side.sides)
		);
		ModelHelper.setBlockModel(
			ModBlocks.HELIORITE_PERMAFROST_ORE,
			() -> new BlockModelStandard<>(ModBlocks.HELIORITE_PERMAFROST_ORE)
				.setTex(0, "tlotd:block/heliorite_permafrost_ore", Side.sides)
		);
		ModelHelper.setBlockModel(
			ModBlocks.HELIORITE_RED_DEEPSLATE_ORE,
			() -> new BlockModelStandard<>(ModBlocks.HELIORITE_RED_DEEPSLATE_ORE)
				.setTex(0, "tlotd:block/heliorite_red_deepslate_ore", Side.sides)
		);
		ModelHelper.setBlockModel(
			ModBlocks.HELIORITE_COMB_BLOCK,
			() -> new BlockModelStandard<>(ModBlocks.HELIORITE_COMB_BLOCK)
				.setTex(0, "tlotd:block/heliorite_comb_block", Side.sides)
		);
		ModelHelper.setBlockModel(
			ModBlocks.HELIORITE_BLOCK,
			() -> new BlockModelStandard<>(ModBlocks.HELIORITE_BLOCK)
				.setTex(0, "tlotd:block/heliorite_block", Side.sides)
		);

		ModelHelper.setBlockModel(
			ModBlocks.ENDURIUM_STONE_ORE,
			() -> new BlockModelStandard<>(ModBlocks.ENDURIUM_STONE_ORE)
				.setTex(0, "tlotd:block/endurium_stone_ore", Side.sides)
		);
		ModelHelper.setBlockModel(
			ModBlocks.ENDURIUM_BASALT_ORE,
			() -> new BlockModelStandard<>(ModBlocks.ENDURIUM_BASALT_ORE)
				.setTex(0, "tlotd:block/endurium_basalt_ore", Side.sides)
		);
		ModelHelper.setBlockModel(
			ModBlocks.ENDURIUM_LIMESTONE_ORE,
			() -> new BlockModelStandard<>(ModBlocks.ENDURIUM_LIMESTONE_ORE)
				.setTex(0, "tlotd:block/endurium_limestone_ore", Side.sides)
		);
		ModelHelper.setBlockModel(
			ModBlocks.ENDURIUM_GRANITE_ORE,
			() -> new BlockModelStandard<>(ModBlocks.ENDURIUM_GRANITE_ORE)
				.setTex(0, "tlotd:block/endurium_granite_ore", Side.sides)
		);
		ModelHelper.setBlockModel(
			ModBlocks.ENDURIUM_PERMAFROST_ORE,
			() -> new BlockModelStandard<>(ModBlocks.ENDURIUM_PERMAFROST_ORE)
				.setTex(0, "tlotd:block/endurium_permafrost_ore", Side.sides)
		);
		ModelHelper.setBlockModel(
			ModBlocks.ENDURIUM_RED_DEEPSLATE_ORE,
			() -> new BlockModelStandard<>(ModBlocks.ENDURIUM_RED_DEEPSLATE_ORE)
				.setTex(0, "tlotd:block/endurium_red_deepslate_ore", Side.sides)
		);
		ModelHelper.setBlockModel(
			ModBlocks.RAW_ENDURIUM_BLOCK,
			() -> new BlockModelStandard<>(ModBlocks.RAW_ENDURIUM_BLOCK)
				.setTex(0, "tlotd:block/raw_endurium_block", Side.sides)
		);
		ModelHelper.setBlockModel(
			ModBlocks.ENDURIUM_BLOCK,
			() -> new BlockModelStandard<>(ModBlocks.ENDURIUM_BLOCK)
				.setTex(0, "tlotd:block/endurium_block", Side.sides)
		);

		ModelHelper.setBlockModel(
			ModBlocks.PALLADIUM_STONE_ORE,
			() -> new BlockModelStandard<>(ModBlocks.PALLADIUM_STONE_ORE)
				.setTex(0, "tlotd:block/palladium_stone_ore", Side.sides)
		);
		ModelHelper.setBlockModel(
			ModBlocks.PALLADIUM_BASALT_ORE,
			() -> new BlockModelStandard<>(ModBlocks.PALLADIUM_BASALT_ORE)
				.setTex(0, "tlotd:block/palladium_basalt_ore", Side.sides)
		);
		ModelHelper.setBlockModel(
			ModBlocks.PALLADIUM_LIMESTONE_ORE,
			() -> new BlockModelStandard<>(ModBlocks.PALLADIUM_LIMESTONE_ORE)
				.setTex(0, "tlotd:block/palladium_limestone_ore", Side.sides)
		);
		ModelHelper.setBlockModel(
			ModBlocks.PALLADIUM_GRANITE_ORE,
			() -> new BlockModelStandard<>(ModBlocks.PALLADIUM_GRANITE_ORE)
				.setTex(0, "tlotd:block/palladium_granite_ore", Side.sides)
		);
		ModelHelper.setBlockModel(
			ModBlocks.PALLADIUM_PERMAFROST_ORE,
			() -> new BlockModelStandard<>(ModBlocks.PALLADIUM_PERMAFROST_ORE)
				.setTex(0, "tlotd:block/palladium_permafrost_ore", Side.sides)
		);
		ModelHelper.setBlockModel(
			ModBlocks.PALLADIUM_RED_DEEPSLATE_ORE,
			() -> new BlockModelStandard<>(ModBlocks.PALLADIUM_RED_DEEPSLATE_ORE)
				.setTex(0, "tlotd:block/palladium_red_deepslate_ore", Side.sides)
		);
		ModelHelper.setBlockModel(
			ModBlocks.RAW_PALLADIUM_BLOCK,
			() -> new BlockModelStandard<>(ModBlocks.RAW_PALLADIUM_BLOCK)
				.setTex(0, "tlotd:block/raw_palladium_block", Side.sides)
		);
		ModelHelper.setBlockModel(
			ModBlocks.PALLADIUM_BLOCK,
			() -> new BlockModelStandard<>(ModBlocks.PALLADIUM_BLOCK)
				.setTex(0, "tlotd:block/palladium_block", Side.sides)
		);

		ModelHelper.setBlockModel(
			ModBlocks.JURASSOLINE_STONE_ORE,
			() -> new BlockModelStandard<>(ModBlocks.JURASSOLINE_STONE_ORE)
				.setTex(0, "tlotd:block/jurassoline_stone_ore", Side.sides)
		);
		ModelHelper.setBlockModel(
			ModBlocks.JURASSOLINE_BASALT_ORE,
			() -> new BlockModelStandard<>(ModBlocks.JURASSOLINE_BASALT_ORE)
				.setTex(0, "tlotd:block/jurassoline_basalt_ore", Side.sides)
		);
		ModelHelper.setBlockModel(
			ModBlocks.JURASSOLINE_LIMESTONE_ORE,
			() -> new BlockModelStandard<>(ModBlocks.JURASSOLINE_LIMESTONE_ORE)
				.setTex(0, "tlotd:block/jurassoline_limestone_ore", Side.sides)
		);
		ModelHelper.setBlockModel(
			ModBlocks.JURASSOLINE_GRANITE_ORE,
			() -> new BlockModelStandard<>(ModBlocks.JURASSOLINE_GRANITE_ORE)
				.setTex(0, "tlotd:block/jurassoline_granite_ore", Side.sides)
		);
		ModelHelper.setBlockModel(
			ModBlocks.JURASSOLINE_PERMAFROST_ORE,
			() -> new BlockModelStandard<>(ModBlocks.JURASSOLINE_PERMAFROST_ORE)
				.setTex(0, "tlotd:block/jurassoline_permafrost_ore", Side.sides)
		);
		ModelHelper.setBlockModel(
			ModBlocks.JURASSOLINE_RED_DEEPSLATE_ORE,
			() -> new BlockModelStandard<>(ModBlocks.JURASSOLINE_RED_DEEPSLATE_ORE)
				.setTex(0, "tlotd:block/jurassoline_red_deepslate_ore", Side.sides)
		);
		ModelHelper.setBlockModel(
			ModBlocks.JURASSOLINE_CRYSTAL_BLOCK,
			() -> new BlockModelStandard<>(ModBlocks.JURASSOLINE_CRYSTAL_BLOCK)
				.setTex(0, "tlotd:block/jurassoline_crystal_block", Side.sides)
		);
		ModelHelper.setBlockModel(
			ModBlocks.JURASSOLINE_BLOCK,
			() -> new BlockModelStandard<>(ModBlocks.JURASSOLINE_BLOCK)
				.setTex(0, "tlotd:block/jurassoline_block", Side.sides)
		);

		ModelHelper.setBlockModel(
			ModBlocks.CINNABAR_STONE_ORE,
			() -> new BlockModelStandard<>(ModBlocks.CINNABAR_STONE_ORE)
				.setTex(0, "tlotd:block/cinnabar_stone_ore", Side.sides)
		);
		ModelHelper.setBlockModel(
			ModBlocks.CINNABAR_BASALT_ORE,
			() -> new BlockModelStandard<>(ModBlocks.CINNABAR_BASALT_ORE)
				.setTex(0, "tlotd:block/cinnabar_basalt_ore", Side.sides)
		);
		ModelHelper.setBlockModel(
			ModBlocks.CINNABAR_LIMESTONE_ORE,
			() -> new BlockModelStandard<>(ModBlocks.CINNABAR_LIMESTONE_ORE)
				.setTex(0, "tlotd:block/cinnabar_limestone_ore", Side.sides)
		);
		ModelHelper.setBlockModel(
			ModBlocks.CINNABAR_GRANITE_ORE,
			() -> new BlockModelStandard<>(ModBlocks.CINNABAR_GRANITE_ORE)
				.setTex(0, "tlotd:block/cinnabar_granite_ore", Side.sides)
		);
		ModelHelper.setBlockModel(
			ModBlocks.CINNABAR_PERMAFROST_ORE,
			() -> new BlockModelStandard<>(ModBlocks.CINNABAR_PERMAFROST_ORE)
				.setTex(0, "tlotd:block/cinnabar_permafrost_ore", Side.sides)
		);
		ModelHelper.setBlockModel(
			ModBlocks.CINNABAR_RED_DEEPSLATE_ORE,
			() -> new BlockModelStandard<>(ModBlocks.CINNABAR_RED_DEEPSLATE_ORE)
				.setTex(0, "tlotd:block/cinnabar_red_deepslate_ore", Side.sides)
		);
		ModelHelper.setBlockModel(
			ModBlocks.CINNABAR_CRYSTAL_BLOCK,
			() -> new BlockModelStandard<>(ModBlocks.CINNABAR_CRYSTAL_BLOCK)
				.setTex(0, "tlotd:block/cinnabar_crystal_block", Side.sides)
		);
		ModelHelper.setBlockModel(
			ModBlocks.CINNABAR_BLOCK,
			() -> new BlockModelStandard<>(ModBlocks.CINNABAR_BLOCK)
				.setTex(0, "tlotd:block/cinnabar_block", Side.sides)
		);

		ModelHelper.setBlockModel(
			ModBlocks.NEBULAR_STONE_ORE,
			() -> new BlockModelStandard<>(ModBlocks.NEBULAR_STONE_ORE)
				.setTex(0, "tlotd:block/nebular_stone_ore", Side.sides)
		);
		ModelHelper.setBlockModel(
			ModBlocks.NEBULAR_BASALT_ORE,
			() -> new BlockModelStandard<>(ModBlocks.NEBULAR_BASALT_ORE)
				.setTex(0, "tlotd:block/nebular_basalt_ore", Side.sides)
		);
		ModelHelper.setBlockModel(
			ModBlocks.NEBULAR_LIMESTONE_ORE,
			() -> new BlockModelStandard<>(ModBlocks.NEBULAR_LIMESTONE_ORE)
				.setTex(0, "tlotd:block/nebular_limestone_ore", Side.sides)
		);
		ModelHelper.setBlockModel(
			ModBlocks.NEBULAR_GRANITE_ORE,
			() -> new BlockModelStandard<>(ModBlocks.NEBULAR_GRANITE_ORE)
				.setTex(0, "tlotd:block/nebular_granite_ore", Side.sides)
		);
		ModelHelper.setBlockModel(
			ModBlocks.NEBULAR_PERMAFROST_ORE,
			() -> new BlockModelStandard<>(ModBlocks.NEBULAR_PERMAFROST_ORE)
				.setTex(0, "tlotd:block/nebular_permafrost_ore", Side.sides)
		);
		ModelHelper.setBlockModel(
			ModBlocks.NEBULAR_RED_DEEPSLATE_ORE,
			() -> new BlockModelStandard<>(ModBlocks.NEBULAR_RED_DEEPSLATE_ORE)
				.setTex(0, "tlotd:block/nebular_red_deepslate_ore", Side.sides)
		);
		ModelHelper.setBlockModel(
			ModBlocks.RAW_NEBULAR_BLOCK,
			() -> new BlockModelStandard<>(ModBlocks.RAW_NEBULAR_BLOCK)
				.setTex(0, "tlotd:block/raw_nebular_block", Side.sides)
		);
		ModelHelper.setBlockModel(
			ModBlocks.NEBULAR_BLOCK,
			() -> new BlockModelStandard<>(ModBlocks.NEBULAR_BLOCK)
				.setTex(0, "tlotd:block/nebular_block", Side.sides)
		);

		ModelHelper.setBlockModel(
			ModBlocks.MITHRIL_STONE_ORE,
			() -> new BlockModelStandard<>(ModBlocks.MITHRIL_STONE_ORE)
				.setTex(0, "tlotd:block/mithril_stone_ore", Side.sides)
		);
		ModelHelper.setBlockModel(
			ModBlocks.MITHRIL_BASALT_ORE,
			() -> new BlockModelStandard<>(ModBlocks.MITHRIL_BASALT_ORE)
				.setTex(0, "tlotd:block/mithril_basalt_ore", Side.sides)
		);
		ModelHelper.setBlockModel(
			ModBlocks.MITHRIL_LIMESTONE_ORE,
			() -> new BlockModelStandard<>(ModBlocks.MITHRIL_LIMESTONE_ORE)
				.setTex(0, "tlotd:block/mithril_limestone_ore", Side.sides)
		);
		ModelHelper.setBlockModel(
			ModBlocks.MITHRIL_GRANITE_ORE,
			() -> new BlockModelStandard<>(ModBlocks.MITHRIL_GRANITE_ORE)
				.setTex(0, "tlotd:block/mithril_granite_ore", Side.sides)
		);
		ModelHelper.setBlockModel(
			ModBlocks.MITHRIL_PERMAFROST_ORE,
			() -> new BlockModelStandard<>(ModBlocks.MITHRIL_PERMAFROST_ORE)
				.setTex(0, "tlotd:block/mithril_permafrost_ore", Side.sides)
		);
		ModelHelper.setBlockModel(
			ModBlocks.MITHRIL_RED_DEEPSLATE_ORE,
			() -> new BlockModelStandard<>(ModBlocks.MITHRIL_RED_DEEPSLATE_ORE)
				.setTex(0, "tlotd:block/mithril_red_deepslate_ore", Side.sides)
		);
		ModelHelper.setBlockModel(
			ModBlocks.MITHRIL_BEDROCK_ORE,
			() -> new BlockModelStandard<>(ModBlocks.MITHRIL_BEDROCK_ORE)
				.setTex(0, "tlotd:block/mithril_bedrock_ore", Side.sides)
		);
		ModelHelper.setBlockModel(
			ModBlocks.RAW_MITHRIL_BLOCK,
			() -> new BlockModelStandard<>(ModBlocks.RAW_MITHRIL_BLOCK)
				.setTex(0, "tlotd:block/raw_mithril_block", Side.sides)
		);
		ModelHelper.setBlockModel(
			ModBlocks.MITHRIL_BLOCK,
			() -> new BlockModelStandard<>(ModBlocks.MITHRIL_BLOCK)
				.setTex(0, "tlotd:block/mithril_block", Side.sides)
		);

		ModelHelper.setBlockModel(
			ModBlocks.RED_DEEPSLATE,
			() -> new BlockModelStandard<>(ModBlocks.RED_DEEPSLATE)
				.setTex(0, "tlotd:block/red_deepslate", Side.sides)
		);
		ModelHelper.setBlockModel(
			ModBlocks.RED_DEEPSLATE_BRICKS,
			() -> new BlockModelStandard<>(ModBlocks.RED_DEEPSLATE_BRICKS)
				.setTex(0, "tlotd:block/red_deepslate_bricks", Side.sides)
		);
		ModelHelper.setBlockModel(
			ModBlocks.COBBLED_RED_DEEPSLATE,
			() -> new BlockModelStandard<>(ModBlocks.COBBLED_RED_DEEPSLATE)
				.setTex(0, "tlotd:block/cobbled_red_deepslate", Side.sides)
		);
		ModelHelper.setBlockModel(
			ModBlocks.COBBLED_RED_DEEPSLATE_BRICKS,
			() -> new BlockModelStandard<>(ModBlocks.COBBLED_RED_DEEPSLATE_BRICKS)
				.setTex(0, "tlotd:block/cobbled_red_deepslate_bricks", Side.sides)
		);
	}

	@Override
	public void initItemModels(ItemModelDispatcher itemModelDispatcher) {

		ModelHelper.setItemModel(
			ModItems.STEEL_ROD, () -> {
				ItemModelStandard model = new ItemModelStandard(ModItems.STEEL_ROD, TLOTD.MOD_ID).setFull3D();
				model.icon = TextureRegistry.getTexture(ModItems.STEEL_ROD.namespaceID);
				return model;
			}
		);
		ModelHelper.setItemModel(
			ModItems.FOSSILIZED_BONE, () -> {
				ItemModelStandard model = new ItemModelStandard(ModItems.FOSSILIZED_BONE, TLOTD.MOD_ID).setFull3D();
				model.icon = TextureRegistry.getTexture(ModItems.FOSSILIZED_BONE.namespaceID);
				return model;
			}
		);
		ModelHelper.setItemModel(
			ModItems.REINFORCED_TOOL_ROD, () -> {
				ItemModelStandard model = new ItemModelStandard(ModItems.REINFORCED_TOOL_ROD, TLOTD.MOD_ID).setFull3D();
				model.icon = TextureRegistry.getTexture(ModItems.REINFORCED_TOOL_ROD.namespaceID);
				return model;
			}
		);
		ModelHelper.setItemModel(
			ModItems.FANCY_TOOL_ROD, () -> {
				ItemModelStandard model = new ItemModelStandard(ModItems.FANCY_TOOL_ROD, TLOTD.MOD_ID).setFull3D();
				model.icon = TextureRegistry.getTexture(ModItems.FANCY_TOOL_ROD.namespaceID);
				return model;
			}
		);
		ModelHelper.setItemModel(
			ModItems.HELIORITE_COMB, () -> {
				ItemModelStandard model = new ItemModelStandard(ModItems.HELIORITE_COMB, TLOTD.MOD_ID);
				model.icon = TextureRegistry.getTexture(ModItems.HELIORITE_COMB.namespaceID);
				return model;
			}
		);
		ModelHelper.setItemModel(
			ModItems.HELIORITE_NUGGET, () -> {
				ItemModelStandard model = new ItemModelStandard(ModItems.HELIORITE_NUGGET, TLOTD.MOD_ID);
				model.icon = TextureRegistry.getTexture(ModItems.HELIORITE_NUGGET.namespaceID);
				return model;
			}
		);
		ModelHelper.setItemModel(
			ModItems.HELIORITE_INGOT, () -> {
				ItemModelStandard model = new ItemModelStandard(ModItems.HELIORITE_INGOT, TLOTD.MOD_ID);
				model.icon = TextureRegistry.getTexture(ModItems.HELIORITE_INGOT.namespaceID);
				return model;
			}
		);
		ModelHelper.setItemModel(
			ModItems.HELIORITE_SWORD, () -> {
				ItemModelStandard model = new ItemModelStandard(ModItems.HELIORITE_SWORD, TLOTD.MOD_ID).setFull3D();
				model.icon = TextureRegistry.getTexture(ModItems.HELIORITE_SWORD.namespaceID);
				return model;
			}
		);
		ModelHelper.setItemModel(
			ModItems.HELIORITE_PICKAXE, () -> {
				ItemModelStandard model = new ItemModelStandard(ModItems.HELIORITE_PICKAXE, TLOTD.MOD_ID).setFull3D();
				model.icon = TextureRegistry.getTexture(ModItems.HELIORITE_PICKAXE.namespaceID);
				return model;
			}
		);
		ModelHelper.setItemModel(
			ModItems.HELIORITE_AXE, () -> {
				ItemModelStandard model = new ItemModelStandard(ModItems.HELIORITE_AXE, TLOTD.MOD_ID).setFull3D();
				model.icon = TextureRegistry.getTexture(ModItems.HELIORITE_AXE.namespaceID);
				return model;
			}
		);
		ModelHelper.setItemModel(
			ModItems.HELIORITE_SHOVEL, () -> {
				ItemModelStandard model = new ItemModelStandard(ModItems.HELIORITE_SHOVEL, TLOTD.MOD_ID).setFull3D();
				model.icon = TextureRegistry.getTexture(ModItems.HELIORITE_SHOVEL.namespaceID);
				return model;
			}
		);
		ModelHelper.setItemModel(
			ModItems.HELIORITE_HOE, () -> {
				ItemModelStandard model = new ItemModelStandard(ModItems.HELIORITE_HOE, TLOTD.MOD_ID).setFull3D();
				model.icon = TextureRegistry.getTexture(ModItems.HELIORITE_HOE.namespaceID);
				return model;
			}
		);
		ModelHelper.setItemModel(
			ModItems.HELIORITE_PAXEL, () -> {
				ItemModelStandard model = new ItemModelStandard(ModItems.HELIORITE_PAXEL, TLOTD.MOD_ID).setFull3D();
				model.icon = TextureRegistry.getTexture(ModItems.HELIORITE_PAXEL.namespaceID);
				return model;
			}
		);
		ModelHelper.setItemModel(
			ModItems.HELIORITE_HELMET, () -> {
				ItemModelStandard model = new ItemModelStandard(ModItems.HELIORITE_HELMET, TLOTD.MOD_ID);
				model.icon = TextureRegistry.getTexture(ModItems.HELIORITE_HELMET.namespaceID);
				return model;
			}
		);
		ModelHelper.setItemModel(
			ModItems.HELIORITE_CHESTPLATE, () -> {
				ItemModelStandard model = new ItemModelStandard(ModItems.HELIORITE_CHESTPLATE, TLOTD.MOD_ID);
				model.icon = TextureRegistry.getTexture(ModItems.HELIORITE_CHESTPLATE.namespaceID);
				return model;
			}
		);
		ModelHelper.setItemModel(
			ModItems.HELIORITE_LEGGINGS, () -> {
				ItemModelStandard model = new ItemModelStandard(ModItems.HELIORITE_LEGGINGS, TLOTD.MOD_ID);
				model.icon = TextureRegistry.getTexture(ModItems.HELIORITE_LEGGINGS.namespaceID);
				return model;
			}
		);
		ModelHelper.setItemModel(
			ModItems.HELIORITE_BOOTS, () -> {
				ItemModelStandard model = new ItemModelStandard(ModItems.HELIORITE_BOOTS, TLOTD.MOD_ID);
				model.icon = TextureRegistry.getTexture(ModItems.HELIORITE_BOOTS.namespaceID);
				return model;
			}
		);
		ModelHelper.setItemModel(
			ModItems.ENDURIUM_CRYSTAL, () -> {
				ItemModelStandard model = new ItemModelStandard(ModItems.ENDURIUM_CRYSTAL, TLOTD.MOD_ID);
				model.icon = TextureRegistry.getTexture(ModItems.ENDURIUM_CRYSTAL.namespaceID);
				return model;
			}
		);
		ModelHelper.setItemModel(
			ModItems.ENDURIUM_NUGGET, () -> {
				ItemModelStandard model = new ItemModelStandard(ModItems.ENDURIUM_NUGGET, TLOTD.MOD_ID);
				model.icon = TextureRegistry.getTexture(ModItems.ENDURIUM_NUGGET.namespaceID);
				return model;
			}
		);
		ModelHelper.setItemModel(
			ModItems.ENDURIUM_INGOT, () -> {
				ItemModelStandard model = new ItemModelStandard(ModItems.ENDURIUM_INGOT, TLOTD.MOD_ID);
				model.icon = TextureRegistry.getTexture(ModItems.ENDURIUM_INGOT.namespaceID);
				return model;
			}
		);
		ModelHelper.setItemModel(
			ModItems.ENDURIUM_SWORD, () -> {
				ItemModelStandard model = new ItemModelStandard(ModItems.ENDURIUM_SWORD, TLOTD.MOD_ID).setFull3D();
				model.icon = TextureRegistry.getTexture(ModItems.ENDURIUM_SWORD.namespaceID);
				return model;
			}
		);
		ModelHelper.setItemModel(
			ModItems.ENDURIUM_PICKAXE, () -> {
				ItemModelStandard model = new ItemModelStandard(ModItems.ENDURIUM_PICKAXE, TLOTD.MOD_ID).setFull3D();
				model.icon = TextureRegistry.getTexture(ModItems.ENDURIUM_PICKAXE.namespaceID);
				return model;
			}
		);
		ModelHelper.setItemModel(
			ModItems.ENDURIUM_AXE, () -> {
				ItemModelStandard model = new ItemModelStandard(ModItems.ENDURIUM_AXE, TLOTD.MOD_ID).setFull3D();
				model.icon = TextureRegistry.getTexture(ModItems.ENDURIUM_AXE.namespaceID);
				return model;
			}
		);
		ModelHelper.setItemModel(
			ModItems.ENDURIUM_SHOVEL, () -> {
				ItemModelStandard model = new ItemModelStandard(ModItems.ENDURIUM_SHOVEL, TLOTD.MOD_ID).setFull3D();
				model.icon = TextureRegistry.getTexture(ModItems.ENDURIUM_SHOVEL.namespaceID);
				return model;
			}
		);
		ModelHelper.setItemModel(
			ModItems.ENDURIUM_HOE, () -> {
				ItemModelStandard model = new ItemModelStandard(ModItems.ENDURIUM_HOE, TLOTD.MOD_ID).setFull3D();
				model.icon = TextureRegistry.getTexture(ModItems.ENDURIUM_HOE.namespaceID);
				return model;
			}
		);
		ModelHelper.setItemModel(
			ModItems.ENDURIUM_PAXEL, () -> {
				ItemModelStandard model = new ItemModelStandard(ModItems.ENDURIUM_PAXEL, TLOTD.MOD_ID).setFull3D();
				model.icon = TextureRegistry.getTexture(ModItems.ENDURIUM_PAXEL.namespaceID);
				return model;
			}
		);
		ModelHelper.setItemModel(
			ModItems.ENDURIUM_HELMET, () -> {
				ItemModelStandard model = new ItemModelStandard(ModItems.ENDURIUM_HELMET, TLOTD.MOD_ID);
				model.icon = TextureRegistry.getTexture(ModItems.ENDURIUM_HELMET.namespaceID);
				return model;
			}
		);
		ModelHelper.setItemModel(
			ModItems.ENDURIUM_CHESTPLATE, () -> {
				ItemModelStandard model = new ItemModelStandard(ModItems.ENDURIUM_CHESTPLATE, TLOTD.MOD_ID);
				model.icon = TextureRegistry.getTexture(ModItems.ENDURIUM_CHESTPLATE.namespaceID);
				return model;
			}
		);
		ModelHelper.setItemModel(
			ModItems.ENDURIUM_LEGGINGS, () -> {
				ItemModelStandard model = new ItemModelStandard(ModItems.ENDURIUM_LEGGINGS, TLOTD.MOD_ID);
				model.icon = TextureRegistry.getTexture(ModItems.ENDURIUM_LEGGINGS.namespaceID);
				return model;
			}
		);
		ModelHelper.setItemModel(
			ModItems.ENDURIUM_BOOTS, () -> {
				ItemModelStandard model = new ItemModelStandard(ModItems.ENDURIUM_BOOTS, TLOTD.MOD_ID);
				model.icon = TextureRegistry.getTexture(ModItems.ENDURIUM_BOOTS.namespaceID);
				return model;
			}
		);
		ModelHelper.setItemModel(
			ModItems.RAW_PALLADIUM, () -> {
				ItemModelStandard model = new ItemModelStandard(ModItems.RAW_PALLADIUM, TLOTD.MOD_ID);
				model.icon = TextureRegistry.getTexture(ModItems.RAW_PALLADIUM.namespaceID);
				return model;
			}
		);
		ModelHelper.setItemModel(
			ModItems.PALLADIUM_NUGGET, () -> {
				ItemModelStandard model = new ItemModelStandard(ModItems.PALLADIUM_NUGGET, TLOTD.MOD_ID);
				model.icon = TextureRegistry.getTexture(ModItems.PALLADIUM_NUGGET.namespaceID);
				return model;
			}
		);
		ModelHelper.setItemModel(
			ModItems.PALLADIUM_INGOT, () -> {
				ItemModelStandard model = new ItemModelStandard(ModItems.PALLADIUM_INGOT, TLOTD.MOD_ID);
				model.icon = TextureRegistry.getTexture(ModItems.PALLADIUM_INGOT.namespaceID);
				return model;
			}
		);
		ModelHelper.setItemModel(
			ModItems.PALLADIUM_SWORD, () -> {
				ItemModelStandard model = new ItemModelStandard(ModItems.PALLADIUM_SWORD, TLOTD.MOD_ID).setFull3D();
				model.icon = TextureRegistry.getTexture(ModItems.PALLADIUM_SWORD.namespaceID);
				return model;
			}
		);
		ModelHelper.setItemModel(
			ModItems.PALLADIUM_PICKAXE, () -> {
				ItemModelStandard model = new ItemModelStandard(ModItems.PALLADIUM_PICKAXE, TLOTD.MOD_ID).setFull3D();
				model.icon = TextureRegistry.getTexture(ModItems.PALLADIUM_PICKAXE.namespaceID);
				return model;
			}
		);
		ModelHelper.setItemModel(
			ModItems.PALLADIUM_AXE, () -> {
				ItemModelStandard model = new ItemModelStandard(ModItems.PALLADIUM_AXE, TLOTD.MOD_ID).setFull3D();
				model.icon = TextureRegistry.getTexture(ModItems.PALLADIUM_AXE.namespaceID);
				return model;
			}
		);
		ModelHelper.setItemModel(
			ModItems.PALLADIUM_SHOVEL, () -> {
				ItemModelStandard model = new ItemModelStandard(ModItems.PALLADIUM_SHOVEL, TLOTD.MOD_ID).setFull3D();
				model.icon = TextureRegistry.getTexture(ModItems.PALLADIUM_SHOVEL.namespaceID);
				return model;
			}
		);
		ModelHelper.setItemModel(
			ModItems.PALLADIUM_HOE, () -> {
				ItemModelStandard model = new ItemModelStandard(ModItems.PALLADIUM_HOE, TLOTD.MOD_ID).setFull3D();
				model.icon = TextureRegistry.getTexture(ModItems.PALLADIUM_HOE.namespaceID);
				return model;
			}
		);
		ModelHelper.setItemModel(
			ModItems.PALLADIUM_PAXEL, () -> {
				ItemModelStandard model = new ItemModelStandard(ModItems.PALLADIUM_PAXEL, TLOTD.MOD_ID).setFull3D();
				model.icon = TextureRegistry.getTexture(ModItems.PALLADIUM_PAXEL.namespaceID);
				return model;
			}
		);
		ModelHelper.setItemModel(
			ModItems.PALLADIUM_HELMET, () -> {
				ItemModelStandard model = new ItemModelStandard(ModItems.PALLADIUM_HELMET, TLOTD.MOD_ID);
				model.icon = TextureRegistry.getTexture(ModItems.PALLADIUM_HELMET.namespaceID);
				return model;
			}
		);
		ModelHelper.setItemModel(
			ModItems.PALLADIUM_CHESTPLATE, () -> {
				ItemModelStandard model = new ItemModelStandard(ModItems.PALLADIUM_CHESTPLATE, TLOTD.MOD_ID);
				model.icon = TextureRegistry.getTexture(ModItems.PALLADIUM_CHESTPLATE.namespaceID);
				return model;
			}
		);
		ModelHelper.setItemModel(
			ModItems.PALLADIUM_LEGGINGS, () -> {
				ItemModelStandard model = new ItemModelStandard(ModItems.PALLADIUM_LEGGINGS, TLOTD.MOD_ID);
				model.icon = TextureRegistry.getTexture(ModItems.PALLADIUM_LEGGINGS.namespaceID);
				return model;
			}
		);
		ModelHelper.setItemModel(
			ModItems.PALLADIUM_BOOTS, () -> {
				ItemModelStandard model = new ItemModelStandard(ModItems.PALLADIUM_BOOTS, TLOTD.MOD_ID);
				model.icon = TextureRegistry.getTexture(ModItems.PALLADIUM_BOOTS.namespaceID);
				return model;
			}
		);
		ModelHelper.setItemModel(
			ModItems.JURASSOLINE_CRYSTAL, () -> {
				ItemModelStandard model = new ItemModelStandard(ModItems.JURASSOLINE_CRYSTAL, TLOTD.MOD_ID);
				model.icon = TextureRegistry.getTexture(ModItems.JURASSOLINE_CRYSTAL.namespaceID);
				return model;
			}
		);
		ModelHelper.setItemModel(
			ModItems.JURASSOLINE_NUGGET, () -> {
				ItemModelStandard model = new ItemModelStandard(ModItems.JURASSOLINE_NUGGET, TLOTD.MOD_ID);
				model.icon = TextureRegistry.getTexture(ModItems.JURASSOLINE_NUGGET.namespaceID);
				return model;
			}
		);
		ModelHelper.setItemModel(
			ModItems.JURASSOLINE_INGOT, () -> {
				ItemModelStandard model = new ItemModelStandard(ModItems.JURASSOLINE_INGOT, TLOTD.MOD_ID);
				model.icon = TextureRegistry.getTexture(ModItems.JURASSOLINE_INGOT.namespaceID);
				return model;
			}
		);
		ModelHelper.setItemModel(
			ModItems.JURASSOLINE_SWORD, () -> {
				ItemModelStandard model = new ItemModelStandard(ModItems.JURASSOLINE_SWORD, TLOTD.MOD_ID).setFull3D();
				model.icon = TextureRegistry.getTexture(ModItems.JURASSOLINE_SWORD.namespaceID);
				return model;
			}
		);
		ModelHelper.setItemModel(
			ModItems.JURASSOLINE_PICKAXE, () -> {
				ItemModelStandard model = new ItemModelStandard(ModItems.JURASSOLINE_PICKAXE, TLOTD.MOD_ID).setFull3D();
				model.icon = TextureRegistry.getTexture(ModItems.JURASSOLINE_PICKAXE.namespaceID);
				return model;
			}
		);
		ModelHelper.setItemModel(
			ModItems.JURASSOLINE_AXE, () -> {
				ItemModelStandard model = new ItemModelStandard(ModItems.JURASSOLINE_AXE, TLOTD.MOD_ID).setFull3D();
				model.icon = TextureRegistry.getTexture(ModItems.JURASSOLINE_AXE.namespaceID);
				return model;
			}
		);
		ModelHelper.setItemModel(
			ModItems.JURASSOLINE_SHOVEL, () -> {
				ItemModelStandard model = new ItemModelStandard(ModItems.JURASSOLINE_SHOVEL, TLOTD.MOD_ID).setFull3D();
				model.icon = TextureRegistry.getTexture(ModItems.JURASSOLINE_SHOVEL.namespaceID);
				return model;
			}
		);
		ModelHelper.setItemModel(
			ModItems.JURASSOLINE_HOE, () -> {
				ItemModelStandard model = new ItemModelStandard(ModItems.JURASSOLINE_HOE, TLOTD.MOD_ID).setFull3D();
				model.icon = TextureRegistry.getTexture(ModItems.JURASSOLINE_HOE.namespaceID);
				return model;
			}
		);
		ModelHelper.setItemModel(
			ModItems.JURASSOLINE_PAXEL, () -> {
				ItemModelStandard model = new ItemModelStandard(ModItems.JURASSOLINE_PAXEL, TLOTD.MOD_ID).setFull3D();
				model.icon = TextureRegistry.getTexture(ModItems.JURASSOLINE_PAXEL.namespaceID);
				return model;
			}
		);
		ModelHelper.setItemModel(
			ModItems.JURASSOLINE_HELMET, () -> {
				ItemModelStandard model = new ItemModelStandard(ModItems.JURASSOLINE_HELMET, TLOTD.MOD_ID);
				model.icon = TextureRegistry.getTexture(ModItems.JURASSOLINE_HELMET.namespaceID);
				return model;
			}
		);
		ModelHelper.setItemModel(
			ModItems.JURASSOLINE_CHESTPLATE, () -> {
				ItemModelStandard model = new ItemModelStandard(ModItems.JURASSOLINE_CHESTPLATE, TLOTD.MOD_ID);
				model.icon = TextureRegistry.getTexture(ModItems.JURASSOLINE_CHESTPLATE.namespaceID);
				return model;
			}
		);
		ModelHelper.setItemModel(
			ModItems.JURASSOLINE_LEGGINGS, () -> {
				ItemModelStandard model = new ItemModelStandard(ModItems.JURASSOLINE_LEGGINGS, TLOTD.MOD_ID);
				model.icon = TextureRegistry.getTexture(ModItems.JURASSOLINE_LEGGINGS.namespaceID);
				return model;
			}
		);
		ModelHelper.setItemModel(
			ModItems.JURASSOLINE_BOOTS, () -> {
				ItemModelStandard model = new ItemModelStandard(ModItems.JURASSOLINE_BOOTS, TLOTD.MOD_ID);
				model.icon = TextureRegistry.getTexture(ModItems.JURASSOLINE_BOOTS.namespaceID);
				return model;
			}
		);
		ModelHelper.setItemModel(
			ModItems.CINNABAR_CRYSTAL, () -> {
				ItemModelStandard model = new ItemModelStandard(ModItems.CINNABAR_CRYSTAL, TLOTD.MOD_ID);
				model.icon = TextureRegistry.getTexture(ModItems.CINNABAR_CRYSTAL.namespaceID);
				return model;
			}
		);
		ModelHelper.setItemModel(
			ModItems.CINNABAR_NUGGET, () -> {
				ItemModelStandard model = new ItemModelStandard(ModItems.CINNABAR_NUGGET, TLOTD.MOD_ID);
				model.icon = TextureRegistry.getTexture(ModItems.CINNABAR_NUGGET.namespaceID);
				return model;
			}
		);
		ModelHelper.setItemModel(
			ModItems.CINNABAR_INGOT, () -> {
				ItemModelStandard model = new ItemModelStandard(ModItems.CINNABAR_INGOT, TLOTD.MOD_ID);
				model.icon = TextureRegistry.getTexture(ModItems.CINNABAR_INGOT.namespaceID);
				return model;
			}
		);
		ModelHelper.setItemModel(
			ModItems.CINNABAR_SWORD, () -> {
				ItemModelStandard model = new ItemModelStandard(ModItems.CINNABAR_SWORD, TLOTD.MOD_ID).setFull3D();
				model.icon = TextureRegistry.getTexture(ModItems.CINNABAR_SWORD.namespaceID);
				return model;
			}
		);
		ModelHelper.setItemModel(
			ModItems.CINNABAR_PICKAXE, () -> {
				ItemModelStandard model = new ItemModelStandard(ModItems.CINNABAR_PICKAXE, TLOTD.MOD_ID).setFull3D();
				model.icon = TextureRegistry.getTexture(ModItems.CINNABAR_PICKAXE.namespaceID);
				return model;
			}
		);
		ModelHelper.setItemModel(
			ModItems.CINNABAR_AXE, () -> {
				ItemModelStandard model = new ItemModelStandard(ModItems.CINNABAR_AXE, TLOTD.MOD_ID).setFull3D();
				model.icon = TextureRegistry.getTexture(ModItems.CINNABAR_AXE.namespaceID);
				return model;
			}
		);
		ModelHelper.setItemModel(
			ModItems.CINNABAR_SHOVEL, () -> {
				ItemModelStandard model = new ItemModelStandard(ModItems.CINNABAR_SHOVEL, TLOTD.MOD_ID).setFull3D();
				model.icon = TextureRegistry.getTexture(ModItems.CINNABAR_SHOVEL.namespaceID);
				return model;
			}
		);
		ModelHelper.setItemModel(
			ModItems.CINNABAR_HOE, () -> {
				ItemModelStandard model = new ItemModelStandard(ModItems.CINNABAR_HOE, TLOTD.MOD_ID).setFull3D();
				model.icon = TextureRegistry.getTexture(ModItems.CINNABAR_HOE.namespaceID);
				return model;
			}
		);
		ModelHelper.setItemModel(
			ModItems.CINNABAR_PAXEL, () -> {
				ItemModelStandard model = new ItemModelStandard(ModItems.CINNABAR_PAXEL, TLOTD.MOD_ID).setFull3D();
				model.icon = TextureRegistry.getTexture(ModItems.CINNABAR_PAXEL.namespaceID);
				return model;
			}
		);
		ModelHelper.setItemModel(
			ModItems.CINNABAR_HELMET, () -> {
				ItemModelStandard model = new ItemModelStandard(ModItems.CINNABAR_HELMET, TLOTD.MOD_ID);
				model.icon = TextureRegistry.getTexture(ModItems.CINNABAR_HELMET.namespaceID);
				return model;
			}
		);
		ModelHelper.setItemModel(
			ModItems.CINNABAR_CHESTPLATE, () -> {
				ItemModelStandard model = new ItemModelStandard(ModItems.CINNABAR_CHESTPLATE, TLOTD.MOD_ID);
				model.icon = TextureRegistry.getTexture(ModItems.CINNABAR_CHESTPLATE.namespaceID);
				return model;
			}
		);
		ModelHelper.setItemModel(
			ModItems.CINNABAR_LEGGINGS, () -> {
				ItemModelStandard model = new ItemModelStandard(ModItems.CINNABAR_LEGGINGS, TLOTD.MOD_ID);
				model.icon = TextureRegistry.getTexture(ModItems.CINNABAR_LEGGINGS.namespaceID);
				return model;
			}
		);
		ModelHelper.setItemModel(
			ModItems.CINNABAR_BOOTS, () -> {
				ItemModelStandard model = new ItemModelStandard(ModItems.CINNABAR_BOOTS, TLOTD.MOD_ID);
				model.icon = TextureRegistry.getTexture(ModItems.CINNABAR_BOOTS.namespaceID);
				return model;
			}
		);
		ModelHelper.setItemModel(
			ModItems.NEBULAR_CRYSTAL, () -> {
				ItemModelStandard model = new ItemModelStandard(ModItems.NEBULAR_CRYSTAL, TLOTD.MOD_ID);
				model.icon = TextureRegistry.getTexture(ModItems.NEBULAR_CRYSTAL.namespaceID);
				return model;
			}
		);
		ModelHelper.setItemModel(
			ModItems.NEBULAR_NUGGET, () -> {
				ItemModelStandard model = new ItemModelStandard(ModItems.NEBULAR_NUGGET, TLOTD.MOD_ID);
				model.icon = TextureRegistry.getTexture(ModItems.NEBULAR_NUGGET.namespaceID);
				return model;
			}
		);
		ModelHelper.setItemModel(
			ModItems.NEBULAR_INGOT, () -> {
				ItemModelStandard model = new ItemModelStandard(ModItems.NEBULAR_INGOT, TLOTD.MOD_ID);
				model.icon = TextureRegistry.getTexture(ModItems.NEBULAR_INGOT.namespaceID);
				return model;
			}
		);
		ModelHelper.setItemModel(
			ModItems.NEBULAR_SWORD, () -> {
				ItemModelStandard model = new ItemModelStandard(ModItems.NEBULAR_SWORD, TLOTD.MOD_ID).setFull3D();
				model.icon = TextureRegistry.getTexture(ModItems.NEBULAR_SWORD.namespaceID);
				return model;
			}
		);
		ModelHelper.setItemModel(
			ModItems.NEBULAR_PICKAXE, () -> {
				ItemModelStandard model = new ItemModelStandard(ModItems.NEBULAR_PICKAXE, TLOTD.MOD_ID).setFull3D();
				model.icon = TextureRegistry.getTexture(ModItems.NEBULAR_PICKAXE.namespaceID);
				return model;
			}
		);
		ModelHelper.setItemModel(
			ModItems.NEBULAR_AXE, () -> {
				ItemModelStandard model = new ItemModelStandard(ModItems.NEBULAR_AXE, TLOTD.MOD_ID).setFull3D();
				model.icon = TextureRegistry.getTexture(ModItems.NEBULAR_AXE.namespaceID);
				return model;
			}
		);
		ModelHelper.setItemModel(
			ModItems.NEBULAR_SHOVEL, () -> {
				ItemModelStandard model = new ItemModelStandard(ModItems.NEBULAR_SHOVEL, TLOTD.MOD_ID).setFull3D();
				model.icon = TextureRegistry.getTexture(ModItems.NEBULAR_SHOVEL.namespaceID);
				return model;
			}
		);
		ModelHelper.setItemModel(
			ModItems.NEBULAR_HOE, () -> {
				ItemModelStandard model = new ItemModelStandard(ModItems.NEBULAR_HOE, TLOTD.MOD_ID).setFull3D();
				model.icon = TextureRegistry.getTexture(ModItems.NEBULAR_HOE.namespaceID);
				return model;
			}
		);
		ModelHelper.setItemModel(
			ModItems.NEBULAR_PAXEL, () -> {
				ItemModelStandard model = new ItemModelStandard(ModItems.NEBULAR_PAXEL, TLOTD.MOD_ID).setFull3D();
				model.icon = TextureRegistry.getTexture(ModItems.NEBULAR_PAXEL.namespaceID);
				return model;
			}
		);
		ModelHelper.setItemModel(
			ModItems.NEBULAR_HELMET, () -> {
				ItemModelStandard model = new ItemModelStandard(ModItems.NEBULAR_HELMET, TLOTD.MOD_ID);
				model.icon = TextureRegistry.getTexture(ModItems.NEBULAR_HELMET.namespaceID);
				return model;
			}
		);
		ModelHelper.setItemModel(
			ModItems.NEBULAR_CHESTPLATE, () -> {
				ItemModelStandard model = new ItemModelStandard(ModItems.NEBULAR_CHESTPLATE, TLOTD.MOD_ID);
				model.icon = TextureRegistry.getTexture(ModItems.NEBULAR_CHESTPLATE.namespaceID);
				return model;
			}
		);
		ModelHelper.setItemModel(
			ModItems.NEBULAR_LEGGINGS, () -> {
				ItemModelStandard model = new ItemModelStandard(ModItems.NEBULAR_LEGGINGS, TLOTD.MOD_ID);
				model.icon = TextureRegistry.getTexture(ModItems.NEBULAR_LEGGINGS.namespaceID);
				return model;
			}
		);
		ModelHelper.setItemModel(
			ModItems.NEBULAR_BOOTS, () -> {
				ItemModelStandard model = new ItemModelStandard(ModItems.NEBULAR_BOOTS, TLOTD.MOD_ID);
				model.icon = TextureRegistry.getTexture(ModItems.NEBULAR_BOOTS.namespaceID);
				return model;
			}
		);
		ModelHelper.setItemModel(
			ModItems.RAW_MITHRIL, () -> {
				ItemModelStandard model = new ItemModelStandard(ModItems.RAW_MITHRIL, TLOTD.MOD_ID);
				model.icon = TextureRegistry.getTexture(ModItems.RAW_MITHRIL.namespaceID);
				return model;
			}
		);
		ModelHelper.setItemModel(
			ModItems.MITHRIL_NUGGET, () -> {
				ItemModelStandard model = new ItemModelStandard(ModItems.MITHRIL_NUGGET, TLOTD.MOD_ID);
				model.icon = TextureRegistry.getTexture(ModItems.MITHRIL_NUGGET.namespaceID);
				return model;
			}
		);
		ModelHelper.setItemModel(
			ModItems.MITHRIL_INGOT, () -> {
				ItemModelStandard model = new ItemModelStandard(ModItems.MITHRIL_INGOT, TLOTD.MOD_ID);
				model.icon = TextureRegistry.getTexture(ModItems.MITHRIL_INGOT.namespaceID);
				return model;
			}
		);
		ModelHelper.setItemModel(
			ModItems.MITHRIL_SWORD, () -> {
				ItemModelStandard model = new ItemModelStandard(ModItems.MITHRIL_SWORD, TLOTD.MOD_ID).setFull3D();
				model.icon = TextureRegistry.getTexture(ModItems.MITHRIL_SWORD.namespaceID);
				return model;
			}
		);
		ModelHelper.setItemModel(
			ModItems.MITHRIL_PICKAXE, () -> {
				ItemModelStandard model = new ItemModelStandard(ModItems.MITHRIL_PICKAXE, TLOTD.MOD_ID).setFull3D();
				model.icon = TextureRegistry.getTexture(ModItems.MITHRIL_PICKAXE.namespaceID);
				return model;
			}
		);
		ModelHelper.setItemModel(
			ModItems.MITHRIL_AXE, () -> {
				ItemModelStandard model = new ItemModelStandard(ModItems.MITHRIL_AXE, TLOTD.MOD_ID).setFull3D();
				model.icon = TextureRegistry.getTexture(ModItems.MITHRIL_AXE.namespaceID);
				return model;
			}
		);
		ModelHelper.setItemModel(
			ModItems.MITHRIL_SHOVEL, () -> {
				ItemModelStandard model = new ItemModelStandard(ModItems.MITHRIL_SHOVEL, TLOTD.MOD_ID).setFull3D();
				model.icon = TextureRegistry.getTexture(ModItems.MITHRIL_SHOVEL.namespaceID);
				return model;
			}
		);
		ModelHelper.setItemModel(
			ModItems.MITHRIL_HOE, () -> {
				ItemModelStandard model = new ItemModelStandard(ModItems.MITHRIL_HOE, TLOTD.MOD_ID).setFull3D();
				model.icon = TextureRegistry.getTexture(ModItems.MITHRIL_HOE.namespaceID);
				return model;
			}
		);
		ModelHelper.setItemModel(
			ModItems.MITHRIL_PAXEL, () -> {
				ItemModelStandard model = new ItemModelStandard(ModItems.MITHRIL_PAXEL, TLOTD.MOD_ID).setFull3D();
				model.icon = TextureRegistry.getTexture(ModItems.MITHRIL_PAXEL.namespaceID);
				return model;
			}
		);
		ModelHelper.setItemModel(
			ModItems.NARSIL_HANDLE, () -> {
				ItemModelStandard model = new ItemModelStandard(ModItems.NARSIL_HANDLE, TLOTD.MOD_ID).setFull3D();
				model.icon = TextureRegistry.getTexture(ModItems.NARSIL_HANDLE.namespaceID);
				return model;
			}
		);
		ModelHelper.setItemModel(
			ModItems.ANDURIL, () -> {
				ItemModelStandard model = new ItemModelStandard(ModItems.ANDURIL, TLOTD.MOD_ID).setFull3D();
				model.icon = TextureRegistry.getTexture(ModItems.ANDURIL.namespaceID);
				return model;
			}
		);

		ModelHelper.setItemModel(
			ModItems.MITHRIL_HELMET, () -> {
				ItemModelStandard model = new ItemModelStandard(ModItems.MITHRIL_HELMET, TLOTD.MOD_ID);
				model.icon = TextureRegistry.getTexture(ModItems.MITHRIL_HELMET.namespaceID);
				return model;
			}
		);
		ModelHelper.setItemModel(
			ModItems.MITHRIL_CHESTPLATE, () -> {
				ItemModelStandard model = new ItemModelStandard(ModItems.MITHRIL_CHESTPLATE, TLOTD.MOD_ID);
				model.icon = TextureRegistry.getTexture(ModItems.MITHRIL_CHESTPLATE.namespaceID);
				return model;
			}
		);
		ModelHelper.setItemModel(
			ModItems.MITHRIL_LEGGINGS, () -> {
				ItemModelStandard model = new ItemModelStandard(ModItems.MITHRIL_LEGGINGS, TLOTD.MOD_ID);
				model.icon = TextureRegistry.getTexture(ModItems.MITHRIL_LEGGINGS.namespaceID);
				return model;
			}
		);
		ModelHelper.setItemModel(
			ModItems.MITHRIL_BOOTS, () -> {
				ItemModelStandard model = new ItemModelStandard(ModItems.MITHRIL_BOOTS, TLOTD.MOD_ID);
				model.icon = TextureRegistry.getTexture(ModItems.MITHRIL_BOOTS.namespaceID);
				return model;
			}
		);

		ModelHelper.setItemModel(
			ModItems.FLOUR, () -> {
				ItemModelStandard model = new ItemModelStandard(ModItems.FLOUR, TLOTD.MOD_ID);
				model.icon = TextureRegistry.getTexture(ModItems.FLOUR.namespaceID);
				return model;
			}
		);
		ModelHelper.setItemModel(
			ModItems.BREADCRUMBS, () -> {
				ItemModelStandard model = new ItemModelStandard(ModItems.BREADCRUMBS, TLOTD.MOD_ID);
				model.icon = TextureRegistry.getTexture(ModItems.BREADCRUMBS.namespaceID);
				return model;
			}
		);
		ModelHelper.setItemModel(
			ModItems.RAW_SCHNITZEL, () -> {
				ItemModelStandard model = new ItemModelStandard(ModItems.RAW_SCHNITZEL, TLOTD.MOD_ID);
				model.icon = TextureRegistry.getTexture(ModItems.RAW_SCHNITZEL.namespaceID);
				return model;
			}
		);
		ModelHelper.setItemModel(
			ModItems.SCHNITZEL, () -> {
				ItemModelStandard model = new ItemModelStandard(ModItems.SCHNITZEL, TLOTD.MOD_ID);
				model.icon = TextureRegistry.getTexture(ModItems.SCHNITZEL.namespaceID);
				return model;
			}
		);
		ModelHelper.setItemModel(
			ModItems.MAULTASCHE, () -> {
				ItemModelStandard model = new ItemModelStandard(ModItems.MAULTASCHE, TLOTD.MOD_ID);
				model.icon = TextureRegistry.getTexture(ModItems.MAULTASCHE.namespaceID);
				return model;
			}
		);
		ModelHelper.setItemModel(
			ModItems.MAULTASCHEN_BROTH, () -> {
				ItemModelStandard model = new ItemModelStandard(ModItems.MAULTASCHEN_BROTH, TLOTD.MOD_ID);
				model.icon = TextureRegistry.getTexture(ModItems.MAULTASCHEN_BROTH.namespaceID);
				return model;
			}
		);

		ModelHelper.setItemModel(
			ModItems.PRESERVES_JAR, () -> {
				ItemModelStandard model = new ItemModelStandard(ModItems.PRESERVES_JAR, TLOTD.MOD_ID);
				model.icon = TextureRegistry.getTexture(ModItems.PRESERVES_JAR.namespaceID);
				return model;
			}
		);
		ModelHelper.setItemModel(
			ModItems.CHERRY_JAM_JAR, () -> {
				ItemModelStandard model = new ItemModelStandard(ModItems.CHERRY_JAM_JAR, TLOTD.MOD_ID);
				model.icon = TextureRegistry.getTexture(ModItems.CHERRY_JAM_JAR.namespaceID);
				return model;
			}
		);
		ModelHelper.setItemModel(
			ModItems.TOAST, () -> {
				ItemModelStandard model = new ItemModelStandard(ModItems.TOAST, TLOTD.MOD_ID);
				model.icon = TextureRegistry.getTexture(ModItems.TOAST.namespaceID);
				return model;
			}
		);
		ModelHelper.setItemModel(
			ModItems.CHERRY_JAM_TOAST, () -> {
				ItemModelStandard model = new ItemModelStandard(ModItems.CHERRY_JAM_TOAST, TLOTD.MOD_ID);
				model.icon = TextureRegistry.getTexture(ModItems.CHERRY_JAM_TOAST.namespaceID);
				return model;
			}
		);
	}

	@Override
	public void initEntityModels(EntityRenderDispatcher entityRenderDispatcher) {

	}

	@Override
	public void initTileEntityModels(TileEntityRenderDispatcher tileEntityRenderDispatcher) {

	}

	@Override
	public void initBlockColors(BlockColorDispatcher blockColorDispatcher) {

	}
}
