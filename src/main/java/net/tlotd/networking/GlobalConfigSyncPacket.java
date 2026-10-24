package net.tlotd.networking;

import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.PacketByteBuf;

public record GlobalConfigSyncPacket(NbtCompound data) {

    public void write(PacketByteBuf buf) {
        buf.writeNbt(data);
    }

    public static GlobalConfigSyncPacket read(PacketByteBuf buf) {
        return new GlobalConfigSyncPacket(buf.readNbt());
    }
}