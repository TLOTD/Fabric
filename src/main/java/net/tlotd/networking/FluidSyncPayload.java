package net.tlotd.networking;

import net.fabricmc.fabric.api.transfer.v1.fluid.FluidVariant;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.codec.PacketCodecs;
import net.minecraft.network.packet.CustomPayload;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.tlotd.TLOTD;

public record FluidSyncPayload(BlockPos pos, FluidVariant variant, long amount) implements CustomPayload {

    public static final Id<FluidSyncPayload> ID = new Id<>(Identifier.of(TLOTD.MOD_ID, "fluid_sync"));

    public static final PacketCodec<RegistryByteBuf, FluidSyncPayload> CODEC = PacketCodec.tuple(BlockPos.PACKET_CODEC, FluidSyncPayload::pos, FluidVariant.PACKET_CODEC, FluidSyncPayload::variant, PacketCodecs.VAR_LONG, FluidSyncPayload::amount, FluidSyncPayload::new);

    @Override
    public Id<? extends CustomPayload> getId() {
        return ID;
    }
}