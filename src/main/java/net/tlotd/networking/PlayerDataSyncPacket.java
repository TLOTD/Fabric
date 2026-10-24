package net.tlotd.networking;

import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.PacketByteBuf;

public record PlayerDataSyncPacket(NbtCompound data) {

    public void write(PacketByteBuf buf) {
        buf.writeNbt(data);
    }

    public static PlayerDataSyncPacket read(PacketByteBuf buf) {
        return new PlayerDataSyncPacket(buf.readNbt());
    }
}