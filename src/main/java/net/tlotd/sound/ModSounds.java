package net.tlotd.sound;

import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;
import net.tlotd.TLOTD;

public class ModSounds {

    public static final SoundEvent ENTITY_TREX_IDLE = register("entity_t-rex_idle");
    public static final SoundEvent ENTITY_TREX_HURT = register("entity_t-rex_hurt");
    public static final SoundEvent ENTITY_TREX_DEATH = register("entity_t-rex_death");

    public static final SoundEvent ITEM_PICKAXE_EXTRACT = register("item_pickaxe_extract");
    public static final SoundEvent ITEM_MITHRIL_MIRROR = register("item_mithril_mirror");

    public static final SoundEvent BLOCK_ELEVATOR_PLING = register("block_elevator_pling");
    public static final SoundEvent BLOCK_RADIO_SWITCH_FREQUENCY = register("block_radio_switch_frequency");

    public static final RegistryEntry.Reference<SoundEvent> RADIO_FREQUENCY_1 = registerReference("radio_frequency_1");
    public static final RegistryEntry.Reference<SoundEvent> RADIO_FREQUENCY_2 = registerReference("radio_frequency_2");
    public static final RegistryEntry.Reference<SoundEvent> RADIO_FREQUENCY_3 = registerReference("radio_frequency_3");
    public static final RegistryEntry.Reference<SoundEvent> RADIO_FREQUENCY_4 = registerReference("radio_frequency_4");

    public static final RegistryEntry.Reference<SoundEvent> BACKROOMS_SIGNAL = registerReference("signal_backrooms");

    public static final SoundEvent BLOCK_TELEVISION_SWITCH_CHANNEL = register("block_television_switch_channel");
    public static final SoundEvent BLOCK_TELEVISION_STATIC_CHANNEL = register("block_television_static_channel");

    public static final SoundEvent BLOCK_VIDEOCASSETTE_RECORDER = register("block_videocassette_recorder");

    public static final SoundEvent BLOCK_COMPUTER_INTERACT = register("block_computer_interact");

    public static final SoundEvent BLOCK_KEYCARD_READER_PLING = register("block_keycard_reader_pling");

    public static final SoundEvent BLOCK_GARBAGE_CAN_USED = register("block_garbage_can_used");

    private static SoundEvent register(String name) {
        return Registry.register(Registries.SOUND_EVENT, Identifier.of(TLOTD.MOD_ID, name), SoundEvent.of(Identifier.of(TLOTD.MOD_ID, name)));
    }

    private static RegistryEntry.Reference<SoundEvent> registerReference(String name) {
        return Registry.registerReference(Registries.SOUND_EVENT, Identifier.of(TLOTD.MOD_ID, name), SoundEvent.of(Identifier.of(TLOTD.MOD_ID, name)));
    }

    public static void registerSounds() {
        TLOTD.LOGGER.info("Registering Sounds for " + TLOTD.MOD_ID);
    }
}
