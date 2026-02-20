package net.tlotd.util;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtElement;
import net.minecraft.nbt.NbtList;
import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;
import net.tlotd.item.ModItems;
import org.jetbrains.annotations.NotNull;

import java.util.Locale;

import static net.tlotd.util.AugmentNbtHelper.getAugmentLevel;

public class EnergyNbtHelper {
    public static final long MAX_AMOUNT = 1000000L;
    public static long getMaxEnergyItem(ItemStack stack) {
        Identifier id = Registries.ITEM.getId(stack.getItem());
        if (id.equals(new Identifier("mekanism", "seismic_reader"))) {
            return 12000L;
        } else if (id.equals(new Identifier("mekanism", "configurator")) || id.equals(new Identifier("mekanism", "network_reader"))) {
            return 60000L;
        } else if (id.equals(new Identifier("mekanism", "free_runners")) || id.equals(new Identifier("mekanism", "free_runners_armored"))) {
            return 64000L;
        } else if (id.equals(new Identifier("mekanism", "robit"))) {
            return 100000L;
        } else if (id.equals(new Identifier("mekanism", "electric_bow"))) {
            return 120000L;
        } else if (id.equals(new Identifier("mekanism", "atomic_disassembler")) || id.equals(new Identifier("mekanism", "energy_tablet")) || id.equals(new Identifier("mekanism", "portable_teleporter"))) {
            return 1000000L;
        } else if (id.equals(new Identifier("mekanism", "meka_tool")) || id.equals(new Identifier("mekanism", "mekasuit_helmet")) || id.equals(new Identifier("mekanism", "mekasuit_bodyarmor")) || id.equals(new Identifier("mekanism", "mekasuit_pants")) || id.equals(new Identifier("mekanism", "mekasuit_boots"))) {
            return 16000000L;
        }
        int max = 0;
        if (stack.isIn(ModTags.Items.HEV_CHARGER_CHARGABLE)) {
            max = 1;
        }
        if (getAugmentLevel(stack, "tlotd:battery_pack") > 0) {
            max = max + getAugmentLevel(stack, "tlotd:battery_pack");
        }
        return max*MAX_AMOUNT;
    }

    public static long getEnergy(ItemStack stack) {
        if (!stack.hasNbt()) return 0;
        NbtCompound tag = stack.getNbt();
        if (!tag.contains("mekData")) return 0;
        NbtCompound mekData = tag.getCompound("mekData");
        if (!mekData.contains("EnergyContainers", NbtElement.LIST_TYPE)) return 0;
        NbtList list = mekData.getList("EnergyContainers", NbtElement.COMPOUND_TYPE);
        if (list.isEmpty()) return 0;
        NbtCompound container = list.getCompound(0);
        if (!container.contains("stored")) return 0;
        try {
            return Long.parseLong(container.getString("stored"));
        } catch (NumberFormatException e) {
            return 0;
        }
    }

    public static void setEnergy(ItemStack stack, long amount) {
        amount = Math.min(amount, getMaxEnergyItem(stack));
        amount = Math.max(amount, 0);
        NbtCompound root = stack.getOrCreateNbt();
        NbtCompound mekData;
        if (root.contains("mekData")) {
            mekData = root.getCompound("mekData");
        } else {
            mekData = new NbtCompound();
            root.put("mekData", mekData);
        }
        NbtList containers;
        if (mekData.contains("EnergyContainers", NbtElement.LIST_TYPE)) {
            containers = mekData.getList("EnergyContainers", NbtElement.COMPOUND_TYPE);
        } else {
            containers = new NbtList();
            mekData.put("EnergyContainers", containers);
        }
        NbtCompound container;
        if (containers.isEmpty()) {
            container = new NbtCompound();
            container.putByte("Container", (byte) 0);
            containers.add(container);
        } else {
            container = containers.getCompound(0);
        }
        container.putString("stored", Long.toString(amount));
    }

    public static @NotNull String getEnergyString(long amount) {
        if (amount < 1000) {
            return Long.toString(amount);
        }
        final String[] units = {"K", "M", "B", "T", "P", "E"};
        int unitIndex = -1;
        double value = amount;
        while (value >= 1000 && unitIndex < units.length - 1) {
            value /= 1000.0;
            unitIndex++;
        }
        if (value >= 10 || value % 1 == 0) {
            return String.format(Locale.ROOT, "%.0f%s", value, units[unitIndex]);
        } else {
            return String.format(Locale.ROOT, "%.1f%s", value, units[unitIndex]);
        }
    }
}