package net.tlotd.networking;

import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.server.network.ServerPlayerEntity;

public class TextureSyncNetworking {

    public static void registerPayloads() {
        PayloadTypeRegistry.playS2C().register(
                TextureSyncPayload.ID,
                TextureSyncPayload.CODEC
        );
    }

    public static void registerClientReceiver() {
        ClientPlayNetworking.registerGlobalReceiver(
                TextureSyncPayload.ID,
                (payload, context) -> context.client().execute(
                        () -> ClientTextureCache.update(payload.textures())
                )
        );
    }

    public static void sendToClient(
            ServerPlayerEntity player,
            TextureSyncPayload payload
    ) {
        ServerPlayNetworking.send(player, payload);
    }
}