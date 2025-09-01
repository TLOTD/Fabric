package net.tlotd.item.custom;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.item.ArrowItem;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.tlotd.entity.custom.ArmorPiercingArrowEntity;

public class ArmorPiercingArrowItem extends ArrowItem {
    public ArmorPiercingArrowItem(Settings settings) {
        super(settings);
    }

    @Override
    public PersistentProjectileEntity createArrow(World world, ItemStack stack, LivingEntity shooter) {
        return new ArmorPiercingArrowEntity(world, shooter);
    }
}