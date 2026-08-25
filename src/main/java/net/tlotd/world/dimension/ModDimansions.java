package net.tlotd.world.dimension;

import net.fabricmc.fabric.api.event.lifecycle.v1.ServerWorldEvents;
import net.kyrptonaught.customportalapi.CustomPortalBlock;
import net.kyrptonaught.customportalapi.api.CustomPortalBuilder;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.tlotd.TLOTD;
import net.tlotd.block.ModBlocks;
import net.tlotd.item.ModItems;
import net.tlotd.world.SignalTrackingArray;

public class ModDimansions {
    public static void register() {
        Registry.register(Registries.CHUNK_GENERATOR, new Identifier(TLOTD.MOD_ID, "prehistoric"), PrehistoricChunkGenerator.CODEC);
        Registry.register(Registries.CHUNK_GENERATOR, new Identifier(TLOTD.MOD_ID, "luna"), LunarChunkGenerator.CODEC);
        Registry.register(Registries.CHUNK_GENERATOR, new Identifier(TLOTD.MOD_ID, "backrooms"), BackroomsChunkGenerator.CODEC);
        CustomPortalBuilder.beginPortal().frameBlock(ModBlocks.REINFORCED_RED_DEEPSLATE).customPortalBlock((CustomPortalBlock) ModBlocks.PREHISTORIC_PORTAL).lightWithItem(ModItems.FOSSIL_AND_STEEL).destDimID(new Identifier(TLOTD.MOD_ID, "prehistoric")).tintColor(0x925240).registerPortal();
        ServerWorldEvents.LOAD.register((server, world) -> {
            if (world.getRegistryKey() == ModDimensionsDataGenerator.BACKROOMS_LEVEL_KEY) {
                SignalTrackingArray tracker = SignalTrackingArray.get(world);
                tracker.addDimensionSignal(Registries.ITEM.getId(ModItems.VHS_CASSETTE_PROJECT_KV31));
                tracker.addDimensionSignal(Registries.ITEM.getId(ModItems.BACKROOMS_SIGNAL));
            }
        });
    }
}
