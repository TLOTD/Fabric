package net.tlotd.item.custom;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.StackReference;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.slot.Slot;
import net.minecraft.util.ClickType;
import net.minecraft.world.World;
import net.tlotd.util.ItemHeatHelper;
import net.tlotd.util.ModTags;

public class HeatableItem extends Item {

    public HeatableItem(Settings settings) {
        super(settings);
    }

    @Override
    public boolean isItemBarVisible(ItemStack stack) {
        return ItemHeatHelper.hasTemperature(stack) && stack.getCount() == 1;
    }

    @Override
    public int getItemBarColor(ItemStack stack) {
        return ItemHeatHelper.getTemperatureColor(stack);
    }

    @Override
    public int getItemBarStep(ItemStack stack) {
        return 13;
    }

    @Override
    public void inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected) {
        super.inventoryTick(stack, world, entity, slot, selected);
        if (world.isClient()) {
            return;
        }
        if (!ItemHeatHelper.hasTemperature(stack)) {
            return;
        }
        world.getTime();
        ItemHeatHelper.editTemperature(stack, -1);
        if (ItemHeatHelper.getTemperature(stack) <= 0) {
            stack.removeSubNbt("temperature");
        }
        if (entity instanceof PlayerEntity player) {
            int temperature = ItemHeatHelper.getTemperature(stack);
            boolean hasBetterTongs = (player.getMainHandStack().isIn(ModTags.Items.REINFORCED_TONGS) || player.getOffHandStack().isIn(ModTags.Items.REINFORCED_TONGS));
            boolean hasTongs = (player.getMainHandStack().isIn(ModTags.Items.TONGS) || player.getOffHandStack().isIn(ModTags.Items.TONGS));
            if (temperature >= 1500 && !hasBetterTongs) {
                player.setOnFireFor(1);
            } else if (temperature >= 500 && !hasTongs) {
                player.setOnFireFor(1);
            }
        }
    }

    @Override
    public boolean onClicked(ItemStack stack, ItemStack otherStack, Slot slot, ClickType clickType, PlayerEntity player, StackReference cursorStackReference) {
        if (!otherStack.isOf(this)) {
            return false;
        }
        if (clickType == ClickType.LEFT) {
            int tempA = ItemHeatHelper.getTemperature(stack);
            int tempB = ItemHeatHelper.getTemperature(otherStack);
            int countA = stack.getCount();
            int countB = otherStack.getCount();
            int total = countA + countB;
            if (total > stack.getMaxCount()) {
                return false;
            }
            int averageTemperature = ((tempA * countA) + (tempB * countB)) / total;
            stack.setCount(total);
            if (averageTemperature <= 0) {
                stack.removeSubNbt("temperature");
            } else {
                ItemHeatHelper.setTemperature(stack, averageTemperature);
            }
            otherStack.setCount(0);
            cursorStackReference.set(ItemStack.EMPTY);
            return true;
        }
        if (clickType == ClickType.RIGHT) {
            if (stack.getCount() >= stack.getMaxCount()) {
                return false;
            }
            int slotTemp = ItemHeatHelper.getTemperature(stack);
            int cursorTemp = ItemHeatHelper.getTemperature(otherStack);
            int slotCount = stack.getCount();
            int newTemperature = ((slotTemp * slotCount) + cursorTemp) / (slotCount + 1);
            stack.increment(1);
            if (newTemperature <= 0) {
                stack.removeSubNbt("temperature");
            } else {
                ItemHeatHelper.setTemperature(stack, newTemperature);
            }
            otherStack.decrement(1);
            if (otherStack.isEmpty()) {
                cursorStackReference.set(ItemStack.EMPTY);
            }
            return true;
        }
        return false;
    }
}