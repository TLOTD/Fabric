package net.tlotd.bta.block.custom;

import net.minecraft.core.block.Block;
import net.minecraft.core.block.BlockLogic;
import net.minecraft.core.block.Blocks;
import net.minecraft.core.block.entity.TileEntity;
import net.minecraft.core.enums.EnumDropCause;
import net.minecraft.core.item.ItemStack;
import net.minecraft.core.world.World;
import net.tlotd.bta.block.ModBlocks;
import org.jetbrains.annotations.NotNull;

public class BlockLogicRedDeepslate extends BlockLogic {
	public BlockLogicRedDeepslate(Block block) {
		super(block, Blocks.SLATE.getMaterial());
	}

	public ItemStack[] getBreakResult(@NotNull World world, EnumDropCause dropCause, int meta, TileEntity tileEntity) {
		return switch (dropCause) {
			case SILK_TOUCH, PICK_BLOCK -> new ItemStack[]{new ItemStack(this)};
			case EXPLOSION, PROPER_TOOL, PISTON_CRUSH ->
				new ItemStack[]{new ItemStack(ModBlocks.COBBLED_RED_DEEPSLATE)};
			default -> null;
		};
	}
}
