package net.tlotd.enchantments;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import net.tlotd.TLOTD;

public class ModEnchantments {
    public static final RegistryKey<Enchantment> TRANSDIMENSIONAL = of("transdimensional");
    public static final RegistryKey<Enchantment> DEPTH_OF_THE_ABYSS = of("depth_of_the_abyss");
    public static final RegistryKey<Enchantment> SHARPER_LENS = of("sharper_lens");
    public static final RegistryKey<Enchantment> REINFORCED_GLASS = of("reinforced_glass");
    public static final RegistryKey<Enchantment> CURSED_REFLECTION = of("shattering_reflection");
    public static final RegistryKey<Enchantment> REFILL_CHARGES = of("refill_charges");
    public static final RegistryKey<Enchantment> RESOURCEFUL_SMOKING = of("resourceful_smoking");

    private static RegistryKey<Enchantment> of(String name) {
        return RegistryKey.of(RegistryKeys.ENCHANTMENT, Identifier.of(TLOTD.MOD_ID, name));
    }

    public static void registerEnchants() {
        TLOTD.LOGGER.debug("Registering enchantment keys for " + TLOTD.MOD_ID);
    }
}