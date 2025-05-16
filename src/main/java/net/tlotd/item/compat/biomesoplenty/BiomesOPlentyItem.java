package net.tlotd.item.compat.biomesoplenty;

import net.minecraft.client.item.TooltipContext;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Style;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class BiomesOPlentyItem extends Item {
    public BiomesOPlentyItem(Settings settings) {
        super(settings);
    }

    public static final Identifier DEFAULT_FONT_ID = new Identifier("minecraft", "default");
    public static final Identifier MODS_FONT_ID = new Identifier("tlotd", "mods");

    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        if (context.isCreative()){
            Style style = this.getName().getStyle();
            tooltip.add(Text.literal("\uE00D ").setStyle(style.withFont(MODS_FONT_ID)).append(Text.translatable("mod.biomesoplenty.name").setStyle(style.withFont(DEFAULT_FONT_ID).withFormatting(Formatting.GRAY))));
            super.appendTooltip(stack, world, tooltip, context);
        }
    }
}
