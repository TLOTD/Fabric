package net.tlotd.compat;

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

public class MithrilSmithingCategory implements DisplayCategory<BasicDisplay> {

    public static final Identifier TEXTURE = new Identifier(TLOTD.MOD_ID, "textures/gui/mithril_anvil.png");
    public static final CategoryIdentifier<MithrilSmithingDisplay> MITHRIL_SMITHING = CategoryIdentifier.of(TLOTD.MOD_ID, "mithril_smithing");

    @Override
    public CategoryIdentifier<? extends BasicDisplay> getCategoryIdentifier() {
        return MITHRIL_SMITHING;
    }

    @Override
    public Text getTitle() {
        return Text.translatable("gui.tlotd.mithril_smithing");
    }

    @Override
    public Renderer getIcon() {
        return EntryStacks.of(ModBlocks.MITHRIL_ANVIL.asItem().getDefaultStack());
    }

    @Override
    public List<Widget> setupDisplay(BasicDisplay display, Rectangle bounds) {
        final Point startPoint = new Point(bounds.getCenterX() - 87, bounds.getCenterY() - 35);
        List<Widget> widgets = new LinkedList<>();
        widgets.add(Widgets.createTexturedWidget(TEXTURE, new Rectangle(startPoint.x, startPoint.y, 175, 82)));

        widgets.add(Widgets.createSlot(new Point(startPoint.x + 32, startPoint.y + 27))
                .entries(display.getInputEntries().get(0)));
        widgets.add(Widgets.createSlot(new Point(startPoint.x + 50, startPoint.y + 18))
                .entries(display.getInputEntries().get(1)));
        widgets.add(Widgets.createSlot(new Point(startPoint.x + 50, startPoint.y + 36))
                .entries(display.getInputEntries().get(2)));
        widgets.add(Widgets.createSlot(new Point(startPoint.x + 68, startPoint.y + 27))
                .entries(display.getInputEntries().get(3)));
        widgets.add(Widgets.createSlot(new Point(startPoint.x + 86, startPoint.y + 18))
                .entries(display.getInputEntries().get(4)));
        widgets.add(Widgets.createSlot(new Point(startPoint.x + 104, startPoint.y + 27))
                .entries(display.getInputEntries().get(5)));

        widgets.add(Widgets.createSlot(new Point(startPoint.x + 140, startPoint.y + 27))
                .markOutput().entries(display.getOutputEntries().get(0)));

        return widgets;
    }

    @Override
    public int getDisplayHeight() {
        return 98;
    }
}
