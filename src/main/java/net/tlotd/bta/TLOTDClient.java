package net.tlotd.bta;

import net.fabricmc.api.ClientModInitializer;
import net.tlotd.bta.block.custom.entity.TileEntityDwarvenForge;
import net.tlotd.bta.datagen.ModModelProvider;
import net.tlotd.bta.gui.MenuDwarvenForge;
import net.tlotd.bta.gui.ScreenDwarvenForge;
import sunsetsatellite.catalyst.Catalyst;
import sunsetsatellite.catalyst.core.util.mp.entry.TileGuiEntry;
import turniplabs.halplibe.event.defs.ClientEvents;
import turniplabs.halplibe.util.dependency.Key;

public class TLOTDClient implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
		ClientEvents.BLOCK_MODEL_RELOAD.listen(Key.of(TLOTD.MOD_ID), (d) -> new ModModelProvider().initBlockModels(d));
		ClientEvents.ITEM_MODEL_RELOAD.listen(Key.of(TLOTD.MOD_ID), (d) -> new ModModelProvider().initItemModels(d));
		Catalyst.GUIS.register(
			TLOTD.MOD_ID + ":gui/dwarven_forge",
			new TileGuiEntry<>(
				TileEntityDwarvenForge.class,
				MenuDwarvenForge.class,
				ScreenDwarvenForge::new
			)
		);
	}
}
