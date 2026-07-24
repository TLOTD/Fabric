package net.tlotd.bta.block.custom;

import net.minecraft.core.block.Block;
import net.minecraft.core.block.BlockLogic;
import net.minecraft.core.block.Blocks;
import net.minecraft.core.block.entity.TileEntity;
import net.minecraft.core.enums.EnumDropCause;
import net.minecraft.core.item.ItemStack;
import net.minecraft.core.world.World;

public class BlockLogicOreBlock extends BlockLogic {
	public BlockLogicOreBlock(Block block) {
		super(block, Blocks.STONE.getMaterial());
	}

	public ItemStack[] getBreakResult(World world, EnumDropCause dropCause, int x, int y, int z, int meta, TileEntity tileEntity) {
		switch (dropCause) {
			case SILK_TOUCH:
			case PICK_BLOCK:
			case PROPER_TOOL:
			case EXPLOSION:
				return new ItemStack[]{new ItemStack(this)};
			default:
				return null;
		}
	}
}
