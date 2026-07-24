package net.tlotd.bta.item.custom;

import net.minecraft.core.block.Block;
import net.minecraft.core.entity.Mob;
import net.minecraft.core.item.ItemStack;
import net.minecraft.core.item.material.ToolMaterial;
import net.minecraft.core.item.tool.ItemTool;
import net.tlotd.bta.block.tag.ModTags;
import org.jetbrains.annotations.NotNull;

import static net.minecraft.core.item.tool.ItemToolPickaxe.miningLevels;

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
}
