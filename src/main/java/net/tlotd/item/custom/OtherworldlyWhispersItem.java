package net.tlotd.item.custom;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class OtherworldlyWhispersItem extends Item {
    public OtherworldlyWhispersItem(Settings settings) {
        super(settings);
    }

    public static final Identifier ILLAGER_FONT_ID = new Identifier("minecraft", "illageralt");

    @Override
    public Text getName(ItemStack stack) {
        return Text.translatable(this.getTranslationKey()).styled(style -> style.withFont(ILLAGER_FONT_ID));
    }
}
