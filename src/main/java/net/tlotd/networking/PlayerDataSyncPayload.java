package net.tlotd.networking;

import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.packet.CustomPayload;
import net.minecraft.util.Identifier;
import net.tlotd.TLOTD;

import java.util.Objects;

public record PlayerDataSyncPayload(NbtCompound data) implements CustomPayload {

    public static final CustomPayload.Id<PlayerDataSyncPayload> ID = new CustomPayload.Id<>(Identifier.of(TLOTD.MOD_ID, "player_data_sync"));

    public static final PacketCodec<RegistryByteBuf, PlayerDataSyncPayload> CODEC = PacketCodec.of((payload, buf) -> buf.writeNbt(payload.data()), buf -> new PlayerDataSyncPayload(Objects.requireNonNull(buf.readNbt())));

    @Override
    public CustomPayload.Id<? extends CustomPayload> getId() {
        return ID;
    }
}