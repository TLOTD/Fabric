package net.tlotd.world;

import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtElement;
import net.minecraft.nbt.NbtList;
import net.minecraft.nbt.NbtString;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.world.PersistentState;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class SignalTrackingArray extends PersistentState {
    private final Set<String> signals = new HashSet<>();

    public static SignalTrackingArray get(ServerWorld world) {
        return world.getPersistentStateManager().getOrCreate(
                SignalTrackingArray::createFromNbt,
                SignalTrackingArray::new,
                "tlotd_signals"
        );
    }

    public static SignalTrackingArray createFromNbt(NbtCompound nbt) {
        SignalTrackingArray state = new SignalTrackingArray();
        NbtList list = nbt.getList("signals", NbtElement.STRING_TYPE);

        for (NbtElement elem : list) {
            state.signals.add(elem.asString());
        }

        return state;
    }

    @Override
    public NbtCompound writeNbt(NbtCompound nbt) {
        NbtList list = new NbtList();
        for (String signal : signals) {
            list.add(NbtString.of(signal));
        }
        nbt.put("signals", list);
        return nbt;
    }

    public boolean hasSignal(String signal) {
        return signals.contains(signal);
    }

    public boolean hasAnySignals() {
        return !signals.isEmpty();
    }

    public int getSignalCount() {
        return signals.size();
    }

    public Set<String> getAllSignals() {
        return Set.copyOf(signals); // immutable copy
    }

    public List<String> getAllSignalsList() {
        return new ArrayList<>(signals); // index-based
    }

    public String getSignalAt(int index) {
        List<String> list = getAllSignalsList();
        if (index < 0 || index >= list.size()) {
            return null;
        }
        return list.get(index);
    }

    public void addSignal(String signal) {
        if (signals.add(signal)) {
            markDirty();
        }
    }

    public void removeSignal(String signal) {
        if (signals.remove(signal)) {
            markDirty();
        }
    }

    public void clearSignals() {
        if (!signals.isEmpty()) {
            signals.clear();
            markDirty();
        }
    }
}