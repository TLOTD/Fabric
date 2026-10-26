package net.tlotd.util;

import net.minecraft.item.tooltip.TooltipData;
import net.minecraft.nbt.NbtList;

public class SpaceSuitTooltipData implements TooltipData {
    private final NbtList serializedStacks;

    public SpaceSuitTooltipData(NbtList serializedStacks) {
        this.serializedStacks = serializedStacks.copy();
    }

    public NbtList getSerializedStacks() {
        return serializedStacks.copy();
    }
}