package net.tlotd.item.custom;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Style;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;

import static net.tlotd.api.TlotdAPI.enlightened;

public class ArcaneCircuitBoardItem extends Item {

    public ArcaneCircuitBoardItem(Settings settings) {
        super(settings);
    }

    @Override
    public boolean hasGlint(ItemStack stack) {
        return true;
    }

    public static final Identifier DEFAULT_FONT_ID = new Identifier("minecraft", "default");
    public static final Identifier ILLAGER_FONT_ID = new Identifier("minecraft", "illageralt");
    public static final Identifier TOOLTIP_FONT_ID = new Identifier("tlotd", "tooltip");

    @Override
    @Environment(EnvType.CLIENT)
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        tooltip.add(Text.translatable("info.tlotd.not_yet_implemented").formatted(Formatting.RED));
        Style style = getName().getStyle();
        PlayerEntity player = MinecraftClient.getInstance().player;
        if (player == null) return;
        if (enlightened(player) >= 10 && Screen.hasShiftDown()) {
            tooltip.add(Text.literal("\uE002 ").setStyle(style.withFont(TOOLTIP_FONT_ID)).append(Text.translatable("item.tlotd.desc_enlightened").setStyle(style.withFont(DEFAULT_FONT_ID).withFormatting(Formatting.GOLD))));
            tooltip.add(Text.translatable("item.tlotd.arcane_circuit_board.tooltip").formatted(Formatting.GRAY));
            tooltip.add(Text.translatable("item.tlotd.arcane_circuit_board.tooltip_2").formatted(Formatting.GRAY));
            tooltip.add(Text.translatable("item.tlotd.arcane_circuit_board.tooltip_3").formatted(Formatting.GRAY));
        } else {
            if (enlightened(player) >= 10) {
                tooltip.add(Text.literal("\uE001 ").setStyle(style.withFont(TOOLTIP_FONT_ID)).append(Text.translatable("item.tlotd.desc_enlightened").setStyle(style.withFont(DEFAULT_FONT_ID).withFormatting(Formatting.GOLD))));
            } else {
                tooltip.add(Text.literal("\uE000 ").setStyle(style.withFont(TOOLTIP_FONT_ID)).append(Text.translatable("item.tlotd.desc_not_enlightened").setStyle(style.withFont(DEFAULT_FONT_ID).withFormatting(Formatting.DARK_GRAY))));
            }
            tooltip.add(Text.translatable("item.tlotd.arcane_circuit_board.tooltip").setStyle(style.withFont(ILLAGER_FONT_ID)).formatted(Formatting.GRAY));
            tooltip.add(Text.translatable("item.tlotd.arcane_circuit_board.tooltip_2").setStyle(style.withFont(ILLAGER_FONT_ID)).formatted(Formatting.GRAY));
            tooltip.add(Text.translatable("item.tlotd.arcane_circuit_board.tooltip_3").setStyle(style.withFont(ILLAGER_FONT_ID)).formatted(Formatting.GRAY));
        }
        super.appendTooltip(stack, world, tooltip, context);
    }
}