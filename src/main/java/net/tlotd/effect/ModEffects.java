package net.tlotd.effect;

import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.Identifier;
import net.tlotd.TLOTD;

public class ModEffects {

    private static RegistryEntry<StatusEffect> register(String id, StatusEffect statusEffect) {
        return Registry.registerReference(Registries.STATUS_EFFECT, Identifier.of(TLOTD.MOD_ID, id), statusEffect);
    }

    public static final RegistryEntry<StatusEffect> DRUNKENNESS = register("drunkenness", (new DrunkennessEffect(StatusEffectCategory.HARMFUL, 0xebba34, 2.0F)).addAttributeModifier(EntityAttributes.GENERIC_ATTACK_DAMAGE, Identifier.of(TLOTD.MOD_ID, "effect.drunkenness"), 1.0F, EntityAttributeModifier.Operation.ADD_VALUE));
    public static final RegistryEntry<StatusEffect> STONED = register("stoned", (new StonedEffect(StatusEffectCategory.HARMFUL, 0x5b754f)).addAttributeModifier(EntityAttributes.GENERIC_ATTACK_SPEED, Identifier.of(TLOTD.MOD_ID, "effect.stoned"), -0.1F, EntityAttributeModifier.Operation.ADD_MULTIPLIED_TOTAL));
    public static final RegistryEntry<StatusEffect> IRRADIATED = register("irradiated", (new IrradiatedEffect(StatusEffectCategory.HARMFUL, 0x00ff8c)));
    public static final RegistryEntry<StatusEffect> HYPOXIA = register("hypoxia", (new HypoxiaEffect(StatusEffectCategory.HARMFUL, 0x000000)));

    public static final RegistryEntry<StatusEffect> SUBSPACE_RESISTANCE = register("subspace_resistance", (new SubspaceResistanceEffect(StatusEffectCategory.NEUTRAL, 0xffffff)));
    public static final RegistryEntry<StatusEffect> SUBSPACE_SICKNESS = register("subspace_sickness", (new SubspaceSicknessEffect(StatusEffectCategory.HARMFUL, 0x000000)));

    public static void registerEffects() {
    }
}
