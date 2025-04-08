package net.tlotd.effect;

import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.damage.DamageType;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.text.Text;
import net.tlotd.util.ModTags;

public class IrradiatedEffect extends StatusEffect {
    protected IrradiatedEffect(StatusEffectCategory category, int color) {
        super(category, color);
    }

    @Override
    public void applyUpdateEffect(LivingEntity entity, int amplifier) {
        if(!entity.getWorld().isClient()) {
            if(entity instanceof PlayerEntity player){
                if (player.getInventory().getArmorStack(0).isIn(ModTags.Items.RADIATION_PROTECTION) &&
                        player.getInventory().getArmorStack(1).isIn(ModTags.Items.RADIATION_PROTECTION) &&
                        player.getInventory().getArmorStack(2).isIn(ModTags.Items.RADIATION_PROTECTION)) {
                    player.getInventory().getArmorStack(0).damage(1,player,e -> e.sendEquipmentBreakStatus(EquipmentSlot.FEET));
                    player.getInventory().getArmorStack(1).damage(1,player,e -> e.sendEquipmentBreakStatus(EquipmentSlot.LEGS));
                    player.getInventory().getArmorStack(2).damage(1,player,e -> e.sendEquipmentBreakStatus(EquipmentSlot.CHEST));
                } else entity.damage(entity.getDamageSources().magic(), 1.0F);
            } else entity.damage(entity.getDamageSources().magic(), 1.0F);
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
