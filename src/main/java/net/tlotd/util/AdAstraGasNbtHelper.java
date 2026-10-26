package net.tlotd.util;

import net.minecraft.component.ComponentType;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.NbtComponent;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtElement;
import net.minecraft.nbt.NbtList;
import net.minecraft.nbt.NbtOps;
import net.minecraft.registry.DynamicRegistryManager;
import net.minecraft.registry.Registries;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.util.collection.DefaultedList;
import net.tlotd.item.custom.SpaceSuitArmorItem;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;

import static net.tlotd.util.AugmentNbtHelper.getAugmentLevel;

public class AdAstraGasNbtHelper {

    private static final Identifier AD_ASTRA_FLUIDS_COMPONENT = Identifier.of("ad_astra", "fluids");

    private static final String FALLBACK_FLUIDS_KEY = "tlotd:fluids";

    @SuppressWarnings("unchecked")
    private static ComponentType<Object> getAdAstraFluidsComponentType() {
        ComponentType<?> type = Registries.DATA_COMPONENT_TYPE.get(AD_ASTRA_FLUIDS_COMPONENT);
        if (type == null || !Registries.DATA_COMPONENT_TYPE.containsId(AD_ASTRA_FLUIDS_COMPONENT)) {
            return null;
        }
        return (ComponentType<Object>) type;
    }

    private static NbtList getFallbackFluidList(ItemStack stack) {
        NbtComponent customData = stack.get(DataComponentTypes.CUSTOM_DATA);
        if (customData == null) {
            return new NbtList();
        }
        NbtCompound nbt = customData.copyNbt();
        if (!nbt.contains(FALLBACK_FLUIDS_KEY, NbtElement.LIST_TYPE)) {
            return new NbtList();
        }
        return nbt.getList(FALLBACK_FLUIDS_KEY, NbtElement.COMPOUND_TYPE).copy();
    }

    private static void setFallbackFluidList(ItemStack stack, NbtList fluids) {
        NbtCompound nbt = stack.getOrDefault(DataComponentTypes.CUSTOM_DATA, NbtComponent.DEFAULT).copyNbt();
        if (fluids.isEmpty()) {
            nbt.remove(FALLBACK_FLUIDS_KEY);
        } else {
            nbt.put(FALLBACK_FLUIDS_KEY, fluids.copy());
        }
        stack.set(DataComponentTypes.CUSTOM_DATA, NbtComponent.of(nbt));
    }

    private static NbtList getAdAstraFluidList(ItemStack stack, ComponentType<Object> type) {
        Object value = stack.get(type);
        if (value == null) {
            return new NbtList();
        }
        if (type.getCodec() == null) {
            throw new IllegalStateException("Ad Astra fluids component has no codec");
        }
        return type.getCodec().encodeStart(NbtOps.INSTANCE, value).result().filter(NbtList.class::isInstance).map(NbtList.class::cast).orElseThrow(() -> new IllegalStateException("Could not encode Ad Astra fluids component"));
    }

    private static NbtList getFluidList(ItemStack stack) {
        ComponentType<Object> type = getAdAstraFluidsComponentType();
        if (type == null) {
            return getFallbackFluidList(stack);
        }
        NbtList fallback = getFallbackFluidList(stack);
        NbtList adAstra = getAdAstraFluidList(stack, type);
        if (!fallback.isEmpty()) {
            if (adAstra.isEmpty()) {
                adAstra = fallback.copy();
            } else {
                for (int i = 0; i < fallback.size(); i++) {
                    NbtCompound fallbackEntry = fallback.getCompound(i);
                    String gasId = fallbackEntry.getString("id");
                    long amount = fallbackEntry.getLong("amount");
                    boolean merged = false;
                    for (int j = 0; j < adAstra.size(); j++) {
                        NbtCompound existing = adAstra.getCompound(j);
                        if (gasId.equals(existing.getString("id"))) {
                            existing.putLong("amount", existing.getLong("amount") + amount);
                            merged = true;
                            break;
                        }
                    }
                    if (!merged) {
                        adAstra.add(fallbackEntry.copy());
                    }
                }
            }
            Object migratedValue = type.getCodec().parse(NbtOps.INSTANCE, adAstra).result().orElseThrow(() -> new IllegalStateException("Could not migrate fallback gas data to Ad Astra"));
            stack.set(type, migratedValue);
            setFallbackFluidList(stack, new NbtList());
            return adAstra;
        }
        return adAstra;
    }

