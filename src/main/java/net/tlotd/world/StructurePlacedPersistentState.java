package net.tlotd.world;

import net.minecraft.nbt.NbtCompound;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.world.PersistentState;
import net.minecraft.world.PersistentStateManager;

public class StructurePlacedPersistentState extends PersistentState {
    private boolean structurePlaced = false;

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
        state.structurePlaced = nbt.getBoolean("structure_placed");
        return state;
    }

    @Override
    public NbtCompound writeNbt(NbtCompound nbt) {
        nbt.putBoolean("structure_placed", structurePlaced);
        return nbt;
    }

    public boolean isStructurePlaced() {
        return structurePlaced;
    }

    public void setStructurePlaced(boolean structurePlaced) {
        this.structurePlaced = structurePlaced;
        markDirty();
    }
}