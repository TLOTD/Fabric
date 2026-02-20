package net.tlotd.compat;

import net.fabricmc.loader.api.FabricLoader;

public class CompatModsCheck {
    public static final boolean FORGE = FabricLoader.getInstance().isModLoaded("forge");
    public static final boolean FABRIC = FabricLoader.getInstance().isModLoaded("fabric");

    public static final boolean TOUGHASNAILS = FabricLoader.getInstance().isModLoaded("toughasnails");

    public static final boolean AETHER = FabricLoader.getInstance().isModLoaded("aether");
    public static final boolean ALEXSCAVES = FabricLoader.getInstance().isModLoaded("alexscaves");
    public static final boolean ATM = FabricLoader.getInstance().isModLoaded("allthemodium");
    public static final boolean BIOMANCY = FabricLoader.getInstance().isModLoaded("biomancy");
    public static final boolean BIOMESOPLENTY = FabricLoader.getInstance().isModLoaded("biomesoplenty");
    public static final boolean CREATE = FabricLoader.getInstance().isModLoaded("create");
    public static final boolean NEEPMEAT = FabricLoader.getInstance().isModLoaded("neepmeat");
    public static final boolean PATCHOULI = FabricLoader.getInstance().isModLoaded("patchouli");
    public static final boolean QUARK = FabricLoader.getInstance().isModLoaded("quark");
    public static final boolean SCULKHORDE = FabricLoader.getInstance().isModLoaded("sculkhorde");
    public static final boolean SPORE = FabricLoader.getInstance().isModLoaded("spore");
    public static final boolean THERMAL = FabricLoader.getInstance().isModLoaded("thermal");
    public static final boolean TCONSTRUCT = FabricLoader.getInstance().isModLoaded("tconstruct");
    public static final boolean TWILIGHTFOREST = FabricLoader.getInstance().isModLoaded("twilightforest");
    public static final boolean UNDERGARDEN = FabricLoader.getInstance().isModLoaded("undergarden");
    public static final boolean WITHERSTORMMOD = FabricLoader.getInstance().isModLoaded("witherstormmod");
}
