package net.tlotd.block;

import net.fabricmc.fabric.api.registry.FlammableBlockRegistry;
import net.tlotd.TLOTD;

public class ModFlammableBlocks {
    public static void registerFlammableBlocks() {
        FlammableBlockRegistry.getDefaultInstance().add(ModBlocks.GINKGO_LOG, 5, 5);
        FlammableBlockRegistry.getDefaultInstance().add(ModBlocks.STRIPPED_GINKGO_LOG, 5, 5);
        FlammableBlockRegistry.getDefaultInstance().add(ModBlocks.GINKGO_WOOD, 5, 5);
        FlammableBlockRegistry.getDefaultInstance().add(ModBlocks.STRIPPED_GINKGO_WOOD, 5, 5);
        FlammableBlockRegistry.getDefaultInstance().add(ModBlocks.GINKGO_LEAVES, 30, 60);
        FlammableBlockRegistry.getDefaultInstance().add(ModBlocks.GINKGO_PLANKS, 5, 20);
        FlammableBlockRegistry.getDefaultInstance().add(ModBlocks.GINKGO_STAIRS, 5, 20);
        FlammableBlockRegistry.getDefaultInstance().add(ModBlocks.GINKGO_SLAB, 5, 20);
        FlammableBlockRegistry.getDefaultInstance().add(ModBlocks.GINKGO_FENCE, 5, 20);
        FlammableBlockRegistry.getDefaultInstance().add(ModBlocks.GINKGO_FENCE_GATE, 5, 20);

        FlammableBlockRegistry.getDefaultInstance().add(ModBlocks.BACKSHROOM_PLANKS, 5, 20);
        FlammableBlockRegistry.getDefaultInstance().add(ModBlocks.BACKSHROOM_STAIRS, 5, 20);
        FlammableBlockRegistry.getDefaultInstance().add(ModBlocks.BACKSHROOM_SLAB, 5, 20);
        FlammableBlockRegistry.getDefaultInstance().add(ModBlocks.BACKSHROOM_FENCE, 5, 20);
        FlammableBlockRegistry.getDefaultInstance().add(ModBlocks.BACKSHROOM_FENCE_GATE, 5, 20);

        TLOTD.LOGGER.info("Registering Flammable Blocks for " + TLOTD.MOD_ID);
    }
}
