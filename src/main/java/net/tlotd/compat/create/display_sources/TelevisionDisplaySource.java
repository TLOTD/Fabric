package net.tlotd.compat.create.display_sources;

import com.simibubi.create.content.redstone.displayLink.DisplayLinkContext;
import com.simibubi.create.content.redstone.displayLink.source.SingleLineDisplaySource;
import com.simibubi.create.content.redstone.displayLink.target.DisplayTargetStats;
import net.minecraft.block.BlockState;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.text.MutableText;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.tlotd.block.ModBlocks;
import net.tlotd.item.ModItems;
import net.tlotd.util.TelevisionSignalRegistry;
import net.tlotd.util.VideoGameRegistry;

import java.util.Optional;

import static net.tlotd.block.custom.TelevisionBlock.CHANNEL;

public class TelevisionDisplaySource extends SingleLineDisplaySource {

    public static final TelevisionDisplaySource INSTANCE = new TelevisionDisplaySource();

    private TelevisionDisplaySource() {}

    @Override
    protected MutableText provideLine(DisplayLinkContext displayLinkContext, DisplayTargetStats displayTargetStats) {
        BlockState state = displayLinkContext.level().getBlockState(displayLinkContext.getSourcePos());
        if (state.getBlock().equals(ModBlocks.TELEVISION_ON)) {
            Optional<TelevisionSignalRegistry.SignalEntry> currentEntry = TelevisionSignalRegistry.getAll().stream().filter(e -> e.onBlock() == state.getBlock() && e.channel() == state.get(CHANNEL)).findFirst();
            Item vhs = ModItems.VHS_CASSETTE_BROKEN;
            if (currentEntry.isPresent()) {
                Identifier signalId = currentEntry.get().signalItem();
                vhs = Registries.ITEM.get(signalId);
            }
            return Text.translatable(vhs.getTranslationKey() + ".desc");
        } else if (state.getBlock().equals(ModBlocks.TELEVISION_GAME)) {
            Optional<VideoGameRegistry.SignalEntry> currentEntry = VideoGameRegistry.getAll().stream().filter(e -> e.tvBlock() == state.getBlock() && e.gameID() == state.get(CHANNEL)).findFirst();
            Item game = ModItems.GAME_CARTRIDGE;
            if (currentEntry.isPresent()) {
                Identifier signalId = currentEntry.get().signalItem();
                game = Registries.ITEM.get(signalId);
            }
            return Text.translatable(game.getTranslationKey() + ".desc");
        } else return Text.translatable("block.tlotd.television.off");
    }

    @Override
    protected boolean allowsLabeling(DisplayLinkContext displayLinkContext) {
        return false;
    }
}