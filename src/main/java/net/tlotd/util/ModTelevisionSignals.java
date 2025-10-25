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
        TelevisionSignalRegistry.register(
                new TelevisionSignalRegistry.SignalEntry(TelevisionSignalRegistry.normalize(
                        Identifier.of(TLOTD.MOD_ID,"vhs_cassette_project_kv31")),
                        ModBlocks.TELEVISION, ModBlocks.TELEVISION_ON, 31));



        VideoGameRegistry.registerBatch(
                new Identifier[]{
                        Identifier.of(TLOTD.MOD_ID,"game_cartridge_1"),
                        Identifier.of(TLOTD.MOD_ID,"game_cartridge_2"),
                        Identifier.of(TLOTD.MOD_ID,"game_cartridge_3")
                },
                ModBlocks.TELEVISION_GAME,
                ModBlocks.COMPUTER_ON,
                1
        );
    }
}