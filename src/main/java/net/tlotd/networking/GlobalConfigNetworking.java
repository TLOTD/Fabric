package net.tlotd.networking;

import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.server.network.ServerPlayerEntity;
import net.tlotd.util.EntityDataSaver;

public class GlobalConfigNetworking {

    public static void registerPayloads() {
        PayloadTypeRegistry.playS2C().register(GlobalConfigSyncPayload.ID, GlobalConfigSyncPayload.CODEC);
    }

    public static void registerClientReceiver() {
        ClientPlayNetworking.registerGlobalReceiver(GlobalConfigSyncPayload.ID, (payload, context) -> {
            context.client().execute(() -> {
                if (context.client().player != null) {
                    EntityDataSaver saver = (EntityDataSaver) context.client().player;
                    saver.getPersistentData().copyFrom(payload.data());
                }
            });
        });
    }

    public static void sendToClient(ServerPlayerEntity player, GlobalConfigSyncPayload payload) {
        ServerPlayNetworking.send(player, payload);
    }
}