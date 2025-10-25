package net.tlotd.enchantments;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ItemStack;
import net.tlotd.item.custom.PipeItem;

public class ResourcefulSmokingEnchantment extends Enchantment {
    public ResourcefulSmokingEnchantment() {
        super(Rarity.UNCOMMON, EnchantmentTarget.WEAPON, new EquipmentSlot[]{EquipmentSlot.MAINHAND});
    }

    @Override
    public int getMaxLevel() {
        return 2;
    }

    @Override
    public boolean isAcceptableItem(ItemStack stack) {
        return stack.getItem() instanceof PipeItem;
    }

    @Override
    protected boolean canAccept(Enchantment other) {
        return super.canAccept(other) || other == ModEnchantments.REFILL_CHARGES;
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