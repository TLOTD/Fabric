package net.tlotd.block.custom;

import net.minecraft.core.block.Block;
import net.minecraft.core.block.BlockLogic;
import net.minecraft.core.block.entity.TileEntity;
import net.minecraft.core.block.material.Material;
import net.minecraft.core.enums.EnumDropCause;
import net.minecraft.core.item.ItemStack;
import net.minecraft.core.world.World;
import net.tlotd.block.ModBlocks;

public class BlockLogicRedDeepslate extends BlockLogic {
	public BlockLogicRedDeepslate(Block block) {
		super(block, Material.stone);
	}

	public ItemStack[] getBreakResult(World world, EnumDropCause dropCause, int x, int y, int z, int meta, TileEntity tileEntity) {
		switch (dropCause) {
			case SILK_TOUCH:
			case PICK_BLOCK:
				return new ItemStack[]{new ItemStack(this)};
			case EXPLOSION:
			case PROPER_TOOL:
			case PISTON_CRUSH:
				return new ItemStack[]{new ItemStack(ModBlocks.COBBLED_RED_DEEPSLATE)};
			default:
				return null;
		}
	}
}
