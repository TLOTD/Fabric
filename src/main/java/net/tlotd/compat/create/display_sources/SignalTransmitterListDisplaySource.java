package net.tlotd.compat.create.display_sources;

import com.simibubi.create.api.behaviour.display.DisplaySource;
import com.simibubi.create.content.redstone.displayLink.DisplayLinkContext;
import com.simibubi.create.content.redstone.displayLink.target.DisplayTargetStats;
import net.minecraft.client.resource.language.I18n;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.nbt.NbtList;
import net.minecraft.nbt.NbtString;
import net.minecraft.registry.Registries;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.MutableText;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Identifier;
import net.tlotd.world.RadioStation;
import net.tlotd.world.SignalTrackingArray;

import java.util.ArrayList;
import java.util.List;

public class SignalTransmitterListDisplaySource extends DisplaySource {

    public static final SignalTransmitterListDisplaySource INSTANCE = new SignalTransmitterListDisplaySource();

    private SignalTransmitterListDisplaySource() {
    }

    @Override
    public List<MutableText> provideText(DisplayLinkContext context, DisplayTargetStats stats) {
        ServerWorld world = (ServerWorld) context.level();
        SignalTrackingArray tracker = SignalTrackingArray.get(world);
        RadioStation station = tracker.getStation(context.getSourcePos());
        if (station == null) {
            return List.of(Text.translatable("block.tlotd.signal_transmitter.not_found"));
        }
        return getSignalText(station);
    }

    private List<MutableText> getSignalText(RadioStation station) {
        if (station.getSignals().isEmpty()) {
            return List.of(Text.translatable("block.tlotd.signal_transmitter.signal_count.0").formatted(Formatting.GRAY));
        }
        List<MutableText> result = new ArrayList<>();
        for (Identifier id : station.getSignals()) {
            Item item = Registries.ITEM.get(id);
            if (item == Items.AIR) {
                continue;
            }
            result.add(Text.translatable(item.getTranslationKey() + ".desc").formatted(Formatting.GRAY));
        }
        return result;
    }
}