package net.tlotd.util;

import net.minecraft.nbt.NbtCompound;

public class EnlightenedData {
    public static void enlighten(EntityDataSaver player) {
        NbtCompound nbt = player.getPersistentData();
        nbt.putBoolean("enlightened", true);
    }

    public static boolean isEnlightened(EntityDataSaver player) {
        NbtCompound nbt = player.getPersistentData();
        return nbt.getBoolean("enlightened");
    }
}
