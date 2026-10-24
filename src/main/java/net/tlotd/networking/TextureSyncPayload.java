package net.tlotd.networking;

import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.packet.CustomPayload;
import net.minecraft.util.Identifier;
import net.tlotd.TLOTD;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public record TextureSyncPayload(Map<UUID, Entry> textures) implements CustomPayload {

    public static final Id<TextureSyncPayload> ID = new Id<>(Identifier.of(TLOTD.MOD_ID, "texture_sync"));

    public static final PacketCodec<RegistryByteBuf, TextureSyncPayload> CODEC = PacketCodec.of((payload, buf) -> {
        buf.writeVarInt(payload.textures().size());
        payload.textures().forEach((uuid, entry) -> {
            buf.writeUuid(uuid);
            buf.writeVarInt(entry.textureId());
            buf.writeString(entry.playerName(), 32767);
        });
    }, buf -> {
        int size = buf.readVarInt();
        Map<UUID, Entry> textures = new HashMap<>();
        for (int i = 0; i < size; i++) {
            UUID uuid = buf.readUuid();
            int textureId = buf.readVarInt();
            String playerName = buf.readString(32767);
            textures.put(uuid, new Entry(textureId, playerName));
        }
        return new TextureSyncPayload(Map.copyOf(textures));
    });

    @Override
    public Id<? extends CustomPayload> getId() {
        return ID;
    }

    public record Entry(int textureId, String playerName) {
    }
}