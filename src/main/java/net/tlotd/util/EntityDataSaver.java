package net.tlotd.util;

import net.minecraft.nbt.NbtCompound;

public interface EntityDataSaver {
    NbtCompound getPersistentData();
}
