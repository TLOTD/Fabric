package net.tlotd.compat.create.display_sources;

import com.simibubi.create.content.redstone.displayLink.DisplayLinkContext;
import com.simibubi.create.content.redstone.displayLink.source.SingleLineDisplaySource;
import com.simibubi.create.content.redstone.displayLink.target.DisplayTargetStats;
import net.minecraft.nbt.NbtList;
import net.minecraft.nbt.NbtString;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.MutableText;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.tlotd.world.RadioStation;
import net.tlotd.world.SignalTrackingArray;

public class SignalTransmitterCountDisplaySource extends SingleLineDisplaySource {

    public static final SignalTransmitterCountDisplaySource INSTANCE = new SignalTransmitterCountDisplaySource();

    private SignalTransmitterCountDisplaySource() {}

    @Override
    protected MutableText provideLine(DisplayLinkContext displayLinkContext, DisplayTargetStats displayTargetStats) {
        ServerWorld world = (ServerWorld) displayLinkContext.level();
        SignalTrackingArray tracker = SignalTrackingArray.get(world);
        RadioStation station = tracker.getStation(displayLinkContext.getSourcePos());
        if (station == null) {
            return Text.translatable("block.tlotd.signal_transmitter.not_found");
        }
        NbtList tracks = new NbtList();
        for (Identifier id : station.getSignals()) {
            tracks.add(NbtString.of(id.toString()));
        }
        int count = tracks.size();
        return (MutableText) SignalTrackingArray.getCountText(count);
    }

    @Override
    protected boolean allowsLabeling(DisplayLinkContext displayLinkContext) {
        return false;
    }
}