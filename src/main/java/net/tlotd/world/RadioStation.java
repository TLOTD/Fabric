package net.tlotd.world;

import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtElement;
import net.minecraft.nbt.NbtList;
import net.minecraft.nbt.NbtString;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;

import java.util.*;

public class RadioStation {

    private String name;
    private final BlockPos pos;
    private boolean active;
    private int strength;
    private int range;

    private final List<Identifier> signals = new ArrayList<>();

    public RadioStation(String name, BlockPos pos, int strength, int range) {
        this.name = name;
        this.pos = pos;
        this.strength = strength;
        this.range = range;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public BlockPos getPos() {
        return pos;
    }

    public int getStrength() {
        return strength;
    }

    public void setStrength(int strength) {
        this.strength = strength;
    }

    public int getRange() {
        return range;
    }

    public void setRange(int range) {
        this.range = range;
    }

    public List<Identifier> getSignals() {
        return Collections.unmodifiableList(signals);
    }

    public boolean hasSignal(Identifier id) {
        return signals.contains(id);
    }

    public void addSignal(Identifier id) {
        if (!signals.contains(id)) {
            signals.add(id);
        }
    }

    public void removeSignal(Identifier id) {
        signals.remove(id);
    }

    public void clearSignals() {
        signals.clear();
    }

    public boolean hasSignals() {
        return !signals.isEmpty();
    }

    public NbtCompound writeNbt() {
        NbtCompound nbt = new NbtCompound();
        nbt.putString("name", name);
        nbt.putLong("pos", pos.asLong());
        nbt.putBoolean("active", active);
        nbt.putInt("strength", strength);
        nbt.putInt("range", range);
        NbtList list = new NbtList();
        for (Identifier id : signals) {
            list.add(NbtString.of(id.toString()));
        }
        nbt.put("signals", list);
        return nbt;
    }

    public static RadioStation fromNbt(NbtCompound nbt) {
        String name = nbt.getString("name");
        BlockPos pos = BlockPos.fromLong(nbt.getLong("pos"));
        boolean active = nbt.getBoolean("active");
        int strength = nbt.getInt("strength");
        int range = nbt.getInt("range");
        RadioStation station = new RadioStation(name, pos, strength, range);
        station.setActive(active);
        NbtList list = nbt.getList("signals", NbtElement.STRING_TYPE);
        for (NbtElement element : list) {
            Identifier id = Identifier.tryParse(element.asString());
            if (id != null) {
                station.addSignal(id);
            }
        }
        return station;
    }
}