package net.tlotd.gui;

import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.screen.slot.Slot;

import static net.tlotd.util.AugmentNbtHelper.getAugmentLevel;

public class TagAugmentNotTagSlot extends Slot {
    private final TagKey<net.minecraft.item.Item> tag;
    private final String augment;
    private final TagKey<net.minecraft.item.Item> notTag;

    public TagAugmentNotTagSlot(Inventory inventory, int index, int x, int y, TagKey<net.minecraft.item.Item> tag, String augment, TagKey<net.minecraft.item.Item> notTag) {
        super(inventory, index, x, y);
        this.tag = tag;
        this.augment = augment;
        this.notTag = notTag;
    }

    @Override
    public boolean canInsert(ItemStack stack) {
        return !stack.isIn(notTag) && (stack.isIn(tag) || (getAugmentLevel(stack, augment) > 0));
    }
}
