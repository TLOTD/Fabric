package net.tlotd.networking;

import io.netty.buffer.Unpooled;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.Identifier;
import net.tlotd.TLOTD;

public class TextureSyncNetworking {

    public static final Identifier TEXTURE_SYNC_ID = new Identifier(TLOTD.MOD_ID, "texture_sync");

    public static void registerClientReceiver() {
        ClientPlayNetworking.registerGlobalReceiver(TEXTURE_SYNC_ID, (client, handler, buf, responseSender) -> {
            TextureSyncPacket packet = TextureSyncPacket.read(buf);
            client.execute(() -> {
                ClientTextureCache.update(packet.textures);
            });
        });
    }

    public static void sendToClient(ServerPlayerEntity player, TextureSyncPacket packet) {
        PacketByteBuf buf = new PacketByteBuf(Unpooled.buffer());
        packet.write(buf);
        ServerPlayNetworking.send(player, TEXTURE_SYNC_ID, buf);
    }
}