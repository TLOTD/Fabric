package net.tlotd.compat.create;

import com.simibubi.create.api.behaviour.display.DisplaySource;
import com.simibubi.create.api.registry.CreateBuiltInRegistries;
import net.createmod.ponder.foundation.PonderIndex;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.tlotd.TLOTD;
import net.tlotd.block.ModBlocks;
import net.tlotd.compat.create.display_sources.*;
import net.tlotd.compat.create.fan_processing_types.EffegiesProcessingType;
import net.tlotd.compat.create.ponder.TLOTDPonderPlugin;

public final class CreateCompat {
    public static void init() {
        SignalTransmitterDisplaySource signalTransmitter = Registry.register(CreateBuiltInRegistries.DISPLAY_SOURCE, new Identifier(TLOTD.MOD_ID, "signal_transmitter_info"), SignalTransmitterDisplaySource.INSTANCE);
        DisplaySource.BY_BLOCK.add(ModBlocks.SIGNAL_TRANSMITTER, signalTransmitter);
        SignalTransmitterNameDisplaySource signalTransmitterName = Registry.register(CreateBuiltInRegistries.DISPLAY_SOURCE, new Identifier(TLOTD.MOD_ID, "signal_transmitter_name"), SignalTransmitterNameDisplaySource.INSTANCE);
        DisplaySource.BY_BLOCK.add(ModBlocks.SIGNAL_TRANSMITTER, signalTransmitterName);
        SignalTransmitterActiveDisplaySource signalTransmitterActive = Registry.register(CreateBuiltInRegistries.DISPLAY_SOURCE, new Identifier(TLOTD.MOD_ID, "signal_transmitter_active"), SignalTransmitterActiveDisplaySource.INSTANCE);
        DisplaySource.BY_BLOCK.add(ModBlocks.SIGNAL_TRANSMITTER, signalTransmitterActive);
        SignalTransmitterStrengthDisplaySource signalTransmitterStrength = Registry.register(CreateBuiltInRegistries.DISPLAY_SOURCE, new Identifier(TLOTD.MOD_ID, "signal_transmitter_strength"), SignalTransmitterStrengthDisplaySource.INSTANCE);
        DisplaySource.BY_BLOCK.add(ModBlocks.SIGNAL_TRANSMITTER, signalTransmitterStrength);
        SignalTransmitterRangeDisplaySource signalTransmitterRange = Registry.register(CreateBuiltInRegistries.DISPLAY_SOURCE, new Identifier(TLOTD.MOD_ID, "signal_transmitter_range"), SignalTransmitterRangeDisplaySource.INSTANCE);
        DisplaySource.BY_BLOCK.add(ModBlocks.SIGNAL_TRANSMITTER, signalTransmitterRange);
        SignalTransmitterCountDisplaySource signalTransmitterCount = Registry.register(CreateBuiltInRegistries.DISPLAY_SOURCE, new Identifier(TLOTD.MOD_ID, "signal_transmitter_signal_count"), SignalTransmitterCountDisplaySource.INSTANCE);
        DisplaySource.BY_BLOCK.add(ModBlocks.SIGNAL_TRANSMITTER, signalTransmitterCount);
        SignalTransmitterListDisplaySource signalTransmitterList = Registry.register(CreateBuiltInRegistries.DISPLAY_SOURCE, new Identifier(TLOTD.MOD_ID, "signal_transmitter_signal_list"), SignalTransmitterListDisplaySource.INSTANCE);
        DisplaySource.BY_BLOCK.add(ModBlocks.SIGNAL_TRANSMITTER, signalTransmitterList);

        RadioDisplaySource radio = Registry.register(CreateBuiltInRegistries.DISPLAY_SOURCE, new Identifier(TLOTD.MOD_ID, "radio_track"), RadioDisplaySource.INSTANCE);
        DisplaySource.BY_BLOCK.add(ModBlocks.RADIO, radio);

        TelevisionDisplaySource television = Registry.register(CreateBuiltInRegistries.DISPLAY_SOURCE, new Identifier(TLOTD.MOD_ID, "television_channel"), TelevisionDisplaySource.INSTANCE);
        DisplaySource.BY_BLOCK.add(ModBlocks.TELEVISION, television);
        DisplaySource.BY_BLOCK.add(ModBlocks.TELEVISION_ON, television);
        DisplaySource.BY_BLOCK.add(ModBlocks.TELEVISION_GAME, television);

        SourceStationDisplaySource sourceStation = Registry.register(CreateBuiltInRegistries.DISPLAY_SOURCE, new Identifier(TLOTD.MOD_ID, "source_station"), SourceStationDisplaySource.INSTANCE);
        DisplaySource.BY_BLOCK.add(ModBlocks.RADIO, sourceStation);
        DisplaySource.BY_BLOCK.add(ModBlocks.TELEVISION, sourceStation);
        DisplaySource.BY_BLOCK.add(ModBlocks.TELEVISION_ON, sourceStation);
        DisplaySource.BY_BLOCK.add(ModBlocks.TELEVISION_GAME, sourceStation);

        Registry.register(CreateBuiltInRegistries.FAN_PROCESSING_TYPE, new Identifier(TLOTD.MOD_ID, "effegies_processing"), EffegiesProcessingType.INSTANCE);

        PonderIndex.addPlugin(new TLOTDPonderPlugin());
    }
}