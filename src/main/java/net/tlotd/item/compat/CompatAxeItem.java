package net.tlotd.item.compat;

import net.minecraft.client.item.TooltipContext;
import net.minecraft.item.AxeItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ToolMaterial;
import net.minecraft.text.Style;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class CompatAxeItem extends AxeItem {

    private final String compat;

    public static final Identifier DEFAULT_FONT_ID = new Identifier("minecraft", "default");
    public static final Identifier MODS_FONT_ID = new Identifier("tlotd", "mods");

    public CompatAxeItem(ToolMaterial toolMaterial, float attackDamage, float attackSpeed, Settings settings, String compat) {
        super(toolMaterial, attackDamage, attackSpeed, settings);
        this.compat = compat;
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        if (context.isCreative()){
            Style style = this.getName().getStyle();
            if (compat.contains("wsm")) {
                tooltip.add(Text.literal("\uE012 ").setStyle(style.withFont(MODS_FONT_ID)).append(Text.translatable("mod.witherstormmod.name").setStyle(style.withFont(DEFAULT_FONT_ID).withFormatting(Formatting.GRAY))));
            }
            super.appendTooltip(stack, world, tooltip, context);
        }
    }
}
