package net.tlotd.util;

import net.minecraft.block.Block;
import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;

import java.util.*;

public class TelevisionSignalRegistry {
    private static final Map<Identifier, SignalEntry> SIGNALS = new LinkedHashMap<>();

    public record SignalEntry(Identifier signalItem, Block offBlock, Block onBlock, int channel) {}

    private static Identifier normalize(Identifier id) {
        if (id == null) return null;
        return new Identifier(id.getNamespace().toLowerCase(Locale.ROOT), id.getPath().toLowerCase(Locale.ROOT));
    }

    public static void register(SignalEntry entry) {
        Identifier key = normalize(entry.signalItem());
        if (SIGNALS.containsKey(key)) {
            System.out.println("[TLOTD-TV] Warning: Duplicate registration for signal " + key);
            return;
        }
        SIGNALS.put(key, entry);
    }

    public static void registerAll(SignalEntry... entries) {
        for (SignalEntry entry : entries) register(entry);
    }

    public static void registerBatch(Identifier[] itemIds, Block offBlock, Block onBlock, int startingChannel) {
        int channel = startingChannel;
        for (Identifier id : itemIds) {
            register(new SignalEntry(normalize(id), offBlock, onBlock, channel++));
        }
    }

    public static Optional<SignalEntry> findBySignal(Identifier id) {
        if (id == null) return Optional.empty();
        Identifier normalized = normalize(id);
        return Optional.ofNullable(SIGNALS.get(normalized));
    }

    public static Collection<SignalEntry> getAll() {
        return SIGNALS.values();
    }

    public static boolean blocksMatch(Block a, Block b) {
        if (a == b) return true;
        if (a == null || b == null) return false;
        return Registries.BLOCK.getId(a).equals(Registries.BLOCK.getId(b));
    }

    public static void debugDump() {
        if (SIGNALS.isEmpty()) {
            System.out.println("[TelevisionSignalRegistry] No signals registered.");
            return;
        }
        System.out.println("[TelevisionSignalRegistry] Dumping all registered signals (" + SIGNALS.size() + "):\n");
        for (SignalEntry entry : SIGNALS.values()) {
            System.out.println("Signal: " + entry.signalItem()
                    + "\n  Off Block: " + entry.offBlock()
                    + "\n  On Block: " + entry.onBlock()
                    + "\n  Channel: " + entry.channel()
                    + "\n"); // extra line for spacing
        }
        System.out.println("[TelevisionSignalRegistry] End of dump.\n");
    }
}