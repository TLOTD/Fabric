package net.tlotd.api;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.Collection;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

public class TlotdAPI {

    public record TelevisionSignal(Identifier signalItem, Block offBlock, Block onBlock, int channel) {}

    //Textures
    public static int getCustomTexture(MinecraftServer server, UUID player) {
        return net.tlotd.world.CustomTextureManager.get(server).getTexture(player);
    }
    public static boolean hasCustomTexture(MinecraftServer server, UUID player) {
        return net.tlotd.world.CustomTextureManager.get(server).hasTexture(player);
    }



    //TV Signal List
    public static BlockState handleTelevisionUse(BlockState state, World world, BlockPos pos, PlayerEntity player) {
        return net.tlotd.block.custom.TelevisionBlock.handleTelevisionUse(state, world, pos, player);
    }
    public static Collection<TelevisionSignal> getAllTelevisionSignals() {
        return net.tlotd.util.TelevisionSignalRegistry.getAll().stream()
                .map(e -> new TelevisionSignal(e.signalItem(), e.offBlock(), e.onBlock(), e.channel()))
                .toList();
    }
    public static Optional<TelevisionSignal> findTelevisionSignal(Identifier signalId) {
        return net.tlotd.util.TelevisionSignalRegistry.findBySignal(signalId)
                .map(e -> new TelevisionSignal(e.signalItem(), e.offBlock(), e.onBlock(), e.channel()));
    }
    public static void registerTelevisionSignal(TelevisionSignal entry) {
        net.tlotd.util.TelevisionSignalRegistry.register(new net.tlotd.util.TelevisionSignalRegistry.SignalEntry(
                entry.signalItem(),
                entry.offBlock(),
                entry.onBlock(),
                entry.channel()
        ));
    }
    public static void registerTelevisionSignalBatch(Identifier[] itemIds, Block offBlock, Block onBlock, int startingChannel) {
        net.tlotd.util.TelevisionSignalRegistry.registerBatch(itemIds, offBlock, onBlock, startingChannel);
    }



    //Signals
    public static boolean hasAnySignals(ServerWorld world) {
        return net.tlotd.world.SignalTrackingArray.get(world).hasAnySignals();
    }
    public static boolean hasSignal(ServerWorld world, Identifier id) {
        return net.tlotd.world.SignalTrackingArray.get(world).hasSignal(id);
    }
    public static boolean hasSignal(ServerWorld world, Item item) {
        return net.tlotd.world.SignalTrackingArray.get(world).hasSignal(item);
    }
    public static int getSignalCount(ServerWorld world) {
        return net.tlotd.world.SignalTrackingArray.get(world).getSignalCount();
    }
    public static Set<Identifier> getAllSignals(ServerWorld world) {
        return net.tlotd.world.SignalTrackingArray.get(world).getAllSignals();
    }
    public void addSignal(ServerWorld world, Item item) {
        net.tlotd.world.SignalTrackingArray.get(world).addSignal(item);
    }
    public void removeSignal(ServerWorld world, Item item) {
        net.tlotd.world.SignalTrackingArray.get(world).removeSignal(item);
    }
    public void clearSignals(ServerWorld world) {
        net.tlotd.world.SignalTrackingArray.get(world).clearSignals();
    }

    //TLOTD Stuff
    public static boolean formerTlotdRewards(MinecraftServer server) {
        return net.tlotd.world.ModGlobalState.get(server).formerTlotdRewards();
    }
}