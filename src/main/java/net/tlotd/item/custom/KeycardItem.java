package net.tlotd.item.custom;

import net.minecraft.client.gui.screen.Screen;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;

import java.util.List;

public class KeycardItem extends Item {
    public KeycardItem(Settings settings) {
        super(settings);
    }

    /*@Override
    public boolean hasGlint(ItemStack stack) {
        return stack.hasNbt();
    }

    @Override
    public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
        if (!stack.hasNbt()) {
            tooltip.add(Text.literal("0 ♣ / 1 ♣ ").append(Text.translatable("item.tlotd.keycard.data")).formatted(Formatting.GREEN));
            if (Screen.hasShiftDown()) {
                tooltip.add(Text.translatable("item.tlotd.keycard.no_password").formatted(Formatting.GRAY));
            }
        } else {
            tooltip.add(Text.literal("1 ♣ / 1 ♣ ").append(Text.translatable("item.tlotd.keycard.data")).formatted(Formatting.GREEN));
            if (Screen.hasShiftDown()) {
                tooltip.add(Text.translatable("item.tlotd.keycard.has_password").formatted(Formatting.GRAY));
            }
        }
        super.appendTooltip(stack, context, tooltip, type);
    }*/
}
