package net.tlotd.util;

import org.jetbrains.annotations.NotNull;

import java.util.Locale;

public class EnergyHelper {
    public static @NotNull String getEnergyString(long amount) {
        if (amount < 1000) {
            return Long.toString(amount);
        }
        final String[] units = {"K", "M", "B", "T", "P", "E"};
        int unitIndex = -1;
        double value = amount;
        while (value >= 1000 && unitIndex < units.length - 1) {
            value /= 1000.0;
            unitIndex++;
        }
        double roundedValue = (value >= 10 || value % 1 == 0) ? Math.round(value) : Math.round(value * 10) / 10.0;
        if (roundedValue >= 1000 && unitIndex < units.length - 1) {
            value /= 1000.0;
            unitIndex++;
            roundedValue = (value >= 10 || value % 1 == 0) ? Math.round(value) : Math.round(value * 10) / 10.0;
        }
        return String.format(Locale.ROOT, value >= 10 || value % 1 == 0 ? "%.0f%s" : "%.1f%s", roundedValue, units[unitIndex]);
    }
}