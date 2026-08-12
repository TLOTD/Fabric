package net.tlotd.item.compat.create;

import net.minecraft.client.item.TooltipContext;
import net.minecraft.fluid.Fluid;
import net.minecraft.item.BucketItem;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Style;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;
import net.tlotd.fluid.ModFluids;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class ModBucketItem extends BucketItem {
    public ModBucketItem(Fluid fluid, Settings settings) {
        super(fluid, settings);
    }

    public static final Identifier DEFAULT_FONT_ID = new Identifier("minecraft", "default");
    public static final Identifier MODS_FONT_ID = new Identifier("tlotd", "mods");

    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        if (context.isCreative()) {
            Style style = this.getName().getStyle();
            if (stack.isOf(ModFluids.BLUE_BERRY_JAM_BUCKET)) {
                tooltip.add(Text.literal("\uE008 ").setStyle(Style.EMPTY.withFont(MODS_FONT_ID)).append(Text.translatable("mod.aether.name").setStyle(Style.EMPTY.withFont(DEFAULT_FONT_ID).withFormatting(Formatting.GRAY))));
            }
            if (stack.isOf(ModFluids.ANCIENT_SOULBERRY_JAM_BUCKET)) {
                tooltip.add(Text.literal("\uE00B ").setStyle(Style.EMPTY.withFont(MODS_FONT_ID)).append(Text.translatable("mod.allthemodium.name").setStyle(Style.EMPTY.withFont(DEFAULT_FONT_ID).withFormatting(Formatting.GRAY))));
            }
            if (stack.isOf(ModFluids.DROOPFRUIT_JAM_BUCKET)) {
                tooltip.add(Text.literal("\uE016 ").setStyle(style.withFont(MODS_FONT_ID)).append(Text.translatable("mod.undergarden.name").setStyle(style.withFont(DEFAULT_FONT_ID).withFormatting(Formatting.GRAY))));
            }
            if (stack.isOf(ModFluids.MOLTEN_MITHRIL_BUCKET)) {
                tooltip.add(Text.literal("\uE017 ").setStyle(style.withFont(MODS_FONT_ID)).append(Text.translatable("mod.tconstruct.name").setStyle(style.withFont(DEFAULT_FONT_ID).withFormatting(Formatting.GRAY))));
            } else {
                tooltip.add(Text.literal("\uE00A ").setStyle(style.withFont(MODS_FONT_ID)).append(Text.translatable("mod.create.name").setStyle(style.withFont(DEFAULT_FONT_ID).withFormatting(Formatting.GRAY))));
            }
        }
        super.appendTooltip(stack, world, tooltip, context);
    }
}
