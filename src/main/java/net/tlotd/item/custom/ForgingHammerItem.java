package net.tlotd.item.custom;

import net.minecraft.client.item.TooltipContext;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.world.World;
import net.tlotd.util.ModTags;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class ForgingHammerItem extends Item {

    public ForgingHammerItem(Settings settings) {
        super(settings);
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        tooltip.add(Text.literal(""));
        tooltip.add(Text.translatable("item.tlotd.forging_hammer.tooltip").formatted(Formatting.GRAY));
        if (stack.isIn(ModTags.Items.FORGING_HAMMERS_TIER9)) {
            tooltip.add(Text.literal(" 9 ").append(Text.translatable("item.tlotd.forging_hammer.tooltip_2")).formatted(Formatting.DARK_GREEN));
        } else if (stack.isIn(ModTags.Items.FORGING_HAMMERS_TIER8)) {
            tooltip.add(Text.literal(" 8 ").append(Text.translatable("item.tlotd.forging_hammer.tooltip_2")).formatted(Formatting.DARK_GREEN));
        } else if (stack.isIn(ModTags.Items.FORGING_HAMMERS_TIER7)) {
            tooltip.add(Text.literal(" 7 ").append(Text.translatable("item.tlotd.forging_hammer.tooltip_2")).formatted(Formatting.DARK_GREEN));
        } else if (stack.isIn(ModTags.Items.FORGING_HAMMERS_TIER6)) {
            tooltip.add(Text.literal(" 6 ").append(Text.translatable("item.tlotd.forging_hammer.tooltip_2")).formatted(Formatting.DARK_GREEN));
        } else if (stack.isIn(ModTags.Items.FORGING_HAMMERS_TIER5)) {
            tooltip.add(Text.literal(" 5 ").append(Text.translatable("item.tlotd.forging_hammer.tooltip_2")).formatted(Formatting.DARK_GREEN));
        } else if (stack.isIn(ModTags.Items.FORGING_HAMMERS_TIER4)) {
            tooltip.add(Text.literal(" 4 ").append(Text.translatable("item.tlotd.forging_hammer.tooltip_2")).formatted(Formatting.DARK_GREEN));
        } else if (stack.isIn(ModTags.Items.FORGING_HAMMERS_TIER3)) {
            tooltip.add(Text.literal(" 3 ").append(Text.translatable("item.tlotd.forging_hammer.tooltip_2")).formatted(Formatting.DARK_GREEN));
        } else if (stack.isIn(ModTags.Items.FORGING_HAMMERS_TIER2)) {
            tooltip.add(Text.literal(" 2 ").append(Text.translatable("item.tlotd.forging_hammer.tooltip_2")).formatted(Formatting.DARK_GREEN));
        } else if (stack.isIn(ModTags.Items.FORGING_HAMMERS_TIER1)) {
            tooltip.add(Text.literal(" 1 ").append(Text.translatable("item.tlotd.forging_hammer.tooltip_2")).formatted(Formatting.DARK_GREEN));
        }
        super.appendTooltip(stack, world, tooltip, context);
    }
}