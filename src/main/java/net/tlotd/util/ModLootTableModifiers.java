package net.tlotd.util;

import net.fabricmc.fabric.api.loot.v2.LootTableEvents;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.entry.LootPoolEntry;
import net.minecraft.util.Identifier;
import net.tlotd.item.ModItems;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ModLootTableModifiers {

    private static final Identifier ARCHAEOLOGY_DESERT_PYRAMID = new Identifier("minecraft", "archaeology/desert_pyramid");
    private static final Identifier ARCHAEOLOGY_OCEAN_RUIN_COLD = new Identifier("minecraft", "archaeology/ocean_ruin_cold");
    private static final Identifier ARCHAEOLOGY_OCREAN_RUIN_WARM = new Identifier("minecraft", "archaeology/ocean_ruin_warm");
    private static final Identifier ARCHAEOLOGY_TRAIL_RUINS_COMMON = new Identifier("minecraft", "archaeology/trail_ruins_common");
    private static final Identifier ARCHAEOLOGY_TRAIL_RUINS_RARE = new Identifier("minecraft", "archaeology/trail_ruins_rara");

    public static void modifyLootTables() {
        LootTableEvents.REPLACE.register((resourceManager, lootManager, id, original, source) -> {
            if(ARCHAEOLOGY_DESERT_PYRAMID.equals(id)) {
                return getLootTable(original);
            }
            if(ARCHAEOLOGY_OCEAN_RUIN_COLD.equals(id)) {
                return getLootTable(original);
            }
            if(ARCHAEOLOGY_OCREAN_RUIN_WARM.equals(id)) {
                return getLootTable(original);
            }
            if(ARCHAEOLOGY_TRAIL_RUINS_COMMON.equals(id)) {
                return getLootTable(original);
            }
            if(ARCHAEOLOGY_TRAIL_RUINS_RARE.equals(id)) {
                return getLootTable(original);
            }
            return null;
        });
    }

    private static @Nullable LootTable getLootTable(LootTable original) {
        List<LootPoolEntry> entries = new ArrayList<>(Arrays.asList(original.pools[0].entries));
        entries.add(ItemEntry.builder(ModItems.FOSSILIZED_BONE).build());
        entries.add(ItemEntry.builder(ModItems.PLANT_FOSSIL).build());
        LootPool.Builder pool = LootPool.builder().with(entries);
        return LootTable.builder().pool(pool).build();
    }
}