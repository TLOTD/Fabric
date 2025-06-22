package net.tlotd.item.compat;

import net.minecraft.block.Block;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Style;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class CompatBlockItem extends BlockItem {

    private final String compat;

    public CompatBlockItem(Block block, Settings settings, String compat) {
        super(block, settings);
        this.compat = compat;
    }

    public static final Identifier DEFAULT_FONT_ID = new Identifier("minecraft", "default");
    public static final Identifier MODS_FONT_ID = new Identifier("tlotd", "mods");

    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        if (context.isCreative()){
            Style style = this.getName().getStyle();
            if (compat.contains("aet")) {
                tooltip.add(Text.literal("\uE008 ").setStyle(style.withFont(MODS_FONT_ID)).append(Text.translatable("mod.aether.name").setStyle(style.withFont(DEFAULT_FONT_ID).withFormatting(Formatting.GRAY))));
            }
            if (compat.contains("tlf")) {
                tooltip.add(Text.literal("\uE009 ").setStyle(style.withFont(MODS_FONT_ID)).append(Text.translatable("mod.twilightforest.name").setStyle(style.withFont(DEFAULT_FONT_ID).withFormatting(Formatting.GRAY))));
            }
            if (compat.contains("atm")) {
                tooltip.add(Text.literal("\uE00B ").setStyle(style.withFont(MODS_FONT_ID)).append(Text.translatable("mod.allthemodium.name").setStyle(style.withFont(DEFAULT_FONT_ID).withFormatting(Formatting.GRAY))));
            }
            if (compat.contains("axc")) {
                tooltip.add(Text.literal("\uE00C ").setStyle(style.withFont(MODS_FONT_ID)).append(Text.translatable("mod.alexscaves.name").setStyle(style.withFont(DEFAULT_FONT_ID).withFormatting(Formatting.GRAY))));
            }
            if (compat.contains("bop")) {
                tooltip.add(Text.literal("\uE00D ").setStyle(style.withFont(MODS_FONT_ID)).append(Text.translatable("mod.biomesoplenty.name").setStyle(style.withFont(DEFAULT_FONT_ID).withFormatting(Formatting.GRAY))));
            }
            if (compat.contains("qrk")) {
                tooltip.add(Text.literal("\uE00E ").setStyle(style.withFont(MODS_FONT_ID)).append(Text.translatable("mod.quark.name").setStyle(style.withFont(DEFAULT_FONT_ID).withFormatting(Formatting.GRAY))));
            }
            if (compat.contains("thr")) {
                tooltip.add(Text.literal("\uE00F ").setStyle(style.withFont(MODS_FONT_ID)).append(Text.translatable("mod.thermal.name").setStyle(style.withFont(DEFAULT_FONT_ID).withFormatting(Formatting.GRAY))));
            }
            if (compat.contains("spr")) {
                tooltip.add(Text.literal("\uE010 ").setStyle(style.withFont(MODS_FONT_ID)).append(Text.translatable("mod.spore.name").setStyle(style.withFont(DEFAULT_FONT_ID).withFormatting(Formatting.GRAY))));
            }
            super.appendTooltip(stack, world, tooltip, context);
        }
    }
}
