package net.tlotd.util;

import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.NbtComponent;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtElement;
import net.minecraft.nbt.NbtList;
import net.tlotd.components.ModComponents;

public class AugmentNbtHelper {

    private static final String AUGMENTS_KEY = "Augments";
    private static final String ID_KEY = "id";
    private static final String LEVEL_KEY = "lvl";

    private static NbtCompound getAugmentsNbt(ItemStack stack) {
        NbtComponent component = stack.get(ModComponents.AUGMENTS);
        return component == null ? new NbtCompound() : component.copyNbt();
    }

    private static void setAugmentsNbt(ItemStack stack, NbtCompound nbt) {
        if (nbt.isEmpty()) {
            stack.remove(ModComponents.AUGMENTS);
        } else {
            stack.set(ModComponents.AUGMENTS, NbtComponent.of(nbt));
        }
    }

    public static int getAugmentLevel(ItemStack stack, String augmentId) {
        NbtCompound nbt = getAugmentsNbt(stack);
        if (!nbt.contains(AUGMENTS_KEY, NbtElement.LIST_TYPE)) {
            return 0;
        }
        NbtList augments = nbt.getList(AUGMENTS_KEY, NbtElement.COMPOUND_TYPE);
        for (int i = 0; i < augments.size(); i++) {
            NbtCompound augment = augments.getCompound(i);
            if (augmentId.equals(augment.getString(ID_KEY))) {
                return augment.getInt(LEVEL_KEY);
            }
        }
        return 0;
    }

    public static boolean freeAugments(ItemStack stack) {
        int maxSlots = 0;
        if (stack.isIn(ModTags.Items.THREE_AUGMENT_SLOTS)) {
            maxSlots = 3;
        } else if (stack.isIn(ModTags.Items.TWO_AUGMENT_SLOTS)) {
            maxSlots = 2;
        } else if (stack.isIn(ModTags.Items.ONE_AUGMENT_SLOT)) {
            maxSlots = 1;
        }
        int slotExpansion = getAugmentLevel(stack, "tlotd:slot_expansion");
        if (slotExpansion > 0) {
            maxSlots += 1 + slotExpansion;
        }
        NbtCompound nbt = getAugmentsNbt(stack);
        if (!nbt.contains(AUGMENTS_KEY, NbtElement.LIST_TYPE)) {
            return maxSlots > 0;
        }
        NbtList augments = nbt.getList(AUGMENTS_KEY, NbtElement.COMPOUND_TYPE);
        return augments.size() < maxSlots;
    }

    public static boolean canApplyAugment(ItemStack stack, String augmentId, int max) {
        int currentLevel = getAugmentLevel(stack, augmentId);
        return (augmentId.equals("tlotd:slot_expansion") || freeAugments(stack) || currentLevel > 0) && currentLevel < max;
    }

    public static boolean hasAugments(ItemStack stack) {
        NbtCompound nbt = getAugmentsNbt(stack);
        return nbt.contains(AUGMENTS_KEY, NbtElement.LIST_TYPE) && !nbt.getList(AUGMENTS_KEY, NbtElement.COMPOUND_TYPE).isEmpty();
    }

    public static void addOrUpdateAugment(ItemStack stack, String augmentId, int newLevel, int max) {
        if (stack.isEmpty()) {
            return;
        }
        NbtCompound nbt = getAugmentsNbt(stack);
        NbtList augments;
        if (nbt.contains(AUGMENTS_KEY, NbtElement.LIST_TYPE)) {
            augments = nbt.getList(AUGMENTS_KEY, NbtElement.COMPOUND_TYPE);
        } else {
            augments = new NbtList();
            nbt.put(AUGMENTS_KEY, augments);
        }
        for (int i = 0; i < augments.size(); i++) {
            NbtCompound augment = augments.getCompound(i);
            if (augmentId.equals(augment.getString(ID_KEY))) {
                int currentLevel = augment.getInt(LEVEL_KEY);
                int finalLevel = Math.max(currentLevel + 1, newLevel);
                augment.putInt(LEVEL_KEY, Math.min(finalLevel, max));
                setAugmentsNbt(stack, nbt);
                return;
            }
        }
        NbtCompound newAugment = new NbtCompound();
        newAugment.putString(ID_KEY, augmentId);
        newAugment.putInt(LEVEL_KEY, Math.min(newLevel, max));
        augments.add(newAugment);
        setAugmentsNbt(stack, nbt);
    }

    public static void removeAugments(ItemStack stack) {
        if (stack.isEmpty()) {
            return;
        }
        stack.remove(ModComponents.AUGMENTS);
        NbtComponent customData = stack.get(DataComponentTypes.CUSTOM_DATA);
        if (customData == null) {
            return;
        }
        NbtCompound nbt = customData.copyNbt();
        nbt.remove("mekData");
        nbt.remove("BotariumData");
        if (nbt.isEmpty()) {
            stack.remove(DataComponentTypes.CUSTOM_DATA);
        } else {
            stack.set(DataComponentTypes.CUSTOM_DATA, NbtComponent.of(nbt));
        }
    }
}