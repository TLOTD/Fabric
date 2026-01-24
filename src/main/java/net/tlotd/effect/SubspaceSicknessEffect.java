package net.tlotd.effect;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.tlotd.util.ModDamageTypes;

public class SubspaceSicknessEffect extends StatusEffect {
    protected SubspaceSicknessEffect(StatusEffectCategory category, int color) {
        super(category, color);
    }

    @Override
    public void applyUpdateEffect(LivingEntity entity, int amplifier) {
        if(!entity.getWorld().isClient()) {
            entity.damage(ModDamageTypes.of(entity.getWorld(), ModDamageTypes.SUBSPACE_SICKNESS), 1.0F);
        }
        super.applyUpdateEffect(entity, amplifier);
    }

    @Override
    public boolean canApplyUpdateEffect(int duration, int amplifier) {
        int i = 20 >> amplifier;
        if (i > 0) {
            return duration % i == 0;
        } else {
            return true;
        }
    }
}
