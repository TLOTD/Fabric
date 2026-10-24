package net.tlotd.networking;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class ClientTextureCache {
    public static final Map<UUID, TextureSyncPayload.Entry> TEXTURES = new HashMap<>();

    public static void update(Map<UUID, TextureSyncPayload.Entry> textures) {
        TEXTURES.clear();
        TEXTURES.putAll(textures);
    }
}