package net.tlotd.item.custom;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.tlotd.effect.ModEffects;

public class CannabisCookieItem extends Item {
    public CannabisCookieItem(Settings settings) {
        super(settings);
    }

    @Override
    public ItemStack finishUsing(ItemStack stack, World world, LivingEntity user) {
        user.addStatusEffect(new StatusEffectInstance(ModEffects.STONED, 600));
        return super.finishUsing(stack, world, user);
    }
}
