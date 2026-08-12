package net.tlotd.world;

import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtElement;
import net.minecraft.nbt.NbtList;
import net.minecraft.nbt.NbtString;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.PersistentState;
import org.jetbrains.annotations.Nullable;

import java.util.*;

public class SignalTrackingArray extends PersistentState {

    private final Set<Identifier> dimensionSignals = new LinkedHashSet<>();
    private static final Map<BlockPos, RadioStation> stations = new HashMap<>();

    public static SignalTrackingArray get(ServerWorld world) {
        return world.getPersistentStateManager().getOrCreate(SignalTrackingArray::createFromNbt, SignalTrackingArray::new, "tlotd_signals");
    }

    public static SignalTrackingArray createFromNbt(NbtCompound nbt) {
        SignalTrackingArray state = new SignalTrackingArray();
        NbtList stationList = nbt.getList("stations", NbtElement.COMPOUND_TYPE);
        for (NbtElement element : stationList) {
            RadioStation station = RadioStation.fromNbt((NbtCompound) element);
            stations.put(station.getPos(), station);
        }
        NbtList dimensionList = nbt.getList("dimensional_signals", NbtElement.STRING_TYPE);
        for (NbtElement element : dimensionList) {
            Identifier id = Identifier.tryParse(element.asString());

            if (id != null) {
                state.dimensionSignals.add(id);
            }
        }

        return state;
    }

    @Override
    public NbtCompound writeNbt(NbtCompound nbt) {
        NbtList stationList = new NbtList();
        for (RadioStation station : stations.values()) {
            stationList.add(station.writeNbt());
        }
        nbt.put("stations", stationList);
        NbtList dimensionList = new NbtList();
        for (Identifier id : dimensionSignals) {
            dimensionList.add(NbtString.of(id.toString()));
        }
        nbt.put("dimension_signals", dimensionList);
        return nbt;
    }

    public List<Identifier> getAvailableSignals(BlockPos receiverPos) {
        LinkedHashSet<Identifier> available = new LinkedHashSet<>();
        RadioStation station = getBestStation(receiverPos);
        if (station != null) {
            available.addAll(station.getSignals());
        }
        available.addAll(dimensionSignals);
        return List.copyOf(available);
    }

    public boolean hasStation(BlockPos pos) {
        return stations.containsKey(pos);
    }

    public RadioStation getStation(BlockPos pos) {
        return stations.get(pos);
    }

    public void addStation(BlockPos pos, int strength, int range) {
        if (stations.containsKey(pos)) return;
        stations.put(pos, new RadioStation(pos, strength, range));
        markDirty();
    }

    public void removeStation(BlockPos pos) {
        if (stations.remove(pos) != null) {
            markDirty();
        }
    }

    public Collection<RadioStation> getStations() {
        return Collections.unmodifiableCollection(stations.values());
    }

    public boolean hasStations() {
        return !stations.isEmpty();
    }

    public void clearStations() {
        if (!stations.isEmpty()) {
            stations.clear();
            markDirty();
        }
    }

    public int getStationCount() {
        return stations.size();
    }

    public void addSignal(BlockPos pos, Identifier signal) {
        RadioStation station = stations.get(pos);
        if (station == null) return;
        if (!station.hasSignal(signal)) {
            station.addSignal(signal);
            markDirty();
        }
    }

    public void removeSignal(BlockPos pos, Identifier signal) {
        RadioStation station = stations.get(pos);
        if (station == null) return;
        if (station.hasSignal(signal)) {
            station.removeSignal(signal);
            if (station.getSignals().isEmpty()) {
                station.setActive(false);
            }
            markDirty();
        }
    }

    public void setStrength(BlockPos pos, int strength) {
        RadioStation station = stations.get(pos);
        if (station == null) return;
        if (station.getStrength() != strength) {
            station.setStrength(strength);
            markDirty();
        }
    }

    public void setRange(BlockPos pos, int range) {
        RadioStation station = stations.get(pos);
        if (station == null) return;
        if (station.getRange() != range) {
            station.setRange(range);
            markDirty();
        }
    }

    public void addTrackToAll(Identifier id) {
        for (RadioStation station : stations.values()) {
            station.addSignal(id);
        }
        markDirty();
    }

    public void removeTrackFromAll(Identifier id) {
        boolean changed = false;
        for (RadioStation station : stations.values()) {
            if (station.hasSignal(id)) {
                station.removeSignal(id);
                changed = true;
            }
        }
        if (changed) {
            markDirty();
        }
    }

    public void clearAllTracks() {
        boolean changed = false;
        for (RadioStation station : stations.values()) {
            if (!station.getSignals().isEmpty()) {
                station.clearSignals();
                changed = true;
            }
        }
        if (changed) {
            markDirty();
        }
    }

