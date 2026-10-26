package net.tlotd.item.custom;

import net.minecraft.client.gui.screen.Screen;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ShieldItem;
import net.minecraft.item.SwordItem;
import net.minecraft.item.ToolMaterial;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.text.Style;
import net.minecraft.text.Text;
import net.minecraft.util.*;
import net.minecraft.world.World;

import java.util.List;

public class AndurilItem extends SwordItem {

    public AndurilItem(ToolMaterial toolMaterial, Settings settings) {
        super(toolMaterial, settings);
    }

    public UseAction getUseAction(ItemStack stack) {
        return UseAction.BLOCK;
    }

    @Override
    public int getMaxUseTime(ItemStack stack, LivingEntity user) {
        return 72000;
    }

    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        Hand hand2 = Hand.MAIN_HAND;
        if (hand == Hand.MAIN_HAND) {
            hand2 = Hand.OFF_HAND;
        }
        ItemStack stack = user.getStackInHand(hand);
        ItemStack otherStack = user.getStackInHand(hand2);
        if (otherStack.getItem() instanceof ShieldItem) {
            user.setCurrentHand(hand2);
            return TypedActionResult.pass(stack);
        }
        user.setCurrentHand(hand);
        return TypedActionResult.consume(stack);
    }

    public static final Identifier TENGWAR_FONT_ID = Identifier.of("tlotd", "tengwar");

    @Override
    public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
        Style style = getName().getStyle();
        if (Screen.hasShiftDown()) {
            tooltip.add(Text.translatable("item.tlotd.anduril.tooltip").formatted(Formatting.GRAY));
            tooltip.add(Text.translatable("item.tlotd.anduril.tooltip_2").formatted(Formatting.GRAY));
            tooltip.add(Text.translatable("item.tlotd.anduril.tooltip_3").formatted(Formatting.GRAY).append(" ").append(Text.translatable("item.tlotd.narsil").formatted(Formatting.YELLOW)).append(Text.translatable("item.tlotd.anduril.tooltip_4").formatted(Formatting.GRAY)));
        } else {
            tooltip.add(Text.translatable("item.tlotd.anduril.tooltip_tengwar").setStyle(style.withFont(TENGWAR_FONT_ID).withColor(Formatting.GRAY)));
            tooltip.add(Text.translatable("item.tlotd.anduril.tooltip_tengwar_2").setStyle(style.withFont(TENGWAR_FONT_ID).withColor(Formatting.GRAY)));
            tooltip.add(Text.translatable("item.tlotd.anduril.tooltip_tengwar_3").setStyle(style.withFont(TENGWAR_FONT_ID).withColor(Formatting.GRAY)).append(" ").append(Text.translatable("item.tlotd.narsil_tengwar").setStyle(style.withFont(TENGWAR_FONT_ID).withColor(Formatting.YELLOW))).append(Text.translatable("item.tlotd.anduril.tooltip_tengwar_4").setStyle(style.withFont(TENGWAR_FONT_ID).withColor(Formatting.GRAY))));
        }
        super.appendTooltip(stack, context, tooltip, type);
    }
}