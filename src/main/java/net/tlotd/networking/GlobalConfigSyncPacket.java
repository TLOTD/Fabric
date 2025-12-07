package net.tlotd.networking;

import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.PacketByteBuf;

public class GlobalConfigSyncPacket {

    public final NbtCompound data;

    public GlobalConfigSyncPacket(NbtCompound data) {
        this.data = data;
    }

    public void write(PacketByteBuf buf) {
        buf.writeNbt(data);
    }

    public static GlobalConfigSyncPacket read(PacketByteBuf buf) {
        return new GlobalConfigSyncPacket(buf.readNbt());
    }
}