    private static void setFluidList(ItemStack stack, NbtList fluids) {
        ComponentType<Object> type = getAdAstraFluidsComponentType();
        if (type == null) {
            setFallbackFluidList(stack, fluids);
            return;
        }
        Object value = type.getCodec().parse(NbtOps.INSTANCE, fluids).result().orElseThrow(() -> new IllegalStateException("Could not decode Ad Astra fluids component: " + fluids));
        stack.set(type, value);
        setFallbackFluidList(stack, new NbtList());
    }

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
        NbtList fluids = getFluidList(stack);
        for (int i = 0; i < fluids.size(); i++) {
            NbtCompound entry = fluids.getCompound(i);
            if (entry.getLong("amount") > 0L) {
                return entry.getString("id");
            }
        }
        return "empty";
    }

    public static long getGasAmount(ItemStack stack, String gasId) {
        NbtList fluids = getFluidList(stack);
        for (int i = 0; i < fluids.size(); i++) {
            NbtCompound entry = fluids.getCompound(i);
            if (gasId.equals(entry.getString("id"))) {
                return entry.getLong("amount");
            }
        }
        return 0L;
    }

    public static void setGasAmount(ItemStack stack, String gasId, long amount) {
        amount = Math.max(amount, 0L);
        NbtList fluids = getFluidList(stack);
        NbtCompound matchingEntry = null;
        for (int i = 0; i < fluids.size(); i++) {
            NbtCompound entry = fluids.getCompound(i);
            if (gasId.equals(entry.getString("id"))) {
                matchingEntry = entry;
                break;
            }
        }
        if (matchingEntry != null) {
            matchingEntry.putLong("amount", amount);
        } else {
            if (!fluids.isEmpty()) {
                NbtCompound first = fluids.getCompound(0);
                first.putString("id", gasId);
                first.putLong("amount", amount);
            } else {
                NbtCompound entry = new NbtCompound();
                entry.putString("id", gasId);
                entry.putLong("amount", amount);
                fluids.add(entry);
            }
        }

        setFluidList(stack, fluids);
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

    public static boolean canMitigateDamage(ItemStack suit, DynamicRegistryManager registryManager) {
        boolean out = false;
        if (getGasAmount(suit, AD_ASTRA_OXYGEN_ID) > 0) {
            out = true;
        } else if (getGasAmount(suit, TLOTD_PIPE_WEED_SMOKE) > 0) {
            out = true;
        } else if (suit.getItem() instanceof SpaceSuitArmorItem) {
            out = getGasAmountFromSuit(suit, AD_ASTRA_OXYGEN_ID, registryManager) > 0 || getGasAmountFromSuit(suit, TLOTD_PIPE_WEED_SMOKE, registryManager) > 0;
        }
        return out;
    }

    public static boolean consumeMitigationGas(ItemStack suit, long amount, DynamicRegistryManager registryManager) {
        if (modifyGasInSuit(suit, AD_ASTRA_OXYGEN_ID, -amount, registryManager) != 0) {
            return true;
        }
        if (modifyGasInSuit(suit, TLOTD_PIPE_WEED_SMOKE, -amount, registryManager) != 0) {
            return true;
        }
        return false;
    }

    public static long getGasAmountFromSuit(ItemStack suit, String gasId, DynamicRegistryManager registryManager) {
        long total = 0;
        DefaultedList<ItemStack> tanks = SpaceSuitArmorItem.getStoredStacks(suit, registryManager);
        for (ItemStack tank : tanks) {
            if (!tank.isEmpty()) {
                total += getGasAmount(tank, gasId);
            }
        }
        return total;
    }

    public static long getMaxGasFromSuit(ItemStack suit, DynamicRegistryManager registryManager) {
        long total = 0;
        DefaultedList<ItemStack> tanks = SpaceSuitArmorItem.getStoredStacks(suit, registryManager);
        for (ItemStack tank : tanks) {
            if (!tank.isEmpty()) {
                total += getMaxGasItem(tank);
            }
        }
        return total;
    }

    public static long modifyGasInSuit(ItemStack suit, String gasId, long delta, DynamicRegistryManager registryManager) {
        DefaultedList<ItemStack> tanks = SpaceSuitArmorItem.getStoredStacks(suit, registryManager);
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
        SpaceSuitArmorItem.setStoredStacks(suit, tanks, registryManager);
        return delta - remaining;
    }

    public static Map<String, GasInfo> getSuitGasContents(ItemStack suit, DynamicRegistryManager registryManager) {
        Map<String, GasInfo> gases = new HashMap<>();
        DefaultedList<ItemStack> tanks = SpaceSuitArmorItem.getStoredStacks(suit, registryManager);
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