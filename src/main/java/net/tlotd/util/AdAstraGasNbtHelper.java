package net.tlotd.util;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtElement;
import net.minecraft.nbt.NbtList;
import net.minecraft.text.Text;
import net.minecraft.util.collection.DefaultedList;
import net.tlotd.item.custom.SpaceSuitArmorItem;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;

import static net.tlotd.util.AugmentNbtHelper.getAugmentLevel;

public class AdAstraGasNbtHelper {
    public static final String AD_ASTRA_OXYGEN_ID = "ad_astra:oxygen";
    public static final String TLOTD_WITHERED_AIR = "tlotd:withered_air";
    public static final String TLOTD_PIPE_WEED_SMOKE = "tlotd:pipe_weed_smoke";
    public static final long MAX_AMOUNT = 81000L;

    public static long getMaxGasItem(ItemStack stack) {
        int max = 0;
        if (stack.isIn(ModTags.Items.OXYGEN_STORING_4K)) {
            max = 4;
        } else if (stack.isIn(ModTags.Items.OXYGEN_STORING_3K)) {
            max = 3;
        } else if (stack.isIn(ModTags.Items.OXYGEN_STORING_2K)) {
            max = 2;
        } else if (stack.isIn(ModTags.Items.OXYGEN_STORING_1K)) {
            max = 1;
        }
        if (getAugmentLevel(stack, "tlotd:oxygen_tank") > 0) {
            max = max + getAugmentLevel(stack, "tlotd:oxygen_tank");
        }
        return max * MAX_AMOUNT;
    }

    public static String getGas(ItemStack stack) {
        if (!stack.hasNbt()) return "empty";
        NbtCompound root = stack.getNbt();
        if (!root.contains("BotariumData")) return "empty";
        NbtCompound botarium = root.getCompound("BotariumData");
        if (!botarium.contains("StoredFluids")) return "empty";
        NbtList fluids = botarium.getList("StoredFluids", NbtElement.COMPOUND_TYPE);
        if (fluids.isEmpty()) return "empty";
        NbtCompound fluidEntry = fluids.getCompound(0);
        if (fluidEntry.getLong("Amount") == 0L) return "empty";
        return fluidEntry.getString("Fluid");
    }

    public static long getGasAmount(ItemStack stack, String gasId) {
        if (!stack.hasNbt()) return 0L;
        NbtCompound root = stack.getNbt();
        if (!root.contains("BotariumData")) return 0L;
        NbtCompound botarium = root.getCompound("BotariumData");
        if (!botarium.contains("StoredFluids")) return 0L;
        NbtList fluids = botarium.getList("StoredFluids", NbtElement.COMPOUND_TYPE);
        if (fluids.isEmpty()) return 0L;
        NbtCompound fluidEntry = fluids.getCompound(0);
        if (!gasId.equals(fluidEntry.getString("Fluid"))) return 0L;
        return fluidEntry.getLong("Amount");
    }

    public static void setGasAmount(ItemStack stack, String gasId, long amount) {
        amount = Math.max(amount, 0);
        NbtCompound root = stack.getOrCreateNbt();
        NbtCompound botarium = root.getCompound("BotariumData");
        NbtList fluids = botarium.getList("StoredFluids", NbtElement.COMPOUND_TYPE);
        NbtCompound fluidEntry;
        if (fluids.isEmpty()) {
            fluidEntry = new NbtCompound();
            fluidEntry.putString("Fluid", gasId);
            fluids.add(fluidEntry);
        } else {
            fluidEntry = fluids.getCompound(0);
            fluidEntry.putString("Fluid", gasId);
        }
        fluidEntry.putLong("Amount", amount);
        fluids.set(0, fluidEntry);
        botarium.put("StoredFluids", fluids);
        root.put("BotariumData", botarium);
        stack.setNbt(root);
    }

    public static Text gasName(String gas) {
        if (gas == null || gas.equals("empty")) {
            return Text.translatable("gas.empty");
        }
        String key = "gas." + gas.replace(':', '.');
        return Text.translatable(key);
    }

    public static long getOxygen(ItemStack stack) {
        return getGasAmount(stack, AD_ASTRA_OXYGEN_ID);
    }

    public static void setOxygen(ItemStack stack, long amount) {
        setGasAmount(stack, AD_ASTRA_OXYGEN_ID, amount);
    }

