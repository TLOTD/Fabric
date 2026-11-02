package net.tlotd.util;

import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.NbtComponent;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.tlotd.components.ModComponents;

import java.util.ArrayList;
import java.util.List;

public class AugmentNbtHelper {

    public static List<AugmentData> getAugments(ItemStack stack) {
        return stack.getOrDefault(ModComponents.AUGMENTS, List.of());
    }

    private static void setAugments(ItemStack stack, List<AugmentData> augments) {
        if (augments.isEmpty()) {
            stack.remove(ModComponents.AUGMENTS);
        } else {
            stack.set(ModComponents.AUGMENTS, List.copyOf(augments));
        }
    }

    public static int getAugmentLevel(ItemStack stack, String augmentId) {
        return getAugments(stack).stream().filter(augment -> augment.id().equals(augmentId)).mapToInt(AugmentData::lvl).findFirst().orElse(0);
    }

    public static int getMaxAugmentSlots(ItemStack stack) {
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
        return maxSlots;
    }

    public static boolean freeAugments(ItemStack stack) {
        return getAugments(stack).size() < getMaxAugmentSlots(stack);
    }

    public static boolean canApplyAugment(ItemStack stack, String augmentId, int max) {
        int currentLevel = getAugmentLevel(stack, augmentId);
        return (augmentId.equals("tlotd:slot_expansion") || freeAugments(stack) || currentLevel > 0) && currentLevel < max;
    }

    public static boolean hasAugments(ItemStack stack) {
        return !getAugments(stack).isEmpty();
    }

    public static void addOrUpdateAugment(ItemStack stack, String augmentId, int newLevel, int max) {
        if (stack.isEmpty()) {
            return;
        }
        List<AugmentData> augments = new ArrayList<>(getAugments(stack));
        for (int i = 0; i < augments.size(); i++) {
            AugmentData augment = augments.get(i);
            if (augment.id().equals(augmentId)) {
                int finalLevel = Math.max(augment.lvl() + 1, newLevel);
                augments.set(i, new AugmentData(augmentId, Math.min(finalLevel, max)));
                setAugments(stack, augments);
                return;
            }
        }
        augments.add(new AugmentData(augmentId, Math.min(newLevel, max)));
        setAugments(stack, augments);
    }

    public static void removeAugments(ItemStack stack) {
        if (stack.isEmpty()) {
            return;
        }
        stack.remove(ModComponents.AUGMENTS);
        AdAstraGasNbtHelper.clearAllGases(stack);
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