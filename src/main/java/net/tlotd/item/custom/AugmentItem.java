package net.tlotd.item.custom;

import net.minecraft.client.item.TooltipContext;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Style;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;
import net.minecraft.world.World;
import net.tlotd.item.ModItems;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class AugmentItem extends Item {
    public AugmentItem(Settings settings) {
        super(settings);
    }

    public Text getName(ItemStack stack) {
        Formatting rarity = Formatting.WHITE;
        if (stack.getRarity().equals(Rarity.EPIC)) {
            rarity = Formatting.GOLD;
        }
        if (stack.getRarity().equals(Rarity.RARE)) {
            rarity = Formatting.AQUA;
        }
        if (stack.getRarity().equals(Rarity.UNCOMMON)) {
            rarity = Formatting.YELLOW;
        }
        return Text.translatable(super.getTranslationKey().replace("augment-", "").replace("item", "augment")).formatted(rarity);
    }

    public static final Identifier DEFAULT_FONT_ID = new Identifier("minecraft", "default");
    public static final Identifier MODS_FONT_ID = new Identifier("tlotd", "mods");

    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        tooltip.add(Text.translatable(super.getTranslationKey().replace("augment-", "").replace("item", "augment") + ".desc").formatted(Formatting.GRAY));
        tooltip.add(Text.translatable(super.getTranslationKey().replace("augment-", "").replace("item", "augment") + ".desc2").formatted(Formatting.GRAY));
        if (context.isCreative() && (stack.isOf(ModItems.AUGMENT_THERMAL_HEATING) || stack.isOf(ModItems.AUGMENT_THERMAL_COOLING))){
            Style style = this.getName().getStyle();
            tooltip.add(Text.literal("\uE018 ").setStyle(style.withFont(MODS_FONT_ID)).append(Text.translatable("mod.toughasnails.name").setStyle(style.withFont(DEFAULT_FONT_ID).withFormatting(Formatting.GRAY))));
        }
        super.appendTooltip(stack, world, tooltip, context);
    }
}