package net.tlotd.compat;

import net.fabricmc.loader.api.FabricLoader;

public class CompatModsCheck {
    public static final boolean FORGE = FabricLoader.getInstance().isModLoaded("forge");
    public static final boolean FABRIC = FabricLoader.getInstance().isModLoaded("fabric");
    public static final boolean CREATE = FabricLoader.getInstance().isModLoaded("create");
    public static final boolean PATCHOULI = FabricLoader.getInstance().isModLoaded("patchouli");
}
