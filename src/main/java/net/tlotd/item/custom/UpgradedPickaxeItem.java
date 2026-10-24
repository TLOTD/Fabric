package net.tlotd.item.custom;

import net.minecraft.item.ItemStack;
import net.minecraft.item.PickaxeItem;
import net.minecraft.item.ToolMaterial;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.text.Style;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.tlotd.item.ModItems;

import java.util.List;

public class UpgradedPickaxeItem extends PickaxeItem {

    public UpgradedPickaxeItem(ToolMaterial material, Settings settings) {
        super(material, settings);
    }

    @Override
    public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
        Style style = getName().getStyle();
        tooltip.add(Text.translatable("item.tlotd.upgrades.tooltip").formatted(Formatting.GRAY));
        if (stack.getItem().equals(ModItems.ENDURIUM_PICKAXE)) {
            tooltip.add(Text.literal(" ").append(Text.translatable("item.tlotd.endurium_pickaxe_head").setStyle(style.withColor(0x005648))));
        } else if (stack.getItem().equals(ModItems.HELIORITE_PICKAXE)) {
            tooltip.add(Text.literal(" ").append(Text.translatable("item.tlotd.heliorite_pickaxe_head").setStyle(style.withColor(0x9866d6))));
        }
        super.appendTooltip(stack, context, tooltip, type);
    }
}
