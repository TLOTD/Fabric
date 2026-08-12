package net.tlotd.util;

import net.fabricmc.fabric.api.entity.event.v1.ServerLivingEntityEvents;
import net.fabricmc.fabric.api.transfer.v1.context.ContainerItemContext;
import net.fabricmc.fabric.api.transfer.v1.item.ItemVariant;
import net.fabricmc.fabric.api.transfer.v1.item.PlayerInventoryStorage;
import net.fabricmc.fabric.api.transfer.v1.storage.base.SingleSlotStorage;
import net.fabricmc.fabric.api.transfer.v1.transaction.Transaction;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.damage.DamageTypes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.math.random.Random;
import net.tlotd.TLOTD;
import net.tlotd.effect.ModEffects;
import net.tlotd.item.ModItems;
import net.tlotd.item.custom.HEVArmorItem;
import net.tlotd.item.custom.SpaceSuitArmorItem;
import net.tlotd.sound.ModSounds;
import team.reborn.energy.api.EnergyStorage;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

import static net.tlotd.util.AugmentNbtHelper.getAugmentLevel;

public class DamageMitigation {

    private static boolean isBlockable(DamageSource damageSource) {
        return (damageSource.isOf(DamageTypes.IN_FIRE) || damageSource.isOf(DamageTypes.LIGHTNING_BOLT) || damageSource.isOf(DamageTypes.ON_FIRE) || damageSource.isOf(DamageTypes.LAVA) || damageSource.isOf(DamageTypes.HOT_FLOOR) || damageSource.isOf(DamageTypes.IN_WALL) || damageSource.isOf(DamageTypes.CRAMMING) || damageSource.isOf(DamageTypes.CACTUS) || damageSource.isOf(DamageTypes.FALL) || damageSource.isOf(DamageTypes.FLY_INTO_WALL) || damageSource.isOf(DamageTypes.GENERIC) || damageSource.isOf(DamageTypes.SWEET_BERRY_BUSH) || damageSource.isOf(DamageTypes.STALAGMITE) || damageSource.isOf(DamageTypes.FALLING_BLOCK) || damageSource.isOf(DamageTypes.FALLING_ANVIL) || damageSource.isOf(DamageTypes.FALLING_STALACTITE) || damageSource.isOf(DamageTypes.STING) || damageSource.isOf(DamageTypes.MOB_ATTACK) || damageSource.isOf(DamageTypes.MOB_ATTACK_NO_AGGRO) || damageSource.isOf(DamageTypes.PLAYER_ATTACK) || damageSource.isOf(DamageTypes.ARROW) || damageSource.isOf(DamageTypes.TRIDENT) || damageSource.isOf(DamageTypes.MOB_PROJECTILE) || damageSource.isOf(DamageTypes.FIREWORKS) || damageSource.isOf(DamageTypes.FIREBALL) || damageSource.isOf(DamageTypes.UNATTRIBUTED_FIREBALL) || damageSource.isOf(DamageTypes.WITHER_SKULL) || damageSource.isOf(DamageTypes.THROWN) || damageSource.isOf(DamageTypes.THORNS) || damageSource.isOf(DamageTypes.EXPLOSION) || damageSource.isOf(DamageTypes.PLAYER_EXPLOSION) || damageSource.isOf(DamageTypes.SONIC_BOOM) || damageSource.isOf(DamageTypes.BAD_RESPAWN_POINT) || damageSource.isOf(DamageTypes.GENERIC_KILL));
    }

