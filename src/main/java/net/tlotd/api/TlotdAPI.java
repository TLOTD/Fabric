package net.tlotd.api;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.registry.Registries;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.tlotd.world.SignalTrackingArray;

import java.util.*;

public class TlotdAPI {

    //Records
    public record TelevisionSignal(Identifier signalItem, Block offBlock, Block onBlock, int channel) {}
    public record VideoGame(Identifier signalItem, Block tvBlock, Block computerBlock, int gameID) {}

    //enlighted
    public static int enlightened(PlayerEntity player) {
        net.tlotd.util.EntityDataSaver data = (net.tlotd.util.EntityDataSaver) player;
        NbtCompound nbt = data.getPersistentData();
        return nbt.getInt("Enlightened");
    }

    //augments
    public static boolean hasAugment(ItemStack stack, String augmentId) {
        return net.tlotd.util.AugmentNbtHelper.getAugmentLevel(stack, augmentId) > 0;
    }
    public static int getAugmentLevel(ItemStack stack, String augmentId) {
        return net.tlotd.util.AugmentNbtHelper.getAugmentLevel(stack, augmentId);
    }
    public static void addOrUpdateAugment(ItemStack stack, String augmentId, int newLevel, int max) {
        net.tlotd.util.AugmentNbtHelper.addOrUpdateAugment(stack, augmentId, newLevel, max);
    }

    //Custom Texture Manager
    public static int getCustomTexture(MinecraftServer server, UUID player) {
        return net.tlotd.world.CustomTextureManager.get(server).getTexture(player);
    }
    public static boolean hasCustomTexture(MinecraftServer server, UUID player) {
        return net.tlotd.world.CustomTextureManager.get(server).hasTexture(player);
    }
    public static class TlotdTextureEntry {
        private final int textureId;
        private final String playerName;
        public TlotdTextureEntry(int textureId, String playerName) { this.textureId = textureId; this.playerName = playerName; }
        public int getTextureId() { return textureId; }
        public String getPlayerName() { return playerName; }
    }
    public static Map<UUID, TlotdTextureEntry> getClientTextures() {
        Map<UUID, TlotdTextureEntry> result = new HashMap<>();
        net.tlotd.networking.ClientTextureCache.TEXTURES.forEach((uuid, internalEntry) ->
                result.put(uuid, new TlotdTextureEntry(internalEntry.textureId, internalEntry.playerName)));
        return Collections.unmodifiableMap(result);
    }

    //Signals
    public static boolean hasStation(ServerWorld world, BlockPos pos) {
        return net.tlotd.world.SignalTrackingArray.get(world).hasStation(pos);
    }
    public static net.tlotd.world.RadioStation getStation(ServerWorld world, BlockPos pos) {
        return net.tlotd.world.SignalTrackingArray.get(world).getStation(pos);
    }
    public static void addStation(ServerWorld world, BlockPos pos, int strength, int range) {
        net.tlotd.world.SignalTrackingArray.get(world).addStation(pos, strength, range);
    }
    public static void addStation(String name, ServerWorld world, BlockPos pos, int strength, int range) {
        net.tlotd.world.SignalTrackingArray.get(world).addStation(name, pos, strength, range);
    }
    public static void removeStation(ServerWorld world, BlockPos pos) {
        net.tlotd.world.SignalTrackingArray.get(world).removeStation(pos);
    }
    public static Collection<net.tlotd.world.RadioStation> getStations(ServerWorld world) {
        return net.tlotd.world.SignalTrackingArray.get(world).getStations();
    }
    public static boolean hasStations(ServerWorld world) {
        return net.tlotd.world.SignalTrackingArray.get(world).hasStations();
    }
    public static void clearStations(ServerWorld world) {
        net.tlotd.world.SignalTrackingArray.get(world).clearStations();
    }
    public static int getStationCount(ServerWorld world) {
        return net.tlotd.world.SignalTrackingArray.get(world).getStationCount();
    }
    public static void addSignal(ServerWorld world, BlockPos pos, Identifier track) {
        net.tlotd.world.SignalTrackingArray.get(world).addSignal(pos, track);
    }
    public static void removeSignal(ServerWorld world, BlockPos pos, Identifier track) {
        net.tlotd.world.SignalTrackingArray.get(world).removeSignal(pos, track);
    }
    public static void setStrength(ServerWorld world, BlockPos pos, int strength) {
        net.tlotd.world.SignalTrackingArray.get(world).setStrength(pos, strength);
    }
    public static void setRange(ServerWorld world, BlockPos pos, int range) {
        net.tlotd.world.SignalTrackingArray.get(world).setRange(pos, range);
    }
    public static net.tlotd.world.RadioStation getBestStation(ServerWorld world, BlockPos pos) {
        return net.tlotd.world.SignalTrackingArray.get(world).getBestStation(pos);
    }
    public static List<Identifier> getAvailableSignals(ServerWorld world, BlockPos pos) {
        return net.tlotd.world.SignalTrackingArray.get(world).getAvailableSignals(pos);
    }
    public static List<Object> getAvailableSignalsUnsafe(Object world, Object pos) {
        return Collections.singletonList(SignalTrackingArray.get((ServerWorld) world).getAvailableSignals((BlockPos) pos));
    }

