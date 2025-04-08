package net.tlotd.item.compat.spore;

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

public class SporeBlockItem extends BlockItem {

    public SporeBlockItem(Block block, Settings settings) {
        super(block, settings);
    }

    public static final Identifier DEFAULT_FONT_ID = new Identifier("minecraft", "default");
    public static final Identifier MODS_FONT_ID = new Identifier("tlotd", "mods");

    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        if (context.isCreative()){
            Style style = this.getName().getStyle();
            tooltip.add(Text.literal("\u0019").setStyle(style.withFont(MODS_FONT_ID)).append(Text.literal(" Spore").setStyle(style.withFont(DEFAULT_FONT_ID).withFormatting(Formatting.GRAY))));
            super.appendTooltip(stack, world, tooltip, context);
        }
    }
}
