package net.tlotd.networking;

import net.minecraft.network.PacketByteBuf;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class TextureSyncPacket {

    public static class Entry {
        public final int textureId;
        public final String playerName;

        public Entry(int textureId, String playerName) {
            this.textureId = textureId;
            this.playerName = playerName;
        }
    }

    public final Map<UUID, Entry> textures;

    public TextureSyncPacket(Map<UUID, Entry> textures) {
        this.textures = textures;
    }

    public void write(PacketByteBuf buf) {
        buf.writeVarInt(textures.size());
        textures.forEach((uuid, entry) -> {
            buf.writeUuid(uuid);
            buf.writeVarInt(entry.textureId);
            buf.writeString(entry.playerName);
        });
    }

    public static TextureSyncPacket read(PacketByteBuf buf) {
        int size = buf.readVarInt();
        Map<UUID, Entry> textures = new HashMap<>();
        for (int i = 0; i < size; i++) {
            UUID uuid = buf.readUuid();
            int tex = buf.readVarInt();
            String name = buf.readString(32767);
            textures.put(uuid, new Entry(tex, name));
        }
        return new TextureSyncPacket(textures);
    }
}