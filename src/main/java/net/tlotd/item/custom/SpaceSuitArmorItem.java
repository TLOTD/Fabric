package net.tlotd.item.custom;

import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class SpaceSuitArmorItem extends ArmorItem {
    public SpaceSuitArmorItem(ArmorMaterial material, Type type, Settings settings) {
        super(material, type, settings);
    }

    @Override
    public void inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected) {
        if (!world.isClient() && entity.isPlayer()) {
            if (stack == ((PlayerEntity) entity).getInventory().getArmorStack(2)) {
                String oxygen = "0%";
                Float oxygenPercentage;
                if(stack.hasNbt()) {
                    oxygenPercentage = stack.getNbt().getInt("tlotd:oxygen")/10.0F;
                    oxygen = String.join("",String.valueOf(oxygenPercentage), "%");
                }
                ((PlayerEntity) entity).sendMessage(Text.translatable("item.tlotd.oxygen_level.tooltip", oxygen), true);
            }
        }
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        super.appendTooltip(stack, world, tooltip, context);
        String oxygen = "0%";
        Float oxygenPercentage;
        if(stack.hasNbt()) {
            oxygenPercentage = stack.getNbt().getInt("tlotd:oxygen")/10.0F;
            oxygen = String.join("",String.valueOf(oxygenPercentage), "%");
        }
        tooltip.add(Text.translatable("item.tlotd.oxygen_level.tooltip", oxygen).formatted(Formatting.GRAY));
        tooltip.add(Text.literal(""));
        tooltip.add(Text.translatable("item.tlotd.hypoxia_proof_armor.tooltip").formatted(Formatting.GRAY));
        tooltip.add(Text.literal(" ").append(Text.translatable("item.tlotd.hypoxia_proof_armor.tooltip_2").formatted(Formatting.BLUE)));
    }

    public float getProgress(ItemStack stack) {
        if (!stack.hasNbt()) {
            return 1000f;
        } else {
            return 1000f - stack.getNbt().getInt("tlotd:oxygen");
        }
    }

    @Override
    public boolean isItemBarVisible(ItemStack stack) {
        return true;
    }

    public int getItemBarStep(ItemStack stack) {
        return Math.round(13.0f - getProgress(stack) * 13.0f / 1000f);
    }

    @Override
    public int getItemBarColor(ItemStack stack) {
        return 0x9fc5e8;
    }
}