    @Nullable
    public static RadioStation getBestStation(BlockPos radioPos) {
        RadioStation best = null;
        double bestQuality = -1;
        for (RadioStation station : stations.values()) {
            if (!station.isActive()) {
                continue;
            }
            double quality = calculateSignalQuality(radioPos, station);
            if (quality > bestQuality) {
                best = station;
                bestQuality = quality;
            }
        }
        return best;
    }

    public static double calculateSignalQuality(BlockPos radioPos, RadioStation station) {
        int range = getRange(station.getRange());
        double distance = Math.sqrt(radioPos.getSquaredDistance(station.getPos()));
        if (distance > range) {
            return -1;
        }
        double normalizedDistance = 1.0 - distance / range;
        double strengthWeight = switch (station.getStrength()) {
            case 1 -> 1.0;
            case 2 -> 2.0;
            case 3 -> 3.0;
            case 4 -> 4.0;
            default -> 0.0;
        };
        return normalizedDistance * strengthWeight;
    }

    public static int getRange(int range) {
        return switch (range) {
            case 1 -> 128;
            case 2 -> 512;
            case 3 -> 2048;
            case 4 -> 8192;
            default -> 0;
        };
    }

    public static Text getCountText(int count) {
        return switch (count) {
            case 0 -> Text.translatable("block.tlotd.signal_transmitter.signal_count.0", count).formatted(Formatting.GRAY);
            case 1 -> Text.translatable("block.tlotd.signal_transmitter.signal_count.1", count).formatted(Formatting.GRAY);
            default -> Text.translatable("block.tlotd.signal_transmitter.signal_count.n", count).formatted(Formatting.GRAY);
        };
    }

    public static Text getStrenthText(int strength) {
        return switch (strength) {
            case 1 -> Text.translatable("block.tlotd.signal_transmitter.signal_strength.1").formatted(Formatting.RED);
            case 2 -> Text.translatable("block.tlotd.signal_transmitter.signal_strength.2").formatted(Formatting.YELLOW);
            case 3 -> Text.translatable("block.tlotd.signal_transmitter.signal_strength.3").formatted(Formatting.GREEN);
            case 4 -> Text.translatable("block.tlotd.signal_transmitter.signal_strength.4").formatted(Formatting.BLUE);
            case 5 -> Text.translatable("block.tlotd.signal_transmitter.signal_strength.5").formatted(Formatting.WHITE);
            default -> Text.translatable("block.tlotd.signal_transmitter.signal_strength.0").formatted(Formatting.GRAY);
        };
    }

    public static Text getRangeText(int range) {
        return switch (range) {
            case 1 -> Text.translatable("block.tlotd.signal_transmitter.signal_range.1").formatted(Formatting.RED);
            case 2 -> Text.translatable("block.tlotd.signal_transmitter.signal_range.2").formatted(Formatting.YELLOW);
            case 3 -> Text.translatable("block.tlotd.signal_transmitter.signal_range.3").formatted(Formatting.GREEN);
            case 4 -> Text.translatable("block.tlotd.signal_transmitter.signal_range.4").formatted(Formatting.BLUE);
            case 5 -> Text.translatable("block.tlotd.signal_transmitter.signal_range.5").formatted(Formatting.WHITE);
            default -> Text.translatable("block.tlotd.signal_transmitter.signal_range.0").formatted(Formatting.GRAY);
        };
    }

    public static Text getStats(boolean active, int strength, int range, int count, String var) {
        return Text.translatable("block.tlotd.signal_transmitter.stats" + var, active ? Text.translatable("block.tlotd.signal_transmitter.active").formatted(Formatting.GREEN) : Text.translatable("block.tlotd.signal_transmitter.inactive").formatted(Formatting.RED), Text.translatable("block.tlotd.signal_transmitter.signal_strength.one_line", Text.translatable("block.tlotd.signal_transmitter.signal_strength").formatted(Formatting.GRAY), getStrenthText(strength)), Text.translatable("block.tlotd.signal_transmitter.signal_range.one_line", Text.translatable("block.tlotd.signal_transmitter.signal_range").formatted(Formatting.GRAY), getRangeText(range)), getCountText(count)).formatted(Formatting.WHITE);
    }

    public boolean hasDimensionSignal(Identifier id) {
        return dimensionSignals.contains(id);
    }
    public void addDimensionSignal(Identifier id) {
        if (dimensionSignals.add(id)) {
            markDirty();
        }
    }
    public void removeDimensionSignal(Identifier id) {
        if (dimensionSignals.remove(id)) {
            markDirty();
        }
    }
    public void clearDimensionSignals() {
        if (!dimensionSignals.isEmpty()) {
            dimensionSignals.clear();
            markDirty();
        }
    }
    public Set<Identifier> getDimensionSignals() {
        return Collections.unmodifiableSet(dimensionSignals);
    }
    public boolean hasDimensionSignals() {
        return !dimensionSignals.isEmpty();
    }
}