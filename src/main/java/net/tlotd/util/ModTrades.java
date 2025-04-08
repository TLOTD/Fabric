package net.tlotd.util;

import net.fabricmc.fabric.api.object.builder.v1.trade.TradeOfferHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.village.TradeOffer;
import net.minecraft.village.VillagerProfession;
import net.tlotd.block.ModBlocks;
import net.tlotd.item.ModItems;
import net.tlotd.villager.ModVillagers;

public class ModTrades {
    public static void registerTrades() {
        TradeOfferHelper.registerVillagerOffers(VillagerProfession.FARMER, 1,
                factories -> {
                    factories.add((entity, random) -> new TradeOffer(
                            new ItemStack(Items.EMERALD, 3),
                            new ItemStack(ModItems.STRAWBERRY_SEEDS, 1),
                            6, 5, 0.1f));
                    factories.add((entity, random) -> new TradeOffer(
                            new ItemStack(Items.EMERALD, 3),
                            new ItemStack(ModItems.ORANGE_SEEDS, 1),
                            6, 5, 0.1f));
                });

        TradeOfferHelper.registerVillagerOffers(ModVillagers.ARCHAEOLOGIST, 1,
                factories -> {
                    factories.add((entity, random) -> new TradeOffer(
                            new ItemStack(ModItems.FOSSILIZED_BONE, 7),
                            new ItemStack(ModBlocks.GINKGO_SAPLING, 1),
                            6, 2, 0.1f));

                    factories.add((entity, random) -> new TradeOffer(
                            new ItemStack(ModItems.FOSSILIZED_BONE, 3),
                            new ItemStack(ModBlocks.ROSE, 1),
                            6, 2, 0.1f));

                    factories.add((entity, random) -> new TradeOffer(
                            new ItemStack(ModItems.FOSSILIZED_BONE, 3),
                            new ItemStack(ModBlocks.EDELWEISS, 1),
                            6, 2, 0.1f));
                });

        TradeOfferHelper.registerVillagerOffers(ModVillagers.ARCHAEOLOGIST, 2,
                factories -> {
                    factories.add((entity, random) -> new TradeOffer(
                            new ItemStack(ModItems.FOSSILIZED_BONE, 3),
                            new ItemStack(ModItems.XEN_CRYSTAL, 1),
                            3, 10, 0.1f));

                    factories.add((entity, random) -> new TradeOffer(
                            new ItemStack(ModBlocks.RED_DEEPSLATE, 3),
                            new ItemStack(Items.EMERALD, 1),
                            new ItemStack(ModBlocks.REINFORCED_RED_DEEPSLATE, 1),
                            6, 10, 0.1f));

                });
        TradeOfferHelper.registerVillagerOffers(ModVillagers.ARCHAEOLOGIST, 3,
                factories -> {
                    factories.add((entity, random) -> new TradeOffer(
                            new ItemStack(ModItems.FOSSILIZED_BONE, 17),
                            new ItemStack(Items.EGG, 1),
                            new ItemStack(ModBlocks.TREX_EGG, 1),
                            1, 20, 0.1f));
                });

        TradeOfferHelper.registerVillagerOffers(ModVillagers.CULTIST, 1,
                factories -> {
                    factories.add((entity, random) -> new TradeOffer(
                            new ItemStack(ModItems.SOUL_FLASK, 1),
                            new ItemStack(ModItems.BLOOD_BOTTLE, 1),
                            new ItemStack(ModItems.CURSED_SOUL_FLASK, 1),
                            6, 2, 0.1f));
                });

        TradeOfferHelper.registerVillagerOffers(ModVillagers.ENGINEER, 1,
                factories -> {
                    factories.add((entity, random) -> new TradeOffer(
                            new ItemStack(ModBlocks.RAW_MITHRIL_BLOCK, 1),
                            new ItemStack(Items.EMERALD, 12),
                            new ItemStack(ModItems.MITHRIL_INGOT, 10),
                            6, 2, 0.1f));
                });

        TradeOfferHelper.registerWanderingTraderOffers(1,
                factories -> {
                    factories.add((entity, random) -> new TradeOffer(
                            new ItemStack(Items.EMERALD, 5),
                            new ItemStack(ModBlocks.GINKGO_SAPLING, 1),
                            1, 5, 0.1f));
                    factories.add((entity, random) -> new TradeOffer(
                            new ItemStack(Items.EMERALD, 3),
                            new ItemStack(ModItems.STRAWBERRY, 1),
                            1, 5, 0.1f));
                    factories.add((entity, random) -> new TradeOffer(
                            new ItemStack(Items.EMERALD, 3),
                            new ItemStack(ModItems.ORANGE, 1),
                            1, 5, 0.1f));
                });
    }
}
