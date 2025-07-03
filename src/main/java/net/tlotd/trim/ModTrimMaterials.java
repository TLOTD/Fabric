package net.tlotd.trim;

import net.minecraft.item.Item;
import net.minecraft.item.trim.ArmorTrimMaterial;
import net.minecraft.registry.Registerable;
import net.minecraft.registry.Registries;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.text.Style;
import net.minecraft.text.Text;
import net.minecraft.text.TextColor;
import net.minecraft.util.Identifier;
import net.minecraft.util.Util;
import net.tlotd.TLOTD;
import net.tlotd.item.ModItems;

import java.util.Map;

public class ModTrimMaterials {

    public static final RegistryKey<ArmorTrimMaterial> HELIORITE = RegistryKey.of(RegistryKeys.TRIM_MATERIAL,
            Identifier.of(TLOTD.MOD_ID, "heliorite"));
    public static final RegistryKey<ArmorTrimMaterial> ENDURIUM = RegistryKey.of(RegistryKeys.TRIM_MATERIAL,
            Identifier.of(TLOTD.MOD_ID, "endurium"));
    public static final RegistryKey<ArmorTrimMaterial> PALLADIUM = RegistryKey.of(RegistryKeys.TRIM_MATERIAL,
            Identifier.of(TLOTD.MOD_ID, "palladium"));
    public static final RegistryKey<ArmorTrimMaterial> JURASSOLINE = RegistryKey.of(RegistryKeys.TRIM_MATERIAL,
            Identifier.of(TLOTD.MOD_ID, "jurassoline"));
    public static final RegistryKey<ArmorTrimMaterial> CINNABAR = RegistryKey.of(RegistryKeys.TRIM_MATERIAL,
            Identifier.of(TLOTD.MOD_ID, "cinnabar"));
    public static final RegistryKey<ArmorTrimMaterial> NEBULAR = RegistryKey.of(RegistryKeys.TRIM_MATERIAL,
            Identifier.of(TLOTD.MOD_ID, "nebular"));
    public static final RegistryKey<ArmorTrimMaterial> MITHRIL = RegistryKey.of(RegistryKeys.TRIM_MATERIAL,
            Identifier.of(TLOTD.MOD_ID, "mithril"));

    public static void bootstrap(Registerable<ArmorTrimMaterial> registerable) {
        register(registerable, HELIORITE, Registries.ITEM.getEntry(ModItems.HELIORITE_INGOT),
                Style.EMPTY.withColor(TextColor.fromRgb(0xdb74e4)), 1.0f);
        register(registerable, ENDURIUM, Registries.ITEM.getEntry(ModItems.ENDURIUM_INGOT),
                Style.EMPTY.withColor(TextColor.fromRgb(0x005648)), 0.9f);
        register(registerable, PALLADIUM, Registries.ITEM.getEntry(ModItems.PALLADIUM_INGOT),
                Style.EMPTY.withColor(TextColor.fromRgb(0xc94722)), 0.5f);
        register(registerable, JURASSOLINE, Registries.ITEM.getEntry(ModItems.JURASSOLINE_INGOT),
                Style.EMPTY.withColor(TextColor.fromRgb(0x70b726)), 0.7f);
        register(registerable, CINNABAR, Registries.ITEM.getEntry(ModItems.CINNABAR_INGOT),
                Style.EMPTY.withColor(TextColor.fromRgb(0x8a0025)), 0.4f);
        register(registerable, NEBULAR, Registries.ITEM.getEntry(ModItems.NEBULAR_INGOT),
                Style.EMPTY.withColor(TextColor.fromRgb(0x371d31)), 0.3f);
        register(registerable, MITHRIL, Registries.ITEM.getEntry(ModItems.MITHRIL_INGOT),
                Style.EMPTY.withColor(TextColor.fromRgb(0xf6f6f6)), 0.1f);
    }

    private static void register(Registerable<ArmorTrimMaterial> registerable, RegistryKey<ArmorTrimMaterial> armorTrimKey, RegistryEntry<Item> item, Style style, float itemModelIndex) {

        ArmorTrimMaterial trimMaterial = new ArmorTrimMaterial(armorTrimKey.getValue().getPath(), item, itemModelIndex, Map.of(),
                Text.translatable(Util.createTranslationKey("trim_material", armorTrimKey.getValue())).fillStyle(style));

        registerable.register(armorTrimKey, trimMaterial);
    }
}