    public static void registerAllowedDamages() {
        final Set<UUID> ignoredDamagePlayers = Collections.newSetFromMap(new WeakHashMap<>());
        AtomicInteger oxygenTick = new AtomicInteger();
        ServerLivingEntityEvents.ALLOW_DAMAGE.register(((livingEntity, damageSource, amount) -> {
            if (!(livingEntity instanceof PlayerEntity player)) return true;
            if (ignoredDamagePlayers.contains(player.getUuid())) return true;
            ItemStack chestplate = player.getInventory().getArmorStack(2);
            if (chestplate.isOf(ModItems.HEV_SUIT_CHESTPLATE) && player.getInventory().getArmorStack(1).isOf(ModItems.HEV_SUIT_LEGGINGS) && player.getInventory().getArmorStack(0).isOf(ModItems.HEV_SUIT_BOOTS) && (HEVArmorItem.getStoredEnergy(chestplate) >= (amount * 500.0f))
                    && isBlockable(damageSource) || damageSource.getType().msgId().contains("bullet") || damageSource.getType().msgId().contains("ammo") || damageSource.getType().msgId().contains("round") || damageSource.getType().msgId().contains("gunfire") || damageSource.isOf(ModDamageTypes.SUBSPACE_SICKNESS) || damageSource.getType().msgId().contains("radiation") || damageSource.getType().msgId().contains("radiated") || damageSource.getType().msgId().contains("freeze") || damageSource.getType().msgId().contains("freezing") || damageSource.getType().msgId().contains("hypothermia")) {
                long energyRequired = (long) (amount * 500.0f);
                PlayerInventoryStorage inventoryStorage = PlayerInventoryStorage.of(player);
                SingleSlotStorage<ItemVariant> chestplateSlot = inventoryStorage.getSlot(38);
                ContainerItemContext context = ContainerItemContext.ofPlayerSlot(player, chestplateSlot);
                EnergyStorage storage = HEVArmorItem.getEnergyStorage(context);
                if (storage != null && storage.supportsExtraction()) {
                    try (Transaction transaction = Transaction.openOuter()) {
                        long extracted = storage.extract(energyRequired, transaction);
                        if (extracted == energyRequired) {
                            transaction.commit();
                            float reducedAmount = amount * 0.5f;
                            ignoredDamagePlayers.add(player.getUuid());
                            player.getWorld().playSound(null, player.getBlockPos(), SoundEvents.UI_TOAST_IN, SoundCategory.PLAYERS, 0.8f, 0.8f);
                            try {
                                player.damage(damageSource, reducedAmount);
                            } finally {
                                ignoredDamagePlayers.remove(player.getUuid());
                            }
                            return false;
                        }
                    }
                }
            } else if (damageSource.getType().msgId().contains("freeze") || damageSource.getType().msgId().contains("freezing") || damageSource.getType().msgId().contains("hypothermia")) {
                if (player.hasStatusEffect(ModEffects.DRUNKENNESS)) {
                    float reducedAmount = amount - (player.getStatusEffect(ModEffects.DRUNKENNESS).getAmplifier() + 1);
                    if (reducedAmount > 0) {
                        ignoredDamagePlayers.add(player.getUuid());
                        player.damage(damageSource, reducedAmount);
                        ignoredDamagePlayers.remove(player.getUuid());
                    }
                    return false;
                } else return true;
            } else if (damageSource.getType().msgId().contains("radiation") || damageSource.getType().msgId().contains("radiated")) {
                float radiationProtectionHelmet = 0f;
                float radiationProtection = 0f;
                for (ItemStack armor : player.getArmorItems()) {
                    if ((getAugmentLevel(armor, "tlotd:lead_plating") > 1 || (armor.isIn(ModTags.Items.RADIATION_PROTECTION_WITHOUT_HELMET)) && armor.getDamage() + 1 < armor.getMaxDamage())) {
                        radiationProtectionHelmet++;
                        radiationProtection++;
                    } else if ((getAugmentLevel(armor, "tlotd:lead_plating") == 1 || (armor.isIn(ModTags.Items.RADIATION_PROTECTION)) && armor.getDamage() + 1 < armor.getMaxDamage())) {
                        radiationProtection++;
                    }
                }
                if (radiationProtectionHelmet >= 3 || radiationProtection >= 4) {
                    int maxSlot;
                    if (radiationProtectionHelmet >= 3) {
                        maxSlot = 3;
                    } else {
                        maxSlot = 4;
                    }
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
                    if ((getAugmentLevel(armor, "tlotd:dimensional_cohesion") > 1 || (armor.isIn(ModTags.Items.SUBSPACE_PROTECTION_WITHOUT_HELMET)) && armor.getDamage() + 1 < armor.getMaxDamage())) {
                        subspaceProtectionHelmet++;
                        subspaceProtection++;
                    } else if ((getAugmentLevel(armor, "tlotd:dimensional_cohesion") == 1 || (armor.isIn(ModTags.Items.SUBSPACE_PROTECTION)) && armor.getDamage() + 1 < armor.getMaxDamage())) {
                        subspaceProtection++;
                    }
                }
                if (subspaceProtectionHelmet >= 3 || subspaceProtection >= 4) {
                    int maxSlot;
                    if (subspaceProtectionHelmet >= 3) {
                        maxSlot = 3;
                    } else {
                        maxSlot = 4;
                    }
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
            } else if (damageSource.getType().msgId().contains("hypoxia") || damageSource.getType().msgId().contains("oxygen")) {
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
                if ((airtightArmor >= 4) && oxygenTank && AdAstraGasNbtHelper.canMitigateDamage(chestplate)) {
                    if (chestplate.getItem() instanceof SpaceSuitArmorItem) {
                        if (oxygenTick.get() >= 20) {
                            oxygenTick.set(0);
                            AdAstraGasNbtHelper.consumeMitigationGas(chestplate, 10);
                        } else {
                            oxygenTick.getAndIncrement();
                        }
                    } else {
                        if (oxygenTick.get() >= 20) {
                            oxygenTick.set(0);
                            long current = AdAstraGasNbtHelper.getOxygen(chestplate);
                            AdAstraGasNbtHelper.setOxygen(chestplate, current - 10);
                        } else {
                            oxygenTick.getAndIncrement();
                        }
                    }
                    return false;
                }
            } else if (damageSource.isOf(DamageTypes.LAVA) || damageSource.isOf(DamageTypes.ON_FIRE) || damageSource.isOf(DamageTypes.IN_FIRE) || damageSource.isOf(DamageTypes.HOT_FLOOR) || damageSource.getType().msgId().contains("fire") && !damageSource.getType().msgId().contains("gunfire")) {
                int fireProtection = 0;
                int lavaProtection = 0;
                for (ItemStack armor : player.getArmorItems()) {
                    if (getAugmentLevel(armor, "tlotd:dragon_scale_plating") > 2) {
                        fireProtection = fireProtection + 3;
                        if (armor.getDamage() + 1 < armor.getMaxDamage()) {
                            lavaProtection++;
                        }
                    } else if (getAugmentLevel(armor, "tlotd:dragon_scale_plating") == 2) {
                        fireProtection = fireProtection + 2;
                    } else if (getAugmentLevel(armor, "tlotd:dragon_scale_plating") == 1) {
                        fireProtection++;
                    }
                }
                if (damageSource.isOf(DamageTypes.LAVA)) {
                    if (lavaProtection > 0f) {
                        float reducedAmount = Math.max(amount - lavaProtection, 0);
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
                } else if (fireProtection > 0f) {
                    float reducedAmount = Math.max(amount - (0.25f * fireProtection), 0);
                    if (reducedAmount > 0) {
                        ignoredDamagePlayers.add(player.getUuid());
                        player.damage(ModDamageTypes.of(livingEntity.getWorld(), DamageTypes.ON_FIRE), reducedAmount);
                        ignoredDamagePlayers.remove(player.getUuid());
                    }
                    return false;
                }
            } else if (damageSource.getType().msgId().contains("bullet") || damageSource.getType().msgId().contains("ammo") || damageSource.getType().msgId().contains("round") || damageSource.getType().msgId().contains("gunfire")) {
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
            } else if (isBlockable(damageSource)) {
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