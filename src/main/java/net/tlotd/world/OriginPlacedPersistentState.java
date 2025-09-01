package net.tlotd.world;

import net.minecraft.nbt.NbtCompound;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.world.PersistentState;
import net.minecraft.world.PersistentStateManager;

public class OriginPlacedPersistentState extends PersistentState {
    private boolean placed = false;

    public static OriginPlacedPersistentState get(ServerWorld world) {
        PersistentStateManager manager = world.getPersistentStateManager();
        return manager.getOrCreate(
                OriginPlacedPersistentState::createFromNbt,
                OriginPlacedPersistentState::new,
                "tlotd_gate_placed"
        );
    }

    public static OriginPlacedPersistentState createFromNbt(NbtCompound nbt) {
        OriginPlacedPersistentState state = new OriginPlacedPersistentState();
        state.placed = nbt.getBoolean("placed");
        return state;
    }

    @Override
    public NbtCompound writeNbt(NbtCompound nbt) {
        nbt.putBoolean("placed", placed);
        return nbt;
    }

    public boolean isPlaced() {
        return placed;
    }

    public void setPlaced(boolean placed) {
        this.placed = placed;
        markDirty();
    }
}