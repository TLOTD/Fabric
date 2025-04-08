package net.tlotd.item.custom;

import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Style;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class AbyssFlaskItem extends Item {

    public AbyssFlaskItem(Settings settings) {
        super(settings);
    }

    public static final Identifier DEFAULT_FONT_ID = new Identifier("minecraft", "default");
    public static final Identifier ILLAGER_FONT_ID = new Identifier("minecraft", "illageralt");

    @Override
    public boolean hasGlint(ItemStack stack) {
        return true;
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        if (Screen.hasShiftDown()) {
            tooltip.add(Text.translatable("item.tlotd.soul_flask_of_the_abyss.tooltip").formatted(Formatting.GRAY));
            tooltip.add(Text.translatable("item.tlotd.soul_flask_of_the_abyss.tooltip_2").formatted(Formatting.GRAY));
            tooltip.add(Text.translatable("item.tlotd.soul_flask_of_the_abyss.tooltip_3").formatted(Formatting.GRAY));
            tooltip.add(Text.translatable("item.tlotd.soul_flask_of_the_abyss.tooltip_4").formatted(Formatting.GRAY));
        } else {
            Style style = getName().getStyle();
            tooltip.add(Text.literal("Und wenn du lange in").setStyle(style.withFont(ILLAGER_FONT_ID)).formatted(Formatting.GRAY));
            tooltip.add(Text.literal("einen Abgrund blickst").setStyle(style.withFont(ILLAGER_FONT_ID)).formatted(Formatting.GRAY).append(Text.literal(",").setStyle(style.withFont(DEFAULT_FONT_ID)).formatted(Formatting.GRAY)));
            tooltip.add(Text.literal("blickt der Abgrund").setStyle(style.withFont(ILLAGER_FONT_ID)).formatted(Formatting.GRAY));
            tooltip.add(Text.literal("auch in dich hinein").setStyle(style.withFont(ILLAGER_FONT_ID)).formatted(Formatting.GRAY));
        }
        super.appendTooltip(stack, world, tooltip, context);
    }
}