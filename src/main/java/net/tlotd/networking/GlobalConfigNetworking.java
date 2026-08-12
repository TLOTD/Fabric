package net.tlotd.networking;

import io.netty.buffer.Unpooled;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.Identifier;
import net.tlotd.TLOTD;
import net.tlotd.world.ModGlobalState;

public class GlobalConfigNetworking {

    public static final Identifier CONFIG_SYNC_ID = new Identifier(TLOTD.MOD_ID, "global_config_sync");

    public static void registerClientReceiver() {
        ClientPlayNetworking.registerGlobalReceiver(CONFIG_SYNC_ID, (client, handler, buf, responseSender) -> {
            GlobalConfigSyncPacket packet = GlobalConfigSyncPacket.read(buf);
            client.execute(() -> {
                if (packet.data != null) {
                    ClientGlobalConfig.update(packet.data);
                }
            });
        });
    }

    public static void sendToClient(ServerPlayerEntity player, ModGlobalState state) {
        PacketByteBuf buf = new PacketByteBuf(Unpooled.buffer());
        NbtCompound tag = new NbtCompound();
        state.writeNbt(tag);
        new GlobalConfigSyncPacket(tag).write(buf);
        ServerPlayNetworking.send(player, CONFIG_SYNC_ID, buf);
    }
}