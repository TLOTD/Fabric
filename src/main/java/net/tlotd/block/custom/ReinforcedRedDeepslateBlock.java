package net.tlotd.block.custom;

import net.minecraft.block.Block;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Style;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Identifier;
import net.minecraft.world.BlockView;
import net.tlotd.compat.CompatModsCheck;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class ReinforcedRedDeepslateBlock extends Block {
    public ReinforcedRedDeepslateBlock(Settings settings) {
        super(settings);
    }

    public static final Identifier DEFAULT_FONT_ID = new Identifier("minecraft", "default");
    public static final Identifier RECIPIES_FONT_ID = new Identifier("tlotd", "recipies");

    @Override
    public void appendTooltip(ItemStack stack, @Nullable BlockView world, List<Text> tooltip, TooltipContext options) {
        Style style = this.getName().getStyle();
        if (CompatModsCheck.PATCHOULI) {
            if (Screen.hasShiftDown()) {
                tooltip.add(Text.literal("\uE007 ").setStyle(style.withFont(RECIPIES_FONT_ID)).append(Text.translatable("item.patchouli.guide_book.tlotd.guiding_grimoire").setStyle(style.withFont(DEFAULT_FONT_ID).withFormatting(Formatting.GRAY))));
                tooltip.add(Text.literal("   ").append(Text.translatable("item.patchouli.guide_book.tlotd.guiding_grimoire.category").formatted(Formatting.DARK_GRAY)).append(Text.translatable("item.patchouli.guide_book.tlotd.guiding_grimoire.category.exploration").formatted(Formatting.GRAY)));
                tooltip.add(Text.literal("   ").append(Text.translatable("item.patchouli.guide_book.tlotd.guiding_grimoire.chapter").formatted(Formatting.DARK_GRAY)).append(Text.translatable("item.patchouli.guide_book.tlotd.guiding_grimoire.chapter.prehistoric").formatted(Formatting.GRAY)));
            } else {
                tooltip.add(Text.literal("\uE007 ").setStyle(style.withFont(RECIPIES_FONT_ID)).append(Text.translatable("item.patchouli.guide_book.tlotd.guiding_grimoire.ponder", Text.translatable("key.keyboard.shift").formatted(Formatting.GRAY)).setStyle(style.withFont(DEFAULT_FONT_ID).withFormatting(Formatting.DARK_GRAY))));
            }
        }
        super.appendTooltip(stack, world, tooltip, options);
    }
}
