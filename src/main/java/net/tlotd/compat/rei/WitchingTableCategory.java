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

public class WitchingTableCategory implements DisplayCategory<BasicDisplay> {

    public static final Identifier TEXTURE = new Identifier(TLOTD.MOD_ID, "textures/gui/rei/witching_table.png");
    public static final CategoryIdentifier<WitchingTableDisplay> WITCHING = CategoryIdentifier.of(TLOTD.MOD_ID, "witching");

    @Override
    public CategoryIdentifier<? extends BasicDisplay> getCategoryIdentifier() {
        return WITCHING;
    }

    @Override
    public Text getTitle() {
        return Text.translatable("gui.tlotd.witching");
    }

    @Override
    public Renderer getIcon() {
        return EntryStacks.of(ModBlocks.WITCHING_TABLE.asItem().getDefaultStack());
    }

    @Override
    public List<Widget> setupDisplay(BasicDisplay display, Rectangle bounds) {
        final Point startPoint = new Point(bounds.getCenterX() - 88, bounds.getCenterY() - 36);
        List<Widget> widgets = new LinkedList<>();
        widgets.add(Widgets.createTexturedWidget(TEXTURE, new Rectangle(startPoint.x, startPoint.y, 178, 74)));

        widgets.add(Widgets.createSlot(new Point(startPoint.x + 27, startPoint.y + 9))
                .entries(display.getInputEntries().get(9)));
        widgets.add(Widgets.createSlot(new Point(startPoint.x + 27, startPoint.y + 49))
                .entries(display.getInputEntries().get(10)));

        widgets.add(Widgets.createSlot(new Point(startPoint.x + 48, startPoint.y + 10))
                .entries(display.getInputEntries().get(1)));
        widgets.add(Widgets.createSlot(new Point(startPoint.x + 67, startPoint.y + 9))
                .entries(display.getInputEntries().get(2)));
        widgets.add(Widgets.createSlot(new Point(startPoint.x + 86, startPoint.y + 10))
                .entries(display.getInputEntries().get(3)));
        widgets.add(Widgets.createSlot(new Point(startPoint.x + 47, startPoint.y + 29))
                .entries(display.getInputEntries().get(4)));
        widgets.add(Widgets.createSlot(new Point(startPoint.x + 67, startPoint.y + 29))
                .entries(display.getInputEntries().get(0)));
        widgets.add(Widgets.createSlot(new Point(startPoint.x + 87, startPoint.y + 29))
                .entries(display.getInputEntries().get(5)));
        widgets.add(Widgets.createSlot(new Point(startPoint.x + 48, startPoint.y + 48))
                .entries(display.getInputEntries().get(6)));
        widgets.add(Widgets.createSlot(new Point(startPoint.x + 67, startPoint.y + 49))
                .entries(display.getInputEntries().get(7)));
        widgets.add(Widgets.createSlot(new Point(startPoint.x + 86, startPoint.y + 48))
                .entries(display.getInputEntries().get(8)));

        widgets.add(Widgets.createSlot(new Point(startPoint.x + 153, startPoint.y + 29))
                .markOutput().entries(display.getOutputEntries().get(0)));

        return widgets;
    }

    @Override
    public int getDisplayHeight() {
        return 74;
    }
}