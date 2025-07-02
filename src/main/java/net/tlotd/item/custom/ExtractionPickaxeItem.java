package net.tlotd.item.custom;

import net.minecraft.core.block.Block;
import net.minecraft.core.block.Blocks;
import net.minecraft.core.entity.player.Player;
import net.minecraft.core.item.ItemStack;
import net.minecraft.core.item.material.ToolMaterial;
import net.minecraft.core.item.tool.ItemToolPickaxe;
import net.minecraft.core.util.helper.Side;
import net.minecraft.core.world.World;
import net.tlotd.block.ModBlocks;
import net.tlotd.item.ModItems;

public class ExtractionPickaxeItem extends ItemToolPickaxe {
	public ExtractionPickaxeItem(String name, String namespaceId, int id, ToolMaterial enumtoolmaterial) {
		super(name, namespaceId, id, enumtoolmaterial);
	}

	@Override
	public boolean onUseItemOnBlock(ItemStack itemstack, Player entityplayer, World world, int blockX, int blockY, int blockZ, Side side, double xPlaced, double yPlaced) {
		Block<?> block = world.getBlock(blockX, blockY, blockZ);
		if (!world.isClientSide){
			if (block == ModBlocks.MITHRIL_BEDROCK_ORE) {
				world.dropItem(blockX,blockY,blockZ, ModItems.RAW_MITHRIL.getDefaultStack());
				world.setBlock(blockX,blockY,blockZ,260);
				world.markBlockDirty(blockX,blockY,blockZ);
				itemstack.damageItem(1,entityplayer);
				return true;
			}
		}
		return false;
	}
}
