package net.tlotd.item.custom;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;

import java.util.List;

public class HempCookieItem extends Item {
    public HempCookieItem(Settings settings) {
        super(settings);
    }

    @Override
    public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
        tooltip.add(Text.translatable("effect.tlotd.stoned").append(Text.literal(" (00:30)")).formatted(Formatting.RED));
        super.appendTooltip(stack, context, tooltip, type);
    }
}