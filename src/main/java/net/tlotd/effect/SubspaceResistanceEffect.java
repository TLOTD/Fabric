package net.tlotd.effect;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.AttributeContainer;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.tlotd.world.dimension.ModDimensions;

public class SubspaceResistanceEffect extends StatusEffect {
    protected SubspaceResistanceEffect(StatusEffectCategory category, int color) {
        super(category, color);
    }

    @Override
    public void onApplied(LivingEntity entity, AttributeContainer attributes, int amplifier) {
        entity.removeStatusEffect(ModEffects.SUBSPACE_SICKNESS);
    }

    @Override
    public void onRemoved(LivingEntity entity, AttributeContainer attributes, int amplifier) {
        if (entity.getWorld().getRegistryKey().equals(ModDimensions.BACKROOMS_LEVEL_KEY)) {
            entity.addStatusEffect(new StatusEffectInstance(ModEffects.SUBSPACE_SICKNESS, -1, amplifier,true,false, true));
        }
    }
}
