package net.tlotd.compat.create.display_sources;

import com.simibubi.create.content.redstone.displayLink.DisplayLinkContext;
import com.simibubi.create.content.redstone.displayLink.source.SingleLineDisplaySource;
import com.simibubi.create.content.redstone.displayLink.target.DisplayTargetStats;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.text.MutableText;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.tlotd.block.custom.RadioBlock;
import net.tlotd.block.entity.RadioBlockEntity;

public class RadioDisplaySource extends SingleLineDisplaySource {

    public static final RadioDisplaySource INSTANCE = new RadioDisplaySource();

    private RadioDisplaySource() {}

    @Override
    protected MutableText provideLine(DisplayLinkContext displayLinkContext, DisplayTargetStats displayTargetStats) {
        if (!displayLinkContext.level().getBlockState(displayLinkContext.getSourcePos()).get(RadioBlock.ON)) {
            return Text.translatable("block.tlotd.radio.off");
        }
        RadioBlockEntity radio = (RadioBlockEntity) displayLinkContext.getSourceBlockEntity();
        Identifier track = radio.getCurrentTrack();
        if (track == null) {
            return Text.translatable("block.tlotd.radio.list_empty");
        }
        Item item = Registries.ITEM.get(track);
        return Text.translatable(item.getTranslationKey() + ".desc");
    }

    @Override
    protected boolean allowsLabeling(DisplayLinkContext displayLinkContext) {
        return false;
    }
}