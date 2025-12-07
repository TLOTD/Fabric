package net.tlotd.networking;

import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.PacketByteBuf;

public class PlayerDataSyncPacket {
    public final NbtCompound data;

    public PlayerDataSyncPacket(NbtCompound data) {
        this.data = data;
    }

    public void write(PacketByteBuf buf) {
        buf.writeNbt(data);
    }

    public static PlayerDataSyncPacket read(PacketByteBuf buf) {
        return new PlayerDataSyncPacket(buf.readNbt());
    }
}