    public static @NotNull String getOxygenString(double oxygenAmount) {
        int displayAmount = (int) Math.round(oxygenAmount * 1000 / AdAstraGasNbtHelper.MAX_AMOUNT);
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

    public static boolean canMitigateDamage(ItemStack suit) {
        boolean out = false;
        if (getGasAmount(suit, AD_ASTRA_OXYGEN_ID) > 0) {
            out = true;
        } else if (getGasAmount(suit, TLOTD_PIPE_WEED_SMOKE) > 0) {
            out = true;
        } else if (suit.getItem() instanceof SpaceSuitArmorItem) {
            out = getGasAmountFromSuit(suit, AD_ASTRA_OXYGEN_ID) > 0 || getGasAmountFromSuit(suit, TLOTD_PIPE_WEED_SMOKE) > 0;
        }
        return out;
    }

    public static boolean consumeMitigationGas(ItemStack suit, long amount) {
        if (modifyGasInSuit(suit, AD_ASTRA_OXYGEN_ID, -amount) != 0) {
            return true;
        }
        if (modifyGasInSuit(suit, TLOTD_PIPE_WEED_SMOKE, -amount) != 0) {
            return true;
        }
        return false;
    }

    public static long getGasAmountFromSuit(ItemStack suit, String gasId) {
        long total = 0;
        DefaultedList<ItemStack> tanks = SpaceSuitArmorItem.getStoredStacks(suit);
        for (ItemStack tank : tanks) {
            if (!tank.isEmpty()) {
                total += getGasAmount(tank, gasId);
            }
        }
        return total;
    }

    public static long getMaxGasFromSuit(ItemStack suit) {
        long total = 0;
        DefaultedList<ItemStack> tanks = SpaceSuitArmorItem.getStoredStacks(suit);
        for (ItemStack tank : tanks) {
            if (!tank.isEmpty()) {
                total += getMaxGasItem(tank);
            }
        }
        return total;
    }

    public static long modifyGasInSuit(ItemStack suit, String gasId, long delta) {
        DefaultedList<ItemStack> tanks = SpaceSuitArmorItem.getStoredStacks(suit);
        long remaining = delta;
        if (delta > 0) {
            for (ItemStack tank : tanks) {
                if (tank.isEmpty()) continue;
                String tankGas = getGas(tank);
                if (!tankGas.equals("empty") && !tankGas.equals(gasId)) continue;
                long current = getGasAmount(tank, gasId);
                long max = getMaxGasItem(tank);
                long space = max - current;
                long toFill = Math.min(space, remaining);
                if (toFill > 0) {
                    setGasAmount(tank, gasId, current + toFill);
                    remaining -= toFill;
                }
                if (remaining <= 0) break;
            }

        } else if (delta < 0) {
            remaining = -remaining;
            for (ItemStack tank : tanks) {
                if (tank.isEmpty()) continue;
                if (!gasId.equals(getGas(tank))) continue;
                long current = getGasAmount(tank, gasId);
                long toDrain = Math.min(current, remaining);
                if (toDrain > 0) {
                    setGasAmount(tank, gasId, current - toDrain);
                    remaining -= toDrain;
                }
                if (remaining <= 0) break;
            }
            remaining = -remaining;
        }
        SpaceSuitArmorItem.setStoredStacks(suit, tanks);
        return delta - remaining;
    }

    public static Map<String, GasInfo> getSuitGasContents(ItemStack suit) {
        Map<String, GasInfo> gases = new HashMap<>();
        DefaultedList<ItemStack> tanks = SpaceSuitArmorItem.getStoredStacks(suit);
        for (ItemStack tank : tanks) {
            if (tank.isEmpty()) {
                continue;
            }
            String gas = getGas(tank);
            long amount;
            long max = getMaxGasItem(tank);
            if (!gas.equals("empty")) {
                amount = getGasAmount(tank, gas);
            } else {
                amount = 0;
            }
            gases.compute(gas, (k, v) -> {
                if (v == null) {
                    return new GasInfo(amount, max);
                }
                v.amount += amount;
                v.max += max;
                return v;
            });
        }

        return gases;
    }

    public static class GasInfo {
        public long amount;
        public long max;

        public GasInfo(long amount, long max) {
            this.amount = amount;
            this.max = max;
        }
    }
}