package net.tlotd.tick;

import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.FireBlock;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.registry.RegistryKey;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.tlotd.config.ModConfigs;
import net.tlotd.effect.ModEffects;
import net.tlotd.world.dimension.ModDimensions;

import static net.tlotd.world.dimension.ModDimensions.LUNA_LEVEL_KEY;

public class ModServerTickEvents {

    private static int tickCounter = 0;

    public static void registerServerTickEvents() {
        ServerTickEvents.END_SERVER_TICK.register(server -> {
            tickCounter++;
            for (ServerPlayerEntity player : server.getPlayerManager().getPlayerList()) {
                if (player.getWorld().getRegistryKey().equals(LUNA_LEVEL_KEY)) {
                    if (player.isOnFire()) {
                        player.extinguish();
                    }
                    StatusEffectInstance h = new StatusEffectInstance(ModEffects.HYPOXIA, 220, 0, true, false, true);
                    StatusEffectInstance s = new StatusEffectInstance(StatusEffects.SLOW_FALLING, 220, 0, true, false, true);
                    StatusEffectInstance j = new StatusEffectInstance(StatusEffects.JUMP_BOOST, 220, 2, true, false, true);
                    player.addStatusEffect(h);
                    player.addStatusEffect(s);
                    player.addStatusEffect(j);
                    if (tickCounter % 20 == 0) {
                        extinguishFireBlocksAroundPlayer(player.getServerWorld(), player);
                    }
                }
                if (tickCounter % 20 == 0) {
                    handleDimensionTransfer(player);
                }
            }
        });
    }

    private static void extinguishFireBlocksAroundPlayer(ServerWorld world, ServerPlayerEntity player) {
        BlockPos playerPos = player.getBlockPos();
        int radius = 8;
        BlockPos.Mutable mutablePos = new BlockPos.Mutable();
        for (int dx = -radius; dx <= radius; dx++) {
            for (int dy = -2; dy <= 4; dy++) {
                for (int dz = -radius; dz <= radius; dz++) {
                    mutablePos.set(playerPos.getX() + dx, playerPos.getY() + dy, playerPos.getZ() + dz);
                    BlockState state = world.getBlockState(mutablePos);
                    if (state.getBlock() instanceof FireBlock) {
                        world.setBlockState(mutablePos, Blocks.AIR.getDefaultState());
                    }
                }
            }
        }
    }

    private static void handleDimensionTransfer(ServerPlayerEntity player) {
        ServerWorld currentWorld = player.getServerWorld();
        RegistryKey<World> currentKey = currentWorld.getRegistryKey();
        MinecraftServer server = currentWorld.getServer();
        RegistryKey<World> lunaKey = ModDimensions.LUNA_LEVEL_KEY;
        RegistryKey<World> overworldKey = World.OVERWORLD;
        BlockPos pos = player.getBlockPos();
        if (currentKey.equals(overworldKey) && pos.getY() > ModConfigs.TERRA_WARP_HEIGHT_THRESHOLD) {
            ServerWorld luna = server.getWorld(lunaKey);
            if (luna != null) {
                teleportPlayer(player, luna, new BlockPos(pos.getX(), ModConfigs.LUNAR_WARP_DESTINATION_HEIGHT, pos.getZ()));
            }
        } else if (currentKey.equals(lunaKey) && pos.getY() > ModConfigs.LUNAR_WARP_HEIGHT_THRESHOLD) {
            ServerWorld overworld = server.getWorld(overworldKey);
            if (overworld != null) {
                teleportPlayer(player, overworld, new BlockPos(pos.getX(), ModConfigs.TERRA_WARP_DESTINATION_HEIGHT, pos.getZ()));
                if (ModConfigs.TERRA_FALL_DISTANCE_RESISTANCE != 0) {
                    player.addStatusEffect(new StatusEffectInstance(StatusEffects.RESISTANCE, ModConfigs.TERRA_FALL_DISTANCE_RESISTANCE, 4, false, false, true));
                }
            }
        }
    }

    private static void teleportPlayer(ServerPlayerEntity player, ServerWorld destination, BlockPos targetPos) {
        player.setVelocity(0,0,0);
        player.teleport(destination, targetPos.getX() + 0.5, targetPos.getY(), targetPos.getZ() + 0.5,
                player.getYaw(), player.getPitch());
    }
}
