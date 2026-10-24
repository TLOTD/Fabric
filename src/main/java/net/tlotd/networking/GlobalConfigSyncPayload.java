package net.tlotd.networking;

import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.packet.CustomPayload;
import net.minecraft.util.Identifier;
import net.tlotd.TLOTD;

import java.util.Objects;

public record GlobalConfigSyncPayload(NbtCompound data) implements CustomPayload {

    public static final Id<GlobalConfigSyncPayload> ID = new Id<>(Identifier.of(TLOTD.MOD_ID, "global_config_sync"));

    public static final PacketCodec<RegistryByteBuf, GlobalConfigSyncPayload> CODEC = PacketCodec.of((payload, buf) -> buf.writeNbt(payload.data()), buf -> new GlobalConfigSyncPayload(Objects.requireNonNull(buf.readNbt())));

    @Override
    public Id<? extends CustomPayload> getId() {
        return ID;
    }
}