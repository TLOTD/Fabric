package net.tlotd.util;

import net.minecraft.util.Identifier;
import net.tlotd.TLOTD;
import net.tlotd.block.ModBlocks;

public class ModTelevisionSignals {
    public static void registerSignals() {
        TelevisionSignalRegistry.registerBatch(
                new Identifier[]{
                        Identifier.of(TLOTD.MOD_ID,"vhs_cassette_broken"),
                        Identifier.of(TLOTD.MOD_ID,"vhs_cassette_1"),
                        Identifier.of(TLOTD.MOD_ID,"vhs_cassette_2"),
                        Identifier.of(TLOTD.MOD_ID,"vhs_cassette_3"),
                        Identifier.of(TLOTD.MOD_ID,"vhs_cassette_4"),
                        Identifier.of(TLOTD.MOD_ID,"vhs_cassette_5"),
                        Identifier.of(TLOTD.MOD_ID,"vhs_cassette_6"),
                        Identifier.of(TLOTD.MOD_ID,"vhs_cassette_7"),
                        Identifier.of(TLOTD.MOD_ID,"vhs_cassette_8"),
                        Identifier.of(TLOTD.MOD_ID,"vhs_cassette_9")
                },
                ModBlocks.TELEVISION,
                ModBlocks.TELEVISION_ON,
                0
        );
    }
}