package net.tlotd.networking;

import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;
import net.minecraft.client.MinecraftClient;
import net.tlotd.block.entity.WitchingTableBlockEntity;
import net.tlotd.gui.WitchingTableGUIHandler;
import net.tlotd.util.FluidStack;

public class FluidSyncNetworking {
    public static void registerPayloads() {
        PayloadTypeRegistry.playS2C().register(FluidSyncPayload.ID, FluidSyncPayload.CODEC);
    }

    public static void registerClientReceiver() {
        ClientPlayNetworking.registerGlobalReceiver(FluidSyncPayload.ID, (payload, context) -> {
            MinecraftClient client = context.client();
            client.execute(() -> {
                if (client.world == null) {
                    return;
                }
                if (client.world.getBlockEntity(payload.pos()) instanceof WitchingTableBlockEntity blockEntity) {
                    blockEntity.setFluidLevel(payload.variant(), payload.amount());

                    if (client.player != null && client.player.currentScreenHandler instanceof WitchingTableGUIHandler screenHandler && screenHandler.blockEntity.getPos().equals(payload.pos())) {
                        screenHandler.setFluid(new FluidStack(payload.variant(), payload.amount()));
                    }
                }
            });
        });
    }
}
