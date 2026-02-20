package net.tlotd.item.custom;

import net.minecraft.item.ArmorItem;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.ItemStack;
import net.tlotd.util.EnergyNbtHelper;

public class HEVArmorItem extends ArmorItem {
    public HEVArmorItem(ArmorMaterial material, Type type, Settings settings) {
        super(material, type, settings);
    }

    @Override
    public ItemStack getDefaultStack() {
        ItemStack base = new ItemStack(this);
        EnergyNbtHelper.setEnergy(base, 0);
        return base;
    }

    public float getProgress(ItemStack stack) {
        return EnergyNbtHelper.getMaxEnergyItem(stack) - EnergyNbtHelper.getEnergy(stack);
    }

    @Override
    public boolean isItemBarVisible(ItemStack stack) {
        return true;
    }

    public int getItemBarStep(ItemStack stack) {
        return Math.round(13.0f - getProgress(stack) * 13.0f / EnergyNbtHelper.getMaxEnergyItem(stack));
    }

    @Override
    public int getItemBarColor(ItemStack stack) {
        return 0xffc074;
    }
}
