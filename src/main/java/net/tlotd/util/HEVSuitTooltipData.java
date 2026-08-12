package net.tlotd.util;

import net.minecraft.client.item.TooltipData;
import net.minecraft.item.ItemStack;
import net.minecraft.util.collection.DefaultedList;

public class HEVSuitTooltipData implements TooltipData {
    private final DefaultedList<ItemStack> stacks;

    public HEVSuitTooltipData(DefaultedList<ItemStack> stacks) {
        this.stacks = stacks;
    }

    public DefaultedList<ItemStack> getStacks() {
        return stacks;
    }
}