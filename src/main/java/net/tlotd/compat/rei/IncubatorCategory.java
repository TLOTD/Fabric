package net.tlotd.compat.rei;

import me.shedaniel.math.Point;
import me.shedaniel.math.Rectangle;
import me.shedaniel.rei.api.client.gui.Renderer;
import me.shedaniel.rei.api.client.gui.widgets.Widget;
import me.shedaniel.rei.api.client.gui.widgets.Widgets;
import me.shedaniel.rei.api.client.registry.display.DisplayCategory;
import me.shedaniel.rei.api.common.category.CategoryIdentifier;
import me.shedaniel.rei.api.common.display.basic.BasicDisplay;
import me.shedaniel.rei.api.common.util.EntryStacks;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.tlotd.TLOTD;
import net.tlotd.block.ModBlocks;

import java.util.LinkedList;
import java.util.List;

public class IncubatorCategory implements DisplayCategory<BasicDisplay> {

    public static final Identifier TEXTURE = new Identifier(TLOTD.MOD_ID, "textures/gui/rei/incubator.png");
    public static final CategoryIdentifier<IncubatorDisplay> INCUBATING = CategoryIdentifier.of(TLOTD.MOD_ID, "incubating");

    @Override
    public CategoryIdentifier<? extends BasicDisplay> getCategoryIdentifier() {
        return INCUBATING;
    }

    @Override
    public Text getTitle() {
        return Text.translatable("gui.tlotd.incubating");
    }

    @Override
    public Renderer getIcon() {
        return EntryStacks.of(ModBlocks.INCUBATOR.asItem().getDefaultStack());
    }

    @Override
    public List<Widget> setupDisplay(BasicDisplay display, Rectangle bounds) {
        final Point startPoint = new Point(bounds.getCenterX() - 52, bounds.getCenterY() - 16);
        List<Widget> widgets = new LinkedList<>();
        widgets.add(Widgets.createTexturedWidget(TEXTURE, new Rectangle(startPoint.x, startPoint.y, 142, 52)));

        widgets.add(Widgets.createSlot(new Point(startPoint.x + 9, startPoint.y + 9))
                .entries(display.getInputEntries().get(0)));
        widgets.add(Widgets.createSlot(new Point(startPoint.x + 45, startPoint.y + 9))
                .entries(display.getInputEntries().get(1)));

        widgets.add(Widgets.createSlot(new Point(startPoint.x + 81, startPoint.y + 9))
                .markOutput().entries(display.getOutputEntries().get(0)));

        return widgets;
    }

    @Override
    public int getDisplayHeight() {
        return 34;
    }
}