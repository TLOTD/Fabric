package net.tlotd.block.enum_property;

import net.minecraft.util.StringIdentifiable;

public enum VerticalConnection implements StringIdentifiable {
    NONE("none"),
    LOWER("lower"),
    MIDDLE("middle"),
    UPPER("upper");

    private final String name;

    VerticalConnection(String name) {
        this.name = name;
    }

    @Override
    public String asString() {
        return name;
    }
}