package net.tlotd.bta;

import net.fabricmc.api.ClientModInitializer;
import net.tlotd.bta.datagen.ModModelProvider;
import turniplabs.halplibe.event.defs.ClientEvents;
import turniplabs.halplibe.util.dependency.Key;

public class TLOTDClient implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
		ClientEvents.BLOCK_MODEL_RELOAD.listen(Key.of(TLOTD.MOD_ID), (d) -> new ModModelProvider().initBlockModels(d));
		ClientEvents.ITEM_MODEL_RELOAD.listen(Key.of(TLOTD.MOD_ID), (d) -> new ModModelProvider().initItemModels(d));
	}
}
