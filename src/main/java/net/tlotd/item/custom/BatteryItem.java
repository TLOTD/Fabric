package net.tlotd.item.custom;

import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.world.World;
import net.tlotd.util.EnergyHelper;
import org.jetbrains.annotations.Nullable;
import team.reborn.energy.api.base.SimpleEnergyItem;

import java.util.List;

public class BatteryItem extends Item implements SimpleEnergyItem {

    private final long capacity;
    private final long in;
    private final long out;

    public BatteryItem(Settings settings, long capacity, long in, long out) {
        super(settings);
        this.capacity = capacity;
        this.in = in;
        this.out = out;
    }

    @Override
    public long getEnergyCapacity(ItemStack stack) {
        return capacity;
    }

    @Override
    public long getEnergyMaxInput(ItemStack stack) {
        return in;
    }

    @Override
    public long getEnergyMaxOutput(ItemStack stack) {
        return out;
    }

    public float getProgress(ItemStack stack) {
        return Math.max(getEnergyCapacity(stack) - getStoredEnergy(stack), 0);
    }

    @Override
    public boolean isItemBarVisible(ItemStack stack) {
        return true;
    }

    public int getItemBarStep(ItemStack stack) {
        return Math.round(13.0f - getProgress(stack) * 13.0f / getEnergyCapacity(stack));
    }

    @Override
    public int getItemBarColor(ItemStack stack) {
        return 0xFFC074;
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        super.appendTooltip(stack, world, tooltip, context);
        String formattedPower;
        String formattedMaxPower;
        if (Screen.hasShiftDown()) {
            long powerAmount = getStoredEnergy(stack);
            formattedPower = String.format("%,d", powerAmount);
            formattedMaxPower = String.format("%,d", getEnergyCapacity(stack));
        } else {
            long powerAmount = getStoredEnergy(stack);
            formattedPower = EnergyHelper.getEnergyString((int) powerAmount);
            formattedMaxPower = EnergyHelper.getEnergyString((int) getEnergyCapacity(stack));
        }
        String formattedPower2 = formattedPower.replace(',', '.');
        String formattedMaxPower2 = formattedMaxPower.replace(',', '.');
        tooltip.add(Text.translatable("item.tlotd.energy_level.tooltip", formattedPower, formattedMaxPower, formattedPower2, formattedMaxPower2).formatted(Formatting.YELLOW));
        if (Screen.hasShiftDown()) {
            tooltip.add(Text.translatable("item.tlotd.energy_level.tooltip_shift", getEnergyMaxInput(stack), getEnergyMaxOutput(stack)).formatted(Formatting.GRAY));
        }
    }
}