package net.tlotd.item.custom;

import net.minecraft.item.ArmorItem;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class FrakturArmorItem extends ArmorItem {

    public static final Identifier FRAKTUR_FONT_ID = Identifier.of("tlotd", "fraktur");

    public FrakturArmorItem(RegistryEntry<ArmorMaterial> material, Type type, Settings settings) {
        super(material, type, settings);
    }

    @Override
    public Text getName(ItemStack stack) {
        return super.getName(stack).copy().styled(style -> style.withFont(FRAKTUR_FONT_ID));
    }
}
