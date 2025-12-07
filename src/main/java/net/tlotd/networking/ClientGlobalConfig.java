package net.tlotd.networking;

import net.minecraft.nbt.NbtCompound;

public class ClientGlobalConfig {
    public static boolean axeStrippingBark = true;
    public static boolean extractionOreCompat = true;
    public static int elevatorMaxDistance = 100;
    public static boolean formerTlotdRewards = false;

    public static void update(NbtCompound nbt) {
        axeStrippingBark = nbt.getBoolean("AxeStrippingBark");
        extractionOreCompat = nbt.getBoolean("ExtractionOreCompat");
        elevatorMaxDistance = nbt.getInt("ElevatorMaxDistance");
        formerTlotdRewards = nbt.getBoolean("FormerTLOTDRewards");
    }
}