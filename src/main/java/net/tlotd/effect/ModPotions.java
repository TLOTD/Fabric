package net.tlotd.effect;

import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.potion.Potion;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.Identifier;
import net.tlotd.TLOTD;

public class ModPotions {
    public static final RegistryEntry<Potion> DRUNKENNESS_POTION = registerPotion("drunkenness_potion",
            new Potion(new StatusEffectInstance(ModEffects.DRUNKENNESS, 200, 0)));

    public static final RegistryEntry<Potion> IRRADIATED_POTION = registerPotion("irradiated_potion",
            new Potion(new StatusEffectInstance(ModEffects.IRRADIATED, 600, 0, false, false, true)));

    public static RegistryEntry<Potion> registerPotion(String name, Potion potion) {
        return Registry.registerReference(Registries.POTION, Identifier.of(TLOTD.MOD_ID, name), potion);
    }

    public static void registerPotions() {
    }
}