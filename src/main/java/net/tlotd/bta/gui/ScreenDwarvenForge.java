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

	public ScreenDwarvenForge(
		ContainerInventory inventory,
		TileEntityDwarvenForge tile
	) {
		super(new MenuDwarvenForge(inventory, tile));
		this.tile = tile;
		this.xSize = 176;
		this.ySize = 185;
	}

	@Override
	protected void drawGuiContainerForegroundLayer() {
		drawStringNoShadow(
			fontRenderer,
			"Dwarven Forge",
			8,
			6,
			4210752
		);

		drawStringNoShadow(
			fontRenderer,
			I18n.getInstance().translateKey("gui.crafting.label.inventory"),
			8,
			this.ySize - 96 + 2,
			4210752
		);
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float f) {
		@NotNull Texture tex =
			this.mc.textureManager.loadTexture(
				"/assets/tlotd/textures/gui/dwarven_forge.png"
			);

		GLRenderer.setColor4f(
			1.0F,
			1.0F,
			1.0F,
			1.0F
		);

		this.mc.textureManager.bindTexture(tex);

		int j = (this.width - this.xSize) / 2;
		int k = (this.height - this.ySize) / 2;

		this.drawTexturedModalRect(
			j,
			k,
			0,
			0,
			this.xSize,
			this.ySize
		);
	}

	@Override
	public boolean isPauseScreen() {
		return false;
	}
}
