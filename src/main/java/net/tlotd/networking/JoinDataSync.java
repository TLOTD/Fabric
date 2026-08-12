package net.tlotd.networking;

import net.fabricmc.fabric.api.networking.v1.ServerPlayConnectionEvents;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.tlotd.util.EntityDataSaver;
import net.tlotd.world.CustomTextureManager;
import net.tlotd.world.ModGlobalState;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class JoinDataSync {

    public static void init() {
        ServerPlayConnectionEvents.JOIN.register((handler, sender, server) -> {
            ServerPlayerEntity player = handler.getPlayer();
            EntityDataSaver saver = (EntityDataSaver) player;
            NbtCompound data = saver.getPersistentData();
            PlayerDataSyncNetworking.sendToClient(player, new PlayerDataSyncPacket(data));

            ModGlobalState state = ModGlobalState.get(server);
            GlobalConfigNetworking.sendToClient(player, state);

            CustomTextureManager manager = CustomTextureManager.get(server);
            Map<UUID, TextureSyncPacket.Entry> map = new HashMap<>();
            manager.getAll().forEach((uuid, texId) -> {
                String name;
                ServerPlayerEntity online = server.getPlayerManager().getPlayer(uuid);
                if (online != null) {
                    name = online.getDisplayName().getString();
                } else {
                    name = server.getUserCache().getByUuid(uuid).map(profile -> profile.getName()).orElse("Unknown");
                }
                map.put(uuid, new TextureSyncPacket.Entry(texId, name));
            });
            TextureSyncPacket packet = new TextureSyncPacket(map);
            TextureSyncNetworking.sendToClient(player, packet);
        });
    }

    public static void syncAll(ServerWorld world) {
        ModGlobalState state = ModGlobalState.get(world.getServer());
        for (ServerPlayerEntity player : world.getPlayers()) {
            GlobalConfigNetworking.sendToClient(player, state);
        }
        CustomTextureManager manager = CustomTextureManager.get(world.getServer());
        Map<UUID, TextureSyncPacket.Entry> map = new HashMap<>();
        manager.getAll().forEach((uuid, texId) -> {
            String name;
            ServerPlayerEntity online = world.getServer().getPlayerManager().getPlayer(uuid);
            if (online != null) {
                name = online.getDisplayName().getString();
            } else {
                name = world.getServer().getUserCache().getByUuid(uuid).map(profile -> profile.getName()).orElse("Unknown");
            }
            map.put(uuid, new TextureSyncPacket.Entry(texId, name));
        });
        TextureSyncPacket packet = new TextureSyncPacket(map);
        for (ServerPlayerEntity player : world.getPlayers()) {
            TextureSyncNetworking.sendToClient(player, packet);
        }
    }
}