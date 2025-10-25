package net.tlotd.api;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.tlotd.util.VideoGameRegistry;

import java.util.Collection;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

public class TlotdAPI {

    public record TelevisionSignal(Identifier signalItem, Block offBlock, Block onBlock, int channel) {}

    public record VideoGame(Identifier signalItem, Block tvBlock, Block computerBlock, int gameID) {}

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

    //Video Game Registry
    public static BlockState handleComputerUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand) {
        return net.tlotd.block.custom.ComputerBlock.handleComputerUse(state, world, pos, player, hand);
    }

    public static Collection<VideoGame> getAllVideoGames() {
        return net.tlotd.util.VideoGameRegistry.getAll().stream()
                .map(e -> new VideoGame(e.signalItem(), e.tvBlock(), e.computerBlock(), e.gameID()))
                .toList();
    }
    public static Optional<VideoGame> findVideoGame(Identifier signalId) {
        return net.tlotd.util.VideoGameRegistry.findBySignal(signalId)
                .map(e -> new VideoGame(e.signalItem(), e.tvBlock(), e.computerBlock(), e.gameID()));
    }
    public static void registerVideoGame(VideoGame entry) {
        net.tlotd.util.VideoGameRegistry.register(new net.tlotd.util.VideoGameRegistry.SignalEntry(
                entry.signalItem(),
                entry.tvBlock(),
                entry.computerBlock(),
                entry.gameID()
        ));
    }
    public static void registerVideoGameBatch(Identifier[] itemIds, Block offBlock, Block onBlock, int startingGameID) {
        net.tlotd.util.VideoGameRegistry.registerBatch(itemIds, offBlock, onBlock, startingGameID);
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