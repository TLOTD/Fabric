package net.tlotd.effect;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
//import net.tlotd.entity.custom.SeatEntity;

public class DrunkennessEffect extends StatusEffect {

    protected DrunkennessEffect(StatusEffectCategory category, int color, double modifier) {
        super(category, color);
    }

    @Override
    public boolean applyUpdateEffect(LivingEntity entity, int amplifier) {
        /*if(!entity.getWorld().isClient()) {
            if (!(entity.getVehicle() instanceof SeatEntity)) {
                entity.stopRiding();
            }
        }*/
        return super.applyUpdateEffect(entity, amplifier);
    }

    @Override
    public void onApplied(LivingEntity entity, int amplifier) {
        super.onApplied(entity, amplifier);
    }

    @Override
    public boolean canApplyUpdateEffect(int duration, int amplifier) {
        return true;
    }
}
