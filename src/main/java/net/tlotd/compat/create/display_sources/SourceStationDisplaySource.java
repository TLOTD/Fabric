package net.tlotd.compat.create.display_sources;

import com.simibubi.create.api.behaviour.display.DisplaySource;
import com.simibubi.create.content.redstone.displayLink.DisplayLinkContext;
import com.simibubi.create.content.redstone.displayLink.target.DisplayTargetStats;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.MutableText;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.tlotd.world.RadioStation;
import net.tlotd.world.SignalTrackingArray;

import java.util.List;

public class SourceStationDisplaySource extends DisplaySource {

    public static final SourceStationDisplaySource INSTANCE = new SourceStationDisplaySource();

    private SourceStationDisplaySource() {}

    @Override
    public List<MutableText> provideText(DisplayLinkContext displayLinkContext, DisplayTargetStats displayTargetStats) {
        ServerWorld world = (ServerWorld) displayLinkContext.level();
        SignalTrackingArray tracker = SignalTrackingArray.get(world);
        RadioStation station = tracker.getBestStation(displayLinkContext.getSourcePos());
        if (station == null) {
            return List.of(Text.translatable("block.tlotd.signal_transmitter.not_found"));
        }
        return List.of(
                station.getName().isEmpty() ? Text.translatable("block.tlotd.signal_transmitter.unnamed").formatted(Formatting.WHITE) : Text.literal(station.getName()).formatted(Formatting.WHITE),
                Text.translatable("tlotd.display_source.source_station.coordinates", station.getPos().getX(), station.getPos().getY(), station.getPos().getZ()).formatted(Formatting.WHITE)
        );
    }
}