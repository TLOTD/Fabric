package net.tlotd.util;

import net.minecraft.block.Block;
import net.minecraft.util.Identifier;

import java.util.*;

public class VideoGameRegistry {
    private static final Map<Identifier, SignalEntry> SIGNALS = new LinkedHashMap<>();

    public record SignalEntry(Identifier signalItem, Block tvBlock, Block computerBlock, int gameID) {}

    public static Identifier normalize(Identifier id) {
        if (id == null) return null;
        return new Identifier(id.getNamespace().toLowerCase(Locale.ROOT), id.getPath().toLowerCase(Locale.ROOT));
    }

    public static void register(SignalEntry entry) {
        Identifier key = normalize(entry.signalItem());
        if (SIGNALS.containsKey(key)) {
            System.out.println("[TLOTD-Games] Warning: Duplicate registration for signal " + key);
            return;
        }
        SIGNALS.put(key, entry);
    }

    public static void registerBatch(Identifier[] itemIds, Block tvBlock, Block computerBlock, int startinggameID) {
        int gameID = startinggameID;
        for (Identifier id : itemIds) {
            register(new SignalEntry(normalize(id), tvBlock, computerBlock, gameID++));
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

    public static void debugDump() {
        if (SIGNALS.isEmpty()) {
            System.out.println("[VideoGameRegistry] No signals registered.");
            return;
        }
        System.out.println("[VideoGameRegistry] Dumping all registered signals (" + SIGNALS.size() + "):\n");
        for (SignalEntry entry : SIGNALS.values()) {
            System.out.println("Signal: " + entry.signalItem() + "\n  TV Block: " + entry.tvBlock() + "\n  Computer Block: " + entry.computerBlock() + "\n  GameID: " + entry.gameID() + "\n");
        }
        System.out.println("[VideoGameRegistry] End of dump.\n");
    }
}