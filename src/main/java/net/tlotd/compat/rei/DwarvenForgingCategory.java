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

public class DwarvenForgingCategory implements DisplayCategory<BasicDisplay> {

    public static final Identifier TEXTURE = new Identifier(TLOTD.MOD_ID, "textures/gui/rei/dwarven_forge.png");
    public static final CategoryIdentifier<MithrilSmithingDisplay> DWARVEN_FORGING = CategoryIdentifier.of(TLOTD.MOD_ID, "dwarven_forging");

    @Override
    public CategoryIdentifier<? extends BasicDisplay> getCategoryIdentifier() {
        return DWARVEN_FORGING;
    }

    @Override
    public Text getTitle() {
        return Text.translatable("gui.tlotd.dwarven_forging");
    }

    @Override
    public Renderer getIcon() {
        return EntryStacks.of(ModBlocks.DWARVEN_FORGE.asItem().getDefaultStack());
    }

    @Override
    public List<Widget> setupDisplay(BasicDisplay display, Rectangle bounds) {
        final Point startPoint = new Point(bounds.getCenterX() - 70, bounds.getCenterY() - 43);
        List<Widget> widgets = new LinkedList<>();
        widgets.add(Widgets.createTexturedWidget(TEXTURE, new Rectangle(startPoint.x, startPoint.y, 141, 88)));

        widgets.add(Widgets.createSlot(new Point(startPoint.x + 44, startPoint.y + 19))
                .entries(display.getInputEntries().get(0)));
        widgets.add(Widgets.createSlot(new Point(startPoint.x + 62, startPoint.y + 18))
                .entries(display.getInputEntries().get(1)));
        widgets.add(Widgets.createSlot(new Point(startPoint.x + 80, startPoint.y + 19))
                .entries(display.getInputEntries().get(2)));

        widgets.add(Widgets.createSlot(new Point(startPoint.x + 9, startPoint.y + 63))
                .entries(display.getInputEntries().get(3)));
        widgets.add(Widgets.createSlot(new Point(startPoint.x + 9, startPoint.y + 45))
                .entries(display.getInputEntries().get(4)));
        widgets.add(Widgets.createSlot(new Point(startPoint.x + 9, startPoint.y + 27))
                .entries(display.getInputEntries().get(5)));
        widgets.add(Widgets.createSlot(new Point(startPoint.x + 9, startPoint.y + 9))
                .entries(display.getInputEntries().get(6)));
        widgets.add(Widgets.createSlot(new Point(startPoint.x + 44, startPoint.y + 53))
                .entries(display.getInputEntries().get(7)));
        widgets.add(Widgets.createSlot(new Point(startPoint.x + 62, startPoint.y + 54))
                .entries(display.getInputEntries().get(8)));
        widgets.add(Widgets.createSlot(new Point(startPoint.x + 80, startPoint.y + 53))
                .entries(display.getInputEntries().get(9)));

        widgets.add(Widgets.createSlot(new Point(startPoint.x + 116, startPoint.y + 36))
                .markOutput().entries(display.getOutputEntries().get(0)));

        return widgets;
    }

    @Override
    public int getDisplayHeight() {
        return 88;
    }
}