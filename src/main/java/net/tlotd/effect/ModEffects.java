package net.tlotd.effect;

import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.tlotd.TLOTD;

public class ModEffects {
    public static final StatusEffect DRUNK = Registry.register(Registries.STATUS_EFFECT, new Identifier(TLOTD.MOD_ID, "drunk"), new DrunkEffect(StatusEffectCategory.HARMFUL, 0xebba34));;
    public static final StatusEffect STONED = Registry.register(Registries.STATUS_EFFECT, new Identifier(TLOTD.MOD_ID, "stoned"), new StonedEffect(StatusEffectCategory.HARMFUL, 0x5b754f));
    public static final StatusEffect IRRADIATED = Registry.register(Registries.STATUS_EFFECT, new Identifier(TLOTD.MOD_ID, "irradiated"), new IrradiatedEffect(StatusEffectCategory.HARMFUL, 0x00ff8c));
    public static final StatusEffect HYPOXIA = Registry.register(Registries.STATUS_EFFECT, new Identifier(TLOTD.MOD_ID, "hypoxia"), new HypoxiaEffect(StatusEffectCategory.HARMFUL, 0x000000));

    public static final StatusEffect SUBSPACE_RESISTANCE = Registry.register(Registries.STATUS_EFFECT, new Identifier(TLOTD.MOD_ID, "subspace_resistance"), new SubspaceResistanceEffect(StatusEffectCategory.NEUTRAL, 0xffffff));
    public static final StatusEffect SUBSPACE_SICKNESS = Registry.register(Registries.STATUS_EFFECT, new Identifier(TLOTD.MOD_ID, "subspace_sickness"), new SubspaceSicknessEffect(StatusEffectCategory.HARMFUL, 0x000000));

    public static void registerEffects() {
    }
}
