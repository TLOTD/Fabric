package net.tlotd.bta.block.custom;

import net.minecraft.core.block.Block;
import net.minecraft.core.block.BlockLogic;
import net.minecraft.core.block.Blocks;
import net.minecraft.core.block.entity.TileEntity;
import net.minecraft.core.enums.EnumDropCause;
import net.minecraft.core.item.ItemStack;
import net.minecraft.core.world.World;
import org.jetbrains.annotations.NotNull;

public class BlockLogicOreBlock extends BlockLogic {
	public BlockLogicOreBlock(Block block) {
		super(block, Blocks.STONE.getMaterial());
	}

	public ItemStack[] getBreakResult(@NotNull World world, EnumDropCause dropCause, int meta, TileEntity tileEntity) {
		return switch (dropCause) {
			case SILK_TOUCH, PICK_BLOCK, PROPER_TOOL, EXPLOSION -> new ItemStack[]{new ItemStack(this)};
			default -> null;
		};
	}
}
