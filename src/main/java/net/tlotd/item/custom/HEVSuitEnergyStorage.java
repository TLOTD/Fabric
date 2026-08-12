package net.tlotd.item.custom;

import net.fabricmc.fabric.api.transfer.v1.context.ContainerItemContext;
import net.fabricmc.fabric.api.transfer.v1.item.ItemVariant;
import net.fabricmc.fabric.api.transfer.v1.transaction.TransactionContext;
import net.minecraft.item.ItemStack;
import net.minecraft.util.collection.DefaultedList;
import team.reborn.energy.api.EnergyStorage;
import team.reborn.energy.api.base.SimpleEnergyItem;

public class HEVSuitEnergyStorage implements EnergyStorage {

    private final ContainerItemContext context;

    public HEVSuitEnergyStorage(ContainerItemContext context) {
        this.context = context;
    }

    private ItemStack getSuit() {
        return context.getItemVariant().toStack();
    }

    private DefaultedList<ItemStack> getBatteries() {
        return HEVArmorItem.getStoredStacks(getSuit());
    }

    private boolean saveSuit(ItemStack modifiedSuit, TransactionContext transaction) {
        return context.exchange(ItemVariant.of(modifiedSuit), 1, transaction) == 1;
    }

    private long getTotalCapacity() {
        long capacity = 0;
        for (ItemStack battery : getBatteries()) {
            if (battery.getItem() instanceof SimpleEnergyItem energyItem) {
                capacity += energyItem.getEnergyCapacity(battery);
            }
        }
        return capacity;
    }

    private long getTotalEnergy() {
        long energy = 0;
        for (ItemStack battery : getBatteries()) {
            if (battery.getItem() instanceof SimpleEnergyItem energyItem) {
                energy += energyItem.getStoredEnergy(battery);
            }
        }
        return energy;
    }

    @Override
    public long insert(long maxAmount, TransactionContext transaction) {
        if (maxAmount <= 0) {
            return 0;
        }
        ItemStack suit = getSuit();
        DefaultedList<ItemStack> batteries = HEVArmorItem.getStoredStacks(suit);
        long remaining = maxAmount;
        long inserted = 0;
        for (ItemStack battery : batteries) {
            if (battery.isEmpty()) {
                continue;
            }
            if (!(battery.getItem() instanceof SimpleEnergyItem energyItem)) {
                continue;
            }
            long current = energyItem.getStoredEnergy(battery);
            long capacity = energyItem.getEnergyCapacity(battery);
            long maxInput = energyItem.getEnergyMaxInput(battery);
            long available = capacity - current;
            if (available <= 0 || maxInput <= 0) {
                continue;
            }
            long amount = Math.min(remaining, available);
            amount = Math.min(amount, maxInput);
            if (amount <= 0) {
                continue;
            }
            energyItem.setStoredEnergy(battery, current + amount);
            inserted += amount;
            remaining -= amount;
            if (remaining <= 0) {
                break;
            }
        }

        if (inserted > 0) {
            HEVArmorItem.setStoredStacks(suit, batteries);
            if (!saveSuit(suit, transaction)) {
                return 0;
            }
        }

        return inserted;
    }

    @Override
    public long extract(long maxAmount, TransactionContext transaction) {
        if (maxAmount <= 0) {
            return 0;
        }
        ItemStack suit = getSuit();
        DefaultedList<ItemStack> batteries = HEVArmorItem.getStoredStacks(suit);
        long remaining = maxAmount;
        long extracted = 0;
        for (int cycle = 0; cycle < 20 && remaining > 0; cycle++) {
            boolean extractedThisCycle = false;
            for (ItemStack battery : batteries) {
                if (battery.isEmpty()) {
                    continue;
                }
                if (!(battery.getItem() instanceof SimpleEnergyItem energyItem)) {
                    continue;
                }
                long current = energyItem.getStoredEnergy(battery);
                long maxOutput = energyItem.getEnergyMaxOutput(battery);
                if (current <= 0 || maxOutput <= 0) {
                    continue;
                }
                long amount = Math.min(remaining, current);
                amount = Math.min(amount, maxOutput);
                if (amount <= 0) {
                    continue;
                }
                energyItem.setStoredEnergy(battery, current - amount);
                extracted += amount;
                remaining -= amount;
                extractedThisCycle = true;
                if (remaining <= 0) {
                    break;
                }
            }
            if (!extractedThisCycle) {
                break;
            }
        }
        if (extracted > 0) {
            HEVArmorItem.setStoredStacks(suit, batteries);
            if (!saveSuit(suit, transaction)) {
                return 0;
            }
        }
        return extracted;
    }

    @Override
    public long getAmount() {
        return getTotalEnergy();
    }

    @Override
    public long getCapacity() {
        return getTotalCapacity();
    }

    @Override
    public boolean supportsInsertion() {
        return getTotalCapacity() > 0;
    }

    @Override
    public boolean supportsExtraction() {
        return getAmount() > 0;
    }
}