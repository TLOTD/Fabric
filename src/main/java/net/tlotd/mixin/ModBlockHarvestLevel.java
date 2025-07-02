package net.tlotd.mixin;

import net.minecraft.core.item.tool.ItemToolPickaxe;
import net.tlotd.block.ModBlocks;
import org.spongepowered.asm.mixin.Mixin;

import static net.minecraft.core.item.tool.ItemToolPickaxe.miningLevels;

@Mixin(ItemToolPickaxe.class)
public abstract class ModBlockHarvestLevel {
	static {
		miningLevels.put(ModBlocks.STONE_FOSSIL, 2);
		miningLevels.put(ModBlocks.BASALT_FOSSIL, 2);
		miningLevels.put(ModBlocks.LIMESTONE_FOSSIL, 2);
		miningLevels.put(ModBlocks.GRANITE_FOSSIL, 2);
		miningLevels.put(ModBlocks.PERMAFROST_FOSSIL, 2);

		miningLevels.put(ModBlocks.HELIORITE_STONE_ORE, 3);
		miningLevels.put(ModBlocks.HELIORITE_BASALT_ORE, 3);
		miningLevels.put(ModBlocks.HELIORITE_LIMESTONE_ORE, 3);
		miningLevels.put(ModBlocks.HELIORITE_GRANITE_ORE, 3);
		miningLevels.put(ModBlocks.HELIORITE_PERMAFROST_ORE, 3);
		miningLevels.put(ModBlocks.HELIORITE_COMB_BLOCK, 3);
		miningLevels.put(ModBlocks.HELIORITE_BLOCK, 3);

		miningLevels.put(ModBlocks.ENDURIUM_STONE_ORE, 3);
		miningLevels.put(ModBlocks.ENDURIUM_BASALT_ORE, 3);
		miningLevels.put(ModBlocks.ENDURIUM_LIMESTONE_ORE, 3);
		miningLevels.put(ModBlocks.ENDURIUM_GRANITE_ORE, 3);
		miningLevels.put(ModBlocks.ENDURIUM_PERMAFROST_ORE, 3);
		miningLevels.put(ModBlocks.RAW_ENDURIUM_BLOCK, 3);
		miningLevels.put(ModBlocks.ENDURIUM_BLOCK, 3);

		miningLevels.put(ModBlocks.PALLADIUM_STONE_ORE, 4);
		miningLevels.put(ModBlocks.PALLADIUM_BASALT_ORE, 4);
		miningLevels.put(ModBlocks.PALLADIUM_LIMESTONE_ORE, 4);
		miningLevels.put(ModBlocks.PALLADIUM_GRANITE_ORE, 4);
		miningLevels.put(ModBlocks.PALLADIUM_PERMAFROST_ORE, 4);
		miningLevels.put(ModBlocks.RAW_PALLADIUM_BLOCK, 4);
		miningLevels.put(ModBlocks.PALLADIUM_BLOCK, 4);

		miningLevels.put(ModBlocks.JURASSOLINE_STONE_ORE, 4);
		miningLevels.put(ModBlocks.JURASSOLINE_BASALT_ORE, 4);
		miningLevels.put(ModBlocks.JURASSOLINE_LIMESTONE_ORE, 4);
		miningLevels.put(ModBlocks.JURASSOLINE_GRANITE_ORE, 4);
		miningLevels.put(ModBlocks.JURASSOLINE_PERMAFROST_ORE, 4);
		miningLevels.put(ModBlocks.JURASSOLINE_CRYSTAL_BLOCK, 4);
		miningLevels.put(ModBlocks.JURASSOLINE_BLOCK, 4);

		miningLevels.put(ModBlocks.CINNABAR_STONE_ORE, 5);
		miningLevels.put(ModBlocks.CINNABAR_BASALT_ORE, 5);
		miningLevels.put(ModBlocks.CINNABAR_LIMESTONE_ORE, 5);
		miningLevels.put(ModBlocks.CINNABAR_GRANITE_ORE, 5);
		miningLevels.put(ModBlocks.CINNABAR_PERMAFROST_ORE, 5);
		miningLevels.put(ModBlocks.CINNABAR_CRYSTAL_BLOCK, 5);
		miningLevels.put(ModBlocks.CINNABAR_BLOCK, 5);

		miningLevels.put(ModBlocks.NEBULAR_STONE_ORE, 5);
		miningLevels.put(ModBlocks.NEBULAR_BASALT_ORE, 5);
		miningLevels.put(ModBlocks.NEBULAR_LIMESTONE_ORE, 5);
		miningLevels.put(ModBlocks.NEBULAR_GRANITE_ORE, 5);
		miningLevels.put(ModBlocks.NEBULAR_PERMAFROST_ORE, 5);
		miningLevels.put(ModBlocks.RAW_NEBULAR_BLOCK, 5);
		miningLevels.put(ModBlocks.NEBULAR_BLOCK, 5);

		miningLevels.put(ModBlocks.MITHRIL_STONE_ORE, 6);
		miningLevels.put(ModBlocks.MITHRIL_BASALT_ORE, 6);
		miningLevels.put(ModBlocks.MITHRIL_LIMESTONE_ORE, 6);
		miningLevels.put(ModBlocks.MITHRIL_GRANITE_ORE, 6);
		miningLevels.put(ModBlocks.MITHRIL_PERMAFROST_ORE, 6);
		miningLevels.put(ModBlocks.MITHRIL_BEDROCK_ORE, 6);
		miningLevels.put(ModBlocks.RAW_MITHRIL_BLOCK, 6);
		miningLevels.put(ModBlocks.MITHRIL_BLOCK, 6);
	}
}
