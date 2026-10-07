package net.tlotd.bta.compat.btwaila;

import net.minecraft.client.render.texture.stitcher.TextureRegistry;
import net.tlotd.bta.block.custom.entity.TileEntityDwarvenForge;
import toufoumaster.btwaila.gui.components.AdvancedInfoComponent;
import toufoumaster.btwaila.tooltips.TileTooltip;
import toufoumaster.btwaila.util.ProgressBarOptions;
import toufoumaster.btwaila.util.TextureOptions;

public class DwarvenForgeTooltip<T> extends TileTooltip<TileEntityDwarvenForge> {
	@Override
	public void initTooltip() {
		this.addClass(TileEntityDwarvenForge.class);
	}

	@Override
	public void drawAdvancedTooltip(TileEntityDwarvenForge tile, AdvancedInfoComponent c) {
		String status = "Idle";
		String texture = "minecraft:block/sand";
		String bgTexture = "minecraft:block/sand";
		if (tile.getCurrentMode() == TileEntityDwarvenForge.ForgeMode.BLAST_FURNACE) {
			status = "Hellfire Blasting";
			texture = "minecraft:block/block_nethercoal";
			bgTexture = "minecraft:block/block_nethercoal";
		} else if (tile.getCurrentMode() == TileEntityDwarvenForge.ForgeMode.FURNACE) {
			status = "Smelting";
			texture = "minecraft:block/fire";
		}
		c.drawStringWithShadow("", 0);
		c.drawStringWithShadow("Status: " + status, 0);
		ProgressBarOptions progress = new ProgressBarOptions().setForegroundOptions(new TextureOptions(0xFFFFFF, TextureRegistry.getTexture(texture))).setBackgroundOptions(new TextureOptions(0x222222, TextureRegistry.getTexture(bgTexture))).setText("Progress: ");
		c.drawProgressBarTextureWithText(tile.currentCookTime, tile.maxCookTime, progress, 0);
		c.drawStringWithShadow("Burn time: " + tile.currentBurnTime + "t", 0);
		c.drawItemList(tile.inventory, 0);
	}
}
