package net.tlotd.item.custom;

import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.text.ClickEvent;
import net.minecraft.text.Style;
import net.minecraft.text.Text;
import net.minecraft.util.*;
import net.minecraft.world.World;
import net.tlotd.util.EnlightenedData;
import net.tlotd.util.EntityDataSaver;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class ForbiddenSpellBookItem extends Item {

    public ForbiddenSpellBookItem(Settings settings) {
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
            tooltip.add(Text.translatable("item.tlotd.forbidden_spell_book.tooltip").formatted(Formatting.GRAY));
            tooltip.add(Text.translatable("item.tlotd.forbidden_spell_book.tooltip_2").formatted(Formatting.GRAY));
            tooltip.add(Text.translatable("item.tlotd.forbidden_spell_book.tooltip_3").formatted(Formatting.GRAY));
        } else {
            Style style = getName().getStyle();
            tooltip.add(Text.literal("Ph").setStyle(style.withFont(ILLAGER_FONT_ID)).formatted(Formatting.GRAY).append(Text.literal("'").setStyle(style.withFont(DEFAULT_FONT_ID)).formatted(Formatting.GRAY).append(Text.literal("nglui mglw").setStyle(style.withFont(ILLAGER_FONT_ID)).formatted(Formatting.GRAY))).append(Text.literal("'").setStyle(style.withFont(DEFAULT_FONT_ID)).formatted(Formatting.GRAY).append(Text.literal("nafh").setStyle(style.withFont(ILLAGER_FONT_ID)).formatted(Formatting.GRAY))));
            tooltip.add(Text.literal("Cthulhu R").setStyle(style.withFont(ILLAGER_FONT_ID)).formatted(Formatting.GRAY).append(Text.literal("'").setStyle(style.withFont(DEFAULT_FONT_ID)).formatted(Formatting.GRAY).append(Text.literal("lyeh").setStyle(style.withFont(ILLAGER_FONT_ID)).formatted(Formatting.GRAY))));
            tooltip.add(Text.literal("wgah").setStyle(style.withFont(ILLAGER_FONT_ID)).formatted(Formatting.GRAY).append(Text.literal("'").setStyle(style.withFont(DEFAULT_FONT_ID)).formatted(Formatting.GRAY).append(Text.literal("nagl fhtagn").setStyle(style.withFont(ILLAGER_FONT_ID)).formatted(Formatting.GRAY))));
        }
        super.appendTooltip(stack, world, tooltip, context);
    }
}