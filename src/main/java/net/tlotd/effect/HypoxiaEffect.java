package net.tlotd.effect;

import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.NbtCompound;
import net.tlotd.util.ModTags;

public class HypoxiaEffect extends StatusEffect {
    protected HypoxiaEffect(StatusEffectCategory category, int color) {
        super(category, color);
    }

    @Override
    public void applyUpdateEffect(LivingEntity entity, int amplifier) {
        if(!entity.getWorld().isClient()) {
            if(entity instanceof PlayerEntity player) {
                if (player.getInventory().getArmorStack(3).isIn(ModTags.Items.HYPOXIA_PROTECTION) &&
                        player.getInventory().getArmorStack(2).isIn(ModTags.Items.HYPOXIA_PROTECTION) &&
                        player.getInventory().getArmorStack(1).isIn(ModTags.Items.HYPOXIA_PROTECTION) &&
                        player.getInventory().getArmorStack(0).isIn(ModTags.Items.HYPOXIA_PROTECTION) &&
                        player.getInventory().getArmorStack(2).hasNbt() &&
                        player.getInventory().getArmorStack(2).getNbt().getInt("tlotd:oxygen") > 0) {
                    NbtCompound nbtData = new NbtCompound();
                    nbtData.putInt("tlotd:oxygen", player.getInventory().getArmorStack(2).getNbt().getInt("tlotd:oxygen")-1);
                    player.getInventory().getArmorStack(2).setNbt(nbtData);
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
