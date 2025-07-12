package net.tlotd.item;

import net.fabricmc.fabric.api.registry.FuelRegistry;
import net.tlotd.TLOTD;
import net.tlotd.block.ModBlocks;
import net.tlotd.fluid.ModFluids;
import net.tlotd.util.ModTags;

public class ModFuels {
    public static void registerModFuels() {
        FuelRegistry.INSTANCE.add(ModItems.SULFUR, 1600);
        FuelRegistry.INSTANCE.add(ModBlocks.SULFUR_BLOCK, 16000);
        FuelRegistry.INSTANCE.add(ModFluids.OIL_BUCKET, 16000);

        FuelRegistry.INSTANCE.add(ModTags.Items.WOODEN_BARK, 200);
        FuelRegistry.INSTANCE.add(ModTags.Items.STICK_EFFIGIES, 200);

        FuelRegistry.INSTANCE.add(ModBlocks.ARCHAEOLOGY_TABLE, 300);
        FuelRegistry.INSTANCE.add(ModBlocks.EFFIGIES, 300);
        FuelRegistry.INSTANCE.add(ModBlocks.RADIO, 800);
        FuelRegistry.INSTANCE.add(ModBlocks.WOODEN_STEIN, 1000);

        TLOTD.LOGGER.info("Registering Mod Fluids for " + TLOTD.MOD_ID);
    }
}
