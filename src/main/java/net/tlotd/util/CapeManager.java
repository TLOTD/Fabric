package net.tlotd.util;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.texture.AbstractTexture;
import net.minecraft.client.texture.NativeImage;
import net.minecraft.client.texture.NativeImageBackedTexture;
import net.minecraft.util.Identifier;

import java.io.InputStream;
import java.net.URL;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.*;


public class CapeManager {

    private static final MinecraftClient MC = MinecraftClient.getInstance();
    private static final Map<String, Identifier> CAPE_CACHE = new ConcurrentHashMap<>();
    private static final Map<String, Long> CACHE_TIME = new ConcurrentHashMap<>();
    private static final Set<String> PENDING = ConcurrentHashMap.newKeySet();
    private static final long CACHE_TTL = TimeUnit.MINUTES.toMillis(10);
    private static final Identifier NO_CAPE = new Identifier("tlotd", "no_cape");
    private static final ExecutorService EXECUTOR = Executors.newCachedThreadPool();
    private static final String BASE_URL = "https://tlotd.net/api/minecraft/cape/";

    public static Identifier getCape(String uuid) {
        if (uuid == null || uuid.isEmpty()) return null;
        long now = System.currentTimeMillis();
        Identifier cached = CAPE_CACHE.get(uuid);
        if (cached != null) {
            if (now - CACHE_TIME.getOrDefault(uuid, 0L) < CACHE_TTL) {
                return cached == NO_CAPE ? null : cached;
            }
            CAPE_CACHE.remove(uuid);
            CACHE_TIME.remove(uuid);
        }
        requestCape(uuid);
        return null;
    }

    public static void requestCape(String uuid) {
        if (uuid == null || uuid.isEmpty()) return;
        if (PENDING.contains(uuid)) return;
        if (CAPE_CACHE.containsKey(uuid)) return;
        PENDING.add(uuid);
        EXECUTOR.submit(() -> fetchCape(uuid));
    }

    private static void fetchCape(String uuid) {
        String url = BASE_URL + uuid;
        try (InputStream stream = new URL(url).openStream()) {
            NativeImage image = NativeImage.read(stream);
            AbstractTexture texture = new NativeImageBackedTexture(image);
            Identifier id = new Identifier("tlotd", "cape/" + uuid.replace("-", ""));
            MC.execute(() -> {
                try {
                    MC.getTextureManager().registerTexture(id, texture);
                    CAPE_CACHE.put(uuid, id);
                    CACHE_TIME.put(uuid, System.currentTimeMillis());
                } catch (Exception e) {
                    CAPE_CACHE.put(uuid, NO_CAPE);
                    CACHE_TIME.put(uuid, System.currentTimeMillis());
                } finally {
                    PENDING.remove(uuid);
                }
            });
        } catch (Exception e) {
            CAPE_CACHE.put(uuid, NO_CAPE);
            CACHE_TIME.put(uuid, System.currentTimeMillis());
            PENDING.remove(uuid);
        }
    }

    public static void clearCache() {
        CAPE_CACHE.clear();
        CACHE_TIME.clear();
        PENDING.clear();
    }
}