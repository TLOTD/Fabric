package net.tlotd.item.custom;

import net.minecraft.client.item.TooltipContext;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Style;
import net.minecraft.text.Text;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import java.util.List;

public class SoulMirrorItem extends Item {
    public SoulMirrorItem(Settings settings) { super(settings); }
    
    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        Style style = getName().getStyle();
        tooltip.add(Text.translatable("item.tlotd.desc_eldritch").setStyle(style.withColor(0x3C009C)));
        super.appendTooltip(stack, world, tooltip, context);
    }
}