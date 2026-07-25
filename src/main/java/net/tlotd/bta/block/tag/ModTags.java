package net.tlotd.bta.block.tag;

import net.minecraft.core.block.Block;
import net.minecraft.core.block.tag.BlockTags;
import net.minecraft.core.data.registry.Registries;
import net.minecraft.core.data.tag.Tag;
import net.tlotd.bta.block.ModBlocks;

import java.util.Objects;

import static net.minecraft.core.data.registry.Registries.stackListOf;

public class ModTags {

	public static final Tag<Block<?>> MINEABLE_BY_PAXEL = Tag.of("mineable_by_paxel");

	public void registerTags (){
		BlockTags.TAG_LIST.add(MINEABLE_BY_PAXEL);
		Registries.ITEM_GROUPS.register("tlotd:fossil_ores", stackListOf(ModBlocks.STONE_FOSSIL, ModBlocks.BASALT_FOSSIL, ModBlocks.LIMESTONE_FOSSIL, ModBlocks.GRANITE_FOSSIL, ModBlocks.PERMAFROST_FOSSIL, ModBlocks.RED_DEEPSLATE_FOSSIL));
		Registries.ITEM_GROUPS.register("tlotd:heliorite_ores", stackListOf(ModBlocks.HELIORITE_STONE_ORE, ModBlocks.HELIORITE_BASALT_ORE, ModBlocks.HELIORITE_LIMESTONE_ORE, ModBlocks.HELIORITE_GRANITE_ORE, ModBlocks.HELIORITE_PERMAFROST_ORE, ModBlocks.HELIORITE_RED_DEEPSLATE_ORE));
		Registries.ITEM_GROUPS.register("tlotd:endurium_ores", stackListOf(ModBlocks.ENDURIUM_STONE_ORE, ModBlocks.ENDURIUM_BASALT_ORE, ModBlocks.ENDURIUM_LIMESTONE_ORE, ModBlocks.ENDURIUM_GRANITE_ORE, ModBlocks.ENDURIUM_PERMAFROST_ORE, ModBlocks.ENDURIUM_RED_DEEPSLATE_ORE));
		Registries.ITEM_GROUPS.register("tlotd:palladium_ores", stackListOf(ModBlocks.PALLADIUM_STONE_ORE, ModBlocks.PALLADIUM_BASALT_ORE, ModBlocks.PALLADIUM_LIMESTONE_ORE, ModBlocks.PALLADIUM_GRANITE_ORE, ModBlocks.PALLADIUM_PERMAFROST_ORE, ModBlocks.PALLADIUM_RED_DEEPSLATE_ORE));
		Registries.ITEM_GROUPS.register("tlotd:jurassoline_ores", stackListOf(ModBlocks.JURASSOLINE_STONE_ORE, ModBlocks.JURASSOLINE_BASALT_ORE, ModBlocks.JURASSOLINE_LIMESTONE_ORE, ModBlocks.JURASSOLINE_GRANITE_ORE, ModBlocks.JURASSOLINE_PERMAFROST_ORE, ModBlocks.JURASSOLINE_RED_DEEPSLATE_ORE));
		Registries.ITEM_GROUPS.register("tlotd:cinnabar_ores", stackListOf(ModBlocks.CINNABAR_STONE_ORE, ModBlocks.CINNABAR_BASALT_ORE, ModBlocks.CINNABAR_LIMESTONE_ORE, ModBlocks.CINNABAR_GRANITE_ORE, ModBlocks.CINNABAR_PERMAFROST_ORE, ModBlocks.CINNABAR_RED_DEEPSLATE_ORE));
		Registries.ITEM_GROUPS.register("tlotd:nebular_ores", stackListOf(ModBlocks.NEBULAR_STONE_ORE, ModBlocks.NEBULAR_BASALT_ORE, ModBlocks.NEBULAR_LIMESTONE_ORE, ModBlocks.NEBULAR_GRANITE_ORE, ModBlocks.NEBULAR_PERMAFROST_ORE, ModBlocks.NEBULAR_RED_DEEPSLATE_ORE));
		Registries.ITEM_GROUPS.register("tlotd:mithril_ores", stackListOf(ModBlocks.MITHRIL_STONE_ORE, ModBlocks.MITHRIL_BASALT_ORE, ModBlocks.MITHRIL_LIMESTONE_ORE, ModBlocks.MITHRIL_GRANITE_ORE, ModBlocks.MITHRIL_PERMAFROST_ORE, ModBlocks.MITHRIL_RED_DEEPSLATE_ORE));
	}
}
