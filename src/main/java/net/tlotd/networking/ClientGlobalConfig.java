package net.tlotd.networking;

import net.minecraft.nbt.NbtCompound;
import net.tlotd.util.TemperatureUnit;

public class ClientGlobalConfig {
    public static String defaultTemperatureUnit = TemperatureUnit.CELSIUS.asString();

    public static boolean axeStrippingBark = true;
    public static boolean extractionOreCompat = true;
    public static int elevatorMaxDistance = 100;
    public static boolean formerTlotdRewards = false;

    public static boolean starlightAnvil = true;
    public static boolean bloodWitching = true;
    public static boolean soulWitching = true;

    public static int warpHeightOutOfTerra = 1000;
    public static int warpHeightOutOfLuna = 1000;
    public static int warpHeightIntoTerra = 320;
    public static int warpHeightIntoLuna = 100;
    public static int terraResistance = 400;

    public static double noClipChance = 0.1;

    public static void update(NbtCompound nbt) {
        defaultTemperatureUnit = nbt.getString("DefaultTemperatureUnit");

        axeStrippingBark = nbt.getBoolean("AxeStrippingBark");
        extractionOreCompat = nbt.getBoolean("ExtractionOreCompat");
        elevatorMaxDistance = nbt.getInt("ElevatorMaxDistance");
        formerTlotdRewards = nbt.getBoolean("FormerTLOTDRewards");

        starlightAnvil = nbt.getBoolean("StarlightAnvil");
        bloodWitching = nbt.getBoolean("BloodWitching");
        soulWitching = nbt.getBoolean("SoulWitching");

        warpHeightOutOfTerra = nbt.getInt("WarpHeightOutOfTerra");
        warpHeightOutOfLuna = nbt.getInt("WarpHeightOutOfLuna");
        warpHeightIntoTerra = nbt.getInt("WarpHeightIntoTerra");
        warpHeightIntoLuna = nbt.getInt("WarpHeightIntoLuna");
        terraResistance = nbt.getInt("TerraResistance");

        noClipChance = nbt.getDouble("NoClipChance");
    }
}