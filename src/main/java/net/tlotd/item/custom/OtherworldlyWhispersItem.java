package net.tlotd.item.custom;

import net.minecraft.client.item.TooltipContext;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class OtherworldlyWhispersItem extends Item {
    public OtherworldlyWhispersItem(Settings settings) {
        super(settings);
    }

    public static final Identifier ILLAGER_FONT_ID = new Identifier("minecraft", "illageralt");

    @Override
    public Text getName(ItemStack stack) {
        return Text.translatable(this.getTranslationKey()).styled(style -> style.withFont(ILLAGER_FONT_ID));
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        tooltip.add(Text.translatable("info.tlotd.not_yet_implemented").formatted(Formatting.RED));
        super.appendTooltip(stack, world, tooltip, context);
    }
}