    //TV Signal Registry
    public static BlockState handleTelevisionUse(BlockState state, World world, BlockPos pos, PlayerEntity player) {
        return net.tlotd.block.custom.TelevisionBlock.handleTelevisionUse(state, world, pos, player);
    }

    public static Object handleTelevisionUseUnsafe(Object state, Object world, Object pos, Object player) {
        try {
            return net.tlotd.block.custom.TelevisionBlock.handleTelevisionUse((BlockState) state, (World) world, (BlockPos) pos, (PlayerEntity) player);
        } catch (ClassCastException e) {
            throw new IllegalArgumentException(
                    "Invalid parameter types supplied to handleTelevisionUseUnsafe",
                    e
            );
        }
    }

    public static Collection<TelevisionSignal> getAllTelevisionSignals() {
        return net.tlotd.util.TelevisionSignalRegistry.getAll().stream().map(e -> new TelevisionSignal(e.signalItem(), e.offBlock(), e.onBlock(), e.channel())).toList();
    }
    public static Optional<TelevisionSignal> findTelevisionSignal(Identifier signalId) {
        return net.tlotd.util.TelevisionSignalRegistry.findBySignal(signalId).map(e -> new TelevisionSignal(e.signalItem(), e.offBlock(), e.onBlock(), e.channel()));
    }
    public static void registerTelevisionSignal(TelevisionSignal entry) {
        net.tlotd.util.TelevisionSignalRegistry.register(new net.tlotd.util.TelevisionSignalRegistry.SignalEntry(entry.signalItem(), entry.offBlock(), entry.onBlock(), entry.channel()));
    }
    public static void registerTelevisionSignalBatch(Identifier[] itemIds, Block offBlock, Block onBlock, int startingChannel) {
        net.tlotd.util.TelevisionSignalRegistry.registerBatch(itemIds, offBlock, onBlock, startingChannel);
    }

    public static void registerTelevisionSignalBatchUnsafe(String[] itemModIds, String[] itemIds, String offBlockModId, String offBlockId, String onBlockModId, String onBlockId, int startingChannel) {
        if (itemModIds.length != itemIds.length) {
            throw new IllegalArgumentException("itemModIds and itemIds must have the same length");
        }
        Identifier[] identifiers = new Identifier[itemIds.length];
        for (int i = 0; i < itemIds.length; i++) {
            identifiers[i] = Identifier.of(itemModIds[i], itemIds[i]);
        }
        Block offBlock = Registries.BLOCK.get(Identifier.of(offBlockModId, offBlockId));
        Block onBlock = Registries.BLOCK.get(Identifier.of(onBlockModId, onBlockId));
        registerTelevisionSignalBatch(identifiers, offBlock, onBlock, startingChannel);
    }

    //Video Game Registry
    public static BlockState handleComputerUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand) {
        return net.tlotd.block.custom.ComputerBlock.handleComputerUse(state, world, pos, player, hand);
    }

    public static Object handleComputerUseUnsafe(Object state, Object world, Object pos, Object player, Object hand) {
        try {
            return net.tlotd.block.custom.ComputerBlock.handleComputerUse(
                    (BlockState) state, (World) world, (BlockPos) pos, (PlayerEntity) player, (Hand) hand);
        } catch (ClassCastException e) {
            throw new IllegalArgumentException(
                    "Invalid parameter types supplied to handleTelevisionUseUnsafe",
                    e
            );
        }
    }

    public static Collection<VideoGame> getAllVideoGames() {
        return net.tlotd.util.VideoGameRegistry.getAll().stream().map(e -> new VideoGame(e.signalItem(), e.tvBlock(), e.computerBlock(), e.gameID())).toList();
    }
    public static Optional<VideoGame> findVideoGame(Identifier signalId) {
        return net.tlotd.util.VideoGameRegistry.findBySignal(signalId).map(e -> new VideoGame(e.signalItem(), e.tvBlock(), e.computerBlock(), e.gameID()));
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

    public static void registerVideoGameBatchUnsafe(String[] itemModIds, String[] itemIds, String offBlockModId, String offBlockId, String onBlockModId, String onBlockId, int startingGameID) {
        if (itemModIds.length != itemIds.length) {
            throw new IllegalArgumentException("itemModIds and itemIds must have the same length");
        }
        Identifier[] identifiers = new Identifier[itemIds.length];
        for (int i = 0; i < itemIds.length; i++) {
            identifiers[i] = Identifier.of(itemModIds[i], itemIds[i]);
        }
        Block offBlock = Registries.BLOCK.get(Identifier.of(offBlockModId, offBlockId));
        Block onBlock = Registries.BLOCK.get(Identifier.of(onBlockModId, onBlockId));
        net.tlotd.util.VideoGameRegistry.registerBatch(identifiers, offBlock, onBlock, startingGameID);
    }

    //TLOTD Stuff
    public static boolean easterEggsClient() {
        return net.tlotd.networking.ClientGlobalConfig.easterEggs;
    }
    public static boolean easterEggs(MinecraftServer server) {
        return net.tlotd.world.ModGlobalState.get(server).easterEggs();
    }
    public static boolean formerTlotdRewardsClient() {
        return net.tlotd.networking.ClientGlobalConfig.formerTlotdRewards;
    }
    public static boolean formerTlotdRewards(MinecraftServer server) {
        return net.tlotd.world.ModGlobalState.get(server).formerTlotdRewards();
    }
}