package net.tlotd.block.enum_property;

import net.minecraft.util.StringIdentifiable;

public enum NoClipable implements StringIdentifiable {
    SOLID("solid"),
    NOCLIPABLE("noclipable"),
    PORTALING("portaling");

    private final String name;

    NoClipable(String name) {
        this.name = name;
    }

    @Override
    public String asString() {
        return name;
    }
}