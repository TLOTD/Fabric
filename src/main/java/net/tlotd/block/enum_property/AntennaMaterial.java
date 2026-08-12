package net.tlotd.block.enum_property;

import net.minecraft.util.StringIdentifiable;

public enum AntennaMaterial implements StringIdentifiable {
    ALUMINIUM("aluminium"),
    COPPER("copper"),
    GOLD("gold"),
    MITHRIL("mithril");

    private final String name;

    AntennaMaterial(String name) {
        this.name = name;
    }

    @Override
    public String asString() {
        return name;
    }
}