package net.tlotd.world;

import net.minecraft.nbt.NbtCompound;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.world.PersistentState;
import net.minecraft.world.PersistentStateManager;

public class StructurePlacedPersistentState extends PersistentState {
    private boolean placed = false;

    public static StructurePlacedPersistentState get(ServerWorld world) {
        PersistentStateManager manager = world.getPersistentStateManager();
        return manager.getOrCreate(
                StructurePlacedPersistentState::createFromNbt,
                StructurePlacedPersistentState::new,
                "structure_placed"
        );
    }

    public static StructurePlacedPersistentState createFromNbt(NbtCompound nbt) {
        StructurePlacedPersistentState state = new StructurePlacedPersistentState();
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