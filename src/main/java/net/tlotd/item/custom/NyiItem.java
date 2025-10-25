package net.tlotd.item.custom;

import net.minecraft.client.item.TooltipContext;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;
import net.tlotd.item.ModItems;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class NyiItem extends Item {
    public NyiItem(Settings settings) {
        super(settings);
    }

    public static final Identifier ILLAGER_FONT_ID = new Identifier("minecraft", "illageralt");

    @Override
    public Text getName(ItemStack stack) {
        Text name = super.getName(stack);
        if (stack.isOf(ModItems.DAYBREAK_DOMAIN_FRAGMENTS)) return name.copy().styled(style -> style.withColor(Formatting.GOLD));
        if (stack.isOf(ModItems.SOMBER_BLOOD_ORBS)) return name.copy().styled(style -> style.withColor(Formatting.RED));
        if (stack.isOf(ModItems.OTHERWORLDLY_WHISPERS)) return name.copy().styled(style -> style.withFont(ILLAGER_FONT_ID).withColor(0x7A0F0F));
        return name;
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        tooltip.add(Text.translatable("info.tlotd.not_yet_implemented").formatted(Formatting.RED));
        super.appendTooltip(stack, world, tooltip, context);
    }
}
