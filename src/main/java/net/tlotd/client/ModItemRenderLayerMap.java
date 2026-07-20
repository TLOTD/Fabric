package net.tlotd.client;

import net.minecraft.block.BlockState;
import net.minecraft.client.item.ModelPredicateProviderRegistry;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.tlotd.TLOTD;
import net.tlotd.item.ModItems;
import net.tlotd.item.custom.MithrilMirrorItem;
import net.tlotd.util.AdAstraGasNbtHelper;
import net.tlotd.util.AugmentNbtHelper;
import net.tlotd.util.ItemHeatHelper;
import net.tlotd.util.ModTags;

import java.util.Comparator;

public class ModItemRenderLayerMap {
    public static void registerItemRenderLayerMaps() {
        ModelPredicateProviderRegistry.register(
                ModItems.MITHRIL_INGOT, new Identifier(TLOTD.MOD_ID, "temperature"), (stack, world, entity, seed) -> {
                    if (entity == null) return 0f;
                    if (ItemHeatHelper.getTemperature(stack) > 3000) return 0.3f;
                    else if (ItemHeatHelper.getTemperature(stack) > 1500) return 0.2f;
                    else if (ItemHeatHelper.getTemperature(stack) >= 500) return 0.1f;
                    return 0f;
                }
        );
        ModelPredicateProviderRegistry.register(
                ModItems.ROUGH_MITHRIL_INGOT, new Identifier(TLOTD.MOD_ID, "temperature"), (stack, world, entity, seed) -> {
                    if (entity == null) return 0f;
                    if (ItemHeatHelper.getTemperature(stack) > 3000) return 0.3f;
                    else if (ItemHeatHelper.getTemperature(stack) > 1500) return 0.2f;
                    else if (ItemHeatHelper.getTemperature(stack) >= 500) return 0.1f;
                    return 0f;
                }
        );
        ModelPredicateProviderRegistry.register(
                ModItems.GONDORIAN_SHIELD, new Identifier(TLOTD.MOD_ID, "blocking"), (stack, world, entity, seed) -> {
                    if (entity == null) return 0f;
                    if (entity.isUsingItem()) return 1f;
                    return 0f;
                }
        );
        ModelPredicateProviderRegistry.register(
                ModItems.GONDORIAN_TOWER_SHIELD, new Identifier(TLOTD.MOD_ID, "blocking"), (stack, world, entity, seed) -> {
                    if (entity == null) return 0f;
                    if (entity.isUsingItem()) return 1f;
                    return 0f;
                }
        );
        ModelPredicateProviderRegistry.register(
                ModItems.GONDORIAN_KINGS_GUARD_TOWER_SHIELD, new Identifier(TLOTD.MOD_ID, "blocking"), (stack, world, entity, seed) -> {
                    if (entity == null) return 0f;
                    if (entity.isUsingItem()) return 1f;
                    return 0f;
                }
        );
        ModelPredicateProviderRegistry.register(
                ModItems.GONDORIAN_KNIGHT_SHIELD, new Identifier(TLOTD.MOD_ID, "blocking"), (stack, world, entity, seed) -> {
                    if (entity == null) return 0f;
                    if (entity.isUsingItem()) return 1f;
                    return 0f;
                }
        );
        ModelPredicateProviderRegistry.register(
                ModItems.GONDORIAN_ORNAMENTED_KNIGHT_SHIELD, new Identifier(TLOTD.MOD_ID, "blocking"), (stack, world, entity, seed) -> {
                    if (entity == null) return 0f;
                    if (entity.isUsingItem()) return 1f;
                    return 0f;
                }
        );

        ModelPredicateProviderRegistry.register(
                ModItems.ANDURIL, new Identifier(TLOTD.MOD_ID, "blocking"), (stack, world, entity, seed) -> {
                    if (entity == null) return 0f;
                    if (entity.isUsingItem()) return 1f;
                    return 0f;
                }
        );

        ModelPredicateProviderRegistry.register(
                ModItems.MITHRIL_MIRROR, new Identifier(TLOTD.MOD_ID, "charge"), (stack, world, entity, seed) -> {
                    if (stack.isEmpty()) return 1.0f;
                    if (!stack.hasNbt()) return 1.0f;
                    NbtCompound tag = stack.getNbt();
                    int used = tag.getInt("ChargesUsed");
                    int maxCharges = MithrilMirrorItem.getMaxCharges(stack);
                    return Math.max(1.0f - ((float) used / (float) maxCharges), 0);
                }
        );
        ModelPredicateProviderRegistry.register(
                ModItems.PIPE, new Identifier(TLOTD.MOD_ID, "content"), (stack, world, entity, seed) -> {
                    if (stack.isEmpty()) return 0.0f;
                    if (!stack.hasNbt()) return 0.0f;
                    NbtCompound tag = stack.getNbt();
                    String content = tag.getString("Content");
                    return switch (content) {
                        case "tlotd:pipe_weed" -> 0.1f;
                        default -> 0.0f;
                    };
                }
        );
        ModelPredicateProviderRegistry.register(
                ModItems.ENVELOPE, new Identifier(TLOTD.MOD_ID, "sealed"), (stack, world, entity, seed) -> {
                    if (stack.isEmpty()) return 0.0f;
                    if (!stack.hasNbt()) return 0.0f;
                    NbtCompound tag = stack.getNbt();
                    if (tag.getBoolean("Sealed")) {
                        return  0.1f;
                    } else {
                        return  0.0f;
                    }
                }
        );
        ModelPredicateProviderRegistry.register(
                ModItems.GAS_CYLINDER, new Identifier(TLOTD.MOD_ID, "gas"), (stack, world, entity, seed) -> {
                    if (stack.isEmpty()) return 0.0f;
                    String gas = AdAstraGasNbtHelper.getGas(stack);
                    return switch (gas) {
                        case AdAstraGasNbtHelper.AD_ASTRA_OXYGEN_ID -> 0.1f;
                        case AdAstraGasNbtHelper.TLOTD_PIPE_WEED_SMOKE -> 0.2f;
                        case AdAstraGasNbtHelper.TLOTD_WITHERED_AIR -> 0.3f;
                        default -> 0.0f;
                    };
                }
        );
        ModelPredicateProviderRegistry.register(
                ModItems.EMF_READER, new Identifier(TLOTD.MOD_ID, "proximity"), (stack, world, entity, seed) -> {
                    if (entity == null || world == null) return 0.0f;
                    if (stack.isEmpty() || stack.getItem() != ModItems.EMF_READER) return 0.0f;
                    Entity nearest = world.getOtherEntities(entity, entity.getBoundingBox().expand(15.0), e -> e != entity && e instanceof LivingEntity)
                            .stream()
                            .min(Comparator.comparingDouble(entity::squaredDistanceTo))
                            .orElse(null);
                    if (nearest == null) return 0.0f;
                    double dist = Math.sqrt(entity.squaredDistanceTo(nearest));
                    //System.out.println("Proximity value: " + dist);
                    if (dist <= 3.0) return 1.0f;
                    else if (dist <= 6.0) return 0.8f;
                    else if (dist <= 9.0) return 0.6f;
                    else if (dist <= 12.0) return 0.4f;
                    else if (dist <= 16.0) return 0.2f;
                    else return 0.0f;
                }
        );
        ModelPredicateProviderRegistry.register(
                ModItems.MITHRIL_SWORD, new Identifier(TLOTD.MOD_ID, "proximity"), (stack, world, entity, seed) -> {
                    if (entity == null || world == null) return 0.0f;
                    if (stack.isEmpty() || !(stack.getItem() == ModItems.MITHRIL_SWORD && (AugmentNbtHelper.getAugmentLevel(stack, "tlotd:elder_days_elven_forged") > 0))) return 0.0f;
                    Entity nearest = world.getOtherEntities(entity, entity.getBoundingBox().expand(31.0), e -> e != entity && e instanceof HostileEntity)
                            .stream()
                            .min(Comparator.comparingDouble(entity::squaredDistanceTo))
                            .orElse(null);
                    if (nearest == null) return 0.0f;
                    double dist = Math.sqrt(entity.squaredDistanceTo(nearest));
                    //System.out.println("Proximity value: " + dist);
                    if (dist <= 4.0) return 1.0f;
                    else if (dist <= 8.0) return 0.8f;
                    else if (dist <= 16) return 0.6f;
                    else if (dist <= 24) return 0.4f;
                    else if (dist <= 32) return 0.2f;
                    else return 0.0f;
                }
        );
        ModelPredicateProviderRegistry.register(
                ModItems.MITHRIL_PICKAXE,
                new Identifier(TLOTD.MOD_ID, "proximity"),
                (stack, world, entity, seed) -> {
                    if (entity == null || world == null) return 0.0f;
                    if (stack.isEmpty() || !(stack.getItem() == ModItems.MITHRIL_PICKAXE
                            && AugmentNbtHelper.getAugmentLevel(stack, "tlotd:elder_days_elven_forged") > 0)) {
                        return 0.0f;
                    }
                    BlockPos origin = entity.getBlockPos();
                    int maxRadius = 8;
                    for (int r = 0; r <= maxRadius; r++) {
                        for (int x = -r; x <= r; x++) {
                            for (int y = -r; y <= r; y++) {
                                for (int z = -r; z <= r; z++) {
                                    if (Math.abs(x) != r && Math.abs(y) != r && Math.abs(z) != r) continue;
                                    BlockPos pos = origin.add(x, y, z);
                                    BlockState state = world.getBlockState(pos);
                                    if (state.isIn(ModTags.Blocks.MITHRIL_REACTING)) {
                                        double dist = Math.sqrt(entity.squaredDistanceTo(
                                                pos.getX() + 0.5,
                                                pos.getY() + 0.5,
                                                pos.getZ() + 0.5
                                        ));
                                        //System.out.println("Proximity value: " + dist);
                                        if (dist <= 2.0) return 1.0f;
                                        else if (dist <= 3.5) return 0.8f;
                                        else if (dist <= 4.5) return 0.6f;
                                        else if (dist <= 6.5) return 0.4f;
                                        else if (dist <= 9.0) return 0.2f;
                                        else return 0.0f;
                                    }
                                }
                            }
                        }
                    }
                    return 0.0f;
                }
        );
    }
}