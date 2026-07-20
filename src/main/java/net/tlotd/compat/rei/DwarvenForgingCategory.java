package net.tlotd.compat.rei;

import me.shedaniel.math.Point;
import me.shedaniel.math.Rectangle;
import me.shedaniel.rei.api.client.gui.Renderer;
import me.shedaniel.rei.api.client.gui.widgets.Widget;
import me.shedaniel.rei.api.client.gui.widgets.Widgets;
import me.shedaniel.rei.api.client.registry.display.DisplayCategory;
import me.shedaniel.rei.api.common.category.CategoryIdentifier;
import me.shedaniel.rei.api.common.display.basic.BasicDisplay;
import me.shedaniel.rei.api.common.entry.EntryIngredient;
import me.shedaniel.rei.api.common.entry.EntryStack;
import me.shedaniel.rei.api.common.entry.type.VanillaEntryTypes;
import me.shedaniel.rei.api.common.util.EntryStacks;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.tlotd.TLOTD;
import net.tlotd.block.ModBlocks;
import net.tlotd.util.ItemHeatHelper;

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

        EntryIngredient output = display.getOutputEntries().get(0);
        EntryStack<?> entry = output.get(0);

        if (entry.getType() == VanillaEntryTypes.ITEM) {
            ItemStack stack = entry.castValue();
            int temperature = ItemHeatHelper.getTemperature(stack);
            int y;
            if (temperature <= 1500) {
                y = temperature * 36 / 1500;
            } else if (temperature <= 3000) {
                y = 36 + (temperature - 1500) * 12 / 1500;
            } else {
                y = 48 + (temperature - 3000) * 24 / 3000;
            }
            int v = 0;
            if (temperature > 3000) {
                v = 28;
            } else if (temperature > 1500) {
                v = 14;
            }
            widgets.add(Widgets.createTexturedWidget(TEXTURE, startPoint.x + 31, startPoint.y + 8 + (72 - y), 141, 72 - y,7,y));
            widgets.add(Widgets.createTexturedWidget(TEXTURE, startPoint.x + 63, startPoint.y + 37, 148, v, 14, 14));
        }

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
                .markOutput().entries(output));

        return widgets;
    }

    @Override
    public int getDisplayHeight() {
        return 88;
    }
}