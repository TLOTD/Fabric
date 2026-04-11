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

    private boolean starlightAnvil = true;
    private boolean bloodWitching = true;
    private boolean soulWitching = true;

    private int warpHeightOutOfTerra = 1000;
    private int warpHeightOutOfLuna = 1000;
    private int warpHeightIntoTerra = 320;
    private int warpHeightIntoLuna = 100;
    private int terraResistance = 400;

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

        state.starlightAnvil = nbt.getBoolean("StarlightAnvil");
        state.bloodWitching = nbt.getBoolean("BloodWitching");
        state.soulWitching = nbt.getBoolean("SoulWitching");

        state.warpHeightOutOfTerra = nbt.getInt("WarpHeightOutOfTerra");
        state.warpHeightOutOfLuna = nbt.getInt("WarpHeightOutOfLuna");
        state.warpHeightIntoTerra = nbt.getInt("WarpHeightIntoTerra");
        state.warpHeightIntoLuna = nbt.getInt("WarpHeightIntoLuna");
        state.terraResistance = nbt.getInt("TerraResistance");
        return state;
    }

    @Override
    public NbtCompound writeNbt(NbtCompound nbt) {
        nbt.putBoolean("AxeStrippingBark", axeStrippingBark);
        nbt.putBoolean("ExtractionOreCompat", extractionOreCompat);
        nbt.putInt("ElevatorMaxDistance", elevatorMaxDistance);
        nbt.putBoolean("FormerTLOTDRewards", formerTlotdRewards);

        nbt.putBoolean("StarlightAnvil", starlightAnvil);
        nbt.putBoolean("BloodWitching", bloodWitching);
        nbt.putBoolean("SoulWitching", soulWitching);

        nbt.putInt("WarpHeightOutOfTerra", warpHeightOutOfTerra);
        nbt.putInt("WarpHeightOutOfLuna", warpHeightOutOfLuna);
        nbt.putInt("WarpHeightIntoTerra", warpHeightIntoTerra);
        nbt.putInt("WarpHeightIntoLuna", warpHeightIntoLuna);
        nbt.putInt("TerraResistance", terraResistance);
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



    public boolean starlightAnvil() {
        return starlightAnvil;
    }
    public void setStarlightAnvil(boolean value) {
        this.starlightAnvil = value;
        markDirty();
    }

    public boolean bloodWitching() {
        return bloodWitching;
    }
    public void setBloodWitching(boolean value) {
        this.bloodWitching = value;
        markDirty();
    }

    public boolean soulWitching() {
        return soulWitching;
    }
    public void setSoulWitching(boolean value) {
        this.soulWitching = value;
        markDirty();
    }

    public int warpHeightOutOfTerra() {
        return warpHeightOutOfTerra;
    }
    public void setWarpHeightOutOfTerra(int value) {
        this.warpHeightOutOfTerra = value;
        markDirty();
    }

    public int warpHeightOutOfLuna() {
        return warpHeightOutOfLuna;
    }
    public void setWarpHeightOutOfLuna(int value) {
        this.warpHeightOutOfLuna = value;
        markDirty();
    }

    public int warpHeightIntoTerra() {
        return warpHeightIntoTerra;
    }
    public void setWarpHeightIntoTerra(int value) {
        this.warpHeightIntoTerra = value;
        markDirty();
    }

    public int warpHeightIntoLuna() {
        return warpHeightIntoLuna;
    }
    public void setWarpHeightIntoLuna(int value) {
        this.warpHeightIntoLuna = value;
        markDirty();
    }

    public int terraResistance() {
        return terraResistance;
    }
    public void setTerraResistance(int value) {
        this.terraResistance = value;
        markDirty();
    }
}