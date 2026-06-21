package net.tlotd.util;

import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ItemEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;
import net.tlotd.block.ModBlocks;
import net.tlotd.item.ModItems;
import net.tlotd.item.custom.HeatableBlockItem;
import net.tlotd.item.custom.HeatableItem;

public class ItemEntityTickHandler {

    public static void register() {
        ServerTickEvents.END_WORLD_TICK.register(world -> {
            if (world.getTime() % 20 != 0) {
                return;
            }
            coolDroppedItems(world);
        });
    }

    private static void coolDroppedItems(ServerWorld world) {
        for (ItemEntity itemEntity : world.getEntitiesByType(EntityType.ITEM, entity ->
                entity.getStack().getItem() instanceof HeatableItem ||
                entity.getStack().getItem() instanceof HeatableBlockItem ||
                entity.getStack().isIn(ModTags.Items.LAVA_CLEANSING))) {
            ItemStack stack = itemEntity.getStack().copy();
            if (stack.isIn(ModTags.Items.LAVA_CLEANSING) && itemEntity.isInLava()) {
                if (stack.isOf(ModItems.RAW_MITHRIL)) {
                    stack = new ItemStack(ModItems.REFINED_RAW_MITHRIL, stack.getCount());
                } else if (stack.isOf(ModBlocks.RAW_MITHRIL_BLOCK.asItem())) {
                    stack = new ItemStack(ModBlocks.REFINED_RAW_MITHRIL_BLOCK.asItem(), stack.getCount());
                }
            } else {
                coolStack(itemEntity, stack);
            }
            itemEntity.setStack(stack);
        }
    }

    private static void coolStack(ItemEntity itemEntity, ItemStack stack) {
        if (!ItemHeatHelper.hasTemperature(stack)) {
            return;
        }
        int cooling = 20;
        if (itemEntity.isWet()) {
            cooling = 160;
        }
        ItemHeatHelper.editTemperature(stack, -cooling);
    }
}