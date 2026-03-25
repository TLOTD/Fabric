package net.tlotd;

import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.fabricmc.fabric.api.registry.StrippableBlockRegistry;
import net.kyrptonaught.customportalapi.CustomPortalBlock;
import net.kyrptonaught.customportalapi.api.CustomPortalBuilder;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.tlotd.banner.ModBanners;
import net.tlotd.block.ModFlammableBlocks;
import net.tlotd.block.ModUseBlockCallback;
import net.tlotd.block.entity.ModBlockEntities;
import net.tlotd.config.ModConfigs;
import net.tlotd.effect.ModEffects;
import net.tlotd.effect.ModPotions;
import net.tlotd.enchantments.ModEnchantments;
import net.tlotd.entity.custom.*;
import net.tlotd.util.*;
import net.tlotd.entity.*;
import net.tlotd.fluid.ModFluids;
import net.tlotd.gui.ModGUIHandlers;
import net.tlotd.item.*;
import net.tlotd.block.ModBlocks;
import net.tlotd.networking.ModMessages;
import net.tlotd.networking.JoinDataSync;
import net.tlotd.painting.ModPaintings;
import net.tlotd.recipe.ModRecipies;
import net.tlotd.sound.ModSounds;
import net.tlotd.tick.ModServerTickEvents;
import net.tlotd.villager.ModVillagers;
import net.tlotd.world.ModChunkEvents;
import net.tlotd.world.dimension.BackroomsChunkGenerator;
import net.tlotd.world.dimension.LunarChunkGenerator;
import net.tlotd.world.dimension.PrehistoricChunkGenerator;
import net.tlotd.world.gen.ModWorldGeneration;
import net.tlotd.world.village.ModVillageAdditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TLOTD implements ModInitializer {
	public static final String MOD_ID = "tlotd";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		ModConfigs.registerConfigs();
		ModTelevisionSignals.registerSignals();
		ModEffects.registerEffects();
		ModPotions.registerPotions();
		ModItemGroups.registerItemGroups();
		ModItems.registerModItems();
		ModCompostingChances.registerCompostableItems();
		ModBlocks.registerModBlocks();
		ModFlammableBlocks.registerFlammableBlocks();
		ModFluids.registerModFluids();
		ModFuels.registerModFuels();
		BrewingRecipeBuilder.registerBrewingRecipes();
		ModLootTableModifiers.modifyLootTables();
		ModBlockEntities.registerBlockEntities();
		ModGUIHandlers.registerGUIHandlers();
		ModRecipies.registerRecipes();
		ModVillagers.registerVillagers();
		ModTrades.registerTrades();
		ModBoats.registerBoats();
		ModPaintings.registerPaintings();
		ModBanners.registerBanners();
		ModEnchantments.registerEnchants();
		ModEntities.registerModEntities();
		ModSounds.registerSounds();
		ModMessages.registerC2SPackets();
		ModVillageAdditions.registerNewVillageStructures();
		ModWorldGeneration.generateModWorldGen();
		DamageMitigation.registerAllowedDamages();
		ModServerTickEvents.registerServerTickEvents();
		ModUseBlockCallback.interceptBlocks();
		ModChunkEvents.generateModWorldGen();
		ModCommands.registerCommands();
		ModAdvancementTriggers.registerCriteria();

		Registry.register(Registries.CHUNK_GENERATOR, new Identifier(TLOTD.MOD_ID, "prehistoric"), PrehistoricChunkGenerator.CODEC);
		Registry.register(Registries.CHUNK_GENERATOR, new Identifier(TLOTD.MOD_ID, "luna"), LunarChunkGenerator.CODEC);
		Registry.register(Registries.CHUNK_GENERATOR, new Identifier(TLOTD.MOD_ID, "backrooms"), BackroomsChunkGenerator.CODEC);



		StrippableBlockRegistry.register(ModBlocks.GINKGO_LOG, ModBlocks.STRIPPED_GINKGO_LOG);
		StrippableBlockRegistry.register(ModBlocks.GINKGO_WOOD, ModBlocks.STRIPPED_GINKGO_WOOD);

		StrippableBlockRegistry.register(ModBlocks.YELLOW_WALLPAPERED_WALL, ModBlocks.STRIPPED_YELLOW_WALLPAPERED_WALL);

		FabricDefaultAttributeRegistry.register(ModEntities.TREX, TRexEntity.createTRexAttributes());
		FabricDefaultAttributeRegistry.register(ModEntities.INFECTED_TREX, InfectedTRexEntity.createInfectedTRexAttributes());

		CustomPortalBuilder.beginPortal()
			.frameBlock(ModBlocks.REINFORCED_RED_DEEPSLATE)
			.customPortalBlock((CustomPortalBlock) ModBlocks.PREHISTORIC_PORTAL)
			.lightWithItem(ModItems.FOSSIL_AND_STEEL)
			.destDimID(new Identifier(TLOTD.MOD_ID, "prehistoric"))
			.tintColor(0x925240)
			.registerPortal();
		JoinDataSync.init();
		LOGGER.info("TLOTD INITIALIZED!");
	}
}