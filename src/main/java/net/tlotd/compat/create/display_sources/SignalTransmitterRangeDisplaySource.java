package net.tlotd.compat.create.display_sources;

import com.simibubi.create.content.redstone.displayLink.DisplayLinkContext;
import com.simibubi.create.content.redstone.displayLink.source.SingleLineDisplaySource;
import com.simibubi.create.content.redstone.displayLink.target.DisplayTargetStats;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.MutableText;
import net.minecraft.text.Text;
import net.tlotd.world.RadioStation;
import net.tlotd.world.SignalTrackingArray;

public class SignalTransmitterRangeDisplaySource extends SingleLineDisplaySource {

    public static final SignalTransmitterRangeDisplaySource INSTANCE = new SignalTransmitterRangeDisplaySource();

    private SignalTransmitterRangeDisplaySource() {}

    @Override
    protected MutableText provideLine(DisplayLinkContext displayLinkContext, DisplayTargetStats displayTargetStats) {
        ServerWorld world = (ServerWorld) displayLinkContext.level();
        SignalTrackingArray tracker = SignalTrackingArray.get(world);
        RadioStation station = tracker.getStation(displayLinkContext.getSourcePos());
        if (station == null) {
            return Text.literal("block.tlotd.signal_transmitter.not_found");
        }
        int range = station.getRange();
        return (MutableText) SignalTrackingArray.getRangeText(range);
    }

    @Override
    protected boolean allowsLabeling(DisplayLinkContext displayLinkContext) {
        return false;
    }
}