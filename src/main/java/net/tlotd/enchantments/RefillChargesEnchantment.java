package net.tlotd.enchantments;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ItemStack;
import net.tlotd.item.ModItems;
import net.tlotd.item.custom.MithrilMirrorItem;
import net.tlotd.item.custom.PipeItem;

public class RefillChargesEnchantment extends Enchantment {
    public RefillChargesEnchantment() {
        super(Rarity.RARE, EnchantmentTarget.WEAPON, new EquipmentSlot[]{EquipmentSlot.MAINHAND});
    }

    @Override
    public int getMaxLevel() {
        return 1;
    }

    @Override
    public boolean isAcceptableItem(ItemStack stack) {
        return (stack.getItem() instanceof PipeItem && !stack.isOf(ModItems.JOINT)) || stack.getItem() instanceof MithrilMirrorItem;
    }

    @Override
    protected boolean canAccept(Enchantment other) {
        return (super.canAccept(other) || other == ModEnchantments.RESOURCEFUL_SMOKING || other == ModEnchantments.DEPTH_OF_THE_ABYSS || other == ModEnchantments.TRANSDIMENSIONAL) && other != ModEnchantments.CURSED_REFLECTION;
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