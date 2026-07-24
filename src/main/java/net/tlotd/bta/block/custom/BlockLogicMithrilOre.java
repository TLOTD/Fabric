package net.tlotd.bta.block.custom;

import it.unimi.dsi.fastutil.ints.Int2IntArrayMap;
import net.minecraft.core.block.Block;
import net.minecraft.core.block.BlockLogic;
import net.minecraft.core.block.entity.TileEntity;
import net.minecraft.core.block.material.Material;
import net.minecraft.core.enums.EnumDropCause;
import net.minecraft.core.item.ItemStack;
import net.minecraft.core.world.World;
import net.tlotd.bta.item.ModItems;

public class BlockLogicMithrilOre extends BlockLogic {
	public static final Int2IntArrayMap VARIANT_MAP = new Int2IntArrayMap();

	public BlockLogicMithrilOre(Block<?> block, Block<?> parentBlock, Material material) {
		super(block, material);
		VARIANT_MAP.put(parentBlock.id(), block.id());
	}

	public ItemStack[] getBreakResult(World world, EnumDropCause dropCause, int x, int y, int z, int meta, TileEntity tileEntity) {
		switch (dropCause) {
			case SILK_TOUCH:
			case PICK_BLOCK:
				return new ItemStack[]{new ItemStack(this)};
			case EXPLOSION:
			case PROPER_TOOL:
			case PISTON_CRUSH:
				return new ItemStack[]{new ItemStack(ModItems.RAW_MITHRIL)};
			default:
				return null;
		}
	}
}
