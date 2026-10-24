package net.tlotd.item;

import net.minecraft.block.jukebox.JukeboxSong;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import net.tlotd.TLOTD;

public class ModJukeboxSongs {
    public static final RegistryKey<JukeboxSong> RADIO_FREQUENCY_1 = RegistryKey.of(RegistryKeys.JUKEBOX_SONG, Identifier.of(TLOTD.MOD_ID, "radio_frequency_1"));
    public static final RegistryKey<JukeboxSong> RADIO_FREQUENCY_2 = RegistryKey.of(RegistryKeys.JUKEBOX_SONG, Identifier.of(TLOTD.MOD_ID, "radio_frequency_2"));
    public static final RegistryKey<JukeboxSong> RADIO_FREQUENCY_3 = RegistryKey.of(RegistryKeys.JUKEBOX_SONG, Identifier.of(TLOTD.MOD_ID, "radio_frequency_3"));
    public static final RegistryKey<JukeboxSong> RADIO_FREQUENCY_4 = RegistryKey.of(RegistryKeys.JUKEBOX_SONG, Identifier.of(TLOTD.MOD_ID, "radio_frequency_4"));

    public static final RegistryKey<JukeboxSong> BACKROOMS_SIGNAL = RegistryKey.of(RegistryKeys.JUKEBOX_SONG, Identifier.of(TLOTD.MOD_ID, "backrooms_signal"));
}