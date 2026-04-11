package net.tlotd.block.enum_property;

import net.minecraft.util.StringIdentifiable;

public enum TallBlock implements StringIdentifiable {
    FULL("full"),
    LOWER("lower"),
    UPPER("upper");

    private final String name;

    TallBlock(String name) {
        this.name = name;
    }

    @Override
    public String asString() {
        return name;
    }
}