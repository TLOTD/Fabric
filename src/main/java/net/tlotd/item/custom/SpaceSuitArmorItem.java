package net.tlotd.item.custom;

import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.world.World;
import net.tlotd.util.AdAstraOxygenNbtHelper;
import org.jetbrains.annotations.Nullable;

import java.util.List;

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

    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        String oxygen = "0 \uD83E\uDEA3 / 1K \uD83E\uDEA3";
        if (Screen.hasShiftDown()) {
            oxygen = "0 \uD83E\uDEA3 / 1,000 \uD83E\uDEA3";
            if (stack.hasNbt()) {
                long oxygenAmount = AdAstraOxygenNbtHelper.getOxygen(stack);
                int displayAmount = (int) Math.round((double) oxygenAmount * 1000 / AdAstraOxygenNbtHelper.MAX_AMOUNT);
                String formattedOxygen = String.format("%,d", displayAmount);
                oxygen = formattedOxygen + " \uD83E\uDEA3 / 1,000 \uD83E\uDEA3";
            }
        } else {
            if (stack.hasNbt()) {
                long oxygenAmount = AdAstraOxygenNbtHelper.getOxygen(stack);
                int displayAmount = (int) Math.round((double) oxygenAmount * 1000 / AdAstraOxygenNbtHelper.MAX_AMOUNT);
                String formattedOxygen;
                if (displayAmount == 1000) {
                    formattedOxygen = "1K";
                } else {
                    formattedOxygen = String.valueOf(displayAmount);
                }
                oxygen = formattedOxygen + " \uD83E\uDEA3 / 1K \uD83E\uDEA3";
            }
        }
        tooltip.add(Text.translatable("item.tlotd.oxygen_level.tooltip", oxygen).formatted(Formatting.GOLD));
        tooltip.add(Text.literal(""));
        tooltip.add(Text.translatable("item.tlotd.hypoxia_proof_armor.tooltip").formatted(Formatting.GRAY));
        tooltip.add(Text.literal(" ").append(Text.translatable("item.tlotd.hypoxia_proof_armor.tooltip_2").formatted(Formatting.BLUE)));
        super.appendTooltip(stack, world, tooltip, context);
    }

    public float getProgress(ItemStack stack) {
        return AdAstraOxygenNbtHelper.MAX_AMOUNT - AdAstraOxygenNbtHelper.getOxygen(stack);
    }

    @Override
    public boolean isItemBarVisible(ItemStack stack) {
        return true;
    }

    public int getItemBarStep(ItemStack stack) {
        return Math.round(13.0f - getProgress(stack) * 13.0f / AdAstraOxygenNbtHelper.MAX_AMOUNT);
    }

    @Override
    public int getItemBarColor(ItemStack stack) {
        return 0x9fc5e8;
    }
}