package net.tlotd.util;

import net.fabricmc.fabric.api.entity.event.v1.ServerLivingEntityEvents;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.damage.DamageSources;
import net.minecraft.entity.damage.DamageType;
import net.minecraft.entity.damage.DamageTypes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.random.Random;
import net.tlotd.TLOTD;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

import static net.tlotd.util.AugmentNbtHelper.getAugmentLevel;

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
                    if ((getAugmentLevel(armor, "tlotd:lead_plating") > 1 || (armor.isIn(ModTags.Items.RADIATION_PROTECTION_WITHOUT_HELMET)) && armor.getDamage()+1 < armor.getMaxDamage())) {
                        radiationProtectionHelmet++;
                        radiationProtection++;
                    } else if ((getAugmentLevel(armor, "tlotd:lead_plating") == 1 || (armor.isIn(ModTags.Items.RADIATION_PROTECTION)) && armor.getDamage()+1 < armor.getMaxDamage())) {
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
            } else if (damageSource.isOf(ModDamageTypes.SUBSPACE_SICKNESS)) {
                float subspaceProtectionHelmet = 0f;
                float subspaceProtection = 0f;
                for (ItemStack armor : player.getArmorItems()) {
                    if ((getAugmentLevel(armor, "tlotd:dimensional_cohesion") > 1 || (armor.isIn(ModTags.Items.SUBSPACE_PROTECTION_WITHOUT_HELMET)) && armor.getDamage()+1 < armor.getMaxDamage())) {
                        subspaceProtectionHelmet++;
                        subspaceProtection++;
                    } else if ((getAugmentLevel(armor, "tlotd:dimensional_cohesion") == 1 || (armor.isIn(ModTags.Items.SUBSPACE_PROTECTION)) && armor.getDamage()+1 < armor.getMaxDamage())) {
                        subspaceProtection++;
                    }
                }
                if ((subspaceProtectionHelmet >= 3) || (subspaceProtection >= 4)) {
                    int maxSlot;
                    if (subspaceProtectionHelmet >= 3) { maxSlot = 3; } else { maxSlot = 4; }
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
            } else if (damageSource.isOf(DamageTypes.DROWN) || damageSource.getType().msgId().contains("hypoxia") || damageSource.getType().msgId().contains("oxygen")) {
                int airtightArmor = 0;
                boolean oxygenTank = false;
                for (ItemStack armor : player.getArmorItems()) {
                    if ((getAugmentLevel(armor, "tlotd:airtight_seals") > 0) || armor.isIn(ModTags.Items.HYPOXIA_PROTECTION)) {
                        airtightArmor++;
                    }
                    if ((getAugmentLevel(armor, "tlotd:oxygen_tank") > 0) || armor.isIn(ModTags.Items.HYPOXIA_PROTECTION)) {
                        oxygenTank = true;
                    }
                }
                if ((airtightArmor >= 4) && oxygenTank && AdAstraOxygenNbtHelper.getOxygen(player.getInventory().getArmorStack(2)) > 0) {
                    if (oxygenTick.get() >= 20) {
                        oxygenTick.set(0);
                        long current = AdAstraOxygenNbtHelper.getOxygen(player.getInventory().getArmorStack(2));
                        AdAstraOxygenNbtHelper.setOxygen(player.getInventory().getArmorStack(2), current - 10);
                    } else {
                        oxygenTick.getAndIncrement();
                    }
                    return false;
                }
            } else if (damageSource.isOf(DamageTypes.LAVA) || damageSource.isOf(DamageTypes.ON_FIRE) || damageSource.isOf(DamageTypes.IN_FIRE) || damageSource.isOf(DamageTypes.HOT_FLOOR) || damageSource.getType().msgId().contains("fire") && !damageSource.getType().msgId().contains("gunfire")) {
                int fireProtection = 0;
                int lavaProtection = 0;
                for (ItemStack armor : player.getArmorItems()) {
                    if (getAugmentLevel(armor, "tlotd:dragon_scale_plating") > 2) {
                        fireProtection = fireProtection+3;
                        if (armor.getDamage()+1 < armor.getMaxDamage()) {
                            lavaProtection++;
                        }
                    } else if (getAugmentLevel(armor, "tlotd:dragon_scale_plating") == 2) {
                        fireProtection = fireProtection+2;
                    } else if (getAugmentLevel(armor, "tlotd:dragon_scale_plating") == 1) {
                        fireProtection++;
                    }
                }
                if (damageSource.isOf(DamageTypes.LAVA)) {
                    if (lavaProtection > 0f) {
                        float reducedAmount = Math.max(amount-lavaProtection, 0);
                        if (reducedAmount > 0) {
                            ignoredDamagePlayers.add(player.getUuid());
                            player.damage(ModDamageTypes.of(livingEntity.getWorld(), DamageTypes.LAVA), reducedAmount);
                            ignoredDamagePlayers.remove(player.getUuid());
                        }
                        List<ItemStack> armorList = new ArrayList<>();
                        List<Integer> slotIndices = new ArrayList<>();
                        for (int slot = 0; slot < 4; slot++) {
                            ItemStack stack = player.getInventory().armor.get(slot);
                            if (!stack.isEmpty() && stack.isDamageable()) {
                                armorList.add(stack);
                                slotIndices.add(slot);
                            }
                        }
                        Random random = player.getRandom();
                        int index = random.nextInt(armorList.size());
                        int chance = random.nextInt(20);
                        if (chance == 19) {
                            ItemStack armorPiece = armorList.get(index);
                            armorPiece.damage(1, player, (p) -> {
                            });
                            if (armorPiece.getDamage() >= armorPiece.getMaxDamage()) {
                                player.getInventory().armor.set(slotIndices.get(index), ItemStack.EMPTY);
                            }
                        }
                        return false;
                    }
                }
                else if (fireProtection > 0f) {
                    float reducedAmount = Math.max(amount-(0.25f*fireProtection), 0);
                    if (reducedAmount > 0) {
                        ignoredDamagePlayers.add(player.getUuid());
                        player.damage(ModDamageTypes.of(livingEntity.getWorld(), DamageTypes.ON_FIRE), reducedAmount);
                        ignoredDamagePlayers.remove(player.getUuid());
                    }
                    return false;
                }
            } else if (damageSource.getType().msgId().contains("bullet") || damageSource.getType().msgId().contains("ammo") ||damageSource.getType().msgId().contains("round") || damageSource.getType().msgId().contains("gunfire")) {
                int bulletPoints = 0;
                int totalPoints = 0;
                for (ItemStack armor : player.getArmorItems()) {
                    if (armor.isIn(ModTags.Items.BULLET_PROOF_ARMOR_III)) {
                        bulletPoints += 3;
                    } else if (armor.isIn(ModTags.Items.BULLET_PROOF_ARMOR_II)) {
                        bulletPoints += 2;
                    } else if (armor.isIn(ModTags.Items.BULLET_PROOF_ARMOR)) {
                        bulletPoints += 1;
                    }
                    int bulletAugmentLevel = getAugmentLevel(armor, "tlotd:bullet_resistance");
                    int augmentLevel = getAugmentLevel(armor, "tlotd:mithril_chainmail");
                    bulletPoints += Math.min(bulletAugmentLevel, 3);
                    totalPoints += Math.min(augmentLevel, 3);
                }
                float dmgAmount = amount;
                if (totalPoints > 0) {
                    float resistance = (totalPoints / 12.0f) * 0.25f;
                    resistance = Math.min(resistance, 0.25f);
                    dmgAmount = amount * (1.0f - resistance);
                }
                if (bulletPoints > 0) {
                    float resistance = (bulletPoints / 24.0f) * 0.9f;
                    resistance = Math.min(resistance, 0.9f);
                    float reducedAmount = dmgAmount * (1.0f - resistance);
                    if (reducedAmount > 1.5f) {
                        ignoredDamagePlayers.add(player.getUuid());
                        player.damage(damageSource, reducedAmount);
                        ignoredDamagePlayers.remove(player.getUuid());
                    }
                    return false;
                }
            } else {
                int totalPoints = 0;
                for (ItemStack armor : player.getArmorItems()) {
                    int augmentLevel = getAugmentLevel(armor, "tlotd:mithril_chainmail");
                    totalPoints += Math.min(augmentLevel, 3);
                }
                if (totalPoints > 0) {
                    float resistance = (totalPoints / 12.0f) * 0.25f;
                    resistance = Math.min(resistance, 0.25f);
                    float reducedAmount = amount * (1.0f - resistance);
                    ignoredDamagePlayers.add(player.getUuid());
                    player.damage(damageSource, reducedAmount);
                    ignoredDamagePlayers.remove(player.getUuid());
                    return false;
                }
            }
            return true;
        }));
        TLOTD.LOGGER.info("Registering Special Armor Protection for " + TLOTD.MOD_ID);
    }
}
