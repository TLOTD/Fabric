package net.tlotd.util;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtElement;
import net.minecraft.nbt.NbtList;
import org.jetbrains.annotations.NotNull;

import static net.tlotd.util.AugmentNbtHelper.getAugmentLevel;

public class AdAstraOxygenNbtHelper {
    public static final String FLUID_ID = "ad_astra:oxygen";
    public static final long MAX_AMOUNT = 81000L;

    public static long getMaxOxygenItem(ItemStack stack) {
        int max = 0;
        if (stack.isIn(ModTags.Items.OXYGEN_STORING_4K)) {
            max = 4;
        } else if (stack.isIn(ModTags.Items.OXYGEN_STORING_2K)) {
            max = 2;
        } else if (stack.isIn(ModTags.Items.OXYGEN_STORING_1K)) {
            max = 1;
        }
        if (getAugmentLevel(stack, "tlotd:oxygen_tank") > 0) {
            max = max + getAugmentLevel(stack, "tlotd:oxygen_tank");
        }
        return max*MAX_AMOUNT;
    }

    public static long getOxygen(ItemStack stack) {
        if (!stack.hasNbt()) return 0L;
        NbtCompound root = stack.getNbt();
        if (!root.contains("BotariumData")) return 0L;
        NbtCompound botarium = root.getCompound("BotariumData");
        if (!botarium.contains("StoredFluids")) return 0L;
        NbtList fluids = botarium.getList("StoredFluids", NbtElement.COMPOUND_TYPE);
        if (fluids.isEmpty()) return 0L;
        NbtCompound fluidEntry = fluids.getCompound(0);
        if (!FLUID_ID.equals(fluidEntry.getString("Fluid"))) return 0L;
        return fluidEntry.getLong("Amount");
    }

    public static void setOxygen(ItemStack stack, long amount) {
        amount = Math.min(amount, getMaxOxygenItem(stack));
        amount = Math.max(amount, 0);
        NbtCompound root = stack.getOrCreateNbt();
        NbtCompound botarium = root.getCompound("BotariumData");
        NbtList fluids = botarium.getList("StoredFluids", NbtElement.COMPOUND_TYPE);
        NbtCompound fluidEntry;
        if (fluids.isEmpty()) {
            fluidEntry = new NbtCompound();
            fluidEntry.putString("Fluid", FLUID_ID);
            fluids.add(fluidEntry);
        } else {
            fluidEntry = fluids.getCompound(0);
        }
        fluidEntry.putLong("Amount", amount);
        fluids.set(0, fluidEntry);
        botarium.put("StoredFluids", fluids);
        root.put("BotariumData", botarium);
        stack.setNbt(root);
    }

    public static @NotNull String getOxygenString(double oxygenAmount) {
        int displayAmount = (int) Math.round(oxygenAmount * 1000 / AdAstraOxygenNbtHelper.MAX_AMOUNT);
        String formattedOxygen;
        if (displayAmount >= 1000) {
            int thousands = displayAmount / 1000;
            int hundreds = (displayAmount % 1000) / 100;
            if (hundreds == 0) {
                formattedOxygen = thousands + "K";
            } else {
                formattedOxygen = thousands + "." + hundreds + "K";
            }
        } else {
            formattedOxygen = String.valueOf(displayAmount);
        }
        return formattedOxygen;
    }
}