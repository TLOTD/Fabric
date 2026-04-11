package net.tlotd.tick;

import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.FireBlock;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.registry.RegistryKey;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.tlotd.compat.CompatModsCheck;
import net.tlotd.effect.ModEffects;
import net.tlotd.util.EnergyNbtHelper;
import net.tlotd.util.ModAdvancementTriggers;
import net.tlotd.world.ModGlobalState;
import net.tlotd.world.dimension.ModDimensions;

import static net.tlotd.util.AugmentNbtHelper.getAugmentLevel;
import static net.tlotd.world.dimension.ModDimensions.LUNA_LEVEL_KEY;

public class ModServerTickEvents {

    private static int tickCounter = 0;

    public static void registerServerTickEvents() {
        ServerTickEvents.END_SERVER_TICK.register(server -> {
            tickCounter++;
            ServerWorld world = server.getWorld(LUNA_LEVEL_KEY);
            if (world == null) return;
            for (Entity entity : world.iterateEntities()) {
                if (!(entity instanceof LivingEntity living)) continue;
                if (living.isOnFire()) {
                    living.extinguish();
                }
                living.addStatusEffect(new StatusEffectInstance(ModEffects.HYPOXIA, 220, 0, true, false, true));
                living.addStatusEffect(new StatusEffectInstance(StatusEffects.SLOW_FALLING, 220, 0, true, false, true));
                living.addStatusEffect(new StatusEffectInstance(StatusEffects.JUMP_BOOST, 220, 2, true, false, true));
            }
            for (ServerPlayerEntity player : server.getPlayerManager().getPlayerList()) {
                ModAdvancementTriggers.HOLD_ITEM.trigger(player);
                if (player.getWorld().getRegistryKey().equals(LUNA_LEVEL_KEY)) {
                    if (tickCounter % 20 == 0) {
                        extinguishFireBlocksAroundPlayer(player.getServerWorld(), player);
                    }
                }
                if (tickCounter % 20 == 0) {
                    handleDimensionTransfer(player);
                    if (CompatModsCheck.TOUGHASNAILS) {
                        handleThermalAugments(player);
                    }
                }
                if (tickCounter % 40 == 0 && player.getWorld().isSkyVisible(player.getBlockPos())) {
                        for (ItemStack armor : player.getArmorItems()) {
                            if (getAugmentLevel(armor, "tlotd:photosynthesis") > 0 && player.getWorld().isDay()) {
                                if (armor.getDamage() > 0) {
                                    armor.setDamage(Math.max(armor.getDamage() - getAugmentLevel(armor, "tlotd:photosynthesis"), 0));
                                }
                            }
                            if (getAugmentLevel(armor, "tlotd:starlight_blessing") > 0 && player.getWorld().isNight()) {
                                if (armor.getDamage() > 0) {
                                    armor.setDamage(Math.max(armor.getDamage() - getAugmentLevel(armor, "tlotd:starlight_blessing"), 0));
                                }
                            }
                        }
                        for (ItemStack hand : player.getHandItems()) {
                            if (getAugmentLevel(hand, "tlotd:photosynthesis") > 0 && player.getWorld().isDay()) {
                                if (hand.getDamage() > 0) {
                                    hand.setDamage(Math.max(hand.getDamage() - getAugmentLevel(hand, "tlotd:photosynthesis"), 0));
                                }
                            }
                            if (getAugmentLevel(hand, "tlotd:starlight_blessing") > 0 && player.getWorld().isNight()) {
                                if (hand.getDamage() > 0) {
                                    hand.setDamage(Math.max(hand.getDamage() - getAugmentLevel(hand, "tlotd:starlight_blessing"), 0));
                                }
                            }
                        }
                }
                if (tickCounter > 100) {
                    tickCounter = 0;
                }
            }
        });
    }

    private static void handleThermalAugments(ServerPlayerEntity player) {
        NbtCompound tag = new NbtCompound();
        player.writeCustomDataToNbt(tag);
        if (!tag.contains("temperatureLevel")) return;
        int temp = tag.getInt("temperatureLevel");
        if (temp == 2) return;
        boolean needsHeat = temp < 2;
        boolean needsCool = temp > 2;
        DefaultedList<ItemStack> armorInventory = player.getInventory().armor;
        if (armorInventory.size() < 4) return;
        for (int i = 0; i < 4; i++) {
            ItemStack piece = armorInventory.get(i);
            if (piece.isEmpty()) return;
            int levelHeat = getAugmentLevel(piece, "tlotd:thermal_heating");
            int levelCool = getAugmentLevel(piece, "tlotd:thermal_cooling");
            boolean valid =
                    (needsHeat && levelHeat > 0) ||
                            (needsCool && levelCool > 0);
            if (!valid) return;
            long energy = EnergyNbtHelper.getEnergy(piece);
            if (energy < 500) return;
        }
        for (int i = 0; i < 4; i++) {
            ItemStack piece = armorInventory.get(i);
            long energy = EnergyNbtHelper.getEnergy(piece);
            EnergyNbtHelper.setEnergy(piece, energy - 500);
        }
        tag.putInt("temperatureLevel", 2);
        player.readCustomDataFromNbt(tag);
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

        int warpHeightTerra = 1000;
        int warpHeightLuna = 1000;
        int warpHeightOutTerra = 320;
        int warpHeightOutLuna = 100;
        int terraResistance = 400;
        if (player.getServer() != null) {
            ModGlobalState globalState = ModGlobalState.get(player.getServer());
            warpHeightTerra = globalState.warpHeightOutOfTerra();
            warpHeightLuna = globalState.warpHeightOutOfLuna();
            warpHeightOutTerra = globalState.warpHeightIntoTerra();
            warpHeightOutLuna = globalState.warpHeightIntoLuna();
            terraResistance = globalState.terraResistance();
        }

        if (currentKey.equals(overworldKey) && pos.getY() > warpHeightTerra) {
            ServerWorld luna = server.getWorld(lunaKey);
            if (luna != null) {
                teleportPlayer(player, luna, new BlockPos(pos.getX(), warpHeightOutLuna, pos.getZ()));
            }
        } else if (currentKey.equals(lunaKey) && pos.getY() > warpHeightLuna) {
            ServerWorld overworld = server.getWorld(overworldKey);
            if (overworld != null) {
                teleportPlayer(player, overworld, new BlockPos(pos.getX(), warpHeightOutTerra, pos.getZ()));
                if (terraResistance != 0) {
                    player.addStatusEffect(new StatusEffectInstance(StatusEffects.RESISTANCE, terraResistance, 4, false, false, true));
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