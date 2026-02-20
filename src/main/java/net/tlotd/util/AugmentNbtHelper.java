package net.tlotd.util;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtElement;
import net.minecraft.nbt.NbtList;

public class AugmentNbtHelper {

    public static int getAugmentLevel(ItemStack stack, String augmentId) {
        if (!stack.hasNbt()) {
            return 0;
        }
        NbtCompound nbt = stack.getNbt();
        if (nbt == null || !nbt.contains("Augments", NbtElement.LIST_TYPE)) {
            return 0;
        }
        NbtList augments = nbt.getList("Augments", NbtElement.COMPOUND_TYPE);
        for (int i = 0; i < augments.size(); i++) {
            NbtCompound augment = augments.getCompound(i);
            if (augmentId.equals(augment.getString("id"))) {
                return augment.getInt("lvl");
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
        if (getAugmentLevel(stack,"tlotd:slot_expansion") > 0) {
            maxSlots += 1+getAugmentLevel(stack,"tlotd:slot_expansion");
        }
        NbtCompound nbt = stack.getNbt();
        NbtList augments = nbt.getList("Augments", NbtElement.COMPOUND_TYPE);
        return augments.size() < maxSlots;
    }

    public static boolean canApplyAugment(ItemStack stack, String augmentId, int Max) {
        return (augmentId.equals("tlotd:slot_expansion") || freeAugments(stack) || getAugmentLevel(stack, augmentId) > 0) && getAugmentLevel(stack, augmentId) < Max;
    }

    public static boolean hasAugments(ItemStack stack) {
        if (!stack.hasNbt()) {
            return false;
        }
        NbtCompound nbt = stack.getNbt();
        return nbt != null && nbt.contains("Augments", NbtElement.LIST_TYPE);
    }

    public static void addOrUpdateAugment(ItemStack stack, String augmentId, int newLevel, int max) {
        if (stack.isEmpty()) return;
        NbtCompound nbt = stack.getOrCreateNbt();
        NbtList augments;
        if (nbt.contains("Augments", NbtElement.LIST_TYPE)) {
            augments = nbt.getList("Augments", NbtElement.COMPOUND_TYPE);
        } else {
            augments = new NbtList();
            nbt.put("Augments", augments);
        }
        for (int i = 0; i < augments.size(); i++) {
            NbtCompound augment = augments.getCompound(i);
            if (augmentId.equals(augment.getString("id"))) {
                int currentLevel = augment.getInt("lvl");
                int finalLevel = Math.max(currentLevel + 1, newLevel);
                augment.putInt("lvl", Math.min(finalLevel, max));
                return;
            }
        }
        NbtCompound newAugment = new NbtCompound();
        newAugment.putString("id", augmentId);
        newAugment.putInt("lvl", newLevel);
        augments.add(newAugment);
    }

    public static void removeAugments(ItemStack stack) {
        if (stack.isEmpty() || !stack.hasNbt()) {
            return;
        }
        NbtCompound nbt = stack.getNbt();
        if (nbt == null) {
            return;
        }
        nbt.remove("Augments");
        nbt.remove("mekData");
        nbt.remove("BotariumData");
        if (nbt.isEmpty()) {
            stack.setNbt(null);
        }
    }
}