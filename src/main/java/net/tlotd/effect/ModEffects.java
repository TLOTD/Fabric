package net.tlotd.effect;

import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.tlotd.TLOTD;

public class ModEffects {
    public static StatusEffect DRUNK;
    public static StatusEffect IRRADIATED;
    public static StatusEffect HYPOXIA;

    public static StatusEffect registerDrunkEffect(String name) {
        return Registry.register(Registries.STATUS_EFFECT, new Identifier(TLOTD.MOD_ID, name), new DrunkEffect(StatusEffectCategory.HARMFUL, 0xebba34));
    }
    public static StatusEffect registerIrradiatedEffect(String name) {
        return Registry.register(Registries.STATUS_EFFECT, new Identifier(TLOTD.MOD_ID, name), new IrradiatedEffect(StatusEffectCategory.HARMFUL, 0x00ff8c));
    }
    public static StatusEffect registerHypoxiaEffect(String name) {
        return Registry.register(Registries.STATUS_EFFECT, new Identifier(TLOTD.MOD_ID, name), new HypoxiaEffect(StatusEffectCategory.HARMFUL, 0x000000));
    }

    public static void registerEffects() {
        DRUNK = registerDrunkEffect("drunk");
        IRRADIATED = registerIrradiatedEffect("irradiated");
        HYPOXIA = registerHypoxiaEffect("hypoxia");
    }
}
