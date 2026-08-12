package net.tlotd.gui;

import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.screen.slot.Slot;

public class TagSlot extends Slot {

    private final TagKey<net.minecraft.item.Item> tag;
    private final TagKey<net.minecraft.item.Item> tag2;

    public TagSlot(Inventory inventory, int index, int x, int y, TagKey<net.minecraft.item.Item> tag) {
        super(inventory, index, x, y);
        this.tag = tag;
        this.tag2 = tag;
    }

    public TagSlot(Inventory inventory, int index, int x, int y, TagKey<net.minecraft.item.Item> tag, TagKey<net.minecraft.item.Item> tag2) {
        super(inventory, index, x, y);
        this.tag = tag;
        this.tag2 = tag2;
    }

    @Override
    public boolean canInsert(ItemStack stack) {
        return stack.isIn(tag) || stack.isIn(tag2);
    }
}