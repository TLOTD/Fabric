package net.tlotd.enchantments;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.tlotd.TLOTD;

public class ModEnchantments {
    public static final Enchantment TRANSDIMENSIONAL = registerEnchant("transdimensional", new TransdimensionalMirrorEnchantment());
    public static final Enchantment DEPTH_OF_THE_ABYSS = registerEnchant("depth_of_the_abyss", new EnduringMirrorEnchantment());
    public static final Enchantment CURSED_REFLECTION = registerEnchant("shattering_reflection", new CursedReflectionEnchantment());
    public static final Enchantment REFILL_CHARGES = registerEnchant("refill_charges", new RefillChargesEnchantment());
    public static final Enchantment RESOURCEFUL_SMOKING = registerEnchant("resourceful_smoking", new ResourcefulSmokingEnchantment());

    private static Enchantment registerEnchant(String name, Enchantment enchantment) {
        return Registry.register(Registries.ENCHANTMENT, new Identifier(TLOTD.MOD_ID, name), enchantment);
    }

    public static void registerEnchants() {
        TLOTD.LOGGER.debug("Registering Enchantmens for " + TLOTD.MOD_ID);
    }
}