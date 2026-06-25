package net.tlotd.util;

import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.world.World;
import net.tlotd.effect.ModEffects;
import net.tlotd.world.ModGlobalState;
import net.tlotd.world.dimension.ModDimensions;

import java.util.Arrays;

public class NoclipTracker {

    public static void record(ServerPlayerEntity player) {
        EntityDataSaver saver = (EntityDataSaver) player;
        NbtCompound data = saver.getPersistentData();
        ModGlobalState globalState = ModGlobalState.get(player.server);
        double noClipChance = globalState.noClipChance();
        long now = player.getWorld().getTime();
        long[] hits = data.getLongArray("NoclipHits");
        hits = Arrays.stream(hits).filter(t -> now - t <= 100).toArray();
        long[] updated = Arrays.copyOf(hits, hits.length + 1);
        updated[updated.length - 1] = now;
        data.putLongArray("NoclipHits", updated);
        if (updated.length >= 5) {
            data.remove("NoclipHits");
            if (player.getRandom().nextFloat() <= noClipChance) {
                noclip(player);
            }
        }
    }

    public static void noclip(ServerPlayerEntity player) {
        MinecraftServer server = player.getServer();
        ServerWorld target;
        if (player.getWorld().getRegistryKey().equals(ModDimensions.BACKROOMS_LEVEL_KEY)) {
            int warpHeightIntoTerra;
            int terraResistance;
            if (server != null) {
                ModGlobalState globalState = ModGlobalState.get(server);
                warpHeightIntoTerra = globalState.warpHeightIntoTerra();
                terraResistance = globalState.terraResistance();
            } else {
                warpHeightIntoTerra = 320;
                terraResistance = 400;
            }
            target = server.getWorld(World.OVERWORLD);
            if (target == null) {
                return;
            }
            player.teleport(target, 0.5, warpHeightIntoTerra, 0.5, 0.0F, 0.0F);
            player.fallDistance = 0.0F;
            if (terraResistance != 0) {
                player.addStatusEffect(new StatusEffectInstance(StatusEffects.RESISTANCE, terraResistance, 4, false, false, true));
            }
            player.removeStatusEffect(ModEffects.SUBSPACE_RESISTANCE);
            player.removeStatusEffect(ModEffects.SUBSPACE_SICKNESS);
        } else {
            target = server.getWorld(ModDimensions.BACKROOMS_LEVEL_KEY);
            if (target == null) {
                return;
            }
            player.addStatusEffect(new StatusEffectInstance(ModEffects.SUBSPACE_RESISTANCE, 1200, 0, true, false, true));
            player.teleport(target, 2.5, 65, -1.5, player.getYaw(), player.getPitch());
        }
    }
}