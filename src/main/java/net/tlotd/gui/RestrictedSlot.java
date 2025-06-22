package net.tlotd.gui;

import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.screen.slot.Slot;

public class RestrictedSlot extends Slot {

    private final TagKey<net.minecraft.item.Item> tag;

    public RestrictedSlot(Inventory inventory, int index, int x, int y, TagKey<net.minecraft.item.Item> tag) {
        super(inventory, index, x, y);
        this.tag = tag;
    }

    @Override
    public boolean canInsert(ItemStack stack) {
        return stack.isIn(tag);
    }
}
