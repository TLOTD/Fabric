package net.tlotd.networking;

import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.minecraft.util.Identifier;
import net.tlotd.TLOTD;
import net.tlotd.networking.packet.FluidSyncS2CPacket;

public class ModMessages {
    public static final Identifier FLUID_SYNC = new Identifier(TLOTD.MOD_ID, "fluid_sync");

    public static void registerC2SPackets() {

    }

    public static void registerS2CPackets() {
        ClientPlayNetworking.registerGlobalReceiver(FLUID_SYNC, FluidSyncS2CPacket::receive);
    }
}