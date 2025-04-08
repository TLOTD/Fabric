package net.tlotd;

import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.fabricmc.fabric.api.registry.FlammableBlockRegistry;
import net.fabricmc.fabric.api.registry.FuelRegistry;
import net.fabricmc.fabric.api.registry.StrippableBlockRegistry;
import net.kyrptonaught.customportalapi.api.CustomPortalBuilder;
import net.minecraft.block.ComposterBlock;
import net.minecraft.util.Identifier;
import net.tlotd.banner.ModBanners;
import net.tlotd.block.entity.ModBlockEntities;
import net.tlotd.config.ModConfigs;
import net.tlotd.effect.ModEffects;
import net.tlotd.entity.ModBoats;
import net.tlotd.entity.ModEntities;
import net.tlotd.entity.custom.InfectedTRexEntity;
import net.tlotd.entity.custom.TRexEntity;
import net.tlotd.fluid.ModFluids;
import net.tlotd.gui.ModGUIHandlers;
import net.tlotd.item.ModItemGroups;
import net.tlotd.item.ModItems;
import net.tlotd.block.ModBlocks;
import net.tlotd.networking.ModMessages;
import net.tlotd.painting.ModPaintings;
import net.tlotd.recipe.ModRecipies;
import net.tlotd.sound.ModSounds;
import net.tlotd.util.ModTags;
import net.tlotd.util.ModTrades;
import net.tlotd.villager.ModVillagers;
import net.tlotd.world.gen.ModWorldGeneration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TLOTD implements ModInitializer {
	public static final String MOD_ID = "tlotd";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {

		ModConfigs.registerConfigs();

		ModItemGroups.registerItemGroups();
		ModItems.registerModItems();
		ModBlocks.registerModBlocks();
		ModFluids.registerModFluids();
		ModBlockEntities.registerBlockEntities();
		ModGUIHandlers.registerGUIHandlers();
		ModRecipies.registerRecipes();
		ModVillagers.registerVillagers();
		ModTrades.registerTrades();
		ModBoats.registerBoats();
		ModPaintings.registerPaintings();
		ModBanners.registerBanners();
		ModEntities.registerModEntities();
		ModSounds.registerSounds();
		ModEffects.registerEffects();
		ModMessages.registerC2SPackets();


		LOGGER.info("TLOTD INITIALIZED!");

		FuelRegistry.INSTANCE.add(ModItems.SULFUR, 1600);
		FuelRegistry.INSTANCE.add(ModBlocks.SULFUR_BLOCK, 16000);
		FuelRegistry.INSTANCE.add(ModFluids.OIL_BUCKET, 16000);

		FuelRegistry.INSTANCE.add(ModTags.Items.WOODEN_BARK, 200);

		ComposterBlock.ITEM_TO_LEVEL_INCREASE_CHANCE.put(ModItems.STRAWBERRY_SEEDS, 0.3f);
		ComposterBlock.ITEM_TO_LEVEL_INCREASE_CHANCE.put(ModItems.ORANGE_SEEDS, 0.3f);

		ComposterBlock.ITEM_TO_LEVEL_INCREASE_CHANCE.put(ModItems.OAK_BARK, 0.3f);
		ComposterBlock.ITEM_TO_LEVEL_INCREASE_CHANCE.put(ModItems.SPRUCE_BARK, 0.3f);
		ComposterBlock.ITEM_TO_LEVEL_INCREASE_CHANCE.put(ModItems.BIRCH_BARK, 0.3f);
		ComposterBlock.ITEM_TO_LEVEL_INCREASE_CHANCE.put(ModItems.JUNGLE_BARK, 0.3f);
		ComposterBlock.ITEM_TO_LEVEL_INCREASE_CHANCE.put(ModItems.ACACIA_BARK, 0.3f);
		ComposterBlock.ITEM_TO_LEVEL_INCREASE_CHANCE.put(ModItems.DARK_OAK_BARK, 0.3f);
		ComposterBlock.ITEM_TO_LEVEL_INCREASE_CHANCE.put(ModItems.MANGROVE_BARK, 0.3f);
		ComposterBlock.ITEM_TO_LEVEL_INCREASE_CHANCE.put(ModItems.CHERRY_BARK, 0.3f);
		ComposterBlock.ITEM_TO_LEVEL_INCREASE_CHANCE.put(ModItems.GINKGO_BARK, 0.3f);

		ComposterBlock.ITEM_TO_LEVEL_INCREASE_CHANCE.put(ModBlocks.GINKGO_LEAVES, 0.3f);
		ComposterBlock.ITEM_TO_LEVEL_INCREASE_CHANCE.put(ModBlocks.GINKGO_SAPLING, 0.3f);

		ComposterBlock.ITEM_TO_LEVEL_INCREASE_CHANCE.put(ModItems.STRAWBERRY, 0.5f);
		ComposterBlock.ITEM_TO_LEVEL_INCREASE_CHANCE.put(ModItems.ORANGE, 0.5f);

		ComposterBlock.ITEM_TO_LEVEL_INCREASE_CHANCE.put(ModItems.MISTLETOE, 0.65f);

		ComposterBlock.ITEM_TO_LEVEL_INCREASE_CHANCE.put(ModBlocks.ROSE, 0.65f);
		ComposterBlock.ITEM_TO_LEVEL_INCREASE_CHANCE.put(ModBlocks.EDELWEISS, 0.65f);
		ComposterBlock.ITEM_TO_LEVEL_INCREASE_CHANCE.put(ModItems.EDELWEISS_PETALS, 0.65f);

		ComposterBlock.ITEM_TO_LEVEL_INCREASE_CHANCE.put(ModItems.CHOCOLATE_STRAWBERRY, 0.85f);
		ComposterBlock.ITEM_TO_LEVEL_INCREASE_CHANCE.put(ModBlocks.STRAWBERRY_CAKE, 1f);
		ComposterBlock.ITEM_TO_LEVEL_INCREASE_CHANCE.put(ModBlocks.ORANGE_CAKE, 1f);

		StrippableBlockRegistry.register(ModBlocks.GINKGO_LOG, ModBlocks.STRIPPED_GINKGO_LOG);
		StrippableBlockRegistry.register(ModBlocks.GINKGO_WOOD, ModBlocks.STRIPPED_GINKGO_WOOD);

		FlammableBlockRegistry.getDefaultInstance().add(ModBlocks.GINKGO_LOG, 5, 5);
		FlammableBlockRegistry.getDefaultInstance().add(ModBlocks.STRIPPED_GINKGO_LOG, 5, 5);
		FlammableBlockRegistry.getDefaultInstance().add(ModBlocks.GINKGO_WOOD, 5, 5);
		FlammableBlockRegistry.getDefaultInstance().add(ModBlocks.STRIPPED_GINKGO_WOOD, 5, 5);

		FlammableBlockRegistry.getDefaultInstance().add(ModBlocks.GINKGO_LEAVES, 30, 60);

		FlammableBlockRegistry.getDefaultInstance().add(ModBlocks.GINKGO_PLANKS, 5, 20);

		FabricDefaultAttributeRegistry.register(ModEntities.TREX, TRexEntity.createTRexAttributes());
		FabricDefaultAttributeRegistry.register(ModEntities.INFECTED_TREX, InfectedTRexEntity.createInfectedTRexAttributes());
		ModWorldGeneration.generateModWorldGen();

		CustomPortalBuilder.beginPortal()
				.frameBlock(ModBlocks.REINFORCED_RED_DEEPSLATE)
				.lightWithItem(ModItems.FOSSIL_AND_STEEL)
				.destDimID(new Identifier(TLOTD.MOD_ID, "prehistoric"))
				.tintColor(0x925240)
				.registerPortal();
	}
}