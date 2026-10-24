package net.tlotd.world;

import net.minecraft.nbt.NbtCompound;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.world.PersistentState;
import net.tlotd.util.TemperatureUnit;

public class ModGlobalState extends PersistentState {

    private static final PersistentState.Type<ModGlobalState> TYPE =
            new PersistentState.Type<>(
                    ModGlobalState::new,
                    ModGlobalState::fromNbt,
                    null
            );

    private boolean easterEggs = true;
    private boolean formerTlotdRewards = false;

    private String defaultTemperatureUnit = TemperatureUnit.CELSIUS.asString();

    private boolean axeStrippingBark = true;
    private boolean extractionOreCompat = true;
    private int elevatorMaxDistance = 100;

    private boolean starlightAnvil = true;
    private boolean bloodWitching = true;
    private boolean soulWitching = true;

    private int warpHeightOutOfTerra = 1000;
    private int warpHeightOutOfLuna = 1000;
    private int warpHeightIntoTerra = 320;
    private int warpHeightIntoLuna = 100;
    private int terraResistance = 400;

    private double noClipChance = 0.1;

    public static ModGlobalState get(MinecraftServer server) {
        ServerWorld overworld = server.getOverworld();
        return overworld.getPersistentStateManager().getOrCreate(TYPE, "tlotd_data_global");
    }

    public NbtCompound toNbt() {
        return writeNbt(new NbtCompound(), null);
    }

    private static ModGlobalState fromNbt(NbtCompound nbt, RegistryWrapper.WrapperLookup registries) {
        ModGlobalState state = new ModGlobalState();
        if (nbt.contains("EasterEggs")) state.easterEggs = nbt.getBoolean("EasterEggs");
        if (nbt.contains("FormerTLOTDRewards")) state.formerTlotdRewards = nbt.getBoolean("FormerTLOTDRewards");
        if (nbt.contains("DefaultTemperatureUnit")) state.defaultTemperatureUnit = nbt.getString("DefaultTemperatureUnit");
        if (nbt.contains("AxeStrippingBark")) state.axeStrippingBark = nbt.getBoolean("AxeStrippingBark");
        if (nbt.contains("ExtractionOreCompat")) state.extractionOreCompat = nbt.getBoolean("ExtractionOreCompat");
        if (nbt.contains("ElevatorMaxDistance")) state.elevatorMaxDistance = nbt.getInt("ElevatorMaxDistance");
        if (nbt.contains("StarlightAnvil")) state.starlightAnvil = nbt.getBoolean("StarlightAnvil");
        if (nbt.contains("BloodWitching")) state.bloodWitching = nbt.getBoolean("BloodWitching");
        if (nbt.contains("SoulWitching")) state.soulWitching = nbt.getBoolean("SoulWitching");
        if (nbt.contains("WarpHeightOutOfTerra")) state.warpHeightOutOfTerra = nbt.getInt("WarpHeightOutOfTerra");
        if (nbt.contains("WarpHeightOutOfLuna")) state.warpHeightOutOfLuna = nbt.getInt("WarpHeightOutOfLuna");
        if (nbt.contains("WarpHeightIntoTerra")) state.warpHeightIntoTerra = nbt.getInt("WarpHeightIntoTerra");
        if (nbt.contains("WarpHeightIntoLuna")) state.warpHeightIntoLuna = nbt.getInt("WarpHeightIntoLuna");
        if (nbt.contains("TerraResistance")) state.terraResistance = nbt.getInt("TerraResistance");
        if (nbt.contains("NoClipChance")) state.noClipChance = nbt.getDouble("NoClipChance");
        return state;
    }

    @Override
    public NbtCompound writeNbt(NbtCompound nbt, RegistryWrapper.WrapperLookup registries) {
        nbt.putBoolean("EasterEggs", easterEggs);
        nbt.putBoolean("FormerTLOTDRewards", formerTlotdRewards);
        nbt.putString("DefaultTemperatureUnit", defaultTemperatureUnit);
        nbt.putBoolean("AxeStrippingBark", axeStrippingBark);
        nbt.putBoolean("ExtractionOreCompat", extractionOreCompat);
        nbt.putInt("ElevatorMaxDistance", elevatorMaxDistance);
        nbt.putBoolean("StarlightAnvil", starlightAnvil);
        nbt.putBoolean("BloodWitching", bloodWitching);
        nbt.putBoolean("SoulWitching", soulWitching);
        nbt.putInt("WarpHeightOutOfTerra", warpHeightOutOfTerra);
        nbt.putInt("WarpHeightOutOfLuna", warpHeightOutOfLuna);
        nbt.putInt("WarpHeightIntoTerra", warpHeightIntoTerra);
        nbt.putInt("WarpHeightIntoLuna", warpHeightIntoLuna);
        nbt.putInt("TerraResistance", terraResistance);
        nbt.putDouble("NoClipChance", noClipChance);
        return nbt;
    }

    public boolean easterEggs() {
        return easterEggs;
    }

    public void setEasterEggs(boolean value) {
        this.easterEggs = value;
        markDirty();
    }

    public boolean formerTlotdRewards() {
        return formerTlotdRewards;
    }

    public void setFormerTlotdRewards(boolean value) {
        this.formerTlotdRewards = value;
        markDirty();
    }

    public TemperatureUnit defaultTemperatureUnit() {
        return TemperatureUnit.fromString(defaultTemperatureUnit);
    }

    public void setDefaultTemperatureUnit(TemperatureUnit unit) {
        this.defaultTemperatureUnit = unit.asString();
        markDirty();
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

    public double noClipChance() {
        return noClipChance;
    }

    public void setNoClipChance(double value) {
        this.noClipChance = value;
        markDirty();
    }
}