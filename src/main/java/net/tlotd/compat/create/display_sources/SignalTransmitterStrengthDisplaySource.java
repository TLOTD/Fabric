package net.tlotd.compat.create.display_sources;

import com.simibubi.create.content.redstone.displayLink.DisplayLinkContext;
import com.simibubi.create.content.redstone.displayLink.source.SingleLineDisplaySource;
import com.simibubi.create.content.redstone.displayLink.target.DisplayTargetStats;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.MutableText;
import net.minecraft.text.Text;
import net.tlotd.world.RadioStation;
import net.tlotd.world.SignalTrackingArray;

public class SignalTransmitterStrengthDisplaySource extends SingleLineDisplaySource {

    public static final SignalTransmitterStrengthDisplaySource INSTANCE = new SignalTransmitterStrengthDisplaySource();

    private SignalTransmitterStrengthDisplaySource() {}

    @Override
    protected MutableText provideLine(DisplayLinkContext displayLinkContext, DisplayTargetStats displayTargetStats) {
        ServerWorld world = (ServerWorld) displayLinkContext.level();
        SignalTrackingArray tracker = SignalTrackingArray.get(world);
        RadioStation station = tracker.getStation(displayLinkContext.getSourcePos());
        if (station == null) {
            return Text.literal("block.tlotd.signal_transmitter.not_found");
        }
        int strength = station.getStrength();
        return (MutableText) SignalTrackingArray.getStrenthText(strength);
    }

    @Override
    protected boolean allowsLabeling(DisplayLinkContext displayLinkContext) {
        return false;
    }
}