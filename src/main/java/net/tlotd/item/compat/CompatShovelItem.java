package net.tlotd.item.compat;

import net.minecraft.item.ItemStack;
import net.minecraft.item.ShovelItem;
import net.minecraft.item.ToolMaterial;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.text.Style;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class CompatShovelItem extends ShovelItem {

    private final String compat;

    public static final Identifier DEFAULT_FONT_ID = Identifier.of("minecraft", "default");
    public static final Identifier MODS_FONT_ID = Identifier.of("tlotd", "mods");

    public CompatShovelItem(ToolMaterial toolMaterial, Settings settings, String compat) {
        super(toolMaterial, settings);
        this.compat = compat;
    }

    @Override
    public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
        /*if (context.isCreative()) {
            Style style = this.getName().getStyle();
            if (compat.contains("wsm")) {
                tooltip.add(Text.literal("\uE012 ").setStyle(style.withFont(MODS_FONT_ID)).append(Text.translatable("mod.witherstormmod.name").setStyle(style.withFont(DEFAULT_FONT_ID).withFormatting(Formatting.GRAY))));
            }
        }*/
        super.appendTooltip(stack, context, tooltip, type);
    }
}
