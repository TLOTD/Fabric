package net.tlotd.item.custom;

import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.item.ToolMaterial;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class AndurilItem extends SwordItem {
    public AndurilItem(ToolMaterial toolMaterial, int attackDamage, float attackSpeed, Settings settings) {
        super(toolMaterial, attackDamage, attackSpeed, settings);
    }

    public static final Identifier TENGWAR_FONT_ID = new Identifier("tlotd", "tengwar");

    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        if (Screen.hasShiftDown()) {
            tooltip.add(Text.translatable("item.tlotd.anduril.tooltip").formatted(Formatting.GRAY));
            tooltip.add(Text.translatable("item.tlotd.anduril.tooltip_2").formatted(Formatting.GRAY));
            tooltip.add(Text.translatable("item.tlotd.anduril.tooltip_3").formatted(Formatting.GRAY).append(" ").append(Text.translatable("item.tlotd.narsil").formatted(Formatting.YELLOW)).append(Text.translatable("item.tlotd.anduril.tooltip_4").formatted(Formatting.GRAY)));
        } else {
            tooltip.add(Text.translatable("item.tlotd.anduril.tooltip_quenya").setStyle(this.getName().getStyle().withFont(TENGWAR_FONT_ID).withColor(Formatting.GRAY)));
            tooltip.add(Text.translatable("item.tlotd.anduril.tooltip_quenya_2").setStyle(this.getName().getStyle().withFont(TENGWAR_FONT_ID).withColor(Formatting.GRAY)));
            tooltip.add(Text.translatable("item.tlotd.anduril.tooltip_quenya_3").setStyle(this.getName().getStyle().withFont(TENGWAR_FONT_ID).withColor(Formatting.GRAY)).append(" ").append(Text.translatable("item.tlotd.narsil_quenya").setStyle(this.getName().getStyle().withFont(TENGWAR_FONT_ID).withColor(Formatting.YELLOW))).append(Text.translatable("item.tlotd.anduril.tooltip_quenya_4").setStyle(this.getName().getStyle().withFont(TENGWAR_FONT_ID).withColor(Formatting.GRAY))));
        }
        super.appendTooltip(stack, world, tooltip, context);
    }
}
