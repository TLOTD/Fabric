package net.tlotd.client;

import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.minecraft.client.render.RenderLayer;
import net.tlotd.block.ModBlocks;
import net.tlotd.fluid.ModFluids;

public class ModBlockRenderLayerMap {
    public static void registerBlockRenderLayerMaps() {
        BlockRenderLayerMap.INSTANCE.putFluids(RenderLayer.getTranslucent(), ModFluids.STILL_SPEZI, ModFluids.FLOWING_SPEZI);
        BlockRenderLayerMap.INSTANCE.putFluids(RenderLayer.getTranslucent(), ModFluids.STILL_BEER, ModFluids.FLOWING_BEER);
        BlockRenderLayerMap.INSTANCE.putFluids(RenderLayer.getTranslucent(), ModFluids.STILL_MEAD, ModFluids.FLOWING_MEAD);
        BlockRenderLayerMap.INSTANCE.putFluids(RenderLayer.getTranslucent(), ModFluids.STILL_HOT_MILK, ModFluids.FLOWING_HOT_MILK);
        BlockRenderLayerMap.INSTANCE.putFluids(RenderLayer.getTranslucent(), ModFluids.STILL_HOT_CHOCOLATE, ModFluids.FLOWING_HOT_CHOCOLATE);
        BlockRenderLayerMap.INSTANCE.putFluids(RenderLayer.getTranslucent(), ModFluids.STILL_OIL, ModFluids.FLOWING_OIL);
        BlockRenderLayerMap.INSTANCE.putFluids(RenderLayer.getTranslucent(), ModFluids.STILL_BLOOD, ModFluids.FLOWING_BLOOD);

        BlockRenderLayerMap.INSTANCE.putFluids(RenderLayer.getTranslucent(), ModFluids.STILL_SWEET_BERRY_JAM, ModFluids.FLOWING_SWEET_BERRY_JAM);
        BlockRenderLayerMap.INSTANCE.putFluids(RenderLayer.getTranslucent(), ModFluids.STILL_GLOW_BERRY_JAM, ModFluids.FLOWING_GLOW_BERRY_JAM);
        BlockRenderLayerMap.INSTANCE.putFluids(RenderLayer.getTranslucent(), ModFluids.STILL_STRAWBERRY_JAM, ModFluids.FLOWING_STRAWBERRY_JAM);
        BlockRenderLayerMap.INSTANCE.putFluids(RenderLayer.getTranslucent(), ModFluids.STILL_ORANGE_MARMELADE, ModFluids.FLOWING_ORANGE_MARMELADE);
        BlockRenderLayerMap.INSTANCE.putFluids(RenderLayer.getTranslucent(), ModFluids.STILL_BLUE_BERRY_JAM, ModFluids.FLOWING_BLUE_BERRY_JAM);
        BlockRenderLayerMap.INSTANCE.putFluids(RenderLayer.getTranslucent(), ModFluids.STILL_DROOPFRUIT_JAM, ModFluids.FLOWING_DROOPFRUIT_JAM);
        BlockRenderLayerMap.INSTANCE.putFluids(RenderLayer.getTranslucent(), ModFluids.STILL_ANCIENT_SOULBERRY_JAM, ModFluids.FLOWING_ANCIENT_SOULBERRY_JAM);

        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.APPARATUS, RenderLayer.getTranslucent());

        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.SULFUR_LANTERN, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.SULFUR_CAMPFIRE, RenderLayer.getCutout());

        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.EXTINGUISHED_TORCH, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.EXTINGUISHED_WALL_TORCH, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.EXTINGUISHED_SOUL_TORCH, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.EXTINGUISHED_SOUL_WALL_TORCH, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.EXTINGUISHED_SULFUR_TORCH, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.EXTINGUISHED_SULFUR_WALL_TORCH, RenderLayer.getCutout());

        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.SMALL_GRAVESTONE, RenderLayer.getCutout());

        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.MITHRIL_BARS, RenderLayer.getCutout());

        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.FRAMED_GLASS, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.FRAMED_GLASS_PANE, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.FRAMED_GLASS_BOTTOM, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.FRAMED_GLASS_BOTTOM_PANE, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.FRAMED_GLASS_MIDDLE, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.FRAMED_GLASS_MIDDLE_PANE, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.FRAMED_GLASS_TOP, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.FRAMED_GLASS_TOP_PANE, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.FRAMED_GLASS_ROUND, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.FRAMED_GLASS_ROUND_PANE, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.FRAMED_GLASS_SPLIT, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.FRAMED_GLASS_SPLIT_PANE, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.FRAMED_GLASS_TILED, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.FRAMED_GLASS_TILED_PANE, RenderLayer.getCutout());

        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.PRESERVES_JAR, RenderLayer.getTranslucent());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.SWEET_BERRY_JAM_JAR, RenderLayer.getTranslucent());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.GLOW_BERRY_JAM_JAR, RenderLayer.getTranslucent());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.STRAWBERRY_JAM_JAR, RenderLayer.getTranslucent());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.ORANGE_MARMELADE_JAR, RenderLayer.getTranslucent());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.BLUE_BERRY_JAM_JAR, RenderLayer.getTranslucent());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.DROOPFRUIT_JAM_JAR, RenderLayer.getTranslucent());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.ANCIENT_SOULBERRY_JAM_JAR, RenderLayer.getTranslucent());

        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.BW_STICKER, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.TLOTD_STICKER, RenderLayer.getCutout());

        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.PRESENT, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.FESTIVE_LIGHTS, RenderLayer.getCutout());

        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.STRAWBERRY_BUSH, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.ORANGE_TREE, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.PIPE_WEED_PLANT, RenderLayer.getCutout());

        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.EFFIGIES, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.GOAT_HEAD, RenderLayer.getCutout());

        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.SKELETON, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.EMERGING_SKELETON, RenderLayer.getCutout());

        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.XEN_CRYSTAL_CLUSTER, RenderLayer.getCutout());

        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.CREEPER_PLUSHIE, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.ZOMBIE_PLUSHIE, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.PLAYER_PLUSHIE, RenderLayer.getCutout());

        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.BENCH, RenderLayer.getCutout());

        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.GARBAGE_CAN, RenderLayer.getCutout());

        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.RADIO, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.RADIO_ON, RenderLayer.getCutout());

        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.SIGNAL_TRANSMITTER, RenderLayer.getCutout());

        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.OXYGEN_COLLECTOR, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.INCUBATOR, RenderLayer.getCutout());

        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.TELEPORTER, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.ALIEN_CONTROL_PANEL, RenderLayer.getTranslucent());

        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.BLOOD_CAULDRON, RenderLayer.getCutout());

        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.ELEVATOR_DIAL, RenderLayer.getCutout());

        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.MINING_ELEVATOR_CONTROLLER, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.MINING_ELEVATOR_BASE, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.GLASS_ELEVATOR_CONTROLLER, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.GLASS_ELEVATOR_BASE, RenderLayer.getCutout());

        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.GLASS_DOOR, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.GLASS_TRAPDOOR, RenderLayer.getCutout());

        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.GINKGO_DOOR, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.GINKGO_TRAPDOOR, RenderLayer.getCutout());

        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.ROSE, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.POTTED_ROSE, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.IRIS, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.POTTED_IRIS, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.EDELWEISS, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.POTTED_EDELWEISS, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.ATHELAS, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.POTTED_ATHELAS, RenderLayer.getCutout());

        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.GINKGO_SAPLING, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.POTTED_GINKGO_SAPLING, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.GINKGO_LEAVES, RenderLayer.getCutout());

        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.WOODEN_STEIN, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.WOODEN_WATER_STEIN, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.WOODEN_APPLE_JUICE_STEIN, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.WOODEN_ORANGE_JUICE_STEIN, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.WOODEN_BEER_STEIN, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.WOODEN_MEAD_STEIN, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.WOODEN_MILK_STEIN, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.WOODEN_STRAWBERRY_MILKSHAKE_STEIN, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.WOODEN_ORANGE_MILKSHAKE_STEIN, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.WOODEN_BLUE_BERRY_MILKSHAKE_STEIN, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.WOODEN_TORCHBERRY_MILKSHAKE_STEIN, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.WOODEN_CHOCOLATE_MILKSHAKE_STEIN, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.WOODEN_CARAMEL_MILKSHAKE_STEIN, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.HOT_WOODEN_MILK_STEIN, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.WOODEN_HOT_CHOCOLATE_STEIN, RenderLayer.getCutout());

        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.TREX_HEAD, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.GREEN_TREX_HEAD, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.GRAY_TREX_HEAD, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.SCULK_TREX_HEAD, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.INFECTED_TREX_HEAD, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.SICKENED_TREX_HEAD, RenderLayer.getCutout());
    }
}
