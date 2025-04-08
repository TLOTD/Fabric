package net.tlotd.item.custom;

import net.minecraft.client.item.TooltipContext;
import net.minecraft.item.ItemStack;
import net.minecraft.item.PickaxeItem;
import net.minecraft.item.ToolMaterial;
import net.minecraft.text.Style;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.world.World;
import net.tlotd.item.ModItems;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class UpgradedPickaxeItem extends PickaxeItem {
    public UpgradedPickaxeItem(ToolMaterial material, int attackDamage, float attackSpeed, Settings settings) {
        super(material, attackDamage, attackSpeed, settings);
    }
    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        Style style = getName().getStyle();
        tooltip.add(Text.translatable("item.tlotd.upgrades.tooltip").formatted(Formatting.GRAY));
        if (stack.getItem().equals(ModItems.ENDURIUM_PICKAXE)) {
            tooltip.add(Text.literal(" ").append(Text.translatable("item.tlotd.endurium_pickaxe_head").setStyle(style.withColor(0x005648))));
        } else if (stack.getItem().equals(ModItems.HELIORITE_PICKAXE)) {
            tooltip.add(Text.literal(" ").append(Text.translatable("item.tlotd.heliorite_pickaxe_head").setStyle(style.withColor(0x9866d6))));
        }
        super.appendTooltip(stack, world, tooltip, context);
    }
}
