package net.tlotd.world;

import net.minecraft.nbt.NbtCompound;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.world.PersistentState;

public class ModGlobalState extends PersistentState {
    private boolean axeStrippingBark = true;
    private boolean extractionOreCompat = true;
    private int elevatorMaxDistance = 100;
    private boolean formerTlotdRewards = false;

    public static ModGlobalState get(MinecraftServer server) {
        ServerWorld overworld = server.getOverworld();
        return overworld.getPersistentStateManager().getOrCreate(ModGlobalState::fromNbt, ModGlobalState::new, "TLOTD_Data");
    }

    private static ModGlobalState fromNbt(NbtCompound nbt) {
        ModGlobalState state = new ModGlobalState();
        state.axeStrippingBark = nbt.getBoolean("AxeStrippingBark");
        state.extractionOreCompat = nbt.getBoolean("ExtractionOreCompat");
        state.elevatorMaxDistance = nbt.getInt("ElevatorMaxDistance");
        state.formerTlotdRewards = nbt.getBoolean("FormerTLOTDRewards");
        return state;
    }

    @Override
    public NbtCompound writeNbt(NbtCompound nbt) {
        nbt.putBoolean("AxeStrippingBark", axeStrippingBark);
        nbt.putBoolean("ExtractionOreCompat", extractionOreCompat);
        nbt.putInt("ElevatorMaxDistance", elevatorMaxDistance);
        nbt.putBoolean("FormerTLOTDRewards", formerTlotdRewards);
        return nbt;
    }

    public boolean strippingDropsBark() {
        return axeStrippingBark;
    }

    public void setStrippingDropsBark(boolean value) {
        this.axeStrippingBark = value;
        markDirty();
    }

    public boolean extractionOreCompat() {
        return extractionOreCompat;
    }

    public void setExtractionOreCompat(boolean value) {
        this.extractionOreCompat = value;
        markDirty();
    }

    public int elevatorMaxDistance() {
        return elevatorMaxDistance;
    }

    public void setElevatorMaxDistance(int value) {
        this.elevatorMaxDistance = value;
        markDirty();
    }

    public boolean formerTlotdRewards() {
        return formerTlotdRewards;
    }

    public void setFormerTlotdRewards(boolean value) {
        this.formerTlotdRewards = value;
        markDirty();
    }
}