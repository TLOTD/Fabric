package net.tlotd.networking;

import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.Identifier;
import net.tlotd.TLOTD;
import net.tlotd.util.EntityDataSaver;

public class PlayerDataSyncNetworking {
    public static final Identifier PLAYER_DATA_SYNC_ID = new Identifier(TLOTD.MOD_ID, "player_data_sync");

    public static void registerClientReceiver() {
        ClientPlayNetworking.registerGlobalReceiver(PLAYER_DATA_SYNC_ID, (client, handler, buf, responseSender) -> {
            PlayerDataSyncPacket packet = PlayerDataSyncPacket.read(buf);
            client.execute(() -> {
                if (client.player != null) {
                    EntityDataSaver saver = (EntityDataSaver) client.player;
                    saver.getPersistentData().copyFrom(packet.data);
                }
            });
        });
    }

    public static void sendToClient(ServerPlayerEntity player, PlayerDataSyncPacket packet) {
        PacketByteBuf buf = PacketByteBufs.create();
        packet.write(buf);
        ServerPlayNetworking.send(player, PLAYER_DATA_SYNC_ID, buf);
    }
}