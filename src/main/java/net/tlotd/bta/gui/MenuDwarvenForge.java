package net.tlotd.bta.gui;

import it.unimi.dsi.fastutil.ints.IntArrayList;
import it.unimi.dsi.fastutil.ints.IntList;
import net.minecraft.core.InventoryAction;
import net.minecraft.core.entity.player.Player;
import net.minecraft.core.player.inventory.container.Container;
import net.minecraft.core.player.inventory.menu.MenuAbstract;
import net.minecraft.core.player.inventory.slot.Slot;
import net.tlotd.bta.block.custom.entity.TileEntityDwarvenForge;
import org.jetbrains.annotations.NotNull;

public class MenuDwarvenForge extends MenuAbstract {

	public final TileEntityDwarvenForge tile;

	public MenuDwarvenForge(Container inventory, TileEntityDwarvenForge tile) {
		this.tile = tile;
		this.addSlot(new Slot(tile, 0, 22, 72));
		this.addSlot(new Slot(tile, 1, 22, 54));
		this.addSlot(new Slot(tile, 2, 22, 36));
		this.addSlot(new Slot(tile, 3, 22, 18));

		this.addSlot(new Slot(tile, 4, 57, 62));
		this.addSlot(new Slot(tile, 5, 75, 63));
		this.addSlot(new Slot(tile, 6, 93, 62));

		this.addSlot(new Slot(tile, 7, 75, 27));
		this.addSlot(new Slot(tile, 8, 93, 28));
		this.addSlot(new Slot(tile, 9, 57, 28));

		this.addSlot(new Slot(tile, 10, 134, 45));

		for (int row = 0; row < 3; ++row) {
			for (int col = 0; col < 9; ++col) {
				this.addSlot(new Slot(inventory, col + row * 9 + 9, 8 + col * 18, 103 + row * 18));
			}
		}

		for (int col = 0; col < 9; ++col) {
			this.addSlot(new Slot(inventory, col, 8 + col * 18, 161));
		}
	}

	@Override
	public IntList getMoveSlots(@NotNull InventoryAction inventoryAction, @NotNull Slot slot, int i, Player player) {
		return new IntArrayList();
	}

	@Override
	public IntList getTargetSlots(@NotNull InventoryAction inventoryAction, @NotNull Slot slot, int i, Player player) {
		return new IntArrayList();
	}

	@Override
	public boolean stillValid(@NotNull Player player) {
		return tile.stillValid(player);
	}
}
