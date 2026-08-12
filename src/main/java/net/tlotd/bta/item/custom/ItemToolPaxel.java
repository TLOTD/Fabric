package net.tlotd.bta.item.custom;

import net.minecraft.core.block.Block;
import net.minecraft.core.entity.Mob;
import net.minecraft.core.entity.player.Player;
import net.minecraft.core.item.ItemStack;
import net.minecraft.core.item.material.ToolMaterial;
import net.minecraft.core.item.tool.ItemTool;
import net.minecraft.core.util.helper.Side;
import net.minecraft.core.world.World;
import net.minecraft.core.world.pos.TilePosc;
import net.tlotd.bta.block.tag.ModTags;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import static net.minecraft.core.item.tool.ItemToolPickaxe.miningLevels;
import static net.tlotd.bta.item.custom.ExtractionPickaxeItem.oreExtraction;

public class ItemToolPaxel extends ItemTool {
	public ItemToolPaxel(String name, String namespaceId, int id, ToolMaterial enumtoolmaterial) {
		super(name, namespaceId, id, 2, enumtoolmaterial, ModTags.MINEABLE_BY_PAXEL);
	}

	@Override
	public float getStrVsBlock(ItemStack itemstack, Block<?> block) {
		return this.material.getEfficiency(false);
	}

	public boolean canHarvestBlock(@NotNull ItemStack selfStack, @NotNull Mob mob, @NotNull Block<?> block) {
		int miningLevel = miningLevels.getOrDefault(block, -1);
		if (miningLevel != -1) {
			return this.material.getMiningLevel() >= miningLevel;
		} else {
			return true;
		}
	}

	@Override
	public boolean onUseOnBlock(@NotNull ItemStack itemstack, @NotNull World world, @Nullable Player player, @NotNull TilePosc blockPos, @NotNull Side side, double xHit, double yHit) {
		return oreExtraction(itemstack, world, player, blockPos);
	}
}
