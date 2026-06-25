package net.tlotd.util;

import net.minecraft.util.StringIdentifiable;

public enum TemperatureUnit implements StringIdentifiable {

    CELSIUS("Celsius"),
    FAHRENHEIT("Fahrenheit"),
    KELVIN("Kelvin"),
    TERRAFIRMACRAFT("TerraFirmaCraft");

    private final String name;

    TemperatureUnit(String name) {
        this.name = name;
    }

    @Override
    public String asString() {
        return name;
    }

    public static TemperatureUnit fromString(String value) {
        for (TemperatureUnit unit : values()) {
            if (unit.asString().equalsIgnoreCase(value)) {
                return unit;
            }
        }
        return null;
    }
}