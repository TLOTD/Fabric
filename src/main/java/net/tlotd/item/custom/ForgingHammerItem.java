package net.tlotd.item.custom;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.tlotd.util.ModTags;

import java.util.List;

public class ForgingHammerItem extends Item {

    public ForgingHammerItem(Settings settings) {
        super(settings);
    }

    @Override
    public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
        int level = 1;
        if (stack.isIn(ModTags.Items.FORGING_HAMMERS_TIER9)) {
            level = 9;
        } else if (stack.isIn(ModTags.Items.FORGING_HAMMERS_TIER8)) {
            level = 8;
        } else if (stack.isIn(ModTags.Items.FORGING_HAMMERS_TIER7)) {
            level = 7;
        } else if (stack.isIn(ModTags.Items.FORGING_HAMMERS_TIER6)) {
            level = 6;
        } else if (stack.isIn(ModTags.Items.FORGING_HAMMERS_TIER5)) {
            level = 5;
        } else if (stack.isIn(ModTags.Items.FORGING_HAMMERS_TIER4)) {
            level = 4;
        } else if (stack.isIn(ModTags.Items.FORGING_HAMMERS_TIER3)) {
            level = 3;
        } else if (stack.isIn(ModTags.Items.FORGING_HAMMERS_TIER2)) {
            level = 2;
        }
        tooltip.add(Text.empty());
        tooltip.add(Text.translatable("item.tlotd.forging_hammer.tooltip").formatted(Formatting.GRAY));
        tooltip.add(Text.literal(" ").append(Text.translatable("item.tlotd.forging_hammer.tooltip_2", level)).formatted(Formatting.DARK_GREEN));
        super.appendTooltip(stack, context, tooltip, type);
    }
}