package net.tlotd.util;

import net.fabricmc.fabric.api.loot.v2.LootTableEvents;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.condition.RandomChanceLootCondition;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.entry.LootPoolEntry;
import net.minecraft.loot.function.SetCountLootFunction;
import net.minecraft.loot.provider.number.ConstantLootNumberProvider;
import net.minecraft.loot.provider.number.UniformLootNumberProvider;
import net.minecraft.util.Identifier;
import net.tlotd.block.ModBlocks;
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
    private static final Identifier ARCHAEOLOGY_TRAIL_RUINS_RARE = new Identifier("minecraft", "archaeology/trail_ruins_rare");

    private static final Identifier SQUID = new Identifier("minecraft", "entities/squid");
    private static final Identifier GLOW_SQUID = new Identifier("minecraft", "entities/glow_squid");
    private static final Identifier GOAT = new Identifier("minecraft", "entities/goat");
    private static final Identifier GRASS = new Identifier("minecraft", "blocks/grass");

    private static final Identifier ENDER_DRAGON = new Identifier("minecraft", "entities/ender_dragon");

    public static void modifyLootTables() {
        LootTableEvents.MODIFY.register((resourceManager, lootManager, id, tableBuilder, source) -> {
            if (SQUID.equals(id) || GLOW_SQUID.equals(id)) {
                LootPool.Builder poolBuilder = LootPool.builder()
                        .rolls(ConstantLootNumberProvider.create(1))
                        .conditionally(RandomChanceLootCondition.builder(1f))
                        .with(ItemEntry.builder(ModItems.SQUID))
                        .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1f,1f)).build());
                tableBuilder.pool(poolBuilder.build());
            }
            if (GOAT.equals(id)) {
                LootPool.Builder poolBuilder = LootPool.builder()
                        .rolls(ConstantLootNumberProvider.create(1))
                        .conditionally(RandomChanceLootCondition.builder(1f))
                        .with(ItemEntry.builder(ModBlocks.GOAT_HEAD))
                        .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1f,1f)).build());
                tableBuilder.pool(poolBuilder.build());
            }
            if (ENDER_DRAGON.equals(id)) {
                LootPool.Builder poolBuilder = LootPool.builder()
                        .rolls(ConstantLootNumberProvider.create(8))
                        .conditionally(RandomChanceLootCondition.builder(1f))
                        .with(ItemEntry.builder(ModItems.ENDER_DRAGON_SCALES))
                        .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1f,1f)).build());
                tableBuilder.pool(poolBuilder.build());
            }
            if (GRASS.equals(id)) {
                tableBuilder.pool(
                        LootPool.builder()
                                .rolls(ConstantLootNumberProvider.create(1))
                                .conditionally(RandomChanceLootCondition.builder(0.125f))
                                .with(ItemEntry.builder(ModItems.STRAWBERRY_SEEDS))
                                .apply(SetCountLootFunction.builder(
                                        UniformLootNumberProvider.create(1f, 1f)
                                ))
                                .build()
                );
                tableBuilder.pool(
                        LootPool.builder()
                                .rolls(ConstantLootNumberProvider.create(1))
                                .conditionally(RandomChanceLootCondition.builder(0.125f))
                                .with(ItemEntry.builder(ModItems.ORANGE_SEEDS))
                                .apply(SetCountLootFunction.builder(
                                        UniformLootNumberProvider.create(1f, 1f)
                                ))
                                .build()
                );
                tableBuilder.pool(
                        LootPool.builder()
                                .rolls(ConstantLootNumberProvider.create(1))
                                .conditionally(RandomChanceLootCondition.builder(0.03125f))
                                .with(ItemEntry.builder(ModItems.PIPE_WEED_SEEDS))
                                .apply(SetCountLootFunction.builder(
                                        UniformLootNumberProvider.create(1f, 1f)
                                ))
                                .build()
                );
            }
        });
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