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

public class FuturisticCircuitBoardItem extends Item {

    public FuturisticCircuitBoardItem(Settings settings) {
        super(settings);
    }

    public static final Identifier SGA_FONT_ID = new Identifier("minecraft", "alt");

    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        if (Screen.hasShiftDown()) {
            tooltip.add(Text.translatable("item.tlotd.futuristic_circuit_board.tooltip").formatted(Formatting.GRAY));
            tooltip.add(Text.translatable("item.tlotd.futuristic_circuit_board.tooltip_2").formatted(Formatting.GRAY));
            tooltip.add(Text.translatable("item.tlotd.futuristic_circuit_board.tooltip_3").formatted(Formatting.GRAY));
        } else {
            Style style = getName().getStyle();
            tooltip.add(Text.translatable("item.tlotd.futuristic_circuit_board.tooltip").setStyle(style.withFont(SGA_FONT_ID)).formatted(Formatting.GRAY));
            tooltip.add(Text.translatable("item.tlotd.futuristic_circuit_board.tooltip_2").setStyle(style.withFont(SGA_FONT_ID)).formatted(Formatting.GRAY));
            tooltip.add(Text.translatable("item.tlotd.futuristic_circuit_board.tooltip_3").setStyle(style.withFont(SGA_FONT_ID)).formatted(Formatting.GRAY));
        }
        super.appendTooltip(stack, world, tooltip, context);
    }
}