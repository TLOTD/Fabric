package net.tlotd.compat.create.display_sources;

import com.simibubi.create.content.redstone.displayLink.DisplayLinkContext;
import com.simibubi.create.content.redstone.displayLink.source.SingleLineDisplaySource;
import com.simibubi.create.content.redstone.displayLink.target.DisplayTargetStats;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.MutableText;
import net.minecraft.text.Text;
import net.tlotd.world.RadioStation;
import net.tlotd.world.SignalTrackingArray;

public class SignalTransmitterNameDisplaySource extends SingleLineDisplaySource {

    public static final SignalTransmitterNameDisplaySource INSTANCE = new SignalTransmitterNameDisplaySource();

    private SignalTransmitterNameDisplaySource() {}

    @Override
    protected MutableText provideLine(DisplayLinkContext displayLinkContext, DisplayTargetStats displayTargetStats) {
        ServerWorld world = (ServerWorld) displayLinkContext.level();
        SignalTrackingArray tracker = SignalTrackingArray.get(world);
        RadioStation station = tracker.getStation(displayLinkContext.getSourcePos());
        if (station == null) {
            return Text.translatable("block.tlotd.signal_transmitter.not_found");
        }
        return station.getName().isEmpty() ? Text.translatable("block.tlotd.signal_transmitter.unnamed") : Text.literal(station.getName());
    }

    @Override
    protected boolean allowsLabeling(DisplayLinkContext displayLinkContext) {
        return false;
    }
}