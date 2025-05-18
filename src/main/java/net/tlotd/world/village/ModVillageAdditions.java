package net.tlotd.world.village;

import fzzyhmstrs.structurized_reborn.impl.FabricStructurePoolRegistry;
import net.minecraft.util.Identifier;
import net.tlotd.TLOTD;

public class ModVillageAdditions {
    // Using https://github.com/fzzyhmstrs/structurized-reborn (Under MIT License)
    public static void registerNewVillageStructures() {
        FabricStructurePoolRegistry.registerSimple(
                new Identifier("minecraft:village/plains/houses"),
                new Identifier(TLOTD.MOD_ID, "plains_castle_forge"),
                10
        );
        FabricStructurePoolRegistry.registerSimple(
                new Identifier("minecraft:village/taiga/houses"),
                new Identifier(TLOTD.MOD_ID, "taiga_castle_forge"),
                10
        );
        FabricStructurePoolRegistry.registerSimple(
                new Identifier("minecraft:village/savanna/houses"),
                new Identifier(TLOTD.MOD_ID, "savanna_castle_forge"),
                10
        );
        FabricStructurePoolRegistry.registerSimple(
                new Identifier("minecraft:village/desert/houses"),
                new Identifier(TLOTD.MOD_ID, "desert_castle_forge"),
                10
        );
        FabricStructurePoolRegistry.registerSimple(
                new Identifier("minecraft:village/snowy/houses"),
                new Identifier(TLOTD.MOD_ID, "taiga_castle_forge"),
                10
        );
    }
}