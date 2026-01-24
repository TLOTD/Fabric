package net.tlotd.item.compat.biomancy;

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

public class BiologicalCircuitBoardItem extends Item {
    public BiologicalCircuitBoardItem(Settings settings) {
        super(settings);
    }

    public static final Identifier DEFAULT_FONT_ID = new Identifier("minecraft", "default");
    public static final Identifier CARO_INVITICA_FONT_ID = new Identifier("tlotd", "caro_invitica");
    public static final Identifier TOOLTIP_FONT_ID = new Identifier("tlotd", "tooltip");
    public static final Identifier MODS_FONT_ID = new Identifier("tlotd", "mods");

    @Override
    public Text getName(ItemStack stack) {
        return Text.translatable(this.getTranslationKey()).styled(style -> style.withColor(0xA58369));
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        tooltip.add(Text.translatable("info.tlotd.not_yet_implemented").formatted(Formatting.RED));
        Style style = getName().getStyle();
        PlayerEntity player = MinecraftClient.getInstance().player;
        if (player == null) return;
        if (context.isCreative()){
            tooltip.add(Text.literal("\uE013 ").setStyle(Style.EMPTY.withFont(MODS_FONT_ID)).append(Text.translatable("mod.biomancy.name").setStyle(Style.EMPTY.withFont(DEFAULT_FONT_ID).withFormatting(Formatting.GRAY))));
            tooltip.add(Text.literal("\uE010 ").setStyle(Style.EMPTY.withFont(MODS_FONT_ID)).append(Text.translatable("mod.spore.name").setStyle(Style.EMPTY.withFont(DEFAULT_FONT_ID).withFormatting(Formatting.GRAY))));
            tooltip.add(Text.literal("\uE014 ").setStyle(Style.EMPTY.withFont(MODS_FONT_ID)).append(Text.translatable("mod.neepmeat.name").setStyle(Style.EMPTY.withFont(DEFAULT_FONT_ID).withFormatting(Formatting.GRAY))));
            tooltip.add(Text.empty());
        }
        if (enlightened(player) >= 20 && Screen.hasShiftDown()) {
            tooltip.add(Text.literal("\uE005 ").setStyle(style.withFont(TOOLTIP_FONT_ID)).append(Text.translatable("item.tlotd.desc_enlightened").setStyle(style.withFont(DEFAULT_FONT_ID).withFormatting(Formatting.GOLD))));
            tooltip.add(Text.translatable("item.tlotd.biological_circuit_board.tooltip").formatted(Formatting.GRAY));
            tooltip.add(Text.translatable("item.tlotd.biological_circuit_board.tooltip_2").formatted(Formatting.GRAY));
            tooltip.add(Text.translatable("item.tlotd.biological_circuit_board.tooltip_3").formatted(Formatting.GRAY));
        } else {
            if (enlightened(player) >= 20) {
                tooltip.add(Text.literal("\uE004 ").setStyle(style.withFont(TOOLTIP_FONT_ID)).append(Text.translatable("item.tlotd.desc_enlightened").setStyle(style.withFont(DEFAULT_FONT_ID).withFormatting(Formatting.GOLD))));
            } else {
                tooltip.add(Text.literal("\uE003 ").setStyle(style.withFont(TOOLTIP_FONT_ID)).append(Text.translatable("item.tlotd.desc_not_enlightened").setStyle(style.withFont(DEFAULT_FONT_ID).withFormatting(Formatting.DARK_GRAY))));
            }
            tooltip.add(Text.translatable("item.tlotd.biological_circuit_board.tooltip").setStyle(style.withFont(CARO_INVITICA_FONT_ID)).formatted(Formatting.GRAY));
            tooltip.add(Text.translatable("item.tlotd.biological_circuit_board.tooltip_2").setStyle(style.withFont(CARO_INVITICA_FONT_ID)).formatted(Formatting.GRAY));
            tooltip.add(Text.translatable("item.tlotd.biological_circuit_board.tooltip_3").setStyle(style.withFont(CARO_INVITICA_FONT_ID)).formatted(Formatting.GRAY));
        }
        tooltip.add(Text.translatable("item.tlotd.desc_occult").formatted(Formatting.RED));
        super.appendTooltip(stack, world, tooltip, context);
    }
}