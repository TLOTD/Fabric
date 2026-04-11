package net.tlotd.effect;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.AttributeContainer;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.tlotd.entity.custom.SeatEntity;

public class DrunkennessEffect extends StatusEffect {
    protected final double modifier;

    protected DrunkennessEffect(StatusEffectCategory category, int color, double modifier) {
        super(category, color);
        this.modifier = modifier;
    }

    @Override
    public void applyUpdateEffect(LivingEntity entity, int amplifier) {
        if(!entity.getWorld().isClient()) {
            if (!(entity.getVehicle() instanceof SeatEntity)) {
                entity.stopRiding();
            }
        }
        super.applyUpdateEffect(entity, amplifier);
    }

    @Override
    public void onApplied(LivingEntity entity, AttributeContainer attributes, int amplifier) {
        super.onApplied(entity, attributes, amplifier);
    }

    @Override
    public boolean canApplyUpdateEffect(int duration, int amplifier) {
        return true;
    }

    @Override
    public double adjustModifierAmount(int amplifier, EntityAttributeModifier modifier) {
        return this.modifier * (double)(amplifier + 1);
    }
}
