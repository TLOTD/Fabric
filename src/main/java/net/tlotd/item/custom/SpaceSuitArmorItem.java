package net.tlotd.item.custom;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.world.World;
import net.tlotd.util.AdAstraOxygenNbtHelper;

public class SpaceSuitArmorItem extends ArmorItem {
    public SpaceSuitArmorItem(ArmorMaterial material, Type type, Settings settings) {
        super(material, type, settings);
    }

    @Override
    public ItemStack getDefaultStack() {
        ItemStack base = new ItemStack(this);
        AdAstraOxygenNbtHelper.setOxygen(base, 0);
        return base;
    }

    @Override
    public void inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected) {
        if (!world.isClient() && entity.isPlayer()) {
            if (stack == ((PlayerEntity) entity).getInventory().getArmorStack(2)) {
                String oxygen = "0%";
                if (stack.hasNbt()) {
                    long oxygenAmount = AdAstraOxygenNbtHelper.getOxygen(stack);
                    int displayAmount = (int) Math.round((double) oxygenAmount * 100 / AdAstraOxygenNbtHelper.MAX_AMOUNT);
                    oxygen = displayAmount + "%";
                }
                ((PlayerEntity) entity).sendMessage(Text.translatable("item.tlotd.oxygen_level.tooltip", oxygen), true);
            }
        }
    }

    public float getProgress(ItemStack stack) {
        return AdAstraOxygenNbtHelper.getMaxOxygenItem(stack) - AdAstraOxygenNbtHelper.getOxygen(stack);
    }

    @Override
    public boolean isItemBarVisible(ItemStack stack) {
        return true;
    }

    public int getItemBarStep(ItemStack stack) {
        return Math.round(13.0f - getProgress(stack) * 13.0f / AdAstraOxygenNbtHelper.getMaxOxygenItem(stack));
    }

    @Override
    public int getItemBarColor(ItemStack stack) {
        return 0xDAE6F0;
    }
}