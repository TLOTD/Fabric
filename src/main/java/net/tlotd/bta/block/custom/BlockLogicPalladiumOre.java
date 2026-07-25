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
import org.jetbrains.annotations.NotNull;

public class BlockLogicPalladiumOre extends BlockLogic {
	public static final Int2IntArrayMap VARIANT_MAP = new Int2IntArrayMap();

	public BlockLogicPalladiumOre(Block<?> block, Block<?> parentBlock, Material material) {
		super(block, material);
		VARIANT_MAP.put(parentBlock.id(), block.id());
	}

	public ItemStack[] getBreakResult(@NotNull World world, EnumDropCause dropCause, int meta, TileEntity tileEntity) {
		return switch (dropCause) {
			case SILK_TOUCH, PICK_BLOCK -> new ItemStack[]{new ItemStack(this)};
			case EXPLOSION, PROPER_TOOL, PISTON_CRUSH -> new ItemStack[]{new ItemStack(ModItems.RAW_PALLADIUM)};
			default -> null;
		};
	}
}
