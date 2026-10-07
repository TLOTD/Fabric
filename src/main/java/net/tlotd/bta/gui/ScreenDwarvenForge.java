package net.tlotd.bta.gui;

import net.minecraft.client.gui.container.ScreenContainerAbstract;
import net.minecraft.client.render.renderer.GLRenderer;
import net.minecraft.client.render.texture.Texture;
import net.minecraft.core.lang.I18n;
import net.minecraft.core.player.inventory.container.ContainerInventory;
import net.tlotd.bta.block.custom.entity.TileEntityDwarvenForge;
import org.jetbrains.annotations.NotNull;

public class ScreenDwarvenForge extends ScreenContainerAbstract {

	public TileEntityDwarvenForge tile;

	public ScreenDwarvenForge(ContainerInventory inventory, TileEntityDwarvenForge tile) {
		super(new MenuDwarvenForge(inventory, tile));
		this.tile = tile;
		this.xSize = 176;
		this.ySize = 185;
	}

	@Override
	protected void drawGuiContainerForegroundLayer() {
		drawStringCenteredNoShadow(fontRenderer, "Dwarven Forge", this.xSize / 2, 6, 4210752);
		drawStringNoShadow(fontRenderer, I18n.getInstance().translateKey("gui.crafting.label.inventory"), 8, this.ySize - 96 + 2, 4210752);
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float f) {
		@NotNull Texture tex = this.mc.textureManager.loadTexture("/assets/tlotd/textures/gui/container/dwarven_forge.png");
		GLRenderer.setColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		this.mc.textureManager.bindTexture(tex);
		int x = (this.width - this.xSize) / 2;
		int y = (this.height - this.ySize) / 2;
		this.drawTexturedModalRect(x, y, 0, 0, this.xSize, this.ySize);
		if (this.tile.isBurning()) {
			int fireHeight = this.tile.getBurnTimeRemainingScaled(12);
			int fireTexturePos = 12;
			if (this.tile.getCurrentMode() == TileEntityDwarvenForge.ForgeMode.BLAST_FURNACE) {
				fireTexturePos = 26;
			}
			this.drawTexturedModalRect(x + 76, y + 46 + 12 - fireHeight, 183, fireTexturePos - fireHeight, 14, fireHeight + 2);
			int arrowWidth = this.tile.getCookProgressScaled(24);
			this.drawTexturedModalRect(x + 101, y + 45, 197, 0, arrowWidth + 1, 15);
		}
	}

	@Override
	public boolean isPauseScreen() {
		return false;
	}
}
