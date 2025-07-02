package net.tlotd;

import net.fabricmc.api.ModInitializer;
import net.tlotd.block.ModBlocks;
import net.tlotd.item.ModItems;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import turniplabs.halplibe.util.GameStartEntrypoint;
import turniplabs.halplibe.util.RecipeEntrypoint;
import turniplabs.halplibe.util.TomlConfigHandler;
import turniplabs.halplibe.util.toml.Toml;

public class TLOTD implements ModInitializer, RecipeEntrypoint, GameStartEntrypoint {
    public static final String MOD_ID = "tlotd";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	private static final Toml TOML = new Toml("1 is enabled, 0 is disabled");
	public static final TomlConfigHandler CFG;
	static {
		TOML.addCategory("IDs")
			.addEntry("starting_item_id", 19000)
			.addEntry("starting_block_id", 11000);

		CFG = new TomlConfigHandler(TLOTD.MOD_ID, TOML);
	}

	@Override
    public void onInitialize() {
        LOGGER.info("TLOTD initialized.");
		new ModItems().registerItems();
		new ModBlocks().registerBlocks();
    }

	@Override
	public void onRecipesReady() {

	}

	@Override
	public void initNamespaces() {

	}

	@Override
	public void beforeGameStart() {

	}

	@Override
	public void afterGameStart() {

	}
}
