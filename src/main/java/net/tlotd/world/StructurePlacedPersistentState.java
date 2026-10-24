package net.tlotd.world;

import net.minecraft.nbt.NbtCompound;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.world.PersistentState;
import net.minecraft.world.PersistentStateManager;

public class StructurePlacedPersistentState extends PersistentState {

    private boolean structurePlaced = false;

    private static final Type<StructurePlacedPersistentState> TYPE = new Type<>(
            StructurePlacedPersistentState::new,
            StructurePlacedPersistentState::createFromNbt,
            null
    );

    public static StructurePlacedPersistentState get(ServerWorld world) {
        PersistentStateManager manager = world.getPersistentStateManager();
        return manager.getOrCreate(TYPE, "structure_placed");
    }

    public static StructurePlacedPersistentState createFromNbt(
            NbtCompound nbt,
            RegistryWrapper.WrapperLookup registryLookup
    ) {
        StructurePlacedPersistentState state =
                new StructurePlacedPersistentState();

        state.structurePlaced = nbt.getBoolean("structure_placed");
        return state;
    }

    @Override
    public NbtCompound writeNbt(
            NbtCompound nbt,
            RegistryWrapper.WrapperLookup registryLookup
    ) {
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