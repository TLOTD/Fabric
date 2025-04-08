package net.tlotd.item.custom;

import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.text.ClickEvent;
import net.minecraft.text.Style;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;
import net.tlotd.item.ModItems;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class ForgingHammerItem extends Item {

    public ForgingHammerItem(Settings settings) {
        super(settings);
    }

    public ClickEvent WIKI = new ClickEvent(ClickEvent.Action.OPEN_URL, "https://tlotd.net/wiki/guidebook");

    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        tooltip.add(Text.literal(""));
        tooltip.add(Text.translatable("item.tlotd.forging_hammer.tooltip").formatted(Formatting.GRAY));
        if (stack.getItem().equals(ModItems.NETHERITE_FORGING_HAMMER)) {
            tooltip.add(Text.literal(" +1 ").formatted(Formatting.BLUE).append(Text.translatable("item.tlotd.forging_hammer.tooltip_2").formatted(Formatting.BLUE)));
        } else if (stack.getItem().equals(ModItems.MITHRIL_FORGING_HAMMER)) {
            tooltip.add(Text.literal(" +2 ").formatted(Formatting.BLUE).append(Text.translatable("item.tlotd.forging_hammer.tooltip_2").formatted(Formatting.BLUE)));
        } else if (stack.getItem().equals(ModItems.ASTRAL_FORGING_HAMMER)) {
            tooltip.add(Text.literal(" +3 ").formatted(Formatting.BLUE).append(Text.translatable("item.tlotd.forging_hammer.tooltip_2").formatted(Formatting.BLUE)));
        }
        super.appendTooltip(stack, world, tooltip, context);
    }
}