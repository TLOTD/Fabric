package net.tlotd.entity;

import net.fabricmc.fabric.api.entity.event.v1.ServerLivingEntityEvents;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.math.random.Random;
import net.tlotd.TLOTD;
import net.tlotd.util.AdAstraOxygenNbtHelper;
import net.tlotd.util.ModDamageTypes;
import net.tlotd.util.ModTags;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class ModArmorProtection {
    public static void registerAllowedDamages() {
        final Set<UUID> ignoredDamagePlayers = Collections.newSetFromMap(new WeakHashMap<>());
        AtomicInteger oxygenTick = new AtomicInteger();
        ServerLivingEntityEvents.ALLOW_DAMAGE.register(((livingEntity, damageSource, amount) -> {
            if (!(livingEntity instanceof PlayerEntity player)) return true;
            if (ignoredDamagePlayers.contains(player.getUuid())) return true;

            if (damageSource.getType().msgId().contains("radiation") || damageSource.getType().msgId().contains("radiated")) {
                float radiationProtectionHelmet = 0f;
                float radiationProtection = 0f;
                for (ItemStack armor : player.getArmorItems()) {
                    if (armor.isIn(ModTags.Items.RADIATION_PROTECTION_WITHOUT_HELMET) && armor.getDamage()+1 < armor.getMaxDamage()) {
                        radiationProtectionHelmet++;
                        radiationProtection++;
                    } else if (armor.isIn(ModTags.Items.RADIATION_PROTECTION) && armor.getDamage()+1 < armor.getMaxDamage()) {
                        radiationProtection++;
                    }
                }
                if ((radiationProtectionHelmet >= 3) || (radiationProtection >= 4)) {
                    int maxSlot;
                    if (radiationProtectionHelmet >= 3) { maxSlot = 3; } else { maxSlot = 4; }
                    List<ItemStack> armorList = new ArrayList<>();
                    List<Integer> slotIndices = new ArrayList<>();
                    for (int slot = 0; slot < maxSlot; slot++) {
                        ItemStack stack = player.getInventory().armor.get(slot);
                        if (!stack.isEmpty() && stack.isDamageable()) {
                            armorList.add(stack);
                            slotIndices.add(slot);
                        }
                    }
                    Random random = player.getRandom();
                    int index = random.nextInt(armorList.size());
                    ItemStack armorPiece = armorList.get(index);
                    armorPiece.damage(1, player, (p) -> {
                    });
                    if (armorPiece.getDamage() >= armorPiece.getMaxDamage()) {
                        player.getInventory().armor.set(slotIndices.get(index), ItemStack.EMPTY);
                    }
                    return false;
                }
            } else if (damageSource.getType().msgId().contains("hypoxia")) {
                float hypoxiaProtection = 0f;
                for (ItemStack armor : player.getArmorItems()) {
                    if (armor.isIn(ModTags.Items.HYPOXIA_PROTECTION)) {
                        hypoxiaProtection++;
                    }
                }
                if (hypoxiaProtection >= 4 && AdAstraOxygenNbtHelper.getOxygen(player.getInventory().getArmorStack(2)) > 0) {
                    if (oxygenTick.get() >= 20) {
                        oxygenTick.set(0);
                        long current = AdAstraOxygenNbtHelper.getOxygen(player.getInventory().getArmorStack(2));
                        AdAstraOxygenNbtHelper.setOxygen(player.getInventory().getArmorStack(2), current - 10);
                    } else {
                        oxygenTick.getAndIncrement();
                    }
                    return false;
                }
            } else if (damageSource.getType().msgId().contains("bullet") || damageSource.getType().msgId().contains("ammo") ||damageSource.getType().msgId().contains("round") || damageSource.getType().msgId().contains("gunfire")) {
                float armorProtection = 0f;
                for (ItemStack armor : player.getArmorItems()) {
                    if (armor.isIn(ModTags.Items.BULLET_PROOF_ARMOR_III)) {
                        armorProtection = armorProtection+3f;
                    } else if (armor.isIn(ModTags.Items.BULLET_PROOF_ARMOR_II)) {
                        armorProtection = armorProtection+2f;
                    } else if (armor.isIn(ModTags.Items.BULLET_PROOF_ARMOR)) {
                        armorProtection++;
                    }
                }
                if (armorProtection > 0f) {
                    float resistance = ((2f / 3f) * armorProtection);
                    resistance = Math.min(resistance, 0.8f);
                    float reducedAmount = amount * (1f - resistance);
                    if (reducedAmount > 1.5f) {
                        ignoredDamagePlayers.add(player.getUuid());
                        player.damage(ModDamageTypes.of(livingEntity.getWorld(), ModDamageTypes.BULLET), reducedAmount);
                        ignoredDamagePlayers.remove(player.getUuid());
                    }
                    return false;
                }
            }
            return true;
        }));

        TLOTD.LOGGER.info("Registering Special Armor Protection for " + TLOTD.MOD_ID);
    }
}
