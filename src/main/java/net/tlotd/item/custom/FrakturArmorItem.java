package net.tlotd.item.custom;

import net.minecraft.item.ArmorItem;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class FrakturArmorItem extends ArmorItem {
    public FrakturArmorItem(ArmorMaterial material, Type type, Settings settings) {
        super(material, type, settings);
    }

    public static final Identifier FRAKTUR_FONT_ID = new Identifier("tlotd", "fraktur");

    @Override
    public Text getName(ItemStack stack) {
        return super.getName(stack).copy().styled(style -> style.withFont(FRAKTUR_FONT_ID));
    }
}
