package net.tlotd.block.custom;

import net.minecraft.block.Block;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Style;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Identifier;
import net.minecraft.world.BlockView;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class CursedWoolBlock extends Block {
    public CursedWoolBlock(Settings settings) {
        super(settings);
    }

    public static final Identifier ILLAGER_FONT_ID = new Identifier("minecraft", "illageralt");

    @Override
    public void appendTooltip(ItemStack stack, @Nullable BlockView world, List<Text> tooltip, TooltipContext options) {
        if (Screen.hasShiftDown()) {
            tooltip.add(Text.translatable("block.tlotd.cursed_carpet.tooltip").formatted(Formatting.GRAY));
            tooltip.add(Text.translatable("block.tlotd.cursed_carpet.tooltip_2").formatted(Formatting.GRAY));
            tooltip.add(Text.translatable("block.tlotd.cursed_carpet.tooltip_3").formatted(Formatting.GRAY));
        } else {
            Style style = getName().getStyle();
            tooltip.add(Text.translatable("block.tlotd.cursed_carpet.tooltip").setStyle(style.withFont(ILLAGER_FONT_ID)).formatted(Formatting.GRAY));
            tooltip.add(Text.translatable("block.tlotd.cursed_carpet.tooltip_2").setStyle(style.withFont(ILLAGER_FONT_ID)).formatted(Formatting.GRAY));
            tooltip.add(Text.translatable("block.tlotd.cursed_carpet.tooltip_3").setStyle(style.withFont(ILLAGER_FONT_ID)).formatted(Formatting.GRAY));
        }
        super.appendTooltip(stack, world, tooltip, options);
    }
}
