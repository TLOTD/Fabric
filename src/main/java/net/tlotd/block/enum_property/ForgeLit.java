package net.tlotd.block.enum_property;

import net.minecraft.util.StringIdentifiable;

public enum ForgeLit implements StringIdentifiable {
    EXTINGUISHED("extinguished"),
    BURNING("burning"),
    SOUL("soul"),
    DRAGON("dragon");

    private final String name;

    ForgeLit(String name) {
        this.name = name;
    }

    @Override
    public String asString() {
        return name;
    }
}