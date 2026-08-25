package net.tlotd.compat.create.display_sources;

import com.simibubi.create.api.behaviour.display.DisplaySource;
import com.simibubi.create.content.redstone.displayLink.DisplayLinkContext;
import com.simibubi.create.content.redstone.displayLink.target.DisplayTargetStats;
import net.minecraft.nbt.NbtList;
import net.minecraft.nbt.NbtString;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.MutableText;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Identifier;
import net.tlotd.world.RadioStation;
import net.tlotd.world.SignalTrackingArray;

import java.util.List;

public class SignalTransmitterDisplaySource extends DisplaySource {

    public static final SignalTransmitterDisplaySource INSTANCE = new SignalTransmitterDisplaySource();

    private SignalTransmitterDisplaySource() {}

    @Override
    public List<MutableText> provideText(DisplayLinkContext displayLinkContext, DisplayTargetStats displayTargetStats) {
        ServerWorld world = (ServerWorld) displayLinkContext.level();
        SignalTrackingArray tracker = SignalTrackingArray.get(world);
        RadioStation station = tracker.getStation(displayLinkContext.getSourcePos());
        if (station == null) {
            return List.of(Text.translatable("block.tlotd.signal_transmitter.not_found"));
        }
        NbtList tracks = new NbtList();
        for (Identifier id : station.getSignals()) {
            tracks.add(NbtString.of(id.toString()));
        }
        return List.of(
                station.getName().isEmpty() ? Text.translatable("block.tlotd.signal_transmitter.unnamed") : Text.literal(station.getName()),
                station.isActive() ? Text.translatable("block.tlotd.signal_transmitter.active").formatted(Formatting.GREEN) : Text.translatable("block.tlotd.signal_transmitter.inactive").formatted(Formatting.RED),
                (MutableText) SignalTrackingArray.getStrenthText(station.getStrength()),
                (MutableText) SignalTrackingArray.getRangeText(station.getRange()),
                (MutableText) SignalTrackingArray.getCountText(tracks.size())
        );
    }
}