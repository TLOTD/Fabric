package net.tlotd.effect;

import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.tlotd.TLOTD;

public class ModEffects {
    public static final StatusEffect DRUNKENNESS = Registry.register(Registries.STATUS_EFFECT, new Identifier(TLOTD.MOD_ID, "drunkenness"), new DrunkennessEffect(StatusEffectCategory.HARMFUL, 0xebba34, 2.0F)).addAttributeModifier(EntityAttributes.GENERIC_ATTACK_DAMAGE, "6D98F6DB-3DA5-48F7-9BAD-200573F5A63F", 0.0F, EntityAttributeModifier.Operation.ADDITION);
    public static final StatusEffect STONED = Registry.register(Registries.STATUS_EFFECT, new Identifier(TLOTD.MOD_ID, "stoned"), new StonedEffect(StatusEffectCategory.HARMFUL, 0x5b754f)).addAttributeModifier(EntityAttributes.GENERIC_ATTACK_SPEED, "42DB3C78-0ACD-4207-8F54-F2054F79EE40", -0.1F, EntityAttributeModifier.Operation.MULTIPLY_TOTAL);
    public static final StatusEffect IRRADIATED = Registry.register(Registries.STATUS_EFFECT, new Identifier(TLOTD.MOD_ID, "irradiated"), new IrradiatedEffect(StatusEffectCategory.HARMFUL, 0x00ff8c));
    public static final StatusEffect HYPOXIA = Registry.register(Registries.STATUS_EFFECT, new Identifier(TLOTD.MOD_ID, "hypoxia"), new HypoxiaEffect(StatusEffectCategory.HARMFUL, 0x000000));

    public static final StatusEffect SUBSPACE_RESISTANCE = Registry.register(Registries.STATUS_EFFECT, new Identifier(TLOTD.MOD_ID, "subspace_resistance"), new SubspaceResistanceEffect(StatusEffectCategory.NEUTRAL, 0xffffff));
    public static final StatusEffect SUBSPACE_SICKNESS = Registry.register(Registries.STATUS_EFFECT, new Identifier(TLOTD.MOD_ID, "subspace_sickness"), new SubspaceSicknessEffect(StatusEffectCategory.HARMFUL, 0x000000));

    public static void registerEffects() {
    }
}
