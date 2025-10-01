package net.tlotd.block.custom;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.item.ItemStack;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.text.Style;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.tlotd.compat.ModCheckOthers;
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
        if (ModCheckOthers.PATCHOULI) {
            if (Screen.hasShiftDown()) {
                tooltip.add(Text.literal("\uE007 ").setStyle(style.withFont(RECIPIES_FONT_ID)).append(Text.translatable("item.patchouli.guide_book.tlotd.guiding_grimoire").setStyle(style.withFont(DEFAULT_FONT_ID).withFormatting(Formatting.GRAY))));
                tooltip.add(Text.literal("   ").append(Text.translatable("item.patchouli.guide_book.tlotd.guiding_grimoire.category").formatted(Formatting.DARK_GRAY)).append(Text.translatable("item.patchouli.guide_book.tlotd.guiding_grimoire.category.exploration").formatted(Formatting.GRAY)));
                tooltip.add(Text.literal("   ").append(Text.translatable("item.patchouli.guide_book.tlotd.guiding_grimoire.chapter").formatted(Formatting.DARK_GRAY)).append(Text.translatable("item.patchouli.guide_book.tlotd.guiding_grimoire.chapter.prehistoric").formatted(Formatting.GRAY)));
            } else {
                tooltip.add(Text.literal("\uE007 ").setStyle(style.withFont(RECIPIES_FONT_ID)).append(Text.translatable("mod.patchouli.name").setStyle(style.withFont(DEFAULT_FONT_ID).withFormatting(Formatting.GRAY))));
            }
        }
        super.appendTooltip(stack, world, tooltip, options);
    }
}
