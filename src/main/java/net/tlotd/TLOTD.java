package net.tlotd;

import net.fabricmc.api.ModInitializer;

import net.tlotd.banner.ModBanners;
import net.tlotd.block.ModFlammableBlocks;
import net.tlotd.block.ModUseBlockCallback;
import net.tlotd.block.behaviour.ModCauldronBehaviors;
import net.tlotd.block.entity.ModBlockEntities;
import net.tlotd.compat.CompatModsCheck;
import net.tlotd.config.ModConfigs;
import net.tlotd.effect.ModEffects;
import net.tlotd.effect.ModPotions;
import net.tlotd.enchantments.ModEnchantments;
import net.tlotd.particle.ModParticles;
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
import net.tlotd.world.dimension.*;
import net.tlotd.world.gen.ModWorldGeneration;
import net.tlotd.world.tree.ModTreeDecoratorTypes;
import net.tlotd.world.village.ModVillageAdditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TLOTD implements ModInitializer {
    public static final String MOD_ID = "tlotd";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    @Override
    public void onInitialize() {
        ModConfigs.registerConfigs();
        ModEffects.registerEffects();
        ModPotions.registerPotions();
        ModItemGroups.registerItemGroups();
        ModItems.registerModItems();
        ModCompostingChances.registerCompostableItems();
        ModParticles.registerParticles();
        ModFluids.registerModFluids();
        ModCauldronBehaviors.register();
        ModBlocks.registerModBlocks();
        ModTreeDecoratorTypes.registerDecorators();
        ModTelevisionSignals.registerSignals();
        ModFlammableBlocks.registerFlammableBlocks();
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
        ModDimansions.register();
        ModCommands.registerCommands();
        ModAdvancementTriggers.registerCriteria();
        ItemEntityTickHandler.register();
        CompatModsCheck.registerCompat();
        JoinDataSync.init();
        LOGGER.info("TLOTD INITIALIZED!");
    }
}