package net.tlotd.util;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.texture.NativeImage;
import net.minecraft.client.texture.NativeImageBackedTexture;
import net.minecraft.util.Identifier;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.Instant;
import java.util.Map;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicReference;


public class CapeManager {
    private static final MinecraftClient MC = MinecraftClient.getInstance();
    private static final Map<String, Identifier> CAPE_CACHE = new ConcurrentHashMap<>();
    private static final Map<String, Long> CAPE_CACHE_TIMESTAMPS = new ConcurrentHashMap<>();
    private static final long CACHE_EXPIRATION_MS = TimeUnit.MINUTES.toMillis(10);

    private static final Identifier NO_CAPE = new Identifier("tlotd", "no_cape");
    private static final String CAPE_BASE_URL = "https://tlotd.net/minecraft/capes/";

    public static Identifier getCape(String uuid) {
        if (uuid == null || uuid.isEmpty()) {
            return null;
        }

        long now = Instant.now().toEpochMilli();

        if (CAPE_CACHE.containsKey(uuid)) {
            if ((now - CAPE_CACHE_TIMESTAMPS.getOrDefault(uuid, 0L)) < CACHE_EXPIRATION_MS) {
                Identifier cached = CAPE_CACHE.get(uuid); return cached == NO_CAPE ? null : cached;
            } else {
                CAPE_CACHE.remove(uuid);
                CAPE_CACHE_TIMESTAMPS.remove(uuid);
            }
        }

        String capeUrl = CAPE_BASE_URL + uuid + ".png";

        try {
            HttpURLConnection head = (HttpURLConnection) new URL(capeUrl).openConnection();
            head.setRequestMethod("HEAD");
            head.setConnectTimeout(1500);
            head.setReadTimeout(1500);

            int responseCode = head.getResponseCode();
            if (responseCode != HttpURLConnection.HTTP_OK) {
                CAPE_CACHE.put(uuid, NO_CAPE);
                CAPE_CACHE_TIMESTAMPS.put(uuid, now);
                return null;
            }

            HttpURLConnection conn = (HttpURLConnection) new URL(capeUrl).openConnection();
            conn.setConnectTimeout(1500);
            conn.setReadTimeout(1500);

            try (InputStream stream = conn.getInputStream()) {
                NativeImage image = NativeImage.read(stream);
                NativeImageBackedTexture texture = new NativeImageBackedTexture(image);
                Identifier id = new Identifier("tlotd", "cape/" + uuid.replace("-", ""));

                CountDownLatch latch = new CountDownLatch(1);
                AtomicReference<Identifier> result = new AtomicReference<>();

                MC.execute(() -> {
                    try {
                        if (MC.getTextureManager() != null) {
                            MC.getTextureManager().registerTexture(id, texture);
                            CAPE_CACHE.put(uuid, id);
                            CAPE_CACHE_TIMESTAMPS.put(uuid, Instant.now().toEpochMilli());
                            result.set(id);
                        } else {
                            System.err.println("[TLOTD] TextureManager is null for " + uuid);
                            CAPE_CACHE.put(uuid, NO_CAPE);
                            CAPE_CACHE_TIMESTAMPS.put(uuid, now);
                        }
                    } finally {
                        latch.countDown();
                    }
                });

                if (!latch.await(1, TimeUnit.SECONDS)) {
                    System.err.println("[TLOTD] Timeout registering custom cape texture for " + uuid);
                    return null;
                }

                return result.get();
            }

        } catch (Exception e) {
            CAPE_CACHE.put(uuid, NO_CAPE);
            CAPE_CACHE_TIMESTAMPS.put(uuid, now);
            return null;
        }
    }

    public static void clearCache() {
        CAPE_CACHE.clear();
        CAPE_CACHE_TIMESTAMPS.clear();
    }
}