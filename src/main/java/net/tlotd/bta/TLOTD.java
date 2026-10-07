package net.tlotd.bta;

import net.fabricmc.api.ModInitializer;
import net.minecraft.core.block.entity.TileEntityDispatcher;
import net.minecraft.core.util.collection.NamespaceID;
import net.tlotd.bta.block.ModBlocks;
import net.tlotd.bta.block.custom.entity.TileEntityDwarvenForge;
import net.tlotd.bta.tag.ModTags;
import net.tlotd.bta.datagen.ModRecipeProvider;
import net.tlotd.bta.gui.MenuDwarvenForge;
import net.tlotd.bta.item.ModItems;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sunsetsatellite.catalyst.Catalyst;
import sunsetsatellite.catalyst.core.util.mp.GuiEntry;
import turniplabs.halplibe.HalpLibe;
import turniplabs.halplibe.event.defs.CommonEvents;
import turniplabs.halplibe.util.TomlConfigHandler;
import turniplabs.halplibe.util.dependency.Key;
import turniplabs.halplibe.util.toml.Toml;

public class TLOTD implements ModInitializer {
	public static final String MOD_ID = HalpLibe.registerMod("tlotd", true);
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	private static final Toml TOML = new Toml("IDs can be changed below if you are planning to play with multiple mods! | 1 is enabled, 0 is disabled");
	public static final TomlConfigHandler CFG;

	static {
		TOML.addCategory("IDs").addEntry("starting_item_id", 19010).addEntry("starting_block_id", 11000);
		CFG = new TomlConfigHandler(TLOTD.MOD_ID, TOML);
	}

	@Override
	public void onInitialize() {
		CommonEvents.BEFORE_GAME_START.listen(Key.of(MOD_ID), this::beforeGameStart);
		CommonEvents.AFTER_GAME_START.listen(Key.of(MOD_ID), this::afterGameStart);
		CommonEvents.AFTER_BLOCK_INIT.listen(Key.of(MOD_ID), () -> new ModBlocks().registerBlocks());
		CommonEvents.AFTER_ITEM_INIT.listen(Key.of(MOD_ID), () -> new ModItems().registerItems());
		CommonEvents.AFTER_ITEM_INIT.listen(Key.of(MOD_ID), () -> new ModTags().registerTags());
		CommonEvents.RECIPES_NAMESPACE_INIT.listen(Key.of(MOD_ID), () -> new ModRecipeProvider().initNamespaces());
		CommonEvents.RECIPES_READY.listen(Key.of(MOD_ID), () -> new ModRecipeProvider().onRecipesReady());
		LOGGER.info("TLOTD BTA! initialized.");
		TileEntityDispatcher.addMapping(TileEntityDwarvenForge.class, NamespaceID.fromPool(MOD_ID, "dwarven_forge"));
		Catalyst.GUIS.register(TLOTD.MOD_ID + ":gui/dwarven_forge", new GuiEntry<>(TileEntityDwarvenForge.class, MenuDwarvenForge.class));
	}

	public void beforeGameStart() {}

	public void afterGameStart() {
		new ModBlocks().initializeBlockDetails();
	}
}
