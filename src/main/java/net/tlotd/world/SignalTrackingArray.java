package net.tlotd.world;

import net.minecraft.item.Item;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtElement;
import net.minecraft.nbt.NbtList;
import net.minecraft.nbt.NbtString;
import net.minecraft.registry.Registries;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.Identifier;
import net.minecraft.world.PersistentState;

import java.util.*;

public class SignalTrackingArray extends PersistentState {
    private final Set<Identifier> signals = new HashSet<>();

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
            String idStr = elem.asString();
            Identifier id = Identifier.tryParse(idStr);
            if (id != null) state.signals.add(id);
        }

        return state;
    }

    @Override
    public NbtCompound writeNbt(NbtCompound nbt) {
        NbtList list = new NbtList();
        for (Identifier id : signals) {
            list.add(NbtString.of(id.toString()));
        }
        nbt.put("signals", list);
        return nbt;
    }

    public boolean hasSignal(Identifier id) {
        return signals.contains(id);
    }

    public boolean hasSignal(Item item) {
        return hasSignal(Registries.ITEM.getId(item));
    }

    public void addSignal(Identifier id) {
        if (signals.add(id)) markDirty();
    }

    public void addSignal(Item item) {
        addSignal(Registries.ITEM.getId(item));
    }

    public void removeSignal(Identifier id) {
        if (signals.remove(id)) markDirty();
    }

    public void removeSignal(Item item) {
        removeSignal(Registries.ITEM.getId(item));
    }

    public void clearSignals() {
        if (!signals.isEmpty()) {
            signals.clear();
            markDirty();
        }
    }

    public Set<Identifier> getAllSignals() {
        return Collections.unmodifiableSet(signals);
    }

    public int getSignalCount() {
        return Collections.unmodifiableSet(signals).size();
    }

    public boolean hasAnySignals() {
        return !signals.isEmpty();
    }
}