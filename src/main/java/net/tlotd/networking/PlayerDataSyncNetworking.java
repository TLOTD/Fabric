package net.tlotd.networking;

import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.server.network.ServerPlayerEntity;
import net.tlotd.util.EntityDataSaver;

public class PlayerDataSyncNetworking {

    public static void registerPayloads() {
        PayloadTypeRegistry.playS2C().register(PlayerDataSyncPayload.ID, PlayerDataSyncPayload.CODEC);
    }

    public static void registerClientReceiver() {
        ClientPlayNetworking.registerGlobalReceiver(PlayerDataSyncPayload.ID, (payload, context) -> {
            context.client().execute(() -> {
                if (context.client().player != null) {
                    EntityDataSaver saver = (EntityDataSaver) context.client().player;
                    saver.getPersistentData().copyFrom(payload.data());
                }
            });
        });
    }

    public static void sendToClient(ServerPlayerEntity player, PlayerDataSyncPayload payload) {
        ServerPlayNetworking.send(player, payload);
    }
}