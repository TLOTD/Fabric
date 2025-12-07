package net.tlotd.enchantments;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ItemStack;
import net.tlotd.item.custom.MithrilMirrorItem;

public class CursedReflectionEnchantment extends Enchantment {
    public CursedReflectionEnchantment() {
        super(Rarity.UNCOMMON, EnchantmentTarget.WEAPON, new EquipmentSlot[]{EquipmentSlot.MAINHAND});
    }

    @Override
    public boolean isCursed() {
        return true;
    }

    @Override
    public int getMaxLevel() {
        return 1;
    }

    @Override
    public boolean isAcceptableItem(ItemStack stack) {
        return stack.getItem() instanceof MithrilMirrorItem;
    }

    @Override
    protected boolean canAccept(Enchantment other) {
        return (super.canAccept(other) ||
                other == ModEnchantments.DEPTH_OF_THE_ABYSS ||
                other == ModEnchantments.SHARPER_LENS ||
                other == ModEnchantments.REINFORCED_GLASS) &&
                (other != ModEnchantments.REFILL_CHARGES ||
                other != ModEnchantments.TRANSDIMENSIONAL);
    }

    @Override
    public int getMinPower(int level) {
        return 0;
    }

    @Override
    public int getMaxPower(int level) {
        return 127;
    }
}