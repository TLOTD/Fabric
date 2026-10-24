package net.tlotd.util;

import net.fabricmc.fabric.api.loot.v3.LootTableEvents;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.provider.number.ConstantLootNumberProvider;
import net.minecraft.loot.provider.number.UniformLootNumberProvider;
import net.minecraft.loot.function.SetCountLootFunction;
import net.minecraft.loot.condition.RandomChanceLootCondition;
import net.minecraft.util.Identifier;
import net.tlotd.block.ModBlocks;
import net.tlotd.item.ModItems;

public class ModLootTableModifiers {

    private static final Identifier ARCHAEOLOGY_DESERT_PYRAMID = Identifier.of("minecraft", "archaeology/desert_pyramid");
    private static final Identifier ARCHAEOLOGY_OCEAN_RUIN_COLD = Identifier.of("minecraft", "archaeology/ocean_ruin_cold");
    private static final Identifier ARCHAEOLOGY_OCEAN_RUIN_WARM = Identifier.of("minecraft", "archaeology/ocean_ruin_warm");
    private static final Identifier ARCHAEOLOGY_TRAIL_RUINS_COMMON = Identifier.of("minecraft", "archaeology/trail_ruins_common");
    private static final Identifier ARCHAEOLOGY_TRAIL_RUINS_RARE = Identifier.of("minecraft", "archaeology/trail_ruins_rare");

    private static final Identifier SQUID = Identifier.of("minecraft", "entities/squid");
    private static final Identifier GLOW_SQUID = Identifier.of("minecraft", "entities/glow_squid");
    private static final Identifier GOAT = Identifier.of("minecraft", "entities/goat");
    private static final Identifier GRASS = Identifier.of("minecraft", "blocks/grass");
    private static final Identifier ENDER_DRAGON = Identifier.of("minecraft", "entities/ender_dragon");

    public static void modifyLootTables() {
        LootTableEvents.MODIFY.register((key, tableBuilder, source, registries) -> {
            Identifier id = key.getValue();
            if (SQUID.equals(id) || GLOW_SQUID.equals(id)) {
                tableBuilder.pool(LootPool.builder().rolls(ConstantLootNumberProvider.create(1)).conditionally(RandomChanceLootCondition.builder(1.0f)).with(ItemEntry.builder(ModItems.SQUID)).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 1.0f))));
            }
            if (GOAT.equals(id)) {
                tableBuilder.pool(LootPool.builder().rolls(ConstantLootNumberProvider.create(1)).conditionally(RandomChanceLootCondition.builder(1.0f)).with(ItemEntry.builder(ModBlocks.GOAT_HEAD)).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 1.0f))));
            }
            if (ENDER_DRAGON.equals(id)) {
                tableBuilder.pool(LootPool.builder().rolls(ConstantLootNumberProvider.create(8)).conditionally(RandomChanceLootCondition.builder(1.0f)).with(ItemEntry.builder(ModItems.ENDER_DRAGON_SCALES)).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 1.0f))));
            }
            if (GRASS.equals(id)) {
                tableBuilder.pool(LootPool.builder().rolls(ConstantLootNumberProvider.create(1)).conditionally(RandomChanceLootCondition.builder(0.125f)).with(ItemEntry.builder(ModItems.STRAWBERRY_SEEDS)).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 1.0f))));
                tableBuilder.pool(LootPool.builder().rolls(ConstantLootNumberProvider.create(1)).conditionally(RandomChanceLootCondition.builder(0.125f)).with(ItemEntry.builder(ModItems.ORANGE_SEEDS)).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 1.0f))));
                tableBuilder.pool(LootPool.builder().rolls(ConstantLootNumberProvider.create(1)).conditionally(RandomChanceLootCondition.builder(0.03125f)).with(ItemEntry.builder(ModItems.PIPE_WEED_SEEDS)).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 1.0f))));
            }
            if (ARCHAEOLOGY_DESERT_PYRAMID.equals(id) || ARCHAEOLOGY_OCEAN_RUIN_COLD.equals(id) || ARCHAEOLOGY_OCEAN_RUIN_WARM.equals(id) || ARCHAEOLOGY_TRAIL_RUINS_COMMON.equals(id) || ARCHAEOLOGY_TRAIL_RUINS_RARE.equals(id)) {
                tableBuilder.pool(LootPool.builder().with(ItemEntry.builder(ModItems.FOSSILIZED_BONE)).with(ItemEntry.builder(ModItems.PLANT_FOSSIL)));
            }
        });
    